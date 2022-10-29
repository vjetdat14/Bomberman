package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;


public abstract class Character extends Entity {
    protected int desX = x;
    protected int desY = y;
    protected int desZ;
    protected int speed;
    protected int left = 0;
    protected int right = 0;
    protected int up = 0;
    protected int down = 0;

    public Character(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        isAlive = true;
    }
    //Tạo biến speed
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    //Các phương thức di chuyển của "Character"
    public void goLeft() {
        desZ = x;
        desX = x - speed;
    }

    public void goRight() {
        desZ = x;
        desX = x + speed;
    }
    public void goUp() {
        desZ = y;
        desY = y - speed;
    }

    public void goDown() {
        desZ = y;
        desY = y + speed;
    }

    // Cho phép di chuyển
    public void move() {
        x = desX;
        y = desY;
    }

    // dừng
    public void stay() {
//        desX = x;
//        desY = y;
        if (desZ == desX + speed) {
            desX = x + speed;
            desY = y;
        } else if (desZ == desX - speed) {
            desX = x - speed;
            desY = y;
        } else if (desZ == desY + speed) {
            desX = x;
            desY = y + speed;
        } else {
            desX = x;
            desY = y - speed;
        }
//        speed = 0;
    }
    //Tạo bound cho "Character"
    public Rectangle getBounds() {
        return new Rectangle(desX, desY, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }


}
