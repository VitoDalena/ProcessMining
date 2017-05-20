package bsh;

import java.lang.reflect.InvocationTargetException;

class BSHPrimarySuffix
  extends SimpleNode
{
  public static final int CLASS = 0;
  public static final int INDEX = 1;
  public static final int NAME = 2;
  public static final int PROPERTY = 3;
  public int operation;
  Object index;
  public String field;
  
  BSHPrimarySuffix(int paramInt)
  {
    super(paramInt);
  }
  
  public Object doSuffix(Object paramObject, boolean paramBoolean, CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    if (this.operation == 0)
    {
      if ((paramObject instanceof BSHType))
      {
        if (paramBoolean) {
          throw new EvalError("Can't assign .class", this, paramCallStack);
        }
        NameSpace localNameSpace = paramCallStack.top();
        return ((BSHType)paramObject).getType(paramCallStack, paramInterpreter);
      }
      throw new EvalError("Attempt to use .class suffix on non class.", this, paramCallStack);
    }
    if ((paramObject instanceof SimpleNode))
    {
      if ((paramObject instanceof BSHAmbiguousName)) {
        paramObject = ((BSHAmbiguousName)paramObject).toObject(paramCallStack, paramInterpreter);
      } else {
        paramObject = ((SimpleNode)paramObject).eval(paramCallStack, paramInterpreter);
      }
    }
    else if ((paramObject instanceof LHS)) {
      try
      {
        paramObject = ((LHS)paramObject).getValue();
      }
      catch (UtilEvalError localUtilEvalError)
      {
        throw localUtilEvalError.toEvalError(this, paramCallStack);
      }
    }
    try
    {
      switch (this.operation)
      {
      case 1: 
        return doIndex(paramObject, paramBoolean, paramCallStack, paramInterpreter);
      case 2: 
        return doName(paramObject, paramBoolean, paramCallStack, paramInterpreter);
      case 3: 
        return doProperty(paramBoolean, paramObject, paramCallStack, paramInterpreter);
      }
      throw new InterpreterError("Unknown suffix type");
    }
    catch (ReflectError localReflectError)
    {
      throw new EvalError("reflection error: " + localReflectError, this, paramCallStack);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new TargetError("target exception", localInvocationTargetException.getTargetException(), this, paramCallStack, true);
    }
  }
  
  /* Error */
  private Object doName(Object paramObject, boolean paramBoolean, CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError, ReflectError, InvocationTargetException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 36	bsh/BSHPrimarySuffix:field	Ljava/lang/String;
    //   4: ldc 37
    //   6: invokevirtual 38	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   9: ifeq +41 -> 50
    //   12: aload_1
    //   13: invokevirtual 39	java/lang/Object:getClass	()Ljava/lang/Class;
    //   16: invokevirtual 40	java/lang/Class:isArray	()Z
    //   19: ifeq +31 -> 50
    //   22: iload_2
    //   23: ifeq +15 -> 38
    //   26: new 4	bsh/EvalError
    //   29: dup
    //   30: ldc 41
    //   32: aload_0
    //   33: aload_3
    //   34: invokespecial 6	bsh/EvalError:<init>	(Ljava/lang/String;Lbsh/SimpleNode;Lbsh/CallStack;)V
    //   37: athrow
    //   38: new 42	bsh/Primitive
    //   41: dup
    //   42: aload_1
    //   43: invokestatic 43	java/lang/reflect/Array:getLength	(Ljava/lang/Object;)I
    //   46: invokespecial 44	bsh/Primitive:<init>	(I)V
    //   49: areturn
    //   50: aload_0
    //   51: invokevirtual 45	bsh/BSHPrimarySuffix:jjtGetNumChildren	()I
    //   54: ifne +25 -> 79
    //   57: iload_2
    //   58: ifeq +12 -> 70
    //   61: aload_1
    //   62: aload_0
    //   63: getfield 36	bsh/BSHPrimarySuffix:field	Ljava/lang/String;
    //   66: invokestatic 46	bsh/Reflect:getLHSObjectField	(Ljava/lang/Object;Ljava/lang/String;)Lbsh/LHS;
    //   69: areturn
    //   70: aload_1
    //   71: aload_0
    //   72: getfield 36	bsh/BSHPrimarySuffix:field	Ljava/lang/String;
    //   75: invokestatic 47	bsh/Reflect:getObjectFieldValue	(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   78: areturn
    //   79: aload_0
    //   80: iconst_0
    //   81: invokevirtual 48	bsh/BSHPrimarySuffix:jjtGetChild	(I)Lbsh/Node;
    //   84: checkcast 49	bsh/BSHArguments
    //   87: aload_3
    //   88: aload 4
    //   90: invokevirtual 50	bsh/BSHArguments:getArguments	(Lbsh/CallStack;Lbsh/Interpreter;)[Ljava/lang/Object;
    //   93: astore 5
    //   95: aload_1
    //   96: aload_0
    //   97: getfield 36	bsh/BSHPrimarySuffix:field	Ljava/lang/String;
    //   100: aload 5
    //   102: aload 4
    //   104: aload_3
    //   105: aload_0
    //   106: invokestatic 51	bsh/Reflect:invokeObjectMethod	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;Lbsh/Interpreter;Lbsh/CallStack;Lbsh/SimpleNode;)Ljava/lang/Object;
    //   109: areturn
    //   110: astore 6
    //   112: new 4	bsh/EvalError
    //   115: dup
    //   116: new 25	java/lang/StringBuffer
    //   119: dup
    //   120: invokespecial 26	java/lang/StringBuffer:<init>	()V
    //   123: ldc 52
    //   125: invokevirtual 28	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   128: aload 6
    //   130: invokevirtual 53	bsh/ReflectError:getMessage	()Ljava/lang/String;
    //   133: invokevirtual 28	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   136: invokevirtual 30	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   139: aload_0
    //   140: aload_3
    //   141: invokespecial 6	bsh/EvalError:<init>	(Ljava/lang/String;Lbsh/SimpleNode;Lbsh/CallStack;)V
    //   144: athrow
    //   145: astore 7
    //   147: new 25	java/lang/StringBuffer
    //   150: dup
    //   151: invokespecial 26	java/lang/StringBuffer:<init>	()V
    //   154: ldc 54
    //   156: invokevirtual 28	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   159: aload_0
    //   160: getfield 36	bsh/BSHPrimarySuffix:field	Ljava/lang/String;
    //   163: invokevirtual 28	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   166: invokevirtual 30	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   169: astore 8
    //   171: aload 7
    //   173: invokevirtual 34	java/lang/reflect/InvocationTargetException:getTargetException	()Ljava/lang/Throwable;
    //   176: astore 9
    //   178: iconst_1
    //   179: istore 10
    //   181: aload 9
    //   183: instanceof 4
    //   186: ifeq +27 -> 213
    //   189: aload 9
    //   191: instanceof 32
    //   194: ifeq +16 -> 210
    //   197: aload 9
    //   199: checkcast 32	bsh/TargetError
    //   202: invokevirtual 55	bsh/TargetError:inNativeCode	()Z
    //   205: istore 10
    //   207: goto +6 -> 213
    //   210: iconst_0
    //   211: istore 10
    //   213: new 32	bsh/TargetError
    //   216: dup
    //   217: aload 8
    //   219: aload 9
    //   221: aload_0
    //   222: aload_3
    //   223: iload 10
    //   225: invokespecial 35	bsh/TargetError:<init>	(Ljava/lang/String;Ljava/lang/Throwable;Lbsh/SimpleNode;Lbsh/CallStack;Z)V
    //   228: athrow
    //   229: astore 5
    //   231: aload 5
    //   233: aload_0
    //   234: aload_3
    //   235: invokevirtual 17	bsh/UtilEvalError:toEvalError	(Lbsh/SimpleNode;Lbsh/CallStack;)Lbsh/EvalError;
    //   238: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	239	0	this	BSHPrimarySuffix
    //   0	239	1	paramObject	Object
    //   0	239	2	paramBoolean	boolean
    //   0	239	3	paramCallStack	CallStack
    //   0	239	4	paramInterpreter	Interpreter
    //   93	8	5	arrayOfObject	Object[]
    //   229	3	5	localUtilEvalError	UtilEvalError
    //   110	19	6	localReflectError	ReflectError
    //   145	27	7	localInvocationTargetException	InvocationTargetException
    //   169	49	8	str	String
    //   176	44	9	localThrowable	Throwable
    //   179	45	10	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   95	109	110	bsh/ReflectError
    //   95	109	145	java/lang/reflect/InvocationTargetException
    //   0	49	229	bsh/UtilEvalError
    //   50	69	229	bsh/UtilEvalError
    //   70	78	229	bsh/UtilEvalError
    //   79	109	229	bsh/UtilEvalError
    //   110	229	229	bsh/UtilEvalError
  }
  
  static int getIndexAux(Object paramObject, CallStack paramCallStack, Interpreter paramInterpreter, SimpleNode paramSimpleNode)
    throws EvalError
  {
    if (!paramObject.getClass().isArray()) {
      throw new EvalError("Not an array", paramSimpleNode, paramCallStack);
    }
    int i;
    try
    {
      Object localObject = ((SimpleNode)paramSimpleNode.jjtGetChild(0)).eval(paramCallStack, paramInterpreter);
      if (!(localObject instanceof Primitive)) {
        localObject = Types.castObject(localObject, Integer.TYPE, 1);
      }
      i = ((Primitive)localObject).intValue();
    }
    catch (UtilEvalError localUtilEvalError)
    {
      Interpreter.debug("doIndex: " + localUtilEvalError);
      throw localUtilEvalError.toEvalError("Arrays may only be indexed by integer types.", paramSimpleNode, paramCallStack);
    }
    return i;
  }
  
  private Object doIndex(Object paramObject, boolean paramBoolean, CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError, ReflectError
  {
    int i = getIndexAux(paramObject, paramCallStack, paramInterpreter, this);
    if (paramBoolean) {
      return new LHS(paramObject, i);
    }
    try
    {
      return Reflect.getIndex(paramObject, i);
    }
    catch (UtilEvalError localUtilEvalError)
    {
      throw localUtilEvalError.toEvalError(this, paramCallStack);
    }
  }
  
  private Object doProperty(boolean paramBoolean, Object paramObject, CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    if (paramObject == Primitive.VOID) {
      throw new EvalError("Attempt to access property on undefined variable or class name", this, paramCallStack);
    }
    if ((paramObject instanceof Primitive)) {
      throw new EvalError("Attempt to access property on a primitive", this, paramCallStack);
    }
    Object localObject1 = ((SimpleNode)jjtGetChild(0)).eval(paramCallStack, paramInterpreter);
    if (!(localObject1 instanceof String)) {
      throw new EvalError("Property expression must be a String or identifier.", this, paramCallStack);
    }
    if (paramBoolean) {
      return new LHS(paramObject, (String)localObject1);
    }
    CollectionManager localCollectionManager = CollectionManager.getCollectionManager();
    if (localCollectionManager.isMap(paramObject))
    {
      Object localObject2 = localCollectionManager.getFromMap(paramObject, localObject1);
      return localObject2 == null ? (localObject2 = Primitive.NULL) : localObject2;
    }
    try
    {
      return Reflect.getObjectProperty(paramObject, (String)localObject1);
    }
    catch (UtilEvalError localUtilEvalError)
    {
      throw localUtilEvalError.toEvalError("Property: " + localObject1, this, paramCallStack);
    }
    catch (ReflectError localReflectError)
    {
      throw new EvalError("No such property: " + localObject1, this, paramCallStack);
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHPrimarySuffix.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */