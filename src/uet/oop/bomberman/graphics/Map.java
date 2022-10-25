package uet.oop.bomberman.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.net.URISyntaxException;
import java.util.LinkedList;

public class Map {
    protected LinkedList<Image> map;

    protected int[][] mapHash;

    protected Block[][] tileMap;
    protected LinkedList<Point> listOne;
    protected Item item;
    protected Point itemLocation;
    private Point portal;

    public Map(int[][] mapHash, Item item, Point portal) {
        this.item = item;
        this.portal = portal;
        this.cpyHash(mapHash);
        this.map = new LinkedList();
        this.tileMap = new Block[mapHash.length][mapHash[0].length];

        for (int i = 0; i < this.tileMap.length; ++i) {
            for (int j = 0; j < this.tileMap[i].length; ++j) {
                this.tileMap[i][j] = new Block((double) (j * 32), (double) (i * 32), 32.0D, 32.0D);
            }
        }

        try {
            this.map.add(new Image(this.getClass().getResource("/res/map/wall1.png").toURI().toString()));
            this.map.add(new Image(this.getClass().getResource("/res/map/wall2.png").toURI().toString()));
            this.map.add(new Image(this.getClass().getResource("/res/map/wall3.png").toURI().toString()));
            this.map.add(new Image(this.getClass().getResource("/res/map/port.png").toURI().toString()));
        } catch (URISyntaxException var5) {
            var5.printStackTrace();
        }
    }
}
