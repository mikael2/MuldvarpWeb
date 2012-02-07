/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * JPA-class defining a Video
 * 
 * @author johan
 */
//JPA-Annotation
@Entity
@Table(name="video")
@XmlRootElement
public class Video implements Serializable{
    
    @Id
    @GeneratedValue
    @Basic(optional =false)
    @NotNull
    @Column(name = "id")
    
    private short id;
    String videoName;
    String videoURL;
    String videoDetail;
    String videoDescription;
    String videoIconURL;
    String videoThumbURL;
    
    /**
     * Constructor for the Video JPA entity
     * Used for testing
     * 
     * @param videoName String value of the video name
     * @param videoURL String value of the video URL
     * @param videoDetail String value of the video detail
     * @param videoDescription String value of the video description
     * @param videoIconURL String value of the icon's URL path
     * @param videoThumbURL String value of the thumbnails URL path (unless one is generated)
     */
    public Video(String videoName, String videoURL, String videoDetail, String videoDescription, String videoIconURL, String videoThumbURL) {
        
        this.videoName = videoName;
        this.videoURL = videoURL;
        this.videoDetail = videoDetail;
        this.videoDescription = videoDescription;
        this.videoIconURL = videoIconURL;
        this.videoThumbURL = videoThumbURL;
        
        
    }
    
    
    
    //Empty constructor
    public Video() {
        
        
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public String getVideoDetail() {
        return videoDetail;
    }

    public void setVideoDetail(String videoDetail) {
        this.videoDetail = videoDetail;
    }

    public String getVideoIconURL() {
        return videoIconURL;
    }

    public void setVideoIconURL(String videoIconURL) {
        this.videoIconURL = videoIconURL;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoThumbURL() {
        return videoThumbURL;
    }

    public void setVideoThumbURL(String videoThumbURL) {
        this.videoThumbURL = videoThumbURL;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }
    
    
}
