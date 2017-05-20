package org.apache.commons.compress.archivers.zip;

public abstract interface UnixStat
{
  public static final int PERM_MASK = 4095;
  public static final int LINK_FLAG = 40960;
  public static final int FILE_FLAG = 32768;
  public static final int DIR_FLAG = 16384;
  public static final int DEFAULT_LINK_PERM = 511;
  public static final int DEFAULT_DIR_PERM = 493;
  public static final int DEFAULT_FILE_PERM = 420;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/zip/UnixStat.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */