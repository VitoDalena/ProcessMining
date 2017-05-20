/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public class XYDataItem
/*     */   implements Cloneable, Comparable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2751513470325494890L;
/*     */   private Number x;
/*     */   private Number y;
/*     */   
/*     */   public XYDataItem(Number x, Number y)
/*     */   {
/*  73 */     if (x == null) {
/*  74 */       throw new IllegalArgumentException("Null 'x' argument.");
/*     */     }
/*  76 */     this.x = x;
/*  77 */     this.y = y;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XYDataItem(double x, double y)
/*     */   {
/*  87 */     this(new Double(x), new Double(y));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getX()
/*     */   {
/*  96 */     return this.x;
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
/*     */   public double getXValue()
/*     */   {
/* 111 */     return this.x.doubleValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getY()
/*     */   {
/* 120 */     return this.y;
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
/*     */   public double getYValue()
/*     */   {
/* 134 */     double result = NaN.0D;
/* 135 */     if (this.y != null) {
/* 136 */       result = this.y.doubleValue();
/*     */     }
/* 138 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setY(double y)
/*     */   {
/* 148 */     setY(new Double(y));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setY(Number y)
/*     */   {
/* 158 */     this.y = y;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int compareTo(Object o1)
/*     */   {
/*     */     int result;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     int result;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 179 */     if ((o1 instanceof XYDataItem)) {
/* 180 */       XYDataItem dataItem = (XYDataItem)o1;
/* 181 */       double compare = this.x.doubleValue() - dataItem.getX().doubleValue();
/*     */       int result;
/* 183 */       if (compare > 0.0D) {
/* 184 */         result = 1;
/*     */       } else {
/*     */         int result;
/* 187 */         if (compare < 0.0D) {
/* 188 */           result = -1;
/*     */         }
/*     */         else {
/* 191 */           result = 0;
/*     */         }
/*     */         
/*     */       }
/*     */       
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 200 */       result = 1;
/*     */     }
/*     */     
/* 203 */     return result;
/*     */   }
/*     */   
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
/* 216 */     return super.clone();
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
/* 228 */     if (obj == this) {
/* 229 */       return true;
/*     */     }
/* 231 */     if (!(obj instanceof XYDataItem)) {
/* 232 */       return false;
/*     */     }
/* 234 */     XYDataItem that = (XYDataItem)obj;
/* 235 */     if (!this.x.equals(that.x)) {
/* 236 */       return false;
/*     */     }
/* 238 */     if (!ObjectUtilities.equal(this.y, that.y)) {
/* 239 */       return false;
/*     */     }
/* 241 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 251 */     int result = this.x.hashCode();
/* 252 */     result = 29 * result + (this.y != null ? this.y.hashCode() : 0);
/* 253 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 263 */     return "[" + getXValue() + ", " + getYValue() + "]";
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/XYDataItem.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */