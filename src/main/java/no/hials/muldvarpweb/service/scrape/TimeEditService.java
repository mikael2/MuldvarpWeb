/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.service.scrape;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author johan
 */
@Stateless
@Path("timeedit")
public class TimeEditService {
    //Maximum number of object requests that will be parsed by getURL()
    private static int MAX_OBJECT_REQUESTS = 10;
    /**
     * URI Parameters Change as needed
     */
    //Tar mye lengre tid å laste inn den "grafiske" time-edit siden    
    //Below are static variables which define the URL and the various parameters to be used.
    //Loading the "graphical" version ofthe site takes more time. "Tekstformat" or "text" is preferred over "Grafisk".
    //ORDER:
    //DATE (date, first/last week)--> OBJECTS(object codes) ---> presentation format
    //Won't work if the order is any different than this.
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
    private final static String TIMEEDIT_PARAM_WEEKSTART = "wv_startWeek";
    private final static String TIMEEDIT_PARAM_WEEKSTOP = "wv_stopWeek";
    private final static String TIMEEDIT_PARAM_DATE = "wv_ts";
    //Static variable types
    private final static String TIMEEDIT_VALUE_FORMAT_TEXT = "text";
    private final static String TIMEEDIT_VALUE_FORMAT_TEXT2 = "Tekstformat";
    private final static String TIMEEDIT_VALUE_FORMAT_GRAPHIC = "Grafisk";

    public TimeEditService() {
    }
    
    /**
     * This method generates an TimeEdit URL based on a String Array of
     * objectCodes, a String representation of a starting and ending week.
     *
     * @param objectCodes
     * @param startWeek
     * @param stopWeek
     * @return
     */
    public String getURL(String[] objectCodes, String startWeek, String stopWeek) {
        String retVal = TIMEEDIT_HIALS_URL                
                + generateObjectCodeURL(objectCodes)
                + TIMEDIT_PARAM_FORMAT + "=" + TIMEEDIT_VALUE_FORMAT_TEXT;
        if (startWeek != null && !startWeek.isEmpty()) {
            retVal += "&" + TIMEEDIT_PARAM_WEEKSTART + "=" + startWeek;
        }
        if (stopWeek != null && !stopWeek.isEmpty()) {
            retVal += "&" + TIMEEDIT_PARAM_WEEKSTOP + "=" + stopWeek;
        }
        return retVal;
    }
    
    /**
     * This method generates an TimeEdit URL based on a String Array of
     * objectCodes, a String representation of a date. (YYYYMMDD)
     *
     * @param objectCodes
     * @param date
     * @return
     */
    public String getURL(String[] objectCodes, String date) {
        String retVal = TIMEEDIT_HIALS_URL;
        if (date != null && !date.isEmpty()) {
            retVal += TIMEEDIT_PARAM_DATE + "=" + date + "&";
        }
        retVal += generateObjectCodeURL(objectCodes)
                + TIMEDIT_PARAM_FORMAT + "=" + TIMEEDIT_VALUE_FORMAT_TEXT;
        return retVal;
    }
    
    /**
     * This method concatenates the TIMEEDIT_PARAM_OBJECT parameter plus a String array of 
     * objectCodes into one string.
     * @param objectCodes
     * @return 
     */
    private String generateObjectCodeURL(String[] objectCodes){
        String retVal = "";
        for (int i = 0; i < objectCodes.length; i++) {
            if (i >= MAX_OBJECT_REQUESTS) {
                break;
            } else {
                retVal += TIMEEDIT_PARAM_OBJECT + Integer.toString(i + 1) + "=" + objectCodes[i] + "&";
            }
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
    public MultivaluedMap getParameters(@Context UriInfo ui) {
                
        System.out.println(ui.getQueryParameters().getFirst(TIMEEDIT_PARAM_OBJECT));
        String objectCodes[] = ui.getQueryParameters().getFirst(TIMEEDIT_PARAM_OBJECT).split(",");
        System.out.println("GET-PARAM:" + getURL(objectCodes,
                ui.getQueryParameters().getFirst(TIMEEDIT_PARAM_WEEKSTART),
                ui.getQueryParameters().getFirst(TIMEEDIT_PARAM_WEEKSTOP)));
        return ui.getQueryParameters();
    }
    
    public Response getResponse(String[] objectCodes, String date, String startWeek, String stopWeek, boolean simple){
        TimeEditSchedule responseEntitity = null;
        if(objectCodes == null 
                || objectCodes.length == 0 
                || objectCodes[0].isEmpty()){
            return Response.ok(Response.Status.OK)
                    .entity("Malformed or no objects supplied. Read the API documentation for instructions. (Not yet available)")
                    .type(MediaType.TEXT_PLAIN).build();
        }
        if (date != null && !date.isEmpty()) {
            date = date.replace("/", "");
            date = date.replace("date","");
//            System.out.println(getURL(objectCodes, date));
            responseEntitity = getTimeEditSchedule(getURL(objectCodes, date));
            
        } else {
            startWeek = startWeek.replace("/", "");
            startWeek = startWeek.replace("startweek", "");
            stopWeek = stopWeek.replace("/", "");
            stopWeek = stopWeek.replace("stopweek", "");
            System.out.println(getURL(objectCodes, startWeek, stopWeek));
            responseEntitity = getTimeEditSchedule(getURL(objectCodes, startWeek, stopWeek));
        }
        
        if (simple) {
            List<ScheduleDay> days = new ArrayList<ScheduleDay>();
            for(int i = 0; i < responseEntitity.getWeeks().size(); i++){
                for(int n = 0; n < responseEntitity.getWeeks().get(i).getDays().size(); n++){
                    days.add(responseEntitity.getWeeks().get(i).getDays().get(n));
                }
            }
            return Response.ok(days, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.ok(responseEntitity, MediaType.APPLICATION_JSON).build();
        }       
    }

        /**
     *
     * @param id The ID of the Object from which the schedule is to be
     * retrieved. The Object ID can represent a Course (Fag), a
     * Programme(Klasse), or a combination.
     */
    @GET
    @Path("simple/{objectstring:|((\\d{6}|\\d{7})/?)+}{startweek:(/startweek/[^/]+?)?}{stopweek:(/stopweek/[^/]+?)?}{date:(/date/[^/]+?)?}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getSimpleScheduleByMultipleObjectID(@PathParam("objectstring") String objectString,
            @PathParam("startweek") String startweek,
            @PathParam("stopweek") String stopweek,
            @PathParam("date") String date) {
        String[] objectCodes = objectString.split("/");
        return getResponse(objectCodes, date, startweek, stopweek, true);
    }
    
    
    /**
     *
     * @param id The ID of the Object from which the schedule is to be
     * retrieved. The Object ID can represent a Course (Fag), a
     * Programme(Klasse), or a combination.
     */
    @GET
    @Path("{objectstring:|((\\d{6}|\\d{7})/?)+}{startweek:(/startweek/[^/]+?)?}{stopweek:(/stopweek/[^/]+?)?}{date:(/date/[^/]+?)?}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getScheduleByMultipleObjectID(@PathParam("objectstring") String objectString,
            @PathParam("startweek") String startweek,
            @PathParam("stopweek") String stopweek,
            @PathParam("date") String date) {
       
        String[] objectCodes = objectString.split("/");        
        return getResponse(objectCodes, date, startweek, stopweek, false);
    }
    
    /**
     * This method mirrors the TimeEdit parameter input exactly. Any input that will work on the TimEdit
     * site will also work here. This method returns a JSONObject. The entire URL can also be accepted, but for the time being will be reconstructed.
     * 
     * @param parameterString
     * @return Response
     */
    @GET
    @Path("params/{parameterString:.+}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getScheduleByMirrorParameters(@PathParam("parameterString") String parameterString) {
        //Match everything after(and including) a question mark
        //Trim away the URL part if supplied and reconstruct the URL
        Pattern pattern = Pattern.compile("\\?(.*)");
        Matcher matcher = pattern.matcher(parameterString);
        if(matcher.find()){
            return Response.ok(getTimeEditSchedule(TIMEEDIT_HIALS_URL + matcher.group().replace("?", "")), MediaType.APPLICATION_JSON).build();    
        }        
        return Response.ok(getTimeEditSchedule(TIMEEDIT_HIALS_URL + parameterString), MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("coursecode/{coursecode}{startweek:(/startweek/[^/]+?)?}{stopweek:(/stopweek/[^/]+?)?}{date:(/date/[^/]+?)?}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getScheduleByCourseCode(@PathParam("coursecode") String courseCode,
            @PathParam("startweek") String startweek,
            @PathParam("stopweek") String stopweek,
            @PathParam("date") String date) {
        
        System.out.println("course code: " + courseCode);
        String[] objectCodes = getObjectCodeFromQuery(courseCode,3).split("/");
        System.out.println("objectCode[0]: " + objectCodes[0]);
        return getResponse(objectCodes, date, startweek, stopweek, true);
    }
    
    @GET
    @Path("classcode/{coursecode}{startweek:(/startweek/[^/]+?)?}{stopweek:(/stopweek/[^/]+?)?}{date:(/date/[^/]+?)?}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getScheduleByClassCode(@PathParam("coursecode") String classCode,
            @PathParam("startweek") String startweek,
            @PathParam("stopweek") String stopweek,
            @PathParam("date") String date) {
        
        System.out.println("course code: " + classCode);
        String[] objectCodes = getObjectCodeFromQuery(classCode,5).split("/");
        System.out.println("objectCode[0]: " + objectCodes[0]);
        return getResponse(objectCodes, date, startweek, stopweek, true);
    }
    
    /**
     *
     * @param queryThe search string
     */
    @GET
    @Path("search/{query:.+}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getScheduleByQuery(@PathParam("query") String query) {
        //NYI
        return query;
    }

    /**
     * Returns the current date in YYYYMMDD format. ex 20130410
     *
     * @return String
     */
    public String getCurrentDateYYYYMMDD() {
        return new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
    } 
    
    /**
     * Returns an empty string if no match is found.
     * WARNING: MAGIC NUMBERS
     * Fag/Course = 3
     * Klasse/Program = 5
     * Lærer/Teacher/Person = 6
     * Rom/Room = 7
     * @param query
     * @param int
     * @return 
     */
    public String getObjectCodeFromQuery(String query, int type){
        String searchURL = TIMEEDIT_HIALS_URL + TIMEDIT_PARAM_SEARCH_TYPE + "=" + type + "&" + TIMEDIT_PARAM_SEARCH + "=" + query;
        System.out.println("searchURL:" + searchURL);
        Document doc = null;
        try {
            doc = Jsoup.connect(searchURL).get();
        } catch (IOException ex) {
            Logger.getLogger(TimeEditService.class.getName()).log(Level.SEVERE, null, ex);
        }
        Elements elements = doc.select("a");
        System.out.println("Elements size: " + elements.size());
        //Regex is just a very lazy guess that none of the links contains six digits in a row.
        //Should be expanded to match the javascript function "addObject". DONE
        Pattern pattern = Pattern.compile("javascript:addObject\\((\\d{6}|\\d{7})\\)");
        Matcher matcher;
        for(Element e : elements){
            String currentString = e.attr("href");
            System.out.println("currentString: " + currentString);
            if(currentString != null && !currentString.isEmpty()){
                matcher = pattern.matcher(currentString);
                if(matcher.find()){
                    System.out.println("matcher: " + matcher.group());
                    String retVal = matcher.group().replace("javascript:addObject(", "");
                    retVal = retVal.replace(")", "");
                    return retVal;
                }
            }
        }
        return "";
    }
    
    /**
     * This function returns a TimeEditSchedule based on a correctly formatted URL string.
     * @param siteURL
     * @return 
     */
    public TimeEditSchedule getTimeEditSchedule(String siteURL){      
        TimeEditSchedule timeEditSchedule = new TimeEditSchedule();
        Document doc = null;
        //Objects to return and/or manage during parsing
            ScheduleWeek week = null;
            List<ScheduleWeek> weeks = new ArrayList<ScheduleWeek>();
            ScheduleDay day = null;
            List<ScheduleDay> days = new ArrayList<ScheduleDay>();
            ScheduleLecture lecture = null;
            List<ScheduleLecture> lectures = new ArrayList<ScheduleLecture>();
            ScheduleCourse course = null;
            List<ScheduleCourse> courses = new ArrayList<ScheduleCourse>();
        try {
            doc = Jsoup.connect(siteURL).get();
        } catch (IOException ex) {
            Logger.getLogger(TimeEditService.class.getName()).log(Level.SEVERE, null, ex);
        }        
        //Catch NullPointerException during parsing, and return a null object should it fail.
        try {
            String head1 = doc.getElementsByClass("head1").first().text();
            Pattern p1 = Pattern.compile("\\d+");
            Matcher m1 = p1.matcher(head1);
            List<String> matches = new ArrayList<String>();
            while (m1.find()) {            
                matches.add(m1.group());
            }
            if(matches.size() == 3){
                timeEditSchedule.setWeekStart(matches.get(0));
                timeEditSchedule.setWeeekEnd(matches.get(1));
                timeEditSchedule.setScheduleYear(matches.get(2));
            }            
            //items to iterate over
            Element content = doc.getElementsByClass("booking").first();
            Elements rows = content.getElementsByTag("tr");
            for(Element row : rows) {
                Elements tableColumns = row.getElementsByTag("td");
                for(int i = 0; i < tableColumns.size(); i++) {
                    //Check if there are any elements with a tag that match "font" is present, do nothing if not.
                    String stringData;
                    if(!tableColumns.get(i).getElementsByTag("font").isEmpty()){
                        stringData = tableColumns.get(i).getElementsByTag("font").first().text();
                        if(stringData.contains("Uke")){ //create new week and break loop if the first element contains Uke
//                            System.out.println("NEWs WEEK: " + stringData);
                            week = new ScheduleWeek(stringData);
                            weeks.add(week);
                            break;
                        } else if(stringData.matches(".*\\w.*") && (week != null)){
    //                        System.out.println(stringData);
                            switch(i){
                                case 2: //Dag (Man, Tir, Ons etc)
                                    day = new ScheduleDay(stringData);
                                    System.out.println(stringData);
                                    break;
                                case 3: //Dato (1 Jan, 1 Apr, 4 Okt, etc)
                                    if(day != null){
                                        day.setDate(stringData);
                                        week.days.add(day);
                                    }                                
                                    System.out.println(stringData);
                                    break;
                                case 4: //Tid (8:15-12:00, to klokkeslett separert med bindestrek)
                                    lecture = new ScheduleLecture(stringData);
                                    System.out.println(stringData);
                                    break;
                                case 5: //Fag (Patologi, Matematikk osv, flere separert med komma)
                                    String[] courseArray = stringData.split(",");
                                    courses = new ArrayList<ScheduleCourse>();
                                    for(int n = 0; n < courseArray.length; n++){
                                        course = new ScheduleCourse(courseArray[n]);
                                        courses.add(course);
                                    }
                                    System.out.println(stringData);
                                    break;
                                case 6: //Type (Forelesning, eller øving)
                                    if(lecture != null){
                                        lecture.setType(stringData);
                                    }
                                    break;
                                case 7: //Klasse (BIO2, DA3 etc.. separert med komma)
                                    if(lecture != null){
                                        lecture.setClassId(stringData);
                                    }
                                    break;
                                case 8: //Lærer (etternavn+fornavn separert med komma)
                                    if(lecture != null){
                                        lecture.setTeachers(stringData);
                                    }
                                    break;
                                case 9: //Rom (C331, B423 etc)
                                    if(lecture != null){
                                        lecture.setRoom(stringData);
                                    }
                                    break;
                                case 10: //Kommentar
                                    if(lecture != null){
                                        lecture.setComment(stringData);
                                    }
                                    break;                            
                            }
                        }
                        if(i >= 10){
                            if(day != null && lecture != null){
                                lecture.courses = courses;
                                day.lectures.add(lecture);
                                days.add(day);
                            }
                        }
                    } else {
                        System.out.println("elements with tag 'font' was empty");
                    }
                }
            }        
        } catch (NullPointerException ex) {
            return timeEditSchedule;
        }
        timeEditSchedule.setWeeks(weeks);
        return timeEditSchedule;
    }
    

    /**
     * Inner Class TimeEditSchedule
     * This class defines a TimeEdit schedule.
     */
    public static class TimeEditSchedule{
        String scheduleYear;
        String weekStart;
        String weeekEnd;
        int numberOfWeeks;
        List<ScheduleWeek> weeks;
        private TimeEditSchedule(){
            this.weeks = new ArrayList<ScheduleWeek>();
        }

        public String getScheduleYear() {
            return scheduleYear;
        }

        public void setScheduleYear(String scheduleYear) {
            this.scheduleYear = scheduleYear;
        }

        public String getWeekStart() {
            return weekStart;
        }

        public void setWeekStart(String weekStart) {
            this.weekStart = weekStart;
        }

        public String getWeeekEnd() {
            return weeekEnd;
        }

        public void setWeeekEnd(String weeekEnd) {
            this.weeekEnd = weeekEnd;
        }
        
        public int getNumberOfWeeks(){
            if(weeks != null){
                numberOfWeeks = weeks.size();
                return numberOfWeeks;
            } else {
                return 0;
            }
        }

        public List<ScheduleWeek> getWeeks() {
            return weeks;
        }

        public void setWeeks(List<ScheduleWeek> weeks) {
            this.weeks = weeks;
        }
    }
    
    /**
     * Inner Class ScheduleWeek
     * This class defines a Week in the schedule.
     */
    public static class ScheduleWeek{
        String weekString;
        String weekNo;
        List<ScheduleDay> days;
        private ScheduleWeek(String weekString){
            this.days = new ArrayList<ScheduleDay>();
            this.weekString = weekString;
        }

        public String getWeekString() {
            return weekString;
        }

        public void setWeekString(String weekString) {
            this.weekString = weekString;
        }

        public String getWeekNo() {
            if((weekNo == null || weekNo.isEmpty()) && !weekString.isEmpty()){
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(weekString);
                if(matcher.find()){
                    weekNo = matcher.group();
                    return weekNo;
                }
                return "";
            }
            return weekNo;
        }

        public void setWeekNo(String weekNo) {
            this.weekNo = weekNo;
        }

        public List<ScheduleDay> getDays() {
            return days;
        }

        public void setDays(List<ScheduleDay> days) {
            this.days = days;
        }
        
        
    }

    /**
     * Inner class ScheduleDay
     */
    public static class ScheduleDay {
        String day;
        String date;
        List<ScheduleLecture> lectures;

        private ScheduleDay(String dag) {
            this.lectures = new ArrayList<ScheduleLecture>();
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

        public List<ScheduleLecture> getLectures() {
            return lectures;
        }

        public void setLectures(List<ScheduleLecture> lectures) {
            this.lectures = lectures;
        }
    }
    
    /**
     * Inner class ScheduleLecture
     */
    public static class ScheduleLecture{
        String time;
        String lectureStart;
        String lectureEnd;
        String type;
        String classId;
        String room;
        String teachers;
        String comment;
        List<ScheduleCourse> courses;  
        
        public ScheduleLecture(String time){
            this.courses = new ArrayList<ScheduleCourse>();
            this.time = time;
            String[] tempString;
            tempString = time.split("-");
            if(tempString.length >= 2){
                lectureStart = tempString[0];
                lectureEnd = tempString[1];
            }
        }
        
        public void setTime(String time) {
            this.time = time;
        }

        public String getLectureStart() {
            if(lectureStart.isEmpty()){
                if (time.isEmpty()) {
                    return "";
                } else {
                    String[] s = time.split("-");
                    return s[0];
                }
            } 
            return lectureStart;
        }

        public void setLectureStart(String lectureStart) {
            this.lectureStart = lectureStart;
        }

        public String getLectureEnd() {
            if(lectureEnd.isEmpty()){
                if (time.isEmpty()) {
                    return "";
                } else {
                    String[] s = time.split("-");
                    return s[1];
                }
            } 
            return lectureEnd;
        }

        public void setLectureEnd(String lectureEnd) {
            this.lectureEnd = lectureEnd;
        }
        
        public List<ScheduleCourse> getCourses() {
            return courses;
        }

        public void setCourses(List<ScheduleCourse> courses) {
            this.courses = courses;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }
        
        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }

        public String getTeachers() {
            return teachers;
        }

        public void setTeachers(String teachers) {
            this.teachers = teachers;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
        
        public void addCourseToLecture(ScheduleCourse course){
            courses.add(course);
        }
    }

    /**
     * Inner class Course
     */
    public static class ScheduleCourse {

        String courseName;
        String courseID;

        private ScheduleCourse(String courseName) {
            this.courseName = courseName;
        }
        
        public String getCourseID() {
            return courseID;
        }

        public void setCourseID(String courseID) {
            this.courseID = courseID;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }
    }
}
