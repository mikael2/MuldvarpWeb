/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.service.scrape;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import org.jsoup.nodes.Document;
import org.primefaces.json.JSONArray;

/**
 *
 * @author johan
 */
@Stateless
@Path("toplel")
public class TimeEditService {
    
    //Maximum number of object requests that will be parsed by getURL()
    private static int MAX_OBJECT_REQUESTS = 10;
    
    /**
     * URI Parameters
     * Change as needed
     */
    //Tar mye lengre tid å laste inn den "grafiske" time-edit siden    
    //Below are static variables which define the URL and the various parameters to be used.
    //Loading the "graphical" version ofthe site takes more time. "Tekstformat" or "text" is preferred over "Grafisk".
    private final static String TIMEEDIT_HIALS_URL = "http://timeedit.hials.no/4DACTION/WebShowSearch/1/1-0?";
    private final static String TIMEDIT_PARAM_SEARCH_TYPE = "wv_type";
    //Fag/Course = 3
    //Klasse/Program = 5
    //Lærer/Teacher/Person = 6
    //Rom/Room = 7
    private final static String TIMEDIT_PARAM_SEARCH = "wv_search"; //Search variable. Can be used with course codes or names.
    private final static String TIMEDIT_PARAM_FORMAT = "wv_text"; //Page will not return anything unless this is added first in the set of variables.    
    //The "object" is the variable which defines the course or name. It is an ID unique to the Timeedit system
    private final static String TIMEEDIT_PARAM_OBJECT = "wv_obj";
    private final static String TIMEEDIT_PARAM_START = "wv_startWeek";
    private final static String TIMEEDIT_PARAM_STOP = "wv_stopWeek";
    String lenke = "http://timeedit.hials.no/4DACTION/WebShowSearch/1/1-0?wv_type=5&wv_ts=20130222T141621X6075&wv_search=&wv_startWeek=1301&wv_stopWeek=1318&wv_addObj=&wv_delObj=&wv_obj1=169000&wv_text=Tekstformat";
    //Static variable types
    private final static String TIMEDIT_PARAM_FORMAT_TEXT = "text";
    private final static String TIMEDIT_PARAM_FORMAT_TEXT2 = "Tekstformat";
    private final static String TIMEDIT_PARAM_FORMAT_GRAPHIC = "Grafisk";
    //The document variable to be stored in memory
    Document webDoc;
    JSONArray cachedResult;

    public TimeEditService() {
    }

    /**
     * This method generates an TimEdit URL based on a String Array of objectCodes, a String representation of a starting and ending week.
     * @param objectCodes
     * @param startWeek
     * @param stopWeek
     * @return 
     */
    public String getURL(String[] objectCodes, String startWeek, String stopWeek) {

        String objectString = "";
        for (int i = 0; i < objectCodes.length; i++) {
            if (i >= MAX_OBJECT_REQUESTS) {
                break;
            } else {
                objectString += "&" + TIMEEDIT_PARAM_OBJECT + Integer.toString(i + 1) + "=" + objectCodes[i];   
            }
        }
        String retVal = TIMEEDIT_HIALS_URL
                + "&" + TIMEDIT_PARAM_FORMAT + "=" + TIMEDIT_PARAM_FORMAT_TEXT
                + objectString;

        if (startWeek != null && !startWeek.isEmpty()) {
            retVal += "&" + TIMEEDIT_PARAM_START + "=" + startWeek;
        }
        if (stopWeek != null && !stopWeek.isEmpty()) {
            retVal += "&" + TIMEEDIT_PARAM_STOP + "=" + stopWeek;
        }
        return retVal;
    }

    /**
     * Method which returns JSON based on get requests.
     *
     * @param ui
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public MultivaluedMap get(@Context UriInfo ui) {
                
        System.out.println(ui.getQueryParameters().getFirst(TIMEEDIT_PARAM_OBJECT));
        String objectCodes[] = ui.getQueryParameters().getFirst(TIMEEDIT_PARAM_OBJECT).split(",");
        System.out.println("GET-PARAM:" + getURL(objectCodes, 
                ui.getQueryParameters().getFirst(TIMEEDIT_PARAM_START),
                ui.getQueryParameters().getFirst(TIMEEDIT_PARAM_STOP)));
        return ui.getQueryParameters();
    }

    /**
     *
     * @param id The ID of the Object from which the schedule is to be
     * retrieved. The Object ID can represent a Course (Fag), a
     * Programme(Klasse), or a combination.
     */
    @GET
    @Path("object/{objectstring:\\d{6}}{startweek:(/startweek/[^/]+?)?}{stopweek:(/stopweek/[^/]+?)?}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getScheduleByObjectID(@PathParam("objectstring") String objectstring,
            @PathParam("startweek") String startweek,
            @PathParam("stopweek") String stopweek) {
        System.out.println(getURL(objectstring.split("/"), startweek, stopweek));

        return objectstring + startweek + stopweek;
    }

    /**
     *
     * @param id The ID of the Object from which the schedule is to be
     * retrieved. The Object ID can represent a Course (Fag), a
     * Programme(Klasse), or a combination.
     */
    @GET
    @Path("objects/{objectstring:(\\d{6}/?)+}{startweek:(/startweek/[^/]+?)?}{stopweek:(/stopweek/[^/]+?)?}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getScheduleByMultipleObjectID(@PathParam("objectstring") String objectstring,
            @PathParam("startweek") String startweek,
            @PathParam("stopweek") String stopweek) {

        startweek = startweek.replace("/", "");
        startweek = startweek.replace("startweek", "");
        stopweek = stopweek.replace("/", "");
        stopweek = stopweek.replace("stopweek", "");

        System.out.println(getURL(objectstring.split("/"), startweek, stopweek));
        Document doc = null;


        return objectstring + startweek + stopweek;
    }

    /**
     *
     * @param queryThe search string
     */
    @GET
    @Path("search/{query:.+}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getScheduleByQuery(@PathParam("query") String query) {
        return query;
    }

    /**
     * Inner class Day
     */
    public static class Day {

        String day;
        String date;
        List<no.hials.muldvarpweb.service.TimeEditService.Course> courses = new ArrayList<no.hials.muldvarpweb.service.TimeEditService.Course>();

        private Day(String dag) {
            this.day = dag;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<no.hials.muldvarpweb.service.TimeEditService.Course> getCourses() {
            return courses;
        }

        public void setCourses(List<no.hials.muldvarpweb.service.TimeEditService.Course> courses) {
            this.courses = courses;
        }
    }

    /**
     * Inner class Course
     */
    public static class Course {

        String time;
        String course;
        String type;
        String mClass;
        String teacher;
        String room;

        private Course(String tid) {
            this.time = tid;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getmClass() {
            return mClass;
        }

        public void setmClass(String mClass) {
            this.mClass = mClass;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }
    }
}
