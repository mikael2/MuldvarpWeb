/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import no.hials.muldvarpweb.domain.Article;
import no.hials.muldvarpweb.domain.Course;
import no.hials.muldvarpweb.domain.LibraryItem;
import no.hials.muldvarpweb.domain.Programme;
import no.hials.muldvarpweb.domain.Quiz;
import no.hials.muldvarpweb.domain.Video;
import no.hials.muldvarpweb.fragments.Fragment;
import no.hials.muldvarpweb.fragments.FragmentModel;
import no.hials.muldvarpweb.service.CourseService;
import no.hials.muldvarpweb.service.LibraryService;
import no.hials.muldvarpweb.service.ProgrammeService;
import no.hials.muldvarpweb.service.QuizService;
import no.hials.muldvarpweb.service.VideoService;
import org.primefaces.model.DualListModel;

/**
 * This is the controller for the ProgrammeService.
 * 
 * @author johan
 */
@Named
@SessionScoped
public class ProgrammeController implements Serializable{
    
    @Inject ProgrammeService service;
    
    List<Programme> programmeList;
    Programme newProgramme;
    Programme selected;
    
    
    /**
     * This function returns the selectedProgramme variable.
     * 
     * @return The selected Programme
     */
    public Programme getSelected(){
        
        //Check if the selectedProgramme variable is null, and set new Programme if it is
        if (selected == null) {
            
            selected = new Programme();
        }
                
        return selected;
    }
    
    public String setSelected(Programme selected) {
        if(selected == null) {
            newProgramme = null;
            selected = getProgramme();
        }
        this.selected = selected;
        courses = null;
        videos = null;
        documents = null;
        quizzes = null;
        fragmentBundle = null;
        fragmentModel = null;
        return "editProgramme?faces-redirect=true";
    }

    public Programme getProgramme() {
        if(newProgramme == null) {
            newProgramme = new Programme();
            save();
        }
        return newProgramme;
    }

    public void setProgramme(Programme newProgramme) {
        this.newProgramme = newProgramme;
    }
    
    public String editProgramme() {
        if(selected != null) {
            service.editProgramme(selected);
        }
        return "listProgramme";
    }
    
    public String removeProgramme() {
        if(selected != null ) {
            service.removeProgramme(selected);
        }
        return "listProgramme?faces-redirect=true";
    }
    
    /**
     * This function returns a List of Programmes from the injected ProgrammeService.
     * 
     * @return List of Programmes
     */
    public List<Programme> getProgrammes() {
        programmeList = service.findProgrammes();
        return programmeList;
    }

    public void setProgramme(List<Programme> programmeList) {
        this.programmeList = programmeList;
    }
    
    /**
     * This function makes a call to the ProgrammeService instantiation and adds the supplied Programme.
     * 
     * @param newProgramme The Programme to be added.
     * @return 
     */
    public Programme addProgramme() {
        
//        //Check if there is a Programme to add
//        if(selectedProgramme != null){
//        
//            
//        }
        
        service.addProgramme(selected);
        return newProgramme;
    }
    
    public void addInfo(int i) {  
        switch(i) {
            case 1:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "INFO: ", "Changes saved"));
                break;
            case 2:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "INFO: ", "Course deleted"));
                break;
            case 3:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "INFO: ", "New revision created"));
                break;
         }
    }
    
    public void removeCourseFromProgramme(Course c) {
        service.removeCourseFromProgramme(selected, c);
    }
    
    // experiment zone
    private DualListModel<Course> courses;
    @Inject CourseService courseService;

    public DualListModel<Course> getCourses() {
        if(courses == null) {
            List<Course> source = courseService.findCourses();
            List<Course> target = new ArrayList<Course>();
            if(selected.getCourses() != null) {
                target = selected.getCourses();
                
                for(int i = 0; i < target.size(); i++) {
                    Course v = target.get(i);
                    for(int k = 0; k < source.size(); k++) {
                        Course vv = source.get(k);
                        if(vv.getId().equals(v.getId())) {
                            source.remove(vv);
                            break;
                        }
                    }
                }
            }
            
            courses = new DualListModel<Course>(source, target);
        }
        
        return courses;
    }

    public void setCourses(DualListModel<Course> course) {
        this.courses = course;
    }
    
    public void addCourses(List<Course> c) {
        selected = service.setCourses(selected, courses.getTarget());
    }
    
    public void setCourses(List<Course> c) {
        selected.setCourses(c);
    }
    
    // end exp zone
    
    String articlename;
    String newsname;
    String programmename;
    String quizname;
    String category;
    Article article;
    Fragment selectedFragment;
    FragmentModel fragmentModel;
    List<Fragment> fragmentBundle;
    String coursename;
    long parentId;
    private String videoname;
    private String documentname;
    
    public List<Fragment> getFragmentBundle() {
        if(fragmentBundle == null) {
            fragmentBundle = selected.getFragmentBundle();
        }
        return fragmentBundle;
    }

    public void setFragmentBundle(List<Fragment> fragmentBundle) {
        this.fragmentBundle = fragmentBundle;
    }
    
    public void addArticleFragment() {
        Fragment f = new Fragment(articlename, parentId, Fragment.Type.ARTICLE);
        f.setArticle(article);
        addFragment(f);
    }
    
    public void addNewsFragment() {
        Fragment f = new Fragment(newsname, parentId, Fragment.Type.NEWS);
        f.setCategory(category);
        addFragment(f);
    }
    
    public void addQuizFragment(List<Quiz> quizzes) {
        Fragment f = new Fragment(quizname, parentId, Fragment.Type.QUIZ);
        f.setQuizzes(quizzes);
        addFragment(f);
    }
    
    public void addCourseFragment() {
        Fragment f = new Fragment(coursename, parentId, Fragment.Type.COURSE);
        addFragment(f);
    }
    
    public void addVideoFragment(List<Video> videos) {
        Fragment f = new Fragment(videoname, parentId, Fragment.Type.VIDEO);
        f.setVideos(videos);
        addFragment(f);
    }
    
    public void addDocumentFragment(List<LibraryItem> documents) {
        Fragment f = new Fragment(documentname, parentId, Fragment.Type.DOCUMENT);
        f.setDocuments(documents);
        addFragment(f);
    }
    
    public void addFragment(Fragment f) {
        fragmentBundle.add(f);
        reset();
    }
    
    public void removeFragment(Fragment f) {
        fragmentBundle.remove(f);
    }
    
    public Fragment getSelectedFragment() {
        if(selectedFragment == null) {
            selectedFragment = new Fragment();
        }
        return selectedFragment;
    }

    public void setSelectedFragment(Fragment selectedFragment) { 
        this.selectedFragment = selectedFragment;
    }

    public FragmentModel getFragmentModel() {
        if(fragmentModel == null) {
            fragmentModel = new FragmentModel(getFragmentBundle());
        }
        return fragmentModel;
    }

    public void setFragmentModel(FragmentModel fragmentModel) {
        this.fragmentModel = fragmentModel;
    }
    
    public void reset() {
        articlename = "";
        newsname = "";
        quizname = "";
        coursename = "";
        videoname = "";
        documentname = "";
        category = "";
        article = null;
        parentId = 0;
        quizzes = null;
    }
    
    public String save() {
        if(selected != null) {
            try {
                if(!fragmentBundle.isEmpty()) {
                    selected.setFragmentBundle(fragmentBundle);
                }
            } catch(NullPointerException ex) {
                System.out.println(ex);
            }
            selected = service.persist(selected);
        }
        return "editProgramme";
    }
    
    // Quiz stuff
    private DualListModel<Quiz> quizzes;
    @Inject QuizService quizService;
    
    public DualListModel<Quiz> getQuizzes() {
        if(quizzes == null) {
            List<Quiz> source = quizService.findQuizzes();
            List<Quiz> target = new ArrayList<Quiz>();
            if(selectedFragment.getQuizzes() != null) {
                target = selectedFragment.getQuizzes();
                
                for(int i = 0; i < target.size(); i++) {
                    Quiz v = target.get(i);
                    for(int k = 0; k < source.size(); k++) {
                        Quiz vv = source.get(k);
                        if(vv.getId() == v.getId()) {
                            source.remove(vv);
                            break;
                        }
                    }
                }
            }
            
            quizzes = new DualListModel<Quiz>(source, target);
        }
        
        return quizzes;
    }
    
    public void setQuizzes(DualListModel<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
    
    public void refreshLists() {
        quizzes = null;
        courses = null;
        videos = null;
        documents = null;
        getQuizzes();
        getCourses();
        getVideos();
        getDocuments();
    }
    
    public void addQuizzes(List<Quiz> q) {
        selectedFragment.setQuizzes(q);
    }
    
    // Video stuff
    private DualListModel<Video> videos;
    @Inject VideoService videoService;

    public DualListModel<Video> getVideos() {
        if(videos == null) {
            List<Video> source = videoService.findVideos();
            List<Video> target = new ArrayList<Video>();
            if(selectedFragment.getVideos() != null) {
                target = selectedFragment.getVideos();
                
                for(int i = 0; i < target.size(); i++) {
                    Video v = target.get(i);
                    for(int k = 0; k < source.size(); k++) {
                        Video vv = source.get(k);
                        if(vv.getId().equals(v.getId())) {
                            source.remove(vv);
                            break;
                        }
                    }
                }
            }
            
            videos = new DualListModel<Video>(source, target);
        }
        
        return videos;
    }
    
    public void setVideos(DualListModel<Video> videos) {
        this.videos = videos;
    }
    
    public void addVideos(List<Video> v) {
        selectedFragment.setVideos(v);
        videos = null;
    }
    
    // Document stuff
    private DualListModel<LibraryItem> documents;
    @Inject LibraryService documentService;
    
    public DualListModel<LibraryItem> getDocuments() {
        System.out.println("getDocuments");
        if(documents == null) {
            System.out.println("getting docs");
            List<LibraryItem> source = documentService.getLibrary();
            for(LibraryItem d : source) {
                System.out.println("Got doc: " + d.getTitle());
            }
            List<LibraryItem> target = new ArrayList<LibraryItem>();
            if(selectedFragment.getDocuments() != null) {
                target = selectedFragment.getDocuments();
                
                for(int i = 0; i < target.size(); i++) {
                    LibraryItem v = target.get(i);
                    for(int k = 0; k < source.size(); k++) {
                        LibraryItem vv = source.get(k);
                        if(vv.getId().equals(v.getId())) {
                            source.remove(vv);
                            break;
                        }
                    }
                }
            }
            
            documents = new DualListModel<LibraryItem>(source, target);
        }
        
        return documents;
    }
    
    public void setDocuments(DualListModel<LibraryItem> document) {
        this.documents = document;
    }
    
    public void addDocuments(List<LibraryItem> v) {
        selectedFragment.setDocuments(v);
        documents = null;
    }

    public String getArticlename() {
        return articlename;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename;
    }

    public String getNewsname() {
        return newsname;
    }

    public void setNewsname(String newsname) {
        this.newsname = newsname;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    public String getDocumentname() {
        return documentname;
    }

    public void setDocumentname(String documentname) {
        this.documentname = documentname;
    }

    public String getQuizname() {
        return quizname;
    }

    public void setQuizname(String quizname) {
        this.quizname = quizname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
