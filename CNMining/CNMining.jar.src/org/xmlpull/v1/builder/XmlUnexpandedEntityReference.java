package org.xmlpull.v1.builder;

public abstract interface XmlUnexpandedEntityReference
{
  public abstract String getName();
  
  public abstract String getSystemIdentifier();
  
  public abstract String getPublicIdentifier();
  
  public abstract String getDeclarationBaseUri();
  
  public abstract XmlElement getParent();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/xmlpull/v1/builder/XmlUnexpandedEntityReference.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */