package no.hials.muldvarpweb.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import no.hials.muldvarpweb.domain.Article;
import no.hials.muldvarpweb.fragments.Fragment;

/**
 *
 * @author Lena
 */
@Stateless
@Path("article")
public class ArticleService {

    @PersistenceContext
    EntityManager em;

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Article getArticle (@PathParam("id") Long id) {
        TypedQuery<Article> q = em.createQuery("Select a from Article a where a.id = :id", Article.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }

    @GET
    @Path("/news/")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Article> findNews() {
        TypedQuery<Article> q = em.createQuery("Select a from Article a where a.category LIKE :category", Article.class);
        q.setParameter("category", "news");
        return q.getResultList();
    }

    public Article findArticle (String title) {
        TypedQuery<Article> q = em.createQuery("Select a from Article a where a.id = :id", Article.class);
        q.setParameter("title", title);
        return q.getSingleResult();
    }

    public List<Article> findArticles (String title) {
        TypedQuery<Article> q = em.createQuery("Select a from Article a where a.title LIKE :title", Article.class);
        q.setParameter("name", "%" + title + "%");
        return q.getResultList();
    }

    public List<Article> findMostRecentArticles () {
        TypedQuery<Article> q = em.createQuery("Select a from Article a", Article.class);
        return q.getResultList();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Article> findArticles() {       
        return em.createQuery("SELECT a from Article a", Article.class).getResultList();
    }

    public Article addArticle(Article newArticle) {
        newArticle= em.merge(newArticle);
        em.persist(newArticle);
        return newArticle;
        }

    public void removeArticle(Article article) {        
        if(article.getFragment().getArticle().equals(article)) {
            article.getFragment().setArticle(null);
            em.merge(article.getFragment());
        }        
        article = em.merge(article);
        em.remove(article);
    }


}
