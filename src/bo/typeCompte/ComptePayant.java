package bo.typeCompte;

import bo.Compte;

public class ComptePayant extends Compte {

    /**
     * Constructeur d'un Compte Payant
     * @param id_compte Id Compte
     * @param solde Solde
     */
    public ComptePayant(int id_compte, double solde) {
        super(id_compte, solde);
    }

    /**
     * Calcul du montant avec le prélevement par opération
     * @param montant Montant
     */
    public void ope_payant(double montant){
        montant = montant * 0.95;
    }
}
