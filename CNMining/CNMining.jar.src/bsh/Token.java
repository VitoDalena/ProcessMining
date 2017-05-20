package bsh;

import java.io.Serializable;

public class Token
  implements Serializable
{
  public int kind;
  public int beginLine;
  public int beginColumn;
  public int endLine;
  public int endColumn;
  public String image;
  public Token next;
  public Token specialToken;
  
  public String toString()
  {
    return this.image;
  }
  
  public static final Token newToken(int paramInt)
  {
    switch (paramInt)
    {
    }
    return new Token();
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/Token.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */