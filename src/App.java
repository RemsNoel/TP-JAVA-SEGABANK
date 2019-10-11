import bo.Agence;
import jdbc.dao.AgenceDAO;
import jdbc.dao.IDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class App {
    public static void main(String args[]) {

        IDAO<Long, Agence> dao = new AgenceDAO();
        Agence agence1 = new Agence(001, "La Faillite", "Rue de la soif");
        Agence agence2 = new Agence(001, "Le Jackpot", "Rue de la tune");
        Agence agence3 = new Agence(002, "Les Bosseurs", "Rue de la main d'oeuvre");
        Agence agence4 = new Agence();
        List<Agence> listAgence = new ArrayList<>();
        try {
            dao.create(agence1);
            dao.create(agence2);
            dao.create(agence3);
            listAgence = dao.findAll();
            agence4 = dao.findById(2L);
            dao.remove(agence4);

            for (Agence ag : listAgence) {
                System.out.println(ag.toString());
            }
            System.out.println(agence4.toString());
            System.out.println("-------------------------");
            dao.remove(agence4);
            listAgence = dao.findAll();
            for (Agence ag : listAgence) {
                System.out.println(ag.toString());
            }
            System.out.println("------------------------------");
            Agence agence1Update = new Agence(1,100,"Noirfutaie","Quelque part");
            dao.update(agence1Update);
            listAgence = dao.findAll();
            for (Agence ag : listAgence) {
                System.out.println(ag.toString());
            }
        } catch (IOException | SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }

    }
}
