package org.xmlpull.v1.wrapper;

import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public abstract interface XmlSerializerWrapper
  extends XmlSerializer
{
  public static final String NO_NAMESPACE = "";
  public static final String XSI_NS = "http://www.w3.org/2001/XMLSchema-instance";
  public static final String XSD_NS = "http://www.w3.org/2001/XMLSchema";
  
  public abstract String getCurrentNamespaceForElements();
  
  public abstract String setCurrentNamespaceForElements(String paramString);
  
  public abstract XmlSerializerWrapper attribute(String paramString1, String paramString2)
    throws IOException, IllegalArgumentException, IllegalStateException;
  
  public abstract XmlSerializerWrapper startTag(String paramString)
    throws IOException, IllegalArgumentException, IllegalStateException;
  
  public abstract XmlSerializerWrapper endTag(String paramString)
    throws IOException, IllegalArgumentException, IllegalStateException;
  
  public abstract XmlSerializerWrapper element(String paramString1, String paramString2, String paramString3)
    throws IOException, XmlPullParserException;
  
  public abstract XmlSerializerWrapper element(String paramString1, String paramString2)
    throws IOException, XmlPullParserException;
  
  public abstract void fragment(String paramString)
    throws IOException, IllegalArgumentException, IllegalStateException, XmlPullParserException;
  
  public abstract void event(XmlPullParser paramXmlPullParser)
    throws IOException, IllegalArgumentException, IllegalStateException, XmlPullParserException;
  
  public abstract String escapeText(String paramString)
    throws IllegalArgumentException;
  
  public abstract String escapeAttributeValue(String paramString)
    throws IllegalArgumentException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/xmlpull/v1/wrapper/XmlSerializerWrapper.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */