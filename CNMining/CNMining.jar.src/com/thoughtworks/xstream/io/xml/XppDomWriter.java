/*    */ package com.thoughtworks.xstream.io.xml;
/*    */ 
/*    */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*    */ import com.thoughtworks.xstream.io.xml.xppdom.XppDom;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class XppDomWriter
/*    */   extends AbstractDocumentWriter
/*    */ {
/*    */   public XppDomWriter()
/*    */   {
/* 20 */     this(null, new XmlFriendlyNameCoder());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public XppDomWriter(XppDom parent)
/*    */   {
/* 27 */     this(parent, new XmlFriendlyNameCoder());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public XppDomWriter(NameCoder nameCoder)
/*    */   {
/* 34 */     this(null, nameCoder);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public XppDomWriter(XppDom parent, NameCoder nameCoder)
/*    */   {
/* 41 */     super(parent, nameCoder);
/*    */   }
/*    */   
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public XppDomWriter(XmlFriendlyReplacer replacer)
/*    */   {
/* 49 */     this(null, replacer);
/*    */   }
/*    */   
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public XppDomWriter(XppDom parent, XmlFriendlyReplacer replacer)
/*    */   {
/* 57 */     this(parent, replacer);
/*    */   }
/*    */   
/*    */   public XppDom getConfiguration() {
/* 61 */     return (XppDom)getTopLevelNodes().get(0);
/*    */   }
/*    */   
/*    */   protected Object createNode(String name) {
/* 65 */     XppDom newNode = new XppDom(encodeNode(name));
/* 66 */     XppDom top = top();
/* 67 */     if (top != null) {
/* 68 */       top().addChild(newNode);
/*    */     }
/* 70 */     return newNode;
/*    */   }
/*    */   
/*    */   public void setValue(String text) {
/* 74 */     top().setValue(text);
/*    */   }
/*    */   
/*    */   public void addAttribute(String key, String value) {
/* 78 */     top().setAttribute(encodeAttribute(key), value);
/*    */   }
/*    */   
/*    */   private XppDom top() {
/* 82 */     return (XppDom)getCurrent();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/XppDomWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */