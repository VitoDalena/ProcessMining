package bsh;

class BSHArguments
  extends SimpleNode
{
  BSHArguments(int paramInt)
  {
    super(paramInt);
  }
  
  public Object[] getArguments(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    Object[] arrayOfObject = new Object[jjtGetNumChildren()];
    for (int i = 0; i < arrayOfObject.length; i++)
    {
      arrayOfObject[i] = ((SimpleNode)jjtGetChild(i)).eval(paramCallStack, paramInterpreter);
      if (arrayOfObject[i] == Primitive.VOID) {
        throw new EvalError("Undefined argument: " + ((SimpleNode)jjtGetChild(i)).getText(), this, paramCallStack);
      }
    }
    return arrayOfObject;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHArguments.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */