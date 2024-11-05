package lk.ijse.dao;

import lk.ijse.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getDAOFactory() {
        return daoFactory == null ? daoFactory= new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        STUDENTDAO
    }

    public SuperDAO getDAO(DAOTypes daoTypes){

        switch (daoTypes){

            case STUDENTDAO:
                return  new StudentDAOImpl();
            default:
                return null;
        }
    }
}
