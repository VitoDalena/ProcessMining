package bsh;

import java.io.Serializable;
import java.lang.reflect.Field;

class LHS
  implements ParserConstants, Serializable
{
  NameSpace nameSpace;
  boolean localVar;
  static final int VARIABLE = 0;
  static final int FIELD = 1;
  static final int PROPERTY = 2;
  static final int INDEX = 3;
  static final int METHOD_EVAL = 4;
  int type;
  String varName;
  String propName;
  Field field;
  Object object;
  int index;
  
  LHS(NameSpace paramNameSpace, String paramString)
  {
    throw new Error("namespace lhs");
  }
  
  LHS(NameSpace paramNameSpace, String paramString, boolean paramBoolean)
  {
    this.type = 0;
    this.localVar = paramBoolean;
    this.varName = paramString;
    this.nameSpace = paramNameSpace;
  }
  
  LHS(Field paramField)
  {
    this.type = 1;
    this.object = null;
    this.field = paramField;
  }
  
  LHS(Object paramObject, Field paramField)
  {
    if (paramObject == null) {
      throw new NullPointerException("constructed empty LHS");
    }
    this.type = 1;
    this.object = paramObject;
    this.field = paramField;
  }
  
  LHS(Object paramObject, String paramString)
  {
    if (paramObject == null) {
      throw new NullPointerException("constructed empty LHS");
    }
    this.type = 2;
    this.object = paramObject;
    this.propName = paramString;
  }
  
  LHS(Object paramObject, int paramInt)
  {
    if (paramObject == null) {
      throw new NullPointerException("constructed empty LHS");
    }
    this.type = 3;
    this.object = paramObject;
    this.index = paramInt;
  }
  
  public Object getValue()
    throws UtilEvalError
  {
    if (this.type == 0) {
      return this.nameSpace.getVariable(this.varName);
    }
    if (this.type == 1) {
      try
      {
        Object localObject = this.field.get(this.object);
        return Primitive.wrap(localObject, this.field.getType());
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new UtilEvalError("Can't read field: " + this.field);
      }
    }
    if (this.type == 2) {
      try
      {
        return Reflect.getObjectProperty(this.object, this.propName);
      }
      catch (ReflectError localReflectError)
      {
        Interpreter.debug(localReflectError.getMessage());
        throw new UtilEvalError("No such property: " + this.propName);
      }
    }
    if (this.type == 3) {
      try
      {
        return Reflect.getIndex(this.object, this.index);
      }
      catch (Exception localException)
      {
        throw new UtilEvalError("Array access: " + localException);
      }
    }
    throw new InterpreterError("LHS type");
  }
  
  public Object assign(Object paramObject, boolean paramBoolean)
    throws UtilEvalError
  {
    if (this.type == 0)
    {
      if (this.localVar) {
        this.nameSpace.setLocalVariable(this.varName, paramObject, paramBoolean);
      } else {
        this.nameSpace.setVariable(this.varName, paramObject, paramBoolean);
      }
    }
    else
    {
      if (this.type == 1) {
        try
        {
          Object localObject = (paramObject instanceof Primitive) ? ((Primitive)paramObject).getValue() : paramObject;
          ReflectManager.RMSetAccessible(this.field);
          this.field.set(this.object, localObject);
          return paramObject;
        }
        catch (NullPointerException localNullPointerException)
        {
          throw new UtilEvalError("LHS (" + this.field.getName() + ") not a static field.");
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          throw new UtilEvalError("LHS (" + this.field.getName() + ") can't access field: " + localIllegalAccessException);
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          String str = (paramObject instanceof Primitive) ? ((Primitive)paramObject).getType().getName() : paramObject.getClass().getName();
          throw new UtilEvalError("Argument type mismatch. " + (paramObject == null ? "null" : str) + " not assignable to field " + this.field.getName());
        }
      }
      if (this.type == 2)
      {
        CollectionManager localCollectionManager = CollectionManager.getCollectionManager();
        if (localCollectionManager.isMap(this.object)) {
          localCollectionManager.putInMap(this.object, this.propName, paramObject);
        } else {
          try
          {
            Reflect.setObjectProperty(this.object, this.propName, paramObject);
          }
          catch (ReflectError localReflectError)
          {
            Interpreter.debug("Assignment: " + localReflectError.getMessage());
            throw new UtilEvalError("No such property: " + this.propName);
          }
        }
      }
      else if (this.type == 3)
      {
        try
        {
          Reflect.setIndex(this.object, this.index, paramObject);
        }
        catch (UtilTargetError localUtilTargetError)
        {
          throw localUtilTargetError;
        }
        catch (Exception localException)
        {
          throw new UtilEvalError("Assignment: " + localException.getMessage());
        }
      }
      else
      {
        throw new InterpreterError("unknown lhs");
      }
    }
    return paramObject;
  }
  
  public String toString()
  {
    return "LHS: " + (this.field != null ? "field = " + this.field.toString() : "") + (this.varName != null ? " varName = " + this.varName : "") + (this.nameSpace != null ? " nameSpace = " + this.nameSpace.toString() : "");
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/LHS.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */