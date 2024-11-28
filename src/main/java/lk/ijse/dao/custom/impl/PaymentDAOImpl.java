package lk.ijse.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.PaymentDAO;
import lk.ijse.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean savePayment(Payment entity, Session session) {
        try {
            session.save(entity);
            return true;
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return false;
        }
    }

    @Override
    public boolean save(Payment dto) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Object payment = session.save(dto);
        System.out.println(payment);

        if (payment != null) {
            transaction.commit();
            session.close();
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean update(Payment dto) throws SQLException, ClassNotFoundException {
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
    public Payment search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
