package Modele;

import Modele.Quete;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe représente une solution efficace pour résoudre un scénario de quêtes.
 * Elle hérite de la classe abstraite Solution.
 */
public class SolutionEfficace extends Solution {

    /**
     * Constructeur de la classe SolutionEfficace.
     *
     * @param quetes la liste des quêtes du scénario
     */
    public SolutionEfficace(List<Quete> quetes) {
        super(quetes);
    }

    /**
     * Résout les quêtes du scénario de manière efficace.
     * La méthode trouve la quête la plus proche à chaque étape
     * et réalise les préconditions nécessaires pour effectuer la quête.
     * La distance parcourue par le joueur et la durée totale sont calculées.
     */
    @Override
    public void resoudreQuetes() {
        while (!quetesRealisees.contains(0)){
            Quete queteLaPlusProche = trouverQueteLaPlusProche();
            int[] posQuete = trouverQueteLaPlusProche().getPos();
            int x1 = positionActuelle[0];
            int x2 = posQuete[0];
            int y1 = positionActuelle[1];
            int y2 = posQuete[1];
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
            if (queteLaPlusProche != null) {
                quetesRealisees.add(queteLaPlusProche.getNumero());
                quetesDisponibles.remove(queteLaPlusProche);
                positionActuelle = queteLaPlusProche.getPos();
                duree += queteLaPlusProche.getDuree();
                if(queteLaPlusProche.getNumero() != 0) {
                    niveauExperienceActuel += queteLaPlusProche.getExperience();
                }
                System.out.println("Quêtes réalisées : " + queteLaPlusProche.getNumero() + " " + queteLaPlusProche.getIntitule());
            }
        }
        System.out.println("Quêtes réalisées : " + quetesRealisees +
                "\nXP : " + niveauExperienceActuel +
                "\ndistance: " + distanceJoueur +
                "\ndurée : "+ (duree + distanceJoueur));
    }

    /**
     * Trouve la quête la plus proche du joueur parmi les quêtes disponibles.
     * La méthode tient compte des préconditions pour sélectionner la quête.
     *
     * @return la quête la plus proche ou null si aucune quête n'est disponible
     */
    @Override
    public Quete trouverQueteLaPlusProche() {
        Quete queteLaPlusProche = null;
        double distanceMin = Double.MAX_VALUE;

        for (Quete quete : quetesDisponibles) {
            if (verifierPreconditions(quete)) {
                niveauExperienceRequis = quete.getExperience();
                if (quete.getNumero() == 0 && niveauExperienceActuel >= niveauExperienceRequis) {
                    queteLaPlusProche = quete;
                }
                if (quete.getNumero() != 0){
                    int[] posQuete = quete.getPos();
                    double distance = calculerDistance(positionActuelle[0], positionActuelle[1], posQuete[0], posQuete[1]);
                    if (distance < distanceMin) {
                        distanceMin = distance;
                        queteLaPlusProche = quete;
                    }
                }
            }
        }
        return queteLaPlusProche;
    }
}
