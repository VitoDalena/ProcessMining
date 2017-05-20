package bsh;

class BSHEnhancedForStatement
  extends SimpleNode
  implements ParserConstants
{
  String varName;
  
  BSHEnhancedForStatement(int paramInt)
  {
    super(paramInt);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    Class localClass = null;
    SimpleNode localSimpleNode2 = null;
    NameSpace localNameSpace = paramCallStack.top();
    SimpleNode localSimpleNode3 = (SimpleNode)jjtGetChild(0);
    int i = jjtGetNumChildren();
    SimpleNode localSimpleNode1;
    if ((localSimpleNode3 instanceof BSHType))
    {
      localClass = ((BSHType)localSimpleNode3).getType(paramCallStack, paramInterpreter);
      localSimpleNode1 = (SimpleNode)jjtGetChild(1);
      if (i > 2) {
        localSimpleNode2 = (SimpleNode)jjtGetChild(2);
      }
    }
    else
    {
      localSimpleNode1 = localSimpleNode3;
      if (i > 1) {
        localSimpleNode2 = (SimpleNode)jjtGetChild(1);
      }
    }
    BlockNameSpace localBlockNameSpace = new BlockNameSpace(localNameSpace);
    paramCallStack.swap(localBlockNameSpace);
    Object localObject1 = localSimpleNode1.eval(paramCallStack, paramInterpreter);
    if (localObject1 == Primitive.NULL) {
      throw new EvalError("The collection, array, map, iterator, or enumeration portion of a for statement cannot be null.", this, paramCallStack);
    }
    CollectionManager localCollectionManager = CollectionManager.getCollectionManager();
    if (!localCollectionManager.isBshIterable(localObject1)) {
      throw new EvalError("Can't iterate over type: " + localObject1.getClass(), this, paramCallStack);
    }
    BshIterator localBshIterator = localCollectionManager.getBshIterator(localObject1);
    Object localObject2 = Primitive.VOID;
    while (localBshIterator.hasNext())
    {
      try
      {
        if (localClass != null) {
          localBlockNameSpace.setTypedVariable(this.varName, localClass, localBshIterator.next(), new Modifiers());
        } else {
          localBlockNameSpace.setVariable(this.varName, localBshIterator.next(), false);
        }
      }
      catch (UtilEvalError localUtilEvalError)
      {
        throw localUtilEvalError.toEvalError("for loop iterator variable:" + this.varName, this, paramCallStack);
      }
      int j = 0;
      if (localSimpleNode2 != null)
      {
        Object localObject3 = localSimpleNode2.eval(paramCallStack, paramInterpreter);
        if ((localObject3 instanceof ReturnControl)) {
          switch (((ReturnControl)localObject3).kind)
          {
          case 46: 
            localObject2 = localObject3;
            j = 1;
            break;
          case 19: 
            break;
          case 12: 
            j = 1;
          }
        }
      }
      if (j != 0) {
        break;
      }
    }
    paramCallStack.swap(localNameSpace);
    return localObject2;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHEnhancedForStatement.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */