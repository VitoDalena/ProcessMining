package bsh;

import java.io.Serializable;

abstract interface Node
  extends Serializable
{
  public abstract void jjtOpen();
  
  public abstract void jjtClose();
  
  public abstract void jjtSetParent(Node paramNode);
  
  public abstract Node jjtGetParent();
  
  public abstract void jjtAddChild(Node paramNode, int paramInt);
  
  public abstract Node jjtGetChild(int paramInt);
  
  public abstract int jjtGetNumChildren();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/Node.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */