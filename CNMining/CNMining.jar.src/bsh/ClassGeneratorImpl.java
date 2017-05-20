package bsh;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClassGeneratorImpl
  extends ClassGenerator
{
  public Class generateClass(String paramString, Modifiers paramModifiers, Class[] paramArrayOfClass, Class paramClass, BSHBlock paramBSHBlock, boolean paramBoolean, CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    return generateClassImpl(paramString, paramModifiers, paramArrayOfClass, paramClass, paramBSHBlock, paramBoolean, paramCallStack, paramInterpreter);
  }
  
  public Object invokeSuperclassMethod(BshClassManager paramBshClassManager, Object paramObject, String paramString, Object[] paramArrayOfObject)
    throws UtilEvalError, ReflectError, InvocationTargetException
  {
    return invokeSuperclassMethodImpl(paramBshClassManager, paramObject, paramString, paramArrayOfObject);
  }
  
  public void setInstanceNameSpaceParent(Object paramObject, String paramString, NameSpace paramNameSpace)
  {
    This localThis = ClassGeneratorUtil.getClassInstanceThis(paramObject, paramString);
    localThis.getNameSpace().setParent(paramNameSpace);
  }
  
  public static Class generateClassImpl(String paramString, Modifiers paramModifiers, Class[] paramArrayOfClass, Class paramClass, BSHBlock paramBSHBlock, boolean paramBoolean, CallStack paramCallStack, Interpreter paramInterpreter)
    throws EvalError
  {
    try
    {
      Capabilities.setAccessibility(true);
    }
    catch (Capabilities.Unavailable localUnavailable)
    {
      throw new EvalError("Defining classes currently requires reflective Accessibility.", paramBSHBlock, paramCallStack);
    }
    NameSpace localNameSpace1 = paramCallStack.top();
    String str1 = localNameSpace1.getPackage();
    String str2 = localNameSpace1.isClass ? localNameSpace1.getName() + "$" + paramString : paramString;
    String str3 = str1 + "." + str2;
    BshClassManager localBshClassManager = paramInterpreter.getClassManager();
    localBshClassManager.definingClass(str3);
    NameSpace localNameSpace2 = new NameSpace(localNameSpace1, str2);
    localNameSpace2.isClass = true;
    paramCallStack.push(localNameSpace2);
    paramBSHBlock.evalBlock(paramCallStack, paramInterpreter, true, ClassNodeFilter.CLASSCLASSES);
    Variable[] arrayOfVariable = getDeclaredVariables(paramBSHBlock, paramCallStack, paramInterpreter, str1);
    DelayedEvalBshMethod[] arrayOfDelayedEvalBshMethod = getDeclaredMethods(paramBSHBlock, paramCallStack, paramInterpreter, str1);
    ClassGeneratorUtil localClassGeneratorUtil = new ClassGeneratorUtil(paramModifiers, str2, str1, paramClass, paramArrayOfClass, arrayOfVariable, arrayOfDelayedEvalBshMethod, localNameSpace2, paramBoolean);
    byte[] arrayOfByte = localClassGeneratorUtil.generateClass();
    String str4 = System.getProperty("debugClasses");
    if (str4 != null) {
      try
      {
        FileOutputStream localFileOutputStream = new FileOutputStream(str4 + "/" + str2 + ".class");
        localFileOutputStream.write(arrayOfByte);
        localFileOutputStream.close();
      }
      catch (IOException localIOException) {}
    }
    Class localClass = localBshClassManager.defineClass(str3, arrayOfByte);
    localNameSpace1.importClass(str3.replace('$', '.'));
    try
    {
      localNameSpace2.setLocalVariable("_bshInstanceInitializer", paramBSHBlock, false);
    }
    catch (UtilEvalError localUtilEvalError)
    {
      throw new InterpreterError("unable to init static: " + localUtilEvalError);
    }
    localNameSpace2.setClassStatic(localClass);
    paramBSHBlock.evalBlock(paramCallStack, paramInterpreter, true, ClassNodeFilter.CLASSSTATIC);
    paramCallStack.pop();
    if (!localClass.isInterface())
    {
      String str5 = "_bshStatic" + str2;
      try
      {
        LHS localLHS = Reflect.getLHSStaticField(localClass, str5);
        localLHS.assign(localNameSpace2.getThis(paramInterpreter), false);
      }
      catch (Exception localException)
      {
        throw new InterpreterError("Error in class gen setup: " + localException);
      }
    }
    localBshClassManager.doneDefiningClass(str3);
    return localClass;
  }
  
  static Variable[] getDeclaredVariables(BSHBlock paramBSHBlock, CallStack paramCallStack, Interpreter paramInterpreter, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < paramBSHBlock.jjtGetNumChildren(); i++)
    {
      SimpleNode localSimpleNode = (SimpleNode)paramBSHBlock.jjtGetChild(i);
      if ((localSimpleNode instanceof BSHTypedVariableDeclaration))
      {
        BSHTypedVariableDeclaration localBSHTypedVariableDeclaration = (BSHTypedVariableDeclaration)localSimpleNode;
        Modifiers localModifiers = localBSHTypedVariableDeclaration.modifiers;
        String str1 = localBSHTypedVariableDeclaration.getTypeDescriptor(paramCallStack, paramInterpreter, paramString);
        BSHVariableDeclarator[] arrayOfBSHVariableDeclarator = localBSHTypedVariableDeclaration.getDeclarators();
        for (int j = 0; j < arrayOfBSHVariableDeclarator.length; j++)
        {
          String str2 = arrayOfBSHVariableDeclarator[j].name;
          try
          {
            Variable localVariable = new Variable(str2, str1, null, localModifiers);
            localArrayList.add(localVariable);
          }
          catch (UtilEvalError localUtilEvalError) {}
        }
      }
    }
    return (Variable[])localArrayList.toArray(new Variable[0]);
  }
  
  static DelayedEvalBshMethod[] getDeclaredMethods(BSHBlock paramBSHBlock, CallStack paramCallStack, Interpreter paramInterpreter, String paramString)
    throws EvalError
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < paramBSHBlock.jjtGetNumChildren(); i++)
    {
      SimpleNode localSimpleNode = (SimpleNode)paramBSHBlock.jjtGetChild(i);
      if ((localSimpleNode instanceof BSHMethodDeclaration))
      {
        BSHMethodDeclaration localBSHMethodDeclaration = (BSHMethodDeclaration)localSimpleNode;
        localBSHMethodDeclaration.insureNodesParsed();
        Modifiers localModifiers = localBSHMethodDeclaration.modifiers;
        String str1 = localBSHMethodDeclaration.name;
        String str2 = localBSHMethodDeclaration.getReturnTypeDescriptor(paramCallStack, paramInterpreter, paramString);
        BSHReturnType localBSHReturnType = localBSHMethodDeclaration.getReturnTypeNode();
        BSHFormalParameters localBSHFormalParameters = localBSHMethodDeclaration.paramsNode;
        String[] arrayOfString = localBSHFormalParameters.getTypeDescriptors(paramCallStack, paramInterpreter, paramString);
        DelayedEvalBshMethod localDelayedEvalBshMethod = new DelayedEvalBshMethod(str1, str2, localBSHReturnType, localBSHMethodDeclaration.paramsNode.getParamNames(), arrayOfString, localBSHFormalParameters, localBSHMethodDeclaration.blockNode, null, localModifiers, paramCallStack, paramInterpreter);
        localArrayList.add(localDelayedEvalBshMethod);
      }
    }
    return (DelayedEvalBshMethod[])localArrayList.toArray(new DelayedEvalBshMethod[0]);
  }
  
  public static Object invokeSuperclassMethodImpl(BshClassManager paramBshClassManager, Object paramObject, String paramString, Object[] paramArrayOfObject)
    throws UtilEvalError, ReflectError, InvocationTargetException
  {
    String str = "_bshSuper" + paramString;
    Class localClass1 = paramObject.getClass();
    Method localMethod = Reflect.resolveJavaMethod(paramBshClassManager, localClass1, str, Types.getTypes(paramArrayOfObject), false);
    if (localMethod != null) {
      return Reflect.invokeMethod(localMethod, paramObject, paramArrayOfObject);
    }
    Class localClass2 = localClass1.getSuperclass();
    localMethod = Reflect.resolveExpectedJavaMethod(paramBshClassManager, localClass2, paramObject, paramString, paramArrayOfObject, false);
    return Reflect.invokeMethod(localMethod, paramObject, paramArrayOfObject);
  }
  
  static class ClassNodeFilter
    implements BSHBlock.NodeFilter
  {
    public static final int STATIC = 0;
    public static final int INSTANCE = 1;
    public static final int CLASSES = 2;
    public static ClassNodeFilter CLASSSTATIC = new ClassNodeFilter(0);
    public static ClassNodeFilter CLASSINSTANCE = new ClassNodeFilter(1);
    public static ClassNodeFilter CLASSCLASSES = new ClassNodeFilter(2);
    int context;
    
    private ClassNodeFilter(int paramInt)
    {
      this.context = paramInt;
    }
    
    public boolean isVisible(SimpleNode paramSimpleNode)
    {
      if (this.context == 2) {
        return paramSimpleNode instanceof BSHClassDeclaration;
      }
      if ((paramSimpleNode instanceof BSHClassDeclaration)) {
        return false;
      }
      if (this.context == 0) {
        return isStatic(paramSimpleNode);
      }
      if (this.context == 1) {
        return !isStatic(paramSimpleNode);
      }
      return true;
    }
    
    boolean isStatic(SimpleNode paramSimpleNode)
    {
      if ((paramSimpleNode instanceof BSHTypedVariableDeclaration)) {
        return (((BSHTypedVariableDeclaration)paramSimpleNode).modifiers != null) && (((BSHTypedVariableDeclaration)paramSimpleNode).modifiers.hasModifier("static"));
      }
      if ((paramSimpleNode instanceof BSHMethodDeclaration)) {
        return (((BSHMethodDeclaration)paramSimpleNode).modifiers != null) && (((BSHMethodDeclaration)paramSimpleNode).modifiers.hasModifier("static"));
      }
      return !(paramSimpleNode instanceof BSHBlock);
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/ClassGeneratorImpl.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */