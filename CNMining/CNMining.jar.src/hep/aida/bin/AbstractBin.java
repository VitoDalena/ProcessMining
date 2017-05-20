/*     */ package hep.aida.bin;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractBin
/*     */   extends PersistentObject
/*     */ {
/*     */   public final double center()
/*     */   {
/*  26 */     return center(0);
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
/*     */   public synchronized double center(int paramInt)
/*     */   {
/*  43 */     return 0.5D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void clear();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/*  56 */     if (!(paramObject instanceof AbstractBin)) return false;
/*  57 */     AbstractBin localAbstractBin = (AbstractBin)paramObject;
/*  58 */     return (size() == localAbstractBin.size()) && (value() == localAbstractBin.value()) && (error() == localAbstractBin.error()) && (center() == localAbstractBin.center());
/*     */   }
/*     */   
/*     */ 
/*     */   public final double error()
/*     */   {
/*  64 */     return error(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized double error(int paramInt)
/*     */   {
/*  73 */     return 0.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract boolean isRebinnable();
/*     */   
/*     */ 
/*     */ 
/*     */   public final double offset()
/*     */   {
/*  85 */     return offset(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double offset(int paramInt)
/*     */   {
/*  97 */     return 1.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract int size();
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized String toString()
/*     */   {
/* 109 */     StringBuffer localStringBuffer = new StringBuffer();
/* 110 */     localStringBuffer.append(getClass().getName());
/* 111 */     localStringBuffer.append("\n-------------");
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 117 */     localStringBuffer.append("\n");
/* 118 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void trimToSize() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final double value()
/*     */   {
/* 132 */     return value(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double value(int paramInt)
/*     */   {
/* 141 */     return 0.0D;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/hep/aida/bin/AbstractBin.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */