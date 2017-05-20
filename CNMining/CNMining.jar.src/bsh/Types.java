package bsh;

import java.util.Hashtable;

class Types
{
  static final int CAST = 0;
  static final int ASSIGNMENT = 1;
  static final int JAVA_BASE_ASSIGNABLE = 1;
  static final int JAVA_BOX_TYPES_ASSIGABLE = 2;
  static final int JAVA_VARARGS_ASSIGNABLE = 3;
  static final int BSH_ASSIGNABLE = 4;
  static final int FIRST_ROUND_ASSIGNABLE = 1;
  static final int LAST_ROUND_ASSIGNABLE = 4;
  static Primitive VALID_CAST = new Primitive(1);
  static Primitive INVALID_CAST = new Primitive(-1);
  
  public static Class[] getTypes(Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject == null) {
      return new Class[0];
    }
    Class[] arrayOfClass = new Class[paramArrayOfObject.length];
    for (int i = 0; i < paramArrayOfObject.length; i++) {
      if (paramArrayOfObject[i] == null) {
        arrayOfClass[i] = null;
      } else if ((paramArrayOfObject[i] instanceof Primitive)) {
        arrayOfClass[i] = ((Primitive)paramArrayOfObject[i]).getType();
      } else {
        arrayOfClass[i] = paramArrayOfObject[i].getClass();
      }
    }
    return arrayOfClass;
  }
  
  static boolean isSignatureAssignable(Class[] paramArrayOfClass1, Class[] paramArrayOfClass2, int paramInt)
  {
    if ((paramInt != 3) && (paramArrayOfClass1.length != paramArrayOfClass2.length)) {
      return false;
    }
    switch (paramInt)
    {
    case 1: 
      for (int i = 0; i < paramArrayOfClass1.length; i++) {
        if (!isJavaBaseAssignable(paramArrayOfClass2[i], paramArrayOfClass1[i])) {
          return false;
        }
      }
      return true;
    case 2: 
      for (int j = 0; j < paramArrayOfClass1.length; j++) {
        if (!isJavaBoxTypesAssignable(paramArrayOfClass2[j], paramArrayOfClass1[j])) {
          return false;
        }
      }
      return true;
    case 3: 
      return isSignatureVarargsAssignable(paramArrayOfClass1, paramArrayOfClass2);
    case 4: 
      for (int k = 0; k < paramArrayOfClass1.length; k++) {
        if (!isBshAssignable(paramArrayOfClass2[k], paramArrayOfClass1[k])) {
          return false;
        }
      }
      return true;
    }
    throw new InterpreterError("bad case");
  }
  
  private static boolean isSignatureVarargsAssignable(Class[] paramArrayOfClass1, Class[] paramArrayOfClass2)
  {
    return false;
  }
  
  static boolean isJavaAssignable(Class paramClass1, Class paramClass2)
  {
    return (isJavaBaseAssignable(paramClass1, paramClass2)) || (isJavaBoxTypesAssignable(paramClass1, paramClass2));
  }
  
  static boolean isJavaBaseAssignable(Class paramClass1, Class paramClass2)
  {
    if (paramClass1 == null) {
      return false;
    }
    if (paramClass2 == null) {
      return !paramClass1.isPrimitive();
    }
    if ((paramClass1.isPrimitive()) && (paramClass2.isPrimitive()))
    {
      if (paramClass1 == paramClass2) {
        return true;
      }
      if ((paramClass2 == Byte.TYPE) && ((paramClass1 == Short.TYPE) || (paramClass1 == Integer.TYPE) || (paramClass1 == Long.TYPE) || (paramClass1 == Float.TYPE) || (paramClass1 == Double.TYPE))) {
        return true;
      }
      if ((paramClass2 == Short.TYPE) && ((paramClass1 == Integer.TYPE) || (paramClass1 == Long.TYPE) || (paramClass1 == Float.TYPE) || (paramClass1 == Double.TYPE))) {
        return true;
      }
      if ((paramClass2 == Character.TYPE) && ((paramClass1 == Integer.TYPE) || (paramClass1 == Long.TYPE) || (paramClass1 == Float.TYPE) || (paramClass1 == Double.TYPE))) {
        return true;
      }
      if ((paramClass2 == Integer.TYPE) && ((paramClass1 == Long.TYPE) || (paramClass1 == Float.TYPE) || (paramClass1 == Double.TYPE))) {
        return true;
      }
      if ((paramClass2 == Long.TYPE) && ((paramClass1 == Float.TYPE) || (paramClass1 == Double.TYPE))) {
        return true;
      }
      if ((paramClass2 == Float.TYPE) && (paramClass1 == Double.TYPE)) {
        return true;
      }
    }
    else if (paramClass1.isAssignableFrom(paramClass2))
    {
      return true;
    }
    return false;
  }
  
  static boolean isJavaBoxTypesAssignable(Class paramClass1, Class paramClass2)
  {
    if (paramClass1 == null) {
      return false;
    }
    if (paramClass1 == Object.class) {
      return true;
    }
    if ((paramClass1 == Number.class) && (paramClass2 != Character.TYPE) && (paramClass2 != Boolean.TYPE)) {
      return true;
    }
    return Primitive.wrapperMap.get(paramClass1) == paramClass2;
  }
  
  static boolean isBshAssignable(Class paramClass1, Class paramClass2)
  {
    try
    {
      return castObject(paramClass1, paramClass2, null, 1, true) == VALID_CAST;
    }
    catch (UtilEvalError localUtilEvalError)
    {
      throw new InterpreterError("err in cast check: " + localUtilEvalError);
    }
  }
  
  public static Object castObject(Object paramObject, Class paramClass, int paramInt)
    throws UtilEvalError
  {
    if (paramObject == null) {
      throw new InterpreterError("null fromValue");
    }
    Class localClass = (paramObject instanceof Primitive) ? ((Primitive)paramObject).getType() : paramObject.getClass();
    return castObject(paramClass, localClass, paramObject, paramInt, false);
  }
  
  private static Object castObject(Class paramClass1, Class paramClass2, Object paramObject, int paramInt, boolean paramBoolean)
    throws UtilEvalError
  {
    if ((paramBoolean) && (paramObject != null)) {
      throw new InterpreterError("bad cast params 1");
    }
    if ((!paramBoolean) && (paramObject == null)) {
      throw new InterpreterError("bad cast params 2");
    }
    if (paramClass2 == Primitive.class) {
      throw new InterpreterError("bad from Type, need to unwrap");
    }
    if ((paramObject == Primitive.NULL) && (paramClass2 != null)) {
      throw new InterpreterError("inconsistent args 1");
    }
    if ((paramObject == Primitive.VOID) && (paramClass2 != Void.TYPE)) {
      throw new InterpreterError("inconsistent args 2");
    }
    if (paramClass1 == Void.TYPE) {
      throw new InterpreterError("loose toType should be null");
    }
    if ((paramClass1 == null) || (paramClass1 == paramClass2)) {
      return paramBoolean ? VALID_CAST : paramObject;
    }
    if (paramClass1.isPrimitive())
    {
      if ((paramClass2 == Void.TYPE) || (paramClass2 == null) || (paramClass2.isPrimitive())) {
        return Primitive.castPrimitive(paramClass1, paramClass2, (Primitive)paramObject, paramBoolean, paramInt);
      }
      if (Primitive.isWrapperType(paramClass2))
      {
        Class localClass = Primitive.unboxType(paramClass2);
        Primitive localPrimitive;
        if (paramBoolean) {
          localPrimitive = null;
        } else {
          localPrimitive = (Primitive)Primitive.wrap(paramObject, localClass);
        }
        return Primitive.castPrimitive(paramClass1, localClass, localPrimitive, paramBoolean, paramInt);
      }
      if (paramBoolean) {
        return INVALID_CAST;
      }
      throw castError(paramClass1, paramClass2, paramInt);
    }
    if ((paramClass2 == Void.TYPE) || (paramClass2 == null) || (paramClass2.isPrimitive()))
    {
      if ((Primitive.isWrapperType(paramClass1)) && (paramClass2 != Void.TYPE) && (paramClass2 != null)) {
        return paramBoolean ? VALID_CAST : Primitive.castWrapper(Primitive.unboxType(paramClass1), ((Primitive)paramObject).getValue());
      }
      if ((paramClass1 == Object.class) && (paramClass2 != Void.TYPE) && (paramClass2 != null)) {
        return paramBoolean ? VALID_CAST : ((Primitive)paramObject).getValue();
      }
      return Primitive.castPrimitive(paramClass1, paramClass2, (Primitive)paramObject, paramBoolean, paramInt);
    }
    if (paramClass1.isAssignableFrom(paramClass2)) {
      return paramBoolean ? VALID_CAST : paramObject;
    }
    if ((paramClass1.isInterface()) && (This.class.isAssignableFrom(paramClass2)) && (Capabilities.canGenerateInterfaces())) {
      return paramBoolean ? VALID_CAST : ((This)paramObject).getInterface(paramClass1);
    }
    if ((Primitive.isWrapperType(paramClass1)) && (Primitive.isWrapperType(paramClass2))) {
      return paramBoolean ? VALID_CAST : Primitive.castWrapper(paramClass1, paramObject);
    }
    if (paramBoolean) {
      return INVALID_CAST;
    }
    throw castError(paramClass1, paramClass2, paramInt);
  }
  
  static UtilEvalError castError(Class paramClass1, Class paramClass2, int paramInt)
  {
    return castError(Reflect.normalizeClassName(paramClass1), Reflect.normalizeClassName(paramClass2), paramInt);
  }
  
  static UtilEvalError castError(String paramString1, String paramString2, int paramInt)
  {
    if (paramInt == 1) {
      return new UtilEvalError("Can't assign " + paramString2 + " to " + paramString1);
    }
    ClassCastException localClassCastException = new ClassCastException("Cannot cast " + paramString2 + " to " + paramString1);
    return new UtilTargetError(localClassCastException);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/Types.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */