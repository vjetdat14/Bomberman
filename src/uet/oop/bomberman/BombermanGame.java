package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Item;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    public static final int WIDTH = 40;
    public static final int HEIGHT = 30;
//    private Sound menuSound;
//    private Stage stage;
    // menu
//    public Parent menu() throws IOException {
//        Pane root = new Pane();
//        root.setPrefSize(992, 416);
//
//        try (InputStream is = Files.newInputStream(Paths.get("res//Menuscreen"))) {
//            ImageView img = new ImageView(new Image(is));
//            img.setFitHeight(416);
//            img.setFitWidth(992);
//            root.getChildren().add(img);
//        } catch (IOException e) {
//            System.out.printf("Can't load the image");
//        }
//
//        MenuItem start = new MenuItem("Start game");
//        public void action() {
//            menuSound.getMediaPlayer().stop();
//        }
//    }
    private GraphicsContext gc;
    private Canvas canvas;
    private Scene scene;

    private List<Entity> entities = new ArrayList<>(); // thực thể
    private List<Entity> stillObjects = new ArrayList<>(); // đối tượng
    public static List<Enemy> enemyList = new ArrayList<>(); // kẻ địch
    public static List<Flame> flameList = new ArrayList<>(); // tia lửa (khi bom nổ)
    private List<Bomb> bombList = new ArrayList<>();  // bom
    public static List<Portal> portalList = new ArrayList<>();  // cửa qua màn
    private List<Item> itemList = new ArrayList<>();  // vật phẩm sử dụng
    private List<Brick> brickList = new ArrayList<>();    // gạch

    public static Integer time = 300;
    public static int levels = 1;
    public static int score = 0;

    public static Bomber bomberman = new Bomber(1,1,Sprite.player_right.getFxImage()); // tạo nhân vật

    public static Label scoreText = new Label();
    public static Label timeText = new Label();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        Scene scene = new Scene(root);
        root.getChildren().add(canvas);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();

        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
    }

    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                }
                else {
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
    }
}