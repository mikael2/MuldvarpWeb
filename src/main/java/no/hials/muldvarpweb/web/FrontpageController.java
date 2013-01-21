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
import no.hials.muldvarpweb.fragments.ArticleFragment;
import no.hials.muldvarpweb.fragments.Fragment;
import no.hials.muldvarpweb.fragments.NewsFragment;
import no.hials.muldvarpweb.fragments.ProgrammeFragment;
import no.hials.muldvarpweb.fragments.QuizFragment;
import no.hials.muldvarpweb.service.FrontpageService;

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
    
    public List<Fragment> getFragmentBundle() {
        return frontpage.getFragmentBundle();
    }

    public void setFragmentBundle(List<Fragment> fragmentBundle) {
        frontpage.setFragmentBundle(fragmentBundle);
    }
    
    public void addArticleFragment() {
        addFragment(new ArticleFragment(articlename, 0, article.getId()));
    }
    
    public void addProgrammeFragment() {
        addFragment(new ProgrammeFragment(programmename, 0));
    }
    
    public void addNewsFragment() {
        addFragment(new NewsFragment(newsname, 0, category));
    }
    
    public void addQuizFragment() {
        addFragment(new QuizFragment(quizname, 0));
    }
    
    public void addFragment(Fragment f) {
        if(frontpage.getFragmentBundle() == null) {
            frontpage.setFragmentBundle(new ArrayList<Fragment>());
        }
        frontpage.addFragment(f);
        reset();
    }
    
    public void removeFragment(Fragment f) {
        frontpage.removeFragment(f);
    }
    
    public void reset() {
        articlename = "";
        newsname = "";
        programmename = "";
        quizname = "";
        category = "";
        article = null;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Frontpage getFrontpage() {
        if(frontpage == null) {
            try {
                frontpage = service.getFrontpage(0);
            } catch(EJBException ex) {
                System.out.println(ex);
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
        if(frontpage == null)
            getFrontpage();
        return frontpage.getName();
    }
    
    public void setTitle(String name) {
        frontpage.setName(name);
    }
    
    public void save() {
        if(frontpage != null)
            frontpage = service.persist(frontpage);
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
    
}
