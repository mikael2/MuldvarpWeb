/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.domain;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import no.hials.muldvarpweb.service.QuizService;

/**
 *
 * @author nosph_000
 */
@Named
@SessionScoped
public class AlternativeConverter implements Converter, Serializable {
    @Inject QuizService service;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Boolean b = false;
        if(value.equalsIgnoreCase("false")){
            b = false;
        }else if(value.equalsIgnoreCase("true")){
            b = true;
        }
        return b;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
