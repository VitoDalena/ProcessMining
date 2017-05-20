package bsh;

class BSHAmbiguousName
  extends SimpleNode
{
  public String text;
  
  BSHAmbiguousName(int paramInt)
  {
    super(paramInt);
  }
  
  public Name getName(NameSpace paramNameSpace)
  {
    return paramNameSpace.getNameResolver(this.text);
  }
  
  public Object toObject(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    return toObject(paramCallStack, paramInterpreter, false);
  }
  
  Object toObject(CallStack paramCallStack, Interpreter paramInterpreter, boolean paramBoolean)
    throws EvalError
  {
    try
    {
      return getName(paramCallStack.top()).toObject(paramCallStack, paramInterpreter, paramBoolean);
    }
    catch (UtilEvalError localUtilEvalError)
    {
      throw localUtilEvalError.toEvalError(this, paramCallStack);
    }
  }
  
  public Class toClass(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    try
    {
      return getName(paramCallStack.top()).toClass();
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new EvalError(localClassNotFoundException.getMessage(), this, paramCallStack);
    }
    catch (UtilEvalError localUtilEvalError)
    {
      throw localUtilEvalError.toEvalError(this, paramCallStack);
    }
  }
  
  public LHS toLHS(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    try
    {
      return getName(paramCallStack.top()).toLHS(paramCallStack, paramInterpreter);
    }
    catch (UtilEvalError localUtilEvalError)
    {
      throw localUtilEvalError.toEvalError(this, paramCallStack);
    }
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    throw new InterpreterError("Don't know how to eval an ambiguous name!  Use toObject() if you want an object.");
  }
  
  public String toString()
  {
    return "AmbigousName: " + this.text;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHAmbiguousName.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */