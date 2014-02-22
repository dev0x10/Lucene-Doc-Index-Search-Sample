package com.springapp.mvc.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Yauri
 * Date: 6/17/13
 * Time: 1:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class AppConfig {

    String indexPath;
    String docPath;

    public AppConfig(){

    }

    public String getIndexPath() {
        return indexPath;
    }

    public void setIndexPath(String indexPath) {
        this.indexPath = indexPath;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

}
