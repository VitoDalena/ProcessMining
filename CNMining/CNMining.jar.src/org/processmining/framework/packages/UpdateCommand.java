/*    */ package org.processmining.framework.packages;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.List;
/*    */ import org.processmining.framework.boot.Boot.Level;
/*    */ import org.processmining.framework.util.AutoHelpCommandLineParser.Command;
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
/*    */ 
/*    */ class UpdateCommand
/*    */   extends AutoHelpCommandLineParser.Command
/*    */ {
/*    */   private final PackageManager manager;
/*    */   
/*    */   public UpdateCommand(PackageManager manager)
/*    */   {
/* 70 */     super("update", "Retrieve the latest package definitions from all repositories");
/* 71 */     this.manager = manager;
/*    */   }
/*    */   
/*    */   public int run(List<String> args) throws Exception
/*    */   {
/* 76 */     System.out.println("Updating...");
/* 77 */     this.manager.update(true, Boot.Level.ALL);
/* 78 */     System.out.println("Done.");
/* 79 */     return 0;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/framework/packages/UpdateCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */