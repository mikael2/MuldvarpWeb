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
import no.hials.muldvarpweb.domain.Programme;

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
    EntityManager entityManager;
    
    
    /**
     * This function merges and persists a Programme item .
     * 
     * @param newProgramme The Programme to be added.
     */
    public void addProgramme(Programme newProgramme){
        
        newProgramme = entityManager.merge(newProgramme);
        entityManager.persist(newProgramme);
    }
    
    
    /**
     * Function that returns all Programmes based on 
     * 
     * @return Programmes
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Programme> findProgrammes() {
        
        return entityManager.createQuery("SELECT v from Programme v", Programme.class).getResultList();
        
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
        TypedQuery<Programme> q = entityManager.createQuery("Select v from Programme v where v.id = :id", Programme.class);
        q.setParameter("id", id);
        
        
        return q.getSingleResult();
    }
    
}
