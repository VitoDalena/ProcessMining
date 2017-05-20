/*      */ package org.apache.lucene.queryParser;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.io.StringReader;
/*      */ import java.text.DateFormat;
/*      */ import java.util.Date;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Locale;
/*      */ import java.util.Vector;
/*      */ import org.apache.lucene.analysis.Analyzer;
/*      */ import org.apache.lucene.analysis.SimpleAnalyzer;
/*      */ import org.apache.lucene.analysis.TokenStream;
/*      */ import org.apache.lucene.document.DateField;
/*      */ import org.apache.lucene.index.Term;
/*      */ import org.apache.lucene.search.BooleanClause;
/*      */ import org.apache.lucene.search.BooleanQuery;
/*      */ import org.apache.lucene.search.BooleanQuery.TooManyClauses;
/*      */ import org.apache.lucene.search.FuzzyQuery;
/*      */ import org.apache.lucene.search.PhraseQuery;
/*      */ import org.apache.lucene.search.PrefixQuery;
/*      */ import org.apache.lucene.search.Query;
/*      */ import org.apache.lucene.search.RangeQuery;
/*      */ import org.apache.lucene.search.TermQuery;
/*      */ import org.apache.lucene.search.WildcardQuery;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class QueryParser
/*      */   implements QueryParserConstants
/*      */ {
/*      */   private static final int CONJ_NONE = 0;
/*      */   private static final int CONJ_AND = 1;
/*      */   private static final int CONJ_OR = 2;
/*      */   private static final int MOD_NONE = 0;
/*      */   private static final int MOD_NOT = 10;
/*      */   private static final int MOD_REQ = 11;
/*      */   public static final int DEFAULT_OPERATOR_OR = 0;
/*      */   public static final int DEFAULT_OPERATOR_AND = 1;
/*   65 */   private int operator = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   71 */   boolean lowercaseWildcardTerms = true;
/*      */   
/*      */   Analyzer analyzer;
/*      */   String field;
/*   75 */   int phraseSlop = 0;
/*   76 */   float fuzzyMinSim = 0.5F;
/*   77 */   Locale locale = Locale.getDefault();
/*      */   public QueryParserTokenManager token_source;
/*      */   public Token token;
/*      */   public Token jj_nt;
/*      */   private int jj_ntk;
/*      */   private Token jj_scanpos;
/*      */   private Token jj_lastpos;
/*      */   private int jj_la;
/*      */   
/*      */   public static Query parse(String query, String field, Analyzer analyzer) throws ParseException {
/*   87 */     QueryParser parser = new QueryParser(field, analyzer);
/*   88 */     return parser.parse(query);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public QueryParser(String f, Analyzer a)
/*      */   {
/*   96 */     this(new FastCharStream(new StringReader("")));
/*   97 */     this.analyzer = a;
/*   98 */     this.field = f;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Query parse(String query)
/*      */     throws ParseException
/*      */   {
/*  107 */     ReInit(new FastCharStream(new StringReader(query)));
/*      */     try {
/*  109 */       return Query(this.field);
/*      */     }
/*      */     catch (TokenMgrError tme) {
/*  112 */       throw new ParseException(tme.getMessage());
/*      */     }
/*      */     catch (BooleanQuery.TooManyClauses tmc) {
/*  115 */       throw new ParseException("Too many boolean clauses");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Analyzer getAnalyzer()
/*      */   {
/*  123 */     return this.analyzer;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String getField()
/*      */   {
/*  130 */     return this.field;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public float getFuzzyMinSim()
/*      */   {
/*  137 */     return this.fuzzyMinSim;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setFuzzyMinSim(float fuzzyMinSim)
/*      */   {
/*  143 */     this.fuzzyMinSim = fuzzyMinSim;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPhraseSlop(int phraseSlop)
/*      */   {
/*  151 */     this.phraseSlop = phraseSlop;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getPhraseSlop()
/*      */   {
/*  158 */     return this.phraseSlop;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setOperator(int operator)
/*      */   {
/*  170 */     this.operator = operator;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getOperator()
/*      */   {
/*  178 */     return this.operator;
/*      */   }
/*      */   
/*      */   public void setLowercaseWildcardTerms(boolean lowercaseWildcardTerms) {
/*  182 */     this.lowercaseWildcardTerms = lowercaseWildcardTerms;
/*      */   }
/*      */   
/*      */   public boolean getLowercaseWildcardTerms() {
/*  186 */     return this.lowercaseWildcardTerms;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setLocale(Locale locale)
/*      */   {
/*  193 */     this.locale = locale;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Locale getLocale()
/*      */   {
/*  200 */     return this.locale;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void addClause(Vector clauses, int conj, int mods, Query q)
/*      */   {
/*  208 */     if ((clauses.size() > 0) && (conj == 1)) {
/*  209 */       BooleanClause c = (BooleanClause)clauses.elementAt(clauses.size() - 1);
/*  210 */       if (!c.prohibited) {
/*  211 */         c.required = true;
/*      */       }
/*      */     }
/*  214 */     if ((clauses.size() > 0) && (this.operator == 1) && (conj == 2))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  219 */       BooleanClause c = (BooleanClause)clauses.elementAt(clauses.size() - 1);
/*  220 */       if (!c.prohibited) {
/*  221 */         c.required = false;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  226 */     if (q == null) return;
/*      */     boolean prohibited;
/*      */     boolean required;
/*  229 */     if (this.operator == 0)
/*      */     {
/*      */ 
/*  232 */       boolean prohibited = mods == 10;
/*  233 */       boolean required = mods == 11;
/*  234 */       if ((conj == 1) && (!prohibited)) {
/*  235 */         required = true;
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  240 */       prohibited = mods == 10;
/*  241 */       required = (!prohibited) && (conj != 2);
/*      */     }
/*  243 */     clauses.addElement(new BooleanClause(q, required, prohibited));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Query getFieldQuery(String field, Analyzer analyzer, String queryText)
/*      */     throws ParseException
/*      */   {
/*  257 */     return getFieldQuery(field, queryText);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Query getFieldQuery(String field, String queryText)
/*      */     throws ParseException
/*      */   {
/*  267 */     TokenStream source = this.analyzer.tokenStream(field, new StringReader(queryText));
/*      */     
/*  269 */     Vector v = new Vector();
/*      */     for (;;)
/*      */     {
/*      */       org.apache.lucene.analysis.Token t;
/*      */       try {
/*  274 */         t = source.next();
/*      */       } catch (IOException e) {
/*      */         org.apache.lucene.analysis.Token t;
/*  277 */         t = null;
/*      */       }
/*  279 */       if (t == null)
/*      */         break;
/*  281 */       v.addElement(t.termText());
/*      */     }
/*      */     try {
/*  284 */       source.close();
/*      */     }
/*      */     catch (IOException e) {}
/*      */     
/*      */ 
/*      */ 
/*  290 */     if (v.size() == 0)
/*  291 */       return null;
/*  292 */     if (v.size() == 1) {
/*  293 */       return new TermQuery(new Term(field, (String)v.elementAt(0)));
/*      */     }
/*  295 */     PhraseQuery q = new PhraseQuery();
/*  296 */     q.setSlop(this.phraseSlop);
/*  297 */     for (int i = 0; i < v.size(); i++) {
/*  298 */       q.add(new Term(field, (String)v.elementAt(i)));
/*      */     }
/*  300 */     return q;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Query getFieldQuery(String field, Analyzer analyzer, String queryText, int slop)
/*      */     throws ParseException
/*      */   {
/*  320 */     return getFieldQuery(field, queryText, slop);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Query getFieldQuery(String field, String queryText, int slop)
/*      */     throws ParseException
/*      */   {
/*  332 */     Query query = getFieldQuery(field, queryText);
/*      */     
/*  334 */     if ((query instanceof PhraseQuery)) {
/*  335 */       ((PhraseQuery)query).setSlop(slop);
/*      */     }
/*      */     
/*  338 */     return query;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Query getRangeQuery(String field, Analyzer analyzer, String part1, String part2, boolean inclusive)
/*      */     throws ParseException
/*      */   {
/*  354 */     return getRangeQuery(field, part1, part2, inclusive);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Query getRangeQuery(String field, String part1, String part2, boolean inclusive)
/*      */     throws ParseException
/*      */   {
/*      */     try
/*      */     {
/*  366 */       DateFormat df = DateFormat.getDateInstance(3, this.locale);
/*  367 */       df.setLenient(true);
/*  368 */       Date d1 = df.parse(part1);
/*  369 */       Date d2 = df.parse(part2);
/*  370 */       part1 = DateField.dateToString(d1);
/*  371 */       part2 = DateField.dateToString(d2);
/*      */     }
/*      */     catch (Exception e) {}
/*      */     
/*  375 */     return new RangeQuery(new Term(field, part1), new Term(field, part2), inclusive);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Query getBooleanQuery(Vector clauses)
/*      */     throws ParseException
/*      */   {
/*  395 */     BooleanQuery query = new BooleanQuery();
/*  396 */     for (int i = 0; i < clauses.size(); i++) {
/*  397 */       query.add((BooleanClause)clauses.elementAt(i));
/*      */     }
/*  399 */     return query;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Query getWildcardQuery(String field, String termStr)
/*      */     throws ParseException
/*      */   {
/*  425 */     if (this.lowercaseWildcardTerms) {
/*  426 */       termStr = termStr.toLowerCase();
/*      */     }
/*  428 */     Term t = new Term(field, termStr);
/*  429 */     return new WildcardQuery(t);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   static final class JJCalls
/*      */   {
/*      */     int gen;
/*      */     
/*      */ 
/*      */ 
/*      */     Token first;
/*      */     
/*      */ 
/*      */ 
/*      */     int arg;
/*      */     
/*      */ 
/*      */ 
/*      */     JJCalls next;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected Query getPrefixQuery(String field, String termStr)
/*      */     throws ParseException
/*      */   {
/*  457 */     if (this.lowercaseWildcardTerms) {
/*  458 */       termStr = termStr.toLowerCase();
/*      */     }
/*  460 */     Term t = new Term(field, termStr);
/*  461 */     return new PrefixQuery(t);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Query getFuzzyQuery(String field, String termStr)
/*      */     throws ParseException
/*      */   {
/*  476 */     return getFuzzyQuery(field, termStr, this.fuzzyMinSim);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Query getFuzzyQuery(String field, String termStr, float minSimilarity)
/*      */     throws ParseException
/*      */   {
/*  493 */     Term t = new Term(field, termStr);
/*  494 */     return new FuzzyQuery(t, minSimilarity);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private String discardEscapeChar(String input)
/*      */   {
/*  502 */     char[] caSource = input.toCharArray();
/*  503 */     char[] caDest = new char[caSource.length];
/*  504 */     int j = 0;
/*  505 */     for (int i = 0; i < caSource.length; i++) {
/*  506 */       if ((caSource[i] != '\\') || ((i > 0) && (caSource[(i - 1)] == '\\'))) {
/*  507 */         caDest[(j++)] = caSource[i];
/*      */       }
/*      */     }
/*  510 */     return new String(caDest, 0, j);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static String escape(String s)
/*      */   {
/*  518 */     StringBuffer sb = new StringBuffer();
/*  519 */     for (int i = 0; i < s.length(); i++) {
/*  520 */       char c = s.charAt(i);
/*      */       
/*  522 */       if ((c == '\\') || (c == '+') || (c == '-') || (c == '!') || (c == '(') || (c == ')') || (c == ':') || (c == '^') || (c == '[') || (c == ']') || (c == '"') || (c == '{') || (c == '}') || (c == '~') || (c == '*') || (c == '?'))
/*      */       {
/*      */ 
/*  525 */         sb.append('\\');
/*      */       }
/*  527 */       sb.append(c);
/*      */     }
/*  529 */     return sb.toString();
/*      */   }
/*      */   
/*      */   public static void main(String[] args) throws Exception {
/*  533 */     QueryParser qp = new QueryParser("field", new SimpleAnalyzer());
/*      */     
/*  535 */     Query q = qp.parse(args[0]);
/*  536 */     System.out.println(q.toString("field"));
/*      */   }
/*      */   
/*      */   public final int Conjunction()
/*      */     throws ParseException
/*      */   {
/*  542 */     int ret = 0;
/*  543 */     switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk) {
/*      */     case 7: 
/*      */     case 8: 
/*  546 */       switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk) {
/*      */       case 7: 
/*  548 */         jj_consume_token(7);
/*  549 */         ret = 1;
/*  550 */         break;
/*      */       case 8: 
/*  552 */         jj_consume_token(8);
/*  553 */         ret = 2;
/*  554 */         break;
/*      */       default: 
/*  556 */         this.jj_la1[0] = this.jj_gen;
/*  557 */         jj_consume_token(-1);
/*  558 */         throw new ParseException();
/*      */       }
/*      */       break;
/*      */     default: 
/*  562 */       this.jj_la1[1] = this.jj_gen;
/*      */     }
/*      */     
/*  565 */     return ret;
/*      */   }
/*      */   
/*      */   public final int Modifiers() throws ParseException
/*      */   {
/*  570 */     int ret = 0;
/*  571 */     switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk) {
/*      */     case 9: 
/*      */     case 10: 
/*      */     case 11: 
/*  575 */       switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk) {
/*      */       case 10: 
/*  577 */         jj_consume_token(10);
/*  578 */         ret = 11;
/*  579 */         break;
/*      */       case 11: 
/*  581 */         jj_consume_token(11);
/*  582 */         ret = 10;
/*  583 */         break;
/*      */       case 9: 
/*  585 */         jj_consume_token(9);
/*  586 */         ret = 10;
/*  587 */         break;
/*      */       default: 
/*  589 */         this.jj_la1[2] = this.jj_gen;
/*  590 */         jj_consume_token(-1);
/*  591 */         throw new ParseException();
/*      */       }
/*      */       break;
/*      */     default: 
/*  595 */       this.jj_la1[3] = this.jj_gen;
/*      */     }
/*      */     
/*  598 */     return ret;
/*      */   }
/*      */   
/*      */   public final Query Query(String field) throws ParseException
/*      */   {
/*  603 */     Vector clauses = new Vector();
/*  604 */     Query firstQuery = null;
/*      */     
/*  606 */     int mods = Modifiers();
/*  607 */     Query q = Clause(field);
/*  608 */     addClause(clauses, 0, mods, q);
/*  609 */     if (mods == 0) {
/*  610 */       firstQuery = q;
/*      */     }
/*      */     for (;;) {
/*  613 */       switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk) {
/*      */       case 7: case 8: 
/*      */       case 9: 
/*      */       case 10: 
/*      */       case 11: 
/*      */       case 12: 
/*      */       case 16: 
/*      */       case 17: 
/*      */       case 19: 
/*      */       case 20: 
/*      */       case 21: 
/*      */       case 22: 
/*      */       case 23: 
/*      */         break;
/*      */       case 13: case 14: 
/*      */       case 15: case 18: 
/*      */       default: 
/*  630 */         this.jj_la1[4] = this.jj_gen;
/*  631 */         break;
/*      */       }
/*  633 */       int conj = Conjunction();
/*  634 */       mods = Modifiers();
/*  635 */       q = Clause(field);
/*  636 */       addClause(clauses, conj, mods, q);
/*      */     }
/*  638 */     if ((clauses.size() == 1) && (firstQuery != null)) {
/*  639 */       return firstQuery;
/*      */     }
/*  641 */     return getBooleanQuery(clauses);
/*      */   }
/*      */   
/*      */ 
/*      */   public final Query Clause(String field)
/*      */     throws ParseException
/*      */   {
/*  648 */     Token fieldToken = null;Token boost = null;
/*  649 */     if (jj_2_1(2)) {
/*  650 */       fieldToken = jj_consume_token(17);
/*  651 */       jj_consume_token(14);
/*  652 */       field = discardEscapeChar(fieldToken.image);
/*      */     }
/*      */     Query q;
/*      */     Query q;
/*  656 */     switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk) {
/*      */     case 16: 
/*      */     case 17: 
/*      */     case 19: 
/*      */     case 20: 
/*      */     case 21: 
/*      */     case 22: 
/*      */     case 23: 
/*  664 */       q = Term(field);
/*  665 */       break;
/*      */     case 12: 
/*  667 */       jj_consume_token(12);
/*  668 */       q = Query(field);
/*  669 */       jj_consume_token(13);
/*  670 */       switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk) {
/*      */       case 15: 
/*  672 */         jj_consume_token(15);
/*  673 */         boost = jj_consume_token(23);
/*  674 */         break;
/*      */       default: 
/*  676 */         this.jj_la1[5] = this.jj_gen;
/*      */       }
/*      */       
/*  679 */       break;
/*      */     case 13: case 14: case 15: case 18: default: 
/*  681 */       this.jj_la1[6] = this.jj_gen;
/*  682 */       jj_consume_token(-1);
/*  683 */       throw new ParseException();
/*      */     }
/*  685 */     if (boost != null) {
/*  686 */       float f = 1.0F;
/*      */       try {
/*  688 */         f = Float.valueOf(boost.image).floatValue();
/*  689 */         q.setBoost(f);
/*      */       } catch (Exception ignored) {}
/*      */     }
/*  692 */     return q;
/*      */   }
/*      */   
/*      */   public final Query Term(String field) throws ParseException
/*      */   {
/*  697 */     Token boost = null;Token fuzzySlop = null;
/*  698 */     boolean prefix = false;
/*  699 */     boolean wildcard = false;
/*  700 */     boolean fuzzy = false;
/*  701 */     boolean rangein = false;
/*      */     Query q;
/*  703 */     Query q; Query q; Query q; switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk) {
/*      */     case 17: case 19: 
/*      */     case 20: case 23: 
/*      */       Token term;
/*      */       Token term;
/*  708 */       switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk) {
/*      */       case 17: 
/*  710 */         term = jj_consume_token(17);
/*  711 */         break;
/*      */       case 19: 
/*  713 */         Token term = jj_consume_token(19);
/*  714 */         prefix = true;
/*  715 */         break;
/*      */       case 20: 
/*  717 */         Token term = jj_consume_token(20);
/*  718 */         wildcard = true;
/*  719 */         break;
/*      */       case 23: 
/*  721 */         term = jj_consume_token(23);
/*  722 */         break;
/*      */       case 18: case 21: case 22: default: 
/*  724 */         this.jj_la1[7] = this.jj_gen;
/*  725 */         jj_consume_token(-1);
/*  726 */         throw new ParseException();
/*      */       }
/*  728 */       switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk) {
/*      */       case 18: 
/*  730 */         fuzzySlop = jj_consume_token(18);
/*  731 */         fuzzy = true;
/*  732 */         break;
/*      */       default: 
/*  734 */         this.jj_la1[8] = this.jj_gen;
/*      */       }
/*      */       
/*  737 */       switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk) {
/*      */       case 15: 
/*  739 */         jj_consume_token(15);
/*  740 */         boost = jj_consume_token(23);
/*  741 */         switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk) {
/*      */         case 18: 
/*  743 */           fuzzySlop = jj_consume_token(18);
/*  744 */           fuzzy = true;
/*  745 */           break;
/*      */         default: 
/*  747 */           this.jj_la1[9] = this.jj_gen;
/*      */         }
/*      */         
/*  750 */         break;
/*      */       default: 
/*  752 */         this.jj_la1[10] = this.jj_gen;
/*      */       }
/*      */       
/*  755 */       String termImage = discardEscapeChar(term.image);
/*  756 */       Query q; if (wildcard) {
/*  757 */         q = getWildcardQuery(field, termImage); } else { Query q;
/*  758 */         if (prefix) {
/*  759 */           q = getPrefixQuery(field, discardEscapeChar(term.image.substring(0, term.image.length() - 1)));
/*      */         } else {
/*      */           Query q;
/*  762 */           if (fuzzy) {
/*  763 */             float fms = this.fuzzyMinSim;
/*      */             try {
/*  765 */               fms = Float.valueOf(fuzzySlop.image.substring(1)).floatValue();
/*      */             } catch (Exception ignored) {}
/*  767 */             if ((fms < 0.0F) || (fms > 1.0F))
/*  768 */               throw new ParseException("Minimum similarity for a FuzzyQuery has to be between 0.0f and 1.0f !");
/*      */             Query q;
/*  770 */             if (fms == this.fuzzyMinSim) {
/*  771 */               q = getFuzzyQuery(field, termImage);
/*      */             } else
/*  773 */               q = getFuzzyQuery(field, termImage, fms);
/*      */           } else {
/*  775 */             q = getFieldQuery(field, this.analyzer, termImage);
/*      */           } } }
/*  777 */       break;
/*      */     case 21: 
/*  779 */       jj_consume_token(21);
/*  780 */       Token goop1; Token goop1; switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk) {
/*      */       case 27: 
/*  782 */         goop1 = jj_consume_token(27);
/*  783 */         break;
/*      */       case 26: 
/*  785 */         goop1 = jj_consume_token(26);
/*  786 */         break;
/*      */       default: 
/*  788 */         this.jj_la1[11] = this.jj_gen;
/*  789 */         jj_consume_token(-1);
/*  790 */         throw new ParseException();
/*      */       }
/*  792 */       switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk) {
/*      */       case 24: 
/*  794 */         jj_consume_token(24);
/*  795 */         break;
/*      */       default: 
/*  797 */         this.jj_la1[12] = this.jj_gen; }
/*      */       Token goop2;
/*      */       Token goop2;
/*  800 */       switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk) {
/*      */       case 27: 
/*  802 */         goop2 = jj_consume_token(27);
/*  803 */         break;
/*      */       case 26: 
/*  805 */         goop2 = jj_consume_token(26);
/*  806 */         break;
/*      */       default: 
/*  808 */         this.jj_la1[13] = this.jj_gen;
/*  809 */         jj_consume_token(-1);
/*  810 */         throw new ParseException();
/*      */       }
/*  812 */       jj_consume_token(25);
/*  813 */       switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk) {
/*      */       case 15: 
/*  815 */         jj_consume_token(15);
/*  816 */         boost = jj_consume_token(23);
/*  817 */         break;
/*      */       default: 
/*  819 */         this.jj_la1[14] = this.jj_gen;
/*      */       }
/*      */       
/*  822 */       if (goop1.kind == 26) {
/*  823 */         goop1.image = goop1.image.substring(1, goop1.image.length() - 1);
/*      */       } else {
/*  825 */         goop1.image = discardEscapeChar(goop1.image);
/*      */       }
/*  827 */       if (goop2.kind == 26) {
/*  828 */         goop2.image = goop2.image.substring(1, goop2.image.length() - 1);
/*      */       } else {
/*  830 */         goop2.image = discardEscapeChar(goop2.image);
/*      */       }
/*  832 */       q = getRangeQuery(field, this.analyzer, goop1.image, goop2.image, true);
/*  833 */       break;
/*      */     case 22: 
/*  835 */       jj_consume_token(22);
/*  836 */       Token goop1; Token goop1; switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk) {
/*      */       case 31: 
/*  838 */         goop1 = jj_consume_token(31);
/*  839 */         break;
/*      */       case 30: 
/*  841 */         goop1 = jj_consume_token(30);
/*  842 */         break;
/*      */       default: 
/*  844 */         this.jj_la1[15] = this.jj_gen;
/*  845 */         jj_consume_token(-1);
/*  846 */         throw new ParseException();
/*      */       }
/*  848 */       switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk) {
/*      */       case 28: 
/*  850 */         jj_consume_token(28);
/*  851 */         break;
/*      */       default: 
/*  853 */         this.jj_la1[16] = this.jj_gen; }
/*      */       Token goop2;
/*      */       Token goop2;
/*  856 */       switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk) {
/*      */       case 31: 
/*  858 */         goop2 = jj_consume_token(31);
/*  859 */         break;
/*      */       case 30: 
/*  861 */         goop2 = jj_consume_token(30);
/*  862 */         break;
/*      */       default: 
/*  864 */         this.jj_la1[17] = this.jj_gen;
/*  865 */         jj_consume_token(-1);
/*  866 */         throw new ParseException();
/*      */       }
/*  868 */       jj_consume_token(29);
/*  869 */       switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk) {
/*      */       case 15: 
/*  871 */         jj_consume_token(15);
/*  872 */         boost = jj_consume_token(23);
/*  873 */         break;
/*      */       default: 
/*  875 */         this.jj_la1[18] = this.jj_gen;
/*      */       }
/*      */       
/*  878 */       if (goop1.kind == 30) {
/*  879 */         goop1.image = goop1.image.substring(1, goop1.image.length() - 1);
/*      */       } else {
/*  881 */         goop1.image = discardEscapeChar(goop1.image);
/*      */       }
/*  883 */       if (goop2.kind == 30) {
/*  884 */         goop2.image = goop2.image.substring(1, goop2.image.length() - 1);
/*      */       } else {
/*  886 */         goop2.image = discardEscapeChar(goop2.image);
/*      */       }
/*      */       
/*  889 */       q = getRangeQuery(field, this.analyzer, goop1.image, goop2.image, false);
/*  890 */       break;
/*      */     case 16: 
/*  892 */       Token term = jj_consume_token(16);
/*  893 */       switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk) {
/*      */       case 18: 
/*  895 */         fuzzySlop = jj_consume_token(18);
/*  896 */         break;
/*      */       default: 
/*  898 */         this.jj_la1[19] = this.jj_gen;
/*      */       }
/*      */       
/*  901 */       switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk) {
/*      */       case 15: 
/*  903 */         jj_consume_token(15);
/*  904 */         boost = jj_consume_token(23);
/*  905 */         break;
/*      */       default: 
/*  907 */         this.jj_la1[20] = this.jj_gen;
/*      */       }
/*      */       
/*  910 */       int s = this.phraseSlop;
/*      */       
/*  912 */       if (fuzzySlop != null) {
/*      */         try {
/*  914 */           s = Float.valueOf(fuzzySlop.image.substring(1)).intValue();
/*      */         }
/*      */         catch (Exception ignored) {}
/*      */       }
/*  918 */       q = getFieldQuery(field, this.analyzer, term.image.substring(1, term.image.length() - 1), s);
/*  919 */       break;
/*      */     case 18: default: 
/*  921 */       this.jj_la1[21] = this.jj_gen;
/*  922 */       jj_consume_token(-1);
/*  923 */       throw new ParseException();
/*      */     }
/*  925 */     if (boost != null) {
/*  926 */       float f = 1.0F;
/*      */       try {
/*  928 */         f = Float.valueOf(boost.image).floatValue();
/*      */       }
/*      */       catch (Exception ignored) {}
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  937 */       if (q != null) {
/*  938 */         q.setBoost(f);
/*      */       }
/*      */     }
/*  941 */     return q;
/*      */   }
/*      */   
/*      */   private final boolean jj_2_1(int xla)
/*      */   {
/*  946 */     this.jj_la = xla;this.jj_lastpos = (this.jj_scanpos = this.token);
/*  947 */     try { return !jj_3_1();
/*  948 */     } catch (LookaheadSuccess ls) { return true;
/*  949 */     } finally { jj_save(0, xla);
/*      */     }
/*      */   }
/*      */   
/*  953 */   private final boolean jj_3_1() { if (jj_scan_token(17)) return true;
/*  954 */     if (jj_scan_token(14)) return true;
/*  955 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  963 */   public boolean lookingAhead = false;
/*      */   private boolean jj_semLA;
/*      */   private int jj_gen;
/*  966 */   private final int[] jj_la1 = new int[22];
/*      */   
/*      */ 
/*      */   private static int[] jj_la1_0;
/*      */   
/*      */ 
/*  972 */   private static void jj_la1_0() { jj_la1_0 = new int[] { 384, 384, 3584, 3584, 16457600, 32768, 16453632, 10092544, 262144, 262144, 32768, 201326592, 16777216, 201326592, 32768, -1073741824, 268435456, -1073741824, 32768, 262144, 32768, 16449536 }; }
/*      */   
/*  974 */   private final JJCalls[] jj_2_rtns = new JJCalls[1];
/*  975 */   private boolean jj_rescan = false;
/*  976 */   private int jj_gc = 0;
/*      */   
/*      */   public QueryParser(CharStream stream) {
/*  979 */     this.token_source = new QueryParserTokenManager(stream);
/*  980 */     this.token = new Token();
/*  981 */     this.jj_ntk = -1;
/*  982 */     this.jj_gen = 0;
/*  983 */     for (int i = 0; i < 22; i++) this.jj_la1[i] = -1;
/*  984 */     for (int i = 0; i < this.jj_2_rtns.length; i++) this.jj_2_rtns[i] = new JJCalls();
/*      */   }
/*      */   
/*      */   public void ReInit(CharStream stream) {
/*  988 */     this.token_source.ReInit(stream);
/*  989 */     this.token = new Token();
/*  990 */     this.jj_ntk = -1;
/*  991 */     this.jj_gen = 0;
/*  992 */     for (int i = 0; i < 22; i++) this.jj_la1[i] = -1;
/*  993 */     for (int i = 0; i < this.jj_2_rtns.length; i++) this.jj_2_rtns[i] = new JJCalls();
/*      */   }
/*      */   
/*      */   public QueryParser(QueryParserTokenManager tm) {
/*  997 */     this.token_source = tm;
/*  998 */     this.token = new Token();
/*  999 */     this.jj_ntk = -1;
/* 1000 */     this.jj_gen = 0;
/* 1001 */     for (int i = 0; i < 22; i++) this.jj_la1[i] = -1;
/* 1002 */     for (int i = 0; i < this.jj_2_rtns.length; i++) this.jj_2_rtns[i] = new JJCalls();
/*      */   }
/*      */   
/*      */   public void ReInit(QueryParserTokenManager tm) {
/* 1006 */     this.token_source = tm;
/* 1007 */     this.token = new Token();
/* 1008 */     this.jj_ntk = -1;
/* 1009 */     this.jj_gen = 0;
/* 1010 */     for (int i = 0; i < 22; i++) this.jj_la1[i] = -1;
/* 1011 */     for (int i = 0; i < this.jj_2_rtns.length; i++) this.jj_2_rtns[i] = new JJCalls();
/*      */   }
/*      */   
/*      */   private final Token jj_consume_token(int kind) throws ParseException {
/*      */     Token oldToken;
/* 1016 */     if ((oldToken = this.token).next != null) this.token = this.token.next; else
/* 1017 */       this.token = (this.token.next = this.token_source.getNextToken());
/* 1018 */     this.jj_ntk = -1;
/* 1019 */     if (this.token.kind == kind) {
/* 1020 */       this.jj_gen += 1;
/* 1021 */       if (++this.jj_gc > 100) {
/* 1022 */         this.jj_gc = 0;
/* 1023 */         for (int i = 0; i < this.jj_2_rtns.length; i++) {
/* 1024 */           JJCalls c = this.jj_2_rtns[i];
/* 1025 */           while (c != null) {
/* 1026 */             if (c.gen < this.jj_gen) c.first = null;
/* 1027 */             c = c.next;
/*      */           }
/*      */         }
/*      */       }
/* 1031 */       return this.token;
/*      */     }
/* 1033 */     this.token = oldToken;
/* 1034 */     this.jj_kind = kind;
/* 1035 */     throw generateParseException(); }
/*      */   
/*      */   private static final class LookaheadSuccess extends Error { private LookaheadSuccess() {}
/* 1038 */     LookaheadSuccess(QueryParser.1 x0) { this(); } }
/* 1039 */   private final LookaheadSuccess jj_ls = new LookaheadSuccess(null);
/*      */   
/* 1041 */   private final boolean jj_scan_token(int kind) { if (this.jj_scanpos == this.jj_lastpos) {
/* 1042 */       this.jj_la -= 1;
/* 1043 */       if (this.jj_scanpos.next == null) {
/* 1044 */         this.jj_lastpos = (this.jj_scanpos = this.jj_scanpos.next = this.token_source.getNextToken());
/*      */       } else {
/* 1046 */         this.jj_lastpos = (this.jj_scanpos = this.jj_scanpos.next);
/*      */       }
/*      */     } else {
/* 1049 */       this.jj_scanpos = this.jj_scanpos.next;
/*      */     }
/* 1051 */     if (this.jj_rescan) {
/* 1052 */       int i = 0; for (Token tok = this.token; 
/* 1053 */           (tok != null) && (tok != this.jj_scanpos); tok = tok.next) i++;
/* 1054 */       if (tok != null) jj_add_error_token(kind, i);
/*      */     }
/* 1056 */     if (this.jj_scanpos.kind != kind) return true;
/* 1057 */     if ((this.jj_la == 0) && (this.jj_scanpos == this.jj_lastpos)) throw this.jj_ls;
/* 1058 */     return false;
/*      */   }
/*      */   
/*      */   public final Token getNextToken() {
/* 1062 */     if (this.token.next != null) this.token = this.token.next; else
/* 1063 */       this.token = (this.token.next = this.token_source.getNextToken());
/* 1064 */     this.jj_ntk = -1;
/* 1065 */     this.jj_gen += 1;
/* 1066 */     return this.token;
/*      */   }
/*      */   
/*      */   public final Token getToken(int index) {
/* 1070 */     Token t = this.lookingAhead ? this.jj_scanpos : this.token;
/* 1071 */     for (int i = 0; i < index; i++) {
/* 1072 */       if (t.next != null) t = t.next; else
/* 1073 */         t = t.next = this.token_source.getNextToken();
/*      */     }
/* 1075 */     return t;
/*      */   }
/*      */   
/*      */   private final int jj_ntk() {
/* 1079 */     if ((this.jj_nt = this.token.next) == null) {
/* 1080 */       return this.jj_ntk = (this.token.next = this.token_source.getNextToken()).kind;
/*      */     }
/* 1082 */     return this.jj_ntk = this.jj_nt.kind;
/*      */   }
/*      */   
/* 1085 */   private Vector jj_expentries = new Vector();
/*      */   private int[] jj_expentry;
/* 1087 */   private int jj_kind = -1;
/* 1088 */   private int[] jj_lasttokens = new int[100];
/*      */   private int jj_endpos;
/*      */   
/*      */   private void jj_add_error_token(int kind, int pos) {
/* 1092 */     if (pos >= 100) return;
/* 1093 */     if (pos == this.jj_endpos + 1) {
/* 1094 */       this.jj_lasttokens[(this.jj_endpos++)] = kind;
/* 1095 */     } else if (this.jj_endpos != 0) {
/* 1096 */       this.jj_expentry = new int[this.jj_endpos];
/* 1097 */       for (int i = 0; i < this.jj_endpos; i++) {
/* 1098 */         this.jj_expentry[i] = this.jj_lasttokens[i];
/*      */       }
/* 1100 */       boolean exists = false;
/* 1101 */       for (Enumeration e = this.jj_expentries.elements(); e.hasMoreElements();) {
/* 1102 */         int[] oldentry = (int[])e.nextElement();
/* 1103 */         if (oldentry.length == this.jj_expentry.length) {
/* 1104 */           exists = true;
/* 1105 */           for (int i = 0; i < this.jj_expentry.length; i++) {
/* 1106 */             if (oldentry[i] != this.jj_expentry[i]) {
/* 1107 */               exists = false;
/* 1108 */               break;
/*      */             }
/*      */           }
/* 1111 */           if (exists) break;
/*      */         }
/*      */       }
/* 1114 */       if (!exists) this.jj_expentries.addElement(this.jj_expentry);
/* 1115 */       if (pos != 0) this.jj_lasttokens[((this.jj_endpos = pos) - 1)] = kind;
/*      */     }
/*      */   }
/*      */   
/*      */   public ParseException generateParseException() {
/* 1120 */     this.jj_expentries.removeAllElements();
/* 1121 */     boolean[] la1tokens = new boolean[32];
/* 1122 */     for (int i = 0; i < 32; i++) {
/* 1123 */       la1tokens[i] = false;
/*      */     }
/* 1125 */     if (this.jj_kind >= 0) {
/* 1126 */       la1tokens[this.jj_kind] = true;
/* 1127 */       this.jj_kind = -1;
/*      */     }
/* 1129 */     for (int i = 0; i < 22; i++) {
/* 1130 */       if (this.jj_la1[i] == this.jj_gen) {
/* 1131 */         for (int j = 0; j < 32; j++) {
/* 1132 */           if ((jj_la1_0[i] & 1 << j) != 0) {
/* 1133 */             la1tokens[j] = true;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 1138 */     for (int i = 0; i < 32; i++) {
/* 1139 */       if (la1tokens[i] != 0) {
/* 1140 */         this.jj_expentry = new int[1];
/* 1141 */         this.jj_expentry[0] = i;
/* 1142 */         this.jj_expentries.addElement(this.jj_expentry);
/*      */       }
/*      */     }
/* 1145 */     this.jj_endpos = 0;
/* 1146 */     jj_rescan_token();
/* 1147 */     jj_add_error_token(0, 0);
/* 1148 */     int[][] exptokseq = new int[this.jj_expentries.size()][];
/* 1149 */     for (int i = 0; i < this.jj_expentries.size(); i++) {
/* 1150 */       exptokseq[i] = ((int[])this.jj_expentries.elementAt(i));
/*      */     }
/* 1152 */     return new ParseException(this.token, exptokseq, QueryParserConstants.tokenImage);
/*      */   }
/*      */   
/*      */ 
/*      */   public final void enable_tracing() {}
/*      */   
/*      */   public final void disable_tracing() {}
/*      */   
/*      */   private final void jj_rescan_token()
/*      */   {
/* 1162 */     this.jj_rescan = true;
/* 1163 */     for (int i = 0; i < 1; i++) {
/* 1164 */       JJCalls p = this.jj_2_rtns[i];
/*      */       do {
/* 1166 */         if (p.gen > this.jj_gen) {
/* 1167 */           this.jj_la = p.arg;this.jj_lastpos = (this.jj_scanpos = p.first);
/* 1168 */           switch (i) {
/* 1169 */           case 0:  jj_3_1();
/*      */           }
/*      */         }
/* 1172 */         p = p.next;
/* 1173 */       } while (p != null);
/*      */     }
/* 1175 */     this.jj_rescan = false;
/*      */   }
/*      */   
/*      */   private final void jj_save(int index, int xla) {
/* 1179 */     JJCalls p = this.jj_2_rtns[index];
/* 1180 */     while (p.gen > this.jj_gen) {
/* 1181 */       if (p.next == null) { p = p.next = new JJCalls(); break; }
/* 1182 */       p = p.next;
/*      */     }
/* 1184 */     p.gen = (this.jj_gen + xla - this.jj_la);p.first = this.token;p.arg = xla;
/*      */   }
/*      */   
/*      */   static {}
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/queryParser/QueryParser.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */