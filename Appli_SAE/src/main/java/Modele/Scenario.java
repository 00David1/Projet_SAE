package Modele;

import Modele.Quete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Cette classe représente un scénario composé de plusieurs quêtes.
 */
public class Scenario {
    final ArrayList<Quete> quetes; // Liste des quêtes du scénario

    /**
     * Constructeur de la classe Scenario.
     */
    public Scenario() {
        quetes = new ArrayList<>();
    }

    /**
     * Ajoute une quête au scénario.
     *
     * @param quete la quête à ajouter
     */
    public void ajout(Quete quete) {
        quetes.add(quete);
    }

    /**
     * Renvoie une représentation sous forme de chaîne de caractères du scénario.
     * Les quêtes sont triées par ordre croissant en fonction de leur numéro.
     *
     * @return la représentation du scénario en tant que chaîne de caractères
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Collections.sort(quetes, Comparator.comparingInt(Quete::getNumero));
        for (Quete q : quetes) {
            sb.append(q.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
