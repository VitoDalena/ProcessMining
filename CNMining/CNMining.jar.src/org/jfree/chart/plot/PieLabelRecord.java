/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.text.TextBox;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PieLabelRecord
/*     */   implements Comparable, Serializable
/*     */ {
/*     */   private Comparable key;
/*     */   private double angle;
/*     */   private double baseY;
/*     */   private double allocatedY;
/*     */   private TextBox label;
/*     */   private double labelHeight;
/*     */   private double gap;
/*     */   private double linkPercent;
/*     */   
/*     */   public PieLabelRecord(Comparable key, double angle, double baseY, TextBox label, double labelHeight, double gap, double linkPercent)
/*     */   {
/*  93 */     this.key = key;
/*  94 */     this.angle = angle;
/*  95 */     this.baseY = baseY;
/*  96 */     this.allocatedY = baseY;
/*  97 */     this.label = label;
/*  98 */     this.labelHeight = labelHeight;
/*  99 */     this.gap = gap;
/* 100 */     this.linkPercent = linkPercent;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getBaseY()
/*     */   {
/* 110 */     return this.baseY;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBaseY(double base)
/*     */   {
/* 119 */     this.baseY = base;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getLowerY()
/*     */   {
/* 128 */     return this.allocatedY - this.labelHeight / 2.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getUpperY()
/*     */   {
/* 137 */     return this.allocatedY + this.labelHeight / 2.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getAngle()
/*     */   {
/* 146 */     return this.angle;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getKey()
/*     */   {
/* 155 */     return this.key;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextBox getLabel()
/*     */   {
/* 164 */     return this.label;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getLabelHeight()
/*     */   {
/* 174 */     return this.labelHeight;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getAllocatedY()
/*     */   {
/* 183 */     return this.allocatedY;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAllocatedY(double y)
/*     */   {
/* 192 */     this.allocatedY = y;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getGap()
/*     */   {
/* 201 */     return this.gap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getLinkPercent()
/*     */   {
/* 210 */     return this.linkPercent;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int compareTo(Object obj)
/*     */   {
/* 221 */     int result = 0;
/* 222 */     if ((obj instanceof PieLabelRecord)) {
/* 223 */       PieLabelRecord plr = (PieLabelRecord)obj;
/* 224 */       if (this.baseY < plr.baseY) {
/* 225 */         result = -1;
/*     */       }
/* 227 */       else if (this.baseY > plr.baseY) {
/* 228 */         result = 1;
/*     */       }
/*     */     }
/* 231 */     return result;
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
/* 242 */     if (obj == this) {
/* 243 */       return true;
/*     */     }
/* 245 */     if (!(obj instanceof PieLabelRecord)) {
/* 246 */       return false;
/*     */     }
/* 248 */     PieLabelRecord that = (PieLabelRecord)obj;
/* 249 */     if (!this.key.equals(that.key)) {
/* 250 */       return false;
/*     */     }
/* 252 */     if (this.angle != that.angle) {
/* 253 */       return false;
/*     */     }
/* 255 */     if (this.gap != that.gap) {
/* 256 */       return false;
/*     */     }
/* 258 */     if (this.allocatedY != that.allocatedY) {
/* 259 */       return false;
/*     */     }
/* 261 */     if (this.baseY != that.baseY) {
/* 262 */       return false;
/*     */     }
/* 264 */     if (this.labelHeight != that.labelHeight) {
/* 265 */       return false;
/*     */     }
/* 267 */     if (this.linkPercent != that.linkPercent) {
/* 268 */       return false;
/*     */     }
/* 270 */     if (!this.label.equals(that.label)) {
/* 271 */       return false;
/*     */     }
/* 273 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 282 */     return this.baseY + ", " + this.key.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/PieLabelRecord.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */