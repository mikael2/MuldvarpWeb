/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * This class represents a programme.
 * 
 * @author johan
 */

@Entity
@Table(name="Programme")
public class Programme implements Serializable{
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name="name")
    String name;
    
    @Column(name="detail")
    String detail;

    /**
     * Empty constructor for the Programme JPA class.
     */
    public Programme(){
        
    }
    
    /**
     * This is the constructor for the Programme JPA class.
     * 
     * @param name name of the Programme.
     * @param detail details about the Programme.
     */
    public Programme(String name, String detail) {
        
        this.name = name;
        this.detail = name;
        
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    
}