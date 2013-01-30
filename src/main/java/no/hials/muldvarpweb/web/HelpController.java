/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.web;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author nosph_000
 */
@SessionScoped
@Named
public class HelpController implements Serializable {
    String service;

    public String getService() {
        return service != null ? service : "";
    }

    public void setService(String service) {
        System.out.println("Service is " + service);
        this.service = service;
    }
    
    public String load() {
        System.out.println("Loading service is '" + getService() + "'");
        return "/viewRest.xhtml?faces-redirect=true";
    }
}