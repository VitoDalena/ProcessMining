package bsh.util;

import bsh.EvalError;
import bsh.Interpreter;
import bsh.This;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JComponent;

public class BshCanvas
  extends JComponent
{
  This ths;
  Image imageBuffer;
  
  public BshCanvas() {}
  
  public BshCanvas(This paramThis)
  {
    this.ths = paramThis;
  }
  
  public void paintComponent(Graphics paramGraphics)
  {
    if (this.imageBuffer != null) {
      paramGraphics.drawImage(this.imageBuffer, 0, 0, this);
    }
    if (this.ths != null) {
      try
      {
        this.ths.invokeMethod("paint", new Object[] { paramGraphics });
      }
      catch (EvalError localEvalError)
      {
        if (Interpreter.DEBUG) {
          Interpreter.debug("BshCanvas: method invocation error:" + localEvalError);
        }
      }
    }
  }
  
  public Graphics getBufferedGraphics()
  {
    Dimension localDimension = getSize();
    this.imageBuffer = createImage(localDimension.width, localDimension.height);
    return this.imageBuffer.getGraphics();
  }
  
  public void setBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    setPreferredSize(new Dimension(paramInt3, paramInt4));
    setMinimumSize(new Dimension(paramInt3, paramInt4));
    super.setBounds(paramInt1, paramInt2, paramInt3, paramInt4);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/util/BshCanvas.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */