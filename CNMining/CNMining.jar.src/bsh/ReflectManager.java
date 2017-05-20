package bsh;

public abstract class ReflectManager
{
  private static ReflectManager rfm;
  
  public static ReflectManager getReflectManager()
    throws Capabilities.Unavailable
  {
    if (rfm == null) {
      try
      {
        Class localClass = Class.forName("bsh.reflect.ReflectManagerImpl");
        rfm = (ReflectManager)localClass.newInstance();
      }
      catch (Exception localException)
      {
        throw new Capabilities.Unavailable("Reflect Manager unavailable: " + localException);
      }
    }
    return rfm;
  }
  
  public static boolean RMSetAccessible(Object paramObject)
    throws Capabilities.Unavailable
  {
    return getReflectManager().setAccessible(paramObject);
  }
  
  public abstract boolean setAccessible(Object paramObject);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/ReflectManager.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */