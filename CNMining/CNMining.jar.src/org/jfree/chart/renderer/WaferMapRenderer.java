/*     */ package org.jfree.chart.renderer;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.jfree.chart.LegendItem;
/*     */ import org.jfree.chart.LegendItemCollection;
/*     */ import org.jfree.chart.plot.DrawingSupplier;
/*     */ import org.jfree.chart.plot.WaferMapPlot;
/*     */ import org.jfree.data.general.WaferMapDataset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WaferMapRenderer
/*     */   extends AbstractRenderer
/*     */ {
/*     */   private Map paintIndex;
/*     */   private WaferMapPlot plot;
/*     */   private int paintLimit;
/*     */   private static final int DEFAULT_PAINT_LIMIT = 35;
/*     */   public static final int POSITION_INDEX = 0;
/*     */   public static final int VALUE_INDEX = 1;
/*     */   private int paintIndexMethod;
/*     */   
/*     */   public WaferMapRenderer()
/*     */   {
/*  94 */     this(null, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public WaferMapRenderer(int paintLimit, int paintIndexMethod)
/*     */   {
/* 104 */     this(new Integer(paintLimit), new Integer(paintIndexMethod));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public WaferMapRenderer(Integer paintLimit, Integer paintIndexMethod)
/*     */   {
/* 116 */     this.paintIndex = new HashMap();
/*     */     
/* 118 */     if (paintLimit == null) {
/* 119 */       this.paintLimit = 35;
/*     */     }
/*     */     else {
/* 122 */       this.paintLimit = paintLimit.intValue();
/*     */     }
/*     */     
/* 125 */     this.paintIndexMethod = 1;
/* 126 */     if ((paintIndexMethod != null) && 
/* 127 */       (isMethodValid(paintIndexMethod.intValue()))) {
/* 128 */       this.paintIndexMethod = paintIndexMethod.intValue();
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
/*     */   private boolean isMethodValid(int method)
/*     */   {
/* 141 */     switch (method) {
/* 142 */     case 0:  return true;
/* 143 */     case 1:  return true; }
/* 144 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DrawingSupplier getDrawingSupplier()
/*     */   {
/* 154 */     DrawingSupplier result = null;
/* 155 */     WaferMapPlot p = getPlot();
/* 156 */     if (p != null) {
/* 157 */       result = p.getDrawingSupplier();
/*     */     }
/* 159 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public WaferMapPlot getPlot()
/*     */   {
/* 168 */     return this.plot;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPlot(WaferMapPlot plot)
/*     */   {
/* 177 */     this.plot = plot;
/* 178 */     makePaintIndex();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getChipColor(Number value)
/*     */   {
/* 189 */     return getSeriesPaint(getPaintIndex(value));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int getPaintIndex(Number value)
/*     */   {
/* 200 */     return ((Integer)this.paintIndex.get(value)).intValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void makePaintIndex()
/*     */   {
/* 208 */     if (this.plot == null) {
/* 209 */       return;
/*     */     }
/* 211 */     WaferMapDataset data = this.plot.getDataset();
/* 212 */     Number dataMin = data.getMinValue();
/* 213 */     Number dataMax = data.getMaxValue();
/* 214 */     Set uniqueValues = data.getUniqueValues();
/* 215 */     int count; Iterator i; if (uniqueValues.size() <= this.paintLimit) {
/* 216 */       count = 0;
/* 217 */       for (i = uniqueValues.iterator(); i.hasNext();) {
/* 218 */         this.paintIndex.put(i.next(), new Integer(count++));
/*     */       }
/*     */       
/*     */     }
/*     */     else
/*     */     {
/* 224 */       switch (this.paintIndexMethod) {
/*     */       case 0: 
/* 226 */         makePositionIndex(uniqueValues);
/* 227 */         break;
/*     */       case 1: 
/* 229 */         makeValueIndex(dataMax, dataMin, uniqueValues);
/* 230 */         break;
/*     */       }
/*     */       
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void makePositionIndex(Set uniqueValues)
/*     */   {
/* 244 */     int valuesPerColor = (int)Math.ceil(uniqueValues.size() / this.paintLimit);
/*     */     
/*     */ 
/* 247 */     int count = 0;
/* 248 */     int paint = 0;
/* 249 */     for (Iterator i = uniqueValues.iterator(); i.hasNext();) {
/* 250 */       this.paintIndex.put(i.next(), new Integer(paint));
/* 251 */       count++; if (count % valuesPerColor == 0) {
/* 252 */         paint++;
/*     */       }
/* 254 */       if (paint > this.paintLimit) {
/* 255 */         paint = this.paintLimit;
/*     */       }
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
/*     */   private void makeValueIndex(Number max, Number min, Set uniqueValues)
/*     */   {
/* 269 */     double valueRange = max.doubleValue() - min.doubleValue();
/* 270 */     double valueStep = valueRange / this.paintLimit;
/* 271 */     int paint = 0;
/* 272 */     double cutPoint = min.doubleValue() + valueStep;
/* 273 */     for (Iterator i = uniqueValues.iterator(); i.hasNext();) {
/* 274 */       Number value = (Number)i.next();
/* 275 */       while (value.doubleValue() > cutPoint) {
/* 276 */         cutPoint += valueStep;
/* 277 */         paint++;
/* 278 */         if (paint > this.paintLimit) {
/* 279 */           paint = this.paintLimit;
/*     */         }
/*     */       }
/* 282 */       this.paintIndex.put(value, new Integer(paint));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LegendItemCollection getLegendCollection()
/*     */   {
/* 293 */     LegendItemCollection result = new LegendItemCollection();
/* 294 */     if ((this.paintIndex != null) && (this.paintIndex.size() > 0)) {
/* 295 */       if (this.paintIndex.size() <= this.paintLimit) {
/* 296 */         Iterator i = this.paintIndex.entrySet().iterator();
/* 297 */         while (i.hasNext())
/*     */         {
/* 299 */           Map.Entry entry = (Map.Entry)i.next();
/* 300 */           String label = entry.getKey().toString();
/* 301 */           String description = label;
/* 302 */           Shape shape = new Rectangle2D.Double(1.0D, 1.0D, 1.0D, 1.0D);
/* 303 */           Paint paint = lookupSeriesPaint(((Integer)entry.getValue()).intValue());
/*     */           
/* 305 */           Paint outlinePaint = Color.black;
/* 306 */           Stroke outlineStroke = DEFAULT_STROKE;
/*     */           
/* 308 */           result.add(new LegendItem(label, description, null, null, shape, paint, outlineStroke, outlinePaint));
/*     */         }
/*     */         
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 315 */         Set unique = new HashSet();
/* 316 */         Iterator i = this.paintIndex.entrySet().iterator();
/* 317 */         while (i.hasNext()) {
/* 318 */           Map.Entry entry = (Map.Entry)i.next();
/* 319 */           if (unique.add(entry.getValue())) {
/* 320 */             String label = getMinPaintValue((Integer)entry.getValue()).toString() + " - " + getMaxPaintValue((Integer)entry.getValue()).toString();
/*     */             
/*     */ 
/*     */ 
/* 324 */             String description = label;
/* 325 */             Shape shape = new Rectangle2D.Double(1.0D, 1.0D, 1.0D, 1.0D);
/* 326 */             Paint paint = getSeriesPaint(((Integer)entry.getValue()).intValue());
/*     */             
/*     */ 
/* 329 */             Paint outlinePaint = Color.black;
/* 330 */             Stroke outlineStroke = DEFAULT_STROKE;
/*     */             
/* 332 */             result.add(new LegendItem(label, description, null, null, shape, paint, outlineStroke, outlinePaint));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 339 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Number getMinPaintValue(Integer index)
/*     */   {
/* 351 */     double minValue = Double.POSITIVE_INFINITY;
/* 352 */     for (Iterator i = this.paintIndex.entrySet().iterator(); i.hasNext();) {
/* 353 */       Map.Entry entry = (Map.Entry)i.next();
/* 354 */       if ((((Integer)entry.getValue()).equals(index)) && 
/* 355 */         (((Number)entry.getKey()).doubleValue() < minValue)) {
/* 356 */         minValue = ((Number)entry.getKey()).doubleValue();
/*     */       }
/*     */     }
/*     */     
/* 360 */     return new Double(minValue);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Number getMaxPaintValue(Integer index)
/*     */   {
/* 372 */     double maxValue = Double.NEGATIVE_INFINITY;
/* 373 */     for (Iterator i = this.paintIndex.entrySet().iterator(); i.hasNext();) {
/* 374 */       Map.Entry entry = (Map.Entry)i.next();
/* 375 */       if ((((Integer)entry.getValue()).equals(index)) && 
/* 376 */         (((Number)entry.getKey()).doubleValue() > maxValue)) {
/* 377 */         maxValue = ((Number)entry.getKey()).doubleValue();
/*     */       }
/*     */     }
/*     */     
/* 381 */     return new Double(maxValue);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/WaferMapRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */