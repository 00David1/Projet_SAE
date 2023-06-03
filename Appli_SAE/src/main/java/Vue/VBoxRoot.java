package vue;

import modele.CalendrierDuMois;
import modele.ConstantesCalendrier;
import modele.DateCalendrier;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * La classe VBoxRoot représente une boîte verticale qui contient le calendrier mensuel et les boutons de navigation de l'application.
 * Elle hérite de la classe VBox et implémente l'interface ConstantesCalendrier.
 */
public class VBoxRoot extends VBox implements ConstantesCalendrier {

    /**
     * Constructeur de la classe VBoxRoot.
     * Initialise la vue principale de l'application contenant le calendrier mensuel et les boutons de navigation.
     */
    public VBoxRoot(){
        DateCalendrier today = new DateCalendrier();
        CalendrierDuMois monthCalendar = new CalendrierDuMois(today.getMois(), today.getAnnee());
        System.out.println(monthCalendar);

        Label labelTitle = new Label(MOIS[monthCalendar.getMois()-1]+""+monthCalendar.getAnnee());

        VBox.setMargin(labelTitle,new Insets(14));

        /*Label labelDateToday = new Label(new DateCalendrier().toString());
        getChildren().add(labelDateToday);

        Label labelDateDemain = new Label(new DateCalendrier().dateDuLendemain().toString());
        getChildren().add(labelDateDemain);*/

        HBox boutons = new HBox();
        boutons.setAlignment(Pos.CENTER_RIGHT);
        Button bf = new Button("<");
        Button bs = new Button("<<");
        Button bt = new Button(">>");
        Button bd = new Button(">");
        boutons.getChildren().addAll(bf,bs,bt,bd);
        HBox TopBox = new HBox();
        TopBox.getChildren().addAll(labelTitle, boutons);
        StackPane stackPanrMois = new StackPane();


        VBox boiteDates = new VBox();

        ScrollPane scrollPaneDates = new ScrollPane();
        scrollPaneDates.setContent(boiteDates);
        VBox.setMargin(scrollPaneDates, new Insets(4));

        for (DateCalendrier date : monthCalendar.getDates()){
            Label labeldate = new Label(date.toString());

            if (date.getMois()!= monthCalendar.getMois()){
                labeldate.setId("Hors Mois");
            }
            if(date.compareTo(today)==0){
                labeldate.setId("Aujourd'hui");
            }

            VBox.setMargin(labeldate,new Insets(8));
            boiteDates.getChildren().add(labeldate);
        }
        this.getChildren().addAll(labelTitle, scrollPaneDates);



    }
}
