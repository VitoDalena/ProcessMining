/*    */ package org.apache.commons.math.stat.descriptive;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ /**
/*    */  * @deprecated
/*    */  */
/*    */ public class DescriptiveStatisticsImpl
/*    */   extends DescriptiveStatistics
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -6467796944112488424L;
/*    */   
/*    */   public DescriptiveStatisticsImpl() {}
/*    */   
/*    */   public DescriptiveStatisticsImpl(int window)
/*    */   {
/* 47 */     super(window);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void clear()
/*    */   {
/* 54 */     super.clear();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/DescriptiveStatisticsImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */