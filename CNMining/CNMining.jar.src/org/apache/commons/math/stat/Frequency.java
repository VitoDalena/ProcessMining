/*     */ package org.apache.commons.math.stat;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Frequency
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3845586908418844111L;
/*  47 */   private TreeMap freqTable = null;
/*     */   
/*     */ 
/*     */ 
/*     */   public Frequency()
/*     */   {
/*  53 */     this.freqTable = new TreeMap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Frequency(Comparator comparator)
/*     */   {
/*  62 */     this.freqTable = new TreeMap(comparator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/*  72 */     NumberFormat nf = NumberFormat.getPercentInstance();
/*  73 */     StringBuffer outBuffer = new StringBuffer();
/*  74 */     outBuffer.append("Value \t Freq. \t Pct. \t Cum Pct. \n");
/*  75 */     Iterator iter = this.freqTable.keySet().iterator();
/*  76 */     while (iter.hasNext()) {
/*  77 */       Object value = iter.next();
/*  78 */       outBuffer.append(value);
/*  79 */       outBuffer.append('\t');
/*  80 */       outBuffer.append(getCount(value));
/*  81 */       outBuffer.append('\t');
/*  82 */       outBuffer.append(nf.format(getPct(value)));
/*  83 */       outBuffer.append('\t');
/*  84 */       outBuffer.append(nf.format(getCumPct(value)));
/*  85 */       outBuffer.append('\n');
/*     */     }
/*  87 */     return outBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addValue(Object v)
/*     */   {
/*  97 */     Object obj = v;
/*  98 */     if ((v instanceof Integer)) {
/*  99 */       obj = new Long(((Integer)v).longValue());
/*     */     }
/*     */     try {
/* 102 */       Long count = (Long)this.freqTable.get(obj);
/* 103 */       if (count == null) {
/* 104 */         this.freqTable.put(obj, new Long(1L));
/*     */       } else {
/* 106 */         this.freqTable.put(obj, new Long(count.longValue() + 1L));
/*     */       }
/*     */     }
/*     */     catch (ClassCastException ex) {
/* 110 */       throw new IllegalArgumentException("Value not comparable to existing values.");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addValue(int v)
/*     */   {
/* 120 */     addValue(new Long(v));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addValue(Integer v)
/*     */   {
/* 129 */     addValue(new Long(v.longValue()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addValue(long v)
/*     */   {
/* 138 */     addValue(new Long(v));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addValue(char v)
/*     */   {
/* 147 */     addValue(new Character(v));
/*     */   }
/*     */   
/*     */   public void clear()
/*     */   {
/* 152 */     this.freqTable.clear();
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
/*     */   public Iterator valuesIterator()
/*     */   {
/* 165 */     return this.freqTable.keySet().iterator();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getSumFreq()
/*     */   {
/* 176 */     long result = 0L;
/* 177 */     Iterator iterator = this.freqTable.values().iterator();
/* 178 */     while (iterator.hasNext()) {
/* 179 */       result += ((Long)iterator.next()).longValue();
/*     */     }
/* 181 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getCount(Object v)
/*     */   {
/* 191 */     if ((v instanceof Integer)) {
/* 192 */       return getCount(((Integer)v).longValue());
/*     */     }
/* 194 */     long result = 0L;
/*     */     try {
/* 196 */       Long count = (Long)this.freqTable.get(v);
/* 197 */       if (count != null) {
/* 198 */         result = count.longValue();
/*     */       }
/*     */     }
/*     */     catch (ClassCastException ex) {}
/*     */     
/* 203 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getCount(int v)
/*     */   {
/* 213 */     return getCount(new Long(v));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getCount(long v)
/*     */   {
/* 223 */     return getCount(new Long(v));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getCount(char v)
/*     */   {
/* 233 */     return getCount(new Character(v));
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
/*     */   public double getPct(Object v)
/*     */   {
/* 248 */     if (getSumFreq() == 0L) {
/* 249 */       return NaN.0D;
/*     */     }
/* 251 */     return getCount(v) / getSumFreq();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getPct(int v)
/*     */   {
/* 262 */     return getPct(new Long(v));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getPct(long v)
/*     */   {
/* 273 */     return getPct(new Long(v));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getPct(char v)
/*     */   {
/* 284 */     return getPct(new Character(v));
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
/*     */   public long getCumFreq(Object v)
/*     */   {
/* 298 */     if (getSumFreq() == 0L) {
/* 299 */       return 0L;
/*     */     }
/* 301 */     if ((v instanceof Integer)) {
/* 302 */       return getCumFreq(((Integer)v).longValue());
/*     */     }
/* 304 */     Comparator c = this.freqTable.comparator();
/* 305 */     if (c == null) {
/* 306 */       c = new NaturalComparator(null);
/*     */     }
/* 308 */     long result = 0L;
/*     */     try
/*     */     {
/* 311 */       Long value = (Long)this.freqTable.get(v);
/* 312 */       if (value != null) {
/* 313 */         result = value.longValue();
/*     */       }
/*     */     } catch (ClassCastException ex) {
/* 316 */       return result;
/*     */     }
/*     */     
/* 319 */     if (c.compare(v, this.freqTable.firstKey()) < 0) {
/* 320 */       return 0L;
/*     */     }
/*     */     
/* 323 */     if (c.compare(v, this.freqTable.lastKey()) >= 0) {
/* 324 */       return getSumFreq();
/*     */     }
/*     */     
/* 327 */     Iterator values = valuesIterator();
/* 328 */     while (values.hasNext()) {
/* 329 */       Object nextValue = values.next();
/* 330 */       if (c.compare(v, nextValue) > 0) {
/* 331 */         result += getCount(nextValue);
/*     */       } else {
/* 333 */         return result;
/*     */       }
/*     */     }
/* 336 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getCumFreq(int v)
/*     */   {
/* 348 */     return getCumFreq(new Long(v));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getCumFreq(long v)
/*     */   {
/* 360 */     return getCumFreq(new Long(v));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getCumFreq(char v)
/*     */   {
/* 372 */     return getCumFreq(new Character(v));
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
/*     */   public double getCumPct(Object v)
/*     */   {
/* 389 */     if (getSumFreq() == 0L) {
/* 390 */       return NaN.0D;
/*     */     }
/* 392 */     return getCumFreq(v) / getSumFreq();
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
/*     */   public double getCumPct(int v)
/*     */   {
/* 405 */     return getCumPct(new Long(v));
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
/*     */   public double getCumPct(long v)
/*     */   {
/* 418 */     return getCumPct(new Long(v));
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
/*     */   public double getCumPct(char v)
/*     */   {
/* 431 */     return getCumPct(new Character(v));
/*     */   }
/*     */   
/*     */   private static class NaturalComparator implements Comparator, Serializable {
/*     */     private static final long serialVersionUID = -3852193713161395148L;
/*     */     
/*     */     NaturalComparator(Frequency.1 x0) {
/* 438 */       this();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public int compare(Object o1, Object o2)
/*     */     {
/* 457 */       return ((Comparable)o1).compareTo(o2);
/*     */     }
/*     */     
/*     */     private NaturalComparator() {}
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/Frequency.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */