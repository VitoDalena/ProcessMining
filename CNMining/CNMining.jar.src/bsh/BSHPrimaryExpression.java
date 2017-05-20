package bsh;

class BSHPrimaryExpression
  extends SimpleNode
{
  BSHPrimaryExpression(int paramInt)
  {
    super(paramInt);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    return eval(false, paramCallStack, paramInterpreter);
  }
  
  public LHS toLHS(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    Object localObject = eval(true, paramCallStack, paramInterpreter);
    if (!(localObject instanceof LHS)) {
      throw new EvalError("Can't assign to:", this, paramCallStack);
    }
    return (LHS)localObject;
  }
  
  private Object eval(boolean paramBoolean, CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    Object localObject = jjtGetChild(0);
    int i = jjtGetNumChildren();
    for (int j = 1; j < i; j++) {
      localObject = ((BSHPrimarySuffix)jjtGetChild(j)).doSuffix(localObject, paramBoolean, paramCallStack, paramInterpreter);
    }
    if ((localObject instanceof SimpleNode)) {
      if ((localObject instanceof BSHAmbiguousName))
      {
        if (paramBoolean) {
          localObject = ((BSHAmbiguousName)localObject).toLHS(paramCallStack, paramInterpreter);
        } else {
          localObject = ((BSHAmbiguousName)localObject).toObject(paramCallStack, paramInterpreter);
        }
      }
      else
      {
        if (paramBoolean) {
          throw new EvalError("Can't assign to prefix.", this, paramCallStack);
        }
        localObject = ((SimpleNode)localObject).eval(paramCallStack, paramInterpreter);
      }
    }
    if ((localObject instanceof LHS))
    {
      if (paramBoolean) {
        return localObject;
      }
      try
      {
        return ((LHS)localObject).getValue();
      }
      catch (UtilEvalError localUtilEvalError)
      {
        throw localUtilEvalError.toEvalError(this, paramCallStack);
      }
    }
    return localObject;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHPrimaryExpression.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */