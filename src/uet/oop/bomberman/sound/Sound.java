package uet.oop.bomberman.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound () {
        soundURL[0] = getClass().getResource("/music/URF.wav");
        soundURL[1] = getClass().getResource("/music/bom_explode.wav");
        soundURL[2] = getClass().getResource("/music/bom_set.wav");
        soundURL[3] = getClass().getResource("/music/die.wav");
        soundURL[4] = getClass().getResource("/music/eat_item.wav");
        soundURL[5] = getClass().getResource("/music/level_up.wav");
        soundURL[6] = getClass().getResource("/music/enemy_die.wav");
        soundURL[7] = getClass().getResource("/music/bomberman_die.wav");
        soundURL[8] = getClass().getResource("/music/soundtrack.wav");
    }

    public void setFile(String name) {
        int position = getPosition(name);
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[position]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void playMusic() {
        play();
        loop();
    }

    public int getPosition(String name) {
        if (name == "playGame") {
            return 0;
        } else if (name == "bomno") {
            return 1;
        } else if (name == "datbom") {
            return 2;
        } else if (name == "gameover") {
            return 3;
        } else if (name == "eatitem") {
            return 4;
        } else if (name == "levelup") {
            return 5;
        }
        else if (name == "enemydie" ) {
            return 6;
        }
        else if (name == "bomberdie" ) {
            return 7;
        }
        else if (name == "ingame" ) {
            return 7;
        }
        return -1;
    }
}