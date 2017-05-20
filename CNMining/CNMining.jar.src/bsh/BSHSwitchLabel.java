package bsh;

class BSHSwitchLabel
  extends SimpleNode
{
  boolean isDefault;
  
  public BSHSwitchLabel(int paramInt)
  {
    super(paramInt);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    if (this.isDefault) {
      return null;
    }
    SimpleNode localSimpleNode = (SimpleNode)jjtGetChild(0);
    return localSimpleNode.eval(paramCallStack, paramInterpreter);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHSwitchLabel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */