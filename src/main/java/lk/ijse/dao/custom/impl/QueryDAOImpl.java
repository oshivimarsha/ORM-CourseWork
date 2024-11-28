package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.QueryDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public Object[] getProgramPaymentDetails(String studentId, String programId) {
        Session session = FactoryConfiguration.getInstance().getSession();

        try {
            // Native SQL Query
            String sql = "SELECT " +
                    "p.fee, " +
                    "r.payment, " +
                    "SUM(pay.upfrontpayment) " +
                    "FROM Register r " +
                    "JOIN Program p ON r.program_programId = p.programId " +
                    "JOIN Student s ON r.student_studentId = s.studentId " +
                    "JOIN Payment pay ON s.studentId = pay.student_studentId " +
                    "WHERE s.studentId = ? AND p.programName = ? " +
                    "GROUP BY p.fee, r.payment";

            Query<Object[]> query = session.createNativeQuery(sql, Object[].class);
            query.setParameter(1, studentId);
            query.setParameter(2, programId);

            Object[] result = query.uniqueResult();

            return result;
        } finally {
            session.close();
        }
    }
}
