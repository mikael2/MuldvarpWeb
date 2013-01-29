/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.web;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author johan
 */
@Named
@SessionScoped
public class DocumentationController implements Serializable {
    String pageName = "documentation/empty";
    
    public DocumentationController(){
        
    }
    
    public String navigate() {
        String newPageName = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("page");
        this.pageName = newPageName;
        return "viewDocumentation?faces-redirect=true";
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
    
}
