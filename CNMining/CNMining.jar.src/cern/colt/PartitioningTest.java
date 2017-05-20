/*     */ package cern.colt;
/*     */ 
/*     */ import cern.colt.list.AbstractIntList;
/*     */ import cern.colt.list.AbstractList;
/*     */ import cern.colt.list.DoubleArrayList;
/*     */ import cern.colt.list.IntArrayList;
/*     */ import cern.colt.matrix.DoubleFactory2D;
/*     */ import cern.colt.matrix.DoubleFactory3D;
/*     */ import cern.colt.matrix.DoubleMatrix1D;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.colt.matrix.DoubleMatrix3D;
/*     */ import cern.jet.random.Uniform;
/*     */ import cern.jet.random.engine.MersenneTwister;
/*     */ import jal.INT.Modification;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class PartitioningTest
/*     */ {
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  30 */     int i = Integer.parseInt(paramArrayOfString[0]);
/*  31 */     int j = Integer.parseInt(paramArrayOfString[1]);
/*  32 */     int k = Integer.parseInt(paramArrayOfString[2]);
/*  33 */     boolean bool1 = new Boolean(paramArrayOfString[3]).booleanValue();
/*  34 */     String str = paramArrayOfString[4];
/*  35 */     boolean bool2 = paramArrayOfString[5].equals("new");
/*     */   }
/*     */   
/*     */ 
/*     */   public static void testPartition()
/*     */   {
/*  41 */     System.out.println("\n\n");
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  64 */     Object localObject = DoubleFactory2D.dense.descending(4, 2);
/*     */     
/*     */ 
/*  67 */     double[] arrayOfDouble1 = { 5.0D, 10.0D, 12.0D };
/*     */     
/*  69 */     DoubleMatrix1D localDoubleMatrix1D = ((DoubleMatrix2D)localObject).viewColumn(0);
/*     */     
/*  71 */     int[] arrayOfInt = new int[arrayOfDouble1.length];
/*  72 */     System.out.println(localObject);
/*  73 */     System.out.println("col1=" + localDoubleMatrix1D);
/*  74 */     double[] arrayOfDouble2 = localDoubleMatrix1D.toArray();
/*  75 */     System.out.println("col2=" + new DoubleArrayList(arrayOfDouble2));
/*     */     
/*     */ 
/*  78 */     System.out.println("col3=" + new DoubleArrayList(arrayOfDouble2));
/*  79 */     System.out.println("sorted1=" + cern.colt.matrix.doublealgo.Sorting.quickSort.sort((DoubleMatrix2D)localObject, 0));
/*     */     
/*  81 */     System.out.println(cern.colt.matrix.doublealgo.Partitioning.partition((DoubleMatrix2D)localObject, 0, arrayOfDouble1, arrayOfInt));
/*     */     
/*  83 */     System.out.println("splitters=" + new DoubleArrayList(arrayOfDouble1));
/*  84 */     System.out.println("splitIndexes=" + new IntArrayList(arrayOfInt));
/*     */     
/*  86 */     System.out.println(localObject);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  97 */     localObject = DoubleFactory3D.dense.descending(4, 2, 3);
/*  98 */     System.out.println(localObject);
/*  99 */     System.out.println("sorted1=" + cern.colt.matrix.doublealgo.Sorting.quickSort.sort((DoubleMatrix3D)localObject, 0, 0));
/* 100 */     System.out.println(localObject);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void testPartition(IntArrayList paramIntArrayList1, int paramInt1, int paramInt2, IntArrayList paramIntArrayList2)
/*     */   {
/* 145 */     IntArrayList localIntArrayList1 = new IntArrayList(paramIntArrayList2.size());
/* 146 */     localIntArrayList1.setSize(paramIntArrayList2.size());
/*     */     
/* 148 */     IntArrayList localIntArrayList2 = paramIntArrayList1.copy();
/* 149 */     Partitioning.partition(localIntArrayList2.elements(), paramInt1, paramInt2, paramIntArrayList2.elements(), 0, paramIntArrayList2.size() - 1, localIntArrayList1.elements());
/*     */     
/*     */ 
/* 152 */     int i = Integer.MIN_VALUE;
/* 153 */     int j = paramInt1 - 1;
/* 154 */     for (int k = 0; k < paramIntArrayList2.size(); k++) {
/* 155 */       m = paramIntArrayList2.get(k);
/* 156 */       int n = localIntArrayList1.get(k);
/* 157 */       for (i1 = j + 1; i1 <= n; i1++) {
/* 158 */         if ((i > localIntArrayList2.get(i1)) || (localIntArrayList2.get(i1) >= m)) {
/* 159 */           throw new RuntimeException("bug detected");
/*     */         }
/*     */       }
/* 162 */       i = m;
/* 163 */       j = n;
/*     */     }
/*     */     
/*     */ 
/* 167 */     for (int m = 1 + j; m <= paramInt2; m++) {
/* 168 */       if ((i > localIntArrayList2.get(m)) || (localIntArrayList2.get(m) > Integer.MAX_VALUE)) {
/* 169 */         System.out.println("list   =" + paramIntArrayList1.partFromTo(paramInt1, paramInt2));
/* 170 */         System.out.println("partial=" + localIntArrayList2.partFromTo(paramInt1, paramInt2));
/* 171 */         System.out.println("splitters=" + paramIntArrayList2);
/* 172 */         System.out.println("splitIndexes=" + localIntArrayList1);
/* 173 */         System.out.println("j=" + m);
/* 174 */         System.out.println("element[j]=" + localIntArrayList2.get(m));
/* 175 */         System.out.println("lastSplitter=" + i);
/* 176 */         throw new RuntimeException("bug detected");
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 182 */     localIntArrayList2.sortFromTo(paramInt1, paramInt2);
/* 183 */     IntArrayList localIntArrayList3 = paramIntArrayList1.copy();
/* 184 */     localIntArrayList3.sortFromTo(paramInt1, paramInt2);
/*     */     
/* 186 */     int i1 = paramIntArrayList1.size();
/* 187 */     if ((i1 != localIntArrayList2.size()) || (!jal.INT.Sorting.includes(localIntArrayList2.elements(), localIntArrayList3.elements(), paramInt1, paramInt2 + 1, paramInt1, paramInt2 + 1)) || (!jal.INT.Sorting.includes(localIntArrayList3.elements(), localIntArrayList2.elements(), paramInt1, paramInt2 + 1, paramInt1, paramInt2 + 1)))
/*     */     {
/*     */ 
/* 190 */       System.out.println("sortedList=" + localIntArrayList3.partFromTo(paramInt1, paramInt2));
/* 191 */       System.out.println("partiallySorted=" + localIntArrayList2.partFromTo(paramInt1, paramInt2));
/* 192 */       throw new RuntimeException("bug detected");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void testPartitionRandomly(int paramInt)
/*     */   {
/* 199 */     MersenneTwister localMersenneTwister = new MersenneTwister();
/* 200 */     Uniform localUniform = new Uniform(localMersenneTwister);
/*     */     
/* 202 */     for (int i = 0; i < paramInt; i++) {
/* 203 */       int j = 50;
/* 204 */       int k = 2 * j;
/*     */       
/*     */ 
/* 207 */       int m = localUniform.nextIntFromTo(0, j);
/*     */       int n;
/* 209 */       int i1; if (m == 0) {
/* 210 */         n = 0;i1 = -1;
/*     */       }
/*     */       else {
/* 213 */         n = localUniform.nextIntFromTo(0, m - 1);
/* 214 */         i1 = localUniform.nextIntFromTo(Math.min(n, m - 1), m - 1);
/*     */       }
/*     */       
/* 217 */       int i2 = localUniform.nextIntFromTo(m / 2, 2 * m);
/* 218 */       int i3 = localUniform.nextIntFromTo(i2, 2 * m);
/* 219 */       IntArrayList localIntArrayList1 = new IntArrayList(m);
/* 220 */       for (int i4 = 0; i4 < m; i4++) { localIntArrayList1.add(localUniform.nextIntFromTo(i2, i3));
/*     */       }
/* 222 */       int i5 = localUniform.nextIntFromTo(0, k);
/* 223 */       IntArrayList localIntArrayList2 = new IntArrayList(i5);
/* 224 */       for (int i6 = 0; i6 < i5; i6++) localIntArrayList2.add(localUniform.nextIntFromTo(i2 / 2, 2 * i3));
/* 225 */       localIntArrayList2.sort();
/*     */       
/* 227 */       i5 = Modification.unique(localIntArrayList2.elements(), 0, i5);
/* 228 */       localIntArrayList2.setSize(i5);
/*     */       
/*     */ 
/* 231 */       testPartition(localIntArrayList1, n, i1, localIntArrayList2);
/*     */     }
/*     */     
/* 234 */     System.out.println("All tests passed. No bug detected.");
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/PartitioningTest.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */