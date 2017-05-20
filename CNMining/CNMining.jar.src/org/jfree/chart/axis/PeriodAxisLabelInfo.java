/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.text.DateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ import java.util.TimeZone;
/*     */ import org.jfree.data.time.RegularTimePeriod;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PeriodAxisLabelInfo
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5710451740920277357L;
/*  82 */   public static final RectangleInsets DEFAULT_INSETS = new RectangleInsets(2.0D, 2.0D, 2.0D, 2.0D);
/*     */   
/*     */ 
/*     */ 
/*  86 */   public static final Font DEFAULT_FONT = new Font("SansSerif", 0, 10);
/*     */   
/*     */ 
/*     */ 
/*  90 */   public static final Paint DEFAULT_LABEL_PAINT = Color.black;
/*     */   
/*     */ 
/*  93 */   public static final Stroke DEFAULT_DIVIDER_STROKE = new BasicStroke(0.5F);
/*     */   
/*     */ 
/*  96 */   public static final Paint DEFAULT_DIVIDER_PAINT = Color.gray;
/*     */   
/*     */ 
/*     */ 
/*     */   private Class periodClass;
/*     */   
/*     */ 
/*     */ 
/*     */   private RectangleInsets padding;
/*     */   
/*     */ 
/*     */ 
/*     */   private DateFormat dateFormat;
/*     */   
/*     */ 
/*     */ 
/*     */   private Font labelFont;
/*     */   
/*     */ 
/*     */   private transient Paint labelPaint;
/*     */   
/*     */ 
/*     */   private boolean drawDividers;
/*     */   
/*     */ 
/*     */   private transient Stroke dividerStroke;
/*     */   
/*     */ 
/*     */   private transient Paint dividerPaint;
/*     */   
/*     */ 
/*     */ 
/*     */   public PeriodAxisLabelInfo(Class periodClass, DateFormat dateFormat)
/*     */   {
/* 130 */     this(periodClass, dateFormat, DEFAULT_INSETS, DEFAULT_FONT, DEFAULT_LABEL_PAINT, true, DEFAULT_DIVIDER_STROKE, DEFAULT_DIVIDER_PAINT);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PeriodAxisLabelInfo(Class periodClass, DateFormat dateFormat, RectangleInsets padding, Font labelFont, Paint labelPaint, boolean drawDividers, Stroke dividerStroke, Paint dividerPaint)
/*     */   {
/* 158 */     if (periodClass == null) {
/* 159 */       throw new IllegalArgumentException("Null 'periodClass' argument.");
/*     */     }
/* 161 */     if (dateFormat == null) {
/* 162 */       throw new IllegalArgumentException("Null 'dateFormat' argument.");
/*     */     }
/* 164 */     if (padding == null) {
/* 165 */       throw new IllegalArgumentException("Null 'padding' argument.");
/*     */     }
/* 167 */     if (labelFont == null) {
/* 168 */       throw new IllegalArgumentException("Null 'labelFont' argument.");
/*     */     }
/* 170 */     if (labelPaint == null) {
/* 171 */       throw new IllegalArgumentException("Null 'labelPaint' argument.");
/*     */     }
/* 173 */     if (dividerStroke == null) {
/* 174 */       throw new IllegalArgumentException("Null 'dividerStroke' argument.");
/*     */     }
/*     */     
/* 177 */     if (dividerPaint == null) {
/* 178 */       throw new IllegalArgumentException("Null 'dividerPaint' argument.");
/*     */     }
/* 180 */     this.periodClass = periodClass;
/* 181 */     this.dateFormat = dateFormat;
/* 182 */     this.padding = padding;
/* 183 */     this.labelFont = labelFont;
/* 184 */     this.labelPaint = labelPaint;
/* 185 */     this.drawDividers = drawDividers;
/* 186 */     this.dividerStroke = dividerStroke;
/* 187 */     this.dividerPaint = dividerPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Class getPeriodClass()
/*     */   {
/* 197 */     return this.periodClass;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateFormat getDateFormat()
/*     */   {
/* 206 */     return this.dateFormat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleInsets getPadding()
/*     */   {
/* 215 */     return this.padding;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Font getLabelFont()
/*     */   {
/* 224 */     return this.labelFont;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getLabelPaint()
/*     */   {
/* 233 */     return this.labelPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getDrawDividers()
/*     */   {
/* 242 */     return this.drawDividers;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getDividerStroke()
/*     */   {
/* 251 */     return this.dividerStroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getDividerPaint()
/*     */   {
/* 260 */     return this.dividerPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public RegularTimePeriod createInstance(Date millisecond, TimeZone zone)
/*     */   {
/* 275 */     return createInstance(millisecond, zone, Locale.getDefault());
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
/*     */   public RegularTimePeriod createInstance(Date millisecond, TimeZone zone, Locale locale)
/*     */   {
/* 292 */     RegularTimePeriod result = null;
/*     */     try {
/* 294 */       Constructor c = this.periodClass.getDeclaredConstructor(new Class[] { Date.class, TimeZone.class, Locale.class });
/*     */       
/* 296 */       result = (RegularTimePeriod)c.newInstance(new Object[] { millisecond, zone, locale });
/*     */     }
/*     */     catch (Exception e) {}
/*     */     
/*     */ 
/*     */ 
/* 302 */     return result;
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
/* 313 */     if (obj == this) {
/* 314 */       return true;
/*     */     }
/* 316 */     if ((obj instanceof PeriodAxisLabelInfo)) {
/* 317 */       PeriodAxisLabelInfo info = (PeriodAxisLabelInfo)obj;
/* 318 */       if (!info.periodClass.equals(this.periodClass)) {
/* 319 */         return false;
/*     */       }
/* 321 */       if (!info.dateFormat.equals(this.dateFormat)) {
/* 322 */         return false;
/*     */       }
/* 324 */       if (!info.padding.equals(this.padding)) {
/* 325 */         return false;
/*     */       }
/* 327 */       if (!info.labelFont.equals(this.labelFont)) {
/* 328 */         return false;
/*     */       }
/* 330 */       if (!info.labelPaint.equals(this.labelPaint)) {
/* 331 */         return false;
/*     */       }
/* 333 */       if (info.drawDividers != this.drawDividers) {
/* 334 */         return false;
/*     */       }
/* 336 */       if (!info.dividerStroke.equals(this.dividerStroke)) {
/* 337 */         return false;
/*     */       }
/* 339 */       if (!info.dividerPaint.equals(this.dividerPaint)) {
/* 340 */         return false;
/*     */       }
/* 342 */       return true;
/*     */     }
/* 344 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 353 */     int result = 41;
/* 354 */     result = 37 * this.periodClass.hashCode();
/* 355 */     result = 37 * this.dateFormat.hashCode();
/* 356 */     return result;
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
/* 367 */     PeriodAxisLabelInfo clone = (PeriodAxisLabelInfo)super.clone();
/* 368 */     return clone;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream stream)
/*     */     throws IOException
/*     */   {
/* 379 */     stream.defaultWriteObject();
/* 380 */     SerialUtilities.writePaint(this.labelPaint, stream);
/* 381 */     SerialUtilities.writeStroke(this.dividerStroke, stream);
/* 382 */     SerialUtilities.writePaint(this.dividerPaint, stream);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void readObject(ObjectInputStream stream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 395 */     stream.defaultReadObject();
/* 396 */     this.labelPaint = SerialUtilities.readPaint(stream);
/* 397 */     this.dividerStroke = SerialUtilities.readStroke(stream);
/* 398 */     this.dividerPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/PeriodAxisLabelInfo.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */