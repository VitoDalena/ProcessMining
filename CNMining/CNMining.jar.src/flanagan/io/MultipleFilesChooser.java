/*     */ package flanagan.io;
/*     */ 
/*     */ import java.io.File;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JOptionPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MultipleFilesChooser
/*     */ {
/*  43 */   private String[] fileNames = null;
/*  44 */   private String[] pathNames = null;
/*  45 */   private String[] dirNames = null;
/*  46 */   private String[] stemNames = null;
/*     */   
/*  48 */   private FileInput[] fileObjects = null;
/*  49 */   private int nFiles = 0;
/*  50 */   private String path = null;
/*     */   
/*     */ 
/*  53 */   private String extn = null;
/*     */   
/*     */ 
/*     */ 
/*     */   public MultipleFilesChooser() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public MultipleFilesChooser(String path)
/*     */   {
/*  63 */     this.path = path;
/*     */   }
/*     */   
/*     */ 
/*     */   public FileInput[] selectFiles()
/*     */   {
/*  69 */     return selectFiles("Select File");
/*     */   }
/*     */   
/*     */ 
/*     */   public FileInput[] selectFiles(String prompt)
/*     */   {
/*  75 */     JFileChooser chooser = new JFileChooser(this.path);
/*  76 */     chooser.setMultiSelectionEnabled(true);
/*  77 */     if (this.extn != null)
/*     */     {
/*  79 */       FileTypeFilter f = new FileTypeFilter();
/*  80 */       f.addExtension(this.extn);
/*  81 */       f.setDescription(this.extn + " files");
/*  82 */       chooser.setFileFilter(f);
/*     */     }
/*     */     else
/*     */     {
/*  86 */       chooser.setAcceptAllFileFilterUsed(true);
/*     */     }
/*     */     
/*  89 */     chooser.setDialogTitle(prompt);
/*  90 */     chooser.showOpenDialog(null);
/*  91 */     File[] files = chooser.getSelectedFiles();
/*  92 */     this.nFiles = files.length;
/*  93 */     this.fileObjects = new FileInput[this.nFiles];
/*  94 */     this.fileNames = new String[this.nFiles];
/*  95 */     this.stemNames = new String[this.nFiles];
/*  96 */     this.pathNames = new String[this.nFiles];
/*  97 */     this.dirNames = new String[this.nFiles];
/*     */     
/*  99 */     for (int i = 0; i < this.nFiles; i++) {
/* 100 */       this.fileNames[i] = files[i].getName();
/* 101 */       this.pathNames[i] = files[i].toString();
/* 102 */       this.dirNames[i] = files[i].getParentFile().toString();
/* 103 */       this.fileObjects[i] = new FileInput(this.pathNames[i]);
/* 104 */       int posDot = this.fileNames[i].indexOf('.');
/* 105 */       if (posDot == -1) {
/* 106 */         this.stemNames[i] = this.fileNames[i];
/*     */       }
/*     */       else {
/* 109 */         this.stemNames[i] = this.fileNames[i].substring(0, posDot);
/*     */       }
/*     */     }
/*     */     
/* 113 */     return this.fileObjects;
/*     */   }
/*     */   
/*     */   public void setPath(String path)
/*     */   {
/* 118 */     this.path = path;
/*     */   }
/*     */   
/*     */   public String getPath()
/*     */   {
/* 123 */     return this.path;
/*     */   }
/*     */   
/*     */   public void setExtension(String extn)
/*     */   {
/* 128 */     this.extn = extn;
/*     */   }
/*     */   
/*     */   public void setAllExtensions()
/*     */   {
/* 133 */     this.extn = null;
/*     */   }
/*     */   
/*     */   public String getExtension()
/*     */   {
/* 138 */     return this.extn;
/*     */   }
/*     */   
/*     */   public int getNumberOfFiles()
/*     */   {
/* 143 */     return this.nFiles;
/*     */   }
/*     */   
/*     */   public String[] getFileNames()
/*     */   {
/* 148 */     return this.fileNames;
/*     */   }
/*     */   
/*     */   public String[] getStemNames()
/*     */   {
/* 153 */     return this.stemNames;
/*     */   }
/*     */   
/*     */   public String[] getPathNames()
/*     */   {
/* 158 */     return this.pathNames;
/*     */   }
/*     */   
/*     */   public String[] getDirPaths()
/*     */   {
/* 163 */     return this.dirNames;
/*     */   }
/*     */   
/*     */   public final synchronized void close()
/*     */   {
/* 168 */     for (int i = 0; i < this.nFiles; i++) {
/* 169 */       this.fileObjects[i].close();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static final synchronized void endProgram()
/*     */   {
/* 177 */     int ans = JOptionPane.showConfirmDialog(null, "Do you wish to end the program", "End Program", 0, 3);
/* 178 */     if (ans == 0) {
/* 179 */       System.exit(0);
/*     */     }
/*     */     else {
/* 182 */       JOptionPane.showMessageDialog(null, "Now you must press the appropriate escape key/s, e.g. Ctrl C, to exit this program");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/io/MultipleFilesChooser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */