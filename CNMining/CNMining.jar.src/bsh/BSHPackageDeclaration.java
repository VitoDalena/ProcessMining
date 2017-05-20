package bsh;

public class BSHPackageDeclaration
  extends SimpleNode
{
  public BSHPackageDeclaration(int paramInt)
  {
    super(paramInt);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    BSHAmbiguousName localBSHAmbiguousName = (BSHAmbiguousName)jjtGetChild(0);
    NameSpace localNameSpace = paramCallStack.top();
    localNameSpace.setPackage(localBSHAmbiguousName.text);
    localNameSpace.importPackage(localBSHAmbiguousName.text);
    return Primitive.VOID;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHPackageDeclaration.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */