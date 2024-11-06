package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.CourseDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.dto.UserDTO;
import lk.ijse.view.UserTm;

import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserFormController {

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colUserEmail;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private TableColumn<?, ?> colUserPosition;

    @FXML
    private ImageView imageView;

    private Image image;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TableView<UserTm> tblUserCart;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPosition;


    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USERBO);


    public void initialize() {
        loadAllUser();
        setCellValueFactory();
        // getCurrentId();

    }


    private void loadAllUser() {
        ObservableList<UserTm> obList = FXCollections.observableArrayList();

        try {
            List<UserDTO> userList = userBO.getAllUser();
            for (UserDTO user : userList) {
                UserTm tm = new UserTm(
                        user.getUserId(),
                        user.getUserName(),
                        user.getUserEmail(),
                        user.getUserPosition(),
                        user.getUserPassword()
                );

                obList.add(tm);
            }

            tblUserCart.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colUserEmail.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        colUserPosition.setCellValueFactory(new PropertyValueFactory<>("userPosition"));
    }


    @FXML
    void btnChooseFile(ActionEvent event) {
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", new String[]{"*png", "*jpg"}));

        File file = openFile.showOpenDialog(main_form.getScene().getWindow());
        if (file != null) {

            image = new Image(file.toURI().toString(), 120.0, 127.0, false, true);
            imageView.setImage(image);
        }
    }

    public void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtPosition.setText("");
        txtPassword.setText("");
        imageView.setImage(null);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Delete User?", yes, no).showAndWait();

        if (type.orElse(no) == yes) {
            String id = txtId.getText();

            try {
                boolean isDeleted = userBO.deleteUser(id);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "user deleted!").show();
                    clearFields();
                    initialize();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        System.out.println("1");
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Save User?", yes, no).showAndWait();

        if (type.orElse(no) == yes) {
            String id = txtId.getText();
            String name = txtName.getText();
            String email = txtEmail.getText();
            String position = txtPosition.getText();
           // String password = txtPassword.getText();
            String path = image.getUrl();

            System.out.println("2");


            UserDTO user = new UserDTO(id, name, email, position, path);
            System.out.println("3");

            try {
                System.out.println("10");
                boolean isSaved = userBO.saveUser(user);
                System.out.println("4");
                if (isSaved) {
                    System.out.println("5");
                    new Alert(Alert.AlertType.CONFIRMATION, "user saved!").show();
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

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Update User?", yes, no).showAndWait();

        if (type.orElse(no) == yes) {
            String id = txtId.getText();
            String name = txtName.getText();
            String email = txtEmail.getText();
            String position = txtPosition.getText();
            // String password = txtPassword.getText();
            String path = image.getUrl();

            UserDTO user = new UserDTO(id, name, email, position, path);

            try {
                boolean isUpdated = userBO.updateUser(user);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "user updated!").show();
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

}
