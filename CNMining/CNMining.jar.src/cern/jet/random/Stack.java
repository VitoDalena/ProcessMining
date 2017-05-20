/*    */ package cern.jet.random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class Stack
/*    */ {
/*    */   int N;
/*    */   
/*    */ 
/*    */ 
/*    */   int[] v;
/*    */   
/*    */ 
/*    */ 
/*    */   int i;
/*    */   
/*    */ 
/*    */ 
/*    */   public Stack(int paramInt)
/*    */   {
/* 22 */     this.N = paramInt;
/* 23 */     this.i = -1;
/* 24 */     this.v = new int[this.N];
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
/*    */   public int pop()
/*    */   {
/* 69 */     if (this.i < 0) throw new InternalError("Cannot pop stack!");
/* 70 */     this.i -= 1;
/* 71 */     return this.v[(this.i + 1)];
/*    */   }
/*    */   
/*    */ 
/*    */   public void push(int paramInt)
/*    */   {
/* 77 */     this.i += 1;
/* 78 */     if (this.i >= this.N) throw new InternalError("Cannot push stack!");
/* 79 */     this.v[this.i] = paramInt;
/*    */   }
/*    */   
/*    */ 
/*    */   public int size()
/*    */   {
/* 85 */     return this.i + 1;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/Stack.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */