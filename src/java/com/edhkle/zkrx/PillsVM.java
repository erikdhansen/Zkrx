/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edhkle.zkrx;

import com.edhkle.pocketrx.model.Pill;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import org.zkoss.zul.AbstractListModel;

/**
 *
 * @author ehansen
 */
public class PillsVM extends AbstractListModel<Pill> {
    final static Logger log = Logger.getLogger(PillsVM.class.getName());
    protected List<Pill> pills = new ArrayList<Pill>();
    private Pill selectedPill = null;
    
    public PillsVM() {
    }
    
    public List<Pill> getPills() {
        return pills;
    }
    
    public void setPills(Collection<Pill> newPills) {
        if(pills == null) {
            // Shouldn't happen
            log.warning("How did we get a null pill list?");
            pills = new ArrayList<Pill>();
        }
        pills.clear();
        pills.addAll(newPills);
    }
    
    public void setSelectedPill(Pill p) {
        selectedPill = p;
        // I think I need to fire pillSelected here...
        log.info("setSelectedPill(" + p.getSetId() + ")");
    }
    
    public void clear() {
        pills.clear();
    }
    
    public Pill getSelectedPill() {
        return selectedPill;
    }
    
    public void pillSelected(Pill p) {
        log.info("!!! PILL SELECTED IN LIST !!! Pill: " + p.toString());
    }
    
    @Override
    public int getSize() {
        return pills.size();
        
    }

    @Override
    public Pill getElementAt(int i) {
        return pills.get(i);
    }
}
