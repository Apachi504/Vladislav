package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

public class Controller {


        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private TextField city_field;

        @FXML
        private Button search_btn;

        @FXML
        private Text temp_text;

        @FXML
        private Text min_text;

        @FXML
        private Text max_text;

        @FXML
        private Text felt_text;

        @FXML
        private Text pressure_text;




    @FXML
    void initialize() {

    search_btn.setOnAction(actionEvent -> {
        String getCity = city_field.getText().trim();
        if (!getCity.equals("")){
        String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + getCity +"&appid=2cd6b53c80352557c2d543a263c38f01&units=metric");
            if (!output.isEmpty()){
                JSONObject obj = new JSONObject(output);
                temp_text.setText("temperature :"+obj.getJSONObject("main").getDouble("temp"));
                max_text.setText("maximum :"+obj.getJSONObject("main").getDouble("temp_max"));
                felt_text.setText("felt :"+obj.getJSONObject("main").getDouble("feels_like"));
                min_text.setText("minimum :"+obj.getJSONObject("main").getDouble("temp_min"));
                pressure_text.setText("pressure :"+obj.getJSONObject("main").getDouble("pressure"));
            }
    }});
  }
  private static String getUrlContent(String urlAdress){
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                content.append(line +"\n");
            }
            bufferedReader.close();
        }catch (Exception e) {
            System.out.println("Not found!!!");
            }
        return content.toString();
  }
}
