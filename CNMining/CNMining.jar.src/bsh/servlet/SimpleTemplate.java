package bsh.servlet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SimpleTemplate
{
  StringBuffer buff;
  static String NO_TEMPLATE = "NO_TEMPLATE";
  static Map templateData = new HashMap();
  static boolean cacheTemplates = true;
  
  public static SimpleTemplate getTemplate(String paramString)
  {
    String str = (String)templateData.get(paramString);
    if ((str == null) || (!cacheTemplates)) {
      try
      {
        FileReader localFileReader = new FileReader(paramString);
        str = getStringFromStream(localFileReader);
        templateData.put(paramString, str);
      }
      catch (IOException localIOException)
      {
        templateData.put(paramString, NO_TEMPLATE);
      }
    } else if (str.equals(NO_TEMPLATE)) {
      return null;
    }
    if (str == null) {
      return null;
    }
    return new SimpleTemplate(str);
  }
  
  public static String getStringFromStream(InputStream paramInputStream)
    throws IOException
  {
    return getStringFromStream(new InputStreamReader(paramInputStream));
  }
  
  public static String getStringFromStream(Reader paramReader)
    throws IOException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    BufferedReader localBufferedReader = new BufferedReader(paramReader);
    String str;
    while ((str = localBufferedReader.readLine()) != null) {
      localStringBuffer.append(str + "\n");
    }
    return localStringBuffer.toString();
  }
  
  public SimpleTemplate(String paramString)
  {
    init(paramString);
  }
  
  public SimpleTemplate(Reader paramReader)
    throws IOException
  {
    String str = getStringFromStream(paramReader);
    init(str);
  }
  
  public SimpleTemplate(URL paramURL)
    throws IOException
  {
    String str = getStringFromStream(paramURL.openStream());
    init(str);
  }
  
  private void init(String paramString)
  {
    this.buff = new StringBuffer(paramString);
  }
  
  public void replace(String paramString1, String paramString2)
  {
    int[] arrayOfInt;
    while ((arrayOfInt = findTemplate(paramString1)) != null) {
      this.buff.replace(arrayOfInt[0], arrayOfInt[1], paramString2);
    }
  }
  
  int[] findTemplate(String paramString)
  {
    String str1 = this.buff.toString();
    int i = str1.length();
    int j = 0;
    while (j < i)
    {
      int k = str1.indexOf("<!--", j);
      if (k == -1) {
        return null;
      }
      int m = str1.indexOf("-->", k);
      if (m == -1) {
        return null;
      }
      m += "-->".length();
      int n = str1.indexOf("TEMPLATE-", k);
      if (n == -1)
      {
        j = m;
      }
      else if (n > m)
      {
        j = m;
      }
      else
      {
        int i1 = n + "TEMPLATE-".length();
        int i2 = i;
        for (i2 = i1; i2 < i; i2++)
        {
          int i3 = str1.charAt(i2);
          if ((i3 == 32) || (i3 == 9) || (i3 == 45)) {
            break;
          }
        }
        if (i2 >= i) {
          return null;
        }
        String str2 = str1.substring(i1, i2);
        if (str2.equals(paramString)) {
          return new int[] { k, m };
        }
        j = m;
      }
    }
    return null;
  }
  
  public String toString()
  {
    return this.buff.toString();
  }
  
  public void write(PrintWriter paramPrintWriter)
  {
    paramPrintWriter.println(toString());
  }
  
  public void write(PrintStream paramPrintStream)
  {
    paramPrintStream.println(toString());
  }
  
  public static void main(String[] paramArrayOfString)
    throws IOException
  {
    String str1 = paramArrayOfString[0];
    String str2 = paramArrayOfString[1];
    String str3 = paramArrayOfString[2];
    FileReader localFileReader = new FileReader(str1);
    String str4 = getStringFromStream(localFileReader);
    SimpleTemplate localSimpleTemplate = new SimpleTemplate(str4);
    localSimpleTemplate.replace(str2, str3);
    localSimpleTemplate.write(System.out);
  }
  
  public static void setCacheTemplates(boolean paramBoolean)
  {
    cacheTemplates = paramBoolean;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/servlet/SimpleTemplate.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */