package vue;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;

public class GridPaneVide extends GridPane {

    public GridPaneVide(VBoxChoix vBoxChoix) {

        setPadding(new Insets(30, 0, 30, 0));
        setVgap(10);
        setHgap(10);

        // ------------------------------------------------SOLUTION EXHAUSTIVE ----------------------------------------------------
        //Titre
        Label titreLabel = new Label("Scénario ");
        GridPane.setHalignment(titreLabel, HPos.CENTER);
        add(titreLabel, 1, 0, 5, 1);

        //separateur
        Separator separator = new Separator();
        this.add(separator, 0,1 , 6, 1);


        // Création des éléments
        Label quetesRealiseesLabel = new Label("Quêtes Finales : " );
        Label QuetRea = new Label(" " );
        QuetRea.setMaxSize(100, 15);
        QuetRea.setMinSize(100, 15);

        Label xpLabel = new Label("XP : ");


        Label distanceLabel = new Label("Distance : ");


        Label dureeLabel = new Label("Durée : " );


        //Placement des éléments
        add(quetesRealiseesLabel, 1, 2);

        add(xpLabel, 1, 3);

        add(distanceLabel, 1, 4);

        add(dureeLabel, 1, 5);


        //Placement tableau des quetes listées
        Label TitrequetesRealiseesPdtLabel = new Label("Quêtes réalisées pendant : ");
        Label choiQuete = new Label("En attente de votre choix ... ");

        add(TitrequetesRealiseesPdtLabel, 5, 2);
        add(choiQuete, 5, 3, 1, 5);

        setMargin(TitrequetesRealiseesPdtLabel, new Insets(0, 50, 0, 30));
        setMargin(choiQuete, new Insets(0, 0, 0, 30));

    }
}
