package bsh;

import bsh.org.objectweb.asm.ClassWriter;
import bsh.org.objectweb.asm.CodeVisitor;
import bsh.org.objectweb.asm.Constants;
import bsh.org.objectweb.asm.Label;
import bsh.org.objectweb.asm.Type;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClassGeneratorUtil
  implements Constants
{
  static final String BSHSTATIC = "_bshStatic";
  static final String BSHTHIS = "_bshThis";
  static final String BSHSUPER = "_bshSuper";
  static final String BSHINIT = "_bshInstanceInitializer";
  static final String BSHCONSTRUCTORS = "_bshConstructors";
  static final int DEFAULTCONSTRUCTOR = -1;
  static final String OBJECT = "Ljava/lang/Object;";
  String className;
  String fqClassName;
  Class superClass;
  String superClassName;
  Class[] interfaces;
  Variable[] vars;
  Constructor[] superConstructors;
  DelayedEvalBshMethod[] constructors;
  DelayedEvalBshMethod[] methods;
  NameSpace classStaticNameSpace;
  Modifiers classModifiers;
  boolean isInterface;
  
  public ClassGeneratorUtil(Modifiers paramModifiers, String paramString1, String paramString2, Class paramClass, Class[] paramArrayOfClass, Variable[] paramArrayOfVariable, DelayedEvalBshMethod[] paramArrayOfDelayedEvalBshMethod, NameSpace paramNameSpace, boolean paramBoolean)
  {
    this.classModifiers = paramModifiers;
    this.className = paramString1;
    if (paramString2 != null) {
      this.fqClassName = (paramString2.replace('.', '/') + "/" + paramString1);
    } else {
      this.fqClassName = paramString1;
    }
    if (paramClass == null) {
      paramClass = Object.class;
    }
    this.superClass = paramClass;
    this.superClassName = Type.getInternalName(paramClass);
    if (paramArrayOfClass == null) {
      paramArrayOfClass = new Class[0];
    }
    this.interfaces = paramArrayOfClass;
    this.vars = paramArrayOfVariable;
    this.classStaticNameSpace = paramNameSpace;
    this.superConstructors = paramClass.getDeclaredConstructors();
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    String str = getBaseName(paramString1);
    for (int i = 0; i < paramArrayOfDelayedEvalBshMethod.length; i++) {
      if (paramArrayOfDelayedEvalBshMethod[i].getName().equals(str)) {
        localArrayList1.add(paramArrayOfDelayedEvalBshMethod[i]);
      } else {
        localArrayList2.add(paramArrayOfDelayedEvalBshMethod[i]);
      }
    }
    this.constructors = ((DelayedEvalBshMethod[])localArrayList1.toArray(new DelayedEvalBshMethod[0]));
    this.methods = ((DelayedEvalBshMethod[])localArrayList2.toArray(new DelayedEvalBshMethod[0]));
    try
    {
      paramNameSpace.setLocalVariable("_bshConstructors", this.constructors, false);
    }
    catch (UtilEvalError localUtilEvalError)
    {
      throw new InterpreterError("can't set cons var");
    }
    this.isInterface = paramBoolean;
  }
  
  public byte[] generateClass()
  {
    int i = getASMModifiers(this.classModifiers) | 0x1;
    if (this.isInterface) {
      i |= 0x200;
    }
    String[] arrayOfString = new String[this.interfaces.length];
    for (int j = 0; j < this.interfaces.length; j++) {
      arrayOfString[j] = Type.getInternalName(this.interfaces[j]);
    }
    String str1 = "BeanShell Generated via ASM (www.objectweb.org)";
    ClassWriter localClassWriter = new ClassWriter(false);
    localClassWriter.visit(i, this.fqClassName, this.superClassName, arrayOfString, str1);
    if (!this.isInterface)
    {
      generateField("_bshThis" + this.className, "Lbsh/This;", 1, localClassWriter);
      generateField("_bshStatic" + this.className, "Lbsh/This;", 9, localClassWriter);
    }
    for (int k = 0; k < this.vars.length; k++)
    {
      String str2 = this.vars[k].getTypeDescriptor();
      if ((!this.vars[k].hasModifier("private")) && (str2 != null))
      {
        if (this.isInterface) {
          n = 25;
        } else {
          n = getASMModifiers(this.vars[k].getModifiers());
        }
        generateField(this.vars[k].getName(), str2, n, localClassWriter);
      }
    }
    int m = 0;
    for (int n = 0; n < this.constructors.length; n++) {
      if (!this.constructors[n].hasModifier("private"))
      {
        i1 = getASMModifiers(this.constructors[n].getModifiers());
        generateConstructor(n, this.constructors[n].getParamTypeDescriptors(), i1, localClassWriter);
        m = 1;
      }
    }
    if ((!this.isInterface) && (m == 0)) {
      generateConstructor(-1, new String[0], 1, localClassWriter);
    }
    for (int i1 = 0; i1 < this.methods.length; i1++)
    {
      String str3 = this.methods[i1].getReturnTypeDescriptor();
      if (!this.methods[i1].hasModifier("private"))
      {
        int i2 = getASMModifiers(this.methods[i1].getModifiers());
        if (this.isInterface) {
          i2 |= 0x401;
        }
        generateMethod(this.className, this.fqClassName, this.methods[i1].getName(), str3, this.methods[i1].getParamTypeDescriptors(), i2, localClassWriter);
        int i3 = (i2 & 0x8) > 0 ? 1 : 0;
        boolean bool = classContainsMethod(this.superClass, this.methods[i1].getName(), this.methods[i1].getParamTypeDescriptors());
        if ((i3 == 0) && (bool)) {
          generateSuperDelegateMethod(this.superClassName, this.methods[i1].getName(), str3, this.methods[i1].getParamTypeDescriptors(), i2, localClassWriter);
        }
      }
    }
    return localClassWriter.toByteArray();
  }
  
  static int getASMModifiers(Modifiers paramModifiers)
  {
    int i = 0;
    if (paramModifiers == null) {
      return i;
    }
    if (paramModifiers.hasModifier("public")) {
      i++;
    }
    if (paramModifiers.hasModifier("protected")) {
      i += 4;
    }
    if (paramModifiers.hasModifier("static")) {
      i += 8;
    }
    if (paramModifiers.hasModifier("synchronized")) {
      i += 32;
    }
    if (paramModifiers.hasModifier("abstract")) {
      i += 1024;
    }
    return i;
  }
  
  static void generateField(String paramString1, String paramString2, int paramInt, ClassWriter paramClassWriter)
  {
    paramClassWriter.visitField(paramInt, paramString1, paramString2, null);
  }
  
  static void generateMethod(String paramString1, String paramString2, String paramString3, String paramString4, String[] paramArrayOfString, int paramInt, ClassWriter paramClassWriter)
  {
    String[] arrayOfString = null;
    boolean bool = (paramInt & 0x8) != 0;
    if (paramString4 == null) {
      paramString4 = "Ljava/lang/Object;";
    }
    String str = getMethodDescriptor(paramString4, paramArrayOfString);
    CodeVisitor localCodeVisitor = paramClassWriter.visitMethod(paramInt, paramString3, str, arrayOfString);
    if ((paramInt & 0x400) != 0) {
      return;
    }
    if (bool)
    {
      localCodeVisitor.visitFieldInsn(178, paramString2, "_bshStatic" + paramString1, "Lbsh/This;");
    }
    else
    {
      localCodeVisitor.visitVarInsn(25, 0);
      localCodeVisitor.visitFieldInsn(180, paramString2, "_bshThis" + paramString1, "Lbsh/This;");
    }
    localCodeVisitor.visitLdcInsn(paramString3);
    generateParameterReifierCode(paramArrayOfString, bool, localCodeVisitor);
    localCodeVisitor.visitInsn(1);
    localCodeVisitor.visitInsn(1);
    localCodeVisitor.visitInsn(1);
    localCodeVisitor.visitInsn(4);
    localCodeVisitor.visitMethodInsn(182, "bsh/This", "invokeMethod", Type.getMethodDescriptor(Type.getType(class$java$lang$Object), new Type[] { Type.getType(String.class), Type.getType(new Object[0].getClass()), Type.getType(Interpreter.class), Type.getType(CallStack.class), Type.getType(SimpleNode.class), Type.getType(Boolean.TYPE) }));
    localCodeVisitor.visitMethodInsn(184, "bsh/Primitive", "unwrap", "(Ljava/lang/Object;)Ljava/lang/Object;");
    generateReturnCode(paramString4, localCodeVisitor);
    localCodeVisitor.visitMaxs(20, 20);
  }
  
  void generateConstructor(int paramInt1, String[] paramArrayOfString, int paramInt2, ClassWriter paramClassWriter)
  {
    int i = paramArrayOfString.length + 1;
    int j = paramArrayOfString.length + 2;
    String[] arrayOfString = null;
    String str = getMethodDescriptor("V", paramArrayOfString);
    CodeVisitor localCodeVisitor = paramClassWriter.visitMethod(paramInt2, "<init>", str, arrayOfString);
    generateParameterReifierCode(paramArrayOfString, false, localCodeVisitor);
    localCodeVisitor.visitVarInsn(58, i);
    generateConstructorSwitch(paramInt1, i, j, localCodeVisitor);
    localCodeVisitor.visitVarInsn(25, 0);
    localCodeVisitor.visitLdcInsn(this.className);
    localCodeVisitor.visitVarInsn(25, i);
    localCodeVisitor.visitMethodInsn(184, "bsh/ClassGeneratorUtil", "initInstance", "(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V");
    localCodeVisitor.visitInsn(177);
    localCodeVisitor.visitMaxs(20, 20);
  }
  
  void generateConstructorSwitch(int paramInt1, int paramInt2, int paramInt3, CodeVisitor paramCodeVisitor)
  {
    Label localLabel1 = new Label();
    Label localLabel2 = new Label();
    int i = this.superConstructors.length + this.constructors.length;
    Label[] arrayOfLabel = new Label[i];
    for (int j = 0; j < i; j++) {
      arrayOfLabel[j] = new Label();
    }
    paramCodeVisitor.visitLdcInsn(this.superClass.getName());
    paramCodeVisitor.visitFieldInsn(178, this.fqClassName, "_bshStatic" + this.className, "Lbsh/This;");
    paramCodeVisitor.visitVarInsn(25, paramInt2);
    paramCodeVisitor.visitIntInsn(16, paramInt1);
    paramCodeVisitor.visitMethodInsn(184, "bsh/ClassGeneratorUtil", "getConstructorArgs", "(Ljava/lang/String;Lbsh/This;[Ljava/lang/Object;I)Lbsh/ClassGeneratorUtil$ConstructorArgs;");
    paramCodeVisitor.visitVarInsn(58, paramInt3);
    paramCodeVisitor.visitVarInsn(25, paramInt3);
    paramCodeVisitor.visitFieldInsn(180, "bsh/ClassGeneratorUtil$ConstructorArgs", "selector", "I");
    paramCodeVisitor.visitTableSwitchInsn(0, i - 1, localLabel1, arrayOfLabel);
    int k = 0;
    int m = 0;
    while (m < this.superConstructors.length)
    {
      doSwitchBranch(k, this.superClassName, getTypeDescriptors(this.superConstructors[m].getParameterTypes()), localLabel2, arrayOfLabel, paramInt3, paramCodeVisitor);
      m++;
      k++;
    }
    int n = 0;
    while (n < this.constructors.length)
    {
      doSwitchBranch(k, this.fqClassName, this.constructors[n].getParamTypeDescriptors(), localLabel2, arrayOfLabel, paramInt3, paramCodeVisitor);
      n++;
      k++;
    }
    paramCodeVisitor.visitLabel(localLabel1);
    paramCodeVisitor.visitVarInsn(25, 0);
    paramCodeVisitor.visitMethodInsn(183, this.superClassName, "<init>", "()V");
    paramCodeVisitor.visitLabel(localLabel2);
  }
  
  static void doSwitchBranch(int paramInt1, String paramString, String[] paramArrayOfString, Label paramLabel, Label[] paramArrayOfLabel, int paramInt2, CodeVisitor paramCodeVisitor)
  {
    paramCodeVisitor.visitLabel(paramArrayOfLabel[paramInt1]);
    paramCodeVisitor.visitVarInsn(25, 0);
    for (int i = 0; i < paramArrayOfString.length; i++)
    {
      str1 = paramArrayOfString[i];
      String str2 = null;
      if (str1.equals("Z")) {
        str2 = "getBoolean";
      } else if (str1.equals("B")) {
        str2 = "getByte";
      } else if (str1.equals("C")) {
        str2 = "getChar";
      } else if (str1.equals("S")) {
        str2 = "getShort";
      } else if (str1.equals("I")) {
        str2 = "getInt";
      } else if (str1.equals("J")) {
        str2 = "getLong";
      } else if (str1.equals("D")) {
        str2 = "getDouble";
      } else if (str1.equals("F")) {
        str2 = "getFloat";
      } else {
        str2 = "getObject";
      }
      paramCodeVisitor.visitVarInsn(25, paramInt2);
      String str3 = "bsh/ClassGeneratorUtil$ConstructorArgs";
      String str4;
      if (str2.equals("getObject")) {
        str4 = "Ljava/lang/Object;";
      } else {
        str4 = str1;
      }
      paramCodeVisitor.visitMethodInsn(182, str3, str2, "()" + str4);
      if (str2.equals("getObject")) {
        paramCodeVisitor.visitTypeInsn(192, descriptorToClassName(str1));
      }
    }
    String str1 = getMethodDescriptor("V", paramArrayOfString);
    paramCodeVisitor.visitMethodInsn(183, paramString, "<init>", str1);
    paramCodeVisitor.visitJumpInsn(167, paramLabel);
  }
  
  static String getMethodDescriptor(String paramString, String[] paramArrayOfString)
  {
    StringBuffer localStringBuffer = new StringBuffer("(");
    for (int i = 0; i < paramArrayOfString.length; i++) {
      localStringBuffer.append(paramArrayOfString[i]);
    }
    localStringBuffer.append(")" + paramString);
    return localStringBuffer.toString();
  }
  
  static void generateSuperDelegateMethod(String paramString1, String paramString2, String paramString3, String[] paramArrayOfString, int paramInt, ClassWriter paramClassWriter)
  {
    String[] arrayOfString = null;
    if (paramString3 == null) {
      paramString3 = "Ljava/lang/Object;";
    }
    String str = getMethodDescriptor(paramString3, paramArrayOfString);
    CodeVisitor localCodeVisitor = paramClassWriter.visitMethod(paramInt, "_bshSuper" + paramString2, str, arrayOfString);
    localCodeVisitor.visitVarInsn(25, 0);
    int i = 1;
    for (int j = 0; j < paramArrayOfString.length; j++)
    {
      if (isPrimitive(paramArrayOfString[j])) {
        localCodeVisitor.visitVarInsn(21, i);
      } else {
        localCodeVisitor.visitVarInsn(25, i);
      }
      i += ((paramArrayOfString[j].equals("D")) || (paramArrayOfString[j].equals("J")) ? 2 : 1);
    }
    localCodeVisitor.visitMethodInsn(183, paramString1, paramString2, str);
    generatePlainReturnCode(paramString3, localCodeVisitor);
    localCodeVisitor.visitMaxs(20, 20);
  }
  
  boolean classContainsMethod(Class paramClass, String paramString, String[] paramArrayOfString)
  {
    while (paramClass != null)
    {
      Method[] arrayOfMethod = paramClass.getDeclaredMethods();
      for (int i = 0; i < arrayOfMethod.length; i++) {
        if (arrayOfMethod[i].getName().equals(paramString))
        {
          String[] arrayOfString = getTypeDescriptors(arrayOfMethod[i].getParameterTypes());
          int j = 1;
          for (int k = 0; k < arrayOfString.length; k++) {
            if (!paramArrayOfString[k].equals(arrayOfString[k]))
            {
              j = 0;
              break;
            }
          }
          if (j != 0) {
            return true;
          }
        }
      }
      paramClass = paramClass.getSuperclass();
    }
    return false;
  }
  
  static void generatePlainReturnCode(String paramString, CodeVisitor paramCodeVisitor)
  {
    if (paramString.equals("V"))
    {
      paramCodeVisitor.visitInsn(177);
    }
    else if (isPrimitive(paramString))
    {
      int i = 172;
      if (paramString.equals("D")) {
        i = 175;
      } else if (paramString.equals("F")) {
        i = 174;
      } else if (paramString.equals("J")) {
        i = 173;
      }
      paramCodeVisitor.visitInsn(i);
    }
    else
    {
      paramCodeVisitor.visitTypeInsn(192, descriptorToClassName(paramString));
      paramCodeVisitor.visitInsn(176);
    }
  }
  
  public static void generateParameterReifierCode(String[] paramArrayOfString, boolean paramBoolean, CodeVisitor paramCodeVisitor)
  {
    paramCodeVisitor.visitIntInsn(17, paramArrayOfString.length);
    paramCodeVisitor.visitTypeInsn(189, "java/lang/Object");
    int i = paramBoolean ? 0 : 1;
    for (int j = 0; j < paramArrayOfString.length; j++)
    {
      String str1 = paramArrayOfString[j];
      paramCodeVisitor.visitInsn(89);
      paramCodeVisitor.visitIntInsn(17, j);
      if (isPrimitive(str1))
      {
        int k;
        if (str1.equals("F")) {
          k = 23;
        } else if (str1.equals("D")) {
          k = 24;
        } else if (str1.equals("J")) {
          k = 22;
        } else {
          k = 21;
        }
        String str2 = "bsh/Primitive";
        paramCodeVisitor.visitTypeInsn(187, str2);
        paramCodeVisitor.visitInsn(89);
        paramCodeVisitor.visitVarInsn(k, i);
        String str3 = str1;
        paramCodeVisitor.visitMethodInsn(183, str2, "<init>", "(" + str3 + ")V");
      }
      else
      {
        paramCodeVisitor.visitVarInsn(25, i);
      }
      paramCodeVisitor.visitInsn(83);
      i += ((str1.equals("D")) || (str1.equals("J")) ? 2 : 1);
    }
  }
  
  public static void generateReturnCode(String paramString, CodeVisitor paramCodeVisitor)
  {
    if (paramString.equals("V"))
    {
      paramCodeVisitor.visitInsn(87);
      paramCodeVisitor.visitInsn(177);
    }
    else if (isPrimitive(paramString))
    {
      int i = 172;
      String str1;
      String str2;
      if (paramString.equals("B"))
      {
        str1 = "java/lang/Byte";
        str2 = "byteValue";
      }
      else if (paramString.equals("I"))
      {
        str1 = "java/lang/Integer";
        str2 = "intValue";
      }
      else if (paramString.equals("Z"))
      {
        str1 = "java/lang/Boolean";
        str2 = "booleanValue";
      }
      else if (paramString.equals("D"))
      {
        i = 175;
        str1 = "java/lang/Double";
        str2 = "doubleValue";
      }
      else if (paramString.equals("F"))
      {
        i = 174;
        str1 = "java/lang/Float";
        str2 = "floatValue";
      }
      else if (paramString.equals("J"))
      {
        i = 173;
        str1 = "java/lang/Long";
        str2 = "longValue";
      }
      else if (paramString.equals("C"))
      {
        str1 = "java/lang/Character";
        str2 = "charValue";
      }
      else
      {
        str1 = "java/lang/Short";
        str2 = "shortValue";
      }
      String str3 = paramString;
      paramCodeVisitor.visitTypeInsn(192, str1);
      paramCodeVisitor.visitMethodInsn(182, str1, str2, "()" + str3);
      paramCodeVisitor.visitInsn(i);
    }
    else
    {
      paramCodeVisitor.visitTypeInsn(192, descriptorToClassName(paramString));
      paramCodeVisitor.visitInsn(176);
    }
  }
  
  public static ConstructorArgs getConstructorArgs(String paramString, This paramThis, Object[] paramArrayOfObject, int paramInt)
  {
    DelayedEvalBshMethod[] arrayOfDelayedEvalBshMethod;
    try
    {
      arrayOfDelayedEvalBshMethod = (DelayedEvalBshMethod[])paramThis.getNameSpace().getVariable("_bshConstructors");
    }
    catch (Exception localException)
    {
      throw new InterpreterError("unable to get instance initializer: " + localException);
    }
    if (paramInt == -1) {
      return ConstructorArgs.DEFAULT;
    }
    DelayedEvalBshMethod localDelayedEvalBshMethod = arrayOfDelayedEvalBshMethod[paramInt];
    if (localDelayedEvalBshMethod.methodBody.jjtGetNumChildren() == 0) {
      return ConstructorArgs.DEFAULT;
    }
    String str = null;
    BSHArguments localBSHArguments = null;
    SimpleNode localSimpleNode = (SimpleNode)localDelayedEvalBshMethod.methodBody.jjtGetChild(0);
    if ((localSimpleNode instanceof BSHPrimaryExpression)) {
      localSimpleNode = (SimpleNode)localSimpleNode.jjtGetChild(0);
    }
    if ((localSimpleNode instanceof BSHMethodInvocation))
    {
      localObject1 = (BSHMethodInvocation)localSimpleNode;
      localObject2 = ((BSHMethodInvocation)localObject1).getNameNode();
      if ((((BSHAmbiguousName)localObject2).text.equals("super")) || (((BSHAmbiguousName)localObject2).text.equals("this")))
      {
        str = ((BSHAmbiguousName)localObject2).text;
        localBSHArguments = ((BSHMethodInvocation)localObject1).getArgsNode();
      }
    }
    if (str == null) {
      return ConstructorArgs.DEFAULT;
    }
    Object localObject1 = new NameSpace(paramThis.getNameSpace(), "consArgs");
    Object localObject2 = localDelayedEvalBshMethod.getParameterNames();
    Class[] arrayOfClass1 = localDelayedEvalBshMethod.getParameterTypes();
    for (int i = 0; i < paramArrayOfObject.length; i++) {
      try
      {
        ((NameSpace)localObject1).setTypedVariable(localObject2[i], arrayOfClass1[i], paramArrayOfObject[i], null);
      }
      catch (UtilEvalError localUtilEvalError)
      {
        throw new InterpreterError("err setting local cons arg:" + localUtilEvalError);
      }
    }
    CallStack localCallStack = new CallStack();
    localCallStack.push((NameSpace)localObject1);
    Object[] arrayOfObject = null;
    Interpreter localInterpreter = paramThis.declaringInterpreter;
    try
    {
      arrayOfObject = localBSHArguments.getArguments(localCallStack, localInterpreter);
    }
    catch (EvalError localEvalError)
    {
      throw new InterpreterError("Error evaluating constructor args: " + localEvalError);
    }
    Class[] arrayOfClass2 = Types.getTypes(arrayOfObject);
    arrayOfObject = Primitive.unwrap(arrayOfObject);
    Class localClass = localInterpreter.getClassManager().classForName(paramString);
    if (localClass == null) {
      throw new InterpreterError("can't find superclass: " + paramString);
    }
    Constructor[] arrayOfConstructor = localClass.getDeclaredConstructors();
    if (str.equals("super"))
    {
      int j = Reflect.findMostSpecificConstructorIndex(arrayOfClass2, arrayOfConstructor);
      if (j == -1) {
        throw new InterpreterError("can't find constructor for args!");
      }
      return new ConstructorArgs(j, arrayOfObject);
    }
    Class[][] arrayOfClass = new Class[arrayOfDelayedEvalBshMethod.length][];
    for (int k = 0; k < arrayOfClass.length; k++) {
      arrayOfClass[k] = arrayOfDelayedEvalBshMethod[k].getParameterTypes();
    }
    int m = Reflect.findMostSpecificSignature(arrayOfClass2, arrayOfClass);
    if (m == -1) {
      throw new InterpreterError("can't find constructor for args 2!");
    }
    int n = m + arrayOfConstructor.length;
    int i1 = paramInt + arrayOfConstructor.length;
    if (n == i1) {
      throw new InterpreterError("Recusive constructor call.");
    }
    return new ConstructorArgs(n, arrayOfObject);
  }
  
  public static void initInstance(Object paramObject, String paramString, Object[] paramArrayOfObject)
  {
    Class[] arrayOfClass = Types.getTypes(paramArrayOfObject);
    CallStack localCallStack = new CallStack();
    This localThis = getClassInstanceThis(paramObject, paramString);
    Interpreter localInterpreter;
    Object localObject2;
    NameSpace localNameSpace;
    if (localThis == null)
    {
      localObject1 = getClassStaticThis(paramObject.getClass(), paramString);
      localInterpreter = ((This)localObject1).declaringInterpreter;
      try
      {
        localObject2 = (BSHBlock)((This)localObject1).getNameSpace().getVariable("_bshInstanceInitializer");
      }
      catch (Exception localException3)
      {
        throw new InterpreterError("unable to get instance initializer: " + localException3);
      }
      localNameSpace = new NameSpace(((This)localObject1).getNameSpace(), paramString);
      localNameSpace.isClass = true;
      localThis = localNameSpace.getThis(localInterpreter);
      try
      {
        LHS localLHS = Reflect.getLHSObjectField(paramObject, "_bshThis" + paramString);
        localLHS.assign(localThis, false);
      }
      catch (Exception localException4)
      {
        throw new InterpreterError("Error in class gen setup: " + localException4);
      }
      localNameSpace.setClassInstance(paramObject);
      localCallStack.push(localNameSpace);
      try
      {
        ((BSHBlock)localObject2).evalBlock(localCallStack, localInterpreter, true, ClassGeneratorImpl.ClassNodeFilter.CLASSINSTANCE);
      }
      catch (Exception localException5)
      {
        throw new InterpreterError("Error in class initialization: " + localException5);
      }
      localCallStack.pop();
    }
    else
    {
      localInterpreter = localThis.declaringInterpreter;
      localNameSpace = localThis.getNameSpace();
    }
    Object localObject1 = getBaseName(paramString);
    try
    {
      localObject2 = localNameSpace.getMethod((String)localObject1, arrayOfClass, true);
      if ((paramArrayOfObject.length > 0) && (localObject2 == null)) {
        throw new InterpreterError("Can't find constructor: " + paramString);
      }
      if (localObject2 != null) {
        ((BshMethod)localObject2).invoke(paramArrayOfObject, localInterpreter, localCallStack, null, false);
      }
    }
    catch (Exception localException1)
    {
      Exception localException2;
      if ((localException1 instanceof TargetError)) {
        localException2 = (Exception)((TargetError)localException1).getTarget();
      }
      if ((localException2 instanceof InvocationTargetException)) {
        localException2 = (Exception)((InvocationTargetException)localException2).getTargetException();
      }
      localException2.printStackTrace(System.err);
      throw new InterpreterError("Error in class initialization: " + localException2);
    }
  }
  
  static This getClassStaticThis(Class paramClass, String paramString)
  {
    try
    {
      return (This)Reflect.getStaticFieldValue(paramClass, "_bshStatic" + paramString);
    }
    catch (Exception localException)
    {
      throw new InterpreterError("Unable to get class static space: " + localException);
    }
  }
  
  static This getClassInstanceThis(Object paramObject, String paramString)
  {
    try
    {
      Object localObject = Reflect.getObjectFieldValue(paramObject, "_bshThis" + paramString);
      return (This)Primitive.unwrap(localObject);
    }
    catch (Exception localException)
    {
      throw new InterpreterError("Generated class: Error getting This" + localException);
    }
  }
  
  private static boolean isPrimitive(String paramString)
  {
    return paramString.length() == 1;
  }
  
  static String[] getTypeDescriptors(Class[] paramArrayOfClass)
  {
    String[] arrayOfString = new String[paramArrayOfClass.length];
    for (int i = 0; i < arrayOfString.length; i++) {
      arrayOfString[i] = BSHType.getTypeDescriptor(paramArrayOfClass[i]);
    }
    return arrayOfString;
  }
  
  private static String descriptorToClassName(String paramString)
  {
    if ((paramString.startsWith("[")) || (!paramString.startsWith("L"))) {
      return paramString;
    }
    return paramString.substring(1, paramString.length() - 1);
  }
  
  private static String getBaseName(String paramString)
  {
    int i = paramString.indexOf("$");
    if (i == -1) {
      return paramString;
    }
    return paramString.substring(i + 1);
  }
  
  public static class ConstructorArgs
  {
    public static ConstructorArgs DEFAULT = new ConstructorArgs();
    public int selector = -1;
    Object[] args;
    int arg = 0;
    
    ConstructorArgs() {}
    
    ConstructorArgs(int paramInt, Object[] paramArrayOfObject)
    {
      this.selector = paramInt;
      this.args = paramArrayOfObject;
    }
    
    Object next()
    {
      return this.args[(this.arg++)];
    }
    
    public boolean getBoolean()
    {
      return ((Boolean)next()).booleanValue();
    }
    
    public byte getByte()
    {
      return ((Byte)next()).byteValue();
    }
    
    public char getChar()
    {
      return ((Character)next()).charValue();
    }
    
    public short getShort()
    {
      return ((Short)next()).shortValue();
    }
    
    public int getInt()
    {
      return ((Integer)next()).intValue();
    }
    
    public long getLong()
    {
      return ((Long)next()).longValue();
    }
    
    public double getDouble()
    {
      return ((Double)next()).doubleValue();
    }
    
    public float getFloat()
    {
      return ((Float)next()).floatValue();
    }
    
    public Object getObject()
    {
      return next();
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/ClassGeneratorUtil.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */