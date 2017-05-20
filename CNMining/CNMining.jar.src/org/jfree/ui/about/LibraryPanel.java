/*     */ package org.jfree.ui.about;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import org.jfree.base.Library;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LibraryPanel
/*     */   extends JPanel
/*     */ {
/*     */   private JTable table;
/*     */   private LibraryTableModel model;
/*     */   
/*     */   public LibraryPanel(List libraries)
/*     */   {
/*  75 */     setLayout(new BorderLayout());
/*  76 */     this.model = new LibraryTableModel(libraries);
/*  77 */     this.table = new JTable(this.model);
/*  78 */     add(new JScrollPane(this.table));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LibraryPanel(ProjectInfo projectInfo)
/*     */   {
/*  88 */     this(getLibraries(projectInfo));
/*     */   }
/*     */   
/*     */   private static List getLibraries(ProjectInfo info) {
/*  92 */     if (info == null) {
/*  93 */       return new ArrayList();
/*     */     }
/*  95 */     ArrayList libs = new ArrayList();
/*  96 */     collectLibraries(info, libs);
/*  97 */     return libs;
/*     */   }
/*     */   
/*     */   private static void collectLibraries(ProjectInfo info, List list)
/*     */   {
/* 102 */     Library[] libs = info.getLibraries();
/* 103 */     for (int i = 0; i < libs.length; i++) {
/* 104 */       Library lib = libs[i];
/* 105 */       if (!list.contains(lib))
/*     */       {
/* 107 */         list.add(lib);
/* 108 */         if ((lib instanceof ProjectInfo)) {
/* 109 */           collectLibraries((ProjectInfo)lib, list);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 114 */     libs = info.getOptionalLibraries();
/* 115 */     for (int i = 0; i < libs.length; i++) {
/* 116 */       Library lib = libs[i];
/* 117 */       if (!list.contains(lib))
/*     */       {
/* 119 */         list.add(lib);
/* 120 */         if ((lib instanceof ProjectInfo)) {
/* 121 */           collectLibraries((ProjectInfo)lib, list);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LibraryTableModel getModel()
/*     */   {
/* 133 */     return this.model;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected JTable getTable()
/*     */   {
/* 142 */     return this.table;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/ui/about/LibraryPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */