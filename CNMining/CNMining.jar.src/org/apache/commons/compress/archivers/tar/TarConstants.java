package org.apache.commons.compress.archivers.tar;

public abstract interface TarConstants
{
  public static final int NAMELEN = 100;
  public static final int MODELEN = 8;
  public static final int UIDLEN = 8;
  public static final int GIDLEN = 8;
  public static final int CHKSUMLEN = 8;
  public static final int SIZELEN = 12;
  public static final long MAXSIZE = 8589934591L;
  public static final int MAGIC_OFFSET = 257;
  public static final int MAGICLEN = 6;
  public static final int VERSION_OFFSET = 263;
  public static final int VERSIONLEN = 2;
  public static final int MODTIMELEN = 12;
  public static final int UNAMELEN = 32;
  public static final int GNAMELEN = 32;
  public static final int DEVLEN = 8;
  public static final int PREFIXLEN = 155;
  public static final byte LF_OLDNORM = 0;
  public static final byte LF_NORMAL = 48;
  public static final byte LF_LINK = 49;
  public static final byte LF_SYMLINK = 50;
  public static final byte LF_CHR = 51;
  public static final byte LF_BLK = 52;
  public static final byte LF_DIR = 53;
  public static final byte LF_FIFO = 54;
  public static final byte LF_CONTIG = 55;
  public static final String MAGIC_POSIX = "ustar\000";
  public static final String VERSION_POSIX = "00";
  public static final String MAGIC_GNU = "ustar ";
  public static final String VERSION_GNU_SPACE = " \000";
  public static final String VERSION_GNU_ZERO = "0\000";
  public static final String GNU_LONGLINK = "././@LongLink";
  public static final byte LF_GNUTYPE_LONGNAME = 76;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/tar/TarConstants.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */