/*     */ package org.deckfour.xes.info.impl;
/*     */ 
/*     */ import java.util.Date;
/*     */ import org.deckfour.xes.extension.std.XTimeExtension;
/*     */ import org.deckfour.xes.info.XTimeBounds;
/*     */ import org.deckfour.xes.model.XEvent;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XTimeBoundsImpl
/*     */   implements XTimeBounds
/*     */ {
/*     */   protected Date first;
/*     */   protected Date last;
/*     */   
/*     */   public XTimeBoundsImpl()
/*     */   {
/*  70 */     this.first = null;
/*  71 */     this.last = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Date getStartDate()
/*     */   {
/*  78 */     return this.first;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Date getEndDate()
/*     */   {
/*  85 */     return this.last;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isWithin(Date date)
/*     */   {
/*  92 */     if (this.first == null)
/*  93 */       return false;
/*  94 */     if (date.equals(this.first))
/*  95 */       return true;
/*  96 */     if (date.equals(this.last))
/*  97 */       return true;
/*  98 */     if ((date.after(this.first)) && (date.before(this.last))) {
/*  99 */       return true;
/*     */     }
/* 101 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void register(XEvent event)
/*     */   {
/* 113 */     Date date = XTimeExtension.instance().extractTimestamp(event);
/* 114 */     if (date != null) {
/* 115 */       register(date);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void register(Date date)
/*     */   {
/* 126 */     if (date != null) {
/* 127 */       if (this.first == null)
/*     */       {
/* 129 */         this.first = date;
/* 130 */         this.last = date;
/* 131 */       } else if (date.before(this.first)) {
/* 132 */         this.first = date;
/* 133 */       } else if (date.after(this.last)) {
/* 134 */         this.last = date;
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
/*     */   public void register(XTimeBounds boundary)
/*     */   {
/* 147 */     register(boundary.getStartDate());
/* 148 */     register(boundary.getEndDate());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 155 */     return this.first.toString() + " -- " + this.last.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/info/impl/XTimeBoundsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */