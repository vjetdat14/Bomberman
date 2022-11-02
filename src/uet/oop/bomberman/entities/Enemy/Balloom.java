package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Enemy.AI.AiLow;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.BombermanGame;

import java.util.Timer;
import java.util.TimerTask;

public class Balloom extends Enemy {
/**
 * Balloon là Enemy đơn giản nhất di chuyển ngẫu nhiên với vận tốc cố định (1)
 */
    private int couterTime = 0;
    public static AiLow ai = new AiLow();
        public Balloom(int x, int y, Image img){
            super(x, y, img);

            setSpeed(10);
        }

    @Override
    public void update() {
            int t = couterTime++ % 400;
            if (t <100) {
                goRight();
            } else if (t <200) {
                goLeft();
            } else if (t <300) {
                goDown();
            } else {
                goUp();
            }
        }
    @Override
    public void goLeft() {
                super.goLeft();
                if (canMove)
                img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, x--, 200).getFxImage();
        }
            @Override
    public void goRight(){
            super.goRight();
            if (canMove)
            img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, x++, 200).getFxImage();
        }
        @Override
        public void goUp() {
        super.goUp();
            if (canMove)
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, y--, 200).getFxImage();
    }
    @Override
    public void goDown(){
        super.goDown();
        if (canMove)
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, y++, 200).getFxImage();
    }
}

