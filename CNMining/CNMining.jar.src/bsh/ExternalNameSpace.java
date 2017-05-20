package bsh;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class ExternalNameSpace
  extends NameSpace
{
  private Map externalMap;
  
  public ExternalNameSpace()
  {
    this(null, "External Map Namespace", null);
  }
  
  public ExternalNameSpace(NameSpace paramNameSpace, String paramString, Map paramMap)
  {
    super(paramNameSpace, paramString);
    if (paramMap == null) {
      paramMap = new HashMap();
    }
    this.externalMap = paramMap;
  }
  
  public Map getMap()
  {
    return this.externalMap;
  }
  
  public void setMap(Map paramMap)
  {
    this.externalMap = null;
    clear();
    this.externalMap = paramMap;
  }
  
  void setVariable(String paramString, Object paramObject, boolean paramBoolean1, boolean paramBoolean2)
    throws UtilEvalError
  {
    super.setVariable(paramString, paramObject, paramBoolean1, paramBoolean2);
    putExternalMap(paramString, paramObject);
  }
  
  public void unsetVariable(String paramString)
  {
    super.unsetVariable(paramString);
    this.externalMap.remove(paramString);
  }
  
  public String[] getVariableNames()
  {
    HashSet localHashSet = new HashSet();
    String[] arrayOfString = super.getVariableNames();
    localHashSet.addAll(Arrays.asList(arrayOfString));
    localHashSet.addAll(this.externalMap.keySet());
    return (String[])localHashSet.toArray(new String[0]);
  }
  
  protected Variable getVariableImpl(String paramString, boolean paramBoolean)
    throws UtilEvalError
  {
    Object localObject1 = this.externalMap.get(paramString);
    Object localObject2;
    if (localObject1 == null)
    {
      super.unsetVariable(paramString);
      localObject2 = super.getVariableImpl(paramString, paramBoolean);
    }
    else
    {
      Variable localVariable = super.getVariableImpl(paramString, false);
      if (localVariable == null) {
        localObject2 = new Variable(paramString, (Class)null, localObject1, (Modifiers)null);
      } else {
        localObject2 = localVariable;
      }
    }
    return (Variable)localObject2;
  }
  
  public Variable[] getDeclaredVariables()
  {
    return super.getDeclaredVariables();
  }
  
  public void setTypedVariable(String paramString, Class paramClass, Object paramObject, Modifiers paramModifiers)
    throws UtilEvalError
  {
    super.setTypedVariable(paramString, paramClass, paramObject, paramModifiers);
    putExternalMap(paramString, paramObject);
  }
  
  public void setMethod(String paramString, BshMethod paramBshMethod)
    throws UtilEvalError
  {
    super.setMethod(paramString, paramBshMethod);
  }
  
  public BshMethod getMethod(String paramString, Class[] paramArrayOfClass, boolean paramBoolean)
    throws UtilEvalError
  {
    return super.getMethod(paramString, paramArrayOfClass, paramBoolean);
  }
  
  protected void getAllNamesAux(Vector paramVector)
  {
    super.getAllNamesAux(paramVector);
  }
  
  public void clear()
  {
    super.clear();
    this.externalMap.clear();
  }
  
  protected void putExternalMap(String paramString, Object paramObject)
  {
    if ((paramObject instanceof Variable)) {
      try
      {
        paramObject = unwrapVariable((Variable)paramObject);
      }
      catch (UtilEvalError localUtilEvalError)
      {
        throw new InterpreterError("unexpected UtilEvalError");
      }
    }
    if ((paramObject instanceof Primitive)) {
      paramObject = Primitive.unwrap((Primitive)paramObject);
    }
    this.externalMap.put(paramString, paramObject);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/ExternalNameSpace.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */