package bsh.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class JConsole
  extends JScrollPane
  implements GUIConsoleInterface, Runnable, KeyListener, MouseListener, ActionListener, PropertyChangeListener
{
  private static final String CUT = "Cut";
  private static final String COPY = "Copy";
  private static final String PASTE = "Paste";
  private OutputStream outPipe;
  private InputStream inPipe;
  private InputStream in;
  private PrintStream out;
  private int cmdStart = 0;
  private Vector history = new Vector();
  private String startedLine;
  private int histLine = 0;
  private JPopupMenu menu;
  private JTextPane text = new JTextPane(this.doc = new DefaultStyledDocument())
  {
    public void cut()
    {
      if (JConsole.this.text.getCaretPosition() < JConsole.this.cmdStart) {
        super.copy();
      } else {
        super.cut();
      }
    }
    
    public void paste()
    {
      JConsole.this.forceCaretMoveToEnd();
      super.paste();
    }
  };
  private DefaultStyledDocument doc;
  NameCompletion nameCompletion;
  final int SHOW_AMBIG_MAX = 10;
  private boolean gotUp = true;
  String ZEROS = "000";
  
  public InputStream getInputStream()
  {
    return this.in;
  }
  
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
  
  public JConsole()
  {
    this(null, null);
  }
  
  public JConsole(InputStream paramInputStream, OutputStream paramOutputStream)
  {
    Font localFont = new Font("Monospaced", 0, 14);
    this.text.setText("");
    this.text.setFont(localFont);
    this.text.setMargin(new Insets(7, 5, 7, 5));
    this.text.addKeyListener(this);
    setViewportView(this.text);
    this.menu = new JPopupMenu("JConsole\tMenu");
    this.menu.add(new JMenuItem("Cut")).addActionListener(this);
    this.menu.add(new JMenuItem("Copy")).addActionListener(this);
    this.menu.add(new JMenuItem("Paste")).addActionListener(this);
    this.text.addMouseListener(this);
    UIManager.addPropertyChangeListener(this);
    this.outPipe = paramOutputStream;
    if (this.outPipe == null)
    {
      this.outPipe = new PipedOutputStream();
      try
      {
        this.in = new PipedInputStream((PipedOutputStream)this.outPipe);
      }
      catch (IOException localIOException1)
      {
        print("Console internal\terror (1)...", Color.red);
      }
    }
    this.inPipe = paramInputStream;
    if (this.inPipe == null)
    {
      PipedOutputStream localPipedOutputStream = new PipedOutputStream();
      this.out = new PrintStream(localPipedOutputStream);
      try
      {
        this.inPipe = new BlockingPipedInputStream(localPipedOutputStream);
      }
      catch (IOException localIOException2)
      {
        print("Console internal error: " + localIOException2);
      }
    }
    new Thread(this).start();
    requestFocus();
  }
  
  public void requestFocus()
  {
    super.requestFocus();
    this.text.requestFocus();
  }
  
  public void keyPressed(KeyEvent paramKeyEvent)
  {
    type(paramKeyEvent);
    this.gotUp = false;
  }
  
  public void keyTyped(KeyEvent paramKeyEvent)
  {
    type(paramKeyEvent);
  }
  
  public void keyReleased(KeyEvent paramKeyEvent)
  {
    this.gotUp = true;
    type(paramKeyEvent);
  }
  
  private synchronized void type(KeyEvent paramKeyEvent)
  {
    switch (paramKeyEvent.getKeyCode())
    {
    case 10: 
      if ((paramKeyEvent.getID() == 401) && (this.gotUp))
      {
        enter();
        resetCommandStart();
        this.text.setCaretPosition(this.cmdStart);
      }
      paramKeyEvent.consume();
      this.text.repaint();
      break;
    case 38: 
      if (paramKeyEvent.getID() == 401) {
        historyUp();
      }
      paramKeyEvent.consume();
      break;
    case 40: 
      if (paramKeyEvent.getID() == 401) {
        historyDown();
      }
      paramKeyEvent.consume();
      break;
    case 8: 
    case 37: 
    case 127: 
      if (this.text.getCaretPosition() <= this.cmdStart) {
        paramKeyEvent.consume();
      }
      break;
    case 39: 
      forceCaretMoveToStart();
      break;
    case 36: 
      this.text.setCaretPosition(this.cmdStart);
      paramKeyEvent.consume();
      break;
    case 85: 
      if ((paramKeyEvent.getModifiers() & 0x2) > 0)
      {
        replaceRange("", this.cmdStart, textLength());
        this.histLine = 0;
        paramKeyEvent.consume();
      }
      break;
    case 16: 
    case 17: 
    case 18: 
    case 19: 
    case 20: 
    case 27: 
    case 112: 
    case 113: 
    case 114: 
    case 115: 
    case 116: 
    case 117: 
    case 118: 
    case 119: 
    case 120: 
    case 121: 
    case 122: 
    case 123: 
    case 145: 
    case 154: 
    case 155: 
    case 157: 
      break;
    case 67: 
      if (this.text.getSelectedText() == null)
      {
        if (((paramKeyEvent.getModifiers() & 0x2) > 0) && (paramKeyEvent.getID() == 401)) {
          append("^C");
        }
        paramKeyEvent.consume();
      }
      break;
    case 9: 
      if (paramKeyEvent.getID() == 402)
      {
        String str = this.text.getText().substring(this.cmdStart);
        doCommandCompletion(str);
      }
      paramKeyEvent.consume();
      break;
    case 11: 
    case 12: 
    case 13: 
    case 14: 
    case 15: 
    case 21: 
    case 22: 
    case 23: 
    case 24: 
    case 25: 
    case 26: 
    case 28: 
    case 29: 
    case 30: 
    case 31: 
    case 32: 
    case 33: 
    case 34: 
    case 35: 
    case 41: 
    case 42: 
    case 43: 
    case 44: 
    case 45: 
    case 46: 
    case 47: 
    case 48: 
    case 49: 
    case 50: 
    case 51: 
    case 52: 
    case 53: 
    case 54: 
    case 55: 
    case 56: 
    case 57: 
    case 58: 
    case 59: 
    case 60: 
    case 61: 
    case 62: 
    case 63: 
    case 64: 
    case 65: 
    case 66: 
    case 68: 
    case 69: 
    case 70: 
    case 71: 
    case 72: 
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
    case 124: 
    case 125: 
    case 126: 
    case 128: 
    case 129: 
    case 130: 
    case 131: 
    case 132: 
    case 133: 
    case 134: 
    case 135: 
    case 136: 
    case 137: 
    case 138: 
    case 139: 
    case 140: 
    case 141: 
    case 142: 
    case 143: 
    case 144: 
    case 146: 
    case 147: 
    case 148: 
    case 149: 
    case 150: 
    case 151: 
    case 152: 
    case 153: 
    case 156: 
    default: 
      if ((paramKeyEvent.getModifiers() & 0xE) == 0) {
        forceCaretMoveToEnd();
      }
      if ((paramKeyEvent.paramString().indexOf("Backspace") != -1) && (this.text.getCaretPosition() <= this.cmdStart)) {
        paramKeyEvent.consume();
      }
      break;
    }
  }
  
  private void doCommandCompletion(String paramString)
  {
    if (this.nameCompletion == null) {
      return;
    }
    for (int i = paramString.length() - 1; (i >= 0) && ((Character.isJavaIdentifierPart(paramString.charAt(i))) || (paramString.charAt(i) == '.')); i--) {}
    paramString = paramString.substring(i + 1);
    if (paramString.length() < 2) {
      return;
    }
    String[] arrayOfString = this.nameCompletion.completeName(paramString);
    if (arrayOfString.length == 0)
    {
      Toolkit.getDefaultToolkit().beep();
      return;
    }
    if ((arrayOfString.length == 1) && (!arrayOfString.equals(paramString)))
    {
      str1 = arrayOfString[0].substring(paramString.length());
      append(str1);
      return;
    }
    String str1 = this.text.getText();
    String str2 = str1.substring(this.cmdStart);
    for (i = this.cmdStart; (str1.charAt(i) != '\n') && (i > 0); i--) {}
    String str3 = str1.substring(i + 1, this.cmdStart);
    StringBuffer localStringBuffer = new StringBuffer("\n");
    for (i = 0; (i < arrayOfString.length) && (i < 10); i++) {
      localStringBuffer.append(arrayOfString[i] + "\n");
    }
    if (i == 10) {
      localStringBuffer.append("...\n");
    }
    print(localStringBuffer, Color.gray);
    print(str3);
    append(str2);
  }
  
  private void resetCommandStart()
  {
    this.cmdStart = textLength();
  }
  
  private void append(String paramString)
  {
    int i = textLength();
    this.text.select(i, i);
    this.text.replaceSelection(paramString);
  }
  
  private String replaceRange(Object paramObject, int paramInt1, int paramInt2)
  {
    String str = paramObject.toString();
    this.text.select(paramInt1, paramInt2);
    this.text.replaceSelection(str);
    return str;
  }
  
  private void forceCaretMoveToEnd()
  {
    if (this.text.getCaretPosition() < this.cmdStart) {
      this.text.setCaretPosition(textLength());
    }
    this.text.repaint();
  }
  
  private void forceCaretMoveToStart()
  {
    if (this.text.getCaretPosition() < this.cmdStart) {}
    this.text.repaint();
  }
  
  private void enter()
  {
    String str = getCmd();
    if (str.length() == 0)
    {
      str = ";\n";
    }
    else
    {
      this.history.addElement(str);
      str = str + "\n";
    }
    append("\n");
    this.histLine = 0;
    acceptLine(str);
    this.text.repaint();
  }
  
  private String getCmd()
  {
    String str = "";
    try
    {
      str = this.text.getText(this.cmdStart, textLength() - this.cmdStart);
    }
    catch (BadLocationException localBadLocationException)
    {
      System.out.println("Internal JConsole Error: " + localBadLocationException);
    }
    return str;
  }
  
  private void historyUp()
  {
    if (this.history.size() == 0) {
      return;
    }
    if (this.histLine == 0) {
      this.startedLine = getCmd();
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
    replaceRange(str, this.cmdStart, textLength());
    this.text.setCaretPosition(textLength());
    this.text.repaint();
  }
  
  private void acceptLine(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = paramString.length();
    for (int j = 0; j < i; j++)
    {
      String str = Integer.toString(paramString.charAt(j), 16);
      str = this.ZEROS.substring(0, 4 - str.length()) + str;
      localStringBuffer.append("\\u" + str);
    }
    paramString = localStringBuffer.toString();
    if (this.outPipe == null) {
      print("Console internal\terror: cannot output ...", Color.red);
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
    this.text.repaint();
  }
  
  public void print(Object paramObject)
  {
    invokeAndWait(new Runnable()
    {
      private final Object val$o;
      
      public void run()
      {
        JConsole.this.append(String.valueOf(this.val$o));
        JConsole.this.resetCommandStart();
        JConsole.this.text.setCaretPosition(JConsole.this.cmdStart);
      }
    });
  }
  
  public void println()
  {
    print("\n");
    this.text.repaint();
  }
  
  public void error(Object paramObject)
  {
    print(paramObject, Color.red);
  }
  
  public void println(Icon paramIcon)
  {
    print(paramIcon);
    println();
    this.text.repaint();
  }
  
  public void print(Icon paramIcon)
  {
    if (paramIcon == null) {
      return;
    }
    invokeAndWait(new Runnable()
    {
      private final Icon val$icon;
      
      public void run()
      {
        JConsole.this.text.insertIcon(this.val$icon);
        JConsole.this.resetCommandStart();
        JConsole.this.text.setCaretPosition(JConsole.this.cmdStart);
      }
    });
  }
  
  public void print(Object paramObject, Font paramFont)
  {
    print(paramObject, paramFont, null);
  }
  
  public void print(Object paramObject, Color paramColor)
  {
    print(paramObject, null, paramColor);
  }
  
  public void print(Object paramObject, Font paramFont, Color paramColor)
  {
    invokeAndWait(new Runnable()
    {
      private final Font val$font;
      private final Color val$color;
      private final Object val$o;
      
      public void run()
      {
        AttributeSet localAttributeSet = JConsole.this.getStyle();
        JConsole.this.setStyle(this.val$font, this.val$color);
        JConsole.this.append(String.valueOf(this.val$o));
        JConsole.this.resetCommandStart();
        JConsole.this.text.setCaretPosition(JConsole.this.cmdStart);
        JConsole.this.setStyle(localAttributeSet, true);
      }
    });
  }
  
  public void print(Object paramObject, String paramString, int paramInt, Color paramColor)
  {
    print(paramObject, paramString, paramInt, paramColor, false, false, false);
  }
  
  public void print(Object paramObject, String paramString, int paramInt, Color paramColor, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    invokeAndWait(new Runnable()
    {
      private final String val$fontFamilyName;
      private final int val$size;
      private final Color val$color;
      private final boolean val$bold;
      private final boolean val$italic;
      private final boolean val$underline;
      private final Object val$o;
      
      public void run()
      {
        AttributeSet localAttributeSet = JConsole.this.getStyle();
        JConsole.this.setStyle(this.val$fontFamilyName, this.val$size, this.val$color, this.val$bold, this.val$italic, this.val$underline);
        JConsole.this.append(String.valueOf(this.val$o));
        JConsole.this.resetCommandStart();
        JConsole.this.text.setCaretPosition(JConsole.this.cmdStart);
        JConsole.this.setStyle(localAttributeSet, true);
      }
    });
  }
  
  private AttributeSet setStyle(Font paramFont)
  {
    return setStyle(paramFont, null);
  }
  
  private AttributeSet setStyle(Color paramColor)
  {
    return setStyle(null, paramColor);
  }
  
  private AttributeSet setStyle(Font paramFont, Color paramColor)
  {
    if (paramFont != null) {
      return setStyle(paramFont.getFamily(), paramFont.getSize(), paramColor, paramFont.isBold(), paramFont.isItalic(), StyleConstants.isUnderline(getStyle()));
    }
    return setStyle(null, -1, paramColor);
  }
  
  private AttributeSet setStyle(String paramString, int paramInt, Color paramColor)
  {
    SimpleAttributeSet localSimpleAttributeSet = new SimpleAttributeSet();
    if (paramColor != null) {
      StyleConstants.setForeground(localSimpleAttributeSet, paramColor);
    }
    if (paramString != null) {
      StyleConstants.setFontFamily(localSimpleAttributeSet, paramString);
    }
    if (paramInt != -1) {
      StyleConstants.setFontSize(localSimpleAttributeSet, paramInt);
    }
    setStyle(localSimpleAttributeSet);
    return getStyle();
  }
  
  private AttributeSet setStyle(String paramString, int paramInt, Color paramColor, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    SimpleAttributeSet localSimpleAttributeSet = new SimpleAttributeSet();
    if (paramColor != null) {
      StyleConstants.setForeground(localSimpleAttributeSet, paramColor);
    }
    if (paramString != null) {
      StyleConstants.setFontFamily(localSimpleAttributeSet, paramString);
    }
    if (paramInt != -1) {
      StyleConstants.setFontSize(localSimpleAttributeSet, paramInt);
    }
    StyleConstants.setBold(localSimpleAttributeSet, paramBoolean1);
    StyleConstants.setItalic(localSimpleAttributeSet, paramBoolean2);
    StyleConstants.setUnderline(localSimpleAttributeSet, paramBoolean3);
    setStyle(localSimpleAttributeSet);
    return getStyle();
  }
  
  private void setStyle(AttributeSet paramAttributeSet)
  {
    setStyle(paramAttributeSet, false);
  }
  
  private void setStyle(AttributeSet paramAttributeSet, boolean paramBoolean)
  {
    this.text.setCharacterAttributes(paramAttributeSet, paramBoolean);
  }
  
  private AttributeSet getStyle()
  {
    return this.text.getCharacterAttributes();
  }
  
  public void setFont(Font paramFont)
  {
    super.setFont(paramFont);
    if (this.text != null) {
      this.text.setFont(paramFont);
    }
  }
  
  private void inPipeWatcher()
    throws IOException
  {
    byte[] arrayOfByte = new byte['Ā'];
    int i;
    while ((i = this.inPipe.read(arrayOfByte)) != -1) {
      print(new String(arrayOfByte, 0, i));
    }
    println("Console: Input\tclosed...");
  }
  
  public void run()
  {
    try
    {
      inPipeWatcher();
    }
    catch (IOException localIOException)
    {
      print("Console: I/O Error: " + localIOException + "\n", Color.red);
    }
  }
  
  public String toString()
  {
    return "BeanShell console";
  }
  
  public void mouseClicked(MouseEvent paramMouseEvent) {}
  
  public void mousePressed(MouseEvent paramMouseEvent)
  {
    if (paramMouseEvent.isPopupTrigger()) {
      this.menu.show((Component)paramMouseEvent.getSource(), paramMouseEvent.getX(), paramMouseEvent.getY());
    }
  }
  
  public void mouseReleased(MouseEvent paramMouseEvent)
  {
    if (paramMouseEvent.isPopupTrigger()) {
      this.menu.show((Component)paramMouseEvent.getSource(), paramMouseEvent.getX(), paramMouseEvent.getY());
    }
    this.text.repaint();
  }
  
  public void mouseEntered(MouseEvent paramMouseEvent) {}
  
  public void mouseExited(MouseEvent paramMouseEvent) {}
  
  public void propertyChange(PropertyChangeEvent paramPropertyChangeEvent)
  {
    if (paramPropertyChangeEvent.getPropertyName().equals("lookAndFeel")) {
      SwingUtilities.updateComponentTreeUI(this.menu);
    }
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    String str = paramActionEvent.getActionCommand();
    if (str.equals("Cut")) {
      this.text.cut();
    } else if (str.equals("Copy")) {
      this.text.copy();
    } else if (str.equals("Paste")) {
      this.text.paste();
    }
  }
  
  private void invokeAndWait(Runnable paramRunnable)
  {
    if (!SwingUtilities.isEventDispatchThread()) {
      try
      {
        SwingUtilities.invokeAndWait(paramRunnable);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    } else {
      paramRunnable.run();
    }
  }
  
  public void setNameCompletion(NameCompletion paramNameCompletion)
  {
    this.nameCompletion = paramNameCompletion;
  }
  
  public void setWaitFeedback(boolean paramBoolean)
  {
    if (paramBoolean) {
      setCursor(Cursor.getPredefinedCursor(3));
    } else {
      setCursor(Cursor.getPredefinedCursor(0));
    }
  }
  
  private int textLength()
  {
    return this.text.getDocument().getLength();
  }
  
  public static class BlockingPipedInputStream
    extends PipedInputStream
  {
    boolean closed;
    
    public BlockingPipedInputStream(PipedOutputStream paramPipedOutputStream)
      throws IOException
    {
      super();
    }
    
    public synchronized int read()
      throws IOException
    {
      if (this.closed) {
        throw new IOException("stream closed");
      }
      while (this.in < 0)
      {
        notifyAll();
        try
        {
          wait(750L);
        }
        catch (InterruptedException localInterruptedException)
        {
          throw new InterruptedIOException();
        }
      }
      int i = this.buffer[(this.out++)] & 0xFF;
      if (this.out >= this.buffer.length) {
        this.out = 0;
      }
      if (this.in == this.out) {
        this.in = -1;
      }
      return i;
    }
    
    public void close()
      throws IOException
    {
      this.closed = true;
      super.close();
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/util/JConsole.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */