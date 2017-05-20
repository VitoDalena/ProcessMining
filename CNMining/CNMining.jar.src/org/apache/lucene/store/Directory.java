package org.apache.lucene.store;

import java.io.IOException;

public abstract class Directory
{
  public abstract String[] list()
    throws IOException;
  
  public abstract boolean fileExists(String paramString)
    throws IOException;
  
  public abstract long fileModified(String paramString)
    throws IOException;
  
  public abstract void touchFile(String paramString)
    throws IOException;
  
  public abstract void deleteFile(String paramString)
    throws IOException;
  
  public abstract void renameFile(String paramString1, String paramString2)
    throws IOException;
  
  public abstract long fileLength(String paramString)
    throws IOException;
  
  public abstract OutputStream createFile(String paramString)
    throws IOException;
  
  public abstract InputStream openFile(String paramString)
    throws IOException;
  
  public abstract Lock makeLock(String paramString);
  
  public abstract void close()
    throws IOException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/store/Directory.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */