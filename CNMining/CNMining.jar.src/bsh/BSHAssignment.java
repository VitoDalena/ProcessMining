package bsh;

class BSHAssignment
  extends SimpleNode
  implements ParserConstants
{
  public int operator;
  
  BSHAssignment(int paramInt)
  {
    super(paramInt);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    BSHPrimaryExpression localBSHPrimaryExpression = (BSHPrimaryExpression)jjtGetChild(0);
    if (localBSHPrimaryExpression == null) {
      throw new InterpreterError("Error, null LHSnode");
    }
    boolean bool = paramInterpreter.getStrictJava();
    LHS localLHS = localBSHPrimaryExpression.toLHS(paramCallStack, paramInterpreter);
    if (localLHS == null) {
      throw new InterpreterError("Error, null LHS");
    }
    Object localObject1 = null;
    if (this.operator != 81) {
      try
      {
        localObject1 = localLHS.getValue();
      }
      catch (UtilEvalError localUtilEvalError1)
      {
        throw localUtilEvalError1.toEvalError(this, paramCallStack);
      }
    }
    SimpleNode localSimpleNode = (SimpleNode)jjtGetChild(1);
    Object localObject2 = localSimpleNode.eval(paramCallStack, paramInterpreter);
    if (localObject2 == Primitive.VOID) {
      throw new EvalError("Void assignment.", this, paramCallStack);
    }
    try
    {
      switch (this.operator)
      {
      case 81: 
        return localLHS.assign(localObject2, bool);
      case 118: 
        return localLHS.assign(operation(localObject1, localObject2, 102), bool);
      case 119: 
        return localLHS.assign(operation(localObject1, localObject2, 103), bool);
      case 120: 
        return localLHS.assign(operation(localObject1, localObject2, 104), bool);
      case 121: 
        return localLHS.assign(operation(localObject1, localObject2, 105), bool);
      case 122: 
      case 123: 
        return localLHS.assign(operation(localObject1, localObject2, 106), bool);
      case 124: 
      case 125: 
        return localLHS.assign(operation(localObject1, localObject2, 108), bool);
      case 126: 
        return localLHS.assign(operation(localObject1, localObject2, 110), bool);
      case 127: 
        return localLHS.assign(operation(localObject1, localObject2, 111), bool);
      case 128: 
      case 129: 
        return localLHS.assign(operation(localObject1, localObject2, 112), bool);
      case 130: 
      case 131: 
        return localLHS.assign(operation(localObject1, localObject2, 114), bool);
      case 132: 
      case 133: 
        return localLHS.assign(operation(localObject1, localObject2, 116), bool);
      }
      throw new InterpreterError("unimplemented operator in assignment BSH");
    }
    catch (UtilEvalError localUtilEvalError2)
    {
      throw localUtilEvalError2.toEvalError(this, paramCallStack);
    }
  }
  
  private Object operation(Object paramObject1, Object paramObject2, int paramInt)
    throws UtilEvalError
  {
    if (((paramObject1 instanceof String)) && (paramObject2 != Primitive.VOID))
    {
      if (paramInt != 102) {
        throw new UtilEvalError("Use of non + operator with String LHS");
      }
      return (String)paramObject1 + paramObject2;
    }
    if (((paramObject1 instanceof Primitive)) || ((paramObject2 instanceof Primitive)))
    {
      if ((paramObject1 == Primitive.VOID) || (paramObject2 == Primitive.VOID)) {
        throw new UtilEvalError("Illegal use of undefined object or 'void' literal");
      }
      if ((paramObject1 == Primitive.NULL) || (paramObject2 == Primitive.NULL)) {
        throw new UtilEvalError("Illegal use of null object or 'null' literal");
      }
    }
    if ((((paramObject1 instanceof Boolean)) || ((paramObject1 instanceof Character)) || ((paramObject1 instanceof Number)) || ((paramObject1 instanceof Primitive))) && (((paramObject2 instanceof Boolean)) || ((paramObject2 instanceof Character)) || ((paramObject2 instanceof Number)) || ((paramObject2 instanceof Primitive)))) {
      return Primitive.binaryOperation(paramObject1, paramObject2, paramInt);
    }
    throw new UtilEvalError("Non primitive value in operator: " + paramObject1.getClass() + " " + ParserConstants.tokenImage[paramInt] + " " + paramObject2.getClass());
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHAssignment.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */