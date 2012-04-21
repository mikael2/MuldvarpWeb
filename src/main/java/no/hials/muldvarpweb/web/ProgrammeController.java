/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
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
    
    @Inject ProgrammeService programmeService;
    
    List<Programme> programmeList;
    Programme newProgramme;
    Programme selectedProgramme;
    
    
    /**
     * This function returns the selectedProgramme variable.
     * 
     * @return The selected Programme
     */
    public Programme getSelectedProgramme(){
        
        //Check if the selectedProgramme variable is null, and set new Programme if it is
        if (selectedProgramme == null) {
            
            selectedProgramme = new Programme();
        }
                
        return selectedProgramme;
    }
    
    /**
     * This function returns a List of Programmes from the injected ProgrammeService.
     * 
     * @return List of Programmes
     */
    public List<Programme> getProgrammes() {
        
        programmeList = programmeService.findProgrammes();
        
        return programmeList;
        
    }
    
    /**
     * This function retrieves a list of programs and creates a list of Select Items for use with JSF
     * 
     * 
     * @return List of SelectItem
     */
    public List<SelectItem> getProgrammeItems() {
        
        //To make sure the list is current
        getProgrammes();
        
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        
        for (int i = 0; i < programmeList.size(); i++) {
            
            selectItems.add(new SelectItem(programmeList.get(i), programmeList.get(i).getName()));
            
        }
        
        return selectItems;
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
        
        programmeService.addProgramme(selectedProgramme);
        
        return newProgramme;
    }
}
