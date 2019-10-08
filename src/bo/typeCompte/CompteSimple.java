package bo.typeCompte;

import bo.Compte;

public class CompteSimple extends Compte {

    private double decouvert;

    public CompteSimple(int id_compte, double solde, double decouvert) {
        super(id_compte, solde);
        this.decouvert = decouvert;
    }

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }
}
