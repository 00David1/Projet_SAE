package vue;

import controleur.Controleur;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import modele.ConstFichier;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class VBoxChoix extends VBox implements ConstFichier {

    private static SolEffRoot chSoleffroot; // Instance de la classe SolEffRoot

    private static SolExRoot chSolExRoot; // Instance de la classe SolExRoot

    private static SolSpeedRoot chSolSpeedRoot; // Instance de la classe SolExRoot

    private ComboBox<File> comboBoxFichiers;
    private RadioButton btnEfficace;
    private RadioButton btnExhaustive;
    private RadioButton btnSpeedRun;

    private static Controleur controleur = new Controleur();


    public VBoxChoix(){

        // Création titre
        Label titrePage = new Label("Veuillez choisir la solution que vous souhaitez tester : ");
        titrePage.getStyleClass().add("label-gras");

        getChildren().add(titrePage);
        setMargin(titrePage, new Insets(30, 0, 30, 0));

        // création
        comboBoxFichiers = new ComboBox<>();
        comboBoxFichiers.getItems().addAll(SCENARIO);


        //création des boutons pour les choix
        btnEfficace = new RadioButton("Efficace");
        btnExhaustive = new RadioButton("Exhaustive");
        btnSpeedRun = new RadioButton("Speed Run");
        ToggleGroup toggleGroup = new ToggleGroup();
        btnEfficace.setToggleGroup(toggleGroup);
        btnExhaustive.setToggleGroup(toggleGroup);
        btnSpeedRun.setToggleGroup(toggleGroup);

        Button btnGo = new Button("Lancer");

        // gestion du placement des éléments
        setMargin(comboBoxFichiers, new Insets(0, 0, 20, 30));
        setMargin(btnEfficace, new Insets(0, 0, 20, 30));
        setMargin(btnExhaustive, new Insets(0, 0, 20, 30));
        setMargin(btnSpeedRun, new Insets(0, 0, 20, 30));

        setMargin(btnGo, new Insets(0, 0, 0, 155));


        getChildren().addAll(comboBoxFichiers, btnEfficace, btnExhaustive,btnSpeedRun, btnGo);


        chSoleffroot = new SolEffRoot(this);
        chSolExRoot = new SolExRoot(this);
        chSolSpeedRoot = new SolSpeedRoot(this);


        btnGo.setOnAction(event -> {
            controleur.setButtons(btnEfficace, btnExhaustive, btnSpeedRun);
            controleur.handle(event);
        });
    }

    public File getChoixComboBox(){
        return comboBoxFichiers.getSelectionModel().getSelectedItem();
    }


}
