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
/*     */ public class ValueHandler
/*     */   extends DefaultHandler
/*     */   implements DatasetTags
/*     */ {
/*     */   private RootHandler rootHandler;
/*     */   private ItemHandler itemHandler;
/*     */   private StringBuffer currentText;
/*     */   
/*     */   public ValueHandler(RootHandler rootHandler, ItemHandler itemHandler)
/*     */   {
/*  69 */     this.rootHandler = rootHandler;
/*  70 */     this.itemHandler = itemHandler;
/*  71 */     this.currentText = new StringBuffer();
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
/*     */   public void startElement(String namespaceURI, String localName, String qName, Attributes atts)
/*     */     throws SAXException
/*     */   {
/*  89 */     if (qName.equals("Value"))
/*     */     {
/*  91 */       clearCurrentText();
/*     */     }
/*     */     else {
/*  94 */       throw new SAXException("Expecting <Value> but found " + qName);
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
/* 112 */     if (qName.equals("Value")) {
/*     */       Number value;
/*     */       try {
/* 115 */         value = Double.valueOf(this.currentText.toString());
/* 116 */         if (((Double)value).isNaN()) {
/* 117 */           value = null;
/*     */         }
/*     */       }
/*     */       catch (NumberFormatException e1) {
/* 121 */         value = null;
/*     */       }
/* 123 */       this.itemHandler.setValue(value);
/* 124 */       this.rootHandler.popSubHandler();
/*     */     }
/*     */     else {
/* 127 */       throw new SAXException("Expecting </Value> but found " + qName);
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
/* 140 */     if (this.currentText != null) {
/* 141 */       this.currentText.append(String.copyValueOf(ch, start, length));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String getCurrentText()
/*     */   {
/* 151 */     return this.currentText.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void clearCurrentText()
/*     */   {
/* 158 */     this.currentText.delete(0, this.currentText.length());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xml/ValueHandler.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */