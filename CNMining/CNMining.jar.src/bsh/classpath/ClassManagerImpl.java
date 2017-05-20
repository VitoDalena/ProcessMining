package bsh.classpath;

import bsh.BshClassManager;
import bsh.BshClassManager.Listener;
import bsh.ClassPathException;
import bsh.Interpreter;
import bsh.InterpreterError;
import bsh.UtilEvalError;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class ClassManagerImpl
  extends BshClassManager
{
  static final String BSH_PACKAGE = "bsh";
  private BshClassPath baseClassPath;
  private boolean superImport;
  private BshClassPath fullClassPath;
  private Vector listeners = new Vector();
  private ReferenceQueue refQueue = new ReferenceQueue();
  private BshClassLoader baseLoader;
  private Map loaderMap;
  
  public ClassManagerImpl()
  {
    reset();
  }
  
  public Class classForName(String paramString)
  {
    Class localClass = (Class)this.absoluteClassCache.get(paramString);
    if (localClass != null) {
      return localClass;
    }
    if (this.absoluteNonClasses.get(paramString) != null)
    {
      if (Interpreter.DEBUG) {
        Interpreter.debug("absoluteNonClass list hit: " + paramString);
      }
      return null;
    }
    if (Interpreter.DEBUG) {
      Interpreter.debug("Trying to load class: " + paramString);
    }
    ClassLoader localClassLoader1 = getLoaderForClass(paramString);
    if (localClassLoader1 != null) {
      try
      {
        localClass = localClassLoader1.loadClass(paramString);
      }
      catch (Exception localException) {}catch (NoClassDefFoundError localNoClassDefFoundError)
      {
        throw BshClassManager.noClassDefFound(paramString, localNoClassDefFoundError);
      }
    }
    if ((localClass == null) && (paramString.startsWith("bsh"))) {
      try
      {
        localClass = Interpreter.class.getClassLoader().loadClass(paramString);
      }
      catch (ClassNotFoundException localClassNotFoundException1) {}
    }
    if ((localClass == null) && (this.baseLoader != null)) {
      try
      {
        localClass = this.baseLoader.loadClass(paramString);
      }
      catch (ClassNotFoundException localClassNotFoundException2) {}
    }
    if ((localClass == null) && (this.externalClassLoader != null)) {
      try
      {
        localClass = this.externalClassLoader.loadClass(paramString);
      }
      catch (ClassNotFoundException localClassNotFoundException3) {}
    }
    if (localClass == null) {
      try
      {
        ClassLoader localClassLoader2 = Thread.currentThread().getContextClassLoader();
        if (localClassLoader2 != null) {
          localClass = Class.forName(paramString, true, localClassLoader2);
        }
      }
      catch (ClassNotFoundException localClassNotFoundException4) {}catch (SecurityException localSecurityException) {}
    }
    if (localClass == null) {
      try
      {
        localClass = plainClassForName(paramString);
      }
      catch (ClassNotFoundException localClassNotFoundException5) {}
    }
    if (localClass == null) {
      localClass = loadSourceClass(paramString);
    }
    cacheClassInfo(paramString, localClass);
    return localClass;
  }
  
  public URL getResource(String paramString)
  {
    URL localURL = null;
    if (this.baseLoader != null) {
      localURL = this.baseLoader.getResource(paramString.substring(1));
    }
    if (localURL == null) {
      localURL = super.getResource(paramString);
    }
    return localURL;
  }
  
  public InputStream getResourceAsStream(String paramString)
  {
    InputStream localInputStream = null;
    if (this.baseLoader != null) {
      localInputStream = this.baseLoader.getResourceAsStream(paramString.substring(1));
    }
    if (localInputStream == null) {
      localInputStream = super.getResourceAsStream(paramString);
    }
    return localInputStream;
  }
  
  ClassLoader getLoaderForClass(String paramString)
  {
    return (ClassLoader)this.loaderMap.get(paramString);
  }
  
  public void addClassPath(URL paramURL)
    throws IOException
  {
    if (this.baseLoader == null)
    {
      setClassPath(new URL[] { paramURL });
    }
    else
    {
      this.baseLoader.addURL(paramURL);
      this.baseClassPath.add(paramURL);
      classLoaderChanged();
    }
  }
  
  public void reset()
  {
    this.baseClassPath = new BshClassPath("baseClassPath");
    this.baseLoader = null;
    this.loaderMap = new HashMap();
    classLoaderChanged();
  }
  
  public void setClassPath(URL[] paramArrayOfURL)
  {
    this.baseClassPath.setPath(paramArrayOfURL);
    initBaseLoader();
    this.loaderMap = new HashMap();
    classLoaderChanged();
  }
  
  public void reloadAllClasses()
    throws ClassPathException
  {
    BshClassPath localBshClassPath = new BshClassPath("temp");
    localBshClassPath.addComponent(this.baseClassPath);
    localBshClassPath.addComponent(BshClassPath.getUserClassPath());
    setClassPath(localBshClassPath.getPathComponents());
  }
  
  private void initBaseLoader()
  {
    this.baseLoader = new BshClassLoader(this, this.baseClassPath);
  }
  
  public void reloadClasses(String[] paramArrayOfString)
    throws ClassPathException
  {
    if (this.baseLoader == null) {
      initBaseLoader();
    }
    DiscreteFilesClassLoader.ClassSourceMap localClassSourceMap = new DiscreteFilesClassLoader.ClassSourceMap();
    for (int i = 0; i < paramArrayOfString.length; i++)
    {
      localObject1 = paramArrayOfString[i];
      localObject2 = this.baseClassPath.getClassSource((String)localObject1);
      if (localObject2 == null)
      {
        BshClassPath.getUserClassPath().insureInitialized();
        localObject2 = BshClassPath.getUserClassPath().getClassSource((String)localObject1);
      }
      if (localObject2 == null) {
        throw new ClassPathException("Nothing known about class: " + (String)localObject1);
      }
      if ((localObject2 instanceof BshClassPath.JarClassSource)) {
        throw new ClassPathException("Cannot reload class: " + (String)localObject1 + " from source: " + localObject2);
      }
      localClassSourceMap.put((String)localObject1, (BshClassPath.ClassSource)localObject2);
    }
    Object localObject1 = new DiscreteFilesClassLoader(this, localClassSourceMap);
    Object localObject2 = localClassSourceMap.keySet().iterator();
    while (((Iterator)localObject2).hasNext()) {
      this.loaderMap.put((String)((Iterator)localObject2).next(), localObject1);
    }
    classLoaderChanged();
  }
  
  public void reloadPackage(String paramString)
    throws ClassPathException
  {
    Set localSet = this.baseClassPath.getClassesForPackage(paramString);
    if (localSet == null) {
      localSet = BshClassPath.getUserClassPath().getClassesForPackage(paramString);
    }
    if (localSet == null) {
      throw new ClassPathException("No classes found for package: " + paramString);
    }
    reloadClasses((String[])localSet.toArray(new String[0]));
  }
  
  public BshClassPath getClassPath()
    throws ClassPathException
  {
    if (this.fullClassPath != null) {
      return this.fullClassPath;
    }
    this.fullClassPath = new BshClassPath("BeanShell Full Class Path");
    this.fullClassPath.addComponent(BshClassPath.getUserClassPath());
    try
    {
      this.fullClassPath.addComponent(BshClassPath.getBootClassPath());
    }
    catch (ClassPathException localClassPathException)
    {
      System.err.println("Warning: can't get boot class path");
    }
    this.fullClassPath.addComponent(this.baseClassPath);
    return this.fullClassPath;
  }
  
  public void doSuperImport()
    throws UtilEvalError
  {
    try
    {
      getClassPath().insureInitialized();
      getClassNameByUnqName("");
    }
    catch (ClassPathException localClassPathException)
    {
      throw new UtilEvalError("Error importing classpath " + localClassPathException);
    }
    this.superImport = true;
  }
  
  protected boolean hasSuperImport()
  {
    return this.superImport;
  }
  
  public String getClassNameByUnqName(String paramString)
    throws ClassPathException
  {
    return getClassPath().getClassNameByUnqName(paramString);
  }
  
  public void addListener(BshClassManager.Listener paramListener)
  {
    this.listeners.addElement(new WeakReference(paramListener, this.refQueue));
    Object localObject;
    while ((localObject = this.refQueue.poll()) != null)
    {
      boolean bool = this.listeners.removeElement(localObject);
      if ((!bool) && (Interpreter.DEBUG)) {
        Interpreter.debug("tried to remove non-existent weak ref: " + localObject);
      }
    }
  }
  
  public void removeListener(BshClassManager.Listener paramListener)
  {
    throw new Error("unimplemented");
  }
  
  public ClassLoader getBaseLoader()
  {
    return this.baseLoader;
  }
  
  public Class defineClass(String paramString, byte[] paramArrayOfByte)
  {
    this.baseClassPath.setClassSource(paramString, new BshClassPath.GeneratedClassSource(paramArrayOfByte));
    try
    {
      reloadClasses(new String[] { paramString });
    }
    catch (ClassPathException localClassPathException)
    {
      throw new InterpreterError("defineClass: " + localClassPathException);
    }
    return classForName(paramString);
  }
  
  protected void classLoaderChanged()
  {
    clearCaches();
    Vector localVector = new Vector();
    Enumeration localEnumeration = this.listeners.elements();
    while (localEnumeration.hasMoreElements())
    {
      localObject = (WeakReference)localEnumeration.nextElement();
      BshClassManager.Listener localListener = (BshClassManager.Listener)((WeakReference)localObject).get();
      if (localListener == null) {
        localVector.add(localObject);
      } else {
        localListener.classLoaderChanged();
      }
    }
    Object localObject = localVector.elements();
    while (((Enumeration)localObject).hasMoreElements()) {
      this.listeners.removeElement(((Enumeration)localObject).nextElement());
    }
  }
  
  public void dump(PrintWriter paramPrintWriter)
  {
    paramPrintWriter.println("Bsh Class Manager Dump: ");
    paramPrintWriter.println("----------------------- ");
    paramPrintWriter.println("baseLoader = " + this.baseLoader);
    paramPrintWriter.println("loaderMap= " + this.loaderMap);
    paramPrintWriter.println("----------------------- ");
    paramPrintWriter.println("baseClassPath = " + this.baseClassPath);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/classpath/ClassManagerImpl.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */