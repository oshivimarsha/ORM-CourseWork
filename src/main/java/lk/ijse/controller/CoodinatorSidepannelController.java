package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class CoodinatorSidepannelController {
    public AnchorPane rootNode;
    public Label lblUserName;
    public AnchorPane childRootNode;
    public Label lblDate;
    public Label lblTime;


    public void initialize() throws IOException {
        loadDashBoard();
        setDate();
        setTime();
    }
    private void loadDashBoard() throws IOException {
        AnchorPane dashPane = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));
        childRootNode.getChildren().clear();
        childRootNode.getChildren().add(dashPane);
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));
    }

    private void setTime() {
        LocalTime now = LocalTime.now();
        lblTime.setText(String.valueOf(now));
    }

    public void setUserName(String userName){
        lblUserName.setText(userName);
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Form");
    }

    public void btnStudentOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane studentPane = FXMLLoader.load(this.getClass().getResource("/view/student_form.fxml"));
        childRootNode.getChildren().clear();
        childRootNode.getChildren().add(studentPane);
    }

    public void btnCourse(ActionEvent actionEvent) throws IOException {
        AnchorPane coursePane = FXMLLoader.load(this.getClass().getResource("/view/program_form.fxml"));
        childRootNode.getChildren().clear();
        childRootNode.getChildren().add(coursePane);
    }

    public void btnRejister(ActionEvent actionEvent) throws IOException {
        AnchorPane registerPane = FXMLLoader.load(this.getClass().getResource("/view/registration_form.fxml"));
        childRootNode.getChildren().clear();
        childRootNode.getChildren().add(registerPane);
    }

    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        AnchorPane dashPane = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));
        childRootNode.getChildren().clear();
        childRootNode.getChildren().add(dashPane);
    }

    public void btnPayment(ActionEvent actionEvent) throws IOException {
        AnchorPane paymentPane = FXMLLoader.load(this.getClass().getResource("/view/payment_form.fxml"));
        childRootNode.getChildren().clear();
        childRootNode.getChildren().add(paymentPane);
    }
}
