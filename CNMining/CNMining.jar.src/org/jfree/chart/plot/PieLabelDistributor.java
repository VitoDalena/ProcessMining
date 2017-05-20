/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.util.Collections;
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
/*     */ public class PieLabelDistributor
/*     */   extends AbstractPieLabelDistributor
/*     */ {
/*  55 */   private double minGap = 4.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PieLabelDistributor(int labelCount) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void distributeLabels(double minY, double height)
/*     */   {
/*  73 */     sort();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  78 */     if (isOverlap()) {
/*  79 */       adjustDownwards(minY, height);
/*     */     }
/*     */     
/*  82 */     if (isOverlap()) {
/*  83 */       adjustUpwards(minY, height);
/*     */     }
/*     */     
/*  86 */     if (isOverlap()) {
/*  87 */       spreadEvenly(minY, height);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isOverlap()
/*     */   {
/*  98 */     double y = 0.0D;
/*  99 */     for (int i = 0; i < this.labels.size(); i++) {
/* 100 */       PieLabelRecord plr = getPieLabelRecord(i);
/* 101 */       if (y > plr.getLowerY()) {
/* 102 */         return true;
/*     */       }
/* 104 */       y = plr.getUpperY();
/*     */     }
/* 106 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void adjustInwards()
/*     */   {
/* 114 */     int lower = 0;
/* 115 */     int upper = this.labels.size() - 1;
/* 116 */     while (upper > lower) {
/* 117 */       if (lower < upper - 1) {
/* 118 */         PieLabelRecord r0 = getPieLabelRecord(lower);
/* 119 */         PieLabelRecord r1 = getPieLabelRecord(lower + 1);
/* 120 */         if (r1.getLowerY() < r0.getUpperY()) {
/* 121 */           double adjust = r0.getUpperY() - r1.getLowerY() + this.minGap;
/*     */           
/* 123 */           r1.setAllocatedY(r1.getAllocatedY() + adjust);
/*     */         }
/*     */       }
/* 126 */       PieLabelRecord r2 = getPieLabelRecord(upper - 1);
/* 127 */       PieLabelRecord r3 = getPieLabelRecord(upper);
/* 128 */       if (r2.getUpperY() > r3.getLowerY()) {
/* 129 */         double adjust = r2.getUpperY() - r3.getLowerY() + this.minGap;
/* 130 */         r3.setAllocatedY(r3.getAllocatedY() + adjust);
/*     */       }
/* 132 */       lower++;
/* 133 */       upper--;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void adjustDownwards(double minY, double height)
/*     */   {
/* 145 */     for (int i = 0; i < this.labels.size() - 1; i++) {
/* 146 */       PieLabelRecord record0 = getPieLabelRecord(i);
/* 147 */       PieLabelRecord record1 = getPieLabelRecord(i + 1);
/* 148 */       if (record1.getLowerY() < record0.getUpperY()) {
/* 149 */         record1.setAllocatedY(Math.min(minY + height - record1.getLabelHeight() / 2.0D, record0.getUpperY() + this.minGap + record1.getLabelHeight() / 2.0D));
/*     */       }
/*     */     }
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
/*     */   protected void adjustUpwards(double minY, double height)
/*     */   {
/* 165 */     for (int i = this.labels.size() - 1; i > 0; i--) {
/* 166 */       PieLabelRecord record0 = getPieLabelRecord(i);
/* 167 */       PieLabelRecord record1 = getPieLabelRecord(i - 1);
/* 168 */       if (record1.getUpperY() > record0.getLowerY()) {
/* 169 */         record1.setAllocatedY(Math.max(minY + record1.getLabelHeight() / 2.0D, record0.getLowerY() - this.minGap - record1.getLabelHeight() / 2.0D));
/*     */       }
/*     */     }
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
/*     */   protected void spreadEvenly(double minY, double height)
/*     */   {
/* 184 */     double y = minY;
/* 185 */     double sumOfLabelHeights = 0.0D;
/* 186 */     for (int i = 0; i < this.labels.size(); i++) {
/* 187 */       sumOfLabelHeights += getPieLabelRecord(i).getLabelHeight();
/*     */     }
/* 189 */     double gap = height - sumOfLabelHeights;
/* 190 */     if (this.labels.size() > 1) {
/* 191 */       gap /= (this.labels.size() - 1);
/*     */     }
/* 193 */     for (int i = 0; i < this.labels.size(); i++) {
/* 194 */       PieLabelRecord record = getPieLabelRecord(i);
/* 195 */       y += record.getLabelHeight() / 2.0D;
/* 196 */       record.setAllocatedY(y);
/* 197 */       y = y + record.getLabelHeight() / 2.0D + gap;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void sort()
/*     */   {
/* 205 */     Collections.sort(this.labels);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 215 */     StringBuffer result = new StringBuffer();
/* 216 */     for (int i = 0; i < this.labels.size(); i++) {
/* 217 */       result.append(getPieLabelRecord(i).toString()).append("\n");
/*     */     }
/* 219 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/PieLabelDistributor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */