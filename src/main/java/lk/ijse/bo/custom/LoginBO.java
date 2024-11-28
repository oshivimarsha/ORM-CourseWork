package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    String Checkcredential(String username) throws SQLException,ClassNotFoundException;
}
