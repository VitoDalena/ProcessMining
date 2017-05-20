package bsh;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Hashtable;

public class BshClassManager
{
  private static Object NOVALUE = new Object();
  private Interpreter declaringInterpreter;
  protected ClassLoader externalClassLoader;
  protected transient Hashtable absoluteClassCache = new Hashtable();
  protected transient Hashtable absoluteNonClasses = new Hashtable();
  protected transient Hashtable resolvedObjectMethods = new Hashtable();
  protected transient Hashtable resolvedStaticMethods = new Hashtable();
  protected transient Hashtable definingClasses = new Hashtable();
  protected transient Hashtable definingClassesBaseNames = new Hashtable();
  
  public static BshClassManager createClassManager(Interpreter paramInterpreter)
  {
    BshClassManager localBshClassManager;
    if ((Capabilities.classExists("java.lang.ref.WeakReference")) && (Capabilities.classExists("java.util.HashMap")) && (Capabilities.classExists("bsh.classpath.ClassManagerImpl"))) {
      try
      {
        Class localClass = Class.forName("bsh.classpath.ClassManagerImpl");
        localBshClassManager = (BshClassManager)localClass.newInstance();
      }
      catch (Exception localException)
      {
        throw new InterpreterError("Error loading classmanager: " + localException);
      }
    } else {
      localBshClassManager = new BshClassManager();
    }
    if (paramInterpreter == null) {
      paramInterpreter = new Interpreter();
    }
    localBshClassManager.declaringInterpreter = paramInterpreter;
    return localBshClassManager;
  }
  
  public boolean classExists(String paramString)
  {
    return classForName(paramString) != null;
  }
  
  public Class classForName(String paramString)
  {
    if (isClassBeingDefined(paramString)) {
      throw new InterpreterError("Attempting to load class in the process of being defined: " + paramString);
    }
    Class localClass = null;
    try
    {
      localClass = plainClassForName(paramString);
    }
    catch (ClassNotFoundException localClassNotFoundException) {}
    if (localClass == null) {
      localClass = loadSourceClass(paramString);
    }
    return localClass;
  }
  
  protected Class loadSourceClass(String paramString)
  {
    String str = "/" + paramString.replace('.', '/') + ".java";
    InputStream localInputStream = getResourceAsStream(str);
    if (localInputStream == null) {
      return null;
    }
    try
    {
      System.out.println("Loading class from source file: " + str);
      this.declaringInterpreter.eval(new InputStreamReader(localInputStream));
    }
    catch (EvalError localEvalError)
    {
      System.err.println(localEvalError);
    }
    try
    {
      return plainClassForName(paramString);
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      System.err.println("Class not found in source file: " + paramString);
    }
    return null;
  }
  
  public Class plainClassForName(String paramString)
    throws ClassNotFoundException
  {
    Class localClass = null;
    try
    {
      if (this.externalClassLoader != null) {
        localClass = this.externalClassLoader.loadClass(paramString);
      } else {
        localClass = Class.forName(paramString);
      }
      cacheClassInfo(paramString, localClass);
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      throw noClassDefFound(paramString, localNoClassDefFoundError);
    }
    return localClass;
  }
  
  public URL getResource(String paramString)
  {
    URL localURL = null;
    if (this.externalClassLoader != null) {
      localURL = this.externalClassLoader.getResource(paramString.substring(1));
    }
    if (localURL == null) {
      localURL = Interpreter.class.getResource(paramString);
    }
    return localURL;
  }
  
  public InputStream getResourceAsStream(String paramString)
  {
    InputStream localInputStream = null;
    if (this.externalClassLoader != null) {
      localInputStream = this.externalClassLoader.getResourceAsStream(paramString.substring(1));
    }
    if (localInputStream == null) {
      localInputStream = Interpreter.class.getResourceAsStream(paramString);
    }
    return localInputStream;
  }
  
  public void cacheClassInfo(String paramString, Class paramClass)
  {
    if (paramClass != null) {
      this.absoluteClassCache.put(paramString, paramClass);
    } else {
      this.absoluteNonClasses.put(paramString, NOVALUE);
    }
  }
  
  public void cacheResolvedMethod(Class paramClass, Class[] paramArrayOfClass, Method paramMethod)
  {
    if (Interpreter.DEBUG) {
      Interpreter.debug("cacheResolvedMethod putting: " + paramClass + " " + paramMethod);
    }
    SignatureKey localSignatureKey = new SignatureKey(paramClass, paramMethod.getName(), paramArrayOfClass);
    if (Modifier.isStatic(paramMethod.getModifiers())) {
      this.resolvedStaticMethods.put(localSignatureKey, paramMethod);
    } else {
      this.resolvedObjectMethods.put(localSignatureKey, paramMethod);
    }
  }
  
  protected Method getResolvedMethod(Class paramClass, String paramString, Class[] paramArrayOfClass, boolean paramBoolean)
  {
    SignatureKey localSignatureKey = new SignatureKey(paramClass, paramString, paramArrayOfClass);
    Method localMethod = (Method)this.resolvedStaticMethods.get(localSignatureKey);
    if ((localMethod == null) && (!paramBoolean)) {
      localMethod = (Method)this.resolvedObjectMethods.get(localSignatureKey);
    }
    if (Interpreter.DEBUG) {
      if (localMethod == null) {
        Interpreter.debug("getResolvedMethod cache MISS: " + paramClass + " - " + paramString);
      } else {
        Interpreter.debug("getResolvedMethod cache HIT: " + paramClass + " - " + localMethod);
      }
    }
    return localMethod;
  }
  
  protected void clearCaches()
  {
    this.absoluteNonClasses = new Hashtable();
    this.absoluteClassCache = new Hashtable();
    this.resolvedObjectMethods = new Hashtable();
    this.resolvedStaticMethods = new Hashtable();
  }
  
  public void setClassLoader(ClassLoader paramClassLoader)
  {
    this.externalClassLoader = paramClassLoader;
    classLoaderChanged();
  }
  
  public void addClassPath(URL paramURL)
    throws IOException
  {}
  
  public void reset()
  {
    clearCaches();
  }
  
  public void setClassPath(URL[] paramArrayOfURL)
    throws UtilEvalError
  {
    throw cmUnavailable();
  }
  
  public void reloadAllClasses()
    throws UtilEvalError
  {
    throw cmUnavailable();
  }
  
  public void reloadClasses(String[] paramArrayOfString)
    throws UtilEvalError
  {
    throw cmUnavailable();
  }
  
  public void reloadPackage(String paramString)
    throws UtilEvalError
  {
    throw cmUnavailable();
  }
  
  protected void doSuperImport()
    throws UtilEvalError
  {
    throw cmUnavailable();
  }
  
  protected boolean hasSuperImport()
  {
    return false;
  }
  
  protected String getClassNameByUnqName(String paramString)
    throws UtilEvalError
  {
    throw cmUnavailable();
  }
  
  public void addListener(Listener paramListener) {}
  
  public void removeListener(Listener paramListener) {}
  
  public void dump(PrintWriter paramPrintWriter)
  {
    paramPrintWriter.println("BshClassManager: no class manager.");
  }
  
  protected void definingClass(String paramString)
  {
    String str1 = Name.suffix(paramString, 1);
    int i = str1.indexOf("$");
    if (i != -1) {
      str1 = str1.substring(i + 1);
    }
    String str2 = (String)this.definingClassesBaseNames.get(str1);
    if (str2 != null) {
      throw new InterpreterError("Defining class problem: " + paramString + ": BeanShell cannot yet simultaneously define two or more " + "dependant classes of the same name.  Attempt to define: " + paramString + " while defining: " + str2);
    }
    this.definingClasses.put(paramString, NOVALUE);
    this.definingClassesBaseNames.put(str1, paramString);
  }
  
  protected boolean isClassBeingDefined(String paramString)
  {
    return this.definingClasses.get(paramString) != null;
  }
  
  protected String getClassBeingDefined(String paramString)
  {
    String str = Name.suffix(paramString, 1);
    return (String)this.definingClassesBaseNames.get(str);
  }
  
  protected void doneDefiningClass(String paramString)
  {
    String str = Name.suffix(paramString, 1);
    this.definingClasses.remove(paramString);
    this.definingClassesBaseNames.remove(str);
  }
  
  public Class defineClass(String paramString, byte[] paramArrayOfByte)
  {
    throw new InterpreterError("Can't create class (" + paramString + ") without class manager package.");
  }
  
  protected void classLoaderChanged() {}
  
  protected static Error noClassDefFound(String paramString, Error paramError)
  {
    return new NoClassDefFoundError("A class required by class: " + paramString + " could not be loaded:\n" + paramError.toString());
  }
  
  protected static UtilEvalError cmUnavailable()
  {
    return new Capabilities.Unavailable("ClassLoading features unavailable.");
  }
  
  static class SignatureKey
  {
    Class clas;
    Class[] types;
    String methodName;
    int hashCode = 0;
    
    SignatureKey(Class paramClass, String paramString, Class[] paramArrayOfClass)
    {
      this.clas = paramClass;
      this.methodName = paramString;
      this.types = paramArrayOfClass;
    }
    
    public int hashCode()
    {
      if (this.hashCode == 0)
      {
        this.hashCode = (this.clas.hashCode() * this.methodName.hashCode());
        if (this.types == null) {
          return this.hashCode;
        }
        for (int i = 0; i < this.types.length; i++)
        {
          int j = this.types[i] == null ? 21 : this.types[i].hashCode();
          this.hashCode = (this.hashCode * (i + 1) + j);
        }
      }
      return this.hashCode;
    }
    
    public boolean equals(Object paramObject)
    {
      SignatureKey localSignatureKey = (SignatureKey)paramObject;
      if (this.types == null) {
        return localSignatureKey.types == null;
      }
      if (this.clas != localSignatureKey.clas) {
        return false;
      }
      if (!this.methodName.equals(localSignatureKey.methodName)) {
        return false;
      }
      if (this.types.length != localSignatureKey.types.length) {
        return false;
      }
      for (int i = 0; i < this.types.length; i++) {
        if (this.types[i] == null)
        {
          if (localSignatureKey.types[i] != null) {
            return false;
          }
        }
        else if (!this.types[i].equals(localSignatureKey.types[i])) {
          return false;
        }
      }
      return true;
    }
  }
  
  public static abstract interface Listener
  {
    public abstract void classLoaderChanged();
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/BshClassManager.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */