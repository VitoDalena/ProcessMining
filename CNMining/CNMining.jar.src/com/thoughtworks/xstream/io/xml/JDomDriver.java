/*    */ package com.thoughtworks.xstream.io.xml;
/*    */ 
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import com.thoughtworks.xstream.io.StreamException;
/*    */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.io.Reader;
/*    */ import java.io.Writer;
/*    */ import org.jdom.Document;
/*    */ import org.jdom.JDOMException;
/*    */ import org.jdom.input.SAXBuilder;
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
/*    */ public class JDomDriver
/*    */   extends AbstractXmlDriver
/*    */ {
/*    */   public JDomDriver()
/*    */   {
/* 36 */     super(new XmlFriendlyNameCoder());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public JDomDriver(NameCoder nameCoder)
/*    */   {
/* 43 */     super(nameCoder);
/*    */   }
/*    */   
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public JDomDriver(XmlFriendlyReplacer replacer)
/*    */   {
/* 51 */     this(replacer);
/*    */   }
/*    */   
/*    */   public HierarchicalStreamReader createReader(Reader reader) {
/*    */     try {
/* 56 */       SAXBuilder builder = new SAXBuilder();
/* 57 */       Document document = builder.build(reader);
/* 58 */       return new JDomReader(document, getNameCoder());
/*    */     } catch (IOException e) {
/* 60 */       throw new StreamException(e);
/*    */     } catch (JDOMException e) {
/* 62 */       throw new StreamException(e);
/*    */     }
/*    */   }
/*    */   
/*    */   public HierarchicalStreamReader createReader(InputStream in) {
/*    */     try {
/* 68 */       SAXBuilder builder = new SAXBuilder();
/* 69 */       Document document = builder.build(in);
/* 70 */       return new JDomReader(document, getNameCoder());
/*    */     } catch (IOException e) {
/* 72 */       throw new StreamException(e);
/*    */     } catch (JDOMException e) {
/* 74 */       throw new StreamException(e);
/*    */     }
/*    */   }
/*    */   
/*    */   public HierarchicalStreamWriter createWriter(Writer out) {
/* 79 */     return new PrettyPrintWriter(out, getNameCoder());
/*    */   }
/*    */   
/*    */   public HierarchicalStreamWriter createWriter(OutputStream out) {
/* 83 */     return new PrettyPrintWriter(new OutputStreamWriter(out));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/JDomDriver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */