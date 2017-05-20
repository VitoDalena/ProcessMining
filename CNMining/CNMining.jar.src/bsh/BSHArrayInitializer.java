package bsh;

import java.lang.reflect.Array;

class BSHArrayInitializer
  extends SimpleNode
{
  BSHArrayInitializer(int paramInt)
  {
    super(paramInt);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    throw new EvalError("Array initializer has no base type.", this, paramCallStack);
  }
  
  public Object eval(Class paramClass, int paramInt, CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    int i = jjtGetNumChildren();
    int[] arrayOfInt = new int[paramInt];
    arrayOfInt[0] = i;
    Object localObject1 = Array.newInstance(paramClass, arrayOfInt);
    for (int j = 0; j < i; j++)
    {
      SimpleNode localSimpleNode = (SimpleNode)jjtGetChild(j);
      Object localObject2;
      if ((localSimpleNode instanceof BSHArrayInitializer))
      {
        if (paramInt < 2) {
          throw new EvalError("Invalid Location for Intializer, position: " + j, this, paramCallStack);
        }
        localObject2 = ((BSHArrayInitializer)localSimpleNode).eval(paramClass, paramInt - 1, paramCallStack, paramInterpreter);
      }
      else
      {
        localObject2 = localSimpleNode.eval(paramCallStack, paramInterpreter);
      }
      if (localObject2 == Primitive.VOID) {
        throw new EvalError("Void in array initializer, position" + j, this, paramCallStack);
      }
      Object localObject3 = localObject2;
      if (paramInt == 1)
      {
        try
        {
          localObject3 = Types.castObject(localObject2, paramClass, 0);
        }
        catch (UtilEvalError localUtilEvalError)
        {
          throw localUtilEvalError.toEvalError("Error in array initializer", this, paramCallStack);
        }
        localObject3 = Primitive.unwrap(localObject3);
      }
      try
      {
        Array.set(localObject1, j, localObject3);
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        Interpreter.debug("illegal arg" + localIllegalArgumentException);
        throwTypeError(paramClass, localObject2, j, paramCallStack);
      }
      catch (ArrayStoreException localArrayStoreException)
      {
        Interpreter.debug("arraystore" + localArrayStoreException);
        throwTypeError(paramClass, localObject2, j, paramCallStack);
      }
    }
    return localObject1;
  }
  
  private void throwTypeError(Class paramClass, Object paramObject, int paramInt, CallStack paramCallStack)
    throws EvalError
  {
    String str;
    if ((paramObject instanceof Primitive)) {
      str = ((Primitive)paramObject).getType().getName();
    } else {
      str = Reflect.normalizeClassName(paramObject.getClass());
    }
    throw new EvalError("Incompatible type: " + str + " in initializer of array type: " + paramClass + " at position: " + paramInt, this, paramCallStack);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHArrayInitializer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */