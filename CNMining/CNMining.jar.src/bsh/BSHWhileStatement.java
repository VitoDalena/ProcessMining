package bsh;

class BSHWhileStatement
  extends SimpleNode
  implements ParserConstants
{
  public boolean isDoStatement;
  
  BSHWhileStatement(int paramInt)
  {
    super(paramInt);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    int i = jjtGetNumChildren();
    SimpleNode localSimpleNode2 = null;
    SimpleNode localSimpleNode1;
    if (this.isDoStatement)
    {
      localSimpleNode1 = (SimpleNode)jjtGetChild(1);
      localSimpleNode2 = (SimpleNode)jjtGetChild(0);
    }
    else
    {
      localSimpleNode1 = (SimpleNode)jjtGetChild(0);
      if (i > 1) {
        localSimpleNode2 = (SimpleNode)jjtGetChild(1);
      }
    }
    boolean bool = this.isDoStatement;
    while ((bool) || (BSHIfStatement.evaluateCondition(localSimpleNode1, paramCallStack, paramInterpreter))) {
      if (localSimpleNode2 != null)
      {
        Object localObject = localSimpleNode2.eval(paramCallStack, paramInterpreter);
        int j = 0;
        if ((localObject instanceof ReturnControl))
        {
          switch (((ReturnControl)localObject).kind)
          {
          case 46: 
            return localObject;
          case 19: 
            break;
          case 12: 
            j = 1;
          }
        }
        else
        {
          if (j != 0) {
            break;
          }
          bool = false;
        }
      }
    }
    return Primitive.VOID;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHWhileStatement.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */