package bsh.org.objectweb.asm;

public abstract interface CodeVisitor
{
  public abstract void visitInsn(int paramInt);
  
  public abstract void visitIntInsn(int paramInt1, int paramInt2);
  
  public abstract void visitVarInsn(int paramInt1, int paramInt2);
  
  public abstract void visitTypeInsn(int paramInt, String paramString);
  
  public abstract void visitFieldInsn(int paramInt, String paramString1, String paramString2, String paramString3);
  
  public abstract void visitMethodInsn(int paramInt, String paramString1, String paramString2, String paramString3);
  
  public abstract void visitJumpInsn(int paramInt, Label paramLabel);
  
  public abstract void visitLabel(Label paramLabel);
  
  public abstract void visitLdcInsn(Object paramObject);
  
  public abstract void visitIincInsn(int paramInt1, int paramInt2);
  
  public abstract void visitTableSwitchInsn(int paramInt1, int paramInt2, Label paramLabel, Label[] paramArrayOfLabel);
  
  public abstract void visitLookupSwitchInsn(Label paramLabel, int[] paramArrayOfInt, Label[] paramArrayOfLabel);
  
  public abstract void visitMultiANewArrayInsn(String paramString, int paramInt);
  
  public abstract void visitTryCatchBlock(Label paramLabel1, Label paramLabel2, Label paramLabel3, String paramString);
  
  public abstract void visitMaxs(int paramInt1, int paramInt2);
  
  public abstract void visitLocalVariable(String paramString1, String paramString2, Label paramLabel1, Label paramLabel2, int paramInt);
  
  public abstract void visitLineNumber(int paramInt, Label paramLabel);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/org/objectweb/asm/CodeVisitor.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */