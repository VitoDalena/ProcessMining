/*    */ package com.thoughtworks.xstream.io.xml;
/*    */ 
/*    */ import com.thoughtworks.xstream.core.util.XmlHeaderAwareReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import com.thoughtworks.xstream.io.StreamException;
/*    */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*    */ import com.thoughtworks.xstream.io.xml.xppdom.XppDom;
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
/*    */ public abstract class AbstractXppDomDriver
/*    */   extends AbstractXmlDriver
/*    */ {
/*    */   public AbstractXppDomDriver(NameCoder nameCoder)
/*    */   {
/* 47 */     super(nameCoder);
/*    */   }
/*    */   
/*    */ 
/*    */   public HierarchicalStreamReader createReader(Reader in)
/*    */   {
/*    */     try
/*    */     {
/* 55 */       XmlPullParser parser = createParser();
/* 56 */       parser.setInput(in);
/* 57 */       return new XppDomReader(XppDom.build(parser), getNameCoder());
/*    */     } catch (XmlPullParserException e) {
/* 59 */       throw new StreamException(e);
/*    */     } catch (IOException e) {
/* 61 */       throw new StreamException(e);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public HierarchicalStreamReader createReader(InputStream in)
/*    */   {
/*    */     try
/*    */     {
/* 70 */       return createReader(new XmlHeaderAwareReader(in));
/*    */     } catch (UnsupportedEncodingException e) {
/* 72 */       throw new StreamException(e);
/*    */     } catch (IOException e) {
/* 74 */       throw new StreamException(e);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public HierarchicalStreamWriter createWriter(Writer out)
/*    */   {
/* 82 */     return new PrettyPrintWriter(out, getNameCoder());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public HierarchicalStreamWriter createWriter(OutputStream out)
/*    */   {
/* 89 */     return createWriter(new OutputStreamWriter(out));
/*    */   }
/*    */   
/*    */   protected abstract XmlPullParser createParser()
/*    */     throws XmlPullParserException;
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/AbstractXppDomDriver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */