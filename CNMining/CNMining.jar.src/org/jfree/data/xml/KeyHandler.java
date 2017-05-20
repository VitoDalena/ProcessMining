/*     */ package org.jfree.data.xml;
/*     */ 
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
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
/*     */ public class KeyHandler
/*     */   extends DefaultHandler
/*     */   implements DatasetTags
/*     */ {
/*     */   private RootHandler rootHandler;
/*     */   private ItemHandler itemHandler;
/*     */   private StringBuffer currentText;
/*     */   
/*     */   public KeyHandler(RootHandler rootHandler, ItemHandler itemHandler)
/*     */   {
/*  71 */     this.rootHandler = rootHandler;
/*  72 */     this.itemHandler = itemHandler;
/*  73 */     this.currentText = new StringBuffer();
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
/*     */   public void startElement(String namespaceURI, String localName, String qName, Attributes atts)
/*     */     throws SAXException
/*     */   {
/*  92 */     if (qName.equals("Key")) {
/*  93 */       clearCurrentText();
/*     */     }
/*     */     else {
/*  96 */       throw new SAXException("Expecting <Key> but found " + qName);
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
/*     */ 
/*     */ 
/*     */   public void endElement(String namespaceURI, String localName, String qName)
/*     */     throws SAXException
/*     */   {
/* 114 */     if (qName.equals("Key")) {
/* 115 */       this.itemHandler.setKey(getCurrentText());
/* 116 */       this.rootHandler.popSubHandler();
/* 117 */       this.rootHandler.pushSubHandler(new ValueHandler(this.rootHandler, this.itemHandler));
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 122 */       throw new SAXException("Expecting </Key> but found " + qName);
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
/*     */   public void characters(char[] ch, int start, int length)
/*     */   {
/* 135 */     if (this.currentText != null) {
/* 136 */       this.currentText.append(String.copyValueOf(ch, start, length));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String getCurrentText()
/*     */   {
/* 146 */     return this.currentText.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void clearCurrentText()
/*     */   {
/* 153 */     this.currentText.delete(0, this.currentText.length());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xml/KeyHandler.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */