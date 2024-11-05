package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.view.StudentTm;

import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public JFXButton btnUpdate;
    public JFXButton btnClear;
    public JFXButton btnDelete;
    public TableView<StudentTm> tblStudentCart;
    public TableColumn<?, ?> colStudentId;
    public TableColumn<?, ?> colStudentName;
    public TableColumn<?, ?> colStudentEmail;
    public TableColumn<?, ?> colStudentContact;
    public TableColumn<?, ?> colStudentNIC;
    public TableColumn<?, ?> colStudentAddress;
    public TableColumn<?, ?> colStudentDOB;
    public TableColumn<?, ?> colStudentAge;
    public TableColumn<?, ?> colStudentGender;
    public TextField txtSearchHere;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENTBO);

    public void initialize() {
        loadAllEmployees();
        setCellValueFactory();
        getCurrentId();

        ObservableList<String> list = FXCollections.observableArrayList("Male", "Female");
        cmbGender.setItems(list);
    }

    private void loadAllEmployees() {
        ObservableList<StudentTm> obList = FXCollections.observableArrayList();

        try {
            List<StudentDTO> studentList = studentBO.getAllStudent();
            for (StudentDTO student : studentList) {
                StudentTm tm = new StudentTm(
                        student.getStudentId(),
                        student.getStudentFullName(),
                        student.getStudentEmail(),
                        student.getStudentContact(),
                        student.getStudentNIC(),
                        student.getStudentAddress(),
                        student.getStudentDOB(),
                        student.getStudentAge(),
                        student.getStudentGender(),
                        student.getStudentPath()

                );

                obList.add(tm);
            }

            tblStudentCart.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentFullName"));
        colStudentEmail.setCellValueFactory(new PropertyValueFactory<>("studentEmail"));
        colStudentContact.setCellValueFactory(new PropertyValueFactory<>("studentContact"));
        colStudentNIC.setCellValueFactory(new PropertyValueFactory<>("studentNIC"));
        colStudentAddress.setCellValueFactory(new PropertyValueFactory<>("studentAddress"));
        colStudentDOB.setCellValueFactory(new PropertyValueFactory<>("studentDOB"));
        colStudentAge.setCellValueFactory(new PropertyValueFactory<>("studentAge"));
        colStudentGender.setCellValueFactory(new PropertyValueFactory<>("studentGender"));

    }

    public void cmbGenderOnAction(ActionEvent actionEvent) {
        String s = cmbGender.getSelectionModel().getSelectedItem().toString();
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
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Save Student?", yes, no).showAndWait();

        if (type.orElse(no) == yes) {
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
                    clearFields();
                    initialize();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.CONFIRMATION, "Wrong Input!").show();
            }
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

    public void txtIdOnAction(ActionEvent actionEvent) {

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Update Student?", yes, no).showAndWait();

        if (type.orElse(no) == yes) {
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
                boolean isUpdated = studentBO.updateStudent(student);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "student updated!").show();
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

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Delete Student?", yes, no).showAndWait();

        if (type.orElse(no) == yes) {
            String id = txtId.getText();

            try {
                boolean isDeleted = studentBO.deleteStudent(id);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "student deleted!").show();
                    clearFields();
                    initialize();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    public void clearFields() {
        txtId.setText("");
        txtFullName.setText("");
        txtEmail.setText("");
        txtContact.setText("");
        txtNIC.setText("");
        txtAddress.setText("");
        txtDOB.setValue(null);
        txtAge.setText("");
        cmbGender.setValue("");
        imageView.setImage(null);
    }
    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }


    public void txtSearchHereOnAction(ActionEvent actionEvent) {
        try {
            String id = txtId.getText();

            StudentDTO student = studentBO.searchStudent(id);

            if (student != null) {
                txtId.setText(student.getStudentId());
                txtFullName.setText(student.getStudentFullName());
                txtEmail.setText(student.getStudentEmail());
                txtContact.setText(student.getStudentContact());
                txtNIC.setText(student.getStudentNIC());
                txtAddress.setText(student.getStudentAddress());
                txtDOB.setValue(LocalDate.parse(student.getStudentDOB().toString()));
                txtAge.setText(student.getStudentAge());
                cmbGender.setValue(student.getStudentGender());
                Image image = new Image(student.getStudentPath());
                imageView.setImage(image);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Student not found!").show();
            }
        }catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, "Student not found!").show();
            throw new RuntimeException(e);
        }
    }
}
