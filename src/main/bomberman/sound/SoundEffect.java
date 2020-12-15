package sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundEffect {
    public static SoundEffect BACKGROUND = new SoundEffect("/music/background.wav");

    public static enum Volume {
        MUTE, LOW, MEDIUM, HIGH
    }

    public static Volume volume = Volume.HIGH;

    private Clip clip;

    public SoundEffect(String soundFileName) {
        try {
            URL url = SoundEffect.class.getResource(soundFileName);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play(Boolean loop) {
        if (volume != Volume.MUTE) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            clip.start();
            if(loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }

    }

    public void stop() {
        clip.stop();
        clip.setFramePosition(0);
    }

    public void mute() {
        volume = Volume.MUTE;
    }

    public boolean isPlaying() {
        return clip.isRunning();
    }
}
