/*     */ package org.apache.lucene.analysis.standard;
/*     */ 
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParseException
/*     */   extends IOException
/*     */ {
/*     */   protected boolean specialConstructor;
/*     */   public Token currentToken;
/*     */   public int[][] expectedTokenSequences;
/*     */   public String[] tokenImage;
/*     */   
/*     */   public ParseException(Token currentTokenVal, int[][] expectedTokenSequencesVal, String[] tokenImageVal)
/*     */   {
/*  32 */     super("");
/*  33 */     this.specialConstructor = true;
/*  34 */     this.currentToken = currentTokenVal;
/*  35 */     this.expectedTokenSequences = expectedTokenSequencesVal;
/*  36 */     this.tokenImage = tokenImageVal;
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
/*     */   public ParseException()
/*     */   {
/*  51 */     this.specialConstructor = false;
/*     */   }
/*     */   
/*     */   public ParseException(String message) {
/*  55 */     super(message);
/*  56 */     this.specialConstructor = false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getMessage()
/*     */   {
/*  98 */     if (!this.specialConstructor) {
/*  99 */       return super.getMessage();
/*     */     }
/* 101 */     String expected = "";
/* 102 */     int maxSize = 0;
/* 103 */     for (int i = 0; i < this.expectedTokenSequences.length; i++) {
/* 104 */       if (maxSize < this.expectedTokenSequences[i].length) {
/* 105 */         maxSize = this.expectedTokenSequences[i].length;
/*     */       }
/* 107 */       for (int j = 0; j < this.expectedTokenSequences[i].length; j++) {
/* 108 */         expected = expected + this.tokenImage[this.expectedTokenSequences[i][j]] + " ";
/*     */       }
/* 110 */       if (this.expectedTokenSequences[i][(this.expectedTokenSequences[i].length - 1)] != 0) {
/* 111 */         expected = expected + "...";
/*     */       }
/* 113 */       expected = expected + this.eol + "    ";
/*     */     }
/* 115 */     String retval = "Encountered \"";
/* 116 */     Token tok = this.currentToken.next;
/* 117 */     for (int i = 0; i < maxSize; i++) {
/* 118 */       if (i != 0) retval = retval + " ";
/* 119 */       if (tok.kind == 0) {
/* 120 */         retval = retval + this.tokenImage[0];
/* 121 */         break;
/*     */       }
/* 123 */       retval = retval + add_escapes(tok.image);
/* 124 */       tok = tok.next;
/*     */     }
/* 126 */     retval = retval + "\" at line " + this.currentToken.next.beginLine + ", column " + this.currentToken.next.beginColumn + "." + this.eol;
/* 127 */     if (this.expectedTokenSequences.length == 1) {
/* 128 */       retval = retval + "Was expecting:" + this.eol + "    ";
/*     */     } else {
/* 130 */       retval = retval + "Was expecting one of:" + this.eol + "    ";
/*     */     }
/* 132 */     retval = retval + expected;
/* 133 */     return retval;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 139 */   protected String eol = System.getProperty("line.separator", "\n");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String add_escapes(String str)
/*     */   {
/* 147 */     StringBuffer retval = new StringBuffer();
/*     */     
/* 149 */     for (int i = 0; i < str.length(); i++) {
/* 150 */       switch (str.charAt(i))
/*     */       {
/*     */       case '\000': 
/*     */         break;
/*     */       case '\b': 
/* 155 */         retval.append("\\b");
/* 156 */         break;
/*     */       case '\t': 
/* 158 */         retval.append("\\t");
/* 159 */         break;
/*     */       case '\n': 
/* 161 */         retval.append("\\n");
/* 162 */         break;
/*     */       case '\f': 
/* 164 */         retval.append("\\f");
/* 165 */         break;
/*     */       case '\r': 
/* 167 */         retval.append("\\r");
/* 168 */         break;
/*     */       case '"': 
/* 170 */         retval.append("\\\"");
/* 171 */         break;
/*     */       case '\'': 
/* 173 */         retval.append("\\'");
/* 174 */         break;
/*     */       case '\\': 
/* 176 */         retval.append("\\\\");
/* 177 */         break;
/*     */       default:  char ch;
/* 179 */         if (((ch = str.charAt(i)) < ' ') || (ch > '~')) {
/* 180 */           String s = "0000" + Integer.toString(ch, 16);
/* 181 */           retval.append("\\u" + s.substring(s.length() - 4, s.length()));
/*     */         } else {
/* 183 */           retval.append(ch);
/*     */         }
/*     */         break;
/*     */       }
/*     */     }
/* 188 */     return retval.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/standard/ParseException.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */