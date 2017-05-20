/*     */ package org.jfree.chart;
/*     */ 
/*     */ import java.awt.datatransfer.DataFlavor;
/*     */ import java.awt.datatransfer.Transferable;
/*     */ import java.awt.datatransfer.UnsupportedFlavorException;
/*     */ import java.io.IOException;
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
/*     */ public class ChartTransferable
/*     */   implements Transferable
/*     */ {
/*  56 */   final DataFlavor imageFlavor = new DataFlavor("image/x-java-image; class=java.awt.Image", "Image");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private JFreeChart chart;
/*     */   
/*     */ 
/*     */ 
/*     */   private int width;
/*     */   
/*     */ 
/*     */ 
/*     */   private int height;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ChartTransferable(JFreeChart chart, int width, int height)
/*     */   {
/*  76 */     this(chart, width, height, true);
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
/*     */   public ChartTransferable(JFreeChart chart, int width, int height, boolean cloneData)
/*     */   {
/*     */     try
/*     */     {
/*  94 */       this.chart = ((JFreeChart)chart.clone());
/*     */     }
/*     */     catch (CloneNotSupportedException e) {
/*  97 */       this.chart = chart;
/*     */     }
/*  99 */     this.width = width;
/* 100 */     this.height = height;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DataFlavor[] getTransferDataFlavors()
/*     */   {
/* 111 */     return new DataFlavor[] { this.imageFlavor };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDataFlavorSupported(DataFlavor flavor)
/*     */   {
/* 122 */     return this.imageFlavor.equals(flavor);
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
/*     */   public Object getTransferData(DataFlavor flavor)
/*     */     throws UnsupportedFlavorException, IOException
/*     */   {
/* 138 */     if (this.imageFlavor.equals(flavor)) {
/* 139 */       return this.chart.createBufferedImage(this.width, this.height);
/*     */     }
/*     */     
/* 142 */     throw new UnsupportedFlavorException(flavor);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/ChartTransferable.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */