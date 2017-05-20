/*     */ package org.processmining.plugins.bk;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.nio.file.FileSystem;
/*     */ import java.nio.file.FileSystems;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.OpenOption;
/*     */ import java.nio.file.StandardOpenOption;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExtractorManager
/*     */ {
/*     */   public void extractConstraints(String name, WorkflowConstraintExtractor constrExtractor, int P_perc, int E_perc, int NP_perc, int NE_perc, int i)
/*     */     throws IOException
/*     */   {
/*  21 */     if (name == null)
/*  22 */       name = "Constraints";
/*  23 */     File file = new File("/Users/flupia/Desktop/sintetici/logsREVISION/constraints/" + name + "_P_" + P_perc + "_E_" + E_perc + "_NP_" + NP_perc + "_NE_" + NE_perc + "_Run_" + i + ".xml");
/*  24 */     if (file.exists())
/*  25 */       file.delete();
/*     */     try {
/*  27 */       file.createNewFile();
/*     */     }
/*     */     catch (IOException e1) {
/*  30 */       e1.printStackTrace();
/*     */     }
/*     */     
/*     */ 
/*  34 */     constrExtractor.setP_perc(P_perc);
/*  35 */     constrExtractor.setE_perc(E_perc);
/*     */     
/*  37 */     constrExtractor.setNP_perc(NP_perc);
/*  38 */     constrExtractor.setNE_perc(NE_perc);
/*     */     
/*  40 */     constrExtractor.extractConstraints();
/*     */     
/*  42 */     String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<Constraint_Set process_ID=\"idProcesso\"\nxmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\nxsi:noNamespaceSchemaLocation=\"ConstraintSchema.xsd\">\n";
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  48 */     DependencySet sampled_trueDeps = constrExtractor.getSampled_trueDeps();
/*  49 */     DependencySet sampled_negTrueDeps = constrExtractor.getSampled_negTrueDeps();
/*  50 */     DependencySet sampled_truePathDeps = constrExtractor.getSampled_truePathDeps();
/*  51 */     DependencySet sampled_negTruePathDeps = constrExtractor.getSampled_negTruePathDeps();
/*     */     
/*  53 */     for (Dependency d : sampled_trueDeps)
/*     */     {
/*  55 */       content = 
/*     */       
/*     */ 
/*  58 */         content + "<Constraint type=\"Edge\">\n<Head>" + d.getTo() + "</Head>\n" + "<Body>" + d.getFrom() + "</Body>\n" + "</Constraint>\n";
/*     */     }
/*     */     
/*  61 */     for (Dependency d : sampled_truePathDeps) {
/*  62 */       content = 
/*     */       
/*     */ 
/*  65 */         content + "<Constraint type=\"Path\">\n<Head>" + d.getTo() + "</Head>\n" + "<Body>" + d.getFrom() + "</Body>\n" + "</Constraint>\n";
/*     */     }
/*     */     
/*  68 */     for (Dependency d : sampled_negTrueDeps) {
/*  69 */       content = 
/*     */       
/*     */ 
/*  72 */         content + "<Constraint type=\"notEdge\">\n<Head>" + d.getTo() + "</Head>\n" + "<Body>" + d.getFrom() + "</Body>\n" + "</Constraint>\n";
/*     */     }
/*     */     
/*  75 */     for (Dependency d : sampled_negTruePathDeps) {
/*  76 */       content = 
/*     */       
/*     */ 
/*  79 */         content + "<Constraint type=\"notPath\">\n<Head>" + d.getTo() + "</Head>\n" + "<Body>" + d.getFrom() + "</Body>\n" + "</Constraint>\n";
/*     */     }
/*  81 */     content = content + "</Constraint_Set>";
/*     */     try
/*     */     {
/*  84 */       Files.write(FileSystems.getDefault().getPath("/Users/flupia/Desktop/sintetici/logsREVISION/constraints/", new String[] { name + "_P_" + P_perc + "_E_" + E_perc + "_NP_" + NP_perc + "_NE_" + NE_perc + "_Run_" + i + ".xml" }), content.getBytes(), new OpenOption[] {
/*  85 */         StandardOpenOption.APPEND });
/*     */     } catch (IOException ioe) {
/*  87 */       ioe.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public int[] extractConstraintsSyntethicsSize(WorkflowConstraintExtractor constrExtractor, int P_perc, int E_perc, int NP_perc, int NE_perc, int i)
/*     */     throws IOException
/*     */   {
/*  95 */     constrExtractor.setP_perc(P_perc);
/*  96 */     constrExtractor.setE_perc(E_perc);
/*     */     
/*  98 */     constrExtractor.setNP_perc(NP_perc);
/*  99 */     constrExtractor.setNE_perc(NE_perc);
/*     */     
/* 101 */     constrExtractor.extractConstraints();
/*     */     
/* 103 */     DependencySet sampled_trueDeps = constrExtractor.getSampled_trueDeps();
/* 104 */     DependencySet sampled_negTrueDeps = constrExtractor.getSampled_negTrueDeps();
/* 105 */     DependencySet sampled_truePathDeps = constrExtractor.getSampled_truePathDeps();
/* 106 */     DependencySet sampled_negTruePathDeps = constrExtractor.getSampled_negTruePathDeps();
/*     */     
/* 108 */     return new int[] { sampled_truePathDeps.size(), sampled_trueDeps.size(), sampled_negTruePathDeps.size(), sampled_negTrueDeps.size() };
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/plugins/bk/ExtractorManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */