/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.domain;

import java.io.Serializable;

/**
 *
 * @author kristoffer
 */
public class Task implements Serializable  {
    String name;
    public enum content {EXTERNAL,VIDEO,PDF}
    Boolean done = false;
    String content_url;
    
    public Task() {
        
    }

    public Task(String name) {
        this.name = name;
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
}
