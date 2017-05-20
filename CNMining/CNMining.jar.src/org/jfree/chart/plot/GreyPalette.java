/*    */ package org.jfree.chart.plot;
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
/*    */ public class GreyPalette
/*    */   extends ColorPalette
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -2120941170159987395L;
/*    */   
/*    */   public GreyPalette()
/*    */   {
/* 68 */     initialize();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void initialize()
/*    */   {
/* 76 */     setPaletteName("Grey");
/*    */     
/* 78 */     this.r = new int['Ā'];
/* 79 */     this.g = new int['Ā'];
/* 80 */     this.b = new int['Ā'];
/*    */     
/* 82 */     this.r[0] = 255;
/* 83 */     this.g[0] = 255;
/* 84 */     this.b[0] = 255;
/* 85 */     this.r[1] = 0;
/* 86 */     this.g[1] = 0;
/* 87 */     this.b[1] = 0;
/*    */     
/* 89 */     for (int i = 2; i < 256; i++) {
/* 90 */       this.r[i] = i;
/* 91 */       this.g[i] = i;
/* 92 */       this.b[i] = i;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/GreyPalette.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */