/*     */ package com.thoughtworks.xstream.core.util;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ConversionException;
/*     */ import com.thoughtworks.xstream.converters.DataHolder;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.ObjectOutputStream.PutField;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CustomObjectOutputStream
/*     */   extends ObjectOutputStream
/*     */ {
/*  24 */   private FastStack callbacks = new FastStack(1);
/*  25 */   private FastStack customFields = new FastStack(1);
/*     */   
/*  27 */   private static final String DATA_HOLDER_KEY = CustomObjectOutputStream.class.getName();
/*     */   
/*     */   public static synchronized CustomObjectOutputStream getInstance(DataHolder whereFrom, StreamCallback callback) {
/*     */     try {
/*  31 */       CustomObjectOutputStream result = (CustomObjectOutputStream)whereFrom.get(DATA_HOLDER_KEY);
/*  32 */       if (result == null) {
/*  33 */         result = new CustomObjectOutputStream(callback);
/*  34 */         whereFrom.put(DATA_HOLDER_KEY, result);
/*     */       } else {
/*  36 */         result.pushCallback(callback);
/*     */       }
/*  38 */       return result;
/*     */     } catch (IOException e) {
/*  40 */       throw new ConversionException("Cannot create CustomObjectStream", e);
/*     */     }
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
/*     */   public CustomObjectOutputStream(StreamCallback callback)
/*     */     throws IOException, SecurityException
/*     */   {
/*  59 */     this.callbacks.push(callback);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void pushCallback(StreamCallback callback)
/*     */   {
/*  66 */     this.callbacks.push(callback);
/*     */   }
/*     */   
/*     */   public StreamCallback popCallback() {
/*  70 */     return (StreamCallback)this.callbacks.pop();
/*     */   }
/*     */   
/*     */   public StreamCallback peekCallback() {
/*  74 */     return (StreamCallback)this.callbacks.peek();
/*     */   }
/*     */   
/*     */   public void defaultWriteObject()
/*     */     throws IOException
/*     */   {
/*  80 */     peekCallback().defaultWriteObject();
/*     */   }
/*     */   
/*     */   protected void writeObjectOverride(Object obj) throws IOException {
/*  84 */     peekCallback().writeToStream(obj);
/*     */   }
/*     */   
/*     */   public void writeBoolean(boolean val) throws IOException {
/*  88 */     peekCallback().writeToStream(val ? Boolean.TRUE : Boolean.FALSE);
/*     */   }
/*     */   
/*     */   public void writeByte(int val) throws IOException {
/*  92 */     peekCallback().writeToStream(new Byte((byte)val));
/*     */   }
/*     */   
/*     */   public void writeInt(int val) throws IOException {
/*  96 */     peekCallback().writeToStream(new Integer(val));
/*     */   }
/*     */   
/*     */   public void writeChar(int val) throws IOException {
/* 100 */     peekCallback().writeToStream(new Character((char)val));
/*     */   }
/*     */   
/*     */   public void writeDouble(double val) throws IOException {
/* 104 */     peekCallback().writeToStream(new Double(val));
/*     */   }
/*     */   
/*     */   public void writeFloat(float val) throws IOException {
/* 108 */     peekCallback().writeToStream(new Float(val));
/*     */   }
/*     */   
/*     */   public void writeLong(long val) throws IOException {
/* 112 */     peekCallback().writeToStream(new Long(val));
/*     */   }
/*     */   
/*     */   public void writeShort(int val) throws IOException {
/* 116 */     peekCallback().writeToStream(new Short((short)val));
/*     */   }
/*     */   
/*     */   public void write(byte[] buf) throws IOException {
/* 120 */     peekCallback().writeToStream(buf);
/*     */   }
/*     */   
/*     */   public void writeChars(String str) throws IOException {
/* 124 */     peekCallback().writeToStream(str.toCharArray());
/*     */   }
/*     */   
/*     */   public void writeUTF(String str) throws IOException {
/* 128 */     peekCallback().writeToStream(str);
/*     */   }
/*     */   
/*     */   public void write(int val) throws IOException {
/* 132 */     peekCallback().writeToStream(new Byte((byte)val));
/*     */   }
/*     */   
/*     */   public void write(byte[] buf, int off, int len) throws IOException {
/* 136 */     byte[] b = new byte[len];
/* 137 */     System.arraycopy(buf, off, b, 0, len);
/* 138 */     peekCallback().writeToStream(b);
/*     */   }
/*     */   
/*     */   public void flush() throws IOException {
/* 142 */     peekCallback().flush();
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 146 */     peekCallback().close();
/*     */   }
/*     */   
/*     */   public ObjectOutputStream.PutField putFields() {
/* 150 */     CustomPutField result = new CustomPutField(null);
/* 151 */     this.customFields.push(result);
/* 152 */     return result;
/*     */   }
/*     */   
/*     */   public void writeFields() throws IOException {
/* 156 */     CustomPutField customPutField = (CustomPutField)this.customFields.pop();
/* 157 */     peekCallback().writeFieldsToStream(customPutField.asMap());
/*     */   }
/*     */   
/* 160 */   private class CustomPutField extends ObjectOutputStream.PutField { CustomPutField(CustomObjectOutputStream.1 x1) { this(); }
/*     */     
/* 162 */     private final Map fields = new OrderRetainingMap();
/*     */     
/*     */     public Map asMap() {
/* 165 */       return this.fields;
/*     */     }
/*     */     
/*     */     public void write(ObjectOutput out) throws IOException {
/* 169 */       CustomObjectOutputStream.this.peekCallback().writeToStream(asMap());
/*     */     }
/*     */     
/*     */     public void put(String name, Object val) {
/* 173 */       this.fields.put(name, val);
/*     */     }
/*     */     
/*     */     public void put(String name, byte val) {
/* 177 */       put(name, new Byte(val));
/*     */     }
/*     */     
/*     */     public void put(String name, char val) {
/* 181 */       put(name, new Character(val));
/*     */     }
/*     */     
/*     */     public void put(String name, double val) {
/* 185 */       put(name, new Double(val));
/*     */     }
/*     */     
/*     */     public void put(String name, float val) {
/* 189 */       put(name, new Float(val));
/*     */     }
/*     */     
/*     */     public void put(String name, int val) {
/* 193 */       put(name, new Integer(val));
/*     */     }
/*     */     
/*     */     public void put(String name, long val) {
/* 197 */       put(name, new Long(val));
/*     */     }
/*     */     
/*     */     public void put(String name, short val) {
/* 201 */       put(name, new Short(val));
/*     */     }
/*     */     
/*     */     public void put(String name, boolean val) {
/* 205 */       put(name, val ? Boolean.TRUE : Boolean.FALSE);
/*     */     }
/*     */     
/*     */     private CustomPutField() {}
/*     */   }
/*     */   
/*     */   public void reset()
/*     */   {
/* 213 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void useProtocolVersion(int version) {
/* 217 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void writeBytes(String str) {
/* 221 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void writeUnshared(Object obj) {
/* 225 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public static abstract interface StreamCallback
/*     */   {
/*     */     public abstract void writeToStream(Object paramObject)
/*     */       throws IOException;
/*     */     
/*     */     public abstract void writeFieldsToStream(Map paramMap)
/*     */       throws IOException;
/*     */     
/*     */     public abstract void defaultWriteObject()
/*     */       throws IOException;
/*     */     
/*     */     public abstract void flush()
/*     */       throws IOException;
/*     */     
/*     */     public abstract void close()
/*     */       throws IOException;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/util/CustomObjectOutputStream.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */