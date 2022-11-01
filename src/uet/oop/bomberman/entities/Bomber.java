package uet.oop.bomberman.entities;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Enemy.Balloom;
import uet.oop.bomberman.entities.Enemy.Enemy;
import uet.oop.bomberman.entities.Still.Bomb;
import uet.oop.bomberman.entities.Still.Brick;
import uet.oop.bomberman.entities.Still.Portal;
import uet.oop.bomberman.entities.Still.Wall;
import uet.oop.bomberman.graphics.Sprite;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomber extends Character {

    private KeyCode key = null; // khai báo phím bấm

    private int bombRemain; // khai báo biến "số bom dữ trự"
    private boolean placeBombCommand = false; // quản lý về việc đặt bom (trả về true or false)
    private boolean isAllowedGoToBom = false; // quản lý việc đi xuyên qua bom (trả về true hoặc false)
    private final List<Bomb> bombs = new ArrayList<>(); // khai báo list quản lý bom
    private int radius; // biến bán kính nổ
    private int life;
    private int timeAfterDie = 0;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        setSpeed(3); // tốc độ
        setLayer(1); // chỉ số va chạm của bomber
        setSpeed(4); // tốc độ
        setBombRemain(2); // số bom dự trữ
        setRadius(1); // bán kính nổ
        life = 1;
    }

    private void setRadius(int i) {
        this.radius = i;
    }

    public void setBombRemain(int bombRemain) { // cài số lượng bom dự trữ
        this.bombRemain = bombRemain;
    }

    public List<Bomb> getBombs() { // trả về list bomb
        return bombs;
    }

    // điều khiển nhân vật
    @Override
    public void update() {

        if (key == KeyCode.A) {
            goLeft();
        }
        if (key == KeyCode.D) {
            goRight();
        }
        if (key == KeyCode.W) {
            goUp();
        }
        if (key == KeyCode.S) {
            goDown();
        }
        if (placeBombCommand) {
            placeBomb();
        }
        for (int i = 0; i < bombs.size(); i++) { // duyệt cái list của bomb
            Bomb bomb = bombs.get(i);
            if (!bomb.isAlive()) {
                bombs.remove(bomb);
                bombRemain++;
            }
        }
        //animate();
        if (!isAlive()) {
            timeAfterDie++;
            die();
        }


    }

    private void placeBomb() {
        if (bombRemain > 0) {
            int xB = (int) Math.round((x + 4) / (double) Sprite.SCALED_SIZE);// tọa độ bom
            int yB = (int) Math.round((y + 4) / (double) Sprite.SCALED_SIZE);//
            for (Bomb bomb : bombs) { // duyệt list bombs
                if (xB * Sprite.SCALED_SIZE == bomb.getX() && yB * Sprite.SCALED_SIZE == bomb.getY()) return;
            }
            bombs.add(new Bomb(xB, yB, Sprite.bomb.getFxImage(), radius)); // tạo bom và add vào list bomb
            isAllowedGoToBom = true; // xuyên qua bom trả về true
            bombRemain--;
        }
    }

    public void handleKeyPressedEvent(KeyCode keyCode) {

        if (keyCode == KeyCode.A || keyCode == KeyCode.D
                || keyCode == KeyCode.W || keyCode == KeyCode.S) {
            this.key = keyCode;
        }
        if (keyCode == KeyCode.SPACE) {
            placeBombCommand = true;
        }
    }

    // điều khiển bomber di chuyển và đặt bomb từ bàn phím (phương thức này sd để load ảnh hiển thị) (xử lý các sự kiện bàn phím)
    public void handleKeyReleasedEvent(KeyCode keyCode) {
        if (key == keyCode) {
            if (key == KeyCode.A) {
                img = Sprite.player_left.getFxImage();
            }
            if (key == KeyCode.D) {
                img = Sprite.player_right.getFxImage();
            }
            if (key == KeyCode.W) {
                img = Sprite.player_up.getFxImage();
            }
            if (key == KeyCode.S) {
                img = Sprite.player_down.getFxImage();
            }
            key = null;
        }
        if (keyCode == KeyCode.SPACE) {
            placeBombCommand = false;
        }

    }

    // các load ảnh hiển thị di chuyển
    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, left++, 20).getFxImage();
        handleCollisions();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, right++, 20).getFxImage();
        handleCollisions();
    }

    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, up++, 20).getFxImage();
        handleCollisions();
    }

    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, down++, 20).getFxImage();
        handleCollisions();
    }

    public void die() {
        if (timeAfterDie == 20) life--; // kể từ sau khi bom nổ đến khi 20 đơn vị thời gian thì số mạng giảm xuống 1
        if (timeAfterDie <= 45) { // load ảnh bomber chết trong 45 đơn vị thời gian
            img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2,
                    Sprite.player_dead3, timeAfterDie, 20).getFxImage();

        }

    }

    public Rectangle getBounds() {
        return new Rectangle(desX, desY + 5, Sprite.SCALED_SIZE - 10, Sprite.SCALED_SIZE * 3 / 4);
    }


    public void handleCollisions() {
        Rectangle r1 = bomberman.getBounds(); // tạo bound cho bomber

        List<Bomb> bombs = bomberman.getBombs(); // tạo list bomb
        boolean bomberIntersectsBom = false; // biến kiểm tra va chạm bomber và bom
        for (Bomb bomb : bombs) {
            Rectangle r2 = bomb.getBounds(); // tạo bound cho bomb
            if (r1.intersects(r2)) {
                bomberIntersectsBom = true; // trả về true nếu bomber va chạm với bomb
            }
        }
        for (Entity stillObject : BombermanGame.stillObjects) { // duyệt all thực thể
            Rectangle r2 = stillObject.getBounds();
            boolean biw = false;
            boolean bibr = false;
            boolean bip = false;
            if (r1.intersects(r2)) { // nếu bomber va chạm với các vật thể thì trả về true
                if (stillObject instanceof Wall)
                    biw = true;
                if (stillObject instanceof Brick)
                    bibr = true;
                if (stillObject instanceof Portal)
                    bip = true;
                if (biw) {
                    bomberman.stay();
                    System.out.println("cham tuong");
                }
                if (bibr) {
                    bomberman.stay();
                    System.out.println("cham gach");
                }
                if (bip) {
                    bomberman.stay();
                    System.out.println("cham cong");
                }
                if (!bomberIntersectsBom) { // chỉ số va chạm của bomber > grass tại ví trí đặt bom và người ko va chạm vs bom
                    bomberman.move(); // cho phép đi qua bom
                    isAllowedGoToBom = false; // trả về false -> ko cho phép vượt qua bom nữa
                } else if (bomberIntersectsBom) {
                    if (isAllowedGoToBom == true)
                        bomberman.move(); // nếu biến trả về true thì cho phép đi qua
                    else {
                        System.out.println("cham bom");
                        bomberman.stay(); // trả về false bắt đứng lại
                    }
                } else {
                    bomberman.move();
                }
                break;
            }
        }

        for (Entity entity : entities) { // duyệt all thực thể
            Rectangle r2 = entity.getBounds();
            boolean biw = false;
            boolean bie = false;
            if (r1.intersects(r2)) { // nếu bomber va chạm với các vật thể thì trả về true
                if (entity instanceof Enemy)
                    bie = true;
                if (bie) {
                    bomberman.stay();
                    System.out.println("cham quai");
                    die();
                }
            }
        }
    }
}