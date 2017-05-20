package bsh.util;

import bsh.EvalError;
import bsh.Interpreter;
import bsh.InterpreterError;
import bsh.Primitive;
import bsh.TargetError;
import bsh.This;
import java.util.Vector;
import org.apache.bsf.BSFDeclaredBean;
import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;
import org.apache.bsf.util.BSFEngineImpl;

public class BeanShellBSFEngine
  extends BSFEngineImpl
{
  Interpreter interpreter;
  boolean installedApplyMethod;
  static final String bsfApplyMethod = "_bsfApply( _bsfNames, _bsfArgs, _bsfText ) {for(i=0;i<_bsfNames.length;i++)this.namespace.setVariable(_bsfNames[i], _bsfArgs[i],false);return this.interpreter.eval(_bsfText, this.namespace);}";
  
  public void initialize(BSFManager paramBSFManager, String paramString, Vector paramVector)
    throws BSFException
  {
    super.initialize(paramBSFManager, paramString, paramVector);
    this.interpreter = new Interpreter();
    try
    {
      this.interpreter.set("bsf", paramBSFManager);
    }
    catch (EvalError localEvalError)
    {
      throw new BSFException("bsh internal error: " + localEvalError.toString());
    }
    for (int i = 0; i < paramVector.size(); i++)
    {
      BSFDeclaredBean localBSFDeclaredBean = (BSFDeclaredBean)paramVector.get(i);
      declareBean(localBSFDeclaredBean);
    }
  }
  
  public void setDebug(boolean paramBoolean)
  {
    Interpreter.DEBUG = paramBoolean;
  }
  
  public Object call(Object paramObject, String paramString, Object[] paramArrayOfObject)
    throws BSFException
  {
    if (paramObject == null) {
      try
      {
        paramObject = this.interpreter.get("global");
      }
      catch (EvalError localEvalError1)
      {
        throw new BSFException("bsh internal error: " + localEvalError1.toString());
      }
    }
    if ((paramObject instanceof This)) {
      try
      {
        Object localObject = ((This)paramObject).invokeMethod(paramString, paramArrayOfObject);
        return Primitive.unwrap(localObject);
      }
      catch (InterpreterError localInterpreterError)
      {
        throw new BSFException("BeanShell interpreter internal error: " + localInterpreterError);
      }
      catch (TargetError localTargetError)
      {
        throw new BSFException("The application script threw an exception: " + localTargetError.getTarget());
      }
      catch (EvalError localEvalError2)
      {
        throw new BSFException("BeanShell script error: " + localEvalError2);
      }
    }
    throw new BSFException("Cannot invoke method: " + paramString + ". Object: " + paramObject + " is not a BeanShell scripted object.");
  }
  
  public Object apply(String paramString, int paramInt1, int paramInt2, Object paramObject, Vector paramVector1, Vector paramVector2)
    throws BSFException
  {
    if (paramVector1.size() != paramVector2.size()) {
      throw new BSFException("number of params/names mismatch");
    }
    if (!(paramObject instanceof String)) {
      throw new BSFException("apply: functino body must be a string");
    }
    String[] arrayOfString = new String[paramVector1.size()];
    paramVector1.copyInto(arrayOfString);
    Object[] arrayOfObject = new Object[paramVector2.size()];
    paramVector2.copyInto(arrayOfObject);
    try
    {
      if (!this.installedApplyMethod)
      {
        this.interpreter.eval("_bsfApply( _bsfNames, _bsfArgs, _bsfText ) {for(i=0;i<_bsfNames.length;i++)this.namespace.setVariable(_bsfNames[i], _bsfArgs[i],false);return this.interpreter.eval(_bsfText, this.namespace);}");
        this.installedApplyMethod = true;
      }
      This localThis = (This)this.interpreter.get("global");
      Object localObject = localThis.invokeMethod("_bsfApply", new Object[] { arrayOfString, arrayOfObject, (String)paramObject });
      return Primitive.unwrap(localObject);
    }
    catch (InterpreterError localInterpreterError)
    {
      throw new BSFException("BeanShell interpreter internal error: " + localInterpreterError + sourceInfo(paramString, paramInt1, paramInt2));
    }
    catch (TargetError localTargetError)
    {
      throw new BSFException("The application script threw an exception: " + localTargetError.getTarget() + sourceInfo(paramString, paramInt1, paramInt2));
    }
    catch (EvalError localEvalError)
    {
      throw new BSFException("BeanShell script error: " + localEvalError + sourceInfo(paramString, paramInt1, paramInt2));
    }
  }
  
  public Object eval(String paramString, int paramInt1, int paramInt2, Object paramObject)
    throws BSFException
  {
    if (!(paramObject instanceof String)) {
      throw new BSFException("BeanShell expression must be a string");
    }
    try
    {
      return this.interpreter.eval((String)paramObject);
    }
    catch (InterpreterError localInterpreterError)
    {
      throw new BSFException("BeanShell interpreter internal error: " + localInterpreterError + sourceInfo(paramString, paramInt1, paramInt2));
    }
    catch (TargetError localTargetError)
    {
      throw new BSFException("The application script threw an exception: " + localTargetError.getTarget() + sourceInfo(paramString, paramInt1, paramInt2));
    }
    catch (EvalError localEvalError)
    {
      throw new BSFException("BeanShell script error: " + localEvalError + sourceInfo(paramString, paramInt1, paramInt2));
    }
  }
  
  public void exec(String paramString, int paramInt1, int paramInt2, Object paramObject)
    throws BSFException
  {
    eval(paramString, paramInt1, paramInt2, paramObject);
  }
  
  public void declareBean(BSFDeclaredBean paramBSFDeclaredBean)
    throws BSFException
  {
    try
    {
      this.interpreter.set(paramBSFDeclaredBean.name, paramBSFDeclaredBean.bean);
    }
    catch (EvalError localEvalError)
    {
      throw new BSFException("error declaring bean: " + paramBSFDeclaredBean.name + " : " + localEvalError.toString());
    }
  }
  
  public void undeclareBean(BSFDeclaredBean paramBSFDeclaredBean)
    throws BSFException
  {
    try
    {
      this.interpreter.unset(paramBSFDeclaredBean.name);
    }
    catch (EvalError localEvalError)
    {
      throw new BSFException("bsh internal error: " + localEvalError.toString());
    }
  }
  
  public void terminate() {}
  
  private String sourceInfo(String paramString, int paramInt1, int paramInt2)
  {
    return " BSF info: " + paramString + " at line: " + paramInt1 + " column: columnNo";
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/util/BeanShellBSFEngine.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */