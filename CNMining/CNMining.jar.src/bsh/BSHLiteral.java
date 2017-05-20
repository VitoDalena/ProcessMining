package bsh;

class BSHLiteral
  extends SimpleNode
{
  public Object value;
  
  BSHLiteral(int paramInt)
  {
    super(paramInt);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    if (this.value == null) {
      throw new InterpreterError("Null in bsh literal: " + this.value);
    }
    return this.value;
  }
  
  private char getEscapeChar(char paramChar)
  {
    switch (paramChar)
    {
    case 'b': 
      paramChar = '\b';
      break;
    case 't': 
      paramChar = '\t';
      break;
    case 'n': 
      paramChar = '\n';
      break;
    case 'f': 
      paramChar = '\f';
      break;
    case 'r': 
      paramChar = '\r';
      break;
    }
    return paramChar;
  }
  
  public void charSetup(String paramString)
  {
    char c = paramString.charAt(0);
    if (c == '\\')
    {
      c = paramString.charAt(1);
      if (Character.isDigit(c)) {
        c = (char)Integer.parseInt(paramString.substring(1), 8);
      } else {
        c = getEscapeChar(c);
      }
    }
    this.value = new Primitive(new Character(c).charValue());
  }
  
  void stringSetup(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < paramString.length(); i++)
    {
      char c = paramString.charAt(i);
      if (c == '\\')
      {
        c = paramString.charAt(++i);
        if (Character.isDigit(c))
        {
          for (int j = i; j < i + 2; j++) {
            if (!Character.isDigit(paramString.charAt(j + 1))) {
              break;
            }
          }
          c = (char)Integer.parseInt(paramString.substring(i, j + 1), 8);
          i = j;
        }
        else
        {
          c = getEscapeChar(c);
        }
      }
      localStringBuffer.append(c);
    }
    this.value = localStringBuffer.toString().intern();
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHLiteral.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */