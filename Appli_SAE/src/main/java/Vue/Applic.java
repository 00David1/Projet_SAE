package vue;

import controleur.Controleur;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.LectureFichierTexte;
import modele.Scenario;

import java.io.File;

/**
 * Cette classe représente l'application principale pour le calendrier du mois.
 */
public class Applic extends Application {

    /**
     * Méthode start pour initialiser et afficher la fenêtre de l'application.
     *
     * @param stage La fenêtre principale de l'application
     */
    @Override
    public void start(Stage stage) {

        HBoxRoot root = new HBoxRoot();
        Scene scene = new Scene(root,940,450);


        File[] fichierCss = new File("src/css").listFiles();
        for (File fichier : fichierCss) {
            scene.getStylesheets().add(fichier.toURI().toString());
        }


        stage.setScene(scene);
        stage.setTitle("SAE");
        stage.show();
    }

    /**
     * Méthode principale pour lancer l'application JavaFX.
     *
     * @param args Les arguments de la ligne de commande
     */
    public static void main (String [] args){
        Application.launch(args);
    }



}
