package bsh;

class BSHFormalParameters
  extends SimpleNode
{
  private String[] paramNames;
  Class[] paramTypes;
  int numArgs;
  String[] typeDescriptors;
  
  BSHFormalParameters(int paramInt)
  {
    super(paramInt);
  }
  
  void insureParsed()
  {
    if (this.paramNames != null) {
      return;
    }
    this.numArgs = jjtGetNumChildren();
    String[] arrayOfString = new String[this.numArgs];
    for (int i = 0; i < this.numArgs; i++)
    {
      BSHFormalParameter localBSHFormalParameter = (BSHFormalParameter)jjtGetChild(i);
      arrayOfString[i] = localBSHFormalParameter.name;
    }
    this.paramNames = arrayOfString;
  }
  
  public String[] getParamNames()
  {
    insureParsed();
    return this.paramNames;
  }
  
  public String[] getTypeDescriptors(CallStack paramCallStack, Interpreter paramInterpreter, String paramString)
  {
    if (this.typeDescriptors != null) {
      return this.typeDescriptors;
    }
    insureParsed();
    String[] arrayOfString = new String[this.numArgs];
    for (int i = 0; i < this.numArgs; i++)
    {
      BSHFormalParameter localBSHFormalParameter = (BSHFormalParameter)jjtGetChild(i);
      arrayOfString[i] = localBSHFormalParameter.getTypeDescriptor(paramCallStack, paramInterpreter, paramString);
    }
    this.typeDescriptors = arrayOfString;
    return arrayOfString;
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    if (this.paramTypes != null) {
      return this.paramTypes;
    }
    insureParsed();
    Class[] arrayOfClass = new Class[this.numArgs];
    for (int i = 0; i < this.numArgs; i++)
    {
      BSHFormalParameter localBSHFormalParameter = (BSHFormalParameter)jjtGetChild(i);
      arrayOfClass[i] = ((Class)localBSHFormalParameter.eval(paramCallStack, paramInterpreter));
    }
    this.paramTypes = arrayOfClass;
    return arrayOfClass;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHFormalParameters.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */