package info.clearthought.layout;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TableLayout
  implements LayoutManager2, Serializable, TableLayoutConstants
{
  protected static final double[][] defaultSize = { new double[0], new double[0] };
  protected static final int C = 0;
  protected static final int R = 1;
  protected static boolean checkForComponentOrientationSupport = true;
  protected static Method methodGetComponentOrientation;
  protected double[][] crSpec = { null, null };
  protected int[][] crSize = { null, null };
  protected int[][] crOffset = { null, null };
  protected LinkedList list;
  protected boolean dirty;
  protected int oldWidth;
  protected int oldHeight;
  protected int hGap;
  protected int vGap;
  
  public TableLayout()
  {
    init(defaultSize[0], defaultSize[1]);
  }
  
  public TableLayout(double[][] paramArrayOfDouble)
  {
    if ((paramArrayOfDouble != null) && (paramArrayOfDouble.length == 2)) {
      init(paramArrayOfDouble[0], paramArrayOfDouble[1]);
    } else {
      throw new IllegalArgumentException("Parameter size should be an array, a[2], where a[0] is the is an array of column widths and a[1] is an array or row heights.");
    }
  }
  
  public TableLayout(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    init(paramArrayOfDouble1, paramArrayOfDouble2);
  }
  
  protected void init(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    if (paramArrayOfDouble1 == null) {
      throw new IllegalArgumentException("Parameter col cannot be null");
    }
    if (paramArrayOfDouble2 == null) {
      throw new IllegalArgumentException("Parameter row cannot be null");
    }
    this.crSpec[0] = new double[paramArrayOfDouble1.length];
    this.crSpec[1] = new double[paramArrayOfDouble2.length];
    System.arraycopy(paramArrayOfDouble1, 0, this.crSpec[0], 0, this.crSpec[0].length);
    System.arraycopy(paramArrayOfDouble2, 0, this.crSpec[1], 0, this.crSpec[1].length);
    for (int i = 0; i < this.crSpec[0].length; i++) {
      if ((this.crSpec[0][i] < 0.0D) && (this.crSpec[0][i] != -1.0D) && (this.crSpec[0][i] != -2.0D) && (this.crSpec[0][i] != -3.0D)) {
        this.crSpec[0][i] = 0.0D;
      }
    }
    for (i = 0; i < this.crSpec[1].length; i++) {
      if ((this.crSpec[1][i] < 0.0D) && (this.crSpec[1][i] != -1.0D) && (this.crSpec[1][i] != -2.0D) && (this.crSpec[1][i] != -3.0D)) {
        this.crSpec[1][i] = 0.0D;
      }
    }
    this.list = new LinkedList();
    this.dirty = true;
  }
  
  public TableLayoutConstraints getConstraints(Component paramComponent)
  {
    ListIterator localListIterator = this.list.listIterator(0);
    while (localListIterator.hasNext())
    {
      Entry localEntry = (Entry)localListIterator.next();
      if (localEntry.component == paramComponent) {
        return new TableLayoutConstraints(localEntry.cr1[0], localEntry.cr1[1], localEntry.cr2[0], localEntry.cr2[1], localEntry.alignment[0], localEntry.alignment[1]);
      }
    }
    return null;
  }
  
  public void setConstraints(Component paramComponent, TableLayoutConstraints paramTableLayoutConstraints)
  {
    if (paramComponent == null) {
      throw new IllegalArgumentException("Parameter component cannot be null.");
    }
    if (paramTableLayoutConstraints == null) {
      throw new IllegalArgumentException("Parameter constraint cannot be null.");
    }
    ListIterator localListIterator = this.list.listIterator(0);
    while (localListIterator.hasNext())
    {
      Entry localEntry = (Entry)localListIterator.next();
      if (localEntry.component == paramComponent) {
        localListIterator.set(new Entry(paramComponent, paramTableLayoutConstraints));
      }
    }
  }
  
  public void setColumn(double[] paramArrayOfDouble)
  {
    setCr(0, paramArrayOfDouble);
  }
  
  public void setRow(double[] paramArrayOfDouble)
  {
    setCr(1, paramArrayOfDouble);
  }
  
  protected void setCr(int paramInt, double[] paramArrayOfDouble)
  {
    this.crSpec[paramInt] = new double[paramArrayOfDouble.length];
    System.arraycopy(paramArrayOfDouble, 0, this.crSpec[paramInt], 0, this.crSpec[paramInt].length);
    for (int i = 0; i < this.crSpec[paramInt].length; i++) {
      if ((this.crSpec[paramInt][i] < 0.0D) && (this.crSpec[paramInt][i] != -1.0D) && (this.crSpec[paramInt][i] != -2.0D) && (this.crSpec[paramInt][i] != -3.0D)) {
        this.crSpec[paramInt][i] = 0.0D;
      }
    }
    this.dirty = true;
  }
  
  public void setColumn(int paramInt, double paramDouble)
  {
    setCr(0, paramInt, paramDouble);
  }
  
  public void setRow(int paramInt, double paramDouble)
  {
    setCr(1, paramInt, paramDouble);
  }
  
  protected void setCr(int paramInt1, int paramInt2, double paramDouble)
  {
    if ((paramDouble < 0.0D) && (paramDouble != -1.0D) && (paramDouble != -2.0D) && (paramDouble != -3.0D)) {
      paramDouble = 0.0D;
    }
    this.crSpec[paramInt1][paramInt2] = paramDouble;
    this.dirty = true;
  }
  
  public double[] getColumn()
  {
    double[] arrayOfDouble = new double[this.crSpec[0].length];
    System.arraycopy(this.crSpec[0], 0, arrayOfDouble, 0, arrayOfDouble.length);
    return arrayOfDouble;
  }
  
  public double[] getRow()
  {
    double[] arrayOfDouble = new double[this.crSpec[1].length];
    System.arraycopy(this.crSpec[1], 0, arrayOfDouble, 0, arrayOfDouble.length);
    return arrayOfDouble;
  }
  
  public double getColumn(int paramInt)
  {
    return this.crSpec[0][paramInt];
  }
  
  public double getRow(int paramInt)
  {
    return this.crSpec[1][paramInt];
  }
  
  public int getNumColumn()
  {
    return this.crSpec[0].length;
  }
  
  public int getNumRow()
  {
    return this.crSpec[1].length;
  }
  
  public int getHGap()
  {
    return this.hGap;
  }
  
  public int getVGap()
  {
    return this.vGap;
  }
  
  public void setHGap(int paramInt)
  {
    if (paramInt >= 0) {
      this.hGap = paramInt;
    } else {
      throw new IllegalArgumentException("Parameter hGap must be non-negative.");
    }
  }
  
  public void setVGap(int paramInt)
  {
    if (paramInt >= 0) {
      this.vGap = paramInt;
    } else {
      throw new IllegalArgumentException("Parameter vGap must be non-negative.");
    }
  }
  
  public void insertColumn(int paramInt, double paramDouble)
  {
    insertCr(0, paramInt, paramDouble);
  }
  
  public void insertRow(int paramInt, double paramDouble)
  {
    insertCr(1, paramInt, paramDouble);
  }
  
  public void insertCr(int paramInt1, int paramInt2, double paramDouble)
  {
    if ((paramInt2 < 0) || (paramInt2 > this.crSpec[paramInt1].length)) {
      throw new IllegalArgumentException("Parameter i is invalid.  i = " + paramInt2 + ".  Valid range is [0, " + this.crSpec[paramInt1].length + "].");
    }
    if ((paramDouble < 0.0D) && (paramDouble != -1.0D) && (paramDouble != -2.0D) && (paramDouble != -3.0D)) {
      paramDouble = 0.0D;
    }
    double[] arrayOfDouble = new double[this.crSpec[paramInt1].length + 1];
    System.arraycopy(this.crSpec[paramInt1], 0, arrayOfDouble, 0, paramInt2);
    System.arraycopy(this.crSpec[paramInt1], paramInt2, arrayOfDouble, paramInt2 + 1, this.crSpec[paramInt1].length - paramInt2);
    arrayOfDouble[paramInt2] = paramDouble;
    this.crSpec[paramInt1] = arrayOfDouble;
    ListIterator localListIterator = this.list.listIterator(0);
    while (localListIterator.hasNext())
    {
      Entry localEntry = (Entry)localListIterator.next();
      if (localEntry.cr1[paramInt1] >= paramInt2) {
        localEntry.cr1[paramInt1] += 1;
      }
      if (localEntry.cr2[paramInt1] >= paramInt2) {
        localEntry.cr2[paramInt1] += 1;
      }
    }
    this.dirty = true;
  }
  
  public void deleteColumn(int paramInt)
  {
    deleteCr(0, paramInt);
  }
  
  public void deleteRow(int paramInt)
  {
    deleteCr(1, paramInt);
  }
  
  protected void deleteCr(int paramInt1, int paramInt2)
  {
    if ((paramInt2 < 0) || (paramInt2 >= this.crSpec[paramInt1].length)) {
      throw new IllegalArgumentException("Parameter i is invalid.  i = " + paramInt2 + ".  Valid range is [0, " + (this.crSpec[paramInt1].length - 1) + "].");
    }
    double[] arrayOfDouble = new double[this.crSpec[paramInt1].length - 1];
    System.arraycopy(this.crSpec[paramInt1], 0, arrayOfDouble, 0, paramInt2);
    System.arraycopy(this.crSpec[paramInt1], paramInt2 + 1, arrayOfDouble, paramInt2, this.crSpec[paramInt1].length - paramInt2 - 1);
    this.crSpec[paramInt1] = arrayOfDouble;
    ListIterator localListIterator = this.list.listIterator(0);
    while (localListIterator.hasNext())
    {
      Entry localEntry = (Entry)localListIterator.next();
      if (localEntry.cr1[paramInt1] > paramInt2) {
        localEntry.cr1[paramInt1] -= 1;
      }
      if (localEntry.cr2[paramInt1] > paramInt2) {
        localEntry.cr2[paramInt1] -= 1;
      }
    }
    this.dirty = true;
  }
  
  public String toString()
  {
    String str = "TableLayout {{";
    int i;
    if (this.crSpec[0].length > 0)
    {
      for (i = 0; i < this.crSpec[0].length - 1; i++) {
        str = str + this.crSpec[0][i] + ", ";
      }
      str = str + this.crSpec[0][(this.crSpec[0].length - 1)] + "}, {";
    }
    else
    {
      str = str + "}, {";
    }
    if (this.crSpec[1].length > 0)
    {
      for (i = 0; i < this.crSpec[1].length - 1; i++) {
        str = str + this.crSpec[1][i] + ", ";
      }
      str = str + this.crSpec[1][(this.crSpec[1].length - 1)] + "}}";
    }
    else
    {
      str = str + "}}";
    }
    return str;
  }
  
  public List getInvalidEntry()
  {
    LinkedList localLinkedList = new LinkedList();
    try
    {
      ListIterator localListIterator = this.list.listIterator(0);
      while (localListIterator.hasNext())
      {
        Entry localEntry = (Entry)localListIterator.next();
        if ((localEntry.cr1[1] < 0) || (localEntry.cr1[0] < 0) || (localEntry.cr2[1] >= this.crSpec[1].length) || (localEntry.cr2[0] >= this.crSpec[0].length)) {
          localLinkedList.add(localEntry.copy());
        }
      }
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new RuntimeException("Unexpected CloneNotSupportedException");
    }
    return localLinkedList;
  }
  
  public List getOverlappingEntry()
  {
    LinkedList localLinkedList = new LinkedList();
    try
    {
      int i = this.list.size();
      if (i == 0) {
        return localLinkedList;
      }
      Entry[] arrayOfEntry = (Entry[])this.list.toArray(new Entry[i]);
      for (int j = 1; j < i; j++) {
        for (int k = j - 1; k >= 0; k--) {
          if (((arrayOfEntry[k].cr1[0] >= arrayOfEntry[j].cr1[0]) && (arrayOfEntry[k].cr1[0] <= arrayOfEntry[j].cr2[0]) && (arrayOfEntry[k].cr1[1] >= arrayOfEntry[j].cr1[1]) && (arrayOfEntry[k].cr1[1] <= arrayOfEntry[j].cr2[1])) || ((arrayOfEntry[k].cr2[0] >= arrayOfEntry[j].cr1[0]) && (arrayOfEntry[k].cr2[0] <= arrayOfEntry[j].cr2[0]) && (arrayOfEntry[k].cr2[1] >= arrayOfEntry[j].cr1[1]) && (arrayOfEntry[k].cr2[1] <= arrayOfEntry[j].cr2[1]))) {
            localLinkedList.add(arrayOfEntry[k].copy());
          }
        }
      }
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new RuntimeException("Unexpected CloneNotSupportedException");
    }
    return localLinkedList;
  }
  
  protected void calculateSize(Container paramContainer)
  {
    Insets localInsets = paramContainer.getInsets();
    Dimension localDimension = paramContainer.getSize();
    int i = localDimension.width - localInsets.left - localInsets.right;
    int j = localDimension.height - localInsets.top - localInsets.bottom;
    if (this.crSpec[0].length > 0) {
      i -= this.hGap * (this.crSpec[0].length - 1);
    }
    if (this.crSpec[1].length > 0) {
      j -= this.vGap * (this.crSpec[1].length - 1);
    }
    this.crSize[0] = new int[this.crSpec[0].length];
    this.crSize[1] = new int[this.crSpec[1].length];
    i = assignAbsoluteSize(0, i);
    j = assignAbsoluteSize(1, j);
    i = assignPrefMinSize(0, i, -3.0D);
    i = assignPrefMinSize(0, i, -2.0D);
    j = assignPrefMinSize(1, j, -3.0D);
    j = assignPrefMinSize(1, j, -2.0D);
    i = assignRelativeSize(0, i);
    j = assignRelativeSize(1, j);
    assignFillSize(0, i);
    assignFillSize(1, j);
    calculateOffset(0, localInsets);
    calculateOffset(1, localInsets);
    this.dirty = false;
    this.oldWidth = localDimension.width;
    this.oldHeight = localDimension.height;
  }
  
  protected int assignAbsoluteSize(int paramInt1, int paramInt2)
  {
    int i = this.crSpec[paramInt1].length;
    for (int j = 0; j < i; j++) {
      if ((this.crSpec[paramInt1][j] >= 1.0D) || (this.crSpec[paramInt1][j] == 0.0D))
      {
        this.crSize[paramInt1][j] = ((int)(this.crSpec[paramInt1][j] + 0.5D));
        paramInt2 -= this.crSize[paramInt1][j];
      }
    }
    return paramInt2;
  }
  
  protected int assignRelativeSize(int paramInt1, int paramInt2)
  {
    int i = paramInt2 < 0 ? 0 : paramInt2;
    int j = this.crSpec[paramInt1].length;
    for (int k = 0; k < j; k++) {
      if ((this.crSpec[paramInt1][k] > 0.0D) && (this.crSpec[paramInt1][k] < 1.0D))
      {
        this.crSize[paramInt1][k] = ((int)(this.crSpec[paramInt1][k] * i + 0.5D));
        paramInt2 -= this.crSize[paramInt1][k];
      }
    }
    return paramInt2;
  }
  
  protected void assignFillSize(int paramInt1, int paramInt2)
  {
    if (paramInt2 <= 0) {
      return;
    }
    int i = 0;
    int j = this.crSpec[paramInt1].length;
    for (int k = 0; k < j; k++) {
      if (this.crSpec[paramInt1][k] == -1.0D) {
        i++;
      }
    }
    k = paramInt2;
    for (int m = 0; m < j; m++) {
      if (this.crSpec[paramInt1][m] == -1.0D)
      {
        this.crSize[paramInt1][m] = (paramInt2 / i);
        k -= this.crSize[paramInt1][m];
      }
    }
    for (m = j - 1; (m >= 0) && (k > 0); m--) {
      if (this.crSpec[paramInt1][m] == -1.0D)
      {
        this.crSize[paramInt1][m] += 1;
        k--;
      }
    }
  }
  
  protected void calculateOffset(int paramInt, Insets paramInsets)
  {
    int i = this.crSpec[paramInt].length;
    this.crOffset[paramInt] = new int[i + 1];
    this.crOffset[paramInt][0] = (paramInt == 0 ? paramInsets.left : paramInsets.top);
    for (int j = 0; j < i; j++) {
      this.crOffset[paramInt][(j + 1)] = (this.crOffset[paramInt][j] + this.crSize[paramInt][j]);
    }
  }
  
  protected int assignPrefMinSize(int paramInt1, int paramInt2, double paramDouble)
  {
    int i = this.crSpec[paramInt1].length;
    for (int j = 0; j < i; j++) {
      if (this.crSpec[paramInt1][j] == paramDouble)
      {
        int k = 0;
        ListIterator localListIterator = this.list.listIterator(0);
        while (localListIterator.hasNext())
        {
          Entry localEntry = (Entry)localListIterator.next();
          if ((localEntry.cr1[paramInt1] >= 0) && (localEntry.cr2[paramInt1] < i)) {
            if ((localEntry.cr1[paramInt1] <= j) && (localEntry.cr2[paramInt1] >= j))
            {
              Dimension localDimension = paramDouble == -2.0D ? localEntry.component.getPreferredSize() : localEntry.component.getMinimumSize();
              int m = paramInt1 == 0 ? localDimension.width : localDimension == null ? 0 : localDimension.height;
              int n = 0;
              int i1;
              if (paramDouble == -2.0D) {
                for (i1 = localEntry.cr1[paramInt1];; i1++)
                {
                  if (i1 > localEntry.cr2[paramInt1]) {
                    break label300;
                  }
                  if ((this.crSpec[paramInt1][i1] >= 0.0D) || (this.crSpec[paramInt1][i1] == -3.0D)) {
                    m -= this.crSize[paramInt1][i1];
                  } else if (this.crSpec[paramInt1][i1] == -2.0D) {
                    n++;
                  } else {
                    if (this.crSpec[paramInt1][i1] == -1.0D) {
                      break;
                    }
                  }
                }
              } else {
                label300:
                for (i1 = localEntry.cr1[paramInt1];; i1++)
                {
                  if (i1 > localEntry.cr2[paramInt1]) {
                    break label418;
                  }
                  if (this.crSpec[paramInt1][i1] >= 0.0D) {
                    m -= this.crSize[paramInt1][i1];
                  } else if ((this.crSpec[paramInt1][i1] == -2.0D) || (this.crSpec[paramInt1][i1] == -3.0D)) {
                    n++;
                  } else {
                    if (this.crSpec[paramInt1][i1] == -1.0D) {
                      break;
                    }
                  }
                }
              }
              label418:
              m = (int)Math.ceil(m / n);
              if (k < m) {
                k = m;
              }
            }
          }
        }
        this.crSize[paramInt1][j] = k;
        paramInt2 -= k;
      }
    }
    return paramInt2;
  }
  
  public void layoutContainer(Container paramContainer)
  {
    Dimension localDimension = paramContainer.getSize();
    if ((this.dirty) || (localDimension.width != this.oldWidth) || (localDimension.height != this.oldHeight)) {
      calculateSize(paramContainer);
    }
    ComponentOrientation localComponentOrientation = getComponentOrientation(paramContainer);
    int i = (localComponentOrientation != null) && (!localComponentOrientation.isLeftToRight()) ? 1 : 0;
    Insets localInsets = paramContainer.getInsets();
    Component[] arrayOfComponent = paramContainer.getComponents();
    for (int j = 0; j < arrayOfComponent.length; j++) {
      try
      {
        ListIterator localListIterator = this.list.listIterator(0);
        for (Entry localEntry = null; localListIterator.hasNext(); localEntry = null)
        {
          localEntry = (Entry)localListIterator.next();
          if (localEntry.component == arrayOfComponent[j]) {
            break;
          }
        }
        if (localEntry == null)
        {
          arrayOfComponent[j].setBounds(0, 0, 0, 0);
        }
        else
        {
          int k = 0;
          int m = 0;
          if ((localEntry.alignment[0] != 2) || (localEntry.alignment[1] != 2))
          {
            localObject = arrayOfComponent[j].getPreferredSize();
            k = ((Dimension)localObject).width;
            m = ((Dimension)localObject).height;
          }
          Object localObject = calculateSizeAndOffset(localEntry, k, true);
          int n = localObject[0];
          int i1 = localObject[1];
          localObject = calculateSizeAndOffset(localEntry, m, false);
          int i2 = localObject[0];
          int i3 = localObject[1];
          if (i != 0) {
            n = localDimension.width - n - i1 + localInsets.left - localInsets.right;
          }
          arrayOfComponent[j].setBounds(n, i2, i1, i3);
        }
      }
      catch (Exception localException)
      {
        arrayOfComponent[j].setBounds(0, 0, 0, 0);
      }
    }
  }
  
  protected ComponentOrientation getComponentOrientation(Container paramContainer)
  {
    ComponentOrientation localComponentOrientation = null;
    try
    {
      if (checkForComponentOrientationSupport)
      {
        methodGetComponentOrientation = Class.forName("java.awt.Container").getMethod("getComponentOrientation", new Class[0]);
        checkForComponentOrientationSupport = false;
      }
      if (methodGetComponentOrientation != null) {
        localComponentOrientation = (ComponentOrientation)methodGetComponentOrientation.invoke(paramContainer, new Object[0]);
      }
    }
    catch (Exception localException) {}
    return localComponentOrientation;
  }
  
  protected int[] calculateSizeAndOffset(Entry paramEntry, int paramInt, boolean paramBoolean)
  {
    int[] arrayOfInt1 = paramBoolean ? this.crOffset[0] : this.crOffset[1];
    int i = paramBoolean ? paramEntry.alignment[0] : paramEntry.alignment[1];
    int j = paramBoolean ? arrayOfInt1[(paramEntry.cr2[0] + 1)] - arrayOfInt1[paramEntry.cr1[0]] : arrayOfInt1[(paramEntry.cr2[1] + 1)] - arrayOfInt1[paramEntry.cr1[1]];
    int k;
    if ((i == 2) || (j < paramInt)) {
      k = j;
    } else {
      k = paramInt;
    }
    if ((paramBoolean) && (i == 4)) {
      i = 0;
    }
    if ((paramBoolean) && (i == 5)) {
      i = 3;
    }
    int m;
    switch (i)
    {
    case 0: 
      m = arrayOfInt1[paramEntry.cr1[1]];
      break;
    case 3: 
      m = arrayOfInt1[(paramEntry.cr2[1] + 1)] - k;
      break;
    case 1: 
      m = arrayOfInt1[paramEntry.cr1[1]] + (j - k >> 1);
      break;
    case 2: 
      m = arrayOfInt1[paramEntry.cr1[1]];
      break;
    default: 
      m = 0;
    }
    int n;
    if (paramBoolean)
    {
      m += this.hGap * paramEntry.cr1[0];
      n = this.hGap * (paramEntry.cr2[0] - paramEntry.cr1[0]);
      switch (i)
      {
      case 3: 
        m += n;
        break;
      case 1: 
        m += (n >> 1);
        break;
      case 2: 
        k += n;
      }
    }
    else
    {
      m += this.vGap * paramEntry.cr1[1];
      n = this.vGap * (paramEntry.cr2[1] - paramEntry.cr1[1]);
      switch (i)
      {
      case 3: 
        m += n;
        break;
      case 1: 
        m += (n >> 1);
        break;
      case 2: 
        k += n;
      }
    }
    int[] arrayOfInt2 = { m, k };
    return arrayOfInt2;
  }
  
  public Dimension preferredLayoutSize(Container paramContainer)
  {
    return calculateLayoutSize(paramContainer, -2.0D);
  }
  
  public Dimension minimumLayoutSize(Container paramContainer)
  {
    return calculateLayoutSize(paramContainer, -3.0D);
  }
  
  protected Dimension calculateLayoutSize(Container paramContainer, double paramDouble)
  {
    Entry[] arrayOfEntry = (Entry[])this.list.toArray(new Entry[this.list.size()]);
    int i = arrayOfEntry.length;
    Dimension[] arrayOfDimension = new Dimension[i];
    for (int j = 0; j < i; j++) {
      arrayOfDimension[j] = (paramDouble == -2.0D ? arrayOfEntry[j].component.getPreferredSize() : arrayOfEntry[j].component.getMinimumSize());
    }
    j = calculateLayoutSize(paramContainer, 0, paramDouble, arrayOfEntry, arrayOfDimension);
    int k = calculateLayoutSize(paramContainer, 1, paramDouble, arrayOfEntry, arrayOfDimension);
    Insets localInsets = paramContainer.getInsets();
    j += localInsets.left + localInsets.right;
    k += localInsets.top + localInsets.bottom;
    return new Dimension(j, k);
  }
  
  protected int calculateLayoutSize(Container paramContainer, int paramInt, double paramDouble, Entry[] paramArrayOfEntry, Dimension[] paramArrayOfDimension)
  {
    int i = 0;
    int m = this.crSpec[paramInt].length;
    double d1 = 1.0D;
    int n = 0;
    for (int k = 0; k < m; k++) {
      if ((this.crSpec[paramInt][k] > 0.0D) && (this.crSpec[paramInt][k] < 1.0D)) {
        d1 -= this.crSpec[paramInt][k];
      } else if (this.crSpec[paramInt][k] == -1.0D) {
        n++;
      }
    }
    if (n > 1) {
      d1 /= n;
    }
    if (d1 < 0.0D) {
      d1 = 0.0D;
    }
    this.crSize[paramInt] = new int[m];
    assignAbsoluteSize(paramInt, 0);
    assignPrefMinSize(paramInt, 0, -3.0D);
    assignPrefMinSize(paramInt, 0, -2.0D);
    int[] arrayOfInt = new int[m];
    for (k = 0; k < m; k++) {
      if ((this.crSpec[paramInt][k] == -2.0D) || (this.crSpec[paramInt][k] == -3.0D)) {
        arrayOfInt[k] = this.crSize[paramInt][k];
      }
    }
    int i1 = this.crSpec[0].length;
    int i2 = this.crSpec[1].length;
    int i3 = paramArrayOfEntry.length;
    for (int i4 = 0; i4 < i3; i4++)
    {
      Entry localEntry = paramArrayOfEntry[i4];
      if ((localEntry.cr1[0] >= 0) && (localEntry.cr1[0] < i1) && (localEntry.cr2[0] < i1) && (localEntry.cr1[1] >= 0) && (localEntry.cr1[1] < i2) && (localEntry.cr2[1] < i2))
      {
        Dimension localDimension = paramArrayOfDimension[i4];
        int i5 = paramInt == 0 ? localDimension.width : localDimension.height;
        for (k = localEntry.cr1[paramInt]; k <= localEntry.cr2[paramInt]; k++) {
          if (this.crSpec[paramInt][k] >= 1.0D) {
            i5 = (int)(i5 - this.crSpec[paramInt][k]);
          } else if ((this.crSpec[paramInt][k] == -2.0D) || (this.crSpec[paramInt][k] == -3.0D)) {
            i5 -= arrayOfInt[k];
          }
        }
        double d2 = 0.0D;
        for (k = localEntry.cr1[paramInt]; k <= localEntry.cr2[paramInt]; k++) {
          if ((this.crSpec[paramInt][k] > 0.0D) && (this.crSpec[paramInt][k] < 1.0D)) {
            d2 += this.crSpec[paramInt][k];
          } else if ((this.crSpec[paramInt][k] == -1.0D) && (d1 != 0.0D)) {
            d2 += d1;
          }
        }
        int j;
        if (d2 == 0.0D) {
          j = 0;
        } else {
          j = (int)(i5 / d2 + 0.5D);
        }
        if (i < j) {
          i = j;
        }
      }
    }
    i4 = i;
    for (k = 0; k < m; k++) {
      if (this.crSpec[paramInt][k] >= 1.0D) {
        i4 += (int)(this.crSpec[paramInt][k] + 0.5D);
      } else if ((this.crSpec[paramInt][k] == -2.0D) || (this.crSpec[paramInt][k] == -3.0D)) {
        i4 += arrayOfInt[k];
      }
    }
    if (m > 0) {
      i4 += (paramInt == 0 ? this.hGap : this.vGap) * (m - 1);
    }
    return i4;
  }
  
  public void addLayoutComponent(String paramString, Component paramComponent)
  {
    addLayoutComponent(paramComponent, paramString);
  }
  
  public void addLayoutComponent(Component paramComponent, Object paramObject)
  {
    if ((paramObject instanceof String))
    {
      paramObject = new TableLayoutConstraints((String)paramObject);
      this.list.add(new Entry(paramComponent, (TableLayoutConstraints)paramObject));
      this.dirty = true;
    }
    else if ((paramObject instanceof TableLayoutConstraints))
    {
      this.list.add(new Entry(paramComponent, (TableLayoutConstraints)paramObject));
      this.dirty = true;
    }
    else
    {
      if (paramObject == null) {
        throw new IllegalArgumentException("No constraint for the component");
      }
      throw new IllegalArgumentException("Cannot accept a constraint of class " + paramObject.getClass());
    }
  }
  
  public void removeLayoutComponent(Component paramComponent)
  {
    ListIterator localListIterator = this.list.listIterator(0);
    while (localListIterator.hasNext())
    {
      Entry localEntry = (Entry)localListIterator.next();
      if (localEntry.component == paramComponent) {
        localListIterator.remove();
      }
    }
    this.dirty = true;
  }
  
  public Dimension maximumLayoutSize(Container paramContainer)
  {
    return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
  }
  
  public float getLayoutAlignmentX(Container paramContainer)
  {
    return 0.5F;
  }
  
  public float getLayoutAlignmentY(Container paramContainer)
  {
    return 0.5F;
  }
  
  public void invalidateLayout(Container paramContainer)
  {
    this.dirty = true;
  }
  
  public static class Entry
    implements Cloneable
  {
    public Component component;
    public int[] cr1;
    public int[] cr2;
    public int[] alignment;
    
    public Entry(Component paramComponent, TableLayoutConstraints paramTableLayoutConstraints)
    {
      int[] arrayOfInt1 = { paramTableLayoutConstraints.col1, paramTableLayoutConstraints.row1 };
      int[] arrayOfInt2 = { paramTableLayoutConstraints.col2, paramTableLayoutConstraints.row2 };
      int[] arrayOfInt3 = { paramTableLayoutConstraints.hAlign, paramTableLayoutConstraints.vAlign };
      this.cr1 = arrayOfInt1;
      this.cr2 = arrayOfInt2;
      this.alignment = arrayOfInt3;
      this.component = paramComponent;
    }
    
    public Object copy()
      throws CloneNotSupportedException
    {
      return clone();
    }
    
    public String toString()
    {
      TableLayoutConstraints localTableLayoutConstraints = new TableLayoutConstraints(this.cr1[0], this.cr1[1], this.cr2[0], this.cr2[1], this.alignment[0], this.alignment[1]);
      return "(" + localTableLayoutConstraints + ") " + this.component;
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/info/clearthought/layout/TableLayout.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */