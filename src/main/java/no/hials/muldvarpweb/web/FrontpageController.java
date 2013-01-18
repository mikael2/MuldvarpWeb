/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import no.hials.muldvarpweb.domain.Article;
import no.hials.muldvarpweb.fragments.ArticleFragment;
import no.hials.muldvarpweb.fragments.Fragment;

/**
 *
 * @author kristoffer
 */
@Named
@SessionScoped
public class FrontpageController implements Serializable {
    List<Fragment> fragmentBundle;
    String name;
    Article article;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<Fragment> getFragmentBundle() {
        return fragmentBundle;
    }

    public void setFragmentBundle(List<Fragment> fragmentBundle) {
        this.fragmentBundle = fragmentBundle;
    }
    
    public void addArticleFragment() {
        if(fragmentBundle == null) {
            fragmentBundle = new ArrayList<Fragment>();
        }
        fragmentBundle.add(new ArticleFragment(name, 0, article.getId()));
        reset();
    }
    
    public void removeFragment(Fragment f) {
        fragmentBundle.remove(f);
    }
    
    public void reset() {
        name = "";
        article = null;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
