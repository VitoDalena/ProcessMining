package bsh;

class BlockNameSpace
  extends NameSpace
{
  public BlockNameSpace(NameSpace paramNameSpace)
    throws EvalError
  {
    super(paramNameSpace, paramNameSpace.getName() + "/BlockNameSpace");
  }
  
  public void setVariable(String paramString, Object paramObject, boolean paramBoolean1, boolean paramBoolean2)
    throws UtilEvalError
  {
    if (weHaveVar(paramString)) {
      super.setVariable(paramString, paramObject, paramBoolean1, false);
    } else {
      getParent().setVariable(paramString, paramObject, paramBoolean1, paramBoolean2);
    }
  }
  
  public void setBlockVariable(String paramString, Object paramObject)
    throws UtilEvalError
  {
    super.setVariable(paramString, paramObject, false, false);
  }
  
  private boolean weHaveVar(String paramString)
  {
    try
    {
      return super.getVariableImpl(paramString, false) != null;
    }
    catch (UtilEvalError localUtilEvalError) {}
    return false;
  }
  
  private NameSpace getNonBlockParent()
  {
    NameSpace localNameSpace = super.getParent();
    if ((localNameSpace instanceof BlockNameSpace)) {
      return ((BlockNameSpace)localNameSpace).getNonBlockParent();
    }
    return localNameSpace;
  }
  
  This getThis(Interpreter paramInterpreter)
  {
    return getNonBlockParent().getThis(paramInterpreter);
  }
  
  public This getSuper(Interpreter paramInterpreter)
  {
    return getNonBlockParent().getSuper(paramInterpreter);
  }
  
  public void importClass(String paramString)
  {
    getParent().importClass(paramString);
  }
  
  public void importPackage(String paramString)
  {
    getParent().importPackage(paramString);
  }
  
  public void setMethod(String paramString, BshMethod paramBshMethod)
    throws UtilEvalError
  {
    getParent().setMethod(paramString, paramBshMethod);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BlockNameSpace.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */