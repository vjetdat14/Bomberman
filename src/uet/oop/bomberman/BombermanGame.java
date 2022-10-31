package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Enemy.Balloom;
import uet.oop.bomberman.entities.Still.Bomb;
import uet.oop.bomberman.entities.Still.Grass;
import uet.oop.bomberman.entities.Still.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static uet.oop.bomberman.Map.createMap;

public class BombermanGame extends Application {
    
    public static int WIDTH = 31;
    public static int HEIGHT = 13;

    public static int level = 1; // khai báo lv

    private GraphicsContext gc;
    private Canvas canvas;
    public static final List<Entity> stillObjects = new ArrayList<>();
    public static final List<Entity> entities = new ArrayList<>();

    public static Bomber bomberman;

    public static int xStart; // tọa độ x ban đầu của bomberman
    public static int yStart; // tọa độ y ban đầu của bomberman
    public static Balloom balloom;
    public static int countTime;
    public static Scanner scanner; // lớp scanner




    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws URISyntaxException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        AnchorPane root = new AnchorPane();
        root.getChildren().add(canvas);
//        Media media = new Media("/music/URF.mp3");
//        MediaPlayer mediaplr = new MediaPlayer(media);
//        mediaplr.setCycleCount(MediaPlayer.INDEFINITE);
//        mediaplr.play();

        // Tao scene
        Scene scene = new Scene(root);
        BackgroundImage bg = new BackgroundImage(Sprite.grass.getFxImage(), BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT, null);
        root.setBackground(new Background(bg));
        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        balloom = new Balloom(10, 6, Sprite.balloom_right2.getFxImage());
        stillObjects.add(balloom);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
                balloom.update();
            }
        };
        timer.start();

        createMap();

        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());

        entities.add(bomberman);
        scene.setOnKeyPressed(event -> bomberman.handleKeyPressedEvent(event.getCode())); // sự kiện nhập từ bàn phím
        scene.setOnKeyReleased(event -> bomberman.handleKeyReleasedEvent(event.getCode()));
        }

    public void createMap1() {
        Entity object;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {

                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1 || (i==4 && j==4)) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                } else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
    }

    public void update() {
        entities.forEach(Entity::update);
        stillObjects.forEach(Entity::update);
        List<Bomb> bombs = bomberman.getBombs(); // tạo list bombs
        for (Bomb bomb : bombs) {
            bomb.update(); // chạy các sự kiện của bomb
        }
        bomberman.handleCollisions();

    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        List<Bomb> bombs = bomberman.getBombs();
        for (Bomb bomb : bombs) {
            bomb.render(gc);
        }
        coutTime();
        bomberman.render(gc);
        balloom.render(gc);
    }
    public void coutTime() {
        if ( countTime<400*60) {
            countTime++;
        }
    }
}
