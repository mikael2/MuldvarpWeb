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
    public enum AlternativeType {
        CHOICE("Choice"),
        TEXT("Text");
        private String alternativeType;        
        private AlternativeType(String quizType){        
            this.alternativeType = quizType;
        }
        public String getName() {            
            return alternativeType;
        }
    }
    @Id
    @GeneratedValue
    int id;    
    String name;
    boolean isCorrect;
    AlternativeType alternativeType;

    public Alternative() {
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

    public AlternativeType getAlternativeType() {
        return alternativeType;
    }

    public void setAlternativeType(AlternativeType alternativeType) {
        this.alternativeType = alternativeType;
    }
    
    
    
}
