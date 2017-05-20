package bsh;

public class ParseException
  extends EvalError
{
  String sourceFile = "<unknown>";
  protected boolean specialConstructor = false;
  public Token currentToken;
  public int[][] expectedTokenSequences;
  public String[] tokenImage;
  protected String eol = System.getProperty("line.separator", "\n");
  
  public void setErrorSourceFile(String paramString)
  {
    this.sourceFile = paramString;
  }
  
  public String getErrorSourceFile()
  {
    return this.sourceFile;
  }
  
  public ParseException(Token paramToken, int[][] paramArrayOfInt, String[] paramArrayOfString)
  {
    this();
    this.specialConstructor = true;
    this.currentToken = paramToken;
    this.expectedTokenSequences = paramArrayOfInt;
    this.tokenImage = paramArrayOfString;
  }
  
  public ParseException()
  {
    this("");
    this.specialConstructor = false;
  }
  
  public ParseException(String paramString)
  {
    super(paramString, null, null);
  }
  
  public String getMessage()
  {
    return getMessage(false);
  }
  
  public String getMessage(boolean paramBoolean)
  {
    if (!this.specialConstructor) {
      return super.getMessage();
    }
    String str1 = "";
    int i = 0;
    for (int j = 0; j < this.expectedTokenSequences.length; j++)
    {
      if (i < this.expectedTokenSequences[j].length) {
        i = this.expectedTokenSequences[j].length;
      }
      for (int k = 0; k < this.expectedTokenSequences[j].length; k++) {
        str1 = str1 + this.tokenImage[this.expectedTokenSequences[j][k]] + " ";
      }
      if (this.expectedTokenSequences[j][(this.expectedTokenSequences[j].length - 1)] != 0) {
        str1 = str1 + "...";
      }
      str1 = str1 + this.eol + "    ";
    }
    String str2 = "In file: " + this.sourceFile + " Encountered \"";
    Token localToken = this.currentToken.next;
    for (int m = 0; m < i; m++)
    {
      if (m != 0) {
        str2 = str2 + " ";
      }
      if (localToken.kind == 0)
      {
        str2 = str2 + this.tokenImage[0];
        break;
      }
      str2 = str2 + add_escapes(localToken.image);
      localToken = localToken.next;
    }
    str2 = str2 + "\" at line " + this.currentToken.next.beginLine + ", column " + this.currentToken.next.beginColumn + "." + this.eol;
    if (paramBoolean)
    {
      if (this.expectedTokenSequences.length == 1) {
        str2 = str2 + "Was expecting:" + this.eol + "    ";
      } else {
        str2 = str2 + "Was expecting one of:" + this.eol + "    ";
      }
      str2 = str2 + str1;
    }
    return str2;
  }
  
  protected String add_escapes(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < paramString.length(); i++) {
      switch (paramString.charAt(i))
      {
      case '\000': 
        break;
      case '\b': 
        localStringBuffer.append("\\b");
        break;
      case '\t': 
        localStringBuffer.append("\\t");
        break;
      case '\n': 
        localStringBuffer.append("\\n");
        break;
      case '\f': 
        localStringBuffer.append("\\f");
        break;
      case '\r': 
        localStringBuffer.append("\\r");
        break;
      case '"': 
        localStringBuffer.append("\\\"");
        break;
      case '\'': 
        localStringBuffer.append("\\'");
        break;
      case '\\': 
        localStringBuffer.append("\\\\");
        break;
      default: 
        char c;
        if (((c = paramString.charAt(i)) < ' ') || (c > '~'))
        {
          String str = "0000" + Integer.toString(c, 16);
          localStringBuffer.append("\\u" + str.substring(str.length() - 4, str.length()));
        }
        else
        {
          localStringBuffer.append(c);
        }
        break;
      }
    }
    return localStringBuffer.toString();
  }
  
  public int getErrorLineNumber()
  {
    return this.currentToken.next.beginLine;
  }
  
  public String getErrorText()
  {
    int i = 0;
    for (int j = 0; j < this.expectedTokenSequences.length; j++) {
      if (i < this.expectedTokenSequences[j].length) {
        i = this.expectedTokenSequences[j].length;
      }
    }
    String str = "";
    Token localToken = this.currentToken.next;
    for (int k = 0; k < i; k++)
    {
      if (k != 0) {
        str = str + " ";
      }
      if (localToken.kind == 0)
      {
        str = str + this.tokenImage[0];
        break;
      }
      str = str + add_escapes(localToken.image);
      localToken = localToken.next;
    }
    return str;
  }
  
  public String toString()
  {
    return getMessage();
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/ParseException.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */