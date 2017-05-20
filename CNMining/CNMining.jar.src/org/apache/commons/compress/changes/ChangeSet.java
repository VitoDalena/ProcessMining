/*     */ package org.apache.commons.compress.changes;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ChangeSet
/*     */ {
/*  37 */   private final Set changes = new LinkedHashSet();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void delete(String filename)
/*     */   {
/*  46 */     addDeletion(new Change(filename, 1));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void deleteDir(String dirName)
/*     */   {
/*  56 */     addDeletion(new Change(dirName, 4));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(ArchiveEntry pEntry, InputStream pInput)
/*     */   {
/*  68 */     add(pEntry, pInput, true);
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
/*     */ 
/*     */ 
/*     */   public void add(ArchiveEntry pEntry, InputStream pInput, boolean replace)
/*     */   {
/*  84 */     addAddition(new Change(pEntry, pInput, replace));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void addAddition(Change pChange)
/*     */   {
/*  94 */     if ((2 != pChange.type()) || (pChange.getInput() == null)) {
/*     */       return;
/*     */     }
/*     */     
/*     */     Iterator it;
/*  99 */     if (!this.changes.isEmpty()) {
/* 100 */       for (it = this.changes.iterator(); it.hasNext();) {
/* 101 */         Change change = (Change)it.next();
/* 102 */         if ((change.type() == 2) && (change.getEntry() != null))
/*     */         {
/* 104 */           ArchiveEntry entry = change.getEntry();
/*     */           
/* 106 */           if (entry.equals(pChange.getEntry())) {
/* 107 */             if (pChange.isReplaceMode()) {
/* 108 */               it.remove();
/* 109 */               this.changes.add(pChange);
/* 110 */               return;
/*     */             }
/*     */             
/* 113 */             return;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 119 */     this.changes.add(pChange);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void addDeletion(Change pChange)
/*     */   {
/* 129 */     if (((1 != pChange.type()) && (4 != pChange.type())) || (pChange.targetFile() == null))
/*     */     {
/*     */ 
/* 132 */       return;
/*     */     }
/* 134 */     String source = pChange.targetFile();
/*     */     Iterator it;
/* 136 */     if (!this.changes.isEmpty()) {
/* 137 */       for (it = this.changes.iterator(); it.hasNext();) {
/* 138 */         Change change = (Change)it.next();
/* 139 */         if ((change.type() == 2) && (change.getEntry() != null))
/*     */         {
/* 141 */           String target = change.getEntry().getName();
/*     */           
/* 143 */           if ((1 == pChange.type()) && (source.equals(target))) {
/* 144 */             it.remove();
/* 145 */           } else if ((4 == pChange.type()) && (target.matches(source + "/.*")))
/*     */           {
/* 147 */             it.remove();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 152 */     this.changes.add(pChange);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   Set getChanges()
/*     */   {
/* 161 */     return new LinkedHashSet(this.changes);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/changes/ChangeSet.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */