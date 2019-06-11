package sample.controllers;

import java.net.DatagramPacket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.controllers.ImageController;
import sample.DatabaseHandler;

public class signUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField signUpLogin;

    @FXML
    private TextField signUpEmail;

    @FXML
    private TextField signUpCity;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private CheckBox signUpCheckBoxMale;

    @FXML
    private CheckBox signUpCheckBoxFemale;

    @FXML
    private Button signUpButton;

    @FXML
    void signUpButton(ActionEvent event) {

    }

    @FXML
    void signUpCheckBoxFemale(ActionEvent event) {

    }

    @FXML
    void signUpCheckBoxMale(ActionEvent event) {

    }

    @FXML
    void signUpCity(ActionEvent event) {

    }

    @FXML
    void signUpEmail(ActionEvent event) {

    }

    @FXML
    void signUpLogin(ActionEvent event) {

    }

    @FXML
    void signUpPassword(ActionEvent event) {

    }

    @FXML
    void initialize() {

        signUpButton.setOnAction(event -> {
            signUpNewUser();
        });
    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String login    = signUpLogin.getText();
        String password = signUpPassword.getText();
        String email    = signUpEmail.getText();
        String city     = signUpCity.getText();
        String gender   = "";

        if(signUpCheckBoxMale.isSelected()){
            gender = "Мужской";
        }else{
            gender = "Женский";
        }

        User user = new User(login,password,email,city,gender);

        dbHandler.signUpUser(user);

    }
}
