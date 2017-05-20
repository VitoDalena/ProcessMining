/*     */ package cern.colt.matrix.impl;
/*     */ 
/*     */ import cern.colt.PersistentObject;
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
/*     */ public abstract class AbstractFormatter
/*     */   extends PersistentObject
/*     */ {
/*     */   public static final String LEFT = "left";
/*     */   public static final String CENTER = "center";
/*     */   public static final String RIGHT = "right";
/*     */   public static final String DECIMAL = "decimal";
/*     */   public static final int DEFAULT_MIN_COLUMN_WIDTH = 1;
/*     */   public static final String DEFAULT_COLUMN_SEPARATOR = " ";
/*     */   public static final String DEFAULT_ROW_SEPARATOR = "\n";
/*     */   public static final String DEFAULT_SLICE_SEPARATOR = "\n\n";
/*  73 */   protected String alignment = "left";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  78 */   protected String format = "%G";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  83 */   protected int minColumnWidth = 1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  88 */   protected String columnSeparator = " ";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  93 */   protected String rowSeparator = "\n";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  98 */   protected String sliceSeparator = "\n\n";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 103 */   protected boolean printShape = true;
/*     */   
/*     */ 
/*     */   private static String[] blanksCache;
/*     */   
/* 108 */   protected static final FormerFactory factory = new FormerFactory();
/*     */   
/*     */   static {
/* 111 */     setupBlanksCache();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void align(String[][] paramArrayOfString)
/*     */   {
/* 121 */     int i = paramArrayOfString.length;
/* 122 */     int j = 0;
/* 123 */     if (i > 0) { j = paramArrayOfString[0].length;
/*     */     }
/* 125 */     int[] arrayOfInt1 = new int[j];
/* 126 */     int[] arrayOfInt2 = null;
/* 127 */     boolean bool = this.alignment.equals("decimal");
/* 128 */     if (bool) { arrayOfInt2 = new int[j];
/*     */     }
/*     */     
/*     */ 
/* 132 */     for (int k = 0; k < j; k++) {
/* 133 */       m = this.minColumnWidth;
/* 134 */       int n = Integer.MIN_VALUE;
/*     */       
/* 136 */       for (int i1 = 0; i1 < i; i1++) {
/* 137 */         String str = paramArrayOfString[i1][k];
/* 138 */         m = Math.max(m, str.length());
/* 139 */         if (bool) { n = Math.max(n, lead(str));
/*     */         }
/*     */       }
/* 142 */       arrayOfInt1[k] = m;
/* 143 */       if (bool) { arrayOfInt2[k] = n;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 149 */     for (int m = 0; m < i; m++) {
/* 150 */       alignRow(paramArrayOfString[m], arrayOfInt1, arrayOfInt2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int alignmentCode(String paramString)
/*     */   {
/* 159 */     if (paramString.equals("left")) return -1;
/* 160 */     if (paramString.equals("center")) return 0;
/* 161 */     if (paramString.equals("right")) return 1;
/* 162 */     if (paramString.equals("decimal")) return 2;
/* 163 */     throw new IllegalArgumentException("unknown alignment: " + paramString);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void alignRow(String[] paramArrayOfString, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*     */   {
/* 169 */     int i = alignmentCode(this.alignment);
/* 170 */     StringBuffer localStringBuffer = new StringBuffer();
/*     */     
/* 172 */     int j = paramArrayOfString.length;
/* 173 */     for (int k = 0; k < j; k++) {
/* 174 */       localStringBuffer.setLength(0);
/* 175 */       String str = paramArrayOfString[k];
/*     */       
/* 177 */       if (this.alignment.equals("right")) {
/* 178 */         localStringBuffer.append(blanks(paramArrayOfInt1[k] - localStringBuffer.length()));
/* 179 */         localStringBuffer.append(str);
/*     */ 
/*     */       }
/* 182 */       else if (this.alignment.equals("decimal")) {
/* 183 */         localStringBuffer.append(blanks(paramArrayOfInt2[k] - lead(str)));
/* 184 */         localStringBuffer.append(str);
/* 185 */         localStringBuffer.append(blanks(paramArrayOfInt1[k] - localStringBuffer.length()));
/*     */ 
/*     */       }
/* 188 */       else if (this.alignment.equals("center")) {
/* 189 */         localStringBuffer.append(blanks((paramArrayOfInt1[k] - str.length()) / 2));
/* 190 */         localStringBuffer.append(str);
/* 191 */         localStringBuffer.append(blanks(paramArrayOfInt1[k] - localStringBuffer.length()));
/*     */ 
/*     */ 
/*     */       }
/* 195 */       else if (this.alignment.equals("left")) {
/* 196 */         localStringBuffer.append(str);
/* 197 */         localStringBuffer.append(blanks(paramArrayOfInt1[k] - localStringBuffer.length()));
/*     */       } else {
/* 199 */         throw new InternalError();
/*     */       }
/* 201 */       paramArrayOfString[k] = localStringBuffer.toString();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected String blanks(int paramInt)
/*     */   {
/* 208 */     if (paramInt < 0) paramInt = 0;
/* 209 */     if (paramInt < blanksCache.length) { return blanksCache[paramInt];
/*     */     }
/* 211 */     StringBuffer localStringBuffer = new StringBuffer(paramInt);
/* 212 */     for (int i = 0; i < paramInt; i++) {
/* 213 */       localStringBuffer.append(' ');
/*     */     }
/* 215 */     return localStringBuffer.toString();
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
/*     */   protected String[] formatRow(AbstractMatrix1D paramAbstractMatrix1D)
/*     */   {
/* 358 */     Former localFormer = null;
/* 359 */     localFormer = factory.create(this.format);
/* 360 */     int i = paramAbstractMatrix1D.size();
/* 361 */     String[] arrayOfString = new String[i];
/* 362 */     for (int j = 0; j < i; j++) {
/* 363 */       arrayOfString[j] = form(paramAbstractMatrix1D, j, localFormer);
/*     */     }
/* 365 */     return arrayOfString;
/*     */   }
/*     */   
/*     */ 
/*     */   protected int lead(String paramString)
/*     */   {
/* 371 */     return paramString.length();
/*     */   }
/*     */   
/*     */ 
/*     */   protected String repeat(char paramChar, int paramInt)
/*     */   {
/* 377 */     if (paramChar == ' ') return blanks(paramInt);
/* 378 */     if (paramInt < 0) paramInt = 0;
/* 379 */     StringBuffer localStringBuffer = new StringBuffer(paramInt);
/* 380 */     for (int i = 0; i < paramInt; i++) {
/* 381 */       localStringBuffer.append(paramChar);
/*     */     }
/* 383 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setAlignment(String paramString)
/*     */   {
/* 390 */     this.alignment = paramString;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setColumnSeparator(String paramString)
/*     */   {
/* 397 */     this.columnSeparator = paramString;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setFormat(String paramString)
/*     */   {
/* 404 */     this.format = paramString;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setMinColumnWidth(int paramInt)
/*     */   {
/* 411 */     if (paramInt < 0) throw new IllegalArgumentException();
/* 412 */     this.minColumnWidth = paramInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setPrintShape(boolean paramBoolean)
/*     */   {
/* 419 */     this.printShape = paramBoolean;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setRowSeparator(String paramString)
/*     */   {
/* 426 */     this.rowSeparator = paramString;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setSliceSeparator(String paramString)
/*     */   {
/* 433 */     this.sliceSeparator = paramString;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static void setupBlanksCache()
/*     */   {
/* 442 */     int i = 40;
/* 443 */     blanksCache = new String[i];
/* 444 */     StringBuffer localStringBuffer = new StringBuffer(i);
/* 445 */     int j = i; do { localStringBuffer.append(' ');j--; } while (j >= 0);
/* 446 */     String str = localStringBuffer.toString();
/* 447 */     int k = i;
/* 448 */     do { blanksCache[k] = str.substring(0, k);k--;
/* 447 */     } while (k >= 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String shape(AbstractMatrix1D paramAbstractMatrix1D)
/*     */   {
/* 459 */     return paramAbstractMatrix1D.size() + " matrix";
/*     */   }
/*     */   
/*     */ 
/*     */   public static String shape(AbstractMatrix2D paramAbstractMatrix2D)
/*     */   {
/* 465 */     return paramAbstractMatrix2D.rows() + " x " + paramAbstractMatrix2D.columns() + " matrix";
/*     */   }
/*     */   
/*     */ 
/*     */   public static String shape(AbstractMatrix3D paramAbstractMatrix3D)
/*     */   {
/* 471 */     return paramAbstractMatrix3D.slices() + " x " + paramAbstractMatrix3D.rows() + " x " + paramAbstractMatrix3D.columns() + " matrix";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected String toString(String[][] paramArrayOfString)
/*     */   {
/* 478 */     int i = paramArrayOfString.length;
/* 479 */     int j = paramArrayOfString.length <= 0 ? 0 : paramArrayOfString[0].length;
/*     */     
/* 481 */     StringBuffer localStringBuffer1 = new StringBuffer();
/* 482 */     StringBuffer localStringBuffer2 = new StringBuffer();
/* 483 */     for (int k = 0; k < i; k++) {
/* 484 */       localStringBuffer2.setLength(0);
/* 485 */       for (int m = 0; m < j; m++) {
/* 486 */         localStringBuffer2.append(paramArrayOfString[k][m]);
/* 487 */         if (m < j - 1) localStringBuffer2.append(this.columnSeparator);
/*     */       }
/* 489 */       localStringBuffer1.append(localStringBuffer2);
/* 490 */       if (k < i - 1) { localStringBuffer1.append(this.rowSeparator);
/*     */       }
/*     */     }
/* 493 */     return localStringBuffer1.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected String toString(AbstractMatrix2D paramAbstractMatrix2D)
/*     */   {
/* 500 */     String[][] arrayOfString = format(paramAbstractMatrix2D);
/* 501 */     align(arrayOfString);
/* 502 */     StringBuffer localStringBuffer = new StringBuffer(toString(arrayOfString));
/* 503 */     if (this.printShape) localStringBuffer.insert(0, shape(paramAbstractMatrix2D) + "\n");
/* 504 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */   public static void demo1() {}
/*     */   
/*     */   public static void demo2() {}
/*     */   
/*     */   public static void demo3(int paramInt, Object paramObject) {}
/*     */   
/*     */   protected abstract String form(AbstractMatrix1D paramAbstractMatrix1D, int paramInt, Former paramFormer);
/*     */   
/*     */   protected abstract String[][] format(AbstractMatrix2D paramAbstractMatrix2D);
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/AbstractFormatter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */