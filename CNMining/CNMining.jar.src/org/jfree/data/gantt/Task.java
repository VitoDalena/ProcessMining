/*     */ package org.jfree.data.gantt;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.jfree.data.time.SimpleTimePeriod;
/*     */ import org.jfree.data.time.TimePeriod;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Task
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1094303785346988894L;
/*     */   private String description;
/*     */   private TimePeriod duration;
/*     */   private Double percentComplete;
/*     */   private List subtasks;
/*     */   
/*     */   public Task(String description, TimePeriod duration)
/*     */   {
/*  84 */     if (description == null) {
/*  85 */       throw new IllegalArgumentException("Null 'description' argument.");
/*     */     }
/*  87 */     this.description = description;
/*  88 */     this.duration = duration;
/*  89 */     this.percentComplete = null;
/*  90 */     this.subtasks = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Task(String description, Date start, Date end)
/*     */   {
/* 102 */     this(description, new SimpleTimePeriod(start, end));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 111 */     return this.description;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 120 */     if (description == null) {
/* 121 */       throw new IllegalArgumentException("Null 'description' argument.");
/*     */     }
/* 123 */     this.description = description;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TimePeriod getDuration()
/*     */   {
/* 132 */     return this.duration;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDuration(TimePeriod duration)
/*     */   {
/* 141 */     this.duration = duration;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Double getPercentComplete()
/*     */   {
/* 150 */     return this.percentComplete;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPercentComplete(Double percent)
/*     */   {
/* 159 */     this.percentComplete = percent;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPercentComplete(double percent)
/*     */   {
/* 168 */     setPercentComplete(new Double(percent));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addSubtask(Task subtask)
/*     */   {
/* 177 */     if (subtask == null) {
/* 178 */       throw new IllegalArgumentException("Null 'subtask' argument.");
/*     */     }
/* 180 */     this.subtasks.add(subtask);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeSubtask(Task subtask)
/*     */   {
/* 189 */     this.subtasks.remove(subtask);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSubtaskCount()
/*     */   {
/* 198 */     return this.subtasks.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Task getSubtask(int index)
/*     */   {
/* 209 */     return (Task)this.subtasks.get(index);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 220 */     if (object == this) {
/* 221 */       return true;
/*     */     }
/* 223 */     if (!(object instanceof Task)) {
/* 224 */       return false;
/*     */     }
/* 226 */     Task that = (Task)object;
/* 227 */     if (!ObjectUtilities.equal(this.description, that.description)) {
/* 228 */       return false;
/*     */     }
/* 230 */     if (!ObjectUtilities.equal(this.duration, that.duration)) {
/* 231 */       return false;
/*     */     }
/* 233 */     if (!ObjectUtilities.equal(this.percentComplete, that.percentComplete))
/*     */     {
/* 235 */       return false;
/*     */     }
/* 237 */     if (!ObjectUtilities.equal(this.subtasks, that.subtasks)) {
/* 238 */       return false;
/*     */     }
/* 240 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 252 */     Task clone = (Task)super.clone();
/* 253 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/gantt/Task.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */