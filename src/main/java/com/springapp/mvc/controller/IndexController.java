package com.springapp.mvc.controller;

import com.springapp.mvc.model.AppConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.springapp.mvc.model.LuceneIndexReader;
import com.springapp.mvc.model.LuceneIndexWriter;
import com.springapp.mvc.model.WarcExtractor;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Controller
public class IndexController {

    private File docDir;
    private Directory indexDir;
    private String docPath;
    private String indexPath;

    @Autowired
    private AppConfig appConfig;

    public IndexController() throws IOException {
    }

    @RequestMapping
    public String addWarc(ModelMap map, @RequestParam("indexfile") MultipartFile file) throws IOException {

        if (!file.isEmpty()) {
            docDir = new File(appConfig.getDocPath());
            indexDir = FSDirectory.open(new File(appConfig.getIndexPath()));
            makeDir(appConfig.getDocPath()+"txt/");
            WarcExtractor warcExtractor = new WarcExtractor(file.getInputStream(), appConfig.getDocPath()+"txt/");
            warcExtractor.extractWarcFile();
            LuceneIndexWriter luceneIndexWriter = new LuceneIndexWriter(this.indexDir, this.docDir);
            try{
                luceneIndexWriter.indexDocs(luceneIndexWriter.getDocDir());
            }
            finally {
                luceneIndexWriter.getWriter().close();
            }
        }
        return "redirect:../";
    }

    @RequestMapping
    public void upload(){

    }

    @RequestMapping
    public void addDocument(ModelMap map) {

    }

    @RequestMapping
    public void data(ModelMap map) throws IOException {
        if (new File(appConfig.getIndexPath()).exists() && FileUtils.sizeOfDirectory(new File(appConfig.getIndexPath())) > 0) {
            Directory indexDir = FSDirectory.open(new File(appConfig.getIndexPath()));
            LuceneIndexReader luceneIndexReader = new LuceneIndexReader(indexDir);
            HashMap<String, Object> listWords = luceneIndexReader.getAllWords();
            map.put("totalKeywords", listWords.size());
            map.put("totalDocs", String.valueOf(luceneIndexReader.getTotalDocs()));
        } else {
            map.put("totalKeywords", 0);
        }

    }

    @RequestMapping
    public String saveDocument(ModelMap map, HttpSession session, @RequestParam("document") MultipartFile file) throws IOException, XmlException, OpenXML4JException, ParserConfigurationException, SAXException {
        //Remember to change save doc path
        makeDir(appConfig.getDocPath() + FilenameUtils.getExtension(file.getOriginalFilename())+"/");
        saveDocumentToDisk(appConfig.getDocPath()+FilenameUtils.getExtension(file.getOriginalFilename())+"/", file);
        indexDocument(appConfig.getDocPath() + FilenameUtils.getExtension(file.getOriginalFilename()) + "/" + file.getOriginalFilename());
        return "redirect:../";
    }

    private void saveDocumentToDisk(String filePath, MultipartFile file) throws IOException {
        if (file!= null && file.getSize() > 0) {
            if (!file.getOriginalFilename().equals("")) {
                file.transferTo(new File(filePath+file.getOriginalFilename()));
            }
        }
    }

    private void indexDocument(String filePath) throws IOException, XmlException, OpenXML4JException, ParserConfigurationException, SAXException {
        LuceneIndexWriter luceneIndexWriter = null;
        docDir = new File(appConfig.getDocPath());
        indexDir = FSDirectory.open(new File(appConfig.getIndexPath()));
        try {
            luceneIndexWriter = new LuceneIndexWriter(this.indexDir, this.docDir);
            luceneIndexWriter.indexDocument(filePath);
        } finally {
            luceneIndexWriter.getWriter().close();
        }
    }

    private void makeDir(String path){
        File theDir = new File(path);
        if (!theDir.exists())
        {
            theDir.mkdirs();
        }
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setIndexPath(String indexPath) {
        this.indexPath = indexPath;
    }

    public String getIndexPath() {
        return indexPath;
    }
}
