package bo;

public class Compte {

    private int id_compte;
    private double solde;
    private int id_agence;
    private double decouvert;
    private double  tauxInteret;

    /**
     * Constructeur par défaut
     */
    public Compte() {
    }

    /**
     * Constructeur Basique
     * @param id_compte Id Compte
     * @param solde Solde
     */
    public Compte(int id_compte, double solde) {
        this.id_compte = id_compte;
        this.solde = solde;
    }

    /**
     * Constructeur pour une Création  de compte
     * @param solde Solde
     * @param id_agence Id Agence
     * @param decouvert Découvert
     * @param tauxInteret Taux interet
     */
    public Compte(double solde, int id_agence, double decouvert, double tauxInteret) {
        this.solde = solde;
        this.id_agence = id_agence;
        this.decouvert = decouvert;
        this.tauxInteret = tauxInteret;
    }

    /**
     * Constructeur pour un Update de Compte
     * @param id_compte Id Compte
     * @param solde Solde
     * @param id_agence Id Agence
     * @param decouvert Découvert
     * @param tauxInteret Taux Interet
     */
    public Compte(int id_compte, double solde, int id_agence, double decouvert, double tauxInteret) {
        this.id_compte = id_compte;
        this.solde = solde;
        this.id_agence = id_agence;
        this.decouvert = decouvert;
        this.tauxInteret = tauxInteret;
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

    public int getId_agence() {
        return id_agence;
    }

    public void setId_agence(int id_agence) {
        this.id_agence = id_agence;
    }

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "id_compte=" + id_compte +
                ", solde=" + solde +
                ", id_agence=" + id_agence +
                ", decouvert=" + decouvert +
                ", tauxInteret=" + tauxInteret +
                '}';
    }
}
