package bsh;

import java.lang.reflect.Array;

class BSHType
  extends SimpleNode
  implements BshClassManager.Listener
{
  private Class baseType;
  private int arrayDims;
  private Class type;
  String descriptor;
  
  BSHType(int paramInt)
  {
    super(paramInt);
  }
  
  public void addArrayDimension()
  {
    this.arrayDims += 1;
  }
  
  SimpleNode getTypeNode()
  {
    return (SimpleNode)jjtGetChild(0);
  }
  
  public String getTypeDescriptor(CallStack paramCallStack, Interpreter paramInterpreter, String paramString)
  {
    if (this.descriptor != null) {
      return this.descriptor;
    }
    SimpleNode localSimpleNode = getTypeNode();
    String str1;
    if ((localSimpleNode instanceof BSHPrimitiveType))
    {
      str1 = getTypeDescriptor(((BSHPrimitiveType)localSimpleNode).type);
    }
    else
    {
      Object localObject = ((BSHAmbiguousName)localSimpleNode).text;
      BshClassManager localBshClassManager = paramInterpreter.getClassManager();
      String str2 = localBshClassManager.getClassBeingDefined((String)localObject);
      Class localClass = null;
      if (str2 == null) {
        try
        {
          localClass = ((BSHAmbiguousName)localSimpleNode).toClass(paramCallStack, paramInterpreter);
        }
        catch (EvalError localEvalError) {}
      } else {
        localObject = str2;
      }
      if (localClass != null) {
        str1 = getTypeDescriptor(localClass);
      } else if ((paramString == null) || (Name.isCompound((String)localObject))) {
        str1 = "L" + ((String)localObject).replace('.', '/') + ";";
      } else {
        str1 = "L" + paramString.replace('.', '/') + "/" + (String)localObject + ";";
      }
    }
    for (int i = 0; i < this.arrayDims; i++) {
      str1 = "[" + str1;
    }
    this.descriptor = str1;
    return str1;
  }
  
  public Class getType(CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    if (this.type != null) {
      return this.type;
    }
    SimpleNode localSimpleNode = getTypeNode();
    if ((localSimpleNode instanceof BSHPrimitiveType)) {
      this.baseType = ((BSHPrimitiveType)localSimpleNode).getType();
    } else {
      this.baseType = ((BSHAmbiguousName)localSimpleNode).toClass(paramCallStack, paramInterpreter);
    }
    if (this.arrayDims > 0) {
      try
      {
        int[] arrayOfInt = new int[this.arrayDims];
        Object localObject = Array.newInstance(this.baseType, arrayOfInt);
        this.type = localObject.getClass();
      }
      catch (Exception localException)
      {
        throw new EvalError("Couldn't construct array type", this, paramCallStack);
      }
    } else {
      this.type = this.baseType;
    }
    paramInterpreter.getClassManager().addListener(this);
    return this.type;
  }
  
  public Class getBaseType()
  {
    return this.baseType;
  }
  
  public int getArrayDims()
  {
    return this.arrayDims;
  }
  
  public void classLoaderChanged()
  {
    this.type = null;
    this.baseType = null;
  }
  
  public static String getTypeDescriptor(Class paramClass)
  {
    if (paramClass == Boolean.TYPE) {
      return "Z";
    }
    if (paramClass == Character.TYPE) {
      return "C";
    }
    if (paramClass == Byte.TYPE) {
      return "B";
    }
    if (paramClass == Short.TYPE) {
      return "S";
    }
    if (paramClass == Integer.TYPE) {
      return "I";
    }
    if (paramClass == Long.TYPE) {
      return "J";
    }
    if (paramClass == Float.TYPE) {
      return "F";
    }
    if (paramClass == Double.TYPE) {
      return "D";
    }
    if (paramClass == Void.TYPE) {
      return "V";
    }
    String str = paramClass.getName().replace('.', '/');
    if ((str.startsWith("[")) || (str.endsWith(";"))) {
      return str;
    }
    return "L" + str.replace('.', '/') + ";";
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BSHType.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */