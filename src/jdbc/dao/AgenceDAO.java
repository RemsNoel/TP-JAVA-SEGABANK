package jdbc.dao;

import bo.Agence;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgenceDAO implements IDAO<Long, Agence> {

    private static final String INSERT_QUERY = "INSERT INTO agence (CODE, NOM_AGENCE, ADRESSE) VALUES(?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE agence SET CODE = ?, NOM_AGENCE = ?, ADRESSE = ? WHERE ID_AGENCE = ?";
    private static final String REMOVE_QUERY = "DELETE FROM agence WHERE ID_AGENCE = ?";
    private static final String FIND_QUERY = "SELECT * FROM agence WHERE ID_AGENCE = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM agence";

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

    @Override
    public void update(Agence agence) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null){
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY, Statement.RETURN_GENERATED_KEYS)){
                ps.setString(1, Integer.toString(agence.getCode()));
                ps.setString(2, agence.getNomAgence());
                ps.setString(3,agence.getAdresse());
                ps.setString(4, Integer.toString(agence.getId_agence()));
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()){
                    if (rs.next()){
                        agence.setId_agence(rs.getInt(1));
                        agence.setCode(rs.getInt(2));
                        agence.setNomAgence(rs.getString(3));
                        agence.setAdresse(rs.getString(4));
                    }
                }
            }
        }
    }

    @Override
    public void remove(Agence agence) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null){
            try (PreparedStatement ps = connection.prepareStatement(REMOVE_QUERY, Statement.RETURN_GENERATED_KEYS)){
                ps.setString(1, Integer.toString(agence.getId_agence()));
                ps.executeUpdate();
            }
        }
    }

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
}
