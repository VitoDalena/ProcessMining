package bsh;

class ReturnControl
  implements ParserConstants
{
  public int kind;
  public Object value;
  public SimpleNode returnPoint;
  
  public ReturnControl(int paramInt, Object paramObject, SimpleNode paramSimpleNode)
  {
    this.kind = paramInt;
    this.value = paramObject;
    this.returnPoint = paramSimpleNode;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/ReturnControl.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */