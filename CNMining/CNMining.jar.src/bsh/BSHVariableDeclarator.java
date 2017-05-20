package bsh;

class BSHVariableDeclarator
  extends SimpleNode
{
  public String name;
  
  BSHVariableDeclarator(int paramInt)
  {
    super(paramInt);
  }
  
  public Object eval(BSHType paramBSHType, CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    Object localObject = null;
    if (jjtGetNumChildren() > 0)
    {
      SimpleNode localSimpleNode = (SimpleNode)jjtGetChild(0);
      if ((paramBSHType != null) && ((localSimpleNode instanceof BSHArrayInitializer))) {
        localObject = ((BSHArrayInitializer)localSimpleNode).eval(paramBSHType.getBaseType(), paramBSHType.getArrayDims(), paramCallStack, paramInterpreter);
      } else {
        localObject = localSimpleNode.eval(paramCallStack, paramInterpreter);
      }
    }
    if (localObject == Primitive.VOID) {
      throw new EvalError("Void initializer.", this, paramCallStack);
    }
    return localObject;
  }
  
  public String toString()
  {
    return "BSHVariableDeclarator " + this.name;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHVariableDeclarator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */