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
/*     */ public class ItemHandler
/*     */   extends DefaultHandler
/*     */   implements DatasetTags
/*     */ {
/*     */   private RootHandler root;
/*     */   private DefaultHandler parent;
/*     */   private Comparable key;
/*     */   private Number value;
/*     */   
/*     */   public ItemHandler(RootHandler root, DefaultHandler parent)
/*     */   {
/*  71 */     this.root = root;
/*  72 */     this.parent = parent;
/*  73 */     this.key = null;
/*  74 */     this.value = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getKey()
/*     */   {
/*  83 */     return this.key;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setKey(Comparable key)
/*     */   {
/*  92 */     this.key = key;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getValue()
/*     */   {
/* 101 */     return this.value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValue(Number value)
/*     */   {
/* 110 */     this.value = value;
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
/* 128 */     if (qName.equals("Item")) {
/* 129 */       KeyHandler subhandler = new KeyHandler(this.root, this);
/* 130 */       this.root.pushSubHandler(subhandler);
/*     */     }
/* 132 */     else if (qName.equals("Value")) {
/* 133 */       ValueHandler subhandler = new ValueHandler(this.root, this);
/* 134 */       this.root.pushSubHandler(subhandler);
/*     */     }
/*     */     else {
/* 137 */       throw new SAXException("Expected <Item> or <Value>...found " + qName);
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
/*     */ 
/*     */   public void endElement(String namespaceURI, String localName, String qName)
/*     */   {
/* 155 */     if ((this.parent instanceof PieDatasetHandler)) {
/* 156 */       PieDatasetHandler handler = (PieDatasetHandler)this.parent;
/* 157 */       handler.addItem(this.key, this.value);
/* 158 */       this.root.popSubHandler();
/*     */     }
/* 160 */     else if ((this.parent instanceof CategorySeriesHandler)) {
/* 161 */       CategorySeriesHandler handler = (CategorySeriesHandler)this.parent;
/* 162 */       handler.addItem(this.key, this.value);
/* 163 */       this.root.popSubHandler();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xml/ItemHandler.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */