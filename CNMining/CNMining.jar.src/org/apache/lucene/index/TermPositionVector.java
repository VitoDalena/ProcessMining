package org.apache.lucene.index;

public abstract interface TermPositionVector
  extends TermFreqVector
{
  public abstract int[] getTermPositions(int paramInt);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/TermPositionVector.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */