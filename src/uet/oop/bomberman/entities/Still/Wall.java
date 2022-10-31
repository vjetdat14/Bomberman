package uet.oop.bomberman.entities.Still;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character;
import uet.oop.bomberman.entities.Entity;

public class Wall extends Character {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
        setLayer(10);
    }

    @Override
    public void update() {

    }


}
