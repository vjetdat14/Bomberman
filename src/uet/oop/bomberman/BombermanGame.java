package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class BombermanGame extends Application {
    
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    private GraphicsContext gc;
    private Canvas canvas;
    public static final List<Entity> stillObjects = new ArrayList<>();
    public static final List<Entity> entities = new ArrayList<>();

    public static Bomber bomberman;
    public static Baloom baloom;
    public static Bomb bomb;
    public static int countTime;



    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws URISyntaxException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);
//        Media media = new Media("/music/URF.mp3");
//        MediaPlayer mediaplr = new MediaPlayer(media);
//        mediaplr.setCycleCount(MediaPlayer.INDEFINITE);
//        mediaplr.play();

        // Tao scene
        Scene scene = new Scene(root, Color.LIGHTBLUE);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        bomb = new Bomb(10,6,Sprite.bomb_2.getFxImage());
        baloom = new Baloom(10, 6, Sprite.balloom_right2.getFxImage());
        stillObjects.add(baloom);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
                baloom.update();
                bomb.update();
            }
        };
        timer.start();

        createMap();

        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());

        entities.add(bomberman);
        scene.setOnKeyPressed(event -> bomberman.handleKeyPressedEvent(event.getCode())); // sự kiện nhập từ bàn phím
        scene.setOnKeyReleased(event -> bomberman.handleKeyReleasedEvent(event.getCode()));
        }

    public void createMap() {
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

    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        coutTime();
        bomberman.render(gc);
        baloom.render(gc);
        bomb.render(gc);
    }
    public void coutTime() {
        if ( countTime<400*60) {
            countTime++;
        }
    }
}
