/*     */ package org.apache.commons.compress.changes;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*     */ import org.apache.commons.compress.archivers.ArchiveInputStream;
/*     */ import org.apache.commons.compress.archivers.ArchiveOutputStream;
/*     */ import org.apache.commons.compress.utils.IOUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChangeSetPerformer
/*     */ {
/*     */   private final Set changes;
/*     */   
/*     */   public ChangeSetPerformer(ChangeSet changeSet)
/*     */   {
/*  49 */     this.changes = changeSet.getChanges();
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public ChangeSetResults perform(ArchiveInputStream in, ArchiveOutputStream out)
/*     */     throws IOException
/*     */   {
/*  69 */     ChangeSetResults results = new ChangeSetResults();
/*     */     
/*  71 */     Set workingSet = new LinkedHashSet(this.changes);
/*     */     
/*  73 */     for (Iterator it = workingSet.iterator(); it.hasNext();) {
/*  74 */       Change change = (Change)it.next();
/*     */       
/*  76 */       if ((change.type() == 2) && (change.isReplaceMode())) {
/*  77 */         copyStream(change.getInput(), out, change.getEntry());
/*  78 */         it.remove();
/*  79 */         results.addedFromChangeSet(change.getEntry().getName());
/*     */       }
/*     */     }
/*     */     
/*  83 */     ArchiveEntry entry = null;
/*  84 */     while ((entry = in.getNextEntry()) != null) {
/*  85 */       boolean copy = true;
/*     */       
/*  87 */       for (Iterator it = workingSet.iterator(); it.hasNext();) {
/*  88 */         Change change = (Change)it.next();
/*     */         
/*  90 */         int type = change.type();
/*  91 */         String name = entry.getName();
/*  92 */         if ((type == 1) && (name != null)) {
/*  93 */           if (name.equals(change.targetFile())) {
/*  94 */             copy = false;
/*  95 */             it.remove();
/*  96 */             results.deleted(name);
/*  97 */             break;
/*     */           }
/*  99 */         } else if ((type == 4) && (name != null) && 
/* 100 */           (name.startsWith(change.targetFile() + "/"))) {
/* 101 */           copy = false;
/* 102 */           results.deleted(name);
/* 103 */           break;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 108 */       if ((copy) && 
/* 109 */         (!isDeletedLater(workingSet, entry)) && (!results.hasBeenAdded(entry.getName()))) {
/* 110 */         copyStream(in, out, entry);
/* 111 */         results.addedFromStream(entry.getName());
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 117 */     for (Iterator it = workingSet.iterator(); it.hasNext();) {
/* 118 */       Change change = (Change)it.next();
/*     */       
/* 120 */       if ((change.type() == 2) && (!change.isReplaceMode()) && (!results.hasBeenAdded(change.getEntry().getName())))
/*     */       {
/*     */ 
/* 123 */         copyStream(change.getInput(), out, change.getEntry());
/* 124 */         it.remove();
/* 125 */         results.addedFromChangeSet(change.getEntry().getName());
/*     */       }
/*     */     }
/* 128 */     out.finish();
/* 129 */     return results;
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
/*     */   private boolean isDeletedLater(Set workingSet, ArchiveEntry entry)
/*     */   {
/* 142 */     String source = entry.getName();
/*     */     Iterator it;
/* 144 */     if (!workingSet.isEmpty()) {
/* 145 */       for (it = workingSet.iterator(); it.hasNext();) {
/* 146 */         Change change = (Change)it.next();
/* 147 */         int type = change.type();
/* 148 */         String target = change.targetFile();
/* 149 */         if ((type == 1) && (source.equals(target))) {
/* 150 */           return true;
/*     */         }
/*     */         
/* 153 */         if ((type == 4) && (source.startsWith(target + "/"))) {
/* 154 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 158 */     return false;
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
/*     */   private void copyStream(InputStream in, ArchiveOutputStream out, ArchiveEntry entry)
/*     */     throws IOException
/*     */   {
/* 175 */     out.putArchiveEntry(entry);
/* 176 */     IOUtils.copy(in, out);
/* 177 */     out.closeArchiveEntry();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/changes/ChangeSetPerformer.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */