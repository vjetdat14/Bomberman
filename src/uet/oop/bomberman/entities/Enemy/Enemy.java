package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character;

public abstract class Enemy extends Character {
    public int spriteIndex = 0;
    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img, nLayer);
    }

    public void goRandom() {
        spriteIndex++;
        int rand = (int) (Math.random() * 16);
        switch (rand % 4) {
            case 0: goLeft();
            case 1: goRight();
            case 2: goUp();
            case 3: goDown();
        }
    }
}
