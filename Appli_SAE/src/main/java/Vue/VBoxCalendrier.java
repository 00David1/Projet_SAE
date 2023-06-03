package vue;

import modele.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * La classe VBoxCalendrier représente une boîte verticale qui affiche le calendrier mensuel dans l'application.
 * Elle hérite de la classe VBox.
 */
public class VBoxCalendrier extends VBox implements ConstantesCalendrier {

    /**
     * Constructeur de la classe VBoxCalendrier.
     * Initialise le calendrier mensuel affiché dans la fenêtre.
     */
    public VBoxCalendrier() {
        super(50);
        DateCalendrier today = new DateCalendrier();
        StackPane stackPaneMois = new StackPane();


        ToggleGroup buttonGroup = new ToggleGroup();
        for (int numMois = 1; numMois <= 12; numMois++) {
            CalendrierDuMois monthCalendar = new CalendrierDuMois(numMois, today.getAnnee());
            TilePane tilePane = new TilePane();
            tilePane.setPrefColumns(7);
            tilePane.setMaxWidth(220);
            tilePane.setMinWidth(220);
            tilePane.setId("opaque");
            for (String jourAb : JOURS_SEMAINE_ABR) {
                Label labelJour = new Label(jourAb);
                tilePane.getChildren().add(labelJour);
            }
            for (DateCalendrier date : monthCalendar.getDates()) {
                ToggleButton boutonDate = new ToggleButton(Integer.toString(date.getJour()));

                boutonDate.setOnAction(HBoxRoot.getControleur());
                boutonDate.setToggleGroup(buttonGroup);
                tilePane.getChildren().add(boutonDate);

                boutonDate.setUserData(date);

                if (date.getMois() != monthCalendar.getMois()) {
                    boutonDate.setId("dateHorsMois");
                }
                if (date.getMois() != monthCalendar.getMois()) {
                    boutonDate.setId("dateHorsMois");
                }

            }
            tilePane.setAccessibleText(MOIS[numMois - 1]+" "+monthCalendar.getAnnee());
            stackPaneMois.getChildren().add(tilePane);
        }

        CalendrierDuMois monthCalendar = new CalendrierDuMois(today.getMois(), today.getAnnee());

        List<Node> liste = stackPaneMois.getChildren();
        int indexHaut = liste.size() -1;
        while (!liste.get(indexHaut).getAccessibleText().equals(MOIS[monthCalendar.getMois()-1] + " " + monthCalendar.getAnnee()))
            liste.get(0).toFront();

        Label labelTitle = new Label(MOIS[monthCalendar.getMois()-1]+" "+monthCalendar.getAnnee());
        VBox.setMargin(labelTitle,new Insets(14));
        HBox boutons = new HBox();
        boutons.setAlignment(Pos.CENTER_RIGHT);

        Button bf = new Button("<");
        bf.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("bouton appuyé");
                liste.get(indexHaut).toBack();
                labelTitle.setText(liste.get(11).getAccessibleText());
            }
        });


        Button bs = new Button("<<");
        bs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println( "bouton appuyé");
                while (!liste.get(indexHaut).getAccessibleText().equals(MOIS[0]))
                    liste.get(0).toFront();
                labelTitle.setText(liste.get(11).getAccessibleText());
            }
        });


        Button bt = new Button(">>");
        bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("bouton appuyé");
                while (!liste.get(indexHaut).getAccessibleText().equals(MOIS[11]))
                    liste.get(0).toFront();
                labelTitle.setText(liste.get(11).getAccessibleText());
            }
        });


        Button bd = new Button(">");
        bd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("bouton appuyé");
                liste.get(0).toFront();
                labelTitle.setText(liste.get(11).getAccessibleText());
            }
        });


        boutons.getChildren().addAll(bf,bs,bt,bd);
        HBox TopBox = new HBox();
        TopBox.getChildren().addAll(labelTitle, boutons);

        getChildren().addAll(stackPaneMois, TopBox);



    }
}
