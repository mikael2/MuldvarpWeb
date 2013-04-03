/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import no.hials.muldvarpweb.domain.Alternative;
import no.hials.muldvarpweb.domain.Question;
import no.hials.muldvarpweb.domain.Quiz;
import no.hials.muldvarpweb.fragments.Fragment;

/**
 * Service class for the Quiz entities.
 * 
 * @author johan
 */
@Stateless
@Path("quiz")
public class QuizService {
    
    @PersistenceContext
    EntityManager em;
    
    /**
     * This function merges and persists a Programme item .
     * 
     * @param newQuiz The Programme to be added.
     */
    public void addQuiz(Quiz newQuiz){
        newQuiz = em.merge(newQuiz);
        em.persist(newQuiz);
    }
    
    /**
     * Function that returns all Quizzes 
     * 
     * @return List<Quiz> List of Quiz items
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Quiz> findQuizzes() {
        return em.createQuery("SELECT q from Quiz q", Quiz.class).getResultList();
    }
        
    /**
     * Function that returns all Quizzes corresponding to one ID.
     * 
     * @param id 
     * @return Quiz
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Quiz getQuiz(@PathParam("id") Long id) {        
        TypedQuery<Quiz> q = em.createQuery("Select q from Quiz q where q.id = :id", Quiz.class);
        q.setParameter("id", id);        
        return q.getSingleResult();
    }

    public void editQuiz(Quiz selected) {
        if(selected.getId() == null) {
            em.persist(selected);
        }
        else {
            em.merge(selected);
        }
    }

    public void removeQuiz(Quiz q) {
        for(Fragment f : q.getFragments()) {
            if(f.getQuizzes().contains(q)) {
                f.getQuizzes().remove(q);
                em.merge(f);
            }
        }
        q = em.merge(q);
        em.remove(q);
    }
    
    /**
     * This method returns a List of Quiz objects based on the name of the Quiz.
     * @param name
     * @return 
     */
    public List<Quiz> findQuizzesByName(String name){        
        TypedQuery<Quiz> query = em.createQuery("SELECT q from Quiz q where UPPER(q.name) LIKE :name", Quiz.class);
        query.setParameter("name", "%" + name + "%");        
        return query.getResultList();
    }
        
    public void addAlternative(Quiz q, Question quest, Alternative a){
        q.addAlternative(quest, a);
    }
    
}
