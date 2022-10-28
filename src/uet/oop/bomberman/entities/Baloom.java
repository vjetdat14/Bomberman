package uet.oop.bomberman.entities;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.BombermanGame;

import java.util.Random;

public class Baloom extends Character {

    public int t;
        public Baloom(int x, int y, Image img){
            super(x, y, img);
            setSpeed(1);
            int t = new Random().nextInt(10);
        }

    @Override
    public void update() {
        int timeAnimation = 0;
        int t = new Random().nextInt(2);
        if (t == 0) {
            timeAnimation++;
            if (timeAnimation == 1) {
                t = new Random().nextInt(2);
                timeAnimation = 0;
                    goRight();
                } else {
                    goLeft();
                }

            }else
            {
                goLeft();
            }
        }

            @Override
    public void goLeft() {
                super.goLeft();
                img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, x--, 200).getFxImage();
                move();
            }
            @Override
    public void goRight(){
            super.goRight();
            img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, x++, 200).getFxImage();
            move();
        }
        public void moving(){
            switch (t){
                case 0:goLeft();
                case 1: goRight();
                case 2: goLeft();
                case 3: goRight();
            }

}
}
