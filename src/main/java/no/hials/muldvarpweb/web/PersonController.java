package no.hials.muldvarpweb.web;

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
public class PersonController {
    
    @Inject PersonService videoService;
    Person newPerson;
    List<Person> personList;
    Person selectedPerson;
    Person filter;
    
}
