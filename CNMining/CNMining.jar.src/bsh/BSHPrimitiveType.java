package bsh;

class BSHPrimitiveType
  extends SimpleNode
{
  public Class type;
  
  BSHPrimitiveType(int paramInt)
  {
    super(paramInt);
  }
  
  public Class getType()
  {
    return this.type;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHPrimitiveType.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */