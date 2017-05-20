package com.jgraph.io.svg;

import java.awt.Color;

public class SVGUtils
{
  public static int LINESPACING = 4;
  public static String HEXCOLOR_BLACK = getHexEncoding(Color.BLACK);
  public static String HEXCOLOR_WHITE = getHexEncoding(Color.WHITE);
  
  public static String getHexEncoding(Color paramColor)
  {
    String str = "";
    if (paramColor == null)
    {
      str = "none";
    }
    else
    {
      for (str = Integer.toHexString(paramColor.getRGB() & 0xFFFFFF); str.length() < 6; str = str + "0") {}
      str = "#" + str;
    }
    return str;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/io/svg/SVGUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */