package Modele;

import Modele.Quete;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite représentant une solution pour résoudre des quêtes.
 */
public abstract class Solution {
    protected List<Integer> quetesRealisees; // Liste des quêtes réalisées
    protected List<Quete> quetesDisponibles; // Liste des quêtes disponibles
    protected int[] positionActuelle; // Position actuelle du joueur
    protected int niveauExperienceActuel; // Niveau d'expérience actuel du joueur
    protected int niveauExperienceRequis;
    protected int distanceJoueur;
    protected int duree;

    /**
     * Constructeur de la classe Solution.
     * Initialise les attributs de la solution.
     *
     * @param quetes la liste des quêtes disponibles
     */
    public Solution(List<Quete> quetes) {
        this.quetesRealisees = new ArrayList<>();
        this.quetesDisponibles = new ArrayList<>(quetes);
        this.positionActuelle = new int[]{0, 0};
        this.niveauExperienceActuel = 0;
        this.niveauExperienceRequis = 0;
        this.distanceJoueur = 0;
        this.duree = 0;
    }

    /**
     * Méthode abstraite pour résoudre les quêtes.
     */
    protected abstract void resoudreQuetes();

    /**
     * Méthode abstraite pour trouver la quête la plus proche du joueur.
     *
     * @return la quête la plus proche
     */
    protected abstract Quete trouverQueteLaPlusProche();

    /**
     * Vérifie si les préconditions d'une quête sont satisfaites.
     *
     * @param quete la quête à vérifier
     * @return true si les préconditions sont satisfaites, false sinon
     */
    protected boolean verifierPreconditions(Quete quete) {
        int[] precond1 = quete.getPrecond1();
        int[] precond2 = quete.getPrecond2();

        // Vérifier si au moins une quête de precond1 a été réalisée
        boolean precond1Realisee = false;
        for (int precondQuete : precond1) {
            if (quetesRealisees.contains(precondQuete)) {
                precond1Realisee = true;
                break;
            }
        }

        // Vérifier si au moins une quête de precond2 a été réalisée
        boolean precond2Realisee = false;
        for (int precondQuete : precond2) {
            if (quetesRealisees.contains(precondQuete)) {
                precond2Realisee = true;
                break;
            }
        }

        if (precond1.length == 0) {
            return true; // Aucune précondition à satisfaire pour precond1
        }

        if (precond1.length > 0 && precond2.length == 0 && precond1Realisee) {
            return true; // precond2 est vide mais precond1 a été réalisée
        }

        if (!precond1Realisee || !precond2Realisee) {
            return false; // Les préconditions ne sont pas satisfaites
        }

        return true;
    }

    /**
     * Calcule la distance entre deux points dans un plan cartésien.
     *
     * @param x1 abscisse du premier point
     * @param y1 ordonnée du premier point
     * @param x2 abscisse du deuxième point
     * @param y2 ordonnée du deuxième point
     * @return la distance entre les deux points
     */
    protected double calculerDistance(int x1, int y1, int x2, int y2) {
        int deltaX = x2 - x1;
        int deltaY = y2 - y1;

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    protected int distanceJoueur(int x1, int y1, int x2, int y2){
        if ( x1 > x2){
            while (x1 != x2){
                distanceJoueur += 1;
                x1 -= 1;
            }
        }
        if ( x1 < x2){
            while (x1 != x2){
                distanceJoueur += 1;
                x1 += 1;
            }
        }
        if ( y1 > y2){
            while (y1 != y2){
                distanceJoueur += 1;
                y1 -= 1;
            }
        }
        if ( y1 < y2){
            while (y1 != y2){
                distanceJoueur += 1;
                y1 += 1;
            }
        }
        return distanceJoueur;
    }
}
