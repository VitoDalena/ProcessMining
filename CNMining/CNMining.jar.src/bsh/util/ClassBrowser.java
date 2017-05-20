package bsh.util;

import bsh.BshClassManager;
import bsh.ClassPathException;
import bsh.StringUtil;
import bsh.classpath.BshClassPath;
import bsh.classpath.ClassManagerImpl;
import bsh.classpath.ClassPathListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.SplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class ClassBrowser
  extends JSplitPane
  implements ListSelectionListener, ClassPathListener
{
  BshClassPath classPath;
  BshClassManager classManager;
  JFrame frame;
  JInternalFrame iframe;
  JList classlist;
  JList conslist;
  JList mlist;
  JList fieldlist;
  PackageTree ptree;
  JTextArea methodLine;
  JTree tree;
  String[] packagesList;
  String[] classesList;
  Constructor[] consList;
  Method[] methodList;
  Field[] fieldList;
  String selectedPackage;
  Class selectedClass;
  private static final Color LIGHT_BLUE = new Color(245, 245, 255);
  
  public ClassBrowser()
  {
    this(BshClassManager.createClassManager(null));
  }
  
  public ClassBrowser(BshClassManager paramBshClassManager)
  {
    super(0, true);
    this.classManager = paramBshClassManager;
    setBorder(null);
    SplitPaneUI localSplitPaneUI = getUI();
    if ((localSplitPaneUI instanceof BasicSplitPaneUI)) {
      ((BasicSplitPaneUI)localSplitPaneUI).getDivider().setBorder(null);
    }
  }
  
  String[] toSortedStrings(Collection paramCollection)
  {
    ArrayList localArrayList = new ArrayList(paramCollection);
    String[] arrayOfString = (String[])localArrayList.toArray(new String[0]);
    return StringUtil.bubbleSort(arrayOfString);
  }
  
  void setClist(String paramString)
  {
    this.selectedPackage = paramString;
    Object localObject = this.classPath.getClassesForPackage(paramString);
    if (localObject == null) {
      localObject = new HashSet();
    }
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = ((Set)localObject).iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (str.indexOf("$") == -1) {
        localArrayList.add(BshClassPath.splitClassname(str)[1]);
      }
    }
    this.classesList = toSortedStrings(localArrayList);
    this.classlist.setListData(this.classesList);
  }
  
  String[] parseConstructors(Constructor[] paramArrayOfConstructor)
  {
    String[] arrayOfString = new String[paramArrayOfConstructor.length];
    for (int i = 0; i < arrayOfString.length; i++)
    {
      Constructor localConstructor = paramArrayOfConstructor[i];
      arrayOfString[i] = StringUtil.methodString(localConstructor.getName(), localConstructor.getParameterTypes());
    }
    return arrayOfString;
  }
  
  String[] parseMethods(Method[] paramArrayOfMethod)
  {
    String[] arrayOfString = new String[paramArrayOfMethod.length];
    for (int i = 0; i < arrayOfString.length; i++) {
      arrayOfString[i] = StringUtil.methodString(paramArrayOfMethod[i].getName(), paramArrayOfMethod[i].getParameterTypes());
    }
    return arrayOfString;
  }
  
  String[] parseFields(Field[] paramArrayOfField)
  {
    String[] arrayOfString = new String[paramArrayOfField.length];
    for (int i = 0; i < arrayOfString.length; i++)
    {
      Field localField = paramArrayOfField[i];
      arrayOfString[i] = localField.getName();
    }
    return arrayOfString;
  }
  
  Constructor[] getPublicConstructors(Constructor[] paramArrayOfConstructor)
  {
    Vector localVector = new Vector();
    for (int i = 0; i < paramArrayOfConstructor.length; i++) {
      if (Modifier.isPublic(paramArrayOfConstructor[i].getModifiers())) {
        localVector.addElement(paramArrayOfConstructor[i]);
      }
    }
    Constructor[] arrayOfConstructor = new Constructor[localVector.size()];
    localVector.copyInto(arrayOfConstructor);
    return arrayOfConstructor;
  }
  
  Method[] getPublicMethods(Method[] paramArrayOfMethod)
  {
    Vector localVector = new Vector();
    for (int i = 0; i < paramArrayOfMethod.length; i++) {
      if (Modifier.isPublic(paramArrayOfMethod[i].getModifiers())) {
        localVector.addElement(paramArrayOfMethod[i]);
      }
    }
    Method[] arrayOfMethod = new Method[localVector.size()];
    localVector.copyInto(arrayOfMethod);
    return arrayOfMethod;
  }
  
  Field[] getPublicFields(Field[] paramArrayOfField)
  {
    Vector localVector = new Vector();
    for (int i = 0; i < paramArrayOfField.length; i++) {
      if (Modifier.isPublic(paramArrayOfField[i].getModifiers())) {
        localVector.addElement(paramArrayOfField[i]);
      }
    }
    Field[] arrayOfField = new Field[localVector.size()];
    localVector.copyInto(arrayOfField);
    return arrayOfField;
  }
  
  void setConslist(Class paramClass)
  {
    if (paramClass == null)
    {
      this.conslist.setListData(new Object[0]);
      return;
    }
    this.consList = getPublicConstructors(paramClass.getDeclaredConstructors());
    this.conslist.setListData(parseConstructors(this.consList));
  }
  
  void setMlist(String paramString)
  {
    if (paramString == null)
    {
      this.mlist.setListData(new Object[0]);
      setConslist(null);
      setClassTree(null);
      return;
    }
    try
    {
      if (this.selectedPackage.equals("<unpackaged>")) {
        this.selectedClass = this.classManager.classForName(paramString);
      } else {
        this.selectedClass = this.classManager.classForName(this.selectedPackage + "." + paramString);
      }
    }
    catch (Exception localException)
    {
      System.err.println(localException);
      return;
    }
    if (this.selectedClass == null)
    {
      System.err.println("class not found: " + paramString);
      return;
    }
    this.methodList = getPublicMethods(this.selectedClass.getDeclaredMethods());
    this.mlist.setListData(parseMethods(this.methodList));
    setClassTree(this.selectedClass);
    setConslist(this.selectedClass);
    setFieldList(this.selectedClass);
  }
  
  void setFieldList(Class paramClass)
  {
    if (paramClass == null)
    {
      this.fieldlist.setListData(new Object[0]);
      return;
    }
    this.fieldList = getPublicFields(paramClass.getDeclaredFields());
    this.fieldlist.setListData(parseFields(this.fieldList));
  }
  
  void setMethodLine(Object paramObject)
  {
    this.methodLine.setText(paramObject == null ? "" : paramObject.toString());
  }
  
  void setClassTree(Class paramClass)
  {
    if (paramClass == null)
    {
      this.tree.setModel(null);
      return;
    }
    Object localObject1 = null;
    Object localObject2 = null;
    do
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = new DefaultMutableTreeNode(paramClass.toString());
      if (localObject2 != null) {
        localDefaultMutableTreeNode.add((MutableTreeNode)localObject2);
      } else {
        localObject1 = localDefaultMutableTreeNode;
      }
      localObject2 = localDefaultMutableTreeNode;
    } while ((paramClass = paramClass.getSuperclass()) != null);
    this.tree.setModel(new DefaultTreeModel((TreeNode)localObject2));
    TreeNode localTreeNode = ((MutableTreeNode)localObject1).getParent();
    if (localTreeNode != null)
    {
      TreePath localTreePath = new TreePath(((DefaultTreeModel)this.tree.getModel()).getPathToRoot(localTreeNode));
      this.tree.expandPath(localTreePath);
    }
  }
  
  JPanel labeledPane(JComponent paramJComponent, String paramString)
  {
    JPanel localJPanel = new JPanel(new BorderLayout());
    localJPanel.add("Center", paramJComponent);
    localJPanel.add("North", new JLabel(paramString, 0));
    return localJPanel;
  }
  
  public void init()
    throws ClassPathException
  {
    this.classPath = ((ClassManagerImpl)this.classManager).getClassPath();
    this.classPath.addListener(this);
    Set localSet = this.classPath.getPackagesSet();
    this.ptree = new PackageTree(localSet);
    this.ptree.addTreeSelectionListener(new TreeSelectionListener()
    {
      public void valueChanged(TreeSelectionEvent paramAnonymousTreeSelectionEvent)
      {
        TreePath localTreePath = paramAnonymousTreeSelectionEvent.getPath();
        Object[] arrayOfObject = localTreePath.getPath();
        StringBuffer localStringBuffer = new StringBuffer();
        for (int i = 1; i < arrayOfObject.length; i++)
        {
          localStringBuffer.append(arrayOfObject[i].toString());
          if (i + 1 < arrayOfObject.length) {
            localStringBuffer.append(".");
          }
        }
        ClassBrowser.this.setClist(localStringBuffer.toString());
      }
    });
    this.classlist = new JList();
    this.classlist.setBackground(LIGHT_BLUE);
    this.classlist.addListSelectionListener(this);
    this.conslist = new JList();
    this.conslist.addListSelectionListener(this);
    this.mlist = new JList();
    this.mlist.setBackground(LIGHT_BLUE);
    this.mlist.addListSelectionListener(this);
    this.fieldlist = new JList();
    this.fieldlist.addListSelectionListener(this);
    JSplitPane localJSplitPane1 = splitPane(0, true, labeledPane(new JScrollPane(this.conslist), "Constructors"), labeledPane(new JScrollPane(this.mlist), "Methods"));
    JSplitPane localJSplitPane2 = splitPane(0, true, localJSplitPane1, labeledPane(new JScrollPane(this.fieldlist), "Fields"));
    JSplitPane localJSplitPane3 = splitPane(1, true, labeledPane(new JScrollPane(this.classlist), "Classes"), localJSplitPane2);
    localJSplitPane3 = splitPane(1, true, labeledPane(new JScrollPane(this.ptree), "Packages"), localJSplitPane3);
    JPanel localJPanel1 = new JPanel(new BorderLayout());
    this.methodLine = new JTextArea(1, 60);
    this.methodLine.setBackground(LIGHT_BLUE);
    this.methodLine.setEditable(false);
    this.methodLine.setLineWrap(true);
    this.methodLine.setWrapStyleWord(true);
    this.methodLine.setFont(new Font("Monospaced", 1, 14));
    this.methodLine.setMargin(new Insets(5, 5, 5, 5));
    this.methodLine.setBorder(new MatteBorder(1, 0, 1, 0, LIGHT_BLUE.darker().darker()));
    localJPanel1.add("North", this.methodLine);
    JPanel localJPanel2 = new JPanel(new BorderLayout());
    this.tree = new JTree();
    this.tree.addTreeSelectionListener(new TreeSelectionListener()
    {
      public void valueChanged(TreeSelectionEvent paramAnonymousTreeSelectionEvent)
      {
        ClassBrowser.this.driveToClass(paramAnonymousTreeSelectionEvent.getPath().getLastPathComponent().toString());
      }
    });
    this.tree.setBorder(BorderFactory.createRaisedBevelBorder());
    setClassTree(null);
    localJPanel2.add("Center", this.tree);
    localJPanel1.add("Center", localJPanel2);
    localJPanel1.setPreferredSize(new Dimension(150, 150));
    setTopComponent(localJSplitPane3);
    setBottomComponent(localJPanel1);
  }
  
  private JSplitPane splitPane(int paramInt, boolean paramBoolean, JComponent paramJComponent1, JComponent paramJComponent2)
  {
    JSplitPane localJSplitPane = new JSplitPane(paramInt, paramBoolean, paramJComponent1, paramJComponent2);
    localJSplitPane.setBorder(null);
    SplitPaneUI localSplitPaneUI = localJSplitPane.getUI();
    if ((localSplitPaneUI instanceof BasicSplitPaneUI)) {
      ((BasicSplitPaneUI)localSplitPaneUI).getDivider().setBorder(null);
    }
    return localJSplitPane;
  }
  
  public static void main(String[] paramArrayOfString)
    throws Exception
  {
    ClassBrowser localClassBrowser = new ClassBrowser();
    localClassBrowser.init();
    JFrame localJFrame = new JFrame("BeanShell Class Browser v1.0");
    localJFrame.getContentPane().add("Center", localClassBrowser);
    localClassBrowser.setFrame(localJFrame);
    localJFrame.pack();
    localJFrame.setVisible(true);
  }
  
  public void setFrame(JFrame paramJFrame)
  {
    this.frame = paramJFrame;
  }
  
  public void setFrame(JInternalFrame paramJInternalFrame)
  {
    this.iframe = paramJInternalFrame;
  }
  
  public void valueChanged(ListSelectionEvent paramListSelectionEvent)
  {
    if (paramListSelectionEvent.getSource() == this.classlist)
    {
      String str1 = (String)this.classlist.getSelectedValue();
      setMlist(str1);
      String str2;
      if (str1 == null)
      {
        str2 = "Package: " + this.selectedPackage;
      }
      else
      {
        String str3 = this.selectedPackage + "." + str1;
        str2 = str3 + " (from " + this.classPath.getClassSource(str3) + ")";
      }
      setMethodLine(str2);
    }
    else
    {
      int i;
      if (paramListSelectionEvent.getSource() == this.mlist)
      {
        i = this.mlist.getSelectedIndex();
        if (i == -1) {
          setMethodLine(null);
        } else {
          setMethodLine(this.methodList[i]);
        }
      }
      else if (paramListSelectionEvent.getSource() == this.conslist)
      {
        i = this.conslist.getSelectedIndex();
        if (i == -1) {
          setMethodLine(null);
        } else {
          setMethodLine(this.consList[i]);
        }
      }
      else if (paramListSelectionEvent.getSource() == this.fieldlist)
      {
        i = this.fieldlist.getSelectedIndex();
        if (i == -1) {
          setMethodLine(null);
        } else {
          setMethodLine(this.fieldList[i]);
        }
      }
    }
  }
  
  public void driveToClass(String paramString)
  {
    String[] arrayOfString = BshClassPath.splitClassname(paramString);
    String str1 = arrayOfString[0];
    String str2 = arrayOfString[1];
    if (this.classPath.getClassesForPackage(str1).size() == 0) {
      return;
    }
    this.ptree.setSelectedPackage(str1);
    for (int i = 0; i < this.classesList.length; i++) {
      if (this.classesList[i].equals(str2))
      {
        this.classlist.setSelectedIndex(i);
        this.classlist.ensureIndexIsVisible(i);
        break;
      }
    }
  }
  
  public void toFront()
  {
    if (this.frame != null) {
      this.frame.toFront();
    } else if (this.iframe != null) {
      this.iframe.toFront();
    }
  }
  
  public void classPathChanged()
  {
    Set localSet = this.classPath.getPackagesSet();
    this.ptree.setPackages(localSet);
    setClist(null);
  }
  
  class PackageTree
    extends JTree
  {
    TreeNode root;
    DefaultTreeModel treeModel;
    Map nodeForPackage = new HashMap();
    
    PackageTree(Collection paramCollection)
    {
      setPackages(paramCollection);
      setRootVisible(false);
      setShowsRootHandles(true);
      setExpandsSelectedPaths(true);
    }
    
    public void setPackages(Collection paramCollection)
    {
      this.treeModel = makeTreeModel(paramCollection);
      setModel(this.treeModel);
    }
    
    DefaultTreeModel makeTreeModel(Collection paramCollection)
    {
      HashMap localHashMap = new HashMap();
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        String str1 = (String)localIterator.next();
        String[] arrayOfString = StringUtil.split(str1, ".");
        Object localObject1 = localHashMap;
        for (int i = 0; i < arrayOfString.length; i++)
        {
          String str2 = arrayOfString[i];
          Object localObject2 = (Map)((Map)localObject1).get(str2);
          if (localObject2 == null)
          {
            localObject2 = new HashMap();
            ((Map)localObject1).put(str2, localObject2);
          }
          localObject1 = localObject2;
        }
      }
      this.root = makeNode(localHashMap, "root");
      mapNodes(this.root);
      return new DefaultTreeModel(this.root);
    }
    
    MutableTreeNode makeNode(Map paramMap, String paramString)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = new DefaultMutableTreeNode(paramString);
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        Map localMap = (Map)paramMap.get(str);
        Object localObject;
        if (localMap.size() == 0)
        {
          localObject = new DefaultMutableTreeNode(str);
          localDefaultMutableTreeNode.add((MutableTreeNode)localObject);
        }
        else
        {
          localObject = makeNode(localMap, str);
          localDefaultMutableTreeNode.add((MutableTreeNode)localObject);
        }
      }
      return localDefaultMutableTreeNode;
    }
    
    void mapNodes(TreeNode paramTreeNode)
    {
      addNodeMap(paramTreeNode);
      Enumeration localEnumeration = paramTreeNode.children();
      while (localEnumeration.hasMoreElements())
      {
        TreeNode localTreeNode = (TreeNode)localEnumeration.nextElement();
        mapNodes(localTreeNode);
      }
    }
    
    void addNodeMap(TreeNode paramTreeNode)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      for (TreeNode localTreeNode = paramTreeNode; localTreeNode != this.root; localTreeNode = localTreeNode.getParent())
      {
        localStringBuffer.insert(0, localTreeNode.toString());
        if (localTreeNode.getParent() != this.root) {
          localStringBuffer.insert(0, ".");
        }
      }
      String str = localStringBuffer.toString();
      this.nodeForPackage.put(str, paramTreeNode);
    }
    
    void setSelectedPackage(String paramString)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)this.nodeForPackage.get(paramString);
      if (localDefaultMutableTreeNode == null) {
        return;
      }
      TreePath localTreePath = new TreePath(this.treeModel.getPathToRoot(localDefaultMutableTreeNode));
      setSelectionPath(localTreePath);
      ClassBrowser.this.setClist(paramString);
      scrollPathToVisible(localTreePath);
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/util/ClassBrowser.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */