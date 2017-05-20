/*    */ package edu.cornell.lassp.houle.RngPack;
/*    */ 
/*    */ import cern.colt.PersistentObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RandomShuffle
/*    */   extends RandomElement
/*    */ {
/*    */   RandomElement generatorA;
/*    */   RandomElement generatorB;
/*    */   int decksize;
/*    */   double[] deck;
/*    */   
/*    */   public RandomShuffle(RandomElement paramRandomElement1, RandomElement paramRandomElement2, int paramInt)
/*    */   {
/* 52 */     this.generatorA = paramRandomElement1;
/* 53 */     this.generatorB = paramRandomElement2;
/* 54 */     this.decksize = paramInt;
/*    */     
/* 56 */     stackdeck();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Object clone()
/*    */   {
/* 65 */     RandomShuffle localRandomShuffle = (RandomShuffle)super.clone();
/* 66 */     localRandomShuffle.generatorA = ((RandomElement)this.generatorA.clone());
/* 67 */     localRandomShuffle.generatorB = ((RandomElement)this.generatorB.clone());
/* 68 */     localRandomShuffle.deck = ((double[])this.deck.clone());
/* 69 */     return localRandomShuffle;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public double raw()
/*    */   {
/* 81 */     int i = this.generatorB.choose(0, this.decksize - 1);
/* 82 */     double d = this.deck[i];
/* 83 */     this.deck[i] = this.generatorA.raw();
/*    */     
/* 85 */     return d;
/*    */   }
/*    */   
/*    */   private void stackdeck()
/*    */   {
/* 90 */     this.deck = new double[this.decksize];
/*    */     
/* 92 */     for (int i = 0; i < this.decksize; i++) {
/* 93 */       this.deck[i] = this.generatorA.raw();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/cornell/lassp/houle/RngPack/RandomShuffle.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */