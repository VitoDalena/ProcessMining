/*    */ package com.thoughtworks.xstream.io.xml;
/*    */ 
/*    */ import com.thoughtworks.xstream.core.util.XmlHeaderAwareReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import com.thoughtworks.xstream.io.StreamException;
/*    */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.io.Reader;
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.io.Writer;
/*    */ import org.xmlpull.v1.XmlPullParser;
/*    */ import org.xmlpull.v1.XmlPullParserException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractXppDriver
/*    */   extends AbstractXmlDriver
/*    */ {
/*    */   public AbstractXppDriver(NameCoder nameCoder)
/*    */   {
/* 47 */     super(nameCoder);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public HierarchicalStreamReader createReader(Reader in)
/*    */   {
/* 54 */     in = (in instanceof BufferedReader) ? in : new BufferedReader(in);
/*    */     try {
/* 56 */       return new XppReader(in, createParser(), getNameCoder());
/*    */     } catch (XmlPullParserException e) {
/* 58 */       throw new StreamException("Cannot create XmlPullParser");
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public HierarchicalStreamReader createReader(InputStream in)
/*    */   {
/*    */     try
/*    */     {
/* 67 */       return createReader(new XmlHeaderAwareReader(in));
/*    */     } catch (UnsupportedEncodingException e) {
/* 69 */       throw new StreamException(e);
/*    */     } catch (IOException e) {
/* 71 */       throw new StreamException(e);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public HierarchicalStreamWriter createWriter(Writer out)
/*    */   {
/* 79 */     return new PrettyPrintWriter(out, getNameCoder());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public HierarchicalStreamWriter createWriter(OutputStream out)
/*    */   {
/* 86 */     return createWriter(new OutputStreamWriter(out));
/*    */   }
/*    */   
/*    */   protected abstract XmlPullParser createParser()
/*    */     throws XmlPullParserException;
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/AbstractXppDriver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */