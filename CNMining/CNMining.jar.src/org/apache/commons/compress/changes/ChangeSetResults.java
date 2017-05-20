/*    */ package org.apache.commons.compress.changes;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
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
/*    */ public class ChangeSetResults
/*    */ {
/* 28 */   private final List addedFromChangeSet = new ArrayList();
/* 29 */   private final List addedFromStream = new ArrayList();
/* 30 */   private final List deleted = new ArrayList();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   void deleted(String fileName)
/*    */   {
/* 37 */     this.deleted.add(fileName);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   void addedFromStream(String fileName)
/*    */   {
/* 46 */     this.addedFromStream.add(fileName);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   void addedFromChangeSet(String fileName)
/*    */   {
/* 55 */     this.addedFromChangeSet.add(fileName);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public List getAddedFromChangeSet()
/*    */   {
/* 63 */     return this.addedFromChangeSet;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public List getAddedFromStream()
/*    */   {
/* 71 */     return this.addedFromStream;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public List getDeleted()
/*    */   {
/* 79 */     return this.deleted;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   boolean hasBeenAdded(String filename)
/*    */   {
/* 88 */     if ((this.addedFromChangeSet.contains(filename)) || (this.addedFromStream.contains(filename))) {
/* 89 */       return true;
/*    */     }
/* 91 */     return false;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/changes/ChangeSetResults.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */