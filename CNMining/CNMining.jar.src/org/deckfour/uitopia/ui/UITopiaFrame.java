/*     */ package org.deckfour.uitopia.ui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Point;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ComponentAdapter;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.JFrame;
/*     */ import org.deckfour.uitopia.api.hub.FrameworkHub;
/*     */ import org.deckfour.uitopia.ui.conf.ConfigurationSet;
/*     */ import org.deckfour.uitopia.ui.conf.UIConfiguration;
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
/*     */ public class UITopiaFrame
/*     */   extends JFrame
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String CONF_X = "window_x";
/*     */   private static final String CONF_Y = "window_y";
/*     */   private static final String CONF_WIDTH = "window_width";
/*     */   private static final String CONF_HEIGHT = "window_height";
/*     */   private ConfigurationSet conf;
/*     */   private final UITopiaController controller;
/*     */   
/*     */   public UITopiaFrame(UITopiaController controller)
/*     */   {
/*  68 */     this.controller = controller;
/*     */     
/*     */ 
/*  71 */     setDefaultCloseOperation(0);
/*  72 */     addWindowListener(new WindowAdapter() {
/*     */       public void windowClosing(WindowEvent e) {
/*  74 */         UITopiaFrame.this.exitApplication(true);
/*     */       }
/*     */       
/*     */       public void windowClosed(WindowEvent e) {
/*  78 */         windowClosing(e);
/*     */       }
/*  80 */     });
/*  81 */     addComponentListener(new ComponentAdapter() {
/*     */       public void componentMoved(ComponentEvent e) {
/*  83 */         UITopiaFrame.this.saveWindowState();
/*     */       }
/*     */       
/*     */       public void componentResized(ComponentEvent e) {
/*  87 */         UITopiaFrame.this.saveWindowState();
/*     */       }
/*     */       
/*  90 */     });
/*  91 */     this.conf = UIConfiguration.master().getChild(getClass().getCanonicalName());
/*     */     
/*  93 */     restoreWindowState();
/*     */     
/*  95 */     setLayout(new BorderLayout());
/*  96 */     add(controller.getMainView(), "Center");
/*  97 */     setTitle("ProM UITopia");
/*     */   }
/*     */   
/*     */   protected void saveWindowState()
/*     */   {
/* 102 */     Point p = getLocation();
/* 103 */     this.conf.setInteger("window_x", p.x);
/* 104 */     this.conf.setInteger("window_y", p.y);
/* 105 */     this.conf.setInteger("window_width", getWidth());
/* 106 */     this.conf.setInteger("window_height", getHeight());
/*     */   }
/*     */   
/*     */   protected void restoreWindowState() {
/* 110 */     int x = this.conf.getInteger("window_x", 10);
/* 111 */     int y = this.conf.getInteger("window_y", 10);
/* 112 */     int width = this.conf.getInteger("window_width", 1024);
/* 113 */     int height = this.conf.getInteger("window_height", 750);
/* 114 */     x = Math.max(0, x);
/* 115 */     y = Math.max(0, y);
/* 116 */     width = Math.min(width, Toolkit.getDefaultToolkit().getScreenSize().width);
/*     */     
/* 118 */     height = Math.min(height, Toolkit.getDefaultToolkit().getScreenSize().height);
/*     */     
/* 120 */     setLocation(x, y);
/* 121 */     setSize(width, height);
/*     */   }
/*     */   
/*     */   protected void exitApplication(boolean askUser) {
/*     */     try {
/* 126 */       UIConfiguration.save();
/* 127 */       this.controller.getFrameworkHub().shutdown(null);
/*     */     } catch (IOException e) {
/* 129 */       System.err.println("ERROR: Could not save UITopia configuration!");
/* 130 */       e.printStackTrace();
/*     */     }
/* 132 */     System.exit(0);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/UITopiaFrame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */