package uet.oop.bomberman;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class Sound {
    private Media media;
    private MediaPlayer MP;
    public Sound(String path) {
        media = new Media(Paths.get(path).toUri().toString());
        MP = new MediaPlayer(media);
    }
    public MediaPlayer getMediaPlayer() {
        return MP;
    }
}
