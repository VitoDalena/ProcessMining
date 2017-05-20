/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.MessageFormat;
/*     */ import java.text.NumberFormat;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.data.xy.XYZDataset;
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
/*     */ public class BubbleXYItemLabelGenerator
/*     */   extends AbstractXYItemLabelGenerator
/*     */   implements XYItemLabelGenerator, PublicCloneable, Serializable
/*     */ {
/*     */   static final long serialVersionUID = -8458568928021240922L;
/*     */   public static final String DEFAULT_FORMAT_STRING = "{3}";
/*     */   private NumberFormat zFormat;
/*     */   private DateFormat zDateFormat;
/*     */   
/*     */   public BubbleXYItemLabelGenerator()
/*     */   {
/*  91 */     this("{3}", NumberFormat.getNumberInstance(), NumberFormat.getNumberInstance(), NumberFormat.getNumberInstance());
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
/*     */ 
/*     */   public BubbleXYItemLabelGenerator(String formatString, NumberFormat xFormat, NumberFormat yFormat, NumberFormat zFormat)
/*     */   {
/* 110 */     super(formatString, xFormat, yFormat);
/* 111 */     if (zFormat == null) {
/* 112 */       throw new IllegalArgumentException("Null 'zFormat' argument.");
/*     */     }
/* 114 */     this.zFormat = zFormat;
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
/*     */   public BubbleXYItemLabelGenerator(String formatString, DateFormat xFormat, DateFormat yFormat, DateFormat zFormat)
/*     */   {
/* 131 */     super(formatString, xFormat, yFormat);
/* 132 */     if (zFormat == null) {
/* 133 */       throw new IllegalArgumentException("Null 'zFormat' argument.");
/*     */     }
/* 135 */     this.zDateFormat = zFormat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NumberFormat getZFormat()
/*     */   {
/* 144 */     return this.zFormat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateFormat getZDateFormat()
/*     */   {
/* 153 */     return this.zDateFormat;
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
/*     */   public String generateLabel(XYDataset dataset, int series, int item)
/*     */   {
/* 166 */     return generateLabelString(dataset, series, item);
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
/*     */   public String generateLabelString(XYDataset dataset, int series, int item)
/*     */   {
/* 179 */     String result = null;
/* 180 */     Object[] items = null;
/* 181 */     if ((dataset instanceof XYZDataset)) {
/* 182 */       items = createItemArray((XYZDataset)dataset, series, item);
/*     */     }
/*     */     else {
/* 185 */       items = createItemArray(dataset, series, item);
/*     */     }
/* 187 */     result = MessageFormat.format(getFormatString(), items);
/* 188 */     return result;
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
/*     */   protected Object[] createItemArray(XYZDataset dataset, int series, int item)
/*     */   {
/* 204 */     Object[] result = new Object[4];
/* 205 */     result[0] = dataset.getSeriesKey(series).toString();
/*     */     
/* 207 */     Number x = dataset.getX(series, item);
/* 208 */     DateFormat xf = getXDateFormat();
/* 209 */     if (xf != null) {
/* 210 */       result[1] = xf.format(x);
/*     */     }
/*     */     else {
/* 213 */       result[1] = getXFormat().format(x);
/*     */     }
/*     */     
/* 216 */     Number y = dataset.getY(series, item);
/* 217 */     DateFormat yf = getYDateFormat();
/* 218 */     if (yf != null) {
/* 219 */       result[2] = yf.format(y);
/*     */     }
/*     */     else {
/* 222 */       result[2] = getYFormat().format(y);
/*     */     }
/*     */     
/* 225 */     Number z = dataset.getZ(series, item);
/* 226 */     if (this.zDateFormat != null) {
/* 227 */       result[3] = this.zDateFormat.format(z);
/*     */     }
/*     */     else {
/* 230 */       result[3] = this.zFormat.format(z);
/*     */     }
/*     */     
/* 233 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 245 */     if (obj == this) {
/* 246 */       return true;
/*     */     }
/* 248 */     if (!(obj instanceof BubbleXYItemLabelGenerator)) {
/* 249 */       return false;
/*     */     }
/* 251 */     if (!super.equals(obj)) {
/* 252 */       return false;
/*     */     }
/* 254 */     BubbleXYItemLabelGenerator that = (BubbleXYItemLabelGenerator)obj;
/* 255 */     if (!ObjectUtilities.equal(this.zFormat, that.zFormat)) {
/* 256 */       return false;
/*     */     }
/* 258 */     if (!ObjectUtilities.equal(this.zDateFormat, that.zDateFormat)) {
/* 259 */       return false;
/*     */     }
/* 261 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 270 */     int h = super.hashCode();
/* 271 */     h = HashUtilities.hashCode(h, this.zFormat);
/* 272 */     h = HashUtilities.hashCode(h, this.zDateFormat);
/* 273 */     return h;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/BubbleXYItemLabelGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */