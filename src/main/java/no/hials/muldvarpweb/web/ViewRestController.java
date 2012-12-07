package no.hials.muldvarpweb.web;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Mikael
 */
@Named
@RequestScoped
public class ViewRestController {
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
