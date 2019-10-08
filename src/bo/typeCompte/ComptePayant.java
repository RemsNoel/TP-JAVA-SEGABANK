package bo.typeCompte;

import bo.Compte;

public class ComptePayant extends Compte {

    public ComptePayant(int id_compte, double solde) {
        super(id_compte, solde);
    }

    public void ope_payant(double montant){
        montant = montant * 0.95;
    }
}
