package brickBreaker;
import java.awt.*;

public class MapGenerator 
{
  public int map[][];
  public int brickWidth,brickHeight;
  
  // Draw The Bricks
  public void draw(Graphics2D g)
  {
      for(int i=0;i<map.length;i++)
          for(int j=0;j<map[i].length;j++)
              if(map[i][j]==1)
              {
                  g.setColor(Color.WHITE);
                  g.fillRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight);
                  
                  g.setStroke(new BasicStroke(3));
                  g.setColor(Color.BLACK);
                  g.drawRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight);
              }
  }
  
  public void setBrickValue(int row,int column)
  {
      map[row][column]=0;
  }
  
  //Constructor
  public MapGenerator(int row,int column)
  {
      map=new int[row][column];
      for(int i=0;i<row;i++)
          for(int j=0;j<column;j++)
              map[i][j]=1;
      brickWidth=540/column;
      brickHeight=150/row;
  }
}
