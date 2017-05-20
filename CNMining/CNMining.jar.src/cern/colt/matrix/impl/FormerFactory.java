/*    */ package cern.colt.matrix.impl;
/*    */ 
/*    */ import corejava.Format;
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
/*    */ public class FormerFactory
/*    */ {
/*    */   public Former create(String paramString)
/*    */   {
/* 47 */     new Former() {
/*    */       private Format f;
/*    */       private final String val$format;
/*    */       
/* 51 */       public String form(double paramAnonymousDouble) { if ((this.f == null) || (paramAnonymousDouble == Double.POSITIVE_INFINITY) || (paramAnonymousDouble == Double.NEGATIVE_INFINITY) || (paramAnonymousDouble != paramAnonymousDouble))
/*    */         {
/*    */ 
/* 54 */           return String.valueOf(paramAnonymousDouble);
/*    */         }
/* 56 */         return this.f.form(paramAnonymousDouble);
/*    */       }
/*    */     };
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/FormerFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */