package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.medClass;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class signUpController extends medClass {

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
    private Button cancelSignUp;

    @FXML
    void cancelSignUp(ActionEvent event) {


    }

    @FXML
    void signUpButton(ActionEvent event) {

    }

    @FXML
    void signUpCheckBoxFemale(ActionEvent event) {
        signUpCheckBoxMale.setSelected(false);
    }

    @FXML
    void signUpCheckBoxMale(ActionEvent event) {
        signUpCheckBoxFemale.setSelected(false);
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

        cancelSignUp.setOnAction(event ->{
            cancelSignUp.getScene().getWindow().hide();
            loadSceneStage("/sample/view/sample.fxml","FunParse");
        });
    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String login    = signUpLogin.getText().trim();
        String password = signUpPassword.getText().trim();
        String email    = signUpEmail.getText().trim();
        String city     = signUpCity.getText().trim();
        String gender   = "";

        if(signUpCheckBoxMale.isSelected()){
            gender = "Мужской";
        }else{
            gender = "Женский";
        }

        User user = new User(login,password,email,city,gender);

        dbHandler.dbSignUpUser(user);

    }

}
