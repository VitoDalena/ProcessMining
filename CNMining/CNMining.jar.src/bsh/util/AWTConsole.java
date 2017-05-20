package bsh.util;

import bsh.ConsoleInterface;
import bsh.Interpreter;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.peer.TextComponentPeer;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.util.Vector;

public class AWTConsole
  extends TextArea
  implements ConsoleInterface, Runnable, KeyListener
{
  private OutputStream outPipe;
  private InputStream inPipe;
  private InputStream in;
  private PrintStream out;
  private StringBuffer line = new StringBuffer();
  private String startedLine;
  private int textLength = 0;
  private Vector history = new Vector();
  private int histLine = 0;
  
  public Reader getIn()
  {
    return new InputStreamReader(this.in);
  }
  
  public PrintStream getOut()
  {
    return this.out;
  }
  
  public PrintStream getErr()
  {
    return this.out;
  }
  
  public AWTConsole(int paramInt1, int paramInt2, InputStream paramInputStream, OutputStream paramOutputStream)
  {
    super(paramInt1, paramInt2);
    setFont(new Font("Monospaced", 0, 14));
    setEditable(false);
    addKeyListener(this);
    this.outPipe = paramOutputStream;
    if (this.outPipe == null)
    {
      this.outPipe = new PipedOutputStream();
      try
      {
        this.in = new PipedInputStream((PipedOutputStream)this.outPipe);
      }
      catch (IOException localIOException)
      {
        print("Console internal error...");
      }
    }
    this.inPipe = paramInputStream;
    new Thread(this).start();
    requestFocus();
  }
  
  public void keyPressed(KeyEvent paramKeyEvent)
  {
    type(paramKeyEvent.getKeyCode(), paramKeyEvent.getKeyChar(), paramKeyEvent.getModifiers());
    paramKeyEvent.consume();
  }
  
  public AWTConsole()
  {
    this(12, 80, null, null);
  }
  
  public AWTConsole(InputStream paramInputStream, OutputStream paramOutputStream)
  {
    this(12, 80, paramInputStream, paramOutputStream);
  }
  
  public void type(int paramInt1, char paramChar, int paramInt2)
  {
    switch (paramInt1)
    {
    case 8: 
      if (this.line.length() > 0)
      {
        this.line.setLength(this.line.length() - 1);
        replaceRange("", this.textLength - 1, this.textLength);
        this.textLength -= 1;
      }
      break;
    case 10: 
      enter();
      break;
    case 85: 
      if ((paramInt2 & 0x2) > 0)
      {
        int i = this.line.length();
        replaceRange("", this.textLength - i, this.textLength);
        this.line.setLength(0);
        this.histLine = 0;
        this.textLength = getText().length();
      }
      else
      {
        doChar(paramChar);
      }
      break;
    case 38: 
      historyUp();
      break;
    case 40: 
      historyDown();
      break;
    case 9: 
      this.line.append("    ");
      append("    ");
      this.textLength += 4;
      break;
    case 67: 
      if ((paramInt2 & 0x2) > 0)
      {
        this.line.append("^C");
        append("^C");
        this.textLength += 2;
      }
      else
      {
        doChar(paramChar);
      }
      break;
    default: 
      doChar(paramChar);
    }
  }
  
  private void doChar(char paramChar)
  {
    if ((paramChar >= ' ') && (paramChar <= '~'))
    {
      this.line.append(paramChar);
      append(String.valueOf(paramChar));
      this.textLength += 1;
    }
  }
  
  private void enter()
  {
    String str;
    if (this.line.length() == 0)
    {
      str = ";\n";
    }
    else
    {
      str = this.line + "\n";
      this.history.addElement(this.line.toString());
    }
    this.line.setLength(0);
    this.histLine = 0;
    append("\n");
    this.textLength = getText().length();
    acceptLine(str);
    setCaretPosition(this.textLength);
  }
  
  public void setCaretPosition(int paramInt)
  {
    ((TextComponentPeer)getPeer()).setCaretPosition(paramInt + countNLs());
  }
  
  private int countNLs()
  {
    String str = getText();
    int i = 0;
    for (int j = 0; j < str.length(); j++) {
      if (str.charAt(j) == '\n') {
        i++;
      }
    }
    return i;
  }
  
  private void historyUp()
  {
    if (this.history.size() == 0) {
      return;
    }
    if (this.histLine == 0) {
      this.startedLine = this.line.toString();
    }
    if (this.histLine < this.history.size())
    {
      this.histLine += 1;
      showHistoryLine();
    }
  }
  
  private void historyDown()
  {
    if (this.histLine == 0) {
      return;
    }
    this.histLine -= 1;
    showHistoryLine();
  }
  
  private void showHistoryLine()
  {
    String str;
    if (this.histLine == 0) {
      str = this.startedLine;
    } else {
      str = (String)this.history.elementAt(this.history.size() - this.histLine);
    }
    replaceRange(str, this.textLength - this.line.length(), this.textLength);
    this.line = new StringBuffer(str);
    this.textLength = getText().length();
  }
  
  private void acceptLine(String paramString)
  {
    if (this.outPipe == null) {
      print("Console internal error...");
    } else {
      try
      {
        this.outPipe.write(paramString.getBytes());
        this.outPipe.flush();
      }
      catch (IOException localIOException)
      {
        this.outPipe = null;
        throw new RuntimeException("Console pipe broken...");
      }
    }
  }
  
  public void println(Object paramObject)
  {
    print(String.valueOf(paramObject) + "\n");
  }
  
  public void error(Object paramObject)
  {
    print(paramObject, Color.red);
  }
  
  public void print(Object paramObject, Color paramColor)
  {
    print("*** " + String.valueOf(paramObject));
  }
  
  public synchronized void print(Object paramObject)
  {
    append(String.valueOf(paramObject));
    this.textLength = getText().length();
  }
  
  private void inPipeWatcher()
    throws IOException
  {
    if (this.inPipe == null)
    {
      localObject = new PipedOutputStream();
      this.out = new PrintStream((OutputStream)localObject);
      this.inPipe = new PipedInputStream((PipedOutputStream)localObject);
    }
    Object localObject = new byte['Ā'];
    int i;
    while ((i = this.inPipe.read((byte[])localObject)) != -1) {
      print(new String((byte[])localObject, 0, i));
    }
    println("Console: Input closed...");
  }
  
  public void run()
  {
    try
    {
      inPipeWatcher();
    }
    catch (IOException localIOException)
    {
      println("Console: I/O Error...");
    }
  }
  
  public static void main(String[] paramArrayOfString)
  {
    AWTConsole localAWTConsole = new AWTConsole();
    Frame localFrame = new Frame("Bsh Console");
    localFrame.add(localAWTConsole, "Center");
    localFrame.pack();
    localFrame.show();
    localFrame.addWindowListener(new WindowAdapter()
    {
      private final Frame val$f;
      
      public void windowClosing(WindowEvent paramAnonymousWindowEvent)
      {
        this.val$f.dispose();
      }
    });
    Interpreter localInterpreter = new Interpreter(localAWTConsole);
    localInterpreter.run();
  }
  
  public String toString()
  {
    return "BeanShell AWTConsole";
  }
  
  public void keyTyped(KeyEvent paramKeyEvent) {}
  
  public void keyReleased(KeyEvent paramKeyEvent) {}
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/util/AWTConsole.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */