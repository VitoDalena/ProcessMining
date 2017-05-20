/*    */ package hep.aida.ref;
/*    */ 
/*    */ import hep.aida.IHistogram;
/*    */ 
/*    */ 
/*    */ 
/*    */ abstract class Histogram
/*    */   implements IHistogram
/*    */ {
/*    */   private String title;
/*    */   
/*    */   Histogram(String paramString)
/*    */   {
/* 14 */     this.title = paramString;
/*    */   }
/*    */   
/*    */   public String title() {
/* 18 */     return this.title;
/*    */   }
/*    */   
/*    */   public abstract double sumExtraBinHeights();
/*    */   
/*    */   public abstract double sumBinHeights();
/*    */   
/*    */   public abstract double sumAllBinHeights();
/*    */   
/*    */   public abstract void reset();
/*    */   
/*    */   public abstract int extraEntries();
/*    */   
/*    */   public abstract double equivalentBinEntries();
/*    */   
/*    */   public abstract int entries();
/*    */   
/*    */   public abstract int dimensions();
/*    */   
/*    */   public abstract int allEntries();
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/hep/aida/ref/Histogram.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */