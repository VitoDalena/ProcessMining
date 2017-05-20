/*    */ package edu.uci.ics.jung.visualization;
/*    */ 
/*    */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*    */ import edu.uci.ics.jung.visualization.transform.MutableTransformer;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Image;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.RenderingHints.Key;
/*    */ import java.awt.geom.Point2D;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
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
/*    */ public class VisualizationImageServer<V, E>
/*    */   extends BasicVisualizationServer<V, E>
/*    */ {
/* 34 */   Map<RenderingHints.Key, Object> renderingHints = new HashMap();
/*    */   
/*    */ 
/*    */ 
/*    */   public VisualizationImageServer(Layout<V, E> layout, Dimension preferredSize)
/*    */   {
/* 40 */     super(layout, preferredSize);
/* 41 */     setSize(preferredSize);
/* 42 */     this.renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 43 */     addNotify();
/*    */   }
/*    */   
/*    */   public Image getImage(Point2D center, Dimension d)
/*    */   {
/* 48 */     int width = getWidth();
/* 49 */     int height = getHeight();
/*    */     
/* 51 */     float scalex = width / d.width;
/* 52 */     float scaley = height / d.height;
/*    */     try
/*    */     {
/* 55 */       this.renderContext.getMultiLayerTransformer().getTransformer(Layer.VIEW).scale(scalex, scaley, center);
/*    */       
/* 57 */       BufferedImage bi = new BufferedImage(width, height, 1);
/*    */       
/* 59 */       Graphics2D graphics = bi.createGraphics();
/* 60 */       graphics.setRenderingHints(this.renderingHints);
/* 61 */       paint(graphics);
/* 62 */       graphics.dispose();
/* 63 */       return bi;
/*    */     } finally {
/* 65 */       this.renderContext.getMultiLayerTransformer().getTransformer(Layer.VIEW).setToIdentity();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/VisualizationImageServer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */