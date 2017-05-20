/*    */ package org.jfree.base.log;
/*    */ 
/*    */ import org.jfree.base.modules.AbstractModule;
/*    */ import org.jfree.base.modules.ModuleInitializeException;
/*    */ import org.jfree.base.modules.SubSystem;
/*    */ import org.jfree.util.Configuration;
/*    */ import org.jfree.util.Log;
/*    */ import org.jfree.util.PrintStreamLogTarget;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultLogModule
/*    */   extends AbstractModule
/*    */ {
/*    */   public DefaultLogModule()
/*    */     throws ModuleInitializeException
/*    */   {
/* 70 */     loadModuleInfo();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void initialize(SubSystem subSystem)
/*    */     throws ModuleInitializeException
/*    */   {
/* 83 */     if (LogConfiguration.isDisableLogging())
/*    */     {
/* 85 */       return;
/*    */     }
/*    */     
/* 88 */     if (LogConfiguration.getLogTarget().equals(PrintStreamLogTarget.class.getName()))
/*    */     {
/*    */ 
/* 91 */       DefaultLog.installDefaultLog();
/* 92 */       Log.getInstance().addTarget(new PrintStreamLogTarget());
/*    */       
/* 94 */       if ("true".equals(subSystem.getGlobalConfig().getConfigProperty("org.jfree.base.LogAutoInit")))
/*    */       {
/*    */ 
/* 97 */         Log.getInstance().init();
/*    */       }
/* 99 */       Log.info("Default log target started ... previous log messages could have been ignored.");
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/base/log/DefaultLogModule.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */