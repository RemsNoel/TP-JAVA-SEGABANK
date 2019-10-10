package jdbc.dao;

import bo.Agence;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class AgenceDAO implements IDAO<Long, Agence> {

    private static final String INSERT_QUERY = "INSERT INTO agence (CODE, ADRESSE) VALUES(?,?)";
    private static final String UPDATE_QUERY = "UPDATE agence SET CODE = ?, ADRESSE = ? WHERE ID_AGENCE = ?";
    private static final String REMOVE_QUERY = "DELETE FROM contact WHERE ID_AGENCE = ?";
    private static final String FIND_QUERY = "SELECT * FROM contact WHERE ID_AGENCE = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM agence";

    @Override
    public void create(Agence agence) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try ( PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS) ) {
                ps.setString(1, Integer.toString(agence.getCode()));
                ps.setString(2, agence.getAdresse());
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

    }

    @Override
    public void remove(Agence agence) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public Agence findById(Long aLong) throws SQLException, IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Agence> findAll() throws SQLException, IOException, ClassNotFoundException {
        return null;
    }
}
