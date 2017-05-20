/*     */ package org.apache.commons.collections15.comparators;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FixedOrderComparator<T>
/*     */   implements Comparator<T>
/*     */ {
/*     */   public static final int UNKNOWN_BEFORE = 0;
/*     */   public static final int UNKNOWN_AFTER = 1;
/*     */   public static final int UNKNOWN_THROW_EXCEPTION = 2;
/*  70 */   private final Map<T, Integer> map = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*  74 */   private int counter = 0;
/*     */   
/*     */ 
/*     */ 
/*  78 */   private boolean isLocked = false;
/*     */   
/*     */ 
/*     */ 
/*  82 */   private int unknownObjectBehavior = 2;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FixedOrderComparator() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FixedOrderComparator(T[] items)
/*     */   {
/* 104 */     if (items == null) {
/* 105 */       throw new IllegalArgumentException("The list of items must not be null");
/*     */     }
/* 107 */     for (int i = 0; i < items.length; i++) {
/* 108 */       add(items[i]);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FixedOrderComparator(List<T> items)
/*     */   {
/* 123 */     if (items == null) {
/* 124 */       throw new IllegalArgumentException("The list of items must not be null");
/*     */     }
/* 126 */     for (Iterator<T> it = items.iterator(); it.hasNext();) {
/* 127 */       add(it.next());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLocked()
/*     */   {
/* 141 */     return this.isLocked;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void checkLocked()
/*     */   {
/* 150 */     if (isLocked()) {
/* 151 */       throw new UnsupportedOperationException("Cannot modify a FixedOrderComparator after a comparison");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getUnknownObjectBehavior()
/*     */   {
/* 162 */     return this.unknownObjectBehavior;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUnknownObjectBehavior(int unknownObjectBehavior)
/*     */   {
/* 174 */     checkLocked();
/* 175 */     if ((unknownObjectBehavior != 1) && (unknownObjectBehavior != 0) && (unknownObjectBehavior != 2)) {
/* 176 */       throw new IllegalArgumentException("Unrecognised value for unknown behaviour flag");
/*     */     }
/* 178 */     this.unknownObjectBehavior = unknownObjectBehavior;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean add(T obj)
/*     */   {
/* 194 */     checkLocked();
/* 195 */     Integer position = (Integer)this.map.put(obj, new Integer(this.counter++));
/* 196 */     return position == null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean addAsEqual(T existingObj, T newObj)
/*     */   {
/* 213 */     checkLocked();
/* 214 */     Integer position = (Integer)this.map.get(existingObj);
/* 215 */     if (position == null) {
/* 216 */       throw new IllegalArgumentException(existingObj + " not known to " + this);
/*     */     }
/* 218 */     Integer result = (Integer)this.map.put(newObj, position);
/* 219 */     return result == null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int compare(T obj1, T obj2)
/*     */   {
/* 239 */     this.isLocked = true;
/* 240 */     Integer position1 = (Integer)this.map.get(obj1);
/* 241 */     Integer position2 = (Integer)this.map.get(obj2);
/* 242 */     if ((position1 == null) || (position2 == null)) {
/* 243 */       switch (this.unknownObjectBehavior) {
/*     */       case 0: 
/* 245 */         if (position1 == null) {
/* 246 */           return position2 == null ? 0 : -1;
/*     */         }
/* 248 */         return 1;
/*     */       
/*     */       case 1: 
/* 251 */         if (position1 == null) {
/* 252 */           return position2 == null ? 0 : 1;
/*     */         }
/* 254 */         return -1;
/*     */       
/*     */       case 2: 
/* 257 */         Object unknownObj = position1 == null ? obj1 : obj2;
/* 258 */         throw new IllegalArgumentException("Attempting to compare unknown object " + unknownObj);
/*     */       }
/* 260 */       throw new UnsupportedOperationException("Unknown unknownObjectBehavior: " + this.unknownObjectBehavior);
/*     */     }
/*     */     
/* 263 */     return position1.compareTo(position2);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/comparators/FixedOrderComparator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */