package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Character;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Oneal extends Enemy {
    /**
     * Oneal là enemy có tốc độ di chuyển thay đổi và đuổi theo bomber
     */
    private int couterTime = 0;
    public int t;
        public Oneal(int x, int y, Image img){
            super(x, y, img);
            setSpeed(2);
            int t = new Random().nextInt(10);
        }

    @Override
    public void update() {
        if (couterTime++ >=1) {
            couterTime =0;
            if (BombermanGame.bomberman.getX() == this.x && BombermanGame.bomberman.getY() >= this.y)
                goDown();
             if (BombermanGame.bomberman.getX() == this.x && BombermanGame.bomberman.getY() <=this.y)
                goUp();
             if (BombermanGame.bomberman.getX() >=this.x && BombermanGame.bomberman.getY() == this.y)
                goRight();
             if (BombermanGame.bomberman.getX() == this.x && BombermanGame.bomberman.getY() < this.y)
                goLeft();
            if (BombermanGame.bomberman.getX() < this.x && BombermanGame.bomberman.getY() > this.y) {
                goDown();
            }
              if (BombermanGame.bomberman.getY() <= this.y && BombermanGame.bomberman.getX() < this.x) {
                goLeft();
            } else
                goRight();
        }
    }

    @Override
    public void goLeft() {
                super.goLeft();
                img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, x--, 200).getFxImage();
                move();
            }
            @Override
    public void goRight(){
            super.goRight();
            img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, x++, 200).getFxImage();
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

