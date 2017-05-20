package bsh;

class BSHCastExpression
  extends SimpleNode
{
  public BSHCastExpression(int paramInt)
  {
    super(paramInt);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    NameSpace localNameSpace = paramCallStack.top();
    Class localClass1 = ((BSHType)jjtGetChild(0)).getType(paramCallStack, paramInterpreter);
    SimpleNode localSimpleNode = (SimpleNode)jjtGetChild(1);
    Object localObject = localSimpleNode.eval(paramCallStack, paramInterpreter);
    Class localClass2 = localObject.getClass();
    try
    {
      return Types.castObject(localObject, localClass1, 0);
    }
    catch (UtilEvalError localUtilEvalError)
    {
      throw localUtilEvalError.toEvalError(this, paramCallStack);
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHCastExpression.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */