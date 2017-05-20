package bsh;

import java.io.Serializable;

public class Variable
  implements Serializable
{
  static final int DECLARATION = 0;
  static final int ASSIGNMENT = 1;
  String name;
  Class type = null;
  String typeDescriptor;
  Object value;
  Modifiers modifiers;
  LHS lhs;
  
  Variable(String paramString, Class paramClass, LHS paramLHS)
  {
    this.name = paramString;
    this.lhs = paramLHS;
    this.type = paramClass;
  }
  
  Variable(String paramString, Object paramObject, Modifiers paramModifiers)
    throws UtilEvalError
  {
    this(paramString, (Class)null, paramObject, paramModifiers);
  }
  
  Variable(String paramString1, String paramString2, Object paramObject, Modifiers paramModifiers)
    throws UtilEvalError
  {
    this(paramString1, (Class)null, paramObject, paramModifiers);
    this.typeDescriptor = paramString2;
  }
  
  Variable(String paramString, Class paramClass, Object paramObject, Modifiers paramModifiers)
    throws UtilEvalError
  {
    this.name = paramString;
    this.type = paramClass;
    this.modifiers = paramModifiers;
    setValue(paramObject, 0);
  }
  
  public void setValue(Object paramObject, int paramInt)
    throws UtilEvalError
  {
    if ((hasModifier("final")) && (this.value != null)) {
      throw new UtilEvalError("Final variable, can't re-assign.");
    }
    if (paramObject == null) {
      paramObject = Primitive.getDefaultValue(this.type);
    }
    if (this.lhs != null)
    {
      this.lhs.assign(paramObject, false);
      return;
    }
    if (this.type != null) {
      paramObject = Types.castObject(paramObject, this.type, paramInt == 0 ? 0 : 1);
    }
    this.value = paramObject;
  }
  
  Object getValue()
    throws UtilEvalError
  {
    if (this.lhs != null) {
      return this.lhs.getValue();
    }
    return this.value;
  }
  
  public Class getType()
  {
    return this.type;
  }
  
  public String getTypeDescriptor()
  {
    return this.typeDescriptor;
  }
  
  public Modifiers getModifiers()
  {
    return this.modifiers;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public boolean hasModifier(String paramString)
  {
    return (this.modifiers != null) && (this.modifiers.hasModifier(paramString));
  }
  
  public String toString()
  {
    return "Variable: " + super.toString() + " " + this.name + ", type:" + this.type + ", value:" + this.value + ", lhs = " + this.lhs;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/Variable.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */