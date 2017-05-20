/*     */ package org.deckfour.uitopia.ui.test.resources;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.deckfour.uitopia.api.event.UpdateListener;
/*     */ import org.deckfour.uitopia.api.hub.ViewManager;
/*     */ import org.deckfour.uitopia.api.model.Resource;
/*     */ import org.deckfour.uitopia.api.model.ResourceType;
/*     */ import org.deckfour.uitopia.api.model.View;
/*     */ import org.deckfour.uitopia.api.model.ViewType;
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
/*     */ public class TestViewManager
/*     */   implements ViewManager
/*     */ {
/*     */   private List<ViewType> types;
/*     */   private List<View> views;
/*     */   private List<UpdateListener> listeners;
/*     */   
/*     */   public TestViewManager(int numTypes, List<ResourceType> resTypes)
/*     */   {
/*  57 */     this.types = new ArrayList();
/*  58 */     this.views = new ArrayList();
/*  59 */     this.listeners = new ArrayList();
/*  60 */     for (ResourceType type : resTypes) {
/*  61 */       for (int i = 0; i < numTypes; i++) {
/*  62 */         ViewType viewType = new TestViewType("View type " + i + " for " + type.getTypeName(), type, TestAuthor.DEFAULT_AUTHOR);
/*     */         
/*     */ 
/*     */ 
/*  66 */         this.types.add(viewType);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void notifyListeners() {
/*  72 */     for (UpdateListener listener : this.listeners) {
/*  73 */       listener.updated();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<ViewType> getViewTypes(Resource resource)
/*     */   {
/*  81 */     ArrayList<ViewType> result = new ArrayList();
/*  82 */     for (ViewType type : this.types) {
/*  83 */       if (type.getViewableType().equals(resource.getType())) {
/*  84 */         result.add(type);
/*     */       }
/*     */     }
/*  87 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<View> getViews()
/*     */   {
/*  94 */     return this.views;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void addListener(UpdateListener listener)
/*     */   {
/* 101 */     this.listeners.add(listener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<UpdateListener> getListeners()
/*     */   {
/* 108 */     return this.listeners;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void removeAllListeners()
/*     */   {
/* 115 */     this.listeners.clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void removeListener(UpdateListener listener)
/*     */   {
/* 122 */     this.listeners.remove(listener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void addView(View view)
/*     */   {
/* 129 */     this.views.add(view);
/* 130 */     notifyListeners();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void removeView(View view)
/*     */   {
/* 137 */     this.views.remove(view);
/* 138 */     notifyListeners();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/test/resources/TestViewManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */