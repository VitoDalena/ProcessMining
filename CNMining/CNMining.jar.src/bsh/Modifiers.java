package bsh;

import java.io.Serializable;
import java.util.Hashtable;

public class Modifiers
  implements Serializable
{
  public static final int CLASS = 0;
  public static final int METHOD = 1;
  public static final int FIELD = 2;
  Hashtable modifiers;
  
  public void addModifier(int paramInt, String paramString)
  {
    if (this.modifiers == null) {
      this.modifiers = new Hashtable();
    }
    Object localObject = this.modifiers.put(paramString, Void.TYPE);
    if (localObject != null) {
      throw new IllegalStateException("Duplicate modifier: " + paramString);
    }
    int i = 0;
    if (hasModifier("private")) {
      i++;
    }
    if (hasModifier("protected")) {
      i++;
    }
    if (hasModifier("public")) {
      i++;
    }
    if (i > 1) {
      throw new IllegalStateException("public/private/protected cannot be used in combination.");
    }
    switch (paramInt)
    {
    case 0: 
      validateForClass();
      break;
    case 1: 
      validateForMethod();
      break;
    case 2: 
      validateForField();
    }
  }
  
  public boolean hasModifier(String paramString)
  {
    if (this.modifiers == null) {
      this.modifiers = new Hashtable();
    }
    return this.modifiers.get(paramString) != null;
  }
  
  private void validateForMethod()
  {
    insureNo("volatile", "Method");
    insureNo("transient", "Method");
  }
  
  private void validateForField()
  {
    insureNo("synchronized", "Variable");
    insureNo("native", "Variable");
    insureNo("abstract", "Variable");
  }
  
  private void validateForClass()
  {
    validateForMethod();
    insureNo("native", "Class");
    insureNo("synchronized", "Class");
  }
  
  private void insureNo(String paramString1, String paramString2)
  {
    if (hasModifier(paramString1)) {
      throw new IllegalStateException(paramString2 + " cannot be declared '" + paramString1 + "'");
    }
  }
  
  public String toString()
  {
    return "Modifiers: " + this.modifiers;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/Modifiers.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */