/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.domain;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import no.hials.muldvarpweb.service.CourseService;

/**
 *
 * @author kristoffer
 */
@Named
@SessionScoped
public class CourseConverter implements Converter, Serializable {
    @Inject CourseService courseService;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Object result = null;
        
        try {
            result = courseService.getCourse(Long.valueOf(value));
        } catch(Throwable t) {
            System.out.println("Error: " + t.getMessage());
            t.printStackTrace();
        }
        
        return result;
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Course v = (Course)value;
        return v.getId().toString();
    }
}
