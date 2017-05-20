/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.MessageFormat;
/*     */ import java.text.NumberFormat;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.data.DataUtilities;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractCategoryItemLabelGenerator
/*     */   implements PublicCloneable, Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7108591260223293197L;
/*     */   private String labelFormat;
/*     */   private String nullValueString;
/*     */   private NumberFormat numberFormat;
/*     */   private DateFormat dateFormat;
/*     */   private NumberFormat percentFormat;
/*     */   
/*     */   protected AbstractCategoryItemLabelGenerator(String labelFormat, NumberFormat formatter)
/*     */   {
/* 107 */     this(labelFormat, formatter, NumberFormat.getPercentInstance());
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
/*     */   protected AbstractCategoryItemLabelGenerator(String labelFormat, NumberFormat formatter, NumberFormat percentFormatter)
/*     */   {
/* 123 */     if (labelFormat == null) {
/* 124 */       throw new IllegalArgumentException("Null 'labelFormat' argument.");
/*     */     }
/* 126 */     if (formatter == null) {
/* 127 */       throw new IllegalArgumentException("Null 'formatter' argument.");
/*     */     }
/* 129 */     if (percentFormatter == null) {
/* 130 */       throw new IllegalArgumentException("Null 'percentFormatter' argument.");
/*     */     }
/*     */     
/* 133 */     this.labelFormat = labelFormat;
/* 134 */     this.numberFormat = formatter;
/* 135 */     this.percentFormat = percentFormatter;
/* 136 */     this.dateFormat = null;
/* 137 */     this.nullValueString = "-";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected AbstractCategoryItemLabelGenerator(String labelFormat, DateFormat formatter)
/*     */   {
/* 149 */     if (labelFormat == null) {
/* 150 */       throw new IllegalArgumentException("Null 'labelFormat' argument.");
/*     */     }
/* 152 */     if (formatter == null) {
/* 153 */       throw new IllegalArgumentException("Null 'formatter' argument.");
/*     */     }
/* 155 */     this.labelFormat = labelFormat;
/* 156 */     this.numberFormat = null;
/* 157 */     this.percentFormat = NumberFormat.getPercentInstance();
/* 158 */     this.dateFormat = formatter;
/* 159 */     this.nullValueString = "-";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String generateRowLabel(CategoryDataset dataset, int row)
/*     */   {
/* 171 */     return dataset.getRowKey(row).toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String generateColumnLabel(CategoryDataset dataset, int column)
/*     */   {
/* 183 */     return dataset.getColumnKey(column).toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLabelFormat()
/*     */   {
/* 192 */     return this.labelFormat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NumberFormat getNumberFormat()
/*     */   {
/* 201 */     return this.numberFormat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateFormat getDateFormat()
/*     */   {
/* 210 */     return this.dateFormat;
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
/*     */   protected String generateLabelString(CategoryDataset dataset, int row, int column)
/*     */   {
/* 224 */     if (dataset == null) {
/* 225 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*     */     }
/* 227 */     String result = null;
/* 228 */     Object[] items = createItemArray(dataset, row, column);
/* 229 */     result = MessageFormat.format(this.labelFormat, items);
/* 230 */     return result;
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
/*     */   protected Object[] createItemArray(CategoryDataset dataset, int row, int column)
/*     */   {
/* 246 */     Object[] result = new Object[4];
/* 247 */     result[0] = dataset.getRowKey(row).toString();
/* 248 */     result[1] = dataset.getColumnKey(column).toString();
/* 249 */     Number value = dataset.getValue(row, column);
/* 250 */     if (value != null) {
/* 251 */       if (this.numberFormat != null) {
/* 252 */         result[2] = this.numberFormat.format(value);
/*     */       }
/* 254 */       else if (this.dateFormat != null) {
/* 255 */         result[2] = this.dateFormat.format(value);
/*     */       }
/*     */     }
/*     */     else {
/* 259 */       result[2] = this.nullValueString;
/*     */     }
/* 261 */     if (value != null) {
/* 262 */       double total = DataUtilities.calculateColumnTotal(dataset, column);
/* 263 */       double percent = value.doubleValue() / total;
/* 264 */       result[3] = this.percentFormat.format(percent);
/*     */     }
/*     */     
/* 267 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 278 */     if (obj == this) {
/* 279 */       return true;
/*     */     }
/* 281 */     if (!(obj instanceof AbstractCategoryItemLabelGenerator)) {
/* 282 */       return false;
/*     */     }
/*     */     
/* 285 */     AbstractCategoryItemLabelGenerator that = (AbstractCategoryItemLabelGenerator)obj;
/*     */     
/* 287 */     if (!this.labelFormat.equals(that.labelFormat)) {
/* 288 */       return false;
/*     */     }
/* 290 */     if (!ObjectUtilities.equal(this.dateFormat, that.dateFormat)) {
/* 291 */       return false;
/*     */     }
/* 293 */     if (!ObjectUtilities.equal(this.numberFormat, that.numberFormat)) {
/* 294 */       return false;
/*     */     }
/* 296 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 305 */     int result = 127;
/* 306 */     result = HashUtilities.hashCode(result, this.labelFormat);
/* 307 */     result = HashUtilities.hashCode(result, this.nullValueString);
/* 308 */     result = HashUtilities.hashCode(result, this.dateFormat);
/* 309 */     result = HashUtilities.hashCode(result, this.numberFormat);
/* 310 */     result = HashUtilities.hashCode(result, this.percentFormat);
/* 311 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 322 */     AbstractCategoryItemLabelGenerator clone = (AbstractCategoryItemLabelGenerator)super.clone();
/*     */     
/* 324 */     if (this.numberFormat != null) {
/* 325 */       clone.numberFormat = ((NumberFormat)this.numberFormat.clone());
/*     */     }
/* 327 */     if (this.dateFormat != null) {
/* 328 */       clone.dateFormat = ((DateFormat)this.dateFormat.clone());
/*     */     }
/* 330 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/AbstractCategoryItemLabelGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */