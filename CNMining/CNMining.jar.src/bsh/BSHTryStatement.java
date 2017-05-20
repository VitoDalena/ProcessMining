package bsh;

import java.util.Vector;

class BSHTryStatement
  extends SimpleNode
{
  BSHTryStatement(int paramInt)
  {
    super(paramInt);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    BSHBlock localBSHBlock1 = (BSHBlock)jjtGetChild(0);
    Vector localVector1 = new Vector();
    Vector localVector2 = new Vector();
    int i = jjtGetNumChildren();
    Object localObject1 = null;
    int j = 1;
    while ((j < i) && (((localObject1 = jjtGetChild(j++)) instanceof BSHFormalParameter)))
    {
      localVector1.addElement(localObject1);
      localVector2.addElement(jjtGetChild(j++));
      localObject1 = null;
    }
    BSHBlock localBSHBlock2 = null;
    if (localObject1 != null) {
      localBSHBlock2 = (BSHBlock)localObject1;
    }
    Object localObject2 = null;
    Throwable localThrowable = null;
    Object localObject3 = null;
    int k = paramCallStack.depth();
    Object localObject4;
    try
    {
      localObject3 = localBSHBlock1.eval(paramCallStack, paramInterpreter);
    }
    catch (TargetError localTargetError)
    {
      localObject2 = localTargetError;
      for (localObject4 = "Bsh Stack: "; paramCallStack.depth() > k; localObject4 = (String)localObject4 + "\t" + paramCallStack.pop() + "\n") {}
    }
    if (localObject2 != null) {
      localThrowable = ((TargetError)localObject2).getTarget();
    }
    if (localThrowable != null)
    {
      int m = localVector1.size();
      for (j = 0; j < m; j++)
      {
        localObject4 = (BSHFormalParameter)localVector1.elementAt(j);
        ((BSHFormalParameter)localObject4).eval(paramCallStack, paramInterpreter);
        if ((((BSHFormalParameter)localObject4).type == null) && (paramInterpreter.getStrictJava())) {
          throw new EvalError("(Strict Java) Untyped catch block", this, paramCallStack);
        }
        if (((BSHFormalParameter)localObject4).type != null) {
          try
          {
            localThrowable = (Throwable)Types.castObject(localThrowable, ((BSHFormalParameter)localObject4).type, 1);
          }
          catch (UtilEvalError localUtilEvalError1)
          {
            continue;
          }
        }
        BSHBlock localBSHBlock3 = (BSHBlock)localVector2.elementAt(j);
        NameSpace localNameSpace = paramCallStack.top();
        BlockNameSpace localBlockNameSpace = new BlockNameSpace(localNameSpace);
        try
        {
          if (((BSHFormalParameter)localObject4).type == BSHFormalParameter.UNTYPED)
          {
            localBlockNameSpace.setBlockVariable(((BSHFormalParameter)localObject4).name, localThrowable);
          }
          else
          {
            Modifiers localModifiers = new Modifiers();
            localBlockNameSpace.setTypedVariable(((BSHFormalParameter)localObject4).name, ((BSHFormalParameter)localObject4).type, localThrowable, new Modifiers());
          }
        }
        catch (UtilEvalError localUtilEvalError2)
        {
          throw new InterpreterError("Unable to set var in catch block namespace.");
        }
        paramCallStack.swap(localBlockNameSpace);
        try
        {
          localObject3 = localBSHBlock3.eval(paramCallStack, paramInterpreter);
        }
        finally
        {
          paramCallStack.swap(localNameSpace);
        }
        localObject2 = null;
        break;
      }
    }
    if (localBSHBlock2 != null) {
      localObject3 = localBSHBlock2.eval(paramCallStack, paramInterpreter);
    }
    if (localObject2 != null) {
      throw ((Throwable)localObject2);
    }
    if ((localObject3 instanceof ReturnControl)) {
      return localObject3;
    }
    return Primitive.VOID;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHTryStatement.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */