/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.axis.ValueTick;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ /**
/*     */  * @deprecated
/*     */  */
/*     */ public abstract class ColorPalette
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -9029901853079622051L;
/*  68 */   protected double minZ = -1.0D;
/*     */   
/*     */ 
/*  71 */   protected double maxZ = -1.0D;
/*     */   
/*     */ 
/*     */   protected int[] r;
/*     */   
/*     */ 
/*     */   protected int[] g;
/*     */   
/*     */ 
/*     */   protected int[] b;
/*     */   
/*     */ 
/*  83 */   protected double[] tickValues = null;
/*     */   
/*     */ 
/*  86 */   protected boolean logscale = false;
/*     */   
/*     */ 
/*  89 */   protected boolean inverse = false;
/*     */   
/*     */ 
/*  92 */   protected String paletteName = null;
/*     */   
/*     */ 
/*  95 */   protected boolean stepped = false;
/*     */   
/*     */ 
/*  98 */   protected static final double log10 = Math.log(10.0D);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getColor(double value)
/*     */   {
/* 115 */     int izV = (int)(253.0D * (value - this.minZ) / (this.maxZ - this.minZ)) + 2;
/*     */     
/* 117 */     return new Color(this.r[izV], this.g[izV], this.b[izV]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Color getColor(int izV)
/*     */   {
/* 128 */     return new Color(this.r[izV], this.g[izV], this.b[izV]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Color getColorLinear(double value)
/*     */   {
/* 139 */     int izV = 0;
/* 140 */     if (this.stepped) {
/* 141 */       int index = Arrays.binarySearch(this.tickValues, value);
/* 142 */       if (index < 0) {
/* 143 */         index = -1 * index - 2;
/*     */       }
/*     */       
/* 146 */       if (index < 0)
/*     */       {
/* 148 */         value = this.minZ;
/*     */       }
/*     */       else {
/* 151 */         value = this.tickValues[index];
/*     */       }
/*     */     }
/* 154 */     izV = (int)(253.0D * (value - this.minZ) / (this.maxZ - this.minZ)) + 2;
/* 155 */     izV = Math.min(izV, 255);
/* 156 */     izV = Math.max(izV, 2);
/* 157 */     return getColor(izV);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Color getColorLog(double value)
/*     */   {
/* 168 */     int izV = 0;
/* 169 */     double minZtmp = this.minZ;
/* 170 */     double maxZtmp = this.maxZ;
/* 171 */     if (this.minZ <= 0.0D)
/*     */     {
/* 173 */       this.maxZ = (maxZtmp - minZtmp + 1.0D);
/* 174 */       this.minZ = 1.0D;
/* 175 */       value = value - minZtmp + 1.0D;
/*     */     }
/* 177 */     double minZlog = Math.log(this.minZ) / log10;
/* 178 */     double maxZlog = Math.log(this.maxZ) / log10;
/* 179 */     value = Math.log(value) / log10;
/*     */     
/* 181 */     if (this.stepped) {
/* 182 */       int numSteps = this.tickValues.length;
/* 183 */       int steps = 256 / (numSteps - 1);
/* 184 */       izV = steps * (int)(numSteps * (value - minZlog) / (maxZlog - minZlog)) + 2;
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 189 */       izV = (int)(253.0D * (value - minZlog) / (maxZlog - minZlog)) + 2;
/*     */     }
/* 191 */     izV = Math.min(izV, 255);
/* 192 */     izV = Math.max(izV, 2);
/*     */     
/* 194 */     this.minZ = minZtmp;
/* 195 */     this.maxZ = maxZtmp;
/*     */     
/* 197 */     return getColor(izV);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMaxZ()
/*     */   {
/* 206 */     return this.maxZ;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMinZ()
/*     */   {
/* 215 */     return this.minZ;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getPaint(double value)
/*     */   {
/* 227 */     if (isLogscale()) {
/* 228 */       return getColorLog(value);
/*     */     }
/*     */     
/* 231 */     return getColorLinear(value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPaletteName()
/*     */   {
/* 241 */     return this.paletteName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] getTickValues()
/*     */   {
/* 250 */     return this.tickValues;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void initialize();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void invertPalette()
/*     */   {
/* 263 */     int[] red = new int['Ā'];
/* 264 */     int[] green = new int['Ā'];
/* 265 */     int[] blue = new int['Ā'];
/* 266 */     for (int i = 0; i < 256; i++) {
/* 267 */       red[i] = this.r[i];
/* 268 */       green[i] = this.g[i];
/* 269 */       blue[i] = this.b[i];
/*     */     }
/*     */     
/* 272 */     for (int i = 2; i < 256; i++) {
/* 273 */       this.r[i] = red[(257 - i)];
/* 274 */       this.g[i] = green[(257 - i)];
/* 275 */       this.b[i] = blue[(257 - i)];
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isInverse()
/*     */   {
/* 285 */     return this.inverse;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLogscale()
/*     */   {
/* 294 */     return this.logscale;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isStepped()
/*     */   {
/* 303 */     return this.stepped;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setInverse(boolean inverse)
/*     */   {
/* 312 */     this.inverse = inverse;
/* 313 */     initialize();
/* 314 */     if (inverse) {
/* 315 */       invertPalette();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLogscale(boolean logscale)
/*     */   {
/* 326 */     this.logscale = logscale;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMaxZ(double newMaxZ)
/*     */   {
/* 335 */     this.maxZ = newMaxZ;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMinZ(double newMinZ)
/*     */   {
/* 344 */     this.minZ = newMinZ;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPaletteName(String paletteName)
/*     */   {
/* 354 */     this.paletteName = paletteName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStepped(boolean stepped)
/*     */   {
/* 364 */     this.stepped = stepped;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTickValues(double[] newTickValues)
/*     */   {
/* 374 */     this.tickValues = newTickValues;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTickValues(List ticks)
/*     */   {
/* 383 */     this.tickValues = new double[ticks.size()];
/* 384 */     for (int i = 0; i < this.tickValues.length; i++) {
/* 385 */       this.tickValues[i] = ((ValueTick)ticks.get(i)).getValue();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 397 */     if (this == o) {
/* 398 */       return true;
/*     */     }
/* 400 */     if (!(o instanceof ColorPalette)) {
/* 401 */       return false;
/*     */     }
/*     */     
/* 404 */     ColorPalette colorPalette = (ColorPalette)o;
/*     */     
/* 406 */     if (this.inverse != colorPalette.inverse) {
/* 407 */       return false;
/*     */     }
/* 409 */     if (this.logscale != colorPalette.logscale) {
/* 410 */       return false;
/*     */     }
/* 412 */     if (this.maxZ != colorPalette.maxZ) {
/* 413 */       return false;
/*     */     }
/* 415 */     if (this.minZ != colorPalette.minZ) {
/* 416 */       return false;
/*     */     }
/* 418 */     if (this.stepped != colorPalette.stepped) {
/* 419 */       return false;
/*     */     }
/* 421 */     if (!Arrays.equals(this.b, colorPalette.b)) {
/* 422 */       return false;
/*     */     }
/* 424 */     if (!Arrays.equals(this.g, colorPalette.g)) {
/* 425 */       return false;
/*     */     }
/* 427 */     if (this.paletteName != null ? !this.paletteName.equals(colorPalette.paletteName) : colorPalette.paletteName != null)
/*     */     {
/*     */ 
/* 430 */       return false;
/*     */     }
/* 432 */     if (!Arrays.equals(this.r, colorPalette.r)) {
/* 433 */       return false;
/*     */     }
/* 435 */     if (!Arrays.equals(this.tickValues, colorPalette.tickValues)) {
/* 436 */       return false;
/*     */     }
/*     */     
/* 439 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 450 */     long temp = Double.doubleToLongBits(this.minZ);
/* 451 */     int result = (int)(temp ^ temp >>> 32);
/* 452 */     temp = Double.doubleToLongBits(this.maxZ);
/* 453 */     result = 29 * result + (int)(temp ^ temp >>> 32);
/* 454 */     result = 29 * result + (this.logscale ? 1 : 0);
/* 455 */     result = 29 * result + (this.inverse ? 1 : 0);
/* 456 */     result = 29 * result + (this.paletteName != null ? this.paletteName.hashCode() : 0);
/*     */     
/* 458 */     result = 29 * result + (this.stepped ? 1 : 0);
/* 459 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 471 */     ColorPalette clone = (ColorPalette)super.clone();
/* 472 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/ColorPalette.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */