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
import no.hials.muldvarpweb.service.QuizService;

/**
 *
 * @author kristoffer
 */
@Named
@SessionScoped
public class QuizConverter implements Converter, Serializable {
    @Inject QuizService service;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Object result = null;
        try {
            result = service.getQuiz(Long.valueOf(value));
            System.out.println("Converting string to Quiz: " + value);
        } catch(Throwable t) {
            System.out.println("Error: " + t.getMessage());
            t.printStackTrace();
        }
        
        return result;
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Quiz v = (Quiz)value;
        System.out.println("Converting Quiz to string: " + v.getId());
        //return v.getId().toString();
        return v.getId() != null ? String.valueOf(v.getId()) : null;
    }
}
