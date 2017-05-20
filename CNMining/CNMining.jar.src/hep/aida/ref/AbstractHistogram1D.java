/*    */ package hep.aida.ref;
/*    */ 
/*    */ import hep.aida.IAxis;
/*    */ import hep.aida.IHistogram1D;
/*    */ 
/*    */ 
/*    */ abstract class AbstractHistogram1D
/*    */   extends Histogram
/*    */   implements IHistogram1D
/*    */ {
/*    */   protected IAxis xAxis;
/*    */   
/*    */   AbstractHistogram1D(String paramString)
/*    */   {
/* 15 */     super(paramString);
/*    */   }
/*    */   
/*    */   public int allEntries() {
/* 19 */     return entries() + extraEntries();
/*    */   }
/*    */   
/*    */   public int dimensions() {
/* 23 */     return 1;
/*    */   }
/*    */   
/*    */   public int entries() {
/* 27 */     int i = 0;
/* 28 */     int j = this.xAxis.bins(); do { i += binEntries(j);j--; } while (j >= 0);
/* 29 */     return i;
/*    */   }
/*    */   
/*    */   public int extraEntries()
/*    */   {
/* 34 */     return binEntries(-2) + binEntries(-1);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   int map(int paramInt)
/*    */   {
/* 42 */     int i = this.xAxis.bins() + 2;
/* 43 */     if (paramInt >= i) throw new IllegalArgumentException("bin=" + paramInt);
/* 44 */     if (paramInt >= 0) return paramInt + 1;
/* 45 */     if (paramInt == -2) return 0;
/* 46 */     if (paramInt == -1) return i - 1;
/* 47 */     throw new IllegalArgumentException("bin=" + paramInt);
/*    */   }
/*    */   
/*    */   public int[] minMaxBins() {
/* 51 */     double d1 = Double.MAX_VALUE;
/* 52 */     double d2 = Double.MIN_VALUE;
/* 53 */     int i = -1;
/* 54 */     int j = -1;
/* 55 */     int k = this.xAxis.bins();
/* 56 */     do { double d3 = binHeight(k);
/* 57 */       if (d3 < d1) {
/* 58 */         d1 = d3;
/* 59 */         i = k;
/*    */       }
/* 61 */       if (d3 > d2) {
/* 62 */         d2 = d3;
/* 63 */         j = k;
/*    */       }
/* 55 */       k--; } while (k >= 0);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 66 */     int[] arrayOfInt = { i, j };
/* 67 */     return arrayOfInt;
/*    */   }
/*    */   
/*    */   public double sumAllBinHeights() {
/* 71 */     return sumBinHeights() + sumExtraBinHeights();
/*    */   }
/*    */   
/*    */   public double sumBinHeights() {
/* 75 */     double d = 0.0D;
/* 76 */     int i = this.xAxis.bins(); do { d += binHeight(i);i--; } while (i >= 0);
/* 77 */     return d;
/*    */   }
/*    */   
/*    */   public double sumExtraBinHeights() {
/* 81 */     return binHeight(-2) + binHeight(-1);
/*    */   }
/*    */   
/*    */   public IAxis xAxis()
/*    */   {
/* 86 */     return this.xAxis;
/*    */   }
/*    */   
/*    */   public abstract double rms();
/*    */   
/*    */   public abstract double mean();
/*    */   
/*    */   public abstract void fill(double paramDouble1, double paramDouble2);
/*    */   
/*    */   public abstract void fill(double paramDouble);
/*    */   
/*    */   public abstract double binHeight(int paramInt);
/*    */   
/*    */   public abstract double binError(int paramInt);
/*    */   
/*    */   public abstract int binEntries(int paramInt);
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/hep/aida/ref/AbstractHistogram1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */