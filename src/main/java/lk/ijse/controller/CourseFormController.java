package lk.ijse.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CourseBO;
import lk.ijse.dto.CourseDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.view.CourseTm;
import lk.ijse.view.StudentTm;

import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CourseFormController {
    @FXML
    public TableView<CourseTm> tblCourseCart;
    @FXML
    public TableColumn<?, ?> colCourseId;
    @FXML
    public TableColumn<?, ?> colCourseName;
    @FXML
    public TableColumn<?, ?> colCourseDuration;
    @FXML
    public TableColumn<?, ?> colCourseFee;

    @FXML
    private TextField txtCourseDuration;

    @FXML
    private TextField txtCourseFee;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtCourseName;


    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSEBO);

    public void initialize() {
        loadAllCourse();
        setCellValueFactory();
       // getCurrentId();

    }

    private void loadAllCourse() {
        ObservableList<CourseTm> obList = FXCollections.observableArrayList();

        try {
            List<CourseDTO> courseList = courseBO.getAllCourse();
            for (CourseDTO course : courseList) {
                CourseTm tm = new CourseTm(
                        course.getCourseId(),
                        course.getCourseName(),
                        course.getCourseDuration(),
                        course.getCourseFee()
                );

                obList.add(tm);
            }

            tblCourseCart.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colCourseDuration.setCellValueFactory(new PropertyValueFactory<>("courseDuration"));
        colCourseFee.setCellValueFactory(new PropertyValueFactory<>("courseFee"));
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        System.out.println("1");
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Save Course?", yes, no).showAndWait();

        if (type.orElse(no) == yes) {
        String cId = txtCourseId.getText();
        String cName = txtCourseName.getText();
        String cDuration = txtCourseDuration.getText();
        String cFee = txtCourseFee.getText();

            System.out.println("2");


            CourseDTO course = new CourseDTO(cId, cName, cDuration, cFee);
            System.out.println("3");

            try {
                System.out.println("10");
                boolean isSaved = courseBO.saveCourse(course);
                System.out.println("4");
                if (isSaved) {
                    System.out.println("5");
                    new Alert(Alert.AlertType.CONFIRMATION, "course saved!").show();
                    System.out.println("6");
                    clearFields();
                    initialize();
                    System.out.println("7");
                }
            } catch (Exception e) {
                System.out.println("8");
                new Alert(Alert.AlertType.CONFIRMATION, "Wrong Input!").show();
                System.out.println("9");
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Update Course?", yes, no).showAndWait();

        if (type.orElse(no) == yes) {
            String cId = txtCourseId.getText();
            String cName = txtCourseName.getText();
            String cDuration = txtCourseDuration.getText();
            String cFee = txtCourseFee.getText();

            CourseDTO course = new CourseDTO(cId, cName, cDuration, cFee);

            try {
                boolean isUpdated = courseBO.updateCourse(course);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "course updated!").show();
                    clearFields();
                    initialize();
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "The data you entered is incorrect").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    public void clearFields() {
        txtCourseId.setText("");
        txtCourseName.setText("");
        txtCourseDuration.setText("");
        txtCourseFee.setText("");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Delete Course?", yes, no).showAndWait();

        if (type.orElse(no) == yes) {
            String id = txtCourseId.getText();

            try {
                boolean isDeleted = courseBO.deleteCourse(id);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "course deleted!").show();
                    clearFields();
                    initialize();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    @FXML
    void txtCourseDurationOnAction(ActionEvent event) {

    }

    @FXML
    void txtCourseFeeOnAction(ActionEvent event) {

    }

    @FXML
    void txtCourseIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtCourseNameOnAction(ActionEvent event) {

    }
}
