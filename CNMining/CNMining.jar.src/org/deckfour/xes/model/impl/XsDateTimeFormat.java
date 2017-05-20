/*     */ package org.deckfour.xes.model.impl;
/*     */ 
/*     */ import java.text.FieldPosition;
/*     */ import java.text.Format;
/*     */ import java.text.ParseException;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
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
/*     */ public class XsDateTimeFormat
/*     */   extends Format
/*     */ {
/*     */   private static final long serialVersionUID = 3258131340871479609L;
/*     */   
/*     */   private int parseInt(String pString, int pOffset, StringBuffer pDigits)
/*     */   {
/*  59 */     int length = pString.length();
/*  60 */     pDigits.setLength(0);
/*  61 */     while (pOffset < length) {
/*  62 */       char c = pString.charAt(pOffset);
/*  63 */       if (!Character.isDigit(c)) break;
/*  64 */       pDigits.append(c);
/*  65 */       pOffset++;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  70 */     return pOffset;
/*     */   }
/*     */   
/*     */   public Date parseObject(String pString) throws ParseException {
/*  74 */     return (Date)super.parseObject(pString);
/*     */   }
/*     */   
/*     */   public Date parseObject(String pString, ParsePosition pParsePosition) {
/*  78 */     if (pString == null) {
/*  79 */       throw new NullPointerException("The String argument must not be null.");
/*     */     }
/*     */     
/*  82 */     if (pParsePosition == null) {
/*  83 */       throw new NullPointerException("The ParsePosition argument must not be null.");
/*     */     }
/*     */     
/*  86 */     int offset = pParsePosition.getIndex();
/*  87 */     int length = pString.length();
/*     */     
/*  89 */     boolean isMinus = false;
/*  90 */     StringBuffer digits = new StringBuffer();
/*     */     
/*     */ 
/*  93 */     if (offset < length) {
/*  94 */       char c = pString.charAt(offset);
/*  95 */       if (c == '+') {
/*  96 */         offset++;
/*  97 */       } else if (c == '-') {
/*  98 */         offset++;
/*  99 */         isMinus = true;
/*     */       }
/*     */     }
/*     */     
/* 103 */     offset = parseInt(pString, offset, digits);
/* 104 */     if (digits.length() < 4) {
/* 105 */       pParsePosition.setErrorIndex(offset);
/* 106 */       return null;
/*     */     }
/* 108 */     int year = Integer.parseInt(digits.toString());
/*     */     
/* 110 */     if ((offset < length) && (pString.charAt(offset) == '-')) {
/* 111 */       offset++;
/*     */     } else {
/* 113 */       pParsePosition.setErrorIndex(offset);
/* 114 */       return null;
/*     */     }
/*     */     
/* 117 */     offset = parseInt(pString, offset, digits);
/* 118 */     if (digits.length() != 2) {
/* 119 */       pParsePosition.setErrorIndex(offset);
/* 120 */       return null;
/*     */     }
/* 122 */     int month = Integer.parseInt(digits.toString());
/*     */     
/* 124 */     if ((offset < length) && (pString.charAt(offset) == '-')) {
/* 125 */       offset++;
/*     */     } else {
/* 127 */       pParsePosition.setErrorIndex(offset);
/* 128 */       return null;
/*     */     }
/*     */     
/* 131 */     offset = parseInt(pString, offset, digits);
/* 132 */     if (digits.length() != 2) {
/* 133 */       pParsePosition.setErrorIndex(offset);
/* 134 */       return null;
/*     */     }
/* 136 */     int mday = Integer.parseInt(digits.toString());
/*     */     
/* 138 */     if ((offset < length) && (pString.charAt(offset) == 'T')) {
/* 139 */       offset++;
/*     */     } else {
/* 141 */       pParsePosition.setErrorIndex(offset);
/* 142 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 146 */     offset = parseInt(pString, offset, digits);
/* 147 */     if (digits.length() != 2) {
/* 148 */       pParsePosition.setErrorIndex(offset);
/* 149 */       return null;
/*     */     }
/* 151 */     int hour = Integer.parseInt(digits.toString());
/*     */     
/* 153 */     if ((offset < length) && (pString.charAt(offset) == ':')) {
/* 154 */       offset++;
/*     */     } else {
/* 156 */       pParsePosition.setErrorIndex(offset);
/* 157 */       return null;
/*     */     }
/*     */     
/* 160 */     offset = parseInt(pString, offset, digits);
/* 161 */     if (digits.length() != 2) {
/* 162 */       pParsePosition.setErrorIndex(offset);
/* 163 */       return null;
/*     */     }
/* 165 */     int minute = Integer.parseInt(digits.toString());
/*     */     
/* 167 */     if ((offset < length) && (pString.charAt(offset) == ':')) {
/* 168 */       offset++;
/*     */     } else {
/* 170 */       pParsePosition.setErrorIndex(offset);
/* 171 */       return null;
/*     */     }
/*     */     
/* 174 */     offset = parseInt(pString, offset, digits);
/* 175 */     if (digits.length() != 2) {
/* 176 */       pParsePosition.setErrorIndex(offset);
/* 177 */       return null;
/*     */     }
/* 179 */     int second = Integer.parseInt(digits.toString());
/*     */     int millis;
/* 181 */     int millis; if ((offset < length) && (pString.charAt(offset) == '.')) {
/* 182 */       offset++;
/* 183 */       offset = parseInt(pString, offset, digits);
/* 184 */       if (digits.length() > 0) {
/* 185 */         int millis = Integer.parseInt(digits.toString());
/* 186 */         if (millis > 999) {
/* 187 */           pParsePosition.setErrorIndex(offset);
/* 188 */           return null;
/*     */         }
/* 190 */         for (int i = digits.length(); i < 3; i++) {
/* 191 */           millis *= 10;
/*     */         }
/*     */       } else {
/* 194 */         millis = 0;
/*     */       }
/*     */     } else {
/* 197 */       millis = 0;
/*     */     }
/*     */     
/* 200 */     digits.setLength(0);
/* 201 */     digits.append("GMT");
/* 202 */     if (offset < length) {
/* 203 */       char c = pString.charAt(offset);
/* 204 */       if (c == 'Z')
/*     */       {
/* 206 */         offset++;
/* 207 */       } else if ((c == '+') || (c == '-')) {
/* 208 */         digits.append(c);
/* 209 */         offset++;
/* 210 */         for (int i = 0; i < 5; i++) {
/* 211 */           if (offset >= length) {
/* 212 */             pParsePosition.setErrorIndex(offset);
/* 213 */             return null;
/*     */           }
/* 215 */           c = pString.charAt(offset);
/* 216 */           if (((i != 2) && (Character.isDigit(c))) || ((i == 2) && (c == ':')))
/*     */           {
/* 218 */             digits.append(c);
/*     */           } else {
/* 220 */             pParsePosition.setErrorIndex(offset);
/* 221 */             return null;
/*     */           }
/* 223 */           offset++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 228 */     Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(digits.toString()));
/*     */     
/* 230 */     cal.set(isMinus ? -year : year, month - 1, mday, hour, minute, second);
/* 231 */     cal.set(14, millis);
/* 232 */     pParsePosition.setIndex(offset);
/* 233 */     return cal.getTime();
/*     */   }
/*     */   
/*     */   private void append(StringBuffer pBuffer, int pNum, int pMinLen) {
/* 237 */     String s = Integer.toString(pNum);
/* 238 */     for (int i = s.length(); i < pMinLen; i++) {
/* 239 */       pBuffer.append('0');
/*     */     }
/* 241 */     pBuffer.append(s);
/*     */   }
/*     */   
/*     */   public StringBuffer format(Object pCalendar, StringBuffer pBuffer, FieldPosition pPos)
/*     */   {
/* 246 */     if (pCalendar == null) {
/* 247 */       throw new NullPointerException("The Calendar argument must not be null.");
/*     */     }
/*     */     
/* 250 */     if (pBuffer == null) {
/* 251 */       throw new NullPointerException("The StringBuffer argument must not be null.");
/*     */     }
/*     */     
/* 254 */     if (pPos == null) {
/* 255 */       throw new NullPointerException("The FieldPosition argument must not be null.");
/*     */     }
/*     */     
/*     */ 
/* 259 */     Calendar cal = Calendar.getInstance();
/* 260 */     cal.setTime((Date)pCalendar);
/* 261 */     int year = cal.get(1);
/* 262 */     if (year < 0) {
/* 263 */       pBuffer.append('-');
/* 264 */       year = -year;
/*     */     }
/* 266 */     append(pBuffer, year, 4);
/* 267 */     pBuffer.append('-');
/* 268 */     append(pBuffer, cal.get(2) + 1, 2);
/* 269 */     pBuffer.append('-');
/* 270 */     append(pBuffer, cal.get(5), 2);
/* 271 */     pBuffer.append('T');
/*     */     
/* 273 */     append(pBuffer, cal.get(11), 2);
/* 274 */     pBuffer.append(':');
/* 275 */     append(pBuffer, cal.get(12), 2);
/* 276 */     pBuffer.append(':');
/* 277 */     append(pBuffer, cal.get(13), 2);
/* 278 */     int millis = cal.get(14);
/* 279 */     if (millis > 0) {
/* 280 */       pBuffer.append('.');
/* 281 */       append(pBuffer, millis, 3);
/*     */     }
/* 283 */     TimeZone tz = cal.getTimeZone();
/*     */     
/* 285 */     int offset = cal.get(15);
/* 286 */     if (tz.inDaylightTime(cal.getTime())) {
/* 287 */       offset += cal.get(16);
/*     */     }
/* 289 */     if (offset == 0) {
/* 290 */       pBuffer.append('Z');
/*     */     } else {
/* 292 */       if (offset < 0) {
/* 293 */         pBuffer.append('-');
/* 294 */         offset = -offset;
/*     */       } else {
/* 296 */         pBuffer.append('+');
/*     */       }
/* 298 */       int minutes = offset / 60000;
/* 299 */       int hours = minutes / 60;
/* 300 */       minutes -= hours * 60;
/* 301 */       append(pBuffer, hours, 2);
/* 302 */       pBuffer.append(':');
/* 303 */       append(pBuffer, minutes, 2);
/*     */     }
/* 305 */     return pBuffer;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/impl/XsDateTimeFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */