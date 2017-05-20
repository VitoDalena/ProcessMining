/*     */ package uk.ac.shef.wit.simmetrics.arbitrators;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.InterfaceStringMetric;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class MeanMetricArbitrator
/*     */   implements InterfaceMetricArbitrator, Serializable
/*     */ {
/*  61 */   private ArrayList<InterfaceStringMetric> metricsForArbitration = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ArrayList<InterfaceStringMetric> getArbitrationMetrics()
/*     */   {
/*  69 */     return this.metricsForArbitration;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setArbitrationMetrics(ArrayList<InterfaceStringMetric> arbitrationMetrics)
/*     */   {
/*  80 */     this.metricsForArbitration = arbitrationMetrics;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addArbitrationMetric(InterfaceStringMetric arbitrationMetric)
/*     */   {
/*  89 */     this.metricsForArbitration.add(arbitrationMetric);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addArbitrationMetrics(ArrayList<InterfaceStringMetric> arbitrationMetrics)
/*     */   {
/* 100 */     this.metricsForArbitration.addAll(arbitrationMetrics);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clearArbitrationMetrics()
/*     */   {
/* 107 */     this.metricsForArbitration.clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getShortDescriptionString()
/*     */   {
/* 116 */     return "MeanMetricArbitrator";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLongDescriptionString()
/*     */   {
/* 125 */     return "MeanMetricArbitrator gives equal weightings too all metrics and returns an arbitrated score for all";
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
/*     */   public long getArbitrationTimingActual(String string1, String string2)
/*     */   {
/* 140 */     long timeBefore = System.currentTimeMillis();
/*     */     
/* 142 */     getArbitrationScore(string1, string2);
/*     */     
/* 144 */     long timeAfter = System.currentTimeMillis();
/*     */     
/* 146 */     return timeAfter - timeBefore;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getArbitrationTimingEstimated(String string1, String string2)
/*     */   {
/* 158 */     float estimatedTime = 0.0F;
/* 159 */     for (Object aMetricsForArbitration : this.metricsForArbitration) {
/* 160 */       estimatedTime += ((InterfaceStringMetric)aMetricsForArbitration).getSimilarityTimingEstimated(string1, string2);
/*     */     }
/* 162 */     return estimatedTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getArbitrationScore(String string1, String string2)
/*     */   {
/* 174 */     float score = 0.0F;
/* 175 */     if (this.metricsForArbitration.size() == 0) {
/* 176 */       return score;
/*     */     }
/* 178 */     for (Object aMetricsForArbitration : this.metricsForArbitration) {
/* 179 */       score += ((InterfaceStringMetric)aMetricsForArbitration).getSimilarity(string1, string2);
/*     */     }
/* 181 */     return score / this.metricsForArbitration.size();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/arbitrators/MeanMetricArbitrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */