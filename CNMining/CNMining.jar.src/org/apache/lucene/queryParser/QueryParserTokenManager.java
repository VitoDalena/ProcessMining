/*      */ package org.apache.lucene.queryParser;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class QueryParserTokenManager
/*      */   implements QueryParserConstants
/*      */ {
/*   14 */   public PrintStream debugStream = System.out;
/*   15 */   public void setDebugStream(PrintStream ds) { this.debugStream = ds; }
/*      */   
/*      */   private final int jjStopStringLiteralDfa_3(int pos, long active0) {
/*   18 */     switch (pos)
/*      */     {
/*      */     }
/*   21 */     return -1;
/*      */   }
/*      */   
/*      */   private final int jjStartNfa_3(int pos, long active0)
/*      */   {
/*   26 */     return jjMoveNfa_3(jjStopStringLiteralDfa_3(pos, active0), pos + 1);
/*      */   }
/*      */   
/*      */   private final int jjStopAtPos(int pos, int kind) {
/*   30 */     this.jjmatchedKind = kind;
/*   31 */     this.jjmatchedPos = pos;
/*   32 */     return pos + 1;
/*      */   }
/*      */   
/*      */   private final int jjStartNfaWithStates_3(int pos, int kind, int state) {
/*   36 */     this.jjmatchedKind = kind;
/*   37 */     this.jjmatchedPos = pos;
/*   38 */     try { this.curChar = this.input_stream.readChar();
/*   39 */     } catch (IOException e) { return pos + 1; }
/*   40 */     return jjMoveNfa_3(state, pos + 1);
/*      */   }
/*      */   
/*      */   private final int jjMoveStringLiteralDfa0_3() {
/*   44 */     switch (this.curChar)
/*      */     {
/*      */     case '(': 
/*   47 */       return jjStopAtPos(0, 12);
/*      */     case ')': 
/*   49 */       return jjStopAtPos(0, 13);
/*      */     case '+': 
/*   51 */       return jjStopAtPos(0, 10);
/*      */     case '-': 
/*   53 */       return jjStopAtPos(0, 11);
/*      */     case ':': 
/*   55 */       return jjStopAtPos(0, 14);
/*      */     case '[': 
/*   57 */       return jjStopAtPos(0, 21);
/*      */     case '^': 
/*   59 */       return jjStopAtPos(0, 15);
/*      */     case '{': 
/*   61 */       return jjStopAtPos(0, 22);
/*      */     }
/*   63 */     return jjMoveNfa_3(0, 0);
/*      */   }
/*      */   
/*      */   private final void jjCheckNAdd(int state)
/*      */   {
/*   68 */     if (this.jjrounds[state] != this.jjround)
/*      */     {
/*   70 */       this.jjstateSet[(this.jjnewStateCnt++)] = state;
/*   71 */       this.jjrounds[state] = this.jjround;
/*      */     }
/*      */   }
/*      */   
/*      */   private final void jjAddStates(int start, int end) {
/*      */     do {
/*   77 */       this.jjstateSet[(this.jjnewStateCnt++)] = jjnextStates[start];
/*   78 */     } while (start++ != end);
/*      */   }
/*      */   
/*      */   private final void jjCheckNAddTwoStates(int state1, int state2) {
/*   82 */     jjCheckNAdd(state1);
/*   83 */     jjCheckNAdd(state2);
/*      */   }
/*      */   
/*      */   private final void jjCheckNAddStates(int start, int end) {
/*      */     do {
/*   88 */       jjCheckNAdd(jjnextStates[start]);
/*   89 */     } while (start++ != end);
/*      */   }
/*      */   
/*      */   private final void jjCheckNAddStates(int start) {
/*   93 */     jjCheckNAdd(jjnextStates[start]);
/*   94 */     jjCheckNAdd(jjnextStates[(start + 1)]); }
/*      */   
/*   96 */   static final long[] jjbitVec0 = { -2L, -1L, -1L, -1L };
/*      */   
/*      */ 
/*   99 */   static final long[] jjbitVec2 = { 0L, 0L, -1L, -1L };
/*      */   
/*      */ 
/*      */ 
/*      */   private final int jjMoveNfa_3(int startState, int curPos)
/*      */   {
/*  105 */     int startsAt = 0;
/*  106 */     this.jjnewStateCnt = 33;
/*  107 */     int i = 1;
/*  108 */     this.jjstateSet[0] = startState;
/*  109 */     int kind = Integer.MAX_VALUE;
/*      */     for (;;)
/*      */     {
/*  112 */       if (++this.jjround == Integer.MAX_VALUE)
/*  113 */         ReInitRounds();
/*  114 */       if (this.curChar < '@')
/*      */       {
/*  116 */         long l = 1L << this.curChar;
/*      */         do
/*      */         {
/*  119 */           switch (this.jjstateSet[(--i)])
/*      */           {
/*      */           case 0: 
/*  122 */             if ((0x7BFFD0F8FFFFD9FF & l) != 0L)
/*      */             {
/*  124 */               if (kind > 17)
/*  125 */                 kind = 17;
/*  126 */               jjCheckNAddStates(0, 6);
/*      */             }
/*  128 */             else if ((0x100002600 & l) != 0L)
/*      */             {
/*  130 */               if (kind > 6) {
/*  131 */                 kind = 6;
/*      */               }
/*  133 */             } else if (this.curChar == '"') {
/*  134 */               jjCheckNAdd(15);
/*  135 */             } else if (this.curChar == '!')
/*      */             {
/*  137 */               if (kind > 9)
/*  138 */                 kind = 9;
/*      */             }
/*  140 */             if (this.curChar == '&')
/*  141 */               this.jjstateSet[(this.jjnewStateCnt++)] = 4;
/*      */             break;
/*      */           case 4: 
/*  144 */             if ((this.curChar == '&') && (kind > 7))
/*  145 */               kind = 7;
/*      */             break;
/*      */           case 5: 
/*  148 */             if (this.curChar == '&')
/*  149 */               this.jjstateSet[(this.jjnewStateCnt++)] = 4;
/*      */             break;
/*      */           case 13: 
/*  152 */             if ((this.curChar == '!') && (kind > 9))
/*  153 */               kind = 9;
/*      */             break;
/*      */           case 14: 
/*  156 */             if (this.curChar == '"')
/*  157 */               jjCheckNAdd(15);
/*      */             break;
/*      */           case 15: 
/*  160 */             if ((0xFFFFFFFBFFFFFFFF & l) != 0L)
/*  161 */               jjCheckNAddTwoStates(15, 16);
/*      */             break;
/*      */           case 16: 
/*  164 */             if ((this.curChar == '"') && (kind > 16))
/*  165 */               kind = 16;
/*      */             break;
/*      */           case 18: 
/*  168 */             if ((0x3FF000000000000 & l) != 0L)
/*      */             {
/*  170 */               if (kind > 18)
/*  171 */                 kind = 18;
/*  172 */               jjAddStates(7, 8); }
/*  173 */             break;
/*      */           case 19: 
/*  175 */             if (this.curChar == '.')
/*  176 */               jjCheckNAdd(20);
/*      */             break;
/*      */           case 20: 
/*  179 */             if ((0x3FF000000000000 & l) != 0L)
/*      */             {
/*  181 */               if (kind > 18)
/*  182 */                 kind = 18;
/*  183 */               jjCheckNAdd(20); }
/*  184 */             break;
/*      */           case 21: 
/*  186 */             if ((0x7BFFD0F8FFFFD9FF & l) != 0L)
/*      */             {
/*  188 */               if (kind > 17)
/*  189 */                 kind = 17;
/*  190 */               jjCheckNAddStates(0, 6); }
/*  191 */             break;
/*      */           case 22: 
/*  193 */             if ((0x7BFFF8F8FFFFD9FF & l) != 0L)
/*      */             {
/*  195 */               if (kind > 17)
/*  196 */                 kind = 17;
/*  197 */               jjCheckNAddTwoStates(22, 23); }
/*  198 */             break;
/*      */           case 24: 
/*  200 */             if ((0x84002F0600000000 & l) != 0L)
/*      */             {
/*  202 */               if (kind > 17)
/*  203 */                 kind = 17;
/*  204 */               jjCheckNAddTwoStates(22, 23); }
/*  205 */             break;
/*      */           case 25: 
/*  207 */             if ((0x7BFFF8F8FFFFD9FF & l) != 0L)
/*  208 */               jjCheckNAddStates(9, 11);
/*      */             break;
/*      */           case 26: 
/*  211 */             if ((this.curChar == '*') && (kind > 19))
/*  212 */               kind = 19;
/*      */             break;
/*      */           case 28: 
/*  215 */             if ((0x84002F0600000000 & l) != 0L)
/*  216 */               jjCheckNAddStates(9, 11);
/*      */             break;
/*      */           case 29: 
/*  219 */             if ((0xFBFFFCF8FFFFD9FF & l) != 0L)
/*      */             {
/*  221 */               if (kind > 20)
/*  222 */                 kind = 20;
/*  223 */               jjCheckNAddTwoStates(29, 30); }
/*  224 */             break;
/*      */           case 31: 
/*  226 */             if ((0x84002F0600000000 & l) != 0L)
/*      */             {
/*  228 */               if (kind > 20)
/*  229 */                 kind = 20;
/*  230 */               jjCheckNAddTwoStates(29, 30);
/*      */             }
/*      */             break;
/*      */           }
/*  234 */         } while (i != startsAt);
/*      */       }
/*  236 */       else if (this.curChar < '')
/*      */       {
/*  238 */         long l = 1L << (this.curChar & 0x3F);
/*      */         do
/*      */         {
/*  241 */           switch (this.jjstateSet[(--i)])
/*      */           {
/*      */           case 0: 
/*  244 */             if ((0x97FFFFFF97FFFFFF & l) != 0L)
/*      */             {
/*  246 */               if (kind > 17)
/*  247 */                 kind = 17;
/*  248 */               jjCheckNAddStates(0, 6);
/*      */             }
/*  250 */             else if (this.curChar == '~')
/*      */             {
/*  252 */               if (kind > 18)
/*  253 */                 kind = 18;
/*  254 */               this.jjstateSet[(this.jjnewStateCnt++)] = 18;
/*      */             }
/*  256 */             if (this.curChar == '\\') {
/*  257 */               jjCheckNAddStates(12, 14);
/*  258 */             } else if (this.curChar == 'N') {
/*  259 */               this.jjstateSet[(this.jjnewStateCnt++)] = 11;
/*  260 */             } else if (this.curChar == '|') {
/*  261 */               this.jjstateSet[(this.jjnewStateCnt++)] = 8;
/*  262 */             } else if (this.curChar == 'O') {
/*  263 */               this.jjstateSet[(this.jjnewStateCnt++)] = 6;
/*  264 */             } else if (this.curChar == 'A')
/*  265 */               this.jjstateSet[(this.jjnewStateCnt++)] = 2;
/*      */             break;
/*      */           case 1: 
/*  268 */             if ((this.curChar == 'D') && (kind > 7))
/*  269 */               kind = 7;
/*      */             break;
/*      */           case 2: 
/*  272 */             if (this.curChar == 'N')
/*  273 */               this.jjstateSet[(this.jjnewStateCnt++)] = 1;
/*      */             break;
/*      */           case 3: 
/*  276 */             if (this.curChar == 'A')
/*  277 */               this.jjstateSet[(this.jjnewStateCnt++)] = 2;
/*      */             break;
/*      */           case 6: 
/*  280 */             if ((this.curChar == 'R') && (kind > 8))
/*  281 */               kind = 8;
/*      */             break;
/*      */           case 7: 
/*  284 */             if (this.curChar == 'O')
/*  285 */               this.jjstateSet[(this.jjnewStateCnt++)] = 6;
/*      */             break;
/*      */           case 8: 
/*  288 */             if ((this.curChar == '|') && (kind > 8))
/*  289 */               kind = 8;
/*      */             break;
/*      */           case 9: 
/*  292 */             if (this.curChar == '|')
/*  293 */               this.jjstateSet[(this.jjnewStateCnt++)] = 8;
/*      */             break;
/*      */           case 10: 
/*  296 */             if ((this.curChar == 'T') && (kind > 9))
/*  297 */               kind = 9;
/*      */             break;
/*      */           case 11: 
/*  300 */             if (this.curChar == 'O')
/*  301 */               this.jjstateSet[(this.jjnewStateCnt++)] = 10;
/*      */             break;
/*      */           case 12: 
/*  304 */             if (this.curChar == 'N')
/*  305 */               this.jjstateSet[(this.jjnewStateCnt++)] = 11;
/*      */             break;
/*      */           case 15: 
/*  308 */             jjAddStates(15, 16);
/*  309 */             break;
/*      */           case 17: 
/*  311 */             if (this.curChar == '~')
/*      */             {
/*  313 */               if (kind > 18)
/*  314 */                 kind = 18;
/*  315 */               this.jjstateSet[(this.jjnewStateCnt++)] = 18; }
/*  316 */             break;
/*      */           case 21: 
/*  318 */             if ((0x97FFFFFF97FFFFFF & l) != 0L)
/*      */             {
/*  320 */               if (kind > 17)
/*  321 */                 kind = 17;
/*  322 */               jjCheckNAddStates(0, 6); }
/*  323 */             break;
/*      */           case 22: 
/*  325 */             if ((0x97FFFFFF97FFFFFF & l) != 0L)
/*      */             {
/*  327 */               if (kind > 17)
/*  328 */                 kind = 17;
/*  329 */               jjCheckNAddTwoStates(22, 23); }
/*  330 */             break;
/*      */           case 23: 
/*  332 */             if (this.curChar == '\\')
/*  333 */               jjCheckNAddTwoStates(24, 24);
/*      */             break;
/*      */           case 24: 
/*  336 */             if ((0x6800000078000000 & l) != 0L)
/*      */             {
/*  338 */               if (kind > 17)
/*  339 */                 kind = 17;
/*  340 */               jjCheckNAddTwoStates(22, 23); }
/*  341 */             break;
/*      */           case 25: 
/*  343 */             if ((0x97FFFFFF97FFFFFF & l) != 0L)
/*  344 */               jjCheckNAddStates(9, 11);
/*      */             break;
/*      */           case 27: 
/*  347 */             if (this.curChar == '\\')
/*  348 */               jjCheckNAddTwoStates(28, 28);
/*      */             break;
/*      */           case 28: 
/*  351 */             if ((0x6800000078000000 & l) != 0L)
/*  352 */               jjCheckNAddStates(9, 11);
/*      */             break;
/*      */           case 29: 
/*  355 */             if ((0x97FFFFFF97FFFFFF & l) != 0L)
/*      */             {
/*  357 */               if (kind > 20)
/*  358 */                 kind = 20;
/*  359 */               jjCheckNAddTwoStates(29, 30); }
/*  360 */             break;
/*      */           case 30: 
/*  362 */             if (this.curChar == '\\')
/*  363 */               jjCheckNAddTwoStates(31, 31);
/*      */             break;
/*      */           case 31: 
/*  366 */             if ((0x6800000078000000 & l) != 0L)
/*      */             {
/*  368 */               if (kind > 20)
/*  369 */                 kind = 20;
/*  370 */               jjCheckNAddTwoStates(29, 30); }
/*  371 */             break;
/*      */           case 32: 
/*  373 */             if (this.curChar == '\\') {
/*  374 */               jjCheckNAddStates(12, 14);
/*      */             }
/*      */             break;
/*      */           }
/*  378 */         } while (i != startsAt);
/*      */       }
/*      */       else
/*      */       {
/*  382 */         int hiByte = this.curChar >> '\b';
/*  383 */         int i1 = hiByte >> 6;
/*  384 */         long l1 = 1L << (hiByte & 0x3F);
/*  385 */         int i2 = (this.curChar & 0xFF) >> '\006';
/*  386 */         long l2 = 1L << (this.curChar & 0x3F);
/*      */         do
/*      */         {
/*  389 */           switch (this.jjstateSet[(--i)])
/*      */           {
/*      */           case 0: 
/*  392 */             if (jjCanMove_0(hiByte, i1, i2, l1, l2))
/*      */             {
/*  394 */               if (kind > 17)
/*  395 */                 kind = 17;
/*  396 */               jjCheckNAddStates(0, 6); }
/*  397 */             break;
/*      */           case 15: 
/*  399 */             if (jjCanMove_0(hiByte, i1, i2, l1, l2))
/*  400 */               jjAddStates(15, 16);
/*      */             break;
/*      */           case 22: 
/*  403 */             if (jjCanMove_0(hiByte, i1, i2, l1, l2))
/*      */             {
/*  405 */               if (kind > 17)
/*  406 */                 kind = 17;
/*  407 */               jjCheckNAddTwoStates(22, 23); }
/*  408 */             break;
/*      */           case 25: 
/*  410 */             if (jjCanMove_0(hiByte, i1, i2, l1, l2))
/*  411 */               jjCheckNAddStates(9, 11);
/*      */             break;
/*      */           case 29: 
/*  414 */             if (jjCanMove_0(hiByte, i1, i2, l1, l2))
/*      */             {
/*  416 */               if (kind > 20)
/*  417 */                 kind = 20;
/*  418 */               jjCheckNAddTwoStates(29, 30);
/*      */             }
/*      */             break;
/*      */           }
/*  422 */         } while (i != startsAt);
/*      */       }
/*  424 */       if (kind != Integer.MAX_VALUE)
/*      */       {
/*  426 */         this.jjmatchedKind = kind;
/*  427 */         this.jjmatchedPos = curPos;
/*  428 */         kind = Integer.MAX_VALUE;
/*      */       }
/*  430 */       curPos++;
/*  431 */       if ((i = this.jjnewStateCnt) == (startsAt = 33 - (this.jjnewStateCnt = startsAt)))
/*  432 */         return curPos;
/*  433 */       try { this.curChar = this.input_stream.readChar(); } catch (IOException e) {} }
/*  434 */     return curPos;
/*      */   }
/*      */   
/*      */   private final int jjStopStringLiteralDfa_1(int pos, long active0)
/*      */   {
/*  439 */     switch (pos)
/*      */     {
/*      */     case 0: 
/*  442 */       if ((active0 & 0x10000000) != 0L)
/*      */       {
/*  444 */         this.jjmatchedKind = 31;
/*  445 */         return 4;
/*      */       }
/*  447 */       return -1;
/*      */     }
/*  449 */     return -1;
/*      */   }
/*      */   
/*      */   private final int jjStartNfa_1(int pos, long active0)
/*      */   {
/*  454 */     return jjMoveNfa_1(jjStopStringLiteralDfa_1(pos, active0), pos + 1);
/*      */   }
/*      */   
/*      */   private final int jjStartNfaWithStates_1(int pos, int kind, int state) {
/*  458 */     this.jjmatchedKind = kind;
/*  459 */     this.jjmatchedPos = pos;
/*  460 */     try { this.curChar = this.input_stream.readChar();
/*  461 */     } catch (IOException e) { return pos + 1; }
/*  462 */     return jjMoveNfa_1(state, pos + 1);
/*      */   }
/*      */   
/*      */   private final int jjMoveStringLiteralDfa0_1() {
/*  466 */     switch (this.curChar)
/*      */     {
/*      */     case 'T': 
/*  469 */       return jjMoveStringLiteralDfa1_1(268435456L);
/*      */     case '}': 
/*  471 */       return jjStopAtPos(0, 29);
/*      */     }
/*  473 */     return jjMoveNfa_1(0, 0);
/*      */   }
/*      */   
/*      */   private final int jjMoveStringLiteralDfa1_1(long active0) {
/*      */     try {
/*  478 */       this.curChar = this.input_stream.readChar();
/*      */     } catch (IOException e) {
/*  480 */       jjStopStringLiteralDfa_1(0, active0);
/*  481 */       return 1;
/*      */     }
/*  483 */     switch (this.curChar)
/*      */     {
/*      */     case 'O': 
/*  486 */       if ((active0 & 0x10000000) != 0L) {
/*  487 */         return jjStartNfaWithStates_1(1, 28, 4);
/*      */       }
/*      */       break;
/*      */     }
/*      */     
/*  492 */     return jjStartNfa_1(0, active0);
/*      */   }
/*      */   
/*      */   private final int jjMoveNfa_1(int startState, int curPos)
/*      */   {
/*  497 */     int startsAt = 0;
/*  498 */     this.jjnewStateCnt = 5;
/*  499 */     int i = 1;
/*  500 */     this.jjstateSet[0] = startState;
/*  501 */     int kind = Integer.MAX_VALUE;
/*      */     for (;;)
/*      */     {
/*  504 */       if (++this.jjround == Integer.MAX_VALUE)
/*  505 */         ReInitRounds();
/*  506 */       if (this.curChar < '@')
/*      */       {
/*  508 */         long l = 1L << this.curChar;
/*      */         do
/*      */         {
/*  511 */           switch (this.jjstateSet[(--i)])
/*      */           {
/*      */           case 0: 
/*  514 */             if ((0xFFFFFFFEFFFFFFFF & l) != 0L)
/*      */             {
/*  516 */               if (kind > 31)
/*  517 */                 kind = 31;
/*  518 */               jjCheckNAdd(4);
/*      */             }
/*  520 */             if ((0x100002600 & l) != 0L)
/*      */             {
/*  522 */               if (kind > 6) {
/*  523 */                 kind = 6;
/*      */               }
/*  525 */             } else if (this.curChar == '"')
/*  526 */               jjCheckNAdd(2);
/*      */             break;
/*      */           case 1: 
/*  529 */             if (this.curChar == '"')
/*  530 */               jjCheckNAdd(2);
/*      */             break;
/*      */           case 2: 
/*  533 */             if ((0xFFFFFFFBFFFFFFFF & l) != 0L)
/*  534 */               jjCheckNAddTwoStates(2, 3);
/*      */             break;
/*      */           case 3: 
/*  537 */             if ((this.curChar == '"') && (kind > 30))
/*  538 */               kind = 30;
/*      */             break;
/*      */           case 4: 
/*  541 */             if ((0xFFFFFFFEFFFFFFFF & l) != 0L)
/*      */             {
/*  543 */               if (kind > 31)
/*  544 */                 kind = 31;
/*  545 */               jjCheckNAdd(4);
/*      */             }
/*      */             break;
/*      */           }
/*  549 */         } while (i != startsAt);
/*      */       }
/*  551 */       else if (this.curChar < '')
/*      */       {
/*  553 */         long l = 1L << (this.curChar & 0x3F);
/*      */         do
/*      */         {
/*  556 */           switch (this.jjstateSet[(--i)])
/*      */           {
/*      */           case 0: 
/*      */           case 4: 
/*  560 */             if ((0xDFFFFFFFFFFFFFFF & l) != 0L)
/*      */             {
/*  562 */               if (kind > 31)
/*  563 */                 kind = 31;
/*  564 */               jjCheckNAdd(4); }
/*  565 */             break;
/*      */           case 2: 
/*  567 */             jjAddStates(17, 18);
/*      */           
/*      */           }
/*      */           
/*  571 */         } while (i != startsAt);
/*      */       }
/*      */       else
/*      */       {
/*  575 */         int hiByte = this.curChar >> '\b';
/*  576 */         int i1 = hiByte >> 6;
/*  577 */         long l1 = 1L << (hiByte & 0x3F);
/*  578 */         int i2 = (this.curChar & 0xFF) >> '\006';
/*  579 */         long l2 = 1L << (this.curChar & 0x3F);
/*      */         do
/*      */         {
/*  582 */           switch (this.jjstateSet[(--i)])
/*      */           {
/*      */           case 0: 
/*      */           case 4: 
/*  586 */             if (jjCanMove_0(hiByte, i1, i2, l1, l2))
/*      */             {
/*  588 */               if (kind > 31)
/*  589 */                 kind = 31;
/*  590 */               jjCheckNAdd(4); }
/*  591 */             break;
/*      */           case 2: 
/*  593 */             if (jjCanMove_0(hiByte, i1, i2, l1, l2)) {
/*  594 */               jjAddStates(17, 18);
/*      */             }
/*      */             break;
/*      */           }
/*  598 */         } while (i != startsAt);
/*      */       }
/*  600 */       if (kind != Integer.MAX_VALUE)
/*      */       {
/*  602 */         this.jjmatchedKind = kind;
/*  603 */         this.jjmatchedPos = curPos;
/*  604 */         kind = Integer.MAX_VALUE;
/*      */       }
/*  606 */       curPos++;
/*  607 */       if ((i = this.jjnewStateCnt) == (startsAt = 5 - (this.jjnewStateCnt = startsAt)))
/*  608 */         return curPos;
/*  609 */       try { this.curChar = this.input_stream.readChar(); } catch (IOException e) {} }
/*  610 */     return curPos;
/*      */   }
/*      */   
/*      */   private final int jjMoveStringLiteralDfa0_0()
/*      */   {
/*  615 */     return jjMoveNfa_0(0, 0);
/*      */   }
/*      */   
/*      */   private final int jjMoveNfa_0(int startState, int curPos)
/*      */   {
/*  620 */     int startsAt = 0;
/*  621 */     this.jjnewStateCnt = 3;
/*  622 */     int i = 1;
/*  623 */     this.jjstateSet[0] = startState;
/*  624 */     int kind = Integer.MAX_VALUE;
/*      */     for (;;)
/*      */     {
/*  627 */       if (++this.jjround == Integer.MAX_VALUE)
/*  628 */         ReInitRounds();
/*  629 */       if (this.curChar < '@')
/*      */       {
/*  631 */         long l = 1L << this.curChar;
/*      */         do
/*      */         {
/*  634 */           switch (this.jjstateSet[(--i)])
/*      */           {
/*      */           case 0: 
/*  637 */             if ((0x3FF000000000000 & l) != 0L)
/*      */             {
/*  639 */               if (kind > 23)
/*  640 */                 kind = 23;
/*  641 */               jjAddStates(19, 20); }
/*  642 */             break;
/*      */           case 1: 
/*  644 */             if (this.curChar == '.')
/*  645 */               jjCheckNAdd(2);
/*      */             break;
/*      */           case 2: 
/*  648 */             if ((0x3FF000000000000 & l) != 0L)
/*      */             {
/*  650 */               if (kind > 23)
/*  651 */                 kind = 23;
/*  652 */               jjCheckNAdd(2);
/*      */             }
/*      */             break;
/*      */           }
/*  656 */         } while (i != startsAt);
/*      */       }
/*  658 */       else if (this.curChar < '')
/*      */       {
/*  660 */         long l = 1L << (this.curChar & 0x3F);
/*      */         do
/*      */         {
/*  663 */           switch (this.jjstateSet[(--i)])
/*      */           {
/*      */           }
/*      */           
/*  667 */         } while (i != startsAt);
/*      */       }
/*      */       else
/*      */       {
/*  671 */         int hiByte = this.curChar >> '\b';
/*  672 */         int i1 = hiByte >> 6;
/*  673 */         long l1 = 1L << (hiByte & 0x3F);
/*  674 */         int i2 = (this.curChar & 0xFF) >> '\006';
/*  675 */         long l2 = 1L << (this.curChar & 0x3F);
/*      */         do
/*      */         {
/*  678 */           switch (this.jjstateSet[(--i)])
/*      */           {
/*      */           }
/*      */           
/*  682 */         } while (i != startsAt);
/*      */       }
/*  684 */       if (kind != Integer.MAX_VALUE)
/*      */       {
/*  686 */         this.jjmatchedKind = kind;
/*  687 */         this.jjmatchedPos = curPos;
/*  688 */         kind = Integer.MAX_VALUE;
/*      */       }
/*  690 */       curPos++;
/*  691 */       if ((i = this.jjnewStateCnt) == (startsAt = 3 - (this.jjnewStateCnt = startsAt)))
/*  692 */         return curPos;
/*  693 */       try { this.curChar = this.input_stream.readChar(); } catch (IOException e) {} }
/*  694 */     return curPos;
/*      */   }
/*      */   
/*      */   private final int jjStopStringLiteralDfa_2(int pos, long active0)
/*      */   {
/*  699 */     switch (pos)
/*      */     {
/*      */     case 0: 
/*  702 */       if ((active0 & 0x1000000) != 0L)
/*      */       {
/*  704 */         this.jjmatchedKind = 27;
/*  705 */         return 4;
/*      */       }
/*  707 */       return -1;
/*      */     }
/*  709 */     return -1;
/*      */   }
/*      */   
/*      */   private final int jjStartNfa_2(int pos, long active0)
/*      */   {
/*  714 */     return jjMoveNfa_2(jjStopStringLiteralDfa_2(pos, active0), pos + 1);
/*      */   }
/*      */   
/*      */   private final int jjStartNfaWithStates_2(int pos, int kind, int state) {
/*  718 */     this.jjmatchedKind = kind;
/*  719 */     this.jjmatchedPos = pos;
/*  720 */     try { this.curChar = this.input_stream.readChar();
/*  721 */     } catch (IOException e) { return pos + 1; }
/*  722 */     return jjMoveNfa_2(state, pos + 1);
/*      */   }
/*      */   
/*      */   private final int jjMoveStringLiteralDfa0_2() {
/*  726 */     switch (this.curChar)
/*      */     {
/*      */     case 'T': 
/*  729 */       return jjMoveStringLiteralDfa1_2(16777216L);
/*      */     case ']': 
/*  731 */       return jjStopAtPos(0, 25);
/*      */     }
/*  733 */     return jjMoveNfa_2(0, 0);
/*      */   }
/*      */   
/*      */   private final int jjMoveStringLiteralDfa1_2(long active0) {
/*      */     try {
/*  738 */       this.curChar = this.input_stream.readChar();
/*      */     } catch (IOException e) {
/*  740 */       jjStopStringLiteralDfa_2(0, active0);
/*  741 */       return 1;
/*      */     }
/*  743 */     switch (this.curChar)
/*      */     {
/*      */     case 'O': 
/*  746 */       if ((active0 & 0x1000000) != 0L) {
/*  747 */         return jjStartNfaWithStates_2(1, 24, 4);
/*      */       }
/*      */       break;
/*      */     }
/*      */     
/*  752 */     return jjStartNfa_2(0, active0);
/*      */   }
/*      */   
/*      */   private final int jjMoveNfa_2(int startState, int curPos)
/*      */   {
/*  757 */     int startsAt = 0;
/*  758 */     this.jjnewStateCnt = 5;
/*  759 */     int i = 1;
/*  760 */     this.jjstateSet[0] = startState;
/*  761 */     int kind = Integer.MAX_VALUE;
/*      */     for (;;)
/*      */     {
/*  764 */       if (++this.jjround == Integer.MAX_VALUE)
/*  765 */         ReInitRounds();
/*  766 */       if (this.curChar < '@')
/*      */       {
/*  768 */         long l = 1L << this.curChar;
/*      */         do
/*      */         {
/*  771 */           switch (this.jjstateSet[(--i)])
/*      */           {
/*      */           case 0: 
/*  774 */             if ((0xFFFFFFFEFFFFFFFF & l) != 0L)
/*      */             {
/*  776 */               if (kind > 27)
/*  777 */                 kind = 27;
/*  778 */               jjCheckNAdd(4);
/*      */             }
/*  780 */             if ((0x100002600 & l) != 0L)
/*      */             {
/*  782 */               if (kind > 6) {
/*  783 */                 kind = 6;
/*      */               }
/*  785 */             } else if (this.curChar == '"')
/*  786 */               jjCheckNAdd(2);
/*      */             break;
/*      */           case 1: 
/*  789 */             if (this.curChar == '"')
/*  790 */               jjCheckNAdd(2);
/*      */             break;
/*      */           case 2: 
/*  793 */             if ((0xFFFFFFFBFFFFFFFF & l) != 0L)
/*  794 */               jjCheckNAddTwoStates(2, 3);
/*      */             break;
/*      */           case 3: 
/*  797 */             if ((this.curChar == '"') && (kind > 26))
/*  798 */               kind = 26;
/*      */             break;
/*      */           case 4: 
/*  801 */             if ((0xFFFFFFFEFFFFFFFF & l) != 0L)
/*      */             {
/*  803 */               if (kind > 27)
/*  804 */                 kind = 27;
/*  805 */               jjCheckNAdd(4);
/*      */             }
/*      */             break;
/*      */           }
/*  809 */         } while (i != startsAt);
/*      */       }
/*  811 */       else if (this.curChar < '')
/*      */       {
/*  813 */         long l = 1L << (this.curChar & 0x3F);
/*      */         do
/*      */         {
/*  816 */           switch (this.jjstateSet[(--i)])
/*      */           {
/*      */           case 0: 
/*      */           case 4: 
/*  820 */             if ((0xFFFFFFFFDFFFFFFF & l) != 0L)
/*      */             {
/*  822 */               if (kind > 27)
/*  823 */                 kind = 27;
/*  824 */               jjCheckNAdd(4); }
/*  825 */             break;
/*      */           case 2: 
/*  827 */             jjAddStates(17, 18);
/*      */           
/*      */           }
/*      */           
/*  831 */         } while (i != startsAt);
/*      */       }
/*      */       else
/*      */       {
/*  835 */         int hiByte = this.curChar >> '\b';
/*  836 */         int i1 = hiByte >> 6;
/*  837 */         long l1 = 1L << (hiByte & 0x3F);
/*  838 */         int i2 = (this.curChar & 0xFF) >> '\006';
/*  839 */         long l2 = 1L << (this.curChar & 0x3F);
/*      */         do
/*      */         {
/*  842 */           switch (this.jjstateSet[(--i)])
/*      */           {
/*      */           case 0: 
/*      */           case 4: 
/*  846 */             if (jjCanMove_0(hiByte, i1, i2, l1, l2))
/*      */             {
/*  848 */               if (kind > 27)
/*  849 */                 kind = 27;
/*  850 */               jjCheckNAdd(4); }
/*  851 */             break;
/*      */           case 2: 
/*  853 */             if (jjCanMove_0(hiByte, i1, i2, l1, l2)) {
/*  854 */               jjAddStates(17, 18);
/*      */             }
/*      */             break;
/*      */           }
/*  858 */         } while (i != startsAt);
/*      */       }
/*  860 */       if (kind != Integer.MAX_VALUE)
/*      */       {
/*  862 */         this.jjmatchedKind = kind;
/*  863 */         this.jjmatchedPos = curPos;
/*  864 */         kind = Integer.MAX_VALUE;
/*      */       }
/*  866 */       curPos++;
/*  867 */       if ((i = this.jjnewStateCnt) == (startsAt = 5 - (this.jjnewStateCnt = startsAt)))
/*  868 */         return curPos;
/*  869 */       try { this.curChar = this.input_stream.readChar(); } catch (IOException e) {} }
/*  870 */     return curPos;
/*      */   }
/*      */   
/*  873 */   static final int[] jjnextStates = { 22, 25, 26, 29, 30, 27, 23, 18, 19, 25, 26, 27, 24, 28, 31, 15, 16, 2, 3, 0, 1 };
/*      */   
/*      */ 
/*      */ 
/*      */   private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2)
/*      */   {
/*  879 */     switch (hiByte)
/*      */     {
/*      */     case 0: 
/*  882 */       return (jjbitVec2[i2] & l2) != 0L;
/*      */     }
/*  884 */     if ((jjbitVec0[i1] & l1) != 0L)
/*  885 */       return true;
/*  886 */     return false;
/*      */   }
/*      */   
/*  889 */   public static final String[] jjstrLiteralImages = { "", null, null, null, null, null, null, null, null, null, "+", "-", "(", ")", ":", "^", null, null, null, null, null, "[", "{", null, "TO", "]", null, null, "TO", "}", null, null };
/*      */   
/*      */ 
/*      */ 
/*  893 */   public static final String[] lexStateNames = { "Boost", "RangeEx", "RangeIn", "DEFAULT" };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  899 */   public static final int[] jjnewLexState = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, 2, 1, 3, -1, 3, -1, -1, -1, 3, -1, -1 };
/*      */   
/*      */ 
/*      */ 
/*  903 */   static final long[] jjtoToken = { 4294967169L };
/*      */   
/*      */ 
/*  906 */   static final long[] jjtoSkip = { 64L };
/*      */   
/*      */   protected CharStream input_stream;
/*      */   
/*  910 */   private final int[] jjrounds = new int[33];
/*  911 */   private final int[] jjstateSet = new int[66];
/*      */   protected char curChar;
/*      */   
/*      */   public QueryParserTokenManager(CharStream stream) {
/*  915 */     this.input_stream = stream;
/*      */   }
/*      */   
/*      */   public QueryParserTokenManager(CharStream stream, int lexState) {
/*  919 */     this(stream);
/*  920 */     SwitchTo(lexState);
/*      */   }
/*      */   
/*      */   public void ReInit(CharStream stream) {
/*  924 */     this.jjmatchedPos = (this.jjnewStateCnt = 0);
/*  925 */     this.curLexState = this.defaultLexState;
/*  926 */     this.input_stream = stream;
/*  927 */     ReInitRounds();
/*      */   }
/*      */   
/*      */   private final void ReInitRounds()
/*      */   {
/*  932 */     this.jjround = -2147483647;
/*  933 */     for (int i = 33; i-- > 0;)
/*  934 */       this.jjrounds[i] = Integer.MIN_VALUE;
/*      */   }
/*      */   
/*      */   public void ReInit(CharStream stream, int lexState) {
/*  938 */     ReInit(stream);
/*  939 */     SwitchTo(lexState);
/*      */   }
/*      */   
/*      */   public void SwitchTo(int lexState) {
/*  943 */     if ((lexState >= 4) || (lexState < 0)) {
/*  944 */       throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", 2);
/*      */     }
/*  946 */     this.curLexState = lexState;
/*      */   }
/*      */   
/*      */   protected Token jjFillToken()
/*      */   {
/*  951 */     Token t = Token.newToken(this.jjmatchedKind);
/*  952 */     t.kind = this.jjmatchedKind;
/*  953 */     String im = jjstrLiteralImages[this.jjmatchedKind];
/*  954 */     t.image = (im == null ? this.input_stream.GetImage() : im);
/*  955 */     t.beginLine = this.input_stream.getBeginLine();
/*  956 */     t.beginColumn = this.input_stream.getBeginColumn();
/*  957 */     t.endLine = this.input_stream.getEndLine();
/*  958 */     t.endColumn = this.input_stream.getEndColumn();
/*  959 */     return t;
/*      */   }
/*      */   
/*  962 */   int curLexState = 3;
/*  963 */   int defaultLexState = 3;
/*      */   
/*      */   int jjnewStateCnt;
/*      */   int jjround;
/*      */   int jjmatchedPos;
/*      */   int jjmatchedKind;
/*      */   
/*      */   public Token getNextToken()
/*      */   {
/*  972 */     Token specialToken = null;
/*      */     
/*  974 */     int curPos = 0;
/*      */     
/*      */ 
/*      */     for (;;)
/*      */     {
/*      */       try
/*      */       {
/*  981 */         this.curChar = this.input_stream.BeginToken();
/*      */       }
/*      */       catch (IOException e)
/*      */       {
/*  985 */         this.jjmatchedKind = 0;
/*  986 */         return jjFillToken();
/*      */       }
/*      */       
/*      */ 
/*  990 */       switch (this.curLexState)
/*      */       {
/*      */       case 0: 
/*  993 */         this.jjmatchedKind = Integer.MAX_VALUE;
/*  994 */         this.jjmatchedPos = 0;
/*  995 */         curPos = jjMoveStringLiteralDfa0_0();
/*  996 */         break;
/*      */       case 1: 
/*  998 */         this.jjmatchedKind = Integer.MAX_VALUE;
/*  999 */         this.jjmatchedPos = 0;
/* 1000 */         curPos = jjMoveStringLiteralDfa0_1();
/* 1001 */         break;
/*      */       case 2: 
/* 1003 */         this.jjmatchedKind = Integer.MAX_VALUE;
/* 1004 */         this.jjmatchedPos = 0;
/* 1005 */         curPos = jjMoveStringLiteralDfa0_2();
/* 1006 */         break;
/*      */       case 3: 
/* 1008 */         this.jjmatchedKind = Integer.MAX_VALUE;
/* 1009 */         this.jjmatchedPos = 0;
/* 1010 */         curPos = jjMoveStringLiteralDfa0_3();
/*      */       }
/*      */       
/* 1013 */       if (this.jjmatchedKind == Integer.MAX_VALUE)
/*      */         break;
/* 1015 */       if (this.jjmatchedPos + 1 < curPos)
/* 1016 */         this.input_stream.backup(curPos - this.jjmatchedPos - 1);
/* 1017 */       if ((jjtoToken[(this.jjmatchedKind >> 6)] & 1L << (this.jjmatchedKind & 0x3F)) != 0L)
/*      */       {
/* 1019 */         Token matchedToken = jjFillToken();
/* 1020 */         if (jjnewLexState[this.jjmatchedKind] != -1)
/* 1021 */           this.curLexState = jjnewLexState[this.jjmatchedKind];
/* 1022 */         return matchedToken;
/*      */       }
/*      */       
/*      */ 
/* 1026 */       if (jjnewLexState[this.jjmatchedKind] != -1) {
/* 1027 */         this.curLexState = jjnewLexState[this.jjmatchedKind];
/*      */       }
/*      */     }
/*      */     
/* 1031 */     int error_line = this.input_stream.getEndLine();
/* 1032 */     int error_column = this.input_stream.getEndColumn();
/* 1033 */     String error_after = null;
/* 1034 */     boolean EOFSeen = false;
/* 1035 */     try { this.input_stream.readChar();this.input_stream.backup(1);
/*      */     } catch (IOException e1) {
/* 1037 */       EOFSeen = true;
/* 1038 */       error_after = curPos <= 1 ? "" : this.input_stream.GetImage();
/* 1039 */       if ((this.curChar == '\n') || (this.curChar == '\r')) {
/* 1040 */         error_line++;
/* 1041 */         error_column = 0;
/*      */       }
/*      */       else {
/* 1044 */         error_column++;
/*      */       } }
/* 1046 */     if (!EOFSeen) {
/* 1047 */       this.input_stream.backup(1);
/* 1048 */       error_after = curPos <= 1 ? "" : this.input_stream.GetImage();
/*      */     }
/* 1050 */     throw new TokenMgrError(EOFSeen, this.curLexState, error_line, error_column, error_after, this.curChar, 0);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/queryParser/QueryParserTokenManager.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */