package sample.todo;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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

//    @FXML
//    private void addEvent(Event a){
//
//    }
    ObservableList<LocalEvent> list = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        datePicker.setValue(LocalDate.now());
        addEventBtn.setOnAction(event -> {
            list.add(new LocalEvent(datePicker.getValue(),descriptionTextField.getText()));
            eventList.setItems(list);
            refresh();
        });

    }
    private void refresh(){
        datePicker.setValue(LocalDate.now());
        descriptionTextField.setText(null);
    }
}
