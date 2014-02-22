package com.springapp.mvc.model;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class DocParser {

    public String parseDocument(String docPath) throws FileNotFoundException,
            IOException,
            XmlException,
            InvalidFormatException,
            OpenXML4JException,
            ParserConfigurationException,
            SAXException
    {
        File file = new File(docPath);
        FileInputStream fs = new FileInputStream(file);

        if (file.getName().endsWith(".docx")) {
            return this.parseDocx(OPCPackage.open(fs));
        } else if (file.getName().endsWith(".xlsx")) {
            return this.parseXlsx(OPCPackage.open(fs));
        } else if (file.getName().endsWith(".pptx")) {
            return this.parsePptx(OPCPackage.open(fs));
        } else if (file.getName().endsWith(".doc")) {
            return this.parseDoc(new POIFSFileSystem(fs));
        } else if (file.getName().endsWith(".xls")) {
            return this.parseXls(new POIFSFileSystem(fs));
        } else if (file.getName().endsWith(".ppt")) {
            return this.parsePpt(new POIFSFileSystem(fs));
        } else if (file.getName().endsWith(".pdf")) {
            return this.parsePdf(fs);
        } else if (file.getName().endsWith(".txt")) {
            return this.parseTxt(fs);
        }

        //better to handle in different way if document is not recognized
        return "";
    }

    private String parseDocx(OPCPackage docx) throws FileNotFoundException,
            IOException,
            InvalidFormatException,
            XmlException,
            OpenXML4JException
    {
        XWPFWordExtractor xw = new XWPFWordExtractor(docx);
        return xw.getText();
    }

    private String parsePptx(OPCPackage pptx) throws FileNotFoundException,
            IOException,
            InvalidFormatException,
            XmlException,
            OpenXML4JException
    {
        XSLFPowerPointExtractor xw = new XSLFPowerPointExtractor(pptx);
        return xw.getText();
    }

    private String parseXlsx(OPCPackage xlsx) throws FileNotFoundException,
            IOException,
            InvalidFormatException,
            XmlException,
            OpenXML4JException
    {
        XSSFExcelExtractor xe = new XSSFExcelExtractor(xlsx);
        return xe.getText();
    }

    private String parseDoc(POIFSFileSystem doc) throws FileNotFoundException,
            IOException,
            InvalidFormatException,
            XmlException,
            OpenXML4JException
    {
        WordExtractor xw = new WordExtractor(doc);
        return xw.getText();
    }

    private String parsePpt(POIFSFileSystem ppt) throws FileNotFoundException,
            IOException,
            InvalidFormatException,
            XmlException,
            OpenXML4JException
    {
        PowerPointExtractor xw = new PowerPointExtractor(ppt);
        return xw.getText();
    }

    private String parseXls(POIFSFileSystem xls) throws FileNotFoundException,
            IOException,
            InvalidFormatException,
            XmlException,
            OpenXML4JException
    {
        ExcelExtractor xe = new ExcelExtractor(xls);
        return xe.getText();
    }

    private String parsePdf(FileInputStream fileInputStream) throws IOException {
        PDFParser pdfParser = new PDFParser(fileInputStream);
        pdfParser.parse();
        COSDocument cd = pdfParser.getDocument();
        PDFTextStripper stripper = new PDFTextStripper();
        String pdfContent = stripper.getText(new PDDocument(cd));
        cd.close();
        return pdfContent;
    }

    private String parseTxt(FileInputStream fileInputStream) throws IOException {
        try {
            String content = IOUtils.toString(fileInputStream);
            return content;
        } finally {
            fileInputStream.close();
        }
    }
}
