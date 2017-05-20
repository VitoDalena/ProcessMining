/*    */ package edu.uci.ics.jung.visualization.picking;
/*    */ 
/*    */ import java.awt.event.ItemEvent;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.LinkedHashSet;
/*    */ import java.util.List;
/*    */ import java.util.Set;
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
/*    */ public class MultiPickedState<T>
/*    */   extends AbstractPickedState<T>
/*    */   implements PickedState<T>
/*    */ {
/* 34 */   protected Set<T> picked = new LinkedHashSet();
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean pick(T v, boolean state)
/*    */   {
/* 40 */     boolean prior_state = this.picked.contains(v);
/* 41 */     if (state) {
/* 42 */       this.picked.add(v);
/* 43 */       if (!prior_state) {
/* 44 */         fireItemStateChanged(new ItemEvent(this, 701, v, 1));
/*    */       }
/*    */     }
/*    */     else
/*    */     {
/* 49 */       this.picked.remove(v);
/* 50 */       if (prior_state == true) {
/* 51 */         fireItemStateChanged(new ItemEvent(this, 701, v, 2));
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 56 */     return prior_state;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void clear()
/*    */   {
/* 63 */     Collection<T> unpicks = new ArrayList(this.picked);
/* 64 */     for (T v : unpicks) {
/* 65 */       pick(v, false);
/*    */     }
/* 67 */     this.picked.clear();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public Set<T> getPicked()
/*    */   {
/* 75 */     return Collections.unmodifiableSet(this.picked);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean isPicked(T e)
/*    */   {
/* 82 */     return this.picked.contains(e);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public T[] getSelectedObjects()
/*    */   {
/* 90 */     List<T> list = new ArrayList(this.picked);
/* 91 */     return (Object[])list.toArray();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/picking/MultiPickedState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */