/*    */ package org.deckfour.spex;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
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
/*    */ public abstract class SXNode
/*    */ {
/* 54 */   protected int tabLevel = 0;
/*    */   
/*    */ 
/*    */ 
/* 58 */   protected String tabString = null;
/*    */   
/*    */ 
/*    */ 
/* 62 */   protected Writer writer = null;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected SXNode(Writer aWriter, int aTabLevel, String aTabString)
/*    */   {
/* 72 */     this.writer = aWriter;
/* 73 */     this.tabLevel = aTabLevel;
/* 74 */     this.tabString = aTabString;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public abstract void close()
/*    */     throws IOException;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public int getTabLevel()
/*    */   {
/* 88 */     return this.tabLevel;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected synchronized void indentLine()
/*    */     throws IOException
/*    */   {
/* 96 */     for (int i = 0; i < this.tabLevel; i++) {
/* 97 */       this.writer.write(this.tabString);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/spex/SXNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */