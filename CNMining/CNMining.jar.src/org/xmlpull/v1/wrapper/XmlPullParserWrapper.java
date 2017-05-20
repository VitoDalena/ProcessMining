package org.xmlpull.v1.wrapper;

import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public abstract interface XmlPullParserWrapper
  extends XmlPullParser
{
  public static final String XSI_NS = "http://www.w3.org/2001/XMLSchema-instance";
  public static final String XSD_NS = "http://www.w3.org/2001/XMLSchema";
  
  public abstract String getAttributeValue(String paramString);
  
  public abstract String getPITarget()
    throws IllegalStateException;
  
  public abstract String getPIData()
    throws IllegalStateException;
  
  public abstract String getRequiredAttributeValue(String paramString)
    throws IOException, XmlPullParserException;
  
  public abstract String getRequiredAttributeValue(String paramString1, String paramString2)
    throws IOException, XmlPullParserException;
  
  public abstract String getRequiredElementText(String paramString1, String paramString2)
    throws IOException, XmlPullParserException;
  
  public abstract boolean isNil()
    throws IOException, XmlPullParserException;
  
  public abstract boolean matches(int paramInt, String paramString1, String paramString2)
    throws XmlPullParserException;
  
  public abstract void nextStartTag()
    throws XmlPullParserException, IOException;
  
  public abstract void nextStartTag(String paramString)
    throws XmlPullParserException, IOException;
  
  public abstract void nextStartTag(String paramString1, String paramString2)
    throws XmlPullParserException, IOException;
  
  public abstract void nextEndTag()
    throws XmlPullParserException, IOException;
  
  public abstract void nextEndTag(String paramString)
    throws XmlPullParserException, IOException;
  
  public abstract void nextEndTag(String paramString1, String paramString2)
    throws XmlPullParserException, IOException;
  
  public abstract String nextText(String paramString1, String paramString2)
    throws IOException, XmlPullParserException;
  
  public abstract void skipSubTree()
    throws XmlPullParserException, IOException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/xmlpull/v1/wrapper/XmlPullParserWrapper.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */