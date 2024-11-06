package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.CourseDAO;
import lk.ijse.entity.Course;
import lk.ijse.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    @Override
    public List<Course> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery quary = session.createNativeQuery("select * from Course");
        quary.addEntity(Course.class);
        ArrayList<Course> courses = (ArrayList<Course>) quary.list();

        transaction.commit();
        session.close();

        return courses;
    }

    @Override
    public boolean save(Course course) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(course);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(Course course) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(course);

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

        NativeQuery delete = session.createNativeQuery("delete from Course where courseId = ?1");
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
        return null;
    }

    @Override
    public Course search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
