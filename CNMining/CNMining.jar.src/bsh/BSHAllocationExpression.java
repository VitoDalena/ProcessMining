package bsh;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;

class BSHAllocationExpression
  extends SimpleNode
{
  private static int innerClassCount = 0;
  
  BSHAllocationExpression(int paramInt)
  {
    super(paramInt);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    SimpleNode localSimpleNode1 = (SimpleNode)jjtGetChild(0);
    SimpleNode localSimpleNode2 = (SimpleNode)jjtGetChild(1);
    if ((localSimpleNode1 instanceof BSHAmbiguousName))
    {
      BSHAmbiguousName localBSHAmbiguousName = (BSHAmbiguousName)localSimpleNode1;
      if ((localSimpleNode2 instanceof BSHArguments)) {
        return objectAllocation(localBSHAmbiguousName, (BSHArguments)localSimpleNode2, paramCallStack, paramInterpreter);
      }
      return objectArrayAllocation(localBSHAmbiguousName, (BSHArrayDimensions)localSimpleNode2, paramCallStack, paramInterpreter);
    }
    return primitiveArrayAllocation((BSHPrimitiveType)localSimpleNode1, (BSHArrayDimensions)localSimpleNode2, paramCallStack, paramInterpreter);
  }
  
  private Object objectAllocation(BSHAmbiguousName paramBSHAmbiguousName, BSHArguments paramBSHArguments, CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    NameSpace localNameSpace = paramCallStack.top();
    Object[] arrayOfObject = paramBSHArguments.getArguments(paramCallStack, paramInterpreter);
    if (arrayOfObject == null) {
      throw new EvalError("Null args in new.", this, paramCallStack);
    }
    Object localObject = paramBSHAmbiguousName.toObject(paramCallStack, paramInterpreter, false);
    localObject = paramBSHAmbiguousName.toObject(paramCallStack, paramInterpreter, true);
    Class localClass = null;
    if ((localObject instanceof ClassIdentifier)) {
      localClass = ((ClassIdentifier)localObject).getTargetClass();
    } else {
      throw new EvalError("Unknown class: " + paramBSHAmbiguousName.text, this, paramCallStack);
    }
    int i = jjtGetNumChildren() > 2 ? 1 : 0;
    if (i != 0)
    {
      BSHBlock localBSHBlock = (BSHBlock)jjtGetChild(2);
      if (localClass.isInterface()) {
        return constructWithInterfaceBody(localClass, arrayOfObject, localBSHBlock, paramCallStack, paramInterpreter);
      }
      return constructWithClassBody(localClass, arrayOfObject, localBSHBlock, paramCallStack, paramInterpreter);
    }
    return constructObject(localClass, arrayOfObject, paramCallStack);
  }
  
  private Object constructObject(Class paramClass, Object[] paramArrayOfObject, CallStack paramCallStack)
    throws EvalError
  {
    Object localObject;
    try
    {
      localObject = Reflect.constructObject(paramClass, paramArrayOfObject);
    }
    catch (ReflectError localReflectError)
    {
      throw new EvalError("Constructor error: " + localReflectError.getMessage(), this, paramCallStack);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      Interpreter.debug("The constructor threw an exception:\n\t" + localInvocationTargetException.getTargetException());
      throw new TargetError("Object constructor", localInvocationTargetException.getTargetException(), this, paramCallStack, true);
    }
    String str = paramClass.getName();
    if (str.indexOf("$") == -1) {
      return localObject;
    }
    This localThis = paramCallStack.top().getThis(null);
    NameSpace localNameSpace = Name.getClassNameSpace(localThis.getNameSpace());
    if ((localNameSpace != null) && (str.startsWith(localNameSpace.getName() + "$"))) {
      try
      {
        ClassGenerator.getClassGenerator().setInstanceNameSpaceParent(localObject, str, localNameSpace);
      }
      catch (UtilEvalError localUtilEvalError)
      {
        throw localUtilEvalError.toEvalError(this, paramCallStack);
      }
    }
    return localObject;
  }
  
  private Object constructWithClassBody(Class paramClass, Object[] paramArrayOfObject, BSHBlock paramBSHBlock, CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    String str = paramCallStack.top().getName() + "$" + ++innerClassCount;
    Modifiers localModifiers = new Modifiers();
    localModifiers.addModifier(0, "public");
    Class localClass;
    try
    {
      localClass = ClassGenerator.getClassGenerator().generateClass(str, localModifiers, null, paramClass, paramBSHBlock, false, paramCallStack, paramInterpreter);
    }
    catch (UtilEvalError localUtilEvalError)
    {
      throw localUtilEvalError.toEvalError(this, paramCallStack);
    }
    try
    {
      return Reflect.constructObject(localClass, paramArrayOfObject);
    }
    catch (Exception localException1)
    {
      Exception localException2;
      if ((localException1 instanceof InvocationTargetException)) {
        localException2 = (Exception)((InvocationTargetException)localException1).getTargetException();
      }
      throw new EvalError("Error constructing inner class instance: " + localException2, this, paramCallStack);
    }
  }
  
  private Object constructWithInterfaceBody(Class paramClass, Object[] paramArrayOfObject, BSHBlock paramBSHBlock, CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    NameSpace localNameSpace1 = paramCallStack.top();
    NameSpace localNameSpace2 = new NameSpace(localNameSpace1, "AnonymousBlock");
    paramCallStack.push(localNameSpace2);
    paramBSHBlock.eval(paramCallStack, paramInterpreter, true);
    paramCallStack.pop();
    localNameSpace2.importStatic(paramClass);
    try
    {
      return localNameSpace2.getThis(paramInterpreter).getInterface(paramClass);
    }
    catch (UtilEvalError localUtilEvalError)
    {
      throw localUtilEvalError.toEvalError(this, paramCallStack);
    }
  }
  
  private Object objectArrayAllocation(BSHAmbiguousName paramBSHAmbiguousName, BSHArrayDimensions paramBSHArrayDimensions, CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    NameSpace localNameSpace = paramCallStack.top();
    Class localClass = paramBSHAmbiguousName.toClass(paramCallStack, paramInterpreter);
    if (localClass == null) {
      throw new EvalError("Class " + paramBSHAmbiguousName.getName(localNameSpace) + " not found.", this, paramCallStack);
    }
    return arrayAllocation(paramBSHArrayDimensions, localClass, paramCallStack, paramInterpreter);
  }
  
  private Object primitiveArrayAllocation(BSHPrimitiveType paramBSHPrimitiveType, BSHArrayDimensions paramBSHArrayDimensions, CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    Class localClass = paramBSHPrimitiveType.getType();
    return arrayAllocation(paramBSHArrayDimensions, localClass, paramCallStack, paramInterpreter);
  }
  
  private Object arrayAllocation(BSHArrayDimensions paramBSHArrayDimensions, Class paramClass, CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    Object localObject = paramBSHArrayDimensions.eval(paramClass, paramCallStack, paramInterpreter);
    if (localObject != Primitive.VOID) {
      return localObject;
    }
    return arrayNewInstance(paramClass, paramBSHArrayDimensions, paramCallStack);
  }
  
  private Object arrayNewInstance(Class paramClass, BSHArrayDimensions paramBSHArrayDimensions, CallStack paramCallStack)
    throws EvalError
  {
    if (paramBSHArrayDimensions.numUndefinedDims > 0)
    {
      Object localObject = Array.newInstance(paramClass, new int[paramBSHArrayDimensions.numUndefinedDims]);
      paramClass = localObject.getClass();
    }
    try
    {
      return Array.newInstance(paramClass, paramBSHArrayDimensions.definedDimensions);
    }
    catch (NegativeArraySizeException localNegativeArraySizeException)
    {
      throw new TargetError(localNegativeArraySizeException, this, paramCallStack);
    }
    catch (Exception localException)
    {
      throw new EvalError("Can't construct primitive array: " + localException.getMessage(), this, paramCallStack);
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHAllocationExpression.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */