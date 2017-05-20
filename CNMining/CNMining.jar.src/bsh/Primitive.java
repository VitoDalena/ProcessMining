package bsh;

import java.io.Serializable;
import java.util.Hashtable;

public final class Primitive
  implements ParserConstants, Serializable
{
  static Hashtable wrapperMap = new Hashtable();
  private Object value;
  public static final Primitive NULL = new Primitive(Special.NULL_VALUE);
  public static final Primitive VOID = new Primitive(Special.VOID_TYPE);
  
  public Primitive(Object paramObject)
  {
    if (paramObject == null) {
      throw new InterpreterError("Use Primitve.NULL instead of Primitive(null)");
    }
    if ((paramObject != Special.NULL_VALUE) && (paramObject != Special.VOID_TYPE) && (!isWrapperType(paramObject.getClass()))) {
      throw new InterpreterError("Not a wrapper type: " + paramObject);
    }
    this.value = paramObject;
  }
  
  public Primitive(boolean paramBoolean)
  {
    this(new Boolean(paramBoolean));
  }
  
  public Primitive(byte paramByte)
  {
    this(new Byte(paramByte));
  }
  
  public Primitive(short paramShort)
  {
    this(new Short(paramShort));
  }
  
  public Primitive(char paramChar)
  {
    this(new Character(paramChar));
  }
  
  public Primitive(int paramInt)
  {
    this(new Integer(paramInt));
  }
  
  public Primitive(long paramLong)
  {
    this(new Long(paramLong));
  }
  
  public Primitive(float paramFloat)
  {
    this(new Float(paramFloat));
  }
  
  public Primitive(double paramDouble)
  {
    this(new Double(paramDouble));
  }
  
  public Object getValue()
  {
    if (this.value == Special.NULL_VALUE) {
      return null;
    }
    if (this.value == Special.VOID_TYPE) {
      throw new InterpreterError("attempt to unwrap void type");
    }
    return this.value;
  }
  
  public String toString()
  {
    if (this.value == Special.NULL_VALUE) {
      return "null";
    }
    if (this.value == Special.VOID_TYPE) {
      return "void";
    }
    return this.value.toString();
  }
  
  public Class getType()
  {
    if (this == VOID) {
      return Void.TYPE;
    }
    if (this == NULL) {
      return null;
    }
    return unboxType(this.value.getClass());
  }
  
  public static Object binaryOperation(Object paramObject1, Object paramObject2, int paramInt)
    throws UtilEvalError
  {
    if ((paramObject1 == NULL) || (paramObject2 == NULL)) {
      throw new UtilEvalError("Null value or 'null' literal in binary operation");
    }
    if ((paramObject1 == VOID) || (paramObject2 == VOID)) {
      throw new UtilEvalError("Undefined variable, class, or 'void' literal in binary operation");
    }
    Class localClass1 = paramObject1.getClass();
    Class localClass2 = paramObject2.getClass();
    if ((paramObject1 instanceof Primitive)) {
      paramObject1 = ((Primitive)paramObject1).getValue();
    }
    if ((paramObject2 instanceof Primitive)) {
      paramObject2 = ((Primitive)paramObject2).getValue();
    }
    Object[] arrayOfObject = promotePrimitives(paramObject1, paramObject2);
    Object localObject1 = arrayOfObject[0];
    Object localObject2 = arrayOfObject[1];
    if (localObject1.getClass() != localObject2.getClass()) {
      throw new UtilEvalError("Type mismatch in operator.  " + localObject1.getClass() + " cannot be used with " + localObject2.getClass());
    }
    Object localObject3;
    try
    {
      localObject3 = binaryOperationImpl(localObject1, localObject2, paramInt);
    }
    catch (ArithmeticException localArithmeticException)
    {
      throw new UtilTargetError("Arithemetic Exception in binary op", localArithmeticException);
    }
    if (((localClass1 == Primitive.class) && (localClass2 == Primitive.class)) || ((localObject3 instanceof Boolean))) {
      return new Primitive(localObject3);
    }
    return localObject3;
  }
  
  static Object binaryOperationImpl(Object paramObject1, Object paramObject2, int paramInt)
    throws UtilEvalError
  {
    if ((paramObject1 instanceof Boolean)) {
      return booleanBinaryOperation((Boolean)paramObject1, (Boolean)paramObject2, paramInt);
    }
    if ((paramObject1 instanceof Integer)) {
      return intBinaryOperation((Integer)paramObject1, (Integer)paramObject2, paramInt);
    }
    if ((paramObject1 instanceof Long)) {
      return longBinaryOperation((Long)paramObject1, (Long)paramObject2, paramInt);
    }
    if ((paramObject1 instanceof Float)) {
      return floatBinaryOperation((Float)paramObject1, (Float)paramObject2, paramInt);
    }
    if ((paramObject1 instanceof Double)) {
      return doubleBinaryOperation((Double)paramObject1, (Double)paramObject2, paramInt);
    }
    throw new UtilEvalError("Invalid types in binary operator");
  }
  
  static Boolean booleanBinaryOperation(Boolean paramBoolean1, Boolean paramBoolean2, int paramInt)
  {
    boolean bool1 = paramBoolean1.booleanValue();
    boolean bool2 = paramBoolean2.booleanValue();
    switch (paramInt)
    {
    case 90: 
      return new Boolean(bool1 == bool2);
    case 95: 
      return new Boolean(bool1 != bool2);
    case 96: 
    case 97: 
      return new Boolean((bool1) || (bool2));
    case 98: 
    case 99: 
      return new Boolean((bool1) && (bool2));
    }
    throw new InterpreterError("unimplemented binary operator");
  }
  
  static Object longBinaryOperation(Long paramLong1, Long paramLong2, int paramInt)
  {
    long l1 = paramLong1.longValue();
    long l2 = paramLong2.longValue();
    switch (paramInt)
    {
    case 84: 
    case 85: 
      return new Boolean(l1 < l2);
    case 82: 
    case 83: 
      return new Boolean(l1 > l2);
    case 90: 
      return new Boolean(l1 == l2);
    case 91: 
    case 92: 
      return new Boolean(l1 <= l2);
    case 93: 
    case 94: 
      return new Boolean(l1 >= l2);
    case 95: 
      return new Boolean(l1 != l2);
    case 102: 
      return new Long(l1 + l2);
    case 103: 
      return new Long(l1 - l2);
    case 104: 
      return new Long(l1 * l2);
    case 105: 
      return new Long(l1 / l2);
    case 111: 
      return new Long(l1 % l2);
    case 112: 
    case 113: 
      return new Long(l1 << (int)l2);
    case 114: 
    case 115: 
      return new Long(l1 >> (int)l2);
    case 116: 
    case 117: 
      return new Long(l1 >>> (int)l2);
    case 106: 
    case 107: 
      return new Long(l1 & l2);
    case 108: 
    case 109: 
      return new Long(l1 | l2);
    case 110: 
      return new Long(l1 ^ l2);
    }
    throw new InterpreterError("Unimplemented binary long operator");
  }
  
  static Object intBinaryOperation(Integer paramInteger1, Integer paramInteger2, int paramInt)
  {
    int i = paramInteger1.intValue();
    int j = paramInteger2.intValue();
    switch (paramInt)
    {
    case 84: 
    case 85: 
      return new Boolean(i < j);
    case 82: 
    case 83: 
      return new Boolean(i > j);
    case 90: 
      return new Boolean(i == j);
    case 91: 
    case 92: 
      return new Boolean(i <= j);
    case 93: 
    case 94: 
      return new Boolean(i >= j);
    case 95: 
      return new Boolean(i != j);
    case 102: 
      return new Integer(i + j);
    case 103: 
      return new Integer(i - j);
    case 104: 
      return new Integer(i * j);
    case 105: 
      return new Integer(i / j);
    case 111: 
      return new Integer(i % j);
    case 112: 
    case 113: 
      return new Integer(i << j);
    case 114: 
    case 115: 
      return new Integer(i >> j);
    case 116: 
    case 117: 
      return new Integer(i >>> j);
    case 106: 
    case 107: 
      return new Integer(i & j);
    case 108: 
    case 109: 
      return new Integer(i | j);
    case 110: 
      return new Integer(i ^ j);
    }
    throw new InterpreterError("Unimplemented binary integer operator");
  }
  
  static Object doubleBinaryOperation(Double paramDouble1, Double paramDouble2, int paramInt)
    throws UtilEvalError
  {
    double d1 = paramDouble1.doubleValue();
    double d2 = paramDouble2.doubleValue();
    switch (paramInt)
    {
    case 84: 
    case 85: 
      return new Boolean(d1 < d2);
    case 82: 
    case 83: 
      return new Boolean(d1 > d2);
    case 90: 
      return new Boolean(d1 == d2);
    case 91: 
    case 92: 
      return new Boolean(d1 <= d2);
    case 93: 
    case 94: 
      return new Boolean(d1 >= d2);
    case 95: 
      return new Boolean(d1 != d2);
    case 102: 
      return new Double(d1 + d2);
    case 103: 
      return new Double(d1 - d2);
    case 104: 
      return new Double(d1 * d2);
    case 105: 
      return new Double(d1 / d2);
    case 111: 
      return new Double(d1 % d2);
    case 112: 
    case 113: 
    case 114: 
    case 115: 
    case 116: 
    case 117: 
      throw new UtilEvalError("Can't shift doubles");
    }
    throw new InterpreterError("Unimplemented binary double operator");
  }
  
  static Object floatBinaryOperation(Float paramFloat1, Float paramFloat2, int paramInt)
    throws UtilEvalError
  {
    float f1 = paramFloat1.floatValue();
    float f2 = paramFloat2.floatValue();
    switch (paramInt)
    {
    case 84: 
    case 85: 
      return new Boolean(f1 < f2);
    case 82: 
    case 83: 
      return new Boolean(f1 > f2);
    case 90: 
      return new Boolean(f1 == f2);
    case 91: 
    case 92: 
      return new Boolean(f1 <= f2);
    case 93: 
    case 94: 
      return new Boolean(f1 >= f2);
    case 95: 
      return new Boolean(f1 != f2);
    case 102: 
      return new Float(f1 + f2);
    case 103: 
      return new Float(f1 - f2);
    case 104: 
      return new Float(f1 * f2);
    case 105: 
      return new Float(f1 / f2);
    case 111: 
      return new Float(f1 % f2);
    case 112: 
    case 113: 
    case 114: 
    case 115: 
    case 116: 
    case 117: 
      throw new UtilEvalError("Can't shift floats ");
    }
    throw new InterpreterError("Unimplemented binary float operator");
  }
  
  static Object promoteToInteger(Object paramObject)
  {
    if ((paramObject instanceof Character)) {
      return new Integer(((Character)paramObject).charValue());
    }
    if (((paramObject instanceof Byte)) || ((paramObject instanceof Short))) {
      return new Integer(((Number)paramObject).intValue());
    }
    return paramObject;
  }
  
  static Object[] promotePrimitives(Object paramObject1, Object paramObject2)
  {
    paramObject1 = promoteToInteger(paramObject1);
    paramObject2 = promoteToInteger(paramObject2);
    if (((paramObject1 instanceof Number)) && ((paramObject2 instanceof Number)))
    {
      Number localNumber1 = (Number)paramObject1;
      Number localNumber2 = (Number)paramObject2;
      boolean bool;
      if (((bool = localNumber1 instanceof Double)) || ((localNumber2 instanceof Double)))
      {
        if (bool) {
          paramObject2 = new Double(localNumber2.doubleValue());
        } else {
          paramObject1 = new Double(localNumber1.doubleValue());
        }
      }
      else if (((bool = localNumber1 instanceof Float)) || ((localNumber2 instanceof Float)))
      {
        if (bool) {
          paramObject2 = new Float(localNumber2.floatValue());
        } else {
          paramObject1 = new Float(localNumber1.floatValue());
        }
      }
      else if (((bool = localNumber1 instanceof Long)) || ((localNumber2 instanceof Long))) {
        if (bool) {
          paramObject2 = new Long(localNumber2.longValue());
        } else {
          paramObject1 = new Long(localNumber1.longValue());
        }
      }
    }
    return new Object[] { paramObject1, paramObject2 };
  }
  
  public static Primitive unaryOperation(Primitive paramPrimitive, int paramInt)
    throws UtilEvalError
  {
    if (paramPrimitive == NULL) {
      throw new UtilEvalError("illegal use of null object or 'null' literal");
    }
    if (paramPrimitive == VOID) {
      throw new UtilEvalError("illegal use of undefined object or 'void' literal");
    }
    Class localClass = paramPrimitive.getType();
    Object localObject = promoteToInteger(paramPrimitive.getValue());
    if ((localObject instanceof Boolean)) {
      return new Primitive(booleanUnaryOperation((Boolean)localObject, paramInt));
    }
    if ((localObject instanceof Integer))
    {
      int i = intUnaryOperation((Integer)localObject, paramInt);
      if ((paramInt == 100) || (paramInt == 101))
      {
        if (localClass == Byte.TYPE) {
          return new Primitive((byte)i);
        }
        if (localClass == Short.TYPE) {
          return new Primitive((short)i);
        }
        if (localClass == Character.TYPE) {
          return new Primitive((char)i);
        }
      }
      return new Primitive(i);
    }
    if ((localObject instanceof Long)) {
      return new Primitive(longUnaryOperation((Long)localObject, paramInt));
    }
    if ((localObject instanceof Float)) {
      return new Primitive(floatUnaryOperation((Float)localObject, paramInt));
    }
    if ((localObject instanceof Double)) {
      return new Primitive(doubleUnaryOperation((Double)localObject, paramInt));
    }
    throw new InterpreterError("An error occurred.  Please call technical support.");
  }
  
  static boolean booleanUnaryOperation(Boolean paramBoolean, int paramInt)
    throws UtilEvalError
  {
    boolean bool = paramBoolean.booleanValue();
    switch (paramInt)
    {
    case 86: 
      return !bool;
    }
    throw new UtilEvalError("Operator inappropriate for boolean");
  }
  
  static int intUnaryOperation(Integer paramInteger, int paramInt)
  {
    int i = paramInteger.intValue();
    switch (paramInt)
    {
    case 102: 
      return i;
    case 103: 
      return -i;
    case 87: 
      return i ^ 0xFFFFFFFF;
    case 100: 
      return i + 1;
    case 101: 
      return i - 1;
    }
    throw new InterpreterError("bad integer unaryOperation");
  }
  
  static long longUnaryOperation(Long paramLong, int paramInt)
  {
    long l = paramLong.longValue();
    switch (paramInt)
    {
    case 102: 
      return l;
    case 103: 
      return -l;
    case 87: 
      return l ^ 0xFFFFFFFFFFFFFFFF;
    case 100: 
      return l + 1L;
    case 101: 
      return l - 1L;
    }
    throw new InterpreterError("bad long unaryOperation");
  }
  
  static float floatUnaryOperation(Float paramFloat, int paramInt)
  {
    float f = paramFloat.floatValue();
    switch (paramInt)
    {
    case 102: 
      return f;
    case 103: 
      return -f;
    }
    throw new InterpreterError("bad float unaryOperation");
  }
  
  static double doubleUnaryOperation(Double paramDouble, int paramInt)
  {
    double d = paramDouble.doubleValue();
    switch (paramInt)
    {
    case 102: 
      return d;
    case 103: 
      return -d;
    }
    throw new InterpreterError("bad double unaryOperation");
  }
  
  public int intValue()
    throws UtilEvalError
  {
    if ((this.value instanceof Number)) {
      return ((Number)this.value).intValue();
    }
    throw new UtilEvalError("Primitive not a number");
  }
  
  public boolean booleanValue()
    throws UtilEvalError
  {
    if ((this.value instanceof Boolean)) {
      return ((Boolean)this.value).booleanValue();
    }
    throw new UtilEvalError("Primitive not a boolean");
  }
  
  public boolean isNumber()
  {
    return (!(this.value instanceof Boolean)) && (this != NULL) && (this != VOID);
  }
  
  public Number numberValue()
    throws UtilEvalError
  {
    Object localObject = this.value;
    if ((localObject instanceof Character)) {
      localObject = new Integer(((Character)localObject).charValue());
    }
    if ((localObject instanceof Number)) {
      return (Number)localObject;
    }
    throw new UtilEvalError("Primitive not a number");
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Primitive)) {
      return ((Primitive)paramObject).value.equals(this.value);
    }
    return false;
  }
  
  public int hashCode()
  {
    return this.value.hashCode() * 21;
  }
  
  public static Object unwrap(Object paramObject)
  {
    if (paramObject == VOID) {
      return null;
    }
    if ((paramObject instanceof Primitive)) {
      return ((Primitive)paramObject).getValue();
    }
    return paramObject;
  }
  
  public static Object[] unwrap(Object[] paramArrayOfObject)
  {
    Object[] arrayOfObject = new Object[paramArrayOfObject.length];
    for (int i = 0; i < paramArrayOfObject.length; i++) {
      arrayOfObject[i] = unwrap(paramArrayOfObject[i]);
    }
    return arrayOfObject;
  }
  
  public static Object[] wrap(Object[] paramArrayOfObject, Class[] paramArrayOfClass)
  {
    if (paramArrayOfObject == null) {
      return null;
    }
    Object[] arrayOfObject = new Object[paramArrayOfObject.length];
    for (int i = 0; i < paramArrayOfObject.length; i++) {
      arrayOfObject[i] = wrap(paramArrayOfObject[i], paramArrayOfClass[i]);
    }
    return arrayOfObject;
  }
  
  public static Object wrap(Object paramObject, Class paramClass)
  {
    if (paramClass == Void.TYPE) {
      return VOID;
    }
    if (paramObject == null) {
      return NULL;
    }
    if (paramClass.isPrimitive()) {
      return new Primitive(paramObject);
    }
    return paramObject;
  }
  
  public static Primitive getDefaultValue(Class paramClass)
  {
    if ((paramClass == null) || (!paramClass.isPrimitive())) {
      return NULL;
    }
    if (paramClass == Boolean.TYPE) {
      return new Primitive(false);
    }
    try
    {
      return new Primitive(0).castToType(paramClass, 0);
    }
    catch (UtilEvalError localUtilEvalError)
    {
      throw new InterpreterError("bad cast");
    }
  }
  
  public static Class boxType(Class paramClass)
  {
    Class localClass = (Class)wrapperMap.get(paramClass);
    if (localClass != null) {
      return localClass;
    }
    throw new InterpreterError("Not a primitive type: " + paramClass);
  }
  
  public static Class unboxType(Class paramClass)
  {
    Class localClass = (Class)wrapperMap.get(paramClass);
    if (localClass != null) {
      return localClass;
    }
    throw new InterpreterError("Not a primitive wrapper type: " + paramClass);
  }
  
  public Primitive castToType(Class paramClass, int paramInt)
    throws UtilEvalError
  {
    return castPrimitive(paramClass, getType(), this, false, paramInt);
  }
  
  static Primitive castPrimitive(Class paramClass1, Class paramClass2, Primitive paramPrimitive, boolean paramBoolean, int paramInt)
    throws UtilEvalError
  {
    if ((paramBoolean) && (paramPrimitive != null)) {
      throw new InterpreterError("bad cast param 1");
    }
    if ((!paramBoolean) && (paramPrimitive == null)) {
      throw new InterpreterError("bad cast param 2");
    }
    if ((paramClass2 != null) && (!paramClass2.isPrimitive())) {
      throw new InterpreterError("bad fromType:" + paramClass2);
    }
    if ((paramPrimitive == NULL) && (paramClass2 != null)) {
      throw new InterpreterError("inconsistent args 1");
    }
    if ((paramPrimitive == VOID) && (paramClass2 != Void.TYPE)) {
      throw new InterpreterError("inconsistent args 2");
    }
    if (paramClass2 == Void.TYPE)
    {
      if (paramBoolean) {
        return Types.INVALID_CAST;
      }
      throw Types.castError(Reflect.normalizeClassName(paramClass1), "void value", paramInt);
    }
    Object localObject = null;
    if (paramPrimitive != null) {
      localObject = paramPrimitive.getValue();
    }
    if (paramClass1.isPrimitive())
    {
      if (paramClass2 == null)
      {
        if (paramBoolean) {
          return Types.INVALID_CAST;
        }
        throw Types.castError("primitive type:" + paramClass1, "Null value", paramInt);
      }
    }
    else
    {
      if (paramClass2 == null) {
        return paramBoolean ? Types.VALID_CAST : NULL;
      }
      if (paramBoolean) {
        return Types.INVALID_CAST;
      }
      throw Types.castError("object type:" + paramClass1, "primitive value", paramInt);
    }
    if (paramClass2 == Boolean.TYPE)
    {
      if (paramClass1 != Boolean.TYPE)
      {
        if (paramBoolean) {
          return Types.INVALID_CAST;
        }
        throw Types.castError(paramClass1, paramClass2, paramInt);
      }
      return paramBoolean ? Types.VALID_CAST : paramPrimitive;
    }
    if ((paramInt == 1) && (!Types.isJavaAssignable(paramClass1, paramClass2)))
    {
      if (paramBoolean) {
        return Types.INVALID_CAST;
      }
      throw Types.castError(paramClass1, paramClass2, paramInt);
    }
    return paramBoolean ? Types.VALID_CAST : new Primitive(castWrapper(paramClass1, localObject));
  }
  
  public static boolean isWrapperType(Class paramClass)
  {
    return (wrapperMap.get(paramClass) != null) && (!paramClass.isPrimitive());
  }
  
  static Object castWrapper(Class paramClass, Object paramObject)
  {
    if (!paramClass.isPrimitive()) {
      throw new InterpreterError("invalid type in castWrapper: " + paramClass);
    }
    if (paramObject == null) {
      throw new InterpreterError("null value in castWrapper, guard");
    }
    if ((paramObject instanceof Boolean))
    {
      if (paramClass != Boolean.TYPE) {
        throw new InterpreterError("bad wrapper cast of boolean");
      }
      return paramObject;
    }
    if ((paramObject instanceof Character)) {
      paramObject = new Integer(((Character)paramObject).charValue());
    }
    if (!(paramObject instanceof Number)) {
      throw new InterpreterError("bad type in cast");
    }
    Number localNumber = (Number)paramObject;
    if (paramClass == Byte.TYPE) {
      return new Byte(localNumber.byteValue());
    }
    if (paramClass == Short.TYPE) {
      return new Short(localNumber.shortValue());
    }
    if (paramClass == Character.TYPE) {
      return new Character((char)localNumber.intValue());
    }
    if (paramClass == Integer.TYPE) {
      return new Integer(localNumber.intValue());
    }
    if (paramClass == Long.TYPE) {
      return new Long(localNumber.longValue());
    }
    if (paramClass == Float.TYPE) {
      return new Float(localNumber.floatValue());
    }
    if (paramClass == Double.TYPE) {
      return new Double(localNumber.doubleValue());
    }
    throw new InterpreterError("error in wrapper cast");
  }
  
  static
  {
    wrapperMap.put(Boolean.TYPE, Boolean.class);
    wrapperMap.put(Byte.TYPE, Byte.class);
    wrapperMap.put(Short.TYPE, Short.class);
    wrapperMap.put(Character.TYPE, Character.class);
    wrapperMap.put(Integer.TYPE, Integer.class);
    wrapperMap.put(Long.TYPE, Long.class);
    wrapperMap.put(Float.TYPE, Float.class);
    wrapperMap.put(Double.TYPE, Double.class);
    wrapperMap.put(Boolean.class, Boolean.TYPE);
    wrapperMap.put(Byte.class, Byte.TYPE);
    wrapperMap.put(Short.class, Short.TYPE);
    wrapperMap.put(Character.class, Character.TYPE);
    wrapperMap.put(Integer.class, Integer.TYPE);
    wrapperMap.put(Long.class, Long.TYPE);
    wrapperMap.put(Float.class, Float.TYPE);
    wrapperMap.put(Double.class, Double.TYPE);
  }
  
  private static class Special
    implements Serializable
  {
    public static final Special NULL_VALUE = new Special();
    public static final Special VOID_TYPE = new Special();
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/Primitive.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */