package bo.typeCompte;

import bo.Compte;

public class CompteEpargne extends Compte {

    private double taux_interet;

    /**
     * Calcul des intéret du Compte Epargne
     * @param solde Solde
     * @param interet Interet
     */
    public void calcule_interet(double solde, double interet){
        solde = solde * interet;
    }

    /**
     * Constructeur d'une Création d'un Compte Epargne
     * @param id_compte Id Compte
     * @param solde Solde
     * @param taux_interet Taux Interet
     */
    public CompteEpargne(int id_compte, int solde, int taux_interet) {
        super(id_compte, solde);
        this.taux_interet = taux_interet;
    }

    public double getTaux_interet() {
        return taux_interet;
    }

    public void setTaux_interet(double taux_interet) {
        this.taux_interet = taux_interet;
    }
}
