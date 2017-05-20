package bsh.util;

import bsh.NameSpace;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;

public class Sessiond
  extends Thread
{
  private ServerSocket ss;
  NameSpace globalNameSpace;
  
  public Sessiond(NameSpace paramNameSpace, int paramInt)
    throws IOException
  {
    this.ss = new ServerSocket(paramInt);
    this.globalNameSpace = paramNameSpace;
  }
  
  public void run()
  {
    try
    {
      for (;;)
      {
        new SessiondConnection(this.globalNameSpace, this.ss.accept()).start();
      }
    }
    catch (IOException localIOException)
    {
      System.out.println(localIOException);
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/util/Sessiond.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */