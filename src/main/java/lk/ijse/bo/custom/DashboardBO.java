package lk.ijse.bo.custom;

import javafx.scene.chart.XYChart;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CustomDTO;

import java.sql.SQLException;
import java.util.List;

public interface DashboardBO extends SuperBO {
    int getStudentCount() throws SQLException, ClassNotFoundException;
    int getProgramCount() throws SQLException, ClassNotFoundException;
    int getUserCount() throws SQLException, ClassNotFoundException;
    //XYChart.Series getLinChart() throws SQLException, ClassNotFoundException;
    List<CustomDTO> getMostProgram() throws SQLException,ClassNotFoundException;
}
