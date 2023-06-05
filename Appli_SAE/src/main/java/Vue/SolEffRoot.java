package vue;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modele.LectureFichierTexte;
import modele.Scenario;
import modele.SolutionEfficace;
import modele.*;

import java.io.File;

public class SolEffRoot extends GridPane {


    public SolEffRoot() {

        setPadding(new Insets(10));
        setVgap(10);
        setHgap(10);

        //Lecture du fichier
        File nomFichier = new File("C:\\Users\\enola\\Desktop\\por\\ProSAE\\src\\Scenario\\scenario_0.txt");
        Scenario contenu = LectureFichierTexte.lecture(nomFichier);
        SolutionEfficace SolEff = new SolutionEfficace(contenu.quetes);
        SolEff.resoudreQuetes();



        // ------------------------------------------------SOLUTION EFFICACE----------------------------------------------------
        // Création du titre avec nom Fichier
        String nomFichierSansChemin = nomFichier.getName();
        String nomFichierSansTrait = nomFichierSansChemin.replace("_", " ");
        String nomFichierSansExtension = nomFichierSansTrait.replace(".txt", "");
        Label titreLabel = new Label("Etude du " + nomFichierSansExtension);

        //Placement Titre
        add(titreLabel, 1, 0);


        // Création des éléments
        Label TitrequetesRealiseesPdtLabel = new Label("Quêtes réalisées tout au long : ");
        TitrequetesRealiseesPdtLabel.setStyle("-fx-underline: true;");
        Label quetesRealiseesPdtLabel = new Label(SolEff.getQuetesRealiseesPendant());


        Label quetesRealiseesLabel = new Label("Quêtes Finales : " + SolEff.getQuetesRealisees());


        Label xpLabel = new Label("XP : " + SolEff.getNiveauExperienceActuel());


        Label distanceLabel = new Label("Distance : " + SolEff.getDistanceJoueur());


        Label dureeLabel = new Label("Durée : " + (SolEff.getDureeSE() + SolEff.getDistanceJoueur()));


        //Placement des éléments
        add(TitrequetesRealiseesPdtLabel, 1, 2);
        add(quetesRealiseesPdtLabel, 1, 3);

        add(quetesRealiseesLabel, 1, 4);

        add(xpLabel, 1, 5);

        add(distanceLabel, 1, 6);

        add(dureeLabel, 1, 7);

    }

}
