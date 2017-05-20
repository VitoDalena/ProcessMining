package com.jgraph.algebra;

import java.util.Hashtable;
import java.util.Map;

public class JGraphUnionFind
{
  protected Map nodes = new Hashtable();
  
  public JGraphUnionFind(Object[] paramArrayOfObject)
  {
    for (int i = 0; i < paramArrayOfObject.length; i++) {
      this.nodes.put(paramArrayOfObject[i], new Node());
    }
  }
  
  public Node getNode(Object paramObject)
  {
    return (Node)this.nodes.get(paramObject);
  }
  
  public Node find(Node paramNode)
  {
    while (paramNode.getParent().getParent() != paramNode.getParent())
    {
      Node localNode = paramNode.getParent().getParent();
      paramNode.setParent(localNode);
      paramNode = localNode;
    }
    return paramNode.getParent();
  }
  
  public void union(Node paramNode1, Node paramNode2)
  {
    Node localNode1 = find(paramNode1);
    Node localNode2 = find(paramNode2);
    if (localNode1 != localNode2) {
      if (localNode1.getSize() < localNode2.getSize())
      {
        localNode2.setParent(localNode1);
        localNode1.setSize(localNode1.getSize() + localNode2.getSize());
      }
      else
      {
        localNode1.setParent(localNode2);
        localNode2.setSize(localNode1.getSize() + localNode2.getSize());
      }
    }
  }
  
  public boolean differ(Object paramObject1, Object paramObject2)
  {
    Node localNode1 = find(getNode(paramObject1));
    Node localNode2 = find(getNode(paramObject2));
    return localNode1 != localNode2;
  }
  
  public class Node
  {
    protected Node parent = this;
    protected int size = 1;
    
    public Node() {}
    
    public Node getParent()
    {
      return this.parent;
    }
    
    public void setParent(Node paramNode)
    {
      this.parent = paramNode;
    }
    
    public int getSize()
    {
      return this.size;
    }
    
    public void setSize(int paramInt)
    {
      this.size = paramInt;
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/algebra/JGraphUnionFind.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */