/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.web;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import no.hials.muldvarpweb.domain.Quiz;
import no.hials.muldvarpweb.service.QuizService;

/**
 * This is the controller for the QuizService.
 * 
 * @author johan
 */
@Named
@SessionScoped
public class QuizController implements Serializable{
    
    @Inject QuizService service;
    
    List<Quiz> quizList;
    Quiz newQuiz;
    Quiz selected;
    
    
    /**
     * This function returns the selectedQuiz variable.
     * 
     * @return The selected Quiz
     */
    public Quiz getSelected(){        
        //Check if the selectedQuiz variable is null, and set new Quiz if it is
        if (selected == null) {            
            selected = new Quiz();
        }                
        return selected;
    }
    
    public String setSelected(Quiz selected) {
        if(selected == null) {
            selected = getQuiz();
        }
        this.selected = selected;
        return "editQuiz?faces-redirect=true";
    }

    public Quiz getQuiz() {
        if(newQuiz == null)
            newQuiz = new Quiz();
        return newQuiz;
    }

    public void setQuiz(Quiz newQuiz) {
        this.newQuiz = newQuiz;
    }
    
    public String editQuiz() {
        if(selected != null) {
            service.editQuiz(selected);
        }
        return "listvQuiz";
    }
    
    public String removeQuiz() {
        if(selected != null ) {
            service.removeQuiz(selected);
        }
        return "listQuiz?faces-redirect=true";
    }
    
    /**
     * This function returns a List of Quizzes from the injected QuizService.
     * 
     * @return List of Quizzes
     */
    public List<Quiz> getQuizzes() {
        quizList = service.findQuizzes();
        return quizList;
    }

    public void setQuiz(List<Quiz> quizList) {
        this.quizList = quizList;
    }
    
    /**
     * This function makes a call to the QuizService instantiation and adds the supplied Quiz.
     * 
     * @param newQuiz The Quiz to be added.
     * @return 
     */
    public Quiz addQuiz() {
                
        service.addQuiz(selected);
        return newQuiz;
    }
    
    public void addInfo(int i) {  
        switch(i) {
            case 1:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "INFO: ", "Changes saved"));
                break;
            case 2:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "INFO: ", "Course deleted"));
                break;
            case 3:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "INFO: ", "New revision created"));
                break;
         }
    }
    
}
