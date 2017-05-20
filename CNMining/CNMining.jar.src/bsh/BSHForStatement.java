package bsh;

class BSHForStatement
  extends SimpleNode
  implements ParserConstants
{
  public boolean hasForInit;
  public boolean hasExpression;
  public boolean hasForUpdate;
  private SimpleNode forInit;
  private SimpleNode expression;
  private SimpleNode forUpdate;
  private SimpleNode statement;
  private boolean parsed;
  
  BSHForStatement(int paramInt)
  {
    super(paramInt);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    int i = 0;
    if (this.hasForInit) {
      this.forInit = ((SimpleNode)jjtGetChild(i++));
    }
    if (this.hasExpression) {
      this.expression = ((SimpleNode)jjtGetChild(i++));
    }
    if (this.hasForUpdate) {
      this.forUpdate = ((SimpleNode)jjtGetChild(i++));
    }
    if (i < jjtGetNumChildren()) {
      this.statement = ((SimpleNode)jjtGetChild(i));
    }
    NameSpace localNameSpace = paramCallStack.top();
    BlockNameSpace localBlockNameSpace = new BlockNameSpace(localNameSpace);
    paramCallStack.swap(localBlockNameSpace);
    if (this.hasForInit) {
      this.forInit.eval(paramCallStack, paramInterpreter);
    }
    Object localObject1 = Primitive.VOID;
    for (;;)
    {
      if (this.hasExpression)
      {
        bool = BSHIfStatement.evaluateCondition(this.expression, paramCallStack, paramInterpreter);
        if (!bool) {
          break;
        }
      }
      boolean bool = false;
      if (this.statement != null)
      {
        Object localObject2 = this.statement.eval(paramCallStack, paramInterpreter);
        if ((localObject2 instanceof ReturnControl)) {
          switch (((ReturnControl)localObject2).kind)
          {
          case 46: 
            localObject1 = localObject2;
            bool = true;
            break;
          case 19: 
            break;
          case 12: 
            bool = true;
          }
        }
      }
      if (bool) {
        break;
      }
      if (this.hasForUpdate) {
        this.forUpdate.eval(paramCallStack, paramInterpreter);
      }
    }
    paramCallStack.swap(localNameSpace);
    return localObject1;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHForStatement.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */