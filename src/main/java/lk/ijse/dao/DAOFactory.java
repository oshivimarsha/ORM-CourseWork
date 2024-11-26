package lk.ijse.dao;

import lk.ijse.dao.custom.impl.ProgramDAOImpl;
import lk.ijse.dao.custom.impl.RegisterDAOImpl;
import lk.ijse.dao.custom.impl.StudentDAOImpl;
import lk.ijse.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getDAOFactory() {
        return daoFactory == null ? daoFactory= new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        STUDENTDAO, PROGRAMDAO, USERDAO, DASHBOARDDAO, REGISTERDAO
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
            default:
                return null;
        }
    }
}
