package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.ProgramDAO;
import lk.ijse.entity.Program;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramDAOImpl implements ProgramDAO {

    @Override
    public List<Program> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery quary = session.createNativeQuery("select * from Program");
        quary.addEntity(Program.class);
        ArrayList<Program> programs = (ArrayList<Program>) quary.list();

        transaction.commit();
        session.close();

        return programs;
    }

    @Override
    public Program searchByName(String name) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction trancation = session.beginTransaction();

        Query query = session.createQuery("from Program where programName = ?1");
        query.setParameter(1,name);
        Program program = (Program) query.uniqueResult();
        trancation.commit();
        //session.close();
        return program;
    }

    @Override
    public boolean save(Program program) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(program);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(Program program) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(program);

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

        NativeQuery delete = session.createNativeQuery("delete from Program where programId = ?1");
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

    public List<String> getProgramNamesByStudentId(String studentId) {
        Session session = FactoryConfiguration.getInstance().getSession();

        try {
            // HQL Query
            String hql = "SELECT r.program.programName " +
                    "FROM Register r " +
                    "WHERE r.student.studentId = :studentId";

            Query<String> query = session.createQuery(hql);
            query.setParameter("studentId", studentId);

            return query.getResultList();
        } finally {
            session.close();
        }

    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "SELECT MAX(CAST(SUBSTRING(p.programId, 3) AS integer)) FROM Program p";

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
    public Program search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
