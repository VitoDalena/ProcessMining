/*      */ package org.jfree.chart;
/*      */ 
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Font;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Line2D.Float;
/*      */ import java.awt.geom.Rectangle2D.Double;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.text.AttributedString;
/*      */ import java.text.CharacterIterator;
/*      */ import org.jfree.data.general.Dataset;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.ui.GradientPaintTransformer;
/*      */ import org.jfree.ui.StandardGradientPaintTransformer;
/*      */ import org.jfree.util.AttributedStringUtilities;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PaintUtilities;
/*      */ import org.jfree.util.PublicCloneable;
/*      */ import org.jfree.util.ShapeUtilities;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class LegendItem
/*      */   implements Cloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = -797214582948827144L;
/*      */   private Dataset dataset;
/*      */   private Comparable seriesKey;
/*      */   private int datasetIndex;
/*      */   private int series;
/*      */   private String label;
/*      */   private Font labelFont;
/*      */   private transient Paint labelPaint;
/*      */   private transient AttributedString attributedLabel;
/*      */   private String description;
/*      */   private String toolTipText;
/*      */   private String urlText;
/*      */   private boolean shapeVisible;
/*      */   private transient Shape shape;
/*      */   private boolean shapeFilled;
/*      */   private transient Paint fillPaint;
/*      */   private GradientPaintTransformer fillPaintTransformer;
/*      */   private boolean shapeOutlineVisible;
/*      */   private transient Paint outlinePaint;
/*      */   private transient Stroke outlineStroke;
/*      */   private boolean lineVisible;
/*      */   private transient Shape line;
/*      */   private transient Stroke lineStroke;
/*      */   private transient Paint linePaint;
/*  194 */   private static final Shape UNUSED_SHAPE = new Line2D.Float();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  200 */   private static final Stroke UNUSED_STROKE = new BasicStroke(0.0F);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LegendItem(String label)
/*      */   {
/*  211 */     this(label, Color.black);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LegendItem(String label, Paint paint)
/*      */   {
/*  224 */     this(label, null, null, null, new Rectangle2D.Double(-4.0D, -4.0D, 8.0D, 8.0D), paint);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LegendItem(String label, String description, String toolTipText, String urlText, Shape shape, Paint fillPaint)
/*      */   {
/*  244 */     this(label, description, toolTipText, urlText, true, shape, true, fillPaint, false, Color.black, UNUSED_STROKE, false, UNUSED_SHAPE, UNUSED_STROKE, Color.black);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LegendItem(String label, String description, String toolTipText, String urlText, Shape shape, Paint fillPaint, Stroke outlineStroke, Paint outlinePaint)
/*      */   {
/*  273 */     this(label, description, toolTipText, urlText, true, shape, true, fillPaint, true, outlinePaint, outlineStroke, false, UNUSED_SHAPE, UNUSED_STROKE, Color.black);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LegendItem(String label, String description, String toolTipText, String urlText, Shape line, Stroke lineStroke, Paint linePaint)
/*      */   {
/*  297 */     this(label, description, toolTipText, urlText, false, UNUSED_SHAPE, false, Color.black, false, Color.black, UNUSED_STROKE, true, line, lineStroke, linePaint);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LegendItem(String label, String description, String toolTipText, String urlText, boolean shapeVisible, Shape shape, boolean shapeFilled, Paint fillPaint, boolean shapeOutlineVisible, Paint outlinePaint, Stroke outlineStroke, boolean lineVisible, Shape line, Stroke lineStroke, Paint linePaint)
/*      */   {
/*  338 */     if (label == null) {
/*  339 */       throw new IllegalArgumentException("Null 'label' argument.");
/*      */     }
/*  341 */     if (fillPaint == null) {
/*  342 */       throw new IllegalArgumentException("Null 'fillPaint' argument.");
/*      */     }
/*  344 */     if (lineStroke == null) {
/*  345 */       throw new IllegalArgumentException("Null 'lineStroke' argument.");
/*      */     }
/*  347 */     if (outlinePaint == null) {
/*  348 */       throw new IllegalArgumentException("Null 'outlinePaint' argument.");
/*      */     }
/*  350 */     if (outlineStroke == null) {
/*  351 */       throw new IllegalArgumentException("Null 'outlineStroke' argument.");
/*      */     }
/*      */     
/*  354 */     this.label = label;
/*  355 */     this.labelPaint = null;
/*  356 */     this.attributedLabel = null;
/*  357 */     this.description = description;
/*  358 */     this.shapeVisible = shapeVisible;
/*  359 */     this.shape = shape;
/*  360 */     this.shapeFilled = shapeFilled;
/*  361 */     this.fillPaint = fillPaint;
/*  362 */     this.fillPaintTransformer = new StandardGradientPaintTransformer();
/*  363 */     this.shapeOutlineVisible = shapeOutlineVisible;
/*  364 */     this.outlinePaint = outlinePaint;
/*  365 */     this.outlineStroke = outlineStroke;
/*  366 */     this.lineVisible = lineVisible;
/*  367 */     this.line = line;
/*  368 */     this.lineStroke = lineStroke;
/*  369 */     this.linePaint = linePaint;
/*  370 */     this.toolTipText = toolTipText;
/*  371 */     this.urlText = urlText;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LegendItem(AttributedString label, String description, String toolTipText, String urlText, Shape shape, Paint fillPaint)
/*      */   {
/*  390 */     this(label, description, toolTipText, urlText, true, shape, true, fillPaint, false, Color.black, UNUSED_STROKE, false, UNUSED_SHAPE, UNUSED_STROKE, Color.black);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LegendItem(AttributedString label, String description, String toolTipText, String urlText, Shape shape, Paint fillPaint, Stroke outlineStroke, Paint outlinePaint)
/*      */   {
/*  419 */     this(label, description, toolTipText, urlText, true, shape, true, fillPaint, true, outlinePaint, outlineStroke, false, UNUSED_SHAPE, UNUSED_STROKE, Color.black);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LegendItem(AttributedString label, String description, String toolTipText, String urlText, Shape line, Stroke lineStroke, Paint linePaint)
/*      */   {
/*  442 */     this(label, description, toolTipText, urlText, false, UNUSED_SHAPE, false, Color.black, false, Color.black, UNUSED_STROKE, true, line, lineStroke, linePaint);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LegendItem(AttributedString label, String description, String toolTipText, String urlText, boolean shapeVisible, Shape shape, boolean shapeFilled, Paint fillPaint, boolean shapeOutlineVisible, Paint outlinePaint, Stroke outlineStroke, boolean lineVisible, Shape line, Stroke lineStroke, Paint linePaint)
/*      */   {
/*  483 */     if (label == null) {
/*  484 */       throw new IllegalArgumentException("Null 'label' argument.");
/*      */     }
/*  486 */     if (fillPaint == null) {
/*  487 */       throw new IllegalArgumentException("Null 'fillPaint' argument.");
/*      */     }
/*  489 */     if (lineStroke == null) {
/*  490 */       throw new IllegalArgumentException("Null 'lineStroke' argument.");
/*      */     }
/*  492 */     if (line == null) {
/*  493 */       throw new IllegalArgumentException("Null 'line' argument.");
/*      */     }
/*  495 */     if (linePaint == null) {
/*  496 */       throw new IllegalArgumentException("Null 'linePaint' argument.");
/*      */     }
/*  498 */     if (outlinePaint == null) {
/*  499 */       throw new IllegalArgumentException("Null 'outlinePaint' argument.");
/*      */     }
/*  501 */     if (outlineStroke == null) {
/*  502 */       throw new IllegalArgumentException("Null 'outlineStroke' argument.");
/*      */     }
/*      */     
/*  505 */     this.label = characterIteratorToString(label.getIterator());
/*  506 */     this.attributedLabel = label;
/*  507 */     this.description = description;
/*  508 */     this.shapeVisible = shapeVisible;
/*  509 */     this.shape = shape;
/*  510 */     this.shapeFilled = shapeFilled;
/*  511 */     this.fillPaint = fillPaint;
/*  512 */     this.fillPaintTransformer = new StandardGradientPaintTransformer();
/*  513 */     this.shapeOutlineVisible = shapeOutlineVisible;
/*  514 */     this.outlinePaint = outlinePaint;
/*  515 */     this.outlineStroke = outlineStroke;
/*  516 */     this.lineVisible = lineVisible;
/*  517 */     this.line = line;
/*  518 */     this.lineStroke = lineStroke;
/*  519 */     this.linePaint = linePaint;
/*  520 */     this.toolTipText = toolTipText;
/*  521 */     this.urlText = urlText;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private String characterIteratorToString(CharacterIterator iterator)
/*      */   {
/*  532 */     int endIndex = iterator.getEndIndex();
/*  533 */     int beginIndex = iterator.getBeginIndex();
/*  534 */     int count = endIndex - beginIndex;
/*  535 */     if (count <= 0) {
/*  536 */       return "";
/*      */     }
/*  538 */     char[] chars = new char[count];
/*  539 */     int i = 0;
/*  540 */     char c = iterator.first();
/*  541 */     while (c != 65535) {
/*  542 */       chars[i] = c;
/*  543 */       i++;
/*  544 */       c = iterator.next();
/*      */     }
/*  546 */     return new String(chars);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Dataset getDataset()
/*      */   {
/*  559 */     return this.dataset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDataset(Dataset dataset)
/*      */   {
/*  570 */     this.dataset = dataset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getDatasetIndex()
/*      */   {
/*  584 */     return this.datasetIndex;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDatasetIndex(int index)
/*      */   {
/*  597 */     this.datasetIndex = index;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Comparable getSeriesKey()
/*      */   {
/*  610 */     return this.seriesKey;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSeriesKey(Comparable key)
/*      */   {
/*  621 */     this.seriesKey = key;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getSeriesIndex()
/*      */   {
/*  632 */     return this.series;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSeriesIndex(int index)
/*      */   {
/*  643 */     this.series = index;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getLabel()
/*      */   {
/*  652 */     return this.label;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Font getLabelFont()
/*      */   {
/*  663 */     return this.labelFont;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelFont(Font font)
/*      */   {
/*  674 */     this.labelFont = font;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getLabelPaint()
/*      */   {
/*  685 */     return this.labelPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelPaint(Paint paint)
/*      */   {
/*  696 */     this.labelPaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public AttributedString getAttributedLabel()
/*      */   {
/*  705 */     return this.attributedLabel;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getDescription()
/*      */   {
/*  714 */     return this.description;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getToolTipText()
/*      */   {
/*  723 */     return this.toolTipText;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getURLText()
/*      */   {
/*  732 */     return this.urlText;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isShapeVisible()
/*      */   {
/*  741 */     return this.shapeVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Shape getShape()
/*      */   {
/*  751 */     return this.shape;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isShapeFilled()
/*      */   {
/*  760 */     return this.shapeFilled;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getFillPaint()
/*      */   {
/*  769 */     return this.fillPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFillPaint(Paint paint)
/*      */   {
/*  780 */     if (paint == null) {
/*  781 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  783 */     this.fillPaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isShapeOutlineVisible()
/*      */   {
/*  793 */     return this.shapeOutlineVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getLineStroke()
/*      */   {
/*  802 */     return this.lineStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getLinePaint()
/*      */   {
/*  811 */     return this.linePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLinePaint(Paint paint)
/*      */   {
/*  822 */     if (paint == null) {
/*  823 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  825 */     this.linePaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getOutlinePaint()
/*      */   {
/*  834 */     return this.outlinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setOutlinePaint(Paint paint)
/*      */   {
/*  845 */     if (paint == null) {
/*  846 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  848 */     this.outlinePaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getOutlineStroke()
/*      */   {
/*  857 */     return this.outlineStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isLineVisible()
/*      */   {
/*  866 */     return this.lineVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Shape getLine()
/*      */   {
/*  875 */     return this.line;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public GradientPaintTransformer getFillPaintTransformer()
/*      */   {
/*  889 */     return this.fillPaintTransformer;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFillPaintTransformer(GradientPaintTransformer transformer)
/*      */   {
/*  903 */     if (transformer == null) {
/*  904 */       throw new IllegalArgumentException("Null 'transformer' attribute.");
/*      */     }
/*  906 */     this.fillPaintTransformer = transformer;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object obj)
/*      */   {
/*  917 */     if (obj == this) {
/*  918 */       return true;
/*      */     }
/*  920 */     if (!(obj instanceof LegendItem)) {
/*  921 */       return false;
/*      */     }
/*  923 */     LegendItem that = (LegendItem)obj;
/*  924 */     if (this.datasetIndex != that.datasetIndex) {
/*  925 */       return false;
/*      */     }
/*  927 */     if (this.series != that.series) {
/*  928 */       return false;
/*      */     }
/*  930 */     if (!this.label.equals(that.label)) {
/*  931 */       return false;
/*      */     }
/*  933 */     if (!AttributedStringUtilities.equal(this.attributedLabel, that.attributedLabel))
/*      */     {
/*  935 */       return false;
/*      */     }
/*  937 */     if (!ObjectUtilities.equal(this.description, that.description)) {
/*  938 */       return false;
/*      */     }
/*  940 */     if (this.shapeVisible != that.shapeVisible) {
/*  941 */       return false;
/*      */     }
/*  943 */     if (!ShapeUtilities.equal(this.shape, that.shape)) {
/*  944 */       return false;
/*      */     }
/*  946 */     if (this.shapeFilled != that.shapeFilled) {
/*  947 */       return false;
/*      */     }
/*  949 */     if (!PaintUtilities.equal(this.fillPaint, that.fillPaint)) {
/*  950 */       return false;
/*      */     }
/*  952 */     if (!ObjectUtilities.equal(this.fillPaintTransformer, that.fillPaintTransformer))
/*      */     {
/*  954 */       return false;
/*      */     }
/*  956 */     if (this.shapeOutlineVisible != that.shapeOutlineVisible) {
/*  957 */       return false;
/*      */     }
/*  959 */     if (!this.outlineStroke.equals(that.outlineStroke)) {
/*  960 */       return false;
/*      */     }
/*  962 */     if (!PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) {
/*  963 */       return false;
/*      */     }
/*  965 */     if ((!this.lineVisible) == that.lineVisible) {
/*  966 */       return false;
/*      */     }
/*  968 */     if (!ShapeUtilities.equal(this.line, that.line)) {
/*  969 */       return false;
/*      */     }
/*  971 */     if (!this.lineStroke.equals(that.lineStroke)) {
/*  972 */       return false;
/*      */     }
/*  974 */     if (!PaintUtilities.equal(this.linePaint, that.linePaint)) {
/*  975 */       return false;
/*      */     }
/*  977 */     if (!ObjectUtilities.equal(this.labelFont, that.labelFont)) {
/*  978 */       return false;
/*      */     }
/*  980 */     if (!PaintUtilities.equal(this.labelPaint, that.labelPaint)) {
/*  981 */       return false;
/*      */     }
/*  983 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object clone()
/*      */     throws CloneNotSupportedException
/*      */   {
/*  998 */     LegendItem clone = (LegendItem)super.clone();
/*  999 */     if ((this.seriesKey instanceof PublicCloneable)) {
/* 1000 */       PublicCloneable pc = (PublicCloneable)this.seriesKey;
/* 1001 */       clone.seriesKey = ((Comparable)pc.clone());
/*      */     }
/*      */     
/* 1004 */     clone.shape = ShapeUtilities.clone(this.shape);
/* 1005 */     if ((this.fillPaintTransformer instanceof PublicCloneable)) {
/* 1006 */       PublicCloneable pc = (PublicCloneable)this.fillPaintTransformer;
/* 1007 */       clone.fillPaintTransformer = ((GradientPaintTransformer)pc.clone());
/*      */     }
/*      */     
/* 1010 */     clone.line = ShapeUtilities.clone(this.line);
/* 1011 */     return clone;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void writeObject(ObjectOutputStream stream)
/*      */     throws IOException
/*      */   {
/* 1022 */     stream.defaultWriteObject();
/* 1023 */     SerialUtilities.writeAttributedString(this.attributedLabel, stream);
/* 1024 */     SerialUtilities.writeShape(this.shape, stream);
/* 1025 */     SerialUtilities.writePaint(this.fillPaint, stream);
/* 1026 */     SerialUtilities.writeStroke(this.outlineStroke, stream);
/* 1027 */     SerialUtilities.writePaint(this.outlinePaint, stream);
/* 1028 */     SerialUtilities.writeShape(this.line, stream);
/* 1029 */     SerialUtilities.writeStroke(this.lineStroke, stream);
/* 1030 */     SerialUtilities.writePaint(this.linePaint, stream);
/* 1031 */     SerialUtilities.writePaint(this.labelPaint, stream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void readObject(ObjectInputStream stream)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1044 */     stream.defaultReadObject();
/* 1045 */     this.attributedLabel = SerialUtilities.readAttributedString(stream);
/* 1046 */     this.shape = SerialUtilities.readShape(stream);
/* 1047 */     this.fillPaint = SerialUtilities.readPaint(stream);
/* 1048 */     this.outlineStroke = SerialUtilities.readStroke(stream);
/* 1049 */     this.outlinePaint = SerialUtilities.readPaint(stream);
/* 1050 */     this.line = SerialUtilities.readShape(stream);
/* 1051 */     this.lineStroke = SerialUtilities.readStroke(stream);
/* 1052 */     this.linePaint = SerialUtilities.readPaint(stream);
/* 1053 */     this.labelPaint = SerialUtilities.readPaint(stream);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/LegendItem.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */