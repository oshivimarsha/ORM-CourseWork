package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery quary = session.createNativeQuery("select * from User");
        quary.addEntity(User.class);
        ArrayList<User> users = (ArrayList<User>) quary.list();

        transaction.commit();
        session.close();

        return users;
    }

    @Override
    public int getUserCount() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "SELECT COUNT(*) FROM User ";

        try {
            Query query = session.createQuery(hql);
            Long count = (Long) query.uniqueResult();
            return count != null ? count.intValue() : 0;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean save(User user) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(user);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(User user) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(user);

            transaction.commit();
            session.close();

            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery delete = session.createNativeQuery("delete from User where UserId = ?1");
        delete.setParameter(1,id);

        int result = delete.executeUpdate();
        boolean re;

        if(result<=1){
            re = true;
        }else {
            re = false;
        }

        transaction.commit();
        session.close();

        return re;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "SELECT MAX(CAST(SUBSTRING(u.userId, 3) AS integer)) FROM User u";

        Query query = session.createQuery(hql);
        Integer id = (Integer) query.uniqueResult();
        if(id == null){
            return null;
        }else {
            String currentId = id.toString();
            System.out.println(currentId);
            return currentId;
        }
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
