package test;

import bo.Compte;
import jdbc.dao.CompteDAO;
import jdbc.dao.IDAO;

import java.io.IOException;
import java.sql.SQLException;

public class TestOutputFile {
    public static void main(String args[]){

        IDAO<Long, Compte> compteDAO = new CompteDAO();
        Compte compte1 = new Compte(5000, 01, -200,0);
        Compte compte2 = new Compte (26, 5800,01,-200,0);
        try {
//            compteDAO.create(compte1);
            compteDAO.update(compte2);
        } catch (SQLException | IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
