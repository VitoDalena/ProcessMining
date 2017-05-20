/*    */ package edu.uci.ics.jung.visualization;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Image;
/*    */ import java.util.LinkedHashSet;
/*    */ import java.util.Set;
/*    */ import javax.swing.Icon;
/*    */ import javax.swing.ImageIcon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LayeredIcon
/*    */   extends ImageIcon
/*    */ {
/* 23 */   Set<Icon> iconSet = new LinkedHashSet();
/*    */   
/*    */   public LayeredIcon(Image image) {
/* 26 */     super(image);
/*    */   }
/*    */   
/*    */   public void paintIcon(Component c, Graphics g, int x, int y) {
/* 30 */     super.paintIcon(c, g, x, y);
/* 31 */     Dimension d = new Dimension(getIconWidth(), getIconHeight());
/* 32 */     for (Icon icon : this.iconSet) {
/* 33 */       Dimension id = new Dimension(icon.getIconWidth(), icon.getIconHeight());
/* 34 */       int dx = (d.width - id.width) / 2;
/* 35 */       int dy = (d.height - id.height) / 2;
/* 36 */       icon.paintIcon(c, g, x + dx, y + dy);
/*    */     }
/*    */   }
/*    */   
/*    */   public void add(Icon icon) {
/* 41 */     this.iconSet.add(icon);
/*    */   }
/*    */   
/*    */   public boolean remove(Icon icon) {
/* 45 */     return this.iconSet.remove(icon);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/LayeredIcon.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */