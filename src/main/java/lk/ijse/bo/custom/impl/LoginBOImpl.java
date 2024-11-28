package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.LoginBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.LoginDAO;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    LoginDAO loginDAO = (LoginDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.LOGINDAO);

    @Override
    public String Checkcredential(String username) throws SQLException, ClassNotFoundException {
        return loginDAO.Checkcredential(username);
    }
}
