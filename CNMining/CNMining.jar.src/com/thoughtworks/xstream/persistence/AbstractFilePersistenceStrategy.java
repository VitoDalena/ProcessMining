/*     */ package com.thoughtworks.xstream.persistence;
/*     */ 
/*     */ import com.thoughtworks.xstream.XStream;
/*     */ import com.thoughtworks.xstream.converters.ConverterLookup;
/*     */ import com.thoughtworks.xstream.io.StreamException;
/*     */ import com.thoughtworks.xstream.mapper.Mapper;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FilenameFilter;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractFilePersistenceStrategy
/*     */   implements PersistenceStrategy
/*     */ {
/*     */   private final FilenameFilter filter;
/*     */   private final File baseDirectory;
/*     */   private final String encoding;
/*     */   private final transient XStream xstream;
/*     */   
/*     */   public AbstractFilePersistenceStrategy(File baseDirectory, XStream xstream, String encoding)
/*     */   {
/*  49 */     this.baseDirectory = baseDirectory;
/*  50 */     this.xstream = xstream;
/*  51 */     this.encoding = encoding;
/*  52 */     this.filter = new ValidFilenameFilter();
/*     */   }
/*     */   
/*     */   protected ConverterLookup getConverterLookup() {
/*  56 */     return this.xstream.getConverterLookup();
/*     */   }
/*     */   
/*     */   protected Mapper getMapper() {
/*  60 */     return this.xstream.getMapper();
/*     */   }
/*     */   
/*     */   protected boolean isValid(File dir, String name) {
/*  64 */     return name.endsWith(".xml");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract Object extractKey(String paramString);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract String getName(Object paramObject);
/*     */   
/*     */ 
/*     */ 
/*     */   protected class ValidFilenameFilter
/*     */     implements FilenameFilter
/*     */   {
/*     */     protected ValidFilenameFilter() {}
/*     */     
/*     */ 
/*  85 */     public boolean accept(File dir, String name) { return (new File(dir, name).isFile()) && (AbstractFilePersistenceStrategy.this.isValid(dir, name)); }
/*     */   }
/*     */   
/*     */   protected class XmlMapEntriesIterator implements Iterator {
/*     */     protected XmlMapEntriesIterator() {}
/*     */     
/*  91 */     private final File[] files = AbstractFilePersistenceStrategy.this.baseDirectory.listFiles(AbstractFilePersistenceStrategy.this.filter);
/*     */     
/*  93 */     private int position = -1;
/*     */     
/*  95 */     private File current = null;
/*     */     
/*     */     public boolean hasNext() {
/*  98 */       return this.position + 1 < this.files.length;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 102 */       if (this.current == null) {
/* 103 */         throw new IllegalStateException();
/*     */       }
/*     */       
/* 106 */       this.current.delete();
/*     */     }
/*     */     
/*     */     public Object next() {
/* 110 */       new Map.Entry() {
/* 111 */         private final File file = AbstractFilePersistenceStrategy.XmlMapEntriesIterator.this.current = AbstractFilePersistenceStrategy.XmlMapEntriesIterator.this.files[AbstractFilePersistenceStrategy.XmlMapEntriesIterator.access$404(AbstractFilePersistenceStrategy.XmlMapEntriesIterator.this)];
/* 112 */         private final Object key = AbstractFilePersistenceStrategy.this.extractKey(this.file.getName());
/*     */         
/*     */         public Object getKey() {
/* 115 */           return this.key;
/*     */         }
/*     */         
/*     */         public Object getValue() {
/* 119 */           return AbstractFilePersistenceStrategy.this.readFile(this.file);
/*     */         }
/*     */         
/*     */         public Object setValue(Object value) {
/* 123 */           return AbstractFilePersistenceStrategy.this.put(this.key, value);
/*     */         }
/*     */         
/*     */         public boolean equals(Object obj) {
/* 127 */           if (!(obj instanceof Map.Entry)) {
/* 128 */             return false;
/*     */           }
/* 130 */           Object value = getValue();
/* 131 */           Map.Entry e2 = (Map.Entry)obj;
/* 132 */           Object key2 = e2.getKey();
/* 133 */           Object value2 = e2.getValue();
/* 134 */           return (this.key == null ? key2 == null : this.key.equals(key2)) && (value == null ? value2 == null : getValue().equals(e2.getValue()));
/*     */         }
/*     */       };
/*     */     }
/*     */   }
/*     */   
/*     */   private void writeFile(File file, Object value)
/*     */   {
/*     */     try {
/* 143 */       FileOutputStream out = new FileOutputStream(file);
/* 144 */       Writer writer = this.encoding != null ? new OutputStreamWriter(out, this.encoding) : new OutputStreamWriter(out);
/*     */       
/*     */       try
/*     */       {
/* 148 */         this.xstream.toXML(value, writer);
/*     */       } finally {
/* 150 */         writer.close();
/*     */       }
/*     */     } catch (IOException e) {
/* 153 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   private File getFile(String filename) {
/* 158 */     return new File(this.baseDirectory, filename);
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   private Object readFile(File file)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: new 25	java/io/FileInputStream
/*     */     //   3: dup
/*     */     //   4: aload_1
/*     */     //   5: invokespecial 26	java/io/FileInputStream:<init>	(Ljava/io/File;)V
/*     */     //   8: astore_2
/*     */     //   9: aload_0
/*     */     //   10: getfield 6	com/thoughtworks/xstream/persistence/AbstractFilePersistenceStrategy:encoding	Ljava/lang/String;
/*     */     //   13: ifnull +18 -> 31
/*     */     //   16: new 27	java/io/InputStreamReader
/*     */     //   19: dup
/*     */     //   20: aload_2
/*     */     //   21: aload_0
/*     */     //   22: getfield 6	com/thoughtworks/xstream/persistence/AbstractFilePersistenceStrategy:encoding	Ljava/lang/String;
/*     */     //   25: invokespecial 28	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
/*     */     //   28: goto +11 -> 39
/*     */     //   31: new 27	java/io/InputStreamReader
/*     */     //   34: dup
/*     */     //   35: aload_2
/*     */     //   36: invokespecial 29	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
/*     */     //   39: astore_3
/*     */     //   40: aload_0
/*     */     //   41: getfield 5	com/thoughtworks/xstream/persistence/AbstractFilePersistenceStrategy:xstream	Lcom/thoughtworks/xstream/XStream;
/*     */     //   44: aload_3
/*     */     //   45: invokevirtual 30	com/thoughtworks/xstream/XStream:fromXML	(Ljava/io/Reader;)Ljava/lang/Object;
/*     */     //   48: astore 4
/*     */     //   50: aload_3
/*     */     //   51: invokevirtual 31	java/io/Reader:close	()V
/*     */     //   54: aload 4
/*     */     //   56: areturn
/*     */     //   57: astore 5
/*     */     //   59: aload_3
/*     */     //   60: invokevirtual 31	java/io/Reader:close	()V
/*     */     //   63: aload 5
/*     */     //   65: athrow
/*     */     //   66: astore_2
/*     */     //   67: aconst_null
/*     */     //   68: areturn
/*     */     //   69: astore_2
/*     */     //   70: new 21	com/thoughtworks/xstream/io/StreamException
/*     */     //   73: dup
/*     */     //   74: aload_2
/*     */     //   75: invokespecial 22	com/thoughtworks/xstream/io/StreamException:<init>	(Ljava/lang/Throwable;)V
/*     */     //   78: athrow
/*     */     // Line number table:
/*     */     //   Java source line #163	-> byte code offset #0
/*     */     //   Java source line #164	-> byte code offset #9
/*     */     //   Java source line #168	-> byte code offset #40
/*     */     //   Java source line #170	-> byte code offset #50
/*     */     //   Java source line #172	-> byte code offset #66
/*     */     //   Java source line #174	-> byte code offset #67
/*     */     //   Java source line #175	-> byte code offset #69
/*     */     //   Java source line #176	-> byte code offset #70
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	79	0	this	AbstractFilePersistenceStrategy
/*     */     //   0	79	1	file	File
/*     */     //   8	28	2	in	java.io.FileInputStream
/*     */     //   66	2	2	e	java.io.FileNotFoundException
/*     */     //   69	6	2	e	IOException
/*     */     //   39	21	3	reader	java.io.Reader
/*     */     //   57	7	5	localObject2	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   40	50	57	finally
/*     */     //   57	59	57	finally
/*     */     //   0	54	66	java/io/FileNotFoundException
/*     */     //   57	66	66	java/io/FileNotFoundException
/*     */     //   0	54	69	java/io/IOException
/*     */     //   57	66	69	java/io/IOException
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value)
/*     */   {
/* 181 */     Object oldValue = get(key);
/* 182 */     String filename = getName(key);
/* 183 */     writeFile(new File(this.baseDirectory, filename), value);
/* 184 */     return oldValue;
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 188 */     return new XmlMapEntriesIterator();
/*     */   }
/*     */   
/*     */   public int size() {
/* 192 */     return this.baseDirectory.list(this.filter).length;
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key)
/*     */   {
/* 197 */     File file = getFile(getName(key));
/* 198 */     return file.isFile();
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 202 */     return readFile(getFile(getName(key)));
/*     */   }
/*     */   
/*     */   public Object remove(Object key)
/*     */   {
/* 207 */     File file = getFile(getName(key));
/* 208 */     Object value = null;
/* 209 */     if (file.isFile()) {
/* 210 */       value = readFile(file);
/* 211 */       file.delete();
/*     */     }
/* 213 */     return value;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/persistence/AbstractFilePersistenceStrategy.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */