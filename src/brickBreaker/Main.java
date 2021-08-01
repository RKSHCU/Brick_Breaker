/*
  @author Alien
   Date: 31-07-2021
   Linkedin Profile: https://linkedin.com/in/rkshcu
 */
package brickBreaker;
import javax.swing.*;
import java.awt.*;

public class Main 
{
    public static void main(String []args) 
    {
      JFrame frame=new JFrame("Brick Breaker");
      frame.setSize(708,600);
      frame.setLocationRelativeTo(null); //Set Location as Center of screen
      frame.setResizable(false);
      
      frame.add(new GamePlay());
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
