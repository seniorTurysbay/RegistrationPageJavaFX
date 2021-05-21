package sample.signUp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.*;
import sample.DatabaseHandler;
import sample.User;
import sample.home.HomeController;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField signUpEmail;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private TextField signUpName;

    @FXML
    private TextField signUpSurname;

    @FXML
    private TextField signUpCountry;

    @FXML
    private RadioButton signUpMaleRadioBtn;

    @FXML
    private RadioButton signUpFemaleRadioBtn;

    @FXML
    private Button backMainPageBtn;

    @FXML
    void initialize() {

        signUpButton.setOnAction(event -> {

                if (!signUpName.getText().isEmpty() && !signUpSurname.getText().isEmpty() && !signUpEmail.getText().isEmpty() && !signUpPassword.getText().isEmpty() && !signUpCountry.getText().isEmpty()) {
                    System.out.println("Success");
                    signUpNewUser();
                    openNewScene("../sample.fxml");
                }else{
                    System.out.println("not success");
                }
            });

        backMainPageBtn.setOnAction(event -> {
            backMainPageBtn.getScene().getWindow().hide();
            openNewScene("../sample.fxml");
        });
    }
    Alert a = new Alert(Alert.AlertType.NONE);
    EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            // set alert type
            a.setAlertType(Alert.AlertType.ERROR);
            // show the dialog
            a.show();
        }
    };
    private void signUpNewUser(){
        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstname = signUpName.getText();
        String lastname = signUpSurname.getText();
        String username = signUpEmail.getText();
        String password = signUpPassword.getText();
        String location = signUpCountry.getText();
        String gender = "";

        if(signUpMaleRadioBtn.isSelected()){
            gender = "Male";
        }
        else{
            gender = "Female";
        }

        User user = new User(firstname, lastname, username, password, location,gender);

        dbHandler.signUpUser(user);

    }
    public void openNewScene(String window){
        signUpButton.getScene().getWindow().hide();

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

