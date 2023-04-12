
package tetris;

import javax.swing.JOptionPane;

public class Tetris {
    private static GameForm gf;
    private static LeaderBoard LF;
    private static StartUp SF;
    private static AudioPlayer aud=new AudioPlayer();
    public static void start(){
        gf.setVisible(true);
        gf.startGame();
    }
    public static void showLeaderBoard()
    {
        LF.setVisible(true);
    }
    public static void showStartUp()
    {
        SF.setVisible(true);
    }
    
    public static void gameOver(int score)
    {
        PlayOver();
        String userName=JOptionPane.showInputDialog("Game Over!\nPlease enter your Name");
        gf.setVisible(false);
        LF.addPlayer(userName,score);
    }
    
    public static void PlayClear()
    {
        aud.PlayClearSound();
    }
    public static void PlayOver()
    {
        aud.PlayGameOverSound();
    }
    public static void main(String[] args) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                gf=new GameForm();
                LF=new LeaderBoard();
                SF=new StartUp();
                SF.setVisible(true); 
            }
        });
    }
}
