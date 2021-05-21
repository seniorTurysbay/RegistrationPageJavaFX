package sample.home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Controller;
import sample.Controller.*;
public class HomeController extends Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button questionnaireBtn;

    @FXML
    void initialize() {
        logoutBtn.setOnAction(event -> {
            logoutBtn.getScene().getWindow().hide();
            openNewScene("../sample.fxml");
        });
        questionnaireBtn.setOnAction(event -> {
            questionnaireBtn.getScene().getWindow().hide();
            openNewScene("/sample/questionnaire/questionnaire.fxml");
        });
    }
    public void openNewScene(String window){
        logoutBtn.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try{
            loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
