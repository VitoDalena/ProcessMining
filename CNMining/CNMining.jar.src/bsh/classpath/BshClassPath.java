package bsh.classpath;

import bsh.ClassPathException;
import bsh.NameSource;
import bsh.NameSource.Listener;
import bsh.StringUtil;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class BshClassPath
  implements ClassPathListener, NameSource
{
  String name;
  private List path;
  private List compPaths;
  private Map packageMap;
  private Map classSource;
  private boolean mapsInitialized;
  private UnqualifiedNameTable unqNameTable;
  private boolean nameCompletionIncludesUnqNames = true;
  Vector listeners = new Vector();
  static URL[] userClassPathComp;
  static BshClassPath userClassPath;
  static BshClassPath bootClassPath;
  List nameSourceListeners;
  static MappingFeedback mappingFeedbackListener;
  
  public BshClassPath(String paramString)
  {
    this.name = paramString;
    reset();
  }
  
  public BshClassPath(String paramString, URL[] paramArrayOfURL)
  {
    this(paramString);
    add(paramArrayOfURL);
  }
  
  public void setPath(URL[] paramArrayOfURL)
  {
    reset();
    add(paramArrayOfURL);
  }
  
  public void addComponent(BshClassPath paramBshClassPath)
  {
    if (this.compPaths == null) {
      this.compPaths = new ArrayList();
    }
    this.compPaths.add(paramBshClassPath);
    paramBshClassPath.addListener(this);
  }
  
  public void add(URL[] paramArrayOfURL)
  {
    this.path.addAll(Arrays.asList(paramArrayOfURL));
    if (this.mapsInitialized) {
      map(paramArrayOfURL);
    }
  }
  
  public void add(URL paramURL)
    throws IOException
  {
    this.path.add(paramURL);
    if (this.mapsInitialized) {
      map(paramURL);
    }
  }
  
  public URL[] getPathComponents()
  {
    return (URL[])getFullPath().toArray(new URL[0]);
  }
  
  public synchronized Set getClassesForPackage(String paramString)
  {
    insureInitialized();
    HashSet localHashSet = new HashSet();
    Object localObject = (Collection)this.packageMap.get(paramString);
    if (localObject != null) {
      localHashSet.addAll((Collection)localObject);
    }
    if (this.compPaths != null) {
      for (int i = 0; i < this.compPaths.size(); i++)
      {
        localObject = ((BshClassPath)this.compPaths.get(i)).getClassesForPackage(paramString);
        if (localObject != null) {
          localHashSet.addAll((Collection)localObject);
        }
      }
    }
    return localHashSet;
  }
  
  public synchronized ClassSource getClassSource(String paramString)
  {
    ClassSource localClassSource = (ClassSource)this.classSource.get(paramString);
    if (localClassSource != null) {
      return localClassSource;
    }
    insureInitialized();
    localClassSource = (ClassSource)this.classSource.get(paramString);
    if ((localClassSource == null) && (this.compPaths != null)) {
      for (int i = 0; (i < this.compPaths.size()) && (localClassSource == null); i++) {
        localClassSource = ((BshClassPath)this.compPaths.get(i)).getClassSource(paramString);
      }
    }
    return localClassSource;
  }
  
  public synchronized void setClassSource(String paramString, ClassSource paramClassSource)
  {
    this.classSource.put(paramString, paramClassSource);
  }
  
  public void insureInitialized()
  {
    insureInitialized(true);
  }
  
  protected synchronized void insureInitialized(boolean paramBoolean)
  {
    if ((paramBoolean) && (!this.mapsInitialized)) {
      startClassMapping();
    }
    if (this.compPaths != null) {
      for (int i = 0; i < this.compPaths.size(); i++) {
        ((BshClassPath)this.compPaths.get(i)).insureInitialized(false);
      }
    }
    if (!this.mapsInitialized) {
      map((URL[])this.path.toArray(new URL[0]));
    }
    if ((paramBoolean) && (!this.mapsInitialized)) {
      endClassMapping();
    }
    this.mapsInitialized = true;
  }
  
  protected List getFullPath()
  {
    ArrayList localArrayList = new ArrayList();
    if (this.compPaths != null) {
      for (int i = 0; i < this.compPaths.size(); i++)
      {
        List localList = ((BshClassPath)this.compPaths.get(i)).getFullPath();
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          Object localObject = localIterator.next();
          if (!localArrayList.contains(localObject)) {
            localArrayList.add(localObject);
          }
        }
      }
    }
    localArrayList.addAll(this.path);
    return localArrayList;
  }
  
  public String getClassNameByUnqName(String paramString)
    throws ClassPathException
  {
    insureInitialized();
    UnqualifiedNameTable localUnqualifiedNameTable = getUnqualifiedNameTable();
    Object localObject = localUnqualifiedNameTable.get(paramString);
    if ((localObject instanceof AmbiguousName)) {
      throw new ClassPathException("Ambigous class names: " + ((AmbiguousName)localObject).get());
    }
    return (String)localObject;
  }
  
  private UnqualifiedNameTable getUnqualifiedNameTable()
  {
    if (this.unqNameTable == null) {
      this.unqNameTable = buildUnqualifiedNameTable();
    }
    return this.unqNameTable;
  }
  
  private UnqualifiedNameTable buildUnqualifiedNameTable()
  {
    UnqualifiedNameTable localUnqualifiedNameTable = new UnqualifiedNameTable();
    if (this.compPaths != null) {
      for (int i = 0; i < this.compPaths.size(); i++)
      {
        localObject = ((BshClassPath)this.compPaths.get(i)).classSource.keySet();
        Iterator localIterator = ((Set)localObject).iterator();
        while (localIterator.hasNext()) {
          localUnqualifiedNameTable.add((String)localIterator.next());
        }
      }
    }
    Object localObject = this.classSource.keySet().iterator();
    while (((Iterator)localObject).hasNext()) {
      localUnqualifiedNameTable.add((String)((Iterator)localObject).next());
    }
    return localUnqualifiedNameTable;
  }
  
  public String[] getAllNames()
  {
    insureInitialized();
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = getPackagesSet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localArrayList.addAll(removeInnerClassNames(getClassesForPackage(str)));
    }
    if (this.nameCompletionIncludesUnqNames) {
      localArrayList.addAll(getUnqualifiedNameTable().keySet());
    }
    return (String[])localArrayList.toArray(new String[0]);
  }
  
  synchronized void map(URL[] paramArrayOfURL)
  {
    for (int i = 0; i < paramArrayOfURL.length; i++) {
      try
      {
        map(paramArrayOfURL[i]);
      }
      catch (IOException localIOException)
      {
        String str = "Error constructing classpath: " + paramArrayOfURL[i] + ": " + localIOException;
        errorWhileMapping(str);
      }
    }
  }
  
  synchronized void map(URL paramURL)
    throws IOException
  {
    String str1 = paramURL.getFile();
    File localFile = new File(str1);
    if (localFile.isDirectory())
    {
      classMapping("Directory " + localFile.toString());
      map(traverseDirForClasses(localFile), new DirClassSource(localFile));
    }
    else if (isArchiveFileName(str1))
    {
      classMapping("Archive: " + paramURL);
      map(searchJarForClasses(paramURL), new JarClassSource(paramURL));
    }
    else
    {
      String str2 = "Not a classpath component: " + str1;
      errorWhileMapping(str2);
    }
  }
  
  private void map(String[] paramArrayOfString, Object paramObject)
  {
    for (int i = 0; i < paramArrayOfString.length; i++) {
      mapClass(paramArrayOfString[i], paramObject);
    }
  }
  
  private void mapClass(String paramString, Object paramObject)
  {
    String[] arrayOfString = splitClassname(paramString);
    String str1 = arrayOfString[0];
    String str2 = arrayOfString[1];
    Object localObject1 = (Set)this.packageMap.get(str1);
    if (localObject1 == null)
    {
      localObject1 = new HashSet();
      this.packageMap.put(str1, localObject1);
    }
    ((Set)localObject1).add(paramString);
    Object localObject2 = this.classSource.get(paramString);
    if (localObject2 == null) {
      this.classSource.put(paramString, paramObject);
    }
  }
  
  private synchronized void reset()
  {
    this.path = new ArrayList();
    this.compPaths = null;
    clearCachedStructures();
  }
  
  private synchronized void clearCachedStructures()
  {
    this.mapsInitialized = false;
    this.packageMap = new HashMap();
    this.classSource = new HashMap();
    this.unqNameTable = null;
    nameSpaceChanged();
  }
  
  public void classPathChanged()
  {
    clearCachedStructures();
    notifyListeners();
  }
  
  static String[] traverseDirForClasses(File paramFile)
    throws IOException
  {
    List localList = traverseDirForClassesAux(paramFile, paramFile);
    return (String[])localList.toArray(new String[0]);
  }
  
  static List traverseDirForClassesAux(File paramFile1, File paramFile2)
    throws IOException
  {
    ArrayList localArrayList = new ArrayList();
    String str1 = paramFile1.getAbsolutePath();
    File[] arrayOfFile = paramFile2.listFiles();
    for (int i = 0; i < arrayOfFile.length; i++)
    {
      File localFile = arrayOfFile[i];
      if (localFile.isDirectory())
      {
        localArrayList.addAll(traverseDirForClassesAux(paramFile1, localFile));
      }
      else
      {
        String str2 = localFile.getAbsolutePath();
        if (isClassFileName(str2))
        {
          if (str2.startsWith(str1)) {
            str2 = str2.substring(str1.length() + 1);
          } else {
            throw new IOException("problem parsing paths");
          }
          str2 = canonicalizeClassName(str2);
          localArrayList.add(str2);
        }
      }
    }
    return localArrayList;
  }
  
  static String[] searchJarForClasses(URL paramURL)
    throws IOException
  {
    Vector localVector = new Vector();
    InputStream localInputStream = paramURL.openStream();
    ZipInputStream localZipInputStream = new ZipInputStream(localInputStream);
    ZipEntry localZipEntry;
    while ((localZipEntry = localZipInputStream.getNextEntry()) != null)
    {
      localObject = localZipEntry.getName();
      if (isClassFileName((String)localObject)) {
        localVector.addElement(canonicalizeClassName((String)localObject));
      }
    }
    localZipInputStream.close();
    Object localObject = new String[localVector.size()];
    localVector.copyInto((Object[])localObject);
    return (String[])localObject;
  }
  
  public static boolean isClassFileName(String paramString)
  {
    return paramString.toLowerCase().endsWith(".class");
  }
  
  public static boolean isArchiveFileName(String paramString)
  {
    paramString = paramString.toLowerCase();
    return (paramString.endsWith(".jar")) || (paramString.endsWith(".zip"));
  }
  
  public static String canonicalizeClassName(String paramString)
  {
    String str = paramString.replace('/', '.');
    str = str.replace('\\', '.');
    if (str.startsWith("class ")) {
      str = str.substring(6);
    }
    if (str.endsWith(".class")) {
      str = str.substring(0, str.length() - 6);
    }
    return str;
  }
  
  public static String[] splitClassname(String paramString)
  {
    paramString = canonicalizeClassName(paramString);
    int i = paramString.lastIndexOf(".");
    String str1;
    String str2;
    if (i == -1)
    {
      str1 = paramString;
      str2 = "<unpackaged>";
    }
    else
    {
      str2 = paramString.substring(0, i);
      str1 = paramString.substring(i + 1);
    }
    return new String[] { str2, str1 };
  }
  
  public static Collection removeInnerClassNames(Collection paramCollection)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(paramCollection);
    Iterator localIterator = localArrayList.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (str.indexOf("$") != -1) {
        localIterator.remove();
      }
    }
    return localArrayList;
  }
  
  public static URL[] getUserClassPathComponents()
    throws ClassPathException
  {
    if (userClassPathComp != null) {
      return userClassPathComp;
    }
    String str = System.getProperty("java.class.path");
    String[] arrayOfString = StringUtil.split(str, File.pathSeparator);
    URL[] arrayOfURL = new URL[arrayOfString.length];
    try
    {
      for (int i = 0; i < arrayOfString.length; i++) {
        arrayOfURL[i] = new File(new File(arrayOfString[i]).getCanonicalPath()).toURL();
      }
    }
    catch (IOException localIOException)
    {
      throw new ClassPathException("can't parse class path: " + localIOException);
    }
    userClassPathComp = arrayOfURL;
    return arrayOfURL;
  }
  
  public Set getPackagesSet()
  {
    insureInitialized();
    HashSet localHashSet = new HashSet();
    localHashSet.addAll(this.packageMap.keySet());
    if (this.compPaths != null) {
      for (int i = 0; i < this.compPaths.size(); i++) {
        localHashSet.addAll(((BshClassPath)this.compPaths.get(i)).packageMap.keySet());
      }
    }
    return localHashSet;
  }
  
  public void addListener(ClassPathListener paramClassPathListener)
  {
    this.listeners.addElement(new WeakReference(paramClassPathListener));
  }
  
  public void removeListener(ClassPathListener paramClassPathListener)
  {
    this.listeners.removeElement(paramClassPathListener);
  }
  
  void notifyListeners()
  {
    Enumeration localEnumeration = this.listeners.elements();
    while (localEnumeration.hasMoreElements())
    {
      WeakReference localWeakReference = (WeakReference)localEnumeration.nextElement();
      ClassPathListener localClassPathListener = (ClassPathListener)localWeakReference.get();
      if (localClassPathListener == null) {
        this.listeners.removeElement(localWeakReference);
      } else {
        localClassPathListener.classPathChanged();
      }
    }
  }
  
  public static BshClassPath getUserClassPath()
    throws ClassPathException
  {
    if (userClassPath == null) {
      userClassPath = new BshClassPath("User Class Path", getUserClassPathComponents());
    }
    return userClassPath;
  }
  
  public static BshClassPath getBootClassPath()
    throws ClassPathException
  {
    if (bootClassPath == null) {
      try
      {
        String str = getRTJarPath();
        URL localURL = new File(str).toURL();
        bootClassPath = new BshClassPath("Boot Class Path", new URL[] { localURL });
      }
      catch (MalformedURLException localMalformedURLException)
      {
        throw new ClassPathException(" can't find boot jar: " + localMalformedURLException);
      }
    }
    return bootClassPath;
  }
  
  private static String getRTJarPath()
  {
    String str = Class.class.getResource("/java/lang/String.class").toExternalForm();
    if (!str.startsWith("jar:file:")) {
      return null;
    }
    int i = str.indexOf("!");
    if (i == -1) {
      return null;
    }
    return str.substring("jar:file:".length(), i);
  }
  
  public static void main(String[] paramArrayOfString)
    throws Exception
  {
    URL[] arrayOfURL = new URL[paramArrayOfString.length];
    for (int i = 0; i < paramArrayOfString.length; i++) {
      arrayOfURL[i] = new File(paramArrayOfString[i]).toURL();
    }
    BshClassPath localBshClassPath = new BshClassPath("Test", arrayOfURL);
  }
  
  public String toString()
  {
    return "BshClassPath " + this.name + "(" + super.toString() + ") path= " + this.path + "\n" + "compPaths = {" + this.compPaths + " }";
  }
  
  void nameSpaceChanged()
  {
    if (this.nameSourceListeners == null) {
      return;
    }
    for (int i = 0; i < this.nameSourceListeners.size(); i++) {
      ((NameSource.Listener)this.nameSourceListeners.get(i)).nameSourceChanged(this);
    }
  }
  
  public void addNameSourceListener(NameSource.Listener paramListener)
  {
    if (this.nameSourceListeners == null) {
      this.nameSourceListeners = new ArrayList();
    }
    this.nameSourceListeners.add(paramListener);
  }
  
  public static void addMappingFeedback(MappingFeedback paramMappingFeedback)
  {
    if (mappingFeedbackListener != null) {
      throw new RuntimeException("Unimplemented: already a listener");
    }
    mappingFeedbackListener = paramMappingFeedback;
  }
  
  void startClassMapping()
  {
    if (mappingFeedbackListener != null) {
      mappingFeedbackListener.startClassMapping();
    } else {
      System.err.println("Start ClassPath Mapping");
    }
  }
  
  void classMapping(String paramString)
  {
    if (mappingFeedbackListener != null) {
      mappingFeedbackListener.classMapping(paramString);
    } else {
      System.err.println("Mapping: " + paramString);
    }
  }
  
  void errorWhileMapping(String paramString)
  {
    if (mappingFeedbackListener != null) {
      mappingFeedbackListener.errorWhileMapping(paramString);
    } else {
      System.err.println(paramString);
    }
  }
  
  void endClassMapping()
  {
    if (mappingFeedbackListener != null) {
      mappingFeedbackListener.endClassMapping();
    } else {
      System.err.println("End ClassPath Mapping");
    }
  }
  
  public static abstract interface MappingFeedback
  {
    public abstract void startClassMapping();
    
    public abstract void classMapping(String paramString);
    
    public abstract void errorWhileMapping(String paramString);
    
    public abstract void endClassMapping();
  }
  
  public static class AmbiguousName
  {
    List list = new ArrayList();
    
    public void add(String paramString)
    {
      this.list.add(paramString);
    }
    
    public List get()
    {
      return this.list;
    }
  }
  
  static class UnqualifiedNameTable
    extends HashMap
  {
    void add(String paramString)
    {
      String str = BshClassPath.splitClassname(paramString)[1];
      Object localObject = super.get(str);
      if (localObject == null)
      {
        super.put(str, paramString);
      }
      else if ((localObject instanceof BshClassPath.AmbiguousName))
      {
        ((BshClassPath.AmbiguousName)localObject).add(paramString);
      }
      else
      {
        BshClassPath.AmbiguousName localAmbiguousName = new BshClassPath.AmbiguousName();
        localAmbiguousName.add((String)localObject);
        localAmbiguousName.add(paramString);
        super.put(str, localAmbiguousName);
      }
    }
  }
  
  public static class GeneratedClassSource
    extends BshClassPath.ClassSource
  {
    GeneratedClassSource(byte[] paramArrayOfByte)
    {
      this.source = paramArrayOfByte;
    }
    
    public byte[] getCode(String paramString)
    {
      return (byte[])this.source;
    }
  }
  
  public static class DirClassSource
    extends BshClassPath.ClassSource
  {
    DirClassSource(File paramFile)
    {
      this.source = paramFile;
    }
    
    public File getDir()
    {
      return (File)this.source;
    }
    
    public String toString()
    {
      return "Dir: " + this.source;
    }
    
    public byte[] getCode(String paramString)
    {
      return readBytesFromFile(getDir(), paramString);
    }
    
    public static byte[] readBytesFromFile(File paramFile, String paramString)
    {
      String str = paramString.replace('.', File.separatorChar) + ".class";
      File localFile = new File(paramFile, str);
      if ((localFile == null) || (!localFile.exists())) {
        return null;
      }
      byte[] arrayOfByte;
      try
      {
        FileInputStream localFileInputStream = new FileInputStream(localFile);
        DataInputStream localDataInputStream = new DataInputStream(localFileInputStream);
        arrayOfByte = new byte[(int)localFile.length()];
        localDataInputStream.readFully(arrayOfByte);
        localDataInputStream.close();
      }
      catch (IOException localIOException)
      {
        throw new RuntimeException("Couldn't load file: " + localFile);
      }
      return arrayOfByte;
    }
  }
  
  public static class JarClassSource
    extends BshClassPath.ClassSource
  {
    JarClassSource(URL paramURL)
    {
      this.source = paramURL;
    }
    
    public URL getURL()
    {
      return (URL)this.source;
    }
    
    public byte[] getCode(String paramString)
    {
      throw new Error("Unimplemented");
    }
    
    public String toString()
    {
      return "Jar: " + this.source;
    }
  }
  
  public static abstract class ClassSource
  {
    Object source;
    
    abstract byte[] getCode(String paramString);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/classpath/BshClassPath.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */