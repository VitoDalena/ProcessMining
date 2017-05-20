package bsh.org.objectweb.asm;

public abstract interface ClassVisitor
{
  public abstract void visit(int paramInt, String paramString1, String paramString2, String[] paramArrayOfString, String paramString3);
  
  public abstract void visitInnerClass(String paramString1, String paramString2, String paramString3, int paramInt);
  
  public abstract void visitField(int paramInt, String paramString1, String paramString2, Object paramObject);
  
  public abstract CodeVisitor visitMethod(int paramInt, String paramString1, String paramString2, String[] paramArrayOfString);
  
  public abstract void visitEnd();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/org/objectweb/asm/ClassVisitor.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */