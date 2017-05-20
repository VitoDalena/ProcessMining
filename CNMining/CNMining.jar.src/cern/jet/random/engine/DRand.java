/*    */ package cern.jet.random.engine;
/*    */ 
/*    */ import edu.cornell.lassp.houle.RngPack.RandomSeedable;
/*    */ import java.util.Date;
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
/*    */ public class DRand
/*    */   extends RandomEngine
/*    */ {
/*    */   private int current;
/*    */   public static final int DEFAULT_SEED = 1;
/*    */   
/*    */   public DRand()
/*    */   {
/* 51 */     this(1);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public DRand(int paramInt)
/*    */   {
/* 58 */     setSeed(paramInt);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public DRand(Date paramDate)
/*    */   {
/* 66 */     this((int)RandomSeedable.ClockSeed(paramDate));
/*    */   }
/*    */   
/*    */ 
/*    */   public int nextInt()
/*    */   {
/* 72 */     this.current *= 663608941;
/*    */     
/*    */ 
/* 75 */     return this.current;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void setSeed(int paramInt)
/*    */   {
/* 84 */     if (paramInt < 0) paramInt = -paramInt;
/* 85 */     int i = (int)((Math.pow(2.0D, 32.0D) - 1.0D) / 4.0D);
/* 86 */     if (paramInt >= i) { paramInt >>= 3;
/*    */     }
/* 88 */     this.current = (4 * paramInt + 1);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/engine/DRand.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */