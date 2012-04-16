/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.web;

import java.io.DataInputStream;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import no.hials.muldvarpweb.domain.LibraryItem;
import no.hials.muldvarpweb.service.LibraryService;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Nospherus
 */

@Named
@SessionScoped
public class LibraryController implements Serializable {
    @Inject 
    LibraryService service;
    LibraryItem newLibraryItem;
    LibraryItem selected;
    List<LibraryItem> libraryList;

    public LibraryItem getLibraryItem() {
        if(newLibraryItem == null)
            newLibraryItem = new LibraryItem();
        
        return newLibraryItem;
    }

    public void setLibraryItem(LibraryItem newLibraryItem) {
        this.newLibraryItem = newLibraryItem;
    }
    
    public void addLibraryItem(){
        service.addLibraryItem(newLibraryItem);
        clearItem();
    }
    
    public void editSelected(){
        service.addLibraryItem(selected);
        addInfo(3);
    }
    
    public void makeTestData(){
        service.makeTestData();
    }
    
    public void clearItem(){
        newLibraryItem = null;
    }
    
    public List<LibraryItem> getLibraryItems(){
        return service.getLibrary();
    }
    
    public void setLibraryItems(List<LibraryItem> libraryList){
        this.libraryList = libraryList;
    }
    
     public String setSelected(LibraryItem selected) {
        if(selected == null) {
            selected = getLibraryItem();
        }
        this.selected = selected;
        return "editDocument";
    }
     
     public void select(LibraryItem s){
         this.selected = s;
     }
     
     public void deleteLibraryItem(LibraryItem lI){
        if(lI != null) {
            service.removeLibraryItem(lI);
        }
    }
     
     public void deleteSelectedLibraryItem(){
        if(selected != null) {
            service.removeLibraryItem(selected);
        }
    }
     
     public LibraryItem getSelected(){
         return selected;
     }
     
     public void addInfo(int i, LibraryItem li) {  
         if(i==1){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Document deleted: ", li.getTitle()));
         }
    }
     
     public void addInfo(int i) { 
         switch(i){
             case 1:
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"INFO: ", "Testdata produced."));
                 break;
                 
             case 2:
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"HELP: ", "Press the edit buttons on the right side to edit the document details."));
                 break;
                 
             case 3:
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"INFO: ", "Changes registered."));
                 break;
                 
             case 4:
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"INFO: ", "This method is not yet implemented."));
                 break;
         }
    }
     public void handleFileUpload(FileUploadEvent event) {  
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
    }
}
