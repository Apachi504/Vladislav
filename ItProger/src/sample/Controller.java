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

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button SingUpButton;

    @FXML
    private Button LoginSignUpButton;

    @FXML
    void initialize() {

        SingUpButton.setOnAction(event -> {
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();

            if(!loginText.equals("") && !loginPassword.equals(""))
                loginUser(loginText,loginPassword);
            else
                System.out.println("Error");
        });

        LoginSignUpButton.setOnAction(event ->{
            openNewScene("/sample/SingUp.fxml");
        });


    }

    private void loginUser(String loginText, String loginPassword) {
        DateBaseHandler dbHandler = new DateBaseHandler(); // Создаем объект класса DateBaseHandler чтобы мы могли обрашаться к его методам

        User user = new User();// Новый объект на основе класса User
        user.setUserName(loginText);// Устонавливаем для пользователя поле Логина
        user.setPassword(loginPassword);// Устонавливаем для пользователя поле Пароль
        ResultSet result = dbHandler.getUser(user);

        int count  = 0;

        while (true){
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            } //Перебираем массив пользователей

        count++;
        }
        if (count >= 1){
            openNewScene("/sample/sample2.fxml");

        } else {
            Shake userLoginAnim = new Shake(login_field);// Используем анимацию для поля Логин
            Shake userPassAnim = new Shake(password_field);// Используем анимацию для поля Пароль
            userLoginAnim.playAnim();
            userPassAnim.playAnim();
        }
    }
        public void openNewScene(String window){
            LoginSignUpButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(window));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        }
}

