package bsh;

import java.io.Serializable;
import java.lang.reflect.Method;

public class BshMethod
  implements Serializable
{
  NameSpace declaringNameSpace;
  Modifiers modifiers;
  private String name;
  private Class creturnType;
  private String[] paramNames;
  private int numArgs;
  private Class[] cparamTypes;
  BSHBlock methodBody;
  private Method javaMethod;
  private Object javaObject;
  
  BshMethod(BSHMethodDeclaration paramBSHMethodDeclaration, NameSpace paramNameSpace, Modifiers paramModifiers)
  {
    this(paramBSHMethodDeclaration.name, paramBSHMethodDeclaration.returnType, paramBSHMethodDeclaration.paramsNode.getParamNames(), paramBSHMethodDeclaration.paramsNode.paramTypes, paramBSHMethodDeclaration.blockNode, paramNameSpace, paramModifiers);
  }
  
  BshMethod(String paramString, Class paramClass, String[] paramArrayOfString, Class[] paramArrayOfClass, BSHBlock paramBSHBlock, NameSpace paramNameSpace, Modifiers paramModifiers)
  {
    this.name = paramString;
    this.creturnType = paramClass;
    this.paramNames = paramArrayOfString;
    if (paramArrayOfString != null) {
      this.numArgs = paramArrayOfString.length;
    }
    this.cparamTypes = paramArrayOfClass;
    this.methodBody = paramBSHBlock;
    this.declaringNameSpace = paramNameSpace;
    this.modifiers = paramModifiers;
  }
  
  BshMethod(Method paramMethod, Object paramObject)
  {
    this(paramMethod.getName(), paramMethod.getReturnType(), null, paramMethod.getParameterTypes(), null, null, null);
    this.javaMethod = paramMethod;
    this.javaObject = paramObject;
  }
  
  public Class[] getParameterTypes()
  {
    return this.cparamTypes;
  }
  
  public String[] getParameterNames()
  {
    return this.paramNames;
  }
  
  public Class getReturnType()
  {
    return this.creturnType;
  }
  
  public Modifiers getModifiers()
  {
    return this.modifiers;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public Object invoke(Object[] paramArrayOfObject, Interpreter paramInterpreter)
    throws EvalError
  {
    return invoke(paramArrayOfObject, paramInterpreter, null, null, false);
  }
  
  public Object invoke(Object[] paramArrayOfObject, Interpreter paramInterpreter, CallStack paramCallStack, SimpleNode paramSimpleNode)
    throws EvalError
  {
    return invoke(paramArrayOfObject, paramInterpreter, paramCallStack, paramSimpleNode, false);
  }
  
  /* Error */
  Object invoke(Object[] paramArrayOfObject, Interpreter paramInterpreter, CallStack paramCallStack, SimpleNode paramSimpleNode, boolean paramBoolean)
    throws EvalError
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +36 -> 37
    //   4: iconst_0
    //   5: istore 6
    //   7: goto +23 -> 30
    //   10: aload_1
    //   11: iload 6
    //   13: aaload
    //   14: ifnonnull +13 -> 27
    //   17: new 23	java/lang/Error
    //   20: dup
    //   21: ldc 24
    //   23: invokespecial 25	java/lang/Error:<init>	(Ljava/lang/String;)V
    //   26: athrow
    //   27: iinc 6 1
    //   30: iload 6
    //   32: aload_1
    //   33: arraylength
    //   34: if_icmplt -24 -> 10
    //   37: aload_0
    //   38: getfield 20	bsh/BshMethod:javaMethod	Ljava/lang/reflect/Method;
    //   41: ifnull +67 -> 108
    //   44: aload_0
    //   45: getfield 20	bsh/BshMethod:javaMethod	Ljava/lang/reflect/Method;
    //   48: aload_0
    //   49: getfield 21	bsh/BshMethod:javaObject	Ljava/lang/Object;
    //   52: aload_1
    //   53: invokestatic 26	bsh/Reflect:invokeMethod	(Ljava/lang/reflect/Method;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   56: areturn
    //   57: astore 7
    //   59: new 28	bsh/EvalError
    //   62: dup
    //   63: new 29	java/lang/StringBuffer
    //   66: dup
    //   67: invokespecial 30	java/lang/StringBuffer:<init>	()V
    //   70: ldc 31
    //   72: invokevirtual 32	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   75: aload 7
    //   77: invokevirtual 33	java/lang/StringBuffer:append	(Ljava/lang/Object;)Ljava/lang/StringBuffer;
    //   80: invokevirtual 34	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   83: aload 4
    //   85: aload_3
    //   86: invokespecial 35	bsh/EvalError:<init>	(Ljava/lang/String;Lbsh/SimpleNode;Lbsh/CallStack;)V
    //   89: athrow
    //   90: astore 8
    //   92: new 37	bsh/TargetError
    //   95: dup
    //   96: ldc 38
    //   98: aload 8
    //   100: aload 4
    //   102: aload_3
    //   103: iconst_1
    //   104: invokespecial 39	bsh/TargetError:<init>	(Ljava/lang/String;Ljava/lang/Throwable;Lbsh/SimpleNode;Lbsh/CallStack;Z)V
    //   107: athrow
    //   108: aload_0
    //   109: getfield 16	bsh/BshMethod:modifiers	Lbsh/Modifiers;
    //   112: ifnull +88 -> 200
    //   115: aload_0
    //   116: getfield 16	bsh/BshMethod:modifiers	Lbsh/Modifiers;
    //   119: ldc 40
    //   121: invokevirtual 41	bsh/Modifiers:hasModifier	(Ljava/lang/String;)Z
    //   124: ifeq +76 -> 200
    //   127: aload_0
    //   128: getfield 15	bsh/BshMethod:declaringNameSpace	Lbsh/NameSpace;
    //   131: getfield 42	bsh/NameSpace:isClass	Z
    //   134: ifeq +27 -> 161
    //   137: aload_0
    //   138: getfield 15	bsh/BshMethod:declaringNameSpace	Lbsh/NameSpace;
    //   141: invokevirtual 43	bsh/NameSpace:getClassInstance	()Ljava/lang/Object;
    //   144: astore 7
    //   146: goto +25 -> 171
    //   149: astore 8
    //   151: new 45	bsh/InterpreterError
    //   154: dup
    //   155: ldc 46
    //   157: invokespecial 47	bsh/InterpreterError:<init>	(Ljava/lang/String;)V
    //   160: athrow
    //   161: aload_0
    //   162: getfield 15	bsh/BshMethod:declaringNameSpace	Lbsh/NameSpace;
    //   165: aload_2
    //   166: invokevirtual 48	bsh/NameSpace:getThis	(Lbsh/Interpreter;)Lbsh/This;
    //   169: astore 7
    //   171: aload 7
    //   173: dup
    //   174: astore 8
    //   176: monitorenter
    //   177: aload_0
    //   178: aload_1
    //   179: aload_2
    //   180: aload_3
    //   181: aload 4
    //   183: iload 5
    //   185: invokespecial 49	bsh/BshMethod:invokeImpl	([Ljava/lang/Object;Lbsh/Interpreter;Lbsh/CallStack;Lbsh/SimpleNode;Z)Ljava/lang/Object;
    //   188: aload 8
    //   190: monitorexit
    //   191: areturn
    //   192: astore 9
    //   194: aload 8
    //   196: monitorexit
    //   197: aload 9
    //   199: athrow
    //   200: aload_0
    //   201: aload_1
    //   202: aload_2
    //   203: aload_3
    //   204: aload 4
    //   206: iload 5
    //   208: invokespecial 49	bsh/BshMethod:invokeImpl	([Ljava/lang/Object;Lbsh/Interpreter;Lbsh/CallStack;Lbsh/SimpleNode;Z)Ljava/lang/Object;
    //   211: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	212	0	this	BshMethod
    //   0	212	1	paramArrayOfObject	Object[]
    //   0	212	2	paramInterpreter	Interpreter
    //   0	212	3	paramCallStack	CallStack
    //   0	212	4	paramSimpleNode	SimpleNode
    //   0	212	5	paramBoolean	boolean
    //   5	30	6	i	int
    //   57	19	7	localReflectError	ReflectError
    //   144	28	7	localObject1	Object
    //   90	9	8	localInvocationTargetException	java.lang.reflect.InvocationTargetException
    //   149	1	8	localUtilEvalError	UtilEvalError
    //   174	21	8	Ljava/lang/Object;	Object
    //   192	6	9	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   44	56	57	bsh/ReflectError
    //   44	56	90	java/lang/reflect/InvocationTargetException
    //   137	146	149	bsh/UtilEvalError
    //   177	191	192	finally
    //   192	197	192	finally
  }
  
  private Object invokeImpl(Object[] paramArrayOfObject, Interpreter paramInterpreter, CallStack paramCallStack, SimpleNode paramSimpleNode, boolean paramBoolean)
    throws EvalError
  {
    Class localClass = getReturnType();
    Class[] arrayOfClass = getParameterTypes();
    if (paramCallStack == null) {
      paramCallStack = new CallStack(this.declaringNameSpace);
    }
    if (paramArrayOfObject == null) {
      paramArrayOfObject = new Object[0];
    }
    if (paramArrayOfObject.length != this.numArgs) {
      throw new EvalError("Wrong number of arguments for local method: " + this.name, paramSimpleNode, paramCallStack);
    }
    NameSpace localNameSpace;
    if (paramBoolean)
    {
      localNameSpace = paramCallStack.top();
    }
    else
    {
      localNameSpace = new NameSpace(this.declaringNameSpace, this.name);
      localNameSpace.isMethod = true;
    }
    localNameSpace.setNode(paramSimpleNode);
    for (int i = 0; i < this.numArgs; i++) {
      if (arrayOfClass[i] != null)
      {
        try
        {
          paramArrayOfObject[i] = Types.castObject(paramArrayOfObject[i], arrayOfClass[i], 1);
        }
        catch (UtilEvalError localUtilEvalError1)
        {
          throw new EvalError("Invalid argument: `" + this.paramNames[i] + "'" + " for method: " + this.name + " : " + localUtilEvalError1.getMessage(), paramSimpleNode, paramCallStack);
        }
        try
        {
          localNameSpace.setTypedVariable(this.paramNames[i], arrayOfClass[i], paramArrayOfObject[i], null);
        }
        catch (UtilEvalError localUtilEvalError2)
        {
          throw localUtilEvalError2.toEvalError("Typed method parameter assignment", paramSimpleNode, paramCallStack);
        }
      }
      else
      {
        if (paramArrayOfObject[i] == Primitive.VOID) {
          throw new EvalError("Undefined variable or class name, parameter: " + this.paramNames[i] + " to method: " + this.name, paramSimpleNode, paramCallStack);
        }
        try
        {
          localNameSpace.setLocalVariable(this.paramNames[i], paramArrayOfObject[i], paramInterpreter.getStrictJava());
        }
        catch (UtilEvalError localUtilEvalError3)
        {
          throw localUtilEvalError3.toEvalError(paramSimpleNode, paramCallStack);
        }
      }
    }
    if (!paramBoolean) {
      paramCallStack.push(localNameSpace);
    }
    Object localObject = this.methodBody.eval(paramCallStack, paramInterpreter, true);
    CallStack localCallStack = paramCallStack.copy();
    if (!paramBoolean) {
      paramCallStack.pop();
    }
    ReturnControl localReturnControl = null;
    if ((localObject instanceof ReturnControl))
    {
      localReturnControl = (ReturnControl)localObject;
      if (localReturnControl.kind == 46) {
        localObject = ((ReturnControl)localObject).value;
      } else {
        throw new EvalError("'continue' or 'break' in method body", localReturnControl.returnPoint, localCallStack);
      }
      if ((localClass == Void.TYPE) && (localObject != Primitive.VOID)) {
        throw new EvalError("Cannot return value from void method", localReturnControl.returnPoint, localCallStack);
      }
    }
    if (localClass != null)
    {
      if (localClass == Void.TYPE) {
        return Primitive.VOID;
      }
      try
      {
        localObject = Types.castObject(localObject, localClass, 1);
      }
      catch (UtilEvalError localUtilEvalError4)
      {
        SimpleNode localSimpleNode = paramSimpleNode;
        if (localReturnControl != null) {
          localSimpleNode = localReturnControl.returnPoint;
        }
        throw localUtilEvalError4.toEvalError("Incorrect type returned from method: " + this.name + localUtilEvalError4.getMessage(), localSimpleNode, paramCallStack);
      }
    }
    return localObject;
  }
  
  public boolean hasModifier(String paramString)
  {
    return (this.modifiers != null) && (this.modifiers.hasModifier(paramString));
  }
  
  public String toString()
  {
    return "Scripted Method: " + StringUtil.methodString(this.name, getParameterTypes());
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BshMethod.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */