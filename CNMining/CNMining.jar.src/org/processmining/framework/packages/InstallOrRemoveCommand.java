/*     */ package org.processmining.framework.packages;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.SortedSet;
/*     */ import org.processmining.framework.util.AutoHelpCommandLineParser.Command;
/*     */ import org.processmining.framework.util.Pair;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class InstallOrRemoveCommand
/*     */   extends AutoHelpCommandLineParser.Command
/*     */ {
/*     */   private final PackageManager manager;
/*     */   
/*     */   public InstallOrRemoveCommand(PackageManager manager)
/*     */   {
/*  89 */     super("change", "Install the packages preceded by +, remove packages preceded by x (example: change +packageA:1.0 xpackageB)");
/*     */     
/*  91 */     this.manager = manager;
/*     */   }
/*     */   
/*     */   public int run(List<String> args) throws Exception
/*     */   {
/*  96 */     List<PackageDescriptor> toInstall = new ArrayList();
/*  97 */     List<PackageDescriptor> toRemove = new ArrayList();
/*     */     
/*  99 */     Set<PackageDescriptor> all = new HashSet(this.manager.getInstalledPackages());
/* 100 */     all.addAll(this.manager.getAvailablePackages());
/* 101 */     Map<String, SortedSet<PackageDescriptor>> map = PackageManager.getPackageMap(all);
/*     */     
/* 103 */     for (String packageNameAndAction : args) {
/* 104 */       if ((packageNameAndAction.length() > 1) && ((packageNameAndAction.charAt(0) == 'x') || (packageNameAndAction.charAt(0) == '+')))
/*     */       {
/* 106 */         Pair<String, PackageVersion> packageName = parse(packageNameAndAction.substring(1));
/* 107 */         boolean install = packageNameAndAction.charAt(0) == '+';
/* 108 */         Set<PackageDescriptor> versions = (Set)map.get(packageName.getFirst());
/* 109 */         PackageDescriptor p = null;
/*     */         
/* 111 */         if ((versions != null) && (!versions.isEmpty())) {
/* 112 */           if (packageName.getSecond() == null)
/*     */           {
/* 114 */             p = ((PackageDescriptor[])versions.toArray(new PackageDescriptor[0]))[(versions.size() - 1)];
/*     */           } else {
/* 116 */             for (PackageDescriptor v : versions) {
/* 117 */               if (v.getVersion().equals(packageName.getSecond())) {
/* 118 */                 p = v;
/* 119 */                 break;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 125 */         if (p == null) {
/* 126 */           System.out.println("Could not find package " + packageNameAndAction.substring(1) + " in the list of available packages, skipping.");
/*     */         }
/*     */         else {
/* 129 */           System.out.println("Selected " + p + " for " + (install ? "installation" : "removal") + "...");
/* 130 */           if (install) {
/* 131 */             toInstall.add(p);
/*     */           } else {
/* 133 */             toRemove.add(p);
/*     */           }
/*     */         }
/*     */       } else {
/* 137 */         System.out.println("Invalid package specification (please use + or - to indicate installation or removal), skipping.");
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 142 */     if ((toInstall.isEmpty()) && (toRemove.isEmpty())) {
/* 143 */       System.out.println("Nothing to install or remove.");
/*     */     } else {
/* 145 */       System.out.println("Starting installation...");
/* 146 */       this.manager.install(toInstall);
/* 147 */       this.manager.uninstall(toRemove);
/* 148 */       PackageStateReport report = this.manager.getLatestReport();
/*     */       
/* 150 */       System.out.print(report);
/* 151 */       System.out.println(report.hasErrors() ? "Installation is NOT performed." : "Installation done.");
/*     */     }
/* 153 */     return 0;
/*     */   }
/*     */   
/*     */   private Pair<String, PackageVersion> parse(String name) {
/* 157 */     int hyphen = name.indexOf(':');
/*     */     
/* 159 */     if (hyphen >= 0) {
/* 160 */       return new Pair(name.substring(0, hyphen), new PackageVersion(name.substring(hyphen + 1)));
/*     */     }
/*     */     
/* 163 */     return new Pair(name, null);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/framework/packages/InstallOrRemoveCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */