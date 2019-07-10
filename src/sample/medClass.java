package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class medClass {
    public void loadSceneStage(String adress,String titleScene) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(adress));
            //fxmlLoader.setController(Controller);

            Scene scene = new Scene(fxmlLoader.load(), 700, 400);
            Stage stage = new Stage();
            stage.setTitle(titleScene);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (
                IOException e) {
        }
    }
   // public void saveFolder(){}
}
