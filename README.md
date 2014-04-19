Demo
=============
http://dev.yauri.me/samplelucene/

About
=============

A sample java project that can index a document (PPT, PPTX, DOC, DOCX, PDF) and search indexed document.
Using:
- Apache Lucene (http://lucene.apache.org/core/)
- Spring Framework 3.2 (http://projects.spring.io/spring-framework/)
- Apache POI (http://poi.apache.org/)
- Apache PDFBox (http://pdfbox.apache.org/)

How To Build
=============
- Download and install Maven (http://maven.apache.org/download.cgi)
- Set location to store documents and index on  **/src/main/webapp/WEB-INF/config.properties**
- Run **mvn install** under project root
- The command will generate a *.war* file inside target folder
- Deploy the *.war* file to your Tomcat server
