package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.animations.Shake;
import sample.home.HomeController;

public class Controller extends Main {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginSignInButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button registerSignUpButton;

    @FXML
    void initialize() {
        loginSignInButton.setOnAction(event -> {
            //getText().trim() - delete spaces
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();

            if(!loginText.equals("")&&!loginPassword.equals("")){
                loginUser(loginText,loginPassword);
            }
            else{
                System.out.println("Field is empty or data is wrong");
            }
        });
        registerSignUpButton.setOnAction(event -> {
            openNewScene("/sample/signUp/signUp.fxml");
        });
    }

    private void loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUsername(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;
        try{
            while(result.next()){
                counter++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(counter >= 1){
            openNewScene("/sample/home/app.fxml");
        }else{
            Shake userLoginAnimation = new Shake(login_field);
            Shake userPasswordAnimation = new Shake(password_field);
            userLoginAnimation.playAnim();
            userPasswordAnimation.playAnim();
        }
    }
    public void openNewScene(String window){
        registerSignUpButton.getScene().getWindow().hide();

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
