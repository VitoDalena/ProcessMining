package bsh.org.objectweb.asm;

public class ClassWriter
  implements ClassVisitor
{
  static final int CLASS = 7;
  static final int FIELD = 9;
  static final int METH = 10;
  static final int IMETH = 11;
  static final int STR = 8;
  static final int INT = 3;
  static final int FLOAT = 4;
  static final int LONG = 5;
  static final int DOUBLE = 6;
  static final int NAME_TYPE = 12;
  static final int UTF8 = 1;
  private short index = 1;
  private ByteVector pool = new ByteVector();
  private Item[] table = new Item[64];
  private int threshold = (int)(0.75D * this.table.length);
  private int access;
  private int name;
  private int superName;
  private int interfaceCount;
  private int[] interfaces;
  private Item sourceFile;
  private int fieldCount;
  private ByteVector fields;
  private boolean computeMaxs;
  CodeWriter firstMethod;
  CodeWriter lastMethod;
  private int innerClassesCount;
  private ByteVector innerClasses;
  Item key = new Item();
  Item key2 = new Item();
  Item key3 = new Item();
  static final int NOARG_INSN = 0;
  static final int SBYTE_INSN = 1;
  static final int SHORT_INSN = 2;
  static final int VAR_INSN = 3;
  static final int IMPLVAR_INSN = 4;
  static final int TYPE_INSN = 5;
  static final int FIELDORMETH_INSN = 6;
  static final int ITFMETH_INSN = 7;
  static final int LABEL_INSN = 8;
  static final int LABELW_INSN = 9;
  static final int LDC_INSN = 10;
  static final int LDCW_INSN = 11;
  static final int IINC_INSN = 12;
  static final int TABL_INSN = 13;
  static final int LOOK_INSN = 14;
  static final int MANA_INSN = 15;
  static final int WIDE_INSN = 16;
  static byte[] TYPE;
  
  public ClassWriter(boolean paramBoolean)
  {
    this.computeMaxs = paramBoolean;
  }
  
  public void visit(int paramInt, String paramString1, String paramString2, String[] paramArrayOfString, String paramString3)
  {
    this.access = paramInt;
    this.name = newClass(paramString1).index;
    this.superName = (paramString2 == null ? 0 : newClass(paramString2).index);
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0))
    {
      this.interfaceCount = paramArrayOfString.length;
      this.interfaces = new int[this.interfaceCount];
      for (int i = 0; i < this.interfaceCount; i++) {
        this.interfaces[i] = newClass(paramArrayOfString[i]).index;
      }
    }
    if (paramString3 != null)
    {
      newUTF8("SourceFile");
      this.sourceFile = newUTF8(paramString3);
    }
    if ((paramInt & 0x20000) != 0) {
      newUTF8("Deprecated");
    }
  }
  
  public void visitInnerClass(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    if (this.innerClasses == null)
    {
      newUTF8("InnerClasses");
      this.innerClasses = new ByteVector();
    }
    this.innerClassesCount += 1;
    this.innerClasses.put2(paramString1 == null ? 0 : newClass(paramString1).index);
    this.innerClasses.put2(paramString2 == null ? 0 : newClass(paramString2).index);
    this.innerClasses.put2(paramString3 == null ? 0 : newUTF8(paramString3).index);
    this.innerClasses.put2(paramInt);
  }
  
  public void visitField(int paramInt, String paramString1, String paramString2, Object paramObject)
  {
    this.fieldCount += 1;
    if (this.fields == null) {
      this.fields = new ByteVector();
    }
    this.fields.put2(paramInt).put2(newUTF8(paramString1).index).put2(newUTF8(paramString2).index);
    int i = 0;
    if (paramObject != null) {
      i++;
    }
    if ((paramInt & 0x10000) != 0) {
      i++;
    }
    if ((paramInt & 0x20000) != 0) {
      i++;
    }
    this.fields.put2(i);
    if (paramObject != null)
    {
      this.fields.put2(newUTF8("ConstantValue").index);
      this.fields.put4(2).put2(newCst(paramObject).index);
    }
    if ((paramInt & 0x10000) != 0) {
      this.fields.put2(newUTF8("Synthetic").index).put4(0);
    }
    if ((paramInt & 0x20000) != 0) {
      this.fields.put2(newUTF8("Deprecated").index).put4(0);
    }
  }
  
  public CodeVisitor visitMethod(int paramInt, String paramString1, String paramString2, String[] paramArrayOfString)
  {
    CodeWriter localCodeWriter = new CodeWriter(this, this.computeMaxs);
    localCodeWriter.init(paramInt, paramString1, paramString2, paramArrayOfString);
    return localCodeWriter;
  }
  
  public void visitEnd() {}
  
  public byte[] toByteArray()
  {
    int i = 24 + 2 * this.interfaceCount;
    if (this.fields != null) {
      i += this.fields.length;
    }
    int j = 0;
    for (CodeWriter localCodeWriter = this.firstMethod; localCodeWriter != null; localCodeWriter = localCodeWriter.next)
    {
      j++;
      i += localCodeWriter.getSize();
    }
    i += this.pool.length;
    int k = 0;
    if (this.sourceFile != null)
    {
      k++;
      i += 8;
    }
    if ((this.access & 0x20000) != 0)
    {
      k++;
      i += 6;
    }
    if (this.innerClasses != null)
    {
      k++;
      i += 8 + this.innerClasses.length;
    }
    ByteVector localByteVector = new ByteVector(i);
    localByteVector.put4(-889275714).put2(3).put2(45);
    localByteVector.put2(this.index).putByteArray(this.pool.data, 0, this.pool.length);
    localByteVector.put2(this.access).put2(this.name).put2(this.superName);
    localByteVector.put2(this.interfaceCount);
    for (int m = 0; m < this.interfaceCount; m++) {
      localByteVector.put2(this.interfaces[m]);
    }
    localByteVector.put2(this.fieldCount);
    if (this.fields != null) {
      localByteVector.putByteArray(this.fields.data, 0, this.fields.length);
    }
    localByteVector.put2(j);
    for (localCodeWriter = this.firstMethod; localCodeWriter != null; localCodeWriter = localCodeWriter.next) {
      localCodeWriter.put(localByteVector);
    }
    localByteVector.put2(k);
    if (this.sourceFile != null) {
      localByteVector.put2(newUTF8("SourceFile").index).put4(2).put2(this.sourceFile.index);
    }
    if ((this.access & 0x20000) != 0) {
      localByteVector.put2(newUTF8("Deprecated").index).put4(0);
    }
    if (this.innerClasses != null)
    {
      localByteVector.put2(newUTF8("InnerClasses").index);
      localByteVector.put4(this.innerClasses.length + 2).put2(this.innerClassesCount);
      localByteVector.putByteArray(this.innerClasses.data, 0, this.innerClasses.length);
    }
    return localByteVector.data;
  }
  
  Item newCst(Object paramObject)
  {
    if ((paramObject instanceof Integer))
    {
      int i = ((Integer)paramObject).intValue();
      return newInteger(i);
    }
    if ((paramObject instanceof Float))
    {
      float f = ((Float)paramObject).floatValue();
      return newFloat(f);
    }
    if ((paramObject instanceof Long))
    {
      long l = ((Long)paramObject).longValue();
      return newLong(l);
    }
    if ((paramObject instanceof Double))
    {
      double d = ((Double)paramObject).doubleValue();
      return newDouble(d);
    }
    if ((paramObject instanceof String)) {
      return newString((String)paramObject);
    }
    throw new IllegalArgumentException("value " + paramObject);
  }
  
  Item newUTF8(String paramString)
  {
    this.key.set(1, paramString, null, null);
    Item localItem = get(this.key);
    if (localItem == null)
    {
      this.pool.put1(1).putUTF(paramString);
      localItem = new Item(this.index++, this.key);
      put(localItem);
    }
    return localItem;
  }
  
  Item newClass(String paramString)
  {
    this.key2.set(7, paramString, null, null);
    Item localItem = get(this.key2);
    if (localItem == null)
    {
      this.pool.put12(7, newUTF8(paramString).index);
      localItem = new Item(this.index++, this.key2);
      put(localItem);
    }
    return localItem;
  }
  
  Item newField(String paramString1, String paramString2, String paramString3)
  {
    this.key3.set(9, paramString1, paramString2, paramString3);
    Item localItem = get(this.key3);
    if (localItem == null)
    {
      put122(9, newClass(paramString1).index, newNameType(paramString2, paramString3).index);
      localItem = new Item(this.index++, this.key3);
      put(localItem);
    }
    return localItem;
  }
  
  Item newMethod(String paramString1, String paramString2, String paramString3)
  {
    this.key3.set(10, paramString1, paramString2, paramString3);
    Item localItem = get(this.key3);
    if (localItem == null)
    {
      put122(10, newClass(paramString1).index, newNameType(paramString2, paramString3).index);
      localItem = new Item(this.index++, this.key3);
      put(localItem);
    }
    return localItem;
  }
  
  Item newItfMethod(String paramString1, String paramString2, String paramString3)
  {
    this.key3.set(11, paramString1, paramString2, paramString3);
    Item localItem = get(this.key3);
    if (localItem == null)
    {
      put122(11, newClass(paramString1).index, newNameType(paramString2, paramString3).index);
      localItem = new Item(this.index++, this.key3);
      put(localItem);
    }
    return localItem;
  }
  
  private Item newInteger(int paramInt)
  {
    this.key.set(paramInt);
    Item localItem = get(this.key);
    if (localItem == null)
    {
      this.pool.put1(3).put4(paramInt);
      localItem = new Item(this.index++, this.key);
      put(localItem);
    }
    return localItem;
  }
  
  private Item newFloat(float paramFloat)
  {
    this.key.set(paramFloat);
    Item localItem = get(this.key);
    if (localItem == null)
    {
      this.pool.put1(4).put4(Float.floatToIntBits(paramFloat));
      localItem = new Item(this.index++, this.key);
      put(localItem);
    }
    return localItem;
  }
  
  private Item newLong(long paramLong)
  {
    this.key.set(paramLong);
    Item localItem = get(this.key);
    if (localItem == null)
    {
      this.pool.put1(5).put8(paramLong);
      localItem = new Item(this.index, this.key);
      put(localItem);
      this.index = ((short)(this.index + 2));
    }
    return localItem;
  }
  
  private Item newDouble(double paramDouble)
  {
    this.key.set(paramDouble);
    Item localItem = get(this.key);
    if (localItem == null)
    {
      this.pool.put1(6).put8(Double.doubleToLongBits(paramDouble));
      localItem = new Item(this.index, this.key);
      put(localItem);
      this.index = ((short)(this.index + 2));
    }
    return localItem;
  }
  
  private Item newString(String paramString)
  {
    this.key2.set(8, paramString, null, null);
    Item localItem = get(this.key2);
    if (localItem == null)
    {
      this.pool.put12(8, newUTF8(paramString).index);
      localItem = new Item(this.index++, this.key2);
      put(localItem);
    }
    return localItem;
  }
  
  private Item newNameType(String paramString1, String paramString2)
  {
    this.key2.set(12, paramString1, paramString2, null);
    Item localItem = get(this.key2);
    if (localItem == null)
    {
      put122(12, newUTF8(paramString1).index, newUTF8(paramString2).index);
      localItem = new Item(this.index++, this.key2);
      put(localItem);
    }
    return localItem;
  }
  
  private Item get(Item paramItem)
  {
    Item[] arrayOfItem = this.table;
    int i = paramItem.hashCode;
    int j = (i & 0x7FFFFFFF) % arrayOfItem.length;
    for (Item localItem = arrayOfItem[j]; localItem != null; localItem = localItem.next) {
      if ((localItem.hashCode == i) && (paramItem.isEqualTo(localItem))) {
        return localItem;
      }
    }
    return null;
  }
  
  private void put(Item paramItem)
  {
    if (this.index > this.threshold)
    {
      i = this.table.length;
      Item[] arrayOfItem1 = this.table;
      int j = i * 2 + 1;
      Item[] arrayOfItem2 = new Item[j];
      this.threshold = ((int)(j * 0.75D));
      this.table = arrayOfItem2;
      int k = i;
      while (k-- > 0)
      {
        Item localItem1 = arrayOfItem1[k];
        while (localItem1 != null)
        {
          Item localItem2 = localItem1;
          localItem1 = localItem1.next;
          int m = (localItem2.hashCode & 0x7FFFFFFF) % j;
          localItem2.next = arrayOfItem2[m];
          arrayOfItem2[m] = localItem2;
        }
      }
    }
    int i = (paramItem.hashCode & 0x7FFFFFFF) % this.table.length;
    paramItem.next = this.table[i];
    this.table[i] = paramItem;
  }
  
  private void put122(int paramInt1, int paramInt2, int paramInt3)
  {
    this.pool.put12(paramInt1, paramInt2).put2(paramInt3);
  }
  
  static
  {
    byte[] arrayOfByte = new byte['Ü'];
    String str = "AAAAAAAAAAAAAAAABCKLLDDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAADDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMAAAAAAAAAAAAAAAAAAAAIIIIIIIIIIIIIIIIDNOAAAAAAGGGGGGGHAFBFAAFFAAQPIIJJIIIIIIIIIIIIIIIIII";
    for (int i = 0; i < arrayOfByte.length; i++) {
      arrayOfByte[i] = ((byte)(str.charAt(i) - 'A'));
    }
    TYPE = arrayOfByte;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/org/objectweb/asm/ClassWriter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */