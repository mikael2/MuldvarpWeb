/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.utility;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import no.hials.muldvarpweb.domain.Programme;

/**
 * This is a custom converter for the Programme entity class.
 * 
 * @author johan
 */
@FacesConverter("ProgrammeConverter")
public class ProgrammeConverter implements Converter{

    /**
     * Overridden method from Converter
     * 
     * @param context
     * @param component
     * @param value
     * @return 
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    /**
     * Overridden method from Converter
     * 
     * @param context
     * @param component
     * @param value
     * @return 
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        Programme currentProgramme = (Programme) value;
      
        return currentProgramme.getName();
    }
    
}
