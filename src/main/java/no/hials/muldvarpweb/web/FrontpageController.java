/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import no.hials.muldvarpweb.domain.Article;
import no.hials.muldvarpweb.domain.Frontpage;
import no.hials.muldvarpweb.domain.Quiz;
import no.hials.muldvarpweb.fragments.Fragment;
import no.hials.muldvarpweb.fragments.FragmentModel;
import no.hials.muldvarpweb.service.FrontpageService;
import no.hials.muldvarpweb.service.QuizService;
import org.primefaces.model.DualListModel;

/**
 *
 * @author kristoffer
 */
@Named
@SessionScoped
public class FrontpageController implements Serializable {
    String articlename;
    String newsname;
    String programmename;
    String quizname;
    String category;
    Article article;
    Frontpage frontpage;
    @Inject FrontpageService service;
    Fragment selectedFragment;
    FragmentModel fragmentModel;
    List<Fragment> fragmentBundle;
    String coursename;
    long parentId;
    
    public List<Fragment> getFragmentBundle() {
        if(fragmentBundle == null) {
            fragmentBundle = frontpage.getFragmentBundle();
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
    
    public void addProgrammeFragment() {
        Fragment f = new Fragment(programmename, parentId, Fragment.Type.PROGRAMME);
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
    
    public void addFragment(Fragment f) {
        fragmentBundle.add(f);
        reset();
    }
    
    public void removeFragment(Fragment f) {
        fragmentBundle.remove(f);
    }
    
    public void reset() {
        articlename = "";
        newsname = "";
        programmename = "";
        quizname = "";
        category = "";
        article = null;
        parentId = 0;
        quizzes = null;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Frontpage getFrontpage() {
        if(frontpage == null) { // note to self: kanskje ta vekk ditta for synkroniseringsgrunn??
            try {
                List<Frontpage> fs = service.findFrontpages();
                frontpage = fs.get(0);
            } catch(EJBException ex) {
                System.out.println("getFrontpage()  " + ex);
                frontpage = getDefaultFrontpage();
            } catch(ArrayIndexOutOfBoundsException ex) {
                System.out.println("getFrontpage()  " + ex);
                frontpage = getDefaultFrontpage();
            }
        }
        return frontpage;
    }
    
    public Frontpage getDefaultFrontpage() {
        Frontpage f = new Frontpage();
        f.setName("Default name");        
        return f;
    }

    public void setFrontpage(Frontpage frontpage) {
        this.frontpage = frontpage;
    }
    
    public String getTitle() {
        if(frontpage == null) {
            getFrontpage();
        }
        return frontpage.getName();
    }
    
    public void setTitle(String name) {
        frontpage.setName(name);
    }
    
    public String save() {
        if(frontpage != null) {
            frontpage.setFragmentBundle(fragmentBundle);
            frontpage = service.persist(frontpage);
        }
        return "editFrontpage";
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getProgrammename() {
        return programmename;
    }

    public void setProgrammename(String programmename) {
        this.programmename = programmename;
    }

    public String getQuizname() {
        return quizname;
    }

    public void setQuizname(String quizname) {
        this.quizname = quizname;
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
    
    public void addQuizzes(List<Quiz> q) {
        selectedFragment.setQuizzes(q);
    }
}
