import java.applet.*; 
import java.awt.*; 

public class BallApplet extends Applet implements Runnable 
{ int x_pos = 10; 
  int y_pos = 100; 
  int radius = 20; 
  int x_speed = 5;
  int y_speed=5;
  int appletsize_x = 400;
  int appletsize_y = 300;
   Image ballimage; 
  private Image dbImage; 
  private Graphics dbg; 
  
  
  public void init() { 
    //setBackground (Color.black);
   ballimage = getImage (getCodeBase (), "ball.jpg"); 
  } 
  
  public void start(){     
    Thread th = new Thread (this); 
    th.start ();
    
  } 
  
  public void stop() { } 
  
  public void destroy() { } 
  
  public void run () { 
    Thread.currentThread().setPriority(Thread.MIN_PRIORITY); 
    
    while (true) 
    {
      if (x_pos>appletsize_x - radius) 
      {  x_speed = -5; } 
      else if (x_pos < radius) 
      {  x_speed = +5; } 
      x_pos += x_speed;
      
      if (y_pos>appletsize_y - radius) 
      {  y_speed = -5; } 
      else if (y_pos < radius) 
      {  y_speed = +5; } 
      y_pos += y_speed;
      repaint(); 
   
      try 
      { 
        Thread.sleep (20); 
      } 
      catch (InterruptedException ex) 
      {
      } 
      Thread.currentThread().setPriority(Thread.MAX_PRIORITY); 
    }
  } 
  
  public void paint (Graphics g) { 
    g.drawImage (ballimage, 0, 0,400,300, this); 
    g.setColor (Color.blue); 
    g.fillOval (x_pos - radius, y_pos - radius, 2 * radius, 2 * radius);

  }
  
  public void update (Graphics g) 
  { 
    if (dbImage == null) 
    { dbImage = createImage (this.getSize().width, this.getSize().height); 
      dbg = dbImage.getGraphics (); 
    } 
    
    dbg.setColor (getBackground ()); 
    dbg.fillRect (0, 0, this.getSize().width, this.getSize().height); 
    
    dbg.setColor (getForeground()); 
    paint (dbg); 
    
    g.drawImage (dbImage, 0, 0, this); 
  } 
  
  
}