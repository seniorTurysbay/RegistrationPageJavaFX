package sample.todo;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TodoController {
//    public void initialize(URL url, ResourceBundle rb){
//        datePicker.setValue(LocalDate.now());
//    }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private Button addEventBtn;

    @FXML
    private ListView<LocalEvent> eventList;

    @FXML
    private Button homeBtn;

    ObservableList<LocalEvent> list = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        datePicker.setValue(LocalDate.now());
        addEventBtn.setOnAction(event -> {
            list.add(new LocalEvent(datePicker.getValue(),descriptionTextField.getText()));
            eventList.setItems(list);
            refresh();
        });
        homeBtn.setOnAction(event -> {
            homeBtn.getScene().getWindow().hide();
            openNewScene("/sample/home/app.fxml");
        });
    }
    private void refresh(){
        datePicker.setValue(LocalDate.now());
        descriptionTextField.setText(null);
    }
    public void openNewScene(String window){
//        logoutBtn.getScene().getWindow().hide();

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
