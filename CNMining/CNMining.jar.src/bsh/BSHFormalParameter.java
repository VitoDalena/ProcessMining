package bsh;

class BSHFormalParameter
  extends SimpleNode
{
  public static final Class UNTYPED = null;
  public String name;
  public Class type;
  
  BSHFormalParameter(int paramInt)
  {
    super(paramInt);
  }
  
  public String getTypeDescriptor(CallStack paramCallStack, Interpreter paramInterpreter, String paramString)
  {
    if (jjtGetNumChildren() > 0) {
      return ((BSHType)jjtGetChild(0)).getTypeDescriptor(paramCallStack, paramInterpreter, paramString);
    }
    return "Ljava/lang/Object;";
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    if (jjtGetNumChildren() > 0) {
      this.type = ((BSHType)jjtGetChild(0)).getType(paramCallStack, paramInterpreter);
    } else {
      this.type = UNTYPED;
    }
    return this.type;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHFormalParameter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */