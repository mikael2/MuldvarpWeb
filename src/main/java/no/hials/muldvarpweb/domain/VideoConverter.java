package no.hials.muldvarpweb.domain;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import no.hials.muldvarpweb.service.VideoService;

/**
 *
 * @author kristoffer
 */
@Named
@SessionScoped
public class VideoConverter implements Converter, Serializable {
    @Inject VideoService videoService;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Object result = null;
        
        try {
            //System.out.println("getAsObject " + value + " videoservice is " + videoService);
            result = videoService.getVideos(Integer.valueOf(value));
            //System.out.println("Got object " + result);
        } catch(Throwable t) {
            System.out.println("Error: " + t.getMessage());
            t.printStackTrace();
        }
        
        return result;
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        //System.out.println("getAsString " + value);
        
        Video v = (Video)value;
        return v.getId().toString();
    }
}
