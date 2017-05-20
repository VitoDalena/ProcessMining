/*     */ package cern.jet.random.sampling;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.list.AbstractBooleanList;
/*     */ import cern.colt.list.BooleanArrayList;
/*     */ import cern.colt.list.IntArrayList;
/*     */ import cern.jet.random.AbstractDistribution;
/*     */ import cern.jet.random.Uniform;
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
/*     */ public class WeightedRandomSampler
/*     */   extends PersistentObject
/*     */ {
/*     */   protected int skip;
/*     */   protected int nextTriggerPos;
/*     */   protected int nextSkip;
/*     */   protected int weight;
/*     */   protected Uniform generator;
/*     */   static final int UNDEFINED = -1;
/*     */   
/*     */   public WeightedRandomSampler()
/*     */   {
/*  38 */     this(1, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public WeightedRandomSampler(int paramInt, RandomElement paramRandomElement)
/*     */   {
/*  49 */     if (paramRandomElement == null) paramRandomElement = AbstractDistribution.makeDefaultGenerator();
/*  50 */     this.generator = new Uniform(paramRandomElement);
/*  51 */     setWeight(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */   public Object clone()
/*     */   {
/*  57 */     WeightedRandomSampler localWeightedRandomSampler = (WeightedRandomSampler)super.clone();
/*  58 */     localWeightedRandomSampler.generator = ((Uniform)this.generator.clone());
/*  59 */     return localWeightedRandomSampler;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getWeight()
/*     */   {
/*  66 */     return this.weight;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean sampleNextElement()
/*     */   {
/*  75 */     if (this.skip > 0) {
/*  76 */       this.skip -= 1;
/*  77 */       return false;
/*     */     }
/*     */     
/*  80 */     if (this.nextTriggerPos == -1) {
/*  81 */       if (this.weight == 1) this.nextTriggerPos = 0; else {
/*  82 */         this.nextTriggerPos = this.generator.nextIntFromTo(0, this.weight - 1);
/*     */       }
/*  84 */       this.nextSkip = (this.weight - 1 - this.nextTriggerPos);
/*     */     }
/*     */     
/*  87 */     if (this.nextTriggerPos > 0) {
/*  88 */       this.nextTriggerPos -= 1;
/*  89 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  93 */     this.nextTriggerPos = -1;
/*  94 */     this.skip = this.nextSkip;
/*     */     
/*  96 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setWeight(int paramInt)
/*     */   {
/* 103 */     if (paramInt < 1) throw new IllegalArgumentException("bad weight");
/* 104 */     this.weight = paramInt;
/* 105 */     this.skip = 0;
/* 106 */     this.nextTriggerPos = -1;
/* 107 */     this.nextSkip = 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public static void test(int paramInt1, int paramInt2)
/*     */   {
/* 113 */     WeightedRandomSampler localWeightedRandomSampler = new WeightedRandomSampler();
/* 114 */     localWeightedRandomSampler.setWeight(paramInt1);
/*     */     
/* 116 */     IntArrayList localIntArrayList = new IntArrayList();
/* 117 */     for (int i = 0; i < paramInt2; i++) {
/* 118 */       if (localWeightedRandomSampler.sampleNextElement()) { localIntArrayList.add(i);
/*     */       }
/*     */     }
/* 121 */     System.out.println("Sample = " + localIntArrayList);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void xsampleNextElements(BooleanArrayList paramBooleanArrayList)
/*     */   {
/* 131 */     int i = paramBooleanArrayList.size();
/* 132 */     boolean[] arrayOfBoolean = paramBooleanArrayList.elements();
/* 133 */     for (int j = 0; j < i; j++) {
/* 134 */       if (this.skip > 0) {
/* 135 */         this.skip -= 1;
/* 136 */         arrayOfBoolean[j] = false;
/*     */       }
/*     */       else
/*     */       {
/* 140 */         if (this.nextTriggerPos == -1) {
/* 141 */           if (this.weight == 1) this.nextTriggerPos = 0; else {
/* 142 */             this.nextTriggerPos = this.generator.nextIntFromTo(0, this.weight - 1);
/*     */           }
/* 144 */           this.nextSkip = (this.weight - 1 - this.nextTriggerPos);
/*     */         }
/*     */         
/* 147 */         if (this.nextTriggerPos > 0) {
/* 148 */           this.nextTriggerPos -= 1;
/* 149 */           arrayOfBoolean[j] = false;
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/* 154 */           this.nextTriggerPos = -1;
/* 155 */           this.skip = this.nextSkip;
/* 156 */           arrayOfBoolean[j] = true;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/sampling/WeightedRandomSampler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */