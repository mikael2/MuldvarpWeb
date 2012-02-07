/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author kristoffer
 */
public class Theme implements Serializable {
    String name;
    ArrayList<Task> tasks;
    Integer completion;
}
