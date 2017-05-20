package bsh;

import java.util.Vector;

public class CallStack
{
  private Vector stack = new Vector(2);
  
  public CallStack() {}
  
  public CallStack(NameSpace paramNameSpace)
  {
    push(paramNameSpace);
  }
  
  public void clear()
  {
    this.stack.removeAllElements();
  }
  
  public void push(NameSpace paramNameSpace)
  {
    this.stack.insertElementAt(paramNameSpace, 0);
  }
  
  public NameSpace top()
  {
    return get(0);
  }
  
  public NameSpace get(int paramInt)
  {
    if (paramInt >= depth()) {
      return NameSpace.JAVACODE;
    }
    return (NameSpace)this.stack.elementAt(paramInt);
  }
  
  public void set(int paramInt, NameSpace paramNameSpace)
  {
    this.stack.setElementAt(paramNameSpace, paramInt);
  }
  
  public NameSpace pop()
  {
    if (depth() < 1) {
      throw new InterpreterError("pop on empty CallStack");
    }
    NameSpace localNameSpace = top();
    this.stack.removeElementAt(0);
    return localNameSpace;
  }
  
  public NameSpace swap(NameSpace paramNameSpace)
  {
    NameSpace localNameSpace = (NameSpace)this.stack.elementAt(0);
    this.stack.setElementAt(paramNameSpace, 0);
    return localNameSpace;
  }
  
  public int depth()
  {
    return this.stack.size();
  }
  
  public NameSpace[] toArray()
  {
    NameSpace[] arrayOfNameSpace = new NameSpace[depth()];
    this.stack.copyInto(arrayOfNameSpace);
    return arrayOfNameSpace;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("CallStack:\n");
    NameSpace[] arrayOfNameSpace = toArray();
    for (int i = 0; i < arrayOfNameSpace.length; i++) {
      localStringBuffer.append("\t" + arrayOfNameSpace[i] + "\n");
    }
    return localStringBuffer.toString();
  }
  
  public CallStack copy()
  {
    CallStack localCallStack = new CallStack();
    localCallStack.stack = ((Vector)this.stack.clone());
    return localCallStack;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/CallStack.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */