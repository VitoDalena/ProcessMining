/*     */ package cern.colt.matrix.objectalgo;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.matrix.ObjectMatrix1D;
/*     */ import cern.colt.matrix.ObjectMatrix2D;
/*     */ import cern.colt.matrix.ObjectMatrix3D;
/*     */ import cern.colt.matrix.impl.AbstractFormatter;
/*     */ import cern.colt.matrix.impl.AbstractMatrix1D;
/*     */ import cern.colt.matrix.impl.AbstractMatrix2D;
/*     */ import cern.colt.matrix.impl.AbstractMatrix3D;
/*     */ import cern.colt.matrix.impl.Former;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Formatter
/*     */   extends AbstractFormatter
/*     */ {
/*     */   public Formatter()
/*     */   {
/*  27 */     this("left");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Formatter(String paramString)
/*     */   {
/*  34 */     setAlignment(paramString);
/*     */   }
/*     */   
/*     */ 
/*     */   protected String form(AbstractMatrix1D paramAbstractMatrix1D, int paramInt, Former paramFormer)
/*     */   {
/*  40 */     return form((ObjectMatrix1D)paramAbstractMatrix1D, paramInt, paramFormer);
/*     */   }
/*     */   
/*     */ 
/*     */   protected String form(ObjectMatrix1D paramObjectMatrix1D, int paramInt, Former paramFormer)
/*     */   {
/*  46 */     Object localObject = paramObjectMatrix1D.get(paramInt);
/*  47 */     if (localObject == null) return "";
/*  48 */     return String.valueOf(localObject);
/*     */   }
/*     */   
/*     */ 
/*     */   protected String[][] format(AbstractMatrix2D paramAbstractMatrix2D)
/*     */   {
/*  54 */     return format((ObjectMatrix2D)paramAbstractMatrix2D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected String[][] format(ObjectMatrix2D paramObjectMatrix2D)
/*     */   {
/*  60 */     String[][] arrayOfString = new String[paramObjectMatrix2D.rows()][paramObjectMatrix2D.columns()];
/*  61 */     int i = paramObjectMatrix2D.rows(); do { arrayOfString[i] = formatRow(paramObjectMatrix2D.viewRow(i));i--; } while (i >= 0);
/*  62 */     return arrayOfString;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toSourceCode(ObjectMatrix1D paramObjectMatrix1D)
/*     */   {
/*  69 */     Formatter localFormatter = (Formatter)clone();
/*  70 */     localFormatter.setPrintShape(false);
/*  71 */     localFormatter.setColumnSeparator(", ");
/*  72 */     String str1 = "{";
/*  73 */     String str2 = "};";
/*  74 */     return str1 + localFormatter.toString(paramObjectMatrix1D) + str2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toSourceCode(ObjectMatrix2D paramObjectMatrix2D)
/*     */   {
/*  81 */     Formatter localFormatter = (Formatter)clone();
/*  82 */     String str1 = blanks(3);
/*  83 */     localFormatter.setPrintShape(false);
/*  84 */     localFormatter.setColumnSeparator(", ");
/*  85 */     localFormatter.setRowSeparator("},\n" + str1 + "{");
/*  86 */     String str2 = "{\n" + str1 + "{";
/*  87 */     String str3 = "}\n};";
/*  88 */     return str2 + localFormatter.toString(paramObjectMatrix2D) + str3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toSourceCode(ObjectMatrix3D paramObjectMatrix3D)
/*     */   {
/*  95 */     Formatter localFormatter = (Formatter)clone();
/*  96 */     String str1 = blanks(3);
/*  97 */     String str2 = blanks(6);
/*  98 */     localFormatter.setPrintShape(false);
/*  99 */     localFormatter.setColumnSeparator(", ");
/* 100 */     localFormatter.setRowSeparator("},\n" + str2 + "{");
/* 101 */     localFormatter.setSliceSeparator("}\n" + str1 + "},\n" + str1 + "{\n" + str2 + "{");
/* 102 */     String str3 = "{\n" + str1 + "{\n" + str2 + "{";
/* 103 */     String str4 = "}\n" + str1 + "}\n}";
/* 104 */     return str3 + localFormatter.toString(paramObjectMatrix3D) + str4;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected String toString(AbstractMatrix2D paramAbstractMatrix2D)
/*     */   {
/* 111 */     return toString((ObjectMatrix2D)paramAbstractMatrix2D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString(ObjectMatrix1D paramObjectMatrix1D)
/*     */   {
/* 118 */     ObjectMatrix2D localObjectMatrix2D = paramObjectMatrix1D.like2D(1, paramObjectMatrix1D.size());
/* 119 */     localObjectMatrix2D.viewRow(0).assign(paramObjectMatrix1D);
/* 120 */     return toString(localObjectMatrix2D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString(ObjectMatrix2D paramObjectMatrix2D)
/*     */   {
/* 127 */     return super.toString(paramObjectMatrix2D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString(ObjectMatrix3D paramObjectMatrix3D)
/*     */   {
/* 134 */     StringBuffer localStringBuffer = new StringBuffer();
/* 135 */     boolean bool = this.printShape;
/* 136 */     this.printShape = false;
/* 137 */     for (int i = 0; i < paramObjectMatrix3D.slices(); i++) {
/* 138 */       if (i != 0) localStringBuffer.append(this.sliceSeparator);
/* 139 */       localStringBuffer.append(toString(paramObjectMatrix3D.viewSlice(i)));
/*     */     }
/* 141 */     this.printShape = bool;
/* 142 */     if (this.printShape) localStringBuffer.insert(0, AbstractFormatter.shape(paramObjectMatrix3D) + "\n");
/* 143 */     return localStringBuffer.toString();
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
/*     */   public String toTitleString(ObjectMatrix2D paramObjectMatrix2D, String[] paramArrayOfString1, String[] paramArrayOfString2, String paramString1, String paramString2, String paramString3)
/*     */   {
/* 158 */     if (paramObjectMatrix2D.size() == 0) return "Empty matrix";
/* 159 */     String str1 = this.format;
/* 160 */     this.format = "left";
/*     */     
/* 162 */     int i = paramObjectMatrix2D.rows();
/* 163 */     int j = paramObjectMatrix2D.columns();
/*     */     
/*     */ 
/* 166 */     int k = 0;
/* 167 */     int m = 0;
/* 168 */     k += (paramArrayOfString2 == null ? 0 : 1);
/* 169 */     m += (paramArrayOfString1 == null ? 0 : 1);
/* 170 */     m += (paramString1 == null ? 0 : 1);
/* 171 */     m += ((paramArrayOfString1 != null) || (paramString1 != null) ? 1 : 0);
/*     */     
/* 173 */     int n = k + Math.max(i, paramString1 == null ? 0 : paramString1.length());
/* 174 */     int i1 = m + j;
/*     */     
/*     */ 
/* 177 */     ObjectMatrix2D localObjectMatrix2D = paramObjectMatrix2D.like(n, i1);
/*     */     
/*     */ 
/* 180 */     localObjectMatrix2D.viewPart(k, m, i, j).assign(paramObjectMatrix2D);
/*     */     
/*     */ 
/* 183 */     if (k > 0) { localObjectMatrix2D.viewRow(0).viewPart(m, j).assign(paramArrayOfString2);
/*     */     }
/*     */     
/* 186 */     if (paramString1 != null) {
/* 187 */       String[] arrayOfString = new String[paramString1.length()];
/* 188 */       int i2 = paramString1.length(); do { arrayOfString[i2] = paramString1.substring(i2, i2 + 1);i2--; } while (i2 >= 0);
/* 189 */       localObjectMatrix2D.viewColumn(0).viewPart(k, paramString1.length()).assign(arrayOfString);
/*     */     }
/*     */     
/* 192 */     if (paramArrayOfString1 != null) { localObjectMatrix2D.viewColumn(m - 2).viewPart(k, i).assign(paramArrayOfString1);
/*     */     }
/*     */     
/* 195 */     if (m > 0) { localObjectMatrix2D.viewColumn(m - 2 + 1).viewPart(0, i + k).assign("|");
/*     */     }
/*     */     
/* 198 */     boolean bool = this.printShape;
/* 199 */     this.printShape = false;
/* 200 */     String str2 = toString(localObjectMatrix2D);
/* 201 */     this.printShape = bool;
/*     */     
/*     */ 
/* 204 */     StringBuffer localStringBuffer = new StringBuffer(str2);
/* 205 */     int i3; if (paramArrayOfString2 != null) {
/* 206 */       i3 = str2.indexOf(this.rowSeparator);
/* 207 */       localStringBuffer.insert(i3 + 1, repeat('-', i3) + this.rowSeparator);
/*     */     }
/* 209 */     else if (paramString2 != null) {
/* 210 */       i3 = str2.indexOf(this.rowSeparator);
/* 211 */       localStringBuffer.insert(0, repeat('-', i3) + this.rowSeparator);
/*     */     }
/*     */     
/*     */ 
/* 215 */     if (paramString2 != null) {
/* 216 */       i3 = 0;
/* 217 */       if (m > 0) i3 = str2.indexOf('|');
/* 218 */       String str3 = blanks(i3);
/* 219 */       if (m > 0) str3 = str3 + "| ";
/* 220 */       str3 = str3 + paramString2 + "\n";
/* 221 */       localStringBuffer.insert(0, str3);
/*     */     }
/*     */     
/*     */ 
/* 225 */     if (paramString3 != null) { localStringBuffer.insert(0, paramString3 + "\n");
/*     */     }
/* 227 */     this.format = str1;
/*     */     
/* 229 */     return localStringBuffer.toString();
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
/*     */   public String toTitleString(ObjectMatrix3D paramObjectMatrix3D, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String paramString1, String paramString2, String paramString3, String paramString4)
/*     */   {
/* 246 */     if (paramObjectMatrix3D.size() == 0) return "Empty matrix";
/* 247 */     StringBuffer localStringBuffer = new StringBuffer();
/* 248 */     for (int i = 0; i < paramObjectMatrix3D.slices(); i++) {
/* 249 */       if (i != 0) localStringBuffer.append(this.sliceSeparator);
/* 250 */       localStringBuffer.append(toTitleString(paramObjectMatrix3D.viewSlice(i), paramArrayOfString2, paramArrayOfString3, paramString2, paramString3, paramString4 + "\n" + paramString1 + "=" + paramArrayOfString1[i]));
/*     */     }
/* 252 */     return localStringBuffer.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/objectalgo/Formatter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */