package bsh;

public class DelayedEvalBshMethod
  extends BshMethod
{
  String returnTypeDescriptor;
  BSHReturnType returnTypeNode;
  String[] paramTypeDescriptors;
  BSHFormalParameters paramTypesNode;
  transient CallStack callstack;
  transient Interpreter interpreter;
  
  DelayedEvalBshMethod(String paramString1, String paramString2, BSHReturnType paramBSHReturnType, String[] paramArrayOfString1, String[] paramArrayOfString2, BSHFormalParameters paramBSHFormalParameters, BSHBlock paramBSHBlock, NameSpace paramNameSpace, Modifiers paramModifiers, CallStack paramCallStack, Interpreter paramInterpreter)
  {
    super(paramString1, null, paramArrayOfString1, null, paramBSHBlock, paramNameSpace, paramModifiers);
    this.returnTypeDescriptor = paramString2;
    this.returnTypeNode = paramBSHReturnType;
    this.paramTypeDescriptors = paramArrayOfString2;
    this.paramTypesNode = paramBSHFormalParameters;
    this.callstack = paramCallStack;
    this.interpreter = paramInterpreter;
  }
  
  public String getReturnTypeDescriptor()
  {
    return this.returnTypeDescriptor;
  }
  
  public Class getReturnType()
  {
    if (this.returnTypeNode == null) {
      return null;
    }
    try
    {
      return this.returnTypeNode.evalReturnType(this.callstack, this.interpreter);
    }
    catch (EvalError localEvalError)
    {
      throw new InterpreterError("can't eval return type: " + localEvalError);
    }
  }
  
  public String[] getParamTypeDescriptors()
  {
    return this.paramTypeDescriptors;
  }
  
  public Class[] getParameterTypes()
  {
    try
    {
      return (Class[])this.paramTypesNode.eval(this.callstack, this.interpreter);
    }
    catch (EvalError localEvalError)
    {
      throw new InterpreterError("can't eval param types: " + localEvalError);
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/DelayedEvalBshMethod.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */