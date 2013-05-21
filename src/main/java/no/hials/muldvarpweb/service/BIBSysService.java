/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

/**
 *
 * @author Nospherus
 */
@Stateless
@Path("BIBSys")
public class BIBSysService {
    
    static String host = "http://sru.bibsys.no/search/biblio?version=1.2&operation=searchRetrieve&startRecord=1&maximumRecords=10&query=bs.bibkode=xb%20AND%20bs.tittel=";
    
    @GET
    @Path("{query}")
    @Produces({MediaType.APPLICATION_JSON})
    public List getResultsByTitle(@PathParam("query")String query) throws IOException {
        String URL = host + query;
        List results = new ArrayList();
        Document doc = Jsoup.connect(URL).get();
        for (Iterator<Element> it = doc.getElementsByTag("marc:record").iterator(); it.hasNext();) { //Splits the result into seperate books
            Element e = it.next();
            for (Iterator<Element> it1 = e.getElementsByTag("marc:datafield").iterator(); it1.hasNext();) { //Splits the result into seperate infofields
                Element e1 = it1.next();
                if (e1.toString().contains("245")) {       //Separates the info from field 245, which contains the title and the name of the author
                    String title = null;
                    String author = null;
                    String[] array = e1.toString().split("\n");
                    title = array[2].substring(2);
                    if (!e1.toString().contains("code=\"b\">")) {       //Checks whether the element contains a subtitle, in which case the author is on line 11, not 8.
                        author = array[8].substring(2);
                    } else {
                        author = array[11].substring(2);
                    }
                    results.add(new Book(title, author));
                }
            }
        }
        return results;
    }
    
    static class Book {     //Inner entityclass to hold the data
        
        private String title;
        private String Author;

        public Book(String title, String Author) {
            this.title = title;
            this.Author = Author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return Author;
        }

        public void setAuthor(String Author) {
            this.Author = Author;
        }
        
    }
}
