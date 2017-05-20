/*     */ package com.thoughtworks.xstream.core.util;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ConversionException;
/*     */ import com.thoughtworks.xstream.converters.DataHolder;
/*     */ import java.io.IOException;
/*     */ import java.io.InvalidObjectException;
/*     */ import java.io.NotActiveException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectInputStream.GetField;
/*     */ import java.io.ObjectInputValidation;
/*     */ import java.io.ObjectStreamClass;
/*     */ import java.io.StreamCorruptedException;
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
/*     */ public class CustomObjectInputStream
/*     */   extends ObjectInputStream
/*     */ {
/*  28 */   private FastStack callbacks = new FastStack(1);
/*     */   
/*     */   private final ClassLoader classLoader;
/*  31 */   private static final String DATA_HOLDER_KEY = CustomObjectInputStream.class.getName();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static synchronized CustomObjectInputStream getInstance(DataHolder whereFrom, StreamCallback callback, ClassLoader classLoaderReference)
/*     */   {
/*     */     try
/*     */     {
/*  43 */       CustomObjectInputStream result = (CustomObjectInputStream)whereFrom.get(DATA_HOLDER_KEY);
/*  44 */       if (result == null) {
/*  45 */         result = new CustomObjectInputStream(callback, classLoaderReference);
/*  46 */         whereFrom.put(DATA_HOLDER_KEY, result);
/*     */       } else {
/*  48 */         result.pushCallback(callback);
/*     */       }
/*  50 */       return result;
/*     */     } catch (IOException e) {
/*  52 */       throw new ConversionException("Cannot create CustomObjectStream", e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CustomObjectInputStream(StreamCallback callback, ClassLoader classLoader)
/*     */     throws IOException, SecurityException
/*     */   {
/*  64 */     this.callbacks.push(callback);
/*  65 */     this.classLoader = classLoader;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void pushCallback(StreamCallback callback)
/*     */   {
/*  72 */     this.callbacks.push(callback);
/*     */   }
/*     */   
/*     */   public StreamCallback popCallback() {
/*  76 */     return (StreamCallback)this.callbacks.pop();
/*     */   }
/*     */   
/*     */   public StreamCallback peekCallback() {
/*  80 */     return (StreamCallback)this.callbacks.peek();
/*     */   }
/*     */   
/*     */   protected Class resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException
/*     */   {
/*  85 */     if (this.classLoader == null) {
/*  86 */       return super.resolveClass(desc);
/*     */     }
/*  88 */     return Class.forName(desc.getName(), false, this.classLoader);
/*     */   }
/*     */   
/*     */   public void defaultReadObject() throws IOException
/*     */   {
/*  93 */     peekCallback().defaultReadObject();
/*     */   }
/*     */   
/*     */   protected Object readObjectOverride() throws IOException {
/*  97 */     return peekCallback().readFromStream();
/*     */   }
/*     */   
/*     */   public Object readUnshared() throws IOException, ClassNotFoundException {
/* 101 */     return readObject();
/*     */   }
/*     */   
/*     */   public boolean readBoolean() throws IOException {
/* 105 */     return ((Boolean)peekCallback().readFromStream()).booleanValue();
/*     */   }
/*     */   
/*     */   public byte readByte() throws IOException {
/* 109 */     return ((Byte)peekCallback().readFromStream()).byteValue();
/*     */   }
/*     */   
/*     */   public int readUnsignedByte() throws IOException {
/* 113 */     int b = ((Byte)peekCallback().readFromStream()).byteValue();
/* 114 */     if (b < 0) {
/* 115 */       b += 127;
/*     */     }
/* 117 */     return b;
/*     */   }
/*     */   
/*     */   public int readInt() throws IOException {
/* 121 */     return ((Integer)peekCallback().readFromStream()).intValue();
/*     */   }
/*     */   
/*     */   public char readChar() throws IOException {
/* 125 */     return ((Character)peekCallback().readFromStream()).charValue();
/*     */   }
/*     */   
/*     */   public float readFloat() throws IOException {
/* 129 */     return ((Float)peekCallback().readFromStream()).floatValue();
/*     */   }
/*     */   
/*     */   public double readDouble() throws IOException {
/* 133 */     return ((Double)peekCallback().readFromStream()).doubleValue();
/*     */   }
/*     */   
/*     */   public long readLong() throws IOException {
/* 137 */     return ((Long)peekCallback().readFromStream()).longValue();
/*     */   }
/*     */   
/*     */   public short readShort() throws IOException {
/* 141 */     return ((Short)peekCallback().readFromStream()).shortValue();
/*     */   }
/*     */   
/*     */   public int readUnsignedShort() throws IOException {
/* 145 */     int b = ((Short)peekCallback().readFromStream()).shortValue();
/* 146 */     if (b < 0) {
/* 147 */       b += 32767;
/*     */     }
/* 149 */     return b;
/*     */   }
/*     */   
/*     */   public String readUTF() throws IOException {
/* 153 */     return (String)peekCallback().readFromStream();
/*     */   }
/*     */   
/*     */   public void readFully(byte[] buf) throws IOException {
/* 157 */     readFully(buf, 0, buf.length);
/*     */   }
/*     */   
/*     */   public void readFully(byte[] buf, int off, int len) throws IOException {
/* 161 */     byte[] b = (byte[])peekCallback().readFromStream();
/* 162 */     System.arraycopy(b, 0, buf, off, len);
/*     */   }
/*     */   
/*     */   public int read() throws IOException {
/* 166 */     return readUnsignedByte();
/*     */   }
/*     */   
/*     */   public int read(byte[] buf, int off, int len) throws IOException {
/* 170 */     byte[] b = (byte[])peekCallback().readFromStream();
/* 171 */     if (b.length != len) {
/* 172 */       throw new StreamCorruptedException("Expected " + len + " bytes from stream, got " + b.length);
/*     */     }
/* 174 */     System.arraycopy(b, 0, buf, off, len);
/* 175 */     return len;
/*     */   }
/*     */   
/*     */   public int read(byte[] b) throws IOException {
/* 179 */     return read(b, 0, b.length);
/*     */   }
/*     */   
/*     */   public ObjectInputStream.GetField readFields() throws IOException {
/* 183 */     return new CustomGetField(peekCallback().readFieldsFromStream());
/*     */   }
/*     */   
/*     */   private class CustomGetField extends ObjectInputStream.GetField
/*     */   {
/*     */     private Map fields;
/*     */     
/*     */     public CustomGetField(Map fields) {
/* 191 */       this.fields = fields;
/*     */     }
/*     */     
/*     */     public ObjectStreamClass getObjectStreamClass() {
/* 195 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     private Object get(String name) {
/* 199 */       return this.fields.get(name);
/*     */     }
/*     */     
/*     */     public boolean defaulted(String name) {
/* 203 */       return !this.fields.containsKey(name);
/*     */     }
/*     */     
/*     */     public byte get(String name, byte val) {
/* 207 */       return defaulted(name) ? val : ((Byte)get(name)).byteValue();
/*     */     }
/*     */     
/*     */     public char get(String name, char val) {
/* 211 */       return defaulted(name) ? val : ((Character)get(name)).charValue();
/*     */     }
/*     */     
/*     */     public double get(String name, double val) {
/* 215 */       return defaulted(name) ? val : ((Double)get(name)).doubleValue();
/*     */     }
/*     */     
/*     */     public float get(String name, float val) {
/* 219 */       return defaulted(name) ? val : ((Float)get(name)).floatValue();
/*     */     }
/*     */     
/*     */     public int get(String name, int val) {
/* 223 */       return defaulted(name) ? val : ((Integer)get(name)).intValue();
/*     */     }
/*     */     
/*     */     public long get(String name, long val) {
/* 227 */       return defaulted(name) ? val : ((Long)get(name)).longValue();
/*     */     }
/*     */     
/*     */     public short get(String name, short val) {
/* 231 */       return defaulted(name) ? val : ((Short)get(name)).shortValue();
/*     */     }
/*     */     
/*     */     public boolean get(String name, boolean val) {
/* 235 */       return defaulted(name) ? val : ((Boolean)get(name)).booleanValue();
/*     */     }
/*     */     
/*     */     public Object get(String name, Object val) {
/* 239 */       return defaulted(name) ? val : get(name);
/*     */     }
/*     */   }
/*     */   
/*     */   public void registerValidation(ObjectInputValidation validation, int priority) throws NotActiveException, InvalidObjectException
/*     */   {
/* 245 */     peekCallback().registerValidation(validation, priority);
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 249 */     peekCallback().close();
/*     */   }
/*     */   
/*     */ 
/*     */   public int available()
/*     */   {
/* 255 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public String readLine() {
/* 259 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public int skipBytes(int len) {
/* 263 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public long skip(long n) {
/* 267 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void mark(int readlimit) {
/* 271 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void reset() {
/* 275 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean markSupported() {
/* 279 */     return false;
/*     */   }
/*     */   
/*     */   public static abstract interface StreamCallback
/*     */   {
/*     */     public abstract Object readFromStream()
/*     */       throws IOException;
/*     */     
/*     */     public abstract Map readFieldsFromStream()
/*     */       throws IOException;
/*     */     
/*     */     public abstract void defaultReadObject()
/*     */       throws IOException;
/*     */     
/*     */     public abstract void registerValidation(ObjectInputValidation paramObjectInputValidation, int paramInt)
/*     */       throws NotActiveException, InvalidObjectException;
/*     */     
/*     */     public abstract void close()
/*     */       throws IOException;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/util/CustomObjectInputStream.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */