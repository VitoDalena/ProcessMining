/*     */ package cern.colt.map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class QuickOpenIntIntHashMap
/*     */   extends OpenIntIntHashMap
/*     */ {
/*  27 */   public int totalProbesSaved = 0;
/*     */   
/*     */ 
/*     */   public QuickOpenIntIntHashMap()
/*     */   {
/*  32 */     this(277);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public QuickOpenIntIntHashMap(int paramInt)
/*     */   {
/*  42 */     this(paramInt, 0.2D, 0.5D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public QuickOpenIntIntHashMap(int paramInt, double paramDouble1, double paramDouble2)
/*     */   {
/*  54 */     setUp(paramInt, paramDouble1, paramDouble2);
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
/*     */   public boolean put(int paramInt1, int paramInt2)
/*     */   {
/*  80 */     int[] arrayOfInt = this.table;
/*  81 */     byte[] arrayOfByte = this.state;
/*  82 */     int j = arrayOfInt.length;
/*     */     
/*  84 */     int k = HashFunctions.hash(paramInt1) & 0x7FFFFFFF;
/*  85 */     int m = k % j;
/*  86 */     int n = k / j % j;
/*  87 */     if (n == 0) { n = 1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  93 */     int i1 = 0;
/*  94 */     int i2 = m;
/*  95 */     while ((arrayOfByte[m] == 1) && (arrayOfInt[m] != paramInt1)) {
/*  96 */       i1++;
/*  97 */       m -= n;
/*     */       
/*  99 */       if (m < 0) { m += j;
/*     */       }
/*     */     }
/* 102 */     if (arrayOfByte[m] == 1)
/*     */     {
/* 104 */       this.values[m] = paramInt2;
/* 105 */       return false;
/*     */     }
/*     */     
/*     */     int i3;
/* 109 */     if (this.distinct > this.highWaterMark) {
/* 110 */       i3 = chooseGrowCapacity(this.distinct + 1, this.minLoadFactor, this.maxLoadFactor);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 115 */       rehash(i3);
/* 116 */       return put(paramInt1, paramInt2);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 132 */     while (i1 > 1)
/*     */     {
/* 134 */       int i = arrayOfInt[i2];
/* 135 */       k = HashFunctions.hash(i) & 0x7FFFFFFF;
/* 136 */       n = k / j % j;
/* 137 */       if (n == 0) n = 1;
/* 138 */       i3 = i2 - n;
/* 139 */       if (i3 < 0) { i3 += j;
/*     */       }
/* 141 */       if (arrayOfByte[i3] != 0) {
/* 142 */         i2 = i3;
/* 143 */         i1--;
/*     */       }
/*     */       else
/*     */       {
/* 147 */         this.totalProbesSaved += i1 - 1;
/* 148 */         arrayOfInt[i3] = i;
/* 149 */         arrayOfByte[i3] = 1;
/* 150 */         this.values[i3] = this.values[i2];
/* 151 */         m = i2;
/* 152 */         i1 = 0;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 157 */     this.table[m] = paramInt1;
/* 158 */     this.values[m] = paramInt2;
/* 159 */     if (this.state[m] == 0) this.freeEntries -= 1;
/* 160 */     this.state[m] = 1;
/* 161 */     this.distinct += 1;
/*     */     
/* 163 */     if (this.freeEntries < 1) {
/* 164 */       i3 = chooseGrowCapacity(this.distinct + 1, this.minLoadFactor, this.maxLoadFactor);
/* 165 */       rehash(i3);
/*     */     }
/*     */     
/* 168 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void rehash(int paramInt)
/*     */   {
/* 177 */     int i = this.table.length;
/*     */     
/*     */ 
/* 180 */     int[] arrayOfInt1 = this.table;
/* 181 */     int[] arrayOfInt2 = this.values;
/* 182 */     byte[] arrayOfByte1 = this.state;
/*     */     
/* 184 */     int[] arrayOfInt3 = new int[paramInt];
/* 185 */     int[] arrayOfInt4 = new int[paramInt];
/* 186 */     byte[] arrayOfByte2 = new byte[paramInt];
/*     */     
/* 188 */     this.lowWaterMark = chooseLowWaterMark(paramInt, this.minLoadFactor);
/* 189 */     this.highWaterMark = chooseHighWaterMark(paramInt, this.maxLoadFactor);
/*     */     
/* 191 */     this.table = arrayOfInt3;
/* 192 */     this.values = arrayOfInt4;
/* 193 */     this.state = arrayOfByte2;
/* 194 */     this.freeEntries = (paramInt - this.distinct);
/*     */     
/* 196 */     int j = this.distinct;
/* 197 */     this.distinct = Integer.MIN_VALUE;
/* 198 */     for (int k = i; k-- > 0;) {
/* 199 */       if (arrayOfByte1[k] == 1) {
/* 200 */         put(arrayOfInt1[k], arrayOfInt2[k]);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 210 */     this.distinct = j;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/map/QuickOpenIntIntHashMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */