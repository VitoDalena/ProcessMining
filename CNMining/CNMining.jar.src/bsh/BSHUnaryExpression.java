package bsh;

class BSHUnaryExpression
  extends SimpleNode
  implements ParserConstants
{
  public int kind;
  public boolean postfix = false;
  
  BSHUnaryExpression(int paramInt)
  {
    super(paramInt);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    SimpleNode localSimpleNode = (SimpleNode)jjtGetChild(0);
    try
    {
      if ((this.kind == 100) || (this.kind == 101))
      {
        LHS localLHS = ((BSHPrimaryExpression)localSimpleNode).toLHS(paramCallStack, paramInterpreter);
        return lhsUnaryOperation(localLHS, paramInterpreter.getStrictJava());
      }
      return unaryOperation(localSimpleNode.eval(paramCallStack, paramInterpreter), this.kind);
    }
    catch (UtilEvalError localUtilEvalError)
    {
      throw localUtilEvalError.toEvalError(this, paramCallStack);
    }
  }
  
  private Object lhsUnaryOperation(LHS paramLHS, boolean paramBoolean)
    throws UtilEvalError
  {
    if (Interpreter.DEBUG) {
      Interpreter.debug("lhsUnaryOperation");
    }
    Object localObject1 = paramLHS.getValue();
    Object localObject2 = unaryOperation(localObject1, this.kind);
    Object localObject3;
    if (this.postfix) {
      localObject3 = localObject1;
    } else {
      localObject3 = localObject2;
    }
    paramLHS.assign(localObject2, paramBoolean);
    return localObject3;
  }
  
  private Object unaryOperation(Object paramObject, int paramInt)
    throws UtilEvalError
  {
    if (((paramObject instanceof Boolean)) || ((paramObject instanceof Character)) || ((paramObject instanceof Number))) {
      return primitiveWrapperUnaryOperation(paramObject, paramInt);
    }
    if (!(paramObject instanceof Primitive)) {
      throw new UtilEvalError("Unary operation " + ParserConstants.tokenImage[paramInt] + " inappropriate for object");
    }
    return Primitive.unaryOperation((Primitive)paramObject, paramInt);
  }
  
  private Object primitiveWrapperUnaryOperation(Object paramObject, int paramInt)
    throws UtilEvalError
  {
    Class localClass = paramObject.getClass();
    Object localObject = Primitive.promoteToInteger(paramObject);
    if ((localObject instanceof Boolean)) {
      return new Boolean(Primitive.booleanUnaryOperation((Boolean)localObject, paramInt));
    }
    if ((localObject instanceof Integer))
    {
      int i = Primitive.intUnaryOperation((Integer)localObject, paramInt);
      if ((paramInt == 100) || (paramInt == 101))
      {
        if (localClass == Byte.TYPE) {
          return new Byte((byte)i);
        }
        if (localClass == Short.TYPE) {
          return new Short((short)i);
        }
        if (localClass == Character.TYPE) {
          return new Character((char)i);
        }
      }
      return new Integer(i);
    }
    if ((localObject instanceof Long)) {
      return new Long(Primitive.longUnaryOperation((Long)localObject, paramInt));
    }
    if ((localObject instanceof Float)) {
      return new Float(Primitive.floatUnaryOperation((Float)localObject, paramInt));
    }
    if ((localObject instanceof Double)) {
      return new Double(Primitive.doubleUnaryOperation((Double)localObject, paramInt));
    }
    throw new InterpreterError("An error occurred.  Please call technical support.");
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHUnaryExpression.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */