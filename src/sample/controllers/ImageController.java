package sample.controllers;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jdk.nashorn.internal.scripts.JO;
import sample.controllers.Controller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import sample.DatabaseHandler;

import javax.swing.*;

public class ImageController {

    private String folderOnComp;
    Controller l            = new Controller();
    String login            = l.loginText;
    String settingSortBy    = l.sortByD;
    String settingUrlSiteToParse   = l.urlSiteToParseD;
    String settingSaveOnComp       = l.saveOnCompD;
    String settingSaveFolder       = l.saveFolderD;
    String settingOnly18           = l.only18D;
    String settingLanguage         = l.languageD;


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
    private Button nextImageButton;

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
    private PasswordField inputNewPassword;

    @FXML
    private PasswordField inputNewPassword2;

    @FXML
    private ChoiceBox<String> chooseLanguage;

    @FXML
    private Button saveSettings;

    @FXML
    private TextField inputNewEmail;

    @FXML
    private AnchorPane paneDonats;

    @FXML
    private Button buttonDonatForProger;

    @FXML
    private ChoiceBox<String> urlSiteToParse;

    public static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try{runtime.exec("xdg-open " + uri);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    @FXML
    void buttonDonatForProger(ActionEvent event)  {
        URI ur = null;
        try {
            ur = new URI("https://money.yandex.ru/to/41001128606244/50");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        openWebpage(ur);
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
        //saveSettings.setDisable(false);
    }


    @FXML
    void setInputNewEmail(ActionEvent event) {
        saveSettings.setDisable(false);
    }

    @FXML
    void nextImageButton(ActionEvent event) {

    }

    @FXML
    void previousImageButton(ActionEvent event) {

    }

    @FXML
    void saveFolder(ActionEvent event) {
    }

    @FXML
    void saveImageOnComputer(ActionEvent event) {
        if (saveImageOnComputer.isSelected() == true){
            saveSettings.setDisable(false);
            saveFolder.setDisable(false);
        }else{
            if(saveSettings.isDisable()){
                saveSettings.setDisable(true);}
            saveFolder.setDisable(true);
        }
    }

    @FXML
    void saveSettings(ActionEvent event) {
    }

    @FXML
    void searchOnlyAdultImages(ActionEvent event) {
        if (searchOnlyAdultImages.isSelected() == true){
            saveSettings.setDisable(false);
        }else if(saveSettings.isDisable()){
            saveSettings.setDisable(true);
        }
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


    @FXML
    void initialize() {
        // по умолчанию кнопк указания адреса сохранения изображений отключена
        saveFolder.setDisable(true);

        //выбор языка интерфейса программы
        chooseLanguage.setItems(FXCollections.observableArrayList("Русский","English"));
        chooseLanguage.setValue("Русский");

        //раскрываем список откуда парсить
        urlSiteToParse.setItems(FXCollections.observableArrayList("VK.com","Pikabu.ru","Yaplakal.ru","Fishki.net"));
        urlSiteToParse.setValue("VK.com");

        //------------ загружаем настройки откуда парсить и языка -----------//

        // откуда парсить - адрес сайта
        urlSiteToParse.setValue(settingUrlSiteToParse);
        // язык программы
        chooseLanguage.setValue(settingLanguage);
        // ---------- конец загрузки настроек ------------//


       saveSettings.setDisable(true);

       // при выборе языка кнопка Сохранить настройки становится доступна
        chooseLanguage.setOnAction(event ->{
                saveSettings.setDisable(false);
        });

        // при выборе сайта откуда парсим кнопка Сохранить настройки становится доступна
        urlSiteToParse.setOnAction(event ->{
                saveSettings.setDisable(false);
        });


        // при наборе пароля кнопка сохранить настройки становится доступна
        inputNewPassword.setOnKeyPressed(keyEvent -> {
            saveSettings.setDisable(false);
        });

        saveFolder.setOnAction(event -> {
            // показываем диалог окно куда сохраняем картинки
            DirectoryChooser saveFolder = new DirectoryChooser();
            saveFolder.setTitle("Папка для сохранения изображений:");
            File selectDirectory = saveFolder.showDialog(saveImageOnComputer.getScene().getWindow());
            if(selectDirectory != null){
                selectDirectory.getAbsolutePath();
            }
            folderOnComp = selectDirectory.toString();
        });
        // кнопка сохранения настроек - передаем логин, для записи настроек именно для этого юзера
        saveSettings.setOnAction(event -> {
            saveUserSetings(login);
        });

    }

    public static void openBrowserUrl(String url) throws URISyntaxException{
        Desktop desktop;
        try{
            desktop = Desktop.getDesktop();
        }catch(Exception ex){
            System.err.println("Класс Desktop не поддерживается!");
            return;
        }

        if(!desktop.isSupported(Desktop.Action.BROWSE)){
            System.err.println("BROWSE :не поддерживается!");
            return;
        }

        try{
            desktop.browse(new URL(url).toURI());
        } catch (IOException ex){
            System.err.println("Failed to Browse. " + ex.getLocalizedMessage());
        }
    }

    public void saveUserSetings(String login){
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();

        String password = "";
        if(!inputNewPassword.getText().trim().equals("")){ password = inputNewPassword.getText().trim();}

        String email    = "";
        if(!inputNewEmail.getText().trim().equals("")){ email = inputNewEmail.getText().trim();}

        String language = chooseLanguage.getValue();
        String getOnly18;
        if(searchOnlyAdultImages.isSelected()){
            getOnly18 = "1";
        }else{
            getOnly18 = "0";
        }
        if(folderOnComp == null){ folderOnComp = "";}
        String saveOnComp;
        if(saveImageOnComputer.isSelected()){
            saveOnComp = "1";
        }else{
            saveOnComp = "0";
        }

        String sortBy = "";
        if(sortImagesByPopular.isSelected()){ sortBy = "1";}
        if(sortImagesByDate.isSelected()){ sortBy = "0";}

        String parseFrom = urlSiteToParse.getValue();
        user.setPassword(password);
        user.setEmail(email);
        user.setLanguage(language);
        user.setOnly18(getOnly18);
        user.setSaveFolder(folderOnComp);
        user.setSaveOnComp(saveOnComp);
        user.setSortBy(sortBy);
        user.setUrlSiteToParse(parseFrom);
        user.setLogin(login);

        dbHandler.updateUser(user);

    }

}
