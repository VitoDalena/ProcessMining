/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Regression
/*     */ {
/*     */   public static double[] getOLSRegression(double[][] data)
/*     */   {
/*  64 */     int n = data.length;
/*  65 */     if (n < 2) {
/*  66 */       throw new IllegalArgumentException("Not enough data.");
/*     */     }
/*     */     
/*  69 */     double sumX = 0.0D;
/*  70 */     double sumY = 0.0D;
/*  71 */     double sumXX = 0.0D;
/*  72 */     double sumXY = 0.0D;
/*  73 */     for (int i = 0; i < n; i++) {
/*  74 */       double x = data[i][0];
/*  75 */       double y = data[i][1];
/*  76 */       sumX += x;
/*  77 */       sumY += y;
/*  78 */       double xx = x * x;
/*  79 */       sumXX += xx;
/*  80 */       double xy = x * y;
/*  81 */       sumXY += xy;
/*     */     }
/*  83 */     double sxx = sumXX - sumX * sumX / n;
/*  84 */     double sxy = sumXY - sumX * sumY / n;
/*  85 */     double xbar = sumX / n;
/*  86 */     double ybar = sumY / n;
/*     */     
/*  88 */     double[] result = new double[2];
/*  89 */     result[1] = (sxy / sxx);
/*  90 */     result[0] = (ybar - result[1] * xbar);
/*     */     
/*  92 */     return result;
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
/*     */   public static double[] getOLSRegression(XYDataset data, int series)
/*     */   {
/* 108 */     int n = data.getItemCount(series);
/* 109 */     if (n < 2) {
/* 110 */       throw new IllegalArgumentException("Not enough data.");
/*     */     }
/*     */     
/* 113 */     double sumX = 0.0D;
/* 114 */     double sumY = 0.0D;
/* 115 */     double sumXX = 0.0D;
/* 116 */     double sumXY = 0.0D;
/* 117 */     for (int i = 0; i < n; i++) {
/* 118 */       double x = data.getXValue(series, i);
/* 119 */       double y = data.getYValue(series, i);
/* 120 */       sumX += x;
/* 121 */       sumY += y;
/* 122 */       double xx = x * x;
/* 123 */       sumXX += xx;
/* 124 */       double xy = x * y;
/* 125 */       sumXY += xy;
/*     */     }
/* 127 */     double sxx = sumXX - sumX * sumX / n;
/* 128 */     double sxy = sumXY - sumX * sumY / n;
/* 129 */     double xbar = sumX / n;
/* 130 */     double ybar = sumY / n;
/*     */     
/* 132 */     double[] result = new double[2];
/* 133 */     result[1] = (sxy / sxx);
/* 134 */     result[0] = (ybar - result[1] * xbar);
/*     */     
/* 136 */     return result;
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
/*     */   public static double[] getPowerRegression(double[][] data)
/*     */   {
/* 151 */     int n = data.length;
/* 152 */     if (n < 2) {
/* 153 */       throw new IllegalArgumentException("Not enough data.");
/*     */     }
/*     */     
/* 156 */     double sumX = 0.0D;
/* 157 */     double sumY = 0.0D;
/* 158 */     double sumXX = 0.0D;
/* 159 */     double sumXY = 0.0D;
/* 160 */     for (int i = 0; i < n; i++) {
/* 161 */       double x = Math.log(data[i][0]);
/* 162 */       double y = Math.log(data[i][1]);
/* 163 */       sumX += x;
/* 164 */       sumY += y;
/* 165 */       double xx = x * x;
/* 166 */       sumXX += xx;
/* 167 */       double xy = x * y;
/* 168 */       sumXY += xy;
/*     */     }
/* 170 */     double sxx = sumXX - sumX * sumX / n;
/* 171 */     double sxy = sumXY - sumX * sumY / n;
/* 172 */     double xbar = sumX / n;
/* 173 */     double ybar = sumY / n;
/*     */     
/* 175 */     double[] result = new double[2];
/* 176 */     result[1] = (sxy / sxx);
/* 177 */     result[0] = Math.pow(Math.exp(1.0D), ybar - result[1] * xbar);
/*     */     
/* 179 */     return result;
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
/*     */   public static double[] getPowerRegression(XYDataset data, int series)
/*     */   {
/* 195 */     int n = data.getItemCount(series);
/* 196 */     if (n < 2) {
/* 197 */       throw new IllegalArgumentException("Not enough data.");
/*     */     }
/*     */     
/* 200 */     double sumX = 0.0D;
/* 201 */     double sumY = 0.0D;
/* 202 */     double sumXX = 0.0D;
/* 203 */     double sumXY = 0.0D;
/* 204 */     for (int i = 0; i < n; i++) {
/* 205 */       double x = Math.log(data.getXValue(series, i));
/* 206 */       double y = Math.log(data.getYValue(series, i));
/* 207 */       sumX += x;
/* 208 */       sumY += y;
/* 209 */       double xx = x * x;
/* 210 */       sumXX += xx;
/* 211 */       double xy = x * y;
/* 212 */       sumXY += xy;
/*     */     }
/* 214 */     double sxx = sumXX - sumX * sumX / n;
/* 215 */     double sxy = sumXY - sumX * sumY / n;
/* 216 */     double xbar = sumX / n;
/* 217 */     double ybar = sumY / n;
/*     */     
/* 219 */     double[] result = new double[2];
/* 220 */     result[1] = (sxy / sxx);
/* 221 */     result[0] = Math.pow(Math.exp(1.0D), ybar - result[1] * xbar);
/*     */     
/* 223 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/statistics/Regression.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */