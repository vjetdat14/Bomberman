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

    protected int boom = 0;

    public Character(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        isAlive = true;
    }
    //Tạo biến speed
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    //Các phương thức di chuyển
    public void goLeft() {
        desX = x - speed;
    }

    public void goRight() {
        desX = x + speed;
    }
    public void goUp() {
        desY = y - speed;
    }

    public void goDown() {
        desY = y + speed;
    }

    // move
    public void move() {
        x = desX;
        y = desY;
    }

    // stop
    public void stay() {
        desX = x;
        desY = y;
    }
    //Tạo bound cho "Character"
    public Rectangle getBounds() {
        return new Rectangle(desX, desY+3, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE*7/8);
    }


}