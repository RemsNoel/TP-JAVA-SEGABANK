import bo.Agence;
import jdbc.dao.AgenceDAO;
import jdbc.dao.IDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class App {
    public static void main(String args[]) {

        IDAO<Long, Agence> dao = new AgenceDAO();
        Agence agence = new Agence(001, "Rue de la soif");
        try {
            dao.create(agence);
            System.out.println(agence);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }

    }
}
