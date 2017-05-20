/*    */ package org.apache.lucene.queryParser;
/*    */ 
/*    */ 
/*    */ public abstract interface QueryParserConstants
/*    */ {
/*    */   public static final int EOF = 0;
/*    */   
/*    */   public static final int _NUM_CHAR = 1;
/*    */   
/*    */   public static final int _ESCAPED_CHAR = 2;
/*    */   public static final int _TERM_START_CHAR = 3;
/*    */   public static final int _TERM_CHAR = 4;
/*    */   public static final int _WHITESPACE = 5;
/*    */   public static final int AND = 7;
/*    */   public static final int OR = 8;
/*    */   public static final int NOT = 9;
/*    */   public static final int PLUS = 10;
/*    */   public static final int MINUS = 11;
/*    */   public static final int LPAREN = 12;
/*    */   public static final int RPAREN = 13;
/*    */   public static final int COLON = 14;
/*    */   public static final int CARAT = 15;
/*    */   public static final int QUOTED = 16;
/*    */   public static final int TERM = 17;
/*    */   public static final int FUZZY_SLOP = 18;
/*    */   public static final int PREFIXTERM = 19;
/*    */   public static final int WILDTERM = 20;
/*    */   public static final int RANGEIN_START = 21;
/*    */   public static final int RANGEEX_START = 22;
/*    */   public static final int NUMBER = 23;
/*    */   public static final int RANGEIN_TO = 24;
/*    */   public static final int RANGEIN_END = 25;
/*    */   public static final int RANGEIN_QUOTED = 26;
/*    */   public static final int RANGEIN_GOOP = 27;
/*    */   public static final int RANGEEX_TO = 28;
/*    */   public static final int RANGEEX_END = 29;
/*    */   public static final int RANGEEX_QUOTED = 30;
/*    */   public static final int RANGEEX_GOOP = 31;
/*    */   public static final int Boost = 0;
/*    */   public static final int RangeEx = 1;
/*    */   public static final int RangeIn = 2;
/*    */   public static final int DEFAULT = 3;
/* 43 */   public static final String[] tokenImage = { "<EOF>", "<_NUM_CHAR>", "<_ESCAPED_CHAR>", "<_TERM_START_CHAR>", "<_TERM_CHAR>", "<_WHITESPACE>", "<token of kind 6>", "<AND>", "<OR>", "<NOT>", "\"+\"", "\"-\"", "\"(\"", "\")\"", "\":\"", "\"^\"", "<QUOTED>", "<TERM>", "<FUZZY_SLOP>", "<PREFIXTERM>", "<WILDTERM>", "\"[\"", "\"{\"", "<NUMBER>", "\"TO\"", "\"]\"", "<RANGEIN_QUOTED>", "<RANGEIN_GOOP>", "\"TO\"", "\"}\"", "<RANGEEX_QUOTED>", "<RANGEEX_GOOP>" };
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/queryParser/QueryParserConstants.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */