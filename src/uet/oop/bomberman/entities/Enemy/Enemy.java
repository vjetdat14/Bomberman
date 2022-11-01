package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character;

public abstract class Enemy extends Character {
    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
}
