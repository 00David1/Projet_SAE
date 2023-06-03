package modele;

import java.util.ArrayList;
import java.util.Collections;
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
            distanceJoueur = distanceJoueur(positionActuelle[0],positionActuelle[1],posQuete[0],posQuete[1]);
            if (queteLaPlusProche != null) {
                quetesRealisees.add(queteLaPlusProche.getNumero());
                quetesDisponibles.remove(queteLaPlusProche);
                positionActuelle = queteLaPlusProche.getPos();
                duree += queteLaPlusProche.getDuree();
                quetesRealiseesPendant.add(queteLaPlusProche.getIntitule());
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

    public List<Integer> getQuetesRealisees() {
        return quetesRealisees;
    }
    public List<Integer> getNiveauExperienceActuel() {
        return Collections.singletonList(niveauExperienceActuel);
    }
    public int getDistanceJoueur() {
        return distanceJoueur;
    }

    public int getDureeSE() {return duree;}


    public String getQuetesRealiseesPendant() {
        StringBuilder sb = new StringBuilder();

        for (String quete : quetesRealiseesPendant) {
            sb.append(quete);
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }
}
