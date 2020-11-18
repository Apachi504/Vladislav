package sample.animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake {
    private TranslateTransition tt;
    //Создаем конструктор Shake с параметром Node - где Node это любой объект в нашем окне : кнопка,надпись и т.д
    public Shake(Node node){
        tt = new TranslateTransition(Duration.millis(50),node);// Duration.millis(100) - длительность анимации
        tt.setFromX(0f);
        tt.setByX(10f);
        tt.setCycleCount(3);
        tt.setAutoReverse(true);
    }
    public void playAnim(){
        tt.playFromStart();
    }

}
