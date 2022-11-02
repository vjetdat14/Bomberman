package uet.oop.bomberman.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character;
import uet.oop.bomberman.entities.Entity;

public abstract class Flame extends Entity {
    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public abstract void update();
    public void bang(){
    }
}
