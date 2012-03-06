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
@Table(name = "theme")
public class Theme implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(name = "name")
    String name;
    
    @OneToMany
    List<Task> tasks;
    
    public Theme() {
        
    }

    public Theme(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Integer getCompletion() {
//        Integer numberOfTasks = 0;
//        Integer completedTasks = 0;
//        for(Task task : tasks) {
//            numberOfTasks++;
//            if(task.getDone() == true) {
//                completedTasks++;
//            }
//        }
//        Integer completion = (completedTasks*100)/numberOfTasks;
//        return completion;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    public void addTask(Task task) {
        tasks.add(task);
    }
}
