package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.LoginDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {
    @Override
    public String Checkcredential(String username) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "SELECT u.userPassword FROM User u WHERE u.userName = ?";

        try {
            Query query = session.createQuery(hql);
            query.setParameter("username", username);

            return (String) query.uniqueResult(); // Fetches the password
        } finally {
            session.close();
        }
    }
}
