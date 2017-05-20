/*    */ package edu.uci.ics.jung.algorithms.importance;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Ranking<V>
/*    */   implements Comparable
/*    */ {
/*    */   public int originalPos;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public double rankScore;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private V ranked;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Ranking(int originalPos, double rankScore, V ranked)
/*    */   {
/* 39 */     this.originalPos = originalPos;
/* 40 */     this.rankScore = rankScore;
/* 41 */     this.ranked = ranked;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int compareTo(Object o)
/*    */   {
/* 51 */     Ranking otherRanking = (Ranking)o;
/* 52 */     return Double.compare(otherRanking.rankScore, this.rankScore);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String toString()
/*    */   {
/* 61 */     return String.valueOf(this.rankScore);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public V getRanked()
/*    */   {
/* 68 */     return (V)this.ranked;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void setRanked(V ranked)
/*    */   {
/* 75 */     this.ranked = ranked;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/importance/Ranking.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */