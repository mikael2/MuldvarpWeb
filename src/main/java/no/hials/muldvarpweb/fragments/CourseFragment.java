/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.fragments;

/**
 *
 * @author kristoffer
 */
public class CourseFragment extends Fragment {
    int programmeID;

    public CourseFragment(String name, int parentID) {
        super(name, parentID, Type.COURSE);
    }
}
