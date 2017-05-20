package bsh.org.objectweb.asm;

public class CodeWriter
  implements CodeVisitor
{
  static final boolean CHECK = false;
  CodeWriter next;
  private ClassWriter cw;
  private Item name;
  private Item desc;
  private int access;
  private int maxStack;
  private int maxLocals;
  private ByteVector code = new ByteVector();
  private int catchCount;
  private ByteVector catchTable;
  private int exceptionCount;
  private int[] exceptions;
  private int localVarCount;
  private ByteVector localVar;
  private int lineNumberCount;
  private ByteVector lineNumber;
  private boolean resize;
  private final boolean computeMaxs;
  private int stackSize;
  private int maxStackSize;
  private Label currentBlock;
  private Label blockStack;
  private static final int[] SIZE;
  private Edge head;
  private Edge tail;
  private static Edge pool;
  
  protected CodeWriter(ClassWriter paramClassWriter, boolean paramBoolean)
  {
    if (paramClassWriter.firstMethod == null)
    {
      paramClassWriter.firstMethod = this;
      paramClassWriter.lastMethod = this;
    }
    else
    {
      paramClassWriter.lastMethod.next = this;
      paramClassWriter.lastMethod = this;
    }
    this.cw = paramClassWriter;
    this.computeMaxs = paramBoolean;
    if (paramBoolean)
    {
      this.currentBlock = new Label();
      this.currentBlock.pushed = true;
      this.blockStack = this.currentBlock;
    }
  }
  
  protected void init(int paramInt, String paramString1, String paramString2, String[] paramArrayOfString)
  {
    this.access = paramInt;
    this.name = this.cw.newUTF8(paramString1);
    this.desc = this.cw.newUTF8(paramString2);
    int i;
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0))
    {
      this.exceptionCount = paramArrayOfString.length;
      this.exceptions = new int[this.exceptionCount];
      for (i = 0; i < this.exceptionCount; i++) {
        this.exceptions[i] = this.cw.newClass(paramArrayOfString[i]).index;
      }
    }
    if (this.computeMaxs)
    {
      i = getArgumentsAndReturnSizes(paramString2) >> 2;
      if ((paramInt & 0x8) != 0) {
        i--;
      }
      if (i > this.maxLocals) {
        this.maxLocals = i;
      }
    }
  }
  
  public void visitInsn(int paramInt)
  {
    if (this.computeMaxs)
    {
      int i = this.stackSize + SIZE[paramInt];
      if (i > this.maxStackSize) {
        this.maxStackSize = i;
      }
      this.stackSize = i;
      if (((paramInt >= 172) && (paramInt <= 177)) || ((paramInt == 191) && (this.currentBlock != null)))
      {
        this.currentBlock.maxStackSize = this.maxStackSize;
        this.currentBlock = null;
      }
    }
    this.code.put1(paramInt);
  }
  
  public void visitIntInsn(int paramInt1, int paramInt2)
  {
    if ((this.computeMaxs) && (paramInt1 != 188))
    {
      int i = this.stackSize + 1;
      if (i > this.maxStackSize) {
        this.maxStackSize = i;
      }
      this.stackSize = i;
    }
    if (paramInt1 == 17) {
      this.code.put12(paramInt1, paramInt2);
    } else {
      this.code.put11(paramInt1, paramInt2);
    }
  }
  
  public void visitVarInsn(int paramInt1, int paramInt2)
  {
    int i;
    if (this.computeMaxs)
    {
      if (paramInt1 == 169)
      {
        if (this.currentBlock != null)
        {
          this.currentBlock.maxStackSize = this.maxStackSize;
          this.currentBlock = null;
        }
      }
      else
      {
        i = this.stackSize + SIZE[paramInt1];
        if (i > this.maxStackSize) {
          this.maxStackSize = i;
        }
        this.stackSize = i;
      }
      if ((paramInt1 == 22) || (paramInt1 == 24) || (paramInt1 == 55) || (paramInt1 == 57)) {
        i = paramInt2 + 2;
      } else {
        i = paramInt2 + 1;
      }
      if (i > this.maxLocals) {
        this.maxLocals = i;
      }
    }
    if ((paramInt2 < 4) && (paramInt1 != 169))
    {
      if (paramInt1 < 54) {
        i = 26 + (paramInt1 - 21 << 2) + paramInt2;
      } else {
        i = 59 + (paramInt1 - 54 << 2) + paramInt2;
      }
      this.code.put1(i);
    }
    else if (paramInt2 >= 256)
    {
      this.code.put1(196).put12(paramInt1, paramInt2);
    }
    else
    {
      this.code.put11(paramInt1, paramInt2);
    }
  }
  
  public void visitTypeInsn(int paramInt, String paramString)
  {
    if ((this.computeMaxs) && (paramInt == 187))
    {
      int i = this.stackSize + 1;
      if (i > this.maxStackSize) {
        this.maxStackSize = i;
      }
      this.stackSize = i;
    }
    this.code.put12(paramInt, this.cw.newClass(paramString).index);
  }
  
  public void visitFieldInsn(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    if (this.computeMaxs)
    {
      int j = paramString3.charAt(0);
      int i;
      switch (paramInt)
      {
      case 178: 
        i = this.stackSize + ((j == 68) || (j == 74) ? 2 : 1);
        break;
      case 179: 
        i = this.stackSize + ((j == 68) || (j == 74) ? -2 : -1);
        break;
      case 180: 
        i = this.stackSize + ((j == 68) || (j == 74) ? 1 : 0);
        break;
      default: 
        i = this.stackSize + ((j == 68) || (j == 74) ? -3 : -2);
      }
      if (i > this.maxStackSize) {
        this.maxStackSize = i;
      }
      this.stackSize = i;
    }
    this.code.put12(paramInt, this.cw.newField(paramString1, paramString2, paramString3).index);
  }
  
  public void visitMethodInsn(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    Item localItem;
    if (paramInt == 185) {
      localItem = this.cw.newItfMethod(paramString1, paramString2, paramString3);
    } else {
      localItem = this.cw.newMethod(paramString1, paramString2, paramString3);
    }
    int i = localItem.intVal;
    if (this.computeMaxs)
    {
      if (i == 0)
      {
        i = getArgumentsAndReturnSizes(paramString3);
        localItem.intVal = i;
      }
      int j;
      if (paramInt == 184) {
        j = this.stackSize - (i >> 2) + (i & 0x3) + 1;
      } else {
        j = this.stackSize - (i >> 2) + (i & 0x3);
      }
      if (j > this.maxStackSize) {
        this.maxStackSize = j;
      }
      this.stackSize = j;
    }
    if (paramInt == 185)
    {
      if ((!this.computeMaxs) && (i == 0))
      {
        i = getArgumentsAndReturnSizes(paramString3);
        localItem.intVal = i;
      }
      this.code.put12(185, localItem.index).put11(i >> 2, 0);
    }
    else
    {
      this.code.put12(paramInt, localItem.index);
    }
  }
  
  public void visitJumpInsn(int paramInt, Label paramLabel)
  {
    if (this.computeMaxs) {
      if (paramInt == 167)
      {
        if (this.currentBlock != null)
        {
          this.currentBlock.maxStackSize = this.maxStackSize;
          addSuccessor(this.stackSize, paramLabel);
          this.currentBlock = null;
        }
      }
      else if (paramInt == 168)
      {
        if (this.currentBlock != null) {
          addSuccessor(this.stackSize + 1, paramLabel);
        }
      }
      else
      {
        this.stackSize += SIZE[paramInt];
        if (this.currentBlock != null) {
          addSuccessor(this.stackSize, paramLabel);
        }
      }
    }
    if ((paramLabel.resolved) && (paramLabel.position - this.code.length < 32768))
    {
      if (paramInt == 167)
      {
        this.code.put1(200);
      }
      else if (paramInt == 168)
      {
        this.code.put1(201);
      }
      else
      {
        this.code.put1(paramInt <= 166 ? (paramInt + 1 ^ 0x1) - 1 : paramInt ^ 0x1);
        this.code.put2(8);
        this.code.put1(200);
      }
      paramLabel.put(this, this.code, this.code.length - 1, true);
    }
    else
    {
      this.code.put1(paramInt);
      paramLabel.put(this, this.code, this.code.length - 1, false);
    }
  }
  
  public void visitLabel(Label paramLabel)
  {
    if (this.computeMaxs)
    {
      if (this.currentBlock != null)
      {
        this.currentBlock.maxStackSize = this.maxStackSize;
        addSuccessor(this.stackSize, paramLabel);
      }
      this.currentBlock = paramLabel;
      this.stackSize = 0;
      this.maxStackSize = 0;
    }
    this.resize |= paramLabel.resolve(this, this.code.length, this.code.data);
  }
  
  public void visitLdcInsn(Object paramObject)
  {
    Item localItem = this.cw.newCst(paramObject);
    if (this.computeMaxs)
    {
      if ((localItem.type == 5) || (localItem.type == 6)) {
        i = this.stackSize + 2;
      } else {
        i = this.stackSize + 1;
      }
      if (i > this.maxStackSize) {
        this.maxStackSize = i;
      }
      this.stackSize = i;
    }
    int i = localItem.index;
    if ((localItem.type == 5) || (localItem.type == 6)) {
      this.code.put12(20, i);
    } else if (i >= 256) {
      this.code.put12(19, i);
    } else {
      this.code.put11(18, i);
    }
  }
  
  public void visitIincInsn(int paramInt1, int paramInt2)
  {
    if (this.computeMaxs)
    {
      int i = paramInt1 + 1;
      if (i > this.maxLocals) {
        this.maxLocals = i;
      }
    }
    if ((paramInt1 > 255) || (paramInt2 > 127) || (paramInt2 < -128)) {
      this.code.put1(196).put12(132, paramInt1).put2(paramInt2);
    } else {
      this.code.put1(132).put11(paramInt1, paramInt2);
    }
  }
  
  public void visitTableSwitchInsn(int paramInt1, int paramInt2, Label paramLabel, Label[] paramArrayOfLabel)
  {
    if (this.computeMaxs)
    {
      this.stackSize -= 1;
      if (this.currentBlock != null)
      {
        this.currentBlock.maxStackSize = this.maxStackSize;
        addSuccessor(this.stackSize, paramLabel);
        for (i = 0; i < paramArrayOfLabel.length; i++) {
          addSuccessor(this.stackSize, paramArrayOfLabel[i]);
        }
        this.currentBlock = null;
      }
    }
    int i = this.code.length;
    this.code.put1(170);
    while (this.code.length % 4 != 0) {
      this.code.put1(0);
    }
    paramLabel.put(this, this.code, i, true);
    this.code.put4(paramInt1).put4(paramInt2);
    for (int j = 0; j < paramArrayOfLabel.length; j++) {
      paramArrayOfLabel[j].put(this, this.code, i, true);
    }
  }
  
  public void visitLookupSwitchInsn(Label paramLabel, int[] paramArrayOfInt, Label[] paramArrayOfLabel)
  {
    if (this.computeMaxs)
    {
      this.stackSize -= 1;
      if (this.currentBlock != null)
      {
        this.currentBlock.maxStackSize = this.maxStackSize;
        addSuccessor(this.stackSize, paramLabel);
        for (i = 0; i < paramArrayOfLabel.length; i++) {
          addSuccessor(this.stackSize, paramArrayOfLabel[i]);
        }
        this.currentBlock = null;
      }
    }
    int i = this.code.length;
    this.code.put1(171);
    while (this.code.length % 4 != 0) {
      this.code.put1(0);
    }
    paramLabel.put(this, this.code, i, true);
    this.code.put4(paramArrayOfLabel.length);
    for (int j = 0; j < paramArrayOfLabel.length; j++)
    {
      this.code.put4(paramArrayOfInt[j]);
      paramArrayOfLabel[j].put(this, this.code, i, true);
    }
  }
  
  public void visitMultiANewArrayInsn(String paramString, int paramInt)
  {
    if (this.computeMaxs) {
      this.stackSize += 1 - paramInt;
    }
    Item localItem = this.cw.newClass(paramString);
    this.code.put12(197, localItem.index).put1(paramInt);
  }
  
  public void visitTryCatchBlock(Label paramLabel1, Label paramLabel2, Label paramLabel3, String paramString)
  {
    if ((this.computeMaxs) && (!paramLabel3.pushed))
    {
      paramLabel3.beginStackSize = 1;
      paramLabel3.pushed = true;
      paramLabel3.next = this.blockStack;
      this.blockStack = paramLabel3;
    }
    this.catchCount += 1;
    if (this.catchTable == null) {
      this.catchTable = new ByteVector();
    }
    this.catchTable.put2(paramLabel1.position);
    this.catchTable.put2(paramLabel2.position);
    this.catchTable.put2(paramLabel3.position);
    this.catchTable.put2(paramString != null ? this.cw.newClass(paramString).index : 0);
  }
  
  public void visitMaxs(int paramInt1, int paramInt2)
  {
    if (this.computeMaxs)
    {
      int i = 0;
      Object localObject1 = this.blockStack;
      while (localObject1 != null)
      {
        Object localObject2 = localObject1;
        localObject1 = ((Label)localObject1).next;
        int j = ((Label)localObject2).beginStackSize;
        int k = j + ((Label)localObject2).maxStackSize;
        if (k > i) {
          i = k;
        }
        for (Edge localEdge = ((Label)localObject2).successors; localEdge != null; localEdge = localEdge.next)
        {
          localObject2 = localEdge.successor;
          if (!((Label)localObject2).pushed)
          {
            ((Label)localObject2).beginStackSize = (j + localEdge.stackSize);
            ((Label)localObject2).pushed = true;
            ((Label)localObject2).next = ((Label)localObject1);
            localObject1 = localObject2;
          }
        }
      }
      this.maxStack = i;
      synchronized (SIZE)
      {
        if (this.tail != null)
        {
          this.tail.poolNext = pool;
          pool = this.head;
        }
      }
    }
    this.maxStack = paramInt1;
    this.maxLocals = paramInt2;
  }
  
  public void visitLocalVariable(String paramString1, String paramString2, Label paramLabel1, Label paramLabel2, int paramInt)
  {
    if (this.localVar == null)
    {
      this.cw.newUTF8("LocalVariableTable");
      this.localVar = new ByteVector();
    }
    this.localVarCount += 1;
    this.localVar.put2(paramLabel1.position);
    this.localVar.put2(paramLabel2.position - paramLabel1.position);
    this.localVar.put2(this.cw.newUTF8(paramString1).index);
    this.localVar.put2(this.cw.newUTF8(paramString2).index);
    this.localVar.put2(paramInt);
  }
  
  public void visitLineNumber(int paramInt, Label paramLabel)
  {
    if (this.lineNumber == null)
    {
      this.cw.newUTF8("LineNumberTable");
      this.lineNumber = new ByteVector();
    }
    this.lineNumberCount += 1;
    this.lineNumber.put2(paramLabel.position);
    this.lineNumber.put2(paramInt);
  }
  
  private static int getArgumentsAndReturnSizes(String paramString)
  {
    int i = 1;
    int j = 1;
    for (;;)
    {
      int k = paramString.charAt(j++);
      if (k == 41)
      {
        k = paramString.charAt(j);
        return i << 2 | ((k == 68) || (k == 74) ? 2 : k == 86 ? 0 : 1);
      }
      if (k == 76)
      {
        while ((goto 69) || (paramString.charAt(j++) != ';')) {}
        i++;
      }
      else if (k == 91)
      {
        while ((k = paramString.charAt(j)) == '[') {
          j++;
        }
        if ((k == 68) || (k == 74)) {
          i--;
        }
      }
      else if ((k == 68) || (k == 74))
      {
        i += 2;
      }
      else
      {
        i++;
      }
    }
  }
  
  private void addSuccessor(int paramInt, Label paramLabel)
  {
    Edge localEdge;
    synchronized (SIZE)
    {
      if (pool == null)
      {
        localEdge = new Edge();
      }
      else
      {
        localEdge = pool;
        pool = pool.poolNext;
      }
    }
    if (this.tail == null) {
      this.tail = localEdge;
    }
    localEdge.poolNext = this.head;
    this.head = localEdge;
    localEdge.stackSize = paramInt;
    localEdge.successor = paramLabel;
    localEdge.next = this.currentBlock.successors;
    this.currentBlock.successors = localEdge;
  }
  
  final int getSize()
  {
    if (this.resize) {
      resizeInstructions(new int[0], new int[0], 0);
    }
    int i = 8;
    if (this.code.length > 0)
    {
      this.cw.newUTF8("Code");
      i += 18 + this.code.length + 8 * this.catchCount;
      if (this.localVar != null) {
        i += 8 + this.localVar.length;
      }
      if (this.lineNumber != null) {
        i += 8 + this.lineNumber.length;
      }
    }
    if (this.exceptionCount > 0)
    {
      this.cw.newUTF8("Exceptions");
      i += 8 + 2 * this.exceptionCount;
    }
    if ((this.access & 0x10000) != 0)
    {
      this.cw.newUTF8("Synthetic");
      i += 6;
    }
    if ((this.access & 0x20000) != 0)
    {
      this.cw.newUTF8("Deprecated");
      i += 6;
    }
    return i;
  }
  
  final void put(ByteVector paramByteVector)
  {
    paramByteVector.put2(this.access).put2(this.name.index).put2(this.desc.index);
    int i = 0;
    if (this.code.length > 0) {
      i++;
    }
    if (this.exceptionCount > 0) {
      i++;
    }
    if ((this.access & 0x10000) != 0) {
      i++;
    }
    if ((this.access & 0x20000) != 0) {
      i++;
    }
    paramByteVector.put2(i);
    int j;
    if (this.code.length > 0)
    {
      j = 12 + this.code.length + 8 * this.catchCount;
      if (this.localVar != null) {
        j += 8 + this.localVar.length;
      }
      if (this.lineNumber != null) {
        j += 8 + this.lineNumber.length;
      }
      paramByteVector.put2(this.cw.newUTF8("Code").index).put4(j);
      paramByteVector.put2(this.maxStack).put2(this.maxLocals);
      paramByteVector.put4(this.code.length).putByteArray(this.code.data, 0, this.code.length);
      paramByteVector.put2(this.catchCount);
      if (this.catchCount > 0) {
        paramByteVector.putByteArray(this.catchTable.data, 0, this.catchTable.length);
      }
      i = 0;
      if (this.localVar != null) {
        i++;
      }
      if (this.lineNumber != null) {
        i++;
      }
      paramByteVector.put2(i);
      if (this.localVar != null)
      {
        paramByteVector.put2(this.cw.newUTF8("LocalVariableTable").index);
        paramByteVector.put4(this.localVar.length + 2).put2(this.localVarCount);
        paramByteVector.putByteArray(this.localVar.data, 0, this.localVar.length);
      }
      if (this.lineNumber != null)
      {
        paramByteVector.put2(this.cw.newUTF8("LineNumberTable").index);
        paramByteVector.put4(this.lineNumber.length + 2).put2(this.lineNumberCount);
        paramByteVector.putByteArray(this.lineNumber.data, 0, this.lineNumber.length);
      }
    }
    if (this.exceptionCount > 0)
    {
      paramByteVector.put2(this.cw.newUTF8("Exceptions").index).put4(2 * this.exceptionCount + 2);
      paramByteVector.put2(this.exceptionCount);
      for (j = 0; j < this.exceptionCount; j++) {
        paramByteVector.put2(this.exceptions[j]);
      }
    }
    if ((this.access & 0x10000) != 0) {
      paramByteVector.put2(this.cw.newUTF8("Synthetic").index).put4(0);
    }
    if ((this.access & 0x20000) != 0) {
      paramByteVector.put2(this.cw.newUTF8("Deprecated").index).put4(0);
    }
  }
  
  protected int[] resizeInstructions(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt)
  {
    byte[] arrayOfByte = this.code.data;
    Object localObject1 = new int[paramInt];
    Object localObject2 = new int[paramInt];
    System.arraycopy(paramArrayOfInt1, 0, localObject1, 0, paramInt);
    System.arraycopy(paramArrayOfInt2, 0, localObject2, 0, paramInt);
    boolean[] arrayOfBoolean = new boolean[this.code.length];
    int i2 = 3;
    int i4;
    int k;
    int i1;
    do
    {
      if (i2 == 3) {
        i2 = 2;
      }
      i = 0;
      while (i < arrayOfByte.length)
      {
        int i3 = arrayOfByte[i] & 0xFF;
        i4 = 0;
        switch (ClassWriter.TYPE[i3])
        {
        case 0: 
        case 4: 
          i++;
          break;
        case 8: 
          if (i3 > 201)
          {
            i3 = i3 < 218 ? i3 - 49 : i3 - 20;
            k = i + readUnsignedShort(arrayOfByte, i + 1);
          }
          else
          {
            k = i + readShort(arrayOfByte, i + 1);
          }
          i1 = getNewOffset((int[])localObject1, (int[])localObject2, i, k);
          if (((i1 < 32768) || (i1 > 32767)) && (arrayOfBoolean[i] == 0))
          {
            if ((i3 == 167) || (i3 == 168)) {
              i4 = 2;
            } else {
              i4 = 5;
            }
            arrayOfBoolean[i] = true;
          }
          i += 3;
          break;
        case 9: 
          i += 5;
          break;
        case 13: 
          if (i2 == 1)
          {
            i1 = getNewOffset((int[])localObject1, (int[])localObject2, 0, i);
            i4 = -(i1 & 0x3);
          }
          else if (arrayOfBoolean[i] == 0)
          {
            i4 = i & 0x3;
            arrayOfBoolean[i] = true;
          }
          i = i + 4 - (i & 0x3);
          i += 4 * (readInt(arrayOfByte, i + 8) - readInt(arrayOfByte, i + 4) + 1) + 12;
          break;
        case 14: 
          if (i2 == 1)
          {
            i1 = getNewOffset((int[])localObject1, (int[])localObject2, 0, i);
            i4 = -(i1 & 0x3);
          }
          else if (arrayOfBoolean[i] == 0)
          {
            i4 = i & 0x3;
            arrayOfBoolean[i] = true;
          }
          i = i + 4 - (i & 0x3);
          i += 8 * readInt(arrayOfByte, i + 4) + 8;
          break;
        case 16: 
          i3 = arrayOfByte[(i + 1)] & 0xFF;
          if (i3 == 132) {
            i += 6;
          } else {
            i += 4;
          }
          break;
        case 1: 
        case 3: 
        case 10: 
          i += 2;
          break;
        case 2: 
        case 5: 
        case 6: 
        case 11: 
        case 12: 
          i += 3;
          break;
        case 7: 
          i += 5;
          break;
        case 15: 
        default: 
          i += 4;
        }
        if (i4 != 0)
        {
          int[] arrayOfInt1 = new int[localObject1.length + 1];
          int[] arrayOfInt2 = new int[localObject2.length + 1];
          System.arraycopy(localObject1, 0, arrayOfInt1, 0, localObject1.length);
          System.arraycopy(localObject2, 0, arrayOfInt2, 0, localObject2.length);
          arrayOfInt1[localObject1.length] = i;
          arrayOfInt2[localObject2.length] = i4;
          localObject1 = arrayOfInt1;
          localObject2 = arrayOfInt2;
          if (i4 > 0) {
            i2 = 3;
          }
        }
      }
      if (i2 < 3) {
        i2--;
      }
    } while (i2 != 0);
    ByteVector localByteVector = new ByteVector(this.code.length);
    int i = 0;
    while (i < this.code.length)
    {
      for (int m = localObject1.length - 1; m >= 0; m--) {
        if ((localObject1[m] == i) && (m < paramInt))
        {
          if (paramArrayOfInt2[m] > 0) {
            localByteVector.putByteArray(null, 0, paramArrayOfInt2[m]);
          } else {
            localByteVector.length += paramArrayOfInt2[m];
          }
          paramArrayOfInt1[m] = localByteVector.length;
        }
      }
      i4 = arrayOfByte[i] & 0xFF;
      int j;
      int i5;
      int n;
      switch (ClassWriter.TYPE[i4])
      {
      case 0: 
      case 4: 
        localByteVector.put1(i4);
        i++;
        break;
      case 8: 
        if (i4 > 201)
        {
          i4 = i4 < 218 ? i4 - 49 : i4 - 20;
          k = i + readUnsignedShort(arrayOfByte, i + 1);
        }
        else
        {
          k = i + readShort(arrayOfByte, i + 1);
        }
        i1 = getNewOffset((int[])localObject1, (int[])localObject2, i, k);
        if ((i1 < 32768) || (i1 > 32767))
        {
          if (i4 == 167)
          {
            localByteVector.put1(200);
          }
          else if (i4 == 168)
          {
            localByteVector.put1(201);
          }
          else
          {
            localByteVector.put1(i4 <= 166 ? (i4 + 1 ^ 0x1) - 1 : i4 ^ 0x1);
            localByteVector.put2(8);
            localByteVector.put1(200);
            i1 -= 3;
          }
          localByteVector.put4(i1);
        }
        else
        {
          localByteVector.put1(i4);
          localByteVector.put2(i1);
        }
        i += 3;
        break;
      case 9: 
        k = i + readInt(arrayOfByte, i + 1);
        i1 = getNewOffset((int[])localObject1, (int[])localObject2, i, k);
        localByteVector.put1(i4);
        localByteVector.put4(i1);
        i += 5;
        break;
      case 13: 
        j = i;
        i = i + 4 - (j & 0x3);
        i5 = localByteVector.length;
        localByteVector.put1(170);
        while (localByteVector.length % 4 != 0) {
          localByteVector.put1(0);
        }
        k = j + readInt(arrayOfByte, i);
        i += 4;
        i1 = getNewOffset((int[])localObject1, (int[])localObject2, j, k);
        localByteVector.put4(i1);
        n = readInt(arrayOfByte, i);
        i += 4;
        localByteVector.put4(n);
        n = readInt(arrayOfByte, i) - n + 1;
        i += 4;
        localByteVector.put4(readInt(arrayOfByte, i - 4));
        while (n > 0)
        {
          k = j + readInt(arrayOfByte, i);
          i += 4;
          i1 = getNewOffset((int[])localObject1, (int[])localObject2, j, k);
          localByteVector.put4(i1);
          n--;
        }
        break;
      case 14: 
        j = i;
        i = i + 4 - (j & 0x3);
        i5 = localByteVector.length;
        localByteVector.put1(171);
        while (localByteVector.length % 4 != 0) {
          localByteVector.put1(0);
        }
        k = j + readInt(arrayOfByte, i);
        i += 4;
        i1 = getNewOffset((int[])localObject1, (int[])localObject2, j, k);
        localByteVector.put4(i1);
        n = readInt(arrayOfByte, i);
        i += 4;
        localByteVector.put4(n);
        while (n > 0)
        {
          localByteVector.put4(readInt(arrayOfByte, i));
          i += 4;
          k = j + readInt(arrayOfByte, i);
          i += 4;
          i1 = getNewOffset((int[])localObject1, (int[])localObject2, j, k);
          localByteVector.put4(i1);
          n--;
        }
        break;
      case 16: 
        i4 = arrayOfByte[(i + 1)] & 0xFF;
        if (i4 == 132)
        {
          localByteVector.putByteArray(arrayOfByte, i, 6);
          i += 6;
        }
        else
        {
          localByteVector.putByteArray(arrayOfByte, i, 4);
          i += 4;
        }
        break;
      case 1: 
      case 3: 
      case 10: 
        localByteVector.putByteArray(arrayOfByte, i, 2);
        i += 2;
        break;
      case 2: 
      case 5: 
      case 6: 
      case 11: 
      case 12: 
        localByteVector.putByteArray(arrayOfByte, i, 3);
        i += 3;
        break;
      case 7: 
        localByteVector.putByteArray(arrayOfByte, i, 5);
        i += 5;
        break;
      case 15: 
      default: 
        localByteVector.putByteArray(arrayOfByte, i, 4);
        i += 4;
      }
    }
    if (this.catchTable != null)
    {
      arrayOfByte = this.catchTable.data;
      for (i = 0; i < this.catchTable.length; i += 8)
      {
        writeShort(arrayOfByte, i, getNewOffset((int[])localObject1, (int[])localObject2, 0, readUnsignedShort(arrayOfByte, i)));
        writeShort(arrayOfByte, i + 2, getNewOffset((int[])localObject1, (int[])localObject2, 0, readUnsignedShort(arrayOfByte, i + 2)));
        writeShort(arrayOfByte, i + 4, getNewOffset((int[])localObject1, (int[])localObject2, 0, readUnsignedShort(arrayOfByte, i + 4)));
      }
    }
    if (this.localVar != null)
    {
      arrayOfByte = this.localVar.data;
      for (i = 0; i < this.localVar.length; i += 10)
      {
        k = readUnsignedShort(arrayOfByte, i);
        i1 = getNewOffset((int[])localObject1, (int[])localObject2, 0, k);
        writeShort(arrayOfByte, i, i1);
        k += readUnsignedShort(arrayOfByte, i + 2);
        i1 = getNewOffset((int[])localObject1, (int[])localObject2, 0, k) - i1;
        writeShort(arrayOfByte, i, i1);
      }
    }
    if (this.lineNumber != null)
    {
      arrayOfByte = this.lineNumber.data;
      for (i = 0; i < this.lineNumber.length; i += 4) {
        writeShort(arrayOfByte, i, getNewOffset((int[])localObject1, (int[])localObject2, 0, readUnsignedShort(arrayOfByte, i)));
      }
    }
    this.code = localByteVector;
    return paramArrayOfInt1;
  }
  
  static int readUnsignedShort(byte[] paramArrayOfByte, int paramInt)
  {
    return (paramArrayOfByte[paramInt] & 0xFF) << 8 | paramArrayOfByte[(paramInt + 1)] & 0xFF;
  }
  
  static short readShort(byte[] paramArrayOfByte, int paramInt)
  {
    return (short)((paramArrayOfByte[paramInt] & 0xFF) << 8 | paramArrayOfByte[(paramInt + 1)] & 0xFF);
  }
  
  static int readInt(byte[] paramArrayOfByte, int paramInt)
  {
    return (paramArrayOfByte[paramInt] & 0xFF) << 24 | (paramArrayOfByte[(paramInt + 1)] & 0xFF) << 16 | (paramArrayOfByte[(paramInt + 2)] & 0xFF) << 8 | paramArrayOfByte[(paramInt + 3)] & 0xFF;
  }
  
  static void writeShort(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    paramArrayOfByte[paramInt1] = ((byte)(paramInt2 >>> 8));
    paramArrayOfByte[(paramInt1 + 1)] = ((byte)paramInt2);
  }
  
  static int getNewOffset(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2)
  {
    int i = paramInt2 - paramInt1;
    for (int j = 0; j < paramArrayOfInt1.length; j++) {
      if ((paramInt1 < paramArrayOfInt1[j]) && (paramArrayOfInt1[j] <= paramInt2)) {
        i += paramArrayOfInt2[j];
      } else if ((paramInt2 < paramArrayOfInt1[j]) && (paramArrayOfInt1[j] <= paramInt1)) {
        i -= paramArrayOfInt2[j];
      }
    }
    return i;
  }
  
  protected int getCodeSize()
  {
    return this.code.length;
  }
  
  protected byte[] getCode()
  {
    return this.code.data;
  }
  
  static
  {
    int[] arrayOfInt = new int['Ê'];
    String str = "EFFFFFFFFGGFFFGGFFFEEFGFGFEEEEEEEEEEEEEEEEEEEEDEDEDDDDDCDCDEEEEEEEEEEEEEEEEEEEEBABABBBBDCFFFGGGEDCDCDCDCDCDCDCDCDCDCEEEEDDDDDDDCDCDCEFEFDDEEFFDEDEEEBDDBBDDDDDDCCCCCCCCEFEDDDCDCDEEEEEEEEEEFEEEEEEDDEEDDEE";
    for (int i = 0; i < arrayOfInt.length; i++) {
      arrayOfInt[i] = (str.charAt(i) - 'E');
    }
    SIZE = arrayOfInt;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/org/objectweb/asm/CodeWriter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */