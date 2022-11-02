package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;


import uet.oop.bomberman.Bomb.*;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.entities.Enemy.Enemy;
import uet.oop.bomberman.entities.Still.Brick;
import uet.oop.bomberman.entities.Still.Portal;
import uet.oop.bomberman.entities.Still.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.item.*;
import uet.oop.bomberman.sound.Sound;

import java.awt.*;
import java.util.*;
import java.util.List;

import static uet.oop.bomberman.Bomb.Bomb.flames;
import static uet.oop.bomberman.BombermanGame.*;

public class Bomber extends Character {

    private KeyCode key = null; // khai báo phím bấm

    public static int bombRemain; // khai báo biến "số bom dữ trự"
    private boolean placeBombCommand = false; // quản lý về việc đặt bom (trả về true or false)
    private boolean isAllowedGoToBom = false; // quản lý việc đi xuyên qua bom (trả về true hoặc false)
    private final List<Bomb> bombs = new ArrayList<>(); // khai báo list quản lý bom
    public static int radius; // biến bán kính nổ
    public static int timeAfterDie = 0;     //tg sau khi chết

    public Sound sound1 = new Sound();

    public Bomber(int x, int y, Image img) {
        super(x, y, img, nLayer);
        setLayer(1); // chỉ số va chạm của bomber
        setSpeed(startSpeed); // tốc độ
        setBombRemain(1); // số bom dự trữ
        setRadius(1); // bán kính nổ
        life = 3;
    }

    private void setRadius(int i) {
        this.radius = i;
    }

    public void setBombRemain(int bombRemain) { // cài số lượng bom dự trữ
        this.bombRemain = bombRemain;
    }

    public int getBombRemain() { // cài số lượng bom dự trữ
        return this.bombRemain;
    }

    public List<Bomb> getBombs() { // trả về list bomb
        return bombs;
    }

    public void removeBomb(Bomb bomb) {
        bombs.remove(bomb);
    }

    public boolean isAlive() { // trả về còn sống hay đã chết
        return isAlive;
    }

    // điều khiển nhân vật
    @Override
    public void update() {

        if (key == KeyCode.A) {
            goLeft();
        }
        if (key == KeyCode.D) {
            goRight();
        }
        if (key == KeyCode.W) {
            goUp();
        }
        if (key == KeyCode.S) {
            goDown();
        }
        if (placeBombCommand) {
            placeBomb();
        }
        for (int i = 0; i < bombs.size(); i++) { // duyệt cái list của bomb
            Bomb bomb = bombs.get(i);
            if (!bomb.isAlive()) {
                bombs.remove(bomb);
                bombRemain++;
            }
        }
        //animate();
        if (!isAlive()) {
            timeAfterDie++;
            die();
        }


    }

    private void placeBomb() {
        if (bombRemain > 0) {
            int xB = (int) Math.round((x + 4) / (double) Sprite.SCALED_SIZE);// tọa độ bom
            int yB = (int) Math.round((y + 4) / (double) Sprite.SCALED_SIZE);//
            for (Bomb bomb : bombs) { // duyệt list bombs
                if (xB * Sprite.SCALED_SIZE == bomb.getX() && yB * Sprite.SCALED_SIZE == bomb.getY()) return;
            }
            bombs.add(new Bomb(xB, yB, Sprite.bomb.getFxImage(),radius)); // tạo bom và add vào list bomb
            for (int i = 1; i <=radius; i++) {
                if (i == radius+1) {
                    flames.add(new FlameLeft(xB - i, yB, null));
                    flames.add(new FlameRight(xB + i, yB, null));
                    flames.add(new FlameUp(x, yB - i, null));
                    flames.add(new FlameDown(x, yB + i, null));
                } else {
                    flames.add(new FlameHORIZON(xB - i, yB, null));
                    flames.add(new FlameHORIZON(xB + i, yB, null));
                    flames.add(new FlameVERTICAL(xB, yB - i, null));
                    flames.add(new FlameVERTICAL(xB, yB + i, null));
                }
            }
            isAllowedGoToBom = true; // xuyên qua bom trả về true
            bombRemain--;
            Sound sound1 = new Sound();
            sound1.setFile("datbom");
            sound1.play();
        }
    }

    public void handleKeyPressedEvent(KeyCode keyCode) {

        if (keyCode == KeyCode.A || keyCode == KeyCode.D
                || keyCode == KeyCode.W || keyCode == KeyCode.S) {
            this.key = keyCode;
        }
        if (keyCode == KeyCode.SPACE) {
            placeBombCommand = true;
        }
    }

    // điều khiển bomber di chuyển và đặt bomb từ bàn phím (phương thức này sd để load ảnh hiển thị) (xử lý các sự kiện bàn phím)
    public void handleKeyReleasedEvent(KeyCode keyCode) {
        if (key == keyCode) {
            if (key == KeyCode.A) {
                img = Sprite.player_left.getFxImage();
            }
            if (key == KeyCode.D) {
                img = Sprite.player_right.getFxImage();
            }
            if (key == KeyCode.W) {
                img = Sprite.player_up.getFxImage();
            }
            if (key == KeyCode.S) {
                img = Sprite.player_down.getFxImage();
            }
            key = null;
        }
        if (keyCode == KeyCode.SPACE) {
            placeBombCommand = false;
        }

    }

    // các load ảnh hiển thị di chuyển
    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, left++, 20).getFxImage();
        handleCollisions();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, right++, 20).getFxImage();
        handleCollisions();
    }

    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, up++, 20).getFxImage();
        handleCollisions();
    }

    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, down++, 20).getFxImage();
        handleCollisions();
    }

    public void die() {
        if (timeAfterDie++ <= 45) { // load ảnh bomber chết trong 45 đơn vị thời gian
            img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2,
                    Sprite.player_dead3, timeAfterDie, 15).getFxImage();
        }
        if (timeAfterDie++ == 45) {
            bomberman.setY(48);
            bomberman.setX(48);
            sound1.setFile("bomberdie");
            sound1.play();
            timeAfterDie=0;
            life--;
            score= score-50;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(desX, desY + 5, Sprite.SCALED_SIZE - 10, Sprite.SCALED_SIZE * 3 / 4);
    }


    public void handleCollisions() {
        Rectangle r1 = bomberman.getBounds(); // tạo bound cho bomber

        List<Bomb> bombs = bomberman.getBombs(); // tạo list bomb
        boolean bomberIntersectsBom = false; // biến kiểm tra va chạm bomber và bom
        for (Bomb bomb : bombs) {
            Rectangle r2 = bomb.getBounds(); // tạo bound cho bomb
            if (r1.intersects(r2)) {
                bomberIntersectsBom = true; // trả về true nếu bomber va chạm với bomb
            }
        }
        for (Entity stillObject : BombermanGame.stillObjects) { // duyệt all thực thể
            Rectangle r2 = stillObject.getBounds();
            boolean biw = false;
            boolean bibr = false;
            boolean bip = false;
            boolean bibi = false;
            boolean bidi = false;
            boolean biei = false;
            boolean bifi = false;
            boolean bisi = false;
            boolean biwpi = false;
            if (r1.intersects(r2)) { // nếu bomber va chạm với các vật thể thì trả về true
                if (stillObject instanceof Wall)
                    biw = true;
                if (stillObject instanceof Brick)
                    bibr = true;
                if (stillObject instanceof Portal)
                    bip = true;
                if (stillObject instanceof DetonatorItem)
                    bidi = true;
                if (stillObject instanceof EnemiKillItem)
                    biei = true;
                if (stillObject instanceof FlameItem)
                    bifi = true;
                if (stillObject instanceof BombItem)
                    bibi = true;
                if (stillObject instanceof SpeedItem)
                    bisi = true;
                if (stillObject instanceof WallPassItem)
                    biwpi = true;
                if (biw) {
                    bomberman.stay();
                    System.out.println("cham tuong");
                }
                if (bibr) {
                    bomberman.stay();
                    System.out.println("cham gach");
                }
                if (bip) {
                    if (entities.size() <=0) {
                        System.out.println("Level Up");
                        check = true;
                    } else {
                        bomberman.stay();
                        System.out.println("cham cong");
                    }
                }
                if (!bomberIntersectsBom) { // chỉ số va chạm của bomber > grass tại ví trí đặt bom và người ko va chạm vs bom
                    bomberman.move(); // cho phép đi qua bom
                    isAllowedGoToBom = false; // trả về false -> ko cho phép vượt qua bom nữa
                } else
                if (bomberIntersectsBom) {
                    if (isAllowedGoToBom) {
                        bomberman.move(); // nếu biến trả về true thì cho phép đi qua
                    } else {
                        System.out.println("cham bom");
                        bomberman.stay(); // trả về false bắt đứng lại
                    }
                    break;
                }
                bomberman.move();
            }
//            if (stillObject instanceof Item) { // nêú chỉ số va chạm của bomber = thực thể và thực thể đó là item
//
                if (bibi) {
                    sound1.setFile("eatitem");
                    sound1.play();
                    System.out.println("an item");
                    startBomb++;
                    bomberman.setBombRemain(startBomb); // set up lại số bom dự trữ
                    stillObjects.remove(stillObject); // xóa vật thể đó khỏi list thực thể (chính là hành động ăn item)
                    break;
                } else if (bisi) { // tương tự bombitem
                    sound1.setFile("eatitem");
                    sound1.play();
                    System.out.println("an item");
                    bomberman.setSpeed(startSpeed++);
                    stillObjects.remove(stillObject);
                    break;
                } else if (bifi) { // tương tự bombitem
                    sound1.setFile("eatitem");
                    sound1.play();
                    System.out.println("an item");
                    startFlame++;
                    System.out.println(startFlame);
                    bomberman.setRadius(startFlame);
                    stillObjects.remove(stillObject);
                    break;
                } else if (bidi) { // tương tự bombitem
                    sound1.setFile("eatitem");
                    sound1.play();
                    System.out.println("an item");
                    life++;
                    stillObjects.remove(stillObject);
                    break;
                } else if (biwpi) { // tương tự bombitem
                    sound1.setFile("eatitem");
                    sound1.play();
                    System.out.println("an item");
                    nLayer++;
                    bomberman.setLayer(nLayer);
                    stillObjects.remove(stillObject);
                    break;
                } else if (biei) { // tương tự bombitem
                    sound1.setFile("eatitem");
                    sound1.play();
                    System.out.println("an item");
                    pass = true;
                    stillObjects.remove(stillObject);
                    break;
                }

        }

        for (Enemy enemy : entities) { // duyệt all thực thể
            Rectangle r3 = enemy.getBounds();
            boolean bie = false;
            if (r1.intersects(r3)) { // nếu bomber va chạm với các vật thể thì trả về true
                bie = true;
                if (bie) {
                    bomberman.setAlive(false);
                    System.out.println("chet");
                    die();
                    bomberman.stay();
                    Timer count = new Timer(); // chạy lại trò chơi
                    count.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            count.cancel();
                        }
                    }, 10, 1);
                }
                break;
            }

            enemy.canMove=true;
            for (Bomb bomb : bombs) { // duyệt list bomb
                Rectangle r5 = bomb.getBounds(); // tạo bound cho bomb
                if (r3.intersects(r5)) { // enemy va chạm bomb
                    enemy.canMove = false;
                    System.out.println("quai dinh bom");
                }
                    else
                    enemy.canMove =true;
                }

            for (Entity entity : stillObjects) { // duyệt list bomb
                Rectangle r6 = entity.getBounds(); // tạo bound cho bomb
                if (r3.intersects(r6)) { // enemy va chạm bomb
                    if(entity instanceof Wall || entity instanceof Brick)
                    enemy.canMove = false;
                }
                else
                    enemy.canMove =true;
            }
        }

        }
    }
