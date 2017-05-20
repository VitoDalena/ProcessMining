package bsh;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;

public class Remote
{
  public static void main(String[] paramArrayOfString)
    throws Exception
  {
    if (paramArrayOfString.length < 2)
    {
      System.out.println("usage: Remote URL(http|bsh) file [ file ] ... ");
      System.exit(1);
    }
    String str1 = paramArrayOfString[0];
    String str2 = getFile(paramArrayOfString[1]);
    int i = eval(str1, str2);
    System.exit(i);
  }
  
  public static int eval(String paramString1, String paramString2)
    throws IOException
  {
    String str = null;
    if (paramString1.startsWith("http:")) {
      str = doHttp(paramString1, paramString2);
    } else if (paramString1.startsWith("bsh:")) {
      str = doBsh(paramString1, paramString2);
    } else {
      throw new IOException("Unrecognized URL type.Scheme must be http:// or bsh://");
    }
    try
    {
      return Integer.parseInt(str);
    }
    catch (Exception localException) {}
    return 0;
  }
  
  static String doBsh(String paramString1, String paramString2)
  {
    String str1 = "";
    String str2 = "";
    String str3 = "-1";
    String str4 = paramString1;
    try
    {
      paramString1 = paramString1.substring(6);
      int i = paramString1.indexOf(":");
      str1 = paramString1.substring(0, i);
      str2 = paramString1.substring(i + 1, paramString1.length());
    }
    catch (Exception localException1)
    {
      System.err.println("Bad URL: " + str4 + ": " + localException1);
      return str3;
    }
    try
    {
      System.out.println("Connecting to host : " + str1 + " at port : " + str2);
      Socket localSocket = new Socket(str1, Integer.parseInt(str2) + 1);
      OutputStream localOutputStream = localSocket.getOutputStream();
      InputStream localInputStream = localSocket.getInputStream();
      sendLine(paramString2, localOutputStream);
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localInputStream));
      String str5;
      while ((str5 = localBufferedReader.readLine()) != null) {
        System.out.println(str5);
      }
      str3 = "1";
      return str3;
    }
    catch (Exception localException2)
    {
      System.err.println("Error communicating with server: " + localException2);
    }
    return str3;
  }
  
  private static void sendLine(String paramString, OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream.write(paramString.getBytes());
    paramOutputStream.flush();
  }
  
  static String doHttp(String paramString1, String paramString2)
  {
    String str1 = null;
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("bsh.client=Remote");
    localStringBuffer.append("&bsh.script=");
    localStringBuffer.append(URLEncoder.encode(paramString2));
    String str2 = localStringBuffer.toString();
    try
    {
      URL localURL = new URL(paramString1);
      HttpURLConnection localHttpURLConnection = (HttpURLConnection)localURL.openConnection();
      localHttpURLConnection.setRequestMethod("POST");
      localHttpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
      localHttpURLConnection.setDoOutput(true);
      localHttpURLConnection.setDoInput(true);
      PrintWriter localPrintWriter = new PrintWriter(new OutputStreamWriter(localHttpURLConnection.getOutputStream(), "8859_1"), true);
      localPrintWriter.print(str2);
      localPrintWriter.flush();
      int i = localHttpURLConnection.getResponseCode();
      if (i != 200) {
        System.out.println("Error, HTTP response: " + i);
      }
      str1 = localHttpURLConnection.getHeaderField("Bsh-Return");
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localHttpURLConnection.getInputStream()));
      String str3;
      while ((str3 = localBufferedReader.readLine()) != null) {
        System.out.println(str3);
      }
      System.out.println("Return Value: " + str1);
    }
    catch (MalformedURLException localMalformedURLException)
    {
      System.out.println(localMalformedURLException);
    }
    catch (IOException localIOException)
    {
      System.out.println(localIOException);
    }
    return str1;
  }
  
  static String getFile(String paramString)
    throws FileNotFoundException, IOException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    BufferedReader localBufferedReader = new BufferedReader(new FileReader(paramString));
    String str;
    while ((str = localBufferedReader.readLine()) != null) {
      localStringBuffer.append(str).append("\n");
    }
    return localStringBuffer.toString();
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/Remote.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */