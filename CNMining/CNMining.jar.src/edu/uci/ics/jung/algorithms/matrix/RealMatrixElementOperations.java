/*    */ package edu.uci.ics.jung.algorithms.matrix;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RealMatrixElementOperations<E>
/*    */   implements MatrixElementOperations<E>
/*    */ {
/* 24 */   private Map<E, Number> edgeData = new HashMap();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public RealMatrixElementOperations(Map<E, Number> edgeData)
/*    */   {
/* 31 */     this.edgeData = edgeData;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void mergePaths(E e, Object pathData)
/*    */   {
/* 40 */     Number pd = (Number)pathData;
/* 41 */     Number ed = (Number)this.edgeData.get(e);
/* 42 */     if (ed == null) {
/* 43 */       this.edgeData.put(e, pd);
/*    */     }
/*    */     else {
/* 46 */       this.edgeData.put(e, Double.valueOf(ed.doubleValue() + pd.doubleValue()));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Number computePathData(E e1, E e2)
/*    */   {
/* 57 */     double d1 = ((Number)this.edgeData.get(e1)).doubleValue();
/* 58 */     double d2 = ((Number)this.edgeData.get(e2)).doubleValue();
/* 59 */     return Double.valueOf(d1 * d2);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public Map<E, Number> getEdgeData()
/*    */   {
/* 66 */     return this.edgeData;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/matrix/RealMatrixElementOperations.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */