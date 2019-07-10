package sample.controllers;

import java.io.File;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import sample.controllers.Controller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import sample.DatabaseHandler;

import javax.swing.*;

public class ImageController {

    private String folderOnComp;
    //private String login;


    //public String getLogin(){
    ///    return login;
    //}


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
    private AnchorPane paneSettings;

    @FXML
    private RadioButton sortImagesByDate;

    @FXML
    private RadioButton sortImagesByPopular;

    @FXML
    private CheckBox saveImageOnComputer;

    @FXML
    private Button saveFolder;

    @FXML
    private CheckBox searchOnlyAdultImages;

    @FXML
    private TextField inputNewEmail;

    @FXML
    private PasswordField inputNewPassword;

    @FXML
    private PasswordField inputNewPassword2;


    @FXML
    private ChoiceBox<String> chooseLanguage;

    @FXML
    private Button saveSettings;

    @FXML
    private ChoiceBox<String> urlSiteToParse;

    @FXML
    private Label loginLabel;

    @FXML
    void setChooseLanguage(ActionEvent event){
        saveSettings.setDisable(false);
    }

    @FXML
    void downloadFileButton(ActionEvent event) {

    }
    @FXML
    void inputNewEmail(ActionEvent event) {
        saveSettings.setDisable(false);
    }

    @FXML
    void inputNewPassword(ActionEvent event) {

    }

    @FXML
    void inputNewPassword2(ActionEvent event) {

    }

    @FXML
    void setInputNewEmail(ActionEvent event) {
        saveSettings.setDisable(false);
    }

    @FXML
    void netxImageButton(ActionEvent event) {

    }

    @FXML
    void previousImageButton(ActionEvent event) {

    }

    @FXML
    void saveFolder(ActionEvent event) {
        saveSettings.setDisable(false);
        // показываем диалог окно куда сохраняем картинки
        DirectoryChooser saveFolder = new DirectoryChooser();
        saveFolder.setTitle("Папка для сохранения изображений:");
        File selectDirectory = saveFolder.showDialog(saveImageOnComputer.getScene().getWindow());
        if(selectDirectory != null){
            selectDirectory.getAbsolutePath();
        }
        folderOnComp = selectDirectory.toString();

        //JOptionPane.showMessageDialog(null,folderOnComp);

    }

    @FXML
    void saveImageOnComputer(ActionEvent event) {
        saveSettings.setDisable(false);
    }

    @FXML
    void saveSettings(ActionEvent event) {
        //saveUserSetings();
    }

    @FXML
    void searchOnlyAdultImages(ActionEvent event) {
        saveSettings.setDisable(false);
    }

    @FXML
    void sortImagesByDate(ActionEvent event) {
        saveSettings.setDisable(false);
        sortImagesByPopular.setSelected(false);
    }

    @FXML
    void sortImagesByPopular(ActionEvent event) {
        saveSettings.setDisable(false);
        sortImagesByDate.setSelected(false);
    }


    Controller l = new Controller();
    String login = l.loginText;

    @FXML
    void initialize() {

        JOptionPane.showMessageDialog(null,login);

        //loginLabel.setText(getLogin());

        saveSettings.setDisable(true);

        //выбор языка интерфейса программы
        chooseLanguage.setItems(FXCollections.observableArrayList("Русский","English"));
        chooseLanguage.setValue("Русский");

        //раскрываем список откуда парсить
        urlSiteToParse.setItems(FXCollections.observableArrayList("VK.com","Pikabu.ru","Yaplakal.ru","Fishki.net"));
        urlSiteToParse.setValue("VK.com");

        // кнопка сохранения настроек
        //DatabaseHandler dbHandler = new DatabaseHandler();
        saveSettings.setOnAction(event -> {
            saveUserSetings(login);

        });
    }
    public void saveUserSetings(String login){
        DatabaseHandler dbHandler = new DatabaseHandler();
        String password = inputNewPassword.getText().trim();
        String email    = inputNewEmail.getText().trim();
        String language = chooseLanguage.getValue();
        String getOnly18;
        if(searchOnlyAdultImages.isSelected()){
            getOnly18 = "1";
        }else{
            getOnly18 = "0";
        }
        String saveFolderAdress = folderOnComp;
        String saveOnComp;
        if(saveImageOnComputer.isSelected()){
            saveOnComp = "1";
        }else{
            saveOnComp = "0";
        }
       // class2.setLogin(login);
       //String loginU = getLogin();
        String sortBy;
        if(sortImagesByPopular.isSelected()){
            sortBy = "1";
        }else{
            sortBy = "0";
        }
        JOptionPane.showMessageDialog(null,"password - "  + password+"\n" +
                                                        "email - "      + email +"\n" +
                                                        "language -"    +language +"\n" +
                                                        "getOnly18  -"  + getOnly18 +"\n" +
                                                        "saveFolderAdress -" + saveFolderAdress +"\n" +
                                                        "saveOnComp -"  + saveOnComp + "\n" +
                                                        "login - "      + login +"\n" +
                                                        "sortBy - "     + sortBy+"");
        //User user = new User(password,email,language,getOnly18,saveFolderAdress,saveOnComp,login,sortBy);
        //User user = new User(password,email,language,getOnly18);
        //dbHandler.updateUser(user);
    }
}
