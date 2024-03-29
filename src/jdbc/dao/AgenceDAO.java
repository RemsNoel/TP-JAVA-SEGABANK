package jdbc.dao;

import bo.Agence;
import bo.Compte;
import com.mysql.cj.exceptions.ConnectionIsClosedException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgenceDAO implements IDAO<Long, Agence> {

    /**
     * Différentes Requêtes SQL pour les Agences
     */
    private static final String INSERT_QUERY = "INSERT INTO agence (CODE, NOM_AGENCE, ADRESSE) VALUES(?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE agence SET CODE = ?, NOM_AGENCE = ?, ADRESSE = ? WHERE ID_AGENCE = ?";
    private static final String REMOVE_QUERY = "DELETE FROM agence WHERE ID_AGENCE = ?";
    private static final String FIND_QUERY = "SELECT * FROM agence WHERE ID_AGENCE = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM agence";
    private static final String FIND_ALL_COMPTE_QUERY = "SELECT compte.ID_COMPTE, compte.SOLDE, compte.ID_AGENCE," +
            " compte.DECOUVERT, compte.TAUX_INTERET FROM compte, agence WHERE agence.ID_AGENCE = ? AND compte.ID_AGENCE = agence.ID_AGENCE";

    /**
     * Création d'une Agence
     * @param agence Agence
     * @throws SQLException en cas d'erreur SQL
     * @throws IOException en cas d'erreur d'écriture ou de lecture
     * @throws ClassNotFoundException en cas d'erreur de classe inexistante
     */
    @Override
    public void create(Agence agence) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try ( PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS) ) {
                ps.setString(1, Integer.toString(agence.getCode()));
                ps.setString(2, agence.getNomAgence());
                ps.setString(3, agence.getAdresse());
                ps.executeUpdate();
                try ( ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        agence.setId_agence(rs.getInt(1));
                    }
                }
            }
        }
    }

    /**
     * Update d'une Agence
     * @param agence Agence
     * @throws SQLException en cas d'erreur SQL
     * @throws IOException en cas d'erreur d'écriture ou de lecture
     * @throws ClassNotFoundException en cas d'erreur de classe inexistante
     */
    @Override
    public void update(Agence agence) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null){
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)){
                ps.setString(1, Integer.toString(agence.getCode()));
                ps.setString(2, agence.getNomAgence());
                ps.setString(3,agence.getAdresse());
                ps.setString(4, Integer.toString(agence.getId_agence()));
                ps.executeUpdate();
            }
        }
    }

    /**
     * Delete d'une Agence
     * @param agence Agence
     * @throws SQLException en cas d'erreur SQL
     * @throws IOException en cas d'erreur d'écriture ou de lecture
     * @throws ClassNotFoundException en cas d'erreur de classe inexistante
     */
    @Override
    public void remove(Agence agence) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null){
            try (PreparedStatement ps = connection.prepareStatement(REMOVE_QUERY)){
                ps.setString(1, Integer.toString(agence.getId_agence()));
                ps.executeUpdate();
            }
        }
    }

    /**
     * Trouver une Agence par son id
     * @param idAgence Id de l'Agence
     * @return Agence
     * @throws SQLException en cas d'erreur SQL
     * @throws IOException en cas d'erreur d'écriture ou de lecture
     * @throws ClassNotFoundException en cas d'erreur de classe inexistante
     */
    @Override
    public Agence findById(Long idAgence) throws SQLException, IOException, ClassNotFoundException {
        Agence agence = new Agence();
        Connection connection = PersistenceManager.getConnection();
        if (connection != null){
            try ( PreparedStatement ps = connection.prepareStatement(FIND_QUERY)){
                ps.setString(1,Long.toString(idAgence));
                try (ResultSet rs = ps.executeQuery()){
                    if (rs.next()) {
                        agence.setId_agence(rs.getInt(1));
                        agence.setCode(rs.getInt(2));
                        agence.setNomAgence(rs.getString(3));
                        agence.setAdresse(rs.getString(4));
                    }
                }
            }
        }

        return agence;
    }

    /**
     * Trouver toute les Agences de la BDD
     * @return Liste d'Agence
     * @throws SQLException en cas d'erreur SQL
     * @throws IOException en cas d'erreur d'écriture ou de lecture
     * @throws ClassNotFoundException en cas d'erreur de classe inexistante
     */
    @Override
    public List<Agence> findAll() throws SQLException, IOException, ClassNotFoundException {
        List<Agence> listAgence = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if (connection != null){
            try(PreparedStatement ps = connection.prepareStatement(FIND_ALL_QUERY)){
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Agence agence = new Agence();
                        agence.setId_agence(rs.getInt(1));
                        agence.setCode(rs.getInt(2));
                        agence.setNomAgence(rs.getString(3));
                        agence.setAdresse(rs.getString(4));
                        listAgence.add(agence);
                    }
                }
            }
        }

        return listAgence;
    }

    /**
     * Trouver tout les comptes d'une Agence
     * @param agence Agence
     * @return
     * @throws SQLException en cas d'erreur SQL
     * @throws IOException en cas d'erreur d'écriture ou de lecture
     * @throws ClassNotFoundException en cas d'erreur de classe inexistante
     */
    public List<Compte> findAllCompte(Agence agence) throws SQLException, IOException, ClassNotFoundException {
        List<Compte> listCompte = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if (connection != null){
            try(PreparedStatement ps = connection.prepareStatement(FIND_ALL_COMPTE_QUERY)){
                ps.setString(1, Integer.toString(agence.getId_agence()));
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Compte compte = new Compte();
                        compte.setId_compte(rs.getInt(1));
                        compte.setSolde(rs.getInt(2));
                        compte.setId_agence(rs.getInt(3));
                        compte.setDecouvert(rs.getInt(4));
                        compte.setTauxInteret(rs.getInt(5));
                        listCompte.add(compte);
                    }
                }
            }
        }
        return listCompte;

    }
}
