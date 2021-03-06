package no.hials.muldvarpweb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import no.hials.muldvarpweb.fragments.Fragment;

/**
 *
 * @author kristoffer
 */
@Entity
@Table(name = "course")
public class Course implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name = "name")
    String name;
    
    @Column(name = "detail")
    String detail;
    
    @Column(name = "description")
    String description;
    
    @Column(name = "imageurl")
    String imageurl;
    
    @Column(name = "revision")
    Integer revision;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "revision_date")
    Date revision_date;
    
    @OneToMany(cascade = CascadeType.ALL)
    List<Theme> themes;
    
    @OneToMany
    List<ObligatoryTask> obligatoryTasks;
    
    @OneToMany
    List<Exam> exams;
    
    @ManyToMany
    List<Person> teachers;
        
    @ManyToMany(mappedBy = "courses")
    List<Programme> programmes;
    
    @ManyToMany(mappedBy = "courses")
    List<Fragment> fragments;
    
    public Course() {
        this.revision = 0;
        this.revision_date = new Date();
    }

    public Course(String name, String detail, String imageurl, Integer revision, List<Theme> themes, List<ObligatoryTask> obligatoryTasks, List<Exam> exams, List<Person> teachers, List<Programme> programme) {
        this.name = name;
        this.detail = detail;
        this.imageurl = imageurl;
        this.revision = revision;
        this.revision_date = new Date();
        this.themes = themes;
        this.obligatoryTasks = obligatoryTasks;
        this.exams = exams;
        this.teachers = teachers;
        this.programmes = programme;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Person> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Person> teachers) {
        this.teachers = teachers;
    }
    
    public void addTeacher(Person teacher) {
        teachers.add(teacher);
    }
    
    public void removeTeacher(Person teacher) {
        teachers.remove(teacher);
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
    
    public void editTheme(Theme theme) {
        for(int i = 0; i < themes.size(); i++) {
            if(themes.get(i).getId() == theme.getId()) {
                themes.set(i, theme);
            }
        }
    }
    
    public void removeTheme(Theme theme) {
        themes.remove(theme);
    }
    
    public void addTask(Theme theme, Task task) {
        for(int i = 0; i < themes.size(); i++) {
            if(themes.get(i).getId() == theme.getId()) {
                themes.get(i).addTask(task);
            }
        }
    }
    
    public void editTask(Theme theme, Task task) {
        for(int i = 0; i < themes.size(); i++) {
            if(themes.get(i).getId() == theme.getId()) {
                themes.get(i).editTask(task);
            }
        }
    }
    
    public void removeTask(Theme theme, Task task) {
        for(int i = 0; i < themes.size(); i++) {
            if(themes.get(i).getId() == theme.getId()) {
                themes.get(i).removeTask(task);
            }
        }
    }
    
    public void addObligatoryTask(ObligatoryTask obligtask) {   
        obligatoryTasks.add(obligtask);
    }
    
    public void editObligatoryTask(ObligatoryTask obligtask) {
        for(int i = 0; i < obligatoryTasks.size(); i++) {
            if(obligatoryTasks.get(i).getId() == obligtask.getId()) {
                obligatoryTasks.set(i, obligtask);
            }
        }
    }
    
    public void removeObligatoryTask(ObligatoryTask obligtask) {
        obligatoryTasks.remove(obligtask);
    }
    
    public void addExam(Exam exam) {
        exams.add(exam);
    }
    
    public void editExam(Exam exam) {
        for(int i = 0; i < exams.size(); i++) {
            if(exams.get(i).getId() == exam.getId()) {
                exams.set(i, exam);
            }
        }
    }
    
    public void removeExam(Exam exam) {
        exams.remove(exam);
    }

//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }

    @XmlTransient
    public List<Programme> getProgrammes() {
        if(programmes == null) {
            programmes = new ArrayList<Programme>();
        }
        return programmes;
    }

    public void setProgrammes(List<Programme> programmes) {
        this.programmes = programmes;
    }
    
    public void addProgramme(Programme p) {
        if(programmes == null) {
            programmes = new ArrayList<Programme>();
        }
        programmes.add(p);
    }
    
    // eksperimentelle greier
    @OneToMany(cascade = CascadeType.ALL)
    List<Fragment> fragmentBundle;

    public List<Fragment> getFragmentBundle() {
        if(fragmentBundle == null) {
            fragmentBundle = new ArrayList<Fragment>();
        }
        return fragmentBundle;
    }

    public void setFragmentBundle(List<Fragment> fragmentBundle) {
        this.fragmentBundle = fragmentBundle;
    }
    
    public void addFragment(Fragment f) {
        if(fragmentBundle == null) {
            fragmentBundle = new ArrayList<Fragment>();
        }
        fragmentBundle.add(f);
    }
    
    public void removeFragment(Fragment f) {
        fragmentBundle.remove(f);
    }

    public List<Fragment> getFragments() {
        return fragments;
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }
}
