package vue;


import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modele.LectureFichierTexte;
import modele.Scenario;
import modele.SolutionEfficace;
import modele.SolutionExhaustive;

import java.io.File;


public class SolExRoot extends GridPane {

    public SolExRoot(VBoxChoix vBoxChoix) {

        setPadding(new Insets(10));
        setVgap(10);
        setHgap(10);

        //Lecture du fichier
        File choixFichier = vBoxChoix.getChoixComboBox();  // Appel de la méthode avec des parenthèses


        if (choixFichier != null) {

            File nomFichier = choixFichier;
            Scenario contenu = LectureFichierTexte.lecture(nomFichier);
            SolutionExhaustive SolEx = new SolutionExhaustive(contenu.quetes);
            SolEx.resoudreQuetes();

            // ------------------------------------------------SOLUTION EXHAUSTIVE ----------------------------------------------------
            //Création du titre avec nom Fichier
            String nomFichierSansChemin = nomFichier.getName();
            String nomFichierSansTrait = nomFichierSansChemin.replace("_", " ");
            String nomFichierSansExtension = nomFichierSansTrait.replace(".txt", "");
            Label titreLabel = new Label("Etude du " + nomFichierSansExtension);

            //Placement Titre
            add(titreLabel, 1, 0);


            // Création des éléments
            Label TitrequetesRealiseesPdtLabel = new Label("Quêtes réalisées tout au long : ");
            TitrequetesRealiseesPdtLabel.setStyle("-fx-underline: true;");
            Label quetesRealiseesPdtLabel = new Label(SolEx.getQuetesRealiseesPendantEx());


            Label quetesRealiseesLabel = new Label("Quêtes Finales : " + SolEx.getQuetesRealiseesEx());


            Label xpLabel = new Label("XP : " + SolEx.getNiveauExperienceActuelEx());


            Label distanceLabel = new Label("Distance : " + SolEx.getDistanceJoueurEx());


            Label dureeLabel = new Label("Durée : " + (SolEx.getDureeSEEx() + SolEx.getDistanceJoueurEx()));


            //Placement des éléments
            add(TitrequetesRealiseesPdtLabel, 1, 2);
            add(quetesRealiseesPdtLabel, 1, 3);

            add(quetesRealiseesLabel, 1, 4);

            add(xpLabel, 1, 5);

            add(distanceLabel, 1, 6);

            add(dureeLabel, 1, 7);

        } else {
            System.out.println("erreur");
        }

    }

}
