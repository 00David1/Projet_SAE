package modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Cette classe représente une solution efficace pour résoudre un scénario de quêtes.
 * Elle hérite de la classe abstraite Solution.
 */
public class SolutionEfficace extends Solution {
    private int distanceJ;

    /**
     * Constructeur de la classe SolutionEfficace.
     *
     * @param quetes la liste des quêtes du scénario
     */
    public SolutionEfficace(List<Quete> quetes) {
        super(quetes);
        distanceJ = 0;
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
            if (queteLaPlusProche != null) {
                quetesRealisees.add(queteLaPlusProche.getNumero());
                quetes.remove(queteLaPlusProche);
                positionActuelle = queteLaPlusProche.getPos();
                duree += queteLaPlusProche.getDuree();
                //System.out.println("2                "+ distanceJoueur);
                distanceJ += distanceJoueur;
                quetesRealiseesPendant.add(queteLaPlusProche.getIntitule());
                if(queteLaPlusProche.getNumero() != 0) {
                    niveauExperienceActuel += queteLaPlusProche.getExperience();
                }
                //System.out.println("Quêtes réalisées : " + queteLaPlusProche.getNumero() + " " + queteLaPlusProche.getIntitule());
            }
        }
        /*System.out.println("Quêtes réalisées : " + quetesRealisees +
                "\nXP : " + niveauExperienceActuel +
                "\ndistance: " + (distanceJ -2) +
                "\ndurée : "+ (duree + distanceJ -2));*/
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
        int distanceMin = 1000;


        for (Quete quete : quetes) {
            if (verifierPreconditions(quete)) {
                niveauExperienceRequis = quete.getExperience();
                if (quete.getNumero() == 0 && niveauExperienceActuel >= niveauExperienceRequis) {
                    queteLaPlusProche = quete;
                }
                if (quete.getNumero() != 0){
                    int[] posQuete = quete.getPos();
                    distanceJoueur = 0;
                    distanceJoueur = calculerDistance(positionActuelle[0], positionActuelle[1], posQuete[0], posQuete[1]);
                    //System.out.println("1          "+ distanceJoueur);
                    if (distanceJoueur < distanceMin) {
                        distanceMin = distanceJoueur;
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
        return distanceJ;
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
