package jdbc.dao;

import java.sql.Connection;

public class PersistenceManager {

    private static final String PROPS_FILE = "./resources/db.properties";

    private static Connection connection;

    private PersistenceManager(){}//Prevents initialization
}
