/*     */ package org.apache.lucene.analysis.standard;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.util.Vector;
/*     */ import org.apache.lucene.analysis.Tokenizer;
/*     */ 
/*     */ public class StandardTokenizer extends Tokenizer implements StandardTokenizerConstants
/*     */ {
/*     */   public StandardTokenizerTokenManager token_source;
/*     */   public Token token;
/*     */   public Token jj_nt;
/*     */   private int jj_ntk;
/*     */   private int jj_gen;
/*     */   
/*     */   public StandardTokenizer(Reader reader)
/*     */   {
/*  18 */     this(new FastCharStream(reader));
/*  19 */     this.input = reader;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public final org.apache.lucene.analysis.Token next()
/*     */     throws ParseException, IOException
/*     */   {
/*  27 */     Token token = null;
/*  28 */     switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk) {
/*     */     case 1: 
/*  30 */       token = jj_consume_token(1);
/*  31 */       break;
/*     */     case 2: 
/*  33 */       token = jj_consume_token(2);
/*  34 */       break;
/*     */     case 3: 
/*  36 */       token = jj_consume_token(3);
/*  37 */       break;
/*     */     case 4: 
/*  39 */       token = jj_consume_token(4);
/*  40 */       break;
/*     */     case 5: 
/*  42 */       token = jj_consume_token(5);
/*  43 */       break;
/*     */     case 6: 
/*  45 */       token = jj_consume_token(6);
/*  46 */       break;
/*     */     case 7: 
/*  48 */       token = jj_consume_token(7);
/*  49 */       break;
/*     */     case 12: 
/*  51 */       token = jj_consume_token(12);
/*  52 */       break;
/*     */     case 0: 
/*  54 */       token = jj_consume_token(0);
/*  55 */       break;
/*     */     case 8: case 9: case 10: case 11: default: 
/*  57 */       this.jj_la1[0] = this.jj_gen;
/*  58 */       jj_consume_token(-1);
/*  59 */       throw new ParseException();
/*     */     }
/*  61 */     if (token.kind == 0) {
/*  62 */       return null;
/*     */     }
/*  64 */     return new org.apache.lucene.analysis.Token(token.image, token.beginColumn, token.endColumn, StandardTokenizerConstants.tokenImage[token.kind]);
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
/*  76 */   private final int[] jj_la1 = new int[1];
/*     */   
/*     */   private static int[] jj_la1_0;
/*     */   
/*     */   private static void jj_la1_0()
/*     */   {
/*  82 */     jj_la1_0 = new int[] { 4351 };
/*     */   }
/*     */   
/*     */   public StandardTokenizer(CharStream stream) {
/*  86 */     this.token_source = new StandardTokenizerTokenManager(stream);
/*  87 */     this.token = new Token();
/*  88 */     this.jj_ntk = -1;
/*  89 */     this.jj_gen = 0;
/*  90 */     for (int i = 0; i < 1; i++) this.jj_la1[i] = -1;
/*     */   }
/*     */   
/*     */   public void ReInit(CharStream stream) {
/*  94 */     this.token_source.ReInit(stream);
/*  95 */     this.token = new Token();
/*  96 */     this.jj_ntk = -1;
/*  97 */     this.jj_gen = 0;
/*  98 */     for (int i = 0; i < 1; i++) this.jj_la1[i] = -1;
/*     */   }
/*     */   
/*     */   public StandardTokenizer(StandardTokenizerTokenManager tm) {
/* 102 */     this.token_source = tm;
/* 103 */     this.token = new Token();
/* 104 */     this.jj_ntk = -1;
/* 105 */     this.jj_gen = 0;
/* 106 */     for (int i = 0; i < 1; i++) this.jj_la1[i] = -1;
/*     */   }
/*     */   
/*     */   public void ReInit(StandardTokenizerTokenManager tm) {
/* 110 */     this.token_source = tm;
/* 111 */     this.token = new Token();
/* 112 */     this.jj_ntk = -1;
/* 113 */     this.jj_gen = 0;
/* 114 */     for (int i = 0; i < 1; i++) this.jj_la1[i] = -1;
/*     */   }
/*     */   
/*     */   private final Token jj_consume_token(int kind) throws ParseException {
/*     */     Token oldToken;
/* 119 */     if ((oldToken = this.token).next != null) this.token = this.token.next; else
/* 120 */       this.token = (this.token.next = this.token_source.getNextToken());
/* 121 */     this.jj_ntk = -1;
/* 122 */     if (this.token.kind == kind) {
/* 123 */       this.jj_gen += 1;
/* 124 */       return this.token;
/*     */     }
/* 126 */     this.token = oldToken;
/* 127 */     this.jj_kind = kind;
/* 128 */     throw generateParseException();
/*     */   }
/*     */   
/*     */   public final Token getNextToken() {
/* 132 */     if (this.token.next != null) this.token = this.token.next; else
/* 133 */       this.token = (this.token.next = this.token_source.getNextToken());
/* 134 */     this.jj_ntk = -1;
/* 135 */     this.jj_gen += 1;
/* 136 */     return this.token;
/*     */   }
/*     */   
/*     */   public final Token getToken(int index) {
/* 140 */     Token t = this.token;
/* 141 */     for (int i = 0; i < index; i++) {
/* 142 */       if (t.next != null) t = t.next; else
/* 143 */         t = t.next = this.token_source.getNextToken();
/*     */     }
/* 145 */     return t;
/*     */   }
/*     */   
/*     */   private final int jj_ntk() {
/* 149 */     if ((this.jj_nt = this.token.next) == null) {
/* 150 */       return this.jj_ntk = (this.token.next = this.token_source.getNextToken()).kind;
/*     */     }
/* 152 */     return this.jj_ntk = this.jj_nt.kind;
/*     */   }
/*     */   
/* 155 */   private Vector jj_expentries = new Vector();
/*     */   private int[] jj_expentry;
/* 157 */   private int jj_kind = -1;
/*     */   
/*     */   public ParseException generateParseException() {
/* 160 */     this.jj_expentries.removeAllElements();
/* 161 */     boolean[] la1tokens = new boolean[15];
/* 162 */     for (int i = 0; i < 15; i++) {
/* 163 */       la1tokens[i] = false;
/*     */     }
/* 165 */     if (this.jj_kind >= 0) {
/* 166 */       la1tokens[this.jj_kind] = true;
/* 167 */       this.jj_kind = -1;
/*     */     }
/* 169 */     for (int i = 0; i < 1; i++) {
/* 170 */       if (this.jj_la1[i] == this.jj_gen) {
/* 171 */         for (int j = 0; j < 32; j++) {
/* 172 */           if ((jj_la1_0[i] & 1 << j) != 0) {
/* 173 */             la1tokens[j] = true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 178 */     for (int i = 0; i < 15; i++) {
/* 179 */       if (la1tokens[i] != 0) {
/* 180 */         this.jj_expentry = new int[1];
/* 181 */         this.jj_expentry[0] = i;
/* 182 */         this.jj_expentries.addElement(this.jj_expentry);
/*     */       }
/*     */     }
/* 185 */     int[][] exptokseq = new int[this.jj_expentries.size()][];
/* 186 */     for (int i = 0; i < this.jj_expentries.size(); i++) {
/* 187 */       exptokseq[i] = ((int[])this.jj_expentries.elementAt(i));
/*     */     }
/* 189 */     return new ParseException(this.token, exptokseq, StandardTokenizerConstants.tokenImage);
/*     */   }
/*     */   
/*     */   public final void enable_tracing() {}
/*     */   
/*     */   public final void disable_tracing() {}
/*     */   
/*     */   static {}
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/standard/StandardTokenizer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */