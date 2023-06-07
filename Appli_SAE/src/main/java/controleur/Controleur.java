package controleur;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.stage.FileChooser;
import modele.*;
import vue.Applic;
import vue.HBoxRoot;
import vue.SolEffRoot;
import vue.SolExRoot;

import java.io.File;

/**
 * Cette classe implémente l'interface EventHandler pour gérer les événements déclenchés par les composants de l'interface utilisateur.
 */
public class Controleur implements EventHandler<ActionEvent>{

    private RadioButton btnEfficace;
    private RadioButton btnExhaustive;

    public Controleur() {
    }

    public void setButtons(RadioButton btnEfficace, RadioButton btnExhaustive) {
        this.btnEfficace = btnEfficace;
        this.btnExhaustive = btnExhaustive;
    }
    @Override
    public void handle(ActionEvent event) {
        if (btnEfficace.isSelected()) {
            SolEffRoot chSoleffroot = new SolEffRoot(HBoxRoot.getChChoix());
            HBoxRoot.setChSoleffroot(chSoleffroot);
        } else if (btnExhaustive.isSelected()) {
            SolExRoot chSolExRoot = new SolExRoot(HBoxRoot.getChChoix());
            HBoxRoot.setChSoleffroot(chSolExRoot);
        }
    }


}

