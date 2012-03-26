/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * JPA-class defining a Video
 * 
 * @author johan
 */
//JPA-Annotation
@Entity
@Table(name="video")
public class Video implements Serializable{
    
    @Id
    @GeneratedValue   
    private Integer id;
    
    @Column(name = "videoName")
    String videoName;
    
    @Column(name = "videoType")
    String videoType;
    
    @Column(name = "videoURI")
    String videoURI;
    
    @Column(name = "videoDetail")
    String videoDetail;
    
    @Column(name = "videoDescription")
    String videoDescription;
    
    @Column(name = "videoIconURI")
    String videoIconURI;
    
    @Column(name = "videoThumbURI")
    String videoThumbURI;
    
    
    /**
     * The empty constructor for the Video JPA-entity class.
     */
    public Video() {
        
        
    }
    
    
    /**
     * Constructor for the Video JPA entity
     * Used for testing purposes.
     * 
     * @param videoId  Integer value of the video ID
     * @param videoName String value of the video name
     * @param videoType String value of the video type
     * @param videoURI String value of the video URI
     * @param videoDetail String value of the video detail
     * @param videoDescription String value of the video description
     * @param videoIconURI String value of the icon's URI path
     * @param videoThumbURI String value of the thumbnails URI (unless one is generated)
     */
    public Video(Integer videoId, String videoName, String videoType, String videoURI, String videoDetail, String videoDescription, String videoIconURI, String videoThumbURI) {
        
        this.id = videoId;
        this.videoName = videoName;
        this.videoType = videoType;
        this.videoURI = videoURI;
        this.videoDetail = videoDetail;
        this.videoDescription = videoDescription;
        this.videoIconURI = videoIconURI;
        this.videoThumbURI = videoThumbURI;
        
        
    }
    


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }
    
    public String getVideoURI() {
        return videoURI;
    }

    public void setVideoURI(String videoURI) {
        this.videoURI = videoURI;
    }
    
    public String getVideoDetail() {
        return videoDetail;
    }

    public void setVideoDetail(String videoDetail) {
        this.videoDetail = videoDetail;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    
    public String getVideoIconURI() {
        return videoIconURI;
    }

    public void setVideoIconURI(String videoIconURI) {
        this.videoIconURI = videoIconURI;
    }

    
    public String getVideoThumbURI() {
        return videoThumbURI;
    }

    public void setVideoThumbURI(String videoThumbURI) {
        this.videoThumbURI = videoThumbURI;
    }

        
    
}
