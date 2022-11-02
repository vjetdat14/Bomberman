package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import uet.oop.bomberman.Bomb.*;
import uet.oop.bomberman.View.BT;
import uet.oop.bomberman.View.JPANEL;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Enemy.Enemy;

import uet.oop.bomberman.entities.Still.Grass;
import uet.oop.bomberman.entities.Still.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import javax.swing.*;
import java.net.URISyntaxException;
import java.util.*;

import static uet.oop.bomberman.Bomb.Bomb.flames;
import static uet.oop.bomberman.Map.createMap;

public class BombermanGame extends Application {
    
    public static int WIDTH = 31;
    public static int HEIGHT = 13;

    public static int level = 1; // khai báo lv

    private GraphicsContext gc;
    private Canvas canvas;
    public static final List<Entity> stillObjects = new ArrayList<>();
    public static final List<Enemy> entities = new ArrayList<>();

    public static Bomber bomberman;

    public static int xStart; // tọa độ x ban đầu của bomberman
    public static int yStart; // tọa độ y ban đầu của bomberman
    public static int countTime;
    public static Scanner scanner; // lớp scanner
    public static Sound sound = new Sound();

    public static int nLayer =1;
    public static int WLayer =2;
    public static boolean pass = false;
    public static boolean check = false;
    public static int startBomb = 1; // chỉ số tăng thêm bom khi ăn bombitem
    public static int startSpeed = 5; // chỉ số speed đạt dc khi ăn speeditem
    public static int startFlame = 1; // chỉ số bán kinh flame tăng thêm khi ăn flameitem

    public static int life =3;
    public static int time = 200; // cài time
    public static int score =0; // point

    public static AnchorPane ro = new AnchorPane(); // ro đại diện cho chức năng load các vùng cũng như ảnh text của toàn game
    public static JPANEL jpanel = new JPANEL(); // để gọi các phương thức lớp JPanel (liên quan đến set up hình ảnh)




    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws URISyntaxException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        // Tao root container
        AnchorPane root1 = new AnchorPane();
        root1.getChildren().add(canvas);
        ro.getChildren().addAll(new Rectangle(2,3));
        jpanel.setPanel();
        root1.getChildren().add(ro);
        // Tao scene
        Scene scene1 = new Scene(root1);

        Font font;
        Image ys = new Image(Objects.requireNonNull(getClass().getResource("/Menuscreen.png")).toURI().toString(), 1200, 800, true, true);
        ImageView yss = new ImageView(ys);
        BT bt = new BT("Start Game");
        bt.setLayoutX(200);
        bt.setLayoutY(450);
        bt.setText(" Start game !!!");
        GraphicsContext render = canvas.getGraphicsContext2D();
        bt.setOnAction(actionEvent -> {
            stage.setScene(scene1);
            stage.centerOnScreen();
            sound.stop();
            sound.setFile("ingame");
            sound.play();
            sound.setFile("playGame");
            sound.playMusic();
        });
        Group layout = new Group();
        layout.getChildren().addAll(yss, bt);
        Scene scene = new Scene(layout, 1200, 750);

        BackgroundImage bg = new BackgroundImage(Sprite.grass.getFxImage(), BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT, null);
        root1.setBackground(new Background(bg));
        Image logo = new Image(Objects.requireNonNull(getClass().getResource("/bom1.png")).toURI().toString());
        stage.getIcons().add(logo);
        stage.setTitle("Bombermannnnnnnnnnnnnnnnnnn");
        // Them scene vao stage
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (check == true ) {
                    if(level >=5 )
                    {
                        this.stop();
                        JOptionPane optionPane = new JOptionPane("YOU WIN", JOptionPane.WARNING_MESSAGE);
                        JDialog dialog = optionPane.createDialog("WIN");
                        dialog.setAlwaysOnTop(true); // to show top of all other application
                        dialog.setVisible(true); // to visible the dialog
                        stage.close();
                        sound.stop();
                    }
                    level++;
                    stillObjects.clear();
                    entities.clear();
                    Map.createMap();
                    check = false;
                    score = score +100;
                }

                if (life == 0) {

                    this.stop();
                    JOptionPane optionPane = new JOptionPane("YOU LOSE", JOptionPane.WARNING_MESSAGE);
                    JDialog dialog = optionPane.createDialog("NON");
                    dialog.setAlwaysOnTop(true); // to show top of all other application
                    dialog.setVisible(true); // to visible the dialog
                    stage.close();
                    sound.stop();
                }
                render();
                update();
            }
        };
        timer.start();

        createMap();
        sound.setFile("playGame");
        sound.playMusic();
        scene1.setOnKeyPressed(event -> bomberman.handleKeyPressedEvent(event.getCode())); // sự kiện nhập từ bàn phím
        scene1.setOnKeyReleased(event -> bomberman.handleKeyReleasedEvent(event.getCode()));
        }

    public void createMap1() {
        Entity object;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {

                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1 || (i==2 && j==2)) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                } else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
    }

    public void update() {
        if(countTime % 100==0){
            time--;
        }
        jpanel.setTimes(time); // set time
        jpanel.setPoint(score); // set điểm
        jpanel.setLives(life); // set mạng
        jpanel.setLevel(level);
        entities.forEach(Entity::update);
        stillObjects.forEach(Entity::update);
        List<Bomb> bombs = bomberman.getBombs(); // tạo list bombs
        for (Bomb bomb : bombs) {
            bomb.update(); // chạy các sự kiện của bomb
        }
        for (Flame flame : flames) {
            flame.update();
        }
        bomberman.handleCollisions();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        List<Bomb> bombs = bomberman.getBombs();

            bombs.forEach(g->g.render(gc));
        for (Flame flame : flames) {
            flame.render(gc);
        }
        coutTime();

    }
    public void coutTime() {
        if ( countTime<400*60) {
            countTime++;
        }
    }
}
