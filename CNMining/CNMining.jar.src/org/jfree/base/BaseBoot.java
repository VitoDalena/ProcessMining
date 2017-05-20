/*     */ package org.jfree.base;
/*     */ 
/*     */ import org.jfree.JCommon;
/*     */ import org.jfree.base.config.ModifiableConfiguration;
/*     */ import org.jfree.base.log.DefaultLogModule;
/*     */ import org.jfree.base.modules.PackageManager;
/*     */ import org.jfree.util.Configuration;
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
/*     */ public class BaseBoot
/*     */   extends AbstractBoot
/*     */ {
/*     */   private static BaseBoot singleton;
/*     */   private BootableProjectInfo bootableProjectInfo;
/*     */   
/*     */   private BaseBoot()
/*     */   {
/*  73 */     this.bootableProjectInfo = JCommon.INFO;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ModifiableConfiguration getConfiguration()
/*     */   {
/*  82 */     return (ModifiableConfiguration)getInstance().getGlobalConfig();
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
/*     */   protected synchronized Configuration loadConfiguration()
/*     */   {
/*  98 */     return createDefaultHierarchicalConfiguration("/org/jfree/base/jcommon.properties", "/jcommon.properties", true, BaseBoot.class);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static synchronized AbstractBoot getInstance()
/*     */   {
/* 109 */     if (singleton == null) {
/* 110 */       singleton = new BaseBoot();
/*     */     }
/* 112 */     return singleton;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void performBoot()
/*     */   {
/* 120 */     ObjectUtilities.setClassLoaderSource(getConfiguration().getConfigProperty("org.jfree.ClassLoader"));
/*     */     
/*     */ 
/* 123 */     getPackageManager().addModule(DefaultLogModule.class.getName());
/* 124 */     getPackageManager().load("org.jfree.jcommon.modules.");
/* 125 */     getPackageManager().initializeModules();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected BootableProjectInfo getProjectInfo()
/*     */   {
/* 134 */     return this.bootableProjectInfo;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/base/BaseBoot.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */