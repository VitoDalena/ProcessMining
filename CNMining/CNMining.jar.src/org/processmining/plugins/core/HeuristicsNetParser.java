/*     */ package org.processmining.plugins.core;
/*     */ 
/*     */ import com.carrotsearch.hppc.ObjectArrayList;
/*     */ import com.carrotsearch.hppc.ObjectIntOpenHashMap;
/*     */ import com.carrotsearch.hppc.ObjectObjectOpenHashMap;
/*     */ import com.carrotsearch.hppc.ObjectOpenHashSet;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HeuristicsNetParser
/*     */ {
/*     */   public Graph parse(String filename, ObjectIntOpenHashMap<String> folded_map)
/*     */   {
/*  20 */     Graph g = new Graph();
/*     */     try
/*     */     {
/*  23 */       BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
/*     */       
/*     */ 
/*  26 */       int count = 0;
/*     */       
/*  28 */       HashMap<String, String> hashmap = new HashMap();
/*  29 */       String input; while ((input = br.readLine()) != null) { String input;
/*  30 */         if (count < 5) {
/*  31 */           count++;
/*     */ 
/*     */         }
/*  34 */         else if ((count >= 5) && (count <= 17)) {
/*  35 */           String[] inputs = input.split(":");
/*     */           
/*  37 */           if (folded_map.containsKey(inputs[0])) {
/*  38 */             Node n = new Node(inputs[0], folded_map.get(inputs[0]));
/*  39 */             g.getMap().put(n, new ObjectOpenHashSet());
/*     */             
/*  41 */             hashmap.put(inputs[1].split("@")[1].split("&")[0], inputs[0]);
/*     */           }
/*  43 */           count++;
/*     */         }
/*  45 */         else if ((count >= 20) && (!input.equals("")))
/*     */         {
/*  47 */           String[] ids = input.split("@");
/*  48 */           Node x = g.getNode((String)hashmap.get(ids[0]), folded_map.get((String)hashmap.get(ids[0])));
/*  49 */           String[] arrayOfString1; int j; int i; String[] arrayOfString2; int m; int k; if (!ids[1].equals("."))
/*     */           {
/*  51 */             String[] ids2 = ids[1].split("\\|");
/*     */             
/*  53 */             j = (arrayOfString1 = ids2).length; for (i = 0; i < j; i++) { String n = arrayOfString1[i];
/*  54 */               if (!n.equals("")) {
/*  55 */                 if (n.contains("&"))
/*     */                 {
/*  57 */                   String[] ms = n.split("&");
/*     */                   
/*  59 */                   m = (arrayOfString2 = ms).length; for (k = 0; k < m; k++) { String m = arrayOfString2[k];
/*  60 */                     if (!m.equals("")) {
/*  61 */                       if (hashmap.get(m) == null)
/*  62 */                         System.out.println("stop");
/*  63 */                       Node y = g.getNode((String)hashmap.get(m), folded_map.get((String)hashmap.get(m)));
/*     */                       
/*  65 */                       if (!g.getLista_archi().contains(new Edge(y, x))) {
/*  66 */                         g.addEdge(y, x, false);
/*     */                       }
/*     */                     }
/*     */                   }
/*     */                 } else {
/*  71 */                   Node y = g.getNode((String)hashmap.get(n), folded_map.get((String)hashmap.get(n)));
/*  72 */                   if (hashmap.get(n) == null)
/*  73 */                     System.out.println("stop");
/*  74 */                   if (!g.getLista_archi().contains(new Edge(y, x))) {
/*  75 */                     g.addEdge(y, x, false);
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*  81 */           if (!ids[2].equals("."))
/*     */           {
/*  83 */             String[] ids2 = ids[2].split("\\|");
/*     */             
/*  85 */             j = (arrayOfString1 = ids2).length; for (i = 0; i < j; i++) { String n = arrayOfString1[i];
/*  86 */               if (!n.equals("")) {
/*  87 */                 if (n.contains("&")) {
/*  88 */                   String[] ms = n.split("&");
/*     */                   
/*  90 */                   m = (arrayOfString2 = ms).length; for (k = 0; k < m; k++) { String m = arrayOfString2[k];
/*  91 */                     if (!m.equals("")) {
/*  92 */                       if (hashmap.get(m) == null)
/*  93 */                         System.out.println("stop");
/*  94 */                       Node y = g.getNode((String)hashmap.get(m), folded_map.get((String)hashmap.get(m)));
/*     */                       
/*  96 */                       if (!g.getLista_archi().contains(new Edge(x, y))) {
/*  97 */                         g.addEdge(x, y, false);
/*     */                       }
/*     */                     }
/*     */                   }
/*     */                 } else {
/* 102 */                   if (hashmap.get(n) == null)
/* 103 */                     System.out.println("stop");
/* 104 */                   Node y = g.getNode((String)hashmap.get(n), folded_map.get((String)hashmap.get(n)));
/*     */                   
/* 106 */                   if (!g.getLista_archi().contains(new Edge(x, y))) {
/* 107 */                     g.addEdge(x, y, false);
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/* 113 */           count++;
/*     */         }
/*     */         else {
/* 116 */           count++;
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception localException) {}
/*     */     
/*     */ 
/* 123 */     return g;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/plugins/core/HeuristicsNetParser.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */