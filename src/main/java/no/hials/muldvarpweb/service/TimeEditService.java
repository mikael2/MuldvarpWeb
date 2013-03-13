/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.service;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author kb
 */
public class TimeEditService {
    /**
     * TimeEdit info:
     * dateformat: yyyymmdd
     * classcodeformat: 183000
     */
    
    String host = "http://timeedit.hials.no/4DACTION/";
    String currentActivityPage = host + "WebShowRoll/1-7?offset=1440&update=0&rows=0&page=0&branch=2&group=-7&day=yes&start=yes&stop=yes&order=ascending&web_cols=1&web_numChars=-";
        
    public void getByClass(String classcode) {
        Document doc = null;
        try {
            doc = Jsoup.connect(getClassScheduleUrlByClasscode(getCurrentDate(), classcode)).get();
        } catch (IOException ex) {
            Logger.getLogger(TimeEditService.class.getName()).log(Level.SEVERE, null, ex);
        }
        Element content = doc.getElementsByClass("booking").first();
        Elements rows = content.getElementsByTag("tr");
        boolean foundEnd = false;
        for(Element row : rows) {
            if(foundEnd) {
               break; 
            }
            for(Element column : row.getElementsByTag("td")) {
                if(column.select("blank") != null) {
                    foundEnd = true;
                }
                System.out.println(column.getElementsByTag("font").first().text());
            }
        }
        
    }
    
    public String getClassScheduleUrlByClasscode(String classcode, String date) {
        return host + "WebShowSearch/1/1-0?wv_ts=" + date + "&wv_obj1=" + classcode + "&wv_text=Tekstformat";
    }
    
    public void getByCourseId(String course) {
        course = "ID102012";
        Document doc = null;
        try {
            doc = Jsoup.connect(currentActivityPage).get();
        } catch (IOException ex) {
            Logger.getLogger(TimeEditService.class.getName()).log(Level.SEVERE, null, ex);
        }
        Elements newsHeadlines = doc.select("#mp-itn b a");
    }
    
    public String getCurrentDate() {
        Date d = new Date();
        return "" + d.getYear() + d.getMonth() + d.getDate();
    }
}
