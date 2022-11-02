package uet.oop.bomberman;

import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Enemy.Balloom;
import uet.oop.bomberman.entities.Enemy.Doll;
import uet.oop.bomberman.entities.Enemy.Kondoria;
import uet.oop.bomberman.entities.Enemy.Oneal;
import uet.oop.bomberman.entities.Still.Brick;
import uet.oop.bomberman.entities.Still.Grass;
import uet.oop.bomberman.entities.Still.Portal;
import uet.oop.bomberman.entities.Still.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.item.*;

import java.io.*;
import java.util.Scanner;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.Entity.WLayer;
import static uet.oop.bomberman.entities.Entity.nLayer;


public class Map {
    public static char[][] ar = new char[13][31];
    public static void createMap() {

        try {
            scanner = new Scanner(new FileReader("res/levels/Level"+ level+".txt")); // đọc file txt chứa thông tin về lv map
        } catch (FileNotFoundException e) { // bắt ngoại lệ
            e.printStackTrace();
        }
        scanner.nextInt(); // nhận vào số nguyên
        HEIGHT = scanner.nextInt(); // nhận vào chỉ số về chiều cao của map
        WIDTH = scanner.nextInt(); // nhận vào chỉ số về chiều rộng của map
        scanner.nextLine();
        for (int i = 0; i < HEIGHT; i++) { // quét các dòng tiếp rồi nhập hết vào string r để duyệt sau đó xuất ảnh đêt load map
            String r = scanner.nextLine();
            for (int j = 0; j < WIDTH; j++) {
                ar[i][j] = r.charAt(j);
                if (r.charAt(j) == '#') {
                    stillObjects.add(new Wall(j, i, Sprite.wall.getFxImage()));
//                } else {
//                    stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                }
                 if (r.charAt(j) == '*') {
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage(), WLayer));
                    }
                    else if (r.charAt(j) == 'x') {
                        stillObjects.add(new Portal(j, i, Sprite.portal.getFxImage()));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage(),nLayer));
                    }
                    else if (r.charAt(j) == '1') {
                        entities.add(new Balloom(j, i, Sprite.balloom_left1.getFxImage()));
                    }
                    else if (r.charAt(j) == '2') {
                        entities.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage()));
                    }
                else if (r.charAt(j) == '3') {
                        entities.add(new Doll(j, i, Sprite.doll_left1.getFxImage()));
                    }
                else if (r.charAt(j) == '4') {
                        entities.add(new Kondoria(j, i, Sprite.doll_left1.getFxImage()));
                    }
                else if (r.charAt(j) == 'b') {
                        stillObjects.add(new BombItem(j, i, Sprite.powerup_bombs.getFxImage(),nLayer));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage(), WLayer));
                    }
                else if (r.charAt(j) == 'f') {
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage(), WLayer));
                        stillObjects.add(new FlameItem(j, i, Sprite.powerup_flames.getFxImage(), bomberman.getLayer()));

                    }
                else if (r.charAt(j) == 's') {
                        stillObjects.add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage(), bomberman.getLayer()));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage(), nLayer+1));
                    }
                else if (r.charAt(j) == 'h') {
                        stillObjects.add(new DetonatorItem(j, i, Sprite.powerup_detonator.getFxImage(),nLayer));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage(), nLayer+1));
                    }
                else if (r.charAt(j) == 'w') {
                        stillObjects.add(new WallPassItem(j, i, Sprite.powerup_wallpass.getFxImage(),bomberman.getLayer()));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage(), nLayer+1));
                    }
                else if (r.charAt(j) == 'k') {
                        stillObjects.add( new EnemiKillItem(j, i, Sprite.powerup_flamepass.getFxImage(),bomberman.getLayer()));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage(), nLayer+1));
                    }
                    else if (r.charAt(j) == 'p') {
                        bomberman = new Bomber(j, i, Sprite.player_right.getFxImage());
                        xStart = j;
                        yStart = i;
                        stillObjects.add(bomberman);
                }
            }
        }
        stillObjects.sort(new Layer()); // các thực thể dc tạo chỉ số va chạm
    }
}