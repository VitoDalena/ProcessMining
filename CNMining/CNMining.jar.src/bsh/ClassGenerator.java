package bsh;

import java.lang.reflect.InvocationTargetException;

public abstract class ClassGenerator
{
  private static ClassGenerator cg;
  
  public static ClassGenerator getClassGenerator()
    throws UtilEvalError
  {
    if (cg == null) {
      try
      {
        Class localClass = Class.forName("bsh.ClassGeneratorImpl");
        cg = (ClassGenerator)localClass.newInstance();
      }
      catch (Exception localException)
      {
        throw new Capabilities.Unavailable("ClassGenerator unavailable: " + localException);
      }
    }
    return cg;
  }
  
  public abstract Class generateClass(String paramString, Modifiers paramModifiers, Class[] paramArrayOfClass, Class paramClass, BSHBlock paramBSHBlock, boolean paramBoolean, CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError;
  
  public abstract Object invokeSuperclassMethod(BshClassManager paramBshClassManager, Object paramObject, String paramString, Object[] paramArrayOfObject)
    throws UtilEvalError, ReflectError, InvocationTargetException;
  
  public abstract void setInstanceNameSpaceParent(Object paramObject, String paramString, NameSpace paramNameSpace);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/ClassGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */