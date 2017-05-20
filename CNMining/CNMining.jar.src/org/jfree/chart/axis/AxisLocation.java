/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class AxisLocation
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3276922179323563410L;
/*  61 */   public static final AxisLocation TOP_OR_LEFT = new AxisLocation("AxisLocation.TOP_OR_LEFT");
/*     */   
/*     */ 
/*     */ 
/*  65 */   public static final AxisLocation TOP_OR_RIGHT = new AxisLocation("AxisLocation.TOP_OR_RIGHT");
/*     */   
/*     */ 
/*     */ 
/*  69 */   public static final AxisLocation BOTTOM_OR_LEFT = new AxisLocation("AxisLocation.BOTTOM_OR_LEFT");
/*     */   
/*     */ 
/*     */ 
/*  73 */   public static final AxisLocation BOTTOM_OR_RIGHT = new AxisLocation("AxisLocation.BOTTOM_OR_RIGHT");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String name;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private AxisLocation(String name)
/*     */   {
/*  85 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AxisLocation getOpposite()
/*     */   {
/*  96 */     return getOpposite(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 105 */     return this.name;
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
/* 118 */     if (this == obj) {
/* 119 */       return true;
/*     */     }
/* 121 */     if (!(obj instanceof AxisLocation)) {
/* 122 */       return false;
/*     */     }
/* 124 */     AxisLocation location = (AxisLocation)obj;
/* 125 */     if (!this.name.equals(location.toString())) {
/* 126 */       return false;
/*     */     }
/* 128 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static AxisLocation getOpposite(AxisLocation location)
/*     */   {
/* 140 */     if (location == null) {
/* 141 */       throw new IllegalArgumentException("Null 'location' argument.");
/*     */     }
/* 143 */     AxisLocation result = null;
/* 144 */     if (location == TOP_OR_LEFT) {
/* 145 */       result = BOTTOM_OR_RIGHT;
/*     */     }
/* 147 */     else if (location == TOP_OR_RIGHT) {
/* 148 */       result = BOTTOM_OR_LEFT;
/*     */     }
/* 150 */     else if (location == BOTTOM_OR_LEFT) {
/* 151 */       result = TOP_OR_RIGHT;
/*     */     }
/* 153 */     else if (location == BOTTOM_OR_RIGHT) {
/* 154 */       result = TOP_OR_LEFT;
/*     */     }
/*     */     else {
/* 157 */       throw new IllegalStateException("AxisLocation not recognised.");
/*     */     }
/* 159 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */     throws ObjectStreamException
/*     */   {
/* 170 */     if (equals(TOP_OR_RIGHT)) {
/* 171 */       return TOP_OR_RIGHT;
/*     */     }
/* 173 */     if (equals(BOTTOM_OR_RIGHT)) {
/* 174 */       return BOTTOM_OR_RIGHT;
/*     */     }
/* 176 */     if (equals(TOP_OR_LEFT)) {
/* 177 */       return TOP_OR_LEFT;
/*     */     }
/* 179 */     if (equals(BOTTOM_OR_LEFT)) {
/* 180 */       return BOTTOM_OR_LEFT;
/*     */     }
/* 182 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/AxisLocation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */