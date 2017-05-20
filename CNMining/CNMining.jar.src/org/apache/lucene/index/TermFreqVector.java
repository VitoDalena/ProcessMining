package org.apache.lucene.index;

public abstract interface TermFreqVector
{
  public abstract String getField();
  
  public abstract int size();
  
  public abstract String[] getTerms();
  
  public abstract int[] getTermFrequencies();
  
  public abstract int indexOf(String paramString);
  
  public abstract int[] indexesOf(String[] paramArrayOfString, int paramInt1, int paramInt2);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/TermFreqVector.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */