/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author kristoffer
 */
public class ObligatoryTask extends Task implements Serializable  {
    Date dueDate;
    Date acceptedDate;

    public ObligatoryTask(String name) {
        super(name);
    }

    public ObligatoryTask() {
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
