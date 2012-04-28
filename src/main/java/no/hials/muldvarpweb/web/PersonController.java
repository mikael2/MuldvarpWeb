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
    
    @Inject PersonService service;
    Person newPerson;
    List<Person> personList;
    Person selected;
    Person filter;
    
    public PersonService getService() {     
        return service;
    }
    
    public Person getPerson() {
        if (newPerson == null) {
            newPerson = new Person();
        }
        
        return newPerson;
    }
    
    public List<Person> getPeople () { 
        return service.findPeople();
    }
    
    public void setPeople(List<Person> personList){
        this.personList = personList;
    }
    
    public Person getPerson(Long id) {
        return service.getPerson(id);
    }

    public void setPerson(Person person) {
        this.newPerson = person;
    }

    public void savePerson() {

        //if(newArticle != null) {
        System.out.println("Submitting person " + newPerson);
        newPerson = service.addPerson(newPerson);
        System.out.println("Submitted person " + newPerson);
        clearPerson();
        // }
    }
    
    public void clearPerson() {
        newPerson = null;
    }

    public void deletePerson(Person per) {
        System.out.println("Deleting person " + per);
        service.removePerson(per);
        System.out.println("Clearing person " + per);
        clearPerson();
    }
    
    public void deleteThePerson() {
        System.out.println("Deleting person " + newPerson);
        service.removePerson(newPerson);
        System.out.println("Clearing person " + newPerson);
        clearPerson();
    }
    
    public void editSelected(){
        service.addPerson(selected);
    }
    
    public String setSelected(Person selected) {
        if(selected == null) {
            selected = getPerson();
        }
        this.selected = selected;
        return "editPerson";
    }
    
    public void select(Person p){
         this.selected = p;
     }
    
     public void deleteSelectedPerson(){
        if(selected != null) {
            service.removePerson(selected);
        }
    }
     
     public Person getSelected(){
         return selected;
     }


}
