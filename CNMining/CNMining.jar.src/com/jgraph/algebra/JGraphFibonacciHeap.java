package com.jgraph.algebra;

import java.util.Hashtable;
import java.util.Map;

public class JGraphFibonacciHeap
{
  protected Map nodes = new Hashtable();
  protected Node min;
  protected int size;
  
  public Node getNode(Object paramObject, boolean paramBoolean)
  {
    Node localNode = (Node)this.nodes.get(paramObject);
    if ((localNode == null) && (paramBoolean))
    {
      localNode = new Node(paramObject, Double.MAX_VALUE);
      this.nodes.put(paramObject, localNode);
      insert(localNode, localNode.getKey());
    }
    return localNode;
  }
  
  public boolean isEmpty()
  {
    return this.min == null;
  }
  
  public void decreaseKey(Node paramNode, double paramDouble)
  {
    if (paramDouble > paramNode.key) {
      throw new IllegalArgumentException("decreaseKey() got larger key value");
    }
    paramNode.key = paramDouble;
    Node localNode = paramNode.parent;
    if ((localNode != null) && (paramNode.key < localNode.key))
    {
      cut(paramNode, localNode);
      cascadingCut(localNode);
    }
    if (paramNode.key < this.min.key) {
      this.min = paramNode;
    }
  }
  
  public void delete(Node paramNode)
  {
    decreaseKey(paramNode, Double.NEGATIVE_INFINITY);
    removeMin();
  }
  
  public void insert(Node paramNode, double paramDouble)
  {
    paramNode.key = paramDouble;
    if (this.min != null)
    {
      paramNode.left = this.min;
      paramNode.right = this.min.right;
      this.min.right = paramNode;
      paramNode.right.left = paramNode;
      if (paramDouble < this.min.key) {
        this.min = paramNode;
      }
    }
    else
    {
      this.min = paramNode;
    }
    this.size += 1;
  }
  
  public Node min()
  {
    return this.min;
  }
  
  public Node removeMin()
  {
    Node localNode1 = this.min;
    if (localNode1 != null)
    {
      int i = localNode1.degree;
      Object localObject = localNode1.child;
      while (i > 0)
      {
        Node localNode2 = ((Node)localObject).right;
        ((Node)localObject).left.right = ((Node)localObject).right;
        ((Node)localObject).right.left = ((Node)localObject).left;
        ((Node)localObject).left = this.min;
        ((Node)localObject).right = this.min.right;
        this.min.right = ((Node)localObject);
        ((Node)localObject).right.left = ((Node)localObject);
        ((Node)localObject).parent = null;
        localObject = localNode2;
        i--;
      }
      localNode1.left.right = localNode1.right;
      localNode1.right.left = localNode1.left;
      if (localNode1 == localNode1.right)
      {
        this.min = null;
      }
      else
      {
        this.min = localNode1.right;
        consolidate();
      }
      this.size -= 1;
    }
    return localNode1;
  }
  
  public int size()
  {
    return this.size;
  }
  
  public static JGraphFibonacciHeap union(JGraphFibonacciHeap paramJGraphFibonacciHeap1, JGraphFibonacciHeap paramJGraphFibonacciHeap2)
  {
    JGraphFibonacciHeap localJGraphFibonacciHeap = new JGraphFibonacciHeap();
    if ((paramJGraphFibonacciHeap1 != null) && (paramJGraphFibonacciHeap2 != null))
    {
      localJGraphFibonacciHeap.min = paramJGraphFibonacciHeap1.min;
      if (localJGraphFibonacciHeap.min != null)
      {
        if (paramJGraphFibonacciHeap2.min != null)
        {
          localJGraphFibonacciHeap.min.right.left = paramJGraphFibonacciHeap2.min.left;
          paramJGraphFibonacciHeap2.min.left.right = localJGraphFibonacciHeap.min.right;
          localJGraphFibonacciHeap.min.right = paramJGraphFibonacciHeap2.min;
          paramJGraphFibonacciHeap2.min.left = localJGraphFibonacciHeap.min;
          if (paramJGraphFibonacciHeap2.min.key < paramJGraphFibonacciHeap1.min.key) {
            localJGraphFibonacciHeap.min = paramJGraphFibonacciHeap2.min;
          }
        }
      }
      else {
        localJGraphFibonacciHeap.min = paramJGraphFibonacciHeap2.min;
      }
      paramJGraphFibonacciHeap1.size += paramJGraphFibonacciHeap2.size;
    }
    return localJGraphFibonacciHeap;
  }
  
  protected void cascadingCut(Node paramNode)
  {
    Node localNode = paramNode.parent;
    if (localNode != null) {
      if (!paramNode.mark)
      {
        paramNode.mark = true;
      }
      else
      {
        cut(paramNode, localNode);
        cascadingCut(localNode);
      }
    }
  }
  
  protected void consolidate()
  {
    int i = this.size + 1;
    Node[] arrayOfNode = new Node[i];
    for (int j = 0; j < i; j++) {
      arrayOfNode[j] = null;
    }
    j = 0;
    Object localObject1 = this.min;
    if (localObject1 != null)
    {
      j++;
      for (localObject1 = ((Node)localObject1).right; localObject1 != this.min; localObject1 = ((Node)localObject1).right) {
        j++;
      }
    }
    while (j > 0)
    {
      k = ((Node)localObject1).degree;
      Node localNode = ((Node)localObject1).right;
      while (arrayOfNode[k] != null)
      {
        Object localObject2 = arrayOfNode[k];
        if (((Node)localObject1).key > ((Node)localObject2).key)
        {
          Object localObject3 = localObject2;
          localObject2 = localObject1;
          localObject1 = localObject3;
        }
        link((Node)localObject2, (Node)localObject1);
        arrayOfNode[k] = null;
        k++;
      }
      arrayOfNode[k] = localObject1;
      localObject1 = localNode;
      j--;
    }
    this.min = null;
    for (int k = 0; k < i; k++) {
      if (arrayOfNode[k] != null) {
        if (this.min != null)
        {
          arrayOfNode[k].left.right = arrayOfNode[k].right;
          arrayOfNode[k].right.left = arrayOfNode[k].left;
          arrayOfNode[k].left = this.min;
          arrayOfNode[k].right = this.min.right;
          this.min.right = arrayOfNode[k];
          arrayOfNode[k].right.left = arrayOfNode[k];
          if (arrayOfNode[k].key < this.min.key) {
            this.min = arrayOfNode[k];
          }
        }
        else
        {
          this.min = arrayOfNode[k];
        }
      }
    }
  }
  
  protected void cut(Node paramNode1, Node paramNode2)
  {
    paramNode1.left.right = paramNode1.right;
    paramNode1.right.left = paramNode1.left;
    paramNode2.degree -= 1;
    if (paramNode2.child == paramNode1) {
      paramNode2.child = paramNode1.right;
    }
    if (paramNode2.degree == 0) {
      paramNode2.child = null;
    }
    paramNode1.left = this.min;
    paramNode1.right = this.min.right;
    this.min.right = paramNode1;
    paramNode1.right.left = paramNode1;
    paramNode1.parent = null;
    paramNode1.mark = false;
  }
  
  protected void link(Node paramNode1, Node paramNode2)
  {
    paramNode1.left.right = paramNode1.right;
    paramNode1.right.left = paramNode1.left;
    paramNode1.parent = paramNode2;
    if (paramNode2.child == null)
    {
      paramNode2.child = paramNode1;
      paramNode1.right = paramNode1;
      paramNode1.left = paramNode1;
    }
    else
    {
      paramNode1.left = paramNode2.child;
      paramNode1.right = paramNode2.child.right;
      paramNode2.child.right = paramNode1;
      paramNode1.right.left = paramNode1;
    }
    paramNode2.degree += 1;
    paramNode1.mark = false;
  }
  
  public static class Node
  {
    Object userObject;
    Node child;
    Node left;
    Node parent;
    Node right;
    boolean mark;
    double key;
    int degree;
    
    public Node(Object paramObject, double paramDouble)
    {
      this.userObject = paramObject;
      this.right = this;
      this.left = this;
      this.key = paramDouble;
    }
    
    public final double getKey()
    {
      return this.key;
    }
    
    public Object getUserObject()
    {
      return this.userObject;
    }
    
    public void setUserObject(Object paramObject)
    {
      this.userObject = paramObject;
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/algebra/JGraphFibonacciHeap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */