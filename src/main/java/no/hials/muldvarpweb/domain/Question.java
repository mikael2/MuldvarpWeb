/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author kristoffer
 */
@Entity
@Table(name = "question")
public class Question implements Serializable {
    @Id
    @GeneratedValue
    long id;
    String name;
    String questionType;  
    @OneToMany
    List<Alternative> alternatives;    
    @OneToOne
    Alternative answer;
    
    public Question(){
        
    }
    
    public Question(String name, List alternatives){
        this.name = name;
        setAlternatives(alternatives);
    }

    public long getId() {
        return id;
    }

    public List<Alternative> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<Alternative> alternatives) {
        this.alternatives = alternatives;
    }

    public Alternative getAnswer() {
        return answer;
    }

    public void setAnswer(Alternative answer) {
        this.answer = answer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
    

    public void addAlternative(Alternative newAlternative) {
        alternatives.add(newAlternative);
    }

    public void removeAlternative(Alternative a) {
        alternatives.remove(a);
    }
}
