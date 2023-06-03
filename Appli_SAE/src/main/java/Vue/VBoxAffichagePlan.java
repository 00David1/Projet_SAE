package vue;

import modele.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import java.util.TreeSet;

/**
 * La classe VBoxAffichagePlan représente la boîte verticale qui affiche le plan de réservation dans l'application.
 * Elle hérite de la classe VBox.
 */
public class VBoxAffichagePlan extends VBox {

    /**
     * Label affichant le numéro de semaine avec le texte "Semaine : ".
     */
    private Label chLabSem = new Label("Semaine : " + new DateCalendrier().getNoSem());

    /**
     * Tableau de visualisation des réservations.
     */
    private TableView <Reservation> chTable = new TableView<Reservation>();

    /**
     * Constructeur de la classe VBoxAffichagePlan.
     *
     * @param parResSem TreeSet contenant les réservations pour une semaine donnée.
     */
    public VBoxAffichagePlan(TreeSet<Reservation> parResSem) {

        // Définition des colonnes de la table
        TableColumn <Reservation, DateCalendrier> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("chDateCal"));
        TableColumn <Reservation, String> coursColumn = new TableColumn<>("Cours");
        coursColumn.setCellValueFactory(new PropertyValueFactory<>("chTitle"));
        TableColumn <Reservation, PlageHoraire> horaireColumn = new TableColumn<>("Horaire");
        horaireColumn.setCellValueFactory(new PropertyValueFactory<>("chPH"));
        chTable.getColumns().addAll(dateColumn, coursColumn, horaireColumn);

        dateColumn.setPrefWidth(120);
        coursColumn.setPrefWidth(120);
        horaireColumn.setPrefWidth(120);
        chTable.setPrefSize(360, 290);
        coursColumn.setSortable(false);

        // Ajout des éléments à la VBox
        getChildren().addAll(chLabSem, chTable);

        // Mise à jour avec la date actuelle et les réservations fournies
        update(new DateCalendrier(), parResSem);
    }

    /**
     * Met à jour l'affichage du plan de réservation.
     *
     * @param parDate     DateCalendrier spécifiant la semaine à afficher.
     * @param parResSem   TreeSet contenant les réservations pour la semaine spécifiée.
     */
    public void update(DateCalendrier parDate, TreeSet<Reservation> parResSem) {
        chLabSem.setText("Semaine : " + parDate.getNoSem());
        chTable.getItems().clear();

        if (parResSem == null)
            return;

        for (Reservation reservation : parResSem)
            chTable.getItems().add(reservation);
    }
}
