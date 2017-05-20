/*     */ package hep.aida.ref;
/*     */ 
/*     */ import cern.colt.list.ObjectArrayList;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.colt.matrix.DoubleMatrix3D;
/*     */ import cern.colt.matrix.doublealgo.Formatter;
/*     */ import cern.colt.matrix.impl.Former;
/*     */ import cern.colt.matrix.impl.FormerFactory;
/*     */ import hep.aida.IAxis;
/*     */ import hep.aida.IHistogram;
/*     */ import hep.aida.IHistogram1D;
/*     */ import hep.aida.IHistogram2D;
/*     */ import hep.aida.IHistogram3D;
/*     */ import hep.aida.bin.BinFunction1D;
/*     */ 
/*     */ public class Converter
/*     */ {
/*     */   public double[] edges(IAxis paramIAxis)
/*     */   {
/*  20 */     int i = paramIAxis.bins();
/*  21 */     double[] arrayOfDouble = new double[i + 1];
/*  22 */     for (int j = 0; j < i; j++) arrayOfDouble[j] = paramIAxis.binLowerEdge(j);
/*  23 */     arrayOfDouble[i] = paramIAxis.upperEdge();
/*  24 */     return arrayOfDouble;
/*     */   }
/*     */   
/*     */   String form(Former paramFormer, double paramDouble) {
/*  28 */     return paramFormer.form(paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected double[] toArrayErrors(IHistogram1D paramIHistogram1D)
/*     */   {
/*  35 */     int i = paramIHistogram1D.xAxis().bins();
/*  36 */     double[] arrayOfDouble = new double[i];
/*  37 */     int j = i;
/*  38 */     do { arrayOfDouble[j] = paramIHistogram1D.binError(j);j--;
/*  37 */     } while (j >= 0);
/*     */     
/*     */ 
/*  40 */     return arrayOfDouble;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected double[][] toArrayErrors(IHistogram2D paramIHistogram2D)
/*     */   {
/*  47 */     int i = paramIHistogram2D.xAxis().bins();
/*  48 */     int j = paramIHistogram2D.yAxis().bins();
/*  49 */     double[][] arrayOfDouble = new double[i][j];
/*  50 */     int k = j;
/*  51 */     do { int m = i;
/*  52 */       do { arrayOfDouble[m][k] = paramIHistogram2D.binError(m, k);m--;
/*  51 */       } while (m >= 0);
/*  50 */       k--; } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  55 */     return arrayOfDouble;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected double[] toArrayHeights(IHistogram1D paramIHistogram1D)
/*     */   {
/*  62 */     int i = paramIHistogram1D.xAxis().bins();
/*  63 */     double[] arrayOfDouble = new double[i];
/*  64 */     int j = i;
/*  65 */     do { arrayOfDouble[j] = paramIHistogram1D.binHeight(j);j--;
/*  64 */     } while (j >= 0);
/*     */     
/*     */ 
/*  67 */     return arrayOfDouble;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected double[][] toArrayHeights(IHistogram2D paramIHistogram2D)
/*     */   {
/*  74 */     int i = paramIHistogram2D.xAxis().bins();
/*  75 */     int j = paramIHistogram2D.yAxis().bins();
/*  76 */     double[][] arrayOfDouble = new double[i][j];
/*  77 */     int k = j;
/*  78 */     do { int m = i;
/*  79 */       do { arrayOfDouble[m][k] = paramIHistogram2D.binHeight(m, k);m--;
/*  78 */       } while (m >= 0);
/*  77 */       k--; } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  82 */     return arrayOfDouble;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected double[][][] toArrayHeights(IHistogram3D paramIHistogram3D)
/*     */   {
/*  89 */     int i = paramIHistogram3D.xAxis().bins();
/*  90 */     int j = paramIHistogram3D.yAxis().bins();
/*  91 */     int k = paramIHistogram3D.zAxis().bins();
/*  92 */     double[][][] arrayOfDouble = new double[i][j][k];
/*  93 */     int m = i;
/*  94 */     do { int n = j;
/*  95 */       do { int i1 = k;
/*  96 */         do { arrayOfDouble[m][n][i1] = paramIHistogram3D.binHeight(m, n, i1);i1--;
/*  95 */         } while (i1 >= 0);
/*  94 */         n--; } while (n >= 0);
/*  93 */       m--; } while (m >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 100 */     return arrayOfDouble;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static String toString(double[] paramArrayOfDouble)
/*     */   {
/* 110 */     StringBuffer localStringBuffer = new StringBuffer();
/* 111 */     localStringBuffer.append("[");
/* 112 */     int i = paramArrayOfDouble.length - 1;
/* 113 */     for (int j = 0; j <= i; j++) {
/* 114 */       localStringBuffer.append(paramArrayOfDouble[j]);
/* 115 */       if (j < i)
/* 116 */         localStringBuffer.append(", ");
/*     */     }
/* 118 */     localStringBuffer.append("]");
/* 119 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString(IAxis paramIAxis)
/*     */   {
/* 126 */     StringBuffer localStringBuffer = new StringBuffer();
/* 127 */     localStringBuffer.append("Range: [" + paramIAxis.lowerEdge() + "," + paramIAxis.upperEdge() + ")");
/* 128 */     localStringBuffer.append(", Bins: " + paramIAxis.bins());
/* 129 */     localStringBuffer.append(", Bin edges: " + toString(edges(paramIAxis)) + "\n");
/* 130 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString(IHistogram1D paramIHistogram1D)
/*     */   {
/* 137 */     String str1 = null;
/* 138 */     String str2 = null;
/* 139 */     BinFunction1D[] arrayOfBinFunction1D = null;
/* 140 */     String str3 = "%G";
/*     */     
/*     */ 
/* 143 */     Former localFormer = new FormerFactory().create(str3);
/* 144 */     String str4 = System.getProperty("line.separator");
/* 145 */     int[] arrayOfInt = paramIHistogram1D.minMaxBins();
/* 146 */     String str5 = paramIHistogram1D.title() + ":" + str4 + "   Entries=" + form(localFormer, paramIHistogram1D.entries()) + ", ExtraEntries=" + form(localFormer, paramIHistogram1D.extraEntries()) + str4 + "   Mean=" + form(localFormer, paramIHistogram1D.mean()) + ", Rms=" + form(localFormer, paramIHistogram1D.rms()) + str4 + "   MinBinHeight=" + form(localFormer, paramIHistogram1D.binHeight(arrayOfInt[0])) + ", MaxBinHeight=" + form(localFormer, paramIHistogram1D.binHeight(arrayOfInt[1])) + str4 + "   Axis: " + "Bins=" + form(localFormer, paramIHistogram1D.xAxis().bins()) + ", Min=" + form(localFormer, paramIHistogram1D.xAxis().lowerEdge()) + ", Max=" + form(localFormer, paramIHistogram1D.xAxis().upperEdge());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 155 */     String[] arrayOfString1 = new String[paramIHistogram1D.xAxis().bins()];
/* 156 */     for (int i = 0; i < paramIHistogram1D.xAxis().bins(); i++) { arrayOfString1[i] = form(localFormer, paramIHistogram1D.xAxis().binLowerEdge(i));
/*     */     }
/* 158 */     String[] arrayOfString2 = null;
/*     */     
/* 160 */     cern.colt.matrix.impl.DenseDoubleMatrix2D localDenseDoubleMatrix2D = new cern.colt.matrix.impl.DenseDoubleMatrix2D(1, paramIHistogram1D.xAxis().bins());
/* 161 */     localDenseDoubleMatrix2D.viewRow(0).assign(toArrayHeights(paramIHistogram1D));
/*     */     
/*     */ 
/*     */ 
/* 165 */     return str5 + str4 + "Heights:" + str4 + new Formatter().toTitleString(localDenseDoubleMatrix2D, arrayOfString2, arrayOfString1, str2, str1, null, arrayOfBinFunction1D);
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
/*     */   public String toString(IHistogram2D paramIHistogram2D)
/*     */   {
/* 181 */     String str1 = "X";
/* 182 */     String str2 = "Y";
/* 183 */     BinFunction1D[] arrayOfBinFunction1D = { hep.aida.bin.BinFunctions1D.sum };
/* 184 */     String str3 = "%G";
/*     */     
/*     */ 
/* 187 */     Former localFormer = new FormerFactory().create(str3);
/* 188 */     String str4 = System.getProperty("line.separator");
/* 189 */     int[] arrayOfInt = paramIHistogram2D.minMaxBins();
/* 190 */     String str5 = paramIHistogram2D.title() + ":" + str4 + "   Entries=" + form(localFormer, paramIHistogram2D.entries()) + ", ExtraEntries=" + form(localFormer, paramIHistogram2D.extraEntries()) + str4 + "   MeanX=" + form(localFormer, paramIHistogram2D.meanX()) + ", RmsX=" + form(localFormer, paramIHistogram2D.rmsX()) + str4 + "   MeanY=" + form(localFormer, paramIHistogram2D.meanY()) + ", RmsY=" + form(localFormer, paramIHistogram2D.rmsX()) + str4 + "   MinBinHeight=" + form(localFormer, paramIHistogram2D.binHeight(arrayOfInt[0], arrayOfInt[1])) + ", MaxBinHeight=" + form(localFormer, paramIHistogram2D.binHeight(arrayOfInt[2], arrayOfInt[3])) + str4 + "   xAxis: " + "Bins=" + form(localFormer, paramIHistogram2D.xAxis().bins()) + ", Min=" + form(localFormer, paramIHistogram2D.xAxis().lowerEdge()) + ", Max=" + form(localFormer, paramIHistogram2D.xAxis().upperEdge()) + str4 + "   yAxis: " + "Bins=" + form(localFormer, paramIHistogram2D.yAxis().bins()) + ", Min=" + form(localFormer, paramIHistogram2D.yAxis().lowerEdge()) + ", Max=" + form(localFormer, paramIHistogram2D.yAxis().upperEdge());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 206 */     String[] arrayOfString1 = new String[paramIHistogram2D.xAxis().bins()];
/* 207 */     for (int i = 0; i < paramIHistogram2D.xAxis().bins(); i++) { arrayOfString1[i] = form(localFormer, paramIHistogram2D.xAxis().binLowerEdge(i));
/*     */     }
/* 209 */     String[] arrayOfString2 = new String[paramIHistogram2D.yAxis().bins()];
/* 210 */     for (int j = 0; j < paramIHistogram2D.yAxis().bins(); j++) arrayOfString2[j] = form(localFormer, paramIHistogram2D.yAxis().binLowerEdge(j));
/* 211 */     new ObjectArrayList(arrayOfString2).reverse();
/*     */     
/* 213 */     Object localObject = new cern.colt.matrix.impl.DenseDoubleMatrix2D(toArrayHeights(paramIHistogram2D));
/* 214 */     localObject = ((DoubleMatrix2D)localObject).viewDice().viewRowFlip();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 221 */     return str5 + str4 + "Heights:" + str4 + new Formatter().toTitleString((DoubleMatrix2D)localObject, arrayOfString2, arrayOfString1, str2, str1, null, arrayOfBinFunction1D);
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
/*     */   public String toString(IHistogram3D paramIHistogram3D)
/*     */   {
/* 237 */     String str1 = "X";
/* 238 */     String str2 = "Y";
/* 239 */     String str3 = "Z";
/* 240 */     BinFunction1D[] arrayOfBinFunction1D = { hep.aida.bin.BinFunctions1D.sum };
/* 241 */     String str4 = "%G";
/*     */     
/*     */ 
/* 244 */     Former localFormer = new FormerFactory().create(str4);
/* 245 */     String str5 = System.getProperty("line.separator");
/* 246 */     int[] arrayOfInt = paramIHistogram3D.minMaxBins();
/* 247 */     String str6 = paramIHistogram3D.title() + ":" + str5 + "   Entries=" + form(localFormer, paramIHistogram3D.entries()) + ", ExtraEntries=" + form(localFormer, paramIHistogram3D.extraEntries()) + str5 + "   MeanX=" + form(localFormer, paramIHistogram3D.meanX()) + ", RmsX=" + form(localFormer, paramIHistogram3D.rmsX()) + str5 + "   MeanY=" + form(localFormer, paramIHistogram3D.meanY()) + ", RmsY=" + form(localFormer, paramIHistogram3D.rmsX()) + str5 + "   MeanZ=" + form(localFormer, paramIHistogram3D.meanZ()) + ", RmsZ=" + form(localFormer, paramIHistogram3D.rmsZ()) + str5 + "   MinBinHeight=" + form(localFormer, paramIHistogram3D.binHeight(arrayOfInt[0], arrayOfInt[1], arrayOfInt[2])) + ", MaxBinHeight=" + form(localFormer, paramIHistogram3D.binHeight(arrayOfInt[3], arrayOfInt[4], arrayOfInt[5])) + str5 + "   xAxis: " + "Bins=" + form(localFormer, paramIHistogram3D.xAxis().bins()) + ", Min=" + form(localFormer, paramIHistogram3D.xAxis().lowerEdge()) + ", Max=" + form(localFormer, paramIHistogram3D.xAxis().upperEdge()) + str5 + "   yAxis: " + "Bins=" + form(localFormer, paramIHistogram3D.yAxis().bins()) + ", Min=" + form(localFormer, paramIHistogram3D.yAxis().lowerEdge()) + ", Max=" + form(localFormer, paramIHistogram3D.yAxis().upperEdge()) + str5 + "   zAxis: " + "Bins=" + form(localFormer, paramIHistogram3D.zAxis().bins()) + ", Min=" + form(localFormer, paramIHistogram3D.zAxis().lowerEdge()) + ", Max=" + form(localFormer, paramIHistogram3D.zAxis().upperEdge());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 269 */     String[] arrayOfString1 = new String[paramIHistogram3D.xAxis().bins()];
/* 270 */     for (int i = 0; i < paramIHistogram3D.xAxis().bins(); i++) { arrayOfString1[i] = form(localFormer, paramIHistogram3D.xAxis().binLowerEdge(i));
/*     */     }
/* 272 */     String[] arrayOfString2 = new String[paramIHistogram3D.yAxis().bins()];
/* 273 */     for (int j = 0; j < paramIHistogram3D.yAxis().bins(); j++) arrayOfString2[j] = form(localFormer, paramIHistogram3D.yAxis().binLowerEdge(j));
/* 274 */     new ObjectArrayList(arrayOfString2).reverse();
/*     */     
/* 276 */     String[] arrayOfString3 = new String[paramIHistogram3D.zAxis().bins()];
/* 277 */     for (int k = 0; k < paramIHistogram3D.zAxis().bins(); k++) arrayOfString3[k] = form(localFormer, paramIHistogram3D.zAxis().binLowerEdge(k));
/* 278 */     new ObjectArrayList(arrayOfString3).reverse();
/*     */     
/* 280 */     Object localObject = new cern.colt.matrix.impl.DenseDoubleMatrix3D(toArrayHeights(paramIHistogram3D));
/* 281 */     localObject = ((DoubleMatrix3D)localObject).viewDice(2, 1, 0).viewSliceFlip().viewRowFlip();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 288 */     return str6 + str5 + "Heights:" + str5 + new Formatter().toTitleString((DoubleMatrix3D)localObject, arrayOfString3, arrayOfString2, arrayOfString1, str3, str2, str1, "", arrayOfBinFunction1D);
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
/*     */   public String toXML(IHistogram1D paramIHistogram1D)
/*     */   {
/* 304 */     StringBuffer localStringBuffer = new StringBuffer();
/* 305 */     String str = System.getProperty("line.separator");
/* 306 */     localStringBuffer.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>");localStringBuffer.append(str);
/* 307 */     localStringBuffer.append("<!DOCTYPE plotML SYSTEM \"plotML.dtd\">");localStringBuffer.append(str);
/* 308 */     localStringBuffer.append("<plotML>");localStringBuffer.append(str);
/* 309 */     localStringBuffer.append("<plot>");localStringBuffer.append(str);
/* 310 */     localStringBuffer.append("<dataArea>");localStringBuffer.append(str);
/* 311 */     localStringBuffer.append("<data1d>");localStringBuffer.append(str);
/* 312 */     localStringBuffer.append("<bins1d title=\"" + paramIHistogram1D.title() + "\">");localStringBuffer.append(str);
/* 313 */     for (int i = 0; i < paramIHistogram1D.xAxis().bins(); i++)
/*     */     {
/* 315 */       localStringBuffer.append(paramIHistogram1D.binEntries(i) + "," + paramIHistogram1D.binError(i));localStringBuffer.append(str);
/*     */     }
/* 317 */     localStringBuffer.append("</bins1d>");localStringBuffer.append(str);
/* 318 */     localStringBuffer.append("<binnedDataAxisAttributes type=\"double\" axis=\"x0\"");
/* 319 */     localStringBuffer.append(" min=\"" + paramIHistogram1D.xAxis().lowerEdge() + "\"");
/* 320 */     localStringBuffer.append(" max=\"" + paramIHistogram1D.xAxis().upperEdge() + "\"");
/* 321 */     localStringBuffer.append(" numberOfBins=\"" + paramIHistogram1D.xAxis().bins() + "\"");
/* 322 */     localStringBuffer.append("/>");localStringBuffer.append(str);
/* 323 */     localStringBuffer.append("<statistics>");localStringBuffer.append(str);
/* 324 */     localStringBuffer.append("<statistic name=\"Entries\" value=\"" + paramIHistogram1D.entries() + "\"/>");localStringBuffer.append(str);
/* 325 */     localStringBuffer.append("<statistic name=\"Underflow\" value=\"" + paramIHistogram1D.binEntries(-2) + "\"/>");localStringBuffer.append(str);
/* 326 */     localStringBuffer.append("<statistic name=\"Overflow\" value=\"" + paramIHistogram1D.binEntries(-1) + "\"/>");localStringBuffer.append(str);
/* 327 */     if (!Double.isNaN(paramIHistogram1D.mean())) {
/* 328 */       localStringBuffer.append("<statistic name=\"Mean\" value=\"" + paramIHistogram1D.mean() + "\"/>");localStringBuffer.append(str);
/*     */     }
/* 330 */     if (!Double.isNaN(paramIHistogram1D.rms())) {
/* 331 */       localStringBuffer.append("<statistic name=\"RMS\" value=\"" + paramIHistogram1D.rms() + "\"/>");localStringBuffer.append(str);
/*     */     }
/* 333 */     localStringBuffer.append("</statistics>");localStringBuffer.append(str);
/* 334 */     localStringBuffer.append("</data1d>");localStringBuffer.append(str);
/* 335 */     localStringBuffer.append("</dataArea>");localStringBuffer.append(str);
/* 336 */     localStringBuffer.append("</plot>");localStringBuffer.append(str);
/* 337 */     localStringBuffer.append("</plotML>");localStringBuffer.append(str);
/* 338 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toXML(IHistogram2D paramIHistogram2D)
/*     */   {
/* 345 */     StringBuffer localStringBuffer = new StringBuffer();
/* 346 */     String str = System.getProperty("line.separator");
/* 347 */     localStringBuffer.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>");localStringBuffer.append(str);
/* 348 */     localStringBuffer.append("<!DOCTYPE plotML SYSTEM \"plotML.dtd\">");localStringBuffer.append(str);
/* 349 */     localStringBuffer.append("<plotML>");localStringBuffer.append(str);
/* 350 */     localStringBuffer.append("<plot>");localStringBuffer.append(str);
/* 351 */     localStringBuffer.append("<dataArea>");localStringBuffer.append(str);
/* 352 */     localStringBuffer.append("<data2d type=\"xxx\">");localStringBuffer.append(str);
/* 353 */     localStringBuffer.append("<bins2d title=\"" + paramIHistogram2D.title() + "\" xSize=\"" + paramIHistogram2D.xAxis().bins() + "\" ySize=\"" + paramIHistogram2D.yAxis().bins() + "\">");localStringBuffer.append(str);
/* 354 */     for (int i = 0; i < paramIHistogram2D.xAxis().bins(); i++)
/* 355 */       for (int j = 0; j < paramIHistogram2D.yAxis().bins(); j++)
/*     */       {
/* 357 */         localStringBuffer.append(paramIHistogram2D.binEntries(i, j) + "," + paramIHistogram2D.binError(i, j));localStringBuffer.append(str);
/*     */       }
/* 359 */     localStringBuffer.append("</bins2d>");localStringBuffer.append(str);
/* 360 */     localStringBuffer.append("<binnedDataAxisAttributes type=\"double\" axis=\"x0\"");
/* 361 */     localStringBuffer.append(" min=\"" + paramIHistogram2D.xAxis().lowerEdge() + "\"");
/* 362 */     localStringBuffer.append(" max=\"" + paramIHistogram2D.xAxis().upperEdge() + "\"");
/* 363 */     localStringBuffer.append(" numberOfBins=\"" + paramIHistogram2D.xAxis().bins() + "\"");
/* 364 */     localStringBuffer.append("/>");localStringBuffer.append(str);
/* 365 */     localStringBuffer.append("<binnedDataAxisAttributes type=\"double\" axis=\"y0\"");
/* 366 */     localStringBuffer.append(" min=\"" + paramIHistogram2D.yAxis().lowerEdge() + "\"");
/* 367 */     localStringBuffer.append(" max=\"" + paramIHistogram2D.yAxis().upperEdge() + "\"");
/* 368 */     localStringBuffer.append(" numberOfBins=\"" + paramIHistogram2D.yAxis().bins() + "\"");
/* 369 */     localStringBuffer.append("/>");localStringBuffer.append(str);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 377 */     localStringBuffer.append("</data2d>");localStringBuffer.append(str);
/* 378 */     localStringBuffer.append("</dataArea>");localStringBuffer.append(str);
/* 379 */     localStringBuffer.append("</plot>");localStringBuffer.append(str);
/* 380 */     localStringBuffer.append("</plotML>");localStringBuffer.append(str);
/* 381 */     return localStringBuffer.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/hep/aida/ref/Converter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */