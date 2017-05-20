package bsh.util;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Label;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import javax.swing.JApplet;

public class JRemoteApplet
  extends JApplet
{
  OutputStream out;
  InputStream in;
  
  public void init()
  {
    getContentPane().setLayout(new BorderLayout());
    try
    {
      URL localURL = getDocumentBase();
      Socket localSocket = new Socket(localURL.getHost(), localURL.getPort() + 1);
      this.out = localSocket.getOutputStream();
      this.in = localSocket.getInputStream();
    }
    catch (IOException localIOException)
    {
      getContentPane().add("Center", new Label("Remote Connection Failed", 1));
      return;
    }
    JConsole localJConsole = new JConsole(this.in, this.out);
    getContentPane().add("Center", localJConsole);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/util/JRemoteApplet.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */