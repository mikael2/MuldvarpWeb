package no.hials.muldvarpweb.web;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import no.hials.muldvarpweb.domain.Article;
import no.hials.muldvarpweb.service.ArticleService;

/**
 *
 * @author Lena
 */
@Named
@SessionScoped
public class ArticleController implements Serializable {

    @Inject
    ArticleService service;
    Article newArticle;
    
    public ArticleService getService() {     
        return service;
    }

    public Article getOldArticle() {
        HttpServletRequest hsr = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        long id = Long.parseLong(hsr.getParameter("articleid"));
        return getService().getArticle((short)id);
    }
    
    public Article getArticle() {
        if (newArticle == null) {
            newArticle = new Article();
        }
        
        return newArticle;
    }
    public List<Article> getMostRecentArticles () { 
        return service.findMostRecentArticles();
    }
    
    public Article getArticleByTitle(String title) {
        return service.findArticle(title);
    }

    public void setArticle(Article article) {
        this.newArticle = article;
    }

    public void submitArticle() {

        //if(newArticle != null) {
        System.out.println("Submitting article " + newArticle);
        newArticle = service.addArticle(newArticle);
        System.out.println("Submitted article " + newArticle);
        clearArticle();
        // }
    }

    public void saveArticle() {

        //if(newArticle != null) {
        System.out.println("Saving article " + newArticle);
        newArticle = service.addArticle(newArticle);
        System.out.println("Saved article " + newArticle);
        // }
    }
    
    public void clearArticle() {
        newArticle = null;
    }

    public void deleteArticle() {
        System.out.println("Deleting article " + newArticle);
        service.removeArticle(newArticle);
        System.out.println("Clearing article " + newArticle);
        clearArticle();
    }

}
