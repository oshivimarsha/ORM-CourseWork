package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CourseBO;
import lk.ijse.bo.custom.RegisterBO;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.dto.CourseDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.view.CartTm;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RegistrationFormController {
    public TextField txtStudentId;
    public TextField txtStudentName;
    public AnchorPane imageRootNode;
    public ImageView imageView;
    public TextField txtStudentTel;
    public Label lblDate;
    public Label lblTime;
    public JFXComboBox<String> cmbCourseNames;
    public Label lblRegisterId;
    public TextField txtCourseId;
    public Label lblCourseFee;
    public TextField txtPayment;
    public TableColumn<?,?> colCourseId;
    public TableColumn<?,?> colCourseName;
    public TableColumn<?,?> colCourseFee;
    public TableColumn<?,?> colAmount;
    public TableColumn<?,?> colPay;
    public TableView<CartTm> tblRegistrationCart;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENTBO);
    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSEBO);
    RegisterBO registerBO = (RegisterBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REJISTERBO);
    ObservableList<CartTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        getCourseNames();
        setCellValueFactory();
        getCurrentRegisterId();
        setDate();
        setTime();
    }

    private void setCellValueFactory() {
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCourseFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colPay.setCellValueFactory(new PropertyValueFactory<>("pay"));
    }

    private void getCurrentRegisterId() {
        try {
            int currentId = registerBO.getCurrentRegisterId();
            String nextOrderId = generateNextOrderId(currentId);
            lblRegisterId.setText(nextOrderId);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private String generateNextOrderId(int currentId) {
        if(currentId != 0) {
            int idNum = currentId;
            System.out.println(idNum);
            return "Reg-0" + ++idNum;
        }
        return "Reg-01";
    }

    private void getCourseNames() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<CourseDTO> courseList = courseBO.getAllCourse();
            List<String> courseNameList = new ArrayList<>();

            for (CourseDTO courseDTO : courseList){
                courseNameList.add(courseDTO.getCourseName());
            }
            for (String name: courseNameList) {
                obList.add(name);
            }
            cmbCourseNames.setItems(obList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    public void txtStudentIdOnAction(ActionEvent actionEvent) {
        String id = txtStudentId.getText();
        try {
            StudentDTO student = studentBO.searchStudent(id);
            if (student != null) {
                txtStudentName.setText(student.getStudentFullName());
                Image image = new Image(student.getStudentPath());
                imageView.setImage(image);
                txtStudentTel.setText(student.getStudentContact());


            }else{
                cmbCourseNames.requestFocus();
                new Alert(Alert.AlertType.INFORMATION, "student not found!").show();
            }


        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void txtCourseDurationOnAction(ActionEvent actionEvent) {

    }

    public void txtCourseIdOnAction(ActionEvent actionEvent) {

    }

    public void txtCourseNameOnAction(ActionEvent actionEvent) {

    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));
    }

    private void setTime() {
        LocalTime now = LocalTime.now();
        lblTime.setText(String.valueOf(now));
    }

    public void cmbCourseNameOnAction(ActionEvent actionEvent) {
        String name = cmbCourseNames.getValue();
        try {
            CourseDTO course = courseBO.searchCourseByName(name);
            if (course != null) {
                txtCourseId.setText(course.getCourseId());
                lblCourseFee.setText(String.valueOf(course.getCourseFee()));
                txtPayment.requestFocus();
            }

            txtPayment.requestFocus();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String cId = txtCourseId.getText();
        String cName = cmbCourseNames.getValue();
        double fee = Double.parseDouble(lblCourseFee.getText());
        double payment = Double.parseDouble(txtPayment.getText());
        double pay = fee - payment;


        CartTm tm = new CartTm(cId, cName, fee, payment, pay);
        obList.add(tm);
        tblRegistrationCart.setItems(obList);
    }
}
