/*     */ package com.thoughtworks.xstream.io.json;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ConversionException;
/*     */ import com.thoughtworks.xstream.core.util.FastStack;
/*     */ import com.thoughtworks.xstream.io.AbstractWriter;
/*     */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*     */ import com.thoughtworks.xstream.io.naming.NoNameCoder;
/*     */ import com.thoughtworks.xstream.mapper.Mapper.Null;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractJsonWriter
/*     */   extends AbstractWriter
/*     */ {
/*     */   public static final int DROP_ROOT_MODE = 1;
/*     */   public static final int STRICT_MODE = 2;
/*     */   public static final int EXPLICIT_MODE = 4;
/*     */   
/*     */   public static class Type
/*     */   {
/* 108 */     public static Type NULL = new Type();
/* 109 */     public static Type STRING = new Type();
/* 110 */     public static Type NUMBER = new Type();
/* 111 */     public static Type BOOLEAN = new Type();
/*     */   }
/*     */   
/*     */   private static class Status {
/* 115 */     public static final Status OBJECT = new Status("object");
/* 116 */     public static final Status PROPERTY = new Status("property");
/* 117 */     public static final Status VALUE = new Status("value");
/* 118 */     public static final Status END = new Status("end");
/* 119 */     public static final Status ARRAY = new Status("array");
/* 120 */     public static final Status ELEMENT = new Status("element");
/* 121 */     public static final Status VIRTUAL = new Status("virtual");
/*     */     private final String name;
/*     */     
/*     */     private Status(String name)
/*     */     {
/* 126 */       this.name = name;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 130 */       return this.name;
/*     */     }
/*     */   }
/*     */   
/* 134 */   private static final List NUMBER_TYPES = Arrays.asList(new Class[] { Byte.TYPE, Byte.class, Short.TYPE, Short.class, Integer.TYPE, Integer.class, Long.TYPE, Long.class, Float.TYPE, Float.class, Double.TYPE, Double.class });
/*     */   
/*     */   private int mode;
/*     */   
/* 138 */   private FastStack statusStack = new FastStack(32);
/* 139 */   private FastStack typeStack = new FastStack(16);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractJsonWriter()
/*     */   {
/* 147 */     this(new NoNameCoder());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractJsonWriter(int mode)
/*     */   {
/* 157 */     this(mode, new NoNameCoder());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractJsonWriter(NameCoder nameCoder)
/*     */   {
/* 167 */     this(0, nameCoder);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractJsonWriter(int mode, NameCoder nameCoder)
/*     */   {
/* 178 */     super(nameCoder);
/* 179 */     this.mode = mode;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void startNode(String name, Class clazz)
/*     */   {
/* 186 */     if (this.statusStack.size() == 0) {
/* 187 */       this.statusStack.push(Status.OBJECT);
/* 188 */       if ((this.mode & 0x1) == 0) {
/* 189 */         startObject(name);
/*     */       }
/*     */     } else {
/* 192 */       Class type = (Class)this.typeStack.peek();
/* 193 */       Status status = (Status)this.statusStack.peek();
/* 194 */       if (isArray(type)) {
/* 195 */         if ((status == Status.OBJECT) || (status == Status.VIRTUAL) || (status == Status.PROPERTY) || (status == Status.VALUE))
/*     */         {
/*     */ 
/*     */ 
/* 199 */           if ((status == Status.PROPERTY) || (status == Status.VIRTUAL)) {
/* 200 */             nextElement();
/* 201 */             addLabel("$");
/*     */           }
/* 203 */           if (status == Status.VALUE) {
/* 204 */             this.statusStack.replaceSilently(Status.ARRAY);
/*     */           } else {
/* 206 */             this.statusStack.push(Status.ARRAY);
/*     */           }
/* 208 */           startArray();
/* 209 */           this.statusStack.push(Status.OBJECT);
/* 210 */           if ((this.mode & 0x4) != 0) {
/* 211 */             startObject(name);
/*     */           }
/* 213 */         } else if (status == Status.END) {
/* 214 */           if ((this.mode & 0x4) != 0) {
/* 215 */             endObject();
/*     */           }
/* 217 */           this.statusStack.popSilently();
/* 218 */           status = (Status)this.statusStack.peek();
/* 219 */           if (status == Status.ARRAY) {
/* 220 */             this.statusStack.replaceSilently(Status.ELEMENT);
/* 221 */           } else if (status != Status.ELEMENT) {
/* 222 */             throw new IllegalStateException("Cannot add new array element");
/*     */           }
/* 224 */           nextElement();
/* 225 */           this.statusStack.push(Status.OBJECT);
/* 226 */           if ((this.mode & 0x4) != 0) {
/* 227 */             startObject(name);
/*     */           }
/*     */         } else {
/* 230 */           throw new IllegalStateException("Cannot start new array element");
/*     */         }
/* 232 */       } else if (status == Status.VALUE) {
/* 233 */         this.statusStack.replaceSilently(Status.OBJECT);
/* 234 */         startObject(name);
/* 235 */       } else if ((status == Status.PROPERTY) || (status == Status.END) || (status == Status.VIRTUAL))
/*     */       {
/*     */ 
/* 238 */         this.statusStack.replaceSilently(Status.PROPERTY);
/* 239 */         nextElement();
/* 240 */         addLabel(name);
/*     */       } else {
/* 242 */         throw new IllegalStateException("Cannot start new element");
/*     */       }
/*     */     }
/* 245 */     this.statusStack.push(Status.VALUE);
/* 246 */     this.typeStack.push(clazz == null ? String.class : clazz);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void startNode(String name)
/*     */   {
/* 253 */     startNode(name, String.class);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void addAttribute(String name, String value)
/*     */   {
/* 260 */     Class type = (Class)this.typeStack.peek();
/* 261 */     if (((this.mode & 0x4) != 0) || (!isArray(type))) {
/* 262 */       Status status = (Status)this.statusStack.peek();
/* 263 */       if (status == Status.VALUE) {
/* 264 */         this.statusStack.replaceSilently(Status.VIRTUAL);
/* 265 */         startObject("@" + name);
/* 266 */       } else if ((status == Status.PROPERTY) || (status == Status.VIRTUAL)) {
/* 267 */         nextElement();
/* 268 */         addLabel("@" + name);
/*     */       } else {
/* 270 */         throw new IllegalStateException("Cannot add attribute");
/*     */       }
/* 272 */       addValue(value, Type.STRING);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setValue(String text)
/*     */   {
/* 280 */     Status status = (Status)this.statusStack.peek();
/* 281 */     Class type = (Class)this.typeStack.peek();
/* 282 */     if ((status == Status.PROPERTY) || (status == Status.VIRTUAL)) {
/* 283 */       nextElement();
/* 284 */       addLabel("$");
/* 285 */       this.statusStack.replaceSilently(Status.END);
/* 286 */     } else if (status == Status.VALUE) {
/* 287 */       this.statusStack.popSilently();
/* 288 */       this.typeStack.popSilently();
/* 289 */       if (((this.mode & 0x2) != 0) && (this.typeStack.size() == 0)) {
/* 290 */         throw new ConversionException("Single value cannot be root element");
/*     */       }
/*     */     } else {
/* 293 */       throw new IllegalStateException("Cannot set value");
/*     */     }
/* 295 */     if (((type == Character.class) || (type == Character.TYPE)) && ("".equals(text))) {
/* 296 */       text = "\000";
/*     */     }
/* 298 */     addValue(text, getType(type));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void endNode()
/*     */   {
/* 305 */     Status status = (Status)this.statusStack.peek();
/* 306 */     if (status == Status.END) {
/* 307 */       this.statusStack.popSilently();
/* 308 */       Class type = (Class)this.typeStack.pop();
/* 309 */       status = (Status)this.statusStack.peek();
/* 310 */       if (isArray(type)) {
/* 311 */         if ((this.mode & 0x4) != 0) {
/* 312 */           endObject();
/*     */         }
/* 314 */         if ((status == Status.ELEMENT) || (status == Status.ARRAY)) {
/* 315 */           endArray();
/* 316 */           this.statusStack.popSilently();
/* 317 */           status = (Status)this.statusStack.peek();
/*     */         } else {
/* 319 */           throw new IllegalStateException("Cannot end array");
/*     */         }
/* 321 */         if (status == Status.VIRTUAL) {
/* 322 */           this.statusStack.popSilently();
/* 323 */           endObject();
/* 324 */           status = (Status)this.statusStack.peek();
/*     */         }
/* 326 */       } else { if ((status != Status.OBJECT) && (status != Status.PROPERTY)) {
/* 327 */           throw new IllegalStateException("Cannot end object");
/*     */         }
/* 329 */         endObject();
/*     */       }
/* 331 */     } else if ((status == Status.VALUE) || (status == Status.VIRTUAL)) {
/* 332 */       this.statusStack.popSilently();
/* 333 */       Class type = (Class)this.typeStack.pop();
/* 334 */       if ((status == Status.VIRTUAL) && ((this.mode & 0x4) != 0)) {
/* 335 */         nextElement();
/* 336 */         addLabel("$");
/*     */       }
/* 338 */       if (isArray(type)) {
/* 339 */         startArray();
/* 340 */         endArray();
/* 341 */       } else if (((this.mode & 0x4) != 0) || (status == Status.VALUE)) {
/* 342 */         Type jsonType = getType(type);
/* 343 */         if (jsonType != Type.NULL) {
/* 344 */           startObject(null);
/* 345 */           endObject();
/*     */         } else {
/* 347 */           addValue("null", jsonType);
/*     */         }
/*     */       }
/* 350 */       if (status == Status.VIRTUAL) {
/* 351 */         endObject();
/*     */       }
/* 353 */       status = (Status)this.statusStack.peek();
/*     */     }
/* 355 */     if ((status == Status.PROPERTY) || (status == Status.OBJECT)) {
/* 356 */       if (this.typeStack.size() == 0) {
/* 357 */         status = (Status)this.statusStack.pop();
/* 358 */         if (status != Status.OBJECT) {
/* 359 */           throw new IllegalStateException("Cannot end object");
/*     */         }
/* 361 */         if ((this.mode & 0x1) == 0) {
/* 362 */           endObject();
/*     */         }
/*     */       } else {
/* 365 */         this.statusStack.replaceSilently(Status.END);
/*     */       }
/*     */     } else {
/* 368 */       throw new IllegalStateException("Cannot end object");
/*     */     }
/*     */   }
/*     */   
/*     */   private Type getType(Class clazz) {
/* 373 */     return NUMBER_TYPES.contains(clazz) ? Type.NUMBER : (clazz == Boolean.class) || (clazz == Boolean.TYPE) ? Type.BOOLEAN : (clazz == Mapper.Null.class) || (clazz == null) ? Type.NULL : Type.STRING;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private boolean isArray(Class clazz)
/*     */   {
/* 380 */     return (clazz.isArray()) || (Collection.class.isAssignableFrom(clazz)) || (Map.class.isAssignableFrom(clazz)) || (((this.mode & 0x4) == 0) && (Map.Entry.class.isAssignableFrom(clazz)));
/*     */   }
/*     */   
/*     */   protected abstract void startObject(String paramString);
/*     */   
/*     */   protected abstract void addLabel(String paramString);
/*     */   
/*     */   protected abstract void addValue(String paramString, Type paramType);
/*     */   
/*     */   protected abstract void startArray();
/*     */   
/*     */   protected abstract void nextElement();
/*     */   
/*     */   protected abstract void endArray();
/*     */   
/*     */   protected abstract void endObject();
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/json/AbstractJsonWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */