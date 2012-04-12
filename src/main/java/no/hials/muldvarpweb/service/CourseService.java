/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import no.hials.muldvarpweb.domain.*;

/**
 *
 * @author kristoffer
 */
@Stateless
@Path("course")
public class CourseService {
    @PersistenceContext
    EntityManager em;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Course> findCourses() {       
        return em.createQuery("SELECT c from Course c", Course.class).getResultList();
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Course getCourse(@PathParam("id") Integer id) {
        TypedQuery<Course> q = em.createQuery("Select c from Course c where c.id = :id", Course.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }
    
    public List<Course> getCourse(String name) {       
        TypedQuery<Course> q =  em.createQuery("Select c from Course c where c.name LIKE :name", Course.class);
        q.setParameter("name", "%" + name + "%");
        return q.getResultList();
    }
    
    @POST
    @Path("edit/{cid}/{tid}/{val}")
    public void setTask(@PathParam("cid") Integer cid, @PathParam("tid") Integer tid, @PathParam("val") Integer val) {
        // Sette task som done
        // ide: bruke lagre knapp og lagre alt i ett
    }
    
    public void addCourse(Course course) {
        course = em.merge(course);
        em.persist(course);
    }
    
    public void addNewRevCourse(Course course) {
        course = new Course(course.getName(), course.getDetail(), course.getImageurl(), course.getRevision(), course.getThemes(), course.getObligatoryTasks(), course.getExams(), course.getTeachers());
        course.setRevision(course.getRevision()+1);
        course = em.merge(course);
        em.persist(course);
    }
    
    public void editCourse(Course course) {        
        course = em.merge(course);
        em.persist(course);
    }
    
    public void removeCourse(Course course) {
        course = em.merge(course);
        em.remove(course);
   }
    
    public void addTheme(Course course, Theme theme) {
        course.addTheme(theme);
        course = em.merge(course);
        em.persist(course);
    }
    
    public void editTheme(Course course, Theme theme) {
        course.editTheme(theme);
        course = em.merge(course);
        em.persist(course);
    }
    
    public void removeTheme(Course course, Theme theme) {
        course.removeTheme(theme);
        course = em.merge(course);
        em.persist(course);
    }
    
    public void addTask(Course course, Theme theme, Task task) {
        course.addTask(theme, task);
        course = em.merge(course);
        em.persist(course);
    }
    
    public void editTask(Course course, Theme theme, Task task) {
        course.editTask(theme, task);
        course = em.merge(course);
        em.persist(course);
    }
    
    public void removeTask(Course course, Theme theme, Task task) {
        course.removeTask(theme, task);
        course = em.merge(course);
        em.persist(course);
    }
    
    public void addObligatoryTask(Course course, ObligatoryTask obligtask) {
        course.addObligatoryTask(obligtask);
        course = em.merge(course);
        em.persist(course);
    }
    
    public void editObligatoryTask(Course course, ObligatoryTask obligtask) {
        course.editObligatoryTask(obligtask);
        course = em.merge(course);
        em.persist(course);
    }
    
    public void removeObligatoryTask(Course course, ObligatoryTask obligtask) {
        course.removeObligatoryTask(obligtask);
        course = em.merge(course);
        em.persist(course);
    }
    
    public void addExam(Course course, Exam exam) {
        course.addExam(exam);
        course = em.merge(course);
        em.persist(course);
    }
    
    public void editExam(Course course, Exam exam) {
        course.editExam(exam);
        course = em.merge(course);
        em.persist(course);
    }
    
    public void removeExam(Course course, Exam exam) {
        course.removeExam(exam);
        course = em.merge(course);
        em.persist(course);
    }
    
    public void makeTestData() {
        Course retVal = new Course("Fagnavn");
        retVal.setDetail("Details");
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss");
        Date date = new Date();
        
        ArrayList<ObligatoryTask> obligTasks = new ArrayList<ObligatoryTask>();
        try {
            date = df.parse("2013-11-28T12:34:56");
        } catch (ParseException ex) {
            Logger.getLogger(CourseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObligatoryTask oblig1 = new ObligatoryTask("Obligatorisk 1");
        oblig1.setDueDate(date);
        obligTasks.add(oblig1);
        oblig1 = new ObligatoryTask("Obligatorisk 2");
        Calendar c = Calendar.getInstance();
        int year = 2012;
        int month = 11;
        int day = 28;
        int hour = 12;
        int minute = 34;
        c.clear();
        c.set(year, month, day, hour, minute);
        oblig1.setDueDate(c.getTime());
        oblig1.setDone(true);
        obligTasks.add(oblig1);
        retVal.setObligatoryTasks(obligTasks); 
        
        try {
            date = df.parse("2011-12-31T12:34:56");
        } catch (ParseException ex) {
            Logger.getLogger(CourseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Exam> exams = new ArrayList<Exam>();
        Exam exam = new Exam("Eksamen 1");
        exam.setExamDate(date);
        exams.add(exam);
        exam = new Exam("Eksamen 2");
        exam.setExamDate(date);
        exams.add(exam);
        retVal.setExams(exams);
        
        ArrayList<Theme> themes = new ArrayList<Theme>();
        
        Theme theme1 = new Theme("Kult tema");
        ArrayList<Task> tasks = new ArrayList<Task>();
        Task task = new Task("Oppgave 1.1");
        tasks.add(task);
        task = new Task("Oppgave 1.2");
        tasks.add(task);
        theme1.setTasks(tasks);
        themes.add(theme1);
        
        Theme theme2 = new Theme("Dummy tema");
        ArrayList<Task> tasks2 = new ArrayList<Task>();
        task = new Task("Oppgave 2.1");
        task.setDone(true);
        tasks2.add(task);
        task = new Task("Oppgave 2.2");
        task.setDone(true);
        tasks2.add(task);
        task = new Task("Læringsvideo 1");
        task.setContent_url("XsFR8DbSRQE");
        task.setDone(true);
        tasks2.add(task);
        theme2.setTasks(tasks2);
        themes.add(theme2);
        
        
        retVal.setThemes(themes);
        
        retVal = em.merge(retVal);
        em.persist(retVal);
    }
}
