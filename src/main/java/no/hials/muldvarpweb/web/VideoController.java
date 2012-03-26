/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import no.hials.muldvarpweb.domain.Video;
import no.hials.muldvarpweb.service.VideoService;

/**
 * This is the controller class for Video.
 * 
 * @author johan
 */
@Named
@SessionScoped
public class VideoController implements Serializable {
    @Inject VideoService videoService;
    Video newVideo;
    List<Video> videoList;
    Video selectedVideo;
    Video filter;
    
    
    /**
     * This function returns the selectedVideo variable.
     * 
     * @return The selected Video
     */
    public Video getSelectedVideo(){
        
        //Check if the selectedVideo variable is null, and set new Video if it is
        if (selectedVideo == null) {
            
            selectedVideo = new Video();
        }
                
        return selectedVideo;
    }
    
    
    /**
     * This function makes a call to the VideoService instantiation and adds the supplied Video.
     * 
     * @param newVideo The Video to be added.
     * @return 
     */
    public Video addVideo() {
        
        //Check if there is a video to add
        if(selectedVideo != null){
        
            
        }
        
        videoService.addVideo(selectedVideo);
        
        return newVideo;
    }
    
    
    /**
     * This function generates a URI for a locally stored asset,
     * NYI
     * 
     * @return String value of URI
     */
    public String generateURI() {
        
        return "URIEXAMPLE";
    }
    
    /**
     * This function creates test data.
     * 
     * @return test data
     */
    public List<Video> makeVideoTestData() {
        
        String testURL[] = {"ygI-2F8ApUM", "Vxi7JRJrod4"};
        
        
        for (int i = 0; i < 15; i++) {
            
            //annenhver
            int n = 1;
            if(i % 2 == 0){
                n = 0;
            }
            
            videoService.addVideo(new Video(i, "Video " + i,"Youtube/ID" , testURL[n],"Video INC", "This video is not an actual video.", "www.db.no", "www.smp.no"));
            
        }
        
        return videoList;
        
    }
    
}
