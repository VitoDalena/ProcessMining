/*     */ package org.jfree.chart.entity;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.chart.imagemap.ToolTipTagFragmentGenerator;
/*     */ import org.jfree.chart.imagemap.URLTagFragmentGenerator;
/*     */ import org.jfree.io.SerialUtilities;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChartEntity
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4445994133561919083L;
/*     */   private transient Shape area;
/*     */   private String toolTipText;
/*     */   private String urlText;
/*     */   
/*     */   public ChartEntity(Shape area)
/*     */   {
/* 116 */     this(area, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ChartEntity(Shape area, String toolTipText)
/*     */   {
/* 127 */     this(area, toolTipText, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ChartEntity(Shape area, String toolTipText, String urlText)
/*     */   {
/* 139 */     if (area == null) {
/* 140 */       throw new IllegalArgumentException("Null 'area' argument.");
/*     */     }
/* 142 */     this.area = area;
/* 143 */     this.toolTipText = toolTipText;
/* 144 */     this.urlText = urlText;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Shape getArea()
/*     */   {
/* 153 */     return this.area;
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
/*     */   public void setArea(Shape area)
/*     */   {
/* 166 */     if (area == null) {
/* 167 */       throw new IllegalArgumentException("Null 'area' argument.");
/*     */     }
/* 169 */     this.area = area;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getToolTipText()
/*     */   {
/* 181 */     return this.toolTipText;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setToolTipText(String text)
/*     */   {
/* 190 */     this.toolTipText = text;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getURLText()
/*     */   {
/* 201 */     return this.urlText;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setURLText(String text)
/*     */   {
/* 210 */     this.urlText = text;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getShapeType()
/*     */   {
/* 220 */     if ((this.area instanceof Rectangle2D)) {
/* 221 */       return "rect";
/*     */     }
/*     */     
/* 224 */     return "poly";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getShapeCoords()
/*     */   {
/* 234 */     if ((this.area instanceof Rectangle2D)) {
/* 235 */       return getRectCoords((Rectangle2D)this.area);
/*     */     }
/*     */     
/* 238 */     return getPolyCoords(this.area);
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
/*     */   private String getRectCoords(Rectangle2D rectangle)
/*     */   {
/* 251 */     if (rectangle == null) {
/* 252 */       throw new IllegalArgumentException("Null 'rectangle' argument.");
/*     */     }
/* 254 */     int x1 = (int)rectangle.getX();
/* 255 */     int y1 = (int)rectangle.getY();
/* 256 */     int x2 = x1 + (int)rectangle.getWidth();
/* 257 */     int y2 = y1 + (int)rectangle.getHeight();
/*     */     
/* 259 */     if (x2 == x1) {
/* 260 */       x2++;
/*     */     }
/* 262 */     if (y2 == y1) {
/* 263 */       y2++;
/*     */     }
/*     */     
/* 266 */     return x1 + "," + y1 + "," + x2 + "," + y2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private String getPolyCoords(Shape shape)
/*     */   {
/* 278 */     if (shape == null) {
/* 279 */       throw new IllegalArgumentException("Null 'shape' argument.");
/*     */     }
/* 281 */     StringBuffer result = new StringBuffer();
/* 282 */     boolean first = true;
/* 283 */     float[] coords = new float[6];
/* 284 */     PathIterator pi = shape.getPathIterator(null, 1.0D);
/* 285 */     while (!pi.isDone()) {
/* 286 */       pi.currentSegment(coords);
/* 287 */       if (first) {
/* 288 */         first = false;
/* 289 */         result.append((int)coords[0]);
/* 290 */         result.append(",").append((int)coords[1]);
/*     */       }
/*     */       else {
/* 293 */         result.append(",");
/* 294 */         result.append((int)coords[0]);
/* 295 */         result.append(",");
/* 296 */         result.append((int)coords[1]);
/*     */       }
/* 298 */       pi.next();
/*     */     }
/* 300 */     return result.toString();
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
/*     */   public String getImageMapAreaTag(ToolTipTagFragmentGenerator toolTipTagFragmentGenerator, URLTagFragmentGenerator urlTagFragmentGenerator)
/*     */   {
/* 320 */     StringBuffer tag = new StringBuffer();
/* 321 */     boolean hasURL = this.urlText != null;
/*     */     
/* 323 */     boolean hasToolTip = this.toolTipText != null;
/*     */     
/* 325 */     if ((hasURL) || (hasToolTip)) {
/* 326 */       tag.append("<area shape=\"" + getShapeType() + "\"" + " coords=\"" + getShapeCoords() + "\"");
/*     */       
/* 328 */       if (hasToolTip) {
/* 329 */         tag.append(toolTipTagFragmentGenerator.generateToolTipFragment(this.toolTipText));
/*     */       }
/*     */       
/* 332 */       if (hasURL) {
/* 333 */         tag.append(urlTagFragmentGenerator.generateURLFragment(this.urlText));
/*     */       }
/*     */       else
/*     */       {
/* 337 */         tag.append(" nohref=\"nohref\"");
/*     */       }
/*     */       
/*     */ 
/* 341 */       if (!hasToolTip) {
/* 342 */         tag.append(" alt=\"\"");
/*     */       }
/* 344 */       tag.append("/>");
/*     */     }
/* 346 */     return tag.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 356 */     StringBuffer buf = new StringBuffer("ChartEntity: ");
/* 357 */     buf.append("tooltip = ");
/* 358 */     buf.append(this.toolTipText);
/* 359 */     return buf.toString();
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
/* 370 */     if (obj == this) {
/* 371 */       return true;
/*     */     }
/* 373 */     if (!(obj instanceof ChartEntity)) {
/* 374 */       return false;
/*     */     }
/* 376 */     ChartEntity that = (ChartEntity)obj;
/* 377 */     if (!this.area.equals(that.area)) {
/* 378 */       return false;
/*     */     }
/* 380 */     if (!ObjectUtilities.equal(this.toolTipText, that.toolTipText)) {
/* 381 */       return false;
/*     */     }
/* 383 */     if (!ObjectUtilities.equal(this.urlText, that.urlText)) {
/* 384 */       return false;
/*     */     }
/* 386 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 395 */     int result = 37;
/* 396 */     result = HashUtilities.hashCode(result, this.toolTipText);
/* 397 */     result = HashUtilities.hashCode(result, this.urlText);
/* 398 */     return result;
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
/* 410 */     return super.clone();
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
/* 421 */     stream.defaultWriteObject();
/* 422 */     SerialUtilities.writeShape(this.area, stream);
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
/* 435 */     stream.defaultReadObject();
/* 436 */     this.area = SerialUtilities.readShape(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/entity/ChartEntity.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */