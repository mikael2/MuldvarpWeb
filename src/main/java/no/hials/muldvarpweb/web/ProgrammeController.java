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
import no.hials.muldvarpweb.domain.Course;
import no.hials.muldvarpweb.domain.Programme;
import no.hials.muldvarpweb.service.ProgrammeService;

/**
 * This is the controller for the ProgrammeService.
 * 
 * @author johan
 */
@Named
@SessionScoped
public class ProgrammeController implements Serializable{
    
    @Inject ProgrammeService service;
    
    List<Programme> programmeList;
    Programme newProgramme;
    Programme selected;
    
    
    /**
     * This function returns the selectedProgramme variable.
     * 
     * @return The selected Programme
     */
    public Programme getSelected(){
        
        //Check if the selectedProgramme variable is null, and set new Programme if it is
        if (selected == null) {
            
            selected = new Programme();
        }
                
        return selected;
    }
    
    public String setSelected(Programme selected) {
        if(selected == null) {
            selected = getProgramme();
        }
        this.selected = selected;
        return "editProgramme?faces-redirect=true";
    }

    public Programme getProgramme() {
        return newProgramme;
    }

    public void setNewProgramme(Programme newProgramme) {
        this.newProgramme = newProgramme;
    }
    
    public String editProgramme() {
        if(selected != null) {
            service.editProgramme(selected);
        }
        return "listProgrammes";
    }
    
    /**
     * This function returns a List of Programmes from the injected ProgrammeService.
     * 
     * @return List of Programmes
     */
    public List<Programme> getProgrammes() {
        programmeList = service.findProgrammes();
        return programmeList;
    }
    
    
    /**
     * This function makes a call to the ProgrammeService instantiation and adds the supplied Programme.
     * 
     * @param newProgramme The Programme to be added.
     * @return 
     */
    public Programme addProgramme() {
        
//        //Check if there is a Programme to add
//        if(selectedProgramme != null){
//        
//            
//        }
        
        service.addProgramme(selected);
        return newProgramme;
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
    
    public void removeCourseFromProgramme(Course c) {
        service.removeCourseFromProgramme(selected, c);
    }
}
