package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateFormController {
    public TextField txtUpdateUserName;
    public PasswordField txtUpdatePassword;
    public PasswordField txtUpdateEmail;
    public PasswordField txtUpdatePosition;
    public AnchorPane rootNode;

    public void btnUpdate(ActionEvent actionEvent) {

    }

    public void btnBackToPreview(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Form");
    }
}
