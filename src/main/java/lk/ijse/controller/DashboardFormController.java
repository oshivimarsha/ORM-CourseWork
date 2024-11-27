package lk.ijse.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.DashboardBO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class DashboardFormController {
    public Label lblStudentCount;
    public Label lblUserCount;
    public Label lblProgramCount;


    private int studentCount;
    private int programCount;
    private int userCount;

    DashboardBO dashboardBO = (DashboardBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DASHBOARDBO);

    public void initialize() {
        try {
            studentCount = dashboardBO.getStudentCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setStudentCount(studentCount);


        try {
            programCount = dashboardBO.getProgramCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setProgramCount(programCount);


        try {
            userCount = dashboardBO.getUserCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setUserCount(userCount);
    }



    private void setStudentCount(int studentCount) {
        lblStudentCount.setText(String.valueOf(studentCount));
    }

    private void setProgramCount(int programCount) {
        lblProgramCount.setText(String.valueOf(programCount));
    }

    private void setUserCount(int userCount) {
        lblUserCount.setText(String.valueOf(userCount));
    }



}
