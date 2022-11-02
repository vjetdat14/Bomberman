package uet.oop.bomberman.Bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FlameLeft extends Flame {

    int couterTime=0;
    public FlameLeft(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public FlameLeft(int xUnit, int yUnit, Image img, int radius) {
        super(xUnit, yUnit, img);


    }

    public void update() {


        if(couterTime ++ >65 && couterTime< 100)
            bang();
        else if (couterTime>100) {
            Bomb.flames.remove(this);

        }
    }

@Override
    public void bang() { // hàm nổ
        img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last,
                Sprite.explosion_horizontal_left_last1,
                Sprite.explosion_horizontal_left_last2,
                couterTime, 20).getFxImage();    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }

    public Rectangle getBounds() {
        return new Rectangle(x+12, y, Sprite.SCALED_SIZE*3/4 , Sprite.SCALED_SIZE);
    }
}