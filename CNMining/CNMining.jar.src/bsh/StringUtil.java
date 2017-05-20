package bsh;

import java.util.StringTokenizer;
import java.util.Vector;

public class StringUtil
{
  public static String[] split(String paramString1, String paramString2)
  {
    Vector localVector = new Vector();
    StringTokenizer localStringTokenizer = new StringTokenizer(paramString1, paramString2);
    while (localStringTokenizer.hasMoreTokens()) {
      localVector.addElement(localStringTokenizer.nextToken());
    }
    String[] arrayOfString = new String[localVector.size()];
    localVector.copyInto(arrayOfString);
    return arrayOfString;
  }
  
  public static String[] bubbleSort(String[] paramArrayOfString)
  {
    Vector localVector = new Vector();
    for (int i = 0; i < paramArrayOfString.length; i++) {
      localVector.addElement(paramArrayOfString[i]);
    }
    int j = localVector.size();
    int k = 1;
    while (k != 0)
    {
      k = 0;
      for (int m = 0; m < j - 1; m++) {
        if (((String)localVector.elementAt(m)).compareTo((String)localVector.elementAt(m + 1)) > 0)
        {
          String str = (String)localVector.elementAt(m + 1);
          localVector.removeElementAt(m + 1);
          localVector.insertElementAt(str, m);
          k = 1;
        }
      }
    }
    String[] arrayOfString = new String[j];
    localVector.copyInto(arrayOfString);
    return arrayOfString;
  }
  
  public static String maxCommonPrefix(String paramString1, String paramString2)
  {
    for (int i = 0; paramString1.regionMatches(0, paramString2, 0, i); i++) {}
    return paramString1.substring(0, i - 1);
  }
  
  public static String methodString(String paramString, Class[] paramArrayOfClass)
  {
    StringBuffer localStringBuffer = new StringBuffer(paramString + "(");
    if (paramArrayOfClass.length > 0) {
      localStringBuffer.append(" ");
    }
    for (int i = 0; i < paramArrayOfClass.length; i++)
    {
      Class localClass = paramArrayOfClass[i];
      localStringBuffer.append((localClass == null ? "null" : localClass.getName()) + (i < paramArrayOfClass.length - 1 ? ", " : " "));
    }
    localStringBuffer.append(")");
    return localStringBuffer.toString();
  }
  
  public static String normalizeClassName(Class paramClass)
  {
    return Reflect.normalizeClassName(paramClass);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/StringUtil.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */