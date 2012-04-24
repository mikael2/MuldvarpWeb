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
import no.hials.muldvarpweb.domain.Person;

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
    @Produces({MediaType.APPLICATION_JSON})
    public List<Person> findPeople() {       
        return em.createQuery("SELECT p from Person p", Person.class).getResultList();
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Person getPerson(@PathParam("id") Long id) {
        TypedQuery<Person> q = em.createQuery("Select p from Person p where p.id = :id", Person.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }
    
    public List<Person> findPerson(String name) {       
        TypedQuery<Person> q = em.createQuery("Select p from Person p where p.name LIKE :name", Person.class);
        q.setParameter("name", "%" + name + "%");
        return q.getResultList();
    }
    
    public Person addPerson(Person newPerson) {
        newPerson= em.merge(newPerson);
        em.persist(newPerson);
        return newPerson;
        }

    public void removePerson(Person person) {
        person = em.merge(person);
        em.remove(person);
    }
    
}
