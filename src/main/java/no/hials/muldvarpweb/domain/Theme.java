/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author kristoffer
 */
public class Theme implements Serializable {
    String name;
    ArrayList<Task> tasks;
    
    public Theme() {
        
    }

    public Theme(String name) {
        this.name = name;
    }

    public Integer getCompletion() {
        Integer numberOfTasks = 0;
        Integer completedTasks = 0;
        for(Task task : tasks) {
            numberOfTasks++;
            if(task.getDone() == true) {
                completedTasks++;
            }
        }
        Integer completion = (completedTasks*100)/numberOfTasks;
        return completion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
