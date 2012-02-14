/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.web;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Lena
 */
@Named
@SessionScoped
public class TestController implements Serializable {
    String test;

    public TestController() {
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
    
    public void save() {
        System.out.println("Value: " + test);
    }
}
