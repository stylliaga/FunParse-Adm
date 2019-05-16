package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class ImageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView showNextImage;

    @FXML
    void initialize() {
        assert showNextImage != null : "fx:id=\"showNextImage\" was not injected: check your FXML file 'app.fxml'.";

    }
}
