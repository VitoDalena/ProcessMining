package bsh;

class BSHReturnStatement
  extends SimpleNode
  implements ParserConstants
{
  public int kind;
  
  BSHReturnStatement(int paramInt)
  {
    super(paramInt);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    Object localObject;
    if (jjtGetNumChildren() > 0) {
      localObject = ((SimpleNode)jjtGetChild(0)).eval(paramCallStack, paramInterpreter);
    } else {
      localObject = Primitive.VOID;
    }
    return new ReturnControl(this.kind, localObject, this);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHReturnStatement.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */