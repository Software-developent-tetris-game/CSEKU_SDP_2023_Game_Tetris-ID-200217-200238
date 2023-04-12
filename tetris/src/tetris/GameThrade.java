package tetris;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThrade extends Thread {
    private GameArea gA;
    private GameForm gF;
    private int score=0;
    private int level=1;
    private int scoreLevel=3;
    private int waitTime=1000;
    private int dereaseTime=100;
    public GameThrade(GameArea gA,GameForm gF){
        this.gF=gF;
        this.gA=gA;
        
        gF.updateScore(score);
        gF.updateLavel(level);
    }
    @Override
    public void run(){
        
    while(true){
        gA.spawnBlock();
        gA.moveDown(); 
        
        while(gA.moveDown()==true)
        {
             try {  
                Thread.sleep(waitTime);
            } catch (InterruptedException ex) {
                return;
            }
        }
        if(gA.isBlockOutofBound())
        {
            Tetris.gameOver(score);
            break;
        }
        gA.moveBlockToBackground();
        score+=gA.clearLine();
        gF.updateScore(score);
        int l =score/scoreLevel+1;
        if(l>level){
            level=l;
            gF.updateLavel(level);
            waitTime-=dereaseTime;
        }
     }
    }
}
