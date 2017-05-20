package bsh;

class BSHIfStatement
  extends SimpleNode
{
  BSHIfStatement(int paramInt)
  {
    super(paramInt);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    Object localObject = null;
    if (evaluateCondition((SimpleNode)jjtGetChild(0), paramCallStack, paramInterpreter)) {
      localObject = ((SimpleNode)jjtGetChild(1)).eval(paramCallStack, paramInterpreter);
    } else if (jjtGetNumChildren() > 2) {
      localObject = ((SimpleNode)jjtGetChild(2)).eval(paramCallStack, paramInterpreter);
    }
    if ((localObject instanceof ReturnControl)) {
      return localObject;
    }
    return Primitive.VOID;
  }
  
  public static boolean evaluateCondition(SimpleNode paramSimpleNode, CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    Object localObject = paramSimpleNode.eval(paramCallStack, paramInterpreter);
    if ((localObject instanceof Primitive))
    {
      if (localObject == Primitive.VOID) {
        throw new EvalError("Condition evaluates to void type", paramSimpleNode, paramCallStack);
      }
      localObject = ((Primitive)localObject).getValue();
    }
    if ((localObject instanceof Boolean)) {
      return ((Boolean)localObject).booleanValue();
    }
    throw new EvalError("Condition must evaluate to a Boolean or boolean.", paramSimpleNode, paramCallStack);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHIfStatement.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */