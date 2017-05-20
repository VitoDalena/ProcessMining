package bsh.util;

import bsh.Interpreter;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Component;

public class AWTDemoApplet
  extends Applet
{
  public void init()
  {
    setLayout(new BorderLayout());
    AWTConsole localAWTConsole = new AWTConsole();
    add("Center", (Component)localAWTConsole);
    Interpreter localInterpreter = new Interpreter(localAWTConsole);
    new Thread(localInterpreter).start();
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/util/AWTDemoApplet.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */