/*     */ package edu.uci.ics.jung.algorithms.util;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DiscreteDistribution
/*     */ {
/*     */   public static double KullbackLeibler(double[] dist, double[] reference)
/*     */   {
/*  40 */     double distance = 0.0D;
/*     */     
/*  42 */     checkLengths(dist, reference);
/*     */     
/*  44 */     for (int i = 0; i < dist.length; i++)
/*     */     {
/*  46 */       if ((dist[i] > 0.0D) && (reference[i] > 0.0D))
/*  47 */         distance += dist[i] * Math.log(dist[i] / reference[i]);
/*     */     }
/*  49 */     return distance;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double symmetricKL(double[] dist, double[] reference)
/*     */   {
/*  58 */     return KullbackLeibler(dist, reference) + KullbackLeibler(reference, dist);
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
/*     */   public static double squaredError(double[] dist, double[] reference)
/*     */   {
/*  71 */     double error = 0.0D;
/*     */     
/*  73 */     checkLengths(dist, reference);
/*     */     
/*  75 */     for (int i = 0; i < dist.length; i++)
/*     */     {
/*  77 */       double difference = dist[i] - reference[i];
/*  78 */       error += difference * difference;
/*     */     }
/*  80 */     return error;
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
/*     */ 
/*     */ 
/*     */   public static double cosine(double[] dist, double[] reference)
/*     */   {
/*  98 */     double v_prod = 0.0D;
/*  99 */     double w_prod = 0.0D;
/* 100 */     double vw_prod = 0.0D;
/*     */     
/* 102 */     checkLengths(dist, reference);
/*     */     
/* 104 */     for (int i = 0; i < dist.length; i++)
/*     */     {
/* 106 */       vw_prod += dist[i] * reference[i];
/* 107 */       v_prod += dist[i] * dist[i];
/* 108 */       w_prod += reference[i] * reference[i];
/*     */     }
/*     */     
/* 111 */     return vw_prod / (Math.sqrt(v_prod) * Math.sqrt(w_prod));
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
/*     */   public static double entropy(double[] dist)
/*     */   {
/* 126 */     double total = 0.0D;
/*     */     
/* 128 */     for (int i = 0; i < dist.length; i++)
/*     */     {
/* 130 */       if (dist[i] > 0.0D)
/* 131 */         total += dist[i] * Math.log(dist[i]);
/*     */     }
/* 133 */     return -total;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static void checkLengths(double[] dist, double[] reference)
/*     */   {
/* 141 */     if (dist.length != reference.length) {
/* 142 */       throw new IllegalArgumentException("Arrays must be of the same length");
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
/*     */   public static void normalize(double[] counts, double alpha)
/*     */   {
/* 156 */     double total_count = 0.0D;
/*     */     
/* 158 */     for (int i = 0; i < counts.length; i++) {
/* 159 */       total_count += counts[i];
/*     */     }
/* 161 */     for (int i = 0; i < counts.length; i++) {
/* 162 */       counts[i] = ((counts[i] + alpha) / (total_count + counts.length * alpha));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double[] mean(Collection<double[]> distributions)
/*     */   {
/* 174 */     if (distributions.isEmpty())
/* 175 */       throw new IllegalArgumentException("Distribution collection must be non-empty");
/* 176 */     Iterator<double[]> iter = distributions.iterator();
/* 177 */     double[] first = (double[])iter.next();
/* 178 */     double[][] d_array = new double[distributions.size()][first.length];
/* 179 */     d_array[0] = first;
/* 180 */     for (int i = 1; i < d_array.length; i++) {
/* 181 */       d_array[i] = ((double[])iter.next());
/*     */     }
/* 183 */     return mean(d_array);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double[] mean(double[][] distributions)
/*     */   {
/* 194 */     double[] d_mean = new double[distributions[0].length];
/* 195 */     for (int j = 0; j < d_mean.length; j++) {
/* 196 */       d_mean[j] = 0.0D;
/*     */     }
/* 198 */     for (int i = 0; i < distributions.length; i++) {
/* 199 */       for (int j = 0; j < d_mean.length; j++)
/* 200 */         d_mean[j] += distributions[i][j] / distributions.length;
/*     */     }
/* 202 */     return d_mean;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/util/DiscreteDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */