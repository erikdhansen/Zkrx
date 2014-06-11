/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edhkle.zkrx;

import java.util.logging.Logger;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import com.edhkle.pocketrx.controller.Pharmacist;
import com.edhkle.pocketrx.model.Pill;
import java.util.Collection;

/**
 *
 * @author ehansen
 */
public class ZkrxController extends SelectorComposer {
    final static Logger log = Logger.getLogger(ZkrxController.class.getName());
    @Wire
    Textbox imprint;
    @Wire
    Textbox description;
    @Wire
    Radiogroup andOr;
    
    private PillsVM pillViewModel = new PillsVM();
            
    String JDBC_URL = "jdbc:mysql://pillbox:p1llb0x@localhost:3306/pillbox?zeroDateTime=convertToNull";
    
    @Listen("onClick=#search")
    public void search(MouseEvent event) {
        log.info("search(imprint=" + imprint.getValue() + " join=" + andOr.getSelectedItem().getValue() + " description=" + description.getValue());
        pillViewModel.clear();
        Pharmacist rx = Pharmacist.getPharmacist(JDBC_URL);
        String[] bits = imprint.getValue().split(" ");
        StringBuilder sb = new StringBuilder("%");
        for(String  b : bits) {
            sb.append(b).append("%");
        }
        try {
            Collection<Pill> pills = rx.getPillsWithImprint(sb.toString());
            log.info("RxSearch: found " + pills.size() + " pill records");
            pillViewModel.addAllPills(pills);
        } catch (Exception e) {
            log.severe(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void setPillViewModel(PillsVM pillViewModel) {
        this.pillViewModel = pillViewModel;
    }
    
    public PillsVM getPillViewModel() {
        return pillViewModel;
    }
}
