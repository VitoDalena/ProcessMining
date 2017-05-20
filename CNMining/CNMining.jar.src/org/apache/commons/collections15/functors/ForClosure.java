/*     */ package org.apache.commons.collections15.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.collections15.Closure;
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
/*     */ public class ForClosure<T>
/*     */   implements Closure<T>, Serializable
/*     */ {
/*     */   static final long serialVersionUID = -1190120533393621674L;
/*     */   private final int iCount;
/*     */   private final Closure<T> iClosure;
/*     */   
/*     */   public static <T> Closure<T> getInstance(int count, Closure<T> closure)
/*     */   {
/*  57 */     if ((count <= 0) || (closure == null)) {
/*  58 */       return NOPClosure.INSTANCE;
/*     */     }
/*  60 */     if (count == 1) {
/*  61 */       return closure;
/*     */     }
/*  63 */     return new ForClosure(count, closure);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ForClosure(int count, Closure<T> closure)
/*     */   {
/*  75 */     this.iCount = count;
/*  76 */     this.iClosure = closure;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void execute(T input)
/*     */   {
/*  85 */     for (int i = 0; i < this.iCount; i++) {
/*  86 */       this.iClosure.execute(input);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Closure<T> getClosure()
/*     */   {
/*  97 */     return this.iClosure;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getCount()
/*     */   {
/* 107 */     return this.iCount;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/ForClosure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */