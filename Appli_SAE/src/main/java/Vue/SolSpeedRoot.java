package vue;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import modele.LectureFichierTexte;
import modele.Scenario;
import modele.SolutionSpeedRun;

import java.io.File;

public class SolSpeedRoot extends GridPane {

    public SolSpeedRoot(VBoxChoix vBoxChoix) {

        setPadding(new Insets(30, 0, 30, 50));
        setVgap(10);
        setHgap(10);
        setGridLinesVisible(false);

        //Liaison avec choix de l'utilisateur sur le fichier
        File choixFichier = vBoxChoix.getChoixComboBox();  // Appel de la méthode avec des parenthèses


        if (choixFichier != null) {

            // ------------------------------------------------SOLUTION SPEEDRUN ----------------------------------------------------

            //Lecture Fichier
            File nomFichier = choixFichier;
            Scenario contenu = LectureFichierTexte.lecture(nomFichier);
            SolutionSpeedRun SolSR = new SolutionSpeedRun(contenu.quetes);
            SolSR.afficherCheminsAvecDurees();

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
            //Quetes Min
            Label QueMin = new Label("Quêtes Chemin Minimum : ");
            QueMin.getStyleClass().add("label-gras");
            Label QuetMinT = new Label("" + SolSR.getcheminMinDuree());

            //Durée Minimal
            Label dureeMinLabel = new Label("Durée Minimal : ");
            dureeMinLabel.getStyleClass().add("label-gras");
            Label DurMaxT = new Label("" + SolSR.getdureeMin());

            //Quete Max
            Label QueteMax = new Label("Quêtes Chemin Maximum : ");
            QueteMax.getStyleClass().add("label-gras");
            Label QMT = new Label(""+ SolSR.getcheminMaxDuree());

            //Durée Max
            Label dureeMaxLabel = new Label("Durée Maximum: ");
            dureeMaxLabel.getStyleClass().add("label-gras");
            Label DurMinT = new Label("" + (SolSR.getdureeMax()));

            //------------------Placement des éléments------------------
            //Quetes Min
            add(QueMin, 1, 2);
            add(QuetMinT, 2, 2);

            //Durée Min
            add(dureeMinLabel, 1, 3);
            add(DurMaxT, 2, 3);

            //Quete Max
            add(QueteMax, 1, 4);
            add(QMT, 2, 4);

            //Durée Max
            add(dureeMaxLabel, 1, 5);
            add(DurMinT, 2, 5);



            //Création et Placement tableau des quetes listées
            /*Label TitrequetesRealiseesPdtLabel = new Label("Quêtes réalisées pendant : ");
            TitrequetesRealiseesPdtLabel.getStyleClass().add("label-gras");
            Label quetesRealiseesPdtLabel = new Label(SolSR.getQuetesRealiseesPendantSR());

            add(TitrequetesRealiseesPdtLabel, 5, 2);
            add(quetesRealiseesPdtLabel, 5, 3, 1, 5);
            setMargin(TitrequetesRealiseesPdtLabel, new Insets(0, 50, 0, 30));
            setMargin(quetesRealiseesPdtLabel, new Insets(0, 50, 0, 30));*/

        }
    }
}
