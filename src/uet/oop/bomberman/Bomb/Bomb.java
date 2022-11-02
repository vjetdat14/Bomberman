package uet.oop.bomberman.Bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character;
import uet.oop.bomberman.entities.Enemy.Enemy;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Still.Brick;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.awt.*;
import java.util.*;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.Bomber.radius;


public class Bomb extends Character {

    private int couterTime = 0;
    boolean no=false;
    public static final List<Flame> flames = new ArrayList<>();


    public Bomb(int xUnit, int yUnit, Image img, int nLayer) {

        super(xUnit, yUnit, img, nLayer);
    }


    public void update() {

        if(couterTime++ == 200)
        {
            Sound sound2 = new Sound();
            sound2.setFile("bomno");
            sound2.play();
        }
        if (couterTime++ < 200) { // thời gian bom sẽ nổ sau khi đặt
                no=true;
            img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, couterTime, 60).getFxImage(); // load ảnh bom trước khi bom nổ
        }else if(couterTime++  >200 && couterTime++<400) {
            bang();
            for (Flame flame : flames) {
                Rectangle r4 = flame.getBounds(); // tạo bound cho enemy
                if(r4.intersects(bomberman.getBounds())) {
                    bomberman.isAlive=false;
                bomberman.die();
                Timer count = new Timer(); // chạy lại trò chơi
                count.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        count.cancel();
                    }
                }, 10, 1);
            }
                for (Enemy enemy : entities) { // duyệt list enemy
                    Rectangle r5 = enemy.getBounds(); // tạo bound cho enemy
                    if (r4.intersects(r5)) { // enemy va chạm flame
                        entities.remove(enemy);
                        score=score+25;
                        System.out.println("quai dinh bom");
                    }
                }
                for (Entity entity : stillObjects) { // duyệt list thuc the
                    Rectangle r5 = entity.getBounds(); // tạo bound cho thuc the
                    if (r4.intersects(r5)) { //
                        if(entity instanceof Brick)
                        stillObjects.remove(entity);
                        score=score+25;
                    }
                }
            }
        }
        if (couterTime++ >400){
            couterTime=0;
            BombermanGame.bomberman.setBombRemain(BombermanGame.bomberman.getBombRemain()+1);
            BombermanGame.bomberman.getBombs().remove(this);
        }


    }

    public void bang() { // hàm nổ
        img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, 200, 60).getFxImage();
    }


    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }

    public Rectangle getBounds() {
        return new Rectangle(x+12, y, Sprite.SCALED_SIZE*3/4 , Sprite.SCALED_SIZE);
    }
}