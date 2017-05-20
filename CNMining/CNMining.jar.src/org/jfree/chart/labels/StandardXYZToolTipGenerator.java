/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.MessageFormat;
/*     */ import java.text.NumberFormat;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.data.xy.XYZDataset;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StandardXYZToolTipGenerator
/*     */   extends StandardXYToolTipGenerator
/*     */   implements XYZToolTipGenerator, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2961577421889473503L;
/*     */   public static final String DEFAULT_TOOL_TIP_FORMAT = "{0}: ({1}, {2}, {3})";
/*     */   private NumberFormat zFormat;
/*     */   private DateFormat zDateFormat;
/*     */   
/*     */   public StandardXYZToolTipGenerator()
/*     */   {
/*  83 */     this("{0}: ({1}, {2}, {3})", NumberFormat.getNumberInstance(), NumberFormat.getNumberInstance(), NumberFormat.getNumberInstance());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StandardXYZToolTipGenerator(String formatString, NumberFormat xFormat, NumberFormat yFormat, NumberFormat zFormat)
/*     */   {
/* 107 */     super(formatString, xFormat, yFormat);
/* 108 */     if (zFormat == null) {
/* 109 */       throw new IllegalArgumentException("Null 'zFormat' argument.");
/*     */     }
/* 111 */     this.zFormat = zFormat;
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
/*     */   public StandardXYZToolTipGenerator(String formatString, DateFormat xFormat, DateFormat yFormat, DateFormat zFormat)
/*     */   {
/* 129 */     super(formatString, xFormat, yFormat);
/* 130 */     if (zFormat == null) {
/* 131 */       throw new IllegalArgumentException("Null 'zFormat' argument.");
/*     */     }
/* 133 */     this.zDateFormat = zFormat;
/*     */   }
/*     */   
/*     */ 
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
/*     */   public String generateToolTip(XYZDataset dataset, int series, int item)
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
/* 180 */     Object[] items = createItemArray((XYZDataset)dataset, series, item);
/* 181 */     result = MessageFormat.format(getFormatString(), items);
/* 182 */     return result;
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
/* 198 */     Object[] result = new Object[4];
/* 199 */     result[0] = dataset.getSeriesKey(series).toString();
/*     */     
/* 201 */     Number x = dataset.getX(series, item);
/* 202 */     DateFormat xf = getXDateFormat();
/* 203 */     if (xf != null) {
/* 204 */       result[1] = xf.format(x);
/*     */     }
/*     */     else {
/* 207 */       result[1] = getXFormat().format(x);
/*     */     }
/*     */     
/* 210 */     Number y = dataset.getY(series, item);
/* 211 */     DateFormat yf = getYDateFormat();
/* 212 */     if (yf != null) {
/* 213 */       result[2] = yf.format(y);
/*     */     }
/*     */     else {
/* 216 */       result[2] = getYFormat().format(y);
/*     */     }
/*     */     
/* 219 */     Number z = dataset.getZ(series, item);
/* 220 */     if (this.zDateFormat != null) {
/* 221 */       result[3] = this.zDateFormat.format(z);
/*     */     }
/*     */     else {
/* 224 */       result[3] = this.zFormat.format(z);
/*     */     }
/*     */     
/* 227 */     return result;
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
/* 239 */     if (obj == this) {
/* 240 */       return true;
/*     */     }
/* 242 */     if (!(obj instanceof StandardXYZToolTipGenerator)) {
/* 243 */       return false;
/*     */     }
/* 245 */     if (!super.equals(obj)) {
/* 246 */       return false;
/*     */     }
/* 248 */     StandardXYZToolTipGenerator that = (StandardXYZToolTipGenerator)obj;
/* 249 */     if (!ObjectUtilities.equal(this.zFormat, that.zFormat)) {
/* 250 */       return false;
/*     */     }
/* 252 */     if (!ObjectUtilities.equal(this.zDateFormat, that.zDateFormat)) {
/* 253 */       return false;
/*     */     }
/* 255 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/StandardXYZToolTipGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */