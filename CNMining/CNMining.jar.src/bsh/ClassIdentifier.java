package bsh;

public class ClassIdentifier
{
  Class clas;
  
  public ClassIdentifier(Class paramClass)
  {
    this.clas = paramClass;
  }
  
  public Class getTargetClass()
  {
    return this.clas;
  }
  
  public String toString()
  {
    return "Class Identifier: " + this.clas.getName();
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/ClassIdentifier.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */