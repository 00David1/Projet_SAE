package modele;

import java.util.Collections;
import java.util.List;

/**
 * Cette classe représente une solution exhaustive pour résoudre un scénario de quêtes.
 * Elle hérite de la classe abstraite Solution.
 */
public class SolutionExhaustive extends Solution {

    /**
     * Constructeur de la classe SolutionExhaustive.
     *
     * @param quetes la liste des quêtes du scénario
     */
    public SolutionExhaustive(List<Quete> quetes) {
        super(quetes);
    }


    /**
     * Résout les quêtes du scénario de manière exhaustive.
     * La méthode trouve la quête la plus proche à chaque étape
     * et réalise les préconditions nécessaires pour effectuer la quête.
     * La distance parcourue par le joueur et la durée totale sont calculées.
     */
    @Override
    public void resoudreQuetes() {
        int distanceJ = 0;
        while (!quetes.isEmpty()){
            Quete queteLaPlusProche = trouverQueteLaPlusProche();
            if (queteLaPlusProche != null) {
                quetesRealisees.add(queteLaPlusProche.getNumero());
                quetes.remove(queteLaPlusProche);
                positionActuelle = queteLaPlusProche.getPos();
                duree += queteLaPlusProche.getDuree();
                distanceJ += distanceJoueur;
                quetesRealiseesPendant.add(queteLaPlusProche.getIntitule());
                if(queteLaPlusProche.getNumero() != 0) {
                    niveauExperienceActuel += queteLaPlusProche.getExperience();
                }
                //System.out.println("Quêtes réalisées : " + queteLaPlusProche.getNumero() + " " + queteLaPlusProche.getIntitule() );
            }


        }
        /*System.out.println("Quêtes réalisées : " + quetesRealisees +
                "\nXP : " + niveauExperienceActuel +
                "\ndistance: " + distanceJ +
                "\ndurée : "+ (duree + distanceJ));*/
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
                if (!(!(quete.getNumero() == 0) || !(niveauExperienceActuel >= niveauExperienceRequis) || !(quetes.size() == 1)))
                    queteLaPlusProche = quete;
                if (quete.getNumero() != 0){
                    int[] posQuete = quete.getPos();
                    distanceJoueur = 0;
                    distanceJoueur = calculerDistance(positionActuelle[0], positionActuelle[1], posQuete[0], posQuete[1]);
                    if (distanceJoueur < distanceMin) {
                        distanceMin = distanceJoueur;
                        queteLaPlusProche = quete;
                    }
                }
            }
        }
        return queteLaPlusProche;
    }


    public List<Integer> getQuetesRealiseesEx() {
        return quetesRealisees;
    }
    public List<Integer> getNiveauExperienceActuelEx() {
        return Collections.singletonList(niveauExperienceActuel);
    }
    public int getDistanceJoueurEx() {
        return distanceJoueur;
    }

    public int getDureeSEEx() {return duree;}


    public String getQuetesRealiseesPendantEx() {
        StringBuilder sb = new StringBuilder();

        for (String quete : quetesRealiseesPendant) {
            sb.append(quete);
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }
}
