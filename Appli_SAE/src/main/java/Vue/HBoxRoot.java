package vue;

import controleur.Controleur;
import modele.*;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

import java.io.File;

/**
 * La classe HBoxRoot représente la fenêtre principale de l'application, qui contient tous les composants graphiques.
 * Elle hérite de la classe HBox et agence les composants horizontalement.
 */
public class HBoxRoot extends HBox {
    private static Controleur controleur = new Controleur();
    private static VBoxCalendrier chCalendrier;
    private static PlanningCollections chPlanning = new PlanningCollections();
    private static GridPaneFormRes chGridPaneFormRes;
    private static VBoxAffichagePlan chVBoxAffichagePlan;

    private static File stockRes = new File("src/main/Sauvegarde" + File.separator + "planning.dat");

    /**
     * Constructeur de la classe HBoxRoot.
     *
     * Initialise les composants de la fenêtre principale.
     */
    public HBoxRoot() {
        chCalendrier = new VBoxCalendrier();
        chGridPaneFormRes = new GridPaneFormRes();
        chVBoxAffichagePlan = new VBoxAffichagePlan(null);
        getChildren().addAll(chCalendrier, chGridPaneFormRes, chVBoxAffichagePlan);
        HBoxRoot.setMargin(chGridPaneFormRes, new Insets(25));
    }

    /**
     * Retourne l'instance de GridPaneFormRes.
     *
     * @return L'instance de GridPaneFormRes utilisée dans l'application.
     */
    public static GridPaneFormRes getChGridPaneFormRes(){
        return chGridPaneFormRes;
    }

    /**
     * Retourne l'instance de VBoxAffichagePlan.
     *
     * @return L'instance de VBoxAffichagePlan utilisée dans l'application.
     */
    public static VBoxAffichagePlan getchVBoxAffichagePlan(){
        return chVBoxAffichagePlan;
    }

    /**
     * Retourne l'instance de PlanningCollections.
     *
     * @return L'instance de PlanningCollections utilisée dans l'application.
     */
    public static PlanningCollections getPlanning(){
        return chPlanning;
    }

    /**
     * Retourne l'instance de VBoxCalendrier.
     *
     * @return L'instance de VBoxCalendrier utilisée dans l'application.
     */
    /*public static VBoxCalendrier getchCalendrier(){
        return chCalendrier;
    }*/

    /**
     * Retourne l'instance de Controleur.
     *
     * @return L'instance de Controleur utilisée dans l'application.
     */
    public static Controleur getControleur(){
        return controleur;
    }

    public static File getFile() {
        return stockRes;
    }
}
