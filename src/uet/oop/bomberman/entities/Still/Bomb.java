package uet.oop.bomberman.entities.Still;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


public class Bomb extends Character {
    private int timeCounter = 0;
    int radius;

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Bomb(int xUnit, int yUnit, Image img, int radius) {
        super(xUnit, yUnit, img);
        this.radius = radius; // cài chỉ số bán kính nổ
    }

    @Override
    public void update() {
        if (timeCounter++ == 200) { // thời gian bom sẽ nổ sau khi đặt
            bang();
        }
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, timeCounter, 60).getFxImage(); // load ảnh bom trước khi bom nổ


    }

    public void bang() { // hàm nổ
//        Flame e = new Flame(x, y); // tạo flame khi bom nổ
//        e.setRadius(radius); // bán kính nổ
//        e.render_explosion(); // chạy các chức năng của Flame
//        alive = false;
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }

    public Rectangle getBounds() {
        return new Rectangle(x + 5, y, Sprite.SCALED_SIZE * 3 / 5, Sprite.SCALED_SIZE * 3 / 5);
    }
}