package no.hials.muldvarpweb.web;

import java.io.Serializable;
import java.util.List;
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
    
    @Inject ArticleService articleService;
    Article newArticle;
    List<Article> articleList;
    Article selectedArticle;
    Article filter;
    
}
