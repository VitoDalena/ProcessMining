package bsh;

class BSHImportDeclaration
  extends SimpleNode
{
  public boolean importPackage;
  public boolean staticImport;
  public boolean superImport;
  
  BSHImportDeclaration(int paramInt)
  {
    super(paramInt);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    NameSpace localNameSpace = paramCallStack.top();
    if (this.superImport)
    {
      try
      {
        localNameSpace.doSuperImport();
      }
      catch (UtilEvalError localUtilEvalError)
      {
        throw localUtilEvalError.toEvalError(this, paramCallStack);
      }
    }
    else
    {
      Object localObject;
      if (this.staticImport)
      {
        if (this.importPackage)
        {
          localObject = ((BSHAmbiguousName)jjtGetChild(0)).toClass(paramCallStack, paramInterpreter);
          localNameSpace.importStatic((Class)localObject);
        }
        else
        {
          throw new EvalError("static field imports not supported yet", this, paramCallStack);
        }
      }
      else
      {
        localObject = ((BSHAmbiguousName)jjtGetChild(0)).text;
        if (this.importPackage) {
          localNameSpace.importPackage((String)localObject);
        } else {
          localNameSpace.importClass((String)localObject);
        }
      }
    }
    return Primitive.VOID;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHImportDeclaration.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */