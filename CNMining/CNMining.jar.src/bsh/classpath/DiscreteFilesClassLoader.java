package bsh.classpath;

import bsh.BshClassManager;
import java.util.HashMap;

public class DiscreteFilesClassLoader
  extends BshClassLoader
{
  ClassSourceMap map;
  
  public DiscreteFilesClassLoader(BshClassManager paramBshClassManager, ClassSourceMap paramClassSourceMap)
  {
    super(paramBshClassManager);
    this.map = paramClassSourceMap;
  }
  
  public Class findClass(String paramString)
    throws ClassNotFoundException
  {
    BshClassPath.ClassSource localClassSource = this.map.get(paramString);
    if (localClassSource != null)
    {
      byte[] arrayOfByte = localClassSource.getCode(paramString);
      return defineClass(paramString, arrayOfByte, 0, arrayOfByte.length);
    }
    return super.findClass(paramString);
  }
  
  public String toString()
  {
    return super.toString() + "for files: " + this.map;
  }
  
  public static class ClassSourceMap
    extends HashMap
  {
    public void put(String paramString, BshClassPath.ClassSource paramClassSource)
    {
      super.put(paramString, paramClassSource);
    }
    
    public BshClassPath.ClassSource get(String paramString)
    {
      return (BshClassPath.ClassSource)super.get(paramString);
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/classpath/DiscreteFilesClassLoader.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */