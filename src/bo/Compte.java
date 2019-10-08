package bo;

public class Compte {

    private int id_compte;
    private double solde;

    public Compte() {
    }

    public Compte(int id_compte, double solde) {
        this.id_compte = id_compte;
        this.solde = solde;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "id_compte=" + id_compte +
                ", solde=" + solde +
                '}';
    }

    public int getId_compte() {
        return id_compte;
    }

    public void setId_compte(int id_compte) {
        this.id_compte = id_compte;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
}
