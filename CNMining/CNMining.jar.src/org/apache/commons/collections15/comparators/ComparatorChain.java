/*     */ package org.apache.commons.collections15.comparators;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.BitSet;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ public class ComparatorChain<T>
/*     */   implements Comparator<T>, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -721644942746081630L;
/*  63 */   protected List<Comparator<T>> comparatorChain = null;
/*     */   
/*     */ 
/*     */ 
/*  67 */   protected BitSet orderingBits = null;
/*     */   
/*     */ 
/*     */ 
/*  71 */   protected boolean isLocked = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ComparatorChain()
/*     */   {
/*  81 */     this(new ArrayList(), new BitSet());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ComparatorChain(Comparator<T> comparator)
/*     */   {
/*  91 */     this(comparator, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ComparatorChain(Comparator<T> comparator, boolean reverse)
/*     */   {
/* 102 */     this.comparatorChain = new ArrayList();
/* 103 */     this.comparatorChain.add(comparator);
/* 104 */     this.orderingBits = new BitSet(1);
/* 105 */     if (reverse == true) {
/* 106 */       this.orderingBits.set(0);
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
/*     */   public ComparatorChain(List<Comparator<T>> list)
/*     */   {
/* 119 */     this(list, new BitSet(list.size()));
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
/*     */   public ComparatorChain(List<Comparator<T>> list, BitSet bits)
/*     */   {
/* 138 */     this.comparatorChain = list;
/* 139 */     this.orderingBits = bits;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addComparator(Comparator<T> comparator)
/*     */   {
/* 150 */     addComparator(comparator, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addComparator(Comparator<T> comparator, boolean reverse)
/*     */   {
/* 161 */     checkLocked();
/*     */     
/* 163 */     this.comparatorChain.add(comparator);
/* 164 */     if (reverse == true) {
/* 165 */       this.orderingBits.set(this.comparatorChain.size() - 1);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setComparator(int index, Comparator<T> comparator)
/*     */     throws IndexOutOfBoundsException
/*     */   {
/* 178 */     setComparator(index, comparator, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setComparator(int index, Comparator<T> comparator, boolean reverse)
/*     */   {
/* 190 */     checkLocked();
/*     */     
/* 192 */     this.comparatorChain.set(index, comparator);
/* 193 */     if (reverse == true) {
/* 194 */       this.orderingBits.set(index);
/*     */     } else {
/* 196 */       this.orderingBits.clear(index);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setForwardSort(int index)
/*     */   {
/* 208 */     checkLocked();
/* 209 */     this.orderingBits.clear(index);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setReverseSort(int index)
/*     */   {
/* 219 */     checkLocked();
/* 220 */     this.orderingBits.set(index);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 229 */     return this.comparatorChain.size();
/*     */   }
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
/* 241 */     return this.isLocked;
/*     */   }
/*     */   
/*     */   private void checkLocked()
/*     */   {
/* 246 */     if (this.isLocked == true) {
/* 247 */       throw new UnsupportedOperationException("Comparator ordering cannot be changed after the first comparison is performed");
/*     */     }
/*     */   }
/*     */   
/*     */   private void checkChainIntegrity() {
/* 252 */     if (this.comparatorChain.size() == 0) {
/* 253 */       throw new UnsupportedOperationException("ComparatorChains must contain at least one Comparator");
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
/*     */   public int compare(T o1, T o2)
/*     */     throws UnsupportedOperationException
/*     */   {
/* 269 */     if (!this.isLocked) {
/* 270 */       checkChainIntegrity();
/* 271 */       this.isLocked = true;
/*     */     }
/*     */     
/*     */ 
/* 275 */     Iterator<Comparator<T>> comparators = this.comparatorChain.iterator();
/* 276 */     for (int comparatorIndex = 0; comparators.hasNext(); comparatorIndex++)
/*     */     {
/* 278 */       Comparator<T> comparator = (Comparator)comparators.next();
/* 279 */       int retval = comparator.compare(o1, o2);
/* 280 */       if (retval != 0)
/*     */       {
/* 282 */         if (this.orderingBits.get(comparatorIndex) == true) {
/* 283 */           if (Integer.MIN_VALUE == retval) {
/* 284 */             retval = Integer.MAX_VALUE;
/*     */           } else {
/* 286 */             retval *= -1;
/*     */           }
/*     */         }
/*     */         
/* 290 */         return retval;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 296 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 308 */     int hash = 0;
/* 309 */     if (null != this.comparatorChain) {
/* 310 */       hash ^= this.comparatorChain.hashCode();
/*     */     }
/* 312 */     if (null != this.orderingBits) {
/* 313 */       hash ^= this.orderingBits.hashCode();
/*     */     }
/* 315 */     return hash;
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
/*     */   public boolean equals(Object object)
/*     */   {
/* 335 */     if (this == object)
/* 336 */       return true;
/* 337 */     if (null == object)
/* 338 */       return false;
/* 339 */     if (object.getClass().equals(getClass())) {
/* 340 */       ComparatorChain chain = (ComparatorChain)object;
/* 341 */       return (null == this.orderingBits ? null == chain.orderingBits : this.orderingBits.equals(chain.orderingBits)) && (null == this.comparatorChain ? null == chain.comparatorChain : this.comparatorChain.equals(chain.comparatorChain));
/*     */     }
/* 343 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/comparators/ComparatorChain.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */