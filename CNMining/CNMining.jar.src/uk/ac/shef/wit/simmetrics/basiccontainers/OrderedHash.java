/*     */ package uk.ac.shef.wit.simmetrics.basiccontainers;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
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
/*     */ final class OrderedHash
/*     */   extends Hashtable
/*     */   implements Serializable
/*     */ {
/*  57 */   private final Vector orderVector = new Vector();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  62 */   private final Vector valueVector = new Vector();
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
/*     */   public Object put(Object key, Object value)
/*     */   {
/*  78 */     if (!this.orderVector.contains(key)) {
/*  79 */       this.orderVector.add(key);
/*     */     }
/*     */     
/*  82 */     if ((value instanceof Vector)) {
/*  83 */       this.valueVector.addAll((Vector)value);
/*     */     } else {
/*  85 */       this.valueVector.add(value);
/*     */     }
/*     */     
/*  88 */     return super.put(key, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Vector getOrderedKeys()
/*     */   {
/*  96 */     return this.orderVector;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Vector getOrderedValues()
/*     */   {
/* 104 */     return this.valueVector;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/basiccontainers/OrderedHash.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */