/*     */ package edu.uci.ics.jung.visualization.control;
/*     */ 
/*     */ import edu.uci.ics.jung.visualization.Layer;
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*     */ import edu.uci.ics.jung.visualization.transform.MutableTransformer;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Double;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.Collections;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RotatingGraphMousePlugin
/*     */   extends AbstractGraphMousePlugin
/*     */   implements MouseListener, MouseMotionListener
/*     */ {
/*     */   public RotatingGraphMousePlugin()
/*     */   {
/*  48 */     this(17);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public RotatingGraphMousePlugin(int modifiers)
/*     */   {
/*  56 */     super(modifiers);
/*  57 */     Dimension cd = Toolkit.getDefaultToolkit().getBestCursorSize(16, 16);
/*  58 */     BufferedImage cursorImage = new BufferedImage(cd.width, cd.height, 2);
/*     */     
/*  60 */     Graphics2D g = cursorImage.createGraphics();
/*  61 */     g.addRenderingHints(Collections.singletonMap(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
/*  62 */     g.setColor(new Color(0, 0, 0, 0));
/*  63 */     g.fillRect(0, 0, 16, 16);
/*     */     
/*  65 */     int left = 0;
/*  66 */     int top = 0;
/*  67 */     int right = 15;
/*  68 */     int bottom = 15;
/*     */     
/*  70 */     g.setColor(Color.white);
/*  71 */     g.setStroke(new BasicStroke(3.0F));
/*     */     
/*  73 */     g.drawLine(left + 2, top + 6, right / 2 + 1, top);
/*  74 */     g.drawLine(right / 2 + 1, top, right - 2, top + 5);
/*     */     
/*  76 */     g.drawLine(left + 2, bottom - 6, right / 2, bottom);
/*  77 */     g.drawLine(right / 2, bottom, right - 2, bottom - 6);
/*     */     
/*  79 */     g.drawLine(left + 2, top + 6, left + 5, top + 6);
/*  80 */     g.drawLine(left + 2, top + 6, left + 2, top + 3);
/*     */     
/*  82 */     g.drawLine(right - 2, bottom - 6, right - 6, bottom - 6);
/*  83 */     g.drawLine(right - 2, bottom - 6, right - 2, bottom - 3);
/*     */     
/*     */ 
/*  86 */     g.setColor(Color.black);
/*  87 */     g.setStroke(new BasicStroke(1.0F));
/*     */     
/*  89 */     g.drawLine(left + 2, top + 6, right / 2 + 1, top);
/*  90 */     g.drawLine(right / 2 + 1, top, right - 2, top + 5);
/*     */     
/*  92 */     g.drawLine(left + 2, bottom - 6, right / 2, bottom);
/*  93 */     g.drawLine(right / 2, bottom, right - 2, bottom - 6);
/*     */     
/*  95 */     g.drawLine(left + 2, top + 6, left + 5, top + 6);
/*  96 */     g.drawLine(left + 2, top + 6, left + 2, top + 3);
/*     */     
/*  98 */     g.drawLine(right - 2, bottom - 6, right - 6, bottom - 6);
/*  99 */     g.drawLine(right - 2, bottom - 6, right - 2, bottom - 3);
/*     */     
/* 101 */     g.dispose();
/*     */     
/* 103 */     this.cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(), "RotateCursor");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mousePressed(MouseEvent e)
/*     */   {
/* 112 */     VisualizationViewer vv = (VisualizationViewer)e.getSource();
/* 113 */     boolean accepted = checkModifiers(e);
/* 114 */     this.down = e.getPoint();
/* 115 */     if (accepted) {
/* 116 */       vv.setCursor(this.cursor);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseReleased(MouseEvent e)
/*     */   {
/* 124 */     VisualizationViewer vv = (VisualizationViewer)e.getSource();
/* 125 */     this.down = null;
/* 126 */     vv.setCursor(Cursor.getPredefinedCursor(0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseDragged(MouseEvent e)
/*     */   {
/* 134 */     if (this.down == null) return;
/* 135 */     VisualizationViewer vv = (VisualizationViewer)e.getSource();
/* 136 */     boolean accepted = checkModifiers(e);
/* 137 */     if (accepted) {
/* 138 */       MutableTransformer modelTransformer = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT);
/*     */       
/*     */ 
/* 141 */       vv.setCursor(this.cursor);
/*     */       
/* 143 */       Point2D center = vv.getCenter();
/* 144 */       Point2D q = this.down;
/* 145 */       Point2D p = e.getPoint();
/* 146 */       Point2D v1 = new Point2D.Double(center.getX() - p.getX(), center.getY() - p.getY());
/* 147 */       Point2D v2 = new Point2D.Double(center.getX() - q.getX(), center.getY() - q.getY());
/* 148 */       double theta = angleBetween(v1, v2);
/* 149 */       modelTransformer.rotate(theta, vv.getRenderContext().getMultiLayerTransformer().inverseTransform(Layer.VIEW, center));
/* 150 */       this.down.x = e.getX();
/* 151 */       this.down.y = e.getY();
/*     */       
/* 153 */       e.consume();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double angleBetween(Point2D v1, Point2D v2)
/*     */   {
/* 165 */     double x1 = v1.getX();
/* 166 */     double y1 = v1.getY();
/* 167 */     double x2 = v2.getX();
/* 168 */     double y2 = v2.getY();
/*     */     
/* 170 */     double cross = x1 * y2 - x2 * y1;
/* 171 */     int cw = 1;
/* 172 */     if (cross > 0.0D) {
/* 173 */       cw = -1;
/*     */     }
/*     */     
/* 176 */     double angle = cw * Math.acos((x1 * x2 + y1 * y2) / (Math.sqrt(x1 * x1 + y1 * y1) * Math.sqrt(x2 * x2 + y2 * y2)));
/*     */     
/*     */ 
/*     */ 
/* 180 */     if (Double.isNaN(angle)) {
/* 181 */       angle = 0.0D;
/*     */     }
/* 183 */     return angle;
/*     */   }
/*     */   
/*     */   public void mouseClicked(MouseEvent e) {}
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {}
/*     */   
/*     */   public void mouseExited(MouseEvent e) {}
/*     */   
/*     */   public void mouseMoved(MouseEvent e) {}
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/RotatingGraphMousePlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */