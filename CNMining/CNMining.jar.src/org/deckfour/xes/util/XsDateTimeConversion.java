/*     */ package org.deckfour.xes.util;
/*     */ 
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.xml.bind.DatatypeConverter;
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
/*     */ public class XsDateTimeConversion
/*     */ {
/*     */   protected static final String XSDATETIME_FORMAT_STRING_MILLIS_TZONE = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
/*  68 */   protected final SimpleDateFormat dfMillisTZone = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  74 */   protected final Pattern xsDtPattern = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})T(\\d{2}):(\\d{2}):(\\d{2})(\\.(\\d{3}))?(.+)?");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  80 */   protected GregorianCalendar cal = new GregorianCalendar();
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
/*     */   public Date parseXsDateTime(String xsDateTime)
/*     */   {
/*     */     try
/*     */     {
/*  95 */       Calendar.getInstance().setLenient(true);
/*  96 */       Calendar cal = DatatypeConverter.parseDateTime(xsDateTime);
/*  97 */       return cal.getTime();
/*     */     }
/*     */     catch (IllegalArgumentException e) {}
/*     */     
/*     */ 
/*     */ 
/* 103 */     return parseXsDateTimeUsingPattern(xsDateTime);
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   private Date parseXsDateTimeUsingPattern(String xsDateTime)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: invokevirtual 18	java/lang/String:length	()I
/*     */     //   4: bipush 6
/*     */     //   6: if_icmplt +102 -> 108
/*     */     //   9: aload_1
/*     */     //   10: aload_1
/*     */     //   11: invokevirtual 18	java/lang/String:length	()I
/*     */     //   14: bipush 6
/*     */     //   16: isub
/*     */     //   17: invokevirtual 19	java/lang/String:charAt	(I)C
/*     */     //   20: bipush 43
/*     */     //   22: if_icmpne +86 -> 108
/*     */     //   25: aload_1
/*     */     //   26: aload_1
/*     */     //   27: invokevirtual 18	java/lang/String:length	()I
/*     */     //   30: iconst_3
/*     */     //   31: isub
/*     */     //   32: invokevirtual 19	java/lang/String:charAt	(I)C
/*     */     //   35: bipush 58
/*     */     //   37: if_icmpne +71 -> 108
/*     */     //   40: new 20	java/lang/StringBuilder
/*     */     //   43: dup
/*     */     //   44: invokespecial 21	java/lang/StringBuilder:<init>	()V
/*     */     //   47: aload_1
/*     */     //   48: iconst_0
/*     */     //   49: aload_1
/*     */     //   50: invokevirtual 18	java/lang/String:length	()I
/*     */     //   53: iconst_3
/*     */     //   54: isub
/*     */     //   55: invokevirtual 22	java/lang/String:substring	(II)Ljava/lang/String;
/*     */     //   58: invokevirtual 23	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   61: aload_1
/*     */     //   62: aload_1
/*     */     //   63: invokevirtual 18	java/lang/String:length	()I
/*     */     //   66: iconst_2
/*     */     //   67: isub
/*     */     //   68: invokevirtual 24	java/lang/String:substring	(I)Ljava/lang/String;
/*     */     //   71: invokevirtual 23	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   74: invokevirtual 25	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   77: astore_2
/*     */     //   78: aload_0
/*     */     //   79: getfield 5	org/deckfour/xes/util/XsDateTimeConversion:dfMillisTZone	Ljava/text/SimpleDateFormat;
/*     */     //   82: dup
/*     */     //   83: astore_3
/*     */     //   84: monitorenter
/*     */     //   85: aload_0
/*     */     //   86: getfield 5	org/deckfour/xes/util/XsDateTimeConversion:dfMillisTZone	Ljava/text/SimpleDateFormat;
/*     */     //   89: aload_2
/*     */     //   90: invokevirtual 26	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
/*     */     //   93: aload_3
/*     */     //   94: monitorexit
/*     */     //   95: areturn
/*     */     //   96: astore 4
/*     */     //   98: aload_3
/*     */     //   99: monitorexit
/*     */     //   100: aload 4
/*     */     //   102: athrow
/*     */     //   103: astore_3
/*     */     //   104: aload_3
/*     */     //   105: invokevirtual 28	java/text/ParseException:printStackTrace	()V
/*     */     //   108: aload_0
/*     */     //   109: getfield 8	org/deckfour/xes/util/XsDateTimeConversion:xsDtPattern	Ljava/util/regex/Pattern;
/*     */     //   112: aload_1
/*     */     //   113: invokevirtual 29	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
/*     */     //   116: astore_2
/*     */     //   117: aload_2
/*     */     //   118: invokevirtual 30	java/util/regex/Matcher:matches	()Z
/*     */     //   121: iconst_1
/*     */     //   122: if_icmpne +194 -> 316
/*     */     //   125: aload_2
/*     */     //   126: iconst_1
/*     */     //   127: invokevirtual 31	java/util/regex/Matcher:group	(I)Ljava/lang/String;
/*     */     //   130: invokestatic 32	java/lang/Integer:parseInt	(Ljava/lang/String;)I
/*     */     //   133: istore_3
/*     */     //   134: aload_2
/*     */     //   135: iconst_2
/*     */     //   136: invokevirtual 31	java/util/regex/Matcher:group	(I)Ljava/lang/String;
/*     */     //   139: invokestatic 32	java/lang/Integer:parseInt	(Ljava/lang/String;)I
/*     */     //   142: iconst_1
/*     */     //   143: isub
/*     */     //   144: istore 4
/*     */     //   146: aload_2
/*     */     //   147: iconst_3
/*     */     //   148: invokevirtual 31	java/util/regex/Matcher:group	(I)Ljava/lang/String;
/*     */     //   151: invokestatic 32	java/lang/Integer:parseInt	(Ljava/lang/String;)I
/*     */     //   154: istore 5
/*     */     //   156: aload_2
/*     */     //   157: iconst_4
/*     */     //   158: invokevirtual 31	java/util/regex/Matcher:group	(I)Ljava/lang/String;
/*     */     //   161: invokestatic 32	java/lang/Integer:parseInt	(Ljava/lang/String;)I
/*     */     //   164: istore 6
/*     */     //   166: aload_2
/*     */     //   167: iconst_5
/*     */     //   168: invokevirtual 31	java/util/regex/Matcher:group	(I)Ljava/lang/String;
/*     */     //   171: invokestatic 32	java/lang/Integer:parseInt	(Ljava/lang/String;)I
/*     */     //   174: istore 7
/*     */     //   176: aload_2
/*     */     //   177: bipush 6
/*     */     //   179: invokevirtual 31	java/util/regex/Matcher:group	(I)Ljava/lang/String;
/*     */     //   182: invokestatic 32	java/lang/Integer:parseInt	(Ljava/lang/String;)I
/*     */     //   185: istore 8
/*     */     //   187: iconst_0
/*     */     //   188: istore 9
/*     */     //   190: aload_2
/*     */     //   191: bipush 7
/*     */     //   193: invokevirtual 31	java/util/regex/Matcher:group	(I)Ljava/lang/String;
/*     */     //   196: ifnull +14 -> 210
/*     */     //   199: aload_2
/*     */     //   200: bipush 8
/*     */     //   202: invokevirtual 31	java/util/regex/Matcher:group	(I)Ljava/lang/String;
/*     */     //   205: invokestatic 32	java/lang/Integer:parseInt	(Ljava/lang/String;)I
/*     */     //   208: istore 9
/*     */     //   210: aload_0
/*     */     //   211: getfield 11	org/deckfour/xes/util/XsDateTimeConversion:cal	Ljava/util/GregorianCalendar;
/*     */     //   214: iload_3
/*     */     //   215: iload 4
/*     */     //   217: iload 5
/*     */     //   219: iload 6
/*     */     //   221: iload 7
/*     */     //   223: iload 8
/*     */     //   225: invokevirtual 33	java/util/GregorianCalendar:set	(IIIIII)V
/*     */     //   228: aload_0
/*     */     //   229: getfield 11	org/deckfour/xes/util/XsDateTimeConversion:cal	Ljava/util/GregorianCalendar;
/*     */     //   232: bipush 14
/*     */     //   234: iload 9
/*     */     //   236: invokevirtual 34	java/util/GregorianCalendar:set	(II)V
/*     */     //   239: aload_2
/*     */     //   240: bipush 9
/*     */     //   242: invokevirtual 31	java/util/regex/Matcher:group	(I)Ljava/lang/String;
/*     */     //   245: astore 10
/*     */     //   247: aload 10
/*     */     //   249: ifnull +47 -> 296
/*     */     //   252: new 20	java/lang/StringBuilder
/*     */     //   255: dup
/*     */     //   256: invokespecial 21	java/lang/StringBuilder:<init>	()V
/*     */     //   259: ldc 35
/*     */     //   261: invokevirtual 23	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   264: aload 10
/*     */     //   266: ldc 36
/*     */     //   268: ldc 37
/*     */     //   270: invokevirtual 38	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
/*     */     //   273: invokevirtual 23	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   276: invokevirtual 25	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   279: astore 10
/*     */     //   281: aload_0
/*     */     //   282: getfield 11	org/deckfour/xes/util/XsDateTimeConversion:cal	Ljava/util/GregorianCalendar;
/*     */     //   285: aload 10
/*     */     //   287: invokestatic 39	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
/*     */     //   290: invokevirtual 40	java/util/GregorianCalendar:setTimeZone	(Ljava/util/TimeZone;)V
/*     */     //   293: goto +15 -> 308
/*     */     //   296: aload_0
/*     */     //   297: getfield 11	org/deckfour/xes/util/XsDateTimeConversion:cal	Ljava/util/GregorianCalendar;
/*     */     //   300: ldc 35
/*     */     //   302: invokestatic 39	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
/*     */     //   305: invokevirtual 40	java/util/GregorianCalendar:setTimeZone	(Ljava/util/TimeZone;)V
/*     */     //   308: aload_0
/*     */     //   309: getfield 11	org/deckfour/xes/util/XsDateTimeConversion:cal	Ljava/util/GregorianCalendar;
/*     */     //   312: invokevirtual 41	java/util/GregorianCalendar:getTime	()Ljava/util/Date;
/*     */     //   315: areturn
/*     */     //   316: getstatic 42	java/lang/System:err	Ljava/io/PrintStream;
/*     */     //   319: new 20	java/lang/StringBuilder
/*     */     //   322: dup
/*     */     //   323: invokespecial 21	java/lang/StringBuilder:<init>	()V
/*     */     //   326: ldc 43
/*     */     //   328: invokevirtual 23	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   331: aload_1
/*     */     //   332: invokevirtual 23	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   335: ldc 44
/*     */     //   337: invokevirtual 23	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   340: invokevirtual 25	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   343: invokevirtual 45	java/io/PrintStream:println	(Ljava/lang/String;)V
/*     */     //   346: aconst_null
/*     */     //   347: areturn
/*     */     // Line number table:
/*     */     //   Java source line #110	-> byte code offset #0
/*     */     //   Java source line #113	-> byte code offset #40
/*     */     //   Java source line #116	-> byte code offset #78
/*     */     //   Java source line #117	-> byte code offset #85
/*     */     //   Java source line #118	-> byte code offset #96
/*     */     //   Java source line #119	-> byte code offset #103
/*     */     //   Java source line #120	-> byte code offset #104
/*     */     //   Java source line #124	-> byte code offset #108
/*     */     //   Java source line #125	-> byte code offset #117
/*     */     //   Java source line #127	-> byte code offset #125
/*     */     //   Java source line #128	-> byte code offset #134
/*     */     //   Java source line #129	-> byte code offset #146
/*     */     //   Java source line #130	-> byte code offset #156
/*     */     //   Java source line #131	-> byte code offset #166
/*     */     //   Java source line #132	-> byte code offset #176
/*     */     //   Java source line #133	-> byte code offset #187
/*     */     //   Java source line #135	-> byte code offset #190
/*     */     //   Java source line #136	-> byte code offset #199
/*     */     //   Java source line #138	-> byte code offset #210
/*     */     //   Java source line #139	-> byte code offset #228
/*     */     //   Java source line #140	-> byte code offset #239
/*     */     //   Java source line #141	-> byte code offset #247
/*     */     //   Java source line #143	-> byte code offset #252
/*     */     //   Java source line #144	-> byte code offset #281
/*     */     //   Java source line #146	-> byte code offset #296
/*     */     //   Java source line #148	-> byte code offset #308
/*     */     //   Java source line #150	-> byte code offset #316
/*     */     //   Java source line #151	-> byte code offset #346
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	348	0	this	XsDateTimeConversion
/*     */     //   0	348	1	xsDateTime	String
/*     */     //   77	13	2	modified	String
/*     */     //   116	124	2	matcher	java.util.regex.Matcher
/*     */     //   83	16	3	Ljava/lang/Object;	Object
/*     */     //   103	2	3	e	java.text.ParseException
/*     */     //   133	82	3	year	int
/*     */     //   96	5	4	localObject1	Object
/*     */     //   144	72	4	month	int
/*     */     //   154	64	5	day	int
/*     */     //   164	56	6	hour	int
/*     */     //   174	48	7	minute	int
/*     */     //   185	39	8	second	int
/*     */     //   188	47	9	millis	int
/*     */     //   245	41	10	tzString	String
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   85	95	96	finally
/*     */     //   96	100	96	finally
/*     */     //   78	95	103	java/text/ParseException
/*     */     //   96	103	103	java/text/ParseException
/*     */   }
/*     */   
/*     */   public String format(Date date)
/*     */   {
/* 163 */     Calendar cal = new GregorianCalendar();
/* 164 */     cal.setTime(date);
/* 165 */     return DatatypeConverter.printDateTime(cal);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/util/XsDateTimeConversion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */