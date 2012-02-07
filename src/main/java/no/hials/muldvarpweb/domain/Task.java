/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.domain;

import java.io.Serializable;

/**
 *
 * @author kristoffer
 */
public class Task implements Serializable  {
    String name;
    enum content {EXTERNAL,VIDEO,PDF}
    Boolean done = false;
    String content_url;
    
}
