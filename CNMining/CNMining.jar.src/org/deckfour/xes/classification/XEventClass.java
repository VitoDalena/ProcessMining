/*     */ package org.deckfour.xes.classification;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XEventClass
/*     */   implements Comparable<XEventClass>
/*     */ {
/*     */   protected int index;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String id;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int size;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XEventClass(String id, int index)
/*     */   {
/*  73 */     this.id = id;
/*  74 */     this.index = index;
/*  75 */     this.size = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getId()
/*     */   {
/*  85 */     return this.id;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getIndex()
/*     */   {
/*  93 */     return this.index;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 103 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSize(int size)
/*     */   {
/* 113 */     this.size = size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void incrementSize()
/*     */   {
/* 121 */     this.size += 1;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 125 */     if ((o instanceof XEventClass)) {
/* 126 */       return this.id.equals(((XEventClass)o).id);
/*     */     }
/* 128 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 133 */     return this.id.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 137 */     return this.id;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int compareTo(XEventClass o)
/*     */   {
/* 144 */     return this.id.compareTo(o.getId());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/classification/XEventClass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */