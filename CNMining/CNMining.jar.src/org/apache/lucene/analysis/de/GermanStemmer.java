/*     */ package org.apache.lucene.analysis.de;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GermanStemmer
/*     */ {
/*  32 */   private StringBuffer sb = new StringBuffer();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  37 */   private int substCount = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String stem(String term)
/*     */   {
/*  48 */     term = term.toLowerCase();
/*  49 */     if (!isStemmable(term)) {
/*  50 */       return term;
/*     */     }
/*  52 */     this.sb.delete(0, this.sb.length());
/*  53 */     this.sb.insert(0, term);
/*     */     
/*  55 */     substitute(this.sb);
/*  56 */     strip(this.sb);
/*  57 */     optimize(this.sb);
/*  58 */     resubstitute(this.sb);
/*  59 */     removeParticleDenotion(this.sb);
/*  60 */     return this.sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isStemmable(String term)
/*     */   {
/*  70 */     for (int c = 0; c < term.length(); c++) {
/*  71 */       if (!Character.isLetter(term.charAt(c)))
/*  72 */         return false;
/*     */     }
/*  74 */     return true;
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
/*     */   private void strip(StringBuffer buffer)
/*     */   {
/*  87 */     boolean doMore = true;
/*  88 */     while ((doMore) && (buffer.length() > 3)) {
/*  89 */       if ((buffer.length() + this.substCount > 5) && (buffer.substring(buffer.length() - 2, buffer.length()).equals("nd")))
/*     */       {
/*     */ 
/*  92 */         buffer.delete(buffer.length() - 2, buffer.length());
/*     */       }
/*  94 */       else if ((buffer.length() + this.substCount > 4) && (buffer.substring(buffer.length() - 2, buffer.length()).equals("em")))
/*     */       {
/*  96 */         buffer.delete(buffer.length() - 2, buffer.length());
/*     */       }
/*  98 */       else if ((buffer.length() + this.substCount > 4) && (buffer.substring(buffer.length() - 2, buffer.length()).equals("er")))
/*     */       {
/* 100 */         buffer.delete(buffer.length() - 2, buffer.length());
/*     */       }
/* 102 */       else if (buffer.charAt(buffer.length() - 1) == 'e') {
/* 103 */         buffer.deleteCharAt(buffer.length() - 1);
/*     */       }
/* 105 */       else if (buffer.charAt(buffer.length() - 1) == 's') {
/* 106 */         buffer.deleteCharAt(buffer.length() - 1);
/*     */       }
/* 108 */       else if (buffer.charAt(buffer.length() - 1) == 'n') {
/* 109 */         buffer.deleteCharAt(buffer.length() - 1);
/*     */ 
/*     */       }
/* 112 */       else if (buffer.charAt(buffer.length() - 1) == 't') {
/* 113 */         buffer.deleteCharAt(buffer.length() - 1);
/*     */       }
/*     */       else {
/* 116 */         doMore = false;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void optimize(StringBuffer buffer)
/*     */   {
/* 128 */     if ((buffer.length() > 5) && (buffer.substring(buffer.length() - 5, buffer.length()).equals("erin*"))) {
/* 129 */       buffer.deleteCharAt(buffer.length() - 1);
/* 130 */       strip(buffer);
/*     */     }
/*     */     
/* 133 */     if (buffer.charAt(buffer.length() - 1) == 'z') {
/* 134 */       buffer.setCharAt(buffer.length() - 1, 'x');
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void removeParticleDenotion(StringBuffer buffer)
/*     */   {
/* 143 */     if (buffer.length() > 4) {
/* 144 */       for (int c = 0; c < buffer.length() - 3; c++) {
/* 145 */         if (buffer.substring(c, c + 4).equals("gege")) {
/* 146 */           buffer.delete(c, c + 2);
/* 147 */           return;
/*     */         }
/*     */       }
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
/*     */   private void substitute(StringBuffer buffer)
/*     */   {
/* 165 */     this.substCount = 0;
/* 166 */     for (int c = 0; c < buffer.length(); c++)
/*     */     {
/* 168 */       if ((c > 0) && (buffer.charAt(c) == buffer.charAt(c - 1))) {
/* 169 */         buffer.setCharAt(c, '*');
/*     */ 
/*     */       }
/* 172 */       else if (buffer.charAt(c) == 'ä') {
/* 173 */         buffer.setCharAt(c, 'a');
/*     */       }
/* 175 */       else if (buffer.charAt(c) == 'ö') {
/* 176 */         buffer.setCharAt(c, 'o');
/*     */       }
/* 178 */       else if (buffer.charAt(c) == 'ü') {
/* 179 */         buffer.setCharAt(c, 'u');
/*     */       }
/*     */       
/* 182 */       if (c < buffer.length() - 1) {
/* 183 */         if (buffer.charAt(c) == 'ß') {
/* 184 */           buffer.setCharAt(c, 's');
/* 185 */           buffer.insert(c + 1, 's');
/* 186 */           this.substCount += 1;
/*     */ 
/*     */         }
/* 189 */         else if ((c < buffer.length() - 2) && (buffer.charAt(c) == 's') && (buffer.charAt(c + 1) == 'c') && (buffer.charAt(c + 2) == 'h'))
/*     */         {
/*     */ 
/* 192 */           buffer.setCharAt(c, '$');
/* 193 */           buffer.delete(c + 1, c + 3);
/* 194 */           this.substCount = 2;
/*     */         }
/* 196 */         else if ((buffer.charAt(c) == 'c') && (buffer.charAt(c + 1) == 'h')) {
/* 197 */           buffer.setCharAt(c, '§');
/* 198 */           buffer.deleteCharAt(c + 1);
/* 199 */           this.substCount += 1;
/*     */         }
/* 201 */         else if ((buffer.charAt(c) == 'e') && (buffer.charAt(c + 1) == 'i')) {
/* 202 */           buffer.setCharAt(c, '%');
/* 203 */           buffer.deleteCharAt(c + 1);
/* 204 */           this.substCount += 1;
/*     */         }
/* 206 */         else if ((buffer.charAt(c) == 'i') && (buffer.charAt(c + 1) == 'e')) {
/* 207 */           buffer.setCharAt(c, '&');
/* 208 */           buffer.deleteCharAt(c + 1);
/* 209 */           this.substCount += 1;
/*     */         }
/* 211 */         else if ((buffer.charAt(c) == 'i') && (buffer.charAt(c + 1) == 'g')) {
/* 212 */           buffer.setCharAt(c, '#');
/* 213 */           buffer.deleteCharAt(c + 1);
/* 214 */           this.substCount += 1;
/*     */         }
/* 216 */         else if ((buffer.charAt(c) == 's') && (buffer.charAt(c + 1) == 't')) {
/* 217 */           buffer.setCharAt(c, '!');
/* 218 */           buffer.deleteCharAt(c + 1);
/* 219 */           this.substCount += 1;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void resubstitute(StringBuffer buffer)
/*     */   {
/* 232 */     for (int c = 0; c < buffer.length(); c++) {
/* 233 */       if (buffer.charAt(c) == '*') {
/* 234 */         char x = buffer.charAt(c - 1);
/* 235 */         buffer.setCharAt(c, x);
/*     */       }
/* 237 */       else if (buffer.charAt(c) == '$') {
/* 238 */         buffer.setCharAt(c, 's');
/* 239 */         buffer.insert(c + 1, new char[] { 'c', 'h' }, 0, 2);
/*     */       }
/* 241 */       else if (buffer.charAt(c) == '§') {
/* 242 */         buffer.setCharAt(c, 'c');
/* 243 */         buffer.insert(c + 1, 'h');
/*     */       }
/* 245 */       else if (buffer.charAt(c) == '%') {
/* 246 */         buffer.setCharAt(c, 'e');
/* 247 */         buffer.insert(c + 1, 'i');
/*     */       }
/* 249 */       else if (buffer.charAt(c) == '&') {
/* 250 */         buffer.setCharAt(c, 'i');
/* 251 */         buffer.insert(c + 1, 'e');
/*     */       }
/* 253 */       else if (buffer.charAt(c) == '#') {
/* 254 */         buffer.setCharAt(c, 'i');
/* 255 */         buffer.insert(c + 1, 'g');
/*     */       }
/* 257 */       else if (buffer.charAt(c) == '!') {
/* 258 */         buffer.setCharAt(c, 's');
/* 259 */         buffer.insert(c + 1, 't');
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/de/GermanStemmer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */