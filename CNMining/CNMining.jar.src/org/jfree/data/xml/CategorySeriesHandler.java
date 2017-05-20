/*     */ package org.jfree.data.xml;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.data.DefaultKeyedValues;
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
/*     */ public class CategorySeriesHandler
/*     */   extends DefaultHandler
/*     */   implements DatasetTags
/*     */ {
/*     */   private RootHandler root;
/*     */   private Comparable seriesKey;
/*     */   private DefaultKeyedValues values;
/*     */   
/*     */   public CategorySeriesHandler(RootHandler root)
/*     */   {
/*  71 */     this.root = root;
/*  72 */     this.values = new DefaultKeyedValues();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSeriesKey(Comparable key)
/*     */   {
/*  81 */     this.seriesKey = key;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addItem(Comparable key, Number value)
/*     */   {
/*  91 */     this.values.addValue(key, value);
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
/* 109 */     if (qName.equals("Series")) {
/* 110 */       setSeriesKey(atts.getValue("name"));
/* 111 */       ItemHandler subhandler = new ItemHandler(this.root, this);
/* 112 */       this.root.pushSubHandler(subhandler);
/*     */     }
/* 114 */     else if (qName.equals("Item")) {
/* 115 */       ItemHandler subhandler = new ItemHandler(this.root, this);
/* 116 */       this.root.pushSubHandler(subhandler);
/* 117 */       subhandler.startElement(namespaceURI, localName, qName, atts);
/*     */     }
/*     */     else
/*     */     {
/* 121 */       throw new SAXException("Expecting <Series> or <Item> tag...found " + qName);
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
/*     */   {
/* 138 */     if ((this.root instanceof CategoryDatasetHandler)) {
/* 139 */       CategoryDatasetHandler handler = (CategoryDatasetHandler)this.root;
/*     */       
/* 141 */       Iterator iterator = this.values.getKeys().iterator();
/* 142 */       while (iterator.hasNext()) {
/* 143 */         Comparable key = (Comparable)iterator.next();
/* 144 */         Number value = this.values.getValue(key);
/* 145 */         handler.addItem(this.seriesKey, key, value);
/*     */       }
/*     */       
/* 148 */       this.root.popSubHandler();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xml/CategorySeriesHandler.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */