/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kristoffer
 */
@Entity
@Table(name = "course")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c")})
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    String name;
    String detail;
    String imageurl;
    
    Integer revision;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date revision_date;
    
    ArrayList<Theme> themes;
    ArrayList<ObligatoryTask> obligatoryTasks;
    ArrayList<Exam> exams;

    public Course() {
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

    public ArrayList<Theme> getThemes() {
        return themes;
    }

    public void setThemes(ArrayList<Theme> themes) {
        this.themes = themes;
    }

    public ArrayList<ObligatoryTask> getObligatoryTasks() {
        return obligatoryTasks;
    }

    public void setObligatoryTasks(ArrayList<ObligatoryTask> obligatoryTasks) {
        this.obligatoryTasks = obligatoryTasks;
    }

    public Date getRevision_date() {
        return revision_date;
    }

    public void setRevision_date(Date revision_date) {
        this.revision_date = revision_date;
    }

    public ArrayList<Exam> getExams() {
        return exams;
    }

    public void setExams(ArrayList<Exam> exams) {
        this.exams = exams;
    }
}
