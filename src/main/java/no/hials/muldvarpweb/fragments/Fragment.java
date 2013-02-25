/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.fragments;

import java.io.Serializable;
import no.hials.muldvarpweb.domain.Article;
import no.hials.muldvarpweb.domain.Quiz;

/**
 *
 * @author Kristoffer
 */
public class Fragment implements Serializable {
    String name;
    long parentID;
    public enum Type {
        FRONTPAGE, PROGRAMME, COURSE, NEWS, ARTICLE, QUIZ
    }
    Type fragmentType;
    
    Article article;
    int programmeID;
    String category;
    String iconurl;
    Quiz quiz;

    public Fragment() {
    }

    public Fragment(String name, long parentID, Type fragmentType) {
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

    public long getParentID() {
        return parentID;
    }

    public void setParentID(long parentID) {
        this.parentID = parentID;
    }

    public Type getFragmentType() {
        return fragmentType;
    }

    public void setFragmentType(Type fragmentType) {
        this.fragmentType = fragmentType;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getProgrammeID() {
        return programmeID;
    }

    public void setProgrammeID(int programmeID) {
        this.programmeID = programmeID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIconurl() {
        switch(fragmentType) {
            case ARTICLE:
                iconurl = "../resources/images/stolen_contacts.png";
                break;
            case COURSE:
                iconurl = "../resources/images/stolen_course_programme.png";
                break;
            case FRONTPAGE:
                iconurl = "../resources/images/stolen_contacts.png";
                break;
            case NEWS:
                iconurl = "../resources/images/stolen_news.png";
                break;
            case PROGRAMME:
                iconurl = "../resources/images/stolen_course_programme.png";
                break;
            case QUIZ:
                iconurl = "../resources/images/stolen_quiz.png";
                break;
        }
        return iconurl;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    
}
