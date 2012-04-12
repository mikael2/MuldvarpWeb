/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Path;
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
    
    
    
}
