package org.xmlpull.v1.builder;

public abstract interface XmlProcessingInstruction
{
  public abstract String getTarget();
  
  public abstract String getContent();
  
  public abstract String getBaseUri();
  
  public abstract XmlNotation getNotation();
  
  public abstract XmlContainer getParent();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/xmlpull/v1/builder/XmlProcessingInstruction.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */