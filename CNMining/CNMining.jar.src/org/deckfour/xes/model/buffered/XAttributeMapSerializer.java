package org.deckfour.xes.model.buffered;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.deckfour.xes.model.XAttributeMap;

public abstract interface XAttributeMapSerializer
{
  public abstract void serialize(XAttributeMap paramXAttributeMap, DataOutput paramDataOutput)
    throws IOException;
  
  public abstract XAttributeMap deserialize(DataInput paramDataInput)
    throws IOException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/buffered/XAttributeMapSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */