package bsh;

public class UtilEvalError
  extends Exception
{
  protected UtilEvalError() {}
  
  public UtilEvalError(String paramString)
  {
    super(paramString);
  }
  
  public EvalError toEvalError(String paramString, SimpleNode paramSimpleNode, CallStack paramCallStack)
  {
    if (Interpreter.DEBUG) {
      printStackTrace();
    }
    if (paramString == null) {
      paramString = "";
    } else {
      paramString = paramString + ": ";
    }
    return new EvalError(paramString + getMessage(), paramSimpleNode, paramCallStack);
  }
  
  public EvalError toEvalError(SimpleNode paramSimpleNode, CallStack paramCallStack)
  {
    return toEvalError(null, paramSimpleNode, paramCallStack);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/UtilEvalError.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */