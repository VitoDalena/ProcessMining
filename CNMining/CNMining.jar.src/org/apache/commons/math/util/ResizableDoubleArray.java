/*     */ package org.apache.commons.math.util;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public class ResizableDoubleArray
/*     */   implements DoubleArray, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3485529955529426875L;
/*     */   public static final int ADDITIVE_MODE = 1;
/*     */   public static final int MULTIPLICATIVE_MODE = 0;
/*  87 */   protected float contractionCriteria = 2.5F;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  97 */   protected float expansionFactor = 2.0F;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 103 */   protected int expansionMode = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 109 */   protected int initialCapacity = 16;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double[] internalArray;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 120 */   protected int numElements = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 128 */   protected int startIndex = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ResizableDoubleArray()
/*     */   {
/* 140 */     this.internalArray = new double[this.initialCapacity];
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
/*     */   public ResizableDoubleArray(int initialCapacity)
/*     */   {
/* 155 */     setInitialCapacity(initialCapacity);
/* 156 */     this.internalArray = new double[this.initialCapacity];
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ResizableDoubleArray(int initialCapacity, float expansionFactor)
/*     */   {
/* 182 */     this.expansionFactor = expansionFactor;
/* 183 */     setInitialCapacity(initialCapacity);
/* 184 */     this.internalArray = new double[initialCapacity];
/* 185 */     setContractionCriteria(expansionFactor + 0.5F);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ResizableDoubleArray(int initialCapacity, float expansionFactor, float contractionCriteria)
/*     */   {
/* 209 */     this.expansionFactor = expansionFactor;
/* 210 */     setContractionCriteria(contractionCriteria);
/* 211 */     setInitialCapacity(initialCapacity);
/* 212 */     this.internalArray = new double[initialCapacity];
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ResizableDoubleArray(int initialCapacity, float expansionFactor, float contractionCriteria, int expansionMode)
/*     */   {
/* 238 */     this.expansionFactor = expansionFactor;
/* 239 */     setContractionCriteria(contractionCriteria);
/* 240 */     setInitialCapacity(initialCapacity);
/* 241 */     setExpansionMode(expansionMode);
/* 242 */     this.internalArray = new double[initialCapacity];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void addElement(double value)
/*     */   {
/* 251 */     this.numElements += 1;
/* 252 */     if (this.startIndex + this.numElements > this.internalArray.length) {
/* 253 */       expand();
/*     */     }
/* 255 */     this.internalArray[(this.startIndex + (this.numElements - 1))] = value;
/* 256 */     if (shouldContract()) {
/* 257 */       contract();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized double addElementRolling(double value)
/*     */   {
/* 278 */     double discarded = this.internalArray[this.startIndex];
/*     */     
/* 280 */     if (this.startIndex + (this.numElements + 1) > this.internalArray.length) {
/* 281 */       expand();
/*     */     }
/*     */     
/* 284 */     this.startIndex += 1;
/*     */     
/*     */ 
/* 287 */     this.internalArray[(this.startIndex + (this.numElements - 1))] = value;
/*     */     
/*     */ 
/* 290 */     if (shouldContract()) {
/* 291 */       contract();
/*     */     }
/* 293 */     return discarded;
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
/*     */   protected void checkContractExpand(float contractionCritera, float expansionFactor)
/*     */   {
/* 310 */     if (contractionCritera < expansionFactor) {
/* 311 */       String msg = "Contraction criteria can never be smaller than the expansion factor.  This would lead to a never ending loop of expansion and contraction as a newly expanded internal storage array would immediately satisfy the criteria for contraction";
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 317 */       throw new IllegalArgumentException(msg);
/*     */     }
/*     */     
/* 320 */     if (this.contractionCriteria <= 1.0D) {
/* 321 */       String msg = "The contraction criteria must be a number larger than one.  If the contractionCriteria is less than or equal to one an endless loop of contraction and expansion would ensue as an internalArray.length == numElements would satisfy the contraction criteria";
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 327 */       throw new IllegalArgumentException(msg);
/*     */     }
/*     */     
/* 330 */     if (expansionFactor <= 1.0D) {
/* 331 */       String msg = "The expansion factor must be a number greater than 1.0";
/*     */       
/* 333 */       throw new IllegalArgumentException(msg);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void clear()
/*     */   {
/* 342 */     this.numElements = 0;
/* 343 */     this.internalArray = new double[this.initialCapacity];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void contract()
/*     */   {
/* 352 */     double[] tempArray = new double[this.numElements + 1];
/*     */     
/*     */ 
/* 355 */     System.arraycopy(this.internalArray, this.startIndex, tempArray, 0, this.numElements);
/* 356 */     this.internalArray = tempArray;
/*     */     
/*     */ 
/* 359 */     this.startIndex = 0;
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
/*     */   public synchronized void discardFrontElements(int i)
/*     */   {
/* 373 */     if (i > this.numElements) {
/* 374 */       String msg = "Cannot discard more elements than arecontained in this array.";
/*     */       
/* 376 */       throw new IllegalArgumentException(msg); }
/* 377 */     if (i < 0) {
/* 378 */       String msg = "Cannot discard a negative number of elements.";
/* 379 */       throw new IllegalArgumentException(msg);
/*     */     }
/*     */     
/* 382 */     this.numElements -= i;
/* 383 */     this.startIndex += i;
/*     */     
/* 385 */     if (shouldContract()) {
/* 386 */       contract();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void expand()
/*     */   {
/* 406 */     int newSize = 0;
/* 407 */     if (this.expansionMode == 0) {
/* 408 */       newSize = (int)Math.ceil(this.internalArray.length * this.expansionFactor);
/*     */     } else {
/* 410 */       newSize = this.internalArray.length + Math.round(this.expansionFactor);
/*     */     }
/* 412 */     double[] tempArray = new double[newSize];
/*     */     
/*     */ 
/* 415 */     System.arraycopy(this.internalArray, 0, tempArray, 0, this.internalArray.length);
/* 416 */     this.internalArray = tempArray;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private synchronized void expandTo(int size)
/*     */   {
/* 425 */     double[] tempArray = new double[size];
/*     */     
/* 427 */     System.arraycopy(this.internalArray, 0, tempArray, 0, this.internalArray.length);
/* 428 */     this.internalArray = tempArray;
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
/*     */   public float getContractionCriteria()
/*     */   {
/* 444 */     return this.contractionCriteria;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized double getElement(int index)
/*     */   {
/* 456 */     if (index >= this.numElements) {
/* 457 */       String msg = "The index specified: " + index + " is larger than the current number of elements";
/*     */       
/*     */ 
/* 460 */       throw new ArrayIndexOutOfBoundsException(msg); }
/* 461 */     if (index >= 0) {
/* 462 */       return this.internalArray[(this.startIndex + index)];
/*     */     }
/* 464 */     String msg = "Elements cannot be retrieved from a negative array index";
/*     */     
/* 466 */     throw new ArrayIndexOutOfBoundsException(msg);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized double[] getElements()
/*     */   {
/* 478 */     double[] elementArray = new double[this.numElements];
/* 479 */     System.arraycopy(this.internalArray, this.startIndex, elementArray, 0, this.numElements);
/*     */     
/* 481 */     return elementArray;
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
/*     */   public float getExpansionFactor()
/*     */   {
/* 497 */     return this.expansionFactor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getExpansionMode()
/*     */   {
/* 508 */     return this.expansionMode;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   synchronized int getInternalLength()
/*     */   {
/* 520 */     return this.internalArray.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized int getNumElements()
/*     */   {
/* 530 */     return this.numElements;
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
/*     */   public synchronized double[] getValues()
/*     */   {
/* 545 */     return this.internalArray;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setContractionCriteria(float contractionCriteria)
/*     */   {
/* 554 */     checkContractExpand(contractionCriteria, getExpansionFactor());
/* 555 */     this.contractionCriteria = contractionCriteria;
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
/*     */   public synchronized void setElement(int index, double value)
/*     */   {
/* 572 */     if (index < 0) {
/* 573 */       String msg = "Cannot set an element at a negative index";
/* 574 */       throw new ArrayIndexOutOfBoundsException(msg);
/*     */     }
/* 576 */     if (index + 1 > this.numElements) {
/* 577 */       this.numElements = (index + 1);
/*     */     }
/* 579 */     if (this.startIndex + index >= this.internalArray.length) {
/* 580 */       expandTo(this.startIndex + (index + 1));
/*     */     }
/* 582 */     this.internalArray[(this.startIndex + index)] = value;
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
/*     */   public void setExpansionFactor(float expansionFactor)
/*     */   {
/* 597 */     checkContractExpand(getContractionCriteria(), expansionFactor);
/*     */     
/* 599 */     this.expansionFactor = expansionFactor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setExpansionMode(int expansionMode)
/*     */   {
/* 610 */     if ((expansionMode != 0) && (expansionMode != 1))
/*     */     {
/* 612 */       throw new IllegalArgumentException("Illegal expansionMode setting.");
/*     */     }
/* 614 */     this.expansionMode = expansionMode;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setInitialCapacity(int initialCapacity)
/*     */   {
/* 625 */     if (initialCapacity > 0) {
/* 626 */       synchronized (this) {
/* 627 */         this.initialCapacity = initialCapacity;
/*     */       }
/*     */     } else {
/* 630 */       String msg = "The initial capacity supplied: " + initialCapacity + "must be a positive integer";
/*     */       
/*     */ 
/* 633 */       throw new IllegalArgumentException(msg);
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
/*     */   public synchronized void setNumElements(int i)
/*     */   {
/* 648 */     if (i < 0) {
/* 649 */       String msg = "Number of elements must be zero or a positive integer";
/*     */       
/* 651 */       throw new IllegalArgumentException(msg);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 656 */     if (this.startIndex + i > this.internalArray.length) {
/* 657 */       expandTo(this.startIndex + i);
/*     */     }
/*     */     
/*     */ 
/* 661 */     this.numElements = i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private synchronized boolean shouldContract()
/*     */   {
/* 671 */     if (this.expansionMode == 0) {
/* 672 */       return this.internalArray.length / this.numElements > this.contractionCriteria;
/*     */     }
/* 674 */     return this.internalArray.length - this.numElements > this.contractionCriteria;
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
/*     */   public synchronized int start()
/*     */   {
/* 688 */     return this.startIndex;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/util/ResizableDoubleArray.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */