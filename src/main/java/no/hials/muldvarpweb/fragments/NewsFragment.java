/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.fragments;

/**
 *
 * @author kristoffer
 */
public class NewsFragment extends Fragment {
    String category;

    public NewsFragment(String name, int parentID) {
        super(name, parentID, Type.NEWS);
    }
    
}
