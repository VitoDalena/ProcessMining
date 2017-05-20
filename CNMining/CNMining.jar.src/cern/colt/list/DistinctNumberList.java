/*     */ package cern.colt.list;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DistinctNumberList
/*     */   extends AbstractLongList
/*     */ {
/*     */   protected long[] distinctValues;
/*     */   protected MinMaxNumberList elements;
/*     */   
/*     */   public DistinctNumberList(long[] paramArrayOfLong, int paramInt)
/*     */   {
/*  67 */     setUp(paramArrayOfLong, paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(long paramLong)
/*     */   {
/*  76 */     this.elements.add(codeOf(paramLong));
/*  77 */     this.size += 1;
/*     */   }
/*     */   
/*     */ 
/*     */   protected int codeOf(long paramLong)
/*     */   {
/*  83 */     int i = Arrays.binarySearch(this.distinctValues, paramLong);
/*  84 */     if (i < 0) throw new IllegalArgumentException("Element=" + paramLong + " not contained in distinct elements.");
/*  85 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void ensureCapacity(int paramInt)
/*     */   {
/*  94 */     this.elements.ensureCapacity(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getQuick(int paramInt)
/*     */   {
/* 105 */     return this.distinctValues[((int)this.elements.getQuick(paramInt))];
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
/*     */   public void removeFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 118 */     this.elements.removeFromTo(paramInt1, paramInt2);
/* 119 */     this.size -= paramInt2 - paramInt1 + 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setQuick(int paramInt, long paramLong)
/*     */   {
/* 131 */     this.elements.setQuick(paramInt, codeOf(paramLong));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void setSizeRaw(int paramInt)
/*     */   {
/* 138 */     super.setSizeRaw(paramInt);
/* 139 */     this.elements.setSizeRaw(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setUp(long[] paramArrayOfLong, int paramInt)
/*     */   {
/* 148 */     this.distinctValues = paramArrayOfLong;
/*     */     
/* 150 */     this.elements = new MinMaxNumberList(0L, paramArrayOfLong.length - 1, paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void trimToSize()
/*     */   {
/* 158 */     this.elements.trimToSize();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/list/DistinctNumberList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */