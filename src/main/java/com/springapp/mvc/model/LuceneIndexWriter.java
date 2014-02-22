package com.springapp.mvc.model;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class LuceneIndexWriter {

//    private final String indexPath = "/Users/Yauri/Desktop/ir/index/";
//    private final String docPath = "/Users/Yauri/Desktop/ir/data/";
    private final File docDir;
    private final Directory indexDir;
    private IndexWriter writer;
    private Analyzer analyzer;
    private IndexWriterConfig iwc;

    public LuceneIndexWriter (Directory indexDir, File docDir) {
        this.docDir = docDir;
        this.indexDir = indexDir;
        this.analyzer = new StandardAnalyzer(Version.LUCENE_42);
        this.iwc = new IndexWriterConfig(Version.LUCENE_42, this.analyzer);
        this.iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        this.iwc.setRAMBufferSizeMB(512);
        try {
            this.writer = new IndexWriter(this.indexDir, iwc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void indexDocs(File file) throws IOException {
        if (file.canRead()) {
            if (file.isDirectory()) {
                String[] files = file.list();
                if (files != null) {
                    for (int i = 0; i < files.length; i++) {
                        indexDocs(new File(file, files[i]));
                    }
                }
            } else {

                FileInputStream fis;
                try {
                    fis = new FileInputStream(file);
                } catch (FileNotFoundException fnfe) {
                    return;
                }

                try {
                    Document doc = new Document();
                    doc.add(new Field("word", new BufferedReader(new InputStreamReader(fis, "UTF-8")), getFieldType()));
                    doc.add(new Field("title", file.getName(), TextField.TYPE_STORED));
                    doc.add(new Field("fullpath", file.getAbsolutePath(), TextField.TYPE_STORED));
                    this.writer.addDocument(doc);

                } finally {
                    fis.close();
                }
            }
        }
    }

    public void indexDocument(String filePath) throws OpenXML4JException, XmlException, IOException, SAXException, ParserConfigurationException {
        DocParser docParser = new DocParser();
        String fileContent = docParser.parseDocument(filePath);
        File file = new File(filePath);

        try {
            Document doc = new Document();
            doc.add(new Field("word", fileContent,  getFieldType()));
            doc.add(new Field("title", file.getName(), TextField.TYPE_STORED));
            doc.add(new Field("fullpath", file.getAbsolutePath(), TextField.TYPE_STORED));

            this.writer.addDocument(doc);
            System.out.println("Indexed " + file.getName());
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error in indexing " + (file.getAbsolutePath()));
        }
    }

    private FieldType getFieldType() {
        FieldType fieldType = new FieldType();
        fieldType.setStoreTermVectors(true);
        fieldType.setStoreTermVectorPositions(true);
        fieldType.setIndexed(true);
        return fieldType;
    }

    public IndexWriter getWriter() {
        return this.writer;
    }

    public File getDocDir() {
        return this.docDir;
    }

}

