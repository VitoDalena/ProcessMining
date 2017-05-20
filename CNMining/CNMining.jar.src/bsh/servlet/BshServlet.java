package bsh.servlet;

import bsh.EvalError;
import bsh.Interpreter;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BshServlet
  extends HttpServlet
{
  static String bshVersion;
  static String exampleScript = "print(\"hello!\");";
  
  static String getBshVersion()
  {
    if (bshVersion != null) {
      return bshVersion;
    }
    Interpreter localInterpreter = new Interpreter();
    try
    {
      localInterpreter.eval(new InputStreamReader(BshServlet.class.getResource("getVersion.bsh").openStream()));
      bshVersion = (String)localInterpreter.eval("getVersion()");
    }
    catch (Exception localException)
    {
      bshVersion = "BeanShell: unknown version";
    }
    return bshVersion;
  }
  
  public void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws ServletException, IOException
  {
    String str1 = paramHttpServletRequest.getParameter("bsh.script");
    String str2 = paramHttpServletRequest.getParameter("bsh.client");
    String str3 = paramHttpServletRequest.getParameter("bsh.servlet.output");
    String str4 = paramHttpServletRequest.getParameter("bsh.servlet.captureOutErr");
    boolean bool = false;
    if ((str4 != null) && (str4.equalsIgnoreCase("true"))) {
      bool = true;
    }
    Object localObject1 = null;
    Object localObject2 = null;
    StringBuffer localStringBuffer = new StringBuffer();
    if (str1 != null) {
      try
      {
        localObject1 = evalScript(str1, localStringBuffer, bool, paramHttpServletRequest, paramHttpServletResponse);
      }
      catch (Exception localException)
      {
        localObject2 = localException;
      }
    }
    paramHttpServletResponse.setHeader("Bsh-Return", String.valueOf(localObject1));
    if (((str3 != null) && (str3.equalsIgnoreCase("raw"))) || ((str2 != null) && (str2.equals("Remote")))) {
      sendRaw(paramHttpServletRequest, paramHttpServletResponse, (Exception)localObject2, localObject1, localStringBuffer);
    } else {
      sendHTML(paramHttpServletRequest, paramHttpServletResponse, str1, (Exception)localObject2, localObject1, localStringBuffer, bool);
    }
  }
  
  void sendHTML(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString, Exception paramException, Object paramObject, StringBuffer paramStringBuffer, boolean paramBoolean)
    throws IOException
  {
    SimpleTemplate localSimpleTemplate = new SimpleTemplate(BshServlet.class.getResource("page.template"));
    localSimpleTemplate.replace("version", getBshVersion());
    String str = paramHttpServletRequest.getRequestURI();
    localSimpleTemplate.replace("servletURL", str);
    if (paramString != null) {
      localSimpleTemplate.replace("script", paramString);
    } else {
      localSimpleTemplate.replace("script", exampleScript);
    }
    if (paramBoolean) {
      localSimpleTemplate.replace("captureOutErr", "CHECKED");
    } else {
      localSimpleTemplate.replace("captureOutErr", "");
    }
    if (paramString != null) {
      localSimpleTemplate.replace("scriptResult", formatScriptResultHTML(paramString, paramObject, paramException, paramStringBuffer));
    }
    paramHttpServletResponse.setContentType("text/html");
    PrintWriter localPrintWriter = paramHttpServletResponse.getWriter();
    localSimpleTemplate.write(localPrintWriter);
    localPrintWriter.flush();
  }
  
  void sendRaw(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Exception paramException, Object paramObject, StringBuffer paramStringBuffer)
    throws IOException
  {
    paramHttpServletResponse.setContentType("text/plain");
    PrintWriter localPrintWriter = paramHttpServletResponse.getWriter();
    if (paramException != null) {
      localPrintWriter.println("Script Error:\n" + paramException);
    } else {
      localPrintWriter.println(paramStringBuffer.toString());
    }
    localPrintWriter.flush();
  }
  
  String formatScriptResultHTML(String paramString, Object paramObject, Exception paramException, StringBuffer paramStringBuffer)
    throws IOException
  {
    SimpleTemplate localSimpleTemplate;
    if (paramException != null)
    {
      localSimpleTemplate = new SimpleTemplate(getClass().getResource("error.template"));
      String str1;
      if ((paramException instanceof EvalError))
      {
        int i = ((EvalError)paramException).getErrorLineNumber();
        String str2 = paramException.getMessage();
        int j = 4;
        str1 = escape(str2);
        if (i > -1) {
          str1 = str1 + "<hr>" + showScriptContextHTML(paramString, i, j);
        }
      }
      else
      {
        str1 = escape(paramException.toString());
      }
      localSimpleTemplate.replace("error", str1);
    }
    else
    {
      localSimpleTemplate = new SimpleTemplate(getClass().getResource("result.template"));
      localSimpleTemplate.replace("value", escape(String.valueOf(paramObject)));
      localSimpleTemplate.replace("output", escape(paramStringBuffer.toString()));
    }
    return localSimpleTemplate.toString();
  }
  
  String showScriptContextHTML(String paramString, int paramInt1, int paramInt2)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    BufferedReader localBufferedReader = new BufferedReader(new StringReader(paramString));
    int i = Math.max(1, paramInt1 - paramInt2);
    int j = paramInt1 + paramInt2;
    for (int k = 1; k <= paramInt1 + paramInt2 + 1; k++) {
      if (k < i)
      {
        try
        {
          localBufferedReader.readLine();
        }
        catch (IOException localIOException1)
        {
          throw new RuntimeException(localIOException1.toString());
        }
      }
      else
      {
        if (k > j) {
          break;
        }
        String str;
        try
        {
          str = localBufferedReader.readLine();
        }
        catch (IOException localIOException2)
        {
          throw new RuntimeException(localIOException2.toString());
        }
        if (str == null) {
          break;
        }
        if (k == paramInt1) {
          localStringBuffer.append("<font color=\"red\">" + k + ": " + str + "</font><br/>");
        } else {
          localStringBuffer.append(k + ": " + str + "<br/>");
        }
      }
    }
    return localStringBuffer.toString();
  }
  
  public void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws ServletException, IOException
  {
    doGet(paramHttpServletRequest, paramHttpServletResponse);
  }
  
  Object evalScript(String paramString, StringBuffer paramStringBuffer, boolean paramBoolean, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws EvalError
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    PrintStream localPrintStream1 = new PrintStream(localByteArrayOutputStream);
    Interpreter localInterpreter = new Interpreter(null, localPrintStream1, localPrintStream1, false);
    localInterpreter.set("bsh.httpServletRequest", paramHttpServletRequest);
    localInterpreter.set("bsh.httpServletResponse", paramHttpServletResponse);
    Object localObject1 = null;
    Object localObject2 = null;
    PrintStream localPrintStream2 = System.out;
    PrintStream localPrintStream3 = System.err;
    if (paramBoolean)
    {
      System.setOut(localPrintStream1);
      System.setErr(localPrintStream1);
    }
    try
    {
      localObject1 = localInterpreter.eval(paramString);
    }
    finally
    {
      if (paramBoolean)
      {
        System.setOut(localPrintStream2);
        System.setErr(localPrintStream3);
      }
    }
    localPrintStream1.flush();
    paramStringBuffer.append(localByteArrayOutputStream.toString());
    return localObject1;
  }
  
  public static String escape(String paramString)
  {
    String str = "&<>";
    String[] arrayOfString = { "&amp;", "&lt;", "&gt;" };
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < paramString.length(); i++)
    {
      char c = paramString.charAt(i);
      int j = str.indexOf(c);
      if (j < 0) {
        localStringBuffer.append(c);
      } else {
        localStringBuffer.append(arrayOfString[j]);
      }
    }
    return localStringBuffer.toString();
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/servlet/BshServlet.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */