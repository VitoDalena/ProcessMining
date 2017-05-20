/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.lucene.index.IndexReader;
/*     */ import org.apache.lucene.index.Term;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WildcardTermEnum
/*     */   extends FilteredTermEnum
/*     */ {
/*     */   Term searchTerm;
/*  35 */   String field = "";
/*  36 */   String text = "";
/*  37 */   String pre = "";
/*  38 */   int preLen = 0;
/*  39 */   boolean fieldMatch = false;
/*  40 */   boolean endEnum = false;
/*     */   
/*     */   public static final char WILDCARD_STRING = '*';
/*     */   
/*     */   public static final char WILDCARD_CHAR = '?';
/*     */   
/*     */   public WildcardTermEnum(IndexReader reader, Term term)
/*     */     throws IOException
/*     */   {
/*  49 */     this.searchTerm = term;
/*  50 */     this.field = this.searchTerm.field();
/*  51 */     this.text = this.searchTerm.text();
/*     */     
/*  53 */     int sidx = this.text.indexOf('*');
/*  54 */     int cidx = this.text.indexOf('?');
/*  55 */     int idx = sidx;
/*  56 */     if (idx == -1) {
/*  57 */       idx = cidx;
/*     */     }
/*  59 */     else if (cidx >= 0) {
/*  60 */       idx = Math.min(idx, cidx);
/*     */     }
/*     */     
/*  63 */     this.pre = this.searchTerm.text().substring(0, idx);
/*  64 */     this.preLen = this.pre.length();
/*  65 */     this.text = this.text.substring(this.preLen);
/*  66 */     setEnum(reader.terms(new Term(this.searchTerm.field(), this.pre)));
/*     */   }
/*     */   
/*     */   protected final boolean termCompare(Term term) {
/*  70 */     if (this.field == term.field()) {
/*  71 */       String searchText = term.text();
/*  72 */       if (searchText.startsWith(this.pre)) {
/*  73 */         return wildcardEquals(this.text, 0, searchText, this.preLen);
/*     */       }
/*     */     }
/*  76 */     this.endEnum = true;
/*  77 */     return false;
/*     */   }
/*     */   
/*     */   public final float difference() {
/*  81 */     return 1.0F;
/*     */   }
/*     */   
/*     */   public final boolean endEnum() {
/*  85 */     return this.endEnum;
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
/*     */   public static final boolean wildcardEquals(String pattern, int patternIdx, String string, int stringIdx)
/*     */   {
/* 103 */     int p = patternIdx;
/*     */     
/* 105 */     for (int s = stringIdx;; s++)
/*     */     {
/*     */ 
/* 108 */       boolean sEnd = s >= string.length();
/*     */       
/* 110 */       boolean pEnd = p >= pattern.length();
/*     */       
/*     */ 
/* 113 */       if (sEnd)
/*     */       {
/*     */ 
/* 116 */         boolean justWildcardsLeft = true;
/*     */         
/*     */ 
/* 119 */         int wildcardSearchPos = p;
/*     */         
/*     */ 
/* 122 */         while ((wildcardSearchPos < pattern.length()) && (justWildcardsLeft))
/*     */         {
/*     */ 
/* 125 */           char wildchar = pattern.charAt(wildcardSearchPos);
/*     */           
/*     */ 
/*     */ 
/* 129 */           if ((wildchar != '?') && (wildchar != '*'))
/*     */           {
/* 131 */             justWildcardsLeft = false;
/*     */ 
/*     */           }
/*     */           else
/*     */           {
/* 136 */             wildcardSearchPos++;
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 142 */         if (justWildcardsLeft)
/*     */         {
/* 144 */           return true;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 150 */       if ((sEnd) || (pEnd)) {
/*     */         break;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 156 */       if (pattern.charAt(p) != '?')
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 162 */         if (pattern.charAt(p) == '*')
/*     */         {
/*     */ 
/* 165 */           p++;
/*     */           
/* 167 */           for (int i = string.length(); i >= s; i--)
/*     */           {
/* 169 */             if (wildcardEquals(pattern, p, string, i))
/*     */             {
/* 171 */               return true;
/*     */             }
/*     */           }
/*     */         }
/*     */         else {
/* 176 */           if (pattern.charAt(p) != string.charAt(s)) {
/*     */             break;
/*     */           }
/*     */         }
/*     */       }
/* 105 */       p++;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 181 */     return false;
/*     */   }
/*     */   
/*     */   public void close()
/*     */     throws IOException
/*     */   {
/* 187 */     super.close();
/* 188 */     this.searchTerm = null;
/* 189 */     this.field = null;
/* 190 */     this.text = null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/WildcardTermEnum.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */