package bsh;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

public class TargetError
  extends EvalError
{
  Throwable target;
  boolean inNativeCode;
  
  public TargetError(String paramString, Throwable paramThrowable, SimpleNode paramSimpleNode, CallStack paramCallStack, boolean paramBoolean)
  {
    super(paramString, paramSimpleNode, paramCallStack);
    this.target = paramThrowable;
    this.inNativeCode = paramBoolean;
  }
  
  public TargetError(Throwable paramThrowable, SimpleNode paramSimpleNode, CallStack paramCallStack)
  {
    this("TargetError", paramThrowable, paramSimpleNode, paramCallStack, false);
  }
  
  public Throwable getTarget()
  {
    if ((this.target instanceof InvocationTargetException)) {
      return ((InvocationTargetException)this.target).getTargetException();
    }
    return this.target;
  }
  
  public String toString()
  {
    return super.toString() + "\nTarget exception: " + printTargetError(this.target);
  }
  
  public void printStackTrace()
  {
    printStackTrace(false, System.err);
  }
  
  public void printStackTrace(PrintStream paramPrintStream)
  {
    printStackTrace(false, paramPrintStream);
  }
  
  public void printStackTrace(boolean paramBoolean, PrintStream paramPrintStream)
  {
    if (paramBoolean)
    {
      super.printStackTrace(paramPrintStream);
      paramPrintStream.println("--- Target Stack Trace ---");
    }
    this.target.printStackTrace(paramPrintStream);
  }
  
  public String printTargetError(Throwable paramThrowable)
  {
    String str = this.target.toString();
    if (Capabilities.canGenerateInterfaces()) {
      str = str + "\n" + xPrintTargetError(paramThrowable);
    }
    return str;
  }
  
  public String xPrintTargetError(Throwable paramThrowable)
  {
    String str = "import java.lang.reflect.UndeclaredThrowableException;String result=\"\";while ( target instanceof UndeclaredThrowableException ) {\ttarget=target.getUndeclaredThrowable(); \tresult+=\"Nested: \"+target.toString();}return result;";
    Interpreter localInterpreter = new Interpreter();
    try
    {
      localInterpreter.set("target", paramThrowable);
      return (String)localInterpreter.eval(str);
    }
    catch (EvalError localEvalError)
    {
      throw new InterpreterError("xprintarget: " + localEvalError.toString());
    }
  }
  
  public boolean inNativeCode()
  {
    return this.inNativeCode;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/TargetError.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */