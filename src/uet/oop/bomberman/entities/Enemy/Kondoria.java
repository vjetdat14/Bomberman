package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Kondoria extends Enemy {

    public int t;
        public Kondoria(int x, int y, Image img){
            super(x, y, img);
            setSpeed(1);
            int t = new Random().nextInt(10);
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
                img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, x--, 200).getFxImage();
                move();
            }
            @Override
    public void goRight(){
            super.goRight();
            img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, x++, 200).getFxImage();
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

