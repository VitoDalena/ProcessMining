/*     */ package edu.oswego.cs.dl.util.concurrent;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FJTaskRunner
/*     */   extends Thread
/*     */ {
/*     */   protected final FJTaskRunnerGroup group;
/*     */   protected static final int INITIAL_CAPACITY = 4096;
/*     */   protected static final int MAX_CAPACITY = 1073741824;
/*     */   
/*     */   protected FJTaskRunner(FJTaskRunnerGroup paramFJTaskRunnerGroup)
/*     */   {
/* 219 */     this.group = paramFJTaskRunnerGroup;
/* 220 */     this.victimRNG = new Random(System.identityHashCode(this));
/* 221 */     this.runPriority = getPriority();
/* 222 */     setDaemon(true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected final FJTaskRunnerGroup getGroup()
/*     */   {
/* 229 */     return this.group;
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
/*     */   protected static final class VolatileTaskRef
/*     */   {
/*     */     protected volatile FJTask ref;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 262 */     protected final void put(FJTask paramFJTask) { this.ref = paramFJTask; }
/*     */     
/* 264 */     protected final FJTask get() { return this.ref; }
/*     */     
/* 266 */     protected final FJTask take() { FJTask localFJTask = this.ref;this.ref = null;return localFJTask;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     protected static VolatileTaskRef[] newArray(int paramInt)
/*     */     {
/* 274 */       VolatileTaskRef[] arrayOfVolatileTaskRef = new VolatileTaskRef[paramInt];
/* 275 */       for (int i = 0; i < paramInt; i++) arrayOfVolatileTaskRef[i] = new VolatileTaskRef();
/* 276 */       return arrayOfVolatileTaskRef;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 285 */   protected VolatileTaskRef[] deq = VolatileTaskRef.newArray(4096);
/*     */   
/*     */   protected int deqSize() {
/* 288 */     return this.deq.length;
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
/* 300 */   protected volatile int top = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 308 */   protected volatile int base = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 316 */   protected final Object barrier = new Object();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 327 */   protected boolean active = false;
/*     */   
/*     */ 
/*     */ 
/*     */   protected final Random victimRNG;
/*     */   
/*     */ 
/* 334 */   protected int scanPriority = 2;
/*     */   
/*     */ 
/*     */ 
/*     */   protected int runPriority;
/*     */   
/*     */ 
/*     */   static final boolean COLLECT_STATS = true;
/*     */   
/*     */ 
/*     */ 
/*     */   protected void setScanPriority(int paramInt)
/*     */   {
/* 347 */     this.scanPriority = paramInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void setRunPriority(int paramInt)
/*     */   {
/* 354 */     this.runPriority = paramInt;
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
/* 371 */   protected int runs = 0;
/*     */   
/*     */ 
/* 374 */   protected int scans = 0;
/*     */   
/*     */ 
/* 377 */   protected int steals = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final void push(FJTask paramFJTask)
/*     */   {
/* 391 */     int i = this.top;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 401 */     if (i < (this.base & this.deq.length - 1) + this.deq.length)
/*     */     {
/* 403 */       this.deq[(i & this.deq.length - 1)].put(paramFJTask);
/* 404 */       this.top = (i + 1);
/*     */     }
/*     */     else
/*     */     {
/* 408 */       slowPush(paramFJTask);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void slowPush(FJTask paramFJTask)
/*     */   {
/* 417 */     checkOverflow();
/* 418 */     push(paramFJTask);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final synchronized void put(FJTask paramFJTask)
/*     */   {
/*     */     for (;;)
/*     */     {
/* 432 */       int i = this.base - 1;
/* 433 */       if (this.top < i + this.deq.length)
/*     */       {
/* 435 */         int j = i & this.deq.length - 1;
/* 436 */         this.deq[j].put(paramFJTask);
/* 437 */         this.base = j;
/*     */         
/* 439 */         if (i != j) {
/* 440 */           int k = this.top & this.deq.length - 1;
/* 441 */           if (k < j) k += this.deq.length;
/* 442 */           this.top = k;
/*     */         }
/* 444 */         return;
/*     */       }
/*     */       
/* 447 */       checkOverflow();
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
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final FJTask pop()
/*     */   {
/* 471 */     int i = --this.top;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 482 */     if (this.base + 1 < i) {
/* 483 */       return this.deq[(i & this.deq.length - 1)].take();
/*     */     }
/* 485 */     return confirmPop(i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final synchronized FJTask confirmPop(int paramInt)
/*     */   {
/* 496 */     if (this.base <= paramInt) {
/* 497 */       return this.deq[(paramInt & this.deq.length - 1)].take();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 505 */     this.top = (this.base = 0);
/* 506 */     return null;
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
/*     */   protected final synchronized FJTask take()
/*     */   {
/* 523 */     int i = this.base++;
/*     */     
/* 525 */     if (i < this.top) {
/* 526 */       return confirmTake(i);
/*     */     }
/*     */     
/* 529 */     this.base = i;
/* 530 */     return null;
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
/*     */   protected FJTask confirmTake(int paramInt)
/*     */   {
/* 547 */     synchronized (this.barrier) {
/* 548 */       if (paramInt < this.top)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 558 */         localFJTask = this.deq[(paramInt & this.deq.length - 1)].get();return localFJTask;
/*     */       }
/*     */       
/* 561 */       this.base = paramInt;
/* 562 */       FJTask localFJTask = null;return localFJTask;
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
/*     */   protected void checkOverflow()
/*     */   {
/* 576 */     int i = this.top;
/* 577 */     int j = this.base;
/*     */     int k;
/* 579 */     int m; int n; if (i - j < this.deq.length - 1)
/*     */     {
/* 581 */       k = j & this.deq.length - 1;
/* 582 */       m = this.top & this.deq.length - 1;
/* 583 */       if (m < k) m += this.deq.length;
/* 584 */       this.top = m;
/* 585 */       this.base = k;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 592 */       n = k;
/*     */       do {
/* 594 */         this.deq[n].ref = null;
/* 595 */         n = n - 1 & this.deq.length - 1;
/* 593 */         if (n == m) break; } while (this.deq[n].ref != null);
/*     */ 
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/*     */ 
/* 601 */       k = i - j;
/* 602 */       m = this.deq.length;
/* 603 */       n = m * 2;
/*     */       
/* 605 */       if (n >= 1073741824) {
/* 606 */         throw new Error("FJTask queue maximum capacity exceeded");
/*     */       }
/* 608 */       VolatileTaskRef[] arrayOfVolatileTaskRef = new VolatileTaskRef[n];
/*     */       
/*     */ 
/* 611 */       for (int i1 = 0; i1 < m; i1++) { arrayOfVolatileTaskRef[i1] = this.deq[(j++ & m - 1)];
/*     */       }
/*     */       
/* 614 */       for (int i2 = m; i2 < n; i2++) { arrayOfVolatileTaskRef[i2] = new VolatileTaskRef();
/*     */       }
/* 616 */       this.deq = arrayOfVolatileTaskRef;
/* 617 */       this.base = 0;
/* 618 */       this.top = k;
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
/*     */ 
/*     */ 
/*     */   protected void scan(FJTask paramFJTask)
/*     */   {
/* 641 */     FJTask localFJTask = null;
/*     */     
/*     */ 
/* 644 */     int i = 0;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 656 */     FJTaskRunner[] arrayOfFJTaskRunner = this.group.getArray();
/* 657 */     int j = this.victimRNG.nextInt(arrayOfFJTaskRunner.length);
/*     */     
/* 659 */     for (int k = 0; k < arrayOfFJTaskRunner.length; k++)
/*     */     {
/* 661 */       FJTaskRunner localFJTaskRunner = arrayOfFJTaskRunner[j];
/* 662 */       j++; if (j >= arrayOfFJTaskRunner.length) { j = 0;
/*     */       }
/* 664 */       if ((localFJTaskRunner != null) && (localFJTaskRunner != this))
/*     */       {
/* 666 */         if ((paramFJTask != null) && (paramFJTask.isDone())) {
/*     */           break;
/*     */         }
/*     */         
/* 670 */         this.scans += 1;
/* 671 */         localFJTask = localFJTaskRunner.take();
/* 672 */         if (localFJTask != null) {
/* 673 */           this.steals += 1; break;
/*     */         }
/*     */         
/* 676 */         if (isInterrupted()) {
/*     */           break;
/*     */         }
/* 679 */         if (i == 0) {
/* 680 */           i = 1;
/* 681 */           setPriority(this.scanPriority);
/*     */         }
/*     */         else {
/* 684 */           Thread.yield();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 691 */     if (localFJTask == null) {
/* 692 */       this.scans += 1;
/* 693 */       localFJTask = this.group.pollEntryQueue();
/* 694 */       if (localFJTask != null) { this.steals += 1;
/*     */       }
/*     */     }
/* 697 */     if (i != 0) { setPriority(this.runPriority);
/*     */     }
/* 699 */     if ((localFJTask != null) && (!localFJTask.isDone())) {
/* 700 */       this.runs += 1;
/* 701 */       localFJTask.run();
/* 702 */       localFJTask.setDone();
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
/*     */   protected void scanWhileIdling()
/*     */   {
/* 719 */     FJTask localFJTask = null;
/*     */     
/* 721 */     int i = 0;
/* 722 */     long l = 0L;
/*     */     
/* 724 */     FJTaskRunner[] arrayOfFJTaskRunner = this.group.getArray();
/* 725 */     int j = this.victimRNG.nextInt(arrayOfFJTaskRunner.length);
/*     */     do
/*     */     {
/* 728 */       for (int k = 0; k < arrayOfFJTaskRunner.length; k++)
/*     */       {
/* 730 */         FJTaskRunner localFJTaskRunner = arrayOfFJTaskRunner[j];
/* 731 */         j++; if (j >= arrayOfFJTaskRunner.length) { j = 0;
/*     */         }
/* 733 */         if ((localFJTaskRunner != null) && (localFJTaskRunner != this)) {
/* 734 */           this.scans += 1;
/*     */           
/* 736 */           localFJTask = localFJTaskRunner.take();
/* 737 */           if (localFJTask != null) {
/* 738 */             this.steals += 1;
/* 739 */             if (i != 0) setPriority(this.runPriority);
/* 740 */             this.group.setActive(this);
/* 741 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 746 */       if (localFJTask == null) {
/* 747 */         if (isInterrupted()) {
/* 748 */           return;
/*     */         }
/* 750 */         this.scans += 1;
/* 751 */         localFJTask = this.group.pollEntryQueue();
/*     */         
/* 753 */         if (localFJTask != null) {
/* 754 */           this.steals += 1;
/* 755 */           if (i != 0) setPriority(this.runPriority);
/* 756 */           this.group.setActive(this);
/*     */         }
/*     */         else {
/* 759 */           l += 1L;
/*     */           
/* 761 */           if (l >= 15L) {
/* 762 */             this.group.checkActive(this, l);
/* 763 */             if (!isInterrupted()) {}
/*     */ 
/*     */           }
/* 766 */           else if (i == 0) {
/* 767 */             i = 1;
/* 768 */             setPriority(this.scanPriority);
/*     */           }
/*     */           else {
/* 771 */             Thread.yield();
/*     */           }
/*     */         }
/*     */       }
/* 775 */     } while (localFJTask == null);
/*     */     
/*     */ 
/* 778 */     if (!localFJTask.isDone()) {
/* 779 */       this.runs += 1;
/* 780 */       localFJTask.run();
/* 781 */       localFJTask.setDone();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void run()
/*     */   {
/*     */     try
/*     */     {
/* 795 */       while (!Thread.interrupted())
/*     */       {
/* 797 */         FJTask localFJTask = pop();
/* 798 */         if (localFJTask != null) {
/* 799 */           if (!localFJTask.isDone())
/*     */           {
/* 801 */             this.runs += 1;
/* 802 */             localFJTask.run();
/* 803 */             localFJTask.setDone();
/*     */           }
/*     */         }
/*     */         else {
/* 807 */           scanWhileIdling();
/*     */         }
/*     */       }
/*     */     } finally {
/* 811 */       this.group.setInactive(this);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final void taskYield()
/*     */   {
/* 822 */     FJTask localFJTask = pop();
/* 823 */     if (localFJTask != null) {
/* 824 */       if (!localFJTask.isDone()) {
/* 825 */         this.runs += 1;
/* 826 */         localFJTask.run();
/* 827 */         localFJTask.setDone();
/*     */       }
/*     */     }
/*     */     else {
/* 831 */       scan(null);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final void taskJoin(FJTask paramFJTask)
/*     */   {
/* 842 */     while (!paramFJTask.isDone())
/*     */     {
/* 844 */       FJTask localFJTask = pop();
/* 845 */       if (localFJTask != null) {
/* 846 */         if (!localFJTask.isDone()) {
/* 847 */           this.runs += 1;
/* 848 */           localFJTask.run();
/* 849 */           localFJTask.setDone();
/* 850 */           if (localFJTask != paramFJTask) {}
/*     */         }
/*     */       }
/*     */       else {
/* 854 */         scan(paramFJTask);
/*     */       }
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
/*     */   protected final void coInvoke(FJTask paramFJTask1, FJTask paramFJTask2)
/*     */   {
/* 868 */     int i = this.top;
/* 869 */     if (i < (this.base & this.deq.length - 1) + this.deq.length)
/*     */     {
/* 871 */       this.deq[(i & this.deq.length - 1)].put(paramFJTask1);
/* 872 */       this.top = (i + 1);
/*     */       
/*     */ 
/*     */ 
/* 876 */       if (!paramFJTask2.isDone()) {
/* 877 */         this.runs += 1;
/* 878 */         paramFJTask2.run();
/* 879 */         paramFJTask2.setDone();
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 884 */       while (!paramFJTask1.isDone()) {
/* 885 */         FJTask localFJTask = pop();
/* 886 */         if (localFJTask != null) {
/* 887 */           if (!localFJTask.isDone()) {
/* 888 */             this.runs += 1;
/* 889 */             localFJTask.run();
/* 890 */             localFJTask.setDone();
/* 891 */             if (localFJTask != paramFJTask1) {}
/*     */           }
/*     */         }
/*     */         else {
/* 895 */           scan(paramFJTask1);
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 900 */       slowCoInvoke(paramFJTask1, paramFJTask2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void slowCoInvoke(FJTask paramFJTask1, FJTask paramFJTask2)
/*     */   {
/* 909 */     push(paramFJTask1);
/* 910 */     FJTask.invoke(paramFJTask2);
/* 911 */     taskJoin(paramFJTask1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final void coInvoke(FJTask[] paramArrayOfFJTask)
/*     */   {
/* 920 */     int i = paramArrayOfFJTask.length - 1;
/*     */     
/*     */ 
/*     */ 
/* 924 */     int j = this.top;
/*     */     
/* 926 */     if ((i >= 0) && (j + i < (this.base & this.deq.length - 1) + this.deq.length)) {
/* 927 */       for (int k = 0; k < i; k++) {
/* 928 */         this.deq[(j++ & this.deq.length - 1)].put(paramArrayOfFJTask[k]);
/* 929 */         this.top = j;
/*     */       }
/*     */       
/*     */ 
/* 933 */       FJTask localFJTask1 = paramArrayOfFJTask[i];
/* 934 */       if (!localFJTask1.isDone()) {
/* 935 */         this.runs += 1;
/* 936 */         localFJTask1.run();
/* 937 */         localFJTask1.setDone();
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 942 */       for (int m = 0; m < i; m++) {
/* 943 */         FJTask localFJTask2 = paramArrayOfFJTask[m];
/* 944 */         while (!localFJTask2.isDone())
/*     */         {
/* 946 */           FJTask localFJTask3 = pop();
/* 947 */           if (localFJTask3 != null) {
/* 948 */             if (!localFJTask3.isDone()) {
/* 949 */               this.runs += 1;
/* 950 */               localFJTask3.run();
/* 951 */               localFJTask3.setDone();
/*     */             }
/*     */           }
/*     */           else {
/* 955 */             scan(localFJTask2);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 961 */       slowCoInvoke(paramArrayOfFJTask);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void slowCoInvoke(FJTask[] paramArrayOfFJTask)
/*     */   {
/* 969 */     for (int i = 0; i < paramArrayOfFJTask.length; i++) push(paramArrayOfFJTask[i]);
/* 970 */     for (int j = 0; j < paramArrayOfFJTask.length; j++) taskJoin(paramArrayOfFJTask[j]);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/FJTaskRunner.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */