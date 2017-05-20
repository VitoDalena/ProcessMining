package bsh;

class BSHTypedVariableDeclaration
  extends SimpleNode
{
  public Modifiers modifiers;
  
  BSHTypedVariableDeclaration(int paramInt)
  {
    super(paramInt);
  }
  
  private BSHType getTypeNode()
  {
    return (BSHType)jjtGetChild(0);
  }
  
  Class evalType(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    BSHType localBSHType = getTypeNode();
    return localBSHType.getType(paramCallStack, paramInterpreter);
  }
  
  BSHVariableDeclarator[] getDeclarators()
  {
    int i = jjtGetNumChildren();
    int j = 1;
    BSHVariableDeclarator[] arrayOfBSHVariableDeclarator = new BSHVariableDeclarator[i - j];
    for (int k = j; k < i; k++) {
      arrayOfBSHVariableDeclarator[(k - j)] = ((BSHVariableDeclarator)jjtGetChild(k));
    }
    return arrayOfBSHVariableDeclarator;
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    try
    {
      NameSpace localNameSpace = paramCallStack.top();
      BSHType localBSHType = getTypeNode();
      Class localClass = localBSHType.getType(paramCallStack, paramInterpreter);
      BSHVariableDeclarator[] arrayOfBSHVariableDeclarator = getDeclarators();
      for (int i = 0; i < arrayOfBSHVariableDeclarator.length; i++)
      {
        BSHVariableDeclarator localBSHVariableDeclarator = arrayOfBSHVariableDeclarator[i];
        Object localObject = localBSHVariableDeclarator.eval(localBSHType, paramCallStack, paramInterpreter);
        try
        {
          localNameSpace.setTypedVariable(localBSHVariableDeclarator.name, localClass, localObject, this.modifiers);
        }
        catch (UtilEvalError localUtilEvalError)
        {
          throw localUtilEvalError.toEvalError(this, paramCallStack);
        }
      }
    }
    catch (EvalError localEvalError)
    {
      localEvalError.reThrow("Typed variable declaration");
    }
    return Primitive.VOID;
  }
  
  public String getTypeDescriptor(CallStack paramCallStack, Interpreter paramInterpreter, String paramString)
  {
    return getTypeNode().getTypeDescriptor(paramCallStack, paramInterpreter, paramString);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHTypedVariableDeclaration.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */