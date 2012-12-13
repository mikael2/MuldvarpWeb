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
import no.hials.muldvarpweb.service.CourseService;
import no.hials.muldvarpweb.service.ProgrammeService;
import org.primefaces.model.DualListModel;

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
        courses = null;
        return "editProgramme?faces-redirect=true";
    }

    public Programme getProgramme() {
        if(newProgramme == null)
            newProgramme = new Programme();
        return newProgramme;
    }

    public void setProgramme(Programme newProgramme) {
        this.newProgramme = newProgramme;
    }
    
    public String editProgramme() {
        if(selected != null) {
            service.editProgramme(selected);
        }
        return "listProgramme";
    }
    
    public String removeProgramme() {
        if(selected != null ) {
            service.removeProgramme(selected);
        }
        return "listProgramme?faces-redirect=true";
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

    public void setProgramme(List<Programme> programmeList) {
        this.programmeList = programmeList;
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
    
    // experiment zone
    private DualListModel<Course> courses;
    @Inject CourseService courseService;

    public DualListModel<Course> getCourses() {
        if(courses == null) {
            List<Course> source = courseService.findCourses();
            List<Course> target = selected.getCourses();
            if(selected.getCourses() != null) {
                target = selected.getCourses();
                
                for(int i = 0; i < target.size(); i++) {
                    Course v = target.get(i);
                    for(int k = 0; k < source.size(); k++) {
                        Course vv = source.get(k);
                        if(vv.getId().equals(v.getId())) {
                            source.remove(vv);
                            break;
                        }
                    }
                }
            }
            
            courses = new DualListModel<Course>(source, target);
        }
        
        return courses;
    }

    public void setCourses(DualListModel<Course> course) {
        this.courses = course;
    }
    
    public void addCourses(List<Course> c) {
        selected = service.setCourses(selected, courses.getTarget(), selected.getCourses());
        //selected = service.addCourses(selected, courses.getTarget());
    }
    
    public void setCourses(List<Course> c) {
        selected.setCourses(c);
    }
}
