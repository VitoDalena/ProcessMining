package org.apache.commons.math.random;

import java.io.Serializable;

public abstract interface RandomGenerator
  extends Serializable
{
  public abstract void setSeed(long paramLong);
  
  public abstract void nextBytes(byte[] paramArrayOfByte);
  
  public abstract int nextInt();
  
  public abstract int nextInt(int paramInt);
  
  public abstract long nextLong();
  
  public abstract boolean nextBoolean();
  
  public abstract float nextFloat();
  
  public abstract double nextDouble();
  
  public abstract double nextGaussian();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/random/RandomGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */