package bsh;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;

class Name
  implements Serializable
{
  public NameSpace namespace;
  String value = null;
  private String evalName;
  private String lastEvalName;
  private static String FINISHED = null;
  private Object evalBaseObject;
  private int callstackDepth;
  Class asClass;
  Class classOfStaticMethod;
  
  private void reset()
  {
    this.evalName = this.value;
    this.evalBaseObject = null;
    this.callstackDepth = 0;
  }
  
  Name(NameSpace paramNameSpace, String paramString)
  {
    this.namespace = paramNameSpace;
    this.value = paramString;
  }
  
  public Object toObject(CallStack paramCallStack, Interpreter paramInterpreter)
    throws UtilEvalError
  {
    return toObject(paramCallStack, paramInterpreter, false);
  }
  
  public synchronized Object toObject(CallStack paramCallStack, Interpreter paramInterpreter, boolean paramBoolean)
    throws UtilEvalError
  {
    reset();
    for (Object localObject = null; this.evalName != null; localObject = consumeNextObjectField(paramCallStack, paramInterpreter, paramBoolean, false)) {}
    if (localObject == null) {
      throw new InterpreterError("null value in toObject()");
    }
    return localObject;
  }
  
  private Object completeRound(String paramString1, String paramString2, Object paramObject)
  {
    if (paramObject == null) {
      throw new InterpreterError("lastEvalName = " + paramString1);
    }
    this.lastEvalName = paramString1;
    this.evalName = paramString2;
    this.evalBaseObject = paramObject;
    return paramObject;
  }
  
  private Object consumeNextObjectField(CallStack paramCallStack, Interpreter paramInterpreter, boolean paramBoolean1, boolean paramBoolean2)
    throws UtilEvalError
  {
    if ((this.evalBaseObject == null) && (!isCompound(this.evalName)) && (!paramBoolean1))
    {
      localObject1 = resolveThisFieldReference(paramCallStack, this.namespace, paramInterpreter, this.evalName, false);
      if (localObject1 != Primitive.VOID) {
        return completeRound(this.evalName, FINISHED, localObject1);
      }
    }
    Object localObject1 = prefix(this.evalName, 1);
    if (((this.evalBaseObject == null) || ((this.evalBaseObject instanceof This))) && (!paramBoolean1))
    {
      if (Interpreter.DEBUG) {
        Interpreter.debug("trying to resolve variable: " + (String)localObject1);
      }
      if (this.evalBaseObject == null) {
        localObject2 = resolveThisFieldReference(paramCallStack, this.namespace, paramInterpreter, (String)localObject1, false);
      } else {
        localObject2 = resolveThisFieldReference(paramCallStack, ((This)this.evalBaseObject).namespace, paramInterpreter, (String)localObject1, true);
      }
      if (localObject2 != Primitive.VOID)
      {
        if (Interpreter.DEBUG) {
          Interpreter.debug("resolved variable: " + (String)localObject1 + " in namespace: " + this.namespace);
        }
        return completeRound((String)localObject1, suffix(this.evalName), localObject2);
      }
    }
    Object localObject4;
    if (this.evalBaseObject == null)
    {
      if (Interpreter.DEBUG) {
        Interpreter.debug("trying class: " + this.evalName);
      }
      localObject2 = null;
      int i = 1;
      localObject4 = null;
      while (i <= countParts(this.evalName))
      {
        localObject4 = prefix(this.evalName, i);
        if ((localObject2 = this.namespace.getClass((String)localObject4)) != null) {
          break;
        }
        i++;
      }
      if (localObject2 != null) {
        return completeRound((String)localObject4, suffix(this.evalName, countParts(this.evalName) - i), new ClassIdentifier((Class)localObject2));
      }
      if (Interpreter.DEBUG) {
        Interpreter.debug("not a class, trying var prefix " + this.evalName);
      }
    }
    Object localObject3;
    if (((this.evalBaseObject == null) || ((this.evalBaseObject instanceof This))) && (!paramBoolean1) && (paramBoolean2))
    {
      localObject2 = this.evalBaseObject == null ? this.namespace : ((This)this.evalBaseObject).namespace;
      localObject3 = new NameSpace((NameSpace)localObject2, "auto: " + (String)localObject1).getThis(paramInterpreter);
      ((NameSpace)localObject2).setVariable((String)localObject1, localObject3, false);
      return completeRound((String)localObject1, suffix(this.evalName), localObject3);
    }
    if (this.evalBaseObject == null)
    {
      if (!isCompound(this.evalName)) {
        return completeRound(this.evalName, FINISHED, Primitive.VOID);
      }
      throw new UtilEvalError("Class or variable not found: " + this.evalName);
    }
    if (this.evalBaseObject == Primitive.NULL) {
      throw new UtilTargetError(new NullPointerException("Null Pointer while evaluating: " + this.value));
    }
    if (this.evalBaseObject == Primitive.VOID) {
      throw new UtilEvalError("Undefined variable or class name while evaluating: " + this.value);
    }
    if ((this.evalBaseObject instanceof Primitive)) {
      throw new UtilEvalError("Can't treat primitive like an object. Error while evaluating: " + this.value);
    }
    if ((this.evalBaseObject instanceof ClassIdentifier))
    {
      localObject2 = ((ClassIdentifier)this.evalBaseObject).getTargetClass();
      localObject3 = prefix(this.evalName, 1);
      if (((String)localObject3).equals("this"))
      {
        for (localObject4 = this.namespace; localObject4 != null; localObject4 = ((NameSpace)localObject4).getParent()) {
          if ((((NameSpace)localObject4).classInstance != null) && (((NameSpace)localObject4).classInstance.getClass() == localObject2)) {
            return completeRound((String)localObject3, suffix(this.evalName), ((NameSpace)localObject4).classInstance);
          }
        }
        throw new UtilEvalError("Can't find enclosing 'this' instance of class: " + localObject2);
      }
      localObject4 = null;
      try
      {
        if (Interpreter.DEBUG) {
          Interpreter.debug("Name call to getStaticFieldValue, class: " + localObject2 + ", field:" + (String)localObject3);
        }
        localObject4 = Reflect.getStaticFieldValue((Class)localObject2, (String)localObject3);
      }
      catch (ReflectError localReflectError2)
      {
        if (Interpreter.DEBUG) {
          Interpreter.debug("field reflect error: " + localReflectError2);
        }
      }
      if (localObject4 == null)
      {
        String str = ((Class)localObject2).getName() + "$" + (String)localObject3;
        Class localClass = this.namespace.getClass(str);
        if (localClass != null) {
          localObject4 = new ClassIdentifier(localClass);
        }
      }
      if (localObject4 == null) {
        throw new UtilEvalError("No static field or inner class: " + (String)localObject3 + " of " + localObject2);
      }
      return completeRound((String)localObject3, suffix(this.evalName), localObject4);
    }
    if (paramBoolean1) {
      throw new UtilEvalError(this.value + " does not resolve to a class name.");
    }
    Object localObject2 = prefix(this.evalName, 1);
    if ((((String)localObject2).equals("length")) && (this.evalBaseObject.getClass().isArray()))
    {
      localObject3 = new Primitive(Array.getLength(this.evalBaseObject));
      return completeRound((String)localObject2, suffix(this.evalName), localObject3);
    }
    try
    {
      localObject3 = Reflect.getObjectFieldValue(this.evalBaseObject, (String)localObject2);
      return completeRound((String)localObject2, suffix(this.evalName), localObject3);
    }
    catch (ReflectError localReflectError1)
    {
      throw new UtilEvalError("Cannot access field: " + (String)localObject2 + ", on object: " + this.evalBaseObject);
    }
  }
  
  Object resolveThisFieldReference(CallStack paramCallStack, NameSpace paramNameSpace, Interpreter paramInterpreter, String paramString, boolean paramBoolean)
    throws UtilEvalError
  {
    if (paramString.equals("this"))
    {
      if (paramBoolean) {
        throw new UtilEvalError("Redundant to call .this on This type");
      }
      localObject1 = paramNameSpace.getThis(paramInterpreter);
      paramNameSpace = ((This)localObject1).getNameSpace();
      Object localObject2 = localObject1;
      NameSpace localNameSpace = getClassNameSpace(paramNameSpace);
      if (localNameSpace != null) {
        if (isCompound(this.evalName)) {
          localObject2 = localNameSpace.getThis(paramInterpreter);
        } else {
          localObject2 = localNameSpace.getClassInstance();
        }
      }
      return localObject2;
    }
    if (paramString.equals("super"))
    {
      localObject1 = paramNameSpace.getSuper(paramInterpreter);
      paramNameSpace = ((This)localObject1).getNameSpace();
      if ((paramNameSpace.getParent() != null) && (paramNameSpace.getParent().isClass)) {
        localObject1 = paramNameSpace.getParent().getThis(paramInterpreter);
      }
      return localObject1;
    }
    Object localObject1 = null;
    if (paramString.equals("global")) {
      localObject1 = paramNameSpace.getGlobal(paramInterpreter);
    }
    if ((localObject1 == null) && (paramBoolean)) {
      if (paramString.equals("namespace")) {
        localObject1 = paramNameSpace;
      } else if (paramString.equals("variables")) {
        localObject1 = paramNameSpace.getVariableNames();
      } else if (paramString.equals("methods")) {
        localObject1 = paramNameSpace.getMethodNames();
      } else if (paramString.equals("interpreter")) {
        if (this.lastEvalName.equals("this")) {
          localObject1 = paramInterpreter;
        } else {
          throw new UtilEvalError("Can only call .interpreter on literal 'this'");
        }
      }
    }
    if ((localObject1 == null) && (paramBoolean) && (paramString.equals("caller")))
    {
      if ((this.lastEvalName.equals("this")) || (this.lastEvalName.equals("caller")))
      {
        if (paramCallStack == null) {
          throw new InterpreterError("no callstack");
        }
        localObject1 = paramCallStack.get(++this.callstackDepth).getThis(paramInterpreter);
      }
      else
      {
        throw new UtilEvalError("Can only call .caller on literal 'this' or literal '.caller'");
      }
      return localObject1;
    }
    if ((localObject1 == null) && (paramBoolean) && (paramString.equals("callstack"))) {
      if (this.lastEvalName.equals("this"))
      {
        if (paramCallStack == null) {
          throw new InterpreterError("no callstack");
        }
        localObject1 = paramCallStack;
      }
      else
      {
        throw new UtilEvalError("Can only call .callstack on literal 'this'");
      }
    }
    if (localObject1 == null) {
      localObject1 = paramNameSpace.getVariable(paramString);
    }
    if (localObject1 == null) {
      throw new InterpreterError("null this field ref:" + paramString);
    }
    return localObject1;
  }
  
  static NameSpace getClassNameSpace(NameSpace paramNameSpace)
  {
    if (paramNameSpace.isClass) {
      return paramNameSpace;
    }
    if ((paramNameSpace.isMethod) && (paramNameSpace.getParent() != null) && (paramNameSpace.getParent().isClass)) {
      return paramNameSpace.getParent();
    }
    return null;
  }
  
  public synchronized Class toClass()
    throws ClassNotFoundException, UtilEvalError
  {
    if (this.asClass != null) {
      return this.asClass;
    }
    reset();
    if (this.evalName.equals("var")) {
      return this.asClass = null;
    }
    Class localClass = this.namespace.getClass(this.evalName);
    if (localClass == null)
    {
      Object localObject = null;
      try
      {
        localObject = toObject(null, null, true);
      }
      catch (UtilEvalError localUtilEvalError) {}
      if ((localObject instanceof ClassIdentifier)) {
        localClass = ((ClassIdentifier)localObject).getTargetClass();
      }
    }
    if (localClass == null) {
      throw new ClassNotFoundException("Class: " + this.value + " not found in namespace");
    }
    this.asClass = localClass;
    return this.asClass;
  }
  
  public synchronized LHS toLHS(CallStack paramCallStack, Interpreter paramInterpreter)
    throws UtilEvalError
  {
    reset();
    LHS localLHS;
    if (!isCompound(this.evalName))
    {
      if (this.evalName.equals("this")) {
        throw new UtilEvalError("Can't assign to 'this'.");
      }
      localLHS = new LHS(this.namespace, this.evalName, false);
      return localLHS;
    }
    Object localObject = null;
    try
    {
      do
      {
        localObject = consumeNextObjectField(paramCallStack, paramInterpreter, false, true);
        if (this.evalName == null) {
          break;
        }
      } while (isCompound(this.evalName));
    }
    catch (UtilEvalError localUtilEvalError)
    {
      throw new UtilEvalError("LHS evaluation: " + localUtilEvalError.getMessage());
    }
    if ((this.evalName == null) && ((localObject instanceof ClassIdentifier))) {
      throw new UtilEvalError("Can't assign to class: " + this.value);
    }
    if (localObject == null) {
      throw new UtilEvalError("Error in LHS: " + this.value);
    }
    if ((localObject instanceof This))
    {
      if ((this.evalName.equals("namespace")) || (this.evalName.equals("variables")) || (this.evalName.equals("methods")) || (this.evalName.equals("caller"))) {
        throw new UtilEvalError("Can't assign to special variable: " + this.evalName);
      }
      Interpreter.debug("found This reference evaluating LHS");
      boolean bool = !this.lastEvalName.equals("super");
      return new LHS(((This)localObject).namespace, this.evalName, bool);
    }
    if (this.evalName != null) {
      try
      {
        if ((localObject instanceof ClassIdentifier))
        {
          Class localClass = ((ClassIdentifier)localObject).getTargetClass();
          localLHS = Reflect.getLHSStaticField(localClass, this.evalName);
          return localLHS;
        }
        localLHS = Reflect.getLHSObjectField(localObject, this.evalName);
        return localLHS;
      }
      catch (ReflectError localReflectError)
      {
        throw new UtilEvalError("Field access: " + localReflectError);
      }
    }
    throw new InterpreterError("Internal error in lhs...");
  }
  
  public Object invokeMethod(Interpreter paramInterpreter, Object[] paramArrayOfObject, CallStack paramCallStack, SimpleNode paramSimpleNode)
    throws UtilEvalError, EvalError, ReflectError, InvocationTargetException
  {
    String str1 = suffix(this.value, 1);
    BshClassManager localBshClassManager = paramInterpreter.getClassManager();
    NameSpace localNameSpace = paramCallStack.top();
    if (this.classOfStaticMethod != null) {
      return Reflect.invokeStaticMethod(localBshClassManager, this.classOfStaticMethod, str1, paramArrayOfObject);
    }
    if (!isCompound(this.value)) {
      return invokeLocalMethod(paramInterpreter, paramArrayOfObject, paramCallStack, paramSimpleNode);
    }
    String str2 = prefix(this.value);
    if ((str2.equals("super")) && (countParts(this.value) == 2))
    {
      localObject1 = localNameSpace.getThis(paramInterpreter);
      localObject2 = ((This)localObject1).getNameSpace();
      localObject3 = getClassNameSpace((NameSpace)localObject2);
      if (localObject3 != null)
      {
        Object localObject4 = ((NameSpace)localObject3).getClassInstance();
        return ClassGenerator.getClassGenerator().invokeSuperclassMethod(localBshClassManager, localObject4, str1, paramArrayOfObject);
      }
    }
    Object localObject1 = localNameSpace.getNameResolver(str2);
    Object localObject2 = ((Name)localObject1).toObject(paramCallStack, paramInterpreter);
    if (localObject2 == Primitive.VOID) {
      throw new UtilEvalError("Attempt to resolve method: " + str1 + "() on undefined variable or class name: " + localObject1);
    }
    if (!(localObject2 instanceof ClassIdentifier))
    {
      if ((localObject2 instanceof Primitive))
      {
        if (localObject2 == Primitive.NULL) {
          throw new UtilTargetError(new NullPointerException("Null Pointer in Method Invocation"));
        }
        if (Interpreter.DEBUG) {
          Interpreter.debug("Attempt to access method on primitive... allowing bsh.Primitive to peek through for debugging");
        }
      }
      return Reflect.invokeObjectMethod(localObject2, str1, paramArrayOfObject, paramInterpreter, paramCallStack, paramSimpleNode);
    }
    if (Interpreter.DEBUG) {
      Interpreter.debug("invokeMethod: trying static - " + localObject1);
    }
    Object localObject3 = ((ClassIdentifier)localObject2).getTargetClass();
    this.classOfStaticMethod = ((Class)localObject3);
    if (localObject3 != null) {
      return Reflect.invokeStaticMethod(localBshClassManager, (Class)localObject3, str1, paramArrayOfObject);
    }
    throw new UtilEvalError("invokeMethod: unknown target: " + localObject1);
  }
  
  private Object invokeLocalMethod(Interpreter paramInterpreter, Object[] paramArrayOfObject, CallStack paramCallStack, SimpleNode paramSimpleNode)
    throws EvalError
  {
    if (Interpreter.DEBUG) {
      Interpreter.debug("invokeLocalMethod: " + this.value);
    }
    if (paramInterpreter == null) {
      throw new InterpreterError("invokeLocalMethod: interpreter = null");
    }
    String str = this.value;
    Class[] arrayOfClass = Types.getTypes(paramArrayOfObject);
    BshMethod localBshMethod1 = null;
    try
    {
      localBshMethod1 = this.namespace.getMethod(str, arrayOfClass);
    }
    catch (UtilEvalError localUtilEvalError1)
    {
      throw localUtilEvalError1.toEvalError("Local method invocation", paramSimpleNode, paramCallStack);
    }
    if (localBshMethod1 != null) {
      return localBshMethod1.invoke(paramArrayOfObject, paramInterpreter, paramCallStack, paramSimpleNode);
    }
    BshClassManager localBshClassManager = paramInterpreter.getClassManager();
    Object localObject;
    try
    {
      localObject = this.namespace.getCommand(str, arrayOfClass, paramInterpreter);
    }
    catch (UtilEvalError localUtilEvalError2)
    {
      throw localUtilEvalError2.toEvalError("Error loading command: ", paramSimpleNode, paramCallStack);
    }
    if (localObject == null)
    {
      BshMethod localBshMethod2 = null;
      try
      {
        localBshMethod2 = this.namespace.getMethod("invoke", new Class[] { null, null });
      }
      catch (UtilEvalError localUtilEvalError4)
      {
        throw localUtilEvalError4.toEvalError("Local method invocation", paramSimpleNode, paramCallStack);
      }
      if (localBshMethod2 != null) {
        return localBshMethod2.invoke(new Object[] { str, paramArrayOfObject }, paramInterpreter, paramCallStack, paramSimpleNode);
      }
      throw new EvalError("Command not found: " + StringUtil.methodString(str, arrayOfClass), paramSimpleNode, paramCallStack);
    }
    if ((localObject instanceof BshMethod)) {
      return ((BshMethod)localObject).invoke(paramArrayOfObject, paramInterpreter, paramCallStack, paramSimpleNode);
    }
    if ((localObject instanceof Class)) {
      try
      {
        return Reflect.invokeCompiledCommand((Class)localObject, paramArrayOfObject, paramInterpreter, paramCallStack);
      }
      catch (UtilEvalError localUtilEvalError3)
      {
        throw localUtilEvalError3.toEvalError("Error invoking compiled command: ", paramSimpleNode, paramCallStack);
      }
    }
    throw new InterpreterError("invalid command type");
  }
  
  public static boolean isCompound(String paramString)
  {
    return paramString.indexOf('.') != -1;
  }
  
  static int countParts(String paramString)
  {
    if (paramString == null) {
      return 0;
    }
    int i = 0;
    int j = -1;
    while ((j = paramString.indexOf('.', j + 1)) != -1) {
      i++;
    }
    return i + 1;
  }
  
  static String prefix(String paramString)
  {
    if (!isCompound(paramString)) {
      return null;
    }
    return prefix(paramString, countParts(paramString) - 1);
  }
  
  static String prefix(String paramString, int paramInt)
  {
    if (paramInt < 1) {
      return null;
    }
    int i = 0;
    int j = -1;
    do
    {
      if ((j = paramString.indexOf('.', j + 1)) == -1) {
        break;
      }
      i++;
    } while (i < paramInt);
    return j == -1 ? paramString : paramString.substring(0, j);
  }
  
  static String suffix(String paramString)
  {
    if (!isCompound(paramString)) {
      return null;
    }
    return suffix(paramString, countParts(paramString) - 1);
  }
  
  public static String suffix(String paramString, int paramInt)
  {
    if (paramInt < 1) {
      return null;
    }
    int i = 0;
    int j = paramString.length() + 1;
    do
    {
      if ((j = paramString.lastIndexOf('.', j - 1)) == -1) {
        break;
      }
      i++;
    } while (i < paramInt);
    return j == -1 ? paramString : paramString.substring(j + 1);
  }
  
  public String toString()
  {
    return this.value;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/Name.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */