/*     */ package org.processmining.framework.packages;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.SortedSet;
/*     */ import org.processmining.framework.util.AutoHelpCommandLineParser.Command;
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
/*     */ class ListCommand
/*     */   extends AutoHelpCommandLineParser.Command
/*     */ {
/*     */   private final PackageManager manager;
/*     */   
/*     */   public ListCommand(PackageManager manager)
/*     */   {
/* 173 */     super("list", "List all known packages and their status (A=available,I=installed,B=broken,+=has update)");
/* 174 */     this.manager = manager;
/*     */   }
/*     */   
/*     */   public int run(List<String> args) throws Exception
/*     */   {
/* 179 */     Set<PackageDescriptor> installed = this.manager.getInstalledPackages();
/* 180 */     Collection<PackageDescriptor> enabled = this.manager.getEnabledPackages();
/*     */     
/* 182 */     Set<PackageDescriptor> all = new HashSet(this.manager.getInstalledPackages());
/* 183 */     all.addAll(this.manager.getAvailablePackages());
/*     */     
/* 185 */     for (Map.Entry<String, SortedSet<PackageDescriptor>> item : PackageManager.getPackageMap(all).entrySet()) {
/* 186 */       PackageDescriptor installedPackage = null;
/* 187 */       PackageVersion highestVersion = null;
/* 188 */       boolean isEnabled = false;
/* 189 */       String versions = "";
/*     */       
/* 191 */       for (PackageDescriptor pack : (SortedSet)item.getValue()) {
/* 192 */         if (installed.contains(pack)) {
/* 193 */           installedPackage = pack;
/*     */         }
/* 195 */         if (enabled.contains(pack)) {
/* 196 */           isEnabled = true;
/*     */         }
/* 198 */         if (versions.length() > 0) {
/* 199 */           versions = versions + ", ";
/*     */         }
/* 201 */         versions = versions + pack.getVersion();
/* 202 */         highestVersion = pack.getVersion();
/*     */       }
/*     */       
/* 205 */       if (installedPackage == null) {
/* 206 */         System.out.println("A  " + (String)item.getKey() + " [" + versions + "]");
/* 207 */       } else if (isEnabled) {
/* 208 */         System.out.println("I" + (installedPackage.getVersion().lessThan(highestVersion) ? "+" : " ") + " " + installedPackage + " [" + versions + "]");
/*     */       }
/*     */       else {
/* 211 */         System.out.println("B" + (installedPackage.getVersion().lessThan(highestVersion) ? "+" : " ") + " " + installedPackage + " [" + versions + "]");
/*     */       }
/*     */     }
/*     */     
/* 215 */     return 0;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/framework/packages/ListCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */