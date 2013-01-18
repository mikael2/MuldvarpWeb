/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.fragments;

/**
 *
 * @author Kristoffer
 */
public class ArticleFragment extends Fragment {
    long articleID;

    public ArticleFragment(String name, int parentID, long articleID) {
        super(name, parentID, Type.ARTICLE);
        this.articleID = articleID;
    }
    
}
