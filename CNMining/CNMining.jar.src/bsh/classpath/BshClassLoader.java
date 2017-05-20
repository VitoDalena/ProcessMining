package bsh.classpath;

import bsh.BshClassManager;
import bsh.Interpreter;
import java.net.URL;
import java.net.URLClassLoader;

public class BshClassLoader
  extends URLClassLoader
{
  BshClassManager classManager;
  
  public BshClassLoader(BshClassManager paramBshClassManager, URL[] paramArrayOfURL)
  {
    super(paramArrayOfURL);
    this.classManager = paramBshClassManager;
  }
  
  public BshClassLoader(BshClassManager paramBshClassManager, BshClassPath paramBshClassPath)
  {
    this(paramBshClassManager, paramBshClassPath.getPathComponents());
  }
  
  protected BshClassLoader(BshClassManager paramBshClassManager)
  {
    this(paramBshClassManager, new URL[0]);
  }
  
  public void addURL(URL paramURL)
  {
    super.addURL(paramURL);
  }
  
  public Class loadClass(String paramString, boolean paramBoolean)
    throws ClassNotFoundException
  {
    Class localClass = null;
    localClass = findLoadedClass(paramString);
    if (localClass != null) {
      return localClass;
    }
    if (paramString.startsWith("bsh")) {
      try
      {
        return Interpreter.class.getClassLoader().loadClass(paramString);
      }
      catch (ClassNotFoundException localClassNotFoundException1) {}
    }
    try
    {
      localClass = findClass(paramString);
    }
    catch (ClassNotFoundException localClassNotFoundException2) {}
    if (localClass == null) {
      throw new ClassNotFoundException("here in loaClass");
    }
    if (paramBoolean) {
      resolveClass(localClass);
    }
    return localClass;
  }
  
  protected Class findClass(String paramString)
    throws ClassNotFoundException
  {
    ClassManagerImpl localClassManagerImpl = (ClassManagerImpl)getClassManager();
    ClassLoader localClassLoader = localClassManagerImpl.getLoaderForClass(paramString);
    if ((localClassLoader != null) && (localClassLoader != this)) {
      try
      {
        return localClassLoader.loadClass(paramString);
      }
      catch (ClassNotFoundException localClassNotFoundException1)
      {
        throw new ClassNotFoundException("Designated loader could not find class: " + localClassNotFoundException1);
      }
    }
    if (getURLs().length > 0) {
      try
      {
        return super.findClass(paramString);
      }
      catch (ClassNotFoundException localClassNotFoundException2) {}
    }
    localClassLoader = localClassManagerImpl.getBaseLoader();
    if ((localClassLoader != null) && (localClassLoader != this)) {
      try
      {
        return localClassLoader.loadClass(paramString);
      }
      catch (ClassNotFoundException localClassNotFoundException3) {}
    }
    return localClassManagerImpl.plainClassForName(paramString);
  }
  
  BshClassManager getClassManager()
  {
    return this.classManager;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/classpath/BshClassLoader.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */