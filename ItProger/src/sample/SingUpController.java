package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SingUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField LoginSingUp;

    @FXML
    private PasswordField PasswordSingUp;

    @FXML
    private Button SingUpButton;

    @FXML
    private TextField NameSingUp;

    @FXML
    private TextField SurnameSingUp;

    @FXML
    private TextField locationSingUp;

    @FXML
    private CheckBox SingUpCheckBoxWoman;

    @FXML
    private CheckBox SingUpCheckBoxMan;

    @FXML
    void initialize() {
        SingUpButton.setOnAction(event ->{

            singUpNewUser();



        });
    }

    private void singUpNewUser() {
        DateBaseHandler dbHandler = new DateBaseHandler();


        String firstName = NameSingUp.getText();
        String lastName = SurnameSingUp.getText();
        String userName = LoginSingUp.getText();
        String location = locationSingUp.getText();
        String password = PasswordSingUp.getText();
        String gender = "";

        if (SingUpCheckBoxMan.isSelected())
            gender = "Male";
        else
            gender = "Woman";

        User user = new User(firstName,lastName,userName,location,password,gender);
        dbHandler.singUpUser(user);
    }
}
