/*     */ package org.jfree.ui.about;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.table.AbstractTableModel;
/*     */ import org.jfree.base.Library;
/*     */ import org.jfree.util.ResourceBundleWrapper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LibraryTableModel
/*     */   extends AbstractTableModel
/*     */ {
/*     */   private Library[] libraries;
/*     */   private String nameColumnLabel;
/*     */   private String versionColumnLabel;
/*     */   private String licenceColumnLabel;
/*     */   private String infoColumnLabel;
/*     */   
/*     */   public LibraryTableModel(List libraries)
/*     */   {
/*  89 */     this.libraries = ((Library[])libraries.toArray(new Library[libraries.size()]));
/*     */     
/*     */ 
/*  92 */     String baseName = "org.jfree.ui.about.resources.AboutResources";
/*  93 */     ResourceBundle resources = ResourceBundleWrapper.getBundle("org.jfree.ui.about.resources.AboutResources");
/*     */     
/*     */ 
/*  96 */     this.nameColumnLabel = resources.getString("libraries-table.column.name");
/*     */     
/*  98 */     this.versionColumnLabel = resources.getString("libraries-table.column.version");
/*     */     
/* 100 */     this.licenceColumnLabel = resources.getString("libraries-table.column.licence");
/*     */     
/* 102 */     this.infoColumnLabel = resources.getString("libraries-table.column.info");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRowCount()
/*     */   {
/* 113 */     return this.libraries.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getColumnCount()
/*     */   {
/* 123 */     return 4;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getColumnName(int column)
/*     */   {
/* 135 */     String result = null;
/*     */     
/* 137 */     switch (column) {
/*     */     case 0: 
/* 139 */       result = this.nameColumnLabel;
/* 140 */       break;
/*     */     case 1: 
/* 142 */       result = this.versionColumnLabel;
/* 143 */       break;
/*     */     case 2: 
/* 145 */       result = this.licenceColumnLabel;
/* 146 */       break;
/*     */     case 3: 
/* 148 */       result = this.infoColumnLabel;
/*     */     }
/*     */     
/*     */     
/*     */ 
/* 153 */     return result;
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
/*     */   public Object getValueAt(int row, int column)
/*     */   {
/* 167 */     Object result = null;
/* 168 */     Library library = this.libraries[row];
/*     */     
/* 170 */     if (column == 0) {
/* 171 */       result = library.getName();
/*     */     }
/* 173 */     else if (column == 1) {
/* 174 */       result = library.getVersion();
/*     */     }
/* 176 */     else if (column == 2) {
/* 177 */       result = library.getLicenceName();
/*     */     }
/* 179 */     else if (column == 3) {
/* 180 */       result = library.getInfo();
/*     */     }
/* 182 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Library[] getLibraries()
/*     */   {
/* 192 */     return (Library[])this.libraries.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/ui/about/LibraryTableModel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */