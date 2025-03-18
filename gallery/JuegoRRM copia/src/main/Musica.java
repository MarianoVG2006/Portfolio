package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Musica {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Musica(){
        soundURL[0] = getClass().getResource("/musica/game-music-player-console-8bit-background-intro-theme-297305.wav");
        soundURL[1] = getClass().getResource("/musica/cerrar-puerta-81438.wav");
        soundURL[2] = getClass().getResource("/musica/abrir-puerta-80075.wav");
        soundURL[3] = getClass().getResource("/musica/coin-257878.wav");
        soundURL[4] = getClass().getResource("/musica/camera-flash-204151.wav");
        //soundURL[5] = getClass().getResource("/musica/Eu Basta Eu eubasta futbol.wav");
    }

    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {

        }
    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    public void parar(){
        clip.stop();
    }


}
