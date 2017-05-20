package bsh;

class BSHBlock
  extends SimpleNode
{
  public boolean isSynchronized = false;
  
  BSHBlock(int paramInt)
  {
    super(paramInt);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    return eval(paramCallStack, paramInterpreter, false);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter, boolean paramBoolean)
    throws EvalError
  {
    Object localObject1 = null;
    if (this.isSynchronized)
    {
      localObject2 = (SimpleNode)jjtGetChild(0);
      localObject1 = ((SimpleNode)localObject2).eval(paramCallStack, paramInterpreter);
    }
    if (this.isSynchronized) {
      synchronized (localObject1)
      {
        localObject2 = evalBlock(paramCallStack, paramInterpreter, paramBoolean, null);
      }
    }
    Object localObject2 = evalBlock(paramCallStack, paramInterpreter, paramBoolean, null);
    return localObject2;
  }
  
  Object evalBlock(CallStack paramCallStack, Interpreter paramInterpreter, boolean paramBoolean, NodeFilter paramNodeFilter)
    throws EvalError
  {
    Object localObject1 = Primitive.VOID;
    NameSpace localNameSpace = null;
    if (!paramBoolean)
    {
      localNameSpace = paramCallStack.top();
      BlockNameSpace localBlockNameSpace = new BlockNameSpace(localNameSpace);
      paramCallStack.swap(localBlockNameSpace);
    }
    SimpleNode localSimpleNode1 = this.isSynchronized ? 1 : 0;
    SimpleNode localSimpleNode2 = jjtGetNumChildren();
    try
    {
      for (int i = localSimpleNode1; i < localSimpleNode2; i++)
      {
        localSimpleNode3 = (SimpleNode)jjtGetChild(i);
        if (((paramNodeFilter == null) || (paramNodeFilter.isVisible(localSimpleNode3))) && ((localSimpleNode3 instanceof BSHClassDeclaration))) {
          localSimpleNode3.eval(paramCallStack, paramInterpreter);
        }
      }
      for (SimpleNode localSimpleNode3 = localSimpleNode1; localSimpleNode3 < localSimpleNode2; localSimpleNode3++)
      {
        SimpleNode localSimpleNode4 = (SimpleNode)jjtGetChild(localSimpleNode3);
        if ((!(localSimpleNode4 instanceof BSHClassDeclaration)) && ((paramNodeFilter == null) || (paramNodeFilter.isVisible(localSimpleNode4))))
        {
          localObject1 = localSimpleNode4.eval(paramCallStack, paramInterpreter);
          if ((localObject1 instanceof ReturnControl)) {
            break;
          }
        }
      }
    }
    finally
    {
      if (!paramBoolean) {
        paramCallStack.swap(localNameSpace);
      }
    }
    return localObject1;
  }
  
  public static abstract interface NodeFilter
  {
    public abstract boolean isVisible(SimpleNode paramSimpleNode);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHBlock.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */