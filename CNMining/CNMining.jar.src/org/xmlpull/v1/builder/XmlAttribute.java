package org.xmlpull.v1.builder;

public abstract interface XmlAttribute
  extends Cloneable
{
  public abstract Object clone()
    throws CloneNotSupportedException;
  
  public abstract XmlElement getOwner();
  
  public abstract String getNamespaceName();
  
  public abstract XmlNamespace getNamespace();
  
  public abstract String getName();
  
  public abstract String getValue();
  
  public abstract String getType();
  
  public abstract boolean isSpecified();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/xmlpull/v1/builder/XmlAttribute.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */