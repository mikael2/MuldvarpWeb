/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarp.web.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import no.hials.muldvarp.web.domain.Course;

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
    public Course getCourse(@PathParam("id") Short id) {
        TypedQuery<Course> q = em.createQuery("Select c from Course c where c.id = :id", Course.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }
    
    public List<Course> getCourse(String name) {       
        TypedQuery<Course> q =  em.createQuery("Select c from Course c where c.name LIKE :name", Course.class);
        q.setParameter("name", "%" + name + "%");
        return q.getResultList();
    }
}
