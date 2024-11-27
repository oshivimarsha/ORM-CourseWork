package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.DashboardBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.StudentDAO;

import java.sql.SQLException;

public class DashboardBOImpl implements DashboardBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENTDAO);

    @Override
    public int getStudentCount() throws SQLException, ClassNotFoundException {
        return studentDAO.getStudentCount();
    }
}
