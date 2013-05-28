/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.utility;

import java.io.IOException;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

/**
 *
 * @author Nospherus
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String query = "ibsen";
        String host = "http://sru.bibsys.no/search/biblio?version=1.2&operation=searchRetrieve&startRecord=1&maximumRecords=10&query=bs.bibkode=xb%20AND%20bs.tittel=" + query;
        Document doc = Jsoup.connect(host).get();
//        doc = Jsoup.parse(doc.html(), "", Parser.xmlParser());
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
    }
}
