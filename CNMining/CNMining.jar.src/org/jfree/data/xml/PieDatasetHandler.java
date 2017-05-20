/*     */ package org.jfree.data.xml;
/*     */ 
/*     */ import java.util.Stack;
/*     */ import org.jfree.data.general.DefaultPieDataset;
/*     */ import org.jfree.data.general.PieDataset;
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
/*     */ public class PieDatasetHandler
/*     */   extends RootHandler
/*     */   implements DatasetTags
/*     */ {
/*     */   private DefaultPieDataset dataset;
/*     */   
/*     */   public PieDatasetHandler()
/*     */   {
/*  61 */     this.dataset = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PieDataset getDataset()
/*     */   {
/*  70 */     return this.dataset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addItem(Comparable key, Number value)
/*     */   {
/*  80 */     this.dataset.setValue(key, value);
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
/*  98 */     DefaultHandler current = getCurrentHandler();
/*  99 */     if (current != this) {
/* 100 */       current.startElement(namespaceURI, localName, qName, atts);
/*     */     }
/* 102 */     else if (qName.equals("PieDataset")) {
/* 103 */       this.dataset = new DefaultPieDataset();
/*     */     }
/* 105 */     else if (qName.equals("Item")) {
/* 106 */       ItemHandler subhandler = new ItemHandler(this, this);
/* 107 */       getSubHandlers().push(subhandler);
/* 108 */       subhandler.startElement(namespaceURI, localName, qName, atts);
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
/* 126 */     DefaultHandler current = getCurrentHandler();
/* 127 */     if (current != this) {
/* 128 */       current.endElement(namespaceURI, localName, qName);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xml/PieDatasetHandler.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */