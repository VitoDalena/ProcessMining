package org.jgraph.util;

import java.util.Arrays;

public class Spline
{
  private double[] xx;
  private double[] yy;
  private double[] a;
  private double[] b;
  private double[] c;
  private double[] d;
  private int storageIndex = 0;
  
  public Spline(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    setValues(paramArrayOfDouble1, paramArrayOfDouble2);
  }
  
  public void setValues(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    this.xx = paramArrayOfDouble1;
    this.yy = paramArrayOfDouble2;
    if (paramArrayOfDouble1.length > 1) {
      calculateCoefficients();
    }
  }
  
  public double getValue(double paramDouble)
  {
    if (this.xx.length == 0) {
      return NaN.0D;
    }
    if (this.xx.length == 1)
    {
      if (this.xx[0] == paramDouble) {
        return this.yy[0];
      }
      return NaN.0D;
    }
    int i = Arrays.binarySearch(this.xx, paramDouble);
    if (i > 0) {
      return this.yy[i];
    }
    i = -(i + 1) - 1;
    if (i < 0) {
      return this.yy[0];
    }
    return this.a[i] + this.b[i] * (paramDouble - this.xx[i]) + this.c[i] * Math.pow(paramDouble - this.xx[i], 2.0D) + this.d[i] * Math.pow(paramDouble - this.xx[i], 3.0D);
  }
  
  public double getFastValue(double paramDouble)
  {
    if ((this.storageIndex <= -1) || (this.storageIndex >= this.xx.length - 1) || (paramDouble <= this.xx[this.storageIndex]) || (paramDouble >= this.xx[(this.storageIndex + 1)]))
    {
      int i = Arrays.binarySearch(this.xx, paramDouble);
      if (i > 0) {
        return this.yy[i];
      }
      i = -(i + 1) - 1;
      this.storageIndex = i;
    }
    if (this.storageIndex < 0) {
      return this.yy[0];
    }
    double d1 = paramDouble - this.xx[this.storageIndex];
    return this.a[this.storageIndex] + this.b[this.storageIndex] * d1 + this.c[this.storageIndex] * (d1 * d1) + this.d[this.storageIndex] * (d1 * d1 * d1);
  }
  
  public boolean checkValues()
  {
    return this.xx.length >= 2;
  }
  
  public double getDx(double paramDouble)
  {
    if ((this.xx.length == 0) || (this.xx.length == 1)) {
      return 0.0D;
    }
    int i = Arrays.binarySearch(this.xx, paramDouble);
    if (i < 0) {
      i = -(i + 1) - 1;
    }
    return this.b[i] + 2.0D * this.c[i] * (paramDouble - this.xx[i]) + 3.0D * this.d[i] * Math.pow(paramDouble - this.xx[i], 2.0D);
  }
  
  private void calculateCoefficients()
  {
    int i = this.yy.length;
    this.a = new double[i];
    this.b = new double[i];
    this.c = new double[i];
    this.d = new double[i];
    if (i == 2)
    {
      this.a[0] = this.yy[0];
      this.b[0] = (this.yy[1] - this.yy[0]);
      return;
    }
    double[] arrayOfDouble1 = new double[i - 1];
    for (int j = 0; j < i - 1; j++)
    {
      this.a[j] = this.yy[j];
      arrayOfDouble1[j] = (this.xx[(j + 1)] - this.xx[j]);
      if (arrayOfDouble1[j] == 0.0D) {
        arrayOfDouble1[j] = 0.01D;
      }
    }
    this.a[(i - 1)] = this.yy[(i - 1)];
    double[][] arrayOfDouble = new double[i - 2][i - 2];
    double[] arrayOfDouble2 = new double[i - 2];
    for (int k = 0; k < i - 2; k++)
    {
      arrayOfDouble2[k] = (3.0D * ((this.yy[(k + 2)] - this.yy[(k + 1)]) / arrayOfDouble1[(k + 1)] - (this.yy[(k + 1)] - this.yy[k]) / arrayOfDouble1[k]));
      arrayOfDouble[k][k] = (2.0D * (arrayOfDouble1[k] + arrayOfDouble1[(k + 1)]));
      if (k > 0) {
        arrayOfDouble[k][(k - 1)] = arrayOfDouble1[k];
      }
      if (k < i - 3) {
        arrayOfDouble[k][(k + 1)] = arrayOfDouble1[(k + 1)];
      }
    }
    solve(arrayOfDouble, arrayOfDouble2);
    for (k = 0; k < i - 2; k++)
    {
      this.c[(k + 1)] = arrayOfDouble2[k];
      this.b[k] = ((this.a[(k + 1)] - this.a[k]) / arrayOfDouble1[k] - (2.0D * this.c[k] + this.c[(k + 1)]) / 3.0D * arrayOfDouble1[k]);
      this.d[k] = ((this.c[(k + 1)] - this.c[k]) / (3.0D * arrayOfDouble1[k]));
    }
    this.b[(i - 2)] = ((this.a[(i - 1)] - this.a[(i - 2)]) / arrayOfDouble1[(i - 2)] - (2.0D * this.c[(i - 2)] + this.c[(i - 1)]) / 3.0D * arrayOfDouble1[(i - 2)]);
    this.d[(i - 2)] = ((this.c[(i - 1)] - this.c[(i - 2)]) / (3.0D * arrayOfDouble1[(i - 2)]));
  }
  
  public void solve(double[][] paramArrayOfDouble, double[] paramArrayOfDouble1)
  {
    int i = paramArrayOfDouble1.length;
    for (int j = 1; j < i; j++)
    {
      paramArrayOfDouble[j][(j - 1)] /= paramArrayOfDouble[(j - 1)][(j - 1)];
      paramArrayOfDouble[j][j] -= paramArrayOfDouble[(j - 1)][j] * paramArrayOfDouble[j][(j - 1)];
      paramArrayOfDouble1[j] -= paramArrayOfDouble[j][(j - 1)] * paramArrayOfDouble1[(j - 1)];
    }
    paramArrayOfDouble1[(i - 1)] /= paramArrayOfDouble[(i - 1)][(i - 1)];
    for (j = paramArrayOfDouble1.length - 2; j >= 0; j--) {
      paramArrayOfDouble1[j] = ((paramArrayOfDouble1[j] - paramArrayOfDouble[j][(j + 1)] * paramArrayOfDouble1[(j + 1)]) / paramArrayOfDouble[j][j]);
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/util/Spline.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */