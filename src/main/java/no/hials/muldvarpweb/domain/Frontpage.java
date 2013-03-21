/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    String name;
    
    @OneToMany(cascade = CascadeType.ALL)
    List<Fragment> fragmentBundle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Fragment> getFragmentBundle() {
        if(fragmentBundle == null) {
            fragmentBundle = new ArrayList<Fragment>();
        }
        return fragmentBundle;
    }

    public void setFragmentBundle(List<Fragment> fragmentBundle) {
        this.fragmentBundle = fragmentBundle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void removeFragment(Fragment f) {
        fragmentBundle.remove(f);
    }
    
    public void addFragment(Fragment f) {
        if(fragmentBundle == null) {
            fragmentBundle = new ArrayList<Fragment>();
        }
        fragmentBundle.add(f);
    }
    
}
