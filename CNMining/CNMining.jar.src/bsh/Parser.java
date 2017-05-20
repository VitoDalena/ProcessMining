package bsh;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Reader;

public class Parser
  implements ParserTreeConstants, ParserConstants
{
  protected JJTParserState jjtree = new JJTParserState();
  boolean retainComments = false;
  public ParserTokenManager token_source;
  JavaCharStream jj_input_stream;
  public Token token;
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos;
  private Token jj_lastpos;
  private int jj_la;
  public boolean lookingAhead = false;
  private boolean jj_semLA;
  private final LookaheadSuccess jj_ls = new LookaheadSuccess(null);
  
  public void setRetainComments(boolean paramBoolean)
  {
    this.retainComments = paramBoolean;
  }
  
  void jjtreeOpenNodeScope(Node paramNode)
  {
    ((SimpleNode)paramNode).firstToken = getToken(1);
  }
  
  void jjtreeCloseNodeScope(Node paramNode)
  {
    ((SimpleNode)paramNode).lastToken = getToken(0);
  }
  
  void reInitInput(Reader paramReader)
  {
    ReInit(paramReader);
  }
  
  public SimpleNode popNode()
  {
    if (this.jjtree.nodeArity() > 0) {
      return (SimpleNode)this.jjtree.popNode();
    }
    return null;
  }
  
  void reInitTokenInput(Reader paramReader)
  {
    this.jj_input_stream.ReInit(paramReader, this.jj_input_stream.getEndLine(), this.jj_input_stream.getEndColumn());
  }
  
  public static void main(String[] paramArrayOfString)
    throws IOException, ParseException
  {
    int i = 0;
    int j = 0;
    if (paramArrayOfString[0].equals("-p"))
    {
      j++;
      i = 1;
    }
    while (j < paramArrayOfString.length)
    {
      FileReader localFileReader = new FileReader(paramArrayOfString[j]);
      Parser localParser = new Parser(localFileReader);
      localParser.setRetainComments(true);
      while (!localParser.Line()) {
        if (i != 0) {
          System.out.println(localParser.popNode());
        }
      }
      j++;
    }
  }
  
  boolean isRegularForStatement()
  {
    int i = 1;
    Token localToken = getToken(i++);
    if (localToken.kind != 30) {
      return false;
    }
    localToken = getToken(i++);
    if (localToken.kind != 72) {
      return false;
    }
    for (;;)
    {
      localToken = getToken(i++);
      switch (localToken.kind)
      {
      case 89: 
        return false;
      case 78: 
        return true;
      case 0: 
        return false;
      }
    }
  }
  
  ParseException createParseException(String paramString)
  {
    Token localToken = this.token;
    int i = localToken.beginLine;
    int j = localToken.beginColumn;
    String str = localToken.kind == 0 ? ParserConstants.tokenImage[0] : localToken.image;
    return new ParseException("Parse error at line " + i + ", column " + j + " : " + paramString);
  }
  
  public final boolean Line()
    throws ParseException
  {
    switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
    {
    case 0: 
      jj_consume_token(0);
      Interpreter.debug("End of File!");
      return true;
    }
    if (jj_2_1(1))
    {
      BlockStatement();
      return false;
    }
    jj_consume_token(-1);
    throw new ParseException();
  }
  
  public final Modifiers Modifiers(int paramInt, boolean paramBoolean)
    throws ParseException
  {
    Modifiers localModifiers = null;
    for (;;)
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 10: 
      case 27: 
      case 39: 
      case 43: 
      case 44: 
      case 45: 
      case 48: 
      case 49: 
      case 51: 
      case 52: 
      case 58: 
        break;
      }
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 43: 
        jj_consume_token(43);
        break;
      case 44: 
        jj_consume_token(44);
        break;
      case 45: 
        jj_consume_token(45);
        break;
      case 51: 
        jj_consume_token(51);
        break;
      case 27: 
        jj_consume_token(27);
        break;
      case 39: 
        jj_consume_token(39);
        break;
      case 52: 
        jj_consume_token(52);
        break;
      case 58: 
        jj_consume_token(58);
        break;
      case 10: 
        jj_consume_token(10);
        break;
      case 48: 
        jj_consume_token(48);
        break;
      case 49: 
        jj_consume_token(49);
        break;
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
      if (!paramBoolean) {
        try
        {
          if (localModifiers == null) {
            localModifiers = new Modifiers();
          }
          localModifiers.addModifier(paramInt, getToken(0).image);
        }
        catch (IllegalStateException localIllegalStateException)
        {
          throw createParseException(localIllegalStateException.getMessage());
        }
      }
    }
    return localModifiers;
  }
  
  public final void ClassDeclaration()
    throws ParseException
  {
    BSHClassDeclaration localBSHClassDeclaration = new BSHClassDeclaration(1);
    int i = 1;
    this.jjtree.openNodeScope(localBSHClassDeclaration);
    jjtreeOpenNodeScope(localBSHClassDeclaration);
    try
    {
      Modifiers localModifiers = Modifiers(0, false);
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 13: 
        jj_consume_token(13);
        break;
      case 37: 
        jj_consume_token(37);
        localBSHClassDeclaration.isInterface = true;
        break;
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
      Token localToken = jj_consume_token(69);
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 25: 
        jj_consume_token(25);
        AmbiguousName();
        localBSHClassDeclaration.extend = true;
        break;
      }
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 33: 
        jj_consume_token(33);
        int j = NameList();
        localBSHClassDeclaration.numInterfaces = j;
        break;
      }
      Block();
      this.jjtree.closeNodeScope(localBSHClassDeclaration, true);
      i = 0;
      jjtreeCloseNodeScope(localBSHClassDeclaration);
      localBSHClassDeclaration.modifiers = localModifiers;
      localBSHClassDeclaration.name = localToken.image;
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHClassDeclaration);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHClassDeclaration, true);
        jjtreeCloseNodeScope(localBSHClassDeclaration);
      }
    }
  }
  
  public final void MethodDeclaration()
    throws ParseException
  {
    BSHMethodDeclaration localBSHMethodDeclaration = new BSHMethodDeclaration(2);
    int i = 1;
    this.jjtree.openNodeScope(localBSHMethodDeclaration);
    jjtreeOpenNodeScope(localBSHMethodDeclaration);
    Token localToken = null;
    try
    {
      Modifiers localModifiers = Modifiers(1, false);
      localBSHMethodDeclaration.modifiers = localModifiers;
      if (jj_2_2(Integer.MAX_VALUE))
      {
        localToken = jj_consume_token(69);
        localBSHMethodDeclaration.name = localToken.image;
      }
      else
      {
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 11: 
        case 14: 
        case 17: 
        case 22: 
        case 29: 
        case 36: 
        case 38: 
        case 47: 
        case 57: 
        case 69: 
          ReturnType();
          localToken = jj_consume_token(69);
          localBSHMethodDeclaration.name = localToken.image;
          break;
        default: 
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      FormalParameters();
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 54: 
        jj_consume_token(54);
        int j = NameList();
        localBSHMethodDeclaration.numThrows = j;
        break;
      }
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 74: 
        Block();
        break;
      case 78: 
        jj_consume_token(78);
        break;
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHMethodDeclaration);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHMethodDeclaration, true);
        jjtreeCloseNodeScope(localBSHMethodDeclaration);
      }
    }
  }
  
  public final void PackageDeclaration()
    throws ParseException
  {
    BSHPackageDeclaration localBSHPackageDeclaration = new BSHPackageDeclaration(3);
    int i = 1;
    this.jjtree.openNodeScope(localBSHPackageDeclaration);
    jjtreeOpenNodeScope(localBSHPackageDeclaration);
    try
    {
      jj_consume_token(42);
      AmbiguousName();
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHPackageDeclaration);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHPackageDeclaration, true);
        jjtreeCloseNodeScope(localBSHPackageDeclaration);
      }
    }
  }
  
  public final void ImportDeclaration()
    throws ParseException
  {
    BSHImportDeclaration localBSHImportDeclaration = new BSHImportDeclaration(4);
    int i = 1;
    this.jjtree.openNodeScope(localBSHImportDeclaration);
    jjtreeOpenNodeScope(localBSHImportDeclaration);
    Token localToken1 = null;
    Token localToken2 = null;
    try
    {
      if (jj_2_3(3))
      {
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 48: 
          localToken1 = jj_consume_token(48);
          break;
        }
        jj_consume_token(34);
        AmbiguousName();
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 80: 
          localToken2 = jj_consume_token(80);
          jj_consume_token(104);
          break;
        }
        jj_consume_token(78);
        this.jjtree.closeNodeScope(localBSHImportDeclaration, true);
        i = 0;
        jjtreeCloseNodeScope(localBSHImportDeclaration);
        if (localToken1 != null) {
          localBSHImportDeclaration.staticImport = true;
        }
        if (localToken2 != null) {
          localBSHImportDeclaration.importPackage = true;
        }
      }
      else
      {
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 34: 
          jj_consume_token(34);
          jj_consume_token(104);
          jj_consume_token(78);
          this.jjtree.closeNodeScope(localBSHImportDeclaration, true);
          i = 0;
          jjtreeCloseNodeScope(localBSHImportDeclaration);
          localBSHImportDeclaration.superImport = true;
          break;
        default: 
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHImportDeclaration);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHImportDeclaration, true);
        jjtreeCloseNodeScope(localBSHImportDeclaration);
      }
    }
  }
  
  public final void VariableDeclarator()
    throws ParseException
  {
    BSHVariableDeclarator localBSHVariableDeclarator = new BSHVariableDeclarator(5);
    int i = 1;
    this.jjtree.openNodeScope(localBSHVariableDeclarator);
    jjtreeOpenNodeScope(localBSHVariableDeclarator);
    try
    {
      Token localToken = jj_consume_token(69);
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 81: 
        jj_consume_token(81);
        VariableInitializer();
        break;
      }
      this.jjtree.closeNodeScope(localBSHVariableDeclarator, true);
      i = 0;
      jjtreeCloseNodeScope(localBSHVariableDeclarator);
      localBSHVariableDeclarator.name = localToken.image;
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHVariableDeclarator);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHVariableDeclarator, true);
        jjtreeCloseNodeScope(localBSHVariableDeclarator);
      }
    }
  }
  
  public final void VariableInitializer()
    throws ParseException
  {
    switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
    {
    case 74: 
      ArrayInitializer();
      break;
    case 11: 
    case 14: 
    case 17: 
    case 22: 
    case 26: 
    case 29: 
    case 36: 
    case 38: 
    case 40: 
    case 41: 
    case 47: 
    case 55: 
    case 57: 
    case 60: 
    case 64: 
    case 66: 
    case 67: 
    case 69: 
    case 72: 
    case 86: 
    case 87: 
    case 100: 
    case 101: 
    case 102: 
    case 103: 
      Expression();
      break;
    case 12: 
    case 13: 
    case 15: 
    case 16: 
    case 18: 
    case 19: 
    case 20: 
    case 21: 
    case 23: 
    case 24: 
    case 25: 
    case 27: 
    case 28: 
    case 30: 
    case 31: 
    case 32: 
    case 33: 
    case 34: 
    case 35: 
    case 37: 
    case 39: 
    case 42: 
    case 43: 
    case 44: 
    case 45: 
    case 46: 
    case 48: 
    case 49: 
    case 50: 
    case 51: 
    case 52: 
    case 53: 
    case 54: 
    case 56: 
    case 58: 
    case 59: 
    case 61: 
    case 62: 
    case 63: 
    case 65: 
    case 68: 
    case 70: 
    case 71: 
    case 73: 
    case 75: 
    case 76: 
    case 77: 
    case 78: 
    case 79: 
    case 80: 
    case 81: 
    case 82: 
    case 83: 
    case 84: 
    case 85: 
    case 88: 
    case 89: 
    case 90: 
    case 91: 
    case 92: 
    case 93: 
    case 94: 
    case 95: 
    case 96: 
    case 97: 
    case 98: 
    case 99: 
    default: 
      jj_consume_token(-1);
      throw new ParseException();
    }
  }
  
  public final void ArrayInitializer()
    throws ParseException
  {
    BSHArrayInitializer localBSHArrayInitializer = new BSHArrayInitializer(6);
    int i = 1;
    this.jjtree.openNodeScope(localBSHArrayInitializer);
    jjtreeOpenNodeScope(localBSHArrayInitializer);
    try
    {
      jj_consume_token(74);
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 11: 
      case 14: 
      case 17: 
      case 22: 
      case 26: 
      case 29: 
      case 36: 
      case 38: 
      case 40: 
      case 41: 
      case 47: 
      case 55: 
      case 57: 
      case 60: 
      case 64: 
      case 66: 
      case 67: 
      case 69: 
      case 72: 
      case 74: 
      case 86: 
      case 87: 
      case 100: 
      case 101: 
      case 102: 
      case 103: 
        VariableInitializer();
        while (jj_2_4(2))
        {
          jj_consume_token(79);
          VariableInitializer();
        }
      }
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 79: 
        jj_consume_token(79);
        break;
      }
      jj_consume_token(75);
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHArrayInitializer);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHArrayInitializer, true);
        jjtreeCloseNodeScope(localBSHArrayInitializer);
      }
    }
  }
  
  public final void FormalParameters()
    throws ParseException
  {
    BSHFormalParameters localBSHFormalParameters = new BSHFormalParameters(7);
    int i = 1;
    this.jjtree.openNodeScope(localBSHFormalParameters);
    jjtreeOpenNodeScope(localBSHFormalParameters);
    try
    {
      jj_consume_token(72);
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 11: 
      case 14: 
      case 17: 
      case 22: 
      case 29: 
      case 36: 
      case 38: 
      case 47: 
      case 69: 
        FormalParameter();
        for (;;)
        {
          switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
          {
          case 79: 
            break;
          }
          jj_consume_token(79);
          FormalParameter();
        }
      }
      jj_consume_token(73);
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHFormalParameters);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHFormalParameters, true);
        jjtreeCloseNodeScope(localBSHFormalParameters);
      }
    }
  }
  
  public final void FormalParameter()
    throws ParseException
  {
    BSHFormalParameter localBSHFormalParameter = new BSHFormalParameter(8);
    int i = 1;
    this.jjtree.openNodeScope(localBSHFormalParameter);
    jjtreeOpenNodeScope(localBSHFormalParameter);
    try
    {
      Token localToken;
      if (jj_2_5(2))
      {
        Type();
        localToken = jj_consume_token(69);
        this.jjtree.closeNodeScope(localBSHFormalParameter, true);
        i = 0;
        jjtreeCloseNodeScope(localBSHFormalParameter);
        localBSHFormalParameter.name = localToken.image;
      }
      else
      {
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 69: 
          localToken = jj_consume_token(69);
          this.jjtree.closeNodeScope(localBSHFormalParameter, true);
          i = 0;
          jjtreeCloseNodeScope(localBSHFormalParameter);
          localBSHFormalParameter.name = localToken.image;
          break;
        default: 
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHFormalParameter);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHFormalParameter, true);
        jjtreeCloseNodeScope(localBSHFormalParameter);
      }
    }
  }
  
  public final void Type()
    throws ParseException
  {
    BSHType localBSHType = new BSHType(9);
    int i = 1;
    this.jjtree.openNodeScope(localBSHType);
    jjtreeOpenNodeScope(localBSHType);
    try
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 11: 
      case 14: 
      case 17: 
      case 22: 
      case 29: 
      case 36: 
      case 38: 
      case 47: 
        PrimitiveType();
        break;
      case 69: 
        AmbiguousName();
        break;
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
      while (jj_2_6(2))
      {
        jj_consume_token(76);
        jj_consume_token(77);
        localBSHType.addArrayDimension();
      }
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHType);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHType, true);
        jjtreeCloseNodeScope(localBSHType);
      }
    }
  }
  
  public final void ReturnType()
    throws ParseException
  {
    BSHReturnType localBSHReturnType = new BSHReturnType(10);
    int i = 1;
    this.jjtree.openNodeScope(localBSHReturnType);
    jjtreeOpenNodeScope(localBSHReturnType);
    try
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 57: 
        jj_consume_token(57);
        this.jjtree.closeNodeScope(localBSHReturnType, true);
        i = 0;
        jjtreeCloseNodeScope(localBSHReturnType);
        localBSHReturnType.isVoid = true;
        break;
      case 11: 
      case 14: 
      case 17: 
      case 22: 
      case 29: 
      case 36: 
      case 38: 
      case 47: 
      case 69: 
        Type();
        break;
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHReturnType);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHReturnType, true);
        jjtreeCloseNodeScope(localBSHReturnType);
      }
    }
  }
  
  public final void PrimitiveType()
    throws ParseException
  {
    BSHPrimitiveType localBSHPrimitiveType = new BSHPrimitiveType(11);
    int i = 1;
    this.jjtree.openNodeScope(localBSHPrimitiveType);
    jjtreeOpenNodeScope(localBSHPrimitiveType);
    try
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 11: 
        jj_consume_token(11);
        this.jjtree.closeNodeScope(localBSHPrimitiveType, true);
        i = 0;
        jjtreeCloseNodeScope(localBSHPrimitiveType);
        localBSHPrimitiveType.type = Boolean.TYPE;
        break;
      case 17: 
        jj_consume_token(17);
        this.jjtree.closeNodeScope(localBSHPrimitiveType, true);
        i = 0;
        jjtreeCloseNodeScope(localBSHPrimitiveType);
        localBSHPrimitiveType.type = Character.TYPE;
        break;
      case 14: 
        jj_consume_token(14);
        this.jjtree.closeNodeScope(localBSHPrimitiveType, true);
        i = 0;
        jjtreeCloseNodeScope(localBSHPrimitiveType);
        localBSHPrimitiveType.type = Byte.TYPE;
        break;
      case 47: 
        jj_consume_token(47);
        this.jjtree.closeNodeScope(localBSHPrimitiveType, true);
        i = 0;
        jjtreeCloseNodeScope(localBSHPrimitiveType);
        localBSHPrimitiveType.type = Short.TYPE;
        break;
      case 36: 
        jj_consume_token(36);
        this.jjtree.closeNodeScope(localBSHPrimitiveType, true);
        i = 0;
        jjtreeCloseNodeScope(localBSHPrimitiveType);
        localBSHPrimitiveType.type = Integer.TYPE;
        break;
      case 38: 
        jj_consume_token(38);
        this.jjtree.closeNodeScope(localBSHPrimitiveType, true);
        i = 0;
        jjtreeCloseNodeScope(localBSHPrimitiveType);
        localBSHPrimitiveType.type = Long.TYPE;
        break;
      case 29: 
        jj_consume_token(29);
        this.jjtree.closeNodeScope(localBSHPrimitiveType, true);
        i = 0;
        jjtreeCloseNodeScope(localBSHPrimitiveType);
        localBSHPrimitiveType.type = Float.TYPE;
        break;
      case 22: 
        jj_consume_token(22);
        this.jjtree.closeNodeScope(localBSHPrimitiveType, true);
        i = 0;
        jjtreeCloseNodeScope(localBSHPrimitiveType);
        localBSHPrimitiveType.type = Double.TYPE;
        break;
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHPrimitiveType, true);
        jjtreeCloseNodeScope(localBSHPrimitiveType);
      }
    }
  }
  
  public final void AmbiguousName()
    throws ParseException
  {
    BSHAmbiguousName localBSHAmbiguousName = new BSHAmbiguousName(12);
    int i = 1;
    this.jjtree.openNodeScope(localBSHAmbiguousName);
    jjtreeOpenNodeScope(localBSHAmbiguousName);
    try
    {
      Token localToken = jj_consume_token(69);
      StringBuffer localStringBuffer = new StringBuffer(localToken.image);
      while (jj_2_7(2))
      {
        jj_consume_token(80);
        localToken = jj_consume_token(69);
        localStringBuffer.append("." + localToken.image);
      }
      this.jjtree.closeNodeScope(localBSHAmbiguousName, true);
      i = 0;
      jjtreeCloseNodeScope(localBSHAmbiguousName);
      localBSHAmbiguousName.text = localStringBuffer.toString();
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHAmbiguousName, true);
        jjtreeCloseNodeScope(localBSHAmbiguousName);
      }
    }
  }
  
  public final int NameList()
    throws ParseException
  {
    int i = 0;
    AmbiguousName();
    i++;
    for (;;)
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 79: 
        break;
      }
      jj_consume_token(79);
      AmbiguousName();
      i++;
    }
    return i;
  }
  
  public final void Expression()
    throws ParseException
  {
    if (jj_2_8(Integer.MAX_VALUE)) {
      Assignment();
    } else {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 11: 
      case 14: 
      case 17: 
      case 22: 
      case 26: 
      case 29: 
      case 36: 
      case 38: 
      case 40: 
      case 41: 
      case 47: 
      case 55: 
      case 57: 
      case 60: 
      case 64: 
      case 66: 
      case 67: 
      case 69: 
      case 72: 
      case 86: 
      case 87: 
      case 100: 
      case 101: 
      case 102: 
      case 103: 
        ConditionalExpression();
        break;
      case 12: 
      case 13: 
      case 15: 
      case 16: 
      case 18: 
      case 19: 
      case 20: 
      case 21: 
      case 23: 
      case 24: 
      case 25: 
      case 27: 
      case 28: 
      case 30: 
      case 31: 
      case 32: 
      case 33: 
      case 34: 
      case 35: 
      case 37: 
      case 39: 
      case 42: 
      case 43: 
      case 44: 
      case 45: 
      case 46: 
      case 48: 
      case 49: 
      case 50: 
      case 51: 
      case 52: 
      case 53: 
      case 54: 
      case 56: 
      case 58: 
      case 59: 
      case 61: 
      case 62: 
      case 63: 
      case 65: 
      case 68: 
      case 70: 
      case 71: 
      case 73: 
      case 74: 
      case 75: 
      case 76: 
      case 77: 
      case 78: 
      case 79: 
      case 80: 
      case 81: 
      case 82: 
      case 83: 
      case 84: 
      case 85: 
      case 88: 
      case 89: 
      case 90: 
      case 91: 
      case 92: 
      case 93: 
      case 94: 
      case 95: 
      case 96: 
      case 97: 
      case 98: 
      case 99: 
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }
  
  public final void Assignment()
    throws ParseException
  {
    BSHAssignment localBSHAssignment = new BSHAssignment(13);
    int i = 1;
    this.jjtree.openNodeScope(localBSHAssignment);
    jjtreeOpenNodeScope(localBSHAssignment);
    try
    {
      PrimaryExpression();
      int j = AssignmentOperator();
      localBSHAssignment.operator = j;
      Expression();
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHAssignment);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHAssignment, true);
        jjtreeCloseNodeScope(localBSHAssignment);
      }
    }
  }
  
  public final int AssignmentOperator()
    throws ParseException
  {
    switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
    {
    case 81: 
      jj_consume_token(81);
      break;
    case 120: 
      jj_consume_token(120);
      break;
    case 121: 
      jj_consume_token(121);
      break;
    case 127: 
      jj_consume_token(127);
      break;
    case 118: 
      jj_consume_token(118);
      break;
    case 119: 
      jj_consume_token(119);
      break;
    case 122: 
      jj_consume_token(122);
      break;
    case 126: 
      jj_consume_token(126);
      break;
    case 124: 
      jj_consume_token(124);
      break;
    case 128: 
      jj_consume_token(128);
      break;
    case 129: 
      jj_consume_token(129);
      break;
    case 130: 
      jj_consume_token(130);
      break;
    case 131: 
      jj_consume_token(131);
      break;
    case 132: 
      jj_consume_token(132);
      break;
    case 133: 
      jj_consume_token(133);
      break;
    case 82: 
    case 83: 
    case 84: 
    case 85: 
    case 86: 
    case 87: 
    case 88: 
    case 89: 
    case 90: 
    case 91: 
    case 92: 
    case 93: 
    case 94: 
    case 95: 
    case 96: 
    case 97: 
    case 98: 
    case 99: 
    case 100: 
    case 101: 
    case 102: 
    case 103: 
    case 104: 
    case 105: 
    case 106: 
    case 107: 
    case 108: 
    case 109: 
    case 110: 
    case 111: 
    case 112: 
    case 113: 
    case 114: 
    case 115: 
    case 116: 
    case 117: 
    case 123: 
    case 125: 
    default: 
      jj_consume_token(-1);
      throw new ParseException();
    }
    Token localToken = getToken(0);
    return localToken.kind;
  }
  
  public final void ConditionalExpression()
    throws ParseException
  {
    ConditionalOrExpression();
    switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
    {
    case 88: 
      jj_consume_token(88);
      Expression();
      jj_consume_token(89);
      BSHTernaryExpression localBSHTernaryExpression = new BSHTernaryExpression(14);
      int i = 1;
      this.jjtree.openNodeScope(localBSHTernaryExpression);
      jjtreeOpenNodeScope(localBSHTernaryExpression);
      try
      {
        ConditionalExpression();
      }
      catch (Throwable localThrowable)
      {
        if (i != 0)
        {
          this.jjtree.clearNodeScope(localBSHTernaryExpression);
          i = 0;
        }
        else
        {
          this.jjtree.popNode();
        }
        if ((localThrowable instanceof RuntimeException)) {
          throw ((RuntimeException)localThrowable);
        }
        if ((localThrowable instanceof ParseException)) {
          throw ((ParseException)localThrowable);
        }
        throw ((Error)localThrowable);
      }
      finally
      {
        if (i != 0)
        {
          this.jjtree.closeNodeScope(localBSHTernaryExpression, 3);
          jjtreeCloseNodeScope(localBSHTernaryExpression);
        }
      }
    }
  }
  
  public final void ConditionalOrExpression()
    throws ParseException
  {
    Token localToken = null;
    ConditionalAndExpression();
    for (;;)
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 96: 
      case 97: 
        break;
      }
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 96: 
        localToken = jj_consume_token(96);
        break;
      case 97: 
        localToken = jj_consume_token(97);
        break;
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
      ConditionalAndExpression();
      BSHBinaryExpression localBSHBinaryExpression = new BSHBinaryExpression(15);
      int i = 1;
      this.jjtree.openNodeScope(localBSHBinaryExpression);
      jjtreeOpenNodeScope(localBSHBinaryExpression);
      try
      {
        this.jjtree.closeNodeScope(localBSHBinaryExpression, 2);
        i = 0;
        jjtreeCloseNodeScope(localBSHBinaryExpression);
        localBSHBinaryExpression.kind = localToken.kind;
      }
      finally
      {
        if (i != 0)
        {
          this.jjtree.closeNodeScope(localBSHBinaryExpression, 2);
          jjtreeCloseNodeScope(localBSHBinaryExpression);
        }
      }
    }
  }
  
  public final void ConditionalAndExpression()
    throws ParseException
  {
    Token localToken = null;
    InclusiveOrExpression();
    for (;;)
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 98: 
      case 99: 
        break;
      }
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 98: 
        localToken = jj_consume_token(98);
        break;
      case 99: 
        localToken = jj_consume_token(99);
        break;
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
      InclusiveOrExpression();
      BSHBinaryExpression localBSHBinaryExpression = new BSHBinaryExpression(15);
      int i = 1;
      this.jjtree.openNodeScope(localBSHBinaryExpression);
      jjtreeOpenNodeScope(localBSHBinaryExpression);
      try
      {
        this.jjtree.closeNodeScope(localBSHBinaryExpression, 2);
        i = 0;
        jjtreeCloseNodeScope(localBSHBinaryExpression);
        localBSHBinaryExpression.kind = localToken.kind;
      }
      finally
      {
        if (i != 0)
        {
          this.jjtree.closeNodeScope(localBSHBinaryExpression, 2);
          jjtreeCloseNodeScope(localBSHBinaryExpression);
        }
      }
    }
  }
  
  public final void InclusiveOrExpression()
    throws ParseException
  {
    Token localToken = null;
    ExclusiveOrExpression();
    for (;;)
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 108: 
      case 109: 
        break;
      }
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 108: 
        localToken = jj_consume_token(108);
        break;
      case 109: 
        localToken = jj_consume_token(109);
        break;
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
      ExclusiveOrExpression();
      BSHBinaryExpression localBSHBinaryExpression = new BSHBinaryExpression(15);
      int i = 1;
      this.jjtree.openNodeScope(localBSHBinaryExpression);
      jjtreeOpenNodeScope(localBSHBinaryExpression);
      try
      {
        this.jjtree.closeNodeScope(localBSHBinaryExpression, 2);
        i = 0;
        jjtreeCloseNodeScope(localBSHBinaryExpression);
        localBSHBinaryExpression.kind = localToken.kind;
      }
      finally
      {
        if (i != 0)
        {
          this.jjtree.closeNodeScope(localBSHBinaryExpression, 2);
          jjtreeCloseNodeScope(localBSHBinaryExpression);
        }
      }
    }
  }
  
  public final void ExclusiveOrExpression()
    throws ParseException
  {
    Token localToken = null;
    AndExpression();
    for (;;)
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 110: 
        break;
      }
      localToken = jj_consume_token(110);
      AndExpression();
      BSHBinaryExpression localBSHBinaryExpression = new BSHBinaryExpression(15);
      int i = 1;
      this.jjtree.openNodeScope(localBSHBinaryExpression);
      jjtreeOpenNodeScope(localBSHBinaryExpression);
      try
      {
        this.jjtree.closeNodeScope(localBSHBinaryExpression, 2);
        i = 0;
        jjtreeCloseNodeScope(localBSHBinaryExpression);
        localBSHBinaryExpression.kind = localToken.kind;
      }
      finally
      {
        if (i != 0)
        {
          this.jjtree.closeNodeScope(localBSHBinaryExpression, 2);
          jjtreeCloseNodeScope(localBSHBinaryExpression);
        }
      }
    }
  }
  
  public final void AndExpression()
    throws ParseException
  {
    Token localToken = null;
    EqualityExpression();
    for (;;)
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 106: 
      case 107: 
        break;
      }
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 106: 
        localToken = jj_consume_token(106);
        break;
      case 107: 
        localToken = jj_consume_token(107);
        break;
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
      EqualityExpression();
      BSHBinaryExpression localBSHBinaryExpression = new BSHBinaryExpression(15);
      int i = 1;
      this.jjtree.openNodeScope(localBSHBinaryExpression);
      jjtreeOpenNodeScope(localBSHBinaryExpression);
      try
      {
        this.jjtree.closeNodeScope(localBSHBinaryExpression, 2);
        i = 0;
        jjtreeCloseNodeScope(localBSHBinaryExpression);
        localBSHBinaryExpression.kind = localToken.kind;
      }
      finally
      {
        if (i != 0)
        {
          this.jjtree.closeNodeScope(localBSHBinaryExpression, 2);
          jjtreeCloseNodeScope(localBSHBinaryExpression);
        }
      }
    }
  }
  
  public final void EqualityExpression()
    throws ParseException
  {
    Token localToken = null;
    InstanceOfExpression();
    for (;;)
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 90: 
      case 95: 
        break;
      }
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 90: 
        localToken = jj_consume_token(90);
        break;
      case 95: 
        localToken = jj_consume_token(95);
        break;
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
      InstanceOfExpression();
      BSHBinaryExpression localBSHBinaryExpression = new BSHBinaryExpression(15);
      int i = 1;
      this.jjtree.openNodeScope(localBSHBinaryExpression);
      jjtreeOpenNodeScope(localBSHBinaryExpression);
      try
      {
        this.jjtree.closeNodeScope(localBSHBinaryExpression, 2);
        i = 0;
        jjtreeCloseNodeScope(localBSHBinaryExpression);
        localBSHBinaryExpression.kind = localToken.kind;
      }
      finally
      {
        if (i != 0)
        {
          this.jjtree.closeNodeScope(localBSHBinaryExpression, 2);
          jjtreeCloseNodeScope(localBSHBinaryExpression);
        }
      }
    }
  }
  
  public final void InstanceOfExpression()
    throws ParseException
  {
    Token localToken = null;
    RelationalExpression();
    switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
    {
    case 35: 
      localToken = jj_consume_token(35);
      Type();
      BSHBinaryExpression localBSHBinaryExpression = new BSHBinaryExpression(15);
      int i = 1;
      this.jjtree.openNodeScope(localBSHBinaryExpression);
      jjtreeOpenNodeScope(localBSHBinaryExpression);
      try
      {
        this.jjtree.closeNodeScope(localBSHBinaryExpression, 2);
        i = 0;
        jjtreeCloseNodeScope(localBSHBinaryExpression);
        localBSHBinaryExpression.kind = localToken.kind;
      }
      finally
      {
        if (i != 0)
        {
          this.jjtree.closeNodeScope(localBSHBinaryExpression, 2);
          jjtreeCloseNodeScope(localBSHBinaryExpression);
        }
      }
    }
  }
  
  public final void RelationalExpression()
    throws ParseException
  {
    Token localToken = null;
    ShiftExpression();
    for (;;)
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 82: 
      case 83: 
      case 84: 
      case 85: 
      case 91: 
      case 92: 
      case 93: 
      case 94: 
        break;
      }
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 84: 
        localToken = jj_consume_token(84);
        break;
      case 85: 
        localToken = jj_consume_token(85);
        break;
      case 82: 
        localToken = jj_consume_token(82);
        break;
      case 83: 
        localToken = jj_consume_token(83);
        break;
      case 91: 
        localToken = jj_consume_token(91);
        break;
      case 92: 
        localToken = jj_consume_token(92);
        break;
      case 93: 
        localToken = jj_consume_token(93);
        break;
      case 94: 
        localToken = jj_consume_token(94);
        break;
      case 86: 
      case 87: 
      case 88: 
      case 89: 
      case 90: 
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
      ShiftExpression();
      BSHBinaryExpression localBSHBinaryExpression = new BSHBinaryExpression(15);
      int i = 1;
      this.jjtree.openNodeScope(localBSHBinaryExpression);
      jjtreeOpenNodeScope(localBSHBinaryExpression);
      try
      {
        this.jjtree.closeNodeScope(localBSHBinaryExpression, 2);
        i = 0;
        jjtreeCloseNodeScope(localBSHBinaryExpression);
        localBSHBinaryExpression.kind = localToken.kind;
      }
      finally
      {
        if (i != 0)
        {
          this.jjtree.closeNodeScope(localBSHBinaryExpression, 2);
          jjtreeCloseNodeScope(localBSHBinaryExpression);
        }
      }
    }
  }
  
  public final void ShiftExpression()
    throws ParseException
  {
    Token localToken = null;
    AdditiveExpression();
    for (;;)
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 112: 
      case 113: 
      case 114: 
      case 115: 
      case 116: 
      case 117: 
        break;
      }
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 112: 
        localToken = jj_consume_token(112);
        break;
      case 113: 
        localToken = jj_consume_token(113);
        break;
      case 114: 
        localToken = jj_consume_token(114);
        break;
      case 115: 
        localToken = jj_consume_token(115);
        break;
      case 116: 
        localToken = jj_consume_token(116);
        break;
      case 117: 
        localToken = jj_consume_token(117);
        break;
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
      AdditiveExpression();
      BSHBinaryExpression localBSHBinaryExpression = new BSHBinaryExpression(15);
      int i = 1;
      this.jjtree.openNodeScope(localBSHBinaryExpression);
      jjtreeOpenNodeScope(localBSHBinaryExpression);
      try
      {
        this.jjtree.closeNodeScope(localBSHBinaryExpression, 2);
        i = 0;
        jjtreeCloseNodeScope(localBSHBinaryExpression);
        localBSHBinaryExpression.kind = localToken.kind;
      }
      finally
      {
        if (i != 0)
        {
          this.jjtree.closeNodeScope(localBSHBinaryExpression, 2);
          jjtreeCloseNodeScope(localBSHBinaryExpression);
        }
      }
    }
  }
  
  public final void AdditiveExpression()
    throws ParseException
  {
    Token localToken = null;
    MultiplicativeExpression();
    for (;;)
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 102: 
      case 103: 
        break;
      }
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 102: 
        localToken = jj_consume_token(102);
        break;
      case 103: 
        localToken = jj_consume_token(103);
        break;
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
      MultiplicativeExpression();
      BSHBinaryExpression localBSHBinaryExpression = new BSHBinaryExpression(15);
      int i = 1;
      this.jjtree.openNodeScope(localBSHBinaryExpression);
      jjtreeOpenNodeScope(localBSHBinaryExpression);
      try
      {
        this.jjtree.closeNodeScope(localBSHBinaryExpression, 2);
        i = 0;
        jjtreeCloseNodeScope(localBSHBinaryExpression);
        localBSHBinaryExpression.kind = localToken.kind;
      }
      finally
      {
        if (i != 0)
        {
          this.jjtree.closeNodeScope(localBSHBinaryExpression, 2);
          jjtreeCloseNodeScope(localBSHBinaryExpression);
        }
      }
    }
  }
  
  public final void MultiplicativeExpression()
    throws ParseException
  {
    Token localToken = null;
    UnaryExpression();
    for (;;)
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 104: 
      case 105: 
      case 111: 
        break;
      }
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 104: 
        localToken = jj_consume_token(104);
        break;
      case 105: 
        localToken = jj_consume_token(105);
        break;
      case 111: 
        localToken = jj_consume_token(111);
        break;
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
      UnaryExpression();
      BSHBinaryExpression localBSHBinaryExpression = new BSHBinaryExpression(15);
      int i = 1;
      this.jjtree.openNodeScope(localBSHBinaryExpression);
      jjtreeOpenNodeScope(localBSHBinaryExpression);
      try
      {
        this.jjtree.closeNodeScope(localBSHBinaryExpression, 2);
        i = 0;
        jjtreeCloseNodeScope(localBSHBinaryExpression);
        localBSHBinaryExpression.kind = localToken.kind;
      }
      finally
      {
        if (i != 0)
        {
          this.jjtree.closeNodeScope(localBSHBinaryExpression, 2);
          jjtreeCloseNodeScope(localBSHBinaryExpression);
        }
      }
    }
  }
  
  public final void UnaryExpression()
    throws ParseException
  {
    Token localToken = null;
    switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
    {
    case 102: 
    case 103: 
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 102: 
        localToken = jj_consume_token(102);
        break;
      case 103: 
        localToken = jj_consume_token(103);
        break;
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
      UnaryExpression();
      BSHUnaryExpression localBSHUnaryExpression = new BSHUnaryExpression(16);
      int i = 1;
      this.jjtree.openNodeScope(localBSHUnaryExpression);
      jjtreeOpenNodeScope(localBSHUnaryExpression);
      try
      {
        this.jjtree.closeNodeScope(localBSHUnaryExpression, 1);
        i = 0;
        jjtreeCloseNodeScope(localBSHUnaryExpression);
        localBSHUnaryExpression.kind = localToken.kind;
      }
      finally
      {
        if (i != 0)
        {
          this.jjtree.closeNodeScope(localBSHUnaryExpression, 1);
          jjtreeCloseNodeScope(localBSHUnaryExpression);
        }
      }
    case 100: 
      PreIncrementExpression();
      break;
    case 101: 
      PreDecrementExpression();
      break;
    case 11: 
    case 14: 
    case 17: 
    case 22: 
    case 26: 
    case 29: 
    case 36: 
    case 38: 
    case 40: 
    case 41: 
    case 47: 
    case 55: 
    case 57: 
    case 60: 
    case 64: 
    case 66: 
    case 67: 
    case 69: 
    case 72: 
    case 86: 
    case 87: 
      UnaryExpressionNotPlusMinus();
      break;
    case 12: 
    case 13: 
    case 15: 
    case 16: 
    case 18: 
    case 19: 
    case 20: 
    case 21: 
    case 23: 
    case 24: 
    case 25: 
    case 27: 
    case 28: 
    case 30: 
    case 31: 
    case 32: 
    case 33: 
    case 34: 
    case 35: 
    case 37: 
    case 39: 
    case 42: 
    case 43: 
    case 44: 
    case 45: 
    case 46: 
    case 48: 
    case 49: 
    case 50: 
    case 51: 
    case 52: 
    case 53: 
    case 54: 
    case 56: 
    case 58: 
    case 59: 
    case 61: 
    case 62: 
    case 63: 
    case 65: 
    case 68: 
    case 70: 
    case 71: 
    case 73: 
    case 74: 
    case 75: 
    case 76: 
    case 77: 
    case 78: 
    case 79: 
    case 80: 
    case 81: 
    case 82: 
    case 83: 
    case 84: 
    case 85: 
    case 88: 
    case 89: 
    case 90: 
    case 91: 
    case 92: 
    case 93: 
    case 94: 
    case 95: 
    case 96: 
    case 97: 
    case 98: 
    case 99: 
    default: 
      jj_consume_token(-1);
      throw new ParseException();
    }
  }
  
  public final void PreIncrementExpression()
    throws ParseException
  {
    Token localToken = null;
    localToken = jj_consume_token(100);
    PrimaryExpression();
    BSHUnaryExpression localBSHUnaryExpression = new BSHUnaryExpression(16);
    int i = 1;
    this.jjtree.openNodeScope(localBSHUnaryExpression);
    jjtreeOpenNodeScope(localBSHUnaryExpression);
    try
    {
      this.jjtree.closeNodeScope(localBSHUnaryExpression, 1);
      i = 0;
      jjtreeCloseNodeScope(localBSHUnaryExpression);
      localBSHUnaryExpression.kind = localToken.kind;
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHUnaryExpression, 1);
        jjtreeCloseNodeScope(localBSHUnaryExpression);
      }
    }
  }
  
  public final void PreDecrementExpression()
    throws ParseException
  {
    Token localToken = null;
    localToken = jj_consume_token(101);
    PrimaryExpression();
    BSHUnaryExpression localBSHUnaryExpression = new BSHUnaryExpression(16);
    int i = 1;
    this.jjtree.openNodeScope(localBSHUnaryExpression);
    jjtreeOpenNodeScope(localBSHUnaryExpression);
    try
    {
      this.jjtree.closeNodeScope(localBSHUnaryExpression, 1);
      i = 0;
      jjtreeCloseNodeScope(localBSHUnaryExpression);
      localBSHUnaryExpression.kind = localToken.kind;
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHUnaryExpression, 1);
        jjtreeCloseNodeScope(localBSHUnaryExpression);
      }
    }
  }
  
  public final void UnaryExpressionNotPlusMinus()
    throws ParseException
  {
    Token localToken = null;
    switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
    {
    case 86: 
    case 87: 
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 87: 
        localToken = jj_consume_token(87);
        break;
      case 86: 
        localToken = jj_consume_token(86);
        break;
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
      UnaryExpression();
      BSHUnaryExpression localBSHUnaryExpression = new BSHUnaryExpression(16);
      int i = 1;
      this.jjtree.openNodeScope(localBSHUnaryExpression);
      jjtreeOpenNodeScope(localBSHUnaryExpression);
      try
      {
        this.jjtree.closeNodeScope(localBSHUnaryExpression, 1);
        i = 0;
        jjtreeCloseNodeScope(localBSHUnaryExpression);
        localBSHUnaryExpression.kind = localToken.kind;
      }
      finally
      {
        if (i != 0)
        {
          this.jjtree.closeNodeScope(localBSHUnaryExpression, 1);
          jjtreeCloseNodeScope(localBSHUnaryExpression);
        }
      }
    default: 
      if (jj_2_9(Integer.MAX_VALUE)) {
        CastExpression();
      } else {
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 11: 
        case 14: 
        case 17: 
        case 22: 
        case 26: 
        case 29: 
        case 36: 
        case 38: 
        case 40: 
        case 41: 
        case 47: 
        case 55: 
        case 57: 
        case 60: 
        case 64: 
        case 66: 
        case 67: 
        case 69: 
        case 72: 
          PostfixExpression();
          break;
        case 12: 
        case 13: 
        case 15: 
        case 16: 
        case 18: 
        case 19: 
        case 20: 
        case 21: 
        case 23: 
        case 24: 
        case 25: 
        case 27: 
        case 28: 
        case 30: 
        case 31: 
        case 32: 
        case 33: 
        case 34: 
        case 35: 
        case 37: 
        case 39: 
        case 42: 
        case 43: 
        case 44: 
        case 45: 
        case 46: 
        case 48: 
        case 49: 
        case 50: 
        case 51: 
        case 52: 
        case 53: 
        case 54: 
        case 56: 
        case 58: 
        case 59: 
        case 61: 
        case 62: 
        case 63: 
        case 65: 
        case 68: 
        case 70: 
        case 71: 
        default: 
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      break;
    }
  }
  
  public final void CastLookahead()
    throws ParseException
  {
    if (jj_2_10(2))
    {
      jj_consume_token(72);
      PrimitiveType();
    }
    else if (jj_2_11(Integer.MAX_VALUE))
    {
      jj_consume_token(72);
      AmbiguousName();
      jj_consume_token(76);
      jj_consume_token(77);
    }
    else
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 72: 
        jj_consume_token(72);
        AmbiguousName();
        jj_consume_token(73);
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 87: 
          jj_consume_token(87);
          break;
        case 86: 
          jj_consume_token(86);
          break;
        case 72: 
          jj_consume_token(72);
          break;
        case 69: 
          jj_consume_token(69);
          break;
        case 40: 
          jj_consume_token(40);
          break;
        case 26: 
        case 41: 
        case 55: 
        case 57: 
        case 60: 
        case 64: 
        case 66: 
        case 67: 
          Literal();
          break;
        default: 
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }
  
  public final void PostfixExpression()
    throws ParseException
  {
    Token localToken = null;
    if (jj_2_12(Integer.MAX_VALUE))
    {
      PrimaryExpression();
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 100: 
        localToken = jj_consume_token(100);
        break;
      case 101: 
        localToken = jj_consume_token(101);
        break;
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
      BSHUnaryExpression localBSHUnaryExpression = new BSHUnaryExpression(16);
      int i = 1;
      this.jjtree.openNodeScope(localBSHUnaryExpression);
      jjtreeOpenNodeScope(localBSHUnaryExpression);
      try
      {
        this.jjtree.closeNodeScope(localBSHUnaryExpression, 1);
        i = 0;
        jjtreeCloseNodeScope(localBSHUnaryExpression);
        localBSHUnaryExpression.kind = localToken.kind;
        localBSHUnaryExpression.postfix = true;
      }
      finally
      {
        if (i != 0)
        {
          this.jjtree.closeNodeScope(localBSHUnaryExpression, 1);
          jjtreeCloseNodeScope(localBSHUnaryExpression);
        }
      }
    }
    else
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 11: 
      case 14: 
      case 17: 
      case 22: 
      case 26: 
      case 29: 
      case 36: 
      case 38: 
      case 40: 
      case 41: 
      case 47: 
      case 55: 
      case 57: 
      case 60: 
      case 64: 
      case 66: 
      case 67: 
      case 69: 
      case 72: 
        PrimaryExpression();
        break;
      case 12: 
      case 13: 
      case 15: 
      case 16: 
      case 18: 
      case 19: 
      case 20: 
      case 21: 
      case 23: 
      case 24: 
      case 25: 
      case 27: 
      case 28: 
      case 30: 
      case 31: 
      case 32: 
      case 33: 
      case 34: 
      case 35: 
      case 37: 
      case 39: 
      case 42: 
      case 43: 
      case 44: 
      case 45: 
      case 46: 
      case 48: 
      case 49: 
      case 50: 
      case 51: 
      case 52: 
      case 53: 
      case 54: 
      case 56: 
      case 58: 
      case 59: 
      case 61: 
      case 62: 
      case 63: 
      case 65: 
      case 68: 
      case 70: 
      case 71: 
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }
  
  public final void CastExpression()
    throws ParseException
  {
    BSHCastExpression localBSHCastExpression = new BSHCastExpression(17);
    int i = 1;
    this.jjtree.openNodeScope(localBSHCastExpression);
    jjtreeOpenNodeScope(localBSHCastExpression);
    try
    {
      if (jj_2_13(Integer.MAX_VALUE))
      {
        jj_consume_token(72);
        Type();
        jj_consume_token(73);
        UnaryExpression();
      }
      else
      {
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 72: 
          jj_consume_token(72);
          Type();
          jj_consume_token(73);
          UnaryExpressionNotPlusMinus();
          break;
        default: 
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHCastExpression);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHCastExpression, true);
        jjtreeCloseNodeScope(localBSHCastExpression);
      }
    }
  }
  
  public final void PrimaryExpression()
    throws ParseException
  {
    BSHPrimaryExpression localBSHPrimaryExpression = new BSHPrimaryExpression(18);
    int i = 1;
    this.jjtree.openNodeScope(localBSHPrimaryExpression);
    jjtreeOpenNodeScope(localBSHPrimaryExpression);
    try
    {
      PrimaryPrefix();
      for (;;)
      {
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 74: 
        case 76: 
        case 80: 
          break;
        }
        PrimarySuffix();
      }
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHPrimaryExpression);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHPrimaryExpression, true);
        jjtreeCloseNodeScope(localBSHPrimaryExpression);
      }
    }
  }
  
  public final void MethodInvocation()
    throws ParseException
  {
    BSHMethodInvocation localBSHMethodInvocation = new BSHMethodInvocation(19);
    int i = 1;
    this.jjtree.openNodeScope(localBSHMethodInvocation);
    jjtreeOpenNodeScope(localBSHMethodInvocation);
    try
    {
      AmbiguousName();
      Arguments();
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHMethodInvocation);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHMethodInvocation, true);
        jjtreeCloseNodeScope(localBSHMethodInvocation);
      }
    }
  }
  
  public final void PrimaryPrefix()
    throws ParseException
  {
    switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
    {
    case 26: 
    case 41: 
    case 55: 
    case 57: 
    case 60: 
    case 64: 
    case 66: 
    case 67: 
      Literal();
      break;
    case 72: 
      jj_consume_token(72);
      Expression();
      jj_consume_token(73);
      break;
    case 40: 
      AllocationExpression();
      break;
    default: 
      if (jj_2_14(Integer.MAX_VALUE)) {
        MethodInvocation();
      } else if (jj_2_15(Integer.MAX_VALUE)) {
        Type();
      } else {
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 69: 
          AmbiguousName();
          break;
        default: 
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      break;
    }
  }
  
  public final void PrimarySuffix()
    throws ParseException
  {
    BSHPrimarySuffix localBSHPrimarySuffix = new BSHPrimarySuffix(20);
    int i = 1;
    this.jjtree.openNodeScope(localBSHPrimarySuffix);
    jjtreeOpenNodeScope(localBSHPrimarySuffix);
    Token localToken = null;
    try
    {
      if (jj_2_16(2))
      {
        jj_consume_token(80);
        jj_consume_token(13);
        this.jjtree.closeNodeScope(localBSHPrimarySuffix, true);
        i = 0;
        jjtreeCloseNodeScope(localBSHPrimarySuffix);
        localBSHPrimarySuffix.operation = 0;
      }
      else
      {
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 76: 
          jj_consume_token(76);
          Expression();
          jj_consume_token(77);
          this.jjtree.closeNodeScope(localBSHPrimarySuffix, true);
          i = 0;
          jjtreeCloseNodeScope(localBSHPrimarySuffix);
          localBSHPrimarySuffix.operation = 1;
          break;
        case 80: 
          jj_consume_token(80);
          localToken = jj_consume_token(69);
          switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
          {
          case 72: 
            Arguments();
            break;
          }
          this.jjtree.closeNodeScope(localBSHPrimarySuffix, true);
          i = 0;
          jjtreeCloseNodeScope(localBSHPrimarySuffix);
          localBSHPrimarySuffix.operation = 2;
          localBSHPrimarySuffix.field = localToken.image;
          break;
        case 74: 
          jj_consume_token(74);
          Expression();
          jj_consume_token(75);
          this.jjtree.closeNodeScope(localBSHPrimarySuffix, true);
          i = 0;
          jjtreeCloseNodeScope(localBSHPrimarySuffix);
          localBSHPrimarySuffix.operation = 3;
          break;
        default: 
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHPrimarySuffix);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHPrimarySuffix, true);
        jjtreeCloseNodeScope(localBSHPrimarySuffix);
      }
    }
  }
  
  public final void Literal()
    throws ParseException
  {
    BSHLiteral localBSHLiteral = new BSHLiteral(21);
    int i = 1;
    this.jjtree.openNodeScope(localBSHLiteral);
    jjtreeOpenNodeScope(localBSHLiteral);
    try
    {
      Token localToken;
      String str;
      int j;
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 60: 
        localToken = jj_consume_token(60);
        this.jjtree.closeNodeScope(localBSHLiteral, true);
        i = 0;
        jjtreeCloseNodeScope(localBSHLiteral);
        str = localToken.image;
        j = str.charAt(str.length() - 1);
        if ((j == 108) || (j == 76))
        {
          str = str.substring(0, str.length() - 1);
          localBSHLiteral.value = new Primitive(new Long(str).longValue());
        }
        else
        {
          try
          {
            localBSHLiteral.value = new Primitive(Integer.decode(str).intValue());
          }
          catch (NumberFormatException localNumberFormatException)
          {
            throw createParseException("Error or number too big for integer type: " + str);
          }
        }
        break;
      case 64: 
        localToken = jj_consume_token(64);
        this.jjtree.closeNodeScope(localBSHLiteral, true);
        i = 0;
        jjtreeCloseNodeScope(localBSHLiteral);
        str = localToken.image;
        j = str.charAt(str.length() - 1);
        if ((j == 102) || (j == 70))
        {
          str = str.substring(0, str.length() - 1);
          localBSHLiteral.value = new Primitive(new Float(str).floatValue());
        }
        else
        {
          if ((j == 100) || (j == 68)) {
            str = str.substring(0, str.length() - 1);
          }
          localBSHLiteral.value = new Primitive(new Double(str).doubleValue());
        }
        break;
      case 66: 
        localToken = jj_consume_token(66);
        this.jjtree.closeNodeScope(localBSHLiteral, true);
        i = 0;
        jjtreeCloseNodeScope(localBSHLiteral);
        try
        {
          localBSHLiteral.charSetup(localToken.image.substring(1, localToken.image.length() - 1));
        }
        catch (Exception localException1)
        {
          throw createParseException("Error parsing character: " + localToken.image);
        }
      case 67: 
        localToken = jj_consume_token(67);
        this.jjtree.closeNodeScope(localBSHLiteral, true);
        i = 0;
        jjtreeCloseNodeScope(localBSHLiteral);
        try
        {
          localBSHLiteral.stringSetup(localToken.image.substring(1, localToken.image.length() - 1));
        }
        catch (Exception localException2)
        {
          throw createParseException("Error parsing string: " + localToken.image);
        }
      case 26: 
      case 55: 
        boolean bool = BooleanLiteral();
        this.jjtree.closeNodeScope(localBSHLiteral, true);
        i = 0;
        jjtreeCloseNodeScope(localBSHLiteral);
        localBSHLiteral.value = new Primitive(bool);
        break;
      case 41: 
        NullLiteral();
        this.jjtree.closeNodeScope(localBSHLiteral, true);
        i = 0;
        jjtreeCloseNodeScope(localBSHLiteral);
        localBSHLiteral.value = Primitive.NULL;
        break;
      case 57: 
        VoidLiteral();
        this.jjtree.closeNodeScope(localBSHLiteral, true);
        i = 0;
        jjtreeCloseNodeScope(localBSHLiteral);
        localBSHLiteral.value = Primitive.VOID;
        break;
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHLiteral);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHLiteral, true);
        jjtreeCloseNodeScope(localBSHLiteral);
      }
    }
  }
  
  public final boolean BooleanLiteral()
    throws ParseException
  {
    switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
    {
    case 55: 
      jj_consume_token(55);
      return true;
    case 26: 
      jj_consume_token(26);
      return false;
    }
    jj_consume_token(-1);
    throw new ParseException();
  }
  
  public final void NullLiteral()
    throws ParseException
  {
    jj_consume_token(41);
  }
  
  public final void VoidLiteral()
    throws ParseException
  {
    jj_consume_token(57);
  }
  
  public final void Arguments()
    throws ParseException
  {
    BSHArguments localBSHArguments = new BSHArguments(22);
    int i = 1;
    this.jjtree.openNodeScope(localBSHArguments);
    jjtreeOpenNodeScope(localBSHArguments);
    try
    {
      jj_consume_token(72);
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 11: 
      case 14: 
      case 17: 
      case 22: 
      case 26: 
      case 29: 
      case 36: 
      case 38: 
      case 40: 
      case 41: 
      case 47: 
      case 55: 
      case 57: 
      case 60: 
      case 64: 
      case 66: 
      case 67: 
      case 69: 
      case 72: 
      case 86: 
      case 87: 
      case 100: 
      case 101: 
      case 102: 
      case 103: 
        ArgumentList();
        break;
      }
      jj_consume_token(73);
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHArguments);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHArguments, true);
        jjtreeCloseNodeScope(localBSHArguments);
      }
    }
  }
  
  public final void ArgumentList()
    throws ParseException
  {
    Expression();
    for (;;)
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 79: 
        break;
      }
      jj_consume_token(79);
      Expression();
    }
  }
  
  public final void AllocationExpression()
    throws ParseException
  {
    BSHAllocationExpression localBSHAllocationExpression = new BSHAllocationExpression(23);
    int i = 1;
    this.jjtree.openNodeScope(localBSHAllocationExpression);
    jjtreeOpenNodeScope(localBSHAllocationExpression);
    try
    {
      if (jj_2_18(2))
      {
        jj_consume_token(40);
        PrimitiveType();
        ArrayDimensions();
      }
      else
      {
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 40: 
          jj_consume_token(40);
          AmbiguousName();
          switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
          {
          case 76: 
            ArrayDimensions();
            break;
          case 72: 
            Arguments();
            if (jj_2_17(2)) {
              Block();
            }
            break;
          default: 
            jj_consume_token(-1);
            throw new ParseException();
          }
          break;
        default: 
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHAllocationExpression);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHAllocationExpression, true);
        jjtreeCloseNodeScope(localBSHAllocationExpression);
      }
    }
  }
  
  public final void ArrayDimensions()
    throws ParseException
  {
    BSHArrayDimensions localBSHArrayDimensions = new BSHArrayDimensions(24);
    int i = 1;
    this.jjtree.openNodeScope(localBSHArrayDimensions);
    jjtreeOpenNodeScope(localBSHArrayDimensions);
    try
    {
      if (jj_2_21(2))
      {
        do
        {
          jj_consume_token(76);
          Expression();
          jj_consume_token(77);
          localBSHArrayDimensions.addDefinedDimension();
        } while (jj_2_19(2));
        while (jj_2_20(2))
        {
          jj_consume_token(76);
          jj_consume_token(77);
          localBSHArrayDimensions.addUndefinedDimension();
        }
      }
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 76: 
        for (;;)
        {
          jj_consume_token(76);
          jj_consume_token(77);
          localBSHArrayDimensions.addUndefinedDimension();
          switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
          {
          }
        }
        ArrayInitializer();
        break;
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHArrayDimensions);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHArrayDimensions, true);
        jjtreeCloseNodeScope(localBSHArrayDimensions);
      }
    }
  }
  
  public final void Statement()
    throws ParseException
  {
    if (jj_2_22(2)) {
      LabeledStatement();
    } else {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 74: 
        Block();
        break;
      case 78: 
        EmptyStatement();
        break;
      case 11: 
      case 14: 
      case 17: 
      case 22: 
      case 26: 
      case 29: 
      case 36: 
      case 38: 
      case 40: 
      case 41: 
      case 47: 
      case 55: 
      case 57: 
      case 60: 
      case 64: 
      case 66: 
      case 67: 
      case 69: 
      case 72: 
      case 86: 
      case 87: 
      case 100: 
      case 101: 
      case 102: 
      case 103: 
        StatementExpression();
        jj_consume_token(78);
        break;
      case 50: 
        SwitchStatement();
        break;
      case 32: 
        IfStatement();
        break;
      case 59: 
        WhileStatement();
        break;
      case 21: 
        DoStatement();
        break;
      case 12: 
      case 13: 
      case 15: 
      case 16: 
      case 18: 
      case 19: 
      case 20: 
      case 23: 
      case 24: 
      case 25: 
      case 27: 
      case 28: 
      case 30: 
      case 31: 
      case 33: 
      case 34: 
      case 35: 
      case 37: 
      case 39: 
      case 42: 
      case 43: 
      case 44: 
      case 45: 
      case 46: 
      case 48: 
      case 49: 
      case 51: 
      case 52: 
      case 53: 
      case 54: 
      case 56: 
      case 58: 
      case 61: 
      case 62: 
      case 63: 
      case 65: 
      case 68: 
      case 70: 
      case 71: 
      case 73: 
      case 75: 
      case 76: 
      case 77: 
      case 79: 
      case 80: 
      case 81: 
      case 82: 
      case 83: 
      case 84: 
      case 85: 
      case 88: 
      case 89: 
      case 90: 
      case 91: 
      case 92: 
      case 93: 
      case 94: 
      case 95: 
      case 96: 
      case 97: 
      case 98: 
      case 99: 
      default: 
        if (isRegularForStatement()) {
          ForStatement();
        } else {
          switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
          {
          case 30: 
            EnhancedForStatement();
            break;
          case 12: 
            BreakStatement();
            break;
          case 19: 
            ContinueStatement();
            break;
          case 46: 
            ReturnStatement();
            break;
          case 51: 
            SynchronizedStatement();
            break;
          case 53: 
            ThrowStatement();
            break;
          case 56: 
            TryStatement();
            break;
          default: 
            jj_consume_token(-1);
            throw new ParseException();
          }
        }
        break;
      }
    }
  }
  
  public final void LabeledStatement()
    throws ParseException
  {
    jj_consume_token(69);
    jj_consume_token(89);
    Statement();
  }
  
  public final void Block()
    throws ParseException
  {
    BSHBlock localBSHBlock = new BSHBlock(25);
    int i = 1;
    this.jjtree.openNodeScope(localBSHBlock);
    jjtreeOpenNodeScope(localBSHBlock);
    try
    {
      jj_consume_token(74);
      while (jj_2_23(1)) {
        BlockStatement();
      }
      jj_consume_token(75);
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHBlock);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHBlock, true);
        jjtreeCloseNodeScope(localBSHBlock);
      }
    }
  }
  
  public final void BlockStatement()
    throws ParseException
  {
    if (jj_2_24(Integer.MAX_VALUE))
    {
      ClassDeclaration();
    }
    else if (jj_2_25(Integer.MAX_VALUE))
    {
      MethodDeclaration();
    }
    else if (jj_2_26(Integer.MAX_VALUE))
    {
      MethodDeclaration();
    }
    else if (jj_2_27(Integer.MAX_VALUE))
    {
      TypedVariableDeclaration();
      jj_consume_token(78);
    }
    else if (jj_2_28(1))
    {
      Statement();
    }
    else
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 34: 
      case 48: 
        ImportDeclaration();
        break;
      case 42: 
        PackageDeclaration();
        break;
      case 68: 
        FormalComment();
        break;
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }
  
  public final void FormalComment()
    throws ParseException
  {
    BSHFormalComment localBSHFormalComment = new BSHFormalComment(26);
    int i = 1;
    this.jjtree.openNodeScope(localBSHFormalComment);
    jjtreeOpenNodeScope(localBSHFormalComment);
    try
    {
      Token localToken = jj_consume_token(68);
      this.jjtree.closeNodeScope(localBSHFormalComment, this.retainComments);
      i = 0;
      jjtreeCloseNodeScope(localBSHFormalComment);
      localBSHFormalComment.text = localToken.image;
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHFormalComment, this.retainComments);
        jjtreeCloseNodeScope(localBSHFormalComment);
      }
    }
  }
  
  public final void EmptyStatement()
    throws ParseException
  {
    jj_consume_token(78);
  }
  
  public final void StatementExpression()
    throws ParseException
  {
    Expression();
  }
  
  public final void SwitchStatement()
    throws ParseException
  {
    BSHSwitchStatement localBSHSwitchStatement = new BSHSwitchStatement(27);
    int i = 1;
    this.jjtree.openNodeScope(localBSHSwitchStatement);
    jjtreeOpenNodeScope(localBSHSwitchStatement);
    try
    {
      jj_consume_token(50);
      jj_consume_token(72);
      Expression();
      jj_consume_token(73);
      jj_consume_token(74);
      for (;;)
      {
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 15: 
        case 20: 
          break;
        }
        SwitchLabel();
        while (jj_2_29(1)) {
          BlockStatement();
        }
      }
      jj_consume_token(75);
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHSwitchStatement);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHSwitchStatement, true);
        jjtreeCloseNodeScope(localBSHSwitchStatement);
      }
    }
  }
  
  public final void SwitchLabel()
    throws ParseException
  {
    BSHSwitchLabel localBSHSwitchLabel = new BSHSwitchLabel(28);
    int i = 1;
    this.jjtree.openNodeScope(localBSHSwitchLabel);
    jjtreeOpenNodeScope(localBSHSwitchLabel);
    try
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 15: 
        jj_consume_token(15);
        Expression();
        jj_consume_token(89);
        break;
      case 20: 
        jj_consume_token(20);
        jj_consume_token(89);
        this.jjtree.closeNodeScope(localBSHSwitchLabel, true);
        i = 0;
        jjtreeCloseNodeScope(localBSHSwitchLabel);
        localBSHSwitchLabel.isDefault = true;
        break;
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHSwitchLabel);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHSwitchLabel, true);
        jjtreeCloseNodeScope(localBSHSwitchLabel);
      }
    }
  }
  
  public final void IfStatement()
    throws ParseException
  {
    BSHIfStatement localBSHIfStatement = new BSHIfStatement(29);
    int i = 1;
    this.jjtree.openNodeScope(localBSHIfStatement);
    jjtreeOpenNodeScope(localBSHIfStatement);
    try
    {
      jj_consume_token(32);
      jj_consume_token(72);
      Expression();
      jj_consume_token(73);
      Statement();
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 23: 
        jj_consume_token(23);
        Statement();
      }
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHIfStatement);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHIfStatement, true);
        jjtreeCloseNodeScope(localBSHIfStatement);
      }
    }
  }
  
  public final void WhileStatement()
    throws ParseException
  {
    BSHWhileStatement localBSHWhileStatement = new BSHWhileStatement(30);
    int i = 1;
    this.jjtree.openNodeScope(localBSHWhileStatement);
    jjtreeOpenNodeScope(localBSHWhileStatement);
    try
    {
      jj_consume_token(59);
      jj_consume_token(72);
      Expression();
      jj_consume_token(73);
      Statement();
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHWhileStatement);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHWhileStatement, true);
        jjtreeCloseNodeScope(localBSHWhileStatement);
      }
    }
  }
  
  public final void DoStatement()
    throws ParseException
  {
    BSHWhileStatement localBSHWhileStatement = new BSHWhileStatement(30);
    int i = 1;
    this.jjtree.openNodeScope(localBSHWhileStatement);
    jjtreeOpenNodeScope(localBSHWhileStatement);
    try
    {
      jj_consume_token(21);
      Statement();
      jj_consume_token(59);
      jj_consume_token(72);
      Expression();
      jj_consume_token(73);
      jj_consume_token(78);
      this.jjtree.closeNodeScope(localBSHWhileStatement, true);
      i = 0;
      jjtreeCloseNodeScope(localBSHWhileStatement);
      localBSHWhileStatement.isDoStatement = true;
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHWhileStatement);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHWhileStatement, true);
        jjtreeCloseNodeScope(localBSHWhileStatement);
      }
    }
  }
  
  public final void ForStatement()
    throws ParseException
  {
    BSHForStatement localBSHForStatement = new BSHForStatement(31);
    int i = 1;
    this.jjtree.openNodeScope(localBSHForStatement);
    jjtreeOpenNodeScope(localBSHForStatement);
    Object localObject1 = null;
    try
    {
      jj_consume_token(30);
      jj_consume_token(72);
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 10: 
      case 11: 
      case 14: 
      case 17: 
      case 22: 
      case 26: 
      case 27: 
      case 29: 
      case 36: 
      case 38: 
      case 39: 
      case 40: 
      case 41: 
      case 43: 
      case 44: 
      case 45: 
      case 47: 
      case 48: 
      case 49: 
      case 51: 
      case 52: 
      case 55: 
      case 57: 
      case 58: 
      case 60: 
      case 64: 
      case 66: 
      case 67: 
      case 69: 
      case 72: 
      case 86: 
      case 87: 
      case 100: 
      case 101: 
      case 102: 
      case 103: 
        ForInit();
        localBSHForStatement.hasForInit = true;
        break;
      }
      jj_consume_token(78);
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 11: 
      case 14: 
      case 17: 
      case 22: 
      case 26: 
      case 29: 
      case 36: 
      case 38: 
      case 40: 
      case 41: 
      case 47: 
      case 55: 
      case 57: 
      case 60: 
      case 64: 
      case 66: 
      case 67: 
      case 69: 
      case 72: 
      case 86: 
      case 87: 
      case 100: 
      case 101: 
      case 102: 
      case 103: 
        Expression();
        localBSHForStatement.hasExpression = true;
        break;
      }
      jj_consume_token(78);
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 11: 
      case 14: 
      case 17: 
      case 22: 
      case 26: 
      case 29: 
      case 36: 
      case 38: 
      case 40: 
      case 41: 
      case 47: 
      case 55: 
      case 57: 
      case 60: 
      case 64: 
      case 66: 
      case 67: 
      case 69: 
      case 72: 
      case 86: 
      case 87: 
      case 100: 
      case 101: 
      case 102: 
      case 103: 
        ForUpdate();
        localBSHForStatement.hasForUpdate = true;
        break;
      }
      jj_consume_token(73);
      Statement();
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHForStatement);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHForStatement, true);
        jjtreeCloseNodeScope(localBSHForStatement);
      }
    }
  }
  
  public final void EnhancedForStatement()
    throws ParseException
  {
    BSHEnhancedForStatement localBSHEnhancedForStatement = new BSHEnhancedForStatement(32);
    int i = 1;
    this.jjtree.openNodeScope(localBSHEnhancedForStatement);
    jjtreeOpenNodeScope(localBSHEnhancedForStatement);
    Token localToken = null;
    try
    {
      if (jj_2_30(4))
      {
        jj_consume_token(30);
        jj_consume_token(72);
        localToken = jj_consume_token(69);
        jj_consume_token(89);
        Expression();
        jj_consume_token(73);
        Statement();
        this.jjtree.closeNodeScope(localBSHEnhancedForStatement, true);
        i = 0;
        jjtreeCloseNodeScope(localBSHEnhancedForStatement);
        localBSHEnhancedForStatement.varName = localToken.image;
      }
      else
      {
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 30: 
          jj_consume_token(30);
          jj_consume_token(72);
          Type();
          localToken = jj_consume_token(69);
          jj_consume_token(89);
          Expression();
          jj_consume_token(73);
          Statement();
          this.jjtree.closeNodeScope(localBSHEnhancedForStatement, true);
          i = 0;
          jjtreeCloseNodeScope(localBSHEnhancedForStatement);
          localBSHEnhancedForStatement.varName = localToken.image;
          break;
        default: 
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHEnhancedForStatement);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHEnhancedForStatement, true);
        jjtreeCloseNodeScope(localBSHEnhancedForStatement);
      }
    }
  }
  
  public final void ForInit()
    throws ParseException
  {
    Object localObject = null;
    if (jj_2_31(Integer.MAX_VALUE)) {
      TypedVariableDeclaration();
    } else {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 11: 
      case 14: 
      case 17: 
      case 22: 
      case 26: 
      case 29: 
      case 36: 
      case 38: 
      case 40: 
      case 41: 
      case 47: 
      case 55: 
      case 57: 
      case 60: 
      case 64: 
      case 66: 
      case 67: 
      case 69: 
      case 72: 
      case 86: 
      case 87: 
      case 100: 
      case 101: 
      case 102: 
      case 103: 
        StatementExpressionList();
        break;
      case 12: 
      case 13: 
      case 15: 
      case 16: 
      case 18: 
      case 19: 
      case 20: 
      case 21: 
      case 23: 
      case 24: 
      case 25: 
      case 27: 
      case 28: 
      case 30: 
      case 31: 
      case 32: 
      case 33: 
      case 34: 
      case 35: 
      case 37: 
      case 39: 
      case 42: 
      case 43: 
      case 44: 
      case 45: 
      case 46: 
      case 48: 
      case 49: 
      case 50: 
      case 51: 
      case 52: 
      case 53: 
      case 54: 
      case 56: 
      case 58: 
      case 59: 
      case 61: 
      case 62: 
      case 63: 
      case 65: 
      case 68: 
      case 70: 
      case 71: 
      case 73: 
      case 74: 
      case 75: 
      case 76: 
      case 77: 
      case 78: 
      case 79: 
      case 80: 
      case 81: 
      case 82: 
      case 83: 
      case 84: 
      case 85: 
      case 88: 
      case 89: 
      case 90: 
      case 91: 
      case 92: 
      case 93: 
      case 94: 
      case 95: 
      case 96: 
      case 97: 
      case 98: 
      case 99: 
      default: 
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }
  
  public final void TypedVariableDeclaration()
    throws ParseException
  {
    BSHTypedVariableDeclaration localBSHTypedVariableDeclaration = new BSHTypedVariableDeclaration(33);
    int i = 1;
    this.jjtree.openNodeScope(localBSHTypedVariableDeclaration);
    jjtreeOpenNodeScope(localBSHTypedVariableDeclaration);
    Object localObject1 = null;
    try
    {
      Modifiers localModifiers = Modifiers(2, false);
      Type();
      VariableDeclarator();
      for (;;)
      {
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 79: 
          break;
        }
        jj_consume_token(79);
        VariableDeclarator();
      }
      this.jjtree.closeNodeScope(localBSHTypedVariableDeclaration, true);
      i = 0;
      jjtreeCloseNodeScope(localBSHTypedVariableDeclaration);
      localBSHTypedVariableDeclaration.modifiers = localModifiers;
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHTypedVariableDeclaration);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHTypedVariableDeclaration, true);
        jjtreeCloseNodeScope(localBSHTypedVariableDeclaration);
      }
    }
  }
  
  public final void StatementExpressionList()
    throws ParseException
  {
    BSHStatementExpressionList localBSHStatementExpressionList = new BSHStatementExpressionList(34);
    int i = 1;
    this.jjtree.openNodeScope(localBSHStatementExpressionList);
    jjtreeOpenNodeScope(localBSHStatementExpressionList);
    try
    {
      StatementExpression();
      for (;;)
      {
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 79: 
          break;
        }
        jj_consume_token(79);
        StatementExpression();
      }
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHStatementExpressionList);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHStatementExpressionList, true);
        jjtreeCloseNodeScope(localBSHStatementExpressionList);
      }
    }
  }
  
  public final void ForUpdate()
    throws ParseException
  {
    StatementExpressionList();
  }
  
  public final void BreakStatement()
    throws ParseException
  {
    BSHReturnStatement localBSHReturnStatement = new BSHReturnStatement(35);
    int i = 1;
    this.jjtree.openNodeScope(localBSHReturnStatement);
    jjtreeOpenNodeScope(localBSHReturnStatement);
    try
    {
      jj_consume_token(12);
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 69: 
        jj_consume_token(69);
        break;
      }
      jj_consume_token(78);
      this.jjtree.closeNodeScope(localBSHReturnStatement, true);
      i = 0;
      jjtreeCloseNodeScope(localBSHReturnStatement);
      localBSHReturnStatement.kind = 12;
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHReturnStatement, true);
        jjtreeCloseNodeScope(localBSHReturnStatement);
      }
    }
  }
  
  public final void ContinueStatement()
    throws ParseException
  {
    BSHReturnStatement localBSHReturnStatement = new BSHReturnStatement(35);
    int i = 1;
    this.jjtree.openNodeScope(localBSHReturnStatement);
    jjtreeOpenNodeScope(localBSHReturnStatement);
    try
    {
      jj_consume_token(19);
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 69: 
        jj_consume_token(69);
        break;
      }
      jj_consume_token(78);
      this.jjtree.closeNodeScope(localBSHReturnStatement, true);
      i = 0;
      jjtreeCloseNodeScope(localBSHReturnStatement);
      localBSHReturnStatement.kind = 19;
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHReturnStatement, true);
        jjtreeCloseNodeScope(localBSHReturnStatement);
      }
    }
  }
  
  public final void ReturnStatement()
    throws ParseException
  {
    BSHReturnStatement localBSHReturnStatement = new BSHReturnStatement(35);
    int i = 1;
    this.jjtree.openNodeScope(localBSHReturnStatement);
    jjtreeOpenNodeScope(localBSHReturnStatement);
    try
    {
      jj_consume_token(46);
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 11: 
      case 14: 
      case 17: 
      case 22: 
      case 26: 
      case 29: 
      case 36: 
      case 38: 
      case 40: 
      case 41: 
      case 47: 
      case 55: 
      case 57: 
      case 60: 
      case 64: 
      case 66: 
      case 67: 
      case 69: 
      case 72: 
      case 86: 
      case 87: 
      case 100: 
      case 101: 
      case 102: 
      case 103: 
        Expression();
        break;
      }
      jj_consume_token(78);
      this.jjtree.closeNodeScope(localBSHReturnStatement, true);
      i = 0;
      jjtreeCloseNodeScope(localBSHReturnStatement);
      localBSHReturnStatement.kind = 46;
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHReturnStatement);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHReturnStatement, true);
        jjtreeCloseNodeScope(localBSHReturnStatement);
      }
    }
  }
  
  public final void SynchronizedStatement()
    throws ParseException
  {
    BSHBlock localBSHBlock = new BSHBlock(25);
    int i = 1;
    this.jjtree.openNodeScope(localBSHBlock);
    jjtreeOpenNodeScope(localBSHBlock);
    try
    {
      jj_consume_token(51);
      jj_consume_token(72);
      Expression();
      jj_consume_token(73);
      Block();
      this.jjtree.closeNodeScope(localBSHBlock, true);
      i = 0;
      jjtreeCloseNodeScope(localBSHBlock);
      localBSHBlock.isSynchronized = true;
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHBlock);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHBlock, true);
        jjtreeCloseNodeScope(localBSHBlock);
      }
    }
  }
  
  public final void ThrowStatement()
    throws ParseException
  {
    BSHThrowStatement localBSHThrowStatement = new BSHThrowStatement(36);
    int i = 1;
    this.jjtree.openNodeScope(localBSHThrowStatement);
    jjtreeOpenNodeScope(localBSHThrowStatement);
    try
    {
      jj_consume_token(53);
      Expression();
      jj_consume_token(78);
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHThrowStatement);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHThrowStatement, true);
        jjtreeCloseNodeScope(localBSHThrowStatement);
      }
    }
  }
  
  public final void TryStatement()
    throws ParseException
  {
    BSHTryStatement localBSHTryStatement = new BSHTryStatement(37);
    int i = 1;
    this.jjtree.openNodeScope(localBSHTryStatement);
    jjtreeOpenNodeScope(localBSHTryStatement);
    int j = 0;
    try
    {
      jj_consume_token(56);
      Block();
      for (;;)
      {
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 16: 
          break;
        }
        jj_consume_token(16);
        jj_consume_token(72);
        FormalParameter();
        jj_consume_token(73);
        Block();
        j = 1;
      }
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 28: 
        jj_consume_token(28);
        Block();
        j = 1;
        break;
      }
      this.jjtree.closeNodeScope(localBSHTryStatement, true);
      i = 0;
      jjtreeCloseNodeScope(localBSHTryStatement);
      if (j == 0) {
        throw generateParseException();
      }
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localBSHTryStatement);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0)
      {
        this.jjtree.closeNodeScope(localBSHTryStatement, true);
        jjtreeCloseNodeScope(localBSHTryStatement);
      }
    }
  }
  
  private final boolean jj_2_1(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_1();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_2(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_2();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_3(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_3();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_4(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_4();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_5(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_5();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_6(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_6();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_7(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_7();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_8(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_8();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_9(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_9();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_10(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_10();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_11(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_11();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_12(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_12();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_13(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_13();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_14(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_14();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_15(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_15();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_16(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_16();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_17(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_17();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_18(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_18();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_19(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_19();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_20(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_20();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_21(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_21();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_22(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_22();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_23(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_23();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_24(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_24();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_25(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_25();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_26(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_26();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_27(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_27();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_28(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_28();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_29(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_29();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_30(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_30();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_2_31(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      return !jj_3_31();
    }
    catch (LookaheadSuccess localLookaheadSuccess) {}
    return true;
  }
  
  private final boolean jj_3R_46()
  {
    return jj_3R_91();
  }
  
  private final boolean jj_3R_28()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_46())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_47())
      {
        this.jj_scanpos = localToken;
        if (jj_3R_48())
        {
          this.jj_scanpos = localToken;
          if (jj_3R_49())
          {
            this.jj_scanpos = localToken;
            if (jj_3_28())
            {
              this.jj_scanpos = localToken;
              if (jj_3R_50())
              {
                this.jj_scanpos = localToken;
                if (jj_3R_51())
                {
                  this.jj_scanpos = localToken;
                  if (jj_3R_52()) {
                    return true;
                  }
                }
              }
            }
          }
        }
      }
    }
    return false;
  }
  
  private final boolean jj_3_23()
  {
    return jj_3R_28();
  }
  
  private final boolean jj_3R_161()
  {
    if (jj_3R_164()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_169());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3R_38()
  {
    if (jj_scan_token(74)) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3_23());
    this.jj_scanpos = localToken;
    return jj_scan_token(75);
  }
  
  private final boolean jj_3R_158()
  {
    if (jj_3R_161()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_167());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3R_40()
  {
    if (jj_scan_token(69)) {
      return true;
    }
    if (jj_scan_token(89)) {
      return true;
    }
    return jj_3R_45();
  }
  
  private final boolean jj_3R_156()
  {
    if (jj_scan_token(88)) {
      return true;
    }
    if (jj_3R_39()) {
      return true;
    }
    if (jj_scan_token(89)) {
      return true;
    }
    return jj_3R_108();
  }
  
  private final boolean jj_3R_165()
  {
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(108))
    {
      this.jj_scanpos = localToken;
      if (jj_scan_token(109)) {
        return true;
      }
    }
    return jj_3R_158();
  }
  
  private final boolean jj_3R_153()
  {
    if (jj_3R_158()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_165());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3R_90()
  {
    return jj_3R_124();
  }
  
  private final boolean jj_3R_89()
  {
    return jj_3R_123();
  }
  
  private final boolean jj_3R_88()
  {
    return jj_3R_122();
  }
  
  private final boolean jj_3R_162()
  {
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(98))
    {
      this.jj_scanpos = localToken;
      if (jj_scan_token(99)) {
        return true;
      }
    }
    return jj_3R_153();
  }
  
  private final boolean jj_3R_87()
  {
    return jj_3R_121();
  }
  
  private final boolean jj_3R_148()
  {
    if (jj_3R_153()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_162());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3R_86()
  {
    return jj_3R_120();
  }
  
  private final boolean jj_3R_85()
  {
    return jj_3R_119();
  }
  
  private final boolean jj_3R_84()
  {
    return jj_3R_118();
  }
  
  private final boolean jj_3R_159()
  {
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(96))
    {
      this.jj_scanpos = localToken;
      if (jj_scan_token(97)) {
        return true;
      }
    }
    return jj_3R_148();
  }
  
  private final boolean jj_3R_83()
  {
    return jj_3R_117();
  }
  
  private final boolean jj_3R_135()
  {
    if (jj_3R_148()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_159());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3R_82()
  {
    return jj_3R_116();
  }
  
  private final boolean jj_3R_81()
  {
    return jj_3R_115();
  }
  
  private final boolean jj_3R_80()
  {
    return jj_3R_114();
  }
  
  private final boolean jj_3R_108()
  {
    if (jj_3R_135()) {
      return true;
    }
    Token localToken = this.jj_scanpos;
    if (jj_3R_156()) {
      this.jj_scanpos = localToken;
    }
    return false;
  }
  
  private final boolean jj_3R_79()
  {
    return jj_3R_113();
  }
  
  private final boolean jj_3R_78()
  {
    if (jj_3R_112()) {
      return true;
    }
    return jj_scan_token(78);
  }
  
  private final boolean jj_3_17()
  {
    return jj_3R_38();
  }
  
  private final boolean jj_3R_77()
  {
    return jj_3R_38();
  }
  
  private final boolean jj_3R_45()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3_22())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_77())
      {
        this.jj_scanpos = localToken;
        if (jj_scan_token(78))
        {
          this.jj_scanpos = localToken;
          if (jj_3R_78())
          {
            this.jj_scanpos = localToken;
            if (jj_3R_79())
            {
              this.jj_scanpos = localToken;
              if (jj_3R_80())
              {
                this.jj_scanpos = localToken;
                if (jj_3R_81())
                {
                  this.jj_scanpos = localToken;
                  if (jj_3R_82())
                  {
                    this.jj_scanpos = localToken;
                    this.lookingAhead = true;
                    this.jj_semLA = isRegularForStatement();
                    this.lookingAhead = false;
                    if ((!this.jj_semLA) || (jj_3R_83()))
                    {
                      this.jj_scanpos = localToken;
                      if (jj_3R_84())
                      {
                        this.jj_scanpos = localToken;
                        if (jj_3R_85())
                        {
                          this.jj_scanpos = localToken;
                          if (jj_3R_86())
                          {
                            this.jj_scanpos = localToken;
                            if (jj_3R_87())
                            {
                              this.jj_scanpos = localToken;
                              if (jj_3R_88())
                              {
                                this.jj_scanpos = localToken;
                                if (jj_3R_89())
                                {
                                  this.jj_scanpos = localToken;
                                  if (jj_3R_90()) {
                                    return true;
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return false;
  }
  
  private final boolean jj_3_22()
  {
    return jj_3R_40();
  }
  
  private final boolean jj_3R_34()
  {
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(81))
    {
      this.jj_scanpos = localToken;
      if (jj_scan_token(120))
      {
        this.jj_scanpos = localToken;
        if (jj_scan_token(121))
        {
          this.jj_scanpos = localToken;
          if (jj_scan_token(127))
          {
            this.jj_scanpos = localToken;
            if (jj_scan_token(118))
            {
              this.jj_scanpos = localToken;
              if (jj_scan_token(119))
              {
                this.jj_scanpos = localToken;
                if (jj_scan_token(122))
                {
                  this.jj_scanpos = localToken;
                  if (jj_scan_token(126))
                  {
                    this.jj_scanpos = localToken;
                    if (jj_scan_token(124))
                    {
                      this.jj_scanpos = localToken;
                      if (jj_scan_token(128))
                      {
                        this.jj_scanpos = localToken;
                        if (jj_scan_token(129))
                        {
                          this.jj_scanpos = localToken;
                          if (jj_scan_token(130))
                          {
                            this.jj_scanpos = localToken;
                            if (jj_scan_token(131))
                            {
                              this.jj_scanpos = localToken;
                              if (jj_scan_token(132))
                              {
                                this.jj_scanpos = localToken;
                                if (jj_scan_token(133)) {
                                  return true;
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return false;
  }
  
  private final boolean jj_3R_111()
  {
    if (jj_scan_token(79)) {
      return true;
    }
    return jj_3R_29();
  }
  
  private final boolean jj_3R_160()
  {
    if (jj_scan_token(76)) {
      return true;
    }
    return jj_scan_token(77);
  }
  
  private final boolean jj_3R_152()
  {
    if (jj_3R_69()) {
      return true;
    }
    Token localToken = this.jj_scanpos;
    if (jj_3_17()) {
      this.jj_scanpos = localToken;
    }
    return false;
  }
  
  private final boolean jj_3R_157()
  {
    if (jj_3R_160()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_160());
    this.jj_scanpos = localToken;
    return jj_3R_97();
  }
  
  private final boolean jj_3_8()
  {
    if (jj_3R_33()) {
      return true;
    }
    return jj_3R_34();
  }
  
  private final boolean jj_3_20()
  {
    if (jj_scan_token(76)) {
      return true;
    }
    return jj_scan_token(77);
  }
  
  private final boolean jj_3R_151()
  {
    return jj_3R_150();
  }
  
  private final boolean jj_3_19()
  {
    if (jj_scan_token(76)) {
      return true;
    }
    if (jj_3R_39()) {
      return true;
    }
    return jj_scan_token(77);
  }
  
  private final boolean jj_3R_107()
  {
    if (jj_3R_33()) {
      return true;
    }
    if (jj_3R_34()) {
      return true;
    }
    return jj_3R_39();
  }
  
  private final boolean jj_3_21()
  {
    if (jj_3_19()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3_19());
    this.jj_scanpos = localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3_20());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3R_150()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3_21())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_157()) {
        return true;
      }
    }
    return false;
  }
  
  private final boolean jj_3R_71()
  {
    return jj_3R_108();
  }
  
  private final boolean jj_3R_39()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_70())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_71()) {
        return true;
      }
    }
    return false;
  }
  
  private final boolean jj_3R_70()
  {
    return jj_3R_107();
  }
  
  private final boolean jj_3R_145()
  {
    if (jj_scan_token(40)) {
      return true;
    }
    if (jj_3R_29()) {
      return true;
    }
    Token localToken = this.jj_scanpos;
    if (jj_3R_151())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_152()) {
        return true;
      }
    }
    return false;
  }
  
  private final boolean jj_3_18()
  {
    if (jj_scan_token(40)) {
      return true;
    }
    if (jj_3R_36()) {
      return true;
    }
    return jj_3R_150();
  }
  
  private final boolean jj_3R_130()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3_18())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_145()) {
        return true;
      }
    }
    return false;
  }
  
  private final boolean jj_3R_147()
  {
    if (jj_scan_token(79)) {
      return true;
    }
    return jj_3R_39();
  }
  
  private final boolean jj_3R_76()
  {
    if (jj_3R_29()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_111());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3R_134()
  {
    if (jj_3R_39()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_147());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3R_106()
  {
    return jj_3R_134();
  }
  
  private final boolean jj_3_7()
  {
    if (jj_scan_token(80)) {
      return true;
    }
    return jj_scan_token(69);
  }
  
  private final boolean jj_3R_69()
  {
    if (jj_scan_token(72)) {
      return true;
    }
    Token localToken = this.jj_scanpos;
    if (jj_3R_106()) {
      this.jj_scanpos = localToken;
    }
    return jj_scan_token(73);
  }
  
  private final boolean jj_3R_29()
  {
    if (jj_scan_token(69)) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3_7());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3R_68()
  {
    return jj_scan_token(22);
  }
  
  private final boolean jj_3R_67()
  {
    return jj_scan_token(29);
  }
  
  private final boolean jj_3R_155()
  {
    return jj_scan_token(26);
  }
  
  private final boolean jj_3R_66()
  {
    return jj_scan_token(38);
  }
  
  private final boolean jj_3R_65()
  {
    return jj_scan_token(36);
  }
  
  private final boolean jj_3R_154()
  {
    return jj_scan_token(55);
  }
  
  private final boolean jj_3R_149()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_154())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_155()) {
        return true;
      }
    }
    return false;
  }
  
  private final boolean jj_3R_64()
  {
    return jj_scan_token(47);
  }
  
  private final boolean jj_3R_56()
  {
    return jj_3R_29();
  }
  
  private final boolean jj_3R_63()
  {
    return jj_scan_token(14);
  }
  
  private final boolean jj_3R_62()
  {
    return jj_scan_token(17);
  }
  
  private final boolean jj_3R_61()
  {
    return jj_scan_token(11);
  }
  
  private final boolean jj_3R_36()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_61())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_62())
      {
        this.jj_scanpos = localToken;
        if (jj_3R_63())
        {
          this.jj_scanpos = localToken;
          if (jj_3R_64())
          {
            this.jj_scanpos = localToken;
            if (jj_3R_65())
            {
              this.jj_scanpos = localToken;
              if (jj_3R_66())
              {
                this.jj_scanpos = localToken;
                if (jj_3R_67())
                {
                  this.jj_scanpos = localToken;
                  if (jj_3R_68()) {
                    return true;
                  }
                }
              }
            }
          }
        }
      }
    }
    return false;
  }
  
  private final boolean jj_3R_144()
  {
    return jj_scan_token(57);
  }
  
  private final boolean jj_3R_74()
  {
    return jj_3R_32();
  }
  
  private final boolean jj_3R_42()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_73())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_74()) {
        return true;
      }
    }
    return false;
  }
  
  private final boolean jj_3R_73()
  {
    return jj_scan_token(57);
  }
  
  private final boolean jj_3R_143()
  {
    return jj_scan_token(41);
  }
  
  private final boolean jj_3_6()
  {
    if (jj_scan_token(76)) {
      return true;
    }
    return jj_scan_token(77);
  }
  
  private final boolean jj_3R_142()
  {
    return jj_3R_149();
  }
  
  private final boolean jj_3R_55()
  {
    return jj_3R_36();
  }
  
  private final boolean jj_3R_110()
  {
    if (jj_scan_token(79)) {
      return true;
    }
    return jj_3R_109();
  }
  
  private final boolean jj_3R_141()
  {
    return jj_scan_token(67);
  }
  
  private final boolean jj_3R_32()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_55())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_56()) {
        return true;
      }
    }
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3_6());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3R_140()
  {
    return jj_scan_token(66);
  }
  
  private final boolean jj_3R_190()
  {
    if (jj_scan_token(28)) {
      return true;
    }
    return jj_3R_38();
  }
  
  private final boolean jj_3_4()
  {
    if (jj_scan_token(79)) {
      return true;
    }
    return jj_3R_31();
  }
  
  private final boolean jj_3R_189()
  {
    if (jj_scan_token(16)) {
      return true;
    }
    if (jj_scan_token(72)) {
      return true;
    }
    if (jj_3R_109()) {
      return true;
    }
    if (jj_scan_token(73)) {
      return true;
    }
    return jj_3R_38();
  }
  
  private final boolean jj_3R_136()
  {
    return jj_scan_token(69);
  }
  
  private final boolean jj_3_5()
  {
    if (jj_3R_32()) {
      return true;
    }
    return jj_scan_token(69);
  }
  
  private final boolean jj_3R_75()
  {
    if (jj_3R_109()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_110());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3R_109()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3_5())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_136()) {
        return true;
      }
    }
    return false;
  }
  
  private final boolean jj_3R_124()
  {
    if (jj_scan_token(56)) {
      return true;
    }
    if (jj_3R_38()) {
      return true;
    }
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_189());
    this.jj_scanpos = localToken;
    Token localToken = this.jj_scanpos;
    if (jj_3R_190()) {
      this.jj_scanpos = localToken;
    }
    return false;
  }
  
  private final boolean jj_3R_43()
  {
    if (jj_scan_token(72)) {
      return true;
    }
    Token localToken = this.jj_scanpos;
    if (jj_3R_75()) {
      this.jj_scanpos = localToken;
    }
    return jj_scan_token(73);
  }
  
  private final boolean jj_3R_163()
  {
    if (jj_3R_31()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3_4());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3R_139()
  {
    return jj_scan_token(64);
  }
  
  private final boolean jj_3R_97()
  {
    if (jj_scan_token(74)) {
      return true;
    }
    Token localToken = this.jj_scanpos;
    if (jj_3R_163()) {
      this.jj_scanpos = localToken;
    }
    localToken = this.jj_scanpos;
    if (jj_scan_token(79)) {
      this.jj_scanpos = localToken;
    }
    return jj_scan_token(75);
  }
  
  private final boolean jj_3R_30()
  {
    if (jj_scan_token(80)) {
      return true;
    }
    return jj_scan_token(104);
  }
  
  private final boolean jj_3R_123()
  {
    if (jj_scan_token(53)) {
      return true;
    }
    if (jj_3R_39()) {
      return true;
    }
    return jj_scan_token(78);
  }
  
  private final boolean jj_3R_180()
  {
    if (jj_scan_token(81)) {
      return true;
    }
    return jj_3R_31();
  }
  
  private final boolean jj_3R_54()
  {
    return jj_3R_39();
  }
  
  private final boolean jj_3R_188()
  {
    return jj_3R_39();
  }
  
  private final boolean jj_3R_53()
  {
    return jj_3R_97();
  }
  
  private final boolean jj_3R_31()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_53())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_54()) {
        return true;
      }
    }
    return false;
  }
  
  private final boolean jj_3R_122()
  {
    if (jj_scan_token(51)) {
      return true;
    }
    if (jj_scan_token(72)) {
      return true;
    }
    if (jj_3R_39()) {
      return true;
    }
    if (jj_scan_token(73)) {
      return true;
    }
    return jj_3R_38();
  }
  
  private final boolean jj_3R_177()
  {
    if (jj_scan_token(79)) {
      return true;
    }
    return jj_3R_176();
  }
  
  private final boolean jj_3R_210()
  {
    if (jj_scan_token(79)) {
      return true;
    }
    return jj_3R_112();
  }
  
  private final boolean jj_3R_121()
  {
    if (jj_scan_token(46)) {
      return true;
    }
    Token localToken = this.jj_scanpos;
    if (jj_3R_188()) {
      this.jj_scanpos = localToken;
    }
    return jj_scan_token(78);
  }
  
  private final boolean jj_3R_129()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_138())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_139())
      {
        this.jj_scanpos = localToken;
        if (jj_3R_140())
        {
          this.jj_scanpos = localToken;
          if (jj_3R_141())
          {
            this.jj_scanpos = localToken;
            if (jj_3R_142())
            {
              this.jj_scanpos = localToken;
              if (jj_3R_143())
              {
                this.jj_scanpos = localToken;
                if (jj_3R_144()) {
                  return true;
                }
              }
            }
          }
        }
      }
    }
    return false;
  }
  
  private final boolean jj_3R_138()
  {
    return jj_scan_token(60);
  }
  
  private final boolean jj_3R_146()
  {
    return jj_3R_69();
  }
  
  private final boolean jj_3R_176()
  {
    if (jj_scan_token(69)) {
      return true;
    }
    Token localToken = this.jj_scanpos;
    if (jj_3R_180()) {
      this.jj_scanpos = localToken;
    }
    return false;
  }
  
  private final boolean jj_3R_105()
  {
    return jj_3R_129();
  }
  
  private final boolean jj_3R_120()
  {
    if (jj_scan_token(19)) {
      return true;
    }
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(69)) {
      this.jj_scanpos = localToken;
    }
    return jj_scan_token(78);
  }
  
  private final boolean jj_3R_119()
  {
    if (jj_scan_token(12)) {
      return true;
    }
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(69)) {
      this.jj_scanpos = localToken;
    }
    return jj_scan_token(78);
  }
  
  private final boolean jj_3R_195()
  {
    return jj_3R_205();
  }
  
  private final boolean jj_3R_128()
  {
    if (jj_scan_token(34)) {
      return true;
    }
    if (jj_scan_token(104)) {
      return true;
    }
    return jj_scan_token(78);
  }
  
  private final boolean jj_3R_133()
  {
    if (jj_scan_token(74)) {
      return true;
    }
    if (jj_3R_39()) {
      return true;
    }
    return jj_scan_token(75);
  }
  
  private final boolean jj_3R_205()
  {
    if (jj_3R_112()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_210());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3R_132()
  {
    if (jj_scan_token(80)) {
      return true;
    }
    if (jj_scan_token(69)) {
      return true;
    }
    Token localToken = this.jj_scanpos;
    if (jj_3R_146()) {
      this.jj_scanpos = localToken;
    }
    return false;
  }
  
  private final boolean jj_3_3()
  {
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(48)) {
      this.jj_scanpos = localToken;
    }
    if (jj_scan_token(34)) {
      return true;
    }
    if (jj_3R_29()) {
      return true;
    }
    localToken = this.jj_scanpos;
    if (jj_3R_30()) {
      this.jj_scanpos = localToken;
    }
    return jj_scan_token(78);
  }
  
  private final boolean jj_3R_94()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3_3())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_128()) {
        return true;
      }
    }
    return false;
  }
  
  private final boolean jj_3R_93()
  {
    if (jj_3R_41()) {
      return true;
    }
    if (jj_3R_32()) {
      return true;
    }
    if (jj_3R_176()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_177());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3R_131()
  {
    if (jj_scan_token(76)) {
      return true;
    }
    if (jj_3R_39()) {
      return true;
    }
    return jj_scan_token(77);
  }
  
  private final boolean jj_3R_95()
  {
    if (jj_scan_token(42)) {
      return true;
    }
    return jj_3R_29();
  }
  
  private final boolean jj_3_2()
  {
    if (jj_scan_token(69)) {
      return true;
    }
    return jj_scan_token(72);
  }
  
  private final boolean jj_3R_175()
  {
    return jj_3R_38();
  }
  
  private final boolean jj_3_16()
  {
    if (jj_scan_token(80)) {
      return true;
    }
    return jj_scan_token(13);
  }
  
  private final boolean jj_3R_104()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3_16())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_131())
      {
        this.jj_scanpos = localToken;
        if (jj_3R_132())
        {
          this.jj_scanpos = localToken;
          if (jj_3R_133()) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  private final boolean jj_3R_174()
  {
    if (jj_scan_token(54)) {
      return true;
    }
    return jj_3R_76();
  }
  
  private final boolean jj_3_15()
  {
    if (jj_3R_32()) {
      return true;
    }
    if (jj_scan_token(80)) {
      return true;
    }
    return jj_scan_token(13);
  }
  
  private final boolean jj_3_31()
  {
    if (jj_3R_41()) {
      return true;
    }
    if (jj_3R_32()) {
      return true;
    }
    return jj_scan_token(69);
  }
  
  private final boolean jj_3_14()
  {
    return jj_3R_37();
  }
  
  private final boolean jj_3R_126()
  {
    return jj_scan_token(69);
  }
  
  private final boolean jj_3R_127()
  {
    if (jj_3R_42()) {
      return true;
    }
    return jj_scan_token(69);
  }
  
  private final boolean jj_3R_92()
  {
    if (jj_3R_41()) {
      return true;
    }
    Token localToken = this.jj_scanpos;
    if (jj_3R_126())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_127()) {
        return true;
      }
    }
    if (jj_3R_43()) {
      return true;
    }
    localToken = this.jj_scanpos;
    if (jj_3R_174()) {
      this.jj_scanpos = localToken;
    }
    localToken = this.jj_scanpos;
    if (jj_3R_175())
    {
      this.jj_scanpos = localToken;
      if (jj_scan_token(78)) {
        return true;
      }
    }
    return false;
  }
  
  private final boolean jj_3R_204()
  {
    return jj_3R_205();
  }
  
  private final boolean jj_3R_103()
  {
    return jj_3R_29();
  }
  
  private final boolean jj_3R_203()
  {
    return jj_3R_93();
  }
  
  private final boolean jj_3R_194()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_203())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_204()) {
        return true;
      }
    }
    return false;
  }
  
  private final boolean jj_3R_102()
  {
    return jj_3R_32();
  }
  
  private final boolean jj_3R_58()
  {
    return jj_3R_104();
  }
  
  private final boolean jj_3R_125()
  {
    return jj_scan_token(37);
  }
  
  private final boolean jj_3R_101()
  {
    return jj_3R_37();
  }
  
  private final boolean jj_3R_100()
  {
    return jj_3R_130();
  }
  
  private final boolean jj_3R_99()
  {
    if (jj_scan_token(72)) {
      return true;
    }
    if (jj_3R_39()) {
      return true;
    }
    return jj_scan_token(73);
  }
  
  private final boolean jj_3R_137()
  {
    if (jj_scan_token(30)) {
      return true;
    }
    if (jj_scan_token(72)) {
      return true;
    }
    if (jj_3R_32()) {
      return true;
    }
    if (jj_scan_token(69)) {
      return true;
    }
    if (jj_scan_token(89)) {
      return true;
    }
    if (jj_3R_39()) {
      return true;
    }
    if (jj_scan_token(73)) {
      return true;
    }
    return jj_3R_45();
  }
  
  private final boolean jj_3R_184()
  {
    if (jj_scan_token(23)) {
      return true;
    }
    return jj_3R_45();
  }
  
  private final boolean jj_3R_173()
  {
    if (jj_scan_token(33)) {
      return true;
    }
    return jj_3R_76();
  }
  
  private final boolean jj_3R_57()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_98())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_99())
      {
        this.jj_scanpos = localToken;
        if (jj_3R_100())
        {
          this.jj_scanpos = localToken;
          if (jj_3R_101())
          {
            this.jj_scanpos = localToken;
            if (jj_3R_102())
            {
              this.jj_scanpos = localToken;
              if (jj_3R_103()) {
                return true;
              }
            }
          }
        }
      }
    }
    return false;
  }
  
  private final boolean jj_3R_98()
  {
    return jj_3R_129();
  }
  
  private final boolean jj_3R_172()
  {
    if (jj_scan_token(25)) {
      return true;
    }
    return jj_3R_29();
  }
  
  private final boolean jj_3_30()
  {
    if (jj_scan_token(30)) {
      return true;
    }
    if (jj_scan_token(72)) {
      return true;
    }
    if (jj_scan_token(69)) {
      return true;
    }
    if (jj_scan_token(89)) {
      return true;
    }
    if (jj_3R_39()) {
      return true;
    }
    if (jj_scan_token(73)) {
      return true;
    }
    return jj_3R_45();
  }
  
  private final boolean jj_3R_118()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3_30())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_137()) {
        return true;
      }
    }
    return false;
  }
  
  private final boolean jj_3R_37()
  {
    if (jj_3R_29()) {
      return true;
    }
    return jj_3R_69();
  }
  
  private final boolean jj_3R_185()
  {
    return jj_3R_194();
  }
  
  private final boolean jj_3R_91()
  {
    if (jj_3R_41()) {
      return true;
    }
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(13))
    {
      this.jj_scanpos = localToken;
      if (jj_3R_125()) {
        return true;
      }
    }
    if (jj_scan_token(69)) {
      return true;
    }
    localToken = this.jj_scanpos;
    if (jj_3R_172()) {
      this.jj_scanpos = localToken;
    }
    localToken = this.jj_scanpos;
    if (jj_3R_173()) {
      this.jj_scanpos = localToken;
    }
    return jj_3R_38();
  }
  
  private final boolean jj_3_13()
  {
    if (jj_scan_token(72)) {
      return true;
    }
    return jj_3R_36();
  }
  
  private final boolean jj_3R_187()
  {
    return jj_3R_195();
  }
  
  private final boolean jj_3R_186()
  {
    return jj_3R_39();
  }
  
  private final boolean jj_3R_33()
  {
    if (jj_3R_57()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_58());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3R_217()
  {
    if (jj_scan_token(72)) {
      return true;
    }
    if (jj_3R_32()) {
      return true;
    }
    if (jj_scan_token(73)) {
      return true;
    }
    return jj_3R_208();
  }
  
  private final boolean jj_3R_216()
  {
    if (jj_scan_token(72)) {
      return true;
    }
    if (jj_3R_32()) {
      return true;
    }
    if (jj_scan_token(73)) {
      return true;
    }
    return jj_3R_191();
  }
  
  private final boolean jj_3R_117()
  {
    if (jj_scan_token(30)) {
      return true;
    }
    if (jj_scan_token(72)) {
      return true;
    }
    Token localToken = this.jj_scanpos;
    if (jj_3R_185()) {
      this.jj_scanpos = localToken;
    }
    if (jj_scan_token(78)) {
      return true;
    }
    localToken = this.jj_scanpos;
    if (jj_3R_186()) {
      this.jj_scanpos = localToken;
    }
    if (jj_scan_token(78)) {
      return true;
    }
    localToken = this.jj_scanpos;
    if (jj_3R_187()) {
      this.jj_scanpos = localToken;
    }
    if (jj_scan_token(73)) {
      return true;
    }
    return jj_3R_45();
  }
  
  private final boolean jj_3R_214()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_216())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_217()) {
        return true;
      }
    }
    return false;
  }
  
  private final boolean jj_3_12()
  {
    if (jj_3R_33()) {
      return true;
    }
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(100))
    {
      this.jj_scanpos = localToken;
      if (jj_scan_token(101)) {
        return true;
      }
    }
    return false;
  }
  
  private final boolean jj_3R_219()
  {
    return jj_3R_33();
  }
  
  private final boolean jj_3R_116()
  {
    if (jj_scan_token(21)) {
      return true;
    }
    if (jj_3R_45()) {
      return true;
    }
    if (jj_scan_token(59)) {
      return true;
    }
    if (jj_scan_token(72)) {
      return true;
    }
    if (jj_3R_39()) {
      return true;
    }
    if (jj_scan_token(73)) {
      return true;
    }
    return jj_scan_token(78);
  }
  
  private final boolean jj_3_11()
  {
    if (jj_scan_token(72)) {
      return true;
    }
    if (jj_3R_29()) {
      return true;
    }
    return jj_scan_token(76);
  }
  
  private final boolean jj_3R_218()
  {
    if (jj_3R_33()) {
      return true;
    }
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(100))
    {
      this.jj_scanpos = localToken;
      if (jj_scan_token(101)) {
        return true;
      }
    }
    return false;
  }
  
  private final boolean jj_3R_215()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_218())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_219()) {
        return true;
      }
    }
    return false;
  }
  
  private final boolean jj_3R_72()
  {
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(43))
    {
      this.jj_scanpos = localToken;
      if (jj_scan_token(44))
      {
        this.jj_scanpos = localToken;
        if (jj_scan_token(45))
        {
          this.jj_scanpos = localToken;
          if (jj_scan_token(51))
          {
            this.jj_scanpos = localToken;
            if (jj_scan_token(27))
            {
              this.jj_scanpos = localToken;
              if (jj_scan_token(39))
              {
                this.jj_scanpos = localToken;
                if (jj_scan_token(52))
                {
                  this.jj_scanpos = localToken;
                  if (jj_scan_token(58))
                  {
                    this.jj_scanpos = localToken;
                    if (jj_scan_token(10))
                    {
                      this.jj_scanpos = localToken;
                      if (jj_scan_token(48))
                      {
                        this.jj_scanpos = localToken;
                        if (jj_scan_token(49)) {
                          return true;
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return false;
  }
  
  private final boolean jj_3R_115()
  {
    if (jj_scan_token(59)) {
      return true;
    }
    if (jj_scan_token(72)) {
      return true;
    }
    if (jj_3R_39()) {
      return true;
    }
    if (jj_scan_token(73)) {
      return true;
    }
    return jj_3R_45();
  }
  
  private final boolean jj_3R_60()
  {
    if (jj_scan_token(72)) {
      return true;
    }
    if (jj_3R_29()) {
      return true;
    }
    if (jj_scan_token(73)) {
      return true;
    }
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(87))
    {
      this.jj_scanpos = localToken;
      if (jj_scan_token(86))
      {
        this.jj_scanpos = localToken;
        if (jj_scan_token(72))
        {
          this.jj_scanpos = localToken;
          if (jj_scan_token(69))
          {
            this.jj_scanpos = localToken;
            if (jj_scan_token(40))
            {
              this.jj_scanpos = localToken;
              if (jj_3R_105()) {
                return true;
              }
            }
          }
        }
      }
    }
    return false;
  }
  
  private final boolean jj_3R_59()
  {
    if (jj_scan_token(72)) {
      return true;
    }
    if (jj_3R_29()) {
      return true;
    }
    if (jj_scan_token(76)) {
      return true;
    }
    return jj_scan_token(77);
  }
  
  private final boolean jj_3_9()
  {
    return jj_3R_35();
  }
  
  private final boolean jj_3_29()
  {
    return jj_3R_28();
  }
  
  private final boolean jj_3R_114()
  {
    if (jj_scan_token(32)) {
      return true;
    }
    if (jj_scan_token(72)) {
      return true;
    }
    if (jj_3R_39()) {
      return true;
    }
    if (jj_scan_token(73)) {
      return true;
    }
    if (jj_3R_45()) {
      return true;
    }
    Token localToken = this.jj_scanpos;
    if (jj_3R_184()) {
      this.jj_scanpos = localToken;
    }
    return false;
  }
  
  private final boolean jj_3R_41()
  {
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_72());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3R_35()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3_10())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_59())
      {
        this.jj_scanpos = localToken;
        if (jj_3R_60()) {
          return true;
        }
      }
    }
    return false;
  }
  
  private final boolean jj_3_10()
  {
    if (jj_scan_token(72)) {
      return true;
    }
    return jj_3R_36();
  }
  
  private final boolean jj_3R_213()
  {
    return jj_3R_215();
  }
  
  private final boolean jj_3R_212()
  {
    return jj_3R_214();
  }
  
  private final boolean jj_3R_202()
  {
    if (jj_scan_token(20)) {
      return true;
    }
    return jj_scan_token(89);
  }
  
  private final boolean jj_3R_211()
  {
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(87))
    {
      this.jj_scanpos = localToken;
      if (jj_scan_token(86)) {
        return true;
      }
    }
    return jj_3R_191();
  }
  
  private final boolean jj_3R_208()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_211())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_212())
      {
        this.jj_scanpos = localToken;
        if (jj_3R_213()) {
          return true;
        }
      }
    }
    return false;
  }
  
  private final boolean jj_3R_201()
  {
    if (jj_scan_token(15)) {
      return true;
    }
    if (jj_3R_39()) {
      return true;
    }
    return jj_scan_token(89);
  }
  
  private final boolean jj_3R_193()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_201())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_202()) {
        return true;
      }
    }
    return false;
  }
  
  private final boolean jj_3R_183()
  {
    if (jj_3R_193()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3_29());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3R_207()
  {
    if (jj_scan_token(101)) {
      return true;
    }
    return jj_3R_33();
  }
  
  private final boolean jj_3_1()
  {
    return jj_3R_28();
  }
  
  private final boolean jj_3R_113()
  {
    if (jj_scan_token(50)) {
      return true;
    }
    if (jj_scan_token(72)) {
      return true;
    }
    if (jj_3R_39()) {
      return true;
    }
    if (jj_scan_token(73)) {
      return true;
    }
    if (jj_scan_token(74)) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_183());
    this.jj_scanpos = localToken;
    return jj_scan_token(75);
  }
  
  private final boolean jj_3R_209()
  {
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(104))
    {
      this.jj_scanpos = localToken;
      if (jj_scan_token(105))
      {
        this.jj_scanpos = localToken;
        if (jj_scan_token(111)) {
          return true;
        }
      }
    }
    return jj_3R_191();
  }
  
  private final boolean jj_3R_206()
  {
    if (jj_scan_token(100)) {
      return true;
    }
    return jj_3R_33();
  }
  
  private final boolean jj_3R_199()
  {
    return jj_3R_208();
  }
  
  private final boolean jj_3R_198()
  {
    return jj_3R_207();
  }
  
  private final boolean jj_3R_197()
  {
    return jj_3R_206();
  }
  
  private final boolean jj_3R_196()
  {
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(102))
    {
      this.jj_scanpos = localToken;
      if (jj_scan_token(103)) {
        return true;
      }
    }
    return jj_3R_191();
  }
  
  private final boolean jj_3R_191()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_196())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_197())
      {
        this.jj_scanpos = localToken;
        if (jj_3R_198())
        {
          this.jj_scanpos = localToken;
          if (jj_3R_199()) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  private final boolean jj_3R_44()
  {
    if (jj_scan_token(54)) {
      return true;
    }
    return jj_3R_76();
  }
  
  private final boolean jj_3R_112()
  {
    return jj_3R_39();
  }
  
  private final boolean jj_3R_181()
  {
    if (jj_3R_191()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_209());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3R_200()
  {
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(102))
    {
      this.jj_scanpos = localToken;
      if (jj_scan_token(103)) {
        return true;
      }
    }
    return jj_3R_181();
  }
  
  private final boolean jj_3R_178()
  {
    if (jj_3R_181()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_200());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3R_96()
  {
    return jj_scan_token(68);
  }
  
  private final boolean jj_3R_192()
  {
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(112))
    {
      this.jj_scanpos = localToken;
      if (jj_scan_token(113))
      {
        this.jj_scanpos = localToken;
        if (jj_scan_token(114))
        {
          this.jj_scanpos = localToken;
          if (jj_scan_token(115))
          {
            this.jj_scanpos = localToken;
            if (jj_scan_token(116))
            {
              this.jj_scanpos = localToken;
              if (jj_scan_token(117)) {
                return true;
              }
            }
          }
        }
      }
    }
    return jj_3R_178();
  }
  
  private final boolean jj_3R_171()
  {
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(90))
    {
      this.jj_scanpos = localToken;
      if (jj_scan_token(95)) {
        return true;
      }
    }
    return jj_3R_166();
  }
  
  private final boolean jj_3R_170()
  {
    if (jj_3R_178()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_192());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3R_52()
  {
    return jj_3R_96();
  }
  
  private final boolean jj_3R_182()
  {
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(84))
    {
      this.jj_scanpos = localToken;
      if (jj_scan_token(85))
      {
        this.jj_scanpos = localToken;
        if (jj_scan_token(82))
        {
          this.jj_scanpos = localToken;
          if (jj_scan_token(83))
          {
            this.jj_scanpos = localToken;
            if (jj_scan_token(91))
            {
              this.jj_scanpos = localToken;
              if (jj_scan_token(92))
              {
                this.jj_scanpos = localToken;
                if (jj_scan_token(93))
                {
                  this.jj_scanpos = localToken;
                  if (jj_scan_token(94)) {
                    return true;
                  }
                }
              }
            }
          }
        }
      }
    }
    return jj_3R_170();
  }
  
  private final boolean jj_3_27()
  {
    if (jj_3R_41()) {
      return true;
    }
    if (jj_3R_32()) {
      return true;
    }
    return jj_scan_token(69);
  }
  
  private final boolean jj_3R_51()
  {
    return jj_3R_95();
  }
  
  private final boolean jj_3R_168()
  {
    if (jj_3R_170()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_182());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3R_50()
  {
    return jj_3R_94();
  }
  
  private final boolean jj_3_26()
  {
    if (jj_3R_41()) {
      return true;
    }
    if (jj_scan_token(69)) {
      return true;
    }
    if (jj_3R_43()) {
      return true;
    }
    Token localToken = this.jj_scanpos;
    if (jj_3R_44()) {
      this.jj_scanpos = localToken;
    }
    return jj_scan_token(74);
  }
  
  private final boolean jj_3R_179()
  {
    if (jj_scan_token(35)) {
      return true;
    }
    return jj_3R_32();
  }
  
  private final boolean jj_3_28()
  {
    return jj_3R_45();
  }
  
  private final boolean jj_3R_166()
  {
    if (jj_3R_168()) {
      return true;
    }
    Token localToken = this.jj_scanpos;
    if (jj_3R_179()) {
      this.jj_scanpos = localToken;
    }
    return false;
  }
  
  private final boolean jj_3_25()
  {
    if (jj_3R_41()) {
      return true;
    }
    if (jj_3R_42()) {
      return true;
    }
    if (jj_scan_token(69)) {
      return true;
    }
    return jj_scan_token(72);
  }
  
  private final boolean jj_3R_49()
  {
    if (jj_3R_93()) {
      return true;
    }
    return jj_scan_token(78);
  }
  
  private final boolean jj_3_24()
  {
    if (jj_3R_41()) {
      return true;
    }
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(13))
    {
      this.jj_scanpos = localToken;
      if (jj_scan_token(37)) {
        return true;
      }
    }
    return false;
  }
  
  private final boolean jj_3R_167()
  {
    if (jj_scan_token(110)) {
      return true;
    }
    return jj_3R_161();
  }
  
  private final boolean jj_3R_48()
  {
    return jj_3R_92();
  }
  
  private final boolean jj_3R_164()
  {
    if (jj_3R_166()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_171());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private final boolean jj_3R_47()
  {
    return jj_3R_92();
  }
  
  private final boolean jj_3R_169()
  {
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(106))
    {
      this.jj_scanpos = localToken;
      if (jj_scan_token(107)) {
        return true;
      }
    }
    return jj_3R_164();
  }
  
  public Parser(InputStream paramInputStream)
  {
    this.jj_input_stream = new JavaCharStream(paramInputStream, 1, 1);
    this.token_source = new ParserTokenManager(this.jj_input_stream);
    this.token = new Token();
    this.jj_ntk = -1;
  }
  
  public void ReInit(InputStream paramInputStream)
  {
    this.jj_input_stream.ReInit(paramInputStream, 1, 1);
    this.token_source.ReInit(this.jj_input_stream);
    this.token = new Token();
    this.jj_ntk = -1;
    this.jjtree.reset();
  }
  
  public Parser(Reader paramReader)
  {
    this.jj_input_stream = new JavaCharStream(paramReader, 1, 1);
    this.token_source = new ParserTokenManager(this.jj_input_stream);
    this.token = new Token();
    this.jj_ntk = -1;
  }
  
  public void ReInit(Reader paramReader)
  {
    this.jj_input_stream.ReInit(paramReader, 1, 1);
    this.token_source.ReInit(this.jj_input_stream);
    this.token = new Token();
    this.jj_ntk = -1;
    this.jjtree.reset();
  }
  
  public Parser(ParserTokenManager paramParserTokenManager)
  {
    this.token_source = paramParserTokenManager;
    this.token = new Token();
    this.jj_ntk = -1;
  }
  
  public void ReInit(ParserTokenManager paramParserTokenManager)
  {
    this.token_source = paramParserTokenManager;
    this.token = new Token();
    this.jj_ntk = -1;
    this.jjtree.reset();
  }
  
  private final Token jj_consume_token(int paramInt)
    throws ParseException
  {
    Token localToken;
    if ((localToken = this.token).next != null) {
      this.token = this.token.next;
    } else {
      this.token = (this.token.next = this.token_source.getNextToken());
    }
    this.jj_ntk = -1;
    if (this.token.kind == paramInt) {
      return this.token;
    }
    this.token = localToken;
    throw generateParseException();
  }
  
  private final boolean jj_scan_token(int paramInt)
  {
    if (this.jj_scanpos == this.jj_lastpos)
    {
      this.jj_la -= 1;
      if (this.jj_scanpos.next == null) {
        this.jj_lastpos = (this.jj_scanpos = this.jj_scanpos.next = this.token_source.getNextToken());
      } else {
        this.jj_lastpos = (this.jj_scanpos = this.jj_scanpos.next);
      }
    }
    else
    {
      this.jj_scanpos = this.jj_scanpos.next;
    }
    if (this.jj_scanpos.kind != paramInt) {
      return true;
    }
    if ((this.jj_la == 0) && (this.jj_scanpos == this.jj_lastpos)) {
      throw this.jj_ls;
    }
    return false;
  }
  
  public final Token getNextToken()
  {
    if (this.token.next != null) {
      this.token = this.token.next;
    } else {
      this.token = (this.token.next = this.token_source.getNextToken());
    }
    this.jj_ntk = -1;
    return this.token;
  }
  
  public final Token getToken(int paramInt)
  {
    Token localToken = this.lookingAhead ? this.jj_scanpos : this.token;
    for (int i = 0; i < paramInt; i++) {
      if (localToken.next != null) {
        localToken = localToken.next;
      } else {
        localToken = localToken.next = this.token_source.getNextToken();
      }
    }
    return localToken;
  }
  
  private final int jj_ntk()
  {
    if ((this.jj_nt = this.token.next) == null) {
      return this.jj_ntk = (this.token.next = this.token_source.getNextToken()).kind;
    }
    return this.jj_ntk = this.jj_nt.kind;
  }
  
  public ParseException generateParseException()
  {
    Token localToken = this.token.next;
    int i = localToken.beginLine;
    int j = localToken.beginColumn;
    String str = localToken.kind == 0 ? ParserConstants.tokenImage[0] : localToken.image;
    return new ParseException("Parse error at line " + i + ", column " + j + ".  Encountered: " + str);
  }
  
  public final void enable_tracing() {}
  
  public final void disable_tracing() {}
  
  private static final class LookaheadSuccess
    extends Error
  {
    private LookaheadSuccess() {}
    
    LookaheadSuccess(Parser.1 param1)
    {
      this();
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/Parser.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */