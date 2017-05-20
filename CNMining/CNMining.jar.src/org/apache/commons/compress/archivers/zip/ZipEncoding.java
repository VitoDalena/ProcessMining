package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.nio.ByteBuffer;

abstract interface ZipEncoding
{
  public abstract boolean canEncode(String paramString);
  
  public abstract ByteBuffer encode(String paramString)
    throws IOException;
  
  public abstract String decode(byte[] paramArrayOfByte)
    throws IOException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/zip/ZipEncoding.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */