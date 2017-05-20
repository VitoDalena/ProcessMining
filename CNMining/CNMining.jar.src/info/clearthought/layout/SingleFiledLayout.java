package info.clearthought.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.io.Serializable;

public class SingleFiledLayout
  implements LayoutManager, Serializable
{
  public static final int COLUMN = 0;
  public static final int ROW = 1;
  public static final int LEFT = 0;
  public static final int TOP = 0;
  public static final int CENTER = 1;
  public static final int FULL = 2;
  public static final int BOTTOM = 3;
  public static final int RIGHT = 4;
  public static int DEFAULT_GAP = 5;
  protected int orientation;
  protected int justification;
  protected int gap;
  
  public SingleFiledLayout()
  {
    this(0, 0, DEFAULT_GAP);
  }
  
  public SingleFiledLayout(int paramInt)
  {
    this(paramInt, 0, DEFAULT_GAP);
  }
  
  public SingleFiledLayout(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 != 1) {
      paramInt1 = 0;
    }
    if ((paramInt2 != 1) && (paramInt2 != 2) && (paramInt2 != 4)) {
      paramInt2 = 0;
    }
    if (paramInt3 < 0) {
      paramInt3 = 0;
    }
    this.orientation = paramInt1;
    this.justification = paramInt2;
    this.gap = paramInt3;
  }
  
  public void layoutContainer(Container paramContainer)
  {
    Dimension localDimension1 = paramContainer.getSize();
    Insets localInsets = paramContainer.getInsets();
    int i = localInsets.left;
    int j = localInsets.top;
    Component[] arrayOfComponent = paramContainer.getComponents();
    int k;
    Dimension localDimension2;
    if (this.orientation == 0) {
      for (k = 0; k < arrayOfComponent.length; k++)
      {
        localDimension2 = arrayOfComponent[k].getPreferredSize();
        switch (this.justification)
        {
        case 0: 
          i = localInsets.left;
          break;
        case 1: 
          i = (localDimension1.width - localDimension2.width >> 1) + localInsets.left - localInsets.right;
          break;
        case 2: 
          i = localInsets.left;
          localDimension2.width = (localDimension1.width - localInsets.left - localInsets.right);
          break;
        case 4: 
          i = localDimension1.width - localDimension2.width - localInsets.right;
        }
        arrayOfComponent[k].setBounds(i, j, localDimension2.width, localDimension2.height);
        j += localDimension2.height + this.gap;
      }
    } else {
      for (k = 0; k < arrayOfComponent.length; k++)
      {
        localDimension2 = arrayOfComponent[k].getPreferredSize();
        switch (this.justification)
        {
        case 0: 
          j = localInsets.top;
          break;
        case 1: 
          j = (localDimension1.height - localDimension2.height >> 1) + localInsets.top - localInsets.bottom;
          break;
        case 2: 
          j = localInsets.top;
          localDimension2.height = (localDimension1.height - localInsets.top - localInsets.bottom);
          break;
        case 3: 
          j = localDimension1.height - localDimension2.height - localInsets.bottom;
        }
        arrayOfComponent[k].setBounds(i, j, localDimension2.width, localDimension2.height);
        i += localDimension2.width + this.gap;
      }
    }
  }
  
  public Dimension preferredLayoutSize(Container paramContainer)
  {
    int i = 0;
    int j = 0;
    Component[] arrayOfComponent = paramContainer.getComponents();
    int k;
    Dimension localDimension;
    if (this.orientation == 0)
    {
      for (k = 0; k < arrayOfComponent.length; k++)
      {
        localDimension = arrayOfComponent[k].getPreferredSize();
        if (i < localDimension.width) {
          i = localDimension.width;
        }
        j += localDimension.height + this.gap;
      }
      j -= this.gap;
    }
    else
    {
      for (k = 0; k < arrayOfComponent.length; k++)
      {
        localDimension = arrayOfComponent[k].getPreferredSize();
        i += localDimension.width + this.gap;
        if (j < localDimension.height) {
          j = localDimension.height;
        }
      }
      i -= this.gap;
    }
    Insets localInsets = paramContainer.getInsets();
    i += localInsets.left + localInsets.right;
    j += localInsets.top + localInsets.bottom;
    return new Dimension(i, j);
  }
  
  public Dimension minimumLayoutSize(Container paramContainer)
  {
    int i = 0;
    int j = 0;
    Component[] arrayOfComponent = paramContainer.getComponents();
    int k;
    Dimension localDimension;
    if (this.orientation == 0)
    {
      for (k = 0; k < arrayOfComponent.length; k++)
      {
        localDimension = arrayOfComponent[k].getMinimumSize();
        if (i < localDimension.width) {
          i = localDimension.width;
        }
        j += localDimension.height + this.gap;
      }
      j -= this.gap;
    }
    else
    {
      for (k = 0; k < arrayOfComponent.length; k++)
      {
        localDimension = arrayOfComponent[k].getMinimumSize();
        i += localDimension.width + this.gap;
        if (j < localDimension.height) {
          j = localDimension.height;
        }
      }
      i = -this.gap;
    }
    Insets localInsets = paramContainer.getInsets();
    i += localInsets.left + localInsets.right;
    j += localInsets.top + localInsets.bottom;
    return new Dimension(i, j);
  }
  
  public void addLayoutComponent(String paramString, Component paramComponent) {}
  
  public void removeLayoutComponent(Component paramComponent) {}
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/info/clearthought/layout/SingleFiledLayout.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */