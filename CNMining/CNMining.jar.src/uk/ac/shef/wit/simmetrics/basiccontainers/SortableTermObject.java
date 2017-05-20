/*     */ package uk.ac.shef.wit.simmetrics.basiccontainers;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class SortableTermObject
/*     */   implements Comparator, Serializable
/*     */ {
/*     */   private final String term;
/*     */   private final int frequency;
/*     */   
/*     */   public SortableTermObject(String termToSet, int frequencyToSet)
/*     */   {
/*  72 */     this.term = termToSet;
/*  73 */     this.frequency = frequencyToSet;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getFrequency()
/*     */   {
/*  81 */     return this.frequency;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getTerm()
/*     */   {
/*  89 */     return this.term;
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
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 105 */     if (((SortableTermObject)o1).frequency > ((SortableTermObject)o2).frequency)
/* 106 */       return 1;
/* 107 */     if (((SortableTermObject)o1).frequency < ((SortableTermObject)o2).frequency) {
/* 108 */       return -1;
/*     */     }
/* 110 */     return 0;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/basiccontainers/SortableTermObject.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */