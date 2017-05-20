package bsh.util;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;

public class Httpd
  extends Thread
{
  ServerSocket ss;
  
  public static void main(String[] paramArrayOfString)
    throws IOException
  {
    new Httpd(Integer.parseInt(paramArrayOfString[0])).start();
  }
  
  public Httpd(int paramInt)
    throws IOException
  {
    this.ss = new ServerSocket(paramInt);
  }
  
  public void run()
  {
    try
    {
      for (;;)
      {
        new HttpdConnection(this.ss.accept()).start();
      }
    }
    catch (IOException localIOException)
    {
      System.out.println(localIOException);
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/util/Httpd.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */