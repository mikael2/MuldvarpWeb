/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.service.scrape;

import java.io.IOException;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.primefaces.json.JSONArray;

/**
 *
 * @author johan
 */
//@Stateless
//@Path("timeedit")
public class TimeEditService {    
    //Tar mye lengre tid å laste inn den "grafiske" time-edit siden    
    //Below are static variables which define the URL and the various parameters to be used.
    //Loading the "graphical" version ofthe site takes more time. "Tekstformat" or "text" is preferred over "Grafisk".
    private final static String TIMEEDIT_HIALS_URL = "http://timeedit.hials.no/4DACTION/WebShowSearch/1/1-0?";
    private final static String TIMEDIT_SEARCH_TYPE = "wv_type=";
    //Fag/Course = 3
    //Klasse/Program = 5
    //Lærer/Teacher/Person = 6
    //Rom/Room = 7
    private final static String TIMEDIT_SEARCH = "wv_search="; //Search variable. Can be used with course codes or names.
    private final static String TIMEDIT_FORMAT="wv_text="; //Page will not return anything unless this is added first in the set of variables.    
    
    //The "object" is the variable which defines the course or name. It is an ID unique to the Timeedit system
    private final static String TIMEEDIT_OBJECT1 = "wv_obj1=";
    
    
    private final static String TIMEEDIT_START = "wv_startWeek=";
    private final static String TIMEEDIT_STOP = "wv_stopWeek=";
    String lenke = "http://timeedit.hials.no/4DACTION/WebShowSearch/1/1-0?wv_type=5&wv_ts=20130222T141621X6075&wv_search=&wv_startWeek=1301&wv_stopWeek=1318&wv_addObj=&wv_delObj=&wv_obj1=169000&wv_text=Tekstformat";
    
    //Static variable types
    private final static String TIMEDIT_FORMAT_TEXT="text";
    private final static String TIMEDIT_FORMAT_TEXT2="Tekstformat";
    private final static String TIMEDIT_FORMAT_GRAPHIC="Grafisk";
    
    //The document variable to be stored in memory
    Document webDoc;
    
    JSONArray cachedResult;
    
    public TimeEditService(){
        
    }
    
    public void getSite() throws IOException{
        String link = TIMEEDIT_HIALS_URL + "&" + TIMEDIT_FORMAT;
        webDoc = Jsoup.connect(link).get();
        Elements test;
    }
    
    /**
     *
     * @param id The ID of the Object from which the schedule is to be retrieved. The Object ID can represent
     * a Course (Fag), a Programme(Klasse), or a combination.
     */
    @GET
    @Path("object/{id1}/{id2}")
    @Produces({MediaType.APPLICATION_JSON})
    public void getScheduleByObjectID(@PathParam("id1") String id1, @PathParam("id2") String id2){
        
    }
    
    /**
     *
     * @param queryThe search string
     */
    @GET
    @Path("search/{query}")
    @Produces({MediaType.APPLICATION_JSON})
    public void getScheduleByQuery(@PathParam("query") String query){
        
    }
}
