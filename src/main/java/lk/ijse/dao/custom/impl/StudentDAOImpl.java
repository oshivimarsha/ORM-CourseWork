package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public List<Student> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Student student) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(student);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(Student dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() {
        /*Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Object query = session.createNativeQuery("SELECT cid FROM customer ORDER BY CAST(SUBSTRING(cid, 2) AS UNSIGNED) DESC LIMIT 1").uniqueResult();
        String nextId = "";

        if (query != null) {
            String id = query.toString();

            String[] split = id.split("C");

            int idNum = Integer.parseInt(split[1]);

            nextId = "C" + String.format("%03d", ++idNum);
        }else {
            nextId = "C001";
        }

        transaction.commit();
        session.close();

        return nextId;
*/
        return null;
    }
}
