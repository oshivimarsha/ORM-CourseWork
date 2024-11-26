package lk.ijse.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.RegisterDAO;
import lk.ijse.entity.Register;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class RegisterDAOImpl implements RegisterDAO {
    @Override
    public boolean saveRegister(List<Register> registerList, Session session) {
        try {
            for (Register register : registerList){
                boolean isSaved = save(register, session);
            }
            return true;
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            return false;
        }
    }

    public boolean save(Register register, Session session) {
        try {
            session.save(register);
            return true;
        } catch (HibernateException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            return false;
        }
    }

    @Override
    public int getCurrentId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        int id = 0;
        try {
            String hql = "SELECT r.registerId FROM Register r ORDER BY r.registerId DESC";
            Query query = session.createQuery(hql);
            query.setMaxResults(1); // Limit the result to only 1 item
            id = (Integer) query.uniqueResult();
            session.close();
            if(id != 0){
                System.out.println(id);
                return id;

            }

        } catch (HibernateException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        System.out.println(id);
        return id;
    }

    @Override
    public boolean save(Register dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Register dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Register search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
