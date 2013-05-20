/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

/**
 *
 * @author Nospherus
 */
@Stateless
@Path("top")
public class BIBSysService {
    
    static String host = "http://sru.bibsys.no/search/biblio?version=1.2&operation=searchRetrieve&maximumRecords=5&query=";
    static int maxNumberOfResults = 10;
    
    public List getResults(String query) throws IOException {
        String URL = host + query;
        Document doc = Jsoup.connect(URL).get();
        doc = Jsoup.parse(doc.html(), "", Parser.xmlParser());
        for (Iterator<Element> it = doc.getElementsByTag("marc:record").iterator(); it.hasNext();) { //Splits the result into seperate books
            Element e = it.next();
            for (Iterator<Element> it1 = e.getElementsByTag("marc:datafield").iterator(); it1.hasNext();) { //Splits the result into seperate infofields
                Element e1 = it1.next();
                if (e1.toString().contains("245")) {       //Separates the info from field 245, which contains the title and the name of the author
                    String[] array = e1.toString().split("\n");
                    System.out.println("title: " +array[2].substring(3));
                    System.out.println("Author: " + array[5].substring(3));
                }
            }
        }
        return null;
    }
}
