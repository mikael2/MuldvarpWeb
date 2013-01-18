/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import no.hials.muldvarpweb.fragments.Fragment;

/**
 *
 * @author kristoffer
 */
@Entity
@Table(name = "frontpage")
public class Frontpage implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;
    
    List<Fragment> fragmentBundle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Fragment> getFragmentBundle() {
        return fragmentBundle;
    }

    public void setFragmentBundle(List<Fragment> fragmentBundle) {
        this.fragmentBundle = fragmentBundle;
    }
    
    
}
