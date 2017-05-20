/*     */ package cern.jet.random.sampling;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.Timer;
/*     */ import cern.colt.list.AbstractBooleanList;
/*     */ import cern.colt.list.BooleanArrayList;
/*     */ import cern.colt.list.LongArrayList;
/*     */ import edu.cornell.lassp.houle.RngPack.RandomElement;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RandomSamplingAssistant
/*     */   extends PersistentObject
/*     */ {
/*     */   protected RandomSampler sampler;
/*     */   protected long[] buffer;
/*     */   protected int bufferPosition;
/*     */   protected long skip;
/*     */   protected long n;
/*     */   static final int MAX_BUFFER_SIZE = 200;
/*     */   
/*     */   public RandomSamplingAssistant(long paramLong1, long paramLong2, RandomElement paramRandomElement)
/*     */   {
/*  42 */     this.n = paramLong1;
/*  43 */     this.sampler = new RandomSampler(paramLong1, paramLong2, 0L, paramRandomElement);
/*  44 */     this.buffer = new long[(int)Math.min(paramLong1, 200L)];
/*  45 */     if (paramLong1 > 0L) { this.buffer[0] = -1L;
/*     */     }
/*  47 */     fetchNextBlock();
/*     */   }
/*     */   
/*     */ 
/*     */   public Object clone()
/*     */   {
/*  53 */     RandomSamplingAssistant localRandomSamplingAssistant = (RandomSamplingAssistant)super.clone();
/*  54 */     localRandomSamplingAssistant.sampler = ((RandomSampler)this.sampler.clone());
/*  55 */     return localRandomSamplingAssistant;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void fetchNextBlock()
/*     */   {
/*  61 */     if (this.n > 0L) {
/*  62 */       long l = this.buffer[this.bufferPosition];
/*  63 */       this.sampler.nextBlock((int)Math.min(this.n, 200L), this.buffer, 0);
/*  64 */       this.skip = (this.buffer[0] - l - 1L);
/*  65 */       this.bufferPosition = 0;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public RandomElement getRandomGenerator()
/*     */   {
/*  72 */     return this.sampler.my_RandomGenerator;
/*     */   }
/*     */   
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  78 */     long l1 = Long.parseLong(paramArrayOfString[0]);
/*  79 */     long l2 = Long.parseLong(paramArrayOfString[1]);
/*     */     
/*  81 */     testArraySampling((int)l1, (int)l2);
/*     */   }
/*     */   
/*     */ 
/*     */   public static int[] sampleArray(int paramInt, int[] paramArrayOfInt)
/*     */   {
/*  87 */     RandomSamplingAssistant localRandomSamplingAssistant = new RandomSamplingAssistant(paramInt, paramArrayOfInt.length, null);
/*  88 */     int[] arrayOfInt = new int[paramInt];
/*  89 */     int i = 0;
/*  90 */     int j = paramArrayOfInt.length;
/*  91 */     for (int k = 0; k < j; k++) {
/*  92 */       if (localRandomSamplingAssistant.sampleNextElement()) arrayOfInt[(i++)] = paramArrayOfInt[k];
/*     */     }
/*  94 */     return arrayOfInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean sampleNextElement()
/*     */   {
/* 101 */     if (this.n == 0L) return false;
/* 102 */     if (this.skip-- > 0L) { return false;
/*     */     }
/*     */     
/* 105 */     this.n -= 1L;
/* 106 */     if (this.bufferPosition < this.buffer.length - 1) {
/* 107 */       this.skip = (this.buffer[(this.bufferPosition + 1)] - this.buffer[(this.bufferPosition++)]);
/* 108 */       this.skip -= 1L;
/*     */     }
/*     */     else {
/* 111 */       fetchNextBlock();
/*     */     }
/*     */     
/* 114 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void test(long paramLong1, long paramLong2)
/*     */   {
/* 121 */     RandomSamplingAssistant localRandomSamplingAssistant = new RandomSamplingAssistant(paramLong1, paramLong2, null);
/*     */     
/* 123 */     LongArrayList localLongArrayList = new LongArrayList((int)paramLong1);
/* 124 */     Timer localTimer = new Timer().start();
/*     */     
/* 126 */     for (long l = 0L; l < paramLong2; l += 1L) {
/* 127 */       if (localRandomSamplingAssistant.sampleNextElement()) {
/* 128 */         localLongArrayList.add(l);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 133 */     localTimer.stop().display();
/* 134 */     System.out.println("sample=" + localLongArrayList);
/* 135 */     System.out.println("Good bye.\n");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void testArraySampling(int paramInt1, int paramInt2)
/*     */   {
/* 142 */     int[] arrayOfInt1 = new int[paramInt2];
/* 143 */     for (int i = 0; i < paramInt2; i++) { arrayOfInt1[i] = i;
/*     */     }
/* 145 */     Timer localTimer = new Timer().start();
/*     */     
/* 147 */     int[] arrayOfInt2 = sampleArray(paramInt1, arrayOfInt1);
/*     */     
/* 149 */     localTimer.stop().display();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 164 */     System.out.println("Good bye.\n");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void xsampleNextElements(BooleanArrayList paramBooleanArrayList)
/*     */   {
/* 173 */     int i = paramBooleanArrayList.size();
/* 174 */     boolean[] arrayOfBoolean = paramBooleanArrayList.elements();
/* 175 */     for (int j = 0; j < i; j++) {
/* 176 */       if (this.n == 0L) {
/* 177 */         arrayOfBoolean[j] = false;
/*     */ 
/*     */       }
/* 180 */       else if (this.skip-- > 0L) {
/* 181 */         arrayOfBoolean[j] = false;
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 186 */         this.n -= 1L;
/* 187 */         if (this.bufferPosition < this.buffer.length - 1) {
/* 188 */           this.skip = (this.buffer[(this.bufferPosition + 1)] - this.buffer[(this.bufferPosition++)]);
/* 189 */           this.skip -= 1L;
/*     */         }
/*     */         else {
/* 192 */           fetchNextBlock();
/*     */         }
/*     */         
/* 195 */         arrayOfBoolean[j] = true;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/sampling/RandomSamplingAssistant.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */