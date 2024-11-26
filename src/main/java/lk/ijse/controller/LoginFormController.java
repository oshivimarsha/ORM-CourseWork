package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public TextField txtUserName;
    public PasswordField txtPassword;
    public JFXButton btnLogin;
    public AnchorPane rootNode;
    public TextField txtPasswordVisible;

    public void txtUserNameOnAction(ActionEvent actionEvent) {

    }

    public void txtPasswordOnAction(ActionEvent actionEvent) {

    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String userName = txtUserName.getText();
        String pw = txtPassword.getText();

        navigateToTheDashboard(userName, actionEvent);
    }

    private void navigateToTheDashboard(String userName, ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/sidepannel_form.fxml"));
        Parent dashboardRoot = loader.load();
        SidepannelController controller = loader.getController();
        controller.setUserName(userName); // Pass the username to the DashboardFormController
        Scene scene = new Scene(dashboardRoot);
        Stage stage = (Stage) rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    public void linkRegistrationOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/signup_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("SignUp Form");
    }

    public void linkUpdateOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/update_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Update Username Password Form");
    }

    public void togglePasswordVisibility(MouseEvent mouseEvent) {
        if (txtPasswordVisible.isVisible()) {
            // Hide the visible TextField and show the PasswordField
            txtPasswordVisible.setVisible(false);
            txtPasswordVisible.setManaged(false);
            txtPassword.setVisible(true);
            txtPassword.setManaged(true);

            // Copy text back to the PasswordField
            txtPassword.setText(txtPasswordVisible.getText());
        } else {
            // Show the visible TextField and hide the PasswordField
            txtPasswordVisible.setVisible(true);
            txtPasswordVisible.setManaged(true);
            txtPassword.setVisible(false);
            txtPassword.setManaged(false);

            // Copy text to the visible TextField
            txtPasswordVisible.setText(txtPassword.getText());
        }
    }
}


