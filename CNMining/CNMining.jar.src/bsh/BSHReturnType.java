package bsh;

class BSHReturnType
  extends SimpleNode
{
  public boolean isVoid;
  
  BSHReturnType(int paramInt)
  {
    super(paramInt);
  }
  
  BSHType getTypeNode()
  {
    return (BSHType)jjtGetChild(0);
  }
  
  public String getTypeDescriptor(CallStack paramCallStack, Interpreter paramInterpreter, String paramString)
  {
    if (this.isVoid) {
      return "V";
    }
    return getTypeNode().getTypeDescriptor(paramCallStack, paramInterpreter, paramString);
  }
  
  public Class evalReturnType(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    if (this.isVoid) {
      return Void.TYPE;
    }
    return getTypeNode().getType(paramCallStack, paramInterpreter);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHReturnType.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */