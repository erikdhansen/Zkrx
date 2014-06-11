/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edhkle.zkrx;

import com.edhkle.pocketrx.model.Pill;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Logger;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

/**
 *
 * @author ehansen
 */
public class PillsVM {
    final static Logger log = Logger.getLogger(PillsVM.class.getName());
    protected Collection<Pill> pills = new LinkedList<Pill>();
    private Pill selectedPill = null;
    
    public PillsVM() {
    }
    
    @Command @NotifyChange("pills")
    public void addAllPills(Collection<Pill> pillCollection) {
        log.info("Received collection of pills to render [ " + pillCollection.size() + " items]");
        for(Pill p : pillCollection) {
            log.info("Created new Pill View Model for: " + p.getMedicineName());
            pills.add(p);
        }
        log.info("Created new PillsListVM view model with " + pills.size() + " pills in list");
    }
    
    @Command @NotifyChange("pills")
    public void setSelectedPill(Pill p) {
        selectedPill = p;
        // I think I need to fire pillSelected here...
        log.info("setSelectedPill(" + p.getSetId() + ")");
    }
    
    @Command @NotifyChange("pills")
    public void clear() {
        pills.clear();
    }
    
    public Pill getSelectedPill() {
        return selectedPill;
    }
    
    public void pillSelected(Pill p) {
        log.info("!!! PILL SELECTED IN LIST !!! Pill: " + p.toString());
    }
}
