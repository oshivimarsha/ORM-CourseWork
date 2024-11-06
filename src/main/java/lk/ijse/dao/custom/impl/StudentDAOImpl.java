package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public List<Student> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery quary = session.createNativeQuery("select * from Student");
        quary.addEntity(Student.class);
        ArrayList<Student> students = (ArrayList<Student>) quary.list();

        transaction.commit();
        session.close();

        return students;
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
    public boolean update(Student student) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(student);

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

        NativeQuery delete = session.createNativeQuery("delete from Student where studentId = ?1");
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
    public String generateNewID() {
        /*Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Object query3 = session.createNativeQuery("SELECT studentId FROM Student ORDER BY CAST(SUBSTRING(studentId, 2) AS UNSIGNED) DESC LIMIT 1").uniqueResult();

        if (query3 != null) {
            String id = query3.toString();
            String[] split = id.split("C");
            int idNum = Integer.parseInt(split[1]);

            if(idNum >= 1){
                return "C" + 0 + 0 + ++idNum;
            }else if(idNum >= 9){
                return "C" + 0 + ++idNum;
            } else if(idNum >= 99){
                return "C" + ++idNum;
            }
        }
        transaction.commit();
        session.close();
        return "C001";*/

        return null;
    }

    @Override
    public Student search(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createNativeQuery("from Student where studentId = ?1");
        query.addEntity(Student.class);
        query.setParameter(1, id);

        Student student = (Student) query.uniqueResult();

        transaction.commit();
        session.close();

        return student;
    }
}
