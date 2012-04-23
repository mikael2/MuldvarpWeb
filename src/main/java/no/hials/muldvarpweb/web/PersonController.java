package no.hials.muldvarpweb.web;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import no.hials.muldvarpweb.domain.Person;
import no.hials.muldvarpweb.service.PersonService;

/**
 *
 * @author Lena
 */
@Named
@SessionScoped
public class PersonController implements Serializable {
    
    @Inject PersonService personService;
    Person newPerson;
    List<Person> personList;
    Person selectedPerson;
    Person filter;
    
    public PersonService getService() {     
        return personService;
    }
    
    public Person getPerson() {
        if (newPerson == null) {
            newPerson = new Person();
        }
        
        return newPerson;
    }
    
    public List<Person> getPeople () { 
        return personService.findPeople();
    }
    
    public Person getPerson(Long id) {
        return personService.getPerson(id);
    }

    public void setPerson(Person person) {
        this.newPerson = person;
    }

    public void savePerson() {

        //if(newArticle != null) {
        System.out.println("Submitting person " + newPerson);
        newPerson = personService.addPerson(newPerson);
        System.out.println("Submitted person " + newPerson);
        clearPerson();
        // }
    }
    
    public void clearPerson() {
        newPerson = null;
    }

    public void deletePerson() {
        System.out.println("Deleting person " + newPerson);
        personService.removePerson(newPerson);
        System.out.println("Clearing person " + newPerson);
        clearPerson();
    }

}
