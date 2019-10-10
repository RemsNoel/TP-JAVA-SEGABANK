package jdbc.dao;

import bo.Compte;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CompteDAO implements IDAO<Long, Compte> {
    @Override
    public void create(Compte object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public void update(Compte object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public void remove(Compte object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public Compte findById(Long aLong) throws SQLException, IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Compte> findAll() throws SQLException, IOException, ClassNotFoundException {
        return null;
    }
}
