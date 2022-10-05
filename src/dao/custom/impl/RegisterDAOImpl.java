package dao.custom.impl;

import dao.custom.RegisterDAO;
import entity.Register;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.List;

public class RegisterDAOImpl implements RegisterDAO {
    @Override
    public boolean add(Register entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Register entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public Register find(String s) throws Exception {
        return null;
    }

    @Override
    public List<Register> findAll() throws Exception {
        return null;
    }


    @Override
    public String getLastOrderId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        String i = (String) session.createNativeQuery("SELECT registerID FROM Register ORDER BY registerID DESC LIMIT 1")
                .uniqueResult();
        return (i == null) ? null : i;
    }

    @Override
    public boolean existsByCustomerId(String customerId) throws Exception {
        return false;
    }
}
