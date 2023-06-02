package Modele;

import java.util.Scanner;

/**
 * Cette classe représente une quête.
 */
public class Quete {
    private int numero;
    private int[] pos;
    private int[] precond1;
    private int[] precond2;
    private int duree;
    private int experience;
    private String intitule;

    /**
     * Constructeur de la classe Quete.
     *
     * @param ligne la ligne contenant les informations de la quête
     */
    public Quete(String ligne) {
        Scanner scanner = new Scanner(ligne).useDelimiter("\\|");
        this.numero = scanner.nextInt();
        String strPos = scanner.next();
        this.pos = extraitPos(strPos);
        String preconds = scanner.next();
        int[][] precond = extraitPrecond(preconds);
        this.precond1 = precond[0];
        this.precond2 = precond[1];
        this.duree = scanner.nextInt();
        this.experience = scanner.nextInt();
        this.intitule = scanner.next();
    }

    /**
     * Extrait les coordonnées de position à partir d'une chaîne de caractères.
     *
     * @param position la chaîne de caractères contenant les coordonnées de position
     * @return un tableau d'entiers représentant les coordonnées de position
     */
    public int[] extraitPos(String position) {
        int[] tabPos = new int[2];
        position = position.replace("(", "");
        position = position.replace(")", "");
        position = position.replace(" ", "");
        Scanner sc = new Scanner(position).useDelimiter(",");
        int i = 0;
        while (sc.hasNext()) {
            String extrait = sc.next();
            if (!extrait.equals("")) {
                tabPos[i] = Integer.parseInt(extrait);
            }
            i++;
        }
        return tabPos;
    }

    /**
     * Extrait les préconditions à partir d'une chaîne de caractères.
     *
     * @param precond la chaîne de caractères contenant les préconditions
     * @return un tableau 2D d'entiers représentant les préconditions
     */
    public int[][] extraitPrecond(String precond) {
        precond = precond.replace(" ", "");

        if (precond.length() > 0) {
            precond = precond.substring(1);

            if (precond.length() > 0) {
                precond = precond.substring(0, precond.length() - 1);

                int deuxiemeVirguleIndex = precond.indexOf(',', precond.indexOf(',') + 1);

                if (deuxiemeVirguleIndex != -1) {
                    String partie1 = precond.substring(0, deuxiemeVirguleIndex);
                    String partie2 = precond.substring(deuxiemeVirguleIndex + 1);

                    partie1 = partie1.replace("(", "").replace(")", "");
                    partie2 = partie2.replace("(", "").replace(")", "");

                    String[] parties1 = partie1.split(",");
                    String[] parties2 = partie2.split(",");

                    int[] precond1;
                    int[] precond2;

                    if (parties1.length == 1 && parties1[0].isEmpty()) {
                        precond1 = new int[0];
                    } else {
                        precond1 = new int[parties1.length];
                        for (int i = 0; i < parties1.length; i++) {
                            if (!parties1[i].isEmpty()) {
                                precond1[i] = Integer.parseInt(parties1[i]);
                            }
                        }
                    }

                    if (parties2.length == 1 && parties2[0].isEmpty()) {
                        precond2 = new int[0];
                    } else {
                        precond2 = new int[parties2.length];
                        for (int i = 0; i < parties2.length; i++) {
                            if (!parties2[i].isEmpty()) {
                                precond2[i] = Integer.parseInt(parties2[i]);
                            }
                        }
                    }

                    return new int[][] { precond1, precond2 };
                }
            }
        }

        return new int[][] { new int[0], new int[0] };
    }

    /**
     * Formate les préconditions pour l'affichage.
     *
     * @param precond les préconditions
     * @return une chaîne de caractères formatée représentant les préconditions
     */
    private String formatPrecond(int[] precond) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");

        for (int i = 0; i < precond.length; i++) {
            sb.append(precond[i]);

            if (i < precond.length - 1) {
                sb.append(", ");
            }
        }

        sb.append(")");

        return sb.toString();
    }

    /**
     * Renvoie une représentation sous forme de chaîne de caractères de la quête.
     *
     * @return la représentation de la quête en tant que chaîne de caractères
     */
    @Override
    public String toString() {
        return "Quête n°" + numero + " : " + intitule + "\n" +
                "Position : (" + pos[0] + ", " + pos[1] + ")\n" +
                "Préconditions : " + formatPrecond(precond1) +" et " + formatPrecond(precond2) + "\n" +
                "Durée : " + duree + " min\n" +
                "Expérience : " + experience + "\n";
    }

    /**
     * Renvoie le numéro de la quête.
     *
     * @return le numéro de la quête
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Renvoie les coordonnées de position de la quête.
     *
     * @return un tableau d'entiers représentant les coordonnées de position
     */
    public int[] getPos() {
        return pos;
    }

    /**
     * Renvoie les préconditions 1 de la quête.
     *
     * @return un tableau d'entiers représentant les préconditions 1
     */
    public int[] getPrecond1() {
        return precond1;
    }

    /**
     * Renvoie les préconditions 2 de la quête.
     *
     * @return un tableau d'entiers représentant les préconditions 2
     */
    public int[] getPrecond2() {
        return precond2;
    }

    /**
     * Renvoie la durée de la quête.
     *
     * @return la durée de la quête en minutes
     */
    public int getDuree() {
        return duree;
    }

    /**
     * Renvoie l'expérience donnée par la quête.
     *
     * @return l'expérience donnée par la quête
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Renvoie l'intitulé de la quête.
     *
     * @return l'intitulé de la quête
     */
    public String getIntitule() {
        return intitule;
    }
}
