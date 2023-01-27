package Diary.view;
/**
 *
 * @author Mr. Deepak Kumar
 */
import java.awt.*;

import javax.swing.*;

public class Loading extends JWindow {
	JProgressBar jb;
	int i=0,num=0;     

	private static final long serialVersionUID = 1L;
Image splashScreen;
   ImageIcon imageIcon;
   public Loading() {
      splashScreen = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("loading.png"));
      // Create ImageIcon from Image
      imageIcon = new ImageIcon(splashScreen);
      // Set JWindow size from image size
      setSize(imageIcon.getIconWidth(),imageIcon.getIconHeight());
      // Get current screen size
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      // Get x coordinate on screen for make JWindow locate at center
      int x = (screenSize.width-500)/2;
      // Get y coordinate on screen for make JWindow locate at center
      int y = (screenSize.height-300)/2;
      // Set new location for JWindow
	  setBounds(x, y, 500, 300);
      // Make JWindow visible
      setVisible(true);
      getContentPane().setLayout(null);
      jb=new JProgressBar(0,2000);    
      jb.setForeground(new Color(241, 197, 78));
      jb.setBounds(0,276,500,24);         
      jb.setValue(0);    
      jb.setStringPainted(true);   
      getContentPane().add(jb);
	  iterate();
   }
   
   public void iterate(){    
	   while(i<=2000){    
	     jb.setValue(i);
	     i=i+40;    
	     try{Thread.sleep(100);}catch(Exception e){}    
	   }
	   }    
   // Paint image onto JWindow
   public void paint(Graphics g) {
      super.paint(g);
      g.drawImage(splashScreen, 0, 0, this);
   }

}