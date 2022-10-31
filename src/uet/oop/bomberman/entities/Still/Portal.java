package uet.oop.bomberman.entities.Still;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character;
import uet.oop.bomberman.entities.Entity;

public class Portal extends Entity {
    public Portal(int x, int y, Image image) {
        super(x, y, image);
        setLayer(3); // chỉ số va cham của portal
    }

    @Override
    public void update() {

    }
}


