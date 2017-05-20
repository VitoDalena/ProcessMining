package bsh;

class BSHThrowStatement
  extends SimpleNode
{
  BSHThrowStatement(int paramInt)
  {
    super(paramInt);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    Object localObject = ((SimpleNode)jjtGetChild(0)).eval(paramCallStack, paramInterpreter);
    if (!(localObject instanceof Exception)) {
      throw new EvalError("Expression in 'throw' must be Exception type", this, paramCallStack);
    }
    throw new TargetError((Exception)localObject, this, paramCallStack);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHThrowStatement.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */