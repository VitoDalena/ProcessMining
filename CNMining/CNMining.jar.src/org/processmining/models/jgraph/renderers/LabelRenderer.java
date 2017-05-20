/*     */ package org.processmining.models.jgraph.renderers;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JLabel;
/*     */ import org.jgraph.graph.DefaultPort;
/*     */ import org.jgraph.graph.PortView;
/*     */ import org.processmining.framework.util.Cast;
/*     */ import org.processmining.models.graphbased.ViewSpecificAttributeMap;
/*     */ import org.processmining.models.graphbased.directed.BoundaryDirectedGraphNode;
/*     */ import org.processmining.models.jgraph.views.JGraphPortView;
/*     */ import org.processmining.models.jgraph.views.JGraphShapeView;
/*     */ import org.processmining.models.shapes.Decorated;
/*     */ import org.processmining.models.shapes.Shape;
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
/*     */ class LabelRenderer
/*     */   extends JLabel
/*     */ {
/*     */   private static final long serialVersionUID = 4310963545024487311L;
/*     */   
/*     */   public void paint(PortView view, boolean selected, Graphics g)
/*     */   {
/*  61 */     Object representedObject = ((DefaultPort)view.getCell()).getUserObject();
/*  62 */     if (((representedObject instanceof BoundaryDirectedGraphNode)) && (((BoundaryDirectedGraphNode)representedObject).getBoundingNode() != null))
/*     */     {
/*  64 */       BoundaryDirectedGraphNode node = (BoundaryDirectedGraphNode)representedObject;
/*  65 */       Dimension d = getSize();
/*  66 */       ViewSpecificAttributeMap map = ((JGraphPortView)view).getViewSpecificAttributeMap();
/*     */       
/*  68 */       if ((!((Boolean)map.get(node, "ProM_Vis_attr_showLabel", Boolean.valueOf(true))).booleanValue()) || (((JGraphPortView)view).isPIP())) {
/*  69 */         setText(null);
/*     */       } else {
/*  71 */         setVerticalAlignment(((Integer)map.get(node, "ProM_Vis_attr_labelVerticalAlignment", Integer.valueOf(1))).intValue());
/*  72 */         setHorizontalAlignment(((Integer)map.get(node, "ProM_Vis_attr_horizontal alignment", Integer.valueOf(0))).intValue());
/*  73 */         String text = (String)map.get(node, "ProM_Vis_attr_label", getText());
/*  74 */         if (!text.toLowerCase().startsWith("<html>")) {
/*  75 */           text = "<html>" + text + "</html>";
/*     */         }
/*  77 */         setText(text);
/*     */       }
/*     */       
/*  80 */       Shape shape = (Shape)map.get(node, "ProM_Vis_attr_shape", JGraphShapeView.RECTANGLE);
/*     */       
/*  82 */       Icon icon = (Icon)map.get(node, "ProM_Vis_attr_icon");
/*  83 */       if ((icon != null) && ((icon instanceof ImageIcon))) {
/*  84 */         Image image = ((ImageIcon)icon).getImage();
/*  85 */         if ((icon.getIconHeight() > d.height) || (icon.getIconWidth() > d.width)) {
/*  86 */           image = image.getScaledInstance(d.height, d.width, 4);
/*     */         }
/*  88 */         icon = new ImageIcon(image);
/*     */       }
/*  90 */       if (!((JGraphPortView)view).isPIP()) {
/*  91 */         setIcon(icon);
/*     */       } else {
/*  93 */         setIcon(null);
/*     */       }
/*     */       
/*  96 */       int b = ((Integer)map.get(node, "ProM_Vis_attr_border", Integer.valueOf(1))).intValue();
/*  97 */       Graphics2D g2 = (Graphics2D)g;
/*     */       
/*  99 */       boolean tmp = selected;
/*     */       
/* 101 */       GeneralPath path = shape.getPath(b, b, d.width - 2 * b, d.height - 2 * b);
/*     */       
/* 103 */       Color fill = (Color)map.get(node, "ProM_Vis_attr_fillcolor");
/* 104 */       g.setColor(fill);
/* 105 */       setOpaque(fill != null);
/* 106 */       if (fill != null) {
/* 107 */         g2.fill(path);
/*     */       }
/*     */       
/* 110 */       g.setColor((Color)map.get(node, "ProM_Vis_attr_strokeColor", Color.BLACK));
/*     */       
/* 112 */       float[] pattern = (float[])map.get(node, "ProM_Vis_attr_dashPattern", new float[0]);
/* 113 */       if (pattern.length > 0.0F)
/*     */       {
/* 115 */         float offset = ((Float)map.get(node, "ProM_Vis_attr_dashOffset", Float.valueOf(0.0F))).floatValue();
/* 116 */         g2.setStroke(new BasicStroke(b, 0, 0, 10.0F, pattern, offset));
/*     */       } else {
/* 118 */         g2.setStroke(new BasicStroke(b));
/*     */       }
/*     */       
/* 121 */       g2.draw(path);
/*     */       try
/*     */       {
/* 124 */         setBorder(null);
/* 125 */         setOpaque(false);
/*     */         
/* 127 */         super.paint(g);
/* 128 */         if ((node instanceof Decorated)) {
/* 129 */           ((Decorated)Cast.cast(node)).decorate(g2, b, b, d.width - 2 * b, d.height - 2 * b);
/*     */         }
/*     */       } finally {
/* 132 */         selected = tmp;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public Point2D getPerimeterPoint(PortView view, Point2D source, Point2D p) {
/* 138 */     Rectangle2D bounds = view.getBounds();
/* 139 */     double x = bounds.getX();
/* 140 */     double y = bounds.getY();
/* 141 */     double width = bounds.getWidth();
/* 142 */     double height = bounds.getHeight();
/* 143 */     double xCenter = x + width / 2.0D;
/* 144 */     double yCenter = y + height / 2.0D;
/* 145 */     double dx = p.getX() - xCenter;
/* 146 */     double dy = p.getY() - yCenter;
/* 147 */     double alpha = Math.atan2(dy, dx);
/* 148 */     double xout = 0.0D;double yout = 0.0D;
/* 149 */     double pi = 3.141592653589793D;
/* 150 */     double pi2 = 1.5707963267948966D;
/* 151 */     double beta = pi2 - alpha;
/* 152 */     double t = Math.atan2(height, width);
/* 153 */     if ((alpha < -pi + t) || (alpha > pi - t)) {
/* 154 */       xout = x;
/* 155 */       yout = yCenter - width * Math.tan(alpha) / 2.0D;
/* 156 */     } else if (alpha < -t) {
/* 157 */       yout = y;
/* 158 */       xout = xCenter - height * Math.tan(beta) / 2.0D;
/* 159 */     } else if (alpha < t) {
/* 160 */       xout = x + width;
/* 161 */       yout = yCenter + width * Math.tan(alpha) / 2.0D;
/*     */     } else {
/* 163 */       yout = y + height;
/* 164 */       xout = xCenter + height * Math.tan(beta) / 2.0D;
/*     */     }
/* 166 */     return new Point2D.Double(xout, yout);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/models/jgraph/renderers/LabelRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */