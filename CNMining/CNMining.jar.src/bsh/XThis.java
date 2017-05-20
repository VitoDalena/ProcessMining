package bsh;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Hashtable;

public class XThis
  extends This
{
  Hashtable interfaces;
  InvocationHandler invocationHandler = new Handler();
  
  public XThis(NameSpace paramNameSpace, Interpreter paramInterpreter)
  {
    super(paramNameSpace, paramInterpreter);
  }
  
  public String toString()
  {
    return "'this' reference (XThis) to Bsh object: " + this.namespace;
  }
  
  public Object getInterface(Class paramClass)
  {
    return getInterface(new Class[] { paramClass });
  }
  
  public Object getInterface(Class[] paramArrayOfClass)
  {
    if (this.interfaces == null) {
      this.interfaces = new Hashtable();
    }
    int i = 21;
    for (int j = 0; j < paramArrayOfClass.length; j++) {
      i *= (paramArrayOfClass[j].hashCode() + 3);
    }
    Integer localInteger = new Integer(i);
    Object localObject = this.interfaces.get(localInteger);
    if (localObject == null)
    {
      ClassLoader localClassLoader = paramArrayOfClass[0].getClassLoader();
      localObject = Proxy.newProxyInstance(localClassLoader, paramArrayOfClass, this.invocationHandler);
      this.interfaces.put(localInteger, localObject);
    }
    return localObject;
  }
  
  class Handler
    implements InvocationHandler, Serializable
  {
    Handler() {}
    
    public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
      throws Throwable
    {
      try
      {
        return invokeImpl(paramObject, paramMethod, paramArrayOfObject);
      }
      catch (TargetError localTargetError)
      {
        throw localTargetError.getTarget();
      }
      catch (EvalError localEvalError)
      {
        if (Interpreter.DEBUG) {
          Interpreter.debug("EvalError in scripted interface: " + XThis.this.toString() + ": " + localEvalError);
        }
        throw localEvalError;
      }
    }
    
    public Object invokeImpl(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
      throws EvalError
    {
      String str = paramMethod.getName();
      CallStack localCallStack = new CallStack(XThis.this.namespace);
      BshMethod localBshMethod = null;
      try
      {
        localBshMethod = XThis.this.namespace.getMethod("equals", new Class[] { Object.class });
      }
      catch (UtilEvalError localUtilEvalError1) {}
      if ((str.equals("equals")) && (localBshMethod == null))
      {
        localObject = paramArrayOfObject[0];
        return new Boolean(paramObject == localObject);
      }
      Object localObject = null;
      try
      {
        localObject = XThis.this.namespace.getMethod("toString", new Class[0]);
      }
      catch (UtilEvalError localUtilEvalError2) {}
      if ((str.equals("toString")) && (localObject == null))
      {
        arrayOfClass = paramObject.getClass().getInterfaces();
        StringBuffer localStringBuffer = new StringBuffer(XThis.this.toString() + "\nimplements:");
        for (int i = 0; i < arrayOfClass.length; i++) {
          localStringBuffer.append(" " + arrayOfClass[i].getName() + (arrayOfClass.length > 1 ? "," : ""));
        }
        return localStringBuffer.toString();
      }
      Class[] arrayOfClass = paramMethod.getParameterTypes();
      return Primitive.unwrap(XThis.this.invokeMethod(str, Primitive.wrap(paramArrayOfObject, arrayOfClass)));
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/XThis.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */