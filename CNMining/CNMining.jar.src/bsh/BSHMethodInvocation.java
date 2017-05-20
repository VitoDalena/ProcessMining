package bsh;

import java.lang.reflect.InvocationTargetException;

class BSHMethodInvocation
  extends SimpleNode
{
  BSHMethodInvocation(int paramInt)
  {
    super(paramInt);
  }
  
  BSHAmbiguousName getNameNode()
  {
    return (BSHAmbiguousName)jjtGetChild(0);
  }
  
  BSHArguments getArgsNode()
  {
    return (BSHArguments)jjtGetChild(1);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    NameSpace localNameSpace = paramCallStack.top();
    BSHAmbiguousName localBSHAmbiguousName = getNameNode();
    if ((localNameSpace.getParent() != null) && (localNameSpace.getParent().isClass) && ((localBSHAmbiguousName.text.equals("super")) || (localBSHAmbiguousName.text.equals("this")))) {
      return Primitive.VOID;
    }
    Name localName = localBSHAmbiguousName.getName(localNameSpace);
    Object[] arrayOfObject = getArgsNode().getArguments(paramCallStack, paramInterpreter);
    try
    {
      return localName.invokeMethod(paramInterpreter, arrayOfObject, paramCallStack, this);
    }
    catch (ReflectError localReflectError)
    {
      throw new EvalError("Error in method invocation: " + localReflectError.getMessage(), this, paramCallStack);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      String str = "Method Invocation " + localName;
      Throwable localThrowable = localInvocationTargetException.getTargetException();
      boolean bool = true;
      if ((localThrowable instanceof EvalError)) {
        if ((localThrowable instanceof TargetError)) {
          bool = ((TargetError)localThrowable).inNativeCode();
        } else {
          bool = false;
        }
      }
      throw new TargetError(str, localThrowable, this, paramCallStack, bool);
    }
    catch (UtilEvalError localUtilEvalError)
    {
      throw localUtilEvalError.toEvalError(this, paramCallStack);
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHMethodInvocation.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */