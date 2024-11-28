package lk.ijse.bo.custom.impl;

import javafx.scene.chart.XYChart;
import lk.ijse.bo.custom.DashboardBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ProgramDAO;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.CustomDTO;
import lk.ijse.entity.Custom;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DashboardBOImpl implements DashboardBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENTDAO);
    ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAMDAO);
    UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USERDAO);

    @Override
    public int getStudentCount() throws SQLException, ClassNotFoundException {
        return studentDAO.getStudentCount();
    }

    @Override
    public int getProgramCount() throws SQLException, ClassNotFoundException {
        return programDAO.getProgramCount();
    }

    @Override
    public int getUserCount() throws SQLException, ClassNotFoundException {
        return userDAO.getUserCount();
    }

    @Override
    public List<CustomDTO> getMostProgram() throws SQLException, ClassNotFoundException {
        return null;
    }
}
