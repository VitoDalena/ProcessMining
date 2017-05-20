/*     */ package org.jfree.data.xml;
/*     */ 
/*     */ import java.util.Stack;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.category.DefaultCategoryDataset;
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
/*     */ public class CategoryDatasetHandler
/*     */   extends RootHandler
/*     */   implements DatasetTags
/*     */ {
/*     */   private DefaultCategoryDataset dataset;
/*     */   
/*     */   public CategoryDatasetHandler()
/*     */   {
/*  61 */     this.dataset = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CategoryDataset getDataset()
/*     */   {
/*  70 */     return this.dataset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addItem(Comparable rowKey, Comparable columnKey, Number value)
/*     */   {
/*  81 */     this.dataset.addValue(value, rowKey, columnKey);
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
/*  99 */     DefaultHandler current = getCurrentHandler();
/* 100 */     if (current != this) {
/* 101 */       current.startElement(namespaceURI, localName, qName, atts);
/*     */     }
/* 103 */     else if (qName.equals("CategoryDataset")) {
/* 104 */       this.dataset = new DefaultCategoryDataset();
/*     */     }
/* 106 */     else if (qName.equals("Series")) {
/* 107 */       CategorySeriesHandler subhandler = new CategorySeriesHandler(this);
/* 108 */       getSubHandlers().push(subhandler);
/* 109 */       subhandler.startElement(namespaceURI, localName, qName, atts);
/*     */     }
/*     */     else {
/* 112 */       throw new SAXException("Element not recognised: " + qName);
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
/* 130 */     DefaultHandler current = getCurrentHandler();
/* 131 */     if (current != this) {
/* 132 */       current.endElement(namespaceURI, localName, qName);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xml/CategoryDatasetHandler.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */