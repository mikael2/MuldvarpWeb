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
    public List<Frontpage> findFrontpages() {       
        return em.createQuery("SELECT c from Frontpage c", Frontpage.class).getResultList();
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Frontpage getFrontpage(@PathParam("id") Integer id) {       
        TypedQuery<Frontpage> q = em.createQuery("Select c from Frontpage c where c.id = :id", Frontpage.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }
    
    public Frontpage persist(Frontpage c) {
        if(c.getId() == null)
            em.persist(c);
        else
            c = em.merge(c);
        
        return c;
    }
}
