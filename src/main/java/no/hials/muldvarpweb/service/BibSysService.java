/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import no.hials.muldvarpweb.domain.LibraryItem;

/**
 *
 * @author nosph_000
 */
@Stateless
@Path("BibSys")
public class BibSysService {
    @PersistenceContext
    EntityManager entityManager;
    
    /**
     * Get method for the bibsys system. The parameter is a query string with the name of the desired book.
     * The method returns a list of matching result
     * @param query
     * @return 
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<LibraryItem> searchBookInfo(String query){
        //TODO: write get method for bibsys here
        return testData();
    }
    
    /**
     * Get method for the bibsys system. input the ISBN of the desired book, and the method returns it, or null if it's not found.
     * @param ISBN
     * @return 
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public LibraryItem getBookByISBN(String ISBN){
        //TODO: write get method using ISBN here
        LibraryItem lI2 = new LibraryItem("Monster manual", "Defence against the black arts", "Severus Snape", "Harry Potter", "5.08.2008", "100", "This book explains how you can kick ass", "http://unrestrictedstock.com/wp-content/uploads/office-icons-book-free-stock-vector.jpg", "http://whhs.cps-k12.org/library/images/icon_book.gif", "http://cran.r-project.org/doc/manuals/R-intro.pdf");
        return lI2;
    }
    
    public List testData(){
        List retval = new ArrayList();
        LibraryItem lI = new LibraryItem("A tale of two men", "How to make money", "Carl Brooke", "Austin Maxwell", "15.03.1998", "239", "This is a book explaining how a pair of people can make money", "http://unrestrictedstock.com/wp-content/uploads/office-icons-book-free-stock-vector.jpg", "http://whhs.cps-k12.org/library/images/icon_book.gif", "http://cran.r-project.org/doc/manuals/R-intro.pdf");
        LibraryItem lI2 = new LibraryItem("Monster manual", "Defence against the black arts", "Severus Snape", "Harry Potter", "5.08.2008", "100", "This book explains how you can kick ass", "http://unrestrictedstock.com/wp-content/uploads/office-icons-book-free-stock-vector.jpg", "http://whhs.cps-k12.org/library/images/icon_book.gif", "http://cran.r-project.org/doc/manuals/R-intro.pdf");
        LibraryItem lI3 = new LibraryItem("Master & Commander", "A wet movie", "Master B", "Commander D", "09.01.1460", "1902", "This book is a book about sailboats and domination", "http://unrestrictedstock.com/wp-content/uploads/office-icons-book-free-stock-vector.jpg", "http://whhs.cps-k12.org/library/images/icon_book.gif", "http://cran.r-project.org/doc/manuals/R-intro.pdf");
        retval.add(lI);
        retval.add(lI2);
        retval.add(lI3);
        return retval;
    }
}
