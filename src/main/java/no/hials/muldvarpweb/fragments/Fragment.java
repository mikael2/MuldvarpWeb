/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.fragments;

import java.io.Serializable;

/**
 *
 * @author Kristoffer
 */
public class Fragment implements Serializable {
    String name;
    int parentID;
    public enum Type {
        PROGRAMME, COURSE, NEWS, ARTICLE, QUIZ
    }
    Type fragmentType;

    public Fragment() {
    }

    public Fragment(String name, int parentID, Type fragmentType) {
        this.name = name;
        this.parentID = parentID;
        this.fragmentType = fragmentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public Type getFragmentType() {
        return fragmentType;
    }

    public void setFragmentType(Type fragmentType) {
        this.fragmentType = fragmentType;
    }
    
}
