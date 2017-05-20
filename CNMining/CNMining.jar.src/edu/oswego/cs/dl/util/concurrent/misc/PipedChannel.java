/*     */ package edu.oswego.cs.dl.util.concurrent.misc;
/*     */ 
/*     */ import edu.oswego.cs.dl.util.concurrent.SemaphoreControlledChannel;
/*     */ import java.io.IOException;
/*     */ import java.io.InterruptedIOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.PipedInputStream;
/*     */ import java.io.PipedOutputStream;
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
/*     */ public class PipedChannel
/*     */   extends SemaphoreControlledChannel
/*     */ {
/*     */   protected ObjectInputStream in_;
/*     */   protected ObjectOutputStream out_;
/*     */   protected final PipedOutputStream outp_;
/*     */   protected final PipedInputStream inp_;
/*     */   
/*     */   public PipedChannel()
/*     */   {
/*  35 */     super(1);
/*     */     try
/*     */     {
/*  38 */       this.outp_ = new PipedOutputStream();
/*  39 */       this.inp_ = new PipedInputStream();
/*  40 */       this.inp_.connect(this.outp_);
/*     */     }
/*     */     catch (IOException localIOException) {
/*  43 */       localIOException.printStackTrace();
/*  44 */       throw new Error("Cannot construct Pipe?");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized ObjectInputStream in()
/*     */   {
/*     */     try
/*     */     {
/*  56 */       if (this.in_ == null) this.in_ = new ObjectInputStream(this.inp_);
/*  57 */       return this.in_;
/*     */     }
/*     */     catch (IOException localIOException) {
/*  60 */       localIOException.printStackTrace();
/*  61 */       throw new Error("IO exception during open");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected synchronized ObjectOutputStream out()
/*     */   {
/*     */     try
/*     */     {
/*  71 */       if (this.out_ == null) this.out_ = new ObjectOutputStream(this.outp_);
/*  72 */       return this.out_;
/*     */     }
/*     */     catch (IOException localIOException) {
/*  75 */       localIOException.printStackTrace();
/*  76 */       throw new Error("IO exception during open");
/*     */     }
/*     */   }
/*     */   
/*     */   protected void insert(Object paramObject)
/*     */   {
/*     */     try
/*     */     {
/*  84 */       out().writeObject(paramObject);
/*     */     }
/*     */     catch (InterruptedIOException localInterruptedIOException) {
/*  87 */       Thread.currentThread().interrupt();
/*     */     }
/*     */     catch (IOException localIOException) {
/*  90 */       localIOException.printStackTrace();
/*  91 */       throw new Error("IO exception during put");
/*     */     }
/*     */   }
/*     */   
/*     */   protected Object extract()
/*     */   {
/*     */     try {
/*  98 */       return in().readObject();
/*     */     }
/*     */     catch (InterruptedIOException localInterruptedIOException) {
/* 101 */       Thread.currentThread().interrupt();
/* 102 */       return null;
/*     */     }
/*     */     catch (IOException localIOException) {
/* 105 */       localIOException.printStackTrace();
/* 106 */       throw new Error("IO exception during take");
/*     */     }
/*     */     catch (ClassNotFoundException localClassNotFoundException) {
/* 109 */       localClassNotFoundException.printStackTrace();
/* 110 */       throw new Error("Serialization exception during take");
/*     */     }
/*     */   }
/*     */   
/*     */   public Object peek() {
/* 115 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/misc/PipedChannel.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */