package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.medClass;


public class Main extends Application {
    medClass openSc = new medClass();

    @Override
    public void start(Stage primaryStage) throws Exception{
        openSc.loadSceneStage("view/sample.fxml", ":: FunParse - парсер картинок ::");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
