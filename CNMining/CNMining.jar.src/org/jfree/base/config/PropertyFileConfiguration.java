/*     */ package org.jfree.base.config;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Properties;
/*     */ import org.jfree.util.Log;
/*     */ import org.jfree.util.ObjectUtilities;
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
/*     */ public class PropertyFileConfiguration
/*     */   extends HierarchicalConfiguration
/*     */ {
/*     */   public void load(String resourceName)
/*     */   {
/*  72 */     load(resourceName, PropertyFileConfiguration.class);
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
/*     */   public void load(String resourceName, Class resourceSource)
/*     */   {
/*  85 */     InputStream in = ObjectUtilities.getResourceRelativeAsStream(resourceName, resourceSource);
/*     */     
/*  87 */     if (in != null)
/*     */     {
/*     */       try
/*     */       {
/*  91 */         load(in);
/*     */       }
/*     */       finally
/*     */       {
/*     */         try
/*     */         {
/*  97 */           in.close();
/*     */         }
/*     */         catch (IOException e) {}
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 107 */     Log.debug("Configuration file not found in the classpath: " + resourceName);
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
/*     */   public void load(InputStream in)
/*     */   {
/* 121 */     if (in == null)
/*     */     {
/* 123 */       throw new NullPointerException();
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 128 */       BufferedInputStream bin = new BufferedInputStream(in);
/* 129 */       Properties p = new Properties();
/* 130 */       p.load(bin);
/* 131 */       getConfiguration().putAll(p);
/* 132 */       bin.close();
/*     */     }
/*     */     catch (IOException ioe)
/*     */     {
/* 136 */       Log.warn("Unable to read configuration", ioe);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/base/config/PropertyFileConfiguration.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */