/*     */ package com.fluxicon.slickerbox.util;
/*     */ 
/*     */ import java.awt.Color;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ColorUtils
/*     */ {
/*     */   public static Color setAlphaFloat(Color color, float alpha)
/*     */   {
/*  53 */     return setAlphaInt(color, (int)(alpha * 255.0F));
/*     */   }
/*     */   
/*     */   public static Color setAlphaInt(Color color, int alpha) {
/*  57 */     return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
/*     */   }
/*     */   
/*     */   public static Color deriveHilight(Color color) {
/*  61 */     int red = Math.min(255, color.getRed() + 40);
/*  62 */     int green = Math.min(255, color.getGreen() + 40);
/*  63 */     int blue = Math.min(255, color.getBlue() + 40);
/*  64 */     return new Color(red, green, blue);
/*     */   }
/*     */   
/*     */   public static Color deriveContrastGreytone(Color color) {
/*  68 */     int red = color.getRed();
/*  69 */     int green = color.getGreen();
/*  70 */     int blue = color.getBlue();
/*  71 */     int mean = (red + green + blue) / 3;
/*     */     int tone;
/*  73 */     int tone; if (mean > 120) {
/*  74 */       tone = Math.max(mean - 160, 0);
/*     */     } else {
/*  76 */       tone = Math.min(mean + 160, 255);
/*     */     }
/*  78 */     return new Color(tone, tone, tone);
/*     */   }
/*     */   
/*     */   public static Color deriveGreytone(Color color, double amount) {
/*  82 */     int red = color.getRed();
/*  83 */     int green = color.getGreen();
/*  84 */     int blue = color.getBlue();
/*  85 */     int mean = (red + green + blue) / 3;
/*  86 */     red = mix(mean, red, amount);
/*  87 */     green = mix(mean, green, amount);
/*  88 */     blue = mix(mean, blue, amount);
/*  89 */     return new Color(red, green, blue);
/*     */   }
/*     */   
/*     */   public static Color adjustBrightness(Color color, int channelDifference) {
/*  93 */     int red = color.getRed() + channelDifference;
/*  94 */     int green = color.getGreen() + channelDifference;
/*  95 */     int blue = color.getBlue() + channelDifference;
/*  96 */     if (red > 255) red = 255;
/*  97 */     if (green > 255) green = 255;
/*  98 */     if (blue > 255) blue = 255;
/*  99 */     if (red < 0) red = 0;
/* 100 */     if (green < 0) green = 0;
/* 101 */     if (blue < 0) blue = 0;
/* 102 */     return new Color(red, green, blue);
/*     */   }
/*     */   
/*     */   public static Color darken(Color color, int darken) {
/* 106 */     return adjustBrightness(color, -darken);
/*     */   }
/*     */   
/*     */   public static Color brighten(Color color, int brighten) {
/* 110 */     return adjustBrightness(color, brighten);
/*     */   }
/*     */   
/*     */   public static Color getBestGreytoneOffset(Color color, int offset, double greytoneAmount) {
/* 114 */     int red = color.getRed();
/* 115 */     int green = color.getGreen();
/* 116 */     int blue = color.getBlue();
/* 117 */     Color greytone = deriveGreytone(color, greytoneAmount);
/* 118 */     int mean = (red + green + blue) / 3;
/* 119 */     if (mean > offset) {
/* 120 */       return darken(greytone, offset);
/*     */     }
/* 122 */     return brighten(greytone, offset);
/*     */   }
/*     */   
/*     */   public static Color getBestGradientHilight(Color color, int offset)
/*     */   {
/* 127 */     int red = color.getRed();
/* 128 */     int green = color.getGreen();
/* 129 */     int blue = color.getBlue();
/* 130 */     int mean = (red + green + blue) / 3;
/* 131 */     if (mean < 255 - offset) {
/* 132 */       return brighten(color, offset);
/*     */     }
/* 134 */     return darken(color, offset);
/*     */   }
/*     */   
/*     */   public static Color getGreytone(Color color)
/*     */   {
/* 139 */     int red = color.getRed();
/* 140 */     int green = color.getGreen();
/* 141 */     int blue = color.getBlue();
/* 142 */     int mean = (red + green + blue) / 3;
/* 143 */     return new Color(mean, mean, mean);
/*     */   }
/*     */   
/*     */   public static int mix(int value1, int value2, double amount) {
/* 147 */     return (int)(value1 * amount + value2 * (1.0D - amount));
/*     */   }
/*     */   
/*     */   public static Color mix(Color color1, Color color2, double percentage) {
/* 151 */     double antiPercentage = 1.0D - percentage;
/* 152 */     int red = (int)(color1.getRed() * antiPercentage + color2.getRed() * percentage);
/* 153 */     int green = (int)(color1.getGreen() * antiPercentage + color2.getGreen() * percentage);
/* 154 */     int blue = (int)(color1.getBlue() * antiPercentage + color2.getBlue() * percentage);
/* 155 */     int alpha = (int)(color1.getAlpha() * antiPercentage + color2.getAlpha() * percentage);
/* 156 */     return new Color(red, green, blue, alpha);
/*     */   }
/*     */   
/*     */   public static Color encodeZeroOneScale(double value) {
/* 160 */     if (value < 0.0D) value = 0.0D;
/* 161 */     if (value > 1.0D) value = 1.0D;
/*     */     float blue;
/* 163 */     float red; float green; float blue; if (value > 0.5D) {
/* 164 */       float red = (1.0F - (float)value) * 2.0F;
/* 165 */       float green = 1.0F;
/* 166 */       blue = 0.0F;
/*     */     } else {
/* 168 */       red = 1.0F;
/* 169 */       green = (float)value * 2.0F;
/* 170 */       blue = 0.0F;
/*     */     }
/* 172 */     red *= 0.8F;
/* 173 */     green *= 0.8F;
/* 174 */     return new Color(red, green, blue);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/util/ColorUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */