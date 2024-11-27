package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

import java.sql.SQLException;

public interface DashboardBO extends SuperBO {
    int getStudentCount() throws SQLException, ClassNotFoundException;

    int getProgramCount() throws SQLException, ClassNotFoundException;
    int getUserCount() throws SQLException, ClassNotFoundException;

}
