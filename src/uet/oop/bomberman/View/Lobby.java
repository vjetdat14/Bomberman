package uet.oop.bomberman.View;

import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URISyntaxException;
import java.util.Objects;

public class Lobby {

    Scene scene, scene1 ;

    public static final int DEFAULT_SIZE = 20;
    public static final int SCALED_SIZE = DEFAULT_SIZE * 2; //40
    public static final int col = 50;
    public static final int row = 30;
    public static final int sc = col*DEFAULT_SIZE;        //1200
    public static final int sr = row*DEFAULT_SIZE;          //800
    public static Canvas canvas = new Canvas(sc, sr);
    public static final GraphicsContext gc = canvas.getGraphicsContext2D();
    private final Stage stage;

    public Lobby(Stage stage) {
        this.stage=stage;

    }

    public void run() throws URISyntaxException {

        Timeline t= new Timeline();
        t.setCycleCount(Timeline.INDEFINITE);

        Font font;
        Image ys = new Image(Objects.requireNonNull(getClass().getResource("/bomber/Chibi_Yasuo_Dragon-1.png")).toURI().toString(), sc, sr, true, true);
        ImageView yss = new ImageView(ys);
        font = Font.loadFont(Objects.requireNonNull(getClass().getResource("/assets/font/upheavtt.ttf")).toURI().toString(), 17);
        Button bt = new Button();
        bt.setLayoutX(sc/2-70);
        bt.setLayoutY(sr-30);
        bt.setText(" Start game !!!");
        bt.setFont(font);
        GraphicsContext render = canvas.getGraphicsContext2D();
        bt.setOnAction(actionEvent -> {
            stage.setScene(scene1);
        });
        Group layout = new Group();
        layout.getChildren().addAll(yss, bt);
        scene = new Scene(layout, sc, sr);
        Group layout2 = new Group();
        layout2.getChildren().addAll(canvas);
        scene1 = new Scene(layout2, sc, sr, Color.LIGHTBLUE);
        Label gs = new Label();
        gs.setFont(font);
        gs.setText("GAME START");
        stage.setScene(scene);
        stage.centerOnScreen();
        render = canvas.getGraphicsContext2D();
        stage.show();
    }

}
