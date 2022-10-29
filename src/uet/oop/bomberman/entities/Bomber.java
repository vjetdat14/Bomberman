package uet.oop.bomberman.entities;

import com.sun.xml.internal.bind.v2.util.CollisionCheckStack;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import java.awt.*;

public class Bomber extends Character {

    private KeyCode key = null; // khai báo phím bấm


    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        setSpeed(3); // tốc độ
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

    }

    // cout --
    // khai báo các sự kiện về bàn phím
    public void handleKeyPressedEvent(KeyCode keyCode) {

        if (keyCode == KeyCode.A || keyCode == KeyCode.D
                || keyCode == KeyCode.W || keyCode == KeyCode.S) {
            this.key = keyCode;
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

    public Rectangle getBounds() { // tạo bao cho bomber
        return new Rectangle(desX + 3, desY + 5, Sprite.SCALED_SIZE-15, Sprite.SCALED_SIZE*3/4);
    }

    public void handleCollisions() {
        Rectangle r1 = BombermanGame.bomberman.getBounds(); // tạo bound cho bomber
        for (Entity stillObject : BombermanGame.stillObjects) { // duyệt all thực thể
            Rectangle r2 = stillObject.getBounds();  // tạo bao cho all thực thể
            if (r1.intersects(r2)) { // nếu bomber va chạm với các vật thể thì trả về true
                if (stillObject instanceof Wall) {
                    BombermanGame.bomberman.stay();
                } else {
                    BombermanGame.bomberman.move();
                }
            }
        }
    }
}