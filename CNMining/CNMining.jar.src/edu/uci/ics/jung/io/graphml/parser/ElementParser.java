package edu.uci.ics.jung.io.graphml.parser;

import edu.uci.ics.jung.io.GraphIOException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.StartElement;

public abstract interface ElementParser
{
  public abstract Object parse(XMLEventReader paramXMLEventReader, StartElement paramStartElement)
    throws GraphIOException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/parser/ElementParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */