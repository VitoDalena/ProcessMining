package bsh.util;

import bsh.ConsoleInterface;
import java.awt.Color;

public abstract interface GUIConsoleInterface
  extends ConsoleInterface
{
  public abstract void print(Object paramObject, Color paramColor);
  
  public abstract void setNameCompletion(NameCompletion paramNameCompletion);
  
  public abstract void setWaitFeedback(boolean paramBoolean);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/util/GUIConsoleInterface.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */