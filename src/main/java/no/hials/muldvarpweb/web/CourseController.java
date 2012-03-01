/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.web;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import no.hials.muldvarpweb.domain.Course;
import no.hials.muldvarpweb.service.CourseService;

/**
 *
 * @author kristoffer
 */
@Named
@SessionScoped
public class CourseController implements Serializable {
    @Inject CourseService service;
    Course newCourse;
    List<Course> courses;
    Course selected;
    Course filter;

    public List<Course> getCourses() {
        if(courses == null) {
            courses = service.findCourses();
        }
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Course getFilter() {
        return filter;
    }

    public void setFilter(Course filter) {
        this.filter = filter;
    }

    public Course getCourse() {
        if(newCourse == null)
            newCourse = new Course();
        
        return newCourse;
    }

    public void setCourse(Course course) {
        this.newCourse = course;
    }

    public Course getSelected() {
        return selected;
    }

    public void setSelected(Course selected) {
        this.selected = selected;
    }

    public CourseService getService() {
        return service;
    }

    public void setService(CourseService service) {
        this.service = service;
    }
    
    public void addCourse() {
        if(newCourse != null) {
            
//            Date ts =  new Date();
//            SimpleDateFormat sDF = new SimpleDateFormat("H:mm:ss dd.MM.yyyy");
//        
//            String date = sDF.format(ts);
            
            //newCourse.setLastUpdate(date);
            
            service.addCourse(newCourse);
        }
    }
}
