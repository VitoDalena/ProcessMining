package bsh;

import java.io.IOException;
import java.io.PrintStream;

public class ParserTokenManager
  implements ParserConstants
{
  public PrintStream debugStream = System.out;
  static final long[] jjbitVec0 = { 0L, 0L, -1L, -1L };
  static final long[] jjbitVec1 = { -2L, -1L, -1L, -1L };
  static final long[] jjbitVec3 = { 2301339413881290750L, -16384L, 4294967295L, 432345564227567616L };
  static final long[] jjbitVec4 = { 0L, 0L, 0L, -36028797027352577L };
  static final long[] jjbitVec5 = { 0L, -1L, -1L, -1L };
  static final long[] jjbitVec6 = { -1L, -1L, 65535L, 0L };
  static final long[] jjbitVec7 = { -1L, -1L, 0L, 0L };
  static final long[] jjbitVec8 = { 70368744177663L, 0L, 0L, 0L };
  static final int[] jjnextStates = { 37, 38, 43, 44, 47, 48, 15, 56, 61, 73, 26, 27, 29, 17, 19, 52, 54, 9, 57, 58, 60, 2, 3, 5, 11, 12, 15, 26, 27, 31, 29, 39, 40, 15, 47, 48, 15, 63, 64, 66, 69, 70, 72, 13, 14, 20, 21, 23, 28, 30, 32, 41, 42, 45, 46, 49, 50 };
  public static final String[] jjstrLiteralImages = { "", null, null, null, null, null, null, null, null, null, "abstract", "boolean", "break", "class", "byte", "case", "catch", "char", "const", "continue", "default", "do", "double", "else", "enum", "extends", "false", "final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "null", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "switch", "synchronized", "transient", "throw", "throws", "true", "try", "void", "volatile", "while", null, null, null, null, null, null, null, null, null, null, null, null, "(", ")", "{", "}", "[", "]", ";", ",", ".", "=", ">", "@gt", "<", "@lt", "!", "~", "?", ":", "==", "<=", "@lteq", ">=", "@gteq", "!=", "||", "@or", "&&", "@and", "++", "--", "+", "-", "*", "/", "&", "@bitwise_and", "|", "@bitwise_or", "^", "%", "<<", "@left_shift", ">>", "@right_shift", ">>>", "@right_unsigned_shift", "+=", "-=", "*=", "/=", "&=", "@and_assign", "|=", "@or_assign", "^=", "%=", "<<=", "@left_shift_assign", ">>=", "@right_shift_assign", ">>>=", "@right_unsigned_shift_assign" };
  public static final String[] lexStateNames = { "DEFAULT" };
  static final long[] jjtoToken = { 2305843009213692929L, -195L, 63L };
  static final long[] jjtoSkip = { 1022L, 0L, 0L };
  static final long[] jjtoSpecial = { 896L, 0L, 0L };
  protected JavaCharStream input_stream;
  private final int[] jjrounds = new int[74];
  private final int[] jjstateSet = new int[''];
  protected char curChar;
  int curLexState = 0;
  int defaultLexState = 0;
  int jjnewStateCnt;
  int jjround;
  int jjmatchedPos;
  int jjmatchedKind;
  
  public void setDebugStream(PrintStream paramPrintStream)
  {
    this.debugStream = paramPrintStream;
  }
  
  private final int jjStopStringLiteralDfa_0(int paramInt, long paramLong1, long paramLong2, long paramLong3)
  {
    switch (paramInt)
    {
    case 0: 
      if ((paramLong2 & 0x200020000000000) != 0L) {
        return 56;
      }
      if ((paramLong1 & 0x3E) != 0L) {
        return 0;
      }
      if ((paramLong2 & 0x10000) != 0L) {
        return 11;
      }
      if ((paramLong1 & 0xFFFFFFFFFFFFC00) != 0L)
      {
        this.jjmatchedKind = 69;
        return 35;
      }
      return -1;
    case 1: 
      if ((paramLong1 & 0x100600000) != 0L) {
        return 35;
      }
      if ((paramLong1 & 0xFFFFFFEFF9FFC00) != 0L)
      {
        if (this.jjmatchedPos != 1)
        {
          this.jjmatchedKind = 69;
          this.jjmatchedPos = 1;
        }
        return 35;
      }
      return -1;
    case 2: 
      if ((paramLong1 & 0xEFFFECEBFDFFC00) != 0L)
      {
        if (this.jjmatchedPos != 2)
        {
          this.jjmatchedKind = 69;
          this.jjmatchedPos = 2;
        }
        return 35;
      }
      if ((paramLong1 & 0x100013040000000) != 0L) {
        return 35;
      }
      return -1;
    case 3: 
      if ((paramLong1 & 0xC7FFCAE3E5D3C00) != 0L)
      {
        if (this.jjmatchedPos != 3)
        {
          this.jjmatchedKind = 69;
          this.jjmatchedPos = 3;
        }
        return 35;
      }
      if ((paramLong1 & 0x28002408182C000) != 0L) {
        return 35;
      }
      return -1;
    case 4: 
      if ((paramLong1 & 0x86080003C053000) != 0L) {
        return 35;
      }
      if ((paramLong1 & 0x41F7CAE02580C00) != 0L)
      {
        if (this.jjmatchedPos != 4)
        {
          this.jjmatchedKind = 69;
          this.jjmatchedPos = 4;
        }
        return 35;
      }
      return -1;
    case 5: 
      if ((paramLong1 & 0x41A1C2A12180C00) != 0L)
      {
        this.jjmatchedKind = 69;
        this.jjmatchedPos = 5;
        return 35;
      }
      if ((paramLong1 & 0x45608400400000) != 0L) {
        return 35;
      }
      return -1;
    case 6: 
      if ((paramLong1 & 0x41A102A00080400) != 0L)
      {
        this.jjmatchedKind = 69;
        this.jjmatchedPos = 6;
        return 35;
      }
      if ((paramLong1 & 0xC0012100800) != 0L) {
        return 35;
      }
      return -1;
    case 7: 
      if ((paramLong1 & 0x402000000080400) != 0L) {
        return 35;
      }
      if ((paramLong1 & 0x18102A00000000) != 0L)
      {
        this.jjmatchedKind = 69;
        this.jjmatchedPos = 7;
        return 35;
      }
      return -1;
    case 8: 
      if ((paramLong1 & 0x8000A00000000) != 0L)
      {
        this.jjmatchedKind = 69;
        this.jjmatchedPos = 8;
        return 35;
      }
      if ((paramLong1 & 0x10102000000000) != 0L) {
        return 35;
      }
      return -1;
    case 9: 
      if ((paramLong1 & 0x8000000000000) != 0L)
      {
        this.jjmatchedKind = 69;
        this.jjmatchedPos = 9;
        return 35;
      }
      if ((paramLong1 & 0xA00000000) != 0L) {
        return 35;
      }
      return -1;
    case 10: 
      if ((paramLong1 & 0x8000000000000) != 0L)
      {
        if (this.jjmatchedPos != 10)
        {
          this.jjmatchedKind = 69;
          this.jjmatchedPos = 10;
        }
        return 35;
      }
      return -1;
    case 11: 
      if ((paramLong1 & 0x8000000000000) != 0L) {
        return 35;
      }
      return -1;
    }
    return -1;
  }
  
  private final int jjStartNfa_0(int paramInt, long paramLong1, long paramLong2, long paramLong3)
  {
    return jjMoveNfa_0(jjStopStringLiteralDfa_0(paramInt, paramLong1, paramLong2, paramLong3), paramInt + 1);
  }
  
  private final int jjStopAtPos(int paramInt1, int paramInt2)
  {
    this.jjmatchedKind = paramInt2;
    this.jjmatchedPos = paramInt1;
    return paramInt1 + 1;
  }
  
  private final int jjStartNfaWithStates_0(int paramInt1, int paramInt2, int paramInt3)
  {
    this.jjmatchedKind = paramInt2;
    this.jjmatchedPos = paramInt1;
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      return paramInt1 + 1;
    }
    return jjMoveNfa_0(paramInt3, paramInt1 + 1);
  }
  
  private final int jjMoveStringLiteralDfa0_0()
  {
    switch (this.curChar)
    {
    case '\t': 
      return jjStartNfaWithStates_0(0, 2, 0);
    case '\n': 
      return jjStartNfaWithStates_0(0, 5, 0);
    case '\f': 
      return jjStartNfaWithStates_0(0, 4, 0);
    case '\r': 
      return jjStartNfaWithStates_0(0, 3, 0);
    case ' ': 
      return jjStartNfaWithStates_0(0, 1, 0);
    case '!': 
      this.jjmatchedKind = 86;
      return jjMoveStringLiteralDfa1_0(0L, 2147483648L, 0L);
    case '%': 
      this.jjmatchedKind = 111;
      return jjMoveStringLiteralDfa1_0(0L, Long.MIN_VALUE, 0L);
    case '&': 
      this.jjmatchedKind = 106;
      return jjMoveStringLiteralDfa1_0(0L, 288230393331580928L, 0L);
    case '(': 
      return jjStopAtPos(0, 72);
    case ')': 
      return jjStopAtPos(0, 73);
    case '*': 
      this.jjmatchedKind = 104;
      return jjMoveStringLiteralDfa1_0(0L, 72057594037927936L, 0L);
    case '+': 
      this.jjmatchedKind = 102;
      return jjMoveStringLiteralDfa1_0(0L, 18014467228958720L, 0L);
    case ',': 
      return jjStopAtPos(0, 79);
    case '-': 
      this.jjmatchedKind = 103;
      return jjMoveStringLiteralDfa1_0(0L, 36028934457917440L, 0L);
    case '.': 
      return jjStartNfaWithStates_0(0, 80, 11);
    case '/': 
      this.jjmatchedKind = 105;
      return jjMoveStringLiteralDfa1_0(0L, 144115188075855872L, 0L);
    case ':': 
      return jjStopAtPos(0, 89);
    case ';': 
      return jjStopAtPos(0, 78);
    case '<': 
      this.jjmatchedKind = 84;
      return jjMoveStringLiteralDfa1_0(0L, 281475110928384L, 1L);
    case '=': 
      this.jjmatchedKind = 81;
      return jjMoveStringLiteralDfa1_0(0L, 67108864L, 0L);
    case '>': 
      this.jjmatchedKind = 82;
      return jjMoveStringLiteralDfa1_0(0L, 5629500071084032L, 20L);
    case '?': 
      return jjStopAtPos(0, 88);
    case '@': 
      return jjMoveStringLiteralDfa1_0(0L, 2894169735298547712L, 42L);
    case '[': 
      return jjStopAtPos(0, 76);
    case ']': 
      return jjStopAtPos(0, 77);
    case '^': 
      this.jjmatchedKind = 110;
      return jjMoveStringLiteralDfa1_0(0L, 4611686018427387904L, 0L);
    case 'a': 
      return jjMoveStringLiteralDfa1_0(1024L, 0L, 0L);
    case 'b': 
      return jjMoveStringLiteralDfa1_0(22528L, 0L, 0L);
    case 'c': 
      return jjMoveStringLiteralDfa1_0(1024000L, 0L, 0L);
    case 'd': 
      return jjMoveStringLiteralDfa1_0(7340032L, 0L, 0L);
    case 'e': 
      return jjMoveStringLiteralDfa1_0(58720256L, 0L, 0L);
    case 'f': 
      return jjMoveStringLiteralDfa1_0(2080374784L, 0L, 0L);
    case 'g': 
      return jjMoveStringLiteralDfa1_0(2147483648L, 0L, 0L);
    case 'i': 
      return jjMoveStringLiteralDfa1_0(270582939648L, 0L, 0L);
    case 'l': 
      return jjMoveStringLiteralDfa1_0(274877906944L, 0L, 0L);
    case 'n': 
      return jjMoveStringLiteralDfa1_0(3848290697216L, 0L, 0L);
    case 'p': 
      return jjMoveStringLiteralDfa1_0(65970697666560L, 0L, 0L);
    case 'r': 
      return jjMoveStringLiteralDfa1_0(70368744177664L, 0L, 0L);
    case 's': 
      return jjMoveStringLiteralDfa1_0(4362862139015168L, 0L, 0L);
    case 't': 
      return jjMoveStringLiteralDfa1_0(139611588448485376L, 0L, 0L);
    case 'v': 
      return jjMoveStringLiteralDfa1_0(432345564227567616L, 0L, 0L);
    case 'w': 
      return jjMoveStringLiteralDfa1_0(576460752303423488L, 0L, 0L);
    case '{': 
      return jjStopAtPos(0, 74);
    case '|': 
      this.jjmatchedKind = 108;
      return jjMoveStringLiteralDfa1_0(0L, 1152921508901814272L, 0L);
    case '}': 
      return jjStopAtPos(0, 75);
    case '~': 
      return jjStopAtPos(0, 87);
    }
    return jjMoveNfa_0(6, 0);
  }
  
  private final int jjMoveStringLiteralDfa1_0(long paramLong1, long paramLong2, long paramLong3)
  {
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(0, paramLong1, paramLong2, paramLong3);
      return 1;
    }
    switch (this.curChar)
    {
    case '&': 
      if ((paramLong2 & 0x400000000) != 0L) {
        return jjStopAtPos(1, 98);
      }
      break;
    case '+': 
      if ((paramLong2 & 0x1000000000) != 0L) {
        return jjStopAtPos(1, 100);
      }
      break;
    case '-': 
      if ((paramLong2 & 0x2000000000) != 0L) {
        return jjStopAtPos(1, 101);
      }
      break;
    case '<': 
      if ((paramLong2 & 0x1000000000000) != 0L)
      {
        this.jjmatchedKind = 112;
        this.jjmatchedPos = 1;
      }
      return jjMoveStringLiteralDfa2_0(paramLong1, 0L, paramLong2, 0L, paramLong3, 1L);
    case '=': 
      if ((paramLong2 & 0x4000000) != 0L) {
        return jjStopAtPos(1, 90);
      }
      if ((paramLong2 & 0x8000000) != 0L) {
        return jjStopAtPos(1, 91);
      }
      if ((paramLong2 & 0x20000000) != 0L) {
        return jjStopAtPos(1, 93);
      }
      if ((paramLong2 & 0x80000000) != 0L) {
        return jjStopAtPos(1, 95);
      }
      if ((paramLong2 & 0x40000000000000) != 0L) {
        return jjStopAtPos(1, 118);
      }
      if ((paramLong2 & 0x80000000000000) != 0L) {
        return jjStopAtPos(1, 119);
      }
      if ((paramLong2 & 0x100000000000000) != 0L) {
        return jjStopAtPos(1, 120);
      }
      if ((paramLong2 & 0x200000000000000) != 0L) {
        return jjStopAtPos(1, 121);
      }
      if ((paramLong2 & 0x400000000000000) != 0L) {
        return jjStopAtPos(1, 122);
      }
      if ((paramLong2 & 0x1000000000000000) != 0L) {
        return jjStopAtPos(1, 124);
      }
      if ((paramLong2 & 0x4000000000000000) != 0L) {
        return jjStopAtPos(1, 126);
      }
      if ((paramLong2 & 0x8000000000000000) != 0L) {
        return jjStopAtPos(1, 127);
      }
      break;
    case '>': 
      if ((paramLong2 & 0x4000000000000) != 0L)
      {
        this.jjmatchedKind = 114;
        this.jjmatchedPos = 1;
      }
      return jjMoveStringLiteralDfa2_0(paramLong1, 0L, paramLong2, 4503599627370496L, paramLong3, 20L);
    case 'a': 
      return jjMoveStringLiteralDfa2_0(paramLong1, 4947869532160L, paramLong2, 576460786663161856L, paramLong3, 0L);
    case 'b': 
      return jjMoveStringLiteralDfa2_0(paramLong1, 1024L, paramLong2, 43980465111040L, paramLong3, 0L);
    case 'e': 
      return jjMoveStringLiteralDfa2_0(paramLong1, 71468256854016L, paramLong2, 0L, paramLong3, 0L);
    case 'f': 
      if ((paramLong1 & 0x100000000) != 0L) {
        return jjStartNfaWithStates_0(1, 32, 35);
      }
      break;
    case 'g': 
      return jjMoveStringLiteralDfa2_0(paramLong1, 0L, paramLong2, 1074266112L, paramLong3, 0L);
    case 'h': 
      return jjMoveStringLiteralDfa2_0(paramLong1, 603623087556132864L, paramLong2, 0L, paramLong3, 0L);
    case 'i': 
      return jjMoveStringLiteralDfa2_0(paramLong1, 402653184L, paramLong2, 0L, paramLong3, 0L);
    case 'l': 
      return jjMoveStringLiteralDfa2_0(paramLong1, 545267712L, paramLong2, 562950223953920L, paramLong3, 2L);
    case 'm': 
      return jjMoveStringLiteralDfa2_0(paramLong1, 25769803776L, paramLong2, 0L, paramLong3, 0L);
    case 'n': 
      return jjMoveStringLiteralDfa2_0(paramLong1, 240534945792L, paramLong2, 0L, paramLong3, 0L);
    case 'o': 
      if ((paramLong1 & 0x200000) != 0L)
      {
        this.jjmatchedKind = 21;
        this.jjmatchedPos = 1;
      }
      return jjMoveStringLiteralDfa2_0(paramLong1, 432345842331682816L, paramLong2, 2305843017803628544L, paramLong3, 0L);
    case 'r': 
      return jjMoveStringLiteralDfa2_0(paramLong1, 112616378963333120L, paramLong2, 11258999068426240L, paramLong3, 40L);
    case 't': 
      return jjMoveStringLiteralDfa2_0(paramLong1, 844424930131968L, paramLong2, 0L, paramLong3, 0L);
    case 'u': 
      return jjMoveStringLiteralDfa2_0(paramLong1, 37383395344384L, paramLong2, 0L, paramLong3, 0L);
    case 'w': 
      return jjMoveStringLiteralDfa2_0(paramLong1, 1125899906842624L, paramLong2, 0L, paramLong3, 0L);
    case 'x': 
      return jjMoveStringLiteralDfa2_0(paramLong1, 33554432L, paramLong2, 0L, paramLong3, 0L);
    case 'y': 
      return jjMoveStringLiteralDfa2_0(paramLong1, 2251799813701632L, paramLong2, 0L, paramLong3, 0L);
    case '|': 
      if ((paramLong2 & 0x100000000) != 0L) {
        return jjStopAtPos(1, 96);
      }
      break;
    }
    return jjStartNfa_0(0, paramLong1, paramLong2, paramLong3);
  }
  
  private final int jjMoveStringLiteralDfa2_0(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6)
  {
    if ((paramLong2 &= paramLong1 | paramLong4 &= paramLong3 | paramLong6 &= paramLong5) == 0L) {
      return jjStartNfa_0(0, paramLong1, paramLong3, paramLong5);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(1, paramLong2, paramLong4, paramLong6);
      return 2;
    }
    switch (this.curChar)
    {
    case '=': 
      if ((paramLong6 & 1L) != 0L) {
        return jjStopAtPos(2, 128);
      }
      if ((paramLong6 & 0x4) != 0L) {
        return jjStopAtPos(2, 130);
      }
      break;
    case '>': 
      if ((paramLong4 & 0x10000000000000) != 0L)
      {
        this.jjmatchedKind = 116;
        this.jjmatchedPos = 2;
      }
      return jjMoveStringLiteralDfa3_0(paramLong2, 0L, paramLong4, 0L, paramLong6, 16L);
    case 'a': 
      return jjMoveStringLiteralDfa3_0(paramLong2, 4785074604220416L, paramLong4, 0L, paramLong6, 0L);
    case 'b': 
      return jjMoveStringLiteralDfa3_0(paramLong2, 35184372088832L, paramLong4, 0L, paramLong6, 0L);
    case 'c': 
      return jjMoveStringLiteralDfa3_0(paramLong2, 4398046511104L, paramLong4, 0L, paramLong6, 0L);
    case 'e': 
      return jjMoveStringLiteralDfa3_0(paramLong2, 4096L, paramLong4, 562949953421312L, paramLong6, 2L);
    case 'f': 
      return jjMoveStringLiteralDfa3_0(paramLong2, 1048576L, paramLong4, 0L, paramLong6, 0L);
    case 'i': 
      return jjMoveStringLiteralDfa3_0(paramLong2, 721710636379144192L, paramLong4, 11302979533537280L, paramLong6, 40L);
    case 'l': 
      return jjMoveStringLiteralDfa3_0(paramLong2, 288232575242076160L, paramLong4, 0L, paramLong6, 0L);
    case 'n': 
      return jjMoveStringLiteralDfa3_0(paramLong2, 2252075095031808L, paramLong4, 576460786663161856L, paramLong6, 0L);
    case 'o': 
      return jjMoveStringLiteralDfa3_0(paramLong2, 158330211272704L, paramLong4, 0L, paramLong6, 0L);
    case 'p': 
      return jjMoveStringLiteralDfa3_0(paramLong2, 25769803776L, paramLong4, 0L, paramLong6, 0L);
    case 'r': 
      if ((paramLong2 & 0x40000000) != 0L) {
        return jjStartNfaWithStates_0(2, 30, 35);
      }
      if ((paramLong4 & 0x200000000) != 0L)
      {
        this.jjmatchedKind = 97;
        this.jjmatchedPos = 2;
      }
      return jjMoveStringLiteralDfa3_0(paramLong2, 27584547717644288L, paramLong4, 2305843009213693952L, paramLong6, 0L);
    case 's': 
      return jjMoveStringLiteralDfa3_0(paramLong2, 34368160768L, paramLong4, 0L, paramLong6, 0L);
    case 't': 
      if ((paramLong2 & 0x1000000000) != 0L)
      {
        this.jjmatchedKind = 36;
        this.jjmatchedPos = 2;
      }
      else if ((paramLong4 & 0x80000) != 0L)
      {
        this.jjmatchedKind = 83;
        this.jjmatchedPos = 2;
      }
      else if ((paramLong4 & 0x200000) != 0L)
      {
        this.jjmatchedKind = 85;
        this.jjmatchedPos = 2;
      }
      return jjMoveStringLiteralDfa3_0(paramLong2, 71058120065024L, paramLong4, 1342177280L, paramLong6, 0L);
    case 'u': 
      return jjMoveStringLiteralDfa3_0(paramLong2, 36028797039935488L, paramLong4, 0L, paramLong6, 0L);
    case 'w': 
      if ((paramLong2 & 0x10000000000) != 0L) {
        return jjStartNfaWithStates_0(2, 40, 35);
      }
      break;
    case 'y': 
      if ((paramLong2 & 0x100000000000000) != 0L) {
        return jjStartNfaWithStates_0(2, 56, 35);
      }
      break;
    }
    return jjStartNfa_0(1, paramLong2, paramLong4, paramLong6);
  }
  
  private final int jjMoveStringLiteralDfa3_0(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6)
  {
    if ((paramLong2 &= paramLong1 | paramLong4 &= paramLong3 | paramLong6 &= paramLong5) == 0L) {
      return jjStartNfa_0(1, paramLong1, paramLong3, paramLong5);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(2, paramLong2, paramLong4, paramLong6);
      return 3;
    }
    switch (this.curChar)
    {
    case '=': 
      if ((paramLong6 & 0x10) != 0L) {
        return jjStopAtPos(3, 132);
      }
      break;
    case '_': 
      return jjMoveStringLiteralDfa4_0(paramLong2, 0L, paramLong4, 2305843009213693952L, paramLong6, 0L);
    case 'a': 
      return jjMoveStringLiteralDfa4_0(paramLong2, 288230377092288512L, paramLong4, 0L, paramLong6, 0L);
    case 'b': 
      return jjMoveStringLiteralDfa4_0(paramLong2, 4194304L, paramLong4, 0L, paramLong6, 0L);
    case 'c': 
      return jjMoveStringLiteralDfa4_0(paramLong2, 2251799813750784L, paramLong4, 0L, paramLong6, 0L);
    case 'd': 
      if ((paramLong2 & 0x200000000000000) != 0L) {
        return jjStartNfaWithStates_0(3, 57, 35);
      }
      if ((paramLong4 & 0x800000000) != 0L)
      {
        this.jjmatchedKind = 99;
        this.jjmatchedPos = 3;
      }
      return jjMoveStringLiteralDfa4_0(paramLong2, 0L, paramLong4, 576460752303423488L, paramLong6, 0L);
    case 'e': 
      if ((paramLong2 & 0x4000) != 0L) {
        return jjStartNfaWithStates_0(3, 14, 35);
      }
      if ((paramLong2 & 0x8000) != 0L) {
        return jjStartNfaWithStates_0(3, 15, 35);
      }
      if ((paramLong2 & 0x800000) != 0L) {
        return jjStartNfaWithStates_0(3, 23, 35);
      }
      if ((paramLong2 & 0x80000000000000) != 0L) {
        return jjStartNfaWithStates_0(3, 55, 35);
      }
      return jjMoveStringLiteralDfa4_0(paramLong2, 137472507904L, paramLong4, 1342177280L, paramLong6, 0L);
    case 'f': 
      return jjMoveStringLiteralDfa4_0(paramLong2, 0L, paramLong4, 562949953421312L, paramLong6, 2L);
    case 'g': 
      if ((paramLong2 & 0x4000000000) != 0L) {
        return jjStartNfaWithStates_0(3, 38, 35);
      }
      return jjMoveStringLiteralDfa4_0(paramLong2, 0L, paramLong4, 11258999068426240L, paramLong6, 40L);
    case 'i': 
      return jjMoveStringLiteralDfa4_0(paramLong2, 563499709235200L, paramLong4, 0L, paramLong6, 0L);
    case 'k': 
      return jjMoveStringLiteralDfa4_0(paramLong2, 4398046511104L, paramLong4, 0L, paramLong6, 0L);
    case 'l': 
      if ((paramLong2 & 0x20000000000) != 0L) {
        return jjStartNfaWithStates_0(3, 41, 35);
      }
      return jjMoveStringLiteralDfa4_0(paramLong2, 576495945265448960L, paramLong4, 0L, paramLong6, 0L);
    case 'm': 
      if ((paramLong2 & 0x1000000) != 0L) {
        return jjStartNfaWithStates_0(3, 24, 35);
      }
      break;
    case 'n': 
      return jjMoveStringLiteralDfa4_0(paramLong2, 4503599627370496L, paramLong4, 0L, paramLong6, 0L);
    case 'o': 
      if ((paramLong2 & 0x80000000) != 0L) {
        return jjStartNfaWithStates_0(3, 31, 35);
      }
      return jjMoveStringLiteralDfa4_0(paramLong2, 27021614944092160L, paramLong4, 0L, paramLong6, 0L);
    case 'r': 
      if ((paramLong2 & 0x20000) != 0L) {
        return jjStartNfaWithStates_0(3, 17, 35);
      }
      return jjMoveStringLiteralDfa4_0(paramLong2, 140737488355328L, paramLong4, 0L, paramLong6, 0L);
    case 's': 
      return jjMoveStringLiteralDfa4_0(paramLong2, 67379200L, paramLong4, 0L, paramLong6, 0L);
    case 't': 
      return jjMoveStringLiteralDfa4_0(paramLong2, 1425001429861376L, paramLong4, 43980465111040L, paramLong6, 0L);
    case 'u': 
      return jjMoveStringLiteralDfa4_0(paramLong2, 70368744177664L, paramLong4, 0L, paramLong6, 0L);
    case 'v': 
      return jjMoveStringLiteralDfa4_0(paramLong2, 8796093022208L, paramLong4, 0L, paramLong6, 0L);
    }
    return jjStartNfa_0(2, paramLong2, paramLong4, paramLong6);
  }
  
  private final int jjMoveStringLiteralDfa4_0(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6)
  {
    if ((paramLong2 &= paramLong1 | paramLong4 &= paramLong3 | paramLong6 &= paramLong5) == 0L) {
      return jjStartNfa_0(2, paramLong1, paramLong3, paramLong5);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(3, paramLong2, paramLong4, paramLong6);
      return 4;
    }
    switch (this.curChar)
    {
    case '_': 
      return jjMoveStringLiteralDfa5_0(paramLong2, 0L, paramLong4, 576460752303423488L, paramLong6, 0L);
    case 'a': 
      return jjMoveStringLiteralDfa5_0(paramLong2, 13228499271680L, paramLong4, 2305843009213693952L, paramLong6, 0L);
    case 'c': 
      return jjMoveStringLiteralDfa5_0(paramLong2, 1688849860263936L, paramLong4, 0L, paramLong6, 0L);
    case 'e': 
      if ((paramLong2 & 0x4000000) != 0L) {
        return jjStartNfaWithStates_0(4, 26, 35);
      }
      if ((paramLong2 & 0x800000000000000) != 0L) {
        return jjStartNfaWithStates_0(4, 59, 35);
      }
      return jjMoveStringLiteralDfa5_0(paramLong2, 17600775981056L, paramLong4, 0L, paramLong6, 0L);
    case 'h': 
      if ((paramLong2 & 0x10000) != 0L) {
        return jjStartNfaWithStates_0(4, 16, 35);
      }
      return jjMoveStringLiteralDfa5_0(paramLong2, 2251799813685248L, paramLong4, 11258999068426240L, paramLong6, 40L);
    case 'i': 
      return jjMoveStringLiteralDfa5_0(paramLong2, 316659349323776L, paramLong4, 0L, paramLong6, 0L);
    case 'k': 
      if ((paramLong2 & 0x1000) != 0L) {
        return jjStartNfaWithStates_0(4, 12, 35);
      }
      break;
    case 'l': 
      if ((paramLong2 & 0x8000000) != 0L)
      {
        this.jjmatchedKind = 27;
        this.jjmatchedPos = 4;
      }
      return jjMoveStringLiteralDfa5_0(paramLong2, 272629760L, paramLong4, 0L, paramLong6, 0L);
    case 'n': 
      return jjMoveStringLiteralDfa5_0(paramLong2, 33554432L, paramLong4, 0L, paramLong6, 0L);
    case 'q': 
      if ((paramLong4 & 0x10000000) != 0L) {
        return jjStopAtPos(4, 92);
      }
      if ((paramLong4 & 0x40000000) != 0L) {
        return jjStopAtPos(4, 94);
      }
      break;
    case 'r': 
      return jjMoveStringLiteralDfa5_0(paramLong2, 70523363001344L, paramLong4, 0L, paramLong6, 0L);
    case 's': 
      if ((paramLong2 & 0x2000) != 0L) {
        return jjStartNfaWithStates_0(4, 13, 35);
      }
      return jjMoveStringLiteralDfa5_0(paramLong2, 4503599627370496L, paramLong4, 0L, paramLong6, 0L);
    case 't': 
      if ((paramLong2 & 0x40000) != 0L) {
        return jjStartNfaWithStates_0(4, 18, 35);
      }
      if ((paramLong2 & 0x20000000) != 0L) {
        return jjStartNfaWithStates_0(4, 29, 35);
      }
      if ((paramLong2 & 0x800000000000) != 0L) {
        return jjStartNfaWithStates_0(4, 47, 35);
      }
      return jjMoveStringLiteralDfa5_0(paramLong2, 288230376151711744L, paramLong4, 562949953421312L, paramLong6, 2L);
    case 'u': 
      return jjMoveStringLiteralDfa5_0(paramLong2, 1048576L, paramLong4, 0L, paramLong6, 0L);
    case 'v': 
      return jjMoveStringLiteralDfa5_0(paramLong2, 549755813888L, paramLong4, 0L, paramLong6, 0L);
    case 'w': 
      if ((paramLong2 & 0x20000000000000) != 0L)
      {
        this.jjmatchedKind = 53;
        this.jjmatchedPos = 4;
      }
      return jjMoveStringLiteralDfa5_0(paramLong2, 18014398509481984L, paramLong4, 43980465111040L, paramLong6, 0L);
    }
    return jjStartNfa_0(3, paramLong2, paramLong4, paramLong6);
  }
  
  private final int jjMoveStringLiteralDfa5_0(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6)
  {
    if ((paramLong2 &= paramLong1 | paramLong4 &= paramLong3 | paramLong6 &= paramLong5) == 0L) {
      return jjStartNfa_0(3, paramLong1, paramLong3, paramLong5);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(4, paramLong2, paramLong4, paramLong6);
      return 5;
    }
    switch (this.curChar)
    {
    case '_': 
      return jjMoveStringLiteralDfa6_0(paramLong2, 0L, paramLong4, 562949953421312L, paramLong6, 2L);
    case 'a': 
      return jjMoveStringLiteralDfa6_0(paramLong2, 3072L, paramLong4, 576460752303423488L, paramLong6, 0L);
    case 'c': 
      if ((paramLong2 & 0x200000000000) != 0L) {
        return jjStartNfaWithStates_0(5, 45, 35);
      }
      if ((paramLong2 & 0x1000000000000) != 0L) {
        return jjStartNfaWithStates_0(5, 48, 35);
      }
      return jjMoveStringLiteralDfa6_0(paramLong2, 17592186044416L, paramLong4, 0L, paramLong6, 0L);
    case 'd': 
      return jjMoveStringLiteralDfa6_0(paramLong2, 33554432L, paramLong4, 0L, paramLong6, 0L);
    case 'e': 
      if ((paramLong2 & 0x400000) != 0L) {
        return jjStartNfaWithStates_0(5, 22, 35);
      }
      if ((paramLong2 & 0x8000000000) != 0L) {
        return jjStartNfaWithStates_0(5, 39, 35);
      }
      break;
    case 'f': 
      return jjMoveStringLiteralDfa6_0(paramLong2, 137438953472L, paramLong4, 0L, paramLong6, 0L);
    case 'g': 
      return jjMoveStringLiteralDfa6_0(paramLong2, 4398046511104L, paramLong4, 0L, paramLong6, 0L);
    case 'h': 
      if ((paramLong2 & 0x4000000000000) != 0L) {
        return jjStartNfaWithStates_0(5, 50, 35);
      }
      break;
    case 'i': 
      return jjMoveStringLiteralDfa6_0(paramLong2, 292733975779082240L, paramLong4, 43980465111040L, paramLong6, 0L);
    case 'l': 
      return jjMoveStringLiteralDfa6_0(paramLong2, 269484032L, paramLong4, 0L, paramLong6, 0L);
    case 'm': 
      return jjMoveStringLiteralDfa6_0(paramLong2, 8589934592L, paramLong4, 0L, paramLong6, 0L);
    case 'n': 
      if ((paramLong2 & 0x400000000000) != 0L) {
        return jjStartNfaWithStates_0(5, 46, 35);
      }
      return jjMoveStringLiteralDfa6_0(paramLong2, 34360262656L, paramLong4, 0L, paramLong6, 0L);
    case 'r': 
      return jjMoveStringLiteralDfa6_0(paramLong2, 2251799813685248L, paramLong4, 0L, paramLong6, 0L);
    case 's': 
      if ((paramLong2 & 0x40000000000000) != 0L) {
        return jjStartNfaWithStates_0(5, 54, 35);
      }
      return jjMoveStringLiteralDfa6_0(paramLong2, 0L, paramLong4, 2305843009213693952L, paramLong6, 0L);
    case 't': 
      if ((paramLong2 & 0x400000000) != 0L) {
        return jjStartNfaWithStates_0(5, 34, 35);
      }
      return jjMoveStringLiteralDfa6_0(paramLong2, 571746046443520L, paramLong4, 11258999068426240L, paramLong6, 40L);
    }
    return jjStartNfa_0(4, paramLong2, paramLong4, paramLong6);
  }
  
  private final int jjMoveStringLiteralDfa6_0(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6)
  {
    if ((paramLong2 &= paramLong1 | paramLong4 &= paramLong3 | paramLong6 &= paramLong5) == 0L) {
      return jjStartNfa_0(4, paramLong1, paramLong3, paramLong5);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(5, paramLong2, paramLong4, paramLong6);
      return 6;
    }
    switch (this.curChar)
    {
    case '_': 
      return jjMoveStringLiteralDfa7_0(paramLong2, 0L, paramLong4, 11258999068426240L, paramLong6, 40L);
    case 'a': 
      return jjMoveStringLiteralDfa7_0(paramLong2, 137438953472L, paramLong4, 0L, paramLong6, 0L);
    case 'c': 
      return jjMoveStringLiteralDfa7_0(paramLong2, 34359739392L, paramLong4, 0L, paramLong6, 0L);
    case 'e': 
      if ((paramLong2 & 0x40000000000) != 0L) {
        return jjStartNfaWithStates_0(6, 42, 35);
      }
      if ((paramLong2 & 0x80000000000) != 0L) {
        return jjStartNfaWithStates_0(6, 43, 35);
      }
      return jjMoveStringLiteralDfa7_0(paramLong2, 4503608217305088L, paramLong4, 0L, paramLong6, 0L);
    case 'f': 
      return jjMoveStringLiteralDfa7_0(paramLong2, 562949953421312L, paramLong4, 0L, paramLong6, 0L);
    case 'l': 
      return jjMoveStringLiteralDfa7_0(paramLong2, 288230376151711744L, paramLong4, 0L, paramLong6, 0L);
    case 'n': 
      if ((paramLong2 & 0x800) != 0L) {
        return jjStartNfaWithStates_0(6, 11, 35);
      }
      break;
    case 'o': 
      return jjMoveStringLiteralDfa7_0(paramLong2, 2251799813685248L, paramLong4, 0L, paramLong6, 0L);
    case 's': 
      if ((paramLong2 & 0x2000000) != 0L) {
        return jjStartNfaWithStates_0(6, 25, 35);
      }
      return jjMoveStringLiteralDfa7_0(paramLong2, 0L, paramLong4, 2882910691935649792L, paramLong6, 2L);
    case 't': 
      if ((paramLong2 & 0x100000) != 0L) {
        return jjStartNfaWithStates_0(6, 20, 35);
      }
      return jjMoveStringLiteralDfa7_0(paramLong2, 17592186044416L, paramLong4, 0L, paramLong6, 0L);
    case 'u': 
      return jjMoveStringLiteralDfa7_0(paramLong2, 524288L, paramLong4, 0L, paramLong6, 0L);
    case 'y': 
      if ((paramLong2 & 0x10000000) != 0L) {
        return jjStartNfaWithStates_0(6, 28, 35);
      }
      break;
    }
    return jjStartNfa_0(5, paramLong2, paramLong4, paramLong6);
  }
  
  private final int jjMoveStringLiteralDfa7_0(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6)
  {
    if ((paramLong2 &= paramLong1 | paramLong4 &= paramLong3 | paramLong6 &= paramLong5) == 0L) {
      return jjStartNfa_0(5, paramLong1, paramLong3, paramLong5);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(6, paramLong2, paramLong4, paramLong6);
      return 7;
    }
    switch (this.curChar)
    {
    case 'c': 
      return jjMoveStringLiteralDfa8_0(paramLong2, 137438953472L, paramLong4, 0L, paramLong6, 0L);
    case 'e': 
      if ((paramLong2 & 0x80000) != 0L) {
        return jjStartNfaWithStates_0(7, 19, 35);
      }
      if ((paramLong2 & 0x400000000000000) != 0L) {
        return jjStartNfaWithStates_0(7, 58, 35);
      }
      return jjMoveStringLiteralDfa8_0(paramLong2, 17626545782784L, paramLong4, 43980465111040L, paramLong6, 0L);
    case 'h': 
      return jjMoveStringLiteralDfa8_0(paramLong2, 0L, paramLong4, 562949953421312L, paramLong6, 2L);
    case 'i': 
      return jjMoveStringLiteralDfa8_0(paramLong2, 0L, paramLong4, 2305843009213693952L, paramLong6, 0L);
    case 'n': 
      return jjMoveStringLiteralDfa8_0(paramLong2, 6755408030990336L, paramLong4, 0L, paramLong6, 0L);
    case 'p': 
      if ((paramLong2 & 0x2000000000000) != 0L) {
        return jjStartNfaWithStates_0(7, 49, 35);
      }
      break;
    case 's': 
      return jjMoveStringLiteralDfa8_0(paramLong2, 0L, paramLong4, 578712552117108736L, paramLong6, 8L);
    case 't': 
      if ((paramLong2 & 0x400) != 0L) {
        return jjStartNfaWithStates_0(7, 10, 35);
      }
      break;
    case 'u': 
      return jjMoveStringLiteralDfa8_0(paramLong2, 0L, paramLong4, 9007199254740992L, paramLong6, 32L);
    }
    return jjStartNfa_0(6, paramLong2, paramLong4, paramLong6);
  }
  
  private final int jjMoveStringLiteralDfa8_0(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6)
  {
    if ((paramLong2 &= paramLong1 | paramLong4 &= paramLong3 | paramLong6 &= paramLong5) == 0L) {
      return jjStartNfa_0(6, paramLong1, paramLong3, paramLong5);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(7, paramLong2, paramLong4, paramLong6);
      return 8;
    }
    switch (this.curChar)
    {
    case '_': 
      return jjMoveStringLiteralDfa9_0(paramLong2, 0L, paramLong4, 43980465111040L, paramLong6, 0L);
    case 'd': 
      if ((paramLong2 & 0x100000000000) != 0L) {
        return jjStartNfaWithStates_0(8, 44, 35);
      }
      break;
    case 'e': 
      if ((paramLong2 & 0x2000000000) != 0L) {
        return jjStartNfaWithStates_0(8, 37, 35);
      }
      break;
    case 'g': 
      return jjMoveStringLiteralDfa9_0(paramLong2, 0L, paramLong4, 2305843009213693952L, paramLong6, 0L);
    case 'h': 
      return jjMoveStringLiteralDfa9_0(paramLong2, 0L, paramLong4, 2251799813685248L, paramLong6, 8L);
    case 'i': 
      return jjMoveStringLiteralDfa9_0(paramLong2, 2251799813685248L, paramLong4, 577023702256844800L, paramLong6, 2L);
    case 'n': 
      return jjMoveStringLiteralDfa9_0(paramLong2, 0L, paramLong4, 9007199254740992L, paramLong6, 32L);
    case 'o': 
      return jjMoveStringLiteralDfa9_0(paramLong2, 34359738368L, paramLong4, 0L, paramLong6, 0L);
    case 't': 
      if ((paramLong2 & 0x10000000000000) != 0L) {
        return jjStartNfaWithStates_0(8, 52, 35);
      }
      return jjMoveStringLiteralDfa9_0(paramLong2, 8589934592L, paramLong4, 0L, paramLong6, 0L);
    }
    return jjStartNfa_0(7, paramLong2, paramLong4, paramLong6);
  }
  
  private final int jjMoveStringLiteralDfa9_0(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6)
  {
    if ((paramLong2 &= paramLong1 | paramLong4 &= paramLong3 | paramLong6 &= paramLong5) == 0L) {
      return jjStartNfa_0(7, paramLong1, paramLong3, paramLong5);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(8, paramLong2, paramLong4, paramLong6);
      return 9;
    }
    switch (this.curChar)
    {
    case 'a': 
      return jjMoveStringLiteralDfa10_0(paramLong2, 0L, paramLong4, 8796093022208L, paramLong6, 0L);
    case 'f': 
      if ((paramLong2 & 0x800000000) != 0L) {
        return jjStartNfaWithStates_0(9, 35, 35);
      }
      return jjMoveStringLiteralDfa10_0(paramLong2, 0L, paramLong4, 562949953421312L, paramLong6, 2L);
    case 'g': 
      return jjMoveStringLiteralDfa10_0(paramLong2, 0L, paramLong4, 576460752303423488L, paramLong6, 0L);
    case 'i': 
      return jjMoveStringLiteralDfa10_0(paramLong2, 0L, paramLong4, 2251799813685248L, paramLong6, 8L);
    case 'n': 
      if ((paramLong4 & 0x2000000000000000) != 0L) {
        return jjStopAtPos(9, 125);
      }
      break;
    case 'o': 
      return jjMoveStringLiteralDfa10_0(paramLong2, 0L, paramLong4, 35184372088832L, paramLong6, 0L);
    case 's': 
      if ((paramLong2 & 0x200000000) != 0L) {
        return jjStartNfaWithStates_0(9, 33, 35);
      }
      return jjMoveStringLiteralDfa10_0(paramLong2, 0L, paramLong4, 9007199254740992L, paramLong6, 32L);
    case 'z': 
      return jjMoveStringLiteralDfa10_0(paramLong2, 2251799813685248L, paramLong4, 0L, paramLong6, 0L);
    }
    return jjStartNfa_0(8, paramLong2, paramLong4, paramLong6);
  }
  
  private final int jjMoveStringLiteralDfa10_0(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6)
  {
    if ((paramLong2 &= paramLong1 | paramLong4 &= paramLong3 | paramLong6 &= paramLong5) == 0L) {
      return jjStartNfa_0(8, paramLong1, paramLong3, paramLong5);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(9, paramLong2, paramLong4, paramLong6);
      return 10;
    }
    switch (this.curChar)
    {
    case 'e': 
      return jjMoveStringLiteralDfa11_0(paramLong2, 2251799813685248L, paramLong4, 0L, paramLong6, 0L);
    case 'f': 
      return jjMoveStringLiteralDfa11_0(paramLong2, 0L, paramLong4, 2251799813685248L, paramLong6, 8L);
    case 'i': 
      return jjMoveStringLiteralDfa11_0(paramLong2, 0L, paramLong4, 9007199254740992L, paramLong6, 32L);
    case 'n': 
      if ((paramLong4 & 0x800000000000000) != 0L) {
        return jjStopAtPos(10, 123);
      }
      return jjMoveStringLiteralDfa11_0(paramLong2, 0L, paramLong4, 8796093022208L, paramLong6, 0L);
    case 'r': 
      if ((paramLong4 & 0x200000000000) != 0L) {
        return jjStopAtPos(10, 109);
      }
      break;
    case 't': 
      if ((paramLong4 & 0x2000000000000) != 0L)
      {
        this.jjmatchedKind = 113;
        this.jjmatchedPos = 10;
      }
      return jjMoveStringLiteralDfa11_0(paramLong2, 0L, paramLong4, 0L, paramLong6, 2L);
    }
    return jjStartNfa_0(9, paramLong2, paramLong4, paramLong6);
  }
  
  private final int jjMoveStringLiteralDfa11_0(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6)
  {
    if ((paramLong2 &= paramLong1 | paramLong4 &= paramLong3 | paramLong6 &= paramLong5) == 0L) {
      return jjStartNfa_0(9, paramLong1, paramLong3, paramLong5);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(10, paramLong2, paramLong4, paramLong6);
      return 11;
    }
    switch (this.curChar)
    {
    case '_': 
      return jjMoveStringLiteralDfa12_0(paramLong2, 0L, paramLong4, 0L, paramLong6, 2L);
    case 'd': 
      if ((paramLong2 & 0x8000000000000) != 0L) {
        return jjStartNfaWithStates_0(11, 51, 35);
      }
      if ((paramLong4 & 0x80000000000) != 0L) {
        return jjStopAtPos(11, 107);
      }
      break;
    case 'g': 
      return jjMoveStringLiteralDfa12_0(paramLong2, 0L, paramLong4, 9007199254740992L, paramLong6, 32L);
    case 't': 
      if ((paramLong4 & 0x8000000000000) != 0L)
      {
        this.jjmatchedKind = 115;
        this.jjmatchedPos = 11;
      }
      return jjMoveStringLiteralDfa12_0(paramLong2, 0L, paramLong4, 0L, paramLong6, 8L);
    }
    return jjStartNfa_0(10, paramLong2, paramLong4, paramLong6);
  }
  
  private final int jjMoveStringLiteralDfa12_0(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6)
  {
    if ((paramLong2 &= paramLong1 | paramLong4 &= paramLong3 | paramLong6 &= paramLong5) == 0L) {
      return jjStartNfa_0(10, paramLong1, paramLong3, paramLong5);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(11, 0L, paramLong4, paramLong6);
      return 12;
    }
    switch (this.curChar)
    {
    case '_': 
      return jjMoveStringLiteralDfa13_0(paramLong4, 0L, paramLong6, 8L);
    case 'a': 
      return jjMoveStringLiteralDfa13_0(paramLong4, 0L, paramLong6, 2L);
    case 'n': 
      return jjMoveStringLiteralDfa13_0(paramLong4, 9007199254740992L, paramLong6, 32L);
    }
    return jjStartNfa_0(11, 0L, paramLong4, paramLong6);
  }
  
  private final int jjMoveStringLiteralDfa13_0(long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    if ((paramLong2 &= paramLong1 | paramLong4 &= paramLong3) == 0L) {
      return jjStartNfa_0(11, 0L, paramLong1, paramLong3);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(12, 0L, paramLong2, paramLong4);
      return 13;
    }
    switch (this.curChar)
    {
    case 'a': 
      return jjMoveStringLiteralDfa14_0(paramLong2, 0L, paramLong4, 8L);
    case 'e': 
      return jjMoveStringLiteralDfa14_0(paramLong2, 9007199254740992L, paramLong4, 32L);
    case 's': 
      return jjMoveStringLiteralDfa14_0(paramLong2, 0L, paramLong4, 2L);
    }
    return jjStartNfa_0(12, 0L, paramLong2, paramLong4);
  }
  
  private final int jjMoveStringLiteralDfa14_0(long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    if ((paramLong2 &= paramLong1 | paramLong4 &= paramLong3) == 0L) {
      return jjStartNfa_0(12, 0L, paramLong1, paramLong3);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(13, 0L, paramLong2, paramLong4);
      return 14;
    }
    switch (this.curChar)
    {
    case 'd': 
      return jjMoveStringLiteralDfa15_0(paramLong2, 9007199254740992L, paramLong4, 32L);
    case 's': 
      return jjMoveStringLiteralDfa15_0(paramLong2, 0L, paramLong4, 10L);
    }
    return jjStartNfa_0(13, 0L, paramLong2, paramLong4);
  }
  
  private final int jjMoveStringLiteralDfa15_0(long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    if ((paramLong2 &= paramLong1 | paramLong4 &= paramLong3) == 0L) {
      return jjStartNfa_0(13, 0L, paramLong1, paramLong3);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(14, 0L, paramLong2, paramLong4);
      return 15;
    }
    switch (this.curChar)
    {
    case '_': 
      return jjMoveStringLiteralDfa16_0(paramLong2, 9007199254740992L, paramLong4, 32L);
    case 'i': 
      return jjMoveStringLiteralDfa16_0(paramLong2, 0L, paramLong4, 2L);
    case 's': 
      return jjMoveStringLiteralDfa16_0(paramLong2, 0L, paramLong4, 8L);
    }
    return jjStartNfa_0(14, 0L, paramLong2, paramLong4);
  }
  
  private final int jjMoveStringLiteralDfa16_0(long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    if ((paramLong2 &= paramLong1 | paramLong4 &= paramLong3) == 0L) {
      return jjStartNfa_0(14, 0L, paramLong1, paramLong3);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(15, 0L, paramLong2, paramLong4);
      return 16;
    }
    switch (this.curChar)
    {
    case 'g': 
      return jjMoveStringLiteralDfa17_0(paramLong2, 0L, paramLong4, 2L);
    case 'i': 
      return jjMoveStringLiteralDfa17_0(paramLong2, 0L, paramLong4, 8L);
    case 's': 
      return jjMoveStringLiteralDfa17_0(paramLong2, 9007199254740992L, paramLong4, 32L);
    }
    return jjStartNfa_0(15, 0L, paramLong2, paramLong4);
  }
  
  private final int jjMoveStringLiteralDfa17_0(long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    if ((paramLong2 &= paramLong1 | paramLong4 &= paramLong3) == 0L) {
      return jjStartNfa_0(15, 0L, paramLong1, paramLong3);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(16, 0L, paramLong2, paramLong4);
      return 17;
    }
    switch (this.curChar)
    {
    case 'g': 
      return jjMoveStringLiteralDfa18_0(paramLong2, 0L, paramLong4, 8L);
    case 'h': 
      return jjMoveStringLiteralDfa18_0(paramLong2, 9007199254740992L, paramLong4, 32L);
    case 'n': 
      if ((paramLong4 & 0x2) != 0L) {
        return jjStopAtPos(17, 129);
      }
      break;
    }
    return jjStartNfa_0(16, 0L, paramLong2, paramLong4);
  }
  
  private final int jjMoveStringLiteralDfa18_0(long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    if ((paramLong2 &= paramLong1 | paramLong4 &= paramLong3) == 0L) {
      return jjStartNfa_0(16, 0L, paramLong1, paramLong3);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(17, 0L, paramLong2, paramLong4);
      return 18;
    }
    switch (this.curChar)
    {
    case 'i': 
      return jjMoveStringLiteralDfa19_0(paramLong2, 9007199254740992L, paramLong4, 32L);
    case 'n': 
      if ((paramLong4 & 0x8) != 0L) {
        return jjStopAtPos(18, 131);
      }
      break;
    }
    return jjStartNfa_0(17, 0L, paramLong2, paramLong4);
  }
  
  private final int jjMoveStringLiteralDfa19_0(long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    if ((paramLong2 &= paramLong1 | paramLong4 &= paramLong3) == 0L) {
      return jjStartNfa_0(17, 0L, paramLong1, paramLong3);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(18, 0L, paramLong2, paramLong4);
      return 19;
    }
    switch (this.curChar)
    {
    case 'f': 
      return jjMoveStringLiteralDfa20_0(paramLong2, 9007199254740992L, paramLong4, 32L);
    }
    return jjStartNfa_0(18, 0L, paramLong2, paramLong4);
  }
  
  private final int jjMoveStringLiteralDfa20_0(long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    if ((paramLong2 &= paramLong1 | paramLong4 &= paramLong3) == 0L) {
      return jjStartNfa_0(18, 0L, paramLong1, paramLong3);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(19, 0L, paramLong2, paramLong4);
      return 20;
    }
    switch (this.curChar)
    {
    case 't': 
      if ((paramLong2 & 0x20000000000000) != 0L)
      {
        this.jjmatchedKind = 117;
        this.jjmatchedPos = 20;
      }
      return jjMoveStringLiteralDfa21_0(paramLong2, 0L, paramLong4, 32L);
    }
    return jjStartNfa_0(19, 0L, paramLong2, paramLong4);
  }
  
  private final int jjMoveStringLiteralDfa21_0(long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    if ((paramLong2 &= paramLong1 | paramLong4 &= paramLong3) == 0L) {
      return jjStartNfa_0(19, 0L, paramLong1, paramLong3);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(20, 0L, 0L, paramLong4);
      return 21;
    }
    switch (this.curChar)
    {
    case '_': 
      return jjMoveStringLiteralDfa22_0(paramLong4, 32L);
    }
    return jjStartNfa_0(20, 0L, 0L, paramLong4);
  }
  
  private final int jjMoveStringLiteralDfa22_0(long paramLong1, long paramLong2)
  {
    if ((paramLong2 &= paramLong1) == 0L) {
      return jjStartNfa_0(20, 0L, 0L, paramLong1);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(21, 0L, 0L, paramLong2);
      return 22;
    }
    switch (this.curChar)
    {
    case 'a': 
      return jjMoveStringLiteralDfa23_0(paramLong2, 32L);
    }
    return jjStartNfa_0(21, 0L, 0L, paramLong2);
  }
  
  private final int jjMoveStringLiteralDfa23_0(long paramLong1, long paramLong2)
  {
    if ((paramLong2 &= paramLong1) == 0L) {
      return jjStartNfa_0(21, 0L, 0L, paramLong1);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(22, 0L, 0L, paramLong2);
      return 23;
    }
    switch (this.curChar)
    {
    case 's': 
      return jjMoveStringLiteralDfa24_0(paramLong2, 32L);
    }
    return jjStartNfa_0(22, 0L, 0L, paramLong2);
  }
  
  private final int jjMoveStringLiteralDfa24_0(long paramLong1, long paramLong2)
  {
    if ((paramLong2 &= paramLong1) == 0L) {
      return jjStartNfa_0(22, 0L, 0L, paramLong1);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(23, 0L, 0L, paramLong2);
      return 24;
    }
    switch (this.curChar)
    {
    case 's': 
      return jjMoveStringLiteralDfa25_0(paramLong2, 32L);
    }
    return jjStartNfa_0(23, 0L, 0L, paramLong2);
  }
  
  private final int jjMoveStringLiteralDfa25_0(long paramLong1, long paramLong2)
  {
    if ((paramLong2 &= paramLong1) == 0L) {
      return jjStartNfa_0(23, 0L, 0L, paramLong1);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(24, 0L, 0L, paramLong2);
      return 25;
    }
    switch (this.curChar)
    {
    case 'i': 
      return jjMoveStringLiteralDfa26_0(paramLong2, 32L);
    }
    return jjStartNfa_0(24, 0L, 0L, paramLong2);
  }
  
  private final int jjMoveStringLiteralDfa26_0(long paramLong1, long paramLong2)
  {
    if ((paramLong2 &= paramLong1) == 0L) {
      return jjStartNfa_0(24, 0L, 0L, paramLong1);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(25, 0L, 0L, paramLong2);
      return 26;
    }
    switch (this.curChar)
    {
    case 'g': 
      return jjMoveStringLiteralDfa27_0(paramLong2, 32L);
    }
    return jjStartNfa_0(25, 0L, 0L, paramLong2);
  }
  
  private final int jjMoveStringLiteralDfa27_0(long paramLong1, long paramLong2)
  {
    if ((paramLong2 &= paramLong1) == 0L) {
      return jjStartNfa_0(25, 0L, 0L, paramLong1);
    }
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      jjStopStringLiteralDfa_0(26, 0L, 0L, paramLong2);
      return 27;
    }
    switch (this.curChar)
    {
    case 'n': 
      if ((paramLong2 & 0x20) != 0L) {
        return jjStopAtPos(27, 133);
      }
      break;
    }
    return jjStartNfa_0(26, 0L, 0L, paramLong2);
  }
  
  private final void jjCheckNAdd(int paramInt)
  {
    if (this.jjrounds[paramInt] != this.jjround)
    {
      this.jjstateSet[(this.jjnewStateCnt++)] = paramInt;
      this.jjrounds[paramInt] = this.jjround;
    }
  }
  
  private final void jjAddStates(int paramInt1, int paramInt2)
  {
    do
    {
      this.jjstateSet[(this.jjnewStateCnt++)] = jjnextStates[paramInt1];
    } while (paramInt1++ != paramInt2);
  }
  
  private final void jjCheckNAddTwoStates(int paramInt1, int paramInt2)
  {
    jjCheckNAdd(paramInt1);
    jjCheckNAdd(paramInt2);
  }
  
  private final void jjCheckNAddStates(int paramInt1, int paramInt2)
  {
    do
    {
      jjCheckNAdd(jjnextStates[paramInt1]);
    } while (paramInt1++ != paramInt2);
  }
  
  private final void jjCheckNAddStates(int paramInt)
  {
    jjCheckNAdd(jjnextStates[paramInt]);
    jjCheckNAdd(jjnextStates[(paramInt + 1)]);
  }
  
  private final int jjMoveNfa_0(int paramInt1, int paramInt2)
  {
    int i = 0;
    this.jjnewStateCnt = 74;
    int j = 1;
    this.jjstateSet[0] = paramInt1;
    int k = Integer.MAX_VALUE;
    for (;;)
    {
      if (++this.jjround == Integer.MAX_VALUE) {
        ReInitRounds();
      }
      long l1;
      if (this.curChar < '@')
      {
        l1 = 1L << this.curChar;
        do
        {
          switch (this.jjstateSet[(--j)])
          {
          case 6: 
            if ((0x1FFFFFFFF & l1) != 0L)
            {
              if (k > 6) {
                k = 6;
              }
              jjCheckNAdd(0);
            }
            else if ((0x3FF000000000000 & l1) != 0L)
            {
              jjCheckNAddStates(0, 6);
            }
            else if (this.curChar == '/')
            {
              jjAddStates(7, 9);
            }
            else if (this.curChar == '$')
            {
              if (k > 69) {
                k = 69;
              }
              jjCheckNAdd(35);
            }
            else if (this.curChar == '"')
            {
              jjCheckNAddStates(10, 12);
            }
            else if (this.curChar == '\'')
            {
              jjAddStates(13, 14);
            }
            else if (this.curChar == '.')
            {
              jjCheckNAdd(11);
            }
            else if (this.curChar == '#')
            {
              this.jjstateSet[(this.jjnewStateCnt++)] = 1;
            }
            if ((0x3FE000000000000 & l1) != 0L)
            {
              if (k > 60) {
                k = 60;
              }
              jjCheckNAddTwoStates(8, 9);
            }
            else if (this.curChar == '0')
            {
              if (k > 60) {
                k = 60;
              }
              jjCheckNAddStates(15, 17);
            }
            break;
          case 56: 
            if (this.curChar == '*')
            {
              this.jjstateSet[(this.jjnewStateCnt++)] = 67;
            }
            else if (this.curChar == '/')
            {
              if (k > 7) {
                k = 7;
              }
              jjCheckNAddStates(18, 20);
            }
            if (this.curChar == '*') {
              jjCheckNAdd(62);
            }
            break;
          case 0: 
            if ((0x1FFFFFFFF & l1) != 0L)
            {
              if (k > 6) {
                k = 6;
              }
              jjCheckNAdd(0);
            }
            break;
          case 1: 
            if (this.curChar == '!') {
              jjCheckNAddStates(21, 23);
            }
            break;
          case 2: 
            if ((0xFFFFFFFFFFFFDBFF & l1) != 0L) {
              jjCheckNAddStates(21, 23);
            }
            break;
          case 3: 
            if (((0x2400 & l1) != 0L) && (k > 8)) {
              k = 8;
            }
            break;
          case 4: 
            if ((this.curChar == '\n') && (k > 8)) {
              k = 8;
            }
            break;
          case 5: 
            if (this.curChar == '\r') {
              this.jjstateSet[(this.jjnewStateCnt++)] = 4;
            }
            break;
          case 7: 
            if ((0x3FE000000000000 & l1) != 0L)
            {
              if (k > 60) {
                k = 60;
              }
              jjCheckNAddTwoStates(8, 9);
            }
            break;
          case 8: 
            if ((0x3FF000000000000 & l1) != 0L)
            {
              if (k > 60) {
                k = 60;
              }
              jjCheckNAddTwoStates(8, 9);
            }
            break;
          case 10: 
            if (this.curChar == '.') {
              jjCheckNAdd(11);
            }
            break;
          case 11: 
            if ((0x3FF000000000000 & l1) != 0L)
            {
              if (k > 64) {
                k = 64;
              }
              jjCheckNAddStates(24, 26);
            }
            break;
          case 13: 
            if ((0x280000000000 & l1) != 0L) {
              jjCheckNAdd(14);
            }
            break;
          case 14: 
            if ((0x3FF000000000000 & l1) != 0L)
            {
              if (k > 64) {
                k = 64;
              }
              jjCheckNAddTwoStates(14, 15);
            }
            break;
          case 16: 
            if (this.curChar == '\'') {
              jjAddStates(13, 14);
            }
            break;
          case 17: 
            if ((0xFFFFFF7FFFFFDBFF & l1) != 0L) {
              jjCheckNAdd(18);
            }
            break;
          case 18: 
            if ((this.curChar == '\'') && (k > 66)) {
              k = 66;
            }
            break;
          case 20: 
            if ((0x8400000000 & l1) != 0L) {
              jjCheckNAdd(18);
            }
            break;
          case 21: 
            if ((0xFF000000000000 & l1) != 0L) {
              jjCheckNAddTwoStates(22, 18);
            }
            break;
          case 22: 
            if ((0xFF000000000000 & l1) != 0L) {
              jjCheckNAdd(18);
            }
            break;
          case 23: 
            if ((0xF000000000000 & l1) != 0L) {
              this.jjstateSet[(this.jjnewStateCnt++)] = 24;
            }
            break;
          case 24: 
            if ((0xFF000000000000 & l1) != 0L) {
              jjCheckNAdd(22);
            }
            break;
          case 25: 
            if (this.curChar == '"') {
              jjCheckNAddStates(10, 12);
            }
            break;
          case 26: 
            if ((0xFFFFFFFBFFFFDBFF & l1) != 0L) {
              jjCheckNAddStates(10, 12);
            }
            break;
          case 28: 
            if ((0x8400000000 & l1) != 0L) {
              jjCheckNAddStates(10, 12);
            }
            break;
          case 29: 
            if ((this.curChar == '"') && (k > 67)) {
              k = 67;
            }
            break;
          case 30: 
            if ((0xFF000000000000 & l1) != 0L) {
              jjCheckNAddStates(27, 30);
            }
            break;
          case 31: 
            if ((0xFF000000000000 & l1) != 0L) {
              jjCheckNAddStates(10, 12);
            }
            break;
          case 32: 
            if ((0xF000000000000 & l1) != 0L) {
              this.jjstateSet[(this.jjnewStateCnt++)] = 33;
            }
            break;
          case 33: 
            if ((0xFF000000000000 & l1) != 0L) {
              jjCheckNAdd(31);
            }
            break;
          case 34: 
            if (this.curChar == '$')
            {
              if (k > 69) {
                k = 69;
              }
              jjCheckNAdd(35);
            }
            break;
          case 35: 
            if ((0x3FF001000000000 & l1) != 0L)
            {
              if (k > 69) {
                k = 69;
              }
              jjCheckNAdd(35);
            }
            break;
          case 36: 
            if ((0x3FF000000000000 & l1) != 0L) {
              jjCheckNAddStates(0, 6);
            }
            break;
          case 37: 
            if ((0x3FF000000000000 & l1) != 0L) {
              jjCheckNAddTwoStates(37, 38);
            }
            break;
          case 38: 
            if (this.curChar == '.')
            {
              if (k > 64) {
                k = 64;
              }
              jjCheckNAddStates(31, 33);
            }
            break;
          case 39: 
            if ((0x3FF000000000000 & l1) != 0L)
            {
              if (k > 64) {
                k = 64;
              }
              jjCheckNAddStates(31, 33);
            }
            break;
          case 41: 
            if ((0x280000000000 & l1) != 0L) {
              jjCheckNAdd(42);
            }
            break;
          case 42: 
            if ((0x3FF000000000000 & l1) != 0L)
            {
              if (k > 64) {
                k = 64;
              }
              jjCheckNAddTwoStates(42, 15);
            }
            break;
          case 43: 
            if ((0x3FF000000000000 & l1) != 0L) {
              jjCheckNAddTwoStates(43, 44);
            }
            break;
          case 45: 
            if ((0x280000000000 & l1) != 0L) {
              jjCheckNAdd(46);
            }
            break;
          case 46: 
            if ((0x3FF000000000000 & l1) != 0L)
            {
              if (k > 64) {
                k = 64;
              }
              jjCheckNAddTwoStates(46, 15);
            }
            break;
          case 47: 
            if ((0x3FF000000000000 & l1) != 0L) {
              jjCheckNAddStates(34, 36);
            }
            break;
          case 49: 
            if ((0x280000000000 & l1) != 0L) {
              jjCheckNAdd(50);
            }
            break;
          case 50: 
            if ((0x3FF000000000000 & l1) != 0L) {
              jjCheckNAddTwoStates(50, 15);
            }
            break;
          case 51: 
            if (this.curChar == '0')
            {
              if (k > 60) {
                k = 60;
              }
              jjCheckNAddStates(15, 17);
            }
            break;
          case 53: 
            if ((0x3FF000000000000 & l1) != 0L)
            {
              if (k > 60) {
                k = 60;
              }
              jjCheckNAddTwoStates(53, 9);
            }
            break;
          case 54: 
            if ((0xFF000000000000 & l1) != 0L)
            {
              if (k > 60) {
                k = 60;
              }
              jjCheckNAddTwoStates(54, 9);
            }
            break;
          case 55: 
            if (this.curChar == '/') {
              jjAddStates(7, 9);
            }
            break;
          case 57: 
            if ((0xFFFFFFFFFFFFDBFF & l1) != 0L)
            {
              if (k > 7) {
                k = 7;
              }
              jjCheckNAddStates(18, 20);
            }
            break;
          case 58: 
            if (((0x2400 & l1) != 0L) && (k > 7)) {
              k = 7;
            }
            break;
          case 59: 
            if ((this.curChar == '\n') && (k > 7)) {
              k = 7;
            }
            break;
          case 60: 
            if (this.curChar == '\r') {
              this.jjstateSet[(this.jjnewStateCnt++)] = 59;
            }
            break;
          case 61: 
            if (this.curChar == '*') {
              jjCheckNAdd(62);
            }
            break;
          case 62: 
            if ((0xFFFFFBFFFFFFFFFF & l1) != 0L) {
              jjCheckNAddTwoStates(62, 63);
            }
            break;
          case 63: 
            if (this.curChar == '*') {
              jjCheckNAddStates(37, 39);
            }
            break;
          case 64: 
            if ((0xFFFF7BFFFFFFFFFF & l1) != 0L) {
              jjCheckNAddTwoStates(65, 63);
            }
            break;
          case 65: 
            if ((0xFFFFFBFFFFFFFFFF & l1) != 0L) {
              jjCheckNAddTwoStates(65, 63);
            }
            break;
          case 66: 
            if ((this.curChar == '/') && (k > 9)) {
              k = 9;
            }
            break;
          case 67: 
            if (this.curChar == '*') {
              jjCheckNAddTwoStates(68, 69);
            }
            break;
          case 68: 
            if ((0xFFFFFBFFFFFFFFFF & l1) != 0L) {
              jjCheckNAddTwoStates(68, 69);
            }
            break;
          case 69: 
            if (this.curChar == '*') {
              jjCheckNAddStates(40, 42);
            }
            break;
          case 70: 
            if ((0xFFFF7BFFFFFFFFFF & l1) != 0L) {
              jjCheckNAddTwoStates(71, 69);
            }
            break;
          case 71: 
            if ((0xFFFFFBFFFFFFFFFF & l1) != 0L) {
              jjCheckNAddTwoStates(71, 69);
            }
            break;
          case 72: 
            if ((this.curChar == '/') && (k > 68)) {
              k = 68;
            }
            break;
          case 73: 
            if (this.curChar == '*') {
              this.jjstateSet[(this.jjnewStateCnt++)] = 67;
            }
            break;
          }
        } while (j != i);
      }
      else if (this.curChar < '')
      {
        l1 = 1L << (this.curChar & 0x3F);
        do
        {
          switch (this.jjstateSet[(--j)])
          {
          case 6: 
          case 35: 
            if ((0x7FFFFFE87FFFFFE & l1) != 0L)
            {
              if (k > 69) {
                k = 69;
              }
              jjCheckNAdd(35);
            }
            break;
          case 2: 
            jjAddStates(21, 23);
            break;
          case 9: 
            if (((0x100000001000 & l1) != 0L) && (k > 60)) {
              k = 60;
            }
            break;
          case 12: 
            if ((0x2000000020 & l1) != 0L) {
              jjAddStates(43, 44);
            }
            break;
          case 15: 
            if (((0x5000000050 & l1) != 0L) && (k > 64)) {
              k = 64;
            }
            break;
          case 17: 
            if ((0xFFFFFFFFEFFFFFFF & l1) != 0L) {
              jjCheckNAdd(18);
            }
            break;
          case 19: 
            if (this.curChar == '\\') {
              jjAddStates(45, 47);
            }
            break;
          case 20: 
            if ((0x14404410000000 & l1) != 0L) {
              jjCheckNAdd(18);
            }
            break;
          case 26: 
            if ((0xFFFFFFFFEFFFFFFF & l1) != 0L) {
              jjCheckNAddStates(10, 12);
            }
            break;
          case 27: 
            if (this.curChar == '\\') {
              jjAddStates(48, 50);
            }
            break;
          case 28: 
            if ((0x14404410000000 & l1) != 0L) {
              jjCheckNAddStates(10, 12);
            }
            break;
          case 40: 
            if ((0x2000000020 & l1) != 0L) {
              jjAddStates(51, 52);
            }
            break;
          case 44: 
            if ((0x2000000020 & l1) != 0L) {
              jjAddStates(53, 54);
            }
            break;
          case 48: 
            if ((0x2000000020 & l1) != 0L) {
              jjAddStates(55, 56);
            }
            break;
          case 52: 
            if ((0x100000001000000 & l1) != 0L) {
              jjCheckNAdd(53);
            }
            break;
          case 53: 
            if ((0x7E0000007E & l1) != 0L)
            {
              if (k > 60) {
                k = 60;
              }
              jjCheckNAddTwoStates(53, 9);
            }
            break;
          case 57: 
            if (k > 7) {
              k = 7;
            }
            jjAddStates(18, 20);
            break;
          case 62: 
            jjCheckNAddTwoStates(62, 63);
            break;
          case 64: 
          case 65: 
            jjCheckNAddTwoStates(65, 63);
            break;
          case 68: 
            jjCheckNAddTwoStates(68, 69);
            break;
          case 70: 
          case 71: 
            jjCheckNAddTwoStates(71, 69);
          }
        } while (j != i);
      }
      else
      {
        int m = this.curChar >> '\b';
        int n = m >> 6;
        long l2 = 1L << (m & 0x3F);
        int i1 = (this.curChar & 0xFF) >> '\006';
        long l3 = 1L << (this.curChar & 0x3F);
        do
        {
          switch (this.jjstateSet[(--j)])
          {
          case 6: 
            if (jjCanMove_0(m, n, i1, l2, l3))
            {
              if (k > 6) {
                k = 6;
              }
              jjCheckNAdd(0);
            }
            if (jjCanMove_2(m, n, i1, l2, l3))
            {
              if (k > 69) {
                k = 69;
              }
              jjCheckNAdd(35);
            }
            break;
          case 0: 
            if (jjCanMove_0(m, n, i1, l2, l3))
            {
              if (k > 6) {
                k = 6;
              }
              jjCheckNAdd(0);
            }
            break;
          case 2: 
            if (jjCanMove_1(m, n, i1, l2, l3)) {
              jjAddStates(21, 23);
            }
            break;
          case 17: 
            if (jjCanMove_1(m, n, i1, l2, l3)) {
              this.jjstateSet[(this.jjnewStateCnt++)] = 18;
            }
            break;
          case 26: 
            if (jjCanMove_1(m, n, i1, l2, l3)) {
              jjAddStates(10, 12);
            }
            break;
          case 34: 
          case 35: 
            if (jjCanMove_2(m, n, i1, l2, l3))
            {
              if (k > 69) {
                k = 69;
              }
              jjCheckNAdd(35);
            }
            break;
          case 57: 
            if (jjCanMove_1(m, n, i1, l2, l3))
            {
              if (k > 7) {
                k = 7;
              }
              jjAddStates(18, 20);
            }
            break;
          case 62: 
            if (jjCanMove_1(m, n, i1, l2, l3)) {
              jjCheckNAddTwoStates(62, 63);
            }
            break;
          case 64: 
          case 65: 
            if (jjCanMove_1(m, n, i1, l2, l3)) {
              jjCheckNAddTwoStates(65, 63);
            }
            break;
          case 68: 
            if (jjCanMove_1(m, n, i1, l2, l3)) {
              jjCheckNAddTwoStates(68, 69);
            }
            break;
          case 70: 
          case 71: 
            if (jjCanMove_1(m, n, i1, l2, l3)) {
              jjCheckNAddTwoStates(71, 69);
            }
            break;
          }
        } while (j != i);
      }
      if (k != Integer.MAX_VALUE)
      {
        this.jjmatchedKind = k;
        this.jjmatchedPos = paramInt2;
        k = Integer.MAX_VALUE;
      }
      paramInt2++;
      if ((j = this.jjnewStateCnt) == (i = 74 - (this.jjnewStateCnt = i))) {
        return paramInt2;
      }
      try
      {
        this.curChar = this.input_stream.readChar();
      }
      catch (IOException localIOException) {}
    }
    return paramInt2;
  }
  
  private static final boolean jjCanMove_0(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2)
  {
    switch (paramInt1)
    {
    case 0: 
      return (jjbitVec0[paramInt3] & paramLong2) != 0L;
    }
    return false;
  }
  
  private static final boolean jjCanMove_1(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2)
  {
    switch (paramInt1)
    {
    case 0: 
      return (jjbitVec0[paramInt3] & paramLong2) != 0L;
    }
    return (jjbitVec1[paramInt2] & paramLong1) != 0L;
  }
  
  private static final boolean jjCanMove_2(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2)
  {
    switch (paramInt1)
    {
    case 0: 
      return (jjbitVec4[paramInt3] & paramLong2) != 0L;
    case 48: 
      return (jjbitVec5[paramInt3] & paramLong2) != 0L;
    case 49: 
      return (jjbitVec6[paramInt3] & paramLong2) != 0L;
    case 51: 
      return (jjbitVec7[paramInt3] & paramLong2) != 0L;
    case 61: 
      return (jjbitVec8[paramInt3] & paramLong2) != 0L;
    }
    return (jjbitVec3[paramInt2] & paramLong1) != 0L;
  }
  
  public ParserTokenManager(JavaCharStream paramJavaCharStream)
  {
    this.input_stream = paramJavaCharStream;
  }
  
  public ParserTokenManager(JavaCharStream paramJavaCharStream, int paramInt)
  {
    this(paramJavaCharStream);
    SwitchTo(paramInt);
  }
  
  public void ReInit(JavaCharStream paramJavaCharStream)
  {
    this.jjmatchedPos = (this.jjnewStateCnt = 0);
    this.curLexState = this.defaultLexState;
    this.input_stream = paramJavaCharStream;
    ReInitRounds();
  }
  
  private final void ReInitRounds()
  {
    this.jjround = -2147483647;
    int i = 74;
    while (i-- > 0) {
      this.jjrounds[i] = Integer.MIN_VALUE;
    }
  }
  
  public void ReInit(JavaCharStream paramJavaCharStream, int paramInt)
  {
    ReInit(paramJavaCharStream);
    SwitchTo(paramInt);
  }
  
  public void SwitchTo(int paramInt)
  {
    if ((paramInt >= 1) || (paramInt < 0)) {
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + paramInt + ". State unchanged.", 2);
    }
    this.curLexState = paramInt;
  }
  
  protected Token jjFillToken()
  {
    Token localToken = Token.newToken(this.jjmatchedKind);
    localToken.kind = this.jjmatchedKind;
    String str = jjstrLiteralImages[this.jjmatchedKind];
    localToken.image = (str == null ? this.input_stream.GetImage() : str);
    localToken.beginLine = this.input_stream.getBeginLine();
    localToken.beginColumn = this.input_stream.getBeginColumn();
    localToken.endLine = this.input_stream.getEndLine();
    localToken.endColumn = this.input_stream.getEndColumn();
    return localToken;
  }
  
  public Token getNextToken()
  {
    Object localObject = null;
    int i = 0;
    for (;;)
    {
      Token localToken;
      try
      {
        this.curChar = this.input_stream.BeginToken();
      }
      catch (IOException localIOException1)
      {
        this.jjmatchedKind = 0;
        localToken = jjFillToken();
        localToken.specialToken = ((Token)localObject);
        return localToken;
      }
      this.jjmatchedKind = Integer.MAX_VALUE;
      this.jjmatchedPos = 0;
      i = jjMoveStringLiteralDfa0_0();
      if (this.jjmatchedKind == Integer.MAX_VALUE) {
        break;
      }
      if (this.jjmatchedPos + 1 < i) {
        this.input_stream.backup(i - this.jjmatchedPos - 1);
      }
      if ((jjtoToken[(this.jjmatchedKind >> 6)] & 1L << (this.jjmatchedKind & 0x3F)) != 0L)
      {
        localToken = jjFillToken();
        localToken.specialToken = ((Token)localObject);
        return localToken;
      }
      if ((jjtoSpecial[(this.jjmatchedKind >> 6)] & 1L << (this.jjmatchedKind & 0x3F)) != 0L)
      {
        localToken = jjFillToken();
        if (localObject == null)
        {
          localObject = localToken;
        }
        else
        {
          localToken.specialToken = ((Token)localObject);
          localObject = ((Token)localObject).next = localToken;
        }
      }
    }
    int j = this.input_stream.getEndLine();
    int k = this.input_stream.getEndColumn();
    String str = null;
    boolean bool = false;
    try
    {
      this.input_stream.readChar();
      this.input_stream.backup(1);
    }
    catch (IOException localIOException2)
    {
      bool = true;
      str = i <= 1 ? "" : this.input_stream.GetImage();
      if ((this.curChar == '\n') || (this.curChar == '\r'))
      {
        j++;
        k = 0;
      }
      else
      {
        k++;
      }
    }
    if (!bool)
    {
      this.input_stream.backup(1);
      str = i <= 1 ? "" : this.input_stream.GetImage();
    }
    throw new TokenMgrError(bool, this.curLexState, j, k, str, this.curChar, 0);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/ParserTokenManager.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */