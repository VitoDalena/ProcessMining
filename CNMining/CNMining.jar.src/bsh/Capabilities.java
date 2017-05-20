package bsh;

import java.util.Hashtable;

public class Capabilities
{
  private static boolean accessibility = false;
  private static Hashtable classes = new Hashtable();
  
  public static boolean haveSwing()
  {
    return classExists("javax.swing.JButton");
  }
  
  public static boolean canGenerateInterfaces()
  {
    return classExists("java.lang.reflect.Proxy");
  }
  
  public static boolean haveAccessibility()
  {
    return accessibility;
  }
  
  public static void setAccessibility(boolean paramBoolean)
    throws Capabilities.Unavailable
  {
    if (!paramBoolean)
    {
      accessibility = false;
      return;
    }
    if ((!classExists("java.lang.reflect.AccessibleObject")) || (!classExists("bsh.reflect.ReflectManagerImpl"))) {
      throw new Unavailable("Accessibility unavailable");
    }
    try
    {
      String.class.getDeclaredMethods();
    }
    catch (SecurityException localSecurityException)
    {
      throw new Unavailable("Accessibility unavailable: " + localSecurityException);
    }
    accessibility = true;
  }
  
  public static boolean classExists(String paramString)
  {
    Object localObject = classes.get(paramString);
    if (localObject == null)
    {
      try
      {
        localObject = Class.forName(paramString);
      }
      catch (ClassNotFoundException localClassNotFoundException) {}
      if (localObject != null) {
        classes.put(localObject, "unused");
      }
    }
    return localObject != null;
  }
  
  public static class Unavailable
    extends UtilEvalError
  {
    public Unavailable(String paramString)
    {
      super();
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/Capabilities.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */