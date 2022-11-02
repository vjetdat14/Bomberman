package uet.oop.bomberman.entities.Still;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Character {
    public Brick(int xUnit, int yUnit, Image img, int nLayer) {
        super(xUnit, yUnit, img, nLayer);
        setLayer(nLayer); // chỉ số va chạm của brick
        isAlive = true; // thuộc tính chỉ còn hay đã mất
    }

    @Override
    public void update() {
        if(!isAlive()){
            if(animated < 45) { // đại diện cho tốc độ load ảnh (ở đây cụ thể là load lúc biến mất)
                animated++;
                img = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1
                        , Sprite.brick_exploded2, animated, 45).getFxImage();
            } else
                BombermanGame.stillObjects.remove(this);//xóa brick
        }
    }

}
