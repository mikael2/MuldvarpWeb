/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import no.hials.muldvarpweb.domain.Frontpage;

/**
 *
 * @author kristoffer
 */
@Stateless
@Path("frontpage")
public class FrontpageService {
    @PersistenceContext
    EntityManager em;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Frontpage> findFrontpage() {       
        return em.createQuery("SELECT c from Frontpage c", Frontpage.class).getResultList();
    }
    
    public Frontpage persist(Frontpage c) {
        if(c.getId() == null)
            em.persist(c);
        else
            c = em.merge(c);
        //em.persist(c);
        
        return c;
    }
}
