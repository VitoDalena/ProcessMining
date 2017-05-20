/*     */ package flanagan.io;
/*     */ 
/*     */ import flanagan.circuits.Phasor;
/*     */ import flanagan.complex.Complex;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FileInput
/*     */ {
/*  57 */   protected String fileName = " ";
/*  58 */   protected String stemName = " ";
/*  59 */   protected String pathName = " ";
/*  60 */   protected String dirPath = " ";
/*  61 */   protected String fullLine = " ";
/*  62 */   protected String fullLineT = " ";
/*  63 */   protected BufferedReader input = null;
/*  64 */   protected boolean testFullLine = false;
/*  65 */   protected boolean testFullLineT = false;
/*  66 */   protected boolean eof = false;
/*  67 */   protected boolean fileFound = true;
/*  68 */   protected boolean inputType = false;
/*     */   
/*  70 */   protected boolean charType = false;
/*  71 */   protected boolean space = true;
/*  72 */   protected boolean supressMessage = false;
/*     */   
/*     */ 
/*     */ 
/*     */   public FileInput() {}
/*     */   
/*     */ 
/*     */   public FileInput(String pathName)
/*     */   {
/*  81 */     this.pathName = pathName;
/*  82 */     int posSlash = pathName.indexOf("//");
/*  83 */     int posBackSlash = pathName.indexOf("\\");
/*  84 */     if ((posSlash != -1) || (posBackSlash != -1)) {
/*  85 */       File file = new File(this.pathName);
/*  86 */       this.fileName = file.getName();
/*  87 */       this.dirPath = file.getParentFile().toString();
/*     */     }
/*  89 */     int posDot = this.fileName.indexOf('.');
/*  90 */     if (posDot == -1) {
/*  91 */       this.stemName = this.fileName;
/*     */     }
/*     */     else {
/*  94 */       this.stemName = this.fileName.substring(0, posDot);
/*     */     }
/*     */     try
/*     */     {
/*  98 */       this.input = new BufferedReader(new FileReader(this.pathName));
/*     */     } catch (FileNotFoundException e) {
/* 100 */       System.out.println(e);
/* 101 */       this.fileFound = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getPathName()
/*     */   {
/* 109 */     return this.pathName;
/*     */   }
/*     */   
/*     */   public String getFileName()
/*     */   {
/* 114 */     return this.fileName;
/*     */   }
/*     */   
/*     */   public String getStemName()
/*     */   {
/* 119 */     return this.stemName;
/*     */   }
/*     */   
/*     */   public String getDirPath()
/*     */   {
/* 124 */     return this.dirPath;
/*     */   }
/*     */   
/*     */   public void removeSpaceAsDelimiter()
/*     */   {
/* 129 */     this.space = false;
/*     */   }
/*     */   
/*     */   public void restoreSpaceAsDelimiter()
/*     */   {
/* 134 */     this.space = true;
/*     */   }
/*     */   
/*     */   public final synchronized void copy(String copyFilename)
/*     */   {
/* 139 */     FileOutput fout = new FileOutput(copyFilename);
/* 140 */     int nLines = numberOfLines();
/* 141 */     for (int i = 0; i < nLines; i++) {
/* 142 */       String cline = readLine();
/* 143 */       fout.println(cline);
/*     */     }
/* 145 */     fout.close();
/*     */   }
/*     */   
/*     */   public final synchronized double readDouble()
/*     */   {
/* 150 */     this.inputType = true;
/* 151 */     String word = "";
/* 152 */     double dd = 0.0D;
/*     */     
/* 154 */     if (!this.testFullLineT) enterLine();
/* 155 */     word = nextWord();
/*     */     
/* 157 */     if (!this.eof) { dd = Double.parseDouble(word.trim());
/*     */     }
/* 159 */     return dd;
/*     */   }
/*     */   
/*     */   public final synchronized float readFloat()
/*     */   {
/* 164 */     this.inputType = true;
/* 165 */     String word = "";
/* 166 */     float ff = 0.0F;
/*     */     
/* 168 */     if (!this.testFullLineT) enterLine();
/* 169 */     word = nextWord();
/* 170 */     if (!this.eof) { ff = Float.parseFloat(word.trim());
/*     */     }
/* 172 */     return ff;
/*     */   }
/*     */   
/*     */ 
/*     */   public final synchronized BigDecimal readBigDecimal()
/*     */   {
/* 178 */     this.inputType = true;
/* 179 */     String word = "";
/* 180 */     BigDecimal big = null;
/*     */     
/* 182 */     if (!this.testFullLineT) enterLine();
/* 183 */     word = nextWord();
/* 184 */     if (!this.eof) { big = new BigDecimal(word.trim());
/*     */     }
/* 186 */     return big;
/*     */   }
/*     */   
/*     */   public final synchronized int readInt()
/*     */   {
/* 191 */     this.inputType = true;
/* 192 */     String word = "";
/* 193 */     int ii = 0;
/*     */     
/* 195 */     if (!this.testFullLineT) enterLine();
/* 196 */     word = nextWord();
/* 197 */     if (!this.eof) { ii = Integer.parseInt(word.trim());
/*     */     }
/* 199 */     return ii;
/*     */   }
/*     */   
/*     */   public final synchronized long readLong()
/*     */   {
/* 204 */     this.inputType = true;
/* 205 */     String word = "";
/* 206 */     long ll = 0L;
/*     */     
/* 208 */     if (!this.testFullLineT) enterLine();
/* 209 */     word = nextWord();
/* 210 */     if (!this.eof) { ll = Long.parseLong(word.trim());
/*     */     }
/* 212 */     return ll;
/*     */   }
/*     */   
/*     */   public final synchronized BigInteger readBigInteger()
/*     */   {
/* 217 */     this.inputType = true;
/* 218 */     String word = "";
/* 219 */     BigInteger big = null;
/*     */     
/* 221 */     if (!this.testFullLineT) enterLine();
/* 222 */     word = nextWord();
/* 223 */     if (!this.eof) { big = new BigInteger(word.trim());
/*     */     }
/* 225 */     return big;
/*     */   }
/*     */   
/*     */   public final synchronized short readShort()
/*     */   {
/* 230 */     this.inputType = true;
/* 231 */     String word = "";
/* 232 */     short ss = 0;
/*     */     
/* 234 */     if (!this.testFullLineT) enterLine();
/* 235 */     word = nextWord();
/* 236 */     if (!this.eof) { ss = Short.parseShort(word.trim());
/*     */     }
/* 238 */     return ss;
/*     */   }
/*     */   
/*     */   public final synchronized byte readByte()
/*     */   {
/* 243 */     this.inputType = true;
/* 244 */     String word = "";
/* 245 */     byte bb = 0;
/*     */     
/* 247 */     if (!this.testFullLineT) enterLine();
/* 248 */     word = nextWord();
/* 249 */     if (!this.eof) { bb = Byte.parseByte(word.trim());
/*     */     }
/* 251 */     return bb;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final synchronized Complex readComplex()
/*     */   {
/* 263 */     this.inputType = true;
/* 264 */     String word = "";
/* 265 */     Complex cc = null;
/*     */     
/* 267 */     if (!this.testFullLineT) enterLine();
/* 268 */     word = nextWord();
/*     */     
/* 270 */     if (!this.eof) cc = Complex.parseComplex(word.trim());
/* 271 */     return cc;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final synchronized Phasor readPhasor()
/*     */   {
/* 279 */     this.inputType = true;
/* 280 */     String word = "";
/* 281 */     Phasor ph = null;
/*     */     
/* 283 */     if (!this.testFullLineT) enterLine();
/* 284 */     word = nextWord();
/*     */     
/* 286 */     if (!this.eof) ph = Phasor.parsePhasor(word.trim());
/* 287 */     return ph;
/*     */   }
/*     */   
/*     */ 
/*     */   public final synchronized boolean readBoolean()
/*     */   {
/* 293 */     boolean retB = true;
/* 294 */     String retS = readWord();
/* 295 */     if ((retS.equals("false")) || (retS.equals("FALSE"))) {
/* 296 */       retB = false;
/*     */ 
/*     */     }
/* 299 */     else if ((retS.equals("true")) || (retS.equals("TRUE"))) {
/* 300 */       retB = true;
/*     */     }
/*     */     else {
/* 303 */       throw new IllegalArgumentException("attempted input neither true nor false");
/*     */     }
/*     */     
/* 306 */     return retB;
/*     */   }
/*     */   
/*     */   public final synchronized String readWord()
/*     */   {
/* 311 */     this.inputType = false;
/* 312 */     String word = "";
/*     */     
/* 314 */     if (!this.testFullLineT) enterLine();
/* 315 */     if (this.fullLine.equals("")) {
/* 316 */       word = "";
/*     */     }
/*     */     else {
/* 319 */       word = nextWord();
/*     */     }
/*     */     
/* 322 */     return word;
/*     */   }
/*     */   
/*     */   public final synchronized String readLine()
/*     */   {
/* 327 */     this.inputType = false;
/* 328 */     return readLineL();
/*     */   }
/*     */   
/*     */   protected final synchronized String readLineL()
/*     */   {
/* 333 */     String line = "";
/*     */     try {
/* 335 */       line = this.input.readLine();
/*     */     } catch (IOException e) {
/* 337 */       System.out.println(e);
/*     */     }
/* 339 */     if (line == null) {
/* 340 */       if (!this.supressMessage) System.out.println("Attempt to read beyond the end of the file");
/* 341 */       this.eof = true;
/* 342 */       line = "";
/*     */     }
/* 344 */     return line;
/*     */   }
/*     */   
/*     */   public final synchronized char readChar()
/*     */   {
/* 349 */     this.inputType = true;
/* 350 */     this.charType = true;
/* 351 */     String word = "";
/* 352 */     char ch = ' ';
/*     */     
/* 354 */     if (!this.testFullLine) enterLine();
/* 355 */     word = nextWord();
/* 356 */     if (word.length() != 1) throw new IllegalArgumentException("attempt to read more than one character into type char");
/* 357 */     if (!this.eof) ch = word.charAt(0);
/* 358 */     return ch;
/*     */   }
/*     */   
/*     */   public final synchronized void close()
/*     */   {
/* 363 */     if (this.fileFound) {
/*     */       try {
/* 365 */         this.input.close();
/*     */       } catch (IOException e) {
/* 367 */         System.out.println(e);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean eol()
/*     */   {
/* 374 */     boolean eol = false;
/* 375 */     if (!this.testFullLineT) eol = true;
/* 376 */     return eol;
/*     */   }
/*     */   
/*     */   public boolean eof()
/*     */   {
/* 381 */     return this.eof;
/*     */   }
/*     */   
/*     */   public boolean fileFound()
/*     */   {
/* 386 */     return this.fileFound;
/*     */   }
/*     */   
/*     */   protected final synchronized void enterLine()
/*     */   {
/* 391 */     int i = 0;
/*     */     
/* 393 */     this.fullLine = readLineL();
/* 394 */     this.fullLineT = this.fullLine;
/* 395 */     if (!this.fullLine.equals("")) {
/* 396 */       i = this.fullLineT.length() - 1;
/* 397 */       while ((this.fullLineT.charAt(i) == ' ') && (i >= 0)) {
/* 398 */         this.fullLineT = this.fullLineT.substring(0, i);
/* 399 */         i--;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected final synchronized String nextWord()
/*     */   {
/* 406 */     this.testFullLine = true;
/* 407 */     this.testFullLineT = true;
/* 408 */     String word = "";
/* 409 */     int posspa = -1;int postab = -1;int possp = -1;int poscom = -1;int poscol = -1;int possem = -1;
/* 410 */     boolean test = true;
/* 411 */     int len = this.fullLine.length();
/*     */     
/*     */ 
/* 414 */     boolean test0 = true;
/* 415 */     boolean test1 = false;
/* 416 */     int pend = this.fullLine.length();
/* 417 */     while (test0) {
/* 418 */       pend--;
/* 419 */       if (this.fullLine.charAt(pend) == ' ') test1 = true;
/* 420 */       if (this.fullLine.charAt(pend) == '\t') test1 = true;
/* 421 */       if (this.inputType) {
/* 422 */         if (this.fullLine.charAt(pend) == ',') test1 = true;
/* 423 */         if (this.fullLine.charAt(pend) == ':') test1 = true;
/* 424 */         if (this.fullLine.charAt(pend) == ';') test1 = true;
/*     */       }
/* 426 */       if (test1) {
/* 427 */         this.fullLine = this.fullLine.substring(0, pend);
/*     */       }
/*     */       else {
/* 430 */         test0 = false;
/*     */       }
/* 432 */       test1 = false;
/*     */     }
/*     */     
/*     */ 
/* 436 */     test0 = true;
/* 437 */     test1 = false;
/* 438 */     while (test0) {
/* 439 */       if (this.fullLine.charAt(0) == ' ') test1 = true;
/* 440 */       if (this.fullLine.charAt(0) == '\t') test1 = true;
/* 441 */       if (this.inputType) {
/* 442 */         if (this.fullLine.charAt(0) == ',') test1 = true;
/* 443 */         if (this.fullLine.charAt(0) == ':') test1 = true;
/* 444 */         if (this.fullLine.charAt(0) == ';') { test1 = true;
/*     */         }
/*     */       }
/* 447 */       if (test1) {
/* 448 */         this.fullLine = this.fullLine.substring(1);
/*     */       }
/*     */       else {
/* 451 */         test0 = false;
/*     */       }
/* 453 */       test1 = false;
/*     */     }
/*     */     
/*     */ 
/* 457 */     int lenPlus = this.fullLine.length() + 10;
/* 458 */     if (this.space) posspa = this.fullLine.indexOf(' ');
/* 459 */     postab = this.fullLine.indexOf('\t');
/* 460 */     int firstMin = lenPlus;
/* 461 */     int secondMin = lenPlus;
/* 462 */     int thirdMin = lenPlus;
/* 463 */     if (this.space) {
/* 464 */       if ((posspa == -1) && (postab == -1)) {
/* 465 */         firstMin = lenPlus;
/*     */ 
/*     */       }
/* 468 */       else if (posspa == -1) {
/* 469 */         firstMin = postab;
/*     */ 
/*     */       }
/* 472 */       else if (postab == -1) {
/* 473 */         firstMin = posspa;
/*     */       }
/*     */       else {
/* 476 */         firstMin = Math.min(posspa, postab);
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 482 */     else if (postab != -1) {
/* 483 */       firstMin = postab;
/*     */     }
/*     */     
/* 486 */     if (this.inputType) {
/* 487 */       poscom = this.fullLine.indexOf(',');
/* 488 */       poscol = this.fullLine.indexOf(':');
/* 489 */       possem = this.fullLine.indexOf(';');
/* 490 */       if ((poscom == -1) && (poscol == -1)) {
/* 491 */         secondMin = lenPlus;
/*     */ 
/*     */       }
/* 494 */       else if (poscom == -1) {
/* 495 */         secondMin = poscol;
/*     */ 
/*     */       }
/* 498 */       else if (poscol == -1) {
/* 499 */         secondMin = poscom;
/*     */       }
/*     */       else {
/* 502 */         secondMin = Math.min(poscom, poscol);
/*     */       }
/*     */       
/*     */ 
/* 506 */       if (possem == -1) {
/* 507 */         thirdMin = lenPlus;
/*     */       }
/*     */       else {
/* 510 */         thirdMin = possem;
/*     */       }
/* 512 */       secondMin = Math.min(secondMin, thirdMin);
/* 513 */       firstMin = Math.min(firstMin, secondMin);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 519 */     if (firstMin == lenPlus) {
/* 520 */       word = this.fullLine;
/* 521 */       this.fullLine = "";
/* 522 */       this.testFullLine = false;
/*     */     }
/*     */     else {
/* 525 */       word = this.fullLine.substring(0, firstMin);
/*     */       
/* 527 */       if (firstMin + 1 > this.fullLine.length()) {
/* 528 */         this.fullLine = "";
/* 529 */         this.testFullLine = false;
/*     */       }
/*     */       else {
/* 532 */         this.fullLine = this.fullLine.substring(firstMin + 1);
/* 533 */         if (this.fullLine.length() == 0) this.testFullLine = false;
/*     */       }
/*     */     }
/* 536 */     if (this.testFullLineT) {
/* 537 */       if (!this.testFullLine) {
/* 538 */         this.testFullLineT = false;
/* 539 */         this.fullLineT = "";
/*     */ 
/*     */       }
/* 542 */       else if (firstMin + 1 > this.fullLineT.length()) {
/* 543 */         this.fullLineT = "";
/* 544 */         this.testFullLineT = false;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 550 */     return word;
/*     */   }
/*     */   
/*     */   protected final synchronized char nextCharInString()
/*     */   {
/* 555 */     this.testFullLine = true;
/* 556 */     char ch = ' ';
/* 557 */     boolean test = true;
/*     */     
/* 559 */     ch = this.fullLine.charAt(0);
/* 560 */     this.fullLine = this.fullLine.substring(1);
/* 561 */     if (this.fullLine.length() == 0) this.testFullLine = false;
/* 562 */     if (this.testFullLineT) {
/* 563 */       this.fullLineT = this.fullLineT.substring(1);
/* 564 */       if (this.fullLineT.length() == 0) { this.testFullLineT = false;
/*     */       }
/*     */     }
/* 567 */     return ch;
/*     */   }
/*     */   
/*     */   public void setSupressMessageToTrue()
/*     */   {
/* 572 */     this.supressMessage = true;
/*     */   }
/*     */   
/*     */   public void setSupressMessageToFalse()
/*     */   {
/* 577 */     this.supressMessage = false;
/*     */   }
/*     */   
/*     */   public final synchronized int numberOfLines()
/*     */   {
/* 582 */     FileInput fin = new FileInput(this.pathName);
/* 583 */     fin.setSupressMessageToTrue();
/* 584 */     boolean test = true;
/* 585 */     int nLines = 0;
/* 586 */     while (test) {
/* 587 */       String inputLine = fin.readLineL();
/* 588 */       if (fin.eof) {
/* 589 */         test = false;
/*     */ 
/*     */       }
/* 592 */       else if (nLines == Integer.MAX_VALUE) {
/* 593 */         System.out.println("Class FileInput; method numberOfLines; The number of lines is greater than the maximum integer value, 2147483647");
/* 594 */         System.out.println("-1 returned");
/* 595 */         nLines = -1;
/*     */       }
/*     */       else {
/* 598 */         nLines++;
/*     */       }
/*     */     }
/*     */     
/* 602 */     fin.close();
/* 603 */     return nLines;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/io/FileInput.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */