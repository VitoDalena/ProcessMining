/*      */ package ViolinStrings;
/*      */ 
/*      */ import java.util.Locale;
/*      */ import java.util.Vector;
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
/*      */ public class Strings
/*      */ {
/*      */   private static Vector buildWordList(String paramString, int paramInt)
/*      */   {
/*   85 */     int i = 0;
/*   86 */     int j = 0;
/*   87 */     int k = 0;
/*      */     
/*   89 */     Vector localVector = new Vector();
/*      */     
/*   91 */     int m = 0;
/*      */     
/*   93 */     for (goto 113; m < paramInt;)
/*      */     {
/*      */ 
/*   96 */       while ((m < paramInt) && (Character.isWhitespace(paramString.charAt(m)))) m++;
/*   97 */       i = m++;
/*      */       
/*   99 */       if (m < paramInt)
/*      */       {
/*  101 */         while ((m < paramInt) && (!Character.isWhitespace(paramString.charAt(m)))) m++;
/*  102 */         j = m++;
/*      */         
/*  104 */         WordEntry localWordEntry = new WordEntry(i, j - i, k++);
/*  105 */         localVector.addElement(localWordEntry);
/*      */       }
/*      */     }
/*      */     
/*  109 */     return localVector;
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
/*      */   public static String center(String paramString, int paramInt)
/*      */   {
/*  122 */     return center(paramString, paramInt, ' ');
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
/*      */   public static String center(String paramString, int paramInt, char paramChar)
/*      */   {
/*  137 */     int j = paramString.length();
/*  138 */     if (paramInt < 1) {
/*  139 */       return "";
/*      */     }
/*      */     
/*  142 */     char[] arrayOfChar = new char[paramInt];
/*  143 */     for (int k = 0; k < paramInt; k++)
/*      */     {
/*  145 */       arrayOfChar[k] = paramChar;
/*      */     }
/*      */     int i;
/*  148 */     if (paramInt > j) {
/*  149 */       i = (paramInt - j) / 2;
/*  150 */       paramString.getChars(0, j, arrayOfChar, i);
/*      */     }
/*      */     else {
/*  153 */       i = (j - paramInt) / 2;
/*  154 */       paramString.getChars(i, i + paramInt, arrayOfChar, 0);
/*      */     }
/*      */     
/*  157 */     return new String(arrayOfChar);
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
/*      */   public static String change(String paramString, char paramChar1, char paramChar2)
/*      */   {
/*  172 */     return paramString.replace(paramChar1, paramChar2);
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
/*      */   public static String change(String paramString, char paramChar1, char paramChar2, int paramInt)
/*      */   {
/*  187 */     return change(paramString, paramChar1, paramChar2, paramInt, Integer.MAX_VALUE, false);
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
/*      */   public static String change(String paramString, char paramChar1, char paramChar2, int paramInt1, int paramInt2)
/*      */   {
/*  204 */     return change(paramString, paramChar1, paramChar2, paramInt1, paramInt2, false);
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
/*      */   public static String change(String paramString, char paramChar1, char paramChar2, int paramInt1, int paramInt2, boolean paramBoolean)
/*      */   {
/*  223 */     int i = paramString.length();
/*      */     
/*      */ 
/*      */ 
/*  227 */     int n = 0;
/*      */     
/*  229 */     if (paramInt1 >= i) {
/*  230 */       return paramString;
/*      */     }
/*      */     
/*  233 */     if (paramInt1 < 0) {
/*  234 */       paramInt1 = 0;
/*      */     }
/*      */     
/*  237 */     int j = paramInt1;
/*      */     
/*  239 */     char[] arrayOfChar = new char[i];
/*      */     
/*  241 */     paramString.getChars(0, paramInt1, arrayOfChar, 0);
/*  242 */     int k = paramInt1;
/*      */     int m;
/*  244 */     while (((m = indexOf(paramString, paramChar1, j, paramBoolean)) >= 0) && (n++ < paramInt2))
/*      */     {
/*  246 */       paramString.getChars(j, m, arrayOfChar, j);
/*  247 */       arrayOfChar[m] = paramChar2;
/*  248 */       j = m + 1;
/*      */     }
/*      */     
/*  251 */     paramString.getChars(j, i, arrayOfChar, j);
/*      */     
/*  253 */     return new String(arrayOfChar, 0, i);
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
/*      */   public static String change(String paramString, char paramChar1, char paramChar2, int paramInt, boolean paramBoolean)
/*      */   {
/*  271 */     return change(paramString, paramChar1, paramChar2, paramInt, Integer.MAX_VALUE, paramBoolean);
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
/*      */   public static String change(String paramString1, String paramString2, String paramString3)
/*      */   {
/*  284 */     return change(paramString1, paramString2, paramString3, 0, Integer.MAX_VALUE, false);
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
/*      */   public static String change(String paramString1, String paramString2, String paramString3, int paramInt)
/*      */   {
/*  299 */     return change(paramString1, paramString2, paramString3, paramInt, Integer.MAX_VALUE);
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
/*      */   public static String change(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2)
/*      */   {
/*  316 */     return change(paramString1, paramString2, paramString3, paramInt1, paramInt2, false);
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
/*      */   public static String change(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, boolean paramBoolean)
/*      */   {
/*  335 */     int i = paramString2.length();
/*  336 */     int j = paramString3.length();
/*  337 */     int k = paramString1.length();
/*  338 */     int m = k;
/*      */     
/*      */ 
/*      */ 
/*  342 */     int i3 = 0;
/*      */     
/*  344 */     if ((i == 0) || (paramInt1 >= k)) {
/*  345 */       return paramString1;
/*      */     }
/*      */     
/*  348 */     if (paramInt1 < 0) {
/*  349 */       paramInt1 = 0;
/*      */     }
/*      */     
/*  352 */     int n = paramInt1;
/*      */     
/*  354 */     if (j > i) {
/*  355 */       int i4 = occurrencesOf(paramString1, paramString2, paramInt1, paramBoolean);
/*  356 */       m = k + i4 * (j - i);
/*      */     }
/*      */     
/*  359 */     char[] arrayOfChar = new char[m];
/*      */     
/*  361 */     paramString1.getChars(0, paramInt1, arrayOfChar, 0);
/*  362 */     int i1 = paramInt1;
/*      */     int i2;
/*  364 */     while (((i2 = indexOf(paramString1, paramString2, n, paramBoolean)) >= 0) && (i3++ < paramInt2))
/*      */     {
/*  366 */       paramString1.getChars(n, i2, arrayOfChar, i1);
/*  367 */       i1 += i2 - n;
/*  368 */       paramString3.getChars(0, j, arrayOfChar, i1);
/*  369 */       n = i2 + i;
/*  370 */       i1 += j;
/*      */     }
/*      */     
/*  373 */     paramString1.getChars(n, k, arrayOfChar, i1);
/*      */     
/*  375 */     return new String(arrayOfChar, 0, i1 - n + k);
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
/*      */   public static String change(String paramString1, String paramString2, String paramString3, int paramInt, boolean paramBoolean)
/*      */   {
/*  393 */     return change(paramString1, paramString2, paramString3, paramInt, Integer.MAX_VALUE, paramBoolean);
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
/*      */   public static String change(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
/*      */   {
/*  408 */     return change(paramString1, paramString2, paramString3, 0, Integer.MAX_VALUE, paramBoolean);
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
/*      */   public static int compare(String paramString1, String paramString2)
/*      */   {
/*  426 */     return paramString1.compareTo(paramString2);
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
/*      */   public static boolean contains(String paramString1, String paramString2)
/*      */   {
/*  441 */     return paramString1.indexOf(paramString2) >= 0;
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
/*      */   public static boolean contains(String paramString1, String paramString2, boolean paramBoolean)
/*      */   {
/*  458 */     return indexOf(paramString1, paramString2, paramBoolean) >= 0;
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
/*      */   public static String copies(String paramString, int paramInt)
/*      */   {
/*  474 */     int i = paramString.length();
/*      */     
/*  476 */     if (paramInt < 1) {
/*  477 */       return "";
/*      */     }
/*      */     
/*  480 */     StringBuffer localStringBuffer = new StringBuffer(i * paramInt);
/*      */     
/*  482 */     for (int j = 0; j < paramInt; j++)
/*      */     {
/*  484 */       localStringBuffer.append(paramString);
/*      */     }
/*      */     
/*  487 */     return localStringBuffer.toString();
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
/*      */   public static boolean endsWith(String paramString1, String paramString2)
/*      */   {
/*  502 */     return paramString1.endsWith(paramString2);
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
/*      */   public static boolean endsWith(String paramString1, String paramString2, boolean paramBoolean)
/*      */   {
/*  519 */     int i = paramString1.length() - paramString2.length();
/*  520 */     return paramString1.regionMatches(paramBoolean, i, paramString2, 0, paramString2.length());
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
/*      */   public static boolean equals(char paramChar1, char paramChar2, boolean paramBoolean)
/*      */   {
/*  537 */     if (paramBoolean) {
/*  538 */       return (Character.toUpperCase(paramChar1) == Character.toUpperCase(paramChar2)) || (Character.toLowerCase(paramChar1) == Character.toLowerCase(paramChar2));
/*      */     }
/*      */     
/*      */ 
/*  542 */     return paramChar1 == paramChar2;
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
/*      */   public static boolean equals(String paramString1, String paramString2, boolean paramBoolean)
/*      */   {
/*  560 */     if (paramBoolean) {
/*  561 */       return paramString1.equalsIgnoreCase(paramString2);
/*      */     }
/*      */     
/*  564 */     return paramString1.equals(paramString2);
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
/*      */   public static int indexOf(String paramString, char paramChar)
/*      */   {
/*  581 */     return paramString.indexOf(paramChar);
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
/*      */   public static int indexOf(String paramString, char paramChar, int paramInt)
/*      */   {
/*  598 */     return paramString.indexOf(paramChar, paramInt);
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
/*      */   public static int indexOf(String paramString, char paramChar, int paramInt, boolean paramBoolean)
/*      */   {
/*  617 */     if (paramBoolean) {
/*  618 */       int i = paramString.length();
/*      */       
/*  620 */       if (paramInt < 0) {
/*  621 */         paramInt = 0;
/*      */       }
/*      */       
/*  624 */       if (paramInt >= i) {
/*  625 */         return -1;
/*      */       }
/*      */       
/*  628 */       for (int j = paramInt; j < i; j++)
/*      */       {
/*  630 */         if (equals(paramString.charAt(j), paramChar, paramBoolean)) {
/*  631 */           return j;
/*      */         }
/*      */       }
/*      */       
/*  635 */       return -1;
/*      */     }
/*      */     
/*  638 */     return paramString.indexOf(paramChar, paramInt);
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
/*      */   public static int indexOf(String paramString, char paramChar, boolean paramBoolean)
/*      */   {
/*  655 */     return indexOf(paramString, paramChar, 0, paramBoolean);
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
/*      */   public static int indexOf(String paramString1, String paramString2)
/*      */   {
/*  671 */     return paramString1.indexOf(paramString2);
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
/*      */   public static int indexOf(String paramString1, String paramString2, int paramInt)
/*      */   {
/*  688 */     return paramString1.indexOf(paramString2, paramInt);
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
/*      */   public static int indexOf(String paramString1, String paramString2, int paramInt, boolean paramBoolean)
/*      */   {
/*  707 */     if (paramBoolean) {
/*  708 */       int i = paramString1.length();
/*  709 */       int j = paramString2.length();
/*  710 */       int k = j - 1;
/*  711 */       int m = i - j;
/*      */       
/*  713 */       if ((j == 0) || (paramInt > m)) {
/*  714 */         return -1;
/*      */       }
/*      */       
/*  717 */       int n = paramInt < 0 ? 0 : paramInt;
/*  718 */       char c = paramString2.charAt(0);
/*      */       
/*  720 */       while (n < m)
/*      */       {
/*  722 */         if ((equals(paramString1.charAt(n), c, paramBoolean)) && 
/*  723 */           (paramString1.regionMatches(paramBoolean, n + 1, paramString2, 1, k))) {
/*  724 */           return n;
/*      */         }
/*      */         
/*  727 */         n++;
/*      */       }
/*      */       
/*  730 */       return -1;
/*      */     }
/*      */     
/*  733 */     return paramString1.indexOf(paramString2, paramInt);
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
/*      */   public static int indexOf(String paramString1, String paramString2, boolean paramBoolean)
/*      */   {
/*  751 */     return indexOf(paramString1, paramString2, 0, paramBoolean);
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
/*      */   public static int indexOfAnyBut(String paramString1, String paramString2)
/*      */   {
/*  767 */     return indexOfAnyBut(paramString1, paramString2, 0, false);
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
/*      */   public static int indexOfAnyBut(String paramString1, String paramString2, int paramInt)
/*      */   {
/*  784 */     return indexOfAnyBut(paramString1, paramString2, paramInt, false);
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
/*      */   public static int indexOfAnyBut(String paramString1, String paramString2, int paramInt, boolean paramBoolean)
/*      */   {
/*  803 */     int i = paramString1.length();
/*  804 */     int j = -1;
/*      */     
/*  806 */     for (int k = paramInt; k < i; k++)
/*      */     {
/*  808 */       char c = paramString1.charAt(k);
/*  809 */       if (indexOf(paramString2, c, paramBoolean) < 0) {
/*  810 */         j = k;
/*  811 */         break;
/*      */       }
/*      */     }
/*      */     
/*  815 */     return j;
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
/*      */   public static int indexOfAnyBut(String paramString1, String paramString2, boolean paramBoolean)
/*      */   {
/*  833 */     return indexOfAnyBut(paramString1, paramString2, 0, paramBoolean);
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
/*      */   public static int indexOfAnyOf(String paramString1, String paramString2)
/*      */   {
/*  850 */     return indexOfAnyOf(paramString1, paramString2, 0);
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
/*      */   public static int indexOfAnyOf(String paramString1, String paramString2, int paramInt)
/*      */   {
/*  869 */     int i = paramString2.length();
/*  870 */     int j = paramString1.length();
/*  871 */     int k = j;
/*      */     
/*  873 */     for (int m = 0; m < i; m++)
/*      */     {
/*  875 */       int n = paramString2.charAt(m);
/*  876 */       int i1 = paramString1.indexOf(n, paramInt);
/*  877 */       if ((i1 >= 0) && (i1 < k)) {
/*  878 */         k = i1;
/*      */       }
/*      */     }
/*  881 */     return k < j ? k : -1;
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
/*      */   public static int indexOfAnyOf(String paramString1, String paramString2, int paramInt, boolean paramBoolean)
/*      */   {
/*  901 */     int i = paramString2.length();
/*  902 */     int j = paramString1.length();
/*  903 */     int k = j;
/*      */     
/*  905 */     for (int m = 0; m < i; m++)
/*      */     {
/*  907 */       char c = paramString2.charAt(m);
/*  908 */       int n = indexOf(paramString1, c, paramInt, paramBoolean);
/*  909 */       if ((n >= 0) && (n < k)) {
/*  910 */         k = n;
/*      */       }
/*      */     }
/*  913 */     return k < j ? k : -1;
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
/*      */   public static int indexOfAnyOf(String paramString1, String paramString2, boolean paramBoolean)
/*      */   {
/*  932 */     return indexOfAnyOf(paramString1, paramString2, 0, paramBoolean);
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
/*      */   public static int indexOfPhrase(String paramString1, String paramString2, int paramInt)
/*      */   {
/*  951 */     return indexOfPhrase(paramString1, paramString2, paramInt, false);
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
/*      */   public static int indexOfPhrase(String paramString1, String paramString2, int paramInt, boolean paramBoolean)
/*      */   {
/*  972 */     WordEntry localWordEntry = wordEntryOfPhrase(paramString1, paramString2, paramInt, paramBoolean);
/*      */     
/*  974 */     if (localWordEntry == null) {
/*  975 */       return -1;
/*      */     }
/*      */     
/*  978 */     return localWordEntry.start;
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
/*      */   public static int indexOfWord(String paramString, int paramInt)
/*      */   {
/*  995 */     int i = paramString.length();
/*      */     
/*  997 */     if (paramInt < 0) {
/*  998 */       return -1;
/*      */     }
/*      */     
/* 1001 */     paramInt++;
/* 1002 */     int j = 0;
/*      */     
/* 1004 */     for (goto 84; j < i;)
/*      */     {
/*      */ 
/* 1007 */       while ((j < i) && (Character.isWhitespace(paramString.charAt(j)))) { j++;
/*      */       }
/* 1009 */       if (j < i) {
/* 1010 */         paramInt--; if (paramInt == 0) break;
/* 1011 */         j++;
/*      */         
/*      */ 
/* 1014 */         while ((j < i) && (!Character.isWhitespace(paramString.charAt(j)))) j++;
/* 1015 */         j++;
/*      */       }
/*      */     }
/*      */     
/* 1019 */     if (paramInt > 0) {
/* 1020 */       j = -1;
/*      */     }
/*      */     
/* 1023 */     return j;
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
/*      */   public static String insert(String paramString1, String paramString2, int paramInt)
/*      */   {
/* 1039 */     return insert(paramString1, paramString2, paramInt, ' ');
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
/*      */   public static String insert(String paramString1, String paramString2, int paramInt, char paramChar)
/*      */   {
/* 1056 */     int i = paramString1.length();
/* 1057 */     int j = paramString2.length();
/*      */     
/*      */ 
/* 1060 */     if (paramInt < 0) {
/* 1061 */       return "";
/*      */     }
/*      */     int k;
/* 1064 */     if (paramInt > i) {
/* 1065 */       k = j + paramInt;
/*      */     }
/*      */     else {
/* 1068 */       k = i + j;
/*      */     }
/*      */     
/* 1071 */     int m = paramInt > i ? i : paramInt;
/* 1072 */     int n = m;
/*      */     
/* 1074 */     char[] arrayOfChar = new char[k];
/*      */     
/* 1076 */     paramString1.getChars(0, m, arrayOfChar, 0);
/*      */     
/* 1078 */     while (m < paramInt)
/*      */     {
/* 1080 */       arrayOfChar[(m++)] = paramChar;
/*      */     }
/*      */     
/* 1083 */     paramString2.getChars(0, j, arrayOfChar, m);
/* 1084 */     paramString1.getChars(n, i, arrayOfChar, m + j);
/*      */     
/* 1086 */     return new String(arrayOfChar);
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
/*      */   public static boolean isAbbreviationOf(String paramString1, String paramString2, int paramInt)
/*      */   {
/* 1103 */     return isAbbreviationOf(paramString1, paramString2, paramInt, false);
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
/*      */   public static boolean isAbbreviationOf(String paramString1, String paramString2, int paramInt, boolean paramBoolean)
/*      */   {
/* 1123 */     int i = paramString1.length();
/*      */     
/* 1125 */     if ((paramInt > i) || (paramInt < 0)) {
/* 1126 */       return false;
/*      */     }
/*      */     
/* 1129 */     if (paramInt == 0) {
/* 1130 */       paramInt = i;
/*      */     }
/*      */     
/* 1133 */     return paramString1.regionMatches(paramBoolean, 0, paramString2, 0, paramInt);
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
/*      */   public static boolean isAbbreviationOf(String paramString1, String paramString2, boolean paramBoolean)
/*      */   {
/* 1152 */     return paramString1.regionMatches(paramBoolean, 0, paramString2, 0, paramString1.length());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isDigit(String paramString)
/*      */   {
/* 1164 */     int i = paramString.length();
/*      */     
/* 1166 */     for (int j = 0; j < i; j++)
/*      */     {
/* 1168 */       if (!Character.isDigit(paramString.charAt(j))) {
/* 1169 */         return false;
/*      */       }
/*      */     }
/*      */     
/* 1173 */     return true;
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
/*      */   public static boolean isIdentifierIgnorable(String paramString)
/*      */   {
/* 1187 */     int i = paramString.length();
/*      */     
/* 1189 */     for (int j = 0; j < i; j++)
/*      */     {
/* 1191 */       if (!Character.isIdentifierIgnorable(paramString.charAt(j))) {
/* 1192 */         return false;
/*      */       }
/*      */     }
/*      */     
/* 1196 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isISOControl(String paramString)
/*      */   {
/* 1208 */     int i = paramString.length();
/*      */     
/* 1210 */     for (int j = 0; j < i; j++)
/*      */     {
/* 1212 */       if (!Character.isISOControl(paramString.charAt(j))) {
/* 1213 */         return false;
/*      */       }
/*      */     }
/*      */     
/* 1217 */     return true;
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
/*      */   public static boolean isJavaIdentifier(String paramString)
/*      */   {
/* 1230 */     int i = paramString.length();
/*      */     
/* 1232 */     if ((i == 0) || (!Character.isJavaIdentifierStart(paramString.charAt(0)))) {
/* 1233 */       return false;
/*      */     }
/*      */     
/* 1236 */     for (int j = 1; j < i; j++)
/*      */     {
/* 1238 */       if (!Character.isJavaIdentifierPart(paramString.charAt(j))) {
/* 1239 */         return false;
/*      */       }
/*      */     }
/*      */     
/* 1243 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isLetter(String paramString)
/*      */   {
/* 1255 */     int i = paramString.length();
/*      */     
/* 1257 */     for (int j = 0; j < i; j++)
/*      */     {
/* 1259 */       if (!Character.isLetter(paramString.charAt(j))) {
/* 1260 */         return false;
/*      */       }
/*      */     }
/*      */     
/* 1264 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isLetterOrDigit(String paramString)
/*      */   {
/* 1276 */     int i = paramString.length();
/*      */     
/* 1278 */     for (int j = 0; j < i; j++)
/*      */     {
/* 1280 */       if (!Character.isLetterOrDigit(paramString.charAt(j))) {
/* 1281 */         return false;
/*      */       }
/*      */     }
/*      */     
/* 1285 */     return true;
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
/*      */   public static boolean isLike(String paramString1, String paramString2)
/*      */   {
/* 1300 */     return isLike(paramString1, paramString2, 0, '*', '?', false);
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
/*      */   public static boolean isLike(String paramString1, String paramString2, int paramInt)
/*      */   {
/* 1316 */     return isLike(paramString1, paramString2, paramInt, '*', '?', false);
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
/*      */   public static boolean isLike(String paramString1, String paramString2, int paramInt, char paramChar1, char paramChar2, boolean paramBoolean)
/*      */   {
/* 1337 */     int i = paramString1.length();
/* 1338 */     int j = paramString2.length();
/*      */     
/* 1340 */     if (j == 0) {
/* 1341 */       return false;
/*      */     }
/*      */     
/* 1344 */     char c = ' ';
/* 1345 */     int k = paramInt;
/* 1346 */     int m = 0;
/* 1347 */     int n = -1;
/* 1348 */     int i1 = 0;
/*      */     
/* 1350 */     while ((m <= j) && (k <= i))
/*      */     {
/* 1352 */       if (m < j) {
/* 1353 */         c = paramString2.charAt(m);
/*      */         
/* 1355 */         if (c == paramChar1) {
/* 1356 */           n = m++;
/* 1357 */           i1 = k;
/*      */         }
/* 1359 */         else if (k < i) {
/* 1360 */           if ((c == paramChar2) || (equals(paramString1.charAt(k), paramString2.charAt(m), paramBoolean))) {
/* 1361 */             m++;
/* 1362 */             k++;
/*      */           } else {
/* 1364 */             if (n < 0)
/*      */             {
/* 1366 */               return false;
/*      */             }
/*      */             
/*      */ 
/* 1370 */             m = n;
/* 1371 */             i1++;k = i1;
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/* 1376 */           return false;
/*      */         }
/*      */       }
/*      */       else {
/* 1380 */         if (c == paramChar1) {
/* 1381 */           return true;
/*      */         }
/* 1383 */         if ((k < i) && (n >= 0))
/*      */         {
/* 1385 */           m = n + 1;
/* 1386 */           i1++;k = i1;
/*      */         }
/*      */         else {
/* 1389 */           return k >= i;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1394 */     return true;
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
/*      */   public static boolean isLike(String paramString1, String paramString2, int paramInt, boolean paramBoolean)
/*      */   {
/* 1412 */     return isLike(paramString1, paramString2, paramInt, '*', '?', paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isLowerCase(String paramString)
/*      */   {
/* 1424 */     int i = paramString.length();
/*      */     
/* 1426 */     for (int j = 0; j < i; j++)
/*      */     {
/* 1428 */       if (!Character.isLowerCase(paramString.charAt(j))) {
/* 1429 */         return false;
/*      */       }
/*      */     }
/*      */     
/* 1433 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isSpaceChar(String paramString)
/*      */   {
/* 1445 */     int i = paramString.length();
/*      */     
/* 1447 */     for (int j = 0; j < i; j++)
/*      */     {
/* 1449 */       if (!Character.isSpaceChar(paramString.charAt(j))) {
/* 1450 */         return false;
/*      */       }
/*      */     }
/*      */     
/* 1454 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isTitleCase(String paramString)
/*      */   {
/* 1466 */     int i = paramString.length();
/*      */     
/* 1468 */     for (int j = 0; j < i; j++)
/*      */     {
/* 1470 */       if (!Character.isTitleCase(paramString.charAt(j))) {
/* 1471 */         return false;
/*      */       }
/*      */     }
/*      */     
/* 1475 */     return true;
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
/*      */   public static boolean isUnicodeIdentifier(String paramString)
/*      */   {
/* 1488 */     int i = paramString.length();
/*      */     
/* 1490 */     if ((i == 0) || (!Character.isUnicodeIdentifierStart(paramString.charAt(0)))) {
/* 1491 */       return false;
/*      */     }
/*      */     
/* 1494 */     for (int j = 1; j < i; j++)
/*      */     {
/* 1496 */       if (!Character.isUnicodeIdentifierPart(paramString.charAt(j))) {
/* 1497 */         return false;
/*      */       }
/*      */     }
/*      */     
/* 1501 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isUpperCase(String paramString)
/*      */   {
/* 1513 */     int i = paramString.length();
/*      */     
/* 1515 */     for (int j = 0; j < i; j++)
/*      */     {
/* 1517 */       if (!Character.isUpperCase(paramString.charAt(j))) {
/* 1518 */         return false;
/*      */       }
/*      */     }
/*      */     
/* 1522 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isWhitespace(String paramString)
/*      */   {
/* 1534 */     int i = paramString.length();
/*      */     
/* 1536 */     for (int j = 0; j < i; j++)
/*      */     {
/* 1538 */       if (!Character.isWhitespace(paramString.charAt(j))) {
/* 1539 */         return false;
/*      */       }
/*      */     }
/*      */     
/* 1543 */     return true;
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
/*      */   public static int lastIndexOf(String paramString, char paramChar)
/*      */   {
/* 1560 */     return paramString.lastIndexOf(paramChar);
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
/*      */   public static int lastIndexOf(String paramString, char paramChar, int paramInt)
/*      */   {
/* 1578 */     return paramString.lastIndexOf(paramChar, paramInt);
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
/*      */   public static int lastIndexOf(String paramString, char paramChar, int paramInt, boolean paramBoolean)
/*      */   {
/* 1598 */     if (paramBoolean) {
/* 1599 */       int i = paramString.length();
/*      */       
/* 1601 */       if (paramInt < 0) {
/* 1602 */         return -1;
/*      */       }
/*      */       
/* 1605 */       if (paramInt >= i) {
/* 1606 */         paramInt = i - 1;
/*      */       }
/*      */       
/* 1609 */       for (int j = paramInt; j >= 0; j--)
/*      */       {
/* 1611 */         if (equals(paramString.charAt(j), paramChar, paramBoolean)) {
/* 1612 */           return j;
/*      */         }
/*      */       }
/*      */       
/* 1616 */       return -1;
/*      */     }
/*      */     
/* 1619 */     return paramString.lastIndexOf(paramChar, paramInt);
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
/*      */   public static int lastIndexOf(String paramString1, String paramString2)
/*      */   {
/* 1637 */     return paramString1.lastIndexOf(paramString2);
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
/*      */   public static int lastIndexOf(String paramString1, String paramString2, int paramInt)
/*      */   {
/* 1655 */     return paramString1.lastIndexOf(paramString2, paramInt);
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
/*      */   public static int lastIndexOf(String paramString1, String paramString2, int paramInt, boolean paramBoolean)
/*      */   {
/* 1675 */     if (paramBoolean) {
/* 1676 */       int i = paramString1.length();
/* 1677 */       int j = paramString2.length();
/* 1678 */       int k = j - 1;
/* 1679 */       int m = i - j;
/*      */       
/* 1681 */       if ((j == 0) || (paramInt < 0)) {
/* 1682 */         return -1;
/*      */       }
/*      */       
/* 1685 */       int n = paramInt >= m ? m : paramInt;
/* 1686 */       char c = paramString2.charAt(0);
/*      */       
/* 1688 */       while (n >= 0)
/*      */       {
/* 1690 */         if ((equals(paramString1.charAt(n), c, paramBoolean)) && 
/* 1691 */           (paramString1.regionMatches(paramBoolean, n + 1, paramString2, 1, k))) {
/* 1692 */           return n;
/*      */         }
/*      */         
/* 1695 */         n--;
/*      */       }
/*      */       
/* 1698 */       return -1;
/*      */     }
/*      */     
/* 1701 */     return paramString1.lastIndexOf(paramString2, paramInt);
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
/*      */   public static int lastIndexOf(String paramString1, String paramString2, boolean paramBoolean)
/*      */   {
/* 1719 */     return lastIndexOf(paramString1, paramString2, Integer.MAX_VALUE, paramBoolean);
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
/*      */   public static int lastIndexOfAnyBut(String paramString1, String paramString2)
/*      */   {
/* 1736 */     return lastIndexOfAnyBut(paramString1, paramString2, Integer.MAX_VALUE, false);
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
/*      */   public static int lastIndexOfAnyBut(String paramString1, String paramString2, int paramInt)
/*      */   {
/* 1754 */     return lastIndexOfAnyBut(paramString1, paramString2, paramInt, false);
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
/*      */   public static int lastIndexOfAnyBut(String paramString1, String paramString2, int paramInt, boolean paramBoolean)
/*      */   {
/* 1774 */     int i = paramString1.length() - 1;
/* 1775 */     int j = -1;
/* 1776 */     paramInt = paramInt < i ? paramInt : i;
/*      */     
/* 1778 */     for (int k = paramInt; k >= 0; k--)
/*      */     {
/* 1780 */       char c = paramString1.charAt(k);
/* 1781 */       if (indexOf(paramString2, c, paramBoolean) < 0) {
/* 1782 */         j = k;
/* 1783 */         break;
/*      */       }
/*      */     }
/*      */     
/* 1787 */     return j;
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
/*      */   public static int lastIndexOfAnyOf(String paramString1, String paramString2)
/*      */   {
/* 1805 */     return lastIndexOfAnyOf(paramString1, paramString2, Integer.MAX_VALUE, false);
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
/*      */   public static int lastIndexOfAnyOf(String paramString1, String paramString2, int paramInt)
/*      */   {
/* 1824 */     return lastIndexOfAnyOf(paramString1, paramString2, paramInt, false);
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
/*      */   public static int lastIndexOfAnyOf(String paramString1, String paramString2, int paramInt, boolean paramBoolean)
/*      */   {
/* 1845 */     int i = paramString2.length();
/* 1846 */     int j = paramString1.length();
/* 1847 */     int k = -1;
/*      */     
/* 1849 */     for (int m = 0; m < i; m++)
/*      */     {
/* 1851 */       char c = paramString2.charAt(m);
/* 1852 */       int n = lastIndexOf(paramString1, c, paramInt, paramBoolean);
/* 1853 */       if ((n >= 0) && (n > k)) {
/* 1854 */         k = n;
/*      */       }
/*      */     }
/* 1857 */     return k;
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
/*      */   public static String leftJustify(String paramString, int paramInt)
/*      */   {
/* 1871 */     return leftJustify(paramString, paramInt, ' ');
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
/*      */   public static String leftJustify(String paramString, int paramInt, char paramChar)
/*      */   {
/* 1886 */     int i = paramString.length();
/* 1887 */     int j = paramInt < i ? paramInt : i;
/*      */     
/* 1889 */     if (paramInt < 1) {
/* 1890 */       return "";
/*      */     }
/*      */     
/* 1893 */     char[] arrayOfChar = new char[paramInt];
/*      */     
/* 1895 */     paramString.getChars(0, j, arrayOfChar, 0);
/*      */     
/* 1897 */     while (j < paramInt)
/*      */     {
/* 1899 */       arrayOfChar[(j++)] = paramChar;
/*      */     }
/* 1901 */     return new String(arrayOfChar);
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
/*      */   public static int numWords(String paramString)
/*      */   {
/* 1914 */     int i = paramString.length();
/* 1915 */     int j = 0;
/*      */     
/* 1917 */     int k = 0;
/*      */     
/* 1919 */     for (goto 70; k < i;)
/*      */     {
/*      */ 
/* 1922 */       while ((k < i) && (Character.isWhitespace(paramString.charAt(k)))) { k++;
/*      */       }
/* 1924 */       if (k++ < i) { j++;
/*      */       }
/*      */       
/* 1927 */       while ((k < i) && (!Character.isWhitespace(paramString.charAt(k)))) k++;
/* 1928 */       k++;
/*      */     }
/*      */     
/* 1931 */     return j;
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
/*      */   public static int occurrencesOf(String paramString1, String paramString2)
/*      */   {
/* 1946 */     return occurrencesOf(paramString1, paramString2, 0, false);
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
/*      */   public static int occurrencesOf(String paramString1, String paramString2, int paramInt)
/*      */   {
/* 1961 */     return occurrencesOf(paramString1, paramString2, paramInt, false);
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
/*      */   public static int occurrencesOf(String paramString1, String paramString2, int paramInt, boolean paramBoolean)
/*      */   {
/* 1978 */     int i = paramString2.length();
/*      */     
/* 1980 */     int k = 0;
/*      */     
/* 1982 */     if (paramInt > paramString1.length() - i) {
/* 1983 */       return 0;
/*      */     }
/*      */     
/* 1986 */     if (i > 0) { int j;
/* 1987 */       while ((j = indexOf(paramString1, paramString2, paramInt, paramBoolean)) >= 0)
/*      */       {
/* 1989 */         k++;
/* 1990 */         paramInt = j + i;
/*      */       }
/*      */     }
/* 1993 */     return k;
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
/*      */   public static int occurrencesOf(String paramString1, String paramString2, boolean paramBoolean)
/*      */   {
/* 2009 */     return occurrencesOf(paramString1, paramString2, 0, paramBoolean);
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
/*      */   public static String overlayWith(String paramString1, String paramString2, int paramInt)
/*      */   {
/* 2024 */     return overlayWith(paramString1, paramString2, paramInt, ' ');
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
/*      */   public static String overlayWith(String paramString1, String paramString2, int paramInt, char paramChar)
/*      */   {
/* 2040 */     int i = paramString1.length();
/* 2041 */     int j = paramString2.length();
/*      */     
/* 2043 */     if (paramInt < 0) {
/* 2044 */       return "";
/*      */     }
/*      */     
/* 2047 */     int k = paramInt + j;
/* 2048 */     int m = paramInt >= i ? i : paramInt;
/* 2049 */     int n = k < i ? i : k;
/*      */     
/* 2051 */     char[] arrayOfChar = new char[n];
/*      */     
/* 2053 */     paramString1.getChars(0, m, arrayOfChar, 0);
/*      */     
/* 2055 */     for (int i1 = i; i1 < paramInt; i1++)
/*      */     {
/* 2057 */       arrayOfChar[i1] = paramChar;
/*      */     }
/*      */     
/* 2060 */     paramString2.getChars(0, j, arrayOfChar, paramInt);
/*      */     
/* 2062 */     if (k < i) {
/* 2063 */       paramString1.getChars(k, i, arrayOfChar, k);
/*      */     }
/*      */     
/* 2066 */     return new String(arrayOfChar);
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
/*      */   public static String remove(String paramString, int paramInt1, int paramInt2)
/*      */   {
/* 2080 */     int i = paramString.length();
/*      */     
/* 2082 */     if ((paramInt1 >= i) || (paramInt1 < 0) || (paramInt2 < 1)) {
/* 2083 */       return paramString;
/*      */     }
/*      */     
/* 2086 */     if (paramInt1 > i - paramInt2) {
/* 2087 */       paramInt2 = i - paramInt1;
/*      */     }
/*      */     
/* 2090 */     int j = i - paramInt2;
/*      */     
/* 2092 */     char[] arrayOfChar = new char[j];
/*      */     
/* 2094 */     paramString.getChars(0, paramInt1, arrayOfChar, 0);
/* 2095 */     paramString.getChars(paramInt1 + paramInt2, i, arrayOfChar, paramInt1);
/*      */     
/* 2097 */     return new String(arrayOfChar);
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
/*      */   public static String removeWords(String paramString, int paramInt1, int paramInt2)
/*      */   {
/* 2113 */     if ((paramInt1 < 0) || (paramInt2 <= 0)) {
/* 2114 */       return paramString;
/*      */     }
/*      */     
/* 2117 */     int i = indexOfWord(paramString, paramInt1);
/*      */     
/* 2119 */     if (i >= 0) {
/* 2120 */       int j = indexOfWord(paramString.substring(i), paramInt2);
/*      */       
/* 2122 */       if (j >= 0) {
/* 2123 */         return remove(paramString, i, j);
/*      */       }
/*      */       
/* 2126 */       return paramString.substring(0, i);
/*      */     }
/*      */     
/*      */ 
/* 2130 */     return paramString;
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
/*      */   public static String reverse(String paramString)
/*      */   {
/* 2143 */     StringBuffer localStringBuffer = new StringBuffer(paramString);
/* 2144 */     localStringBuffer.reverse();
/* 2145 */     return localStringBuffer.toString();
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
/*      */   public static String rightJustify(String paramString, int paramInt)
/*      */   {
/* 2159 */     return rightJustify(paramString, paramInt, ' ');
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
/*      */   public static String rightJustify(String paramString, int paramInt, char paramChar)
/*      */   {
/* 2174 */     int i = paramString.length();
/*      */     
/*      */ 
/* 2177 */     if (paramInt < 1) {
/* 2178 */       return "";
/*      */     }
/*      */     
/* 2181 */     char[] arrayOfChar = new char[paramInt];
/*      */     int j;
/* 2183 */     if (paramInt > i) {
/* 2184 */       j = paramInt - i;
/* 2185 */       paramString.getChars(0, i, arrayOfChar, j);
/*      */       
/* 2187 */       while (j > 0)
/*      */       {
/* 2189 */         arrayOfChar[(--j)] = paramChar;
/*      */       }
/*      */     }
/*      */     else {
/* 2193 */       j = i - paramInt;
/* 2194 */       paramString.getChars(j, i, arrayOfChar, 0);
/*      */     }
/*      */     
/* 2197 */     return new String(arrayOfChar);
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
/*      */   public static String[] split(String paramString)
/*      */   {
/* 2214 */     return split(paramString, Integer.MAX_VALUE);
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
/*      */   public static String[] split(String paramString, int paramInt)
/*      */   {
/* 2233 */     String[] arrayOfString = new String[Math.min(paramInt, numWords(paramString))];
/*      */     
/* 2235 */     for (int i = 0; i < arrayOfString.length; i++)
/*      */     {
/* 2237 */       arrayOfString[i] = subWords(paramString, i, 1);
/*      */     }
/*      */     
/* 2240 */     return arrayOfString;
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
/*      */   public static boolean startsWith(String paramString1, String paramString2)
/*      */   {
/* 2255 */     return paramString1.startsWith(paramString2);
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
/*      */   public static boolean startsWith(String paramString1, String paramString2, int paramInt)
/*      */   {
/* 2271 */     return paramString1.startsWith(paramString2, paramInt);
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
/*      */   public static boolean startsWith(String paramString1, String paramString2, int paramInt, boolean paramBoolean)
/*      */   {
/* 2289 */     return paramString1.regionMatches(paramBoolean, paramInt, paramString2, 0, paramString2.length());
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
/*      */   public static boolean startsWith(String paramString1, String paramString2, boolean paramBoolean)
/*      */   {
/* 2306 */     return paramString1.regionMatches(paramBoolean, 0, paramString2, 0, paramString2.length());
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
/*      */   public static String strip(String paramString, char paramChar)
/*      */   {
/* 2320 */     return strip(paramString, paramChar, false);
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
/*      */   public static String strip(String paramString, char paramChar, boolean paramBoolean)
/*      */   {
/* 2336 */     String str = stripLeading(paramString, paramChar, paramBoolean);
/* 2337 */     return stripTrailing(str, paramChar, paramBoolean);
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
/*      */   public static String strip(String paramString1, String paramString2)
/*      */   {
/* 2351 */     return strip(paramString1, paramString2, false);
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
/*      */   public static String strip(String paramString1, String paramString2, boolean paramBoolean)
/*      */   {
/* 2367 */     String str = stripLeading(paramString1, paramString2, paramBoolean);
/* 2368 */     return stripTrailing(str, paramString2, paramBoolean);
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
/*      */   public static String stripBlanks(String paramString)
/*      */   {
/* 2384 */     return paramString.trim();
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
/*      */   public static String stripLeading(String paramString, char paramChar)
/*      */   {
/* 2398 */     return stripLeading(paramString, paramChar, false);
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
/*      */   public static String stripLeading(String paramString, char paramChar, boolean paramBoolean)
/*      */   {
/* 2414 */     int i = paramString.length();
/* 2415 */     int j = 0;
/*      */     
/* 2417 */     while ((j < i) && (equals(paramString.charAt(j), paramChar, paramBoolean)))
/*      */     {
/* 2419 */       j++;
/*      */     }
/* 2421 */     return paramString.substring(j);
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
/*      */   public static String stripLeading(String paramString1, String paramString2)
/*      */   {
/* 2435 */     return stripLeading(paramString1, paramString2, false);
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
/*      */   public static String stripLeading(String paramString1, String paramString2, boolean paramBoolean)
/*      */   {
/* 2452 */     int i = paramString1.length();
/* 2453 */     int j = 0;
/*      */     
/* 2455 */     while ((j < i) && (indexOf(paramString2, paramString1.charAt(j), paramBoolean) >= 0))
/*      */     {
/* 2457 */       j++;
/*      */     }
/* 2459 */     return paramString1.substring(j);
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
/*      */   public static String stripLeadingBlanks(String paramString)
/*      */   {
/* 2475 */     int i = paramString.length();
/* 2476 */     int j = 0;
/*      */     
/* 2478 */     while ((j < i) && (Character.isWhitespace(paramString.charAt(j))))
/*      */     {
/* 2480 */       j++;
/*      */     }
/* 2482 */     return paramString.substring(j);
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
/*      */   public static String stripTrailing(String paramString, char paramChar)
/*      */   {
/* 2496 */     return stripTrailing(paramString, paramChar, false);
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
/*      */   public static String stripTrailing(String paramString, char paramChar, boolean paramBoolean)
/*      */   {
/* 2512 */     int i = paramString.length() - 1;
/*      */     
/* 2514 */     while ((i >= 0) && (equals(paramString.charAt(i), paramChar, paramBoolean)))
/*      */     {
/* 2516 */       i--;
/*      */     }
/* 2518 */     return paramString.substring(0, i + 1);
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
/*      */   public static String stripTrailing(String paramString1, String paramString2)
/*      */   {
/* 2532 */     return stripTrailing(paramString1, paramString2, false);
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
/*      */   public static String stripTrailing(String paramString1, String paramString2, boolean paramBoolean)
/*      */   {
/* 2548 */     int i = paramString1.length() - 1;
/*      */     
/* 2550 */     while ((i >= 0) && (indexOf(paramString2, paramString1.charAt(i), paramBoolean) >= 0))
/*      */     {
/* 2552 */       i--;
/*      */     }
/* 2554 */     return paramString1.substring(0, i + 1);
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
/*      */   public static String stripTrailingBlanks(String paramString)
/*      */   {
/* 2570 */     int i = paramString.length() - 1;
/*      */     
/* 2572 */     while ((i >= 0) && (Character.isWhitespace(paramString.charAt(i))))
/*      */     {
/* 2574 */       i--;
/*      */     }
/* 2576 */     return paramString.substring(0, i + 1);
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
/*      */   public static String substring(String paramString, int paramInt)
/*      */   {
/* 2593 */     return paramString.substring(paramInt);
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
/*      */   public static String substring(String paramString, int paramInt1, int paramInt2)
/*      */   {
/* 2612 */     return substring(paramString, paramInt1, paramInt2, ' ');
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
/*      */   public static String substring(String paramString, int paramInt1, int paramInt2, char paramChar)
/*      */   {
/* 2634 */     int i = paramString.length();
/*      */     
/* 2636 */     if ((paramInt1 < 0) || (paramInt1 >= i)) {
/* 2637 */       throw new StringIndexOutOfBoundsException(paramInt1);
/*      */     }
/*      */     
/* 2640 */     if (paramInt2 < 0) {
/* 2641 */       throw new StringIndexOutOfBoundsException(paramInt2);
/*      */     }
/*      */     
/* 2644 */     if (paramInt2 > i - paramInt1) {
/* 2645 */       char[] arrayOfChar = new char[paramInt2];
/*      */       
/* 2647 */       paramString.getChars(paramInt1, i, arrayOfChar, 0);
/*      */       
/* 2649 */       for (int j = i - paramInt1; j < paramInt2; j++)
/*      */       {
/* 2651 */         arrayOfChar[j] = paramChar;
/*      */       }
/*      */       
/* 2654 */       return new String(arrayOfChar);
/*      */     }
/*      */     
/* 2657 */     return paramString.substring(paramInt1, paramInt1 + paramInt2);
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
/*      */   public static String subWords(String paramString, int paramInt1, int paramInt2)
/*      */   {
/* 2675 */     int i = paramString.length();
/*      */     
/* 2677 */     if ((paramInt1 < 0) || (paramInt2 <= 0)) {
/* 2678 */       return "";
/*      */     }
/*      */     
/* 2681 */     int j = indexOfWord(paramString, paramInt1);
/*      */     
/* 2683 */     if (j >= 0) {
/* 2684 */       int k = indexOfWord(paramString.substring(j), paramInt2 - 1);
/*      */       
/* 2686 */       if (k >= 0)
/*      */       {
/* 2688 */         int m = j + k;
/* 2689 */         while ((m < i) && (!Character.isWhitespace(paramString.charAt(m)))) m++;
/* 2690 */         return paramString.substring(j, m);
/*      */       }
/*      */       
/* 2693 */       return stripTrailingBlanks(paramString.substring(j));
/*      */     }
/*      */     
/*      */ 
/* 2697 */     return "";
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean toBoolean(String paramString)
/*      */   {
/* 2709 */     return Boolean.valueOf(paramString).booleanValue();
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
/*      */   public static byte toByte(String paramString)
/*      */     throws NumberFormatException
/*      */   {
/* 2724 */     return Byte.valueOf(paramString).byteValue();
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
/*      */   public static byte toByte(String paramString, int paramInt)
/*      */     throws NumberFormatException
/*      */   {
/* 2741 */     return Byte.valueOf(paramString, paramInt).byteValue();
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
/*      */   public static double toDouble(String paramString)
/*      */     throws NumberFormatException
/*      */   {
/* 2756 */     return Double.valueOf(paramString).doubleValue();
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
/*      */   public static float toFloat(String paramString)
/*      */     throws NumberFormatException
/*      */   {
/* 2771 */     return Float.valueOf(paramString).floatValue();
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
/*      */   public static int toInt(String paramString)
/*      */     throws NumberFormatException
/*      */   {
/* 2786 */     return Integer.valueOf(paramString).intValue();
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
/*      */   public static int toInt(String paramString, int paramInt)
/*      */     throws NumberFormatException
/*      */   {
/* 2803 */     return Integer.valueOf(paramString, paramInt).intValue();
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
/*      */   public static long toLong(String paramString)
/*      */     throws NumberFormatException
/*      */   {
/* 2818 */     return Long.valueOf(paramString).longValue();
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
/*      */   public static long toLong(String paramString, int paramInt)
/*      */     throws NumberFormatException
/*      */   {
/* 2835 */     return Long.valueOf(paramString, paramInt).longValue();
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
/*      */   public static String toLowerCase(String paramString)
/*      */   {
/* 2848 */     return paramString.toLowerCase();
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
/*      */   public static String toLowerCase(String paramString, Locale paramLocale)
/*      */   {
/* 2861 */     return paramString.toLowerCase(paramLocale);
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
/*      */   public static short toShort(String paramString)
/*      */     throws NumberFormatException
/*      */   {
/* 2876 */     return Short.valueOf(paramString).shortValue();
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
/*      */   public static short toShort(String paramString, int paramInt)
/*      */     throws NumberFormatException
/*      */   {
/* 2893 */     return Short.valueOf(paramString, paramInt).shortValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static String toTitleCase(String paramString)
/*      */   {
/* 2905 */     int i = paramString.length();
/*      */     
/* 2907 */     char[] arrayOfChar = new char[i];
/*      */     
/* 2909 */     paramString.getChars(0, i, arrayOfChar, 0);
/*      */     
/* 2911 */     for (int j = 0; j < i; j++)
/*      */     {
/* 2913 */       Character.toTitleCase(arrayOfChar[j]);
/*      */     }
/*      */     
/* 2916 */     return new String(arrayOfChar);
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
/*      */   public static String toUpperCase(String paramString)
/*      */   {
/* 2929 */     return paramString.toUpperCase();
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
/*      */   public static String toUpperCase(String paramString, Locale paramLocale)
/*      */   {
/* 2942 */     return paramString.toUpperCase(paramLocale);
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
/*      */   public static String translate(String paramString1, String paramString2, String paramString3)
/*      */   {
/* 2964 */     return translate(paramString1, paramString2, paramString3, ' ');
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
/*      */   public static String translate(String paramString1, String paramString2, String paramString3, char paramChar)
/*      */   {
/* 2987 */     return translate(paramString1, paramString2, paramString3, paramChar, false);
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
/*      */ 
/*      */   public static String translate(String paramString1, String paramString2, String paramString3, char paramChar, boolean paramBoolean)
/*      */   {
/* 3013 */     int i = paramString1.length();
/* 3014 */     int j = paramString3.length();
/*      */     
/* 3016 */     char[] arrayOfChar = new char[i];
/*      */     
/* 3018 */     paramString1.getChars(0, i, arrayOfChar, 0);
/*      */     
/* 3020 */     int k = 0;
/*      */     
/*      */ 
/* 3023 */     while ((k = indexOfAnyOf(paramString1, paramString2, k, paramBoolean)) >= 0)
/*      */     {
/* 3025 */       int m = indexOf(paramString2, arrayOfChar[k], paramBoolean);
/* 3026 */       if (m >= j) {
/* 3027 */         arrayOfChar[k] = paramChar;
/*      */       }
/*      */       else {
/* 3030 */         arrayOfChar[k] = paramString3.charAt(m);
/*      */       }
/* 3032 */       k++;
/*      */     }
/*      */     
/* 3035 */     return new String(arrayOfChar);
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
/*      */   public static String translate(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
/*      */   {
/* 3060 */     return translate(paramString1, paramString2, paramString3, ' ', paramBoolean);
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
/*      */   public static String word(String paramString, int paramInt)
/*      */   {
/* 3074 */     int i = paramString.length();
/*      */     
/*      */ 
/* 3077 */     if (paramInt < 0) {
/* 3078 */       return "";
/*      */     }
/*      */     
/* 3081 */     int j = indexOfWord(paramString, paramInt);
/*      */     
/* 3083 */     if (j >= 0)
/*      */     {
/* 3085 */       int k = j;
/* 3086 */       while ((k < i) && (!Character.isWhitespace(paramString.charAt(k)))) { k++;
/*      */       }
/* 3088 */       return paramString.substring(j, k);
/*      */     }
/*      */     
/* 3091 */     return "";
/*      */   }
/*      */   
/*      */   private static WordEntry wordEntryOfPhrase(String paramString1, String paramString2, int paramInt, boolean paramBoolean) {
/* 3095 */     int i = paramString1.length();
/* 3096 */     int j = paramString2.length();
/*      */     
/* 3098 */     if ((i == 0) || (j == 0)) {
/* 3099 */       return null;
/*      */     }
/*      */     
/* 3102 */     if (paramInt < 0) {
/* 3103 */       paramInt = 0;
/*      */     }
/*      */     
/* 3106 */     Vector localVector1 = buildWordList(paramString1, i);
/* 3107 */     Vector localVector2 = buildWordList(paramString2, j);
/*      */     
/* 3109 */     int k = localVector1.size();
/* 3110 */     int m = localVector2.size();
/* 3111 */     int n = paramInt;
/* 3112 */     int i1 = 0;
/* 3113 */     int i2 = paramInt;
/* 3114 */     int i3 = -1;
/*      */     
/* 3116 */     while ((n < k) && (i1 < m))
/*      */     {
/* 3118 */       WordEntry localWordEntry1 = (WordEntry)localVector1.elementAt(n);
/* 3119 */       WordEntry localWordEntry2 = (WordEntry)localVector2.elementAt(i1);
/*      */       
/* 3121 */       if (paramString1.regionMatches(paramBoolean, localWordEntry1.start, paramString2, localWordEntry2.start, localWordEntry2.len))
/*      */       {
/* 3123 */         n++;
/* 3124 */         i1++;
/*      */       }
/*      */       else
/*      */       {
/* 3128 */         i2++;n = i2;
/* 3129 */         i1 = 0;
/*      */       }
/*      */     }
/*      */     
/* 3133 */     if (i1 == m)
/*      */     {
/* 3135 */       return (WordEntry)localVector1.elementAt(i2);
/*      */     }
/*      */     
/* 3138 */     return null;
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
/*      */   public static int wordIndexOfPhrase(String paramString1, String paramString2, int paramInt)
/*      */   {
/* 3156 */     return wordIndexOfPhrase(paramString1, paramString2, paramInt, false);
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
/*      */   public static int wordIndexOfPhrase(String paramString1, String paramString2, int paramInt, boolean paramBoolean)
/*      */   {
/* 3176 */     WordEntry localWordEntry = wordEntryOfPhrase(paramString1, paramString2, paramInt, paramBoolean);
/*      */     
/* 3178 */     if (localWordEntry == null) {
/* 3179 */       return -1;
/*      */     }
/*      */     
/* 3182 */     return localWordEntry.wordNum;
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
/*      */   public static String wordSpace(String paramString, int paramInt)
/*      */   {
/* 3197 */     int i = paramString.length();
/*      */     
/*      */ 
/* 3200 */     if (paramInt < 0) {
/* 3201 */       return paramString;
/*      */     }
/*      */     
/* 3204 */     Vector localVector = buildWordList(paramString, i);
/*      */     
/* 3206 */     int k = localVector.size();
/*      */     
/* 3208 */     if (k == 0) {
/* 3209 */       return "";
/*      */     }
/*      */     
/*      */ 
/* 3213 */     int m = 0;
/*      */     
/* 3215 */     for (int j = 0; j < k; j++)
/*      */     {
/* 3217 */       localObject = (WordEntry)localVector.elementAt(j);
/* 3218 */       m += ((WordEntry)localObject).len;
/*      */     }
/*      */     
/* 3221 */     Object localObject = new char[m + (k - 1) * paramInt];
/*      */     
/* 3223 */     int n = 0;
/*      */     
/* 3225 */     for (j = 0; j < k; j++)
/*      */     {
/* 3227 */       WordEntry localWordEntry = (WordEntry)localVector.elementAt(j);
/* 3228 */       paramString.getChars(localWordEntry.start, localWordEntry.start + localWordEntry.len, (char[])localObject, n);
/* 3229 */       n += localWordEntry.len;
/*      */       
/* 3231 */       if (j < k - 1) {
/* 3232 */         for (int i1 = 0; i1 < paramInt; i1++)
/*      */         {
/* 3234 */           localObject[(n++)] = 32;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 3239 */     return new String((char[])localObject);
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
/*      */   public static String xRange(char paramChar1, char paramChar2)
/*      */   {
/* 3252 */     if (paramChar1 <= paramChar2) {
/* 3253 */       char c1 = paramChar2 - paramChar1 + 1;
/* 3254 */       char[] arrayOfChar = new char[c1];
/*      */       
/* 3256 */       for (char c2 = '\000'; c2 < c1; c2++)
/*      */       {
/* 3258 */         arrayOfChar[c2] = ((char)(paramChar1 + c2));
/*      */       }
/* 3260 */       return new String(arrayOfChar);
/*      */     }
/*      */     
/* 3263 */     return "";
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/ViolinStrings/Strings.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */