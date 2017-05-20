/*     */ package flanagan.physprop;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.JOptionPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IonicRadii
/*     */ {
/*  43 */   private static String[] ions1 = { "Ag+", "Al+++", "Au+", "Au+++", "Ba++", "Be++", "Bi+++", "Ca++", "Cd++", "Ce+++", "Ce++++", "Co++ ls", "Co++ hs", "Co+++ ls", "Co+++ hs", "Cr++ ls", "Cr++ hs", "Cr+++", "Cs+", "Cu+", "Cu++", "Dy+++", "Er+++", "Eu++", "Eu+++", "Fe++ ls", "Fe++ hs", "Fe+++ ls", "Fe+++ hs", "Ga+++", "Gd+++", "Hf++++", "Hg+", "Hg++", "Ho+++", "In+++", "Ir+++", "K+", "La+++", "Li+", "Lu+++", "Mg++", "Mn++ ls", "Mn++ hs", "Mn+++ ls", "Mn+++ hs", "Mo+++", "Na+", "Nb+++", "Nd+++", "Ni++", "Pb++", "Pd++", "Pm+++", "Pr+++", "Pt++", "Rb+", "Rh+++", "Ru+++", "Sb+++", "Sc+++", "Sm+++", "Sr++", "Ta+++", "Tb+++", "Th++++", "Ti++", "Ti+++", "Ti++++", "Tl+", "Tl+++", "Tm+++", "U+++", "U++++", "V++", "V+++", "Y+++", "Yb++", "Yb+++", "Zn++", "Zr++++", "Br-", "Cl-", "F-", "H-", "I-", "O--", "S--", "Se--", "Te--", "OH-" };
/*  44 */   private static String[] ions2 = { "Ag1+", "Al3+", "Au1+", "Au3+", "Ba2+", "Be2+", "Bi3+", "Ca2+", "Cd2+", "Ce3+", "Ce4+", "Co2+ ls", "Co2+ hs", "Co3+ ls", "Co3+ hs", "Cr2+ ls", "Cr2+ hs", "Cr3+", "Cs1+", "Cu1+", "Cu2+", "Dy3+", "Er3+", "Eu2+", "Eu3+", "Fe2+ ls", "Fe2+ hs", "Fe3+ ls", "Fe3+ hs", "Ga3+", "Gd3+", "Hf4+", "Hg1+", "Hg2+", "Ho3+", "In3+", "Ir3+", "K1+", "La3+", "Li1+", "Lu3+", "Mg2+", "Mn2+ ls", "Mn2+ hs", "Mn3+ ls", "Mn3+ hs", "Mo3+", "Na1+", "Nb3+", "Nd3+", "Ni2+", "Pb2+", "Pd2+", "Pm3+", "Pr3+", "Pt2+", "Rb1+", "Rh3+", "Ru3+", "Sb3+", "Sc3+", "Sm3+", "Sr2+", "Ta3+", "Tb3+", "Th4+", "Ti2+", "Ti3+", "Ti4+", "Tl1+", "Tl3+", "Tm3+", "U3+", "U4+", "V2+", "V3+", "Y3+", "Yb2+", "Yb3+", "Zn2+", "Zr4+", "Br1-", "Cl1-", "F1-", "H1-", "I1-", "O2-", "S2-", "Se2-", "Te2-", "OH1-" };
/*  45 */   private static String[] ions3 = { "Ag+1", "Al+3", "Au+1", "Au+3", "Ba+2", "Be+2", "Bi+3", "Ca+2", "Cd+2", "Ce+3", "Ce+4", "Co+2 ls", "Co+2 hs", "Co+3 ls", "Co+3 hs", "Cr+2 ls", "Cr+2 hs", "Cr+3", "Cs+1", "Cu+1", "Cu+2", "Dy+3", "Er+3", "Eu+2", "Eu+3", "Fe+2 ls", "Fe+2 hs", "Fe+3 ls", "Fe+3 hs", "Ga+3", "Gd+3", "Hf+4", "Hg+1", "Hg+2", "Ho+3", "In+3", "Ir+3", "K+1", "La+3", "Li+1", "Lu+3", "Mg+2", "Mn+2 ls", "Mn+2 hs", "Mn+3 ls", "Mn+3 hs", "Mo+3", "Na+1", "Nb+3", "Nd+3", "Ni+2", "Pb+2", "Pd+2", "Pm+3", "Pr+3", "Pt+2", "Rb+1", "Rh+3", "Ru+3", "Sb+3", "Sc+3", "Sm+3", "Sr+2", "Ta+3", "Tb+3", "Th+4", "Ti+2", "Ti+3", "Ti+4", "Tl+1", "Tl+3", "Tm+3", "U+3", "U+4", "V+2", "V+3", "Y+3", "Yb+2", "Yb+3", "Zn+2", "Zr+4", "Br-1", "Cl-1", "F-1", "H-1", "I-1", "O-2", "S-2", "Se-2", "Te-2", "OH-1" };
/*  46 */   private static String[] ions4 = { "Ag(+)", "Al(+++)", "Au(+)", "Au(+++)", "Ba(++)", "Be(++)", "Bi(+++)", "Ca(++)", "Cd(++)", "Ce(+++)", "Ce(++++)", "Co(++) ls", "Co(++) hs", "Co(+++) ls", "Co(+++) hs", "Cr(++) ls", "Cr(++) hs", "Cr(+++)", "Cs(+)", "Cu(+)", "Cu(++)", "Dy(+++)", "Er(+++)", "Eu(++)", "Eu(+++)", "Fe(++) ls", "Fe(++) hs", "Fe(+++) ls", "Fe(+++) hs", "Ga(+++)", "Gd(+++)", "Hf(++++)", "Hg(+)", "Hg(++)", "Ho(+++)", "In(+++)", "Ir(+++)", "K(+)", "La(+++)", "Li(+)", "Lu(+++)", "Mg(++)", "Mn(++) ls", "Mn(++) hs", "Mn(+++) ls", "Mn(+++) hs", "Mo(+++)", "Na(+)", "Nb(+++)", "Nd(+++)", "Ni(++)", "Pb(++)", "Pd(++)", "Pm(+++)", "Pr(+++)", "Pt(++)", "Rb(+)", "Rh(+++)", "Ru(+++)", "Sb(+++)", "Sc(+++)", "Sm(+++)", "Sr(++)", "Ta(+++)", "Tb(+++)", "Th(++++)", "Ti(++)", "Ti(+++)", "Ti(++++)", "Tl(+)", "Tl(+++)", "Tm(+++)", "U(+++)", "U(++++)", "V(++)", "V(+++)", "Y(+++)", "Yb(++)", "Yb(+++)", "Zn(++)", "Zr(++++)", "Br(-)", "Cl(-)", "F(-)", "H(-)", "I(-)", "O(--)", "S(--)", "Se(--)", "Te(--)", "OH(-)" };
/*  47 */   private static String[] ions5 = { "Ag(1+)", "Al(3+)", "Au(1+)", "Au(3+)", "Ba(2+)", "Be(2+)", "Bi(3+)", "Ca(2+)", "Cd(2+)", "Ce(3+)", "Ce(4+)", "Co(2+) ls", "Co(2+) hs", "Co(3+) ls", "Co(3+) hs", "Cr(2+) ls", "Cr(2+) hs", "Cr(3+)", "Cs(1+)", "Cu(1+)", "Cu(2+)", "Dy(3+)", "Er(3+)", "Eu(2+)", "Eu(3+)", "Fe(2+) ls", "Fe(2+) hs", "Fe(3+) ls", "Fe(3+) hs", "Ga(3+)", "Gd(3+)", "Hf(4+)", "Hg(1+)", "Hg(2+)", "Ho(3+)", "In(3+)", "Ir(3+)", "K(1+)", "La(3+)", "Li(1+)", "Lu(3+)", "Mg(2+)", "Mn(2+) ls", "Mn(2+) hs", "Mn(3+) ls", "Mn(3+) hs", "Mo(3+)", "Na(1+)", "Nb(3+)", "Nd(3+)", "Ni(2+)", "Pb(2+)", "Pd(2+)", "Pm(3+)", "Pr(3+)", "Pt(2+)", "Rb(1+)", "Rh(3+)", "Ru(3+)", "Sb(3+)", "Sc(3+)", "Sm(3+)", "Sr(2+)", "Ta(3+)", "Tb(3+)", "Th(4+)", "Ti(2+)", "Ti(3+)", "Ti(4+)", "Tl(1+)", "Tl(3+)", "Tm(3+)", "U(3+)", "U(4+)", "V(2+)", "V(3+)", "Y(3+)", "Yb(2+)", "Yb(3+)", "Zn(2+)", "Zr(4+)", "Br(1-)", "Cl(1-)", "F(1-)", "H(1-)", "I(1-)", "O(2-)", "S(2-)", "Se(2-)", "Te(2-)", "OH(1-)" };
/*  48 */   private static String[] ions6 = { "Ag(+1)", "Al(+3)", "Au(+1)", "Au(+3)", "Ba(+2)", "Be(+2)", "Bi(+3)", "Ca(+2)", "Cd(+2)", "Ce(+3)", "Ce(+4)", "Co(+2) ls", "Co(+2) hs", "Co(+3) ls", "Co(+3) hs", "Cr(+2) ls", "Cr(+2) hs", "Cr(+3)", "Cs(+1)", "Cu(+1)", "Cu(+2)", "Dy(+3)", "Er(+3)", "Eu(+2)", "Eu(+3)", "Fe(+2) ls", "Fe(+2) hs", "Fe(+3) ls", "Fe(+3) hs", "Ga(+3)", "Gd(+3)", "Hf(+4)", "Hg(+1)", "Hg(+2)", "Ho(+3)", "In(+3)", "Ir(+3)", "K(+1)", "La(+3)", "Li(+1)", "Lu(+3)", "Mg(+2)", "Mn(+2) ls", "Mn(+2) hs", "Mn(+3) ls", "Mn(+3) hs", "Mo(+3)", "Na(+1)", "Nb(+3)", "Nd(+3)", "Ni(+2)", "Pb(+2)", "Pd(+2)", "Pm(+3)", "Pr(+3)", "Pt(+2)", "Rb(+1)", "Rh(+3)", "Ru(+3)", "Sb(+3)", "Sc(+3)", "Sm(+3)", "Sr(+2)", "Ta(+3)", "Tb(+3)", "Th(+4)", "Ti(+2)", "Ti(+3)", "Ti(+4)", "Tl(+1)", "Tl(+3)", "Tm(+3)", "U(+3)", "U(+4)", "V(+2)", "V(+3)", "Y(+3)", "Yb(+2)", "Yb(+3)", "Zn(+2)", "Zr(+4)", "Br(-1)", "Cl(-1)", "F(-1)", "H(-1)", "I(-1)", "O(-2)", "S(-2)", "Se(-2)", "Te(-2)", "OH(-1)" };
/*     */   
/*     */ 
/*  51 */   private static boolean[] spins = { false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, false, false, false, false, false, false, false, false, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false };
/*     */   
/*     */ 
/*  54 */   private static int[] ionCharge = { 1, 3, 1, 3, 2, 2, 3, 2, 2, 3, 4, 2, 2, 3, 3, 2, 2, 3, 1, 1, 2, 3, 3, 2, 3, 2, 2, 3, 3, 3, 3, 4, 1, 2, 3, 3, 3, 1, 3, 1, 3, 2, 2, 2, 3, 3, 3, 1, 3, 3, 2, 2, 2, 3, 3, 2, 1, 3, 3, 3, 3, 3, 2, 3, 3, 4, 2, 3, 4, 1, 3, 3, 3, 4, 2, 3, 3, 2, 3, 2, 4, -1, -1, -1, -1, -1, -2, -2, -2, -2, -1 };
/*     */   
/*     */ 
/*  57 */   private static double[] radii = { 129.0D, 67.5D, 151.0D, 99.0D, 149.0D, 59.0D, 117.0D, 114.0D, 109.0D, 115.0D, 101.0D, 79.0D, 88.5D, 68.5D, 75.0D, 87.0D, 94.0D, 75.5D, 181.0D, 91.0D, 87.0D, 105.2D, 103.0D, 131.0D, 108.7D, 75.0D, 92.0D, 69.0D, 78.5D, 76.0D, 107.8D, 85.0D, 133.0D, 116.0D, 104.1D, 94.0D, 82.0D, 152.0D, 117.2D, 90.0D, 100.1D, 86.0D, 81.0D, 97.0D, 72.0D, 78.5D, 83.0D, 116.0D, 86.0D, 112.3D, 83.0D, 133.0D, 100.0D, 111.0D, 113.0D, 94.0D, 166.0D, 80.5D, 82.0D, 90.0D, 88.5D, 109.8D, 132.0D, 86.0D, 106.3D, 108.0D, 100.0D, 81.0D, 74.5D, 164.0D, 102.5D, 102.0D, 116.5D, 103.0D, 93.0D, 78.0D, 104.0D, 116.0D, 100.8D, 88.0D, 86.0D, 167.0D, 182.0D, 119.0D, 139.0D, 206.0D, 126.0D, 170.0D, 184.0D, 207.0D, 120.0D };
/*     */   
/*     */ 
/*  60 */   private static String[] hydratedIons1 = { "Ag+", "Al+++", "Be++", "Ca++", "Cd++", "Cs+", "K+", "Li+", "Mg++", "Na+", "Pb++", "Rb+", "Tl+", "Zn++", "H30+", "NH4+", "Cl-", "Br-", "F-", "I-", "NO3-", "OH-" };
/*  61 */   private static String[] hydratedIons2 = { "Ag1+", "Al3+", "Be2+", "Ca2+", "Cd2+", "Cs1+", "K1+", "Li1+", "Mg2+", "Na1+", "Pb2+", "Rb1+", "Tl1+", "Zn2+", "H301+", "NH41+", "Cl1-", "Br1-", "F1-", "I1-", "NO31-", "OH1-" };
/*  62 */   private static String[] hydratedIons3 = { "Ag+1", "Al+3", "Be+2", "Ca+2", "Cd+2", "Cs+1", "K+1", "Li+1", "Mg+2", "Na+1", "Pb+2", "Rb+1", "Tl+1", "Zn+2", "H30+1", "NH4+1", "Cl-1", "Br-1", "F-1", "I-1", "NO3-1", "OH-1" };
/*  63 */   private static String[] hydratedIons4 = { "Ag(+)", "Al(+++)", "Be(++)", "Ca(++)", "Cd(++)", "Cs(+)", "K(+)", "Li(+)", "Mg(++)", "Na(+)", "Pb(++)", "Rb(+)", "Tl(+)", "Zn(++)", "H30(+)", "NH4(+)", "Cl(-)", "Br(-)", "F(-)", "I(-)", "NO3(-)", "OH(-)" };
/*  64 */   private static String[] hydratedIons5 = { "Ag(1+)", "Al(3+)", "Be(2+)", "Ca(2+)", "Cd(2+)", "Cs(1+)", "K(1+)", "Li(1+)", "Mg(2+)", "Na(1+)", "Pb(2+)", "Rb(1+)", "Tl(1+)", "Zn(2+)", "H30(1+)", "NH4(1+)", "Cl(1-)", "Br(1-)", "F(1-)", "I(1-)", "NO3(1-)", "OH(1-)" };
/*  65 */   private static String[] hydratedIons6 = { "Ag(+1)", "Al(+3)", "Be(+2)", "Ca(+2)", "Cd(+2)", "Cs(+1)", "K(+1)", "Li(+1)", "Mg(+2)", "Na(+1)", "Pb(+2)", "Rb(+1)", "Tl(+1)", "Zn(+2)", "H30(+1)", "NH4(+1)", "Cl(-1)", "Br(-1)", "F(-1)", "I(-1)", "NO3(-1)", "OH(-1)" };
/*     */   
/*     */ 
/*  68 */   private static double[] hydratedRadii = { 341.0D, 480.0D, 459.0D, 412.0D, 426.0D, 329.0D, 331.0D, 382.0D, 428.0D, 358.0D, 401.0D, 329.0D, 330.0D, 430.0D, 280.0D, 331.0D, 332.0D, 332.0D, 330.0D, 352.0D, 340.0D, 300.0D };
/*     */   
/*  70 */   private static int nIons = 91;
/*  71 */   private static int nHydratedIons = 22;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double radius(String ion, String spin)
/*     */   {
/*  81 */     boolean spinSet = false;
/*  82 */     spin = spin.trim();
/*     */     
/*  84 */     if ((spin.equals("ls")) || (spin.equals("low")) || (spin.equals("low spin")) || (spin.equals("LS"))) {
/*  85 */       spin = "ls";
/*     */ 
/*     */     }
/*  88 */     else if ((spin.equals("hs")) || (spin.equals("high")) || (spin.equals("high spin")) || (spin.equals("HS"))) {
/*  89 */       spin = "hs";
/*     */     }
/*     */     else {
/*  92 */       throw new IllegalArgumentException("spin state must be entered as ls or hs not as " + spin);
/*     */     }
/*     */     
/*  95 */     spinSet = true;
/*  96 */     ion = ion.trim();
/*  97 */     String fullIon = ion + " " + spin;
/*  98 */     return radiusCalc(fullIon, spinSet);
/*     */   }
/*     */   
/*     */   public static double radius(String ion)
/*     */   {
/* 103 */     boolean spinSet = false;
/* 104 */     return radiusCalc(ion, spinSet);
/*     */   }
/*     */   
/*     */   private static double radiusCalc(String ion, boolean spinSet)
/*     */   {
/* 109 */     String fullIon = ion.trim();
/* 110 */     if (!spinSet) { ion = fullIon;
/*     */     }
/* 112 */     boolean test0 = true;
/* 113 */     boolean test1 = false;
/* 114 */     int ii = 0;
/* 115 */     double radius = 0.0D;
/*     */     
/*     */ 
/* 118 */     while (test0) {
/* 119 */       if (compareBare(fullIon, ii)) {
/* 120 */         test0 = false;
/* 121 */         test1 = true;
/* 122 */         radius = radii[ii] * 1.0E-12D;
/*     */       }
/*     */       else {
/* 125 */         ii++;
/* 126 */         if (ii >= nIons) { test0 = false;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 131 */     if ((!test1) && (!spinSet)) {
/* 132 */       test0 = true;
/* 133 */       ii = 0;
/* 134 */       while (test0) {
/* 135 */         if ((compareSubstringBare(ion, ii)) && (spins[ii] != 0)) {
/* 136 */           test0 = false;
/* 137 */           test1 = true;
/* 138 */           boolean test2 = true;
/* 139 */           String enqTitle = ion + " may be low spin or high spin";
/* 140 */           Object[] options = { "low spin", "high spin" };
/* 141 */           while (test2) {
/* 142 */             int opt = JOptionPane.showOptionDialog(null, "Click appropriate box", enqTitle, 1, 3, null, options, options[0]);
/*     */             
/* 144 */             if (opt == 0) {
/* 145 */               radius = radii[ii] * 1.0E-12D;
/* 146 */               test2 = false;
/*     */ 
/*     */             }
/* 149 */             else if (opt == 1) {
/* 150 */               radius = radii[(ii + 1)] * 1.0E-12D;
/* 151 */               test2 = false;
/*     */             }
/*     */             else {
/* 154 */               System.out.println("You must click either low spin or high spin");
/*     */             }
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 160 */           ii++;
/* 161 */           if (ii >= nIons) { test0 = false;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 167 */     if ((!test1) && 
/* 168 */       (spinSet)) {
/* 169 */       test0 = true;
/* 170 */       ii = 0;
/* 171 */       while (test0) {
/* 172 */         if (compareBare(ion, ii)) {
/* 173 */           test0 = false;
/* 174 */           test1 = true;
/* 175 */           radius = radii[ii] * 1.0E-12D;
/* 176 */           System.out.println(ion + " does not have low and high spin states listed");
/* 177 */           System.out.println("Single availabe listed radius was used");
/*     */         }
/*     */         else {
/* 180 */           ii++;
/* 181 */           if (ii >= nIons) { test0 = false;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 188 */     if (!test1) {
/* 189 */       System.out.println("Class: IonicRadii\nMethod: radius\n" + fullIon + " was not found in the lists of non-hydrated radii");
/* 190 */       System.out.println("0.0D returned");
/*     */     }
/* 192 */     spinSet = false;
/* 193 */     return radius;
/*     */   }
/*     */   
/*     */   public static boolean compareBare(String ion, int ii)
/*     */   {
/* 198 */     boolean test = false;
/* 199 */     if ((ion.equals(ions1[ii])) || (ion.equals(ions2[ii])) || (ion.equals(ions3[ii])) || (ion.equals(ions4[ii])) || (ion.equals(ions5[ii])) || (ion.equals(ions6[ii]))) {
/* 200 */       test = true;
/*     */     }
/* 202 */     return test;
/*     */   }
/*     */   
/*     */   public static boolean compareSubstringBare(String ion, int ii)
/*     */   {
/* 207 */     boolean test = false;
/* 208 */     if ((ions1[ii].indexOf(ion) > -1) || (ions2[ii].indexOf(ion) > -1) || (ions3[ii].indexOf(ion) > -1) || (ions4[ii].indexOf(ion) > -1) || (ions5[ii].indexOf(ion) > -1) || (ions6[ii].indexOf(ion) > -1)) {
/* 209 */       test = true;
/*     */     }
/* 211 */     return test;
/*     */   }
/*     */   
/*     */   public static double hydratedRadius(String ion)
/*     */   {
/* 216 */     ion = ion.trim();
/*     */     
/* 218 */     boolean test0 = true;
/* 219 */     boolean test1 = false;
/* 220 */     int i = 0;
/* 221 */     double radius = 0.0D;
/*     */     
/*     */ 
/* 224 */     while (test0) {
/* 225 */       if (compareHydrated(ion, i)) {
/* 226 */         test0 = false;
/* 227 */         test1 = true;
/* 228 */         radius = hydratedRadii[i] * 1.0E-12D;
/*     */       }
/*     */       else {
/* 231 */         i++;
/* 232 */         if (i >= nHydratedIons) { test0 = false;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 237 */     if (!test1) {
/* 238 */       System.out.println("Class: IonicRadii\nMethod: hydratedRadius\n" + ion + " was not found in the lists of hydrated radii");
/* 239 */       System.out.println("0.0D returned");
/*     */     }
/*     */     
/* 242 */     return radius;
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean compareHydrated(String ion, int ii)
/*     */   {
/* 248 */     boolean test = false;
/* 249 */     if ((ion.equals(hydratedIons1[ii])) || (ion.equals(hydratedIons2[ii])) || (ion.equals(hydratedIons3[ii])) || (ion.equals(hydratedIons4[ii])) || (ion.equals(hydratedIons5[ii])) || (ion.equals(hydratedIons6[ii]))) {
/* 250 */       test = true;
/*     */     }
/* 252 */     return test;
/*     */   }
/*     */   
/*     */   public static boolean compareSubstringHydrated(String ion, int ii)
/*     */   {
/* 257 */     boolean test = false;
/* 258 */     if ((hydratedIons1[ii].indexOf(ion) > -1) || (hydratedIons2[ii].indexOf(ion) > -1) || (hydratedIons3[ii].indexOf(ion) > -1) || (hydratedIons4[ii].indexOf(ion) > -1) || (hydratedIons5[ii].indexOf(ion) > -1) || (hydratedIons6[ii].indexOf(ion) > -1)) {
/* 259 */       test = true;
/*     */     }
/* 261 */     return test;
/*     */   }
/*     */   
/*     */   public static int charge(String ion)
/*     */   {
/* 266 */     ion = ion.trim();
/*     */     
/* 268 */     boolean test0 = true;
/* 269 */     boolean test1 = false;
/* 270 */     int i = 0;
/* 271 */     int charge = 0;
/*     */     
/*     */ 
/* 274 */     while (test0) {
/* 275 */       if (compareBare(ion, i)) {
/* 276 */         test0 = false;
/* 277 */         test1 = true;
/* 278 */         charge = ionCharge[i];
/*     */       }
/*     */       else {
/* 281 */         i++;
/* 282 */         if (i >= nIons) { test0 = false;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 287 */     if (!test1) {
/* 288 */       System.out.println("Class: IonicRadii\nMethod: charge\n" + ion + " was not found in the lists of non-hydrated ions");
/* 289 */       System.out.println("0 returned");
/*     */     }
/*     */     
/* 292 */     return charge;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/physprop/IonicRadii.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */