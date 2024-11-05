package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.dto.StudentDTO;

import java.io.File;
import java.sql.Date;

public class StudentFormController {
    public TextField txtId;
    public JFXButton btnSave;
    public TextField txtEmail;
    public DatePicker txtDOB;
    public TextField txtFullName;
    public TextField txtContact;
    public TextField txtAddress;
    public TextField txtAge;
    public TextField txtNIC;
    public ComboBox<String> cmbGender;
    public ImageView imageView;
    public AnchorPane main_form;
    public Image image;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENTBO);

    public void initialize() {
        getCurrentId();

        ObservableList<String> list = FXCollections.observableArrayList("Male", "Female");
        cmbGender.setItems(list);
    }

    public void cmbGenderOnAction(ActionEvent actionEvent) {
        String s = cmbGender.getSelectionModel().getSelectedItem();
    }

    public void txtIdOnAction(ActionEvent actionEvent) {

    }

    private String getCurrentId() {
        String nextId = "";

        try {

            nextId = studentBO.generateNewID();
            txtId.setText(nextId);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nextId;
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtFullName.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        String nic = txtNIC.getText();
        String address = txtAddress.getText();
        java.sql.Date dob = Date.valueOf(txtDOB.getValue());
        String age = txtAge.getText();
        String gender = (String) cmbGender.getValue();
        String path = image.getUrl();

        StudentDTO student = new StudentDTO(id, name, email, contact, nic, address, dob, age, gender, path);

        try {
            boolean isSaved = studentBO.saveStudent(student);//CustomerRepo.save(customer);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "student saved!").show();
               // clearFields();
                initialize();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION, "Wrong Input!").show();
        }
    }

    public void btnChooseFile(ActionEvent actionEvent) {
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", new String[]{"*png", "*jpg"}));

        File file = openFile.showOpenDialog(main_form.getScene().getWindow());
        if (file != null) {

            image = new Image(file.toURI().toString(), 120.0, 127.0, false, true);
            imageView.setImage(image);
        }
    }
}
