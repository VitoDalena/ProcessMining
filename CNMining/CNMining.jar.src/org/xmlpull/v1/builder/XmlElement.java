package org.xmlpull.v1.builder;

import java.util.Iterator;

public abstract interface XmlElement
  extends XmlContainer, XmlContained, Cloneable
{
  public static final String NO_NAMESPACE = "";
  
  public abstract Object clone()
    throws CloneNotSupportedException;
  
  public abstract String getBaseUri();
  
  public abstract void setBaseUri(String paramString);
  
  public abstract XmlContainer getRoot();
  
  public abstract XmlContainer getParent();
  
  public abstract void setParent(XmlContainer paramXmlContainer);
  
  public abstract XmlNamespace getNamespace();
  
  public abstract String getNamespaceName();
  
  public abstract void setNamespace(XmlNamespace paramXmlNamespace);
  
  public abstract String getName();
  
  public abstract void setName(String paramString);
  
  public abstract Iterator attributes();
  
  public abstract XmlAttribute addAttribute(XmlAttribute paramXmlAttribute);
  
  public abstract XmlAttribute addAttribute(String paramString1, String paramString2);
  
  public abstract XmlAttribute addAttribute(XmlNamespace paramXmlNamespace, String paramString1, String paramString2);
  
  public abstract XmlAttribute addAttribute(String paramString1, XmlNamespace paramXmlNamespace, String paramString2, String paramString3);
  
  public abstract XmlAttribute addAttribute(String paramString1, XmlNamespace paramXmlNamespace, String paramString2, String paramString3, boolean paramBoolean);
  
  public abstract XmlAttribute addAttribute(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, boolean paramBoolean);
  
  public abstract void ensureAttributeCapacity(int paramInt);
  
  public abstract String getAttributeValue(String paramString1, String paramString2);
  
  public abstract XmlAttribute attribute(String paramString);
  
  public abstract XmlAttribute attribute(XmlNamespace paramXmlNamespace, String paramString);
  
  /**
   * @deprecated
   */
  public abstract XmlAttribute findAttribute(String paramString1, String paramString2);
  
  public abstract boolean hasAttributes();
  
  public abstract void removeAttribute(XmlAttribute paramXmlAttribute);
  
  public abstract void removeAllAttributes();
  
  public abstract Iterator namespaces();
  
  public abstract XmlNamespace declareNamespace(String paramString1, String paramString2);
  
  public abstract XmlNamespace declareNamespace(XmlNamespace paramXmlNamespace);
  
  public abstract void ensureNamespaceDeclarationsCapacity(int paramInt);
  
  public abstract boolean hasNamespaceDeclarations();
  
  public abstract XmlNamespace lookupNamespaceByPrefix(String paramString);
  
  public abstract XmlNamespace lookupNamespaceByName(String paramString);
  
  public abstract XmlNamespace newNamespace(String paramString);
  
  public abstract XmlNamespace newNamespace(String paramString1, String paramString2);
  
  public abstract void removeAllNamespaceDeclarations();
  
  public abstract Iterator children();
  
  public abstract void addChild(Object paramObject);
  
  public abstract void addChild(int paramInt, Object paramObject);
  
  public abstract XmlElement addElement(XmlElement paramXmlElement);
  
  public abstract XmlElement addElement(int paramInt, XmlElement paramXmlElement);
  
  public abstract XmlElement addElement(String paramString);
  
  public abstract XmlElement addElement(XmlNamespace paramXmlNamespace, String paramString);
  
  public abstract boolean hasChildren();
  
  public abstract boolean hasChild(Object paramObject);
  
  public abstract void ensureChildrenCapacity(int paramInt);
  
  /**
   * @deprecated
   */
  public abstract XmlElement findElementByName(String paramString);
  
  /**
   * @deprecated
   */
  public abstract XmlElement findElementByName(String paramString1, String paramString2);
  
  /**
   * @deprecated
   */
  public abstract XmlElement findElementByName(String paramString, XmlElement paramXmlElement);
  
  /**
   * @deprecated
   */
  public abstract XmlElement findElementByName(String paramString1, String paramString2, XmlElement paramXmlElement);
  
  public abstract XmlElement element(int paramInt);
  
  public abstract XmlElement requiredElement(XmlNamespace paramXmlNamespace, String paramString)
    throws XmlBuilderException;
  
  public abstract XmlElement element(XmlNamespace paramXmlNamespace, String paramString);
  
  public abstract XmlElement element(XmlNamespace paramXmlNamespace, String paramString, boolean paramBoolean);
  
  public abstract Iterable elements(XmlNamespace paramXmlNamespace, String paramString);
  
  public abstract void insertChild(int paramInt, Object paramObject);
  
  public abstract XmlElement newElement(String paramString);
  
  public abstract XmlElement newElement(XmlNamespace paramXmlNamespace, String paramString);
  
  public abstract XmlElement newElement(String paramString1, String paramString2);
  
  public abstract void removeAllChildren();
  
  public abstract void removeChild(Object paramObject);
  
  public abstract void replaceChild(Object paramObject1, Object paramObject2);
  
  public abstract Iterable requiredElementContent();
  
  public abstract String requiredTextContent();
  
  public abstract void replaceChildrenWithText(String paramString);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/xmlpull/v1/builder/XmlElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */