package bsh;

class BSHMethodDeclaration
  extends SimpleNode
{
  public String name;
  BSHReturnType returnTypeNode;
  BSHFormalParameters paramsNode;
  BSHBlock blockNode;
  int firstThrowsClause;
  public Modifiers modifiers;
  Class returnType;
  int numThrows = 0;
  
  BSHMethodDeclaration(int paramInt)
  {
    super(paramInt);
  }
  
  synchronized void insureNodesParsed()
  {
    if (this.paramsNode != null) {
      return;
    }
    Node localNode = jjtGetChild(0);
    this.firstThrowsClause = 1;
    if ((localNode instanceof BSHReturnType))
    {
      this.returnTypeNode = ((BSHReturnType)localNode);
      this.paramsNode = ((BSHFormalParameters)jjtGetChild(1));
      if (jjtGetNumChildren() > 2 + this.numThrows) {
        this.blockNode = ((BSHBlock)jjtGetChild(2 + this.numThrows));
      }
      this.firstThrowsClause += 1;
    }
    else
    {
      this.paramsNode = ((BSHFormalParameters)jjtGetChild(0));
      this.blockNode = ((BSHBlock)jjtGetChild(1 + this.numThrows));
    }
  }
  
  Class evalReturnType(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    insureNodesParsed();
    if (this.returnTypeNode != null) {
      return this.returnTypeNode.evalReturnType(paramCallStack, paramInterpreter);
    }
    return null;
  }
  
  String getReturnTypeDescriptor(CallStack paramCallStack, Interpreter paramInterpreter, String paramString)
  {
    insureNodesParsed();
    if (this.returnTypeNode == null) {
      return null;
    }
    return this.returnTypeNode.getTypeDescriptor(paramCallStack, paramInterpreter, paramString);
  }
  
  BSHReturnType getReturnTypeNode()
  {
    insureNodesParsed();
    return this.returnTypeNode;
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    this.returnType = evalReturnType(paramCallStack, paramInterpreter);
    evalNodes(paramCallStack, paramInterpreter);
    NameSpace localNameSpace = paramCallStack.top();
    BshMethod localBshMethod = new BshMethod(this, localNameSpace, this.modifiers);
    try
    {
      localNameSpace.setMethod(this.name, localBshMethod);
    }
    catch (UtilEvalError localUtilEvalError)
    {
      throw localUtilEvalError.toEvalError(this, paramCallStack);
    }
    return Primitive.VOID;
  }
  
  private void evalNodes(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    insureNodesParsed();
    for (int i = this.firstThrowsClause; i < this.numThrows + this.firstThrowsClause; i++) {
      ((BSHAmbiguousName)jjtGetChild(i)).toClass(paramCallStack, paramInterpreter);
    }
    this.paramsNode.eval(paramCallStack, paramInterpreter);
    if (paramInterpreter.getStrictJava())
    {
      for (int j = 0; j < this.paramsNode.paramTypes.length; j++) {
        if (this.paramsNode.paramTypes[j] == null) {
          throw new EvalError("(Strict Java Mode) Undeclared argument type, parameter: " + this.paramsNode.getParamNames()[j] + " in method: " + this.name, this, null);
        }
      }
      if (this.returnType == null) {
        throw new EvalError("(Strict Java Mode) Undeclared return type for method: " + this.name, this, null);
      }
    }
  }
  
  public String toString()
  {
    return "MethodDeclaration: " + this.name;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHMethodDeclaration.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */