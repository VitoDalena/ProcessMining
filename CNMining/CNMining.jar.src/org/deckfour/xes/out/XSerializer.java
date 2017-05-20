package org.deckfour.xes.out;

import java.io.IOException;
import java.io.OutputStream;
import org.deckfour.xes.model.XLog;

public abstract interface XSerializer
{
  public abstract String getName();
  
  public abstract String getDescription();
  
  public abstract String getAuthor();
  
  public abstract String[] getSuffices();
  
  public abstract void serialize(XLog paramXLog, OutputStream paramOutputStream)
    throws IOException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/out/XSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */