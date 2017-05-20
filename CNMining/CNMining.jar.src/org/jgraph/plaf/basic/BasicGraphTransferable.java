package org.jgraph.plaf.basic;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import javax.swing.plaf.UIResource;

public class BasicGraphTransferable
  implements Transferable, UIResource, Serializable
{
  private static DataFlavor[] htmlFlavors;
  private static DataFlavor[] stringFlavors;
  private static DataFlavor[] plainFlavors;
  
  public DataFlavor[] getTransferDataFlavors()
  {
    DataFlavor[] arrayOfDataFlavor1 = getRicherFlavors();
    int i = arrayOfDataFlavor1 != null ? arrayOfDataFlavor1.length : 0;
    int j = isHTMLSupported() ? htmlFlavors.length : 0;
    int k = isPlainSupported() ? plainFlavors.length : 0;
    int m = isPlainSupported() ? stringFlavors.length : 0;
    int n = i + j + k + m;
    DataFlavor[] arrayOfDataFlavor2 = new DataFlavor[n];
    int i1 = 0;
    if (i > 0)
    {
      System.arraycopy(arrayOfDataFlavor1, 0, arrayOfDataFlavor2, i1, i);
      i1 += i;
    }
    if (j > 0)
    {
      System.arraycopy(htmlFlavors, 0, arrayOfDataFlavor2, i1, j);
      i1 += j;
    }
    if (k > 0)
    {
      System.arraycopy(plainFlavors, 0, arrayOfDataFlavor2, i1, k);
      i1 += k;
    }
    if (m > 0)
    {
      System.arraycopy(stringFlavors, 0, arrayOfDataFlavor2, i1, m);
      i1 += m;
    }
    return arrayOfDataFlavor2;
  }
  
  public boolean isDataFlavorSupported(DataFlavor paramDataFlavor)
  {
    DataFlavor[] arrayOfDataFlavor = getTransferDataFlavors();
    for (int i = 0; i < arrayOfDataFlavor.length; i++) {
      if (arrayOfDataFlavor[i].equals(paramDataFlavor)) {
        return true;
      }
    }
    return false;
  }
  
  public Object getTransferData(DataFlavor paramDataFlavor)
    throws UnsupportedFlavorException, IOException
  {
    if (isRicherFlavor(paramDataFlavor)) {
      return getRicherData(paramDataFlavor);
    }
    String str;
    if (isHTMLFlavor(paramDataFlavor))
    {
      str = getHTMLData();
      str = str == null ? "" : str;
      if (String.class.equals(paramDataFlavor.getRepresentationClass())) {
        return str;
      }
      if (Reader.class.equals(paramDataFlavor.getRepresentationClass())) {
        return new StringReader(str);
      }
      if (InputStream.class.equals(paramDataFlavor.getRepresentationClass())) {
        return new StringBufferInputStream(str);
      }
    }
    else if (isPlainFlavor(paramDataFlavor))
    {
      str = getPlainData();
      str = str == null ? "" : str;
      if (String.class.equals(paramDataFlavor.getRepresentationClass())) {
        return str;
      }
      if (Reader.class.equals(paramDataFlavor.getRepresentationClass())) {
        return new StringReader(str);
      }
      if (InputStream.class.equals(paramDataFlavor.getRepresentationClass())) {
        return new StringBufferInputStream(str);
      }
    }
    else if (isStringFlavor(paramDataFlavor))
    {
      str = getPlainData();
      str = str == null ? "" : str;
      return str;
    }
    throw new UnsupportedFlavorException(paramDataFlavor);
  }
  
  protected boolean isRicherFlavor(DataFlavor paramDataFlavor)
  {
    DataFlavor[] arrayOfDataFlavor = getRicherFlavors();
    int i = arrayOfDataFlavor != null ? arrayOfDataFlavor.length : 0;
    for (int j = 0; j < i; j++) {
      if (arrayOfDataFlavor[j].equals(paramDataFlavor)) {
        return true;
      }
    }
    return false;
  }
  
  protected DataFlavor[] getRicherFlavors()
  {
    return null;
  }
  
  protected Object getRicherData(DataFlavor paramDataFlavor)
    throws UnsupportedFlavorException
  {
    return null;
  }
  
  protected boolean isHTMLFlavor(DataFlavor paramDataFlavor)
  {
    DataFlavor[] arrayOfDataFlavor = htmlFlavors;
    for (int i = 0; i < arrayOfDataFlavor.length; i++) {
      if (arrayOfDataFlavor[i].equals(paramDataFlavor)) {
        return true;
      }
    }
    return false;
  }
  
  protected boolean isHTMLSupported()
  {
    return false;
  }
  
  protected String getHTMLData()
  {
    return null;
  }
  
  protected boolean isPlainFlavor(DataFlavor paramDataFlavor)
  {
    DataFlavor[] arrayOfDataFlavor = plainFlavors;
    for (int i = 0; i < arrayOfDataFlavor.length; i++) {
      if (arrayOfDataFlavor[i].equals(paramDataFlavor)) {
        return true;
      }
    }
    return false;
  }
  
  protected boolean isPlainSupported()
  {
    return false;
  }
  
  protected String getPlainData()
  {
    return null;
  }
  
  protected boolean isStringFlavor(DataFlavor paramDataFlavor)
  {
    DataFlavor[] arrayOfDataFlavor = stringFlavors;
    for (int i = 0; i < arrayOfDataFlavor.length; i++) {
      if (arrayOfDataFlavor[i].equals(paramDataFlavor)) {
        return true;
      }
    }
    return false;
  }
  
  static
  {
    try
    {
      htmlFlavors = new DataFlavor[3];
      htmlFlavors[0] = new DataFlavor("text/html;class=java.lang.String");
      htmlFlavors[1] = new DataFlavor("text/html;class=java.io.Reader");
      htmlFlavors[2] = new DataFlavor("text/html;charset=unicode;class=java.io.InputStream");
      plainFlavors = new DataFlavor[3];
      plainFlavors[0] = new DataFlavor("text/plain;class=java.lang.String");
      plainFlavors[1] = new DataFlavor("text/plain;class=java.io.Reader");
      plainFlavors[2] = new DataFlavor("text/plain;charset=unicode;class=java.io.InputStream");
      stringFlavors = new DataFlavor[2];
      stringFlavors[0] = new DataFlavor("application/x-java-jvm-local-objectref;class=java.lang.String");
      stringFlavors[1] = DataFlavor.stringFlavor;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      System.err.println("error initializing javax.swing.plaf.basic.BasicTranserable");
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/plaf/basic/BasicGraphTransferable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */