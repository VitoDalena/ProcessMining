package bsh;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Vector;

class Reflect
{
  public static Object invokeObjectMethod(Object paramObject, String paramString, Object[] paramArrayOfObject, Interpreter paramInterpreter, CallStack paramCallStack, SimpleNode paramSimpleNode)
    throws ReflectError, EvalError, InvocationTargetException
  {
    if (((paramObject instanceof This)) && (!This.isExposedThisMethod(paramString))) {
      return ((This)paramObject).invokeMethod(paramString, paramArrayOfObject, paramInterpreter, paramCallStack, paramSimpleNode, false);
    }
    try
    {
      BshClassManager localBshClassManager = paramInterpreter == null ? null : paramInterpreter.getClassManager();
      Class localClass = paramObject.getClass();
      Method localMethod = resolveExpectedJavaMethod(localBshClassManager, localClass, paramObject, paramString, paramArrayOfObject, false);
      return invokeMethod(localMethod, paramObject, paramArrayOfObject);
    }
    catch (UtilEvalError localUtilEvalError)
    {
      throw localUtilEvalError.toEvalError(paramSimpleNode, paramCallStack);
    }
  }
  
  public static Object invokeStaticMethod(BshClassManager paramBshClassManager, Class paramClass, String paramString, Object[] paramArrayOfObject)
    throws ReflectError, UtilEvalError, InvocationTargetException
  {
    Interpreter.debug("invoke static Method");
    Method localMethod = resolveExpectedJavaMethod(paramBshClassManager, paramClass, null, paramString, paramArrayOfObject, true);
    return invokeMethod(localMethod, null, paramArrayOfObject);
  }
  
  static Object invokeMethod(Method paramMethod, Object paramObject, Object[] paramArrayOfObject)
    throws ReflectError, InvocationTargetException
  {
    if (paramArrayOfObject == null) {
      paramArrayOfObject = new Object[0];
    }
    logInvokeMethod("Invoking method (entry): ", paramMethod, paramArrayOfObject);
    Object[] arrayOfObject = new Object[paramArrayOfObject.length];
    Class[] arrayOfClass = paramMethod.getParameterTypes();
    try
    {
      for (int i = 0; i < paramArrayOfObject.length; i++) {
        arrayOfObject[i] = Types.castObject(paramArrayOfObject[i], arrayOfClass[i], 1);
      }
    }
    catch (UtilEvalError localUtilEvalError)
    {
      throw new InterpreterError("illegal argument type in method invocation: " + localUtilEvalError);
    }
    arrayOfObject = Primitive.unwrap(arrayOfObject);
    logInvokeMethod("Invoking method (after massaging values): ", paramMethod, arrayOfObject);
    try
    {
      Object localObject = paramMethod.invoke(paramObject, arrayOfObject);
      if (localObject == null) {
        localObject = Primitive.NULL;
      }
      Class localClass = paramMethod.getReturnType();
      return Primitive.wrap(localObject, localClass);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new ReflectError("Cannot access method " + StringUtil.methodString(paramMethod.getName(), paramMethod.getParameterTypes()) + " in '" + paramMethod.getDeclaringClass() + "' :" + localIllegalAccessException);
    }
  }
  
  public static Object getIndex(Object paramObject, int paramInt)
    throws ReflectError, UtilTargetError
  {
    if (Interpreter.DEBUG) {
      Interpreter.debug("getIndex: " + paramObject + ", index=" + paramInt);
    }
    try
    {
      Object localObject = Array.get(paramObject, paramInt);
      return Primitive.wrap(localObject, paramObject.getClass().getComponentType());
    }
    catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
    {
      throw new UtilTargetError(localArrayIndexOutOfBoundsException);
    }
    catch (Exception localException)
    {
      throw new ReflectError("Array access:" + localException);
    }
  }
  
  public static void setIndex(Object paramObject1, int paramInt, Object paramObject2)
    throws ReflectError, UtilTargetError
  {
    try
    {
      paramObject2 = Primitive.unwrap(paramObject2);
      Array.set(paramObject1, paramInt, paramObject2);
    }
    catch (ArrayStoreException localArrayStoreException)
    {
      throw new UtilTargetError(localArrayStoreException);
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      throw new UtilTargetError(new ArrayStoreException(localIllegalArgumentException.toString()));
    }
    catch (Exception localException)
    {
      throw new ReflectError("Array access:" + localException);
    }
  }
  
  public static Object getStaticFieldValue(Class paramClass, String paramString)
    throws UtilEvalError, ReflectError
  {
    return getFieldValue(paramClass, null, paramString, true);
  }
  
  public static Object getObjectFieldValue(Object paramObject, String paramString)
    throws UtilEvalError, ReflectError
  {
    if ((paramObject instanceof This)) {
      return ((This)paramObject).namespace.getVariable(paramString);
    }
    try
    {
      return getFieldValue(paramObject.getClass(), paramObject, paramString, false);
    }
    catch (ReflectError localReflectError)
    {
      if (hasObjectPropertyGetter(paramObject.getClass(), paramString)) {
        return getObjectProperty(paramObject, paramString);
      }
      throw localReflectError;
    }
  }
  
  static LHS getLHSStaticField(Class paramClass, String paramString)
    throws UtilEvalError, ReflectError
  {
    Field localField = resolveExpectedJavaField(paramClass, paramString, true);
    return new LHS(localField);
  }
  
  static LHS getLHSObjectField(Object paramObject, String paramString)
    throws UtilEvalError, ReflectError
  {
    if ((paramObject instanceof This))
    {
      boolean bool = false;
      return new LHS(((This)paramObject).namespace, paramString, bool);
    }
    try
    {
      Field localField = resolveExpectedJavaField(paramObject.getClass(), paramString, false);
      return new LHS(paramObject, localField);
    }
    catch (ReflectError localReflectError)
    {
      if (hasObjectPropertySetter(paramObject.getClass(), paramString)) {
        return new LHS(paramObject, paramString);
      }
      throw localReflectError;
    }
  }
  
  private static Object getFieldValue(Class paramClass, Object paramObject, String paramString, boolean paramBoolean)
    throws UtilEvalError, ReflectError
  {
    try
    {
      Field localField = resolveExpectedJavaField(paramClass, paramString, paramBoolean);
      Object localObject = localField.get(paramObject);
      Class localClass = localField.getType();
      return Primitive.wrap(localObject, localClass);
    }
    catch (NullPointerException localNullPointerException)
    {
      throw new ReflectError("???" + paramString + " is not a static field.");
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new ReflectError("Can't access field: " + paramString);
    }
  }
  
  protected static Field resolveJavaField(Class paramClass, String paramString, boolean paramBoolean)
    throws UtilEvalError
  {
    try
    {
      return resolveExpectedJavaField(paramClass, paramString, paramBoolean);
    }
    catch (ReflectError localReflectError) {}
    return null;
  }
  
  protected static Field resolveExpectedJavaField(Class paramClass, String paramString, boolean paramBoolean)
    throws UtilEvalError, ReflectError
  {
    Field localField;
    try
    {
      if (Capabilities.haveAccessibility()) {
        localField = findAccessibleField(paramClass, paramString);
      } else {
        localField = paramClass.getField(paramString);
      }
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      throw new ReflectError("No such field: " + paramString);
    }
    catch (SecurityException localSecurityException)
    {
      throw new UtilTargetError("Security Exception while searching fields of: " + paramClass, localSecurityException);
    }
    if ((paramBoolean) && (!Modifier.isStatic(localField.getModifiers()))) {
      throw new UtilEvalError("Can't reach instance field: " + paramString + " from static context: " + paramClass.getName());
    }
    return localField;
  }
  
  private static Field findAccessibleField(Class paramClass, String paramString)
    throws UtilEvalError, NoSuchFieldException
  {
    try
    {
      localField = paramClass.getField(paramString);
      ReflectManager.RMSetAccessible(localField);
      return localField;
    }
    catch (NoSuchFieldException localNoSuchFieldException1)
    {
      Field localField;
      while (paramClass != null) {
        try
        {
          localField = paramClass.getDeclaredField(paramString);
          ReflectManager.RMSetAccessible(localField);
          return localField;
        }
        catch (NoSuchFieldException localNoSuchFieldException2)
        {
          paramClass = paramClass.getSuperclass();
        }
      }
      throw new NoSuchFieldException(paramString);
    }
  }
  
  protected static Method resolveExpectedJavaMethod(BshClassManager paramBshClassManager, Class paramClass, Object paramObject, String paramString, Object[] paramArrayOfObject, boolean paramBoolean)
    throws ReflectError, UtilEvalError
  {
    if (paramObject == Primitive.NULL) {
      throw new UtilTargetError(new NullPointerException("Attempt to invoke method " + paramString + " on null value"));
    }
    Class[] arrayOfClass = Types.getTypes(paramArrayOfObject);
    Method localMethod = resolveJavaMethod(paramBshClassManager, paramClass, paramString, arrayOfClass, paramBoolean);
    if (localMethod == null) {
      throw new ReflectError((paramBoolean ? "Static method " : "Method ") + StringUtil.methodString(paramString, arrayOfClass) + " not found in class'" + paramClass.getName() + "'");
    }
    return localMethod;
  }
  
  protected static Method resolveJavaMethod(BshClassManager paramBshClassManager, Class paramClass, String paramString, Class[] paramArrayOfClass, boolean paramBoolean)
    throws UtilEvalError
  {
    if (paramClass == null) {
      throw new InterpreterError("null class");
    }
    Method localMethod = null;
    if (paramBshClassManager == null) {
      Interpreter.debug("resolveJavaMethod UNOPTIMIZED lookup");
    } else {
      localMethod = paramBshClassManager.getResolvedMethod(paramClass, paramString, paramArrayOfClass, paramBoolean);
    }
    if (localMethod == null)
    {
      boolean bool = !Capabilities.haveAccessibility();
      try
      {
        localMethod = findOverloadedMethod(paramClass, paramString, paramArrayOfClass, bool);
      }
      catch (SecurityException localSecurityException)
      {
        throw new UtilTargetError("Security Exception while searching methods of: " + paramClass, localSecurityException);
      }
      checkFoundStaticMethod(localMethod, paramBoolean, paramClass);
      if ((localMethod != null) && (!bool)) {
        try
        {
          ReflectManager.RMSetAccessible(localMethod);
        }
        catch (UtilEvalError localUtilEvalError) {}
      }
      if ((localMethod != null) && (paramBshClassManager != null)) {
        paramBshClassManager.cacheResolvedMethod(paramClass, paramArrayOfClass, localMethod);
      }
    }
    return localMethod;
  }
  
  private static Method findOverloadedMethod(Class paramClass, String paramString, Class[] paramArrayOfClass, boolean paramBoolean)
  {
    if (Interpreter.DEBUG) {
      Interpreter.debug("Searching for method: " + StringUtil.methodString(paramString, paramArrayOfClass) + " in '" + paramClass.getName() + "'");
    }
    Method[] arrayOfMethod = getCandidateMethods(paramClass, paramString, paramArrayOfClass.length, paramBoolean);
    if (Interpreter.DEBUG) {
      Interpreter.debug("Looking for most specific method: " + paramString);
    }
    Method localMethod = findMostSpecificMethod(paramArrayOfClass, arrayOfMethod);
    return localMethod;
  }
  
  static Method[] getCandidateMethods(Class paramClass, String paramString, int paramInt, boolean paramBoolean)
  {
    Vector localVector = gatherMethodsRecursive(paramClass, paramString, paramInt, paramBoolean, null);
    Method[] arrayOfMethod = new Method[localVector.size()];
    localVector.copyInto(arrayOfMethod);
    return arrayOfMethod;
  }
  
  private static Vector gatherMethodsRecursive(Class paramClass, String paramString, int paramInt, boolean paramBoolean, Vector paramVector)
  {
    if (paramVector == null) {
      paramVector = new Vector();
    }
    if (paramBoolean)
    {
      if (isPublic(paramClass)) {
        addCandidates(paramClass.getMethods(), paramString, paramInt, paramBoolean, paramVector);
      }
    }
    else {
      addCandidates(paramClass.getDeclaredMethods(), paramString, paramInt, paramBoolean, paramVector);
    }
    Class[] arrayOfClass = paramClass.getInterfaces();
    for (int i = 0; i < arrayOfClass.length; i++) {
      gatherMethodsRecursive(arrayOfClass[i], paramString, paramInt, paramBoolean, paramVector);
    }
    Class localClass = paramClass.getSuperclass();
    if (localClass != null) {
      gatherMethodsRecursive(localClass, paramString, paramInt, paramBoolean, paramVector);
    }
    return paramVector;
  }
  
  private static Vector addCandidates(Method[] paramArrayOfMethod, String paramString, int paramInt, boolean paramBoolean, Vector paramVector)
  {
    for (int i = 0; i < paramArrayOfMethod.length; i++)
    {
      Method localMethod = paramArrayOfMethod[i];
      if ((localMethod.getName().equals(paramString)) && (localMethod.getParameterTypes().length == paramInt) && ((!paramBoolean) || (isPublic(localMethod)))) {
        paramVector.add(localMethod);
      }
    }
    return paramVector;
  }
  
  static Object constructObject(Class paramClass, Object[] paramArrayOfObject)
    throws ReflectError, InvocationTargetException
  {
    if (paramClass.isInterface()) {
      throw new ReflectError("Can't create instance of an interface: " + paramClass);
    }
    Object localObject = null;
    Class[] arrayOfClass = Types.getTypes(paramArrayOfObject);
    Constructor localConstructor = null;
    Constructor[] arrayOfConstructor = Capabilities.haveAccessibility() ? paramClass.getDeclaredConstructors() : paramClass.getConstructors();
    if (Interpreter.DEBUG) {
      Interpreter.debug("Looking for most specific constructor: " + paramClass);
    }
    localConstructor = findMostSpecificConstructor(arrayOfClass, arrayOfConstructor);
    if (localConstructor == null) {
      throw cantFindConstructor(paramClass, arrayOfClass);
    }
    if (!isPublic(localConstructor)) {
      try
      {
        ReflectManager.RMSetAccessible(localConstructor);
      }
      catch (UtilEvalError localUtilEvalError) {}
    }
    paramArrayOfObject = Primitive.unwrap(paramArrayOfObject);
    try
    {
      localObject = localConstructor.newInstance(paramArrayOfObject);
    }
    catch (InstantiationException localInstantiationException)
    {
      throw new ReflectError("The class " + paramClass + " is abstract ");
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new ReflectError("We don't have permission to create an instance.Use setAccessibility(true) to enable access.");
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      throw new ReflectError("The number of arguments was wrong");
    }
    if (localObject == null) {
      throw new ReflectError("Couldn't construct the object");
    }
    return localObject;
  }
  
  static Constructor findMostSpecificConstructor(Class[] paramArrayOfClass, Constructor[] paramArrayOfConstructor)
  {
    int i = findMostSpecificConstructorIndex(paramArrayOfClass, paramArrayOfConstructor);
    return i == -1 ? null : paramArrayOfConstructor[i];
  }
  
  static int findMostSpecificConstructorIndex(Class[] paramArrayOfClass, Constructor[] paramArrayOfConstructor)
  {
    Class[][] arrayOfClass = new Class[paramArrayOfConstructor.length][];
    for (int i = 0; i < arrayOfClass.length; i++) {
      arrayOfClass[i] = paramArrayOfConstructor[i].getParameterTypes();
    }
    return findMostSpecificSignature(paramArrayOfClass, arrayOfClass);
  }
  
  static Method findMostSpecificMethod(Class[] paramArrayOfClass, Method[] paramArrayOfMethod)
  {
    Class[][] arrayOfClass = new Class[paramArrayOfMethod.length][];
    for (int i = 0; i < paramArrayOfMethod.length; i++) {
      arrayOfClass[i] = paramArrayOfMethod[i].getParameterTypes();
    }
    int j = findMostSpecificSignature(paramArrayOfClass, arrayOfClass);
    return j == -1 ? null : paramArrayOfMethod[j];
  }
  
  static int findMostSpecificSignature(Class[] paramArrayOfClass, Class[][] paramArrayOfClass1)
  {
    for (int i = 1; i <= 4; i++)
    {
      Object localObject = null;
      int j = -1;
      for (int k = 0; k < paramArrayOfClass1.length; k++)
      {
        Class[] arrayOfClass = paramArrayOfClass1[k];
        if ((Types.isSignatureAssignable(paramArrayOfClass, arrayOfClass, i)) && ((localObject == null) || (Types.isSignatureAssignable(arrayOfClass, (Class[])localObject, 1))))
        {
          localObject = arrayOfClass;
          j = k;
        }
      }
      if (localObject != null) {
        return j;
      }
    }
    return -1;
  }
  
  private static String accessorName(String paramString1, String paramString2)
  {
    return paramString1 + String.valueOf(Character.toUpperCase(paramString2.charAt(0))) + paramString2.substring(1);
  }
  
  public static boolean hasObjectPropertyGetter(Class paramClass, String paramString)
  {
    String str = accessorName("get", paramString);
    try
    {
      paramClass.getMethod(str, new Class[0]);
      return true;
    }
    catch (NoSuchMethodException localNoSuchMethodException1)
    {
      str = accessorName("is", paramString);
      try
      {
        Method localMethod = paramClass.getMethod(str, new Class[0]);
        return localMethod.getReturnType() == Boolean.TYPE;
      }
      catch (NoSuchMethodException localNoSuchMethodException2) {}
    }
    return false;
  }
  
  public static boolean hasObjectPropertySetter(Class paramClass, String paramString)
  {
    String str = accessorName("set", paramString);
    Method[] arrayOfMethod = paramClass.getMethods();
    for (int i = 0; i < arrayOfMethod.length; i++) {
      if (arrayOfMethod[i].getName().equals(str)) {
        return true;
      }
    }
    return false;
  }
  
  public static Object getObjectProperty(Object paramObject, String paramString)
    throws UtilEvalError, ReflectError
  {
    Object[] arrayOfObject = new Object[0];
    Interpreter.debug("property access: ");
    Method localMethod = null;
    Object localObject1 = null;
    Object localObject2 = null;
    try
    {
      String str1 = accessorName("get", paramString);
      localMethod = resolveExpectedJavaMethod(null, paramObject.getClass(), paramObject, str1, arrayOfObject, false);
    }
    catch (Exception localException1)
    {
      localObject1 = localException1;
    }
    if (localMethod == null) {
      try
      {
        String str2 = accessorName("is", paramString);
        localMethod = resolveExpectedJavaMethod(null, paramObject.getClass(), paramObject, str2, arrayOfObject, false);
        if (localMethod.getReturnType() != Boolean.TYPE) {
          localMethod = null;
        }
      }
      catch (Exception localException2)
      {
        localObject2 = localException2;
      }
    }
    if (localMethod == null) {
      throw new ReflectError("Error in property getter: " + localObject1 + (localObject2 != null ? " : " + localObject2 : ""));
    }
    try
    {
      return invokeMethod(localMethod, paramObject, arrayOfObject);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new UtilEvalError("Property accessor threw exception: " + localInvocationTargetException.getTargetException());
    }
  }
  
  public static void setObjectProperty(Object paramObject1, String paramString, Object paramObject2)
    throws ReflectError, UtilEvalError
  {
    String str = accessorName("set", paramString);
    Object[] arrayOfObject = { paramObject2 };
    Interpreter.debug("property access: ");
    try
    {
      Method localMethod = resolveExpectedJavaMethod(null, paramObject1.getClass(), paramObject1, str, arrayOfObject, false);
      invokeMethod(localMethod, paramObject1, arrayOfObject);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new UtilEvalError("Property accessor threw exception: " + localInvocationTargetException.getTargetException());
    }
  }
  
  public static String normalizeClassName(Class paramClass)
  {
    if (!paramClass.isArray()) {
      return paramClass.getName();
    }
    StringBuffer localStringBuffer = new StringBuffer();
    try
    {
      localStringBuffer.append(getArrayBaseType(paramClass).getName() + " ");
      for (int i = 0; i < getArrayDimensions(paramClass); i++) {
        localStringBuffer.append("[]");
      }
    }
    catch (ReflectError localReflectError) {}
    return localStringBuffer.toString();
  }
  
  public static int getArrayDimensions(Class paramClass)
  {
    if (!paramClass.isArray()) {
      return 0;
    }
    return paramClass.getName().lastIndexOf('[') + 1;
  }
  
  public static Class getArrayBaseType(Class paramClass)
    throws ReflectError
  {
    if (!paramClass.isArray()) {
      throw new ReflectError("The class is not an array.");
    }
    return paramClass.getComponentType();
  }
  
  public static Object invokeCompiledCommand(Class paramClass, Object[] paramArrayOfObject, Interpreter paramInterpreter, CallStack paramCallStack)
    throws UtilEvalError
  {
    Object[] arrayOfObject = new Object[paramArrayOfObject.length + 2];
    arrayOfObject[0] = paramInterpreter;
    arrayOfObject[1] = paramCallStack;
    System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 2, paramArrayOfObject.length);
    BshClassManager localBshClassManager = paramInterpreter.getClassManager();
    try
    {
      return invokeStaticMethod(localBshClassManager, paramClass, "invoke", arrayOfObject);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new UtilEvalError("Error in compiled command: " + localInvocationTargetException.getTargetException());
    }
    catch (ReflectError localReflectError)
    {
      throw new UtilEvalError("Error invoking compiled command: " + localReflectError);
    }
  }
  
  private static void logInvokeMethod(String paramString, Method paramMethod, Object[] paramArrayOfObject)
  {
    if (Interpreter.DEBUG)
    {
      Interpreter.debug(paramString + paramMethod + " with args:");
      for (int i = 0; i < paramArrayOfObject.length; i++) {
        Interpreter.debug("args[" + i + "] = " + paramArrayOfObject[i] + " type = " + paramArrayOfObject[i].getClass());
      }
    }
  }
  
  private static void checkFoundStaticMethod(Method paramMethod, boolean paramBoolean, Class paramClass)
    throws UtilEvalError
  {
    if ((paramMethod != null) && (paramBoolean) && (!isStatic(paramMethod))) {
      throw new UtilEvalError("Cannot reach instance method: " + StringUtil.methodString(paramMethod.getName(), paramMethod.getParameterTypes()) + " from static context: " + paramClass.getName());
    }
  }
  
  private static ReflectError cantFindConstructor(Class paramClass, Class[] paramArrayOfClass)
  {
    if (paramArrayOfClass.length == 0) {
      return new ReflectError("Can't find default constructor for: " + paramClass);
    }
    return new ReflectError("Can't find constructor: " + StringUtil.methodString(paramClass.getName(), paramArrayOfClass) + " in class: " + paramClass.getName());
  }
  
  private static boolean isPublic(Class paramClass)
  {
    return Modifier.isPublic(paramClass.getModifiers());
  }
  
  private static boolean isPublic(Method paramMethod)
  {
    return Modifier.isPublic(paramMethod.getModifiers());
  }
  
  private static boolean isPublic(Constructor paramConstructor)
  {
    return Modifier.isPublic(paramConstructor.getModifiers());
  }
  
  private static boolean isStatic(Method paramMethod)
  {
    return Modifier.isStatic(paramMethod.getModifiers());
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/Reflect.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */