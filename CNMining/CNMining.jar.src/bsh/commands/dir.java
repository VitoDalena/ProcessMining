package bsh.commands;

import bsh.CallStack;
import bsh.Interpreter;
import bsh.StringUtil;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

public class dir
{
  static final String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
  
  public static String usage()
  {
    return "usage: dir( String dir )\n       dir()";
  }
  
  public static void invoke(Interpreter paramInterpreter, CallStack paramCallStack)
  {
    String str = ".";
    invoke(paramInterpreter, paramCallStack, str);
  }
  
  public static void invoke(Interpreter paramInterpreter, CallStack paramCallStack, String paramString)
  {
    File localFile1;
    try
    {
      localFile1 = paramInterpreter.pathToFile(paramString);
    }
    catch (IOException localIOException)
    {
      paramInterpreter.println("error reading path: " + localIOException);
      return;
    }
    if ((!localFile1.exists()) || (!localFile1.canRead()))
    {
      paramInterpreter.println("Can't read " + localFile1);
      return;
    }
    if (!localFile1.isDirectory()) {
      paramInterpreter.println("'" + paramString + "' is not a directory");
    }
    String[] arrayOfString = localFile1.list();
    arrayOfString = StringUtil.bubbleSort(arrayOfString);
    for (int i = 0; i < arrayOfString.length; i++)
    {
      File localFile2 = new File(paramString + File.separator + arrayOfString[i]);
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append(localFile2.canRead() ? "r" : "-");
      localStringBuffer1.append(localFile2.canWrite() ? "w" : "-");
      localStringBuffer1.append("_");
      localStringBuffer1.append(" ");
      Date localDate = new Date(localFile2.lastModified());
      GregorianCalendar localGregorianCalendar = new GregorianCalendar();
      localGregorianCalendar.setTime(localDate);
      int j = localGregorianCalendar.get(5);
      localStringBuffer1.append(months[localGregorianCalendar.get(2)] + " " + j);
      if (j < 10) {
        localStringBuffer1.append(" ");
      }
      localStringBuffer1.append(" ");
      int k = 8;
      StringBuffer localStringBuffer2 = new StringBuffer();
      for (int m = 0; m < k; m++) {
        localStringBuffer2.append(" ");
      }
      localStringBuffer2.insert(0, localFile2.length());
      localStringBuffer2.setLength(k);
      int n = localStringBuffer2.toString().indexOf(" ");
      if (n != -1)
      {
        String str = localStringBuffer2.toString().substring(n);
        localStringBuffer2.setLength(n);
        localStringBuffer2.insert(0, str);
      }
      localStringBuffer1.append(localStringBuffer2.toString());
      localStringBuffer1.append(" " + localFile2.getName());
      if (localFile2.isDirectory()) {
        localStringBuffer1.append("/");
      }
      paramInterpreter.println(localStringBuffer1.toString());
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/commands/dir.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */