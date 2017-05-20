/*    */ package org.apache.lucene.store;
/*    */ 
/*    */ import java.util.Vector;
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
/*    */ class RAMFile
/*    */ {
/* 22 */   Vector buffers = new Vector();
/*    */   long length;
/* 24 */   long lastModified = System.currentTimeMillis();
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/store/RAMFile.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */