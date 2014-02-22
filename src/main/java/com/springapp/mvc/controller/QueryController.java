package com.springapp.mvc.controller;

import com.springapp.mvc.model.AppConfig;
import com.springapp.mvc.model.DocParser;
import com.springapp.mvc.model.LuceneIndexReader;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Controller
public class QueryController {

    @Autowired
    AppConfig appConfig;

    @RequestMapping
    public void result(ModelMap map, @RequestParam("keyword") String keyword) throws XmlException, OpenXML4JException, ParserConfigurationException, SAXException {
        try {
            Directory indexDir = FSDirectory.open(new File(appConfig.getIndexPath()));
            LuceneIndexReader luceneIndexReader = new LuceneIndexReader(indexDir);
            Map<String,String> result = luceneIndexReader.searchIndex(keyword, 20);
            map.put("results",result);
            map.put("totalResult",result.size());
            map.put("keyword", luceneIndexReader.tokenizeStopStem(keyword));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
