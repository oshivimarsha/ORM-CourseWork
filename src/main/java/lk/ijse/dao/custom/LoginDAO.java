package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;

import java.sql.SQLException;

public interface LoginDAO extends SuperDAO {
    String Checkcredential(String username) throws SQLException,ClassNotFoundException;
}
