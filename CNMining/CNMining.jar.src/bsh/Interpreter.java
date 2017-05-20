package bsh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Interpreter
  implements Runnable, ConsoleInterface, Serializable
{
  public static final String VERSION = "2.0b4";
  public static boolean DEBUG;
  public static boolean TRACE;
  public static boolean LOCALSCOPING;
  static transient PrintStream debug;
  static String systemLineSeparator = "\n";
  static This sharedObject;
  private boolean strictJava = false;
  transient Parser parser;
  NameSpace globalNameSpace;
  transient Reader in;
  transient PrintStream out;
  transient PrintStream err;
  ConsoleInterface console;
  Interpreter parent;
  String sourceFileInfo;
  private boolean exitOnEOF = true;
  protected boolean evalOnly;
  protected boolean interactive;
  private boolean showResults;
  
  public Interpreter(Reader paramReader, PrintStream paramPrintStream1, PrintStream paramPrintStream2, boolean paramBoolean, NameSpace paramNameSpace, Interpreter paramInterpreter, String paramString)
  {
    this.parser = new Parser(paramReader);
    long l1 = System.currentTimeMillis();
    this.in = paramReader;
    this.out = paramPrintStream1;
    this.err = paramPrintStream2;
    this.interactive = paramBoolean;
    debug = paramPrintStream2;
    this.parent = paramInterpreter;
    if (paramInterpreter != null) {
      setStrictJava(paramInterpreter.getStrictJava());
    }
    this.sourceFileInfo = paramString;
    BshClassManager localBshClassManager = BshClassManager.createClassManager(this);
    if (paramNameSpace == null) {
      this.globalNameSpace = new NameSpace(localBshClassManager, "global");
    } else {
      this.globalNameSpace = paramNameSpace;
    }
    if (!(getu("bsh") instanceof This)) {
      initRootSystemObject();
    }
    if (paramBoolean) {
      loadRCFiles();
    }
    long l2 = System.currentTimeMillis();
    if (DEBUG) {
      debug("Time to initialize interpreter: " + (l2 - l1));
    }
  }
  
  public Interpreter(Reader paramReader, PrintStream paramPrintStream1, PrintStream paramPrintStream2, boolean paramBoolean, NameSpace paramNameSpace)
  {
    this(paramReader, paramPrintStream1, paramPrintStream2, paramBoolean, paramNameSpace, null, null);
  }
  
  public Interpreter(Reader paramReader, PrintStream paramPrintStream1, PrintStream paramPrintStream2, boolean paramBoolean)
  {
    this(paramReader, paramPrintStream1, paramPrintStream2, paramBoolean, null);
  }
  
  public Interpreter(ConsoleInterface paramConsoleInterface, NameSpace paramNameSpace)
  {
    this(paramConsoleInterface.getIn(), paramConsoleInterface.getOut(), paramConsoleInterface.getErr(), true, paramNameSpace);
    setConsole(paramConsoleInterface);
  }
  
  public Interpreter(ConsoleInterface paramConsoleInterface)
  {
    this(paramConsoleInterface, null);
  }
  
  public Interpreter()
  {
    this(new StringReader(""), System.out, System.err, false, null);
    this.evalOnly = true;
    setu("bsh.evalOnly", new Primitive(true));
  }
  
  public void setConsole(ConsoleInterface paramConsoleInterface)
  {
    this.console = paramConsoleInterface;
    setu("bsh.console", paramConsoleInterface);
    setOut(paramConsoleInterface.getOut());
    setErr(paramConsoleInterface.getErr());
  }
  
  private void initRootSystemObject()
  {
    BshClassManager localBshClassManager = getClassManager();
    setu("bsh", new NameSpace(localBshClassManager, "Bsh Object").getThis(this));
    if (sharedObject == null) {
      sharedObject = new NameSpace(localBshClassManager, "Bsh Shared System Object").getThis(this);
    }
    setu("bsh.system", sharedObject);
    setu("bsh.shared", sharedObject);
    This localThis = new NameSpace(localBshClassManager, "Bsh Command Help Text").getThis(this);
    setu("bsh.help", localThis);
    try
    {
      setu("bsh.cwd", System.getProperty("user.dir"));
    }
    catch (SecurityException localSecurityException)
    {
      setu("bsh.cwd", ".");
    }
    setu("bsh.interactive", new Primitive(this.interactive));
    setu("bsh.evalOnly", new Primitive(this.evalOnly));
  }
  
  public void setNameSpace(NameSpace paramNameSpace)
  {
    this.globalNameSpace = paramNameSpace;
  }
  
  public NameSpace getNameSpace()
  {
    return this.globalNameSpace;
  }
  
  public static void main(String[] paramArrayOfString)
  {
    Object localObject1;
    Object localObject2;
    Interpreter localInterpreter;
    if (paramArrayOfString.length > 0)
    {
      localObject1 = paramArrayOfString[0];
      if (paramArrayOfString.length > 1)
      {
        localObject2 = new String[paramArrayOfString.length - 1];
        System.arraycopy(paramArrayOfString, 1, localObject2, 0, paramArrayOfString.length - 1);
      }
      else
      {
        localObject2 = new String[0];
      }
      localInterpreter = new Interpreter();
      localInterpreter.setu("bsh.args", localObject2);
      try
      {
        Object localObject3 = localInterpreter.source((String)localObject1, localInterpreter.globalNameSpace);
        if ((localObject3 instanceof Class)) {
          try
          {
            invokeMain((Class)localObject3, (String[])localObject2);
          }
          catch (Exception localException)
          {
            Object localObject4 = localException;
            if ((localException instanceof InvocationTargetException)) {
              localObject4 = ((InvocationTargetException)localException).getTargetException();
            }
            System.err.println("Class: " + localObject3 + " main method threw exception:" + localObject4);
          }
        }
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        System.out.println("File not found: " + localFileNotFoundException);
      }
      catch (TargetError localTargetError)
      {
        System.out.println("Script threw exception: " + localTargetError);
        if (localTargetError.inNativeCode()) {
          localTargetError.printStackTrace(DEBUG, System.err);
        }
      }
      catch (EvalError localEvalError)
      {
        System.out.println("Evaluation Error: " + localEvalError);
      }
      catch (IOException localIOException)
      {
        System.out.println("I/O Error: " + localIOException);
      }
    }
    else
    {
      if ((System.getProperty("os.name").startsWith("Windows")) && (System.getProperty("java.version").startsWith("1.1."))) {
        localObject1 = new FilterInputStream(System.in)
        {
          public int available()
            throws IOException
          {
            return 0;
          }
        };
      } else {
        localObject1 = System.in;
      }
      localObject2 = new CommandLineReader(new InputStreamReader((InputStream)localObject1));
      localInterpreter = new Interpreter((Reader)localObject2, System.out, System.err, true);
      localInterpreter.run();
    }
  }
  
  public static void invokeMain(Class paramClass, String[] paramArrayOfString)
    throws Exception
  {
    Method localMethod = Reflect.resolveJavaMethod(null, paramClass, "main", new Class[] { new String[0].getClass() }, true);
    if (localMethod != null) {
      localMethod.invoke(null, new Object[] { paramArrayOfString });
    }
  }
  
  public void run()
  {
    if (this.evalOnly) {
      throw new RuntimeException("bsh Interpreter: No stream");
    }
    if (this.interactive) {
      try
      {
        eval("printBanner();");
      }
      catch (EvalError localEvalError1)
      {
        println("BeanShell 2.0b4 - by Pat Niemeyer (pat@pat.net)");
      }
    }
    CallStack localCallStack = new CallStack(this.globalNameSpace);
    boolean bool = false;
    while (!bool) {
      try
      {
        System.out.flush();
        System.err.flush();
        Thread.yield();
        if (this.interactive) {
          print(getBshPrompt());
        }
        bool = Line();
        if (get_jjtree().nodeArity() > 0)
        {
          SimpleNode localSimpleNode = (SimpleNode)get_jjtree().rootNode();
          if (DEBUG) {
            localSimpleNode.dump(">");
          }
          Object localObject1 = localSimpleNode.eval(localCallStack, this);
          if (localCallStack.depth() > 1) {
            throw new InterpreterError("Callstack growing: " + localCallStack);
          }
          if ((localObject1 instanceof ReturnControl)) {
            localObject1 = ((ReturnControl)localObject1).value;
          }
          if (localObject1 != Primitive.VOID)
          {
            setu("$_", localObject1);
            if (this.showResults) {
              println("<" + localObject1 + ">");
            }
          }
        }
      }
      catch (ParseException localParseException)
      {
        error("Parser Error: " + localParseException.getMessage(DEBUG));
        if (DEBUG) {
          localParseException.printStackTrace();
        }
        if (!this.interactive) {
          bool = true;
        }
        this.parser.reInitInput(this.in);
      }
      catch (InterpreterError localInterpreterError)
      {
        error("Internal Error: " + localInterpreterError.getMessage());
        localInterpreterError.printStackTrace();
        if (!this.interactive) {
          bool = true;
        }
      }
      catch (TargetError localTargetError)
      {
        error("// Uncaught Exception: " + localTargetError);
        if (localTargetError.inNativeCode()) {
          localTargetError.printStackTrace(DEBUG, this.err);
        }
        if (!this.interactive) {
          bool = true;
        }
        setu("$_e", localTargetError.getTarget());
      }
      catch (EvalError localEvalError2)
      {
        if (this.interactive) {
          error("EvalError: " + localEvalError2.toString());
        } else {
          error("EvalError: " + localEvalError2.getMessage());
        }
        if (DEBUG) {
          localEvalError2.printStackTrace();
        }
        if (!this.interactive) {
          bool = true;
        }
      }
      catch (Exception localException)
      {
        error("Unknown error: " + localException);
        if (DEBUG) {
          localException.printStackTrace();
        }
        if (!this.interactive) {
          bool = true;
        }
      }
      catch (TokenMgrError localTokenMgrError)
      {
        error("Error parsing input: " + localTokenMgrError);
        this.parser.reInitTokenInput(this.in);
        if (!this.interactive) {
          bool = true;
        }
      }
      finally
      {
        get_jjtree().reset();
        if (localCallStack.depth() > 1)
        {
          localCallStack.clear();
          localCallStack.push(this.globalNameSpace);
        }
      }
    }
    if ((this.interactive) && (this.exitOnEOF)) {
      System.exit(0);
    }
  }
  
  public Object source(String paramString, NameSpace paramNameSpace)
    throws FileNotFoundException, IOException, EvalError
  {
    File localFile = pathToFile(paramString);
    if (DEBUG) {
      debug("Sourcing file: " + localFile);
    }
    BufferedReader localBufferedReader = new BufferedReader(new FileReader(localFile));
    try
    {
      Object localObject1 = eval(localBufferedReader, paramNameSpace, paramString);
      return localObject1;
    }
    finally
    {
      localBufferedReader.close();
    }
  }
  
  public Object source(String paramString)
    throws FileNotFoundException, IOException, EvalError
  {
    return source(paramString, this.globalNameSpace);
  }
  
  public Object eval(Reader paramReader, NameSpace paramNameSpace, String paramString)
    throws EvalError
  {
    Object localObject1 = null;
    if (DEBUG) {
      debug("eval: nameSpace = " + paramNameSpace);
    }
    Interpreter localInterpreter = new Interpreter(paramReader, this.out, this.err, false, paramNameSpace, this, paramString);
    CallStack localCallStack = new CallStack(paramNameSpace);
    boolean bool = false;
    while (!bool)
    {
      SimpleNode localSimpleNode = null;
      try
      {
        bool = localInterpreter.Line();
        if (localInterpreter.get_jjtree().nodeArity() > 0)
        {
          localSimpleNode = (SimpleNode)localInterpreter.get_jjtree().rootNode();
          localSimpleNode.setSourceFile(paramString);
          if (TRACE) {
            println("// " + localSimpleNode.getText());
          }
          localObject1 = localSimpleNode.eval(localCallStack, localInterpreter);
          if (localCallStack.depth() > 1) {
            throw new InterpreterError("Callstack growing: " + localCallStack);
          }
          if ((localObject1 instanceof ReturnControl))
          {
            localObject1 = ((ReturnControl)localObject1).value;
            localInterpreter.get_jjtree().reset();
            if (localCallStack.depth() <= 1) {
              break;
            }
            localCallStack.clear();
            localCallStack.push(paramNameSpace);
            break;
          }
          if ((localInterpreter.showResults) && (localObject1 != Primitive.VOID)) {
            println("<" + localObject1 + ">");
          }
        }
      }
      catch (ParseException localParseException)
      {
        if (DEBUG) {
          error(localParseException.getMessage(DEBUG));
        }
        localParseException.setErrorSourceFile(paramString);
        throw localParseException;
      }
      catch (InterpreterError localInterpreterError)
      {
        localInterpreterError.printStackTrace();
        throw new EvalError("Sourced file: " + paramString + " internal Error: " + localInterpreterError.getMessage(), localSimpleNode, localCallStack);
      }
      catch (TargetError localTargetError)
      {
        if (localTargetError.getNode() == null) {
          localTargetError.setNode(localSimpleNode);
        }
        localTargetError.reThrow("Sourced file: " + paramString);
      }
      catch (EvalError localEvalError)
      {
        if (DEBUG) {
          localEvalError.printStackTrace();
        }
        if (localEvalError.getNode() == null) {
          localEvalError.setNode(localSimpleNode);
        }
        localEvalError.reThrow("Sourced file: " + paramString);
      }
      catch (Exception localException)
      {
        if (DEBUG) {
          localException.printStackTrace();
        }
        throw new EvalError("Sourced file: " + paramString + " unknown error: " + localException.getMessage(), localSimpleNode, localCallStack);
      }
      catch (TokenMgrError localTokenMgrError)
      {
        throw new EvalError("Sourced file: " + paramString + " Token Parsing Error: " + localTokenMgrError.getMessage(), localSimpleNode, localCallStack);
      }
      finally
      {
        localInterpreter.get_jjtree().reset();
        if (localCallStack.depth() > 1)
        {
          localCallStack.clear();
          localCallStack.push(paramNameSpace);
        }
      }
    }
    return Primitive.unwrap(localObject1);
  }
  
  public Object eval(Reader paramReader)
    throws EvalError
  {
    return eval(paramReader, this.globalNameSpace, "eval stream");
  }
  
  public Object eval(String paramString)
    throws EvalError
  {
    if (DEBUG) {
      debug("eval(String): " + paramString);
    }
    return eval(paramString, this.globalNameSpace);
  }
  
  public Object eval(String paramString, NameSpace paramNameSpace)
    throws EvalError
  {
    String str = paramString + ";";
    return eval(new StringReader(str), paramNameSpace, "inline evaluation of: ``" + showEvalString(str) + "''");
  }
  
  private String showEvalString(String paramString)
  {
    paramString = paramString.replace('\n', ' ');
    paramString = paramString.replace('\r', ' ');
    if (paramString.length() > 80) {
      paramString = paramString.substring(0, 80) + " . . . ";
    }
    return paramString;
  }
  
  public final void error(Object paramObject)
  {
    if (this.console != null)
    {
      this.console.error("// Error: " + paramObject + "\n");
    }
    else
    {
      this.err.println("// Error: " + paramObject);
      this.err.flush();
    }
  }
  
  public Reader getIn()
  {
    return this.in;
  }
  
  public PrintStream getOut()
  {
    return this.out;
  }
  
  public PrintStream getErr()
  {
    return this.err;
  }
  
  public final void println(Object paramObject)
  {
    print(String.valueOf(paramObject) + systemLineSeparator);
  }
  
  public final void print(Object paramObject)
  {
    if (this.console != null)
    {
      this.console.print(paramObject);
    }
    else
    {
      this.out.print(paramObject);
      this.out.flush();
    }
  }
  
  public static final void debug(String paramString)
  {
    if (DEBUG) {
      debug.println("// Debug: " + paramString);
    }
  }
  
  public Object get(String paramString)
    throws EvalError
  {
    try
    {
      Object localObject = this.globalNameSpace.get(paramString, this);
      return Primitive.unwrap(localObject);
    }
    catch (UtilEvalError localUtilEvalError)
    {
      throw localUtilEvalError.toEvalError(SimpleNode.JAVACODE, new CallStack());
    }
  }
  
  Object getu(String paramString)
  {
    try
    {
      return get(paramString);
    }
    catch (EvalError localEvalError)
    {
      throw new InterpreterError("set: " + localEvalError);
    }
  }
  
  public void set(String paramString, Object paramObject)
    throws EvalError
  {
    if (paramObject == null) {
      paramObject = Primitive.NULL;
    }
    CallStack localCallStack = new CallStack();
    try
    {
      if (Name.isCompound(paramString))
      {
        LHS localLHS = this.globalNameSpace.getNameResolver(paramString).toLHS(localCallStack, this);
        localLHS.assign(paramObject, false);
      }
      else
      {
        this.globalNameSpace.setVariable(paramString, paramObject, false);
      }
    }
    catch (UtilEvalError localUtilEvalError)
    {
      throw localUtilEvalError.toEvalError(SimpleNode.JAVACODE, localCallStack);
    }
  }
  
  void setu(String paramString, Object paramObject)
  {
    try
    {
      set(paramString, paramObject);
    }
    catch (EvalError localEvalError)
    {
      throw new InterpreterError("set: " + localEvalError);
    }
  }
  
  public void set(String paramString, long paramLong)
    throws EvalError
  {
    set(paramString, new Primitive(paramLong));
  }
  
  public void set(String paramString, int paramInt)
    throws EvalError
  {
    set(paramString, new Primitive(paramInt));
  }
  
  public void set(String paramString, double paramDouble)
    throws EvalError
  {
    set(paramString, new Primitive(paramDouble));
  }
  
  public void set(String paramString, float paramFloat)
    throws EvalError
  {
    set(paramString, new Primitive(paramFloat));
  }
  
  public void set(String paramString, boolean paramBoolean)
    throws EvalError
  {
    set(paramString, new Primitive(paramBoolean));
  }
  
  public void unset(String paramString)
    throws EvalError
  {
    CallStack localCallStack = new CallStack();
    try
    {
      LHS localLHS = this.globalNameSpace.getNameResolver(paramString).toLHS(localCallStack, this);
      if (localLHS.type != 0) {
        throw new EvalError("Can't unset, not a variable: " + paramString, SimpleNode.JAVACODE, new CallStack());
      }
      localLHS.nameSpace.unsetVariable(paramString);
    }
    catch (UtilEvalError localUtilEvalError)
    {
      throw new EvalError(localUtilEvalError.getMessage(), SimpleNode.JAVACODE, new CallStack());
    }
  }
  
  public Object getInterface(Class paramClass)
    throws EvalError
  {
    try
    {
      return this.globalNameSpace.getThis(this).getInterface(paramClass);
    }
    catch (UtilEvalError localUtilEvalError)
    {
      throw localUtilEvalError.toEvalError(SimpleNode.JAVACODE, new CallStack());
    }
  }
  
  private JJTParserState get_jjtree()
  {
    return this.parser.jjtree;
  }
  
  private JavaCharStream get_jj_input_stream()
  {
    return this.parser.jj_input_stream;
  }
  
  private boolean Line()
    throws ParseException
  {
    return this.parser.Line();
  }
  
  void loadRCFiles()
  {
    try
    {
      String str = System.getProperty("user.home") + File.separator + ".bshrc";
      source(str, this.globalNameSpace);
    }
    catch (Exception localException)
    {
      if (DEBUG) {
        debug("Could not find rc file: " + localException);
      }
    }
  }
  
  public File pathToFile(String paramString)
    throws IOException
  {
    File localFile = new File(paramString);
    if (!localFile.isAbsolute())
    {
      String str = (String)getu("bsh.cwd");
      localFile = new File(str + File.separator + paramString);
    }
    return new File(localFile.getCanonicalPath());
  }
  
  public static void redirectOutputToFile(String paramString)
  {
    try
    {
      PrintStream localPrintStream = new PrintStream(new FileOutputStream(paramString));
      System.setOut(localPrintStream);
      System.setErr(localPrintStream);
    }
    catch (IOException localIOException)
    {
      System.err.println("Can't redirect output to file: " + paramString);
    }
  }
  
  public void setClassLoader(ClassLoader paramClassLoader)
  {
    getClassManager().setClassLoader(paramClassLoader);
  }
  
  public BshClassManager getClassManager()
  {
    return getNameSpace().getClassManager();
  }
  
  public void setStrictJava(boolean paramBoolean)
  {
    this.strictJava = paramBoolean;
  }
  
  public boolean getStrictJava()
  {
    return this.strictJava;
  }
  
  static void staticInit()
  {
    try
    {
      systemLineSeparator = System.getProperty("line.separator");
      debug = System.err;
      DEBUG = Boolean.getBoolean("debug");
      TRACE = Boolean.getBoolean("trace");
      LOCALSCOPING = Boolean.getBoolean("localscoping");
      String str = System.getProperty("outfile");
      if (str != null) {
        redirectOutputToFile(str);
      }
    }
    catch (SecurityException localSecurityException)
    {
      System.err.println("Could not init static:" + localSecurityException);
    }
    catch (Exception localException)
    {
      System.err.println("Could not init static(2):" + localException);
    }
    catch (Throwable localThrowable)
    {
      System.err.println("Could not init static(3):" + localThrowable);
    }
  }
  
  public String getSourceFileInfo()
  {
    if (this.sourceFileInfo != null) {
      return this.sourceFileInfo;
    }
    return "<unknown source>";
  }
  
  public Interpreter getParent()
  {
    return this.parent;
  }
  
  public void setOut(PrintStream paramPrintStream)
  {
    this.out = paramPrintStream;
  }
  
  public void setErr(PrintStream paramPrintStream)
  {
    this.err = paramPrintStream;
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    paramObjectInputStream.defaultReadObject();
    if (this.console != null)
    {
      setOut(this.console.getOut());
      setErr(this.console.getErr());
    }
    else
    {
      setOut(System.out);
      setErr(System.err);
    }
  }
  
  private String getBshPrompt()
  {
    try
    {
      return (String)eval("getBshPrompt()");
    }
    catch (Exception localException) {}
    return "bsh % ";
  }
  
  public void setExitOnEOF(boolean paramBoolean)
  {
    this.exitOnEOF = paramBoolean;
  }
  
  public void setShowResults(boolean paramBoolean)
  {
    this.showResults = paramBoolean;
  }
  
  public boolean getShowResults()
  {
    return this.showResults;
  }
  
  static
  {
    staticInit();
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/Interpreter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */