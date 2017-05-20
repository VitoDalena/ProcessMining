package bsh.util;

import bsh.Interpreter;
import bsh.NameSpace;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

class SessiondConnection
  extends Thread
{
  NameSpace globalNameSpace;
  Socket client;
  
  SessiondConnection(NameSpace paramNameSpace, Socket paramSocket)
  {
    this.client = paramSocket;
    this.globalNameSpace = paramNameSpace;
  }
  
  public void run()
  {
    try
    {
      InputStream localInputStream = this.client.getInputStream();
      PrintStream localPrintStream = new PrintStream(this.client.getOutputStream());
      Interpreter localInterpreter = new Interpreter(new InputStreamReader(localInputStream), localPrintStream, localPrintStream, true, this.globalNameSpace);
      localInterpreter.setExitOnEOF(false);
      localInterpreter.run();
    }
    catch (IOException localIOException)
    {
      System.out.println(localIOException);
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/util/SessiondConnection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */