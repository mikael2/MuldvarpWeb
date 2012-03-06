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
import no.hials.muldvarpweb.domain.*;
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
    Theme newTheme;
    Task newTask;
    ObligatoryTask newObligatoryTask;
    Exam newExam;
    List<Course> courses;
    Course selected;
    Course filter;

    public List<Course> getCourses() {
        //if(courses == null) {
            courses = service.findCourses();
        //}
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

    public String setSelected(Course selected) {
        this.selected = selected;
        return "editCourse";
    }

    public CourseService getService() {
        return service;
    }

    public void setService(CourseService service) {
        this.service = service;
    }
    
    public String addCourse() {
        if(newCourse != null) {
            
//            Date ts =  new Date();
//            SimpleDateFormat sDF = new SimpleDateFormat("H:mm:ss dd.MM.yyyy");
//        
//            String date = sDF.format(ts);
            
            //newCourse.setLastUpdate(date);
            
            service.addCourse(newCourse);
        }
        return "listCourses";
    }
    
    public void editCourse() {
        if(selected != null) {
            service.editCourse(selected);
        }
    }
    
    public String removeCourse(Course course) {
        service.removeCourse(course);
        return "listCourses";
    }
    
    public void addTheme() {
        if(newTheme != null && selected != null) {
            service.addTheme(selected, newTheme);
            newTheme = null;
        }
    }
    
    public void removeTheme(Theme theme) {
        
    }
    
    public void addExam() {
        if(newExam != null && selected != null) {
            service.addExam(selected, newExam);
            newExam = null;
        }
    }
    
    public void removeExam(Exam exam) {
        
    }
    
    public void addTask(Theme theme) {
        if(newTask != null && selected != null) {
            service.addTask(selected, theme, newTask);
            newTask = null;
        }
    }
    
    public void removeTask(Theme theme, Task task) {
        
    }
    
    public void addObligatoryTask() {
        if(newObligatoryTask != null && selected != null) {
            service.addObligatoryTask(selected, newObligatoryTask);
            newObligatoryTask = null;
        }
    }

    public Exam getExam() {
        if(newExam == null)
            newExam = new Exam();
        return newExam;
    }

    public void setExam(Exam newExam) {
        this.newExam = newExam;
    }

    public ObligatoryTask getObligatoryTask() {
        if(newObligatoryTask == null)
            newObligatoryTask = new ObligatoryTask();
        return newObligatoryTask;
    }

    public void setObligatoryTask(ObligatoryTask newObligatoryTask) {
        this.newObligatoryTask = newObligatoryTask;
    }

    public Task getTask() {
        if(newTask == null)
            newTask = new Task();
        return newTask;
    }

    public void setTask(Task newTask) {
        this.newTask = newTask;
    }

    public Theme getTheme() {
        if(newTheme == null)
            newTheme = new Theme();
        return newTheme;
    }

    public void setTheme(Theme newTheme) {
        this.newTheme = newTheme;
    }
    
    public void makeTestData() {
        service.makeTestData();
    }
}
