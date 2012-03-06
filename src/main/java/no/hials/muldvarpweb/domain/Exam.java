/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author kristoffer
 */
@Entity
@Table(name = "exam")
public class Exam implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(name = "name")
    String name;
    
    @Column(name = "room")
    String room;
    
    @Column(name = "info")
    String info;
    
    @Column(name = "examDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date examDate;

    public Exam(String name) {
        this.name = name;
    }
    
    public Exam() {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
