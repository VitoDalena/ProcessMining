/*     */ package org.apache.commons.math.random;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.EOFException;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Serializable;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.math.stat.descriptive.StatisticalSummary;
/*     */ import org.apache.commons.math.stat.descriptive.SummaryStatistics;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EmpiricalDistributionImpl
/*     */   implements Serializable, EmpiricalDistribution
/*     */ {
/*     */   private static final long serialVersionUID = -6773236347582113490L;
/*  67 */   private ArrayList binStats = null;
/*     */   
/*     */ 
/*  70 */   private SummaryStatistics sampleStats = null;
/*     */   
/*     */ 
/*  73 */   private int binCount = 1000;
/*     */   
/*     */ 
/*  76 */   private boolean loaded = false;
/*     */   
/*     */ 
/*  79 */   private double[] upperBounds = null;
/*     */   
/*     */ 
/*  82 */   private RandomData randomData = new RandomDataImpl();
/*     */   
/*     */ 
/*     */ 
/*     */   public EmpiricalDistributionImpl()
/*     */   {
/*  88 */     this.binStats = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public EmpiricalDistributionImpl(int binCount)
/*     */   {
/*  97 */     this.binCount = binCount;
/*  98 */     this.binStats = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void load(double[] in)
/*     */   {
/* 108 */     DataAdapter da = new ArrayDataAdapter(in);
/*     */     try {
/* 110 */       da.computeStats();
/* 111 */       fillBinStats(in);
/*     */     } catch (Exception e) {
/* 113 */       throw new RuntimeException(e.getMessage());
/*     */     }
/* 115 */     this.loaded = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void load(URL url)
/*     */     throws IOException
/*     */   {
/* 126 */     BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
/*     */     try
/*     */     {
/* 129 */       DataAdapter da = new StreamDataAdapter(in);
/*     */       try {
/* 131 */         da.computeStats();
/*     */       } catch (Exception e) {
/* 133 */         throw new IOException(e.getMessage());
/*     */       }
/* 135 */       if (this.sampleStats.getN() == 0L) {
/* 136 */         throw new EOFException("URL " + url + " contains no data");
/*     */       }
/* 138 */       in = new BufferedReader(new InputStreamReader(url.openStream()));
/* 139 */       fillBinStats(in);
/* 140 */       this.loaded = true; return;
/*     */     } finally {
/* 142 */       if (in != null) {
/*     */         try {
/* 144 */           in.close();
/*     */         }
/*     */         catch (Exception ex) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void load(File file)
/*     */     throws IOException
/*     */   {
/* 159 */     BufferedReader in = new BufferedReader(new FileReader(file));
/*     */     try {
/* 161 */       DataAdapter da = new StreamDataAdapter(in);
/*     */       try {
/* 163 */         da.computeStats();
/*     */       } catch (Exception e) {
/* 165 */         throw new IOException(e.getMessage());
/*     */       }
/* 167 */       in = new BufferedReader(new FileReader(file));
/* 168 */       fillBinStats(in);
/* 169 */       this.loaded = true; return;
/*     */     } finally {
/* 171 */       if (in != null)
/*     */         try {
/* 173 */           in.close();
/*     */         } catch (Exception ex) {}
/*     */     }
/*     */   }
/*     */   
/*     */   private abstract class DataAdapter {
/*     */     private DataAdapter() {}
/*     */     
/*     */     public abstract void computeBinStats(double paramDouble1, double paramDouble2) throws Exception;
/*     */     
/*     */     public abstract void computeStats() throws Exception;
/*     */     
/* 185 */     DataAdapter(EmpiricalDistributionImpl.1 x1) { this(); }
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
/*     */ 
/*     */ 
/*     */   private class DataAdapterFactory
/*     */   {
/*     */     DataAdapterFactory(EmpiricalDistributionImpl.1 x1)
/*     */     {
/* 207 */       this();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public EmpiricalDistributionImpl.DataAdapter getAdapter(Object in)
/*     */     {
/* 215 */       if ((in instanceof BufferedReader)) {
/* 216 */         BufferedReader inputStream = (BufferedReader)in;
/* 217 */         return new EmpiricalDistributionImpl.StreamDataAdapter(EmpiricalDistributionImpl.this, inputStream); }
/* 218 */       if ((in instanceof double[])) {
/* 219 */         double[] inputArray = (double[])in;
/* 220 */         return new EmpiricalDistributionImpl.ArrayDataAdapter(EmpiricalDistributionImpl.this, inputArray);
/*     */       }
/* 222 */       throw new IllegalArgumentException("Input data comes from the unsupported source");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     private DataAdapterFactory() {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private class StreamDataAdapter
/*     */     extends EmpiricalDistributionImpl.DataAdapter
/*     */   {
/*     */     private BufferedReader inputStream;
/*     */     
/*     */ 
/*     */ 
/*     */     public StreamDataAdapter(BufferedReader in)
/*     */     {
/* 241 */       super(null);
/* 242 */       this.inputStream = in;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void computeBinStats(double min, double delta)
/*     */       throws IOException
/*     */     {
/* 253 */       String str = null;
/* 254 */       double val = 0.0D;
/* 255 */       while ((str = this.inputStream.readLine()) != null) {
/* 256 */         val = Double.parseDouble(str);
/* 257 */         SummaryStatistics stats = (SummaryStatistics)EmpiricalDistributionImpl.this.binStats.get(EmpiricalDistributionImpl.this.findBin(min, val, delta));
/*     */         
/* 259 */         stats.addValue(val);
/*     */       }
/*     */       
/* 262 */       this.inputStream.close();
/* 263 */       this.inputStream = null;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public void computeStats()
/*     */       throws IOException
/*     */     {
/* 271 */       String str = null;
/* 272 */       double val = 0.0D;
/* 273 */       EmpiricalDistributionImpl.this.sampleStats = new SummaryStatistics();
/* 274 */       while ((str = this.inputStream.readLine()) != null) {
/* 275 */         val = new Double(str).doubleValue();
/* 276 */         EmpiricalDistributionImpl.this.sampleStats.addValue(val);
/*     */       }
/* 278 */       this.inputStream.close();
/* 279 */       this.inputStream = null;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private class ArrayDataAdapter
/*     */     extends EmpiricalDistributionImpl.DataAdapter
/*     */   {
/*     */     private double[] inputArray;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public ArrayDataAdapter(double[] in)
/*     */     {
/* 297 */       super(null);
/* 298 */       this.inputArray = in;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public void computeStats()
/*     */       throws IOException
/*     */     {
/* 306 */       EmpiricalDistributionImpl.this.sampleStats = new SummaryStatistics();
/* 307 */       for (int i = 0; i < this.inputArray.length; i++) {
/* 308 */         EmpiricalDistributionImpl.this.sampleStats.addValue(this.inputArray[i]);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void computeBinStats(double min, double delta)
/*     */       throws IOException
/*     */     {
/* 320 */       for (int i = 0; i < this.inputArray.length; i++) {
/* 321 */         SummaryStatistics stats = (SummaryStatistics)EmpiricalDistributionImpl.this.binStats.get(EmpiricalDistributionImpl.this.findBin(min, this.inputArray[i], delta));
/*     */         
/*     */ 
/* 324 */         stats.addValue(this.inputArray[i]);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void fillBinStats(Object in)
/*     */     throws IOException
/*     */   {
/* 337 */     double min = this.sampleStats.getMin();
/* 338 */     double max = this.sampleStats.getMax();
/* 339 */     double delta = (max - min) / new Double(this.binCount).doubleValue();
/* 340 */     double[] binUpperBounds = new double[this.binCount];
/* 341 */     binUpperBounds[0] = (min + delta);
/* 342 */     for (int i = 1; i < this.binCount - 1; i++) {
/* 343 */       binUpperBounds[i] = (binUpperBounds[(i - 1)] + delta);
/*     */     }
/* 345 */     binUpperBounds[(this.binCount - 1)] = max;
/*     */     
/*     */ 
/* 348 */     if (!this.binStats.isEmpty()) {
/* 349 */       this.binStats.clear();
/*     */     }
/* 351 */     for (int i = 0; i < this.binCount; i++) {
/* 352 */       SummaryStatistics stats = new SummaryStatistics();
/* 353 */       this.binStats.add(i, stats);
/*     */     }
/*     */     
/*     */ 
/* 357 */     DataAdapterFactory aFactory = new DataAdapterFactory(null);
/* 358 */     DataAdapter da = aFactory.getAdapter(in);
/*     */     try {
/* 360 */       da.computeBinStats(min, delta);
/*     */     } catch (Exception e) {
/* 362 */       if ((e instanceof RuntimeException)) {
/* 363 */         throw new RuntimeException(e.getMessage());
/*     */       }
/* 365 */       throw new IOException(e.getMessage());
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 370 */     this.upperBounds = new double[this.binCount];
/* 371 */     this.upperBounds[0] = (((SummaryStatistics)this.binStats.get(0)).getN() / this.sampleStats.getN());
/*     */     
/*     */ 
/* 374 */     for (int i = 1; i < this.binCount - 1; i++) {
/* 375 */       this.upperBounds[i] = (this.upperBounds[(i - 1)] + ((SummaryStatistics)this.binStats.get(i)).getN() / this.sampleStats.getN());
/*     */     }
/*     */     
/*     */ 
/* 379 */     this.upperBounds[(this.binCount - 1)] = 1.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int findBin(double min, double value, double delta)
/*     */   {
/* 391 */     return Math.min(Math.max((int)Math.ceil((value - min) / delta) - 1, 0), this.binCount - 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getNextValue()
/*     */     throws IllegalStateException
/*     */   {
/* 404 */     if (!this.loaded) {
/* 405 */       throw new IllegalStateException("distribution not loaded");
/*     */     }
/*     */     
/*     */ 
/* 409 */     double x = Math.random();
/*     */     
/*     */ 
/* 412 */     for (int i = 0; i < this.binCount; i++) {
/* 413 */       if (x <= this.upperBounds[i]) {
/* 414 */         SummaryStatistics stats = (SummaryStatistics)this.binStats.get(i);
/* 415 */         if (stats.getN() > 0L) {
/* 416 */           if (stats.getStandardDeviation() > 0.0D) {
/* 417 */             return this.randomData.nextGaussian(stats.getMean(), stats.getStandardDeviation());
/*     */           }
/*     */           
/* 420 */           return stats.getMean();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 425 */     throw new RuntimeException("No bin selected");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StatisticalSummary getSampleStats()
/*     */   {
/* 437 */     return this.sampleStats;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getBinCount()
/*     */   {
/* 446 */     return this.binCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getBinStats()
/*     */   {
/* 457 */     return this.binStats;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] getUpperBounds()
/*     */   {
/* 469 */     int len = this.upperBounds.length;
/* 470 */     double[] out = new double[len];
/* 471 */     System.arraycopy(this.upperBounds, 0, out, 0, len);
/* 472 */     return out;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLoaded()
/*     */   {
/* 481 */     return this.loaded;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/random/EmpiricalDistributionImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */