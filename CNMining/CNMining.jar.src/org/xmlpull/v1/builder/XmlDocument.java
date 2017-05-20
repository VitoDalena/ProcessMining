package org.xmlpull.v1.builder;

public abstract interface XmlDocument
  extends XmlContainer, Cloneable
{
  public abstract Object clone()
    throws CloneNotSupportedException;
  
  public abstract Iterable children();
  
  public abstract XmlElement getDocumentElement();
  
  public abstract XmlElement requiredElement(XmlNamespace paramXmlNamespace, String paramString);
  
  public abstract XmlElement element(XmlNamespace paramXmlNamespace, String paramString);
  
  public abstract XmlElement element(XmlNamespace paramXmlNamespace, String paramString, boolean paramBoolean);
  
  public abstract Iterable notations();
  
  public abstract Iterable unparsedEntities();
  
  public abstract String getBaseUri();
  
  public abstract String getCharacterEncodingScheme();
  
  public abstract void setCharacterEncodingScheme(String paramString);
  
  public abstract Boolean isStandalone();
  
  public abstract String getVersion();
  
  public abstract boolean isAllDeclarationsProcessed();
  
  public abstract void setDocumentElement(XmlElement paramXmlElement);
  
  public abstract void addChild(Object paramObject);
  
  public abstract void insertChild(int paramInt, Object paramObject);
  
  public abstract void removeAllChildren();
  
  public abstract XmlComment newComment(String paramString);
  
  public abstract XmlComment addComment(String paramString);
  
  public abstract XmlDoctype newDoctype(String paramString1, String paramString2);
  
  public abstract XmlDoctype addDoctype(String paramString1, String paramString2);
  
  public abstract XmlElement addDocumentElement(String paramString);
  
  public abstract XmlElement addDocumentElement(XmlNamespace paramXmlNamespace, String paramString);
  
  public abstract XmlProcessingInstruction newProcessingInstruction(String paramString1, String paramString2);
  
  public abstract XmlProcessingInstruction addProcessingInstruction(String paramString1, String paramString2);
  
  public abstract void removeAllUnparsedEntities();
  
  public abstract XmlNotation addNotation(String paramString1, String paramString2, String paramString3, String paramString4);
  
  public abstract void removeAllNotations();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/xmlpull/v1/builder/XmlDocument.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */