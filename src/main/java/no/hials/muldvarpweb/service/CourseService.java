/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import no.hials.muldvarpweb.domain.Course;
import no.hials.muldvarpweb.domain.Exam;
import no.hials.muldvarpweb.domain.ObligatoryTask;
import no.hials.muldvarpweb.domain.Task;
import no.hials.muldvarpweb.domain.Theme;

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
        //return em.createQuery("SELECT c from Course c", Course.class).getResultList();
        
        //testdata
        Course c = new Course("Test");
        List<Course> retVal = new ArrayList<Course>();
        retVal.add(c);
        c = new Course("Hei fra muldvarpweb");
        c.setImageurl("http://developer.android.com/assets/images/bg_logo.png");
        retVal.add(c);
        for(int i = 0; i <= 30; i++) {
            
           c = new Course("Fagnavn numero " + i);
           
           //Just adding a few ifs to add variety
           //No big deal
           if(i >= 20){
               
               c.setDetail("Linje 3");
               
           } else if (i >= 10) {
               c.setDetail("Linje 2");
               
           } else  {
               c.setDetail("Linje 1");
           }
           
           retVal.add(c); 
        }
        return retVal;
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Course getCourse(@PathParam("id") Short id) {
//        TypedQuery<Course> q = em.createQuery("Select c from Course c where c.id = :id", Course.class);
//        q.setParameter("id", id);
//        return q.getSingleResult();
        
        
        // testdata
        Course retVal = new Course("Fagnavn");
        retVal.setDetail("Details");
        
        ArrayList<ObligatoryTask> obligTasks = new ArrayList<ObligatoryTask>();
        ObligatoryTask oblig1 = new ObligatoryTask("Obligatorisk 1");
        obligTasks.add(oblig1);
        oblig1 = new ObligatoryTask("Obligatorisk 2");
        obligTasks.add(oblig1);
        retVal.setObligatoryTasks(obligTasks);
        
        ArrayList<Exam> exams = new ArrayList<Exam>();
        exams.add(new Exam("Eksamen 1"));
        exams.add(new Exam("Eksamen 2"));
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
        theme2.setTasks(tasks2);
        themes.add(theme2);
        
        
        retVal.setThemes(themes);
        
        return retVal;
    }
    
    public List<Course> getCourse(String name) {       
        TypedQuery<Course> q =  em.createQuery("Select c from Course c where c.name LIKE :name", Course.class);
        q.setParameter("name", "%" + name + "%");
        return q.getResultList();
    }
}
