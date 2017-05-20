/*     */ package org.deckfour.uitopia.ui.test.resources;
/*     */ 
/*     */ import com.fluxicon.slickerbox.util.GraphicsUtilities;
/*     */ import java.awt.Image;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Date;
/*     */ import java.util.Random;
/*     */ import org.deckfour.uitopia.api.model.Action;
/*     */ import org.deckfour.uitopia.api.model.Resource;
/*     */ import org.deckfour.uitopia.api.model.ResourceType;
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
/*     */ public class TestResource
/*     */   implements Resource
/*     */ {
/*     */   private final Date creationTime;
/*     */   private Date lastAccessTime;
/*     */   private String name;
/*     */   private ResourceType type;
/*     */   private boolean favorite;
/*     */   private Action sourceAction;
/*  59 */   private boolean destroyed = false;
/*     */   
/*     */   public TestResource(String name, Date creationTime, ResourceType type, Action sourceAction)
/*     */   {
/*  63 */     this.creationTime = creationTime;
/*  64 */     this.lastAccessTime = new Date(System.currentTimeMillis() + new Random().nextInt(Integer.MAX_VALUE));
/*     */     
/*  66 */     this.name = name;
/*  67 */     this.type = type;
/*  68 */     this.favorite = false;
/*  69 */     this.sourceAction = sourceAction;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void destroy()
/*     */   {
/*  78 */     System.out.println("destroyed " + this.name + ".");
/*  79 */     this.destroyed = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getCreationTime()
/*     */   {
/*  88 */     return this.creationTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getLastAccessTime()
/*     */   {
/*  97 */     return this.lastAccessTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 106 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Image getPreview(int maxWidth, int maxHeight)
/*     */   {
/* 115 */     BufferedImage image = ImageLoader.load("TEST_IMAGE.png");
/* 116 */     int imgWidth = image.getWidth();
/* 117 */     int imgHeight = image.getHeight();
/* 118 */     double wFactor = maxWidth / imgWidth;
/* 119 */     double hFactor = maxHeight / imgHeight;
/* 120 */     if ((wFactor < 1.0D) || (hFactor < 1.0D)) {
/* 121 */       double factor = Math.min(wFactor, hFactor);
/* 122 */       int width = (int)(factor * imgWidth);
/* 123 */       int height = (int)(factor * imgHeight);
/* 124 */       return GraphicsUtilities.createThumbnailFast(image, width, height);
/*     */     }
/* 126 */     return image;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Action getSourceAction()
/*     */   {
/* 136 */     return this.sourceAction;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ResourceType getType()
/*     */   {
/* 145 */     return this.type;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isFavorite()
/*     */   {
/* 154 */     return this.favorite;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFavorite(boolean favorite)
/*     */   {
/* 163 */     this.favorite = favorite;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 172 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void updateLastAccessTime()
/*     */   {
/* 181 */     this.lastAccessTime = new Date();
/*     */   }
/*     */   
/*     */   public boolean isDestroyed()
/*     */   {
/* 186 */     return this.destroyed;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/test/resources/TestResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */