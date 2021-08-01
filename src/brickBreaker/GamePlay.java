package brickBreaker;
import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePlay extends JPanel
{
    private boolean play;
    private Timer timer;
    private int score,totalBricks,delay;
    private int playerX,playerY;
    private int ballPosX,ballPosY,ballDirX,ballDirY;
    private MapGenerator map;
    
    public void paint(Graphics g)
    {
        //Background
        g.setColor(Color.BLACK);
        g.fillRect(1,1,692,592);
        
        //borders around the JPanel
        g.setColor(new Color(5,255,255));
        g.fillRect(0,0,3,562); 
        g.fillRect(1,0,689,3); 
        g.fillRect(689,0,3,562); 
        g.fillRect(1,558,689,3);
        
        //Draw Brick
        map.draw((Graphics2D)g);
        
        //paddle
        g.setColor(Color.ORANGE);
        g.fillRect(playerX,549,100,8);
        
        //ball
        g.setColor(new Color(127,255,0));
        g.fillOval(ballPosX,ballPosY,13,13);
        
        //Score
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString(""+score,645,30);
        
        //Game Over
        if(ballPosY>555)
        {
            play=false;
            ballDirX=ballDirY=0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD,60));
            g.drawString("Game Over",216,300);
            g.setFont(new Font("serif",Font.BOLD,40));
            g.drawString("Score: "+score,285,340);
            
            g.setColor(new Color(0,0,255));
            g.setFont(new Font("",Font.PLAIN,30));
            g.drawString("Press Enter To Restart",220,390);
        }
        
        //Game Won
        if(totalBricks<=0)
        {
            play=false;
            ballDirX=ballDirY=0;
            g.setColor(Color.GREEN);
            g.setFont(new Font("serif",Font.BOLD,60));
            g.drawString("You Won",216,300);
            g.setFont(new Font("serif",Font.BOLD,40));
            g.drawString("Score: "+score,257,340);
            
            g.setColor(new Color(0,0,255));
            g.setFont(new Font("",Font.PLAIN,30));
            g.drawString("Press Enter To Restart",192,390);
            
        }
        
        g.dispose();
    }
    
   //Constructer
    public GamePlay()
    {
        play=false;
        score=0;
        totalBricks=21;
        delay=10;
        
        playerX=349;
        playerY=370;
        
        ballPosX=126;
        ballPosY=350;
        ballDirX=-2;
        ballDirY=-2;
        
        map=new MapGenerator(3,7);
        
        timer=new Timer(delay,new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
              if(play)
              {
                  if(new Rectangle(ballPosX,ballPosY,13,13).intersects(new Rectangle(playerX,549,100,8)))
                      ballDirY=-ballDirY;
                  
                  //Code to set the brick value as 0 to disappear the brick
              A:  for(int i=0;i<map.map.length;i++)
                      for(int j=0;j<map.map[i].length;j++)
                          if(map.map[i][j]==1)
                          {
                              int brickX=j*map.brickWidth+80;
                              int brickY=i*map.brickHeight+50;
                              int brickWidth=map.brickWidth;
                              int brickHeight=map.brickHeight;
                              
                              Rectangle brickRect=new Rectangle(brickX,brickY,brickWidth,brickHeight);
                              Rectangle ballRect=new Rectangle(ballPosX,ballPosY,13,13);
                              if(brickRect.intersects(ballRect))
                              {
                                 map.setBrickValue(i,j);
                                 totalBricks--;
                                 score+=5;
                                      
                                 ballDirX=-ballDirX;
                                 ballDirY=-ballDirY;
                                 

                                 break A;
                              }
                          }
                  
                  ballPosX+=ballDirX;
                  ballPosY+=ballDirY;
                  
                  if(ballPosX<0)
                      ballDirX=-ballDirX;

                  if(ballPosY<0)
                      ballDirY=-ballDirY;
                      
                  if(ballPosX>670)
                      ballDirX=-ballDirX;
                  
              }
              //timer.start();
              repaint();
         }
        });
        timer.start();
        //Setting Property of JPanel
        setFocusable(true);
        
        //Event Listeners
        addKeyListener(new KeyAdapter(){
          @Override
          public void keyPressed(KeyEvent e)
          {
              if(e.getKeyCode()==KeyEvent.VK_LEFT)
                  if(playerX>10)
                  {
                      play=true;
                      playerX-=30;
                  }
              
              if(e.getKeyCode()==KeyEvent.VK_RIGHT)
                  if(playerX<578)
                  {
                      play=true;
                      playerX+=20;
                  }
              
              if(!play && e.getKeyCode()==KeyEvent.VK_ENTER)
              {
                  play=true;
                  ballPosX=120;
                  ballPosY=350;
                  ballDirX=-2;
                  ballDirY=-2;
                  score=0;
                  playerX=310;
                  totalBricks=21;
                  map=new MapGenerator(3,7);
                  
                  repaint();
              }
          }
        });
        
        addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e)
        {
            
        }
        });
        
    }
    
}
