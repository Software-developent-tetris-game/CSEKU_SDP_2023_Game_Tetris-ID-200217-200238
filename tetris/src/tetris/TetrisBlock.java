package tetris;

import java.awt.Color;
import java.util.Random;

public class TetrisBlock {
    private int [][] shape;
    private Color color;
    private int x,y;
    private int [][][] shapes;
    private int currentrot=0;
    private Color colors[]={Color.green,Color.RED,Color.BLUE,Color.yellow};
    public TetrisBlock(int [][] shape) {
        this.shape=shape;
      
        initShapes();
    }
    private void initShapes(){
        shapes=new int [4][][];
        for(int i=0;i<4;i++){
            int r=shape[0].length;
            int c=shape.length;
            shapes[i]=new int [r][c];
            for(int y=0;y<r;y++){
                for(int x=0;x<c;x++){
                    shapes[i][y][x]=shape[c-x-1][y];
                }
            }
            shape=shapes[i];
        }
    }
    public void spwan(int gridWidth){
        Random r=new Random();
        color=colors[r.nextInt(colors.length)];
        currentrot=0;
        shape=shapes[currentrot];
        y=-getHeight();
        x=r.nextInt(gridWidth-getWidth());
        
    }
    
    public int[][] getShape()
    {
        return shape;
    }
    
    public Color getColor()
    {
        return color;
    }
    public int getHeight()
    {
        return shape.length;
    }
    public int getWidth()
    {
        return shape[0].length;
    }
    public int getX(){
        return x;
    }
    
    public void setX(int newX)
    {
        x=newX;
    }
    
    public int getY(){
        return y;
    }
    public void setY(int newY)
    {
        y=newY;
    }
    public void moveDown(){y++;}
    public void moveRight(){x++;}
    public void moveLeft(){x--;}
    public void rotate(){
        currentrot=(currentrot+1)%4;
        shape=shapes[currentrot];
    }
    public int getBottoEdge(){
        return y+getHeight();
    }
    public int getRightEdge(){
        return x+getWidth();
    }
    public int getLeftEgde(){
        return x;
    }
}
