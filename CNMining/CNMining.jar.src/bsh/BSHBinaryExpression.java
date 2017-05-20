package bsh;

class BSHBinaryExpression
  extends SimpleNode
  implements ParserConstants
{
  public int kind;
  
  BSHBinaryExpression(int paramInt)
  {
    super(paramInt);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    Object localObject1 = ((SimpleNode)jjtGetChild(0)).eval(paramCallStack, paramInterpreter);
    Object localObject2;
    if (this.kind == 35)
    {
      if (localObject1 == Primitive.NULL) {
        return new Primitive(false);
      }
      localObject2 = ((BSHType)jjtGetChild(1)).getType(paramCallStack, paramInterpreter);
      if ((localObject1 instanceof Primitive))
      {
        if (localObject2 == Primitive.class) {
          return new Primitive(true);
        }
        return new Primitive(false);
      }
      boolean bool2 = Types.isJavaBaseAssignable((Class)localObject2, localObject1.getClass());
      return new Primitive(bool2);
    }
    if ((this.kind == 98) || (this.kind == 99))
    {
      localObject2 = localObject1;
      if (isPrimitiveValue(localObject1)) {
        localObject2 = ((Primitive)localObject1).getValue();
      }
      if (((localObject2 instanceof Boolean)) && (!((Boolean)localObject2).booleanValue())) {
        return new Primitive(false);
      }
    }
    if ((this.kind == 96) || (this.kind == 97))
    {
      localObject2 = localObject1;
      if (isPrimitiveValue(localObject1)) {
        localObject2 = ((Primitive)localObject1).getValue();
      }
      if (((localObject2 instanceof Boolean)) && (((Boolean)localObject2).booleanValue() == true)) {
        return new Primitive(true);
      }
    }
    boolean bool1 = isWrapper(localObject1);
    Object localObject3 = ((SimpleNode)jjtGetChild(1)).eval(paramCallStack, paramInterpreter);
    boolean bool3 = isWrapper(localObject3);
    if (((bool1) || (isPrimitiveValue(localObject1))) && ((bool3) || (isPrimitiveValue(localObject3))) && ((!bool1) || (!bool3) || (this.kind != 90))) {
      try
      {
        return Primitive.binaryOperation(localObject1, localObject3, this.kind);
      }
      catch (UtilEvalError localUtilEvalError)
      {
        throw localUtilEvalError.toEvalError(this, paramCallStack);
      }
    }
    switch (this.kind)
    {
    case 90: 
      return new Primitive(localObject1 == localObject3);
    case 95: 
      return new Primitive(localObject1 != localObject3);
    case 102: 
      if (((localObject1 instanceof String)) || ((localObject3 instanceof String))) {
        return localObject1.toString() + localObject3.toString();
      }
      break;
    }
    if (((localObject1 instanceof Primitive)) || ((localObject3 instanceof Primitive)))
    {
      if ((localObject1 == Primitive.VOID) || (localObject3 == Primitive.VOID)) {
        throw new EvalError("illegal use of undefined variable, class, or 'void' literal", this, paramCallStack);
      }
      if ((localObject1 == Primitive.NULL) || (localObject3 == Primitive.NULL)) {
        throw new EvalError("illegal use of null value or 'null' literal", this, paramCallStack);
      }
    }
    throw new EvalError("Operator: '" + ParserConstants.tokenImage[this.kind] + "' inappropriate for objects", this, paramCallStack);
  }
  
  private boolean isPrimitiveValue(Object paramObject)
  {
    return ((paramObject instanceof Primitive)) && (paramObject != Primitive.VOID) && (paramObject != Primitive.NULL);
  }
  
  private boolean isWrapper(Object paramObject)
  {
    return ((paramObject instanceof Boolean)) || ((paramObject instanceof Character)) || ((paramObject instanceof Number));
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHBinaryExpression.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */