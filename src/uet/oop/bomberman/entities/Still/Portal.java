package uet.oop.bomberman.entities.Still;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character;

public class Portal extends Character {
    public Portal(int x, int y, Image image) {
        super(x, y, image, nLayer);
        setLayer(3); // chỉ số va cham của portal
    }

    @Override
    public void update() {

    }
}


