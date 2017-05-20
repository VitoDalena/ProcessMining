/*     */ package cern.jet.stat.quantile;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.function.DoubleProcedure;
/*     */ import cern.colt.list.DoubleArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class DoubleBufferSet
/*     */   extends BufferSet
/*     */ {
/*     */   protected DoubleBuffer[] buffers;
/*     */   private boolean nextTriggerCalculationState;
/*     */   
/*     */   public DoubleBufferSet(int paramInt1, int paramInt2)
/*     */   {
/*  23 */     this.buffers = new DoubleBuffer[paramInt1];
/*  24 */     clear(paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleBuffer _getFirstEmptyBuffer()
/*     */   {
/*  32 */     DoubleBuffer localDoubleBuffer = null;
/*  33 */     int i = this.buffers.length;
/*  34 */     do { if (this.buffers[i].isEmpty()) {
/*  35 */         if (this.buffers[i].isAllocated()) return this.buffers[i];
/*  36 */         localDoubleBuffer = this.buffers[i];
/*     */       }
/*  33 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  40 */     return localDoubleBuffer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DoubleBuffer[] _getFullOrPartialBuffers()
/*     */   {
/*  47 */     int i = 0;
/*  48 */     int j = this.buffers.length;
/*  49 */     do { if (!this.buffers[j].isEmpty()) i++;
/*  48 */       j--; } while (j >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  53 */     DoubleBuffer[] arrayOfDoubleBuffer = new DoubleBuffer[i];
/*  54 */     int k = 0;
/*  55 */     int m = this.buffers.length;
/*  56 */     do { if (!this.buffers[m].isEmpty()) {
/*  57 */         arrayOfDoubleBuffer[(k++)] = this.buffers[m];
/*     */       }
/*  55 */       m--; } while (m >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  61 */     return arrayOfDoubleBuffer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleBuffer[] _getFullOrPartialBuffersWithLevel(int paramInt)
/*     */   {
/*  69 */     int i = 0;
/*  70 */     int j = this.buffers.length;
/*  71 */     do { if ((!this.buffers[j].isEmpty()) && (this.buffers[j].level() == paramInt)) i++;
/*  70 */       j--; } while (j >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  75 */     DoubleBuffer[] arrayOfDoubleBuffer = new DoubleBuffer[i];
/*  76 */     int k = 0;
/*  77 */     int m = this.buffers.length;
/*  78 */     do { if ((!this.buffers[m].isEmpty()) && (this.buffers[m].level() == paramInt)) {
/*  79 */         arrayOfDoubleBuffer[(k++)] = this.buffers[m];
/*     */       }
/*  77 */       m--; } while (m >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  83 */     return arrayOfDoubleBuffer;
/*     */   }
/*     */   
/*     */ 
/*     */   public int _getMinLevelOfFullOrPartialBuffers()
/*     */   {
/*  89 */     int i = b();
/*  90 */     int j = Integer.MAX_VALUE;
/*     */     
/*     */ 
/*  93 */     for (int k = 0; k < i; k++) {
/*  94 */       DoubleBuffer localDoubleBuffer = this.buffers[k];
/*  95 */       if ((!localDoubleBuffer.isEmpty()) && (localDoubleBuffer.level() < j)) {
/*  96 */         j = localDoubleBuffer.level();
/*     */       }
/*     */     }
/*  99 */     return j;
/*     */   }
/*     */   
/*     */ 
/*     */   public int _getNumberOfEmptyBuffers()
/*     */   {
/* 105 */     int i = 0;
/* 106 */     int j = this.buffers.length;
/* 107 */     do { if (this.buffers[j].isEmpty()) i++;
/* 106 */       j--; } while (j >= 0);
/*     */     
/*     */ 
/*     */ 
/* 110 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */   public DoubleBuffer _getPartialBuffer()
/*     */   {
/* 116 */     int i = this.buffers.length;
/* 117 */     do { if (this.buffers[i].isPartial()) return this.buffers[i];
/* 116 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/* 119 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public int b()
/*     */   {
/* 125 */     return this.buffers.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 132 */     clear(k());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void clear(int paramInt)
/*     */   {
/* 139 */     int i = b(); do { this.buffers[i] = new DoubleBuffer(paramInt);i--; } while (i >= 0);
/* 140 */     this.nextTriggerCalculationState = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 148 */     DoubleBufferSet localDoubleBufferSet = (DoubleBufferSet)super.clone();
/*     */     
/* 150 */     localDoubleBufferSet.buffers = ((DoubleBuffer[])localDoubleBufferSet.buffers.clone());
/* 151 */     int i = this.buffers.length;
/* 152 */     do { localDoubleBufferSet.buffers[i] = ((DoubleBuffer)localDoubleBufferSet.buffers[i].clone());i--;
/* 151 */     } while (i >= 0);
/*     */     
/*     */ 
/* 154 */     return localDoubleBufferSet;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleBuffer collapse(DoubleBuffer[] paramArrayOfDoubleBuffer)
/*     */   {
/* 163 */     int i = 0;
/* 164 */     for (int j = 0; j < paramArrayOfDoubleBuffer.length; j++) { i += paramArrayOfDoubleBuffer[j].weight();
/*     */     }
/*     */     
/* 167 */     int k = k();
/* 168 */     long[] arrayOfLong = new long[k];
/* 169 */     for (int m = 0; m < k; m++) { arrayOfLong[m] = nextTriggerPosition(m, i);
/*     */     }
/*     */     
/* 172 */     double[] arrayOfDouble = getValuesAtPositions(paramArrayOfDoubleBuffer, arrayOfLong);
/*     */     
/*     */ 
/* 175 */     for (int n = 1; n < paramArrayOfDoubleBuffer.length; n++) { paramArrayOfDoubleBuffer[n].clear();
/*     */     }
/* 177 */     DoubleBuffer localDoubleBuffer = paramArrayOfDoubleBuffer[0];
/* 178 */     localDoubleBuffer.values.elements(arrayOfDouble);
/* 179 */     localDoubleBuffer.weight(i);
/*     */     
/* 181 */     return localDoubleBuffer;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean contains(double paramDouble)
/*     */   {
/* 187 */     int i = this.buffers.length;
/* 188 */     do { if ((!this.buffers[i].isEmpty()) && (this.buffers[i].contains(paramDouble))) {
/* 189 */         return true;
/*     */       }
/* 187 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 193 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean forEach(DoubleProcedure paramDoubleProcedure)
/*     */   {
/* 201 */     int i = this.buffers.length;
/* 202 */     do { int j = this.buffers[i].weight();
/* 203 */       do { if (!this.buffers[i].values.forEach(paramDoubleProcedure)) return false;
/* 202 */         j--; } while (j >= 0);
/* 201 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 206 */     return true;
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
/*     */   protected double[] getValuesAtPositions(DoubleBuffer[] paramArrayOfDoubleBuffer, long[] paramArrayOfLong)
/*     */   {
/* 229 */     int i = paramArrayOfDoubleBuffer.length;
/* 230 */     do { paramArrayOfDoubleBuffer[i].sort();i--;
/* 229 */     } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 234 */     int[] arrayOfInt1 = new int[paramArrayOfDoubleBuffer.length];
/* 235 */     double[][] arrayOfDouble = new double[paramArrayOfDoubleBuffer.length][];
/* 236 */     int j = 0;
/* 237 */     int k = paramArrayOfDoubleBuffer.length;
/* 238 */     do { arrayOfInt1[k] = paramArrayOfDoubleBuffer[k].size();
/* 239 */       arrayOfDouble[k] = paramArrayOfDoubleBuffer[k].values.elements();
/* 240 */       j += arrayOfInt1[k];k--;
/* 237 */     } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 247 */     int m = paramArrayOfDoubleBuffer.length;
/* 248 */     int n = paramArrayOfLong.length;
/*     */     
/*     */ 
/* 251 */     int i1 = 0;
/* 252 */     int[] arrayOfInt2 = new int[paramArrayOfDoubleBuffer.length];
/* 253 */     long l1 = 0L;
/* 254 */     long l2 = paramArrayOfLong[i1];
/* 255 */     double[] arrayOfDouble1 = new double[n];
/*     */     
/* 257 */     if (j == 0)
/*     */     {
/*     */ 
/* 260 */       for (i2 = 0; i2 < paramArrayOfLong.length; i2++) {
/* 261 */         arrayOfDouble1[i2] = NaN.0D;
/*     */       }
/* 263 */       return arrayOfDouble1;
/*     */     }
/*     */     
/*     */ 
/* 267 */     while (i1 < n)
/*     */     {
/*     */       int i2;
/*     */       
/*     */ 
/*     */ 
/* 273 */       double d1 = Double.POSITIVE_INFINITY;
/* 274 */       int i3 = -1;
/*     */       
/* 276 */       int i4 = m;
/*     */       do
/*     */       {
/* 279 */         if (arrayOfInt2[i4] < arrayOfInt1[i4])
/*     */         {
/* 281 */           double d2 = arrayOfDouble[i4][arrayOfInt2[i4]];
/* 282 */           if (d2 <= d1) {
/* 283 */             d1 = d2;
/* 284 */             i3 = i4;
/*     */           }
/*     */         }
/* 276 */         i4--; } while (i4 >= 0);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 289 */       DoubleBuffer localDoubleBuffer = paramArrayOfDoubleBuffer[i3];
/*     */       
/*     */ 
/* 292 */       l1 += localDoubleBuffer.weight();
/* 293 */       while ((l1 > l2) && (i1 < n)) {
/* 294 */         arrayOfDouble1[(i1++)] = d1;
/*     */         
/* 296 */         if (i1 < n) { l2 = paramArrayOfLong[i1];
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 301 */       arrayOfInt2[i3] += 1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 307 */     return arrayOfDouble1;
/*     */   }
/*     */   
/*     */ 
/*     */   public int k()
/*     */   {
/* 313 */     return this.buffers[0].k;
/*     */   }
/*     */   
/*     */ 
/*     */   public long memory()
/*     */   {
/* 319 */     long l = 0L;
/* 320 */     int i = this.buffers.length;
/* 321 */     do { l += this.buffers[i].memory();i--;
/* 320 */     } while (i >= 0);
/*     */     
/*     */ 
/* 323 */     return l;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected long nextTriggerPosition(int paramInt, long paramLong)
/*     */   {
/*     */     long l;
/*     */     
/*     */ 
/* 334 */     if (paramLong % 2L != 0L) {
/* 335 */       l = paramInt * paramLong + (paramLong + 1L) / 2L;
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/* 340 */     else if (this.nextTriggerCalculationState) {
/* 341 */       l = paramInt * paramLong + paramLong / 2L;
/*     */     }
/*     */     else {
/* 344 */       l = paramInt * paramLong + (paramLong + 2L) / 2L;
/*     */     }
/*     */     
/*     */ 
/* 348 */     return l;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double phi(double paramDouble)
/*     */   {
/* 358 */     double d = 0.0D;
/* 359 */     int i = this.buffers.length;
/* 360 */     do { if (!this.buffers[i].isEmpty()) {
/* 361 */         d += this.buffers[i].weight * this.buffers[i].rank(paramDouble);
/*     */       }
/* 359 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 365 */     return d / totalSize();
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 371 */     StringBuffer localStringBuffer = new StringBuffer();
/* 372 */     for (int i = 0; i < b(); i++) {
/* 373 */       if (!this.buffers[i].isEmpty()) {
/* 374 */         localStringBuffer.append("buffer#" + i + " = ");
/* 375 */         localStringBuffer.append(this.buffers[i].toString() + "\n");
/*     */       }
/*     */     }
/* 378 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */   public long totalSize()
/*     */   {
/* 384 */     DoubleBuffer[] arrayOfDoubleBuffer = _getFullOrPartialBuffers();
/* 385 */     long l = 0L;
/* 386 */     int i = arrayOfDoubleBuffer.length;
/* 387 */     do { l += arrayOfDoubleBuffer[i].size() * arrayOfDoubleBuffer[i].weight();i--;
/* 386 */     } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/* 390 */     return l;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/stat/quantile/DoubleBufferSet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */