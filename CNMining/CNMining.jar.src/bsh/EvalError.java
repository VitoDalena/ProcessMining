package bsh;

public class EvalError
  extends Exception
{
  SimpleNode node;
  String message;
  CallStack callstack;
  
  public EvalError(String paramString, SimpleNode paramSimpleNode, CallStack paramCallStack)
  {
    setMessage(paramString);
    this.node = paramSimpleNode;
    if (paramCallStack != null) {
      this.callstack = paramCallStack.copy();
    }
  }
  
  public String toString()
  {
    String str;
    if (this.node != null) {
      str = " : at Line: " + this.node.getLineNumber() + " : in file: " + this.node.getSourceFile() + " : " + this.node.getText();
    } else {
      str = ": <at unknown location>";
    }
    if (this.callstack != null) {
      str = str + "\n" + getScriptStackTrace();
    }
    return getMessage() + str;
  }
  
  public void reThrow(String paramString)
    throws EvalError
  {
    prependMessage(paramString);
    throw this;
  }
  
  SimpleNode getNode()
  {
    return this.node;
  }
  
  void setNode(SimpleNode paramSimpleNode)
  {
    this.node = paramSimpleNode;
  }
  
  public String getErrorText()
  {
    if (this.node != null) {
      return this.node.getText();
    }
    return "<unknown error>";
  }
  
  public int getErrorLineNumber()
  {
    if (this.node != null) {
      return this.node.getLineNumber();
    }
    return -1;
  }
  
  public String getErrorSourceFile()
  {
    if (this.node != null) {
      return this.node.getSourceFile();
    }
    return "<unknown file>";
  }
  
  public String getScriptStackTrace()
  {
    if (this.callstack == null) {
      return "<Unknown>";
    }
    String str = "";
    CallStack localCallStack = this.callstack.copy();
    while (localCallStack.depth() > 0)
    {
      NameSpace localNameSpace = localCallStack.pop();
      SimpleNode localSimpleNode = localNameSpace.getNode();
      if (localNameSpace.isMethod)
      {
        str = str + "\nCalled from method: " + localNameSpace.getName();
        if (localSimpleNode != null) {
          str = str + " : at Line: " + localSimpleNode.getLineNumber() + " : in file: " + localSimpleNode.getSourceFile() + " : " + localSimpleNode.getText();
        }
      }
    }
    return str;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public void setMessage(String paramString)
  {
    this.message = paramString;
  }
  
  protected void prependMessage(String paramString)
  {
    if (paramString == null) {
      return;
    }
    if (this.message == null) {
      this.message = paramString;
    } else {
      this.message = (paramString + " : " + this.message);
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/EvalError.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */