package uet.oop.bomberman.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Item extends Entity {
    public Item(int xUnit, int yUnit, Image img, int nLayer) {
        super(xUnit, yUnit, img);
        setLayer(nLayer); // chỉ số va chạm của các Item
    }

    public abstract void update();
}
