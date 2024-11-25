package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.time.LocalTime;

public class RejisterFormController {

    public Label lblRegisterTime;
    public Label lblRegisterDate;
    @FXML
    private ImageView imageView;

    @FXML
    private Label lblCourseFee;

    @FXML
    private Label lblCourseId;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblRejisterId;

    @FXML
    private Label lblStudentNIC;

    @FXML
    private Label lblStudentName;

    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtCourseName;

    @FXML
    private TextField txtStudentId;


    public void initialize() {
        setDate();
        setTime();
    }


    @FXML
    void txtCourseNameOnAction(ActionEvent event) {

    }

    @FXML
    void txtStudentIdOnAction(ActionEvent event) {

    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblRegisterDate.setText(String.valueOf(now));
    }

    private void setTime() {
        LocalTime now = LocalTime.now();
        lblRegisterTime.setText(String.valueOf(now));
    }

}
