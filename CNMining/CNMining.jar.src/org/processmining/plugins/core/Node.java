/*     */ package org.processmining.plugins.core;
/*     */ 
/*     */ import com.carrotsearch.hppc.IntArrayList;
/*     */ import com.carrotsearch.hppc.IntOpenHashSet;
/*     */ import com.carrotsearch.hppc.ObjectIntOpenHashMap;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Node
/*     */ {
/*  15 */   ObjectIntOpenHashMap<IntOpenHashSet> output = new ObjectIntOpenHashMap();
/*  16 */   ObjectIntOpenHashMap<IntOpenHashSet> input = new ObjectIntOpenHashMap();
/*     */   
/*  18 */   ObjectIntOpenHashMap<IntArrayList> extendedOutput = new ObjectIntOpenHashMap();
/*  19 */   ObjectIntOpenHashMap<IntArrayList> extendedInput = new ObjectIntOpenHashMap();
/*     */   
/*     */   public ObjectIntOpenHashMap<IntArrayList> getExtendedOutput() {
/*  22 */     return this.extendedOutput;
/*     */   }
/*     */   
/*     */   public void setExtendedOutput(ObjectIntOpenHashMap<IntArrayList> extendedOutput) {
/*  26 */     this.extendedOutput = extendedOutput;
/*     */   }
/*     */   
/*     */   public ObjectIntOpenHashMap<IntArrayList> getExtendedInput() {
/*  30 */     return this.extendedInput;
/*     */   }
/*     */   
/*     */   public void setExtendedInput(ObjectIntOpenHashMap<IntArrayList> extendedInput) {
/*  34 */     this.extendedInput = extendedInput;
/*     */   }
/*     */   
/*     */   public ObjectIntOpenHashMap<IntOpenHashSet> getOutput() {
/*  38 */     return this.output;
/*     */   }
/*     */   
/*     */   public void setOutput(ObjectIntOpenHashMap<IntOpenHashSet> output) {
/*  42 */     this.output = output;
/*     */   }
/*     */   
/*     */   public ObjectIntOpenHashMap<IntOpenHashSet> getInput() {
/*  46 */     return this.input;
/*     */   }
/*     */   
/*     */   public void setJoin(ObjectIntOpenHashMap<IntOpenHashSet> input) {
/*  50 */     this.input = input;
/*     */   }
/*     */   
/*     */   public static void main(String... args)
/*     */   {
/*  55 */     Node n1 = new Node("Ciao", 1);
/*  56 */     Node n2 = new Node("Ciao", 2);
/*  57 */     Node n3 = new Node("prova", 1);
/*     */     
/*  59 */     ObjectIntOpenHashMap<Node> mappa = new ObjectIntOpenHashMap();
/*  60 */     mappa.put(n1, 1);
/*  61 */     mappa.put(n2, 2);
/*  62 */     mappa.put(n3, 3);
/*  63 */     Object[] keys = mappa.keys;
/*     */     
/*  65 */     for (int i = 0; i < mappa.allocated.length; i++) {
/*  66 */       if (mappa.allocated[i] != 0) {
/*  67 */         Node n = (Node)keys[i];
/*  68 */         System.out.println(n);
/*  69 */         int v = mappa.values[i];
/*  70 */         System.out.println(v);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  79 */   private int inner_degree = 0;
/*     */   
/*  81 */   private int outer_degree = 0;
/*     */   
/*     */   private final int id_attivita;
/*     */   
/*     */   private final String nome_attivita;
/*     */   
/*  87 */   private boolean mark = false;
/*     */   
/*     */   public Node(String nome_attivita, int id_attivita)
/*     */   {
/*  91 */     this.nome_attivita = nome_attivita;
/*  92 */     this.id_attivita = id_attivita;
/*     */   }
/*     */   
/*     */   public void decr_Inner_degree() {
/*  96 */     this.inner_degree -= 1;
/*     */   }
/*     */   
/*     */   public void decr_Outer_degree() {
/* 100 */     this.outer_degree -= 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 110 */     if (this == obj)
/* 111 */       return true;
/* 112 */     if (obj == null)
/* 113 */       return false;
/* 114 */     if (getClass() != obj.getClass())
/* 115 */       return false;
/* 116 */     Node other = (Node)obj;
/* 117 */     if (this.id_attivita != other.id_attivita)
/* 118 */       return false;
/* 119 */     if (this.nome_attivita == null) {
/* 120 */       if (other.nome_attivita != null)
/* 121 */         return false;
/* 122 */     } else if (!this.nome_attivita.equals(other.nome_attivita))
/* 123 */       return false;
/* 124 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getID_attivita()
/*     */   {
/* 132 */     return this.id_attivita;
/*     */   }
/*     */   
/*     */   public int getInner_degree() {
/* 136 */     return this.inner_degree;
/*     */   }
/*     */   
/*     */   public String getNomeAttivita() {
/* 140 */     return this.nome_attivita;
/*     */   }
/*     */   
/*     */   public int getOuter_degree() {
/* 144 */     return this.outer_degree;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 154 */     int prime = 31;
/* 155 */     int result = 1;
/* 156 */     result = 31 * result + this.id_attivita;
/* 157 */     result = 31 * result + (this.nome_attivita == null ? 0 : this.nome_attivita.hashCode());
/* 158 */     return result;
/*     */   }
/*     */   
/*     */   public void incr_Inner_degree() {
/* 162 */     this.inner_degree += 1;
/*     */   }
/*     */   
/*     */   public void incr_Outer_degree() {
/* 166 */     this.outer_degree += 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isMarked()
/*     */   {
/* 173 */     return this.mark;
/*     */   }
/*     */   
/*     */   public void setInner_degree(int inner_degree) {
/* 177 */     this.inner_degree = inner_degree;
/*     */   }
/*     */   
/*     */   public void setOuter_degree(int outer_degree) {
/* 181 */     this.outer_degree = outer_degree;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMark(boolean mark)
/*     */   {
/* 189 */     this.mark = mark;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 199 */     return this.nome_attivita;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/plugins/core/Node.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */