/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author kristoffer
 */
@Entity
@Table(name = "task")
public class Task implements Serializable  {
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(name = "name")
    String name;
//    
//    @OneToMany
//    List<Question> questions;
    
    @Column(name = "contentType")
    String contentType;
//
//    public void addQuestion(Question newQuestion) {
//        questions.add(newQuestion);
//    }
//
//    public void editQuestion(Question q) {
//        for(int i = 0; i < questions.size(); i++) {
//            if(questions.get(i).getId() == q.getId()) {
//                questions.set(i, q);
//            }
//        }
//    }
//
//    public void removeQuestion(Question q) {
//        questions.remove(q);
//    }
    
    @Column(name = "done")
    Boolean done = false;
    
    @Column(name = "content_url")
    String content_url;
    
//    @OneToMany
//    List<Video> videos;
    
    public Task() {
        
    }

    public Task(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public void acceptTask() {
        done = true;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

//    public List<Video> getVideos() {
//        return videos;
//    }
//
//    public void setVideos(List<Video> videos) {
//        this.videos = videos;
//    }

//    public List<Question> getQuestions() {
//        return questions;
//    }
//
//    public void setQuestions(List<Question> questions) {
//        this.questions = questions;
//    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
