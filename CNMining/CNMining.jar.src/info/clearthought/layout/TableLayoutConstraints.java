package info.clearthought.layout;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class TableLayoutConstraints
  implements TableLayoutConstants
{
  public int col1;
  public int row1;
  public int col2;
  public int row2;
  public int hAlign;
  public int vAlign;
  
  public TableLayoutConstraints()
  {
    this.col1 = (this.row1 = this.col2 = this.col2 = 0);
    this.hAlign = (this.vAlign = 2);
  }
  
  public TableLayoutConstraints(int paramInt1, int paramInt2)
  {
    this(paramInt1, paramInt2, paramInt1, paramInt2, 2, 2);
  }
  
  public TableLayoutConstraints(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this(paramInt1, paramInt2, paramInt3, paramInt4, 2, 2);
  }
  
  public TableLayoutConstraints(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    this.col1 = paramInt1;
    this.row1 = paramInt2;
    this.col2 = paramInt3;
    this.row2 = paramInt4;
    if ((paramInt5 == 0) || (paramInt5 == 3) || (paramInt5 == 1) || (paramInt5 == 2) || (paramInt5 == 5)) {
      this.hAlign = paramInt5;
    } else {
      this.hAlign = 2;
    }
    if ((paramInt6 == 0) || (paramInt6 == 3) || (paramInt6 == 1)) {
      this.vAlign = paramInt6;
    } else {
      this.vAlign = 2;
    }
  }
  
  public TableLayoutConstraints(String paramString)
  {
    this.col1 = 0;
    this.row1 = 0;
    this.col2 = 0;
    this.row2 = 0;
    this.hAlign = 2;
    this.vAlign = 2;
    StringTokenizer localStringTokenizer = new StringTokenizer(paramString, ", ");
    int i = localStringTokenizer.countTokens();
    try
    {
      if ((i != 2) && (i != 4) && (i != 6)) {
        throw new RuntimeException();
      }
      String str1 = localStringTokenizer.nextToken();
      this.col1 = new Integer(str1).intValue();
      this.col2 = this.col1;
      String str2 = localStringTokenizer.nextToken();
      this.row1 = new Integer(str2).intValue();
      this.row2 = this.row1;
      str1 = localStringTokenizer.nextToken();
      str2 = localStringTokenizer.nextToken();
      try
      {
        this.col2 = new Integer(str1).intValue();
        this.row2 = new Integer(str2).intValue();
        str1 = localStringTokenizer.nextToken();
        str2 = localStringTokenizer.nextToken();
      }
      catch (NumberFormatException localNumberFormatException)
      {
        this.col2 = this.col1;
        this.row2 = this.row1;
      }
      if ((str1.equalsIgnoreCase("L")) || (str1.equalsIgnoreCase("LEFT"))) {
        this.hAlign = 0;
      } else if ((str1.equalsIgnoreCase("C")) || (str1.equalsIgnoreCase("CENTER"))) {
        this.hAlign = 1;
      } else if ((str1.equalsIgnoreCase("F")) || (str1.equalsIgnoreCase("FULL"))) {
        this.hAlign = 2;
      } else if ((str1.equalsIgnoreCase("R")) || (str1.equalsIgnoreCase("RIGHT"))) {
        this.hAlign = 3;
      } else if ((str1.equalsIgnoreCase("LD")) || (str1.equalsIgnoreCase("LEADING"))) {
        this.hAlign = 4;
      } else if ((str1.equalsIgnoreCase("TL")) || (str1.equalsIgnoreCase("TRAILING"))) {
        this.hAlign = 5;
      } else {
        throw new RuntimeException();
      }
      if ((str2.equalsIgnoreCase("T")) || (str2.equalsIgnoreCase("TOP"))) {
        this.vAlign = 0;
      } else if ((str2.equalsIgnoreCase("C")) || (str2.equalsIgnoreCase("CENTER"))) {
        this.vAlign = 1;
      } else if ((str2.equalsIgnoreCase("F")) || (str2.equalsIgnoreCase("FULL"))) {
        this.vAlign = 2;
      } else if ((str2.equalsIgnoreCase("B")) || (str2.equalsIgnoreCase("BOTTOM"))) {
        this.vAlign = 3;
      } else {
        throw new RuntimeException();
      }
    }
    catch (NoSuchElementException localNoSuchElementException) {}catch (RuntimeException localRuntimeException)
    {
      throw new IllegalArgumentException("Expected constraints in one of the following formats:\n  col1, row1\n  col1, row1, col2, row2\n  col1, row1, hAlign, vAlign\n  col1, row1, col2, row2, hAlign, vAlign\nConstraints provided '" + paramString + "'");
    }
    if (this.row2 < this.row1) {
      this.row2 = this.row1;
    }
    if (this.col2 < this.col1) {
      this.col2 = this.col1;
    }
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(this.col1);
    localStringBuffer.append(", ");
    localStringBuffer.append(this.row1);
    localStringBuffer.append(", ");
    localStringBuffer.append(this.col2);
    localStringBuffer.append(", ");
    localStringBuffer.append(this.row2);
    localStringBuffer.append(", ");
    String[] arrayOfString1 = { "left", "center", "full", "right", "leading", "trailing" };
    String[] arrayOfString2 = { "top", "center", "full", "bottom" };
    localStringBuffer.append(arrayOfString1[this.hAlign]);
    localStringBuffer.append(", ");
    localStringBuffer.append(arrayOfString2[this.vAlign]);
    return localStringBuffer.toString();
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/info/clearthought/layout/TableLayoutConstraints.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */