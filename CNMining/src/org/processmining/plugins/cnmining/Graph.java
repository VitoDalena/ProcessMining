/*     */ package org.processmining.plugins.cnmining;
/*     */ 
/*     */ import com.carrotsearch.hppc.ObjectArrayList;
/*     */ import com.carrotsearch.hppc.ObjectObjectOpenHashMap;
/*     */ import com.carrotsearch.hppc.ObjectOpenHashSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Graph
/*     */ {
/*  14 */   private ObjectObjectOpenHashMap<Node, ObjectOpenHashSet<Node>> map = new ObjectObjectOpenHashMap();
/*     */   
/*  16 */   private final ObjectArrayList<Edge> lista_archi = new ObjectArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private ObjectArrayList<Node> listaNodi;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addEdge(Node node1, Node node2, boolean flag)
/*     */   {
/*  29 */     ObjectOpenHashSet<Node> adjacent = (ObjectOpenHashSet)this.map.get(node1);
/*  30 */     if (adjacent == null) {
/*  31 */       adjacent = new ObjectOpenHashSet();
/*  32 */       this.map.put(node1, adjacent);
/*     */     }
/*  34 */     adjacent.add(node2);
/*  35 */     this.lista_archi.add(new Edge(node1, node2, flag));
/*     */   }
/*     */   
/*     */   public void addTwoWayVertex(Node node1, Node node2, boolean flag) {
/*  39 */     addEdge(node1, node2, flag);
/*  40 */     addEdge(node2, node1, flag);
/*     */   }
/*     */   
/*     */   public ObjectArrayList<Node> adjacentNodes(Node last) {
/*  44 */     ObjectOpenHashSet<Node> adjacent = (ObjectOpenHashSet)this.map.get(last);
/*  45 */     if (adjacent == null) {
/*  46 */       return new ObjectArrayList();
/*     */     }
/*  48 */     ObjectArrayList<Node> adjacents = new ObjectArrayList(adjacent);
/*  49 */     adjacents.trimToSize();
/*  50 */     return adjacents;
/*     */   }
/*     */   
/*     */   public ObjectArrayList<Edge> getLista_archi() {
/*  54 */     return this.lista_archi;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ObjectObjectOpenHashMap<Node, ObjectOpenHashSet<Node>> getMap()
/*     */   {
/*  61 */     return this.map;
/*     */   }
/*     */   
/*     */   public Node getNode(String activity_name, int id_activity)
/*     */   {
/*  66 */     Object[] keys = this.map.keys;
/*     */     
/*  68 */     boolean[] states = this.map.allocated;
/*  69 */     Node n1 = new Node(activity_name, id_activity);
/*     */     
/*  71 */     for (int i = 0; i < states.length; i++)
/*     */     {
/*  73 */       if (states[i] != false)
/*     */       {
/*     */ 
/*  76 */         if (n1.equals((Node)keys[i])) {
/*  77 */           return (Node)keys[i];
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  88 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isConnected(Node node1, Node node2)
/*     */   {
/*  93 */     ObjectOpenHashSet<Node> adjacent = (ObjectOpenHashSet)this.map.get(node1);
/*  94 */     if (adjacent == null) {
/*  95 */       return false;
/*     */     }
/*  97 */     Object[] keys = adjacent.keys;
/*     */     
/*  99 */     boolean[] states = adjacent.allocated;
/*     */     
/* 101 */     for (int i = 0; i < states.length; i++)
/*     */     {
/* 103 */       if (states[i] != false)
/*     */       {
/*     */ 
/* 106 */         if (node2.equals((Node)keys[i]))
/* 107 */           return true;
/*     */       }
/*     */     }
/* 110 */     return false;
/*     */   }
/*     */   
/*     */   public ObjectArrayList<Node> listaNodi()
/*     */   {
/* 115 */     if (this.listaNodi == null) {
/* 116 */       this.listaNodi = new ObjectArrayList();
/* 117 */       Object[] keys = this.map.keys;
/*     */       
/* 119 */       boolean[] states = this.map.allocated;
/*     */       
/* 121 */       for (int i = 0; i < states.length; i++)
/*     */       {
/* 123 */         if (states[i] != false)
/*     */         {
/*     */ 
/* 126 */           this.listaNodi.add((Node)keys[i]);
/*     */         }
/*     */       }
/*     */       
/* 130 */       this.listaNodi.trimToSize();
/*     */     }
/* 132 */     return this.listaNodi;
/*     */   }
/*     */   
/*     */   public boolean removeEdge(Node node1, Node node2)
/*     */   {
/* 137 */     ObjectOpenHashSet<Node> adjacent = (ObjectOpenHashSet)this.map.get(node1);
/* 138 */     this.lista_archi.removeAllOccurrences(new Edge(node1, node2));
/* 139 */     if (adjacent != null) {
/* 140 */       return adjacent.remove(node2);
/*     */     }
/* 142 */     return false;
/*     */   }
/*     */   
/*     */   public void removeNode(Node n) {
/* 146 */     this.map.remove(n);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/Dipendenze/CNMining.jar!/org/processmining/plugins/core/Graph.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */