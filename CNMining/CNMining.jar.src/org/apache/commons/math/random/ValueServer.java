/*     */ package org.apache.commons.math.random;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.List;
/*     */ import org.apache.commons.math.stat.descriptive.StatisticalSummary;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ValueServer
/*     */ {
/*  49 */   private int mode = 5;
/*     */   
/*     */ 
/*  52 */   private URL valuesFileURL = null;
/*     */   
/*     */ 
/*  55 */   private double mu = 0.0D;
/*     */   
/*     */ 
/*  58 */   private double sigma = 0.0D;
/*     */   
/*     */ 
/*  61 */   private EmpiricalDistribution empiricalDistribution = null;
/*     */   
/*     */ 
/*  64 */   private BufferedReader filePointer = null;
/*     */   
/*     */ 
/*  67 */   private RandomData randomData = new RandomDataImpl();
/*     */   
/*     */ 
/*     */ 
/*     */   public static final int DIGEST_MODE = 0;
/*     */   
/*     */ 
/*     */ 
/*     */   public static final int REPLAY_MODE = 1;
/*     */   
/*     */ 
/*     */ 
/*     */   public static final int UNIFORM_MODE = 2;
/*     */   
/*     */ 
/*     */ 
/*     */   public static final int EXPONENTIAL_MODE = 3;
/*     */   
/*     */ 
/*     */ 
/*     */   public static final int GAUSSIAN_MODE = 4;
/*     */   
/*     */ 
/*     */   public static final int CONSTANT_MODE = 5;
/*     */   
/*     */ 
/*     */ 
/*     */   public ValueServer() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public double getNext()
/*     */     throws IOException
/*     */   {
/* 101 */     switch (this.mode) {
/* 102 */     case 0:  return getNextDigest();
/* 103 */     case 1:  return getNextReplay();
/* 104 */     case 2:  return getNextUniform();
/* 105 */     case 3:  return getNextExponential();
/* 106 */     case 4:  return getNextGaussian();
/* 107 */     case 5:  return this.mu; }
/* 108 */     throw new IllegalStateException("Bad mode: " + this.mode);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void fill(double[] values)
/*     */     throws IOException
/*     */   {
/* 120 */     for (int i = 0; i < values.length; i++) {
/* 121 */       values[i] = getNext();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] fill(int length)
/*     */     throws IOException
/*     */   {
/* 134 */     double[] out = new double[length];
/* 135 */     for (int i = 0; i < length; i++) {
/* 136 */       out[i] = getNext();
/*     */     }
/* 138 */     return out;
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
/*     */   public void computeDistribution()
/*     */     throws IOException
/*     */   {
/* 154 */     this.empiricalDistribution = new EmpiricalDistributionImpl();
/* 155 */     this.empiricalDistribution.load(this.valuesFileURL);
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
/*     */   public void computeDistribution(int binCount)
/*     */     throws IOException
/*     */   {
/* 174 */     this.empiricalDistribution = new EmpiricalDistributionImpl(binCount);
/* 175 */     this.empiricalDistribution.load(this.valuesFileURL);
/* 176 */     this.mu = this.empiricalDistribution.getSampleStats().getMean();
/* 177 */     this.sigma = this.empiricalDistribution.getSampleStats().getStandardDeviation();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getMode()
/*     */   {
/* 184 */     return this.mode;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setMode(int mode)
/*     */   {
/* 191 */     this.mode = mode;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public URL getValuesFileURL()
/*     */   {
/* 199 */     return this.valuesFileURL;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValuesFileURL(String url)
/*     */     throws MalformedURLException
/*     */   {
/* 208 */     this.valuesFileURL = new URL(url);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValuesFileURL(URL url)
/*     */   {
/* 216 */     this.valuesFileURL = url;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public EmpiricalDistribution getEmpiricalDistribution()
/*     */   {
/* 223 */     return this.empiricalDistribution;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void resetReplayFile()
/*     */     throws IOException
/*     */   {
/* 232 */     if (this.filePointer != null) {
/*     */       try {
/* 234 */         this.filePointer.close();
/* 235 */         this.filePointer = null;
/*     */       }
/*     */       catch (IOException ex) {}
/*     */     }
/*     */     
/* 240 */     this.filePointer = new BufferedReader(new InputStreamReader(this.valuesFileURL.openStream()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void closeReplayFile()
/*     */     throws IOException
/*     */   {
/* 249 */     if (this.filePointer != null) {
/* 250 */       this.filePointer.close();
/* 251 */       this.filePointer = null;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getMu()
/*     */   {
/* 259 */     return this.mu;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setMu(double mu)
/*     */   {
/* 266 */     this.mu = mu;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getSigma()
/*     */   {
/* 273 */     return this.sigma;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setSigma(double sigma)
/*     */   {
/* 280 */     this.sigma = sigma;
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
/*     */   private double getNextDigest()
/*     */   {
/* 296 */     if ((this.empiricalDistribution == null) || (this.empiricalDistribution.getBinStats().size() == 0))
/*     */     {
/* 298 */       throw new IllegalStateException("Digest not initialized");
/*     */     }
/* 300 */     return this.empiricalDistribution.getNextValue();
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
/*     */ 
/*     */   private double getNextReplay()
/*     */     throws IOException
/*     */   {
/* 322 */     String str = null;
/* 323 */     if (this.filePointer == null) {
/* 324 */       resetReplayFile();
/*     */     }
/* 326 */     if ((str = this.filePointer.readLine()) == null)
/*     */     {
/* 328 */       closeReplayFile();
/* 329 */       resetReplayFile();
/* 330 */       if ((str = this.filePointer.readLine()) == null) {
/* 331 */         throw new EOFException("URL " + this.valuesFileURL + " contains no data");
/*     */       }
/*     */     }
/* 334 */     return Double.valueOf(str).doubleValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double getNextUniform()
/*     */   {
/* 343 */     return this.randomData.nextUniform(0.0D, 2.0D * this.mu);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double getNextExponential()
/*     */   {
/* 352 */     return this.randomData.nextExponential(this.mu);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double getNextGaussian()
/*     */   {
/* 362 */     return this.randomData.nextGaussian(this.mu, this.sigma);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ValueServer(RandomData randomData)
/*     */   {
/* 374 */     this.randomData = randomData;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/random/ValueServer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */