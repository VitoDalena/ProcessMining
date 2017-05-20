/*    */ package org.deckfour.uitopia.ui.components;
/*    */ 
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Image;
/*    */ import javax.swing.BorderFactory;
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
/*    */ public class TiledPanel
/*    */   extends ShadedPanel
/*    */ {
/*    */   private static final long serialVersionUID = 4365724185437872246L;
/*    */   private Image tile;
/*    */   
/*    */   public TiledPanel(Image tile)
/*    */   {
/* 51 */     this.tile = tile;
/* 52 */     setBorder(BorderFactory.createEmptyBorder());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void paintBackground(Graphics2D g2d)
/*    */   {
/* 60 */     int width = getWidth();
/* 61 */     int height = getHeight();
/* 62 */     int tileWidth = this.tile.getWidth(null);
/* 63 */     int tileHeight = this.tile.getHeight(null);
/* 64 */     for (int y = 0; y < height; y += tileHeight) {
/* 65 */       for (int x = 0; x < width; x += tileWidth) {
/* 66 */         g2d.drawImage(this.tile, x, y, null);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/components/TiledPanel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */