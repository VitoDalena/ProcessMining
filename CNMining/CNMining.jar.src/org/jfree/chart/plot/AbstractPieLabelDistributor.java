/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractPieLabelDistributor
/*     */   implements Serializable
/*     */ {
/*     */   protected List labels;
/*     */   
/*     */   public AbstractPieLabelDistributor()
/*     */   {
/*  61 */     this.labels = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PieLabelRecord getPieLabelRecord(int index)
/*     */   {
/*  72 */     return (PieLabelRecord)this.labels.get(index);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addPieLabelRecord(PieLabelRecord record)
/*     */   {
/*  81 */     if (record == null) {
/*  82 */       throw new IllegalArgumentException("Null 'record' argument.");
/*     */     }
/*  84 */     this.labels.add(record);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getItemCount()
/*     */   {
/*  93 */     return this.labels.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 100 */     this.labels.clear();
/*     */   }
/*     */   
/*     */   public abstract void distributeLabels(double paramDouble1, double paramDouble2);
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/AbstractPieLabelDistributor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */