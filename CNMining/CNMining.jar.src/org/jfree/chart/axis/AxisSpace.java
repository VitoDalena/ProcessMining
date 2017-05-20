/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.Serializable;
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
/*     */ public class AxisSpace
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2490732595134766305L;
/*     */   private double top;
/*     */   private double bottom;
/*     */   private double left;
/*     */   private double right;
/*     */   
/*     */   public AxisSpace()
/*     */   {
/*  79 */     this.top = 0.0D;
/*  80 */     this.bottom = 0.0D;
/*  81 */     this.left = 0.0D;
/*  82 */     this.right = 0.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getTop()
/*     */   {
/*  91 */     return this.top;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTop(double space)
/*     */   {
/* 100 */     this.top = space;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getBottom()
/*     */   {
/* 109 */     return this.bottom;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBottom(double space)
/*     */   {
/* 118 */     this.bottom = space;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getLeft()
/*     */   {
/* 127 */     return this.left;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLeft(double space)
/*     */   {
/* 136 */     this.left = space;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getRight()
/*     */   {
/* 145 */     return this.right;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRight(double space)
/*     */   {
/* 154 */     this.right = space;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(double space, RectangleEdge edge)
/*     */   {
/* 164 */     if (edge == null) {
/* 165 */       throw new IllegalArgumentException("Null 'edge' argument.");
/*     */     }
/* 167 */     if (edge == RectangleEdge.TOP) {
/* 168 */       this.top += space;
/*     */     }
/* 170 */     else if (edge == RectangleEdge.BOTTOM) {
/* 171 */       this.bottom += space;
/*     */     }
/* 173 */     else if (edge == RectangleEdge.LEFT) {
/* 174 */       this.left += space;
/*     */     }
/* 176 */     else if (edge == RectangleEdge.RIGHT) {
/* 177 */       this.right += space;
/*     */     }
/*     */     else {
/* 180 */       throw new IllegalStateException("Unrecognised 'edge' argument.");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void ensureAtLeast(AxisSpace space)
/*     */   {
/* 190 */     this.top = Math.max(this.top, space.top);
/* 191 */     this.bottom = Math.max(this.bottom, space.bottom);
/* 192 */     this.left = Math.max(this.left, space.left);
/* 193 */     this.right = Math.max(this.right, space.right);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void ensureAtLeast(double space, RectangleEdge edge)
/*     */   {
/* 204 */     if (edge == RectangleEdge.TOP) {
/* 205 */       if (this.top < space) {
/* 206 */         this.top = space;
/*     */       }
/*     */     }
/* 209 */     else if (edge == RectangleEdge.BOTTOM) {
/* 210 */       if (this.bottom < space) {
/* 211 */         this.bottom = space;
/*     */       }
/*     */     }
/* 214 */     else if (edge == RectangleEdge.LEFT) {
/* 215 */       if (this.left < space) {
/* 216 */         this.left = space;
/*     */       }
/*     */     }
/* 219 */     else if (edge == RectangleEdge.RIGHT) {
/* 220 */       if (this.right < space) {
/* 221 */         this.right = space;
/*     */       }
/*     */     }
/*     */     else {
/* 225 */       throw new IllegalStateException("AxisSpace.ensureAtLeast(): unrecognised AxisLocation.");
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
/*     */   public Rectangle2D shrink(Rectangle2D area, Rectangle2D result)
/*     */   {
/* 240 */     if (result == null) {
/* 241 */       result = new Rectangle2D.Double();
/*     */     }
/* 243 */     result.setRect(area.getX() + this.left, area.getY() + this.top, area.getWidth() - this.left - this.right, area.getHeight() - this.top - this.bottom);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 249 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Rectangle2D expand(Rectangle2D area, Rectangle2D result)
/*     */   {
/* 261 */     if (result == null) {
/* 262 */       result = new Rectangle2D.Double();
/*     */     }
/* 264 */     result.setRect(area.getX() - this.left, area.getY() - this.top, area.getWidth() + this.left + this.right, area.getHeight() + this.top + this.bottom);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 270 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Rectangle2D reserved(Rectangle2D area, RectangleEdge edge)
/*     */   {
/* 282 */     Rectangle2D result = null;
/* 283 */     if (edge == RectangleEdge.TOP) {
/* 284 */       result = new Rectangle2D.Double(area.getX(), area.getY(), area.getWidth(), this.top);
/*     */ 
/*     */ 
/*     */     }
/* 288 */     else if (edge == RectangleEdge.BOTTOM) {
/* 289 */       result = new Rectangle2D.Double(area.getX(), area.getMaxY() - this.top, area.getWidth(), this.bottom);
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/* 294 */     else if (edge == RectangleEdge.LEFT) {
/* 295 */       result = new Rectangle2D.Double(area.getX(), area.getY(), this.left, area.getHeight());
/*     */ 
/*     */ 
/*     */     }
/* 299 */     else if (edge == RectangleEdge.RIGHT) {
/* 300 */       result = new Rectangle2D.Double(area.getMaxX() - this.right, area.getY(), this.right, area.getHeight());
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 305 */     return result;
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
/* 317 */     return super.clone();
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
/* 328 */     if (obj == this) {
/* 329 */       return true;
/*     */     }
/* 331 */     if (!(obj instanceof AxisSpace)) {
/* 332 */       return false;
/*     */     }
/* 334 */     AxisSpace that = (AxisSpace)obj;
/* 335 */     if (this.top != that.top) {
/* 336 */       return false;
/*     */     }
/* 338 */     if (this.bottom != that.bottom) {
/* 339 */       return false;
/*     */     }
/* 341 */     if (this.left != that.left) {
/* 342 */       return false;
/*     */     }
/* 344 */     if (this.right != that.right) {
/* 345 */       return false;
/*     */     }
/* 347 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 356 */     int result = 23;
/* 357 */     long l = Double.doubleToLongBits(this.top);
/* 358 */     result = 37 * result + (int)(l ^ l >>> 32);
/* 359 */     l = Double.doubleToLongBits(this.bottom);
/* 360 */     result = 37 * result + (int)(l ^ l >>> 32);
/* 361 */     l = Double.doubleToLongBits(this.left);
/* 362 */     result = 37 * result + (int)(l ^ l >>> 32);
/* 363 */     l = Double.doubleToLongBits(this.right);
/* 364 */     result = 37 * result + (int)(l ^ l >>> 32);
/* 365 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 374 */     return super.toString() + "[left=" + this.left + ",right=" + this.right + ",top=" + this.top + ",bottom=" + this.bottom + "]";
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/AxisSpace.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */