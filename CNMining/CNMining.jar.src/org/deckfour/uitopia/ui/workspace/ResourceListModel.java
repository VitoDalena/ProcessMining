/*     */ package org.deckfour.uitopia.ui.workspace;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.swing.AbstractListModel;
/*     */ import javax.swing.ListModel;
/*     */ import org.deckfour.uitopia.api.model.Resource;
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
/*     */ public class ResourceListModel
/*     */   extends AbstractListModel
/*     */   implements ListModel
/*     */ {
/*     */   private static final long serialVersionUID = -6538819257326757806L;
/*  53 */   private static final Comparator<Resource> COMP_ALPHABETICAL = new Comparator() {
/*     */     public int compare(Resource r1, Resource r2) {
/*  55 */       int c = r1.getName().compareTo(r2.getName());
/*  56 */       if (c != 0) {
/*  57 */         return c;
/*     */       }
/*  59 */       return ResourceListModel.COMP_CREATION_TIME.compare(r1, r2);
/*     */     }
/*     */   };
/*     */   
/*  63 */   private static final Comparator<Resource> COMP_CREATION_TIME = new Comparator() {
/*     */     public int compare(Resource r1, Resource r2) {
/*  65 */       return r2.getCreationTime().compareTo(r1.getCreationTime());
/*     */     }
/*     */   };
/*     */   
/*  69 */   private static final Comparator<Resource> COMP_ACCESS_TIME = new Comparator() {
/*     */     public int compare(Resource r1, Resource r2) {
/*  71 */       int c = r2.getLastAccessTime().compareTo(r1.getLastAccessTime());
/*  72 */       if (c != 0) {
/*  73 */         return c;
/*     */       }
/*  75 */       return ResourceListModel.COMP_ALPHABETICAL.compare(r1, r2);
/*     */     }
/*     */   };
/*     */   
/*     */   private List<? extends Resource> fullList;
/*     */   private List<? extends Resource> filteredList;
/*     */   private boolean filterFavorites;
/*     */   private Comparator<Resource> comparator;
/*     */   
/*     */   public ResourceListModel(List<? extends Resource> resources)
/*     */   {
/*  86 */     this.fullList = resources;
/*  87 */     this.filteredList = new ArrayList(resources);
/*  88 */     this.filterFavorites = false;
/*  89 */     this.comparator = COMP_ALPHABETICAL;
/*     */   }
/*     */   
/*     */   public void sortByAccessTime() {
/*  93 */     this.comparator = COMP_ACCESS_TIME;
/*  94 */     updateList();
/*     */   }
/*     */   
/*     */   public void sortByCreationTime() {
/*  98 */     this.comparator = COMP_CREATION_TIME;
/*  99 */     updateList();
/*     */   }
/*     */   
/*     */   public void sortByAlphabetical() {
/* 103 */     this.comparator = COMP_ALPHABETICAL;
/* 104 */     updateList();
/*     */   }
/*     */   
/*     */   public void setFilterFavorites(boolean isFiltered) {
/* 108 */     this.filterFavorites = isFiltered;
/* 109 */     updateList();
/*     */   }
/*     */   
/*     */   private void updateList() {
/* 113 */     this.filteredList = new ArrayList(this.fullList);
/* 114 */     if (this.filterFavorites) {
/* 115 */       ArrayList<Resource> filtered = new ArrayList();
/* 116 */       for (Resource r : this.filteredList) {
/* 117 */         if (r.isFavorite()) {
/* 118 */           filtered.add(r);
/*     */         }
/*     */       }
/* 121 */       this.filteredList = filtered;
/*     */     }
/* 123 */     Collections.sort(this.filteredList, this.comparator);
/* 124 */     fireContentsChanged(this, 0, getSize() - 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object getElementAt(int index)
/*     */   {
/* 133 */     return this.filteredList.get(index);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSize()
/*     */   {
/* 142 */     return this.filteredList.size();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/workspace/ResourceListModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */