package bsh.util;

import bsh.Interpreter;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Window;

public class Util
{
  static Window splashScreen;
  
  public static void startSplashScreen()
  {
    int i = 275;
    int j = 148;
    Window localWindow = new Window(new Frame());
    localWindow.pack();
    BshCanvas localBshCanvas = new BshCanvas();
    localBshCanvas.setSize(i, j);
    Toolkit localToolkit = Toolkit.getDefaultToolkit();
    Dimension localDimension = localToolkit.getScreenSize();
    localWindow.setBounds(localDimension.width / 2 - i / 2, localDimension.height / 2 - j / 2, i, j);
    localWindow.add("Center", localBshCanvas);
    Image localImage = localToolkit.getImage(Interpreter.class.getResource("/bsh/util/lib/splash.gif"));
    MediaTracker localMediaTracker = new MediaTracker(localBshCanvas);
    localMediaTracker.addImage(localImage, 0);
    try
    {
      localMediaTracker.waitForAll();
    }
    catch (Exception localException) {}
    Graphics localGraphics = localBshCanvas.getBufferedGraphics();
    localGraphics.drawImage(localImage, 0, 0, localBshCanvas);
    localWindow.setVisible(true);
    localWindow.toFront();
    splashScreen = localWindow;
  }
  
  public static void endSplashScreen()
  {
    if (splashScreen != null) {
      splashScreen.dispose();
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/util/Util.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */