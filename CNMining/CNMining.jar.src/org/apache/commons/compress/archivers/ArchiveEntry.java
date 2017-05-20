package org.apache.commons.compress.archivers;

public abstract interface ArchiveEntry
{
  public static final long SIZE_UNKNOWN = -1L;
  
  public abstract String getName();
  
  public abstract long getSize();
  
  public abstract boolean isDirectory();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/ArchiveEntry.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */