package sample.controllers;

        import java.io.IOException;
        import java.net.URL;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.ResourceBundle;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.PasswordField;
        import javafx.scene.control.TextField;
        import javafx.stage.Stage;
        import sample.DatabaseHandler;

public class Controller {

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
            String loginText = loginField.getText().trim();
            String loginPassword = passwordField.getText().trim();

            if(!loginText.equals("") && !loginPassword.equals("")){
                loginUser(loginText, loginPassword);
            }else{
                System.out.println("Вы не заполнили все поля!");
            }
        });
        loginSigninButton.setOnAction(event ->{
            loginSigninButton.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/signUp.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
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
            System.out.println("Success!");
        }
    }

}
