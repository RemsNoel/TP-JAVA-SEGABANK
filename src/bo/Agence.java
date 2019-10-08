package bo;

import java.util.List;

public class Agence {

    private int id_agence;
    private int code;
    private String adresse;
    private List<Compte> listeCompte;

    public Agence() {
    }

    public Agence(int id_agence, int code, String adresse, List<Compte> listeCompte) {
        this.id_agence = id_agence;
        this.code = code;
        this.adresse = adresse;
        this.listeCompte = listeCompte;
    }

    public int getId_agence() {
        return id_agence;
    }

    public void setId_agence(int id_agence) {
        this.id_agence = id_agence;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
