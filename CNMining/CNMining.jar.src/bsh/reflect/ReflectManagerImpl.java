package bsh.reflect;

import bsh.ReflectManager;
import java.lang.reflect.AccessibleObject;

public class ReflectManagerImpl
  extends ReflectManager
{
  public boolean setAccessible(Object paramObject)
  {
    if ((paramObject instanceof AccessibleObject))
    {
      ((AccessibleObject)paramObject).setAccessible(true);
      return true;
    }
    return false;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/reflect/ReflectManagerImpl.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */