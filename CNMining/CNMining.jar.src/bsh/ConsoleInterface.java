package bsh;

import java.io.PrintStream;
import java.io.Reader;

public abstract interface ConsoleInterface
{
  public abstract Reader getIn();
  
  public abstract PrintStream getOut();
  
  public abstract PrintStream getErr();
  
  public abstract void println(Object paramObject);
  
  public abstract void print(Object paramObject);
  
  public abstract void error(Object paramObject);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/ConsoleInterface.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */