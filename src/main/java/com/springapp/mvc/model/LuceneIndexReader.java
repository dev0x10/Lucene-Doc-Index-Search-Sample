package com.springapp.mvc.model;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.*;

public class LuceneIndexReader {

    private final Directory indexDir;
    private IndexReader indexReader;
    private Fields fields;
    private final StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_42);

    public LuceneIndexReader(Directory indexDir) throws IOException {
        this.indexDir = indexDir;
        //noinspection deprecation
        this.indexReader = IndexReader.open(this.indexDir);
        this.fields =  MultiFields.getFields(this.indexReader);
    }

    public long getTotalDocs() {
        return  this.indexReader.numDocs();
    }

    long getTermFreq(Term term) throws IOException {
        return this.indexReader.totalTermFreq(term);
    }

    public Map<String,String> searchIndex(String searchString, int totalResult) throws IOException, XmlException, OpenXML4JException, ParserConfigurationException, SAXException {
        Query q = null;
        IndexReader reader = null;
        Map<String, String> result = null;
        try {
            q = new QueryParser(Version.LUCENE_42, "word", analyzer).parse(tokenizeStopStem(searchString));
            //noinspection deprecation
            reader = IndexReader.open(this.indexDir);
            IndexSearcher searcher = new IndexSearcher(reader);
            TopScoreDocCollector collector = TopScoreDocCollector.create(totalResult, true);
            searcher.search(q, collector);
            ScoreDoc[] hits = collector.topDocs().scoreDocs;
            result = new HashMap<String, String>();
            for (int i = 0; i < hits.length; i++) {
                Document doc = searcher.doc(hits[i].doc);
                result.put(doc.get("fullpath"), getPreview(doc.get("fullpath"), searchString.replaceAll("[^a-zA-Z0-9\\s]", "")));
            }

        } catch (org.apache.lucene.queryparser.classic.ParseException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
        return new TreeMap<String, String>(result).descendingMap();
    }

    //This will return indexed word and its doc frequency
    public HashMap<String, Object> getAllWords() throws IOException {
        HashMap<String, Object> indexedWord = new HashMap<String, Object>();
        Fields fields = MultiFields.getFields(this.indexReader);
        Terms terms = fields.terms("word");
        TermsEnum iterator = terms.iterator(null);
        BytesRef byteRef;
        Term term;
        while ((byteRef = iterator.next()) != null) {
            String word = new String(byteRef.bytes, byteRef.offset, byteRef.length);
            term = new Term("word", word);
            indexedWord.put(word, getTermFreq(term));
        }
        return indexedWord;
    }

    private String getPreview (String filePath, String keyword) throws OpenXML4JException, XmlException, IOException, SAXException, ParserConfigurationException {
        DocParser docParser = new DocParser();
        String content = docParser.parseDocument(filePath);
        int indexOfKeyword = getKeywordIndex(content,  keyword);
        if(indexOfKeyword > -1){
            int startOfPreview = indexOfKeyword - 100 > 0 ? indexOfKeyword - 100 : indexOfKeyword;
            int endOfPreview = content.length() < indexOfKeyword + 300 ? content.length() : indexOfKeyword + 300;
            return content.substring(startOfPreview, endOfPreview);
        }
        return "Sorry for system cannot show preview for non-standard query";
    }

    private int getKeywordIndex(String content, String keyword) {
        //Dirty way to trick the case sensitive of indexOf method
        int indexOfKeyword = content.indexOf(keyword);
        if(indexOfKeyword < 0){
            indexOfKeyword = content.indexOf(StringUtils.capitalize(keyword));
            if(indexOfKeyword < 0){
                indexOfKeyword = content.indexOf(keyword.toLowerCase());
                if(indexOfKeyword < 0){
                    indexOfKeyword = content.indexOf(keyword.toUpperCase());
                    if (indexOfKeyword < 0) {
                        indexOfKeyword = -1;
                    }
                }
            }
        }
        return indexOfKeyword;
    }

    public String tokenizeStopStem(String input) {

        if(input.indexOf("=") < 0){
            return input;
        }

        input = input.replaceAll("=", "");
        TokenStream tokenStream = new StandardTokenizer(Version.LUCENE_42, new StringReader(input));
        CharArraySet stop_word_set = new CharArraySet(Version.LUCENE_42, 1, true);
        tokenStream = new StopFilter(Version.LUCENE_42, tokenStream, stop_word_set);
        tokenStream = new PorterStemFilter(tokenStream);

        StringBuilder sb = new StringBuilder();
        OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
        CharTermAttribute charTermAttr = tokenStream.getAttribute(CharTermAttribute.class);
        try{
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                if (sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(charTermAttr.toString());
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }
}
