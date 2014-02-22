package com.springapp.mvc.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.*;

public class WarcExtractor {
    private final InputStream warcFile;
    private final String extractedDocName;

    public WarcExtractor(InputStream warcFile, String extractedDocName) {
        this.warcFile = warcFile;
        this.extractedDocName = extractedDocName;
    }

    public void extractWarcFile() throws IOException {
        InputStream in = new DataInputStream(this.warcFile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        Document stringToParse;
        Document title = null;
        BufferedWriter out = null;
        String strLine;
        FileWriter fostream;

        while((strLine=reader.readLine())!=null)
        {
            if(strLine.contains("<title>")){
                title=Jsoup.parseBodyFragment(strLine);
            }

            if(strLine.contains("<body"))
            {
                if(title.title().lastIndexOf('-') > -1){
                    title.title(title.title().substring(0,title.title().lastIndexOf('-')));
                }

                String fileName = title.title().replace("/", "");

                fostream = new FileWriter(this.extractedDocName + fileName + ".txt");
                out = new BufferedWriter(fostream);

                while(!(strLine.contains("</body>")))
                {
                    stringToParse= Jsoup.parseBodyFragment(strLine);
                    if(!stringToParse.body().text().isEmpty())
                    {
                        out.write(stringToParse.body().text());
                        out.newLine();
                    }
                    strLine=reader.readLine();

                }
                stringToParse=Jsoup.parseBodyFragment(strLine);
                out.write(stringToParse.body().text());
                out.newLine();
                out.close();
            }

        }
        if (out != null) {
            out.close();
        }
    }
}
