package bsh;

import bsh.util.Util;
import java.io.PrintStream;

public class Console
{
  public static void main(String[] paramArrayOfString)
  {
    if (!Capabilities.classExists("bsh.util.Util")) {
      System.out.println("Can't find the BeanShell utilities...");
    }
    if (Capabilities.haveSwing())
    {
      Util.startSplashScreen();
      try
      {
        new Interpreter().eval("desktop()");
      }
      catch (EvalError localEvalError)
      {
        System.err.println("Couldn't start desktop: " + localEvalError);
      }
    }
    else
    {
      System.err.println("Can't find javax.swing package:  An AWT based Console is available but not built by default.");
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/Console.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */