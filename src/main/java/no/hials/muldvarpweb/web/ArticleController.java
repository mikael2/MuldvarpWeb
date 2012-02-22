package no.hials.muldvarpweb.web;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import no.hials.muldvarpweb.domain.Article;
import no.hials.muldvarpweb.service.ArticleService;

/**
 *
 * @author Lena
 */
@Named
@SessionScoped
public class ArticleController implements Serializable {
    
    @Inject ArticleService service;
    Article newArticle;

    public ArticleService getService() {
        return service;
    }

    public Article getArticle() {
        if(newArticle == null)
            newArticle = new Article();

        return newArticle;
    }
    
    public void setArticle(Article article) {
        this.newArticle = article;
    }
    
    public void addArticle() {
        if(newArticle != null) {
            
            service.addArticle(newArticle);
        }
    }

    
}
