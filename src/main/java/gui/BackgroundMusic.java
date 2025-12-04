/*package gui;

import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.*;

public class BackgroundMusic {

    private static Clip clip;

    public static void playMusic() {
        try {
            // Load the resource from the classpath
            InputStream audioSrc = BackgroundMusic.class.getResourceAsStream("/ThemeSong.wav");
            if (audioSrc == null) {
                System.err.println("Music file not found!");
                return;
            }

            // Convert it to AudioInputStream
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                    new java.io.BufferedInputStream(audioSrc)
            );

            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}


 */