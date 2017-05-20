/*     */ package org.jfree.chart.labels;
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
/*     */ public final class ItemLabelAnchor
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -1233101616128695658L;
/*  60 */   public static final ItemLabelAnchor CENTER = new ItemLabelAnchor("ItemLabelAnchor.CENTER");
/*     */   
/*     */ 
/*     */ 
/*  64 */   public static final ItemLabelAnchor INSIDE1 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE1");
/*     */   
/*     */ 
/*     */ 
/*  68 */   public static final ItemLabelAnchor INSIDE2 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE2");
/*     */   
/*     */ 
/*     */ 
/*  72 */   public static final ItemLabelAnchor INSIDE3 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE3");
/*     */   
/*     */ 
/*     */ 
/*  76 */   public static final ItemLabelAnchor INSIDE4 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE4");
/*     */   
/*     */ 
/*     */ 
/*  80 */   public static final ItemLabelAnchor INSIDE5 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE5");
/*     */   
/*     */ 
/*     */ 
/*  84 */   public static final ItemLabelAnchor INSIDE6 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE6");
/*     */   
/*     */ 
/*     */ 
/*  88 */   public static final ItemLabelAnchor INSIDE7 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE7");
/*     */   
/*     */ 
/*     */ 
/*  92 */   public static final ItemLabelAnchor INSIDE8 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE8");
/*     */   
/*     */ 
/*     */ 
/*  96 */   public static final ItemLabelAnchor INSIDE9 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE9");
/*     */   
/*     */ 
/*     */ 
/* 100 */   public static final ItemLabelAnchor INSIDE10 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE10");
/*     */   
/*     */ 
/*     */ 
/* 104 */   public static final ItemLabelAnchor INSIDE11 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE11");
/*     */   
/*     */ 
/*     */ 
/* 108 */   public static final ItemLabelAnchor INSIDE12 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE12");
/*     */   
/*     */ 
/*     */ 
/* 112 */   public static final ItemLabelAnchor OUTSIDE1 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE1");
/*     */   
/*     */ 
/*     */ 
/* 116 */   public static final ItemLabelAnchor OUTSIDE2 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE2");
/*     */   
/*     */ 
/*     */ 
/* 120 */   public static final ItemLabelAnchor OUTSIDE3 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE3");
/*     */   
/*     */ 
/*     */ 
/* 124 */   public static final ItemLabelAnchor OUTSIDE4 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE4");
/*     */   
/*     */ 
/*     */ 
/* 128 */   public static final ItemLabelAnchor OUTSIDE5 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE5");
/*     */   
/*     */ 
/*     */ 
/* 132 */   public static final ItemLabelAnchor OUTSIDE6 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE6");
/*     */   
/*     */ 
/*     */ 
/* 136 */   public static final ItemLabelAnchor OUTSIDE7 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE7");
/*     */   
/*     */ 
/*     */ 
/* 140 */   public static final ItemLabelAnchor OUTSIDE8 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE8");
/*     */   
/*     */ 
/*     */ 
/* 144 */   public static final ItemLabelAnchor OUTSIDE9 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE9");
/*     */   
/*     */ 
/*     */ 
/* 148 */   public static final ItemLabelAnchor OUTSIDE10 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE10");
/*     */   
/*     */ 
/*     */ 
/* 152 */   public static final ItemLabelAnchor OUTSIDE11 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE11");
/*     */   
/*     */ 
/*     */ 
/* 156 */   public static final ItemLabelAnchor OUTSIDE12 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE12");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String name;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private ItemLabelAnchor(String name)
/*     */   {
/* 168 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 177 */     return this.name;
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
/*     */   public boolean equals(Object o)
/*     */   {
/* 190 */     if (this == o) {
/* 191 */       return true;
/*     */     }
/* 193 */     if (!(o instanceof ItemLabelAnchor)) {
/* 194 */       return false;
/*     */     }
/*     */     
/* 197 */     ItemLabelAnchor order = (ItemLabelAnchor)o;
/* 198 */     if (!this.name.equals(order.toString())) {
/* 199 */       return false;
/*     */     }
/*     */     
/* 202 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */     throws ObjectStreamException
/*     */   {
/* 214 */     ItemLabelAnchor result = null;
/* 215 */     if (equals(CENTER)) {
/* 216 */       result = CENTER;
/*     */     }
/* 218 */     else if (equals(INSIDE1)) {
/* 219 */       result = INSIDE1;
/*     */     }
/* 221 */     else if (equals(INSIDE2)) {
/* 222 */       result = INSIDE2;
/*     */     }
/* 224 */     else if (equals(INSIDE3)) {
/* 225 */       result = INSIDE3;
/*     */     }
/* 227 */     else if (equals(INSIDE4)) {
/* 228 */       result = INSIDE4;
/*     */     }
/* 230 */     else if (equals(INSIDE5)) {
/* 231 */       result = INSIDE5;
/*     */     }
/* 233 */     else if (equals(INSIDE6)) {
/* 234 */       result = INSIDE6;
/*     */     }
/* 236 */     else if (equals(INSIDE7)) {
/* 237 */       result = INSIDE7;
/*     */     }
/* 239 */     else if (equals(INSIDE8)) {
/* 240 */       result = INSIDE8;
/*     */     }
/* 242 */     else if (equals(INSIDE9)) {
/* 243 */       result = INSIDE9;
/*     */     }
/* 245 */     else if (equals(INSIDE10)) {
/* 246 */       result = INSIDE10;
/*     */     }
/* 248 */     else if (equals(INSIDE11)) {
/* 249 */       result = INSIDE11;
/*     */     }
/* 251 */     else if (equals(INSIDE12)) {
/* 252 */       result = INSIDE12;
/*     */     }
/* 254 */     else if (equals(OUTSIDE1)) {
/* 255 */       result = OUTSIDE1;
/*     */     }
/* 257 */     else if (equals(OUTSIDE2)) {
/* 258 */       result = OUTSIDE2;
/*     */     }
/* 260 */     else if (equals(OUTSIDE3)) {
/* 261 */       result = OUTSIDE3;
/*     */     }
/* 263 */     else if (equals(OUTSIDE4)) {
/* 264 */       result = OUTSIDE4;
/*     */     }
/* 266 */     else if (equals(OUTSIDE5)) {
/* 267 */       result = OUTSIDE5;
/*     */     }
/* 269 */     else if (equals(OUTSIDE6)) {
/* 270 */       result = OUTSIDE6;
/*     */     }
/* 272 */     else if (equals(OUTSIDE7)) {
/* 273 */       result = OUTSIDE7;
/*     */     }
/* 275 */     else if (equals(OUTSIDE8)) {
/* 276 */       result = OUTSIDE8;
/*     */     }
/* 278 */     else if (equals(OUTSIDE9)) {
/* 279 */       result = OUTSIDE9;
/*     */     }
/* 281 */     else if (equals(OUTSIDE10)) {
/* 282 */       result = OUTSIDE10;
/*     */     }
/* 284 */     else if (equals(OUTSIDE11)) {
/* 285 */       result = OUTSIDE11;
/*     */     }
/* 287 */     else if (equals(OUTSIDE12)) {
/* 288 */       result = OUTSIDE12;
/*     */     }
/* 290 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/ItemLabelAnchor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */