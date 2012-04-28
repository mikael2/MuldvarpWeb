/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.web;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    Video videoForDeletion;
    String filterString;
    
    
    /**
     * This function returns a list of videos.
     * @return List<Video>
     */
    public List<Video> getVideos(){
        
        return videoService.findVideos();
    }
    
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
     * This function returns a List of Videos from VideoService based on a
     * global variable in the VideoController class.
     * 
     * @return List of Video
     */
    public List<Video> getNameFilteredVideos(){
        
        //Make sure the filterString is not null but has a value, even if it's empty
        if(filterString == null){
            filterString = "";
        }
        
        return videoService.findVideosByName(filterString);
    }
    
    
    /**
     * This function makes a call to the VideoService instantiation and adds the supplied Video.
     * 
     * @param newVideo The Video to be added.
     * @return 
     */
    public Video addVideo() {
        
        videoService.addVideo(selectedVideo);
        
        return newVideo;
    }
    
    public void selectVideo(Video video){
        
        this.selectedVideo = video;
    }
    
        
    public void removeVideo(Video video){
        
        if(video != null){
            videoService.removeVideo(video);
        }
    }
    
    public void removeSelectedVideo(){
        
        if(selectedVideo != null){
            videoService.removeVideo(selectedVideo);
        }
    }
    
    public void editSelected() {
        videoService.addVideo(selectedVideo);
        addInfo(3);
    }
    
    public void setVideoForDeletion(Video video){
        
        this.videoForDeletion = video;
    }
    
    public void setupVideoForDeletion(Video video){
        
        this.videoForDeletion = video;
    }

    public Video getVideoForDeletion() {
        return videoForDeletion;
    }
    
    public void deleteVideoForDeletion(){
        
        if(videoForDeletion != null){
            videoService.removeVideo(newVideo);
        }
    }
    
    
    public void addInfo(int i, Video video){
        
        if (i == 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Video deleted: ", video.getVideoName()));
        }        
    }
    
    public void addInfo(int i) { 
         switch(i){
             case 1:
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"INFO: ", "Testdata produced."));
                 break;
                 
             case 2:
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"HELP: ", "Press the edit buttons on the right side to edit the video details."));
                 break;
                 
             case 3:
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"INFO: ", "Changes registered."));
                 break;
                 
             case 4:
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"INFO: ", "This method is not yet implemented."));
                 break;
             case 5:
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"INFO: ", "Edit the fields and press submit when done."));
                 break;    
         }
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
    public void makeVideoTestData() {
        
        videoService.makeVideoTestData();        
                
    }

    public String getFilterString() {
        return filterString;
    }

    public void setFilterString(String filterString) {
        this.filterString = filterString;
    }
    
    
}
