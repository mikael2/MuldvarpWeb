package no.hials.muldvarpweb.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import no.hials.muldvarpweb.domain.Person;
import no.hials.muldvarpweb.domain.Video;

/**
 *
 * @author Lena
 */
@Stateless
@Path("person")
public class PersonService {
    @PersistenceContext
    EntityManager em;
    
    @GET
    @Path("{id}")
    public Person getPerson(@PathParam("id") short id) {
        TypedQuery<Person> q = em.createQuery("Select p from Person p where p.id = :id", Person.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }
    
    public List<Person> getPerson(String name) {       
        TypedQuery<Person> q = em.createQuery("Select p from Person p where p.name LIKE :name", Person.class);
        q.setParameter("name", "%" + name + "%");
        return q.getResultList();
    }
    
}
