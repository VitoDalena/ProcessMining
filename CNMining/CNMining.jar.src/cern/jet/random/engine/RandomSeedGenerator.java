/*    */ package cern.jet.random.engine;
/*    */ 
/*    */ import cern.colt.PersistentObject;
/*    */ import java.io.PrintStream;
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
/*    */ public class RandomSeedGenerator
/*    */   extends PersistentObject
/*    */ {
/*    */   protected int row;
/*    */   protected int column;
/*    */   
/*    */   public RandomSeedGenerator()
/*    */   {
/* 33 */     this(0, 0);
/*    */   }
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
/*    */   public RandomSeedGenerator(int paramInt1, int paramInt2)
/*    */   {
/* 47 */     this.row = paramInt1;
/* 48 */     this.column = paramInt2;
/*    */   }
/*    */   
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 54 */     int i = Integer.parseInt(paramArrayOfString[0]);
/* 55 */     int j = Integer.parseInt(paramArrayOfString[1]);
/* 56 */     int k = Integer.parseInt(paramArrayOfString[2]);
/* 57 */     new RandomSeedGenerator(i, j).print(k);
/*    */   }
/*    */   
/*    */ 
/*    */   public int nextSeed()
/*    */   {
/* 63 */     return RandomSeedTable.getSeedAtRowColumn(this.row++, this.column);
/*    */   }
/*    */   
/*    */ 
/*    */   public void print(int paramInt)
/*    */   {
/* 69 */     System.out.println("Generating " + paramInt + " random seeds...");
/* 70 */     RandomSeedGenerator localRandomSeedGenerator = (RandomSeedGenerator)clone();
/* 71 */     for (int i = 0; i < paramInt; i++) {
/* 72 */       int j = localRandomSeedGenerator.nextSeed();
/* 73 */       System.out.println(j);
/*    */     }
/*    */     
/* 76 */     System.out.println("\ndone.");
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/engine/RandomSeedGenerator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */