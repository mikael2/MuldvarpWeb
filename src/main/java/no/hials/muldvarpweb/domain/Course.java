/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author kristoffer
 */
@Entity
@Table(name = "course")
public class Course implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(name = "name")
    String name;
    
    @Column(name = "detail")
    String detail;
    
    @Column(name = "imageurl")
    String imageurl;
    
    @Column(name = "revision")
    Integer revision = 0;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "revision_date")
    Date revision_date;
    
    @OneToMany
    List<Theme> themes;
    
    @OneToMany
    List<ObligatoryTask> obligatoryTasks;
    
    @OneToMany
    List<Exam> exams;

    public Course() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Course(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    public List<ObligatoryTask> getObligatoryTasks() {
        return obligatoryTasks;
    }

    public void setObligatoryTasks(List<ObligatoryTask> obligatoryTasks) {
        this.obligatoryTasks = obligatoryTasks;
    }

    public Date getRevision_date() {
        return revision_date;
    }

    public void setRevision_date(Date revision_date) {
        this.revision_date = revision_date;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
    
    public void addTheme(Theme theme) {
        themes.add(theme);
    }
    
    public void removeTheme(Theme theme) {
        
    }
    
    public void addTask(Theme theme, Task task) {
        for(int i = 0; i < themes.size(); i++) {
            if(themes.get(i).getId() == theme.getId()) {
                themes.get(i).addTask(task);
            }
        }
    }
    
    public void removeTask(Theme theme, Task task) {
        
    }
    
    public void addObligatoryTask(ObligatoryTask obligtask) {   
        obligatoryTasks.add(obligtask);
    }
    
    public void removeObligatoryTask(ObligatoryTask obligtask) {
        
    }
    
    public void addExam(Exam exam) {
        exams.add(exam);
    }
    
    public void removeExam(Exam exam) {
        
    }
}
