/*    */ package org.jfree.chart.block;
/*    */ 
/*    */ import org.jfree.chart.entity.EntityCollection;
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
/*    */ public class BlockResult
/*    */   implements EntityBlockResult
/*    */ {
/*    */   private EntityCollection entities;
/*    */   
/*    */   public BlockResult()
/*    */   {
/* 58 */     this.entities = null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public EntityCollection getEntityCollection()
/*    */   {
/* 67 */     return this.entities;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setEntityCollection(EntityCollection entities)
/*    */   {
/* 76 */     this.entities = entities;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/block/BlockResult.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */