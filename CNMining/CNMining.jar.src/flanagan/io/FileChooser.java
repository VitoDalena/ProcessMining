/*     */ package flanagan.io;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileReader;
/*     */ import java.io.PrintStream;
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
/*     */ public class FileChooser
/*     */   extends FileInput
/*     */ {
/*     */   private File file;
/*  46 */   private String path = null;
/*     */   
/*     */ 
/*  49 */   private String extn = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public FileChooser() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public FileChooser(String path)
/*     */   {
/*  61 */     this.path = path;
/*     */   }
/*     */   
/*     */ 
/*     */   public String selectFile()
/*     */   {
/*  67 */     return selectFile("Select File");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String selectFile(String prompt)
/*     */   {
/*  74 */     JFileChooser chooser = new JFileChooser(this.path);
/*     */     
/*  76 */     if (this.extn != null)
/*     */     {
/*  78 */       FileTypeFilter f = new FileTypeFilter();
/*  79 */       f.addExtension(this.extn);
/*  80 */       f.setDescription(this.extn + " files");
/*  81 */       chooser.setFileFilter(f);
/*     */     }
/*     */     else
/*     */     {
/*  85 */       chooser.setAcceptAllFileFilterUsed(true);
/*     */     }
/*     */     
/*  88 */     chooser.setDialogTitle(prompt);
/*  89 */     chooser.showOpenDialog(null);
/*  90 */     this.file = chooser.getSelectedFile();
/*  91 */     if (this.file == null) {
/*  92 */       this.fileName = null;
/*  93 */       this.stemName = null;
/*  94 */       this.pathName = null;
/*  95 */       this.dirPath = null;
/*  96 */       this.fileFound = false;
/*     */     }
/*     */     else {
/*  99 */       this.pathName = this.file.toString();
/* 100 */       this.fileName = this.file.getName();
/* 101 */       this.dirPath = this.file.getParentFile().toString();
/* 102 */       int posDot = this.fileName.indexOf('.');
/* 103 */       if (posDot == -1) {
/* 104 */         this.stemName = this.fileName;
/*     */       }
/*     */       else {
/* 107 */         this.stemName = this.fileName.substring(0, posDot);
/*     */       }
/*     */       try
/*     */       {
/* 111 */         this.input = new BufferedReader(new FileReader(this.pathName));
/*     */       } catch (FileNotFoundException e) {
/* 113 */         System.out.println(e);
/* 114 */         this.fileFound = false;
/*     */       }
/*     */     }
/*     */     
/* 118 */     return this.fileName;
/*     */   }
/*     */   
/*     */   public void setPath(String path)
/*     */   {
/* 123 */     this.path = path;
/*     */   }
/*     */   
/*     */   public String getPath()
/*     */   {
/* 128 */     return this.path;
/*     */   }
/*     */   
/*     */   public void setExtension(String extn)
/*     */   {
/* 133 */     this.extn = extn;
/*     */   }
/*     */   
/*     */   public void setAllExtensions()
/*     */   {
/* 138 */     this.extn = null;
/*     */   }
/*     */   
/*     */   public String getExtension()
/*     */   {
/* 143 */     return this.extn;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static final synchronized void endProgram()
/*     */   {
/* 150 */     int ans = JOptionPane.showConfirmDialog(null, "Do you wish to end the program", "End Program", 0, 3);
/* 151 */     if (ans == 0) {
/* 152 */       System.exit(0);
/*     */     }
/*     */     else {
/* 155 */       JOptionPane.showMessageDialog(null, "Now you must press the appropriate escape key/s, e.g. Ctrl C, to exit this program");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/io/FileChooser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */