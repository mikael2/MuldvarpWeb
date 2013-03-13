/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    
    static String host = "http://timeedit.hials.no/4DACTION/";
    static String currentActivityPage = host + "WebShowRoll/1-7?offset=1440&update=0&rows=0&page=0&branch=2&group=-7&day=yes&start=yes&stop=yes&order=ascending&web_cols=1&web_numChars=-";
        
    public static List<Dag> getByClass(String classcode) {
        Document doc = null;
        try {
            doc = Jsoup.connect(getClassScheduleUrlByClasscode(getCurrentDate(), classcode)).get();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Element content = doc.getElementsByClass("booking").first();
        Elements rows = content.getElementsByTag("tr");
        List<Dag> dager = new ArrayList<Dag>();
        Dag d = null;
        Time t = null;
        for(Element row : rows) {
            for(int i = 0; i < row.getElementsByTag("td").size(); i++) {
                try {
                    String data = row.getElementsByTag("td").get(i).getElementsByTag("font").first().text();
                    if(data.contains("Uke")) { // Skreller vekk uke radene
                        break;
                    } else if(data.matches(".*\\w.*")) { // Bruker bare kolonnene med innhold
                        switch(i) {
                            case 2:
                                d = new Dag(data);
                                dager.add(d);
                                break;
                            case 3:
                                d.dato = data;
                                break;
                            case 4:
                                t = new Time(data);
                                break;
                            case 5:
                                t.fag = data;
                                break;
                            case 6:
                                t.type = data;
                                break;
                            case 7:
                                t.klasse = data;
                                break;
                            case 8:
                                t.lærer = data;
                                break;
                            case 9:
                                t.rom = data;
                                d.timer.add(t);
                                break;
                        }
                    }
                } catch(NullPointerException ex) {
                }
            }
            
        }
        
        for(Dag dag : dager) {
            System.out.println("--------------");
            System.out.println("Dag: " + dag.dag);
            System.out.println("Dato: " + dag.dato);
            System.out.println("Timer: " + dag.timer.size());
            System.out.println("-------");
            for(Time time : dag.timer) {
                System.out.println("---");
                System.out.println("Tid:    " + time.tid);
                System.out.println("Fag:    " + time.fag);
                System.out.println("Type:   " + time.type);
                System.out.println("Klasse: " + time.klasse);
                System.out.println("Lærer:  " + time.lærer);
                System.out.println("Rom:    " + time.rom);
            }
        }
        return dager;
    }
    
    public static String getClassScheduleUrlByClasscode(String date, String classcode) {
        String retVal = host + "WebShowSearch/1/1-0?wv_ts=" + date + "&wv_obj1=" + classcode + "&wv_text=Tekstformat";
        System.out.println("Getting url: " + retVal);
        return retVal;
    }
    
    public static void getByCourseId(String course) {
        course = "ID102012";
        Document doc = null;
        try {
            doc = Jsoup.connect(currentActivityPage).get();
        } catch (IOException ex) {
        }
        Elements newsHeadlines = doc.select("#mp-itn b a");
    }
    
    public static String getCurrentDate() {
        Date d = new Date();
        String year = "" + (d.getYear() + 1900);
        String month = "";
        if(d.getMonth() < 10) {
            month += "0";
        }
        month += (d.getMonth() + 1);
        String retVal = "" + year + month + d.getDate();
        System.out.println("Getting date " + retVal);
        return retVal;
    }
    
    static class Dag {
        String dag;
        String dato;
        List<Time> timer = new ArrayList<Time>();

        private Dag(String dag) {
            this.dag = dag;
        }
    }
    
    static class Time {
        String tid;
        String fag;
        String type;
        String klasse;
        String lærer;
        String rom;

        private Time(String tid) {
            this.tid = tid;
        }
    }
}
