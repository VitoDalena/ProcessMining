/*     */ package org.deckfour.uitopia.ui.util;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.print.PageFormat;
/*     */ import java.awt.print.Printable;
/*     */ import java.awt.print.PrinterException;
/*     */ import java.awt.print.PrinterJob;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.RepaintManager;
/*     */ import org.deckfour.uitopia.api.model.View;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PrintUtils
/*     */   implements Printable
/*     */ {
/*     */   private View view;
/*     */   
/*     */   public PrintUtils(View view)
/*     */   {
/*  28 */     this.view = view;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void printView(View view)
/*     */   {
/*  37 */     new PrintUtils(view).print();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void print()
/*     */   {
/*  47 */     PrinterJob printerJob = PrinterJob.getPrinterJob();
/*     */     
/*     */ 
/*     */ 
/*  51 */     printerJob.setPrintable(this);
/*     */     
/*     */ 
/*     */ 
/*  55 */     if (printerJob.printDialog())
/*     */     {
/*     */       try
/*     */       {
/*     */ 
/*  60 */         printerJob.print();
/*     */       } catch (PrinterException ex) {
/*  62 */         System.err.println("Error while printing: " + ex);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
/*     */     throws PrinterException
/*     */   {
/*  76 */     int result = 1;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  81 */     Rectangle2D bounds = this.view.getViewComponent().getBounds();
/*  82 */     double panelWidth = bounds.getWidth();
/*  83 */     double panelHeight = bounds.getHeight();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  88 */     double pageWidth = pageFormat.getImageableWidth();
/*  89 */     double pageHeight = pageFormat.getImageableHeight();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  95 */     double scale = pageWidth / panelWidth;
/*  96 */     int nofPages = (int)Math.ceil(scale * panelHeight / pageHeight);
/*     */     
/*     */ 
/*  99 */     if (pageIndex < nofPages)
/*     */     {
/*     */ 
/*     */ 
/* 103 */       RepaintManager.currentManager(this.view.getViewComponent()).setDoubleBufferingEnabled(false);
/*     */       
/*     */ 
/*     */ 
/* 107 */       Graphics2D graphics2d = (Graphics2D)graphics;
/*     */       
/*     */ 
/*     */ 
/* 111 */       graphics2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
/*     */       
/*     */ 
/*     */ 
/* 115 */       graphics2d.translate(0.0D, -pageIndex * pageHeight);
/*     */       
/*     */ 
/*     */ 
/* 119 */       graphics2d.scale(scale, scale);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 124 */       this.view.getViewComponent().paint(graphics2d);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 129 */       RepaintManager.currentManager(this.view.getViewComponent()).setDoubleBufferingEnabled(true);
/*     */       
/*     */ 
/*     */ 
/* 133 */       result = 0;
/*     */     }
/* 135 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/util/PrintUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */