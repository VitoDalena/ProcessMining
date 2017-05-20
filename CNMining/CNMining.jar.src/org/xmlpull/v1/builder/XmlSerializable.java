package org.xmlpull.v1.builder;

import java.io.IOException;
import org.xmlpull.v1.XmlSerializer;

public abstract interface XmlSerializable
{
  public abstract void serialize(XmlSerializer paramXmlSerializer)
    throws IOException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/xmlpull/v1/builder/XmlSerializable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */