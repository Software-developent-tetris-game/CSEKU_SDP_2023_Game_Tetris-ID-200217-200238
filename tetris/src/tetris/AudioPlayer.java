package tetris;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
    private String soundFolder="AudioFiles"+File.separator;
    private String ClearSoundPath=soundFolder+"clear.wav";
    private String GameOverSoundPath=soundFolder+"GameOver.wav";
    private Clip ClearSound,GameOverSound;
    
    public AudioPlayer()
    {
        try {
            ClearSound=AudioSystem.getClip();
            GameOverSound=AudioSystem.getClip();
            ClearSound.open(AudioSystem.getAudioInputStream(new File(ClearSoundPath).getAbsoluteFile()));
            GameOverSound.open(AudioSystem.getAudioInputStream(new File(GameOverSoundPath).getAbsoluteFile()));
        } catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch(UnsupportedAudioFileException | IOException ex){
            
        }
        
            
          
         
        
    }
    
    
    public void PlayClearSound()
    {
        ClearSound.setFramePosition(0);
        ClearSound.start();
    }
    
    public void PlayGameOverSound()
    {
        GameOverSound.setFramePosition(0);
        GameOverSound.start();
    }
}
