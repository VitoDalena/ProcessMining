/*     */ package flanagan.io;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FileInputAsChar
/*     */ {
/*  40 */   protected String fileName = "";
/*  41 */   protected String stemName = "";
/*  42 */   protected String pathName = "";
/*  43 */   protected String dirPath = "";
/*  44 */   protected BufferedReader input = null;
/*  45 */   protected boolean testFullLine = false;
/*  46 */   protected boolean testFullLineT = false;
/*  47 */   protected boolean eof = false;
/*  48 */   protected boolean fileFound = true;
/*     */   
/*     */   public FileInputAsChar(String pathName)
/*     */   {
/*  52 */     this.pathName = pathName;
/*  53 */     int posSlash = pathName.indexOf("//");
/*  54 */     int posBackSlash = pathName.indexOf("\\");
/*  55 */     if ((posSlash != -1) || (posBackSlash != -1)) {
/*  56 */       File file = new File(this.pathName);
/*  57 */       this.fileName = file.getName();
/*  58 */       this.dirPath = file.getParentFile().toString();
/*     */     }
/*  60 */     int posDot = this.fileName.indexOf('.');
/*  61 */     if (posDot == -1) {
/*  62 */       this.stemName = this.fileName;
/*     */     }
/*     */     else {
/*  65 */       this.stemName = this.fileName.substring(0, posDot);
/*     */     }
/*     */     try
/*     */     {
/*  69 */       this.input = new BufferedReader(new FileReader(this.pathName));
/*     */     } catch (FileNotFoundException e) {
/*  71 */       System.out.println(e);
/*  72 */       this.fileFound = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getPathName()
/*     */   {
/*  80 */     return this.pathName;
/*     */   }
/*     */   
/*     */   public String getFileName()
/*     */   {
/*  85 */     return this.fileName;
/*     */   }
/*     */   
/*     */   public String getStemName()
/*     */   {
/*  90 */     return this.stemName;
/*     */   }
/*     */   
/*     */   public String getDirPath()
/*     */   {
/*  95 */     return this.dirPath;
/*     */   }
/*     */   
/*     */   public final synchronized char readchar()
/*     */   {
/* 100 */     int ich = -1;
/* 101 */     char ch = '\000';
/*     */     try {
/* 103 */       ich = this.input.read();
/*     */     } catch (IOException e) {
/* 105 */       System.out.println(e);
/*     */     }
/* 107 */     if (ich == -1) {
/* 108 */       System.out.println("FileInputAsChar.readchar:  attempt to read beyond end of file");
/* 109 */       this.eof = true;
/* 110 */       ch = '\000';
/*     */     }
/*     */     else {
/* 113 */       ch = (char)ich;
/*     */     }
/* 115 */     return ch;
/*     */   }
/*     */   
/*     */   public final synchronized Character readCharacter()
/*     */   {
/* 120 */     int ich = -1;
/* 121 */     char ch = '\000';
/* 122 */     Character wch = null;
/*     */     try
/*     */     {
/* 125 */       ich = this.input.read();
/*     */     } catch (IOException e) {
/* 127 */       System.out.println(e);
/*     */     }
/* 129 */     if (ich == -1) {
/* 130 */       System.out.println("FileInputAsChar.readChar:  attempt to read beyond end of file");
/* 131 */       this.eof = true;
/* 132 */       ch = '\000';
/* 133 */       wch = null;
/*     */     }
/*     */     else {
/* 136 */       ch = (char)ich;
/* 137 */       wch = new Character(ch);
/*     */     }
/* 139 */     return wch;
/*     */   }
/*     */   
/*     */   public final synchronized int readint()
/*     */   {
/* 144 */     int ch = -1;
/*     */     try {
/* 146 */       ch = this.input.read();
/*     */     } catch (IOException e) {
/* 148 */       System.out.println(e);
/*     */     }
/* 150 */     if (ch == -1) {
/* 151 */       System.out.println("FileInputAsChar.readint:  attempt to read beyond end of file");
/* 152 */       this.eof = true;
/*     */     }
/* 154 */     return ch;
/*     */   }
/*     */   
/*     */   public final synchronized void close()
/*     */   {
/* 159 */     if (this.fileFound) {
/*     */       try {
/* 161 */         this.input.close();
/*     */       } catch (IOException e) {
/* 163 */         System.out.println(e);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean eof()
/*     */   {
/* 170 */     return this.eof;
/*     */   }
/*     */   
/*     */   public boolean fileFound()
/*     */   {
/* 175 */     return this.fileFound;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/io/FileInputAsChar.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */