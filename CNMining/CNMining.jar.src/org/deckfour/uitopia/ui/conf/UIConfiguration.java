/*    */ package org.deckfour.uitopia.ui.conf;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import org.deckfour.uitopia.ui.util.RuntimeUtils;
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
/*    */ public class UIConfiguration
/*    */ {
/*    */   private static final String FILE_NAME = "uitopiaconfig.xml";
/* 48 */   private static final UIConfiguration configuration = new UIConfiguration();
/*    */   private ConfigurationSet configSet;
/*    */   
/* 51 */   public static ConfigurationSet master() { return configuration.configSet; }
/*    */   
/*    */   public static void save() throws IOException
/*    */   {
/* 55 */     String path = RuntimeUtils.getSupportFolder() + "uitopiaconfig.xml";
/* 56 */     ConfigurationSerializer.serialize(configuration.configSet, new File(path));
/*    */   }
/*    */   
/*    */ 
/*    */   private UIConfiguration()
/*    */   {
/* 62 */     String path = RuntimeUtils.getSupportFolder() + "uitopiaconfig.xml";
/*    */     try {
/* 64 */       this.configSet = ConfigurationParser.parse(new File(path));
/*    */     } catch (Exception e) {
/* 66 */       this.configSet = new ConfigurationSet("master");
/* 67 */       System.err.println("WARNING: No configuration found for UITopia!");
/* 68 */       System.err.println("(This is okay if you start the application for the first time)");
/* 69 */       System.err.println("Creating new configuration file at " + path + "...");
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/conf/UIConfiguration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */