package no.hials.muldvarpweb.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import no.hials.muldvarpweb.domain.Article;

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
    public Article getArticle(@PathParam("id") short id) {
        TypedQuery<Article> q = em.createQuery("Select a from Article a where a.id = :id", Article.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }
    
    public List<Article> getArticle(String title) {       
        TypedQuery<Article> q = em.createQuery("Select a from Article a where a.title LIKE :title", Article.class);
        q.setParameter("name", "%" + title + "%");
        return q.getResultList();
    }
    
}
