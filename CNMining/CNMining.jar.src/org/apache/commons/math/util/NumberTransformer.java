package org.apache.commons.math.util;

import java.io.Serializable;
import org.apache.commons.math.MathException;

public abstract interface NumberTransformer
  extends Serializable
{
  public abstract double transform(Object paramObject)
    throws MathException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/util/NumberTransformer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */