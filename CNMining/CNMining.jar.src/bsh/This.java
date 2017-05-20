package bsh;

import java.io.Serializable;

public class This
  implements Serializable, Runnable
{
  NameSpace namespace;
  transient Interpreter declaringInterpreter;
  
  static This getThis(NameSpace paramNameSpace, Interpreter paramInterpreter)
  {
    try
    {
      Class localClass;
      if (Capabilities.canGenerateInterfaces()) {
        localClass = Class.forName("bsh.XThis");
      } else if (Capabilities.haveSwing()) {
        localClass = Class.forName("bsh.JThis");
      } else {
        return new This(paramNameSpace, paramInterpreter);
      }
      return (This)Reflect.constructObject(localClass, new Object[] { paramNameSpace, paramInterpreter });
    }
    catch (Exception localException)
    {
      throw new InterpreterError("internal error 1 in This: " + localException);
    }
  }
  
  public Object getInterface(Class paramClass)
    throws UtilEvalError
  {
    if (paramClass.isInstance(this)) {
      return this;
    }
    throw new UtilEvalError("Dynamic proxy mechanism not available. Cannot construct interface type: " + paramClass);
  }
  
  public Object getInterface(Class[] paramArrayOfClass)
    throws UtilEvalError
  {
    for (int i = 0; i < paramArrayOfClass.length; i++) {
      if (!paramArrayOfClass[i].isInstance(this)) {
        throw new UtilEvalError("Dynamic proxy mechanism not available. Cannot construct interface type: " + paramArrayOfClass[i]);
      }
    }
    return this;
  }
  
  protected This(NameSpace paramNameSpace, Interpreter paramInterpreter)
  {
    this.namespace = paramNameSpace;
    this.declaringInterpreter = paramInterpreter;
  }
  
  public NameSpace getNameSpace()
  {
    return this.namespace;
  }
  
  public String toString()
  {
    return "'this' reference to Bsh object: " + this.namespace;
  }
  
  public void run()
  {
    try
    {
      invokeMethod("run", new Object[0]);
    }
    catch (EvalError localEvalError)
    {
      this.declaringInterpreter.error("Exception in runnable:" + localEvalError);
    }
  }
  
  public Object invokeMethod(String paramString, Object[] paramArrayOfObject)
    throws EvalError
  {
    return invokeMethod(paramString, paramArrayOfObject, null, null, null, false);
  }
  
  public Object invokeMethod(String paramString, Object[] paramArrayOfObject, Interpreter paramInterpreter, CallStack paramCallStack, SimpleNode paramSimpleNode, boolean paramBoolean)
    throws EvalError
  {
    if (paramArrayOfObject != null)
    {
      localObject1 = new Object[paramArrayOfObject.length];
      for (int i = 0; i < paramArrayOfObject.length; i++) {
        localObject1[i] = (paramArrayOfObject[i] == null ? Primitive.NULL : paramArrayOfObject[i]);
      }
      paramArrayOfObject = (Object[])localObject1;
    }
    if (paramInterpreter == null) {
      paramInterpreter = this.declaringInterpreter;
    }
    if (paramCallStack == null) {
      paramCallStack = new CallStack(this.namespace);
    }
    if (paramSimpleNode == null) {
      paramSimpleNode = SimpleNode.JAVACODE;
    }
    Object localObject1 = Types.getTypes(paramArrayOfObject);
    BshMethod localBshMethod = null;
    try
    {
      localBshMethod = this.namespace.getMethod(paramString, (Class[])localObject1, paramBoolean);
    }
    catch (UtilEvalError localUtilEvalError1) {}
    if (localBshMethod != null) {
      return localBshMethod.invoke(paramArrayOfObject, paramInterpreter, paramCallStack, paramSimpleNode);
    }
    if (paramString.equals("toString")) {
      return toString();
    }
    if (paramString.equals("hashCode")) {
      return new Integer(hashCode());
    }
    if (paramString.equals("equals"))
    {
      Object localObject2 = paramArrayOfObject[0];
      return new Boolean(this == localObject2);
    }
    try
    {
      localBshMethod = this.namespace.getMethod("invoke", new Class[] { null, null });
    }
    catch (UtilEvalError localUtilEvalError2) {}
    if (localBshMethod != null) {
      return localBshMethod.invoke(new Object[] { paramString, paramArrayOfObject }, paramInterpreter, paramCallStack, paramSimpleNode);
    }
    throw new EvalError("Method " + StringUtil.methodString(paramString, (Class[])localObject1) + " not found in bsh scripted object: " + this.namespace.getName(), paramSimpleNode, paramCallStack);
  }
  
  public static void bind(This paramThis, NameSpace paramNameSpace, Interpreter paramInterpreter)
  {
    paramThis.namespace.setParent(paramNameSpace);
    paramThis.declaringInterpreter = paramInterpreter;
  }
  
  static boolean isExposedThisMethod(String paramString)
  {
    return (paramString.equals("getClass")) || (paramString.equals("invokeMethod")) || (paramString.equals("getInterface")) || (paramString.equals("wait")) || (paramString.equals("notify")) || (paramString.equals("notifyAll"));
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/This.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */