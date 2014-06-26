/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edhkle.zkrx;

import com.edhkle.pocketrx.model.Pill;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Html;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

/**
 *
 * @author ehansen
 */
public class PillViewController extends SelectorComposer<Window> {
    final static Logger log = Logger.getLogger(PillViewController.class.getName());
    
    Pill p;    
    @Wire
    Window pillModal;
    @Wire
    Image pmPillImage;
    @Wire
    Label pmImprint;
    @Wire
    Label pmRxString;
    @Wire
    Label pmMedicineName;
    @Wire
    Label pmIngredients;
    @Wire
    Label pmInactiveIngredients;
    @Wire
    Label pmMedType;
    @Wire
    Label pmAuthor;
    
    @Override
    @AfterCompose
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);
        final Execution execution = Executions.getCurrent();
        p = (Pill)execution.getArg().get("selectedPill");
        log.info("doAfterCompose filling out component tree values for pill: \n" + p);
        pmPillImage.setSrc(p.getImagePath());
        pmImprint.setValue(p.getSPLimprint().replace(";"," "));
        pmAuthor.setValue(p.getAuthor());
        pmIngredients.setValue(p.getIngredients());
        pmInactiveIngredients.setValue(p.getSPLinactiveIngredients());
        pmMedType.setValue(p.getSPLstrength());
        pmMedicineName.setValue(p.getMedicineName());
        pmRxString.setValue(p.getRxString());
    }

    private String toHtmlList(String[] items) {
        StringBuilder sb = new StringBuilder("<ul><h3><i>Ingredients</i></h3><br/>");
        for(String s : items) {
            sb.append("<li>").append(s).append("</li><br/>");
        }
        sb.append("</ul><br/>");
        return sb.toString();
    }
    @Listen("onClick = #closeButton")
    public void showModal(Event e) {
        pillModal.detach();
    }
    
    public static Window createWindow(Component parent, Pill p) {
        Map<String,Pill> args = new HashMap<String,Pill>();
        args.put("selectedPill", p);
        Window window = (Window)Executions.createComponents("pillview.zul", parent, args);
        return window;
    }
}
