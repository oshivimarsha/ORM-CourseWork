package lk.ijse.dao;

import lk.ijse.dao.custom.impl.CourseDAOImpl;
import lk.ijse.dao.custom.impl.StudentDAOImpl;
import lk.ijse.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getDAOFactory() {
        return daoFactory == null ? daoFactory= new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        STUDENTDAO, COURSEDAO, USERDAO
    }

    public SuperDAO getDAO(DAOTypes daoTypes){

        switch (daoTypes){

            case STUDENTDAO:
                return new StudentDAOImpl();
            case COURSEDAO:
                return new CourseDAOImpl();
            case USERDAO:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}
