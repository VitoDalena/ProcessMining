/*    */ package edu.uci.ics.jung.visualization.transform.shape;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Shape;
/*    */ import javax.swing.CellRendererPane;
/*    */ import javax.swing.Icon;
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
/*    */ public class GraphicsDecorator
/*    */   extends Graphics2DWrapper
/*    */ {
/*    */   public GraphicsDecorator()
/*    */   {
/* 34 */     this(null);
/*    */   }
/*    */   
/* 37 */   public GraphicsDecorator(Graphics2D delegate) { super(delegate); }
/*    */   
/*    */   public void draw(Icon icon, Component c, Shape clip, int x, int y)
/*    */   {
/* 41 */     int w = icon.getIconWidth();
/* 42 */     int h = icon.getIconHeight();
/* 43 */     icon.paintIcon(c, this.delegate, x - w / 2, y - h / 2);
/*    */   }
/*    */   
/*    */   public void draw(Component c, CellRendererPane rendererPane, int x, int y, int w, int h, boolean shouldValidate)
/*    */   {
/* 48 */     rendererPane.paintComponent(this.delegate, c, c.getParent(), x, y, w, h, shouldValidate);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/transform/shape/GraphicsDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */