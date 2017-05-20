/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.Window;
/*     */ import java.lang.reflect.Method;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ import javax.swing.table.TableModel;
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
/*     */ public class RefineryUtilities
/*     */ {
/*     */   public static Point getCenterPoint()
/*     */   {
/* 109 */     GraphicsEnvironment localGraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
/*     */     try
/*     */     {
/* 112 */       Method method = GraphicsEnvironment.class.getMethod("getCenterPoint", (Class[])null);
/* 113 */       return (Point)method.invoke(localGraphicsEnvironment, (Object[])null);
/*     */ 
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */ 
/* 120 */       Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
/* 121 */       return new Point(s.width / 2, s.height / 2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Rectangle getMaximumWindowBounds()
/*     */   {
/* 132 */     GraphicsEnvironment localGraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
/*     */     try
/*     */     {
/* 135 */       Method method = GraphicsEnvironment.class.getMethod("getMaximumWindowBounds", (Class[])null);
/* 136 */       return (Rectangle)method.invoke(localGraphicsEnvironment, (Object[])null);
/*     */ 
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */ 
/* 143 */       Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
/* 144 */       return new Rectangle(0, 0, s.width, s.height);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void centerFrameOnScreen(Window frame)
/*     */   {
/* 153 */     positionFrameOnScreen(frame, 0.5D, 0.5D);
/*     */   }
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
/*     */   public static void positionFrameOnScreen(Window frame, double horizontalPercent, double verticalPercent)
/*     */   {
/* 170 */     Rectangle s = getMaximumWindowBounds();
/* 171 */     Dimension f = frame.getSize();
/* 172 */     int w = Math.max(s.width - f.width, 0);
/* 173 */     int h = Math.max(s.height - f.height, 0);
/* 174 */     int x = (int)(horizontalPercent * w) + s.x;
/* 175 */     int y = (int)(verticalPercent * h) + s.y;
/* 176 */     frame.setBounds(x, y, f.width, f.height);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void positionFrameRandomly(Window frame)
/*     */   {
/* 187 */     positionFrameOnScreen(frame, Math.random(), Math.random());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void centerDialogInParent(Dialog dialog)
/*     */   {
/* 196 */     positionDialogRelativeToParent(dialog, 0.5D, 0.5D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void positionDialogRelativeToParent(Dialog dialog, double horizontalPercent, double verticalPercent)
/*     */   {
/* 209 */     Dimension d = dialog.getSize();
/* 210 */     Container parent = dialog.getParent();
/* 211 */     Dimension p = parent.getSize();
/*     */     
/* 213 */     int baseX = parent.getX() - d.width;
/* 214 */     int baseY = parent.getY() - d.height;
/* 215 */     int w = d.width + p.width;
/* 216 */     int h = d.height + p.height;
/* 217 */     int x = baseX + (int)(horizontalPercent * w);
/* 218 */     int y = baseY + (int)(verticalPercent * h);
/*     */     
/*     */ 
/* 221 */     Rectangle s = getMaximumWindowBounds();
/* 222 */     x = Math.min(x, s.width - d.width);
/* 223 */     x = Math.max(x, 0);
/* 224 */     y = Math.min(y, s.height - d.height);
/* 225 */     y = Math.max(y, 0);
/*     */     
/* 227 */     dialog.setBounds(x + s.x, y + s.y, d.width, d.height);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static JPanel createTablePanel(TableModel model)
/*     */   {
/* 240 */     JPanel panel = new JPanel(new BorderLayout());
/* 241 */     JTable table = new JTable(model);
/* 242 */     for (int columnIndex = 0; columnIndex < model.getColumnCount(); columnIndex++) {
/* 243 */       TableColumn column = table.getColumnModel().getColumn(columnIndex);
/* 244 */       Class c = model.getColumnClass(columnIndex);
/* 245 */       if (c.equals(Number.class)) {
/* 246 */         column.setCellRenderer(new NumberCellRenderer());
/*     */       }
/*     */     }
/* 249 */     panel.add(new JScrollPane(table));
/* 250 */     return panel;
/*     */   }
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
/*     */   public static JLabel createJLabel(String text, Font font)
/*     */   {
/* 264 */     JLabel result = new JLabel(text);
/* 265 */     result.setFont(font);
/* 266 */     return result;
/*     */   }
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
/*     */   public static JLabel createJLabel(String text, Font font, Color color)
/*     */   {
/* 281 */     JLabel result = new JLabel(text);
/* 282 */     result.setFont(font);
/* 283 */     result.setForeground(color);
/* 284 */     return result;
/*     */   }
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
/*     */   public static JButton createJButton(String label, Font font)
/*     */   {
/* 298 */     JButton result = new JButton(label);
/* 299 */     result.setFont(font);
/* 300 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/ui/RefineryUtilities.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */