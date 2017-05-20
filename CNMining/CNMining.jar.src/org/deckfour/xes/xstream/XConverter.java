package org.deckfour.xes.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;

public abstract class XConverter
  implements Converter
{
  public void registerAliases(XStream stream) {}
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/xstream/XConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */