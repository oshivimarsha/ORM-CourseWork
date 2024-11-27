package lk.ijse.dao;

import lk.ijse.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getDAOFactory() {
        return daoFactory == null ? daoFactory= new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        STUDENTDAO, PROGRAMDAO, USERDAO, DASHBOARDDAO, REGISTERDAO, LOGINDAO
    }

    public SuperDAO getDAO(DAOTypes daoTypes){

        switch (daoTypes){

            case STUDENTDAO:
                return new StudentDAOImpl();
            case PROGRAMDAO:
                return new ProgramDAOImpl();
            case USERDAO:
                return new UserDAOImpl();
            case REGISTERDAO:
                return new RegisterDAOImpl();
            case LOGINDAO:
                return new LoginDAOImpl();
            default:
                return null;
        }
    }
}
