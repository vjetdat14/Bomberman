package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character;
import uet.oop.bomberman.entities.Enemy.AI.AiLow;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.BombermanGame;

import java.util.Random;

public class Balloom extends Enemy {
/**
 * Balloon là Enemy đơn giản nhất di chuyển ngẫu nhiên với vận tốc cố định (1)
 */
    public int t;
        public Balloom(int x, int y, Image img){
            super(x, y, img);
            AiLow ai = new AiLow();
            setSpeed(1);
            t = ai.calDirection();
        }

    @Override
    public void update() {
//            Timer count = new Timer();
//
//        count.schedule(new TimerTask() {
//            @Override
//            public void run() {
//            int t = new Random().nextInt(2);
//            if (t == 0) {
//                    goRight();
//                } else {
//                    goLeft();
//                }
//            }
//        }, 200, 1);
//    }
//        int timeAnimation = 0;
//        int t = new Random().nextInt(2);
        if (BombermanGame.countTime%200>=100) {
                    goRight();
                } else {
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

