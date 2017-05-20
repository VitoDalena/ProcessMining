/*     */ package com.thoughtworks.xstream;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ConversionException;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
/*     */ import com.thoughtworks.xstream.io.xml.XppDriver;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.io.StringWriter;
/*     */ import java.io.Writer;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XStreamer
/*     */ {
/*     */   public String toXML(XStream xstream, Object obj)
/*     */     throws ObjectStreamException
/*     */   {
/*  46 */     Writer writer = new StringWriter();
/*     */     try {
/*  48 */       toXML(xstream, obj, writer);
/*     */     } catch (ObjectStreamException e) {
/*  50 */       throw e;
/*     */     } catch (IOException e) {
/*  52 */       throw new ConversionException("Unexpeced IO error from a StringWriter", e);
/*     */     }
/*  54 */     return writer.toString();
/*     */   }
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
/*     */   public void toXML(XStream xstream, Object obj, Writer out)
/*     */     throws IOException
/*     */   {
/*  74 */     XStream outer = new XStream();
/*  75 */     ObjectOutputStream oos = outer.createObjectOutputStream(out);
/*     */     try {
/*  77 */       oos.writeObject(xstream);
/*  78 */       oos.flush();
/*  79 */       xstream.toXML(obj, out);
/*     */     } finally {
/*  81 */       oos.close();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object fromXML(String xml)
/*     */     throws ClassNotFoundException, ObjectStreamException
/*     */   {
/*     */     try
/*     */     {
/*  97 */       return fromXML(new StringReader(xml));
/*     */     } catch (ObjectStreamException e) {
/*  99 */       throw e;
/*     */     } catch (IOException e) {
/* 101 */       throw new ConversionException("Unexpeced IO error from a StringReader", e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object fromXML(HierarchicalStreamDriver driver, String xml)
/*     */     throws ClassNotFoundException, ObjectStreamException
/*     */   {
/*     */     try
/*     */     {
/* 117 */       return fromXML(driver, new StringReader(xml));
/*     */     } catch (ObjectStreamException e) {
/* 119 */       throw e;
/*     */     } catch (IOException e) {
/* 121 */       throw new ConversionException("Unexpeced IO error from a StringReader", e);
/*     */     }
/*     */   }
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
/*     */   public Object fromXML(Reader xml)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 137 */     return fromXML(new XppDriver(), xml);
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public Object fromXML(HierarchicalStreamDriver driver, Reader xml)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/*     */     // Byte code:
/*     */     //   0: new 11	com/thoughtworks/xstream/XStream
/*     */     //   3: dup
/*     */     //   4: aload_1
/*     */     //   5: invokespecial 25	com/thoughtworks/xstream/XStream:<init>	(Lcom/thoughtworks/xstream/io/HierarchicalStreamDriver;)V
/*     */     //   8: astore_3
/*     */     //   9: aload_1
/*     */     //   10: aload_2
/*     */     //   11: invokeinterface 26 2 0
/*     */     //   16: astore 4
/*     */     //   18: aload_3
/*     */     //   19: aload 4
/*     */     //   21: invokevirtual 27	com/thoughtworks/xstream/XStream:createObjectInputStream	(Lcom/thoughtworks/xstream/io/HierarchicalStreamReader;)Ljava/io/ObjectInputStream;
/*     */     //   24: astore 5
/*     */     //   26: aload 5
/*     */     //   28: invokevirtual 28	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
/*     */     //   31: checkcast 11	com/thoughtworks/xstream/XStream
/*     */     //   34: astore 6
/*     */     //   36: aload 6
/*     */     //   38: aload 4
/*     */     //   40: invokevirtual 27	com/thoughtworks/xstream/XStream:createObjectInputStream	(Lcom/thoughtworks/xstream/io/HierarchicalStreamReader;)Ljava/io/ObjectInputStream;
/*     */     //   43: astore 7
/*     */     //   45: aload 7
/*     */     //   47: invokevirtual 28	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
/*     */     //   50: astore 8
/*     */     //   52: aload 7
/*     */     //   54: invokevirtual 29	java/io/ObjectInputStream:close	()V
/*     */     //   57: aload 5
/*     */     //   59: invokevirtual 29	java/io/ObjectInputStream:close	()V
/*     */     //   62: aload 8
/*     */     //   64: areturn
/*     */     //   65: astore 9
/*     */     //   67: aload 7
/*     */     //   69: invokevirtual 29	java/io/ObjectInputStream:close	()V
/*     */     //   72: aload 9
/*     */     //   74: athrow
/*     */     //   75: astore 10
/*     */     //   77: aload 5
/*     */     //   79: invokevirtual 29	java/io/ObjectInputStream:close	()V
/*     */     //   82: aload 10
/*     */     //   84: athrow
/*     */     // Line number table:
/*     */     //   Java source line #150	-> byte code offset #0
/*     */     //   Java source line #151	-> byte code offset #9
/*     */     //   Java source line #152	-> byte code offset #18
/*     */     //   Java source line #154	-> byte code offset #26
/*     */     //   Java source line #155	-> byte code offset #36
/*     */     //   Java source line #157	-> byte code offset #45
/*     */     //   Java source line #159	-> byte code offset #52
/*     */     //   Java source line #162	-> byte code offset #57
/*     */     //   Java source line #159	-> byte code offset #65
/*     */     //   Java source line #162	-> byte code offset #75
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	85	0	this	XStreamer
/*     */     //   0	85	1	driver	HierarchicalStreamDriver
/*     */     //   0	85	2	xml	Reader
/*     */     //   8	11	3	outer	XStream
/*     */     //   16	23	4	reader	com.thoughtworks.xstream.io.HierarchicalStreamReader
/*     */     //   24	54	5	configIn	java.io.ObjectInputStream
/*     */     //   34	3	6	configured	XStream
/*     */     //   43	25	7	in	java.io.ObjectInputStream
/*     */     //   50	13	8	localObject1	Object
/*     */     //   65	8	9	localObject2	Object
/*     */     //   75	8	10	localObject3	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   45	52	65	finally
/*     */     //   65	67	65	finally
/*     */     //   26	57	75	finally
/*     */     //   65	77	75	finally
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/XStreamer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */