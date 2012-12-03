package no.hials.muldvarpweb.service;

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
import no.hials.muldvarpweb.domain.Programme;
import no.hials.muldvarpweb.domain.Quiz;

/**
 * Service class for the Programme entities.
 * 
 * 
 * @author johan
 */
@Stateless
@Path("programme")
public class ProgrammeService {
    
    @PersistenceContext
    EntityManager em;
    
    /**
     * This function merges and persists a Programme item .
     * 
     * @param newProgramme The Programme to be added.
     */
    public void addProgramme(Programme newProgramme){
        newProgramme = em.merge(newProgramme);
        em.persist(newProgramme);
    }
    
    /**
     * Function that returns all Programmes based on 
     * 
     * @return Programmes
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Programme> findProgrammes() {
        return em.createQuery("SELECT v from Programme v", Programme.class).getResultList();
    }
    
    @GET
    @Path("courses/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Course> findCoursesInProgrammes(@PathParam("id") Integer id) {
        TypedQuery<Course> q = em.createQuery("SELECT v.courses from Programme v where v.id = :id", Course.class);
        q.setParameter("id", id);
        return q.getResultList();
    }
    
    @GET
    @Path("quiz/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Quiz> findQuizzesInProgrammes(@PathParam("id") Integer id) {
        TypedQuery<Quiz> q = em.createQuery("SELECT q.quizzes from Programme q where q.id = :id", Quiz.class);
        q.setParameter("id", id);
        return q.getResultList();
    }
        
    /**
     * Function that returns all Programmes corresponding to one ID.
     * 
     * @param id 
     * @return Programmes
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Programme getProgrammes(@PathParam("id") Integer id) {
        TypedQuery<Programme> q = em.createQuery("Select v from Programme v where v.id = :id", Programme.class);
        q.setParameter("id", id);
        
        return q.getSingleResult();
    }

    public void editProgramme(Programme selected) {
        selected = em.merge(selected);
        em.persist(selected);
    }

    public void removeCourseFromProgramme(Programme selected, Course c) {
        selected.removeCourse(c);
        editProgramme(selected);
    }

    public void removeProgramme(Programme p) {
        p = em.merge(p);
        em.remove(p);
    }
}
