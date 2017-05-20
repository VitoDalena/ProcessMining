/*     */ package org.processmining.plugins.bk;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.TreeMap;
/*     */ import java.util.TreeSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorkflowConstraintExtractor
/*     */ {
/*  23 */   public static boolean RANDOM = false;
/*  24 */   public static boolean DROP_SUFFIX = true;
/*  25 */   public static int START_TASK_NR = 0;
/*     */   
/*  27 */   private int E_perc = 0; private int NE_perc = 0; private int P_perc = 0; private int NP_perc = 0;
/*  28 */   private Map<String, Integer> taskMap = null;
/*  29 */   private Map<Integer, String> revTaskMap = null;
/*  30 */   private boolean[][] trueDepGraph = null;
/*  31 */   private Map<String, Integer> taskMap2print = null;
/*     */   
/*  33 */   private DependencySet trueDeps = null;
/*     */   
/*     */   public static boolean isRANDOM() {
/*  36 */     return RANDOM;
/*     */   }
/*     */   
/*  39 */   public static void setRANDOM(boolean rANDOM) { RANDOM = rANDOM; }
/*     */   
/*     */   public DependencySet getTrueDeps() {
/*  42 */     return this.trueDeps;
/*     */   }
/*     */   
/*  45 */   public void setTrueDeps(DependencySet trueDeps) { this.trueDeps = trueDeps; }
/*     */   
/*     */   public DependencySet getNegTrueDeps() {
/*  48 */     return this.negTrueDeps;
/*     */   }
/*     */   
/*  51 */   public void setNegTrueDeps(DependencySet negTrueDeps) { this.negTrueDeps = negTrueDeps; }
/*     */   
/*     */   public DependencySet getTruePathDeps() {
/*  54 */     return this.truePathDeps;
/*     */   }
/*     */   
/*  57 */   public void setTruePathDeps(DependencySet truePathDeps) { this.truePathDeps = truePathDeps; }
/*     */   
/*     */   public DependencySet getNegTruePathDeps() {
/*  60 */     return this.negTruePathDeps;
/*     */   }
/*     */   
/*  63 */   public void setNegTruePathDeps(DependencySet negTruePathDeps) { this.negTruePathDeps = negTruePathDeps; }
/*     */   
/*     */   public DependencySet getSampled_trueDeps() {
/*  66 */     return this.sampled_trueDeps;
/*     */   }
/*     */   
/*  69 */   public void setSampled_trueDeps(DependencySet sampled_trueDeps) { this.sampled_trueDeps = sampled_trueDeps; }
/*     */   
/*     */   public DependencySet getSampled_negTrueDeps() {
/*  72 */     return this.sampled_negTrueDeps;
/*     */   }
/*     */   
/*  75 */   public void setSampled_negTrueDeps(DependencySet sampled_negTrueDeps) { this.sampled_negTrueDeps = sampled_negTrueDeps; }
/*     */   
/*     */   public DependencySet getSampled_truePathDeps() {
/*  78 */     return this.sampled_truePathDeps;
/*     */   }
/*     */   
/*  81 */   public void setSampled_truePathDeps(DependencySet sampled_truePathDeps) { this.sampled_truePathDeps = sampled_truePathDeps; }
/*     */   
/*     */   public DependencySet getSampled_negTruePathDeps() {
/*  84 */     return this.sampled_negTruePathDeps;
/*     */   }
/*     */   
/*  87 */   public void setSampled_negTruePathDeps(DependencySet sampled_negTruePathDeps) { this.sampled_negTruePathDeps = sampled_negTruePathDeps; }
/*     */   
/*     */ 
/*  90 */   private DependencySet negTrueDeps = null;
/*  91 */   private DependencySet truePathDeps = null;
/*  92 */   private DependencySet negTruePathDeps = null;
/*     */   
/*  94 */   private DependencySet sampled_trueDeps = null;
/*  95 */   private DependencySet sampled_negTrueDeps = null;
/*  96 */   private DependencySet sampled_truePathDeps = null;
/*  97 */   private DependencySet sampled_negTruePathDeps = null;
/*  98 */   private boolean extracted = false;
/*     */   
/*     */   public WorkflowConstraintExtractor(String fileName)
/*     */     throws Exception
/*     */   {
/* 103 */     this.taskMap2print = null;
/* 104 */     loadGraph(fileName);
/* 105 */     deriveConstraintsFromGraph(this.trueDepGraph);
/*     */   }
/*     */   
/* 108 */   public WorkflowConstraintExtractor(boolean[][] trueDepGraph, Map<String, Integer> folded_map, Map<Integer, String> reverse_folded_map) { this.trueDepGraph = trueDepGraph;
/* 109 */     this.taskMap = folded_map;
/* 110 */     this.revTaskMap = reverse_folded_map;
/* 111 */     deriveConstraintsFromGraph(this.trueDepGraph);
/*     */   }
/*     */   
/* 114 */   public List<AdHocConstraint> getEdgeConstr() { List<AdHocConstraint> res = new ArrayList();
/* 115 */     for (Dependency d : this.sampled_trueDeps) {
/* 116 */       TreeSet<Integer> body = new TreeSet();
/* 117 */       body.add(Integer.valueOf(getTaskID2print(d.getFrom())));
/* 118 */       int head = getTaskID2print(d.getTo());
/* 119 */       res.add(new AdHocConstraint(Integer.valueOf(head), body));
/*     */     }
/* 121 */     return res;
/*     */   }
/*     */   
/* 124 */   public List<AdHocConstraint> getNegEdgeConstr() { List<AdHocConstraint> res = new ArrayList();
/* 125 */     for (Dependency d : this.sampled_negTrueDeps) {
/* 126 */       TreeSet<Integer> body = new TreeSet();
/* 127 */       body.add(Integer.valueOf(getTaskID2print(d.getFrom())));
/* 128 */       int head = getTaskID2print(d.getTo());
/* 129 */       res.add(new AdHocConstraint(Integer.valueOf(head), body));
/*     */     }
/* 131 */     return res;
/*     */   }
/*     */   
/* 134 */   public List<AdHocConstraint> getPathConstr() { List<AdHocConstraint> res = new ArrayList();
/* 135 */     for (Dependency d : this.sampled_truePathDeps) {
/* 136 */       TreeSet<Integer> body = new TreeSet();
/* 137 */       body.add(Integer.valueOf(getTaskID2print(d.getFrom())));
/* 138 */       int head = getTaskID2print(d.getTo());
/* 139 */       res.add(new AdHocConstraint(Integer.valueOf(head), body));
/*     */     }
/* 141 */     return res;
/*     */   }
/*     */   
/* 144 */   public List<AdHocConstraint> getNegPathConstr() { List<AdHocConstraint> res = new ArrayList();
/* 145 */     for (Dependency d : this.sampled_negTruePathDeps) {
/* 146 */       TreeSet<Integer> body = new TreeSet();
/* 147 */       body.add(Integer.valueOf(getTaskID2print(d.getFrom())));
/* 148 */       int head = getTaskID2print(d.getTo());
/* 149 */       res.add(new AdHocConstraint(Integer.valueOf(head), body));
/*     */     }
/* 151 */     return res;
/*     */   }
/*     */   
/*     */   public void setTaskMap2print(TreeMap<String, Integer> taskMap2print) {
/* 155 */     this.taskMap2print = taskMap2print;
/*     */   }
/*     */   
/* 158 */   public boolean isValid() { return this.E_perc + this.NE_perc + this.P_perc + this.NP_perc > 0; }
/*     */   
/*     */   public void setE_perc(int E_perc) {
/* 161 */     if (E_perc != this.E_perc) this.extracted = false;
/* 162 */     this.E_perc = E_perc;
/*     */   }
/*     */   
/* 165 */   public void setNE_perc(int NE_perc) { if (NE_perc != this.NE_perc) this.extracted = false;
/* 166 */     this.NE_perc = NE_perc;
/*     */   }
/*     */   
/* 169 */   public void setP_perc(int P_perc) { if (P_perc != this.P_perc) this.extracted = false;
/* 170 */     this.P_perc = P_perc;
/*     */   }
/*     */   
/* 173 */   public void setNP_perc(int NP_perc) { if (NP_perc != this.NP_perc) this.extracted = false;
/* 174 */     this.NP_perc = NP_perc;
/*     */   }
/*     */   
/* 177 */   public int getE_perc() { return this.E_perc; }
/*     */   
/*     */   public int getNE_perc() {
/* 180 */     return this.NE_perc;
/*     */   }
/*     */   
/* 183 */   public int getP_perc() { return this.P_perc; }
/*     */   
/*     */ 
/* 186 */   public int getNP_perc() { return this.NP_perc; }
/*     */   
/*     */   private int getTaskID2print(String taskName) {
/* 189 */     if (this.taskMap2print == null) {
/* 190 */       return ((Integer)this.taskMap.get(taskName)).intValue();
/*     */     }
/* 192 */     return ((Integer)this.taskMap2print.get(taskName)).intValue();
/*     */   }
/*     */   
/*     */ 
/*     */   public void printModelGraph(String fileName)
/*     */   {
/*     */     try
/*     */     {
/* 200 */       FileWriter fw = new FileWriter(fileName + "_TrueModel" + ".dot");
/* 201 */       fw.write("digraph MODEL {\n");
/*     */       
/* 203 */       for (int i = 0; i < this.trueDepGraph.length; i++) {
/* 204 */         for (int j = 0; j < this.trueDepGraph[0].length; j++) {
/* 205 */           if (this.trueDepGraph[i][j] != 0) {
/* 206 */             fw.write("\t" + (String)this.revTaskMap.get(Integer.valueOf(i)) + "->" + (String)this.revTaskMap.get(Integer.valueOf(j)) + ";\n");
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 212 */       fw.write("}\n");
/* 213 */       fw.flush();
/* 214 */       fw.close();
/*     */     }
/*     */     catch (IOException e) {
/* 217 */       System.out.println(e);
/*     */     }
/*     */   }
/*     */   
/* 221 */   private void loadGraph(String fileName) { String line = null;
/*     */     
/* 223 */     this.taskMap = new TreeMap();
/*     */     
/*     */     try
/*     */     {
/* 227 */       BufferedReader in = new BufferedReader(new FileReader(fileName + ".map"));
/*     */       
/* 229 */       line = in.readLine();
/* 230 */       line = in.readLine();
/* 231 */       while ((line != null) && (line.length() > 0)) {
/* 232 */         StringTokenizer st = new StringTokenizer(line, "\t\b");
/* 233 */         String taskLabel = st.nextToken();
/* 234 */         String taskIDstr = st.nextToken();
/* 235 */         int lastChar = taskIDstr.length();
/* 236 */         if (DROP_SUFFIX) lastChar--;
/* 237 */         int taskNr = Integer.parseInt(taskIDstr.substring(0, lastChar)) - START_TASK_NR;
/* 238 */         this.taskMap.put(taskLabel, Integer.valueOf(taskNr));
/* 239 */         this.revTaskMap.put(Integer.valueOf(taskNr), taskLabel);
/* 240 */         line = in.readLine();
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 245 */       this.trueDepGraph = new boolean[this.taskMap.size()][this.taskMap.size()];
/*     */       
/* 247 */       in = new BufferedReader(new FileReader(fileName + ".dep"));
/* 248 */       for (int i = 0; i < this.taskMap.size(); i++) {
/* 249 */         line = in.readLine();
/* 250 */         StringTokenizer st = new StringTokenizer(line, "\t");
/* 251 */         for (int j = 0; j < this.taskMap.size(); j++) {
/* 252 */           String wStr = st.nextToken();
/* 253 */           double weight = new Double(wStr).doubleValue();
/* 254 */           this.trueDepGraph[i][j] = (weight > 0.0D ? 1 : 0);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 259 */       System.out.println(e);
/*     */     }
/*     */   }
/*     */   
/*     */   private void deriveConstraintsFromGraph(boolean[][] graphMat) {
/* 264 */     this.trueDeps = new DependencySet();
/* 265 */     this.truePathDeps = new DependencySet();
/* 266 */     this.negTrueDeps = new DependencySet();
/* 267 */     this.negTruePathDeps = new DependencySet();
/*     */     
/*     */ 
/* 270 */     int n = graphMat.length;
/* 271 */     boolean[][] transClos = new boolean[n][n];
/* 272 */     for (int i = 0; i < n; i++) {
/* 273 */       for (int j = 0; j < n; j++) {
/* 274 */         transClos[i][j] = graphMat[i][j];
/* 275 */         if (graphMat[i][j] != 0) {
/* 276 */           this.trueDeps.addDependency((String)this.revTaskMap.get(Integer.valueOf(i)), (String)this.revTaskMap.get(Integer.valueOf(j)), 1.0D);
/*     */         } else
/* 278 */           this.negTrueDeps.addDependency((String)this.revTaskMap.get(Integer.valueOf(i)), (String)this.revTaskMap.get(Integer.valueOf(j)), 1.0D);
/*     */       }
/*     */     }
/* 281 */     for (int k = 0; k < n; k++) {
/* 282 */       for (int i = 0; i < n; i++) {
/* 283 */         for (int j = 0; j < n; j++) {
/* 284 */           if ((transClos[i][k] != 0) && (transClos[k][j] != 0)) {
/* 285 */             transClos[i][j] = 1;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 291 */     for (int i = 0; i < n; i++) {
/* 292 */       for (int j = 0; j < n; j++) {
/* 293 */         if (transClos[i][j] != 0) {
/* 294 */           this.truePathDeps.addDependency((String)this.revTaskMap.get(Integer.valueOf(i)), (String)this.revTaskMap.get(Integer.valueOf(j)), 1.0D);
/*     */         }
/*     */         else {
/* 297 */           this.negTruePathDeps.addDependency((String)this.revTaskMap.get(Integer.valueOf(i)), (String)this.revTaskMap.get(Integer.valueOf(j)), 1.0D);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public int[] saveConstraintFile(String fileName) throws Exception {
/* 304 */     if (this.taskMap2print == null) { throw new Exception("Unitialized task map!!");
/*     */     }
/* 306 */     if (!this.extracted) extractConstraints();
/*     */     try
/*     */     {
/* 309 */       FileWriter out = new FileWriter(fileName);
/*     */       
/* 311 */       boolean printComments = true;
/* 312 */       for (int printIt = 0; printIt < 2; printIt++) {
/* 313 */         for (Dependency sampledDep : this.sampled_trueDeps) {
/* 314 */           int toTask_index = getTaskID2print(sampledDep.getTo());
/* 315 */           int fromTask_index = getTaskID2print(sampledDep.getFrom());
/* 316 */           if (!printComments) {
/* 317 */             out.write("E " + toTask_index + " " + fromTask_index + "\n");
/*     */           } else
/* 319 */             out.write("// " + sampledDep.getFrom() + " -> " + sampledDep.getTo() + "\n");
/*     */         }
/* 321 */         for (Dependency sampledDep : this.sampled_negTrueDeps) {
/* 322 */           int toTask_index = getTaskID2print(sampledDep.getTo());
/* 323 */           int fromTask_index = getTaskID2print(sampledDep.getFrom());
/* 324 */           if (!printComments) {
/* 325 */             out.write("NE " + toTask_index + " " + fromTask_index + "\n");
/*     */           } else {
/* 327 */             out.write("// � (" + sampledDep.getFrom() + " -> " + sampledDep.getTo() + ")\n");
/*     */           }
/*     */         }
/* 330 */         for (Dependency sampledDep : this.sampled_truePathDeps) {
/* 331 */           int toTask_index = getTaskID2print(sampledDep.getTo());
/* 332 */           int fromTask_index = getTaskID2print(sampledDep.getFrom());
/* 333 */           if (!printComments) {
/* 334 */             out.write("P " + toTask_index + " " + fromTask_index + "\n");
/*     */           } else
/* 336 */             out.write("// " + sampledDep.getFrom() + " --> " + sampledDep.getTo() + "\n");
/*     */         }
/* 338 */         for (Dependency sampledDep : this.sampled_negTruePathDeps) {
/* 339 */           int toTask_index = getTaskID2print(sampledDep.getTo());
/* 340 */           int fromTask_index = getTaskID2print(sampledDep.getFrom());
/* 341 */           if (!printComments) {
/* 342 */             out.write("NP " + toTask_index + " " + fromTask_index + "\n");
/*     */           } else
/* 344 */             out.write("// � (" + sampledDep.getFrom() + " --> " + sampledDep.getTo() + ")\n");
/*     */         }
/* 346 */         printComments = !printComments;
/*     */       }
/* 348 */       out.flush();
/* 349 */       out.close();
/*     */     }
/*     */     catch (Exception e) {
/* 352 */       System.out.println(e);
/*     */     }
/* 354 */     int[] res = { this.trueDeps.size(), this.negTrueDeps.size(), this.truePathDeps.size(), this.negTruePathDeps.size(), 
/* 355 */       this.sampled_trueDeps.size(), this.sampled_negTrueDeps.size(), this.sampled_truePathDeps.size(), this.sampled_negTruePathDeps.size() };
/*     */     
/* 357 */     return res;
/*     */   }
/*     */   
/*     */   public void extractConstraints() {
/* 361 */     this.sampled_trueDeps = new DependencySet();
/* 362 */     this.sampled_negTrueDeps = new DependencySet();
/* 363 */     this.sampled_truePathDeps = new DependencySet();
/* 364 */     this.sampled_negTruePathDeps = new DependencySet();
/* 365 */     this.extracted = true;
/* 366 */     if (RANDOM) {
/* 367 */       extractConstraints_Random();
/*     */     } else {
/* 369 */       extractConstraints_Deterministic();
/*     */     }
/*     */   }
/*     */   
/*     */   public void shuffle() {
/* 374 */     for (Dependency d : this.trueDeps) {
/* 375 */       System.out.println(d);
/*     */     }
/* 377 */     Collections.shuffle(this.trueDeps);
/* 378 */     System.out.println();
/*     */     
/* 380 */     for (Dependency d : this.trueDeps) {
/* 381 */       System.out.println(d);
/*     */     }
/*     */     
/* 384 */     Collections.shuffle(this.negTrueDeps);
/* 385 */     Collections.shuffle(this.truePathDeps);
/* 386 */     Collections.shuffle(this.negTruePathDeps);
/* 387 */     this.extracted = false;
/*     */   }
/*     */   
/*     */   private void extractConstraints_Random() {
/* 391 */     ArrayList<Dependency> E_sample = new ArrayList();
/* 392 */     ArrayList<Dependency> NE_sample = new ArrayList();
/* 393 */     ArrayList<Dependency> P_sample = new ArrayList();
/* 394 */     ArrayList<Dependency> NP_sample = new ArrayList();
/*     */     
/* 396 */     for (Dependency d : this.trueDeps) {
/* 397 */       E_sample.add(d);
/*     */     }
/* 399 */     for (Dependency d : this.negTrueDeps) {
/* 400 */       NE_sample.add(d);
/*     */     }
/* 402 */     for (Dependency d : this.truePathDeps) {
/* 403 */       P_sample.add(d);
/*     */     }
/* 405 */     for (Dependency d : this.negTruePathDeps) {
/* 406 */       NP_sample.add(d);
/*     */     }
/*     */     
/* 409 */     int E_size = (int)Math.ceil(this.E_perc * E_sample.size() / 100);
/* 410 */     int P_size = (int)Math.ceil(this.P_perc * P_sample.size() / 100);
/* 411 */     int NE_size = (int)Math.ceil(this.NE_perc * NE_sample.size() / 100);
/* 412 */     int NP_size = (int)Math.ceil(this.NP_perc * NP_sample.size() / 100);
/*     */     
/* 414 */     for (int i = 0; i < E_size; i++) {
/* 415 */       int k = (int)(Math.random() * E_sample.size());
/* 416 */       Dependency sampledDep = (Dependency)E_sample.get(k);
/* 417 */       this.sampled_trueDeps.add(sampledDep);
/* 418 */       E_sample.remove(k);
/*     */     }
/* 420 */     for (int i = 0; i < NE_size; i++) {
/* 421 */       int k = (int)(Math.random() * NE_sample.size());
/* 422 */       Dependency sampledDep = (Dependency)NE_sample.get(k);
/* 423 */       this.sampled_negTrueDeps.add(sampledDep);
/* 424 */       NE_sample.remove(k);
/*     */     }
/* 426 */     for (int i = 0; i < P_size; i++) {
/* 427 */       int k = (int)(Math.random() * P_sample.size());
/* 428 */       Dependency sampledDep = (Dependency)P_sample.get(k);
/* 429 */       this.sampled_truePathDeps.add(sampledDep);
/* 430 */       P_sample.remove(k);
/*     */     }
/* 432 */     for (int i = 0; i < NP_size; i++) {
/* 433 */       int k = (int)(Math.random() * NP_sample.size());
/* 434 */       Dependency sampledDep = (Dependency)NP_sample.get(k);
/* 435 */       this.sampled_negTruePathDeps.add(sampledDep);
/* 436 */       NP_sample.remove(k);
/*     */     }
/*     */   }
/*     */   
/*     */   private void extractConstraints_Deterministic() {
/* 441 */     int E_size = (int)Math.ceil(this.E_perc * this.trueDeps.size() / 100);
/* 442 */     int P_size = (int)Math.ceil(this.P_perc * this.truePathDeps.size() / 100);
/* 443 */     int NE_size = (int)Math.ceil(this.NE_perc * this.negTrueDeps.size() / 100);
/* 444 */     int NP_size = (int)Math.ceil(this.NP_perc * this.negTruePathDeps.size() / 100);
/*     */     
/* 446 */     Iterator<Dependency> it = this.trueDeps.iterator();
/* 447 */     for (int i = 0; i < E_size; i++) {
/* 448 */       if (!it.hasNext()) break;
/* 449 */       Dependency sampledDep = (Dependency)it.next();
/* 450 */       this.sampled_trueDeps.add(sampledDep);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 456 */     it = this.negTrueDeps.iterator();
/* 457 */     for (int i = 0; i < NE_size; i++) {
/* 458 */       if (!it.hasNext()) break;
/* 459 */       Dependency sampledDep = (Dependency)it.next();
/* 460 */       this.sampled_negTrueDeps.add(sampledDep);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 466 */     it = this.truePathDeps.iterator();
/* 467 */     for (int i = 0; i < P_size; i++) {
/* 468 */       if (!it.hasNext()) break;
/* 469 */       Dependency sampledDep = (Dependency)it.next();
/* 470 */       this.sampled_truePathDeps.add(sampledDep);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 476 */     it = this.negTruePathDeps.iterator();
/* 477 */     for (int i = 0; i < NP_size; i++) {
/* 478 */       if (!it.hasNext()) break;
/* 479 */       Dependency sampledDep = (Dependency)it.next();
/* 480 */       this.sampled_negTruePathDeps.add(sampledDep);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/plugins/bk/WorkflowConstraintExtractor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */