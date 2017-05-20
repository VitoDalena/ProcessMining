package bsh.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.StringTokenizer;

class HttpdConnection
  extends Thread
{
  Socket client;
  BufferedReader in;
  OutputStream out;
  PrintStream pout;
  boolean isHttp1;
  
  HttpdConnection(Socket paramSocket)
  {
    this.client = paramSocket;
    setPriority(4);
  }
  
  public void run()
  {
    try
    {
      this.in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
      this.out = this.client.getOutputStream();
      this.pout = new PrintStream(this.out);
      String str1 = this.in.readLine();
      if (str1 == null) {
        error(400, "Empty Request");
      }
      if (str1.toLowerCase().indexOf("http/1.") != -1)
      {
        while ((goto 88) || ((!(localObject = this.in.readLine()).equals("")) && (localObject != null))) {}
        this.isHttp1 = true;
      }
      Object localObject = new StringTokenizer(str1);
      if (((StringTokenizer)localObject).countTokens() < 2)
      {
        error(400, "Bad Request");
      }
      else
      {
        String str2 = ((StringTokenizer)localObject).nextToken();
        if (str2.equals("GET")) {
          serveFile(((StringTokenizer)localObject).nextToken());
        } else {
          error(400, "Bad Request");
        }
      }
      this.client.close();
    }
    catch (IOException localIOException)
    {
      System.out.println("I/O error " + localIOException);
      try
      {
        this.client.close();
      }
      catch (Exception localException) {}
    }
  }
  
  private void serveFile(String paramString)
    throws FileNotFoundException, IOException
  {
    if (paramString.equals("/")) {
      paramString = "/remote/remote.html";
    }
    if (paramString.startsWith("/remote/")) {
      paramString = "/bsh/util/lib/" + paramString.substring(8);
    }
    if (paramString.startsWith("/java")) {
      error(404, "Object Not Found");
    } else {
      try
      {
        System.out.println("sending file: " + paramString);
        sendFileData(paramString);
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        error(404, "Object Not Found");
      }
    }
  }
  
  private void sendFileData(String paramString)
    throws IOException, FileNotFoundException
  {
    InputStream localInputStream = getClass().getResourceAsStream(paramString);
    if (localInputStream == null) {
      throw new FileNotFoundException(paramString);
    }
    byte[] arrayOfByte = new byte[localInputStream.available()];
    if (this.isHttp1)
    {
      this.pout.println("HTTP/1.0 200 Document follows");
      this.pout.println("Content-length: " + arrayOfByte.length);
      if (paramString.endsWith(".gif")) {
        this.pout.println("Content-type: image/gif");
      } else if ((paramString.endsWith(".html")) || (paramString.endsWith(".htm"))) {
        this.pout.println("Content-Type: text/html");
      } else {
        this.pout.println("Content-Type: application/octet-stream");
      }
      this.pout.println();
    }
    int i = 0;
    do
    {
      i = localInputStream.read(arrayOfByte);
      if (i > 0) {
        this.pout.write(arrayOfByte, 0, i);
      }
    } while (i != -1);
    this.pout.flush();
  }
  
  private void error(int paramInt, String paramString)
  {
    paramString = "<html><h1>" + paramString + "</h1></html>";
    if (this.isHttp1)
    {
      this.pout.println("HTTP/1.0 " + paramInt + " " + paramString);
      this.pout.println("Content-type: text/html");
      this.pout.println("Content-length: " + paramString.length() + "\n");
    }
    this.pout.println(paramString);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/util/HttpdConnection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */