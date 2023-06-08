package vue;


import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import modele.LectureFichierTexte;
import modele.Scenario;
import modele.SolutionExhaustive;

import java.io.File;


public class SolExRoot extends GridPane {

    public SolExRoot(VBoxChoix vBoxChoix) {

        setPadding(new Insets(30, 0, 30, 50));
        setVgap(10);
        setHgap(10);
        setGridLinesVisible(false);

        //Liaison avec choix de l'utilisateur sur le fichier
        File choixFichier = vBoxChoix.getChoixComboBox();  // Appel de la méthode avec des parenthèses


        if (choixFichier != null) {

            // ------------------------------------------------SOLUTION EXHAUSTIVE ----------------------------------------------------

            //Lecture Fichier
            File nomFichier = choixFichier;
            Scenario contenu = LectureFichierTexte.lecture(nomFichier);
            SolutionExhaustive SolEx = new SolutionExhaustive(contenu.quetes);
            SolEx.resoudreQuetes();

            //Création du titre avec nom Fichier
            String nomFichierSansChemin = nomFichier.getName();
            String nomFichierSansTrait = nomFichierSansChemin.replace("_", " ");
            String nomFichierSansExtension = nomFichierSansTrait.replace(".txt", "");
            String nomFichierNumero = nomFichierSansExtension.replace("scenario", "");
            Label titreLabel = new Label("Scénario " + nomFichierNumero);
            titreLabel.getStyleClass().add("label-gras");

            //Placement Titre
            GridPane.setHalignment(titreLabel, HPos.CENTER);
            add(titreLabel, 1, 0, 5, 1);;


            //separateur
            Separator separator = new Separator();
            this.add(separator, 0,1 , 6, 1);


            // ------------------Création des éléments------------------
            //Quetes Finales
            Label quetesRealiseesLabel = new Label("Quêtes Finales : ");
            quetesRealiseesLabel.getStyleClass().add("label-gras");
            Label QuetRea = new Label("" + SolEx.getQuetesRealiseesEx());
            QuetRea.setMaxSize(100, 15);
            QuetRea.setMinSize(100, 15);

            //XP
            Label xpLabel = new Label("XP : ");
            xpLabel.getStyleClass().add("label-gras");
            Label NivExActu = new Label("" + SolEx.getNiveauExperienceActuelEx());

            //Distance
            Label distanceLabel = new Label("Distance : ");
            distanceLabel.getStyleClass().add("label-gras");
            Label DisJ = new Label(""+ SolEx.getDistanceJoueurEx());

            //Durée
            Label dureeLabel = new Label("Durée : ");
            dureeLabel.getStyleClass().add("label-gras");
            Label DurDis = new Label("" + (SolEx.getDureeSEEx() + SolEx.getDistanceJoueurEx()));


            //------------------Placement des éléments------------------
            //Quetes Finales
            add(quetesRealiseesLabel, 1, 2);
            add(QuetRea, 2, 2);

            //XP
            add(xpLabel, 1, 3);
            add(NivExActu, 2, 3);

            //Distance
            add(distanceLabel, 1, 4);
            add(DisJ, 2, 4);

            //Durée
            add(dureeLabel, 1, 5);
            add(DurDis, 2, 5);


            //Création et Placement tableau des quetes listées
            Label TitrequetesRealiseesPdtLabel = new Label("Quêtes réalisées pendant : ");
            TitrequetesRealiseesPdtLabel.getStyleClass().add("label-gras");
            Label quetesRealiseesPdtLabel = new Label(SolEx.getQuetesRealiseesPendantEx());

            add(TitrequetesRealiseesPdtLabel, 5, 2);
            add(quetesRealiseesPdtLabel, 5, 3, 1, 5);
            setMargin(TitrequetesRealiseesPdtLabel, new Insets(0, 50, 0, 30));
            setMargin(quetesRealiseesPdtLabel, new Insets(0, 50, 0, 30));

        }
    }

}
