/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edhkle.zkrx;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

/**
 *
 * @author ehansen
 */
public class PillViewController extends SelectorComposer<Component> {
    private static final long serialVersionUID = 1L;
    
    @Wire
    Window pillModal;
    @Wire
    Label imprint;
    @Wire
    Label rxString;
    @Wire
    Label medicineName;
    @Wire
    Label ingredients;
    @Wire
    Label medTypes;
    @Wire
    Label author;
    
    @Listen("onClick = #closeButton")
    public void showModal(Event e) {
        pillModal.detach();
    }
}
