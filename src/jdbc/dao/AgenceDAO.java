package jdbc.dao;

import bo.Agence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AgenceDAO implements IDAO<Long, Agence> {
    @Override
    public void create(Agence object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public void update(Agence object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public void remove(Agence object) throws SQLException, IOException, ClassNotFoundException {

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
