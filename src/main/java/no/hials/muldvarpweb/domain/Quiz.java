/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import no.hials.muldvarpweb.fragments.Fragment;

/**
 * JPA-class defining a Quiz
 * 
 * @author johan
 */
@Entity
@Table(name = "quiz")
public class Quiz implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    String name;
    String description;
    
    @OneToMany(cascade = CascadeType.ALL)
    List<Question> questions;
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
    QuizType quizType;
    boolean shuffleQuestions;
    @OneToOne
    Question beingEdited;
    
    @ManyToMany(mappedBy = "quizzes")
    List<Fragment> fragments;
    
    public Quiz(){
        questions = new ArrayList<Question>();
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

    public void setBeingEdited(Question beingEdited) {
        this.beingEdited = beingEdited;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    
    public void setAlternatives(Question q){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public QuizType getQuizType() {
        return quizType;
    }

    public void setQuizType(QuizType quizType) {
        this.quizType = quizType;
    }
    

    public void removeQuestion(Question q) {
        questions.remove(q);
    }

    public void addQuestion(Question newQuestion) {
        questions.add(newQuestion);
    }
    
    public void addAlternative(Question q, Alternative a){
        q.addAlternative(a);
    }
    
    public void removeAlternative(Question q, Alternative a){
        q.removeAlternative(a);
    }
    
    public void updateQuestion(Question q){
        if(q!=null && beingEdited!=null){
            questions.remove(beingEdited);
            questions.add(q);
        }
    }

//    @Override
//    public boolean equals(Object obj) {
//        if(!(obj instanceof Quiz)) {
//            return false;
//        }
//        
//        Quiz quiz = (Quiz) obj;
//        
//        return (this.id == quiz.id);
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
//        return hash;
//    }

    @XmlTransient
    public List<Fragment> getFragments() {
        return fragments;
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }
}
