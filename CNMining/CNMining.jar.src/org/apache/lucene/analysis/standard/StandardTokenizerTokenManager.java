/*      */ package org.apache.lucene.analysis.standard;
/*      */ 
/*      */ import java.io.IOException;
/*      */ 
/*      */ public class StandardTokenizerTokenManager implements StandardTokenizerConstants {
/*    6 */   public java.io.PrintStream debugStream = System.out;
/*    7 */   public void setDebugStream(java.io.PrintStream ds) { this.debugStream = ds; }
/*      */   
/*      */   private final int jjMoveStringLiteralDfa0_0() {
/*   10 */     return jjMoveNfa_0(0, 0);
/*      */   }
/*      */   
/*      */   private final void jjCheckNAdd(int state) {
/*   14 */     if (this.jjrounds[state] != this.jjround)
/*      */     {
/*   16 */       this.jjstateSet[(this.jjnewStateCnt++)] = state;
/*   17 */       this.jjrounds[state] = this.jjround;
/*      */     }
/*      */   }
/*      */   
/*      */   private final void jjAddStates(int start, int end) {
/*      */     do {
/*   23 */       this.jjstateSet[(this.jjnewStateCnt++)] = jjnextStates[start];
/*   24 */     } while (start++ != end);
/*      */   }
/*      */   
/*      */   private final void jjCheckNAddTwoStates(int state1, int state2) {
/*   28 */     jjCheckNAdd(state1);
/*   29 */     jjCheckNAdd(state2);
/*      */   }
/*      */   
/*      */   private final void jjCheckNAddStates(int start, int end) {
/*      */     do {
/*   34 */       jjCheckNAdd(jjnextStates[start]);
/*   35 */     } while (start++ != end);
/*      */   }
/*      */   
/*      */   private final void jjCheckNAddStates(int start) {
/*   39 */     jjCheckNAdd(jjnextStates[start]);
/*   40 */     jjCheckNAdd(jjnextStates[(start + 1)]); }
/*      */   
/*   42 */   static final long[] jjbitVec0 = { 2301339409586323456L, -16384L, 4294967295L, 432345564227567616L };
/*      */   
/*      */ 
/*   45 */   static final long[] jjbitVec2 = { 0L, -1L, -1L, -1L };
/*      */   
/*      */ 
/*   48 */   static final long[] jjbitVec3 = { -1L, -1L, 65535L, 0L };
/*      */   
/*      */ 
/*   51 */   static final long[] jjbitVec4 = { -1L, -1L, 0L, 0L };
/*      */   
/*      */ 
/*   54 */   static final long[] jjbitVec5 = { 70368744177663L, 0L, 0L, 0L };
/*      */   
/*      */ 
/*   57 */   static final long[] jjbitVec6 = { 5632L, 0L, 0L, 0L };
/*      */   
/*      */ 
/*   60 */   static final long[] jjbitVec7 = { 0L, 281200098803712L, 0L, 281200098803712L };
/*      */   
/*      */ 
/*   63 */   static final long[] jjbitVec8 = { 0L, 4393751543808L, 0L, 287948901175001088L };
/*      */   
/*      */ 
/*   66 */   static final long[] jjbitVec9 = { 0L, 281200098803712L, 0L, 280925220896768L };
/*      */   
/*      */ 
/*   69 */   static final long[] jjbitVec10 = { 0L, 281200098803712L, 0L, 0L };
/*      */   
/*      */ 
/*   72 */   static final long[] jjbitVec11 = { 0L, 67043328L, 0L, 67043328L };
/*      */   
/*      */ 
/*   75 */   static final long[] jjbitVec12 = { 0L, 1023L, 0L, 0L };
/*      */   
/*      */ 
/*   78 */   static final long[] jjbitVec13 = { 4294967294L, 0L, 0L, 0L };
/*      */   
/*      */ 
/*   81 */   static final long[] jjbitVec14 = { 0L, 0L, 0L, -36028797027352577L };
/*      */   
/*      */ 
/*      */ 
/*      */   private final int jjMoveNfa_0(int startState, int curPos)
/*      */   {
/*   87 */     int startsAt = 0;
/*   88 */     this.jjnewStateCnt = 73;
/*   89 */     int i = 1;
/*   90 */     this.jjstateSet[0] = startState;
/*   91 */     int kind = Integer.MAX_VALUE;
/*      */     for (;;)
/*      */     {
/*   94 */       if (++this.jjround == Integer.MAX_VALUE)
/*   95 */         ReInitRounds();
/*   96 */       if (this.curChar < '@')
/*      */       {
/*   98 */         long l = 1L << this.curChar;
/*      */         do
/*      */         {
/*  101 */           switch (this.jjstateSet[(--i)])
/*      */           {
/*      */           case 0: 
/*  104 */             if ((0x3FF000000000000 & l) != 0L)
/*      */             {
/*  106 */               if (kind > 1)
/*  107 */                 kind = 1;
/*  108 */               jjCheckNAddStates(0, 17);
/*      */             }
/*  110 */             if ((0x3FF000000000000 & l) != 0L)
/*  111 */               jjCheckNAddStates(18, 23);
/*      */             break;
/*      */           case 1: 
/*  114 */             if ((0x3FF000000000000 & l) != 0L) {
/*  115 */               jjCheckNAddStates(18, 23);
/*      */             }
/*      */             break;
/*      */           case 2: case 39: 
/*  119 */             if ((0x3FF000000000000 & l) != 0L)
/*  120 */               jjCheckNAddTwoStates(2, 3);
/*      */             break;
/*      */           case 3: 
/*  123 */             if ((0xF00000000000 & l) != 0L)
/*  124 */               jjCheckNAdd(4);
/*      */             break;
/*      */           case 4: 
/*  127 */             if ((0x3FF000000000000 & l) != 0L)
/*      */             {
/*  129 */               if (kind > 7)
/*  130 */                 kind = 7;
/*  131 */               jjCheckNAdd(4); }
/*  132 */             break;
/*      */           case 5: 
/*      */           case 48: 
/*  135 */             if ((0x3FF000000000000 & l) != 0L)
/*  136 */               jjCheckNAddTwoStates(5, 6);
/*      */             break;
/*      */           case 6: 
/*  139 */             if ((0xF00000000000 & l) != 0L)
/*  140 */               jjCheckNAdd(7);
/*      */             break;
/*      */           case 7: 
/*  143 */             if ((0x3FF000000000000 & l) != 0L)
/*  144 */               jjCheckNAddTwoStates(7, 8);
/*      */             break;
/*      */           case 8: 
/*  147 */             if ((0xF00000000000 & l) != 0L)
/*  148 */               jjCheckNAddTwoStates(9, 10);
/*      */             break;
/*      */           case 9: 
/*  151 */             if ((0x3FF000000000000 & l) != 0L) {
/*  152 */               jjCheckNAddTwoStates(9, 10);
/*      */             }
/*      */             break;
/*      */           case 10: case 11: 
/*  156 */             if ((0x3FF000000000000 & l) != 0L)
/*      */             {
/*  158 */               if (kind > 7)
/*  159 */                 kind = 7;
/*  160 */               jjCheckNAddTwoStates(6, 11); }
/*  161 */             break;
/*      */           case 12: 
/*      */           case 61: 
/*  164 */             if ((0x3FF000000000000 & l) != 0L)
/*  165 */               jjCheckNAddTwoStates(12, 13);
/*      */             break;
/*      */           case 13: 
/*  168 */             if ((0xF00000000000 & l) != 0L)
/*  169 */               jjCheckNAdd(14);
/*      */             break;
/*      */           case 14: 
/*  172 */             if ((0x3FF000000000000 & l) != 0L)
/*  173 */               jjCheckNAddTwoStates(14, 15);
/*      */             break;
/*      */           case 15: 
/*  176 */             if ((0xF00000000000 & l) != 0L)
/*  177 */               jjCheckNAddTwoStates(16, 17);
/*      */             break;
/*      */           case 16: 
/*  180 */             if ((0x3FF000000000000 & l) != 0L) {
/*  181 */               jjCheckNAddTwoStates(16, 17);
/*      */             }
/*      */             break;
/*      */           case 17: case 18: 
/*  185 */             if ((0x3FF000000000000 & l) != 0L)
/*  186 */               jjCheckNAddTwoStates(18, 19);
/*      */             break;
/*      */           case 19: 
/*  189 */             if ((0xF00000000000 & l) != 0L)
/*  190 */               jjCheckNAdd(20);
/*      */             break;
/*      */           case 20: 
/*  193 */             if ((0x3FF000000000000 & l) != 0L)
/*      */             {
/*  195 */               if (kind > 7)
/*  196 */                 kind = 7;
/*  197 */               jjCheckNAddTwoStates(15, 20); }
/*  198 */             break;
/*      */           case 21: 
/*  200 */             if ((0x3FF000000000000 & l) != 0L)
/*      */             {
/*  202 */               if (kind > 1)
/*  203 */                 kind = 1;
/*  204 */               jjCheckNAddStates(0, 17); }
/*  205 */             break;
/*      */           case 22: 
/*  207 */             if ((0x3FF000000000000 & l) != 0L)
/*      */             {
/*  209 */               if (kind > 1)
/*  210 */                 kind = 1;
/*  211 */               jjCheckNAdd(22); }
/*  212 */             break;
/*      */           case 23: 
/*  214 */             if ((0x3FF000000000000 & l) != 0L)
/*  215 */               jjCheckNAddStates(24, 26);
/*      */             break;
/*      */           case 24: 
/*  218 */             if ((0x600000000000 & l) != 0L)
/*  219 */               jjCheckNAdd(25);
/*      */             break;
/*      */           case 25: 
/*  222 */             if ((0x3FF000000000000 & l) != 0L)
/*  223 */               jjCheckNAddStates(27, 29);
/*      */             break;
/*      */           case 27: 
/*  226 */             if ((0x3FF000000000000 & l) != 0L)
/*  227 */               jjCheckNAddTwoStates(27, 28);
/*      */             break;
/*      */           case 28: 
/*  230 */             if ((0x600000000000 & l) != 0L)
/*  231 */               jjCheckNAdd(29);
/*      */             break;
/*      */           case 29: 
/*  234 */             if ((0x3FF000000000000 & l) != 0L)
/*      */             {
/*  236 */               if (kind > 5)
/*  237 */                 kind = 5;
/*  238 */               jjCheckNAddTwoStates(28, 29); }
/*  239 */             break;
/*      */           case 30: 
/*  241 */             if ((0x3FF000000000000 & l) != 0L)
/*  242 */               jjCheckNAddTwoStates(30, 31);
/*      */             break;
/*      */           case 31: 
/*  245 */             if (this.curChar == '.')
/*  246 */               jjCheckNAdd(32);
/*      */             break;
/*      */           case 32: 
/*  249 */             if ((0x3FF000000000000 & l) != 0L)
/*      */             {
/*  251 */               if (kind > 6)
/*  252 */                 kind = 6;
/*  253 */               jjCheckNAddTwoStates(31, 32); }
/*  254 */             break;
/*      */           case 33: 
/*  256 */             if ((0x3FF000000000000 & l) != 0L)
/*  257 */               jjCheckNAddTwoStates(33, 34);
/*      */             break;
/*      */           case 34: 
/*  260 */             if ((0xF00000000000 & l) != 0L)
/*  261 */               jjCheckNAddTwoStates(35, 36);
/*      */             break;
/*      */           case 35: 
/*  264 */             if ((0x3FF000000000000 & l) != 0L) {
/*  265 */               jjCheckNAddTwoStates(35, 36);
/*      */             }
/*      */             break;
/*      */           case 36: case 37: 
/*  269 */             if ((0x3FF000000000000 & l) != 0L)
/*      */             {
/*  271 */               if (kind > 7)
/*  272 */                 kind = 7;
/*  273 */               jjCheckNAdd(37); }
/*  274 */             break;
/*      */           case 38: 
/*  276 */             if ((0x3FF000000000000 & l) != 0L)
/*  277 */               jjCheckNAddTwoStates(38, 39);
/*      */             break;
/*      */           case 40: 
/*  280 */             if ((0x3FF000000000000 & l) != 0L)
/*  281 */               jjCheckNAddTwoStates(40, 41);
/*      */             break;
/*      */           case 41: 
/*  284 */             if ((0xF00000000000 & l) != 0L)
/*  285 */               jjCheckNAddTwoStates(42, 43);
/*      */             break;
/*      */           case 42: 
/*  288 */             if ((0x3FF000000000000 & l) != 0L) {
/*  289 */               jjCheckNAddTwoStates(42, 43);
/*      */             }
/*      */             break;
/*      */           case 43: case 44: 
/*  293 */             if ((0x3FF000000000000 & l) != 0L)
/*  294 */               jjCheckNAddTwoStates(44, 45);
/*      */             break;
/*      */           case 45: 
/*  297 */             if ((0xF00000000000 & l) != 0L)
/*  298 */               jjCheckNAdd(46);
/*      */             break;
/*      */           case 46: 
/*  301 */             if ((0x3FF000000000000 & l) != 0L)
/*      */             {
/*  303 */               if (kind > 7)
/*  304 */                 kind = 7;
/*  305 */               jjCheckNAddTwoStates(41, 46); }
/*  306 */             break;
/*      */           case 47: 
/*  308 */             if ((0x3FF000000000000 & l) != 0L)
/*  309 */               jjCheckNAddTwoStates(47, 48);
/*      */             break;
/*      */           case 49: 
/*  312 */             if ((0x3FF000000000000 & l) != 0L)
/*  313 */               jjCheckNAddTwoStates(49, 50);
/*      */             break;
/*      */           case 50: 
/*  316 */             if ((0xF00000000000 & l) != 0L)
/*  317 */               jjCheckNAddTwoStates(51, 52);
/*      */             break;
/*      */           case 51: 
/*  320 */             if ((0x3FF000000000000 & l) != 0L) {
/*  321 */               jjCheckNAddTwoStates(51, 52);
/*      */             }
/*      */             break;
/*      */           case 52: case 53: 
/*  325 */             if ((0x3FF000000000000 & l) != 0L)
/*  326 */               jjCheckNAddTwoStates(53, 54);
/*      */             break;
/*      */           case 54: 
/*  329 */             if ((0xF00000000000 & l) != 0L)
/*  330 */               jjCheckNAdd(55);
/*      */             break;
/*      */           case 55: 
/*  333 */             if ((0x3FF000000000000 & l) != 0L)
/*  334 */               jjCheckNAddTwoStates(55, 56);
/*      */             break;
/*      */           case 56: 
/*  337 */             if ((0xF00000000000 & l) != 0L)
/*  338 */               jjCheckNAddTwoStates(57, 58);
/*      */             break;
/*      */           case 57: 
/*  341 */             if ((0x3FF000000000000 & l) != 0L) {
/*  342 */               jjCheckNAddTwoStates(57, 58);
/*      */             }
/*      */             break;
/*      */           case 58: case 59: 
/*  346 */             if ((0x3FF000000000000 & l) != 0L)
/*      */             {
/*  348 */               if (kind > 7)
/*  349 */                 kind = 7;
/*  350 */               jjCheckNAddTwoStates(54, 59); }
/*  351 */             break;
/*      */           case 60: 
/*  353 */             if ((0x3FF000000000000 & l) != 0L)
/*  354 */               jjCheckNAddTwoStates(60, 61);
/*      */             break;
/*      */           case 64: 
/*  357 */             if (this.curChar == '\'')
/*  358 */               this.jjstateSet[(this.jjnewStateCnt++)] = 65;
/*      */             break;
/*      */           case 67: 
/*  361 */             if (this.curChar == '.')
/*  362 */               jjCheckNAdd(68);
/*      */             break;
/*      */           case 69: 
/*  365 */             if (this.curChar == '.')
/*      */             {
/*  367 */               if (kind > 3)
/*  368 */                 kind = 3;
/*  369 */               jjCheckNAdd(68); }
/*  370 */             break;
/*      */           case 71: 
/*  372 */             if (this.curChar == '&') {
/*  373 */               this.jjstateSet[(this.jjnewStateCnt++)] = 72;
/*      */             }
/*      */             break;
/*      */           }
/*  377 */         } while (i != startsAt);
/*      */       }
/*  379 */       else if (this.curChar < '')
/*      */       {
/*  381 */         long l = 1L << (this.curChar & 0x3F);
/*      */         do
/*      */         {
/*  384 */           switch (this.jjstateSet[(--i)])
/*      */           {
/*      */           case 0: 
/*  387 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  388 */               jjCheckNAddStates(30, 35);
/*  389 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*      */             {
/*  391 */               if (kind > 1)
/*  392 */                 kind = 1;
/*  393 */               jjCheckNAddStates(0, 17);
/*      */             }
/*      */             break;
/*      */           case 2: 
/*  397 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  398 */               jjAddStates(36, 37);
/*      */             break;
/*      */           case 3: 
/*  401 */             if (this.curChar == '_')
/*  402 */               jjCheckNAdd(4);
/*      */             break;
/*      */           case 4: 
/*  405 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*      */             {
/*  407 */               if (kind > 7)
/*  408 */                 kind = 7;
/*  409 */               jjCheckNAdd(4); }
/*  410 */             break;
/*      */           case 5: 
/*  412 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  413 */               jjCheckNAddTwoStates(5, 6);
/*      */             break;
/*      */           case 6: 
/*  416 */             if (this.curChar == '_')
/*  417 */               jjCheckNAdd(7);
/*      */             break;
/*      */           case 7: 
/*  420 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  421 */               jjCheckNAddTwoStates(7, 8);
/*      */             break;
/*      */           case 8: 
/*  424 */             if (this.curChar == '_')
/*  425 */               jjCheckNAddTwoStates(9, 10);
/*      */             break;
/*      */           case 9: 
/*  428 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  429 */               jjCheckNAddTwoStates(9, 10);
/*      */             break;
/*      */           case 11: 
/*  432 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*      */             {
/*  434 */               if (kind > 7)
/*  435 */                 kind = 7;
/*  436 */               jjCheckNAddTwoStates(6, 11); }
/*  437 */             break;
/*      */           case 12: 
/*  439 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  440 */               jjAddStates(38, 39);
/*      */             break;
/*      */           case 13: 
/*  443 */             if (this.curChar == '_')
/*  444 */               jjCheckNAdd(14);
/*      */             break;
/*      */           case 14: 
/*  447 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  448 */               jjCheckNAddTwoStates(14, 15);
/*      */             break;
/*      */           case 15: 
/*  451 */             if (this.curChar == '_')
/*  452 */               jjCheckNAddTwoStates(16, 17);
/*      */             break;
/*      */           case 16: 
/*  455 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  456 */               jjCheckNAddTwoStates(16, 17);
/*      */             break;
/*      */           case 18: 
/*  459 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  460 */               jjAddStates(40, 41);
/*      */             break;
/*      */           case 19: 
/*  463 */             if (this.curChar == '_')
/*  464 */               jjCheckNAdd(20);
/*      */             break;
/*      */           case 20: 
/*  467 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*      */             {
/*  469 */               if (kind > 7)
/*  470 */                 kind = 7;
/*  471 */               jjCheckNAddTwoStates(15, 20); }
/*  472 */             break;
/*      */           case 21: 
/*  474 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*      */             {
/*  476 */               if (kind > 1)
/*  477 */                 kind = 1;
/*  478 */               jjCheckNAddStates(0, 17); }
/*  479 */             break;
/*      */           case 22: 
/*  481 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*      */             {
/*  483 */               if (kind > 1)
/*  484 */                 kind = 1;
/*  485 */               jjCheckNAdd(22); }
/*  486 */             break;
/*      */           case 23: 
/*  488 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  489 */               jjCheckNAddStates(24, 26);
/*      */             break;
/*      */           case 24: 
/*  492 */             if (this.curChar == '_')
/*  493 */               jjCheckNAdd(25);
/*      */             break;
/*      */           case 25: 
/*  496 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  497 */               jjCheckNAddStates(27, 29);
/*      */             break;
/*      */           case 26: 
/*  500 */             if (this.curChar == '@')
/*  501 */               jjCheckNAdd(27);
/*      */             break;
/*      */           case 27: 
/*  504 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  505 */               jjCheckNAddTwoStates(27, 28);
/*      */             break;
/*      */           case 29: 
/*  508 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*      */             {
/*  510 */               if (kind > 5)
/*  511 */                 kind = 5;
/*  512 */               jjCheckNAddTwoStates(28, 29); }
/*  513 */             break;
/*      */           case 30: 
/*  515 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  516 */               jjCheckNAddTwoStates(30, 31);
/*      */             break;
/*      */           case 32: 
/*  519 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*      */             {
/*  521 */               if (kind > 6)
/*  522 */                 kind = 6;
/*  523 */               jjCheckNAddTwoStates(31, 32); }
/*  524 */             break;
/*      */           case 33: 
/*  526 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  527 */               jjCheckNAddTwoStates(33, 34);
/*      */             break;
/*      */           case 34: 
/*  530 */             if (this.curChar == '_')
/*  531 */               jjCheckNAddTwoStates(35, 36);
/*      */             break;
/*      */           case 35: 
/*  534 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  535 */               jjCheckNAddTwoStates(35, 36);
/*      */             break;
/*      */           case 37: 
/*  538 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*      */             {
/*  540 */               if (kind > 7)
/*  541 */                 kind = 7;
/*  542 */               this.jjstateSet[(this.jjnewStateCnt++)] = 37; }
/*  543 */             break;
/*      */           case 38: 
/*  545 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  546 */               jjCheckNAddTwoStates(38, 39);
/*      */             break;
/*      */           case 40: 
/*  549 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  550 */               jjCheckNAddTwoStates(40, 41);
/*      */             break;
/*      */           case 41: 
/*  553 */             if (this.curChar == '_')
/*  554 */               jjCheckNAddTwoStates(42, 43);
/*      */             break;
/*      */           case 42: 
/*  557 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  558 */               jjCheckNAddTwoStates(42, 43);
/*      */             break;
/*      */           case 44: 
/*  561 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  562 */               jjAddStates(42, 43);
/*      */             break;
/*      */           case 45: 
/*  565 */             if (this.curChar == '_')
/*  566 */               jjCheckNAdd(46);
/*      */             break;
/*      */           case 46: 
/*  569 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*      */             {
/*  571 */               if (kind > 7)
/*  572 */                 kind = 7;
/*  573 */               jjCheckNAddTwoStates(41, 46); }
/*  574 */             break;
/*      */           case 47: 
/*  576 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  577 */               jjCheckNAddTwoStates(47, 48);
/*      */             break;
/*      */           case 49: 
/*  580 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  581 */               jjCheckNAddTwoStates(49, 50);
/*      */             break;
/*      */           case 50: 
/*  584 */             if (this.curChar == '_')
/*  585 */               jjCheckNAddTwoStates(51, 52);
/*      */             break;
/*      */           case 51: 
/*  588 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  589 */               jjCheckNAddTwoStates(51, 52);
/*      */             break;
/*      */           case 53: 
/*  592 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  593 */               jjCheckNAddTwoStates(53, 54);
/*      */             break;
/*      */           case 54: 
/*  596 */             if (this.curChar == '_')
/*  597 */               jjCheckNAdd(55);
/*      */             break;
/*      */           case 55: 
/*  600 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  601 */               jjCheckNAddTwoStates(55, 56);
/*      */             break;
/*      */           case 56: 
/*  604 */             if (this.curChar == '_')
/*  605 */               jjCheckNAddTwoStates(57, 58);
/*      */             break;
/*      */           case 57: 
/*  608 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  609 */               jjCheckNAddTwoStates(57, 58);
/*      */             break;
/*      */           case 59: 
/*  612 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*      */             {
/*  614 */               if (kind > 7)
/*  615 */                 kind = 7;
/*  616 */               jjCheckNAddTwoStates(54, 59); }
/*  617 */             break;
/*      */           case 60: 
/*  619 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  620 */               jjCheckNAddTwoStates(60, 61);
/*      */             break;
/*      */           case 62: 
/*  623 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  624 */               jjCheckNAddStates(30, 35);
/*      */             break;
/*      */           case 63: 
/*  627 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  628 */               jjCheckNAddTwoStates(63, 64);
/*      */             break;
/*      */           case 65: 
/*  631 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*      */             {
/*  633 */               if (kind > 2)
/*  634 */                 kind = 2;
/*  635 */               jjCheckNAddTwoStates(64, 65); }
/*  636 */             break;
/*      */           case 66: 
/*  638 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  639 */               jjCheckNAddTwoStates(66, 67);
/*      */             break;
/*      */           case 68: 
/*  642 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  643 */               jjAddStates(44, 45);
/*      */             break;
/*      */           case 70: 
/*  646 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*  647 */               jjCheckNAddTwoStates(70, 71);
/*      */             break;
/*      */           case 71: 
/*  650 */             if (this.curChar == '@')
/*  651 */               jjCheckNAdd(72);
/*      */             break;
/*      */           case 72: 
/*  654 */             if ((0x7FFFFFE07FFFFFE & l) != 0L)
/*      */             {
/*  656 */               if (kind > 4)
/*  657 */                 kind = 4;
/*  658 */               jjCheckNAdd(72);
/*      */             }
/*      */             break;
/*      */           }
/*  662 */         } while (i != startsAt);
/*      */       }
/*      */       else
/*      */       {
/*  666 */         int hiByte = this.curChar >> '\b';
/*  667 */         int i1 = hiByte >> 6;
/*  668 */         long l1 = 1L << (hiByte & 0x3F);
/*  669 */         int i2 = (this.curChar & 0xFF) >> '\006';
/*  670 */         long l2 = 1L << (this.curChar & 0x3F);
/*      */         do
/*      */         {
/*  673 */           switch (this.jjstateSet[(--i)])
/*      */           {
/*      */           case 0: 
/*  676 */             if (jjCanMove_0(hiByte, i1, i2, l1, l2))
/*      */             {
/*  678 */               if (kind > 12)
/*  679 */                 kind = 12;
/*      */             }
/*  681 */             if (jjCanMove_1(hiByte, i1, i2, l1, l2))
/*  682 */               jjCheckNAddStates(18, 23);
/*  683 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*      */             {
/*  685 */               if (kind > 1)
/*  686 */                 kind = 1;
/*  687 */               jjCheckNAddStates(0, 17);
/*      */             }
/*  689 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  690 */               jjCheckNAddStates(30, 35);
/*      */             break;
/*      */           case 1: 
/*  693 */             if (jjCanMove_1(hiByte, i1, i2, l1, l2))
/*  694 */               jjCheckNAddStates(18, 23);
/*      */             break;
/*      */           case 2: 
/*  697 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  698 */               jjCheckNAddTwoStates(2, 3);
/*      */             break;
/*      */           case 4: 
/*  701 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*      */             {
/*  703 */               if (kind > 7)
/*  704 */                 kind = 7;
/*  705 */               this.jjstateSet[(this.jjnewStateCnt++)] = 4; }
/*  706 */             break;
/*      */           case 5: 
/*  708 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  709 */               jjCheckNAddTwoStates(5, 6);
/*      */             break;
/*      */           case 7: 
/*  712 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  713 */               jjAddStates(46, 47);
/*      */             break;
/*      */           case 9: 
/*  716 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  717 */               jjAddStates(48, 49);
/*      */             break;
/*      */           case 10: 
/*  720 */             if (jjCanMove_1(hiByte, i1, i2, l1, l2))
/*      */             {
/*  722 */               if (kind > 7)
/*  723 */                 kind = 7;
/*  724 */               jjCheckNAddTwoStates(6, 11); }
/*  725 */             break;
/*      */           case 11: 
/*  727 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*      */             {
/*  729 */               if (kind > 7)
/*  730 */                 kind = 7;
/*  731 */               jjCheckNAddTwoStates(6, 11); }
/*  732 */             break;
/*      */           case 12: 
/*  734 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  735 */               jjCheckNAddTwoStates(12, 13);
/*      */             break;
/*      */           case 14: 
/*  738 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  739 */               jjCheckNAddTwoStates(14, 15);
/*      */             break;
/*      */           case 16: 
/*  742 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  743 */               jjAddStates(50, 51);
/*      */             break;
/*      */           case 17: 
/*  746 */             if (jjCanMove_1(hiByte, i1, i2, l1, l2))
/*  747 */               jjCheckNAddTwoStates(18, 19);
/*      */             break;
/*      */           case 18: 
/*  750 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  751 */               jjCheckNAddTwoStates(18, 19);
/*      */             break;
/*      */           case 20: 
/*  754 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*      */             {
/*  756 */               if (kind > 7)
/*  757 */                 kind = 7;
/*  758 */               jjCheckNAddTwoStates(15, 20); }
/*  759 */             break;
/*      */           case 21: 
/*  761 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*      */             {
/*  763 */               if (kind > 1)
/*  764 */                 kind = 1;
/*  765 */               jjCheckNAddStates(0, 17); }
/*  766 */             break;
/*      */           case 22: 
/*  768 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*      */             {
/*  770 */               if (kind > 1)
/*  771 */                 kind = 1;
/*  772 */               jjCheckNAdd(22); }
/*  773 */             break;
/*      */           case 23: 
/*  775 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  776 */               jjCheckNAddStates(24, 26);
/*      */             break;
/*      */           case 25: 
/*  779 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  780 */               jjCheckNAddStates(27, 29);
/*      */             break;
/*      */           case 27: 
/*  783 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  784 */               jjCheckNAddTwoStates(27, 28);
/*      */             break;
/*      */           case 29: 
/*  787 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*      */             {
/*  789 */               if (kind > 5)
/*  790 */                 kind = 5;
/*  791 */               jjCheckNAddTwoStates(28, 29); }
/*  792 */             break;
/*      */           case 30: 
/*  794 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  795 */               jjCheckNAddTwoStates(30, 31);
/*      */             break;
/*      */           case 32: 
/*  798 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*      */             {
/*  800 */               if (kind > 6)
/*  801 */                 kind = 6;
/*  802 */               jjCheckNAddTwoStates(31, 32); }
/*  803 */             break;
/*      */           case 33: 
/*  805 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  806 */               jjCheckNAddTwoStates(33, 34);
/*      */             break;
/*      */           case 35: 
/*  809 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  810 */               jjAddStates(52, 53);
/*      */             break;
/*      */           case 36: 
/*  813 */             if (jjCanMove_1(hiByte, i1, i2, l1, l2))
/*      */             {
/*  815 */               if (kind > 7)
/*  816 */                 kind = 7;
/*  817 */               jjCheckNAdd(37); }
/*  818 */             break;
/*      */           case 37: 
/*  820 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*      */             {
/*  822 */               if (kind > 7)
/*  823 */                 kind = 7;
/*  824 */               jjCheckNAdd(37); }
/*  825 */             break;
/*      */           case 38: 
/*  827 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  828 */               jjCheckNAddTwoStates(38, 39);
/*      */             break;
/*      */           case 39: 
/*  831 */             if (jjCanMove_1(hiByte, i1, i2, l1, l2))
/*  832 */               jjCheckNAddTwoStates(2, 3);
/*      */             break;
/*      */           case 40: 
/*  835 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  836 */               jjCheckNAddTwoStates(40, 41);
/*      */             break;
/*      */           case 42: 
/*  839 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  840 */               jjAddStates(54, 55);
/*      */             break;
/*      */           case 43: 
/*  843 */             if (jjCanMove_1(hiByte, i1, i2, l1, l2))
/*  844 */               jjCheckNAddTwoStates(44, 45);
/*      */             break;
/*      */           case 44: 
/*  847 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  848 */               jjCheckNAddTwoStates(44, 45);
/*      */             break;
/*      */           case 46: 
/*  851 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*      */             {
/*  853 */               if (kind > 7)
/*  854 */                 kind = 7;
/*  855 */               jjCheckNAddTwoStates(41, 46); }
/*  856 */             break;
/*      */           case 47: 
/*  858 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  859 */               jjCheckNAddTwoStates(47, 48);
/*      */             break;
/*      */           case 48: 
/*  862 */             if (jjCanMove_1(hiByte, i1, i2, l1, l2))
/*  863 */               jjCheckNAddTwoStates(5, 6);
/*      */             break;
/*      */           case 49: 
/*  866 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  867 */               jjCheckNAddTwoStates(49, 50);
/*      */             break;
/*      */           case 51: 
/*  870 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  871 */               jjAddStates(56, 57);
/*      */             break;
/*      */           case 52: 
/*  874 */             if (jjCanMove_1(hiByte, i1, i2, l1, l2))
/*  875 */               jjCheckNAddTwoStates(53, 54);
/*      */             break;
/*      */           case 53: 
/*  878 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  879 */               jjCheckNAddTwoStates(53, 54);
/*      */             break;
/*      */           case 55: 
/*  882 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  883 */               jjAddStates(58, 59);
/*      */             break;
/*      */           case 57: 
/*  886 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  887 */               jjAddStates(60, 61);
/*      */             break;
/*      */           case 58: 
/*  890 */             if (jjCanMove_1(hiByte, i1, i2, l1, l2))
/*      */             {
/*  892 */               if (kind > 7)
/*  893 */                 kind = 7;
/*  894 */               jjCheckNAddTwoStates(54, 59); }
/*  895 */             break;
/*      */           case 59: 
/*  897 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*      */             {
/*  899 */               if (kind > 7)
/*  900 */                 kind = 7;
/*  901 */               jjCheckNAddTwoStates(54, 59); }
/*  902 */             break;
/*      */           case 60: 
/*  904 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  905 */               jjCheckNAddTwoStates(60, 61);
/*      */             break;
/*      */           case 61: 
/*  908 */             if (jjCanMove_1(hiByte, i1, i2, l1, l2))
/*  909 */               jjCheckNAddTwoStates(12, 13);
/*      */             break;
/*      */           case 62: 
/*  912 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  913 */               jjCheckNAddStates(30, 35);
/*      */             break;
/*      */           case 63: 
/*  916 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  917 */               jjCheckNAddTwoStates(63, 64);
/*      */             break;
/*      */           case 65: 
/*  920 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*      */             {
/*  922 */               if (kind > 2)
/*  923 */                 kind = 2;
/*  924 */               jjCheckNAddTwoStates(64, 65); }
/*  925 */             break;
/*      */           case 66: 
/*  927 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  928 */               jjCheckNAddTwoStates(66, 67);
/*      */             break;
/*      */           case 68: 
/*  931 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  932 */               jjAddStates(44, 45);
/*      */             break;
/*      */           case 70: 
/*  935 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*  936 */               jjCheckNAddTwoStates(70, 71);
/*      */             break;
/*      */           case 72: 
/*  939 */             if (jjCanMove_2(hiByte, i1, i2, l1, l2))
/*      */             {
/*  941 */               if (kind > 4)
/*  942 */                 kind = 4;
/*  943 */               this.jjstateSet[(this.jjnewStateCnt++)] = 72;
/*      */             }
/*      */             break;
/*      */           }
/*  947 */         } while (i != startsAt);
/*      */       }
/*  949 */       if (kind != Integer.MAX_VALUE)
/*      */       {
/*  951 */         this.jjmatchedKind = kind;
/*  952 */         this.jjmatchedPos = curPos;
/*  953 */         kind = Integer.MAX_VALUE;
/*      */       }
/*  955 */       curPos++;
/*  956 */       if ((i = this.jjnewStateCnt) == (startsAt = 73 - (this.jjnewStateCnt = startsAt)))
/*  957 */         return curPos;
/*  958 */       try { this.curChar = this.input_stream.readChar(); } catch (IOException e) {} }
/*  959 */     return curPos;
/*      */   }
/*      */   
/*  962 */   static final int[] jjnextStates = { 22, 23, 24, 26, 30, 31, 33, 34, 38, 39, 40, 41, 47, 48, 49, 50, 60, 61, 2, 3, 5, 6, 12, 13, 23, 24, 26, 24, 25, 26, 63, 64, 66, 67, 70, 71, 2, 3, 12, 13, 18, 19, 44, 45, 68, 69, 7, 8, 9, 10, 16, 17, 35, 36, 42, 43, 51, 52, 55, 56, 57, 58 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2)
/*      */   {
/*  970 */     switch (hiByte)
/*      */     {
/*      */     case 48: 
/*  973 */       return (jjbitVec2[i2] & l2) != 0L;
/*      */     case 49: 
/*  975 */       return (jjbitVec3[i2] & l2) != 0L;
/*      */     case 51: 
/*  977 */       return (jjbitVec4[i2] & l2) != 0L;
/*      */     case 61: 
/*  979 */       return (jjbitVec5[i2] & l2) != 0L;
/*      */     }
/*  981 */     if ((jjbitVec0[i1] & l1) != 0L)
/*  982 */       return true;
/*  983 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jjCanMove_1(int hiByte, int i1, int i2, long l1, long l2)
/*      */   {
/*  988 */     switch (hiByte)
/*      */     {
/*      */     case 6: 
/*  991 */       return (jjbitVec8[i2] & l2) != 0L;
/*      */     case 11: 
/*  993 */       return (jjbitVec9[i2] & l2) != 0L;
/*      */     case 13: 
/*  995 */       return (jjbitVec10[i2] & l2) != 0L;
/*      */     case 14: 
/*  997 */       return (jjbitVec11[i2] & l2) != 0L;
/*      */     case 16: 
/*  999 */       return (jjbitVec12[i2] & l2) != 0L;
/*      */     }
/* 1001 */     if ((jjbitVec6[i1] & l1) != 0L) {
/* 1002 */       if ((jjbitVec7[i2] & l2) == 0L) {
/* 1003 */         return false;
/*      */       }
/* 1005 */       return true; }
/* 1006 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jjCanMove_2(int hiByte, int i1, int i2, long l1, long l2)
/*      */   {
/* 1011 */     switch (hiByte)
/*      */     {
/*      */     case 0: 
/* 1014 */       return (jjbitVec14[i2] & l2) != 0L;
/*      */     }
/* 1016 */     if ((jjbitVec13[i1] & l1) != 0L)
/* 1017 */       return true;
/* 1018 */     return false;
/*      */   }
/*      */   
/* 1021 */   public static final String[] jjstrLiteralImages = { "", null, null, null, null, null, null, null, null, null, null, null, null, null, null };
/*      */   
/*      */ 
/* 1024 */   public static final String[] lexStateNames = { "DEFAULT" };
/*      */   
/*      */ 
/* 1027 */   static final long[] jjtoToken = { 4351L };
/*      */   
/*      */ 
/* 1030 */   static final long[] jjtoSkip = { 16384L };
/*      */   
/*      */   protected CharStream input_stream;
/*      */   
/* 1034 */   private final int[] jjrounds = new int[73];
/* 1035 */   private final int[] jjstateSet = new int[''];
/*      */   protected char curChar;
/*      */   
/*      */   public StandardTokenizerTokenManager(CharStream stream) {
/* 1039 */     this.input_stream = stream;
/*      */   }
/*      */   
/*      */   public StandardTokenizerTokenManager(CharStream stream, int lexState) {
/* 1043 */     this(stream);
/* 1044 */     SwitchTo(lexState);
/*      */   }
/*      */   
/*      */   public void ReInit(CharStream stream) {
/* 1048 */     this.jjmatchedPos = (this.jjnewStateCnt = 0);
/* 1049 */     this.curLexState = this.defaultLexState;
/* 1050 */     this.input_stream = stream;
/* 1051 */     ReInitRounds();
/*      */   }
/*      */   
/*      */   private final void ReInitRounds()
/*      */   {
/* 1056 */     this.jjround = -2147483647;
/* 1057 */     for (int i = 73; i-- > 0;)
/* 1058 */       this.jjrounds[i] = Integer.MIN_VALUE;
/*      */   }
/*      */   
/*      */   public void ReInit(CharStream stream, int lexState) {
/* 1062 */     ReInit(stream);
/* 1063 */     SwitchTo(lexState);
/*      */   }
/*      */   
/*      */   public void SwitchTo(int lexState) {
/* 1067 */     if ((lexState >= 1) || (lexState < 0)) {
/* 1068 */       throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", 2);
/*      */     }
/* 1070 */     this.curLexState = lexState;
/*      */   }
/*      */   
/*      */   protected Token jjFillToken()
/*      */   {
/* 1075 */     Token t = Token.newToken(this.jjmatchedKind);
/* 1076 */     t.kind = this.jjmatchedKind;
/* 1077 */     String im = jjstrLiteralImages[this.jjmatchedKind];
/* 1078 */     t.image = (im == null ? this.input_stream.GetImage() : im);
/* 1079 */     t.beginLine = this.input_stream.getBeginLine();
/* 1080 */     t.beginColumn = this.input_stream.getBeginColumn();
/* 1081 */     t.endLine = this.input_stream.getEndLine();
/* 1082 */     t.endColumn = this.input_stream.getEndColumn();
/* 1083 */     return t;
/*      */   }
/*      */   
/* 1086 */   int curLexState = 0;
/* 1087 */   int defaultLexState = 0;
/*      */   
/*      */   int jjnewStateCnt;
/*      */   int jjround;
/*      */   int jjmatchedPos;
/*      */   int jjmatchedKind;
/*      */   
/*      */   public Token getNextToken()
/*      */   {
/* 1096 */     Token specialToken = null;
/*      */     
/* 1098 */     int curPos = 0;
/*      */     
/*      */ 
/*      */     do
/*      */     {
/*      */       try
/*      */       {
/* 1105 */         this.curChar = this.input_stream.BeginToken();
/*      */       }
/*      */       catch (IOException e)
/*      */       {
/* 1109 */         this.jjmatchedKind = 0;
/* 1110 */         return jjFillToken();
/*      */       }
/*      */       
/*      */ 
/* 1114 */       this.jjmatchedKind = Integer.MAX_VALUE;
/* 1115 */       this.jjmatchedPos = 0;
/* 1116 */       curPos = jjMoveStringLiteralDfa0_0();
/* 1117 */       if ((this.jjmatchedPos == 0) && (this.jjmatchedKind > 14))
/*      */       {
/* 1119 */         this.jjmatchedKind = 14;
/*      */       }
/* 1121 */       if (this.jjmatchedKind == Integer.MAX_VALUE)
/*      */         break;
/* 1123 */       if (this.jjmatchedPos + 1 < curPos)
/* 1124 */         this.input_stream.backup(curPos - this.jjmatchedPos - 1);
/* 1125 */     } while ((jjtoToken[(this.jjmatchedKind >> 6)] & 1L << (this.jjmatchedKind & 0x3F)) == 0L);
/*      */     
/* 1127 */     Token matchedToken = jjFillToken();
/* 1128 */     return matchedToken;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1135 */     int error_line = this.input_stream.getEndLine();
/* 1136 */     int error_column = this.input_stream.getEndColumn();
/* 1137 */     String error_after = null;
/* 1138 */     boolean EOFSeen = false;
/* 1139 */     try { this.input_stream.readChar();this.input_stream.backup(1);
/*      */     } catch (IOException e1) {
/* 1141 */       EOFSeen = true;
/* 1142 */       error_after = curPos <= 1 ? "" : this.input_stream.GetImage();
/* 1143 */       if ((this.curChar == '\n') || (this.curChar == '\r')) {
/* 1144 */         error_line++;
/* 1145 */         error_column = 0;
/*      */       }
/*      */       else {
/* 1148 */         error_column++;
/*      */       } }
/* 1150 */     if (!EOFSeen) {
/* 1151 */       this.input_stream.backup(1);
/* 1152 */       error_after = curPos <= 1 ? "" : this.input_stream.GetImage();
/*      */     }
/* 1154 */     throw new TokenMgrError(EOFSeen, this.curLexState, error_line, error_column, error_after, this.curChar, 0);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/standard/StandardTokenizerTokenManager.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */