/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.service.scrape;

import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Service class for the Quiz entities.
 * 
 * @author johan
 */
@Stateless
@Path("fronter")
public class FronterService {
    
    @GET
    @Path("dummy")
    @Produces({MediaType.APPLICATION_JSON})
    public Fronter getDummyData(@PathParam("id") String id) {
        return null;
    }
    
    static class Fronter {
        List<String> messages;
        List<String> documents;
    }
}
