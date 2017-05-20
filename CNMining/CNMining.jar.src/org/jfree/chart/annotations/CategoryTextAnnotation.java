/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.CategoryAnchor;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.text.TextUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
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
/*     */ public class CategoryTextAnnotation
/*     */   extends TextAnnotation
/*     */   implements CategoryAnnotation, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3333360090781320147L;
/*     */   private Comparable category;
/*     */   private CategoryAnchor categoryAnchor;
/*     */   private double value;
/*     */   
/*     */   public CategoryTextAnnotation(String text, Comparable category, double value)
/*     */   {
/*  95 */     super(text);
/*  96 */     if (category == null) {
/*  97 */       throw new IllegalArgumentException("Null 'category' argument.");
/*     */     }
/*  99 */     this.category = category;
/* 100 */     this.value = value;
/* 101 */     this.categoryAnchor = CategoryAnchor.MIDDLE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getCategory()
/*     */   {
/* 112 */     return this.category;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCategory(Comparable category)
/*     */   {
/* 123 */     if (category == null) {
/* 124 */       throw new IllegalArgumentException("Null 'category' argument.");
/*     */     }
/* 126 */     this.category = category;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CategoryAnchor getCategoryAnchor()
/*     */   {
/* 137 */     return this.categoryAnchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCategoryAnchor(CategoryAnchor anchor)
/*     */   {
/* 148 */     if (anchor == null) {
/* 149 */       throw new IllegalArgumentException("Null 'anchor' argument.");
/*     */     }
/* 151 */     this.categoryAnchor = anchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getValue()
/*     */   {
/* 162 */     return this.value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValue(double value)
/*     */   {
/* 173 */     this.value = value;
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
/*     */   public void draw(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea, CategoryAxis domainAxis, ValueAxis rangeAxis)
/*     */   {
/* 188 */     CategoryDataset dataset = plot.getDataset();
/* 189 */     int catIndex = dataset.getColumnIndex(this.category);
/* 190 */     int catCount = dataset.getColumnCount();
/*     */     
/* 192 */     float anchorX = 0.0F;
/* 193 */     float anchorY = 0.0F;
/* 194 */     PlotOrientation orientation = plot.getOrientation();
/* 195 */     RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
/*     */     
/* 197 */     RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
/*     */     
/*     */ 
/* 200 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 201 */       anchorY = (float)domainAxis.getCategoryJava2DCoordinate(this.categoryAnchor, catIndex, catCount, dataArea, domainEdge);
/*     */       
/*     */ 
/* 204 */       anchorX = (float)rangeAxis.valueToJava2D(this.value, dataArea, rangeEdge);
/*     */ 
/*     */     }
/* 207 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 208 */       anchorX = (float)domainAxis.getCategoryJava2DCoordinate(this.categoryAnchor, catIndex, catCount, dataArea, domainEdge);
/*     */       
/*     */ 
/* 211 */       anchorY = (float)rangeAxis.valueToJava2D(this.value, dataArea, rangeEdge);
/*     */     }
/*     */     
/* 214 */     g2.setFont(getFont());
/* 215 */     g2.setPaint(getPaint());
/* 216 */     TextUtilities.drawRotatedString(getText(), g2, anchorX, anchorY, getTextAnchor(), getRotationAngle(), getRotationAnchor());
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 229 */     if (obj == this) {
/* 230 */       return true;
/*     */     }
/* 232 */     if (!(obj instanceof CategoryTextAnnotation)) {
/* 233 */       return false;
/*     */     }
/* 235 */     CategoryTextAnnotation that = (CategoryTextAnnotation)obj;
/* 236 */     if (!super.equals(obj)) {
/* 237 */       return false;
/*     */     }
/* 239 */     if (!this.category.equals(that.getCategory())) {
/* 240 */       return false;
/*     */     }
/* 242 */     if (!this.categoryAnchor.equals(that.getCategoryAnchor())) {
/* 243 */       return false;
/*     */     }
/* 245 */     if (this.value != that.getValue()) {
/* 246 */       return false;
/*     */     }
/* 248 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 257 */     int result = super.hashCode();
/* 258 */     result = 37 * result + this.category.hashCode();
/* 259 */     result = 37 * result + this.categoryAnchor.hashCode();
/* 260 */     long temp = Double.doubleToLongBits(this.value);
/* 261 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 262 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 274 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/annotations/CategoryTextAnnotation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */