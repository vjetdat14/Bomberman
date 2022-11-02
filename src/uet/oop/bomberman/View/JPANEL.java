package uet.oop.bomberman.View;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import uet.oop.bomberman.BombermanGame;

import java.net.URISyntaxException;
import java.util.Objects;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.RED;

public class JPANEL extends AnchorPane{
    public Label labelLevel; // hiển thị chữ level trên thanh thông tin

    public Label labelTime; // hiển thị chữ time trên thanh thông tin

    public Label labelPoint; // hiển thị chữ point trên thanh thông tin

    public Label labelLives; // hiển thị chữ live trên thanh thông tin

    public JPANEL() {
        Font font;
        try {
            font = Font.loadFont(Objects.requireNonNull(getClass().getResource("/font/upheavtt.ttf")).toURI().toString(), 20);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        labelTime = new Label("TIME : "+ BombermanGame.time); // cài time
        // chỉnh tọa độ xếp thông tin về time
        labelTime.setLayoutX(180);
        labelTime.setLayoutY(1);
        labelTime.setFont(font);
        labelTime.setTextFill(BLACK);
        // chỉnh tọa độ xếp thông tin về point
        labelPoint = new Label("POINT : "+ BombermanGame.score);
        labelPoint.setLayoutX(450);
        labelPoint.setLayoutY(1);
        labelPoint.setFont(font);
        labelPoint.setTextFill(BLACK);
        // chỉnh tọa độ xếp thông tin về lives
        labelLives = new Label("LIVES : "+ BombermanGame.life);
        labelLives.setLayoutX(700);
        labelLives.setLayoutY(1);
        labelLives.setFont(font);

        labelLevel = new Label("LEVEL : "+ BombermanGame.level);
        labelLevel.setLayoutX(1000);
        labelLevel.setLayoutY(1);
        labelLevel.setFont(font);
    }
    public void setPanel() {
        BombermanGame.ro.getChildren().addAll(labelTime,labelPoint,labelLives,labelLevel);
    }
    public void setLevel(int t) {
        labelLevel.setText("LEVEL : " + t);
    }

    public void setTimes(int t) {
        labelTime.setText("TIMES : "+t);
    }

    public void setPoint(int t) {
        labelPoint.setText("POINT : "+t);
    }

    public void setLives(int t) {
        labelLives.setText("LIVES : "+t);
    }
}