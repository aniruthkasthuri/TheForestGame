package byog.World;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/*
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
*/
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class PlayMusic implements Serializable {
    private Clip clip = null;

    public void play(String path, int delay, int numberOfLoops) {
        if (clip != null) {
            clip.stop();
            clip = null;
        }
        play(path, numberOfLoops);
        return;
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    private void play(String path, int numberOfLoops) {
        File file = new File(path);
        if (file.exists()) {
            try {
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(file));
                clip.start();
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException d) {
                d.printStackTrace();
            }
        }
    }

    public void stop() {
        clip.stop();
    }
}
