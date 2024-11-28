package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.LoginBO;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.entity.User;
import lk.ijse.util.PasswordUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginFormController {
    public TextField txtUserName;
    public PasswordField txtPassword;
    public JFXButton btnLogin;
    public AnchorPane rootNode;
    public TextField txtPasswordVisible;

    LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGINBO);
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USERBO);

    public void txtUserNameOnAction(ActionEvent actionEvent) {

    }

    public void txtPasswordOnAction(ActionEvent actionEvent) {

    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String userName = null;
        navigateToTheDashboard(userName);
        /*String userId = txtUserName.getText();
        String pw = txtPassword.getText();

        try {
           // if (isValied()) {
                checkCredential(userId, pw);
           // }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }*/
    }

    void navigatoT0AdminSidePanel() throws IOException {
        AnchorPane mainNode = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/sidepannel_form.fxml")));

        Scene scene = new Scene(mainNode);
        Stage stage = (Stage) rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Home Page");
    }

    void navigatoToCoordinatorSidePanel() throws IOException {
        AnchorPane mainNode = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/coodinatorSidepannel_form.fxml")));
        Scene scene = new Scene(mainNode);
        Stage stage = (Stage) rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Home Page");
    }

    private void checkCredential(String userName, String password) throws SQLException, IOException, ClassNotFoundException {
        String pass = loginBO.Checkcredential(userName);

        if (pass == null) {
            new Alert(Alert.AlertType.ERROR, "Sorry, user name not found!").show();
        }else {
            if (password.equals(pass)) {

                navigateToTheDashboard(userName);
            } else {
                new Alert(Alert.AlertType.ERROR, "Sorry, the password is incorrect!").show();
            }
        }
    }

    private void navigateToTheDashboard(String userName) throws IOException {
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


