/*     */ package org.jfree.data;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import org.jfree.util.SortOrder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KeyedValueComparator
/*     */   implements Comparator
/*     */ {
/*     */   private KeyedValueComparatorType type;
/*     */   private SortOrder order;
/*     */   
/*     */   public KeyedValueComparator(KeyedValueComparatorType type, SortOrder order)
/*     */   {
/*  70 */     if (order == null) {
/*  71 */       throw new IllegalArgumentException("Null 'order' argument.");
/*     */     }
/*  73 */     this.type = type;
/*  74 */     this.order = order;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KeyedValueComparatorType getType()
/*     */   {
/*  83 */     return this.type;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SortOrder getOrder()
/*     */   {
/*  92 */     return this.order;
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
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 106 */     if (o2 == null) {
/* 107 */       return -1;
/*     */     }
/* 109 */     if (o1 == null) {
/* 110 */       return 1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 115 */     KeyedValue kv1 = (KeyedValue)o1;
/* 116 */     KeyedValue kv2 = (KeyedValue)o2;
/*     */     
/* 118 */     if (this.type == KeyedValueComparatorType.BY_KEY) { int result;
/* 119 */       if (this.order.equals(SortOrder.ASCENDING)) {
/* 120 */         result = kv1.getKey().compareTo(kv2.getKey());
/*     */       } else { int result;
/* 122 */         if (this.order.equals(SortOrder.DESCENDING)) {
/* 123 */           result = kv2.getKey().compareTo(kv1.getKey());
/*     */         }
/*     */         else
/* 126 */           throw new IllegalArgumentException("Unrecognised sort order.");
/*     */       }
/*     */     } else { int result;
/* 129 */       if (this.type == KeyedValueComparatorType.BY_VALUE) {
/* 130 */         Number n1 = kv1.getValue();
/* 131 */         Number n2 = kv2.getValue();
/* 132 */         if (n2 == null) {
/* 133 */           return -1;
/*     */         }
/* 135 */         if (n1 == null) {
/* 136 */           return 1;
/*     */         }
/* 138 */         double d1 = n1.doubleValue();
/* 139 */         double d2 = n2.doubleValue();
/* 140 */         int result; if (this.order.equals(SortOrder.ASCENDING)) { int result;
/* 141 */           if (d1 > d2) {
/* 142 */             result = 1;
/*     */           } else { int result;
/* 144 */             if (d1 < d2) {
/* 145 */               result = -1;
/*     */             }
/*     */             else
/* 148 */               result = 0;
/*     */           }
/*     */         } else { int result;
/* 151 */           if (this.order.equals(SortOrder.DESCENDING)) { int result;
/* 152 */             if (d1 > d2) {
/* 153 */               result = -1;
/*     */             } else { int result;
/* 155 */               if (d1 < d2) {
/* 156 */                 result = 1;
/*     */               }
/*     */               else {
/* 159 */                 result = 0;
/*     */               }
/*     */             }
/*     */           } else {
/* 163 */             throw new IllegalArgumentException("Unrecognised sort order.");
/*     */           }
/*     */         }
/*     */       } else {
/* 167 */         throw new IllegalArgumentException("Unrecognised type.");
/*     */       } }
/*     */     int result;
/* 170 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/KeyedValueComparator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */