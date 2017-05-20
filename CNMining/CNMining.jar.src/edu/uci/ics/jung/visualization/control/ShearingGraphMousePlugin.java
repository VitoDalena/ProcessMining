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
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.geom.Point2D;
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
/*     */ public class ShearingGraphMousePlugin
/*     */   extends AbstractGraphMousePlugin
/*     */   implements MouseListener, MouseMotionListener
/*     */ {
/*  46 */   private static int mask = 2;
/*     */   
/*     */   static {
/*  49 */     if (System.getProperty("os.name").startsWith("Mac")) {
/*  50 */       mask = 4;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public ShearingGraphMousePlugin()
/*     */   {
/*  57 */     this(0x10 | mask);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ShearingGraphMousePlugin(int modifiers)
/*     */   {
/*  65 */     super(modifiers);
/*  66 */     Dimension cd = Toolkit.getDefaultToolkit().getBestCursorSize(16, 16);
/*  67 */     BufferedImage cursorImage = new BufferedImage(cd.width, cd.height, 2);
/*     */     
/*  69 */     Graphics g = cursorImage.createGraphics();
/*  70 */     Graphics2D g2 = (Graphics2D)g;
/*  71 */     g2.addRenderingHints(Collections.singletonMap(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
/*  72 */     g.setColor(new Color(0, 0, 0, 0));
/*  73 */     g.fillRect(0, 0, 16, 16);
/*     */     
/*  75 */     int left = 0;
/*  76 */     int top = 0;
/*  77 */     int right = 15;
/*  78 */     int bottom = 15;
/*     */     
/*  80 */     g.setColor(Color.white);
/*  81 */     g2.setStroke(new BasicStroke(3.0F));
/*  82 */     g.drawLine(left + 2, top + 5, right - 2, top + 5);
/*  83 */     g.drawLine(left + 2, bottom - 5, right - 2, bottom - 5);
/*  84 */     g.drawLine(left + 2, top + 5, left + 4, top + 3);
/*  85 */     g.drawLine(left + 2, top + 5, left + 4, top + 7);
/*  86 */     g.drawLine(right - 2, bottom - 5, right - 4, bottom - 3);
/*  87 */     g.drawLine(right - 2, bottom - 5, right - 4, bottom - 7);
/*     */     
/*  89 */     g.setColor(Color.black);
/*  90 */     g2.setStroke(new BasicStroke(1.0F));
/*  91 */     g.drawLine(left + 2, top + 5, right - 2, top + 5);
/*  92 */     g.drawLine(left + 2, bottom - 5, right - 2, bottom - 5);
/*  93 */     g.drawLine(left + 2, top + 5, left + 4, top + 3);
/*  94 */     g.drawLine(left + 2, top + 5, left + 4, top + 7);
/*  95 */     g.drawLine(right - 2, bottom - 5, right - 4, bottom - 3);
/*  96 */     g.drawLine(right - 2, bottom - 5, right - 4, bottom - 7);
/*  97 */     g.dispose();
/*  98 */     this.cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(), "RotateCursor");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mousePressed(MouseEvent e)
/*     */   {
/* 107 */     VisualizationViewer vv = (VisualizationViewer)e.getSource();
/* 108 */     boolean accepted = checkModifiers(e);
/* 109 */     this.down = e.getPoint();
/* 110 */     if (accepted) {
/* 111 */       vv.setCursor(this.cursor);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseReleased(MouseEvent e)
/*     */   {
/* 119 */     VisualizationViewer vv = (VisualizationViewer)e.getSource();
/* 120 */     this.down = null;
/* 121 */     vv.setCursor(Cursor.getPredefinedCursor(0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseDragged(MouseEvent e)
/*     */   {
/* 131 */     if (this.down == null) return;
/* 132 */     VisualizationViewer vv = (VisualizationViewer)e.getSource();
/* 133 */     boolean accepted = checkModifiers(e);
/* 134 */     if (accepted) {
/* 135 */       MutableTransformer modelTransformer = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT);
/*     */       
/* 137 */       vv.setCursor(this.cursor);
/* 138 */       Point2D q = this.down;
/* 139 */       Point2D p = e.getPoint();
/* 140 */       float dx = (float)(p.getX() - q.getX());
/* 141 */       float dy = (float)(p.getY() - q.getY());
/*     */       
/* 143 */       Dimension d = vv.getSize();
/* 144 */       float shx = 2.0F * dx / d.height;
/* 145 */       float shy = 2.0F * dy / d.width;
/* 146 */       Point2D center = vv.getCenter();
/* 147 */       if (p.getX() < center.getX()) {
/* 148 */         shy = -shy;
/*     */       }
/* 150 */       if (p.getY() < center.getY()) {
/* 151 */         shx = -shx;
/*     */       }
/* 153 */       modelTransformer.shear(shx, shy, center);
/* 154 */       this.down.x = e.getX();
/* 155 */       this.down.y = e.getY();
/*     */       
/* 157 */       e.consume();
/*     */     }
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/ShearingGraphMousePlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */