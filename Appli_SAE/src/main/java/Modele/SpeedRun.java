package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpeedRun {
    private List<List<Integer>> chemins;
    private List<Integer> durees; // Ajout de la liste des durées
    private List<Quete> quetes;
    private List<Quete> quetesCopy;
    private int[] posActuele ;
    private List<Integer> experienceTotal;
    private int experienceRequis;

    /**
     * Constructeur de la classe CheminVersQuete0.
     */
    public SpeedRun(List<Quete> quetes) {
        this.quetes = quetes;
        this.chemins = new ArrayList<>();
        this.durees = new ArrayList<>(); // Initialisation de la liste des durées
        this.posActuele = new int[]{0, 0};
        this.quetesCopy = new ArrayList<>(quetes); // Créer une copie indépendante de la liste quetes
        this.experienceTotal = new ArrayList<>();
        this.experienceRequis = 0;
    }

    public void afficherCheminsAvecDurees() {
        List<List<Integer>> chemins = obtenirChemins();
        List<Integer> durees = getDurees();
        List<Integer> experienceA = getExperienceTotal();

        Map<List<Integer>, Integer> cheminDureeMap = new HashMap<>();

        for (int i = 0; i < chemins.size(); i++) {
            List<Integer> chemin = chemins.get(i);
            int duree = durees.get(i);
            int XP = experienceA.get(i);
            cheminDureeMap.put(chemin, duree);
            //System.out.println("Chemin : " + chemin + " Durée : " + duree + " XP : " + XP);
        }

        List<Integer> cheminMinDuree = trouverCheminMinDuree(cheminDureeMap);
        int dureeMin = cheminDureeMap.get(cheminMinDuree);
        System.out.println("Chemin avec la durée minimale : " + cheminMinDuree + " Durée minimale : " + dureeMin);
    }
}
