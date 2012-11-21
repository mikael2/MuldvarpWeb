/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author kristoffer
 */
@Entity
@Table(name = "quiz")
public class Quiz implements Serializable {
    @ManyToMany(mappedBy = "quizzes")
    private List<Programme> programmes;
    public enum QuizType {
        FEEDBACK("Feedback"),
        REMOTE("Remote"),
        REMOTEFEEDBACK("Remote med feedback"),
        GUIDE("Guide");        
        private String quizType;        
        private QuizType(String quizType){        
            this.quizType = quizType;
        }
        public String getName() {            
            return quizType;
        }
    }
    @Id
    @GeneratedValue
    long id;
    String name;
    String description;
    @OneToMany
    List<Question> questions;
    QuizType quizType; 
    boolean shuffleQuestions;
    
    public Quiz(){
        
    }
    
    public Quiz(String name, ArrayList<Question> questions, QuizType quizType){
        this.name = name;
        this.questions = questions;
        this.quizType = quizType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isShuffleQuestions() {
        return shuffleQuestions;
    }

    public void setShuffleQuestions(boolean shuffleQuestions) {
        this.shuffleQuestions = shuffleQuestions;
    }
}
