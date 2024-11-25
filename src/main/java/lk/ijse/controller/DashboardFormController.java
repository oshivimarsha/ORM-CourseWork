package lk.ijse.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.DashboardBO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class DashboardFormController {

    public Label lblDate;
    public Label lblTime;
    public Label lblStudentCount;
    public Label lblCourseCount;
    public Label lblUserCount;


    private int studentCount;
    private int employeeCount;
    private int orderCount;

    DashboardBO dashboardBO = (DashboardBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DASHBOARDBO);

    public void initialize() {


        setDate();
        setTime();
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));
    }

    private void setTime() {
        LocalTime now = LocalTime.now();
        lblTime.setText(String.valueOf(now));
    }

    private void setStudentCount(int studentCount) {
        lblStudentCount.setText(String.valueOf(studentCount));
    }



}
