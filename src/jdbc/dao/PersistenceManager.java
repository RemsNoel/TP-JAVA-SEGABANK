package jdbc.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PersistenceManager {

    private static final String PROPS_FILE = "./ressources/db.properties";

    private static Connection connection;

    private PersistenceManager(){}//Prevents initialization

    /**
     * Permet de maintenir la connection
     * @return Connection
     * @throws SQLException en cas d'erreur SQL
     * @throws IOException en cas d'erreur d'écriture ou de lecture
     * @throws ClassNotFoundException en cas d'erreur de classe inexistante
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException, IOException {
        if ( connection == null || !connection.isValid( 2 )) {

            Properties props = new Properties();
            try ( FileInputStream fis = new FileInputStream( PROPS_FILE ) ) {
                props.load( fis );
            }

            String driverClass = props.getProperty( "jdbc.class.driver" );
            String dbURL = props.getProperty( "jdbc.db.url" );
            String dbLogin = props.getProperty( "jdbc.db.login" );
            String dbPWD = props.getProperty( "jdbc.db.pwd" );

            Class.forName( driverClass );
            connection = DriverManager.getConnection( dbURL, dbLogin, dbPWD );
        }
        return connection;
    }

    /**
     * Permet de finir la Connection
     * @throws SQLException en cas d'erreur SQL
     */
    public static void closeConnection() throws SQLException {
        if (connection != null && connection.isValid( 2 )) {
            connection.close();
        }
    }
}
