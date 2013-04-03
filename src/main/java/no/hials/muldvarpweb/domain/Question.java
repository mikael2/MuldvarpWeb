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
    Long id;
    
    
    String name;
    String questionType;  
    boolean shuffleAlternatives;
    
    @OneToMany(cascade = CascadeType.ALL)
    List<Alternative> alternatives;    
    
    @OneToOne
    Alternative answer;
    
    public Question(){
        
    }
    
    public Question(String name, List alternatives){
        this.name = name;
        setAlternatives(alternatives);
    }
    
    public Question(String name, List alternatives, String questionType){
        this.name = name;
        this.questionType = questionType;
        setAlternatives(alternatives);
    }

    public Long getId() {
        return id;
    }

    public boolean isShuffleAlternatives() {
        return shuffleAlternatives;
    }

    public void setShuffleAlternatives(boolean shuffleAlternatives) {
        this.shuffleAlternatives = shuffleAlternatives;
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
        if(name == null || name.equals("")){
            return "NO NAME SPECIFIED";
        }
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
