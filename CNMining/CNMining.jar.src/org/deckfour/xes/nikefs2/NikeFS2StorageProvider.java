package org.deckfour.xes.nikefs2;

import java.io.IOException;

public abstract interface NikeFS2StorageProvider
{
  public abstract NikeFS2RandomAccessStorage createStorage()
    throws IOException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/nikefs2/NikeFS2StorageProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */