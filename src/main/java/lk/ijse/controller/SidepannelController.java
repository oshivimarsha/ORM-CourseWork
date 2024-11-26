package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SidepannelController {
    public AnchorPane rootNode;
    public Label lblUserName;
    public AnchorPane childRootNode;

    public void setUserName(String userName) {
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


    public void initialize() throws IOException {
        loadDashBoard();
    }

    private void loadDashBoard() throws IOException {
        AnchorPane dashPane = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));
        childRootNode.getChildren().clear();
        childRootNode.getChildren().add(dashPane);
    }

    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        AnchorPane dashboardPane = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));
        childRootNode.getChildren().clear();
        childRootNode.getChildren().add(dashboardPane);
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

    public void btnUser(ActionEvent actionEvent) throws IOException {
        AnchorPane userPane = FXMLLoader.load(this.getClass().getResource("/view/user_form.fxml"));
        childRootNode.getChildren().clear();
        childRootNode.getChildren().add(userPane);
    }

}
