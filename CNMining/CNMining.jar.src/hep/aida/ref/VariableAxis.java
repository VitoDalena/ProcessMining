/*    */ package hep.aida.ref;
/*    */ 
/*    */ import hep.aida.IAxis;
/*    */ import java.util.Arrays;
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
/*    */ public class VariableAxis
/*    */   implements IAxis
/*    */ {
/*    */   protected double min;
/*    */   protected int bins;
/*    */   protected double[] edges;
/*    */   
/*    */   public VariableAxis(double[] paramArrayOfDouble)
/*    */   {
/* 24 */     if (paramArrayOfDouble.length < 1) { throw new IllegalArgumentException();
/*    */     }
/*    */     
/* 27 */     for (int i = 0; i < paramArrayOfDouble.length - 1; i++) {
/* 28 */       if (paramArrayOfDouble[(i + 1)] <= paramArrayOfDouble[i]) {
/* 29 */         throw new IllegalArgumentException("edges must be sorted ascending and must not contain multiple identical values");
/*    */       }
/*    */     }
/*    */     
/* 33 */     this.min = paramArrayOfDouble[0];
/* 34 */     this.bins = (paramArrayOfDouble.length - 1);
/* 35 */     this.edges = ((double[])paramArrayOfDouble.clone());
/*    */   }
/*    */   
/*    */   public double binCentre(int paramInt) {
/* 39 */     return (binLowerEdge(paramInt) + binUpperEdge(paramInt)) / 2.0D;
/*    */   }
/*    */   
/*    */   public double binLowerEdge(int paramInt) {
/* 43 */     if (paramInt == -2) return Double.NEGATIVE_INFINITY;
/* 44 */     if (paramInt == -1) return upperEdge();
/* 45 */     return this.edges[paramInt];
/*    */   }
/*    */   
/*    */   public int bins() {
/* 49 */     return this.bins;
/*    */   }
/*    */   
/*    */   public double binUpperEdge(int paramInt) {
/* 53 */     if (paramInt == -2) return lowerEdge();
/* 54 */     if (paramInt == -1) return Double.POSITIVE_INFINITY;
/* 55 */     return this.edges[(paramInt + 1)];
/*    */   }
/*    */   
/*    */   public double binWidth(int paramInt) {
/* 59 */     return binUpperEdge(paramInt) - binLowerEdge(paramInt);
/*    */   }
/*    */   
/*    */   public int coordToIndex(double paramDouble) {
/* 63 */     if (paramDouble < this.min) { return -2;
/*    */     }
/* 65 */     int i = Arrays.binarySearch(this.edges, paramDouble);
/*    */     
/* 67 */     if (i < 0) { i = -i - 1 - 1;
/*    */     }
/*    */     
/* 70 */     if (i >= this.bins) { return -1;
/*    */     }
/* 72 */     return i;
/*    */   }
/*    */   
/*    */   public double lowerEdge() {
/* 76 */     return this.min;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected static String toString(double[] paramArrayOfDouble)
/*    */   {
/* 86 */     StringBuffer localStringBuffer = new StringBuffer();
/* 87 */     localStringBuffer.append("[");
/* 88 */     int i = paramArrayOfDouble.length - 1;
/* 89 */     for (int j = 0; j <= i; j++) {
/* 90 */       localStringBuffer.append(paramArrayOfDouble[j]);
/* 91 */       if (j < i)
/* 92 */         localStringBuffer.append(", ");
/*    */     }
/* 94 */     localStringBuffer.append("]");
/* 95 */     return localStringBuffer.toString();
/*    */   }
/*    */   
/*    */   public double upperEdge() {
/* 99 */     return this.edges[(this.edges.length - 1)];
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/hep/aida/ref/VariableAxis.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */