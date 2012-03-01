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
@Table(name = "obligatory_task")
public class ObligatoryTask extends Task implements Serializable  {
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(name = "dueDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dueDate;
    
    @Column(name = "acceptedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date acceptedDate;

    public ObligatoryTask(String name) {
        super(name);
    }

    public ObligatoryTask() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Override
    public void acceptTask() {
        super.acceptTask();
        acceptedDate = new Date();
    }

    public Date getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(Date acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
