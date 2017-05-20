/*     */ package com.thoughtworks.xstream.io.binary;
/*     */ 
/*     */ import com.thoughtworks.xstream.io.StreamException;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Token
/*     */ {
/*     */   private static final byte TYPE_MASK = 7;
/*     */   public static final byte TYPE_VERSION = 1;
/*     */   public static final byte TYPE_MAP_ID_TO_VALUE = 2;
/*     */   public static final byte TYPE_START_NODE = 3;
/*     */   public static final byte TYPE_END_NODE = 4;
/*     */   public static final byte TYPE_ATTRIBUTE = 5;
/*     */   public static final byte TYPE_VALUE = 6;
/*     */   private static final byte ID_MASK = 56;
/*     */   private static final byte ID_ONE_BYTE = 8;
/*     */   private static final byte ID_TWO_BYTES = 16;
/*     */   private static final byte ID_FOUR_BYTES = 24;
/*     */   private static final byte ID_EIGHT_BYTES = 32;
/*     */   private static final String ID_SPLITTED = "\000‡\000";
/*     */   private static final int MAX_UTF8_LENGTH = 65535;
/*     */   private final byte type;
/*  57 */   protected long id = -1L;
/*     */   protected String value;
/*     */   
/*     */   public Token(byte type) {
/*  61 */     this.type = type;
/*     */   }
/*     */   
/*     */   public byte getType() {
/*  65 */     return this.type;
/*     */   }
/*     */   
/*     */   public long getId() {
/*  69 */     return this.id;
/*     */   }
/*     */   
/*     */   public String getValue() {
/*  73 */     return this.value;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  77 */     return getClass().getName() + " [id=" + this.id + ", value='" + this.value + "']";
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  81 */     if (this == o) return true;
/*  82 */     if ((o == null) || (getClass() != o.getClass())) { return false;
/*     */     }
/*  84 */     Token token = (Token)o;
/*     */     
/*  86 */     if (this.id != token.id) return false;
/*  87 */     if (this.type != token.type) return false;
/*  88 */     return this.value != null ? this.value.equals(token.value) : token.value == null;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/*  93 */     int result = this.type;
/*  94 */     result = 29 * result + (int)(this.id ^ this.id >>> 32);
/*  95 */     result = 29 * result + (this.value != null ? this.value.hashCode() : 0);
/*  96 */     return result;
/*     */   }
/*     */   
/*     */   public abstract void writeTo(DataOutput paramDataOutput, byte paramByte) throws IOException;
/*     */   
/*     */   public abstract void readFrom(DataInput paramDataInput, byte paramByte) throws IOException;
/*     */   
/*     */   protected void writeId(DataOutput out, long id, byte idType) throws IOException {
/* 104 */     if (id < 0L) {
/* 105 */       throw new IOException("id must not be negative " + id);
/*     */     }
/* 107 */     switch (idType) {
/*     */     case 8: 
/* 109 */       out.writeByte((byte)(int)id + Byte.MIN_VALUE);
/* 110 */       break;
/*     */     case 16: 
/* 112 */       out.writeShort((short)(int)id + Short.MIN_VALUE);
/* 113 */       break;
/*     */     case 24: 
/* 115 */       out.writeInt((int)id + Integer.MIN_VALUE);
/* 116 */       break;
/*     */     case 32: 
/* 118 */       out.writeLong(id + Long.MIN_VALUE);
/* 119 */       break;
/*     */     default: 
/* 121 */       throw new Error("Unknown idType " + idType);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void writeString(DataOutput out, String string) throws IOException {
/* 126 */     byte[] bytes = string.length() > 16383 ? string.getBytes("utf-8") : new byte[0];
/* 127 */     int length = bytes.length;
/* 128 */     if (length <= 65535) {
/* 129 */       out.writeUTF(string);
/*     */     } else {
/* 131 */       out.writeUTF("\000‡\000");
/* 132 */       out.writeInt(bytes.length);
/* 133 */       out.write(bytes);
/*     */     }
/*     */   }
/*     */   
/*     */   protected long readId(DataInput in, byte idType) throws IOException {
/* 138 */     switch (idType) {
/*     */     case 8: 
/* 140 */       return in.readByte() - Byte.MIN_VALUE;
/*     */     case 16: 
/* 142 */       return in.readShort() - Short.MIN_VALUE;
/*     */     case 24: 
/* 144 */       return in.readInt() - Integer.MIN_VALUE;
/*     */     case 32: 
/* 146 */       return in.readLong() - Long.MIN_VALUE;
/*     */     }
/* 148 */     throw new Error("Unknown idType " + idType);
/*     */   }
/*     */   
/*     */   protected String readString(DataInput in) throws IOException
/*     */   {
/* 153 */     String string = in.readUTF();
/* 154 */     if (!"\000‡\000".equals(string)) {
/* 155 */       return string;
/*     */     }
/* 157 */     int size = in.readInt();
/* 158 */     byte[] bytes = new byte[size];
/* 159 */     in.readFully(bytes);
/* 160 */     return new String(bytes, "utf-8");
/*     */   }
/*     */   
/*     */   public static class Formatter
/*     */   {
/*     */     public void write(DataOutput out, Token token) throws IOException {
/* 166 */       long id = token.getId();
/*     */       byte idType;
/* 168 */       byte idType; if (id <= 255L) {
/* 169 */         idType = 8; } else { byte idType;
/* 170 */         if (id <= 65535L) {
/* 171 */           idType = 16; } else { byte idType;
/* 172 */           if (id <= 4294967295L) {
/* 173 */             idType = 24;
/*     */           } else
/* 175 */             idType = 32;
/*     */         } }
/* 177 */       out.write(token.getType() + idType);
/* 178 */       token.writeTo(out, idType);
/*     */     }
/*     */     
/*     */     public Token read(DataInput in) throws IOException {
/* 182 */       byte nextByte = in.readByte();
/* 183 */       byte type = (byte)(nextByte & 0x7);
/* 184 */       byte idType = (byte)(nextByte & 0x38);
/* 185 */       Token token = contructToken(type);
/* 186 */       token.readFrom(in, idType);
/* 187 */       return token;
/*     */     }
/*     */     
/*     */     private Token contructToken(byte type) {
/* 191 */       switch (type) {
/*     */       case 3: 
/* 193 */         return new Token.StartNode();
/*     */       case 2: 
/* 195 */         return new Token.MapIdToValue();
/*     */       case 5: 
/* 197 */         return new Token.Attribute();
/*     */       case 4: 
/* 199 */         return new Token.EndNode();
/*     */       case 6: 
/* 201 */         return new Token.Value();
/*     */       }
/* 203 */       throw new StreamException("Unknown token type");
/*     */     }
/*     */   }
/*     */   
/*     */   public static class MapIdToValue extends Token
/*     */   {
/*     */     public MapIdToValue(long id, String value)
/*     */     {
/* 211 */       super();
/* 212 */       this.id = id;
/* 213 */       this.value = value;
/*     */     }
/*     */     
/*     */     public MapIdToValue() {
/* 217 */       super();
/*     */     }
/*     */     
/*     */     public void writeTo(DataOutput out, byte idType) throws IOException {
/* 221 */       writeId(out, this.id, idType);
/* 222 */       writeString(out, this.value);
/*     */     }
/*     */     
/*     */     public void readFrom(DataInput in, byte idType) throws IOException {
/* 226 */       this.id = readId(in, idType);
/* 227 */       this.value = readString(in);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class StartNode extends Token
/*     */   {
/*     */     public StartNode(long id)
/*     */     {
/* 235 */       super();
/* 236 */       this.id = id;
/*     */     }
/*     */     
/*     */     public StartNode() {
/* 240 */       super();
/*     */     }
/*     */     
/*     */     public void writeTo(DataOutput out, byte idType) throws IOException {
/* 244 */       writeId(out, this.id, idType);
/*     */     }
/*     */     
/*     */     public void readFrom(DataInput in, byte idType) throws IOException {
/* 248 */       this.id = readId(in, idType);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class EndNode extends Token
/*     */   {
/*     */     public EndNode()
/*     */     {
/* 256 */       super();
/*     */     }
/*     */     
/*     */ 
/*     */     public void writeTo(DataOutput out, byte idType) {}
/*     */     
/*     */     public void readFrom(DataInput in, byte idType) {}
/*     */   }
/*     */   
/*     */   public static class Attribute
/*     */     extends Token
/*     */   {
/*     */     public Attribute(long id, String value)
/*     */     {
/* 270 */       super();
/* 271 */       this.id = id;
/* 272 */       this.value = value;
/*     */     }
/*     */     
/*     */     public Attribute() {
/* 276 */       super();
/*     */     }
/*     */     
/*     */     public void writeTo(DataOutput out, byte idType) throws IOException {
/* 280 */       writeId(out, this.id, idType);
/* 281 */       writeString(out, this.value);
/*     */     }
/*     */     
/*     */     public void readFrom(DataInput in, byte idType) throws IOException {
/* 285 */       this.id = readId(in, idType);
/* 286 */       this.value = readString(in);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Value extends Token
/*     */   {
/*     */     public Value(String value)
/*     */     {
/* 294 */       super();
/* 295 */       this.value = value;
/*     */     }
/*     */     
/*     */     public Value() {
/* 299 */       super();
/*     */     }
/*     */     
/*     */     public void writeTo(DataOutput out, byte idType) throws IOException {
/* 303 */       writeString(out, this.value);
/*     */     }
/*     */     
/*     */     public void readFrom(DataInput in, byte idType) throws IOException {
/* 307 */       this.value = readString(in);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/binary/Token.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */