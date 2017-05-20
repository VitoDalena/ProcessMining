/*     */ package org.apache.lucene.analysis;
/*     */ 
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class PorterStemmer
/*     */ {
/*     */   private char[] b;
/*     */   private int i;
/*     */   private int j;
/*     */   private int k;
/*     */   private int k0;
/*  62 */   private boolean dirty = false;
/*     */   private static final int INC = 50;
/*     */   private static final int EXTRA = 1;
/*     */   
/*     */   public PorterStemmer() {
/*  67 */     this.b = new char[50];
/*  68 */     this.i = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reset()
/*     */   {
/*  76 */     this.i = 0;this.dirty = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void add(char ch)
/*     */   {
/*  83 */     if (this.b.length <= this.i + 1) {
/*  84 */       char[] new_b = new char[this.b.length + 50];
/*  85 */       for (int c = 0; c < this.b.length; c++)
/*  86 */         new_b[c] = this.b[c];
/*  87 */       this.b = new_b;
/*     */     }
/*  89 */     this.b[(this.i++)] = ch;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/*  97 */     return new String(this.b, 0, this.i);
/*     */   }
/*     */   
/*     */   public int getResultLength()
/*     */   {
/* 102 */     return this.i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public char[] getResultBuffer()
/*     */   {
/* 109 */     return this.b;
/*     */   }
/*     */   
/*     */   private final boolean cons(int i)
/*     */   {
/* 114 */     switch (this.b[i]) {
/*     */     case 'a': case 'e': case 'i': case 'o': case 'u': 
/* 116 */       return false;
/*     */     case 'y': 
/* 118 */       return i == this.k0;
/*     */     }
/* 120 */     return true;
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
/*     */   private final int m()
/*     */   {
/* 136 */     int n = 0;
/* 137 */     int i = this.k0;
/*     */     for (;;) {
/* 139 */       if (i > this.j)
/* 140 */         return n;
/* 141 */       if (!cons(i))
/*     */         break;
/* 143 */       i++;
/*     */     }
/* 145 */     i++;
/*     */     for (;;)
/*     */     {
/* 148 */       if (i > this.j)
/* 149 */         return n;
/* 150 */       if (!cons(i))
/*     */       {
/* 152 */         i++;
/*     */       } else {
/* 154 */         i++;
/* 155 */         n++;
/*     */         for (;;) {
/* 157 */           if (i > this.j)
/* 158 */             return n;
/* 159 */           if (!cons(i))
/*     */             break;
/* 161 */           i++;
/*     */         }
/* 163 */         i++;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private final boolean vowelinstem()
/*     */   {
/* 171 */     for (int i = this.k0; i <= this.j; i++)
/* 172 */       if (!cons(i))
/* 173 */         return true;
/* 174 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   private final boolean doublec(int j)
/*     */   {
/* 180 */     if (j < this.k0 + 1)
/* 181 */       return false;
/* 182 */     if (this.b[j] != this.b[(j - 1)])
/* 183 */       return false;
/* 184 */     return cons(j);
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
/*     */   private final boolean cvc(int i)
/*     */   {
/* 197 */     if ((i < this.k0 + 2) || (!cons(i)) || (cons(i - 1)) || (!cons(i - 2))) {
/* 198 */       return false;
/*     */     }
/* 200 */     int ch = this.b[i];
/* 201 */     if ((ch == 119) || (ch == 120) || (ch == 121)) { return false;
/*     */     }
/* 203 */     return true;
/*     */   }
/*     */   
/*     */   private final boolean ends(String s) {
/* 207 */     int l = s.length();
/* 208 */     int o = this.k - l + 1;
/* 209 */     if (o < this.k0)
/* 210 */       return false;
/* 211 */     for (int i = 0; i < l; i++)
/* 212 */       if (this.b[(o + i)] != s.charAt(i))
/* 213 */         return false;
/* 214 */     this.j = (this.k - l);
/* 215 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   void setto(String s)
/*     */   {
/* 222 */     int l = s.length();
/* 223 */     int o = this.j + 1;
/* 224 */     for (int i = 0; i < l; i++)
/* 225 */       this.b[(o + i)] = s.charAt(i);
/* 226 */     this.k = (this.j + l);
/* 227 */     this.dirty = true;
/*     */   }
/*     */   
/*     */   void r(String s)
/*     */   {
/* 232 */     if (m() > 0) { setto(s);
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final void step1()
/*     */   {
/* 257 */     if (this.b[this.k] == 's') {
/* 258 */       if (ends("sses")) { this.k -= 2;
/* 259 */       } else if (ends("ies")) { setto("i");
/* 260 */       } else if (this.b[(this.k - 1)] != 's') this.k -= 1;
/*     */     }
/* 262 */     if (ends("eed")) {
/* 263 */       if (m() > 0) {
/* 264 */         this.k -= 1;
/*     */       }
/* 266 */     } else if (((ends("ed")) || (ends("ing"))) && (vowelinstem())) {
/* 267 */       this.k = this.j;
/* 268 */       if (ends("at")) { setto("ate");
/* 269 */       } else if (ends("bl")) { setto("ble");
/* 270 */       } else if (ends("iz")) { setto("ize");
/* 271 */       } else if (doublec(this.k)) {
/* 272 */         int ch = this.b[(this.k--)];
/* 273 */         if ((ch == 108) || (ch == 115) || (ch == 122)) {
/* 274 */           this.k += 1;
/*     */         }
/* 276 */       } else if ((m() == 1) && (cvc(this.k))) {
/* 277 */         setto("e");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private final void step2()
/*     */   {
/* 284 */     if ((ends("y")) && (vowelinstem())) {
/* 285 */       this.b[this.k] = 'i';
/* 286 */       this.dirty = true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private final void step3()
/*     */   {
/* 295 */     if (this.k == this.k0) return;
/* 296 */     switch (this.b[(this.k - 1)]) {
/*     */     case 'a': 
/* 298 */       if (ends("ational")) { r("ate");
/* 299 */       } else if (ends("tional")) r("tion");
/*     */       break;
/*     */     case 'c': 
/* 302 */       if (ends("enci")) { r("ence");
/* 303 */       } else if (ends("anci")) r("ance");
/*     */       break;
/*     */     case 'e': 
/* 306 */       if (ends("izer")) r("ize");
/*     */       break;
/*     */     case 'l': 
/* 309 */       if (ends("bli")) { r("ble");
/* 310 */       } else if (ends("alli")) { r("al");
/* 311 */       } else if (ends("entli")) { r("ent");
/* 312 */       } else if (ends("eli")) { r("e");
/* 313 */       } else if (ends("ousli")) r("ous");
/*     */       break;
/*     */     case 'o': 
/* 316 */       if (ends("ization")) { r("ize");
/* 317 */       } else if (ends("ation")) { r("ate");
/* 318 */       } else if (ends("ator")) r("ate");
/*     */       break;
/*     */     case 's': 
/* 321 */       if (ends("alism")) { r("al");
/* 322 */       } else if (ends("iveness")) { r("ive");
/* 323 */       } else if (ends("fulness")) { r("ful");
/* 324 */       } else if (ends("ousness")) r("ous");
/*     */       break;
/*     */     case 't': 
/* 327 */       if (ends("aliti")) { r("al");
/* 328 */       } else if (ends("iviti")) { r("ive");
/* 329 */       } else if (ends("biliti")) r("ble");
/*     */       break;
/*     */     case 'g': 
/* 332 */       if (ends("logi")) r("log");
/*     */       break;
/*     */     }
/*     */   }
/*     */   
/*     */   private final void step4()
/*     */   {
/* 339 */     switch (this.b[this.k]) {
/*     */     case 'e': 
/* 341 */       if (ends("icate")) { r("ic");
/* 342 */       } else if (ends("ative")) { r("");
/* 343 */       } else if (ends("alize")) r("al");
/*     */       break;
/*     */     case 'i': 
/* 346 */       if (ends("iciti")) r("ic");
/*     */       break;
/*     */     case 'l': 
/* 349 */       if (ends("ical")) { r("ic");
/* 350 */       } else if (ends("ful")) r("");
/*     */       break;
/*     */     case 's': 
/* 353 */       if (ends("ness")) { r("");
/*     */       }
/*     */       break;
/*     */     }
/*     */   }
/*     */   
/*     */   private final void step5()
/*     */   {
/* 361 */     if (this.k == this.k0) return;
/* 362 */     switch (this.b[(this.k - 1)]) {
/*     */     case 'a': 
/* 364 */       if (!ends("al"))
/*     */         return;
/*     */       break;
/* 367 */     case 'c':  if ((!ends("ance")) && 
/* 368 */         (!ends("ence")))
/*     */         return;
/*     */       break;
/* 371 */     case 'e':  if (!ends("er"))
/*     */         return;
/*     */       break; case 'i':  if (!ends("ic"))
/*     */         return;
/*     */       break; case 'l':  if ((!ends("able")) && 
/* 376 */         (!ends("ible")))
/*     */         return;
/*     */       break; case 'n':  if ((!ends("ant")) && 
/* 379 */         (!ends("ement")) && 
/* 380 */         (!ends("ment")))
/*     */       {
/* 382 */         if (!ends("ent"))
/*     */           return; }
/*     */       break;
/* 385 */     case 'o':  if ((!ends("ion")) || (this.j < 0) || ((this.b[this.j] != 's') && (this.b[this.j] != 't')))
/*     */       {
/* 387 */         if (!ends("ou"))
/*     */           return; }
/*     */       break;
/*     */     case 's': 
/* 391 */       if (!ends("ism"))
/*     */         return;
/*     */       break;
/* 394 */     case 't':  if ((!ends("ate")) && 
/* 395 */         (!ends("iti")))
/*     */         return;
/*     */       break;
/* 398 */     case 'u':  if (!ends("ous"))
/*     */         return;
/*     */       break;
/* 401 */     case 'v':  if (!ends("ive"))
/*     */         return;
/*     */       break;
/* 404 */     case 'z':  if (!ends("ize"))
/*     */         return;
/*     */       break;
/* 407 */     case 'b': case 'd': case 'f': case 'g': case 'h': case 'j': case 'k': case 'm': case 'p': case 'q': case 'r': case 'w': case 'x': case 'y': default:  return;
/*     */     }
/* 409 */     if (m() > 1) {
/* 410 */       this.k = this.j;
/*     */     }
/*     */   }
/*     */   
/*     */   private final void step6()
/*     */   {
/* 416 */     this.j = this.k;
/* 417 */     if (this.b[this.k] == 'e') {
/* 418 */       int a = m();
/* 419 */       if ((a > 1) || ((a == 1) && (!cvc(this.k - 1))))
/* 420 */         this.k -= 1;
/*     */     }
/* 422 */     if ((this.b[this.k] == 'l') && (doublec(this.k)) && (m() > 1)) {
/* 423 */       this.k -= 1;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String stem(String s)
/*     */   {
/* 431 */     if (stem(s.toCharArray(), s.length())) {
/* 432 */       return toString();
/*     */     }
/* 434 */     return s;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean stem(char[] word)
/*     */   {
/* 442 */     return stem(word, word.length);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean stem(char[] wordBuffer, int offset, int wordLen)
/*     */   {
/* 451 */     reset();
/* 452 */     if (this.b.length < wordLen) {
/* 453 */       char[] new_b = new char[wordLen + 1];
/* 454 */       this.b = new_b;
/*     */     }
/* 456 */     for (int j = 0; j < wordLen; j++)
/* 457 */       this.b[j] = wordBuffer[(offset + j)];
/* 458 */     this.i = wordLen;
/* 459 */     return stem(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean stem(char[] word, int wordLen)
/*     */   {
/* 468 */     return stem(word, 0, wordLen);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean stem()
/*     */   {
/* 477 */     return stem(0);
/*     */   }
/*     */   
/*     */   public boolean stem(int i0) {
/* 481 */     this.k = (this.i - 1);
/* 482 */     this.k0 = i0;
/* 483 */     if (this.k > this.k0 + 1) {
/* 484 */       step1();step2();step3();step4();step5();step6();
/*     */     }
/*     */     
/*     */ 
/* 488 */     if (this.i != this.k + 1)
/* 489 */       this.dirty = true;
/* 490 */     this.i = (this.k + 1);
/* 491 */     return this.dirty;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 499 */     PorterStemmer s = new PorterStemmer();
/*     */     
/* 501 */     for (int i = 0; i < args.length; i++) {
/*     */       try {
/* 503 */         InputStream in = new FileInputStream(args[i]);
/* 504 */         byte[] buffer = new byte['Ѐ'];
/*     */         
/*     */ 
/* 507 */         int bufferLen = in.read(buffer);
/* 508 */         int offset = 0;
/* 509 */         s.reset();
/*     */         for (;;) { int ch;
/*     */           int ch;
/* 512 */           if (offset < bufferLen) {
/* 513 */             ch = buffer[(offset++)];
/*     */           } else {
/* 515 */             bufferLen = in.read(buffer);
/* 516 */             offset = 0;
/* 517 */             int ch; if (bufferLen < 0) {
/* 518 */               ch = -1;
/*     */             } else {
/* 520 */               ch = buffer[(offset++)];
/*     */             }
/*     */           }
/* 523 */           if (Character.isLetter((char)ch)) {
/* 524 */             s.add(Character.toLowerCase((char)ch));
/*     */           }
/*     */           else {
/* 527 */             s.stem();
/* 528 */             System.out.print(s.toString());
/* 529 */             s.reset();
/* 530 */             if (ch < 0) {
/*     */               break;
/*     */             }
/* 533 */             System.out.print((char)ch);
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 538 */         in.close();
/*     */       }
/*     */       catch (IOException e) {
/* 541 */         System.out.println("error reading " + args[i]);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/PorterStemmer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */