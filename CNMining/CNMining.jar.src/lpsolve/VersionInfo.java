/*    */ package lpsolve;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VersionInfo
/*    */ {
/*    */   private int _majorversion;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private int _minorversion;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private int _release;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private int _build;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public VersionInfo(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*    */   {
/* 39 */     this._majorversion = paramInt1;
/* 40 */     this._minorversion = paramInt2;
/* 41 */     this._release = paramInt3;
/* 42 */     this._build = paramInt4;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public int getBuild()
/*    */   {
/* 49 */     return this._build;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public int getMajorversion()
/*    */   {
/* 56 */     return this._majorversion;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public int getMinorversion()
/*    */   {
/* 63 */     return this._minorversion;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public int getRelease()
/*    */   {
/* 70 */     return this._release;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/lpsolve/VersionInfo.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */