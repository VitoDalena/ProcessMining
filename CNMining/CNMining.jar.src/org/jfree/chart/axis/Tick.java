/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.ui.TextAnchor;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Tick
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 6668230383875149773L;
/*     */   private String text;
/*     */   private TextAnchor textAnchor;
/*     */   private TextAnchor rotationAnchor;
/*     */   private double angle;
/*     */   
/*     */   public Tick(String text, TextAnchor textAnchor, TextAnchor rotationAnchor, double angle)
/*     */   {
/*  84 */     if (textAnchor == null) {
/*  85 */       throw new IllegalArgumentException("Null 'textAnchor' argument.");
/*     */     }
/*  87 */     if (rotationAnchor == null) {
/*  88 */       throw new IllegalArgumentException("Null 'rotationAnchor' argument.");
/*     */     }
/*     */     
/*     */ 
/*  92 */     this.text = text;
/*  93 */     this.textAnchor = textAnchor;
/*  94 */     this.rotationAnchor = rotationAnchor;
/*  95 */     this.angle = angle;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getText()
/*     */   {
/* 104 */     return this.text;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextAnchor getTextAnchor()
/*     */   {
/* 113 */     return this.textAnchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextAnchor getRotationAnchor()
/*     */   {
/* 123 */     return this.rotationAnchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getAngle()
/*     */   {
/* 132 */     return this.angle;
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
/* 143 */     if (this == obj) {
/* 144 */       return true;
/*     */     }
/* 146 */     if ((obj instanceof Tick)) {
/* 147 */       Tick t = (Tick)obj;
/* 148 */       if (!ObjectUtilities.equal(this.text, t.text)) {
/* 149 */         return false;
/*     */       }
/* 151 */       if (!ObjectUtilities.equal(this.textAnchor, t.textAnchor)) {
/* 152 */         return false;
/*     */       }
/* 154 */       if (!ObjectUtilities.equal(this.rotationAnchor, t.rotationAnchor)) {
/* 155 */         return false;
/*     */       }
/* 157 */       if (this.angle != t.angle) {
/* 158 */         return false;
/*     */       }
/* 160 */       return true;
/*     */     }
/* 162 */     return false;
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
/* 173 */     Tick clone = (Tick)super.clone();
/* 174 */     return clone;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 183 */     return this.text;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/Tick.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */