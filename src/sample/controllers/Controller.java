package sample.controllers;

        import java.net.URL;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.ResourceBundle;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.PasswordField;
        import javafx.scene.control.TextField;
        import sample.DatabaseHandler;
        import sample.animation.Shake;
        import sample.medClass;
        import sample.parsers.vk.VKParserImages;

        import javax.swing.*;

public class Controller  {
    medClass openSc = new medClass();
    static String loginText;
    public static String sortByD;
    public static String saveOnCompD;
    public static String saveFolderD;
    public static String only18D;
    public static String languageD;
    static String urlSiteToParseD;

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

    //VKParserImages pressButtonNext = new VKParserImages();

    @FXML
    void about(ActionEvent event) {
       // pressButtonNext.vkParserImagesAdult();

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
                try {
                    loginUser(loginText, loginPassword);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{
                Shake userLoginAnimation    = new Shake(loginField);
                Shake userPasswordAnimation = new Shake(passwordField);

                userLoginAnimation.playAnim();
                userPasswordAnimation.playAnim();
            }

        });

        /*loginSigninButton.setOnAction(event ->{
            loginSigninButton.getScene().getWindow().hide();
            openSc.loadSceneStage("/sample/view/signUp.fxml",":: FunPars - регистрация пользователя ::");



            //openNewScene();
        });*/
    }

    private void loginUser(String loginText, String loginPassword) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();

        user.setLogin(loginText);
        user.setPassword(loginPassword);

        ResultSet result = dbHandler.getUserFromDB(user);


        String resultMeta = "";
        //resultMeta = result.getMetaData().getColumnCount();

        int counter = 0;
        try {
            while (result.next()) {
                counter++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        //JOptionPane.showMessageDialog(null,result.getMetaData().getColumnName(1));
        if(counter >= 1){
            authSignInButton.getScene().getWindow().hide();
            openSc.loadSceneStage("/sample/view/app.fxml",":: FunPars - парсер картинок ::");
        }else{
            Shake userLoginAnimation    = new Shake(loginField);
            Shake userPasswordAnimation = new Shake(passwordField);

            userLoginAnimation.playAnim();
            userPasswordAnimation.playAnim();

        }
    }

}
