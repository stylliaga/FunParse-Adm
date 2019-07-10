package sample.controllers;

        import java.io.IOException;
        import java.net.URL;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.ResourceBundle;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.JavaFXBuilderFactory;
        import javafx.scene.Parent;
        import javafx.scene.Node;
        import javafx.scene.control.Button;
        import javafx.scene.control.PasswordField;
        import javafx.scene.control.TextField;
        import javafx.stage.Stage;
        import sample.DatabaseHandler;
        import sample.animation.Shake;
        import sample.medClass;

        import javax.swing.*;

public class Controller  {
    medClass openSc = new medClass();
    static String loginText;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button authSignInButton;

    @FXML
    private Button about;

    @FXML
    private Button loginSigninButton;

    @FXML
    void about(ActionEvent event) {

    }

    @FXML
    void authSignInButton(ActionEvent event) {

    }

    @FXML
    void loginField(ActionEvent event) {

    }

    @FXML
    void loginSigninButton(ActionEvent event) {


    }

    @FXML
    void passwordField(ActionEvent event) {

    }

    @FXML
    void initialize() {
        authSignInButton.setOnAction(event ->{
            loginText = loginField.getText().trim();
            String loginPassword = passwordField.getText().trim();

            //String login =  class1.getLogin();

            //JOptionPane.showMessageDialog(null,"loginText "+loginText);



            if(!loginText.equals("") && !loginPassword.equals("")){
                loginUser(loginText, loginPassword);
            }else{
                Shake userLoginAnimation    = new Shake(loginField);
                Shake userPasswordAnimation = new Shake(passwordField);

                userLoginAnimation.playAnim();
                userPasswordAnimation.playAnim();
            }

        });

        loginSigninButton.setOnAction(event ->{
            loginSigninButton.getScene().getWindow().hide();
            openSc.loadSceneStage("/sample/view/signUp.fxml",":: FunPars - регистрация пользователя ::");



            //openNewScene();
        });
    }

    private void loginUser(String loginText, String loginPassword){
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();

        user.setLogin(loginText);
        user.setPassword(loginPassword);

        ResultSet result = dbHandler.getUser(user);

        int counter = 0;
        try {
            while (result.next()) {
                counter++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        if(counter >= 1){
            loginSigninButton.getScene().getWindow().hide();
            openSc.loadSceneStage("/sample/view/app.fxml",":: FunPars - парсер картинок ::");
        }else{
            Shake userLoginAnimation    = new Shake(loginField);
            Shake userPasswordAnimation = new Shake(passwordField);

            userLoginAnimation.playAnim();
            userPasswordAnimation.playAnim();

        }
    }

}
