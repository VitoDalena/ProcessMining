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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FileNameSelector
/*     */ {
/*     */   private File file;
/*  47 */   private String path = null;
/*     */   
/*     */ 
/*  50 */   private String extn = null;
/*     */   
/*  52 */   private String fileName = null;
/*  53 */   private String stemName = null;
/*  54 */   private String pathName = null;
/*  55 */   private String dirPath = null;
/*  56 */   private boolean fileFound = false;
/*     */   
/*     */ 
/*     */ 
/*     */   public FileNameSelector() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public FileNameSelector(String path)
/*     */   {
/*  66 */     this.path = path;
/*     */   }
/*     */   
/*     */ 
/*     */   public String selectFile()
/*     */   {
/*  72 */     return selectFile("Select File");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String selectFile(String prompt)
/*     */   {
/*  79 */     JFileChooser chooser = new JFileChooser(this.path);
/*     */     
/*  81 */     if (this.extn != null)
/*     */     {
/*  83 */       FileTypeFilter f = new FileTypeFilter();
/*  84 */       f.addExtension(this.extn);
/*  85 */       f.setDescription(this.extn + " files");
/*  86 */       chooser.setFileFilter(f);
/*     */     }
/*     */     else
/*     */     {
/*  90 */       chooser.setAcceptAllFileFilterUsed(true);
/*     */     }
/*     */     
/*  93 */     chooser.setDialogTitle(prompt);
/*  94 */     chooser.showOpenDialog(null);
/*  95 */     this.file = chooser.getSelectedFile();
/*  96 */     if (this.file == null) {
/*  97 */       this.fileName = null;
/*  98 */       this.stemName = null;
/*  99 */       this.pathName = null;
/* 100 */       this.dirPath = null;
/* 101 */       this.fileFound = false;
/*     */     }
/*     */     else {
/* 104 */       this.pathName = this.file.toString();
/* 105 */       this.fileName = this.file.getName();
/* 106 */       this.dirPath = this.file.getParentFile().toString();
/* 107 */       int posDot = this.fileName.indexOf('.');
/* 108 */       if (posDot == -1) {
/* 109 */         this.stemName = this.fileName;
/*     */       }
/*     */       else {
/* 112 */         this.stemName = this.fileName.substring(0, posDot);
/*     */       }
/*     */     }
/*     */     
/* 116 */     return this.fileName;
/*     */   }
/*     */   
/*     */   public void setPath(String path)
/*     */   {
/* 121 */     this.path = path;
/*     */   }
/*     */   
/*     */   public String getPath()
/*     */   {
/* 126 */     return this.path;
/*     */   }
/*     */   
/*     */   public void setExtension(String extn)
/*     */   {
/* 131 */     this.extn = extn;
/*     */   }
/*     */   
/*     */   public void setAllExtensions()
/*     */   {
/* 136 */     this.extn = null;
/*     */   }
/*     */   
/*     */   public String getExtension()
/*     */   {
/* 141 */     return this.extn;
/*     */   }
/*     */   
/*     */   public String getPathName()
/*     */   {
/* 146 */     return this.pathName;
/*     */   }
/*     */   
/*     */   public String getFileName()
/*     */   {
/* 151 */     return this.fileName;
/*     */   }
/*     */   
/*     */   public String getStemName()
/*     */   {
/* 156 */     return this.stemName;
/*     */   }
/*     */   
/*     */   public String getDirPath()
/*     */   {
/* 161 */     return this.dirPath;
/*     */   }
/*     */   
/*     */   public boolean fileFound()
/*     */   {
/* 166 */     return this.fileFound;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static final synchronized void endProgram()
/*     */   {
/* 173 */     int ans = JOptionPane.showConfirmDialog(null, "Do you wish to end the program", "End Program", 0, 3);
/* 174 */     if (ans == 0) {
/* 175 */       System.exit(0);
/*     */     }
/*     */     else {
/* 178 */       JOptionPane.showMessageDialog(null, "Now you must press the appropriate escape key/s, e.g. Ctrl C, to exit this program");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/io/FileNameSelector.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */