package bsh;

class BSHSwitchStatement
  extends SimpleNode
  implements ParserConstants
{
  public BSHSwitchStatement(int paramInt)
  {
    super(paramInt);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    int i = jjtGetNumChildren();
    int j = 0;
    SimpleNode localSimpleNode = (SimpleNode)jjtGetChild(j++);
    Object localObject1 = localSimpleNode.eval(paramCallStack, paramInterpreter);
    ReturnControl localReturnControl = null;
    if (j >= i) {
      throw new EvalError("Empty switch statement.", this, paramCallStack);
    }
    BSHSwitchLabel localBSHSwitchLabel = (BSHSwitchLabel)jjtGetChild(j++);
    while ((j < i) && (localReturnControl == null))
    {
      if (!localBSHSwitchLabel.isDefault)
      {
        if (!primitiveEquals(localObject1, localBSHSwitchLabel.eval(paramCallStack, paramInterpreter), paramCallStack, localSimpleNode)) {}
      }
      else
      {
        while (j < i)
        {
          localNode = jjtGetChild(j++);
          if (!(localNode instanceof BSHSwitchLabel))
          {
            localObject2 = ((SimpleNode)localNode).eval(paramCallStack, paramInterpreter);
            if ((localObject2 instanceof ReturnControl))
            {
              localReturnControl = (ReturnControl)localObject2;
              break;
            }
          }
        }
        continue;
      }
      while (j < i)
      {
        Object localObject2;
        Node localNode = jjtGetChild(j++);
        if ((localNode instanceof BSHSwitchLabel))
        {
          localBSHSwitchLabel = (BSHSwitchLabel)localNode;
          break;
        }
      }
    }
    if ((localReturnControl != null) && (localReturnControl.kind == 46)) {
      return localReturnControl;
    }
    return Primitive.VOID;
  }
  
  private boolean primitiveEquals(Object paramObject1, Object paramObject2, CallStack paramCallStack, SimpleNode paramSimpleNode)
    throws EvalError
  {
    if (((paramObject1 instanceof Primitive)) || ((paramObject2 instanceof Primitive))) {
      try
      {
        Object localObject = Primitive.binaryOperation(paramObject1, paramObject2, 90);
        localObject = Primitive.unwrap(localObject);
        return localObject.equals(Boolean.TRUE);
      }
      catch (UtilEvalError localUtilEvalError)
      {
        throw localUtilEvalError.toEvalError("Switch value: " + paramSimpleNode.getText() + ": ", this, paramCallStack);
      }
    }
    return paramObject1.equals(paramObject2);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHSwitchStatement.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */