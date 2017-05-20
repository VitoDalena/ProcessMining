/*     */ package hep.aida.bin;
/*     */ 
/*     */ import cern.colt.list.AbstractDoubleList;
/*     */ import cern.colt.list.DoubleArrayList;
/*     */ import cern.jet.random.engine.DRand;
/*     */ import cern.jet.stat.quantile.DoubleQuantileFinder;
/*     */ import cern.jet.stat.quantile.QuantileFinderFactory;
/*     */ import edu.cornell.lassp.houle.RngPack.RandomElement;
/*     */ import hep.aida.IAxis;
/*     */ import hep.aida.ref.Converter;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QuantileBin1D
/*     */   extends MightyStaticBin1D
/*     */ {
/* 557 */   protected DoubleQuantileFinder finder = null;
/*     */   
/*     */ 
/*     */ 
/*     */   protected QuantileBin1D()
/*     */   {
/* 563 */     super(false, false, 2);
/*     */   }
/*     */   
/*     */ 
/*     */   public QuantileBin1D(double paramDouble)
/*     */   {
/* 569 */     this(false, Long.MAX_VALUE, paramDouble, 0.001D, 10000, new DRand(new Date()));
/*     */   }
/*     */   
/*     */ 
/*     */   public QuantileBin1D(boolean paramBoolean, long paramLong, double paramDouble1, double paramDouble2, int paramInt, RandomElement paramRandomElement)
/*     */   {
/* 575 */     this(paramBoolean, paramLong, paramDouble1, paramDouble2, paramInt, paramRandomElement, false, false, 2);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public QuantileBin1D(boolean paramBoolean1, long paramLong, double paramDouble1, double paramDouble2, int paramInt1, RandomElement paramRandomElement, boolean paramBoolean2, boolean paramBoolean3, int paramInt2)
/*     */   {
/* 625 */     super(paramBoolean2, paramBoolean3, paramInt2);
/* 626 */     this.finder = QuantileFinderFactory.newDoubleQuantileFinder(paramBoolean1, paramLong, paramDouble1, paramDouble2, paramInt1, paramRandomElement);
/* 627 */     clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void addAllOfFromTo(DoubleArrayList paramDoubleArrayList, int paramInt1, int paramInt2)
/*     */   {
/* 638 */     super.addAllOfFromTo(paramDoubleArrayList, paramInt1, paramInt2);
/* 639 */     if (this.finder != null) { this.finder.addAllOfFromTo(paramDoubleArrayList, paramInt1, paramInt2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void clear()
/*     */   {
/* 646 */     super.clear();
/* 647 */     if (this.finder != null) { this.finder.clear();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized Object clone()
/*     */   {
/* 655 */     QuantileBin1D localQuantileBin1D = (QuantileBin1D)super.clone();
/* 656 */     if (this.finder != null) localQuantileBin1D.finder = ((DoubleQuantileFinder)localQuantileBin1D.finder.clone());
/* 657 */     return localQuantileBin1D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String compareWith(AbstractBin1D paramAbstractBin1D)
/*     */   {
/* 665 */     StringBuffer localStringBuffer = new StringBuffer(super.compareWith(paramAbstractBin1D));
/* 666 */     if ((paramAbstractBin1D instanceof QuantileBin1D)) {
/* 667 */       QuantileBin1D localQuantileBin1D = (QuantileBin1D)paramAbstractBin1D;
/* 668 */       localStringBuffer.append("25%, 50% and 75% Quantiles: " + relError(quantile(0.25D), localQuantileBin1D.quantile(0.25D)) + ", " + relError(quantile(0.5D), localQuantileBin1D.quantile(0.5D)) + ", " + relError(quantile(0.75D), localQuantileBin1D.quantile(0.75D)));
/* 669 */       localStringBuffer.append("\nquantileInverse(mean): " + relError(quantileInverse(mean()), localQuantileBin1D.quantileInverse(localQuantileBin1D.mean())) + " %");
/* 670 */       localStringBuffer.append("\n");
/*     */     }
/* 672 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */   public double median()
/*     */   {
/* 678 */     return quantile(0.5D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized double quantile(double paramDouble)
/*     */   {
/* 687 */     return quantiles(new DoubleArrayList(new double[] { paramDouble })).get(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized double quantileInverse(double paramDouble)
/*     */   {
/* 697 */     return this.finder.phi(paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized DoubleArrayList quantiles(DoubleArrayList paramDoubleArrayList)
/*     */   {
/* 707 */     return this.finder.quantileElements(paramDoubleArrayList);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int sizeOfRange(double paramDouble1, double paramDouble2)
/*     */   {
/* 719 */     return (int)Math.round(size() * (quantileInverse(paramDouble2) - quantileInverse(paramDouble1)));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized MightyStaticBin1D[] splitApproximately(DoubleArrayList paramDoubleArrayList, int paramInt)
/*     */   {
/* 831 */     int i = paramDoubleArrayList.size();
/* 832 */     if ((paramInt < 1) || (i < 2)) { throw new IllegalArgumentException();
/*     */     }
/* 834 */     double[] arrayOfDouble1 = paramDoubleArrayList.elements();
/* 835 */     int j = i - 1;
/*     */     
/*     */ 
/*     */ 
/* 839 */     double[] arrayOfDouble2 = new double[1 + paramInt * (i - 1)];
/* 840 */     arrayOfDouble2[0] = arrayOfDouble1[0];
/* 841 */     int k = 1;
/*     */     
/* 843 */     for (int m = 0; m < j; m++) {
/* 844 */       double d1 = (arrayOfDouble1[(m + 1)] - arrayOfDouble1[m]) / paramInt;
/* 845 */       for (n = 1; n <= paramInt; n++) {
/* 846 */         arrayOfDouble2[(k++)] = (arrayOfDouble1[m] + n * d1);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 851 */     double[] arrayOfDouble3 = quantiles(new DoubleArrayList(arrayOfDouble2)).elements();
/*     */     
/*     */ 
/*     */ 
/* 855 */     MightyStaticBin1D[] arrayOfMightyStaticBin1D = new MightyStaticBin1D[j];
/* 856 */     int n = getMaxOrderForSumOfPowers();
/* 857 */     n = Math.min(10, n);
/*     */     
/* 859 */     int i1 = size();
/* 860 */     k = 0;
/* 861 */     for (int i2 = 0; i2 < j; i2++) {
/* 862 */       double d2 = (arrayOfDouble1[(i2 + 1)] - arrayOfDouble1[i2]) / paramInt;
/* 863 */       double d3 = 0.0D;
/* 864 */       double d4 = 0.0D;
/* 865 */       double d5 = 0.0D;
/* 866 */       double d6 = 0.0D;
/* 867 */       double[] arrayOfDouble4 = null;
/* 868 */       if (n > 2) {
/* 869 */         arrayOfDouble4 = new double[n - 2];
/*     */       }
/*     */       
/* 872 */       double d7 = arrayOfDouble3[(k++)];
/* 873 */       double d8 = d7;
/* 874 */       double d9 = i1 * d2;
/*     */       
/* 876 */       for (int i3 = 1; i3 <= paramInt; i3++) {
/* 877 */         double d10 = arrayOfDouble3[(k++)];
/* 878 */         double d12 = (d7 + d10) / 2.0D;
/* 879 */         d3 += d12 * d9;
/* 880 */         d4 += d12 * d12 * d9;
/* 881 */         if (this.hasSumOfLogarithms) {
/* 882 */           d5 += Math.log(d12) * d9;
/*     */         }
/* 884 */         if (this.hasSumOfInversions) {
/* 885 */           d6 += 1.0D / d12 * d9;
/*     */         }
/* 887 */         if (n >= 3) arrayOfDouble4[0] += d12 * d12 * d12 * d9;
/* 888 */         if (n >= 4) arrayOfDouble4[1] += d12 * d12 * d12 * d12 * d9;
/* 889 */         for (int i5 = 5; i5 <= n; i5++) {
/* 890 */           arrayOfDouble4[(i5 - 3)] += Math.pow(d12, i5) * d9;
/*     */         }
/*     */         
/* 893 */         d7 = d10;
/*     */       }
/* 895 */       k--;
/*     */       
/*     */ 
/* 898 */       int i4 = (int)Math.round((arrayOfDouble1[(i2 + 1)] - arrayOfDouble1[i2]) * i1);
/* 899 */       double d11 = d7;
/* 900 */       d7 = d8;
/*     */       
/*     */ 
/* 903 */       arrayOfMightyStaticBin1D[i2] = new MightyStaticBin1D(this.hasSumOfLogarithms, this.hasSumOfInversions, n);
/* 904 */       if (i4 > 0) {
/* 905 */         arrayOfMightyStaticBin1D[i2].size = i4;
/* 906 */         arrayOfMightyStaticBin1D[i2].min = d7;
/* 907 */         arrayOfMightyStaticBin1D[i2].max = d11;
/* 908 */         arrayOfMightyStaticBin1D[i2].sum = d3;
/* 909 */         arrayOfMightyStaticBin1D[i2].sum_xx = d4;
/* 910 */         arrayOfMightyStaticBin1D[i2].sumOfLogarithms = d5;
/* 911 */         arrayOfMightyStaticBin1D[i2].sumOfInversions = d6;
/* 912 */         arrayOfMightyStaticBin1D[i2].sumOfPowers = arrayOfDouble4;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 927 */     return arrayOfMightyStaticBin1D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized MightyStaticBin1D[] splitApproximately(IAxis paramIAxis, int paramInt)
/*     */   {
/* 939 */     DoubleArrayList localDoubleArrayList = new DoubleArrayList(new Converter().edges(paramIAxis));
/* 940 */     localDoubleArrayList.beforeInsert(0, Double.NEGATIVE_INFINITY);
/* 941 */     localDoubleArrayList.add(Double.POSITIVE_INFINITY);
/* 942 */     int i = localDoubleArrayList.size();
/* 943 */     do { localDoubleArrayList.set(i, quantileInverse(localDoubleArrayList.get(i)));i--;
/* 942 */     } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/* 946 */     return splitApproximately(localDoubleArrayList, paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized String toString()
/*     */   {
/* 952 */     StringBuffer localStringBuffer = new StringBuffer(super.toString());
/* 953 */     localStringBuffer.append("25%, 50%, 75% Quantiles: " + quantile(0.25D) + ", " + quantile(0.5D) + ", " + quantile(0.75D));
/*     */     
/* 955 */     localStringBuffer.append("\nquantileInverse(median): " + quantileInverse(median()));
/* 956 */     localStringBuffer.append("\n");
/* 957 */     return localStringBuffer.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/hep/aida/bin/QuantileBin1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */