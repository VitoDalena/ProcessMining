package bsh;

class BSHClassDeclaration
  extends SimpleNode
{
  static final String CLASSINITNAME = "_bshClassInit";
  String name;
  Modifiers modifiers;
  int numInterfaces;
  boolean extend;
  boolean isInterface;
  
  BSHClassDeclaration(int paramInt)
  {
    super(paramInt);
  }
  
  public Object eval(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    int i = 0;
    Class localClass = null;
    if (this.extend)
    {
      localObject1 = (BSHAmbiguousName)jjtGetChild(i++);
      localClass = ((BSHAmbiguousName)localObject1).toClass(paramCallStack, paramInterpreter);
    }
    Object localObject1 = new Class[this.numInterfaces];
    Object localObject2;
    for (int j = 0; j < this.numInterfaces; j++)
    {
      localObject2 = (BSHAmbiguousName)jjtGetChild(i++);
      localObject1[j] = ((BSHAmbiguousName)localObject2).toClass(paramCallStack, paramInterpreter);
      if (!localObject1[j].isInterface()) {
        throw new EvalError("Type: " + ((BSHAmbiguousName)localObject2).text + " is not an interface!", this, paramCallStack);
      }
    }
    if (i < jjtGetNumChildren()) {
      localObject2 = (BSHBlock)jjtGetChild(i);
    } else {
      localObject2 = new BSHBlock(25);
    }
    try
    {
      return ClassGenerator.getClassGenerator().generateClass(this.name, this.modifiers, (Class[])localObject1, localClass, (BSHBlock)localObject2, this.isInterface, paramCallStack, paramInterpreter);
    }
    catch (UtilEvalError localUtilEvalError)
    {
      throw localUtilEvalError.toEvalError(this, paramCallStack);
    }
  }
  
  public String toString()
  {
    return "ClassDeclaration: " + this.name;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHClassDeclaration.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */