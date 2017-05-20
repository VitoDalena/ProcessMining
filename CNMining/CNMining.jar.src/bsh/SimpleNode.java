package bsh;

import java.io.PrintStream;

class SimpleNode
  implements Node
{
  public static SimpleNode JAVACODE = new SimpleNode(-1)
  {
    public String getSourceFile()
    {
      return "<Called from Java Code>";
    }
    
    public int getLineNumber()
    {
      return -1;
    }
    
    public String getText()
    {
      return "<Compiled Java Code>";
    }
  };
  protected Node parent;
  protected Node[] children;
  protected int id;
  Token firstToken;
  Token lastToken;
  String sourceFile;
  
  public SimpleNode(int paramInt)
  {
    this.id = paramInt;
  }
  
  public void jjtOpen() {}
  
  public void jjtClose() {}
  
  public void jjtSetParent(Node paramNode)
  {
    this.parent = paramNode;
  }
  
  public Node jjtGetParent()
  {
    return this.parent;
  }
  
  public void jjtAddChild(Node paramNode, int paramInt)
  {
    if (this.children == null)
    {
      this.children = new Node[paramInt + 1];
    }
    else if (paramInt >= this.children.length)
    {
      Node[] arrayOfNode = new Node[paramInt + 1];
      System.arraycopy(this.children, 0, arrayOfNode, 0, this.children.length);
      this.children = arrayOfNode;
    }
    this.children[paramInt] = paramNode;
  }
  
  public Node jjtGetChild(int paramInt)
  {
    return this.children[paramInt];
  }
  
  public SimpleNode getChild(int paramInt)
  {
    return (SimpleNode)jjtGetChild(paramInt);
  }
  
  public int jjtGetNumChildren()
  {
    return this.children == null ? 0 : this.children.length;
  }
  
  public String toString()
  {
    return ParserTreeConstants.jjtNodeName[this.id];
  }
  
  public String toString(String paramString)
  {
    return paramString + toString();
  }
  
  public void dump(String paramString)
  {
    System.out.println(toString(paramString));
    if (this.children != null) {
      for (int i = 0; i < this.children.length; i++)
      {
        SimpleNode localSimpleNode = (SimpleNode)this.children[i];
        if (localSimpleNode != null) {
          localSimpleNode.dump(paramString + " ");
        }
      }
    }
  }
  
  public void prune()
  {
    jjtSetParent(null);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    throw new InterpreterError("Unimplemented or inappropriate for " + getClass().getName());
  }
  
  public void setSourceFile(String paramString)
  {
    this.sourceFile = paramString;
  }
  
  public String getSourceFile()
  {
    if (this.sourceFile == null)
    {
      if (this.parent != null) {
        return ((SimpleNode)this.parent).getSourceFile();
      }
      return "<unknown file>";
    }
    return this.sourceFile;
  }
  
  public int getLineNumber()
  {
    return this.firstToken.beginLine;
  }
  
  public String getText()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (Token localToken = this.firstToken; localToken != null; localToken = localToken.next)
    {
      localStringBuffer.append(localToken.image);
      if (!localToken.image.equals(".")) {
        localStringBuffer.append(" ");
      }
      if ((localToken == this.lastToken) || (localToken.image.equals("{")) || (localToken.image.equals(";"))) {
        break;
      }
    }
    return localStringBuffer.toString();
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/SimpleNode.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */