package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "SELECT MAX(CAST(SUBSTRING(s.studentId, 3) AS integer)) FROM Student s";

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
    public Student searchId(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction trancation = session.beginTransaction();

        Query query = session.createQuery("from Student where studentId = ?1");
        query.setParameter(1,id);
        Student student = (Student)query.uniqueResult();
        trancation.commit();
        //session.close();
        return student;
    }

    @Override
    public int getStudentCount() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "SELECT COUNT(*) FROM Student";

        try {
            Query query = session.createQuery(hql);
            Long count = (Long) query.uniqueResult(); // COUNT returns a Long in Hibernate
            return count != null ? count.intValue() : 0; // Convert Long to int
        } finally {
            session.close(); // Ensure session is closed to prevent memory leaks
        }
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
