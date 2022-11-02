package uet.oop.bomberman.Bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;


public class FlameDown extends Flame {

    int radius;
    int couterTime=0;
    public FlameDown(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public FlameDown(int xUnit, int yUnit, Image img, int radius) {
        super(xUnit, yUnit, img);
        this.radius = radius; // cài chỉ số bán kính nổ

    }

    public void update() {

        if(couterTime >60 && couterTime<100)
            bang();
        else if (couterTime>100) {
            Bomb.flames.remove(this);

        }
    }

@Override
    public void bang() { // hàm nổ
        img = Sprite.movingSprite(Sprite.explosion_vertical_down_last,
                Sprite.explosion_vertical_down_last1,
                Sprite.explosion_vertical_down_last2,
                couterTime, 20).getFxImage();
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }
    public void Exploded() {
        // Up Flame Update
        System.out.println(radius);
//        for (int j = UpFlame.size() - 1; j >= 0; j--) {
//            UpFlame.remove(j);
//            DownFlame.remove(j);
//            RightFlame.remove(j);
//            LeftFlame.remove(j);
//        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x+12, y, Sprite.SCALED_SIZE*3/4 , Sprite.SCALED_SIZE);
    }
}