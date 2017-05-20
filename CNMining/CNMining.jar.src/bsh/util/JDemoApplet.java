package bsh.util;

import bsh.EvalError;
import bsh.Interpreter;
import bsh.TargetError;
import java.awt.BorderLayout;
import java.awt.Container;
import java.io.PrintStream;
import javax.swing.JApplet;

public class JDemoApplet
  extends JApplet
{
  public void init()
  {
    String str1 = getParameter("debug");
    if ((str1 != null) && (str1.equals("true"))) {
      Interpreter.DEBUG = true;
    }
    String str2 = getParameter("type");
    if ((str2 != null) && (str2.equals("desktop")))
    {
      try
      {
        new Interpreter().eval("desktop()");
      }
      catch (TargetError localTargetError)
      {
        localTargetError.printStackTrace();
        System.out.println(localTargetError.getTarget());
        localTargetError.getTarget().printStackTrace();
      }
      catch (EvalError localEvalError)
      {
        System.out.println(localEvalError);
        localEvalError.printStackTrace();
      }
    }
    else
    {
      getContentPane().setLayout(new BorderLayout());
      JConsole localJConsole = new JConsole();
      getContentPane().add("Center", localJConsole);
      Interpreter localInterpreter = new Interpreter(localJConsole);
      new Thread(localInterpreter).start();
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/util/JDemoApplet.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */