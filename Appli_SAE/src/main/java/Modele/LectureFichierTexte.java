package Modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Cette classe permet de lire un fichier texte et de créer un objet Scenario à partir des données lues.
 */
public class LectureFichierTexte {

    /**
     * Lit le contenu d'un fichier texte et crée un objet Scenario à partir des données lues.
     *
     * @param fichier le fichier texte à lire
     * @return un objet Scenario contenant les données lues du fichier
     */
    public static Scenario lecture(File fichier) {
        Scenario scenario = new Scenario();

        try {
            Scanner scanner = new Scanner(fichier);

            while (scanner.hasNext()) {
                scenario.ajout(new Quete(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        return scenario;
    }
}
