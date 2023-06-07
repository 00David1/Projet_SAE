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

    /**
     * Renvoie tous les chemins possibles pour atteindre la quête 0.
     *
     * @return une liste de chemins vers la quête 0
     */
    public List<List<Integer>> obtenirChemins() {
        List<Integer> quetesRealisees = new ArrayList<>();
        List<Integer> cheminActuel = new ArrayList<>();
        resoudreQuetes(quetes, quetesRealisees, cheminActuel, 0);
        return chemins;
    }

    private List<Integer> trouverCheminMinDuree(Map<List<Integer>, Integer> cheminDureeMap) {
        List<Integer> cheminMinDuree = null;
        int dureeMin = Integer.MAX_VALUE;

        for (Map.Entry<List<Integer>, Integer> entry : cheminDureeMap.entrySet()) {
            List<Integer> chemin = entry.getKey();
            int duree = entry.getValue();

            if (duree < dureeMin) {
                dureeMin = duree;
                cheminMinDuree = chemin;
            }
        }

        return cheminMinDuree;
    }

    /**
     * Résout les quêtes en ajoutant les quêtes réalisées à la liste et en
     * construisant les chemins vers la quête 0.
     *
     * @param quetes            la liste des quêtes disponibles
     * @param quetesRealisees   la liste des quêtes déjà réalisées
     * @param cheminActuel      le chemin actuel en cours de construction
     */
    private void resoudreQuetes(List<Quete> quetes, List<Integer> quetesRealisees, List<Integer> cheminActuel, int experienceActuel) {
        if (cheminActuel.contains(0) || quetesCopy.isEmpty()) {
            int dureeTotale = calculerDureeTotale(cheminActuel);
            int experience = calculerExperienceTotale(cheminActuel);
            chemins.add(new ArrayList<>(cheminActuel));
            durees.add(dureeTotale);
            experienceTotal.add(experience);
        } else {
            for (int i = 0; i < quetesCopy.size(); i++) {
                Quete quete = quetesCopy.get(i);
                if (verifierPreconditions(quete, quetesRealisees)) {
                    if (quete.getNumero() == 0 && experienceActuel >= quete.getExperience()) {
                        quetesRealisees.add(quete.getNumero());
                        quetesCopy.remove(quete);
                        cheminActuel.add(quete.getNumero());

                        int nouvelleExperienceActuel = experienceActuel - quete.getExperience();
                        resoudreQuetes(quetesCopy, quetesRealisees, cheminActuel, nouvelleExperienceActuel);

                        quetesRealisees.remove(quetesRealisees.size() - 1);
                        quetesCopy.add(i, quete);
                        cheminActuel.remove(cheminActuel.size() - 1);
                    } else if (quete.getNumero() != 0) {
                        quetesRealisees.add(quete.getNumero());
                        quetesCopy.remove(quete);
                        cheminActuel.add(quete.getNumero());

                        int nouvelleExperienceActuel = experienceActuel + quete.getExperience();
                        resoudreQuetes(quetesCopy, quetesRealisees, cheminActuel, nouvelleExperienceActuel);

                        quetesRealisees.remove(quetesRealisees.size() - 1);
                        quetesCopy.add(i, quete);
                        cheminActuel.remove(cheminActuel.size() - 1);
                    }
                }
            }
        }
    }

    /**
     * Vérifie si les préconditions d'une quête sont satisfaites.
     *
     * @param quete             la quête à vérifier
     * @param quetesRealisees   la liste des quêtes déjà réalisées
     * @return true si les préconditions sont satisfaites, false sinon
     */
    private boolean verifierPreconditions(Quete quete, List<Integer> quetesRealisees) {
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

        return precond1Realisee && precond2Realisee;
    }

    /**
     * Calcule la durée totale d'un chemin.
     *
     * @param chemin    le chemin pour lequel calculer la durée totale
     * @return la durée totale du chemin
     */
    private int calculerDureeTotale(List<Integer> chemin) {
        int dureeTotale = 0;
        int x1 = posActuele[0];
        int y1 = posActuele[1];

        for (int n : chemin) {
            Quete quete = trouverQueteParNumero(n);
            if (quete != null) {  // Ajout de la vérification pour quete != null
                int[] pos = quete.getPos();
                int x2 = pos[0];
                int y2 = pos[1];
                int distance = calculerDistance(x1, y1, x2, y2);
                dureeTotale += distance + quete.getDuree();
                x1 = x2;
                y1 = y2;
            }
        }
        return dureeTotale;
    }

    private int calculerExperienceTotale(List<Integer> chemin) {
        int experienceTot = 0;
        for (int n : chemin) {
            Quete quete = trouverQueteParNumero(n);
            if (quete != null) {  // Ajout de la vérification pour quete != null
                if (quete.getNumero() != 0){
                    experienceTot += quete.getExperience();
                }
            }
        }
        return experienceTot;
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

    private int calculerDistance(int x1, int y1, int x2, int y2) {
        int distanceJ = 0;
        if (x1 > x2) {
            while (x1 != x2) {
                distanceJ += 1;
                x1 -= 1;
            }
        }
        if (x1 < x2) {
            while (x1 != x2) {
                distanceJ += 1;
                x1 += 1;
            }
        }
        if (y1 > y2) {
            while (y1 != y2) {
                distanceJ += 1;
                y1 -= 1;
            }
        }
        if (y1 < y2) {
            while (y1 != y2) {
                distanceJ += 1;
                y1 += 1;
            }
        }
        return distanceJ;
    }

    /**
     * Trouve une quête par son numéro.
     *
     * @param numero    le numéro de la quête à rechercher
     * @return la quête correspondante, ou null si non trouvée
     */
    private Quete trouverQueteParNumero(int numero) {
        for (Quete quete : quetes) {
            if (quete.getNumero() == numero) {
                return quete;
            }
        }
        return null;
    }

    public List<Integer> getDurees() {
        return durees;
    }

    public List<Integer> getExperienceTotal() {
        return experienceTotal;
    }
}
