/*     */ package org.deckfour.uitopia.ui.test.resources;
/*     */ 
/*     */ import com.fluxicon.slickerbox.util.GraphicsUtilities;
/*     */ import java.awt.Image;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.JComponent;
/*     */ import org.deckfour.uitopia.api.model.Resource;
/*     */ import org.deckfour.uitopia.api.model.View;
/*     */ import org.deckfour.uitopia.api.model.ViewType;
/*     */ import org.deckfour.uitopia.ui.util.ImageLoader;
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
/*     */ public class TestView
/*     */   implements View
/*     */ {
/*  53 */   private static final BufferedImage TEST_IMAGE = ImageLoader.load("TEST_IMAGE.png");
/*     */   private String name;
/*     */   private ViewType type;
/*     */   private Resource resource;
/*     */   
/*     */   public TestView(String name, ViewType type, Resource resource)
/*     */   {
/*  60 */     this.name = name;
/*  61 */     this.type = type;
/*  62 */     this.resource = resource;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void destroy()
/*     */   {
/*  69 */     System.out.println("View " + this.name + " destroyed.");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getCustomName()
/*     */   {
/*  76 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Image getPreview(int maxWidth, int maxHeight)
/*     */   {
/*  83 */     int imgWidth = TEST_IMAGE.getWidth();
/*  84 */     int imgHeight = TEST_IMAGE.getHeight();
/*  85 */     double wFactor = maxWidth / imgWidth;
/*  86 */     double hFactor = maxHeight / imgHeight;
/*  87 */     if ((wFactor < 1.0D) || (hFactor < 1.0D)) {
/*  88 */       double factor = Math.min(wFactor, hFactor);
/*  89 */       int width = (int)(factor * imgWidth);
/*  90 */       int height = (int)(factor * imgHeight);
/*  91 */       return GraphicsUtilities.createThumbnailFast(TEST_IMAGE, width, height);
/*     */     }
/*  93 */     return TEST_IMAGE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Resource getResource()
/*     */   {
/* 101 */     return this.resource;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ViewType getType()
/*     */   {
/* 108 */     return this.type;
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
/*     */   public JComponent getViewComponent()
/*     */   {
/* 123 */     return new TestPanel();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setCustomName(String name)
/*     */   {
/* 130 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void captureNow() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void refresh() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isReady()
/*     */   {
/* 145 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/test/resources/TestView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */