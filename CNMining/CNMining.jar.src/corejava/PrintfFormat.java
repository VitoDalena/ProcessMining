/*      */ package corejava;
/*      */ 
/*      */ import java.text.DecimalFormatSymbols;
/*      */ import java.util.Enumeration;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PrintfFormat
/*      */ {
/*      */   private class ConversionSpecification
/*      */   {
/*      */     ConversionSpecification() {}
/*      */     
/*      */     ConversionSpecification(String paramString)
/*      */       throws IllegalArgumentException
/*      */     {
/*  496 */       if (paramString == null)
/*  497 */         throw new NullPointerException();
/*  498 */       if (paramString.length() == 0) {
/*  499 */         throw new IllegalArgumentException("Control strings must have positive lengths.");
/*      */       }
/*      */       
/*  502 */       if (paramString.charAt(0) == '%') {
/*  503 */         this.fmt = paramString;
/*  504 */         this.pos = 1;
/*  505 */         setArgPosition();
/*  506 */         setFlagCharacters();
/*  507 */         setFieldWidth();
/*  508 */         setPrecision();
/*  509 */         setOptionalHL();
/*  510 */         if (setConversionCharacter()) {
/*  511 */           if (this.pos == paramString.length()) {
/*  512 */             if ((this.leadingZeros) && (this.leftJustify))
/*  513 */               this.leadingZeros = false;
/*  514 */             if ((this.precisionSet) && (this.leadingZeros) && (
/*  515 */               (this.conversionCharacter == 'd') || (this.conversionCharacter == 'i') || (this.conversionCharacter == 'o') || (this.conversionCharacter == 'x')))
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*  520 */               this.leadingZeros = false;
/*      */             }
/*      */           }
/*      */           else
/*      */           {
/*  525 */             throw new IllegalArgumentException("Malformed conversion specification=" + paramString);
/*      */           }
/*      */           
/*      */         }
/*      */         else {
/*  530 */           throw new IllegalArgumentException("Malformed conversion specification=" + paramString);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  535 */         throw new IllegalArgumentException("Control strings must begin with %.");
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     void setLiteral(String paramString)
/*      */     {
/*  543 */       this.fmt = paramString;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     String getLiteral()
/*      */     {
/*  552 */       StringBuffer localStringBuffer = new StringBuffer();
/*  553 */       int i = 0;
/*  554 */       while (i < this.fmt.length())
/*  555 */         if (this.fmt.charAt(i) == '\\') {
/*  556 */           i++;
/*  557 */           if (i < this.fmt.length()) {
/*  558 */             int j = this.fmt.charAt(i);
/*  559 */             switch (j) {
/*      */             case 97: 
/*  561 */               localStringBuffer.append('\007');
/*  562 */               break;
/*      */             case 98: 
/*  564 */               localStringBuffer.append('\b');
/*  565 */               break;
/*      */             case 102: 
/*  567 */               localStringBuffer.append('\f');
/*  568 */               break;
/*      */             case 110: 
/*  570 */               localStringBuffer.append(System.getProperty("line.separator"));
/*  571 */               break;
/*      */             case 114: 
/*  573 */               localStringBuffer.append('\r');
/*  574 */               break;
/*      */             case 116: 
/*  576 */               localStringBuffer.append('\t');
/*  577 */               break;
/*      */             case 118: 
/*  579 */               localStringBuffer.append('\013');
/*  580 */               break;
/*      */             case 92: 
/*  582 */               localStringBuffer.append('\\');
/*      */             }
/*      */             
/*  585 */             i++;
/*      */           }
/*      */           else {
/*  588 */             localStringBuffer.append('\\');
/*      */           }
/*      */         } else {
/*  591 */           i++;
/*      */         }
/*  593 */       return this.fmt;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     char getConversionCharacter()
/*      */     {
/*  602 */       return this.conversionCharacter;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     boolean isVariableFieldWidth()
/*      */     {
/*  613 */       return this.variableFieldWidth;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     void setFieldWidthWithArg(int paramInt)
/*      */     {
/*  622 */       if (paramInt < 0) this.leftJustify = true;
/*  623 */       this.fieldWidthSet = true;
/*  624 */       this.fieldWidth = Math.abs(paramInt);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     boolean isVariablePrecision()
/*      */     {
/*  635 */       return this.variablePrecision;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     void setPrecisionWithArg(int paramInt)
/*      */     {
/*  643 */       this.precisionSet = true;
/*  644 */       this.precision = Math.max(paramInt, 0);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     String internalsprintf(int paramInt)
/*      */       throws IllegalArgumentException
/*      */     {
/*  656 */       String str = "";
/*  657 */       switch (this.conversionCharacter) {
/*      */       case 'd': 
/*      */       case 'i': 
/*  660 */         if (this.optionalh) {
/*  661 */           str = printDFormat((short)paramInt);
/*  662 */         } else if (this.optionall) {
/*  663 */           str = printDFormat(paramInt);
/*      */         } else
/*  665 */           str = printDFormat(paramInt);
/*  666 */         break;
/*      */       case 'X': 
/*      */       case 'x': 
/*  669 */         if (this.optionalh) {
/*  670 */           str = printXFormat((short)paramInt);
/*  671 */         } else if (this.optionall) {
/*  672 */           str = printXFormat(paramInt);
/*      */         } else
/*  674 */           str = printXFormat(paramInt);
/*  675 */         break;
/*      */       case 'o': 
/*  677 */         if (this.optionalh) {
/*  678 */           str = printOFormat((short)paramInt);
/*  679 */         } else if (this.optionall) {
/*  680 */           str = printOFormat(paramInt);
/*      */         } else
/*  682 */           str = printOFormat(paramInt);
/*  683 */         break;
/*      */       case 'C': 
/*      */       case 'c': 
/*  686 */         str = printCFormat((char)paramInt);
/*  687 */         break;
/*      */       default: 
/*  689 */         throw new IllegalArgumentException("Cannot format a int with a format using a " + this.conversionCharacter + " conversion character.");
/*      */       }
/*      */       
/*      */       
/*      */ 
/*  694 */       return str;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     String internalsprintf(long paramLong)
/*      */       throws IllegalArgumentException
/*      */     {
/*  706 */       String str = "";
/*  707 */       switch (this.conversionCharacter) {
/*      */       case 'd': 
/*      */       case 'i': 
/*  710 */         if (this.optionalh) {
/*  711 */           str = printDFormat((short)(int)paramLong);
/*  712 */         } else if (this.optionall) {
/*  713 */           str = printDFormat(paramLong);
/*      */         } else
/*  715 */           str = printDFormat((int)paramLong);
/*  716 */         break;
/*      */       case 'X': 
/*      */       case 'x': 
/*  719 */         if (this.optionalh) {
/*  720 */           str = printXFormat((short)(int)paramLong);
/*  721 */         } else if (this.optionall) {
/*  722 */           str = printXFormat(paramLong);
/*      */         } else
/*  724 */           str = printXFormat((int)paramLong);
/*  725 */         break;
/*      */       case 'o': 
/*  727 */         if (this.optionalh) {
/*  728 */           str = printOFormat((short)(int)paramLong);
/*  729 */         } else if (this.optionall) {
/*  730 */           str = printOFormat(paramLong);
/*      */         } else
/*  732 */           str = printOFormat((int)paramLong);
/*  733 */         break;
/*      */       case 'C': 
/*      */       case 'c': 
/*  736 */         str = printCFormat((char)(int)paramLong);
/*  737 */         break;
/*      */       default: 
/*  739 */         throw new IllegalArgumentException("Cannot format a long with a format using a " + this.conversionCharacter + " conversion character.");
/*      */       }
/*      */       
/*      */       
/*  743 */       return str;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     String internalsprintf(double paramDouble)
/*      */       throws IllegalArgumentException
/*      */     {
/*  756 */       String str = "";
/*  757 */       switch (this.conversionCharacter) {
/*      */       case 'f': 
/*  759 */         str = printFFormat(paramDouble);
/*  760 */         break;
/*      */       case 'E': 
/*      */       case 'e': 
/*  763 */         str = printEFormat(paramDouble);
/*  764 */         break;
/*      */       case 'G': 
/*      */       case 'g': 
/*  767 */         str = printGFormat(paramDouble);
/*  768 */         break;
/*      */       default: 
/*  770 */         throw new IllegalArgumentException("Cannot format a double with a format using a " + this.conversionCharacter + " conversion character.");
/*      */       }
/*      */       
/*      */       
/*  774 */       return str;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     String internalsprintf(String paramString)
/*      */       throws IllegalArgumentException
/*      */     {
/*  786 */       String str = "";
/*  787 */       if ((this.conversionCharacter == 's') || (this.conversionCharacter == 'S'))
/*      */       {
/*  789 */         str = printSFormat(paramString);
/*      */       } else {
/*  791 */         throw new IllegalArgumentException("Cannot format a String with a format using a " + this.conversionCharacter + " conversion character.");
/*      */       }
/*      */       
/*  794 */       return str;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     String internalsprintf(Object paramObject)
/*      */     {
/*  805 */       String str = "";
/*  806 */       if ((this.conversionCharacter == 's') || (this.conversionCharacter == 'S'))
/*      */       {
/*  808 */         str = printSFormat(paramObject.toString());
/*      */       } else {
/*  810 */         throw new IllegalArgumentException("Cannot format a String with a format using a " + this.conversionCharacter + " conversion character.");
/*      */       }
/*      */       
/*      */ 
/*  814 */       return str;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private char[] fFormatDigits(double paramDouble)
/*      */     {
/*  843 */       int i1 = 0;
/*  844 */       int i2 = 0;
/*  845 */       String str; if (paramDouble > 0.0D) {
/*  846 */         str = Double.toString(paramDouble);
/*  847 */       } else if (paramDouble < 0.0D) {
/*  848 */         str = Double.toString(-paramDouble);
/*  849 */         i2 = 1;
/*      */       }
/*      */       else {
/*  852 */         str = Double.toString(paramDouble);
/*  853 */         if (str.charAt(0) == '-') {
/*  854 */           i2 = 1;
/*  855 */           str = str.substring(1);
/*      */         }
/*      */       }
/*  858 */       int i3 = str.indexOf('E');
/*  859 */       int i4 = str.indexOf('.');
/*  860 */       int m; if (i4 != -1) { m = i4;
/*  861 */       } else if (i3 != -1) m = i3; else
/*  862 */         m = str.length();
/*  863 */       int n; if (i4 != -1) {
/*  864 */         if (i3 != -1) n = i3 - i4 - 1; else {
/*  865 */           n = str.length() - i4 - 1;
/*      */         }
/*      */       } else
/*  868 */         n = 0;
/*  869 */       int i5; if (i3 != -1) {
/*  870 */         i5 = i3 + 1;
/*  871 */         i1 = 0;
/*  872 */         if (str.charAt(i5) == '-') {
/*  873 */           for (i5++; i5 < str.length(); i5++)
/*  874 */             if (str.charAt(i5) != '0') break;
/*  875 */           if (i5 < str.length()) {
/*  876 */             i1 = -Integer.parseInt(str.substring(i5));
/*      */           }
/*      */         } else {
/*  879 */           if (str.charAt(i5) == '+') i5++;
/*  880 */           for (; i5 < str.length(); i5++)
/*  881 */             if (str.charAt(i5) != '0') break;
/*  882 */           if (i5 < str.length()) {
/*  883 */             i1 = Integer.parseInt(str.substring(i5));
/*      */           }
/*      */         }
/*      */       }
/*  887 */       if (this.precisionSet) i5 = this.precision; else
/*  888 */         i5 = 5;
/*  889 */       char[] arrayOfChar1 = str.toCharArray();
/*  890 */       char[] arrayOfChar2 = new char[m + n];
/*      */       
/*  892 */       for (int j = 0; j < m; j++)
/*  893 */         arrayOfChar2[j] = arrayOfChar1[j];
/*  894 */       int i = j + 1;
/*  895 */       for (int k = 0; k < n; k++) {
/*  896 */         arrayOfChar2[j] = arrayOfChar1[i];j++;i++; }
/*  897 */       char[] arrayOfChar3; if (m + i1 <= 0) {
/*  898 */         arrayOfChar3 = new char[-i1 + n];
/*  899 */         j = 0; for (k = 0; k < -m - i1; j++) {
/*  900 */           arrayOfChar3[j] = '0';k++; }
/*  901 */         for (i = 0; i < m + n; j++) {
/*  902 */           arrayOfChar3[j] = arrayOfChar2[i];i++;
/*      */         }
/*      */       } else {
/*  905 */         arrayOfChar3 = arrayOfChar2; }
/*  906 */       boolean bool = false;
/*  907 */       if (i5 < -i1 + n) {
/*  908 */         if (i1 < 0) i = i5; else
/*  909 */           i = i5 + m;
/*  910 */         bool = checkForCarry(arrayOfChar3, i);
/*  911 */         if (bool)
/*  912 */           bool = startSymbolicCarry(arrayOfChar3, i - 1, 0); }
/*      */       char[] arrayOfChar4;
/*  914 */       if (m + i1 <= 0) {
/*  915 */         arrayOfChar4 = new char[2 + i5];
/*  916 */         if (!bool) arrayOfChar4[0] = '0'; else
/*  917 */           arrayOfChar4[0] = '1';
/*  918 */         if ((this.alternateForm) || (!this.precisionSet) || (this.precision != 0)) {
/*  919 */           arrayOfChar4[1] = '.';
/*  920 */           i = 0; for (j = 2; i < Math.min(i5, arrayOfChar3.length); j++) {
/*  921 */             arrayOfChar4[j] = arrayOfChar3[i];i++; }
/*  922 */           for (; j < arrayOfChar4.length; j++) arrayOfChar4[j] = '0';
/*      */         }
/*      */       }
/*      */       else {
/*  926 */         if (!bool) {
/*  927 */           if ((this.alternateForm) || (!this.precisionSet) || (this.precision != 0))
/*      */           {
/*  929 */             arrayOfChar4 = new char[m + i1 + i5 + 1];
/*      */           } else
/*  931 */             arrayOfChar4 = new char[m + i1];
/*  932 */           j = 0;
/*      */         }
/*      */         else {
/*  935 */           if ((this.alternateForm) || (!this.precisionSet) || (this.precision != 0))
/*      */           {
/*  937 */             arrayOfChar4 = new char[m + i1 + i5 + 2];
/*      */           } else
/*  939 */             arrayOfChar4 = new char[m + i1 + 1];
/*  940 */           arrayOfChar4[0] = '1';
/*  941 */           j = 1;
/*      */         }
/*  943 */         for (i = 0; i < Math.min(m + i1, arrayOfChar3.length); j++) {
/*  944 */           arrayOfChar4[j] = arrayOfChar3[i];i++; }
/*  945 */         for (; i < m + i1; j++) {
/*  946 */           arrayOfChar4[j] = '0';i++; }
/*  947 */         if ((this.alternateForm) || (!this.precisionSet) || (this.precision != 0)) {
/*  948 */           arrayOfChar4[j] = '.';j++;
/*  949 */           k = 0;
/*  950 */           do { arrayOfChar4[j] = arrayOfChar3[i];i++;j++;k++;
/*  949 */             if (i >= arrayOfChar3.length) break; } while (k < i5);
/*  951 */           for (; 
/*  951 */               j < arrayOfChar4.length; j++) arrayOfChar4[j] = '0';
/*      */         }
/*      */       }
/*  954 */       int i6 = 0;
/*  955 */       if ((!this.leftJustify) && (this.leadingZeros)) {
/*  956 */         i7 = 0;
/*  957 */         if (this.thousands) {
/*  958 */           i8 = 0;
/*  959 */           if ((arrayOfChar4[0] == '+') || (arrayOfChar4[0] == '-') || (arrayOfChar4[0] == ' '))
/*  960 */             i8 = 1;
/*  961 */           for (i9 = i8; 
/*  962 */               i9 < arrayOfChar4.length; i9++)
/*  963 */             if (arrayOfChar4[i9] == '.') break;
/*  964 */           i7 = (i9 - i8) / 3;
/*      */         }
/*  966 */         if (this.fieldWidthSet)
/*  967 */           i6 = this.fieldWidth - arrayOfChar4.length;
/*  968 */         if (((i2 == 0) && ((this.leadingSign) || (this.leadingSpace))) || (i2 != 0))
/*  969 */           i6--;
/*  970 */         i6 -= i7;
/*  971 */         if (i6 < 0) i6 = 0;
/*      */       }
/*  973 */       j = 0;
/*  974 */       char[] arrayOfChar5; if (((i2 == 0) && ((this.leadingSign) || (this.leadingSpace))) || (i2 != 0)) {
/*  975 */         arrayOfChar5 = new char[arrayOfChar4.length + i6 + 1];
/*  976 */         j++;
/*      */       }
/*      */       else {
/*  979 */         arrayOfChar5 = new char[arrayOfChar4.length + i6]; }
/*  980 */       if (i2 == 0) {
/*  981 */         if (this.leadingSign) arrayOfChar5[0] = '+';
/*  982 */         if (this.leadingSpace) arrayOfChar5[0] = ' ';
/*      */       }
/*      */       else {
/*  985 */         arrayOfChar5[0] = '-'; }
/*  986 */       for (i = 0; i < i6; j++) {
/*  987 */         arrayOfChar5[j] = '0';i++; }
/*  988 */       for (i = 0; i < arrayOfChar4.length; j++) { arrayOfChar5[j] = arrayOfChar4[i];i++;
/*      */       }
/*  990 */       int i7 = 0;
/*  991 */       if ((arrayOfChar5[0] == '+') || (arrayOfChar5[0] == '-') || (arrayOfChar5[0] == ' '))
/*  992 */         i7 = 1;
/*  993 */       for (int i8 = i7; 
/*  994 */           i8 < arrayOfChar5.length; i8++)
/*  995 */         if (arrayOfChar5[i8] == '.') break;
/*  996 */       int i9 = (i8 - i7) / 3;
/*      */       
/*  998 */       if (i8 < arrayOfChar5.length)
/*  999 */         arrayOfChar5[i8] = PrintfFormat.this.dfs.getDecimalSeparator();
/* 1000 */       char[] arrayOfChar6 = arrayOfChar5;
/* 1001 */       if ((this.thousands) && (i9 > 0)) {
/* 1002 */         arrayOfChar6 = new char[arrayOfChar5.length + i9 + i7];
/* 1003 */         arrayOfChar6[0] = arrayOfChar5[0];
/* 1004 */         i = i7; for (k = i7; i < i8; i++) {
/* 1005 */           if ((i > 0) && ((i8 - i) % 3 == 0))
/*      */           {
/* 1007 */             arrayOfChar6[k] = PrintfFormat.this.dfs.getGroupingSeparator();
/* 1008 */             arrayOfChar6[(k + 1)] = arrayOfChar5[i];
/* 1009 */             k += 2;
/*      */           }
/*      */           else {
/* 1012 */             arrayOfChar6[k] = arrayOfChar5[i];k++;
/*      */           }
/*      */         }
/* 1015 */         for (; i < arrayOfChar5.length; k++) {
/* 1016 */           arrayOfChar6[k] = arrayOfChar5[i];i++;
/*      */         }
/*      */       }
/* 1019 */       return arrayOfChar6;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private String fFormatString(double paramDouble)
/*      */     {
/* 1031 */       int i = 0;
/*      */       char[] arrayOfChar1;
/* 1033 */       if (Double.isInfinite(paramDouble)) {
/* 1034 */         if (paramDouble == Double.POSITIVE_INFINITY) {
/* 1035 */           if (this.leadingSign) { arrayOfChar1 = "+Inf".toCharArray();
/* 1036 */           } else if (this.leadingSpace)
/* 1037 */             arrayOfChar1 = " Inf".toCharArray(); else {
/* 1038 */             arrayOfChar1 = "Inf".toCharArray();
/*      */           }
/*      */         } else
/* 1041 */           arrayOfChar1 = "-Inf".toCharArray();
/* 1042 */         i = 1;
/*      */       }
/* 1044 */       else if (Double.isNaN(paramDouble)) {
/* 1045 */         if (this.leadingSign) { arrayOfChar1 = "+NaN".toCharArray();
/* 1046 */         } else if (this.leadingSpace)
/* 1047 */           arrayOfChar1 = " NaN".toCharArray(); else
/* 1048 */           arrayOfChar1 = "NaN".toCharArray();
/* 1049 */         i = 1;
/*      */       }
/*      */       else {
/* 1052 */         arrayOfChar1 = fFormatDigits(paramDouble); }
/* 1053 */       char[] arrayOfChar2 = applyFloatPadding(arrayOfChar1, false);
/* 1054 */       return new String(arrayOfChar2);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private char[] eFormatDigits(double paramDouble, char paramChar)
/*      */     {
/* 1092 */       int i2 = 0;
/*      */       
/* 1094 */       int i6 = 0;
/* 1095 */       String str; if (paramDouble > 0.0D) {
/* 1096 */         str = Double.toString(paramDouble);
/* 1097 */       } else if (paramDouble < 0.0D) {
/* 1098 */         str = Double.toString(-paramDouble);
/* 1099 */         i6 = 1;
/*      */       }
/*      */       else {
/* 1102 */         str = Double.toString(paramDouble);
/* 1103 */         if (str.charAt(0) == '-') {
/* 1104 */           i6 = 1;
/* 1105 */           str = str.substring(1);
/*      */         }
/*      */       }
/* 1108 */       int i3 = str.indexOf('E');
/* 1109 */       if (i3 == -1) i3 = str.indexOf('e');
/* 1110 */       int i4 = str.indexOf('.');
/* 1111 */       int n; if (i4 != -1) { n = i4;
/* 1112 */       } else if (i3 != -1) n = i3; else
/* 1113 */         n = str.length();
/* 1114 */       int i1; if (i4 != -1) {
/* 1115 */         if (i3 != -1) i1 = i3 - i4 - 1; else {
/* 1116 */           i1 = str.length() - i4 - 1;
/*      */         }
/*      */       } else
/* 1119 */         i1 = 0;
/* 1120 */       if (i3 != -1) {
/* 1121 */         i7 = i3 + 1;
/* 1122 */         i2 = 0;
/* 1123 */         if (str.charAt(i7) == '-') {
/* 1124 */           for (i7++; i7 < str.length(); i7++)
/* 1125 */             if (str.charAt(i7) != '0') break;
/* 1126 */           if (i7 < str.length()) {
/* 1127 */             i2 = -Integer.parseInt(str.substring(i7));
/*      */           }
/*      */         } else {
/* 1130 */           if (str.charAt(i7) == '+') i7++;
/* 1131 */           for (; i7 < str.length(); i7++)
/* 1132 */             if (str.charAt(i7) != '0') break;
/* 1133 */           if (i7 < str.length())
/* 1134 */             i2 = Integer.parseInt(str.substring(i7));
/*      */         }
/*      */       }
/* 1137 */       if (i4 != -1) i2 += i4 - 1;
/* 1138 */       int m; if (this.precisionSet) m = this.precision; else
/* 1139 */         m = 5;
/* 1140 */       Object localObject; if ((i4 != -1) && (i3 != -1)) {
/* 1141 */         localObject = (str.substring(0, i4) + str.substring(i4 + 1, i3)).toCharArray();
/*      */       }
/* 1143 */       else if (i4 != -1) {
/* 1144 */         localObject = (str.substring(0, i4) + str.substring(i4 + 1)).toCharArray();
/*      */       }
/* 1146 */       else if (i3 != -1) {
/* 1147 */         localObject = str.substring(0, i3).toCharArray();
/*      */       } else
/* 1149 */         localObject = str.toCharArray();
/* 1150 */       int i7 = 0;
/* 1151 */       int i8 = 0;
/* 1152 */       if (localObject[0] != '0') {
/* 1153 */         i8 = 0;
/*      */       } else
/* 1155 */         for (i8 = 0; i8 < localObject.length; i8++)
/* 1156 */           if (localObject[i8] != '0') break;
/* 1157 */       char[] arrayOfChar1; if (i8 + m < localObject.length - 1) {
/* 1158 */         boolean bool = checkForCarry((char[])localObject, i8 + m + 1);
/* 1159 */         if (bool)
/* 1160 */           bool = startSymbolicCarry((char[])localObject, i8 + m, i8);
/* 1161 */         if (bool) {
/* 1162 */           arrayOfChar1 = new char[i8 + m + 1];
/* 1163 */           arrayOfChar1[i8] = '1';
/* 1164 */           for (j = 0; j < i8; j++) arrayOfChar1[j] = '0';
/* 1165 */           i = i8; for (j = i8 + 1; j < m + 1; j++) {
/* 1166 */             arrayOfChar1[j] = localObject[i];i++; }
/* 1167 */           i2++;
/* 1168 */           localObject = arrayOfChar1;
/*      */         } }
/*      */       int i5;
/* 1171 */       if ((Math.abs(i2) < 100) && (!this.optionalL)) i5 = 4; else
/* 1172 */         i5 = 5;
/* 1173 */       if ((this.alternateForm) || (!this.precisionSet) || (this.precision != 0)) {
/* 1174 */         arrayOfChar1 = new char[2 + m + i5];
/*      */       } else
/* 1176 */         arrayOfChar1 = new char[1 + i5];
/* 1177 */       if (localObject[0] != '0') {
/* 1178 */         arrayOfChar1[0] = localObject[0];
/* 1179 */         j = 1;
/*      */       }
/*      */       else {
/* 1182 */         for (j = 1; j < (i3 == -1 ? localObject.length : i3); j++)
/* 1183 */           if (localObject[j] != '0') break;
/* 1184 */         if (((i3 != -1) && (j < i3)) || ((i3 == -1) && (j < localObject.length)))
/*      */         {
/* 1186 */           arrayOfChar1[0] = localObject[j];
/* 1187 */           i2 -= j;
/* 1188 */           j++;
/*      */         }
/*      */         else {
/* 1191 */           arrayOfChar1[0] = '0';
/* 1192 */           j = 2;
/*      */         }
/*      */       }
/* 1195 */       if ((this.alternateForm) || (!this.precisionSet) || (this.precision != 0)) {
/* 1196 */         arrayOfChar1[1] = '.';
/* 1197 */         i = 2;
/*      */       }
/*      */       else {
/* 1200 */         i = 1; }
/* 1201 */       int k = 0;
/* 1202 */       do { arrayOfChar1[i] = localObject[j];j++;i++;k++;
/* 1201 */         if (k >= m) break; } while (j < localObject.length);
/* 1203 */       for (; 
/* 1203 */           i < arrayOfChar1.length - i5; i++)
/* 1204 */         arrayOfChar1[i] = '0';
/* 1205 */       arrayOfChar1[(i++)] = paramChar;
/* 1206 */       if (i2 < 0) arrayOfChar1[(i++)] = '-'; else
/* 1207 */         arrayOfChar1[(i++)] = '+';
/* 1208 */       i2 = Math.abs(i2);
/* 1209 */       if (i2 >= 100) {
/* 1210 */         switch (i2 / 100) {
/* 1211 */         case 1:  arrayOfChar1[i] = '1'; break;
/* 1212 */         case 2:  arrayOfChar1[i] = '2'; break;
/* 1213 */         case 3:  arrayOfChar1[i] = '3'; break;
/* 1214 */         case 4:  arrayOfChar1[i] = '4'; break;
/* 1215 */         case 5:  arrayOfChar1[i] = '5'; break;
/* 1216 */         case 6:  arrayOfChar1[i] = '6'; break;
/* 1217 */         case 7:  arrayOfChar1[i] = '7'; break;
/* 1218 */         case 8:  arrayOfChar1[i] = '8'; break;
/* 1219 */         case 9:  arrayOfChar1[i] = '9';
/*      */         }
/* 1221 */         i++;
/*      */       }
/* 1223 */       switch (i2 % 100 / 10) {
/* 1224 */       case 0:  arrayOfChar1[i] = '0'; break;
/* 1225 */       case 1:  arrayOfChar1[i] = '1'; break;
/* 1226 */       case 2:  arrayOfChar1[i] = '2'; break;
/* 1227 */       case 3:  arrayOfChar1[i] = '3'; break;
/* 1228 */       case 4:  arrayOfChar1[i] = '4'; break;
/* 1229 */       case 5:  arrayOfChar1[i] = '5'; break;
/* 1230 */       case 6:  arrayOfChar1[i] = '6'; break;
/* 1231 */       case 7:  arrayOfChar1[i] = '7'; break;
/* 1232 */       case 8:  arrayOfChar1[i] = '8'; break;
/* 1233 */       case 9:  arrayOfChar1[i] = '9';
/*      */       }
/* 1235 */       i++;
/* 1236 */       switch (i2 % 10) {
/* 1237 */       case 0:  arrayOfChar1[i] = '0'; break;
/* 1238 */       case 1:  arrayOfChar1[i] = '1'; break;
/* 1239 */       case 2:  arrayOfChar1[i] = '2'; break;
/* 1240 */       case 3:  arrayOfChar1[i] = '3'; break;
/* 1241 */       case 4:  arrayOfChar1[i] = '4'; break;
/* 1242 */       case 5:  arrayOfChar1[i] = '5'; break;
/* 1243 */       case 6:  arrayOfChar1[i] = '6'; break;
/* 1244 */       case 7:  arrayOfChar1[i] = '7'; break;
/* 1245 */       case 8:  arrayOfChar1[i] = '8'; break;
/* 1246 */       case 9:  arrayOfChar1[i] = '9';
/*      */       }
/* 1248 */       int i9 = 0;
/* 1249 */       if ((!this.leftJustify) && (this.leadingZeros)) {
/* 1250 */         i10 = 0;
/* 1251 */         if (this.thousands) {
/* 1252 */           i11 = 0;
/* 1253 */           if ((arrayOfChar1[0] == '+') || (arrayOfChar1[0] == '-') || (arrayOfChar1[0] == ' '))
/* 1254 */             i11 = 1;
/* 1255 */           for (i12 = i11; 
/* 1256 */               i12 < arrayOfChar1.length; i12++)
/* 1257 */             if (arrayOfChar1[i12] == '.') break;
/* 1258 */           i10 = (i12 - i11) / 3;
/*      */         }
/* 1260 */         if (this.fieldWidthSet)
/* 1261 */           i9 = this.fieldWidth - arrayOfChar1.length;
/* 1262 */         if (((i6 == 0) && ((this.leadingSign) || (this.leadingSpace))) || (i6 != 0))
/* 1263 */           i9--;
/* 1264 */         i9 -= i10;
/* 1265 */         if (i9 < 0) i9 = 0;
/*      */       }
/* 1267 */       int j = 0;
/* 1268 */       char[] arrayOfChar2; if (((i6 == 0) && ((this.leadingSign) || (this.leadingSpace))) || (i6 != 0)) {
/* 1269 */         arrayOfChar2 = new char[arrayOfChar1.length + i9 + 1];
/* 1270 */         j++;
/*      */       }
/*      */       else {
/* 1273 */         arrayOfChar2 = new char[arrayOfChar1.length + i9]; }
/* 1274 */       if (i6 == 0) {
/* 1275 */         if (this.leadingSign) arrayOfChar2[0] = '+';
/* 1276 */         if (this.leadingSpace) arrayOfChar2[0] = ' ';
/*      */       }
/*      */       else {
/* 1279 */         arrayOfChar2[0] = '-'; }
/* 1280 */       for (k = 0; k < i9; k++) {
/* 1281 */         arrayOfChar2[j] = '0';j++; }
/* 1282 */       for (int i = 0; (i < arrayOfChar1.length) && (j < arrayOfChar2.length); j++) {
/* 1283 */         arrayOfChar2[j] = arrayOfChar1[i];i++;
/*      */       }
/* 1285 */       int i10 = 0;
/* 1286 */       if ((arrayOfChar2[0] == '+') || (arrayOfChar2[0] == '-') || (arrayOfChar2[0] == ' '))
/* 1287 */         i10 = 1;
/* 1288 */       for (int i11 = i10; 
/* 1289 */           i11 < arrayOfChar2.length; i11++)
/* 1290 */         if (arrayOfChar2[i11] == '.') break;
/* 1291 */       int i12 = i11 / 3;
/*      */       
/* 1293 */       if (i11 < arrayOfChar2.length)
/* 1294 */         arrayOfChar2[i11] = PrintfFormat.this.dfs.getDecimalSeparator();
/* 1295 */       char[] arrayOfChar3 = arrayOfChar2;
/* 1296 */       if ((this.thousands) && (i12 > 0)) {
/* 1297 */         arrayOfChar3 = new char[arrayOfChar2.length + i12 + i10];
/* 1298 */         arrayOfChar3[0] = arrayOfChar2[0];
/* 1299 */         i = i10; for (k = i10; i < i11; i++) {
/* 1300 */           if ((i > 0) && ((i11 - i) % 3 == 0))
/*      */           {
/* 1302 */             arrayOfChar3[k] = PrintfFormat.this.dfs.getGroupingSeparator();
/* 1303 */             arrayOfChar3[(k + 1)] = arrayOfChar2[i];
/* 1304 */             k += 2;
/*      */           }
/*      */           else {
/* 1307 */             arrayOfChar3[k] = arrayOfChar2[i];k++;
/*      */           }
/*      */         }
/* 1310 */         for (; i < arrayOfChar2.length; k++) {
/* 1311 */           arrayOfChar3[k] = arrayOfChar2[i];i++;
/*      */         } }
/* 1313 */       return arrayOfChar3;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private boolean checkForCarry(char[] paramArrayOfChar, int paramInt)
/*      */     {
/* 1326 */       boolean bool = false;
/* 1327 */       if (paramInt < paramArrayOfChar.length) {
/* 1328 */         if ((paramArrayOfChar[paramInt] == '6') || (paramArrayOfChar[paramInt] == '7') || (paramArrayOfChar[paramInt] == '8') || (paramArrayOfChar[paramInt] == '9')) {
/* 1329 */           bool = true;
/* 1330 */         } else if (paramArrayOfChar[paramInt] == '5') {
/* 1331 */           for (int i = paramInt + 1; 
/* 1332 */               i < paramArrayOfChar.length; i++)
/* 1333 */             if (paramArrayOfChar[i] != '0') break;
/* 1334 */           bool = i < paramArrayOfChar.length;
/* 1335 */           if ((!bool) && (paramInt > 0)) {
/* 1336 */             bool = (paramArrayOfChar[(paramInt - 1)] == '1') || (paramArrayOfChar[(paramInt - 1)] == '3') || (paramArrayOfChar[(paramInt - 1)] == '5') || (paramArrayOfChar[(paramInt - 1)] == '7') || (paramArrayOfChar[(paramInt - 1)] == '9');
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1342 */       return bool;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private boolean startSymbolicCarry(char[] paramArrayOfChar, int paramInt1, int paramInt2)
/*      */     {
/* 1359 */       boolean bool = true;
/* 1360 */       for (int i = paramInt1; (bool) && (i >= paramInt2); i--) {
/* 1361 */         bool = false;
/* 1362 */         switch (paramArrayOfChar[i]) {
/* 1363 */         case '0':  paramArrayOfChar[i] = '1'; break;
/* 1364 */         case '1':  paramArrayOfChar[i] = '2'; break;
/* 1365 */         case '2':  paramArrayOfChar[i] = '3'; break;
/* 1366 */         case '3':  paramArrayOfChar[i] = '4'; break;
/* 1367 */         case '4':  paramArrayOfChar[i] = '5'; break;
/* 1368 */         case '5':  paramArrayOfChar[i] = '6'; break;
/* 1369 */         case '6':  paramArrayOfChar[i] = '7'; break;
/* 1370 */         case '7':  paramArrayOfChar[i] = '8'; break;
/* 1371 */         case '8':  paramArrayOfChar[i] = '9'; break;
/* 1372 */         case '9':  paramArrayOfChar[i] = '0';bool = true;
/*      */         }
/*      */       }
/* 1375 */       return bool;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private String eFormatString(double paramDouble, char paramChar)
/*      */     {
/* 1389 */       int i = 0;
/*      */       char[] arrayOfChar1;
/* 1391 */       if (Double.isInfinite(paramDouble)) {
/* 1392 */         if (paramDouble == Double.POSITIVE_INFINITY) {
/* 1393 */           if (this.leadingSign) { arrayOfChar1 = "+Inf".toCharArray();
/* 1394 */           } else if (this.leadingSpace)
/* 1395 */             arrayOfChar1 = " Inf".toCharArray(); else {
/* 1396 */             arrayOfChar1 = "Inf".toCharArray();
/*      */           }
/*      */         } else
/* 1399 */           arrayOfChar1 = "-Inf".toCharArray();
/* 1400 */         i = 1;
/*      */       }
/* 1402 */       else if (Double.isNaN(paramDouble)) {
/* 1403 */         if (this.leadingSign) { arrayOfChar1 = "+NaN".toCharArray();
/* 1404 */         } else if (this.leadingSpace)
/* 1405 */           arrayOfChar1 = " NaN".toCharArray(); else
/* 1406 */           arrayOfChar1 = "NaN".toCharArray();
/* 1407 */         i = 1;
/*      */       }
/*      */       else {
/* 1410 */         arrayOfChar1 = eFormatDigits(paramDouble, paramChar); }
/* 1411 */       char[] arrayOfChar2 = applyFloatPadding(arrayOfChar1, false);
/* 1412 */       return new String(arrayOfChar2);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private char[] applyFloatPadding(char[] paramArrayOfChar, boolean paramBoolean)
/*      */     {
/* 1423 */       char[] arrayOfChar = paramArrayOfChar;
/* 1424 */       if (this.fieldWidthSet) { int k;
/*      */         int i;
/* 1426 */         int j; if (this.leftJustify) {
/* 1427 */           k = this.fieldWidth - paramArrayOfChar.length;
/* 1428 */           if (k > 0) {
/* 1429 */             arrayOfChar = new char[paramArrayOfChar.length + k];
/* 1430 */             for (i = 0; i < paramArrayOfChar.length; i++)
/* 1431 */               arrayOfChar[i] = paramArrayOfChar[i];
/* 1432 */             for (j = 0; j < k; i++) {
/* 1433 */               arrayOfChar[i] = ' ';j++;
/*      */             }
/*      */           }
/* 1436 */         } else if ((!this.leadingZeros) || (paramBoolean)) {
/* 1437 */           k = this.fieldWidth - paramArrayOfChar.length;
/* 1438 */           if (k > 0) {
/* 1439 */             arrayOfChar = new char[paramArrayOfChar.length + k];
/* 1440 */             for (i = 0; i < k; i++)
/* 1441 */               arrayOfChar[i] = ' ';
/* 1442 */             for (j = 0; j < paramArrayOfChar.length; j++) {
/* 1443 */               arrayOfChar[i] = paramArrayOfChar[j];i++;
/*      */             }
/*      */           }
/* 1446 */         } else if (this.leadingZeros) {
/* 1447 */           k = this.fieldWidth - paramArrayOfChar.length;
/* 1448 */           if (k > 0) {
/* 1449 */             arrayOfChar = new char[paramArrayOfChar.length + k];
/* 1450 */             i = 0;j = 0;
/* 1451 */             if (paramArrayOfChar[0] == '-') { arrayOfChar[0] = '-';i++;j++; }
/* 1452 */             for (int m = 0; m < k; m++) {
/* 1453 */               arrayOfChar[i] = '0';i++; }
/* 1454 */             for (; j < paramArrayOfChar.length; j++) {
/* 1455 */               arrayOfChar[i] = paramArrayOfChar[j];i++;
/*      */             }
/*      */           }
/*      */         } }
/* 1459 */       return arrayOfChar;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     private String printFFormat(double paramDouble)
/*      */     {
/* 1467 */       return fFormatString(paramDouble);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private String printEFormat(double paramDouble)
/*      */     {
/* 1476 */       if (this.conversionCharacter == 'e') {
/* 1477 */         return eFormatString(paramDouble, 'e');
/*      */       }
/* 1479 */       return eFormatString(paramDouble, 'E');
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private String printGFormat(double paramDouble)
/*      */     {
/* 1509 */       int i = this.precision;
/*      */       
/*      */ 
/* 1512 */       int k = 0;
/* 1513 */       char[] arrayOfChar1; if (Double.isInfinite(paramDouble)) {
/* 1514 */         if (paramDouble == Double.POSITIVE_INFINITY) {
/* 1515 */           if (this.leadingSign) { arrayOfChar1 = "+Inf".toCharArray();
/* 1516 */           } else if (this.leadingSpace)
/* 1517 */             arrayOfChar1 = " Inf".toCharArray(); else {
/* 1518 */             arrayOfChar1 = "Inf".toCharArray();
/*      */           }
/*      */         } else
/* 1521 */           arrayOfChar1 = "-Inf".toCharArray();
/* 1522 */         k = 1;
/*      */       }
/* 1524 */       else if (Double.isNaN(paramDouble)) {
/* 1525 */         if (this.leadingSign) { arrayOfChar1 = "+NaN".toCharArray();
/* 1526 */         } else if (this.leadingSpace)
/* 1527 */           arrayOfChar1 = " NaN".toCharArray(); else
/* 1528 */           arrayOfChar1 = "NaN".toCharArray();
/* 1529 */         k = 1;
/*      */       }
/*      */       else {
/* 1532 */         if (!this.precisionSet) this.precision = 6;
/* 1533 */         if (this.precision == 0) this.precision = 1;
/* 1534 */         int m = -1;
/* 1535 */         String str1; if (this.conversionCharacter == 'g') {
/* 1536 */           str1 = eFormatString(paramDouble, 'e').trim();
/* 1537 */           m = str1.indexOf('e');
/*      */         }
/*      */         else {
/* 1540 */           str1 = eFormatString(paramDouble, 'E').trim();
/* 1541 */           m = str1.indexOf('E');
/*      */         }
/* 1543 */         int j = m + 1;
/* 1544 */         int n = 0;
/* 1545 */         if (str1.charAt(j) == '-') {
/* 1546 */           for (j++; j < str1.length(); j++)
/* 1547 */             if (str1.charAt(j) != '0') break;
/* 1548 */           if (j < str1.length()) {
/* 1549 */             n = -Integer.parseInt(str1.substring(j));
/*      */           }
/*      */         } else {
/* 1552 */           if (str1.charAt(j) == '+') j++;
/* 1553 */           for (; j < str1.length(); j++)
/* 1554 */             if (str1.charAt(j) != '0') break;
/* 1555 */           if (j < str1.length()) {
/* 1556 */             n = Integer.parseInt(str1.substring(j));
/*      */           }
/*      */         }
/*      */         
/*      */         String str4;
/* 1561 */         if (!this.alternateForm) { String str2;
/* 1562 */           if ((n >= -4) && (n < this.precision)) {
/* 1563 */             str2 = fFormatString(paramDouble).trim();
/*      */           } else
/* 1565 */             str2 = str1.substring(0, m);
/* 1566 */           for (j = str2.length() - 1; 
/* 1567 */               j >= 0; j--)
/* 1568 */             if (str2.charAt(j) != '0') break;
/* 1569 */           if ((j >= 0) && (str2.charAt(j) == '.')) j--;
/* 1570 */           String str3; if (j == -1) { str3 = "0";
/* 1571 */           } else if (!Character.isDigit(str2.charAt(j)))
/* 1572 */             str3 = str2.substring(0, j + 1) + "0"; else
/* 1573 */             str3 = str2.substring(0, j + 1);
/* 1574 */           if ((n >= -4) && (n < this.precision)) {
/* 1575 */             str4 = str3;
/*      */           } else {
/* 1577 */             str4 = str3 + str1.substring(m);
/*      */           }
/*      */         }
/* 1580 */         else if ((n >= -4) && (n < this.precision)) {
/* 1581 */           str4 = fFormatString(paramDouble).trim();
/*      */         } else {
/* 1583 */           str4 = str1;
/*      */         }
/*      */         
/*      */ 
/* 1587 */         if ((this.leadingSpace) && (paramDouble >= 0.0D)) str4 = " " + str4;
/* 1588 */         arrayOfChar1 = str4.toCharArray();
/*      */       }
/*      */       
/* 1591 */       char[] arrayOfChar2 = applyFloatPadding(arrayOfChar1, false);
/* 1592 */       this.precision = i;
/* 1593 */       return new String(arrayOfChar2);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private String printDFormat(short paramShort)
/*      */     {
/* 1622 */       return printDFormat(Short.toString(paramShort));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private String printDFormat(long paramLong)
/*      */     {
/* 1651 */       return printDFormat(Long.toString(paramLong));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private String printDFormat(int paramInt)
/*      */     {
/* 1680 */       return printDFormat(Integer.toString(paramInt));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private String printDFormat(String paramString)
/*      */     {
/* 1691 */       int i = 0;
/* 1692 */       int j = 0;int k = 0;
/* 1693 */       int m = 0;int n = 0;
/* 1694 */       int i1 = paramString.charAt(0) == '-' ? 1 : 0;
/* 1695 */       if ((paramString.equals("0")) && (this.precisionSet) && (this.precision == 0))
/* 1696 */         paramString = "";
/* 1697 */       if (i1 == 0) {
/* 1698 */         if ((this.precisionSet) && (paramString.length() < this.precision)) {
/* 1699 */           i = this.precision - paramString.length();
/*      */         }
/*      */       }
/* 1702 */       else if ((this.precisionSet) && (paramString.length() - 1 < this.precision)) {
/* 1703 */         i = this.precision - paramString.length() + 1;
/*      */       }
/* 1705 */       if (i < 0) i = 0;
/* 1706 */       if (this.fieldWidthSet) {
/* 1707 */         j = this.fieldWidth - i - paramString.length();
/* 1708 */         if ((i1 == 0) && ((this.leadingSign) || (this.leadingSpace)))
/* 1709 */           j--;
/*      */       }
/* 1711 */       if (j < 0) j = 0;
/* 1712 */       if (this.leadingSign) { k++;
/* 1713 */       } else if (this.leadingSpace) k++;
/* 1714 */       k += j;
/* 1715 */       k += i;
/* 1716 */       k += paramString.length();
/* 1717 */       char[] arrayOfChar1 = new char[k];
/* 1718 */       int i4; if (this.leftJustify) {
/* 1719 */         if (i1 != 0) { arrayOfChar1[(m++)] = '-';
/* 1720 */         } else if (this.leadingSign) { arrayOfChar1[(m++)] = '+';
/* 1721 */         } else if (this.leadingSpace) arrayOfChar1[(m++)] = ' ';
/* 1722 */         char[] arrayOfChar2 = paramString.toCharArray();
/* 1723 */         n = i1 != 0 ? 1 : 0;
/* 1724 */         for (int i3 = 0; i3 < i; i3++) {
/* 1725 */           arrayOfChar1[m] = '0';m++; }
/* 1726 */         for (i4 = n; i4 < arrayOfChar2.length; m++) {
/* 1727 */           arrayOfChar1[m] = arrayOfChar2[i4];i4++; }
/* 1728 */         for (int i5 = 0; i5 < j; i5++) {
/* 1729 */           arrayOfChar1[m] = ' ';m++;
/*      */         }
/*      */       } else {
/* 1732 */         if (!this.leadingZeros) {
/* 1733 */           for (m = 0; m < j; m++)
/* 1734 */             arrayOfChar1[m] = ' ';
/* 1735 */           if (i1 != 0) { arrayOfChar1[(m++)] = '-';
/* 1736 */           } else if (this.leadingSign) { arrayOfChar1[(m++)] = '+';
/* 1737 */           } else if (this.leadingSpace) arrayOfChar1[(m++)] = ' ';
/*      */         }
/*      */         else {
/* 1740 */           if (i1 != 0) { arrayOfChar1[(m++)] = '-';
/* 1741 */           } else if (this.leadingSign) { arrayOfChar1[(m++)] = '+';
/* 1742 */           } else if (this.leadingSpace) arrayOfChar1[(m++)] = ' ';
/* 1743 */           for (i2 = 0; i2 < j; m++) {
/* 1744 */             arrayOfChar1[m] = '0';i2++;
/*      */           } }
/* 1746 */         for (int i2 = 0; i2 < i; m++) {
/* 1747 */           arrayOfChar1[m] = '0';i2++; }
/* 1748 */         char[] arrayOfChar3 = paramString.toCharArray();
/* 1749 */         n = i1 != 0 ? 1 : 0;
/* 1750 */         for (i4 = n; i4 < arrayOfChar3.length; m++) {
/* 1751 */           arrayOfChar1[m] = arrayOfChar3[i4];i4++;
/*      */         } }
/* 1753 */       return new String(arrayOfChar1);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private String printXFormat(short paramShort)
/*      */     {
/* 1776 */       String str1 = null;
/* 1777 */       if (paramShort == Short.MIN_VALUE) {
/* 1778 */         str1 = "8000";
/* 1779 */       } else if (paramShort < 0) {
/*      */         String str2;
/* 1781 */         if (paramShort == Short.MIN_VALUE) {
/* 1782 */           str2 = "0";
/*      */         } else {
/* 1784 */           str2 = Integer.toString(-paramShort - 1 ^ 0xFFFFFFFF ^ 0x8000, 16);
/*      */           
/* 1786 */           if ((str2.charAt(0) == 'F') || (str2.charAt(0) == 'f'))
/* 1787 */             str2 = str2.substring(16, 32);
/*      */         }
/* 1789 */         switch (str2.length()) {
/*      */         case 1: 
/* 1791 */           str1 = "800" + str2;
/* 1792 */           break;
/*      */         case 2: 
/* 1794 */           str1 = "80" + str2;
/* 1795 */           break;
/*      */         case 3: 
/* 1797 */           str1 = "8" + str2;
/* 1798 */           break;
/*      */         case 4: 
/* 1800 */           switch (str2.charAt(0)) {
/*      */           case '1': 
/* 1802 */             str1 = "9" + str2.substring(1, 4);
/* 1803 */             break;
/*      */           case '2': 
/* 1805 */             str1 = "a" + str2.substring(1, 4);
/* 1806 */             break;
/*      */           case '3': 
/* 1808 */             str1 = "b" + str2.substring(1, 4);
/* 1809 */             break;
/*      */           case '4': 
/* 1811 */             str1 = "c" + str2.substring(1, 4);
/* 1812 */             break;
/*      */           case '5': 
/* 1814 */             str1 = "d" + str2.substring(1, 4);
/* 1815 */             break;
/*      */           case '6': 
/* 1817 */             str1 = "e" + str2.substring(1, 4);
/* 1818 */             break;
/*      */           case '7': 
/* 1820 */             str1 = "f" + str2.substring(1, 4);
/*      */           }
/*      */           break;
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1827 */         str1 = Integer.toString(paramShort, 16); }
/* 1828 */       return printXFormat(str1);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private String printXFormat(long paramLong)
/*      */     {
/* 1851 */       String str1 = null;
/* 1852 */       if (paramLong == Long.MIN_VALUE) {
/* 1853 */         str1 = "8000000000000000";
/* 1854 */       } else if (paramLong < 0L) {
/* 1855 */         String str2 = Long.toString(-paramLong - 1L ^ 0xFFFFFFFFFFFFFFFF ^ 0x8000000000000000, 16);
/*      */         
/* 1857 */         switch (str2.length()) {
/*      */         case 1: 
/* 1859 */           str1 = "800000000000000" + str2;
/* 1860 */           break;
/*      */         case 2: 
/* 1862 */           str1 = "80000000000000" + str2;
/* 1863 */           break;
/*      */         case 3: 
/* 1865 */           str1 = "8000000000000" + str2;
/* 1866 */           break;
/*      */         case 4: 
/* 1868 */           str1 = "800000000000" + str2;
/* 1869 */           break;
/*      */         case 5: 
/* 1871 */           str1 = "80000000000" + str2;
/* 1872 */           break;
/*      */         case 6: 
/* 1874 */           str1 = "8000000000" + str2;
/* 1875 */           break;
/*      */         case 7: 
/* 1877 */           str1 = "800000000" + str2;
/* 1878 */           break;
/*      */         case 8: 
/* 1880 */           str1 = "80000000" + str2;
/* 1881 */           break;
/*      */         case 9: 
/* 1883 */           str1 = "8000000" + str2;
/* 1884 */           break;
/*      */         case 10: 
/* 1886 */           str1 = "800000" + str2;
/* 1887 */           break;
/*      */         case 11: 
/* 1889 */           str1 = "80000" + str2;
/* 1890 */           break;
/*      */         case 12: 
/* 1892 */           str1 = "8000" + str2;
/* 1893 */           break;
/*      */         case 13: 
/* 1895 */           str1 = "800" + str2;
/* 1896 */           break;
/*      */         case 14: 
/* 1898 */           str1 = "80" + str2;
/* 1899 */           break;
/*      */         case 15: 
/* 1901 */           str1 = "8" + str2;
/* 1902 */           break;
/*      */         case 16: 
/* 1904 */           switch (str2.charAt(0)) {
/*      */           case '1': 
/* 1906 */             str1 = "9" + str2.substring(1, 16);
/* 1907 */             break;
/*      */           case '2': 
/* 1909 */             str1 = "a" + str2.substring(1, 16);
/* 1910 */             break;
/*      */           case '3': 
/* 1912 */             str1 = "b" + str2.substring(1, 16);
/* 1913 */             break;
/*      */           case '4': 
/* 1915 */             str1 = "c" + str2.substring(1, 16);
/* 1916 */             break;
/*      */           case '5': 
/* 1918 */             str1 = "d" + str2.substring(1, 16);
/* 1919 */             break;
/*      */           case '6': 
/* 1921 */             str1 = "e" + str2.substring(1, 16);
/* 1922 */             break;
/*      */           case '7': 
/* 1924 */             str1 = "f" + str2.substring(1, 16);
/*      */           }
/*      */           break;
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1931 */         str1 = Long.toString(paramLong, 16); }
/* 1932 */       return printXFormat(str1);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private String printXFormat(int paramInt)
/*      */     {
/* 1955 */       String str1 = null;
/* 1956 */       if (paramInt == Integer.MIN_VALUE) {
/* 1957 */         str1 = "80000000";
/* 1958 */       } else if (paramInt < 0) {
/* 1959 */         String str2 = Integer.toString(-paramInt - 1 ^ 0xFFFFFFFF ^ 0x80000000, 16);
/*      */         
/* 1961 */         switch (str2.length()) {
/*      */         case 1: 
/* 1963 */           str1 = "8000000" + str2;
/* 1964 */           break;
/*      */         case 2: 
/* 1966 */           str1 = "800000" + str2;
/* 1967 */           break;
/*      */         case 3: 
/* 1969 */           str1 = "80000" + str2;
/* 1970 */           break;
/*      */         case 4: 
/* 1972 */           str1 = "8000" + str2;
/* 1973 */           break;
/*      */         case 5: 
/* 1975 */           str1 = "800" + str2;
/* 1976 */           break;
/*      */         case 6: 
/* 1978 */           str1 = "80" + str2;
/* 1979 */           break;
/*      */         case 7: 
/* 1981 */           str1 = "8" + str2;
/* 1982 */           break;
/*      */         case 8: 
/* 1984 */           switch (str2.charAt(0)) {
/*      */           case '1': 
/* 1986 */             str1 = "9" + str2.substring(1, 8);
/* 1987 */             break;
/*      */           case '2': 
/* 1989 */             str1 = "a" + str2.substring(1, 8);
/* 1990 */             break;
/*      */           case '3': 
/* 1992 */             str1 = "b" + str2.substring(1, 8);
/* 1993 */             break;
/*      */           case '4': 
/* 1995 */             str1 = "c" + str2.substring(1, 8);
/* 1996 */             break;
/*      */           case '5': 
/* 1998 */             str1 = "d" + str2.substring(1, 8);
/* 1999 */             break;
/*      */           case '6': 
/* 2001 */             str1 = "e" + str2.substring(1, 8);
/* 2002 */             break;
/*      */           case '7': 
/* 2004 */             str1 = "f" + str2.substring(1, 8);
/*      */           }
/*      */           break;
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 2011 */         str1 = Integer.toString(paramInt, 16); }
/* 2012 */       return printXFormat(str1);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private String printXFormat(String paramString)
/*      */     {
/* 2023 */       int i = 0;
/* 2024 */       int j = 0;
/* 2025 */       if ((paramString.equals("0")) && (this.precisionSet) && (this.precision == 0))
/* 2026 */         paramString = "";
/* 2027 */       if (this.precisionSet)
/* 2028 */         i = this.precision - paramString.length();
/* 2029 */       if (i < 0) i = 0;
/* 2030 */       if (this.fieldWidthSet) {
/* 2031 */         j = this.fieldWidth - i - paramString.length();
/* 2032 */         if (this.alternateForm) j -= 2;
/*      */       }
/* 2034 */       if (j < 0) j = 0;
/* 2035 */       int k = 0;
/* 2036 */       if (this.alternateForm) k += 2;
/* 2037 */       k += i;
/* 2038 */       k += paramString.length();
/* 2039 */       k += j;
/* 2040 */       char[] arrayOfChar1 = new char[k];
/* 2041 */       int m = 0;
/* 2042 */       int n; int i2; if (this.leftJustify) {
/* 2043 */         if (this.alternateForm) {
/* 2044 */           arrayOfChar1[(m++)] = '0';arrayOfChar1[(m++)] = 'x';
/*      */         }
/* 2046 */         for (n = 0; n < i; m++) {
/* 2047 */           arrayOfChar1[m] = '0';n++; }
/* 2048 */         char[] arrayOfChar2 = paramString.toCharArray();
/* 2049 */         for (i2 = 0; i2 < arrayOfChar2.length; m++) {
/* 2050 */           arrayOfChar1[m] = arrayOfChar2[i2];i2++; }
/* 2051 */         for (int i3 = 0; i3 < j; m++) {
/* 2052 */           arrayOfChar1[m] = ' ';i3++;
/*      */         }
/*      */       } else {
/* 2055 */         if (!this.leadingZeros)
/* 2056 */           for (n = 0; n < j; m++) {
/* 2057 */             arrayOfChar1[m] = ' ';n++; }
/* 2058 */         if (this.alternateForm) {
/* 2059 */           arrayOfChar1[(m++)] = '0';arrayOfChar1[(m++)] = 'x';
/*      */         }
/* 2061 */         if (this.leadingZeros)
/* 2062 */           for (int i1 = 0; i1 < j; m++) {
/* 2063 */             arrayOfChar1[m] = '0';i1++; }
/* 2064 */         for (i2 = 0; i2 < i; m++) {
/* 2065 */           arrayOfChar1[m] = '0';i2++; }
/* 2066 */         char[] arrayOfChar3 = paramString.toCharArray();
/* 2067 */         for (int i4 = 0; i4 < arrayOfChar3.length; m++) {
/* 2068 */           arrayOfChar1[m] = arrayOfChar3[i4];i4++;
/*      */         } }
/* 2070 */       String str = new String(arrayOfChar1);
/* 2071 */       if (this.conversionCharacter == 'X')
/* 2072 */         str = str.toUpperCase();
/* 2073 */       return str;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private String printOFormat(short paramShort)
/*      */     {
/* 2097 */       String str1 = null;
/* 2098 */       if (paramShort == Short.MIN_VALUE) {
/* 2099 */         str1 = "100000";
/* 2100 */       } else if (paramShort < 0) {
/* 2101 */         String str2 = Integer.toString(-paramShort - 1 ^ 0xFFFFFFFF ^ 0x8000, 8);
/*      */         
/* 2103 */         switch (str2.length()) {
/*      */         case 1: 
/* 2105 */           str1 = "10000" + str2;
/* 2106 */           break;
/*      */         case 2: 
/* 2108 */           str1 = "1000" + str2;
/* 2109 */           break;
/*      */         case 3: 
/* 2111 */           str1 = "100" + str2;
/* 2112 */           break;
/*      */         case 4: 
/* 2114 */           str1 = "10" + str2;
/* 2115 */           break;
/*      */         case 5: 
/* 2117 */           str1 = "1" + str2;
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 2122 */         str1 = Integer.toString(paramShort, 8); }
/* 2123 */       return printOFormat(str1);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private String printOFormat(long paramLong)
/*      */     {
/* 2147 */       String str1 = null;
/* 2148 */       if (paramLong == Long.MIN_VALUE) {
/* 2149 */         str1 = "1000000000000000000000";
/* 2150 */       } else if (paramLong < 0L) {
/* 2151 */         String str2 = Long.toString(-paramLong - 1L ^ 0xFFFFFFFFFFFFFFFF ^ 0x8000000000000000, 8);
/*      */         
/* 2153 */         switch (str2.length()) {
/*      */         case 1: 
/* 2155 */           str1 = "100000000000000000000" + str2;
/* 2156 */           break;
/*      */         case 2: 
/* 2158 */           str1 = "10000000000000000000" + str2;
/* 2159 */           break;
/*      */         case 3: 
/* 2161 */           str1 = "1000000000000000000" + str2;
/* 2162 */           break;
/*      */         case 4: 
/* 2164 */           str1 = "100000000000000000" + str2;
/* 2165 */           break;
/*      */         case 5: 
/* 2167 */           str1 = "10000000000000000" + str2;
/* 2168 */           break;
/*      */         case 6: 
/* 2170 */           str1 = "1000000000000000" + str2;
/* 2171 */           break;
/*      */         case 7: 
/* 2173 */           str1 = "100000000000000" + str2;
/* 2174 */           break;
/*      */         case 8: 
/* 2176 */           str1 = "10000000000000" + str2;
/* 2177 */           break;
/*      */         case 9: 
/* 2179 */           str1 = "1000000000000" + str2;
/* 2180 */           break;
/*      */         case 10: 
/* 2182 */           str1 = "100000000000" + str2;
/* 2183 */           break;
/*      */         case 11: 
/* 2185 */           str1 = "10000000000" + str2;
/* 2186 */           break;
/*      */         case 12: 
/* 2188 */           str1 = "1000000000" + str2;
/* 2189 */           break;
/*      */         case 13: 
/* 2191 */           str1 = "100000000" + str2;
/* 2192 */           break;
/*      */         case 14: 
/* 2194 */           str1 = "10000000" + str2;
/* 2195 */           break;
/*      */         case 15: 
/* 2197 */           str1 = "1000000" + str2;
/* 2198 */           break;
/*      */         case 16: 
/* 2200 */           str1 = "100000" + str2;
/* 2201 */           break;
/*      */         case 17: 
/* 2203 */           str1 = "10000" + str2;
/* 2204 */           break;
/*      */         case 18: 
/* 2206 */           str1 = "1000" + str2;
/* 2207 */           break;
/*      */         case 19: 
/* 2209 */           str1 = "100" + str2;
/* 2210 */           break;
/*      */         case 20: 
/* 2212 */           str1 = "10" + str2;
/* 2213 */           break;
/*      */         case 21: 
/* 2215 */           str1 = "1" + str2;
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 2220 */         str1 = Long.toString(paramLong, 8); }
/* 2221 */       return printOFormat(str1);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private String printOFormat(int paramInt)
/*      */     {
/* 2245 */       String str1 = null;
/* 2246 */       if (paramInt == Integer.MIN_VALUE) {
/* 2247 */         str1 = "20000000000";
/* 2248 */       } else if (paramInt < 0) {
/* 2249 */         String str2 = Integer.toString(-paramInt - 1 ^ 0xFFFFFFFF ^ 0x80000000, 8);
/*      */         
/* 2251 */         switch (str2.length()) {
/*      */         case 1: 
/* 2253 */           str1 = "2000000000" + str2;
/* 2254 */           break;
/*      */         case 2: 
/* 2256 */           str1 = "200000000" + str2;
/* 2257 */           break;
/*      */         case 3: 
/* 2259 */           str1 = "20000000" + str2;
/* 2260 */           break;
/*      */         case 4: 
/* 2262 */           str1 = "2000000" + str2;
/* 2263 */           break;
/*      */         case 5: 
/* 2265 */           str1 = "200000" + str2;
/* 2266 */           break;
/*      */         case 6: 
/* 2268 */           str1 = "20000" + str2;
/* 2269 */           break;
/*      */         case 7: 
/* 2271 */           str1 = "2000" + str2;
/* 2272 */           break;
/*      */         case 8: 
/* 2274 */           str1 = "200" + str2;
/* 2275 */           break;
/*      */         case 9: 
/* 2277 */           str1 = "20" + str2;
/* 2278 */           break;
/*      */         case 10: 
/* 2280 */           str1 = "2" + str2;
/* 2281 */           break;
/*      */         case 11: 
/* 2283 */           str1 = "3" + str2.substring(1);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 2288 */         str1 = Integer.toString(paramInt, 8); }
/* 2289 */       return printOFormat(str1);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private String printOFormat(String paramString)
/*      */     {
/* 2300 */       int i = 0;
/* 2301 */       int j = 0;
/* 2302 */       if ((paramString.equals("0")) && (this.precisionSet) && (this.precision == 0))
/* 2303 */         paramString = "";
/* 2304 */       if (this.precisionSet)
/* 2305 */         i = this.precision - paramString.length();
/* 2306 */       if (this.alternateForm) i++;
/* 2307 */       if (i < 0) i = 0;
/* 2308 */       if (this.fieldWidthSet)
/* 2309 */         j = this.fieldWidth - i - paramString.length();
/* 2310 */       if (j < 0) j = 0;
/* 2311 */       int k = i + paramString.length() + j;
/* 2312 */       char[] arrayOfChar1 = new char[k];
/*      */       int m;
/* 2314 */       int i2; if (this.leftJustify) {
/* 2315 */         for (m = 0; m < i; m++) arrayOfChar1[m] = '0';
/* 2316 */         char[] arrayOfChar2 = paramString.toCharArray();
/* 2317 */         for (int i1 = 0; i1 < arrayOfChar2.length; m++) {
/* 2318 */           arrayOfChar1[m] = arrayOfChar2[i1];i1++; }
/* 2319 */         for (i2 = 0; i2 < j; m++) { arrayOfChar1[m] = ' ';i2++;
/*      */         }
/*      */       } else {
/* 2322 */         if (this.leadingZeros) {
/* 2323 */           for (m = 0; m < j; m++) arrayOfChar1[m] = '0';
/*      */         } else
/* 2325 */           for (m = 0; m < j; m++) arrayOfChar1[m] = ' ';
/* 2326 */         for (int n = 0; n < i; m++) {
/* 2327 */           arrayOfChar1[m] = '0';n++; }
/* 2328 */         char[] arrayOfChar3 = paramString.toCharArray();
/* 2329 */         for (i2 = 0; i2 < arrayOfChar3.length; m++) {
/* 2330 */           arrayOfChar1[m] = arrayOfChar3[i2];i2++;
/*      */         } }
/* 2332 */       return new String(arrayOfChar1);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private String printCFormat(char paramChar)
/*      */     {
/* 2352 */       int i = 1;
/* 2353 */       int j = this.fieldWidth;
/* 2354 */       if (!this.fieldWidthSet) j = i;
/* 2355 */       char[] arrayOfChar = new char[j];
/* 2356 */       int k = 0;
/* 2357 */       if (this.leftJustify) {
/* 2358 */         arrayOfChar[0] = paramChar;
/* 2359 */         for (k = 1; k <= j - i; k++) arrayOfChar[k] = ' ';
/*      */       }
/*      */       else {
/* 2362 */         for (k = 0; k < j - i; k++) arrayOfChar[k] = ' ';
/* 2363 */         arrayOfChar[k] = paramChar;
/*      */       }
/* 2365 */       return new String(arrayOfChar);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private String printSFormat(String paramString)
/*      */     {
/* 2391 */       int i = paramString.length();
/* 2392 */       int j = this.fieldWidth;
/* 2393 */       if ((this.precisionSet) && (i > this.precision))
/* 2394 */         i = this.precision;
/* 2395 */       if (!this.fieldWidthSet) j = i;
/* 2396 */       int k = 0;
/* 2397 */       if (j > i) k += j - i;
/* 2398 */       if (i >= paramString.length()) k += paramString.length(); else
/* 2399 */         k += i;
/* 2400 */       char[] arrayOfChar1 = new char[k];
/* 2401 */       int m = 0;
/* 2402 */       if (this.leftJustify) { char[] arrayOfChar2;
/* 2403 */         if (i >= paramString.length()) {
/* 2404 */           arrayOfChar2 = paramString.toCharArray();
/* 2405 */           for (m = 0; m < paramString.length(); m++) arrayOfChar1[m] = arrayOfChar2[m];
/*      */         }
/*      */         else {
/* 2408 */           arrayOfChar2 = paramString.substring(0, i).toCharArray();
/*      */           
/* 2410 */           for (m = 0; m < i; m++) arrayOfChar1[m] = arrayOfChar2[m];
/*      */         }
/* 2412 */         for (int n = 0; n < j - i; m++) {
/* 2413 */           arrayOfChar1[m] = ' ';n++;
/*      */         }
/*      */       } else {
/* 2416 */         for (m = 0; m < j - i; m++) arrayOfChar1[m] = ' ';
/* 2417 */         char[] arrayOfChar3; int i1; if (i >= paramString.length()) {
/* 2418 */           arrayOfChar3 = paramString.toCharArray();
/* 2419 */           for (i1 = 0; i1 < paramString.length(); i1++) {
/* 2420 */             arrayOfChar1[m] = arrayOfChar3[i1];m++;
/*      */           }
/*      */         } else {
/* 2423 */           arrayOfChar3 = paramString.substring(0, i).toCharArray();
/*      */           
/* 2425 */           for (i1 = 0; i1 < i; i1++) {
/* 2426 */             arrayOfChar1[m] = arrayOfChar3[i1];m++;
/*      */           }
/*      */         } }
/* 2429 */       return new String(arrayOfChar1);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private boolean setConversionCharacter()
/*      */     {
/* 2441 */       boolean bool = false;
/* 2442 */       this.conversionCharacter = '\000';
/* 2443 */       if (this.pos < this.fmt.length()) {
/* 2444 */         char c = this.fmt.charAt(this.pos);
/* 2445 */         if ((c == 'i') || (c == 'd') || (c == 'f') || (c == 'g') || (c == 'G') || (c == 'o') || (c == 'x') || (c == 'X') || (c == 'e') || (c == 'E') || (c == 'c') || (c == 's') || (c == '%'))
/*      */         {
/*      */ 
/* 2448 */           this.conversionCharacter = c;
/* 2449 */           this.pos += 1;
/* 2450 */           bool = true;
/*      */         }
/*      */       }
/* 2453 */       return bool;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private void setOptionalHL()
/*      */     {
/* 2465 */       this.optionalh = false;
/* 2466 */       this.optionall = false;
/* 2467 */       this.optionalL = false;
/* 2468 */       if (this.pos < this.fmt.length()) {
/* 2469 */         int i = this.fmt.charAt(this.pos);
/* 2470 */         if (i == 104) { this.optionalh = true;this.pos += 1;
/* 2471 */         } else if (i == 108) { this.optionall = true;this.pos += 1;
/* 2472 */         } else if (i == 76) { this.optionalL = true;this.pos += 1;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     private void setPrecision()
/*      */     {
/* 2479 */       int i = this.pos;
/* 2480 */       this.precisionSet = false;
/* 2481 */       if ((this.pos < this.fmt.length()) && (this.fmt.charAt(this.pos) == '.')) {
/* 2482 */         this.pos += 1;
/* 2483 */         if ((this.pos < this.fmt.length()) && (this.fmt.charAt(this.pos) == '*'))
/*      */         {
/* 2485 */           this.pos += 1;
/* 2486 */           if (!setPrecisionArgPosition()) {
/* 2487 */             this.variablePrecision = true;
/* 2488 */             this.precisionSet = true;
/*      */           }
/* 2490 */           return;
/*      */         }
/* 2493 */         for (; 
/* 2493 */             this.pos < this.fmt.length(); 
/*      */             
/* 2495 */             this.pos += 1)
/*      */         {
/* 2494 */           char c = this.fmt.charAt(this.pos);
/* 2495 */           if (!Character.isDigit(c))
/*      */             break;
/*      */         }
/* 2498 */         if (this.pos > i + 1) {
/* 2499 */           String str = this.fmt.substring(i + 1, this.pos);
/* 2500 */           this.precision = Integer.parseInt(str);
/* 2501 */           this.precisionSet = true;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     private void setFieldWidth()
/*      */     {
/* 2510 */       int i = this.pos;
/* 2511 */       this.fieldWidth = 0;
/* 2512 */       this.fieldWidthSet = false;
/* 2513 */       if ((this.pos < this.fmt.length()) && (this.fmt.charAt(this.pos) == '*'))
/*      */       {
/* 2515 */         this.pos += 1;
/* 2516 */         if (!setFieldWidthArgPosition()) {
/* 2517 */           this.variableFieldWidth = true;
/* 2518 */           this.fieldWidthSet = true;
/*      */         }
/*      */       }
/*      */       else {
/* 2522 */         for (; this.pos < this.fmt.length(); 
/*      */             
/* 2524 */             this.pos += 1)
/*      */         {
/* 2523 */           char c = this.fmt.charAt(this.pos);
/* 2524 */           if (!Character.isDigit(c))
/*      */             break;
/*      */         }
/* 2527 */         if ((i < this.pos) && (i < this.fmt.length())) {
/* 2528 */           String str = this.fmt.substring(i, this.pos);
/* 2529 */           this.fieldWidth = Integer.parseInt(str);
/* 2530 */           this.fieldWidthSet = true;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     private void setArgPosition()
/*      */     {
/* 2539 */       for (int i = this.pos; i < this.fmt.length(); i++) {
/* 2540 */         if (!Character.isDigit(this.fmt.charAt(i)))
/*      */           break;
/*      */       }
/* 2543 */       if ((i > this.pos) && (i < this.fmt.length()) && 
/* 2544 */         (this.fmt.charAt(i) == '$')) {
/* 2545 */         this.positionalSpecification = true;
/* 2546 */         this.argumentPosition = Integer.parseInt(this.fmt.substring(this.pos, i));
/*      */         
/* 2548 */         this.pos = (i + 1);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     private boolean setFieldWidthArgPosition()
/*      */     {
/* 2556 */       boolean bool = false;
/*      */       
/* 2558 */       for (int i = this.pos; i < this.fmt.length(); i++) {
/* 2559 */         if (!Character.isDigit(this.fmt.charAt(i)))
/*      */           break;
/*      */       }
/* 2562 */       if ((i > this.pos) && (i < this.fmt.length()) && 
/* 2563 */         (this.fmt.charAt(i) == '$')) {
/* 2564 */         this.positionalFieldWidth = true;
/* 2565 */         this.argumentPositionForFieldWidth = Integer.parseInt(this.fmt.substring(this.pos, i));
/*      */         
/* 2567 */         this.pos = (i + 1);
/* 2568 */         bool = true;
/*      */       }
/*      */       
/* 2571 */       return bool;
/*      */     }
/*      */     
/*      */ 
/*      */     private boolean setPrecisionArgPosition()
/*      */     {
/* 2577 */       boolean bool = false;
/*      */       
/* 2579 */       for (int i = this.pos; i < this.fmt.length(); i++) {
/* 2580 */         if (!Character.isDigit(this.fmt.charAt(i)))
/*      */           break;
/*      */       }
/* 2583 */       if ((i > this.pos) && (i < this.fmt.length()) && 
/* 2584 */         (this.fmt.charAt(i) == '$')) {
/* 2585 */         this.positionalPrecision = true;
/* 2586 */         this.argumentPositionForPrecision = Integer.parseInt(this.fmt.substring(this.pos, i));
/*      */         
/* 2588 */         this.pos = (i + 1);
/* 2589 */         bool = true;
/*      */       }
/*      */       
/* 2592 */       return bool;
/*      */     }
/*      */     
/* 2595 */     boolean isPositionalSpecification() { return this.positionalSpecification; }
/*      */     
/* 2597 */     int getArgumentPosition() { return this.argumentPosition; }
/*      */     
/* 2599 */     boolean isPositionalFieldWidth() { return this.positionalFieldWidth; }
/*      */     
/*      */     int getArgumentPositionForFieldWidth() {
/* 2602 */       return this.argumentPositionForFieldWidth;
/*      */     }
/*      */     
/* 2605 */     boolean isPositionalPrecision() { return this.positionalPrecision; }
/*      */     
/*      */     int getArgumentPositionForPrecision() {
/* 2608 */       return this.argumentPositionForPrecision;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     private void setFlagCharacters()
/*      */     {
/* 2615 */       this.thousands = false;
/* 2616 */       this.leftJustify = false;
/* 2617 */       this.leadingSign = false;
/* 2618 */       this.leadingSpace = false;
/* 2619 */       this.alternateForm = false;
/* 2620 */       this.leadingZeros = false;
/* 2621 */       for (; this.pos < this.fmt.length(); this.pos += 1) {
/* 2622 */         int i = this.fmt.charAt(this.pos);
/* 2623 */         if (i == 39) { this.thousands = true;
/* 2624 */         } else if (i == 45) {
/* 2625 */           this.leftJustify = true;
/* 2626 */           this.leadingZeros = false;
/*      */         }
/* 2628 */         else if (i == 43) {
/* 2629 */           this.leadingSign = true;
/* 2630 */           this.leadingSpace = false;
/*      */         }
/* 2632 */         else if (i == 32) {
/* 2633 */           if (!this.leadingSign) this.leadingSpace = true;
/*      */         }
/* 2635 */         else if (i == 35) { this.alternateForm = true;
/* 2636 */         } else { if (i != 48) break;
/* 2637 */           if (!this.leftJustify) { this.leadingZeros = true;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2648 */     private boolean thousands = false;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2653 */     private boolean leftJustify = false;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2658 */     private boolean leadingSign = false;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2663 */     private boolean leadingSpace = false;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2675 */     private boolean alternateForm = false;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2680 */     private boolean leadingZeros = false;
/*      */     
/*      */ 
/*      */ 
/* 2684 */     private boolean variableFieldWidth = false;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2690 */     private int fieldWidth = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2695 */     private boolean fieldWidthSet = false;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2705 */     private int precision = 0;
/*      */     
/*      */ 
/*      */     private static final int defaultDigits = 6;
/*      */     
/*      */ 
/* 2711 */     private boolean variablePrecision = false;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2716 */     private boolean precisionSet = false;
/*      */     
/*      */ 
/* 2719 */     private boolean positionalSpecification = false;
/* 2720 */     private int argumentPosition = 0;
/* 2721 */     private boolean positionalFieldWidth = false;
/* 2722 */     private int argumentPositionForFieldWidth = 0;
/* 2723 */     private boolean positionalPrecision = false;
/* 2724 */     private int argumentPositionForPrecision = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2730 */     private boolean optionalh = false;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2736 */     private boolean optionall = false;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2742 */     private boolean optionalL = false;
/*      */     
/* 2744 */     private char conversionCharacter = '\000';
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2749 */     private int pos = 0;
/*      */     
/*      */     private String fmt;
/*      */   }
/*      */   
/* 2754 */   private Vector vFmt = new Vector();
/*      */   
/* 2756 */   private int cPos = 0;
/*      */   
/* 2758 */   private DecimalFormatSymbols dfs = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PrintfFormat(String paramString)
/*      */     throws IllegalArgumentException
/*      */   {
/* 2773 */     this(Locale.getDefault(), paramString);
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
/*      */   public PrintfFormat(Locale paramLocale, String paramString)
/*      */     throws IllegalArgumentException
/*      */   {
/* 2789 */     this.dfs = new DecimalFormatSymbols(paramLocale);
/* 2790 */     int i = 0;
/* 2791 */     ConversionSpecification localConversionSpecification = null;
/* 2792 */     String str = nonControl(paramString, 0);
/* 2793 */     if (str != null) {
/* 2794 */       localConversionSpecification = new ConversionSpecification();
/* 2795 */       localConversionSpecification.setLiteral(str);
/* 2796 */       this.vFmt.addElement(localConversionSpecification);
/*      */     }
/* 2798 */     while ((this.cPos != -1) && (this.cPos < paramString.length())) {
/* 2799 */       for (i = this.cPos + 1; i < paramString.length(); 
/* 2800 */           i++) {
/* 2801 */         int j = 0;
/* 2802 */         j = paramString.charAt(i);
/* 2803 */         if ((j == 105) || 
/* 2804 */           (j == 100) || 
/* 2805 */           (j == 102) || 
/* 2806 */           (j == 103) || 
/* 2807 */           (j == 71) || 
/* 2808 */           (j == 111) || 
/* 2809 */           (j == 120) || 
/* 2810 */           (j == 88) || 
/* 2811 */           (j == 101) || 
/* 2812 */           (j == 69) || 
/* 2813 */           (j == 99) || 
/* 2814 */           (j == 115) || 
/* 2815 */           (j == 37)) break;
/*      */       }
/* 2817 */       i = Math.min(i + 1, paramString.length());
/* 2818 */       localConversionSpecification = new ConversionSpecification(paramString.substring(this.cPos, i));
/*      */       
/* 2820 */       this.vFmt.addElement(localConversionSpecification);
/* 2821 */       str = nonControl(paramString, i);
/* 2822 */       if (str != null) {
/* 2823 */         localConversionSpecification = new ConversionSpecification();
/* 2824 */         localConversionSpecification.setLiteral(str);
/* 2825 */         this.vFmt.addElement(localConversionSpecification);
/*      */       }
/*      */     }
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
/*      */   private String nonControl(String paramString, int paramInt)
/*      */   {
/* 2843 */     String str = "";
/* 2844 */     this.cPos = paramString.indexOf("%", paramInt);
/* 2845 */     if (this.cPos == -1) this.cPos = paramString.length();
/* 2846 */     return paramString.substring(paramInt, this.cPos);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String sprintf()
/*      */   {
/* 2853 */     Enumeration localEnumeration = this.vFmt.elements();
/* 2854 */     ConversionSpecification localConversionSpecification = null;
/* 2855 */     int i = 0;
/* 2856 */     StringBuffer localStringBuffer = new StringBuffer();
/* 2857 */     while (localEnumeration.hasMoreElements()) {
/* 2858 */       localConversionSpecification = (ConversionSpecification)localEnumeration.nextElement();
/*      */       
/* 2860 */       i = localConversionSpecification.getConversionCharacter();
/* 2861 */       if (i == 0) { localStringBuffer.append(localConversionSpecification.getLiteral());
/* 2862 */       } else if (i == 37) localStringBuffer.append("%");
/*      */     }
/* 2864 */     return localStringBuffer.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String sprintf(Object[] paramArrayOfObject)
/*      */   {
/* 2875 */     Enumeration localEnumeration = this.vFmt.elements();
/* 2876 */     ConversionSpecification localConversionSpecification = null;
/* 2877 */     int i = 0;
/* 2878 */     int j = 0;
/* 2879 */     StringBuffer localStringBuffer = new StringBuffer();
/* 2880 */     while (localEnumeration.hasMoreElements()) {
/* 2881 */       localConversionSpecification = (ConversionSpecification)localEnumeration.nextElement();
/*      */       
/* 2883 */       i = localConversionSpecification.getConversionCharacter();
/* 2884 */       if (i == 0) { localStringBuffer.append(localConversionSpecification.getLiteral());
/* 2885 */       } else if (i == 37) { localStringBuffer.append("%");
/*      */       } else {
/* 2887 */         if (localConversionSpecification.isPositionalSpecification()) {
/* 2888 */           j = localConversionSpecification.getArgumentPosition() - 1;
/* 2889 */           int k; if (localConversionSpecification.isPositionalFieldWidth()) {
/* 2890 */             k = localConversionSpecification.getArgumentPositionForFieldWidth() - 1;
/* 2891 */             localConversionSpecification.setFieldWidthWithArg(((Integer)paramArrayOfObject[k]).intValue());
/*      */           }
/* 2893 */           if (localConversionSpecification.isPositionalPrecision()) {
/* 2894 */             k = localConversionSpecification.getArgumentPositionForPrecision() - 1;
/* 2895 */             localConversionSpecification.setPrecisionWithArg(((Integer)paramArrayOfObject[k]).intValue());
/*      */           }
/*      */         }
/*      */         else {
/* 2899 */           if (localConversionSpecification.isVariableFieldWidth()) {
/* 2900 */             localConversionSpecification.setFieldWidthWithArg(((Integer)paramArrayOfObject[j]).intValue());
/* 2901 */             j++;
/*      */           }
/* 2903 */           if (localConversionSpecification.isVariablePrecision()) {
/* 2904 */             localConversionSpecification.setPrecisionWithArg(((Integer)paramArrayOfObject[j]).intValue());
/* 2905 */             j++;
/*      */           }
/*      */         }
/* 2908 */         if ((paramArrayOfObject[j] instanceof Byte)) {
/* 2909 */           localStringBuffer.append(localConversionSpecification.internalsprintf(((Byte)paramArrayOfObject[j]).byteValue()));
/*      */         }
/* 2911 */         else if ((paramArrayOfObject[j] instanceof Short)) {
/* 2912 */           localStringBuffer.append(localConversionSpecification.internalsprintf(((Short)paramArrayOfObject[j]).shortValue()));
/*      */         }
/* 2914 */         else if ((paramArrayOfObject[j] instanceof Integer)) {
/* 2915 */           localStringBuffer.append(localConversionSpecification.internalsprintf(((Integer)paramArrayOfObject[j]).intValue()));
/*      */         }
/* 2917 */         else if ((paramArrayOfObject[j] instanceof Long)) {
/* 2918 */           localStringBuffer.append(localConversionSpecification.internalsprintf(((Long)paramArrayOfObject[j]).longValue()));
/*      */         }
/* 2920 */         else if ((paramArrayOfObject[j] instanceof Float)) {
/* 2921 */           localStringBuffer.append(localConversionSpecification.internalsprintf(((Float)paramArrayOfObject[j]).floatValue()));
/*      */         }
/* 2923 */         else if ((paramArrayOfObject[j] instanceof Double)) {
/* 2924 */           localStringBuffer.append(localConversionSpecification.internalsprintf(((Double)paramArrayOfObject[j]).doubleValue()));
/*      */         }
/* 2926 */         else if ((paramArrayOfObject[j] instanceof Character)) {
/* 2927 */           localStringBuffer.append(localConversionSpecification.internalsprintf(((Character)paramArrayOfObject[j]).charValue()));
/*      */         }
/* 2929 */         else if ((paramArrayOfObject[j] instanceof String)) {
/* 2930 */           localStringBuffer.append(localConversionSpecification.internalsprintf((String)paramArrayOfObject[j]));
/*      */         }
/*      */         else {
/* 2933 */           localStringBuffer.append(localConversionSpecification.internalsprintf(paramArrayOfObject[j]));
/*      */         }
/* 2935 */         if (!localConversionSpecification.isPositionalSpecification())
/* 2936 */           j++;
/*      */       }
/*      */     }
/* 2939 */     return localStringBuffer.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String sprintf(double paramDouble)
/*      */     throws IllegalArgumentException
/*      */   {
/* 2951 */     Enumeration localEnumeration = this.vFmt.elements();
/* 2952 */     ConversionSpecification localConversionSpecification = null;
/* 2953 */     int i = 0;
/* 2954 */     StringBuffer localStringBuffer = new StringBuffer();
/* 2955 */     while (localEnumeration.hasMoreElements()) {
/* 2956 */       localConversionSpecification = (ConversionSpecification)localEnumeration.nextElement();
/*      */       
/* 2958 */       i = localConversionSpecification.getConversionCharacter();
/* 2959 */       if (i == 0) { localStringBuffer.append(localConversionSpecification.getLiteral());
/* 2960 */       } else if (i == 37) localStringBuffer.append("%"); else
/* 2961 */         localStringBuffer.append(localConversionSpecification.internalsprintf(paramDouble));
/*      */     }
/* 2963 */     return localStringBuffer.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String sprintf(int paramInt)
/*      */     throws IllegalArgumentException
/*      */   {
/* 2975 */     Enumeration localEnumeration = this.vFmt.elements();
/* 2976 */     ConversionSpecification localConversionSpecification = null;
/* 2977 */     int i = 0;
/* 2978 */     StringBuffer localStringBuffer = new StringBuffer();
/* 2979 */     while (localEnumeration.hasMoreElements()) {
/* 2980 */       localConversionSpecification = (ConversionSpecification)localEnumeration.nextElement();
/*      */       
/* 2982 */       i = localConversionSpecification.getConversionCharacter();
/* 2983 */       if (i == 0) { localStringBuffer.append(localConversionSpecification.getLiteral());
/* 2984 */       } else if (i == 37) localStringBuffer.append("%"); else
/* 2985 */         localStringBuffer.append(localConversionSpecification.internalsprintf(paramInt));
/*      */     }
/* 2987 */     return localStringBuffer.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String sprintf(long paramLong)
/*      */     throws IllegalArgumentException
/*      */   {
/* 2999 */     Enumeration localEnumeration = this.vFmt.elements();
/* 3000 */     ConversionSpecification localConversionSpecification = null;
/* 3001 */     int i = 0;
/* 3002 */     StringBuffer localStringBuffer = new StringBuffer();
/* 3003 */     while (localEnumeration.hasMoreElements()) {
/* 3004 */       localConversionSpecification = (ConversionSpecification)localEnumeration.nextElement();
/*      */       
/* 3006 */       i = localConversionSpecification.getConversionCharacter();
/* 3007 */       if (i == 0) { localStringBuffer.append(localConversionSpecification.getLiteral());
/* 3008 */       } else if (i == 37) localStringBuffer.append("%"); else
/* 3009 */         localStringBuffer.append(localConversionSpecification.internalsprintf(paramLong));
/*      */     }
/* 3011 */     return localStringBuffer.toString();
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
/*      */   public String sprintf(Object paramObject)
/*      */     throws IllegalArgumentException
/*      */   {
/* 3028 */     Enumeration localEnumeration = this.vFmt.elements();
/* 3029 */     ConversionSpecification localConversionSpecification = null;
/* 3030 */     int i = 0;
/* 3031 */     StringBuffer localStringBuffer = new StringBuffer();
/* 3032 */     while (localEnumeration.hasMoreElements()) {
/* 3033 */       localConversionSpecification = (ConversionSpecification)localEnumeration.nextElement();
/*      */       
/* 3035 */       i = localConversionSpecification.getConversionCharacter();
/* 3036 */       if (i == 0) { localStringBuffer.append(localConversionSpecification.getLiteral());
/* 3037 */       } else if (i == 37) { localStringBuffer.append("%");
/*      */       }
/* 3039 */       else if ((paramObject instanceof Byte)) {
/* 3040 */         localStringBuffer.append(localConversionSpecification.internalsprintf(((Byte)paramObject).byteValue()));
/*      */       }
/* 3042 */       else if ((paramObject instanceof Short)) {
/* 3043 */         localStringBuffer.append(localConversionSpecification.internalsprintf(((Short)paramObject).shortValue()));
/*      */       }
/* 3045 */       else if ((paramObject instanceof Integer)) {
/* 3046 */         localStringBuffer.append(localConversionSpecification.internalsprintf(((Integer)paramObject).intValue()));
/*      */       }
/* 3048 */       else if ((paramObject instanceof Long)) {
/* 3049 */         localStringBuffer.append(localConversionSpecification.internalsprintf(((Long)paramObject).longValue()));
/*      */       }
/* 3051 */       else if ((paramObject instanceof Float)) {
/* 3052 */         localStringBuffer.append(localConversionSpecification.internalsprintf(((Float)paramObject).floatValue()));
/*      */       }
/* 3054 */       else if ((paramObject instanceof Double)) {
/* 3055 */         localStringBuffer.append(localConversionSpecification.internalsprintf(((Double)paramObject).doubleValue()));
/*      */       }
/* 3057 */       else if ((paramObject instanceof Character)) {
/* 3058 */         localStringBuffer.append(localConversionSpecification.internalsprintf(((Character)paramObject).charValue()));
/*      */       }
/* 3060 */       else if ((paramObject instanceof String)) {
/* 3061 */         localStringBuffer.append(localConversionSpecification.internalsprintf((String)paramObject));
/*      */       }
/*      */       else {
/* 3064 */         localStringBuffer.append(localConversionSpecification.internalsprintf(paramObject));
/*      */       }
/*      */     }
/* 3067 */     return localStringBuffer.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String sprintf(String paramString)
/*      */     throws IllegalArgumentException
/*      */   {
/* 3078 */     Enumeration localEnumeration = this.vFmt.elements();
/* 3079 */     ConversionSpecification localConversionSpecification = null;
/* 3080 */     int i = 0;
/* 3081 */     StringBuffer localStringBuffer = new StringBuffer();
/* 3082 */     while (localEnumeration.hasMoreElements()) {
/* 3083 */       localConversionSpecification = (ConversionSpecification)localEnumeration.nextElement();
/*      */       
/* 3085 */       i = localConversionSpecification.getConversionCharacter();
/* 3086 */       if (i == 0) { localStringBuffer.append(localConversionSpecification.getLiteral());
/* 3087 */       } else if (i == 37) localStringBuffer.append("%"); else
/* 3088 */         localStringBuffer.append(localConversionSpecification.internalsprintf(paramString));
/*      */     }
/* 3090 */     return localStringBuffer.toString();
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/corejava/PrintfFormat.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */