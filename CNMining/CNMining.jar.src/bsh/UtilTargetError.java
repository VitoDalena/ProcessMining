package bsh;

public class UtilTargetError
  extends UtilEvalError
{
  public Throwable t;
  
  public UtilTargetError(String paramString, Throwable paramThrowable)
  {
    super(paramString);
    this.t = paramThrowable;
  }
  
  public UtilTargetError(Throwable paramThrowable)
  {
    this(null, paramThrowable);
  }
  
  public EvalError toEvalError(String paramString, SimpleNode paramSimpleNode, CallStack paramCallStack)
  {
    if (paramString == null) {
      paramString = getMessage();
    } else {
      paramString = paramString + ": " + getMessage();
    }
    return new TargetError(paramString, this.t, paramSimpleNode, paramCallStack, false);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/UtilTargetError.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */