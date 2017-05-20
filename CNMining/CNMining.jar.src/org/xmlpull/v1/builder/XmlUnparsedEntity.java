package org.xmlpull.v1.builder;

public abstract interface XmlUnparsedEntity
{
  public abstract String getName();
  
  public abstract String getSystemIdentifier();
  
  public abstract String getPublicIdentifier();
  
  public abstract String getDeclarationBaseUri();
  
  public abstract String getNotationName();
  
  public abstract XmlNotation getNotation();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/xmlpull/v1/builder/XmlUnparsedEntity.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */