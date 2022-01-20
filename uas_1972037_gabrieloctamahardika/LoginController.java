package com.gabriel.uas_1972037_gabrieloctamahardika;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author 1972037 Gabriel Octa Mahardika
 **/
public class LoginController implements Initializable {

    public TextField txtId;
    public PasswordField txtPassword;
    public Button btnLogin;

    public void loginAction(ActionEvent actionEvent) throws IOException {
        if(txtId.getText().trim().isEmpty() || txtPassword.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Tolong isi semua field");
            alert.showAndWait();
        }else {
            if (txtId.getText().equals("1972037") && txtPassword.getText().equals("gabriel1234")){
                Stage new_stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
                Parent root = fxmlLoader.load();
                com.gabriel.uas_1972037_gabrieloctamahardika.MainController mainController = fxmlLoader.getController();
                mainController.setLoginController(this);
                Scene new_scene = new Scene(root);
                new_stage.setScene(new_scene);
                new_stage.initModality(Modality.WINDOW_MODAL);
                new_stage.setTitle("Main Form");
                new_stage.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Password or Id Invalid");
                alert.showAndWait();
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
