/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import no.hials.muldvarpweb.domain.LibraryItem;

/**
 *
 * @author Nospherus
 */
@Stateless
@Path("Library")
public class LibraryService {
    @PersistenceContext
    EntityManager entityManager;
        
    
    
    
    public ArrayList<LibraryItem> getlibraryTestData() {  
        ArrayList<LibraryItem> libraryList = new ArrayList<LibraryItem>();
        for (int i = 0; i < 10; i++) {
            libraryList.add(new LibraryItem("this is a title", "This is a summary"));
        }
        return libraryList;
    }
        
//        @GET
//        @Produces({MediaType.APPLICATION_JSON})
//        public List<LibraryItem> findLibraryItems() {
//            ArrayList lA = new ArrayList<LibraryItem>();
////            while(true){
////            TypedQuery<LibraryItem> l = entityManager.createQuery("SELECT c FROM LibraryItem c", LibraryItem.class);
////            LibraryItem lib = l.getSingleResult();
////            if(lib==null){break;}
////            lA.add(lib);
////        }
//            return getlibraryTestData();   //For testing only
//        }
        
        @GET
        @Produces({MediaType.APPLICATION_JSON})
        public LibraryItem getTestLibrary(){
            LibraryItem lI = new LibraryItem("this is a title", "This is a summary");
            return lI;
        }
    
    
    
//        public LibraryItem getLibraryItem(@PathParam("id") Long id){
//            TypedQuery<LibraryItem> l = entityManager.createQuery("SELECT c FROM LibraryItem c WHERE c.id = :id", LibraryItem.class);
//            l.setParameter("id", id);
//            return l.getSingleResult();
//        }
        
        
}
