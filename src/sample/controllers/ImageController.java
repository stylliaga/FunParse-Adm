package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.DatabaseHandler;

public class ImageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane paneViewImage;

    @FXML
    private ImageView showNextImage;

    @FXML
    private Button previousImageButton;

    @FXML
    private Button netxImageButton;

    @FXML
    private Button downloadFileButton;

    @FXML
    private Button saveFolder;

    @FXML
    private AnchorPane paneSettings;

    @FXML
    private RadioButton sortImagesByDate;

    @FXML
    private RadioButton sortImagesByPopular;

    @FXML
    private CheckBox saveImageOnComputer;

    @FXML
    private CheckBox searchOnlyAdultImages;

    @FXML
    private PasswordField inputOldPassword;

    @FXML
    private PasswordField inputNewPassword;

    @FXML
    private PasswordField inputNewPassword2;

    @FXML
    private ChoiceBox<?> chooseLanguage;

    @FXML
    private ChoiceBox<?> urlSiteToParse;

    @FXML
    private Button saveSettings;

    @FXML
    void downloadFileButton(ActionEvent event) {

    }

    @FXML
    void inputNewPassword(ActionEvent event) {

    }

    @FXML
    void inputNewPassword2(ActionEvent event) {

    }

    @FXML
    void inputOldPassword(ActionEvent event) {

    }

    @FXML
    void netxImageButton(ActionEvent event) {

    }

    @FXML
    void previousImageButton(ActionEvent event) {

    }

    @FXML
    void saveFolder(ActionEvent event) {

    }

    @FXML
    void saveImageOnComputer(ActionEvent event) {

    }

    @FXML
    void searchOnlyAdultImages(ActionEvent event) {

    }

    @FXML
    void sortImagesByDate(ActionEvent event) {

    }

    @FXML
    void sortImagesByPopular(ActionEvent event) {

    }

    @FXML
    void saveSettings(ActionEvent event) {

    }

    @FXML
    void initialize() {

        DatabaseHandler dbHandler = new DatabaseHandler();

        saveSettings.setOnAction(event -> {
            //dbHandler.signUpUser();
        });
    }
}
