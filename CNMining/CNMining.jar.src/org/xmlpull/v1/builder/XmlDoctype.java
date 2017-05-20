package org.xmlpull.v1.builder;

import java.util.Iterator;

public abstract interface XmlDoctype
  extends XmlContainer
{
  public abstract String getSystemIdentifier();
  
  public abstract String getPublicIdentifier();
  
  public abstract Iterator children();
  
  public abstract XmlDocument getParent();
  
  public abstract XmlProcessingInstruction addProcessingInstruction(String paramString1, String paramString2);
  
  public abstract void removeAllProcessingInstructions();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/xmlpull/v1/builder/XmlDoctype.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */