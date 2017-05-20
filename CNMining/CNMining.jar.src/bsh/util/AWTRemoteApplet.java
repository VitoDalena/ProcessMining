package bsh.util;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Label;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;

public class AWTRemoteApplet
  extends Applet
{
  OutputStream out;
  InputStream in;
  
  public void init()
  {
    setLayout(new BorderLayout());
    try
    {
      URL localURL = getDocumentBase();
      Socket localSocket = new Socket(localURL.getHost(), localURL.getPort() + 1);
      this.out = localSocket.getOutputStream();
      this.in = localSocket.getInputStream();
    }
    catch (IOException localIOException)
    {
      add("Center", new Label("Remote Connection Failed", 1));
      return;
    }
    AWTConsole localAWTConsole = new AWTConsole(this.in, this.out);
    add("Center", localAWTConsole);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/util/AWTRemoteApplet.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */