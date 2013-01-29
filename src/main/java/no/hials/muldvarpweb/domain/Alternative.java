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
@Table(name = "alternative")
public class Alternative implements Serializable {
    
    @Id
    @GeneratedValue
    int id;    
    String name;
    boolean isCorrect;
    String alternativeType;

    public Alternative() {
        isCorrect = false;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String getAlternativeType() {
        return alternativeType;
    }

    public void setAlternativeType(String alternativeType) {
        this.alternativeType = alternativeType;
    }
    
    public Alternative(String name, boolean isCorrect) {
        this.name = name;
        this.isCorrect = isCorrect;
    }
    
    public Alternative(String name, boolean isCorrect, String alternativeType) {
        this.name = name;
        this.isCorrect = isCorrect;
        this.alternativeType = alternativeType;
    }
    
}
