package tetris;

import Blocks.*;
import Blocks.IShape;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class GameArea extends JPanel {
    
    private int gridRow;
    private int gridCollumn;
    private int gridCellsize;
    private Color[][] backGround;
    
    private TetrisBlock block;
    private TetrisBlock[] blocks;
       
    public GameArea(JPanel placeholder,int collumns){
        //placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());
      
        gridCollumn=collumns;
        gridCellsize=this.getBounds().width/gridCollumn;
        gridRow=this.getBounds().height/gridCellsize;
        
        blocks=new TetrisBlock[] {
            new BoxBlock(),
            new IShape(),
            new JBlock(),
            new LBlock(),
            new TBlock(),
            new ZBlock()};
     
    }
    
    public void initBackGround()
    {
        backGround=new Color[gridRow][gridCollumn];
    }
    public void spawnBlock()
    {
      Random rand=new Random();
      int r=rand.nextInt(blocks.length);
      block=blocks[r];
      block.spwan(gridCollumn);
    }
    
    public boolean isBlockOutofBound(){
        if(block.getY()<0){
            block=null;
            return true;
        }
        return false;
    }
    public boolean moveDown()
    {
        if(checkBottom()==false){
            return false;
        }
        block.moveDown();
        repaint();
        return true;
    }
    public void moveLeft(){
        if(block==null)return;
        if(checkLeft()==false)return;
        block.moveLeft();
        repaint();
    }
    
    public void moveRight(){
        if(block==null)return;
        if(checkRight()==false)return;
        block.moveRight();
        repaint();
    }
    
    public void DropBlock(){
        if(block==null)return;
        while(checkBottom()){
            block.moveDown();
        }
        repaint();
    }
    
    public void rotateBlock(){
        if(block==null)return;
        block.rotate();
        if(block.getLeftEgde()<0)block.setX(0);
        if(block.getRightEdge()>=gridCollumn)block.setX(gridCollumn-block.getWidth());
        if(block.getBottoEdge()>=gridRow)block.setY(gridRow-block.getHeight());
        repaint();
    }
    private boolean checkBottom(){
        if(block.getBottoEdge()==gridRow) {return false;}
        
        int[][] shape=block.getShape();
        int w=block.getWidth();
        int h=block.getHeight();
        for(int col=0;col<w;col++)
        {
            for(int row=h-1;row>=0;row--)
            {
                if(shape[row][col]!=0)
                {
                    int x=col+block.getX();
                    int y=row+block.getY()+1;
                    if(y<0)break;
                    if(backGround[y][x]!=null)return false;
                    break;
                }
            }
        }
        return true;
        
    }
    private boolean checkLeft(){
        if(block.getLeftEgde()==0)return false;
        
        int[][] shape=block.getShape();
        int w=block.getWidth();
        int h=block.getHeight();
        for(int row=0;row<h;row++)
        {
            for(int col=0;col<w;col++)
            {
                if(shape[row][col]!=0)
                {
                    int x=col+block.getX()-1;
                    int y=row+block.getY();
                    if(y<0)break;
                    if(backGround[y][x]!=null)return false;
                    break;
                }
            }
        }
        return true;
    }
    private boolean checkRight(){
        if(block.getRightEdge()==gridCollumn)return false;
        
        int[][] shape=block.getShape();
        int w=block.getWidth();
        int h=block.getHeight();
       for(int row=0;row<h;row++)
        {
            for(int col=w-1;col>=0;col--)
            {
                if(shape[row][col]!=0)
                {
                    int x=col+block.getX()+1;
                    int y=row+block.getY();
                    if(y<0)break;
                    if(backGround[y][x]!=null)return false;
                    break;
                }
            }
        }
        return true;
    }
    public int clearLine(){
        boolean flag;
        int numOfClLine=0;
        for(int r=gridRow-1;r>=0;r--){
            flag=true;
            for(int c=0;c<gridCollumn;c++){
                if(backGround[r][c]==null){
                    flag=false;
                    break;
                }
            }
            if(flag==true){
                numOfClLine++;
                ereaseLine(r);
                shiftDown(r);
                ereaseLine(0);
                r++;
                repaint();
               
            }
        }
        if(numOfClLine>0)Tetris.PlayClear();
        return numOfClLine;
    }
    
    private void ereaseLine(int r){
        for(int i=0;i<gridCollumn;i++)backGround[r][i]=null;
    }
    private void shiftDown(int r){
        for(int row=r;row>0;row--){
            for(int col=0;col<gridCollumn;col++){
                backGround[row][col]=backGround[row-1][col];
            }
        }
    }
    void moveBlockToBackground(){
        int [][] shape=block.getShape();
        int h=block.getHeight();
        int w=block.getWidth();
        int xpos=block.getX();
        int ypos=block.getY();
        Color color=block.getColor();
        for(int r=0;r<h;r++)
        {
            for(int c=0;c<w;c++)
            {
                if(shape[r][c]==1)
                {
                    backGround[r+ypos][c+xpos]=color;
                    //println(r+"/"+c=)
                }
            }
        }
    }
    private void drawBlock(Graphics g)
    {
        int h=block.getHeight();
        int w=block.getWidth();
        Color c=block.getColor();
        int [][] shape=block.getShape();
        for(int row=0;row<h;row++){
            for(int col=0;col<w;col++){
                if(shape[row][col]==1)
                {
                    int x=(block.getX()+col)*gridCellsize;
                    int y=(block.getY()+row)*gridCellsize;
                    drawGridSquare(g,c,x,y);
                }
//                else {
//                    
//                }
//            
            }
            
        }
    }
    
    
    private void drawBackground(Graphics g){
        Color color;
        for(int r=0;r<gridRow;r++)
        {
            for(int c=0;c<gridCollumn;c++){
                color=backGround[r][c];
                //System.out.println(r+"/ "+c+"="+backGround[r][c]);
                if(color!=null){
                    int x=c*gridCellsize;
                    int y=r*gridCellsize;
                    drawGridSquare(g,color,x,y);
                    
                }
            }
        }
    }
    
    private void drawGridSquare(Graphics g,Color color, int x,int y)
    {
         g.setColor(color);
         g.fillRect(x, y, gridCellsize, gridCellsize);
         g.setColor(Color.black);
         g.drawRect(x, y, gridCellsize, gridCellsize);
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawBackground(g);
        
        drawBlock(g);
    }
}
