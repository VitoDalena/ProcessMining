package bsh;

class BSHStatementExpressionList
  extends SimpleNode
{
  BSHStatementExpressionList(int paramInt)
  {
    super(paramInt);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    int i = jjtGetNumChildren();
    for (int j = 0; j < i; j++)
    {
      SimpleNode localSimpleNode = (SimpleNode)jjtGetChild(j);
      localSimpleNode.eval(paramCallStack, paramInterpreter);
    }
    return Primitive.VOID;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHStatementExpressionList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */