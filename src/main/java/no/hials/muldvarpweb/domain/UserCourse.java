/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author kristoffer
 */
@Entity
@Table(name = "userCourse")
public class UserCourse implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    
    Person person;
    CourseClass courseClass;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
