/*     */ package org.deckfour.xes.model.impl;
/*     */ 
/*     */ import java.util.Date;
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeTimestamp;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XAttributeTimestampImpl
/*     */   extends XAttributeImpl
/*     */   implements XAttributeTimestamp
/*     */ {
/*     */   private Date value;
/*     */   
/*     */   public XAttributeTimestampImpl(String key, Date value)
/*     */   {
/*  69 */     this(key, value, null);
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
/*     */   public XAttributeTimestampImpl(String key, Date value, XExtension extension)
/*     */   {
/*  83 */     super(key, extension);
/*  84 */     setValue(value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XAttributeTimestampImpl(String key, long millis)
/*     */   {
/*  96 */     this(key, millis, null);
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
/*     */   public XAttributeTimestampImpl(String key, long millis, XExtension extension)
/*     */   {
/* 110 */     this(key, new Date(millis), extension);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getValue()
/*     */   {
/* 119 */     return this.value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getValueMillis()
/*     */   {
/* 128 */     return this.value.getTime();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValue(Date value)
/*     */   {
/* 137 */     if (value == null) {
/* 138 */       throw new NullPointerException("No null value allowed in timestamp attribute!");
/*     */     }
/*     */     
/* 141 */     this.value = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValueMillis(long value)
/*     */   {
/* 150 */     this.value.setTime(value);
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public String toString()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: getstatic 13	org/deckfour/xes/model/impl/XAttributeTimestampImpl:FORMATTER	Lorg/deckfour/xes/model/impl/XsDateTimeFormat;
/*     */     //   3: dup
/*     */     //   4: astore_1
/*     */     //   5: monitorenter
/*     */     //   6: getstatic 13	org/deckfour/xes/model/impl/XAttributeTimestampImpl:FORMATTER	Lorg/deckfour/xes/model/impl/XsDateTimeFormat;
/*     */     //   9: aload_0
/*     */     //   10: getfield 7	org/deckfour/xes/model/impl/XAttributeTimestampImpl:value	Ljava/util/Date;
/*     */     //   13: invokevirtual 14	org/deckfour/xes/model/impl/XsDateTimeFormat:format	(Ljava/lang/Object;)Ljava/lang/String;
/*     */     //   16: aload_1
/*     */     //   17: monitorexit
/*     */     //   18: areturn
/*     */     //   19: astore_2
/*     */     //   20: aload_1
/*     */     //   21: monitorexit
/*     */     //   22: aload_2
/*     */     //   23: athrow
/*     */     // Line number table:
/*     */     //   Java source line #160	-> byte code offset #0
/*     */     //   Java source line #161	-> byte code offset #6
/*     */     //   Java source line #162	-> byte code offset #19
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	24	0	this	XAttributeTimestampImpl
/*     */     //   4	17	1	Ljava/lang/Object;	Object
/*     */     //   19	4	2	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   6	18	19	finally
/*     */     //   19	22	19	finally
/*     */   }
/*     */   
/*     */   public Object clone()
/*     */   {
/* 166 */     XAttributeTimestampImpl clone = (XAttributeTimestampImpl)super.clone();
/* 167 */     clone.value = new Date(clone.value.getTime());
/* 168 */     return clone;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 172 */     if ((obj instanceof XAttributeTimestamp)) {
/* 173 */       XAttributeTimestamp other = (XAttributeTimestamp)obj;
/* 174 */       return (super.equals(other)) && (this.value.equals(other.getValue()));
/*     */     }
/*     */     
/* 177 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int compareTo(XAttribute other)
/*     */   {
/* 183 */     if (!(other instanceof XAttributeTimestamp)) {
/* 184 */       throw new ClassCastException();
/*     */     }
/* 186 */     int result = super.compareTo(other);
/* 187 */     if (result != 0) {
/* 188 */       return result;
/*     */     }
/* 190 */     return this.value.compareTo(((XAttributeTimestamp)other).getValue());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/impl/XAttributeTimestampImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */