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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import no.hials.muldvarpweb.domain.*;
import no.hials.muldvarpweb.fragments.Fragment;

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
    public Course getCourse(@PathParam("id") Long id) {
        TypedQuery<Course> q = em.createQuery("Select c from Course c where c.id = :id", Course.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }
    
    public List<Course> getCourse(String name) {       
        TypedQuery<Course> q =  em.createQuery("Select c from Course c where c.name LIKE :name", Course.class);
        q.setParameter("name", "%" + name + "%");
        return q.getResultList();
    }
    
    public List<Programme> getProgrammesInCourse(@PathParam("id") Long id) {
        TypedQuery<Programme> q = em.createQuery("SELECT v.programmes from Course v where v.id = :id", Programme.class);
        q.setParameter("id", id);
        return q.getResultList();
    }
    
    @GET
    @Path("/videos/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Video> findVideosInCourse(@PathParam("id") Long id) {
        TypedQuery<Video> q = em.createQuery("SELECT v.videos from Course v where v.id = :id", Video.class);
        q.setParameter("id", id);
        return q.getResultList();
    }
    
    @GET
    @Path("quiz/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Quiz> findQuizzesInCourse(@PathParam("id") Long id) {
        TypedQuery<Quiz> q = em.createQuery("SELECT q.quizzes from Course q where q.id = :id", Quiz.class);
        q.setParameter("id", id);
        return q.getResultList();
    }
    
    @GET
    @Path("edit/{cid}/{themeid}/{taskid}/{val}")
    public String setTask(@PathParam("cid") Long cid,
    @PathParam("themeid") Integer themeid,
    @PathParam("taskid") Integer taskid,
    @PathParam("val") Integer val) {
        // Sette task som done
        // ide: bruke lagre knapp og lagre alt i ett
        String retval = "ERROR\n";
        Course c = getCourse(cid);
        List<Theme> themes = c.getThemes();
        Theme theme = null;
        for(int i = 0; i < themes.size(); i++) {
            if(themes.get(i).getId() == themeid) {
                theme = themes.get(i);
                break;
            }
        }
        
        if(theme != null) {
            List<Task> tasks = theme.getTasks();
            for(int i = 0; i < tasks.size(); i++) {
                if(tasks.get(i).getId() == taskid) {
                    Task task = tasks.get(i);
                    if(val == 1) {
                        task.setDone(true);
                    } else if (val == 0) {
                        task.setDone(false);
                    }
                    editTask(c, theme, task);
                    retval = "Task completed";
                }
            }
        } else {
            retval += "Theme == null";
        }
        
        return retval;
    }
    
    public void addCourse(Course course) {
        persist(course);
    }
    
    public Course persist(Course c) {
        if(c.getId() == null) {
            em.persist(c);
        }
        else {
            c = em.merge(c);
        }
        //em.persist(c);
        
        return c;
    }
    
    public void addNewRevCourse(Course course) {
        course = new Course(course.getName(), course.getDetail(), course.getImageurl(), course.getRevision(), course.getThemes(), course.getObligatoryTasks(), course.getExams(), course.getTeachers(), course.getProgrammes());
        course.setRevision(course.getRevision()+1);
        persist(course);
    }
    
    public void editCourse(Course course) {        
        persist(course);
    }
    
    public void removeCourse(Course course) {
        for(Fragment f : course.getFragments()) {
            if(f.getCourses().contains(course)) {
                f.getCourses().remove(course);
                em.merge(f);
            }
        }
        course = em.merge(course);
        em.remove(course);
    }
    
    public Course addTheme(Course course, Theme theme) {
        course.addTheme(theme);
        return persist(course);
    }
    
    public Course editTheme(Course course, Theme theme) {
        course.editTheme(theme);
        return persist(course);
    }
    
    public Course removeTheme(Course course, Theme theme) {
        course.removeTheme(theme);
        return persist(course);
    }
    
    public Course addTask(Course course, Theme theme, Task task) {
        course.addTask(theme, task);
        return persist(course);
    }
    
    public Course editTask(Course course, Theme theme, Task task) {
        course.editTask(theme, task);
        return persist(course);
    }
    
    public Course removeTask(Course course, Theme theme, Task task) {
        course.removeTask(theme, task);
        return persist(course);
    }
    
    public Course addObligatoryTask(Course course, ObligatoryTask obligtask) {
        course.addObligatoryTask(obligtask);
        return persist(course);
    }
    
    public Course editObligatoryTask(Course course, ObligatoryTask obligtask) {
        course.editObligatoryTask(obligtask);
        return persist(course);
    }
    
    public Course removeObligatoryTask(Course course, ObligatoryTask obligtask) {
        course.removeObligatoryTask(obligtask);
        return persist(course);
    }
    
    public void acceptObligatoryTask(Course course, ObligatoryTask obligtask) {
        obligtask.acceptTask();
        editObligatoryTask(course, obligtask);
    }
    
    public Course addExam(Course course, Exam exam) {
        course.addExam(exam);
        return persist(course);
    }
    
    public Course editExam(Course course, Exam exam) {
        course.editExam(exam);
        return persist(course);
    }
    
    public Course removeExam(Course course, Exam exam) {
        course.removeExam(exam);
        return persist(course);
    }
    
    public void addProgramme(Course c, Programme p) {
        c.addProgramme(p);
        editCourse(c);
    }
        
    public void makeTestData() {     
        Course retVal = new Course("Fagnavn");
        retVal.setDetail("Details");
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss");
        Date date = new Date();
        retVal.setRevision(1);
        retVal.setRevision_date(new Date());
        
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
        oblig1.acceptTask();
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
        task = new Task("Les dette");
        task.setDone(true);
        task.setContentType("PDF");
        tasks2.add(task);
        
        task = new Task("Quiz");
        task.setContentType("Quiz");
        ArrayList<Question> questions = new ArrayList<Question>();
        Question q = new Question();
        q.setName("Spørsmål");
        ArrayList<Alternative> alts = new ArrayList<Alternative>();
        Alternative a = new Alternative();
        a.setName("Alternativ 1");
        alts.add(a);
        a = new Alternative();
        a.setName("Alternativ 2");
        alts.add(a);
        q.setAlternatives(alts);
        questions.add(q);
//        task.setQuestions(questions);
        tasks2.add(task);
        
        task = new Task("Læringsvideo 1");
        task.setContent_url("XsFR8DbSRQE");
        task.setContentType("Video");
        tasks2.add(task);
        theme2.setTasks(tasks2);
        themes.add(theme2);
        
      
        retVal.setThemes(themes);
        em.persist(retVal);
        
        Programme prog = new Programme("Ingeniør IKT", "blablabla");
        prog.addCourse(retVal);
        retVal.addProgramme(prog);
        
        
        Course matCourse = makeMatBIKT();;
        prog.addCourse(matCourse);
        matCourse.addProgramme(prog);
        
        matCourse = em.merge(matCourse);
        retVal = em.merge(retVal);       
        
        
    }
    
    public Course makeMatBIKT(){
        
        Course retVal = new Course("Matematikk B IKT");
        retVal.setDetail("Matematikkfag for Ingeniørstudiet.");
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss");
        Date date = new Date();
        retVal.setRevision(1);
        retVal.setRevision_date(new Date());
        
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
        
        Theme theme1 = new Theme("Derivasjon");
        ArrayList<Task> tasks = new ArrayList<Task>();
        Task task = new Task("Oppgave 1.1");
        tasks.add(task);
        task = new Task("Oppgave 1.2");
        tasks.add(task);
        theme1.setTasks(tasks);
        themes.add(theme1);
        
        Theme theme2 = new Theme("Integrasjon");
        ArrayList<Task> tasks2 = new ArrayList<Task>();
        task = new Task("Les dette");
        task.setDone(true);
        task.setContentType("PDF");
        tasks2.add(task);
        
        task = new Task("Quiz");
        task.setContentType("Quiz");
        ArrayList<Question> questions = new ArrayList<Question>();
        Question q = new Question();
        q.setName("Spørsmål");
        ArrayList<Alternative> alts = new ArrayList<Alternative>();
        Alternative a = new Alternative();
        a.setName("Alternativ 1");
        alts.add(a);
        a = new Alternative();
        a.setName("Alternativ 2");
        alts.add(a);
        q.setAlternatives(alts);
        questions.add(q);
//        task.setQuestions(questions);
        tasks2.add(task);
        
        task = new Task("Læringsvideo 1");
        task.setContent_url("XsFR8DbSRQE");
        task.setContentType("Video");
        tasks2.add(task);
        theme2.setTasks(tasks2);
        themes.add(theme2);
        
        retVal.setThemes(themes);
        em.persist(retVal);
                        
        return retVal;  
        
    }
}
