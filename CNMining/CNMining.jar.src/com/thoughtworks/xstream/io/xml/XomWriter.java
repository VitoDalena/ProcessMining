/*    */ package com.thoughtworks.xstream.io.xml;
/*    */ 
/*    */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*    */ import nu.xom.Attribute;
/*    */ import nu.xom.Element;
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
/*    */ public class XomWriter
/*    */   extends AbstractDocumentWriter
/*    */ {
/*    */   public XomWriter()
/*    */   {
/* 26 */     this(null);
/*    */   }
/*    */   
/*    */   public XomWriter(Element parentElement) {
/* 30 */     this(parentElement, new XmlFriendlyNameCoder());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public XomWriter(Element parentElement, NameCoder nameCoder)
/*    */   {
/* 37 */     super(parentElement, nameCoder);
/*    */   }
/*    */   
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public XomWriter(Element parentElement, XmlFriendlyReplacer replacer)
/*    */   {
/* 45 */     this(parentElement, replacer);
/*    */   }
/*    */   
/*    */   protected Object createNode(String name) {
/* 49 */     Element newNode = new Element(encodeNode(name));
/* 50 */     Element top = top();
/* 51 */     if (top != null) {
/* 52 */       top().appendChild(newNode);
/*    */     }
/* 54 */     return newNode;
/*    */   }
/*    */   
/*    */   public void addAttribute(String name, String value) {
/* 58 */     top().addAttribute(new Attribute(encodeAttribute(name), value));
/*    */   }
/*    */   
/*    */   public void setValue(String text) {
/* 62 */     top().appendChild(text);
/*    */   }
/*    */   
/*    */   private Element top() {
/* 66 */     return (Element)getCurrent();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/XomWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */