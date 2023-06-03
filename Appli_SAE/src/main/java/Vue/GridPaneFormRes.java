package vue;

import modele.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import static modele.ConstantesCalendrier.HEURES;
import static modele.ConstantesCalendrier.MINUTES;

/**
 * La classe GridPaneFormRes représente un formulaire de réservation dans un GridPane.
 * Il permet à l'utilisateur de saisir les informations nécessaires pour créer une réservation.
 */
public class GridPaneFormRes extends GridPane {


    private DateCalendrier chDateCal = new DateCalendrier();
    /**
     * Label affichant la date.
     */
    private Label chLabelDate = new Label(chDateCal.toString());

    /**
     * TextField avec le titre du cours.
     */
    private static TextField chTFCours = new TextField();

    /**
     * Des ComboBox pour les saisies d'heures et minutes.
     */
    private static ComboBox chH1 = new ComboBox();
    private static ComboBox chM1 = new ComboBox();
    private static ComboBox chH2 = new ComboBox();
    private static ComboBox chM2 = new ComboBox();

    /**
     * Constructeur de la classe GridPaneFormRes.
     * Initialise les composants du formulaire de réservation.
     */
    public GridPaneFormRes(){
        //GridPane gp = new GridPane();
        this.setGridLinesVisible(false);
        this.setHgap(8);
        this.setVgap(20);



        //Titre
        GridPane.setHalignment(chLabelDate, HPos.CENTER);
        this.add(chLabelDate, 0,0 , 6, 1);

        //separateur
        Separator separator = new Separator();
        this.add(separator, 0,1 , 6, 1);

        //Zone de texte
        Label labelCour = new Label("cours");
        TextField textFieldCours = chTFCours;
        labelCour.setLabelFor(textFieldCours);

        this.add(labelCour, 0,2);
        this.add(textFieldCours, 1, 2, 4, 1);


        //Bouttons on/off
        Label niv = new Label("niveau");

        RadioButton b1 = new RadioButton("_debutant");
        RadioButton b2 = new RadioButton("_moyen");
        RadioButton b3 = new RadioButton("_avance");
        RadioButton b4 = new RadioButton("_expert");

        this.add(niv, 0,3);

        this.add(b1, 1,3,2,1);
        this.add(b2, 3, 3,4, 1);
        this.add(b3, 1,4,2, 1);
        this.add(b4, 3, 4,4, 1);

        //horaire
        Label h = new Label("horaire");
        this.add(h, 0,5);

        Label de = new Label("de");
        this.add(de, 1,5);

        ComboBox hdeb = chH1 ;
        hdeb.getItems().addAll(HEURES);
        this.add(hdeb, 2, 5);



        Label h1 = new Label("h");
        this.add(h1, 3,5);

        ComboBox mdeb = chM1 ;
        mdeb.getItems().addAll(MINUTES);
        this.add(mdeb, 4, 5);

        Label min1 = new Label("min");
        this.add(min1, 5,5);

        Label a = new Label("à");
        this.add(a, 1,6);

        ComboBox hfin = chH2 ;
        hfin.getItems().addAll(HEURES);
        this.add(hfin, 2, 6);

        Label h2 = new Label("h");
        this.add(h2, 3,6);

        ComboBox mfin = chM2 ;
        mfin.getItems().addAll(MINUTES);
        this.add(mfin, 4, 6);

        Label min2 = new Label("min");
        this.add(min2, 5,6);

        //separateur
        Separator separator2 = new Separator();
        this.add(separator2, 0,7 , 6, 1);


        //buttons Annuler
        Button Annuler = new Button("Annuler");
        this.add(Annuler, 2, 8, 2, 1);
        GridPane.setHalignment(Annuler, HPos.RIGHT);
        Annuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("bouton appuyé");
                ToggleButton buttonA = (ToggleButton)actionEvent.getSource();
                //System.out.println(buttonA.getF());
            }
        });


        //buttons Enregistrer
        Button Enregistrer = new Button("Enregistrer");
        this.add(Enregistrer, 4,8, 2, 1);
        GridPane.setHalignment(Enregistrer, HPos.RIGHT);
        Enregistrer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("bouton appuyé");
            }
        });
        Enregistrer.setOnAction(HBoxRoot.getControleur());



    }

    /**
     * Définit la date du formulaire de réservation.
     *
     * @param parDate La date de la réservation.
     */
    public void setDate(DateCalendrier parDate){
        chDateCal = parDate ;
        chLabelDate.setText(chDateCal.toString());
    }


    /**
     * Récupère les informations saisies dans le formulaire et crée une réservation.
     *
     * @return La réservation créée à partir des informations saisies.
     * @throws ExceptionPlageHoraire Si la plage horaire est invalide.
     * @throws ExceptionReservation Si la réservation est invalide.
     */
    public Reservation getReservation() throws ExceptionPlageHoraire, ExceptionReservation {
        DateCalendrier date = chDateCal;
        String titre = chTFCours.getText();
        int h1 = chH1.getSelectionModel().getSelectedIndex()+1;
        int m1 = chM1.getSelectionModel().getSelectedIndex()*15;
        int h2 = chH2.getSelectionModel().getSelectedIndex()+1;
        int m2 = chM2.getSelectionModel().getSelectedIndex()*15;

        PlageHoraire plage = new PlageHoraire(new Horaire(h1, m1), new Horaire(h2, m2));

        return new Reservation(date, plage, titre);

    }

}
