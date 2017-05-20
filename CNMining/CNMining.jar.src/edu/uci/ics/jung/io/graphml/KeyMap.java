/*     */ package edu.uci.ics.jung.io.graphml;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
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
/*     */ public class KeyMap
/*     */ {
/*  30 */   private final Map<Metadata.MetadataType, List<Key>> map = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addKey(Key key)
/*     */   {
/*  39 */     switch (key.getForType()) {
/*     */     case EDGE: 
/*  41 */       getKeyList(Metadata.MetadataType.EDGE).add(key);
/*  42 */       break;
/*     */     
/*     */     case ENDPOINT: 
/*  45 */       getKeyList(Metadata.MetadataType.ENDPOINT).add(key);
/*  46 */       break;
/*     */     
/*     */     case GRAPH: 
/*  49 */       getKeyList(Metadata.MetadataType.GRAPH).add(key);
/*  50 */       break;
/*     */     
/*     */     case HYPEREDGE: 
/*  53 */       getKeyList(Metadata.MetadataType.HYPEREDGE).add(key);
/*  54 */       break;
/*     */     
/*     */     case NODE: 
/*  57 */       getKeyList(Metadata.MetadataType.NODE).add(key);
/*  58 */       break;
/*     */     
/*     */     case PORT: 
/*  61 */       getKeyList(Metadata.MetadataType.PORT).add(key);
/*  62 */       break;
/*     */     
/*     */ 
/*     */ 
/*     */     default: 
/*  67 */       getKeyList(Metadata.MetadataType.EDGE).add(key);
/*  68 */       getKeyList(Metadata.MetadataType.ENDPOINT).add(key);
/*  69 */       getKeyList(Metadata.MetadataType.GRAPH).add(key);
/*  70 */       getKeyList(Metadata.MetadataType.HYPEREDGE).add(key);
/*  71 */       getKeyList(Metadata.MetadataType.NODE).add(key);
/*  72 */       getKeyList(Metadata.MetadataType.PORT).add(key);
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void applyKeys(Metadata metadata)
/*     */   {
/*  84 */     List<Key> keys = getKeyList(metadata.getMetadataType());
/*  85 */     for (Key key : keys) {
/*  86 */       key.applyKey(metadata);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/*  94 */     this.map.clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<Map.Entry<Metadata.MetadataType, List<Key>>> entrySet()
/*     */   {
/* 103 */     return this.map.entrySet();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private List<Key> getKeyList(Metadata.MetadataType type)
/*     */   {
/* 115 */     List<Key> keys = (List)this.map.get(type);
/* 116 */     if (keys == null) {
/* 117 */       keys = new ArrayList();
/* 118 */       this.map.put(type, keys);
/*     */     }
/*     */     
/* 121 */     return keys;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/KeyMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */