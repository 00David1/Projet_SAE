package vue;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.LectureFichierTexte;
import modele.Quete;
import modele.Scenario;
import modele.SolutionEfficace;

import java.util.ArrayList;
import java.util.List;

import java.io.File;

public class GridPaneRoot extends GridPane {
    public GridPaneRoot() {
        this.setGridLinesVisible(true);
        this.setHgap(8);
        this.setVgap(20);

        //Lecture du fichier
        File nomFichier = new File("C:\\Users\\enola\\Desktop\\por\\ProSAE\\src\\Scenario\\scenario_0.txt");
        Scenario contenu = LectureFichierTexte.lecture(nomFichier);

        SolutionEfficace SolEff = new SolutionEfficace(contenu.quetes);
        SolEff.resoudreQuetes();


        // Création du titre avec nom Fichier
        String nomFichierSansChemin = nomFichier.getName();
        String nomFichierSansTrait = nomFichierSansChemin.replace("_", " ");
        String nomFichierSansExtension = nomFichierSansTrait.replace(".txt", "");
        Label titreLabel = new Label("Etude du " + nomFichierSansExtension);

        //Placement Titre
        this.add(titreLabel, 1,0);


        // Création des éléments
        Label TitrequetesRealiseesPdtLabel = new Label("Quêtes réalisées tout au long : ");
        TitrequetesRealiseesPdtLabel.setStyle("-fx-underline: true;");
        Label quetesRealiseesPdtLabel = new Label( SolEff.getQuetesRealiseesPendant());


        Label quetesRealiseesLabel = new Label("Quêtes Finales : " + SolEff.getQuetesRealisees());


        Label xpLabel = new Label("XP : " + SolEff.getNiveauExperienceActuel());


        Label distanceLabel = new Label("Distance : " + SolEff.getDistanceJoueur());


        Label dureeLabel = new Label("Durée : " + (SolEff.getDureeSE() + SolEff.getDistanceJoueur()));


        //Placement des éléments
        this.add(TitrequetesRealiseesPdtLabel, 1,2);
        this.add(quetesRealiseesPdtLabel, 1,3);

        this.add(quetesRealiseesLabel, 1,4);

        this.add(xpLabel, 1,5);

        this.add(distanceLabel, 1,6);

        this.add(dureeLabel, 1,7);




    }
}
