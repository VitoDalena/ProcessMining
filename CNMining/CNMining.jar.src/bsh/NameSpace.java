package bsh;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class NameSpace
  implements Serializable, BshClassManager.Listener, NameSource
{
  public static final NameSpace JAVACODE = new NameSpace((BshClassManager)null, "Called from compiled Java code.");
  private String nsName;
  private NameSpace parent;
  private Hashtable variables;
  private Hashtable methods;
  protected Hashtable importedClasses;
  private Vector importedPackages;
  private Vector importedCommands;
  private Vector importedObjects;
  private Vector importedStatic;
  private String packageName;
  private transient BshClassManager classManager;
  private This thisReference;
  private Hashtable names;
  SimpleNode callerInfoNode;
  boolean isMethod;
  boolean isClass;
  Class classStatic;
  Object classInstance;
  private transient Hashtable classCache;
  Vector nameSourceListeners;
  
  void setClassStatic(Class paramClass)
  {
    this.classStatic = paramClass;
    importStatic(paramClass);
  }
  
  void setClassInstance(Object paramObject)
  {
    this.classInstance = paramObject;
    importObject(paramObject);
  }
  
  Object getClassInstance()
    throws UtilEvalError
  {
    if (this.classInstance != null) {
      return this.classInstance;
    }
    if (this.classStatic != null) {
      throw new UtilEvalError("Can't refer to class instance from static context.");
    }
    throw new InterpreterError("Can't resolve class instance 'this' in: " + this);
  }
  
  public NameSpace(NameSpace paramNameSpace, String paramString)
  {
    this(paramNameSpace, null, paramString);
  }
  
  public NameSpace(BshClassManager paramBshClassManager, String paramString)
  {
    this(null, paramBshClassManager, paramString);
  }
  
  public NameSpace(NameSpace paramNameSpace, BshClassManager paramBshClassManager, String paramString)
  {
    setName(paramString);
    setParent(paramNameSpace);
    setClassManager(paramBshClassManager);
    if (paramBshClassManager != null) {
      paramBshClassManager.addListener(this);
    }
  }
  
  public void setName(String paramString)
  {
    this.nsName = paramString;
  }
  
  public String getName()
  {
    return this.nsName;
  }
  
  void setNode(SimpleNode paramSimpleNode)
  {
    this.callerInfoNode = paramSimpleNode;
  }
  
  SimpleNode getNode()
  {
    if (this.callerInfoNode != null) {
      return this.callerInfoNode;
    }
    if (this.parent != null) {
      return this.parent.getNode();
    }
    return null;
  }
  
  public Object get(String paramString, Interpreter paramInterpreter)
    throws UtilEvalError
  {
    CallStack localCallStack = new CallStack(this);
    return getNameResolver(paramString).toObject(localCallStack, paramInterpreter);
  }
  
  public void setVariable(String paramString, Object paramObject, boolean paramBoolean)
    throws UtilEvalError
  {
    boolean bool = Interpreter.LOCALSCOPING ? paramBoolean : true;
    setVariable(paramString, paramObject, paramBoolean, bool);
  }
  
  void setLocalVariable(String paramString, Object paramObject, boolean paramBoolean)
    throws UtilEvalError
  {
    setVariable(paramString, paramObject, paramBoolean, false);
  }
  
  void setVariable(String paramString, Object paramObject, boolean paramBoolean1, boolean paramBoolean2)
    throws UtilEvalError
  {
    if (this.variables == null) {
      this.variables = new Hashtable();
    }
    if (paramObject == null) {
      throw new InterpreterError("null variable value");
    }
    Variable localVariable = getVariableImpl(paramString, paramBoolean2);
    if (localVariable != null)
    {
      try
      {
        localVariable.setValue(paramObject, 1);
      }
      catch (UtilEvalError localUtilEvalError)
      {
        throw new UtilEvalError("Variable assignment: " + paramString + ": " + localUtilEvalError.getMessage());
      }
    }
    else
    {
      if (paramBoolean1) {
        throw new UtilEvalError("(Strict Java mode) Assignment to undeclared variable: " + paramString);
      }
      NameSpace localNameSpace = this;
      localNameSpace.variables.put(paramString, new Variable(paramString, paramObject, null));
      nameSpaceChanged();
    }
  }
  
  public void unsetVariable(String paramString)
  {
    if (this.variables != null)
    {
      this.variables.remove(paramString);
      nameSpaceChanged();
    }
  }
  
  public String[] getVariableNames()
  {
    if (this.variables == null) {
      return new String[0];
    }
    return enumerationToStringArray(this.variables.keys());
  }
  
  public String[] getMethodNames()
  {
    if (this.methods == null) {
      return new String[0];
    }
    return enumerationToStringArray(this.methods.keys());
  }
  
  public BshMethod[] getMethods()
  {
    if (this.methods == null) {
      return new BshMethod[0];
    }
    return flattenMethodCollection(this.methods.elements());
  }
  
  private String[] enumerationToStringArray(Enumeration paramEnumeration)
  {
    Vector localVector = new Vector();
    while (paramEnumeration.hasMoreElements()) {
      localVector.addElement(paramEnumeration.nextElement());
    }
    String[] arrayOfString = new String[localVector.size()];
    localVector.copyInto(arrayOfString);
    return arrayOfString;
  }
  
  private BshMethod[] flattenMethodCollection(Enumeration paramEnumeration)
  {
    Vector localVector1 = new Vector();
    while (paramEnumeration.hasMoreElements())
    {
      localObject = paramEnumeration.nextElement();
      if ((localObject instanceof BshMethod))
      {
        localVector1.addElement(localObject);
      }
      else
      {
        Vector localVector2 = (Vector)localObject;
        for (int i = 0; i < localVector2.size(); i++) {
          localVector1.addElement(localVector2.elementAt(i));
        }
      }
    }
    Object localObject = new BshMethod[localVector1.size()];
    localVector1.copyInto((Object[])localObject);
    return (BshMethod[])localObject;
  }
  
  public NameSpace getParent()
  {
    return this.parent;
  }
  
  public This getSuper(Interpreter paramInterpreter)
  {
    if (this.parent != null) {
      return this.parent.getThis(paramInterpreter);
    }
    return getThis(paramInterpreter);
  }
  
  public This getGlobal(Interpreter paramInterpreter)
  {
    if (this.parent != null) {
      return this.parent.getGlobal(paramInterpreter);
    }
    return getThis(paramInterpreter);
  }
  
  This getThis(Interpreter paramInterpreter)
  {
    if (this.thisReference == null) {
      this.thisReference = This.getThis(this, paramInterpreter);
    }
    return this.thisReference;
  }
  
  public BshClassManager getClassManager()
  {
    if (this.classManager != null) {
      return this.classManager;
    }
    if ((this.parent != null) && (this.parent != JAVACODE)) {
      return this.parent.getClassManager();
    }
    System.out.println("experiment: creating class manager");
    this.classManager = BshClassManager.createClassManager(null);
    return this.classManager;
  }
  
  void setClassManager(BshClassManager paramBshClassManager)
  {
    this.classManager = paramBshClassManager;
  }
  
  public void prune()
  {
    if (this.classManager == null) {
      setClassManager(BshClassManager.createClassManager(null));
    }
    setParent(null);
  }
  
  public void setParent(NameSpace paramNameSpace)
  {
    this.parent = paramNameSpace;
    if (paramNameSpace == null) {
      loadDefaultImports();
    }
  }
  
  public Object getVariable(String paramString)
    throws UtilEvalError
  {
    return getVariable(paramString, true);
  }
  
  public Object getVariable(String paramString, boolean paramBoolean)
    throws UtilEvalError
  {
    Variable localVariable = getVariableImpl(paramString, paramBoolean);
    return unwrapVariable(localVariable);
  }
  
  protected Variable getVariableImpl(String paramString, boolean paramBoolean)
    throws UtilEvalError
  {
    Variable localVariable = null;
    if ((localVariable == null) && (this.isClass)) {
      localVariable = getImportedVar(paramString);
    }
    if ((localVariable == null) && (this.variables != null)) {
      localVariable = (Variable)this.variables.get(paramString);
    }
    if ((localVariable == null) && (!this.isClass)) {
      localVariable = getImportedVar(paramString);
    }
    if ((paramBoolean) && (localVariable == null) && (this.parent != null)) {
      localVariable = this.parent.getVariableImpl(paramString, paramBoolean);
    }
    return localVariable;
  }
  
  public Variable[] getDeclaredVariables()
  {
    if (this.variables == null) {
      return new Variable[0];
    }
    Variable[] arrayOfVariable = new Variable[this.variables.size()];
    int i = 0;
    Enumeration localEnumeration = this.variables.elements();
    while (localEnumeration.hasMoreElements()) {
      arrayOfVariable[(i++)] = ((Variable)localEnumeration.nextElement());
    }
    return arrayOfVariable;
  }
  
  protected Object unwrapVariable(Variable paramVariable)
    throws UtilEvalError
  {
    return paramVariable == null ? Primitive.VOID : paramVariable.getValue();
  }
  
  /**
   * @deprecated
   */
  public void setTypedVariable(String paramString, Class paramClass, Object paramObject, boolean paramBoolean)
    throws UtilEvalError
  {
    Modifiers localModifiers = new Modifiers();
    if (paramBoolean) {
      localModifiers.addModifier(2, "final");
    }
    setTypedVariable(paramString, paramClass, paramObject, localModifiers);
  }
  
  public void setTypedVariable(String paramString, Class paramClass, Object paramObject, Modifiers paramModifiers)
    throws UtilEvalError
  {
    if (this.variables == null) {
      this.variables = new Hashtable();
    }
    Variable localVariable = getVariableImpl(paramString, false);
    if ((localVariable != null) && (localVariable.getType() != null))
    {
      if (localVariable.getType() != paramClass) {
        throw new UtilEvalError("Typed variable: " + paramString + " was previously declared with type: " + localVariable.getType());
      }
      localVariable.setValue(paramObject, 0);
      return;
    }
    this.variables.put(paramString, new Variable(paramString, paramClass, paramObject, paramModifiers));
  }
  
  public void setMethod(String paramString, BshMethod paramBshMethod)
    throws UtilEvalError
  {
    if (this.methods == null) {
      this.methods = new Hashtable();
    }
    Object localObject = this.methods.get(paramString);
    if (localObject == null)
    {
      this.methods.put(paramString, paramBshMethod);
    }
    else if ((localObject instanceof BshMethod))
    {
      Vector localVector = new Vector();
      localVector.addElement(localObject);
      localVector.addElement(paramBshMethod);
      this.methods.put(paramString, localVector);
    }
    else
    {
      ((Vector)localObject).addElement(paramBshMethod);
    }
  }
  
  public BshMethod getMethod(String paramString, Class[] paramArrayOfClass)
    throws UtilEvalError
  {
    return getMethod(paramString, paramArrayOfClass, false);
  }
  
  public BshMethod getMethod(String paramString, Class[] paramArrayOfClass, boolean paramBoolean)
    throws UtilEvalError
  {
    BshMethod localBshMethod = null;
    if ((localBshMethod == null) && (this.isClass) && (!paramBoolean)) {
      localBshMethod = getImportedMethod(paramString, paramArrayOfClass);
    }
    Object localObject1 = null;
    if ((localBshMethod == null) && (this.methods != null))
    {
      localObject1 = this.methods.get(paramString);
      if (localObject1 != null)
      {
        BshMethod[] arrayOfBshMethod;
        if ((localObject1 instanceof Vector))
        {
          localObject2 = (Vector)localObject1;
          arrayOfBshMethod = new BshMethod[((Vector)localObject2).size()];
          ((Vector)localObject2).copyInto(arrayOfBshMethod);
        }
        else
        {
          arrayOfBshMethod = new BshMethod[] { (BshMethod)localObject1 };
        }
        Object localObject2 = new Class[arrayOfBshMethod.length][];
        for (int i = 0; i < arrayOfBshMethod.length; i++) {
          localObject2[i] = arrayOfBshMethod[i].getParameterTypes();
        }
        int j = Reflect.findMostSpecificSignature(paramArrayOfClass, (Class[][])localObject2);
        if (j != -1) {
          localBshMethod = arrayOfBshMethod[j];
        }
      }
    }
    if ((localBshMethod == null) && (!this.isClass) && (!paramBoolean)) {
      localBshMethod = getImportedMethod(paramString, paramArrayOfClass);
    }
    if ((!paramBoolean) && (localBshMethod == null) && (this.parent != null)) {
      return this.parent.getMethod(paramString, paramArrayOfClass);
    }
    return localBshMethod;
  }
  
  public void importClass(String paramString)
  {
    if (this.importedClasses == null) {
      this.importedClasses = new Hashtable();
    }
    this.importedClasses.put(Name.suffix(paramString, 1), paramString);
    nameSpaceChanged();
  }
  
  public void importPackage(String paramString)
  {
    if (this.importedPackages == null) {
      this.importedPackages = new Vector();
    }
    if (this.importedPackages.contains(paramString)) {
      this.importedPackages.remove(paramString);
    }
    this.importedPackages.addElement(paramString);
    nameSpaceChanged();
  }
  
  public void importCommands(String paramString)
  {
    if (this.importedCommands == null) {
      this.importedCommands = new Vector();
    }
    paramString = paramString.replace('.', '/');
    if (!paramString.startsWith("/")) {
      paramString = "/" + paramString;
    }
    if ((paramString.length() > 1) && (paramString.endsWith("/"))) {
      paramString = paramString.substring(0, paramString.length() - 1);
    }
    if (this.importedCommands.contains(paramString)) {
      this.importedCommands.remove(paramString);
    }
    this.importedCommands.addElement(paramString);
    nameSpaceChanged();
  }
  
  public Object getCommand(String paramString, Class[] paramArrayOfClass, Interpreter paramInterpreter)
    throws UtilEvalError
  {
    if (Interpreter.DEBUG) {
      Interpreter.debug("getCommand: " + paramString);
    }
    BshClassManager localBshClassManager = paramInterpreter.getClassManager();
    if (this.importedCommands != null) {
      for (int i = this.importedCommands.size() - 1; i >= 0; i--)
      {
        String str1 = (String)this.importedCommands.elementAt(i);
        String str2;
        if (str1.equals("/")) {
          str2 = str1 + paramString + ".bsh";
        } else {
          str2 = str1 + "/" + paramString + ".bsh";
        }
        Interpreter.debug("searching for script: " + str2);
        InputStream localInputStream = localBshClassManager.getResourceAsStream(str2);
        if (localInputStream != null) {
          return loadScriptedCommand(localInputStream, paramString, paramArrayOfClass, str2, paramInterpreter);
        }
        String str3;
        if (str1.equals("/")) {
          str3 = paramString;
        } else {
          str3 = str1.substring(1).replace('/', '.') + "." + paramString;
        }
        Interpreter.debug("searching for class: " + str3);
        Class localClass = localBshClassManager.classForName(str3);
        if (localClass != null) {
          return localClass;
        }
      }
    }
    if (this.parent != null) {
      return this.parent.getCommand(paramString, paramArrayOfClass, paramInterpreter);
    }
    return null;
  }
  
  protected BshMethod getImportedMethod(String paramString, Class[] paramArrayOfClass)
    throws UtilEvalError
  {
    Class localClass;
    Method localMethod;
    if (this.importedObjects != null) {
      for (int i = 0; i < this.importedObjects.size(); i++)
      {
        Object localObject = this.importedObjects.elementAt(i);
        localClass = localObject.getClass();
        localMethod = Reflect.resolveJavaMethod(getClassManager(), localClass, paramString, paramArrayOfClass, false);
        if (localMethod != null) {
          return new BshMethod(localMethod, localObject);
        }
      }
    }
    if (this.importedStatic != null) {
      for (int j = 0; j < this.importedStatic.size(); j++)
      {
        localClass = (Class)this.importedStatic.elementAt(j);
        localMethod = Reflect.resolveJavaMethod(getClassManager(), localClass, paramString, paramArrayOfClass, true);
        if (localMethod != null) {
          return new BshMethod(localMethod, null);
        }
      }
    }
    return null;
  }
  
  protected Variable getImportedVar(String paramString)
    throws UtilEvalError
  {
    Class localClass;
    Field localField;
    if (this.importedObjects != null) {
      for (int i = 0; i < this.importedObjects.size(); i++)
      {
        Object localObject = this.importedObjects.elementAt(i);
        localClass = localObject.getClass();
        localField = Reflect.resolveJavaField(localClass, paramString, false);
        if (localField != null) {
          return new Variable(paramString, localField.getType(), new LHS(localObject, localField));
        }
      }
    }
    if (this.importedStatic != null) {
      for (int j = 0; j < this.importedStatic.size(); j++)
      {
        localClass = (Class)this.importedStatic.elementAt(j);
        localField = Reflect.resolveJavaField(localClass, paramString, true);
        if (localField != null) {
          return new Variable(paramString, localField.getType(), new LHS(localField));
        }
      }
    }
    return null;
  }
  
  private BshMethod loadScriptedCommand(InputStream paramInputStream, String paramString1, Class[] paramArrayOfClass, String paramString2, Interpreter paramInterpreter)
    throws UtilEvalError
  {
    try
    {
      paramInterpreter.eval(new InputStreamReader(paramInputStream), this, paramString2);
    }
    catch (EvalError localEvalError)
    {
      Interpreter.debug(localEvalError.toString());
      throw new UtilEvalError("Error loading script: " + localEvalError.getMessage());
    }
    BshMethod localBshMethod = getMethod(paramString1, paramArrayOfClass);
    return localBshMethod;
  }
  
  void cacheClass(String paramString, Class paramClass)
  {
    if (this.classCache == null) {
      this.classCache = new Hashtable();
    }
    this.classCache.put(paramString, paramClass);
  }
  
  public Class getClass(String paramString)
    throws UtilEvalError
  {
    Class localClass = getClassImpl(paramString);
    if (localClass != null) {
      return localClass;
    }
    if (this.parent != null) {
      return this.parent.getClass(paramString);
    }
    return null;
  }
  
  private Class getClassImpl(String paramString)
    throws UtilEvalError
  {
    Class localClass = null;
    if (this.classCache != null)
    {
      localClass = (Class)this.classCache.get(paramString);
      if (localClass != null) {
        return localClass;
      }
    }
    int i = !Name.isCompound(paramString) ? 1 : 0;
    if (i != 0)
    {
      if (localClass == null) {
        localClass = getImportedClassImpl(paramString);
      }
      if (localClass != null)
      {
        cacheClass(paramString, localClass);
        return localClass;
      }
    }
    localClass = classForName(paramString);
    if (localClass != null)
    {
      if (i != 0) {
        cacheClass(paramString, localClass);
      }
      return localClass;
    }
    if (Interpreter.DEBUG) {
      Interpreter.debug("getClass(): " + paramString + " not\tfound in " + this);
    }
    return null;
  }
  
  private Class getImportedClassImpl(String paramString)
    throws UtilEvalError
  {
    String str = null;
    if (this.importedClasses != null) {
      str = (String)this.importedClasses.get(paramString);
    }
    if (str != null)
    {
      Class localClass = classForName(str);
      if (localClass == null)
      {
        if (Name.isCompound(str)) {
          try
          {
            localClass = getNameResolver(str).toClass();
          }
          catch (ClassNotFoundException localClassNotFoundException) {}
        } else if (Interpreter.DEBUG) {
          Interpreter.debug("imported unpackaged name not found:" + str);
        }
        if (localClass != null)
        {
          getClassManager().cacheClassInfo(str, localClass);
          return localClass;
        }
      }
      else
      {
        return localClass;
      }
      return null;
    }
    Object localObject2;
    if (this.importedPackages != null) {
      for (int i = this.importedPackages.size() - 1; i >= 0; i--)
      {
        localObject1 = (String)this.importedPackages.elementAt(i) + "." + paramString;
        localObject2 = classForName((String)localObject1);
        if (localObject2 != null) {
          return (Class)localObject2;
        }
      }
    }
    Object localObject1 = getClassManager();
    if (((BshClassManager)localObject1).hasSuperImport())
    {
      localObject2 = ((BshClassManager)localObject1).getClassNameByUnqName(paramString);
      if (localObject2 != null) {
        return classForName((String)localObject2);
      }
    }
    return null;
  }
  
  private Class classForName(String paramString)
  {
    return getClassManager().classForName(paramString);
  }
  
  public String[] getAllNames()
  {
    Vector localVector = new Vector();
    getAllNamesAux(localVector);
    String[] arrayOfString = new String[localVector.size()];
    localVector.copyInto(arrayOfString);
    return arrayOfString;
  }
  
  protected void getAllNamesAux(Vector paramVector)
  {
    Enumeration localEnumeration1 = this.variables.keys();
    while (localEnumeration1.hasMoreElements()) {
      paramVector.addElement(localEnumeration1.nextElement());
    }
    Enumeration localEnumeration2 = this.methods.keys();
    while (localEnumeration2.hasMoreElements()) {
      paramVector.addElement(localEnumeration2.nextElement());
    }
    if (this.parent != null) {
      this.parent.getAllNamesAux(paramVector);
    }
  }
  
  public void addNameSourceListener(NameSource.Listener paramListener)
  {
    if (this.nameSourceListeners == null) {
      this.nameSourceListeners = new Vector();
    }
    this.nameSourceListeners.addElement(paramListener);
  }
  
  public void doSuperImport()
    throws UtilEvalError
  {
    getClassManager().doSuperImport();
  }
  
  public String toString()
  {
    return "NameSpace: " + (this.nsName == null ? super.toString() : new StringBuffer().append(this.nsName).append(" (").append(super.toString()).append(")").toString()) + (this.isClass ? " (isClass) " : "") + (this.isMethod ? " (method) " : "") + (this.classStatic != null ? " (class static) " : "") + (this.classInstance != null ? " (class instance) " : "");
  }
  
  private synchronized void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    this.names = null;
    paramObjectOutputStream.defaultWriteObject();
  }
  
  public Object invokeMethod(String paramString, Object[] paramArrayOfObject, Interpreter paramInterpreter)
    throws EvalError
  {
    return invokeMethod(paramString, paramArrayOfObject, paramInterpreter, null, null);
  }
  
  public Object invokeMethod(String paramString, Object[] paramArrayOfObject, Interpreter paramInterpreter, CallStack paramCallStack, SimpleNode paramSimpleNode)
    throws EvalError
  {
    return getThis(paramInterpreter).invokeMethod(paramString, paramArrayOfObject, paramInterpreter, paramCallStack, paramSimpleNode, false);
  }
  
  public void classLoaderChanged()
  {
    nameSpaceChanged();
  }
  
  public void nameSpaceChanged()
  {
    this.classCache = null;
    this.names = null;
  }
  
  public void loadDefaultImports()
  {
    importClass("bsh.EvalError");
    importClass("bsh.Interpreter");
    importPackage("javax.swing.event");
    importPackage("javax.swing");
    importPackage("java.awt.event");
    importPackage("java.awt");
    importPackage("java.net");
    importPackage("java.util");
    importPackage("java.io");
    importPackage("java.lang");
    importCommands("/bsh/commands");
  }
  
  Name getNameResolver(String paramString)
  {
    if (this.names == null) {
      this.names = new Hashtable();
    }
    Name localName = (Name)this.names.get(paramString);
    if (localName == null)
    {
      localName = new Name(this, paramString);
      this.names.put(paramString, localName);
    }
    return localName;
  }
  
  public int getInvocationLine()
  {
    SimpleNode localSimpleNode = getNode();
    if (localSimpleNode != null) {
      return localSimpleNode.getLineNumber();
    }
    return -1;
  }
  
  public String getInvocationText()
  {
    SimpleNode localSimpleNode = getNode();
    if (localSimpleNode != null) {
      return localSimpleNode.getText();
    }
    return "<invoked from Java code>";
  }
  
  public static Class identifierToClass(ClassIdentifier paramClassIdentifier)
  {
    return paramClassIdentifier.getTargetClass();
  }
  
  public void clear()
  {
    this.variables = null;
    this.methods = null;
    this.importedClasses = null;
    this.importedPackages = null;
    this.importedCommands = null;
    this.importedObjects = null;
    if (this.parent == null) {
      loadDefaultImports();
    }
    this.classCache = null;
    this.names = null;
  }
  
  public void importObject(Object paramObject)
  {
    if (this.importedObjects == null) {
      this.importedObjects = new Vector();
    }
    if (this.importedObjects.contains(paramObject)) {
      this.importedObjects.remove(paramObject);
    }
    this.importedObjects.addElement(paramObject);
    nameSpaceChanged();
  }
  
  public void importStatic(Class paramClass)
  {
    if (this.importedStatic == null) {
      this.importedStatic = new Vector();
    }
    if (this.importedStatic.contains(paramClass)) {
      this.importedStatic.remove(paramClass);
    }
    this.importedStatic.addElement(paramClass);
    nameSpaceChanged();
  }
  
  void setPackage(String paramString)
  {
    this.packageName = paramString;
  }
  
  String getPackage()
  {
    if (this.packageName != null) {
      return this.packageName;
    }
    if (this.parent != null) {
      return this.parent.getPackage();
    }
    return null;
  }
  
  static
  {
    JAVACODE.isMethod = true;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/NameSpace.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */