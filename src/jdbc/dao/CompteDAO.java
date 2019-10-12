package jdbc.dao;

import bo.Compte;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteDAO implements IDAO<Long, Compte> {

    /**
     * Différentes Requêtes SQL pour les Comptes
     */
    private static final String INSERT_QUERY = "INSERT INTO compte (SOLDE, ID_AGENCE, DECOUVERT, TAUX_INTERET) VALUES(?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE compte SET SOLDE = ?, ID_AGENCE = ?, DECOUVERT = ?, TAUX_INTERET = ? WHERE ID_COMPTE = ?";
    private static final String REMOVE_QUERY = "DELETE FROM compte WHERE ID_COMPTE = ?";
    private static final String FIND_QUERY = "SELECT * FROM compte WHERE ID_COMPTE = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM compte";
    /**
     * Path sortie fichier csv
     */
    private static String OUT_FILE = "./ressources/compte/compte";
    private static String FORMAT = ".csv";

    /**
     * Création d'un Compte
     * @param compte Compte
     * @throws SQLException en cas d'erreur SQL
     * @throws IOException en cas d'erreur d'écriture ou de lecture
     * @throws ClassNotFoundException en cas d'erreur de classe inexistante
     */
    @Override
    public void create(Compte compte) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try ( PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS) ) {
                ps.setString(1, Double.toString(compte.getSolde()));
                ps.setString(2, Integer.toString(compte.getId_agence()));
                ps.setString(3, Double.toString(compte.getDecouvert()));
                ps.setString(4, Double.toString(compte.getTauxInteret()));
                ps.executeUpdate();
                try ( ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        compte.setId_compte(rs.getInt(1));
                    }
                }
            }
            String donneeCompte = compte.getId_compte() + "," +
                    compte.getSolde() + "," + compte.getId_agence() + "," +
                    compte.getDecouvert() + "," + compte.getTauxInteret();
            Path outCsv = Paths.get(OUT_FILE + compte.getId_compte() + FORMAT);
            File file = new File(outCsv.toString());
            file.createNewFile();
            FileWriter fw = new FileWriter(outCsv.toString(), true);
            try(BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(donneeCompte);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Update d'un Compte
     * @param compte Compte
     * @throws SQLException en cas d'erreur SQL
     * @throws IOException en cas d'erreur d'écriture ou de lecture
     * @throws ClassNotFoundException en cas d'erreur de classe inexistante
     */
    @Override
    public void update(Compte compte) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null){
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)){
                ps.setString(1, Double.toString(compte.getSolde()));
                ps.setString(2, Integer.toString(compte.getId_agence()));
                ps.setString(3, Double.toString(compte.getDecouvert()));
                ps.setString(4, Double.toString(compte.getTauxInteret()));
                ps.setString(5, Integer.toString(compte.getId_compte()));
                ps.executeUpdate();
            }
            String donneeCompte = compte.getId_compte() + "," +
                    compte.getSolde() + "," + compte.getId_agence() + "," +
                    compte.getDecouvert() + "," + compte.getTauxInteret();
            Path outCsv = Paths.get(OUT_FILE + compte.getId_compte() + FORMAT);
            File file = new File(outCsv.toString());
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(outCsv.toString(), true);
            try(BufferedWriter bw = new BufferedWriter(fw)) {
                bw.newLine();
                bw.write(donneeCompte);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Delete d'un compte
     * @param compte Compte
     * @throws SQLException en cas d'erreur SQL
     * @throws IOException en cas d'erreur d'écriture ou de lecture
     * @throws ClassNotFoundException en cas d'erreur de classe inexistante
     */
    @Override
    public void remove(Compte compte) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null){
            try (PreparedStatement ps = connection.prepareStatement(REMOVE_QUERY)){
                ps.setString(1, Integer.toString(compte.getId_compte()));
                ps.executeUpdate();
            }
        }
    }

    /**
     * Trouver un Compte avec don id
     * @param idCompte Id d'un Compte
     * @return Compte
     * @throws SQLException en cas d'erreur SQL
     * @throws IOException en cas d'erreur d'écriture ou de lecture
     * @throws ClassNotFoundException en cas d'erreur de classe inexistante
     */
    @Override
    public Compte findById(Long idCompte) throws SQLException, IOException, ClassNotFoundException {
        Compte compte = new Compte();
        Connection connection = PersistenceManager.getConnection();
        if (connection != null){
            try ( PreparedStatement ps = connection.prepareStatement(FIND_QUERY)){
                ps.setString(1,Long.toString(idCompte));
                try (ResultSet rs = ps.executeQuery()){
                    if (rs.next()) {
                        compte.setId_compte(rs.getInt(1));
                        compte.setSolde(rs.getDouble(2));
                        compte.setId_agence(rs.getInt(3));
                        compte.setDecouvert(rs.getDouble(4));
                        compte.setTauxInteret(rs.getDouble(5));
                    }
                }
            }
        }

        return compte;
    }

    /**
     * Trouver tout les Comptes existants
     * @return Liste de Comptes
     * @throws SQLException en cas d'erreur SQL
     * @throws IOException en cas d'erreur d'écriture ou de lecture
     * @throws ClassNotFoundException en cas d'erreur de classe inexistante
     */
    @Override
    public List<Compte> findAll() throws SQLException, IOException, ClassNotFoundException {
        List<Compte> listCompte = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if (connection != null){
            try(PreparedStatement ps = connection.prepareStatement(FIND_ALL_QUERY)){
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Compte compte = new Compte();
                        compte.setId_compte(rs.getInt(1));
                        compte.setSolde(rs.getDouble(2));
                        compte.setId_agence(rs.getInt(3));
                        compte.setDecouvert(rs.getDouble(4));
                        compte.setTauxInteret(rs.getDouble(4));
                        listCompte.add(compte);
                    }
                }
            }
        }

        return listCompte;
    }
}
