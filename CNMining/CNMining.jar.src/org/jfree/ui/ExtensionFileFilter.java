/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.io.File;
/*     */ import javax.swing.filechooser.FileFilter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExtensionFileFilter
/*     */   extends FileFilter
/*     */ {
/*     */   private String description;
/*     */   private String extension;
/*     */   
/*     */   public ExtensionFileFilter(String description, String extension)
/*     */   {
/*  69 */     this.description = description;
/*  70 */     this.extension = extension;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean accept(File file)
/*     */   {
/*  82 */     if (file.isDirectory()) {
/*  83 */       return true;
/*     */     }
/*     */     
/*  86 */     String name = file.getName().toLowerCase();
/*  87 */     if (name.endsWith(this.extension)) {
/*  88 */       return true;
/*     */     }
/*     */     
/*  91 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 102 */     return this.description;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/ui/ExtensionFileFilter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */