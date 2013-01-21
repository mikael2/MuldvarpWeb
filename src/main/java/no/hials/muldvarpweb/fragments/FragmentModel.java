/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarpweb.fragments;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author kristoffer
 */
public class FragmentModel extends ListDataModel<Fragment> implements SelectableDataModel<Fragment> {

    public FragmentModel() {
    }

    public FragmentModel(List<Fragment> list) {
        super(list);
    }

    @Override
    public Object getRowKey(Fragment t) {
        return t.getName();
    }

    @Override
    public Fragment getRowData(String string) {
        List<Fragment> fragments = (List<Fragment>) getWrappedData();
        
        for(Fragment f : fragments) {
            if(f.getName().equals(string)) {
                return f;
            }
        }
        return null;
    }
    
}
