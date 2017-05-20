package bsh;

import java.lang.reflect.Array;

class BSHArrayDimensions
  extends SimpleNode
{
  public Class baseType;
  public int numDefinedDims;
  public int numUndefinedDims;
  public int[] definedDimensions;
  
  BSHArrayDimensions(int paramInt)
  {
    super(paramInt);
  }
  
  public void addDefinedDimension()
  {
    this.numDefinedDims += 1;
  }
  
  public void addUndefinedDimension()
  {
    this.numUndefinedDims += 1;
  }
  
  public Object eval(Class paramClass, CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    if (Interpreter.DEBUG) {
      Interpreter.debug("array base type = " + paramClass);
    }
    this.baseType = paramClass;
    return eval(paramCallStack, paramInterpreter);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    SimpleNode localSimpleNode = (SimpleNode)jjtGetChild(0);
    Object localObject2;
    if ((localSimpleNode instanceof BSHArrayInitializer))
    {
      if (this.baseType == null) {
        throw new EvalError("Internal Array Eval err:  unknown base type", this, paramCallStack);
      }
      Object localObject1 = ((BSHArrayInitializer)localSimpleNode).eval(this.baseType, this.numUndefinedDims, paramCallStack, paramInterpreter);
      localObject2 = localObject1.getClass();
      int j = Reflect.getArrayDimensions((Class)localObject2);
      this.definedDimensions = new int[j];
      if (this.definedDimensions.length != this.numUndefinedDims) {
        throw new EvalError("Incompatible initializer. Allocation calls for a " + this.numUndefinedDims + " dimensional array, but initializer is a " + j + " dimensional array", this, paramCallStack);
      }
      Object localObject3 = localObject1;
      for (int k = 0; k < this.definedDimensions.length; k++)
      {
        this.definedDimensions[k] = Array.getLength(localObject3);
        if (this.definedDimensions[k] > 0) {
          localObject3 = Array.get(localObject3, 0);
        }
      }
      return localObject1;
    }
    this.definedDimensions = new int[this.numDefinedDims];
    for (int i = 0; i < this.numDefinedDims; i++) {
      try
      {
        localObject2 = ((SimpleNode)jjtGetChild(i)).eval(paramCallStack, paramInterpreter);
        this.definedDimensions[i] = ((Primitive)localObject2).intValue();
      }
      catch (Exception localException)
      {
        throw new EvalError("Array index: " + i + " does not evaluate to an integer", this, paramCallStack);
      }
    }
    return Primitive.VOID;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHArrayDimensions.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */