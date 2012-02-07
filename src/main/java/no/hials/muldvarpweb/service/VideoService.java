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
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import no.hials.muldvarpweb.domain.Video;

/**
 *
 * @author johan
 */
@Stateless
@Path("video")
public class VideoService {
    
    @PersistenceContext
    EntityManager entityManager;
    
    
    public List<Video> getVideoTestData() {
        
        List<Video> videoList = new ArrayList<Video>();
        
        for (int i = 0; i < 10; i++) {
            
            videoList.add(new Video("Video" + i, "www.vg.no","Video INC", "Thisvideo is not an actual video.", "www.db.no", "www.smp.no"));
            
        }
        
        return videoList;
        
    }
    
    
    @GET
    public List<Video> findVideos() {
        
        //Return videos
        //Removed for now as no database exist
        //return entityManager.createQuery("SELECT v from Video v", Video.class).getResultList();
        
        return getVideoTestData();
        
    }
    
    @GET
    @Path("{id}")
    public Video getVideo(@PathParam("id") short id) {
        TypedQuery<Video> q = entityManager.createQuery("Select v from Video v where v.id = :id", Video.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }
    
    public List<Video> getVideo(String name) {       
        TypedQuery<Video> q = entityManager.createQuery("Select v from Video c where v.name LIKE :name", Video.class);
        q.setParameter("name", "%" + name + "%");
        return q.getResultList();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
