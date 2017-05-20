package com.jgraph.components.labels;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;

public class RichTextValue
  implements Serializable, Cloneable
{
  public static RTFEditorKit editorKit = new RTFEditorKit();
  protected String richText;
  protected String plainText;
  
  public RichTextValue() {}
  
  public RichTextValue(Document paramDocument)
  {
    setRichText(getRichText(paramDocument));
  }
  
  public RichTextValue(String paramString)
  {
    this(createDefaultDocument(paramString));
  }
  
  public void insertInto(Document paramDocument)
  {
    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(this.richText.getBytes());
    try
    {
      paramDocument.remove(0, paramDocument.getLength());
      editorKit.read(localByteArrayInputStream, paramDocument, 0);
      localByteArrayInputStream.close();
    }
    catch (Exception localException) {}
  }
  
  public String getRichText()
  {
    return this.richText;
  }
  
  public void setRichText(String paramString)
  {
    this.richText = paramString;
    this.plainText = getPlainText(this);
  }
  
  public String toString()
  {
    return this.plainText;
  }
  
  public static String getRichText(Document paramDocument)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      editorKit.write(localByteArrayOutputStream, paramDocument, 0, paramDocument.getLength());
      localByteArrayOutputStream.flush();
    }
    catch (Exception localException) {}
    return new String(localByteArrayOutputStream.toByteArray());
  }
  
  public static String getPlainText(RichTextValue paramRichTextValue)
  {
    Document localDocument = createDefaultDocument();
    paramRichTextValue.insertInto(localDocument);
    try
    {
      return localDocument.getText(0, localDocument.getLength()).trim();
    }
    catch (BadLocationException localBadLocationException) {}
    return paramRichTextValue.getRichText();
  }
  
  protected static Document createDefaultDocument()
  {
    return createDefaultDocument(null);
  }
  
  public static Document createDefaultDocument(String paramString)
  {
    Document localDocument = editorKit.createDefaultDocument();
    if (paramString != null) {
      try
      {
        localDocument.insertString(0, paramString, null);
      }
      catch (BadLocationException localBadLocationException) {}
    }
    return localDocument;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/components/labels/RichTextValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */