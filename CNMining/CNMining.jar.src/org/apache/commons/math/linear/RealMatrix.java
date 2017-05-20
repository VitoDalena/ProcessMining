package org.apache.commons.math.linear;

public abstract interface RealMatrix
{
  public abstract RealMatrix copy();
  
  public abstract RealMatrix add(RealMatrix paramRealMatrix)
    throws IllegalArgumentException;
  
  public abstract RealMatrix subtract(RealMatrix paramRealMatrix)
    throws IllegalArgumentException;
  
  public abstract RealMatrix scalarAdd(double paramDouble);
  
  public abstract RealMatrix scalarMultiply(double paramDouble);
  
  public abstract RealMatrix multiply(RealMatrix paramRealMatrix)
    throws IllegalArgumentException;
  
  public abstract RealMatrix preMultiply(RealMatrix paramRealMatrix)
    throws IllegalArgumentException;
  
  public abstract double[][] getData();
  
  public abstract double getNorm();
  
  public abstract RealMatrix getSubMatrix(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws MatrixIndexException;
  
  public abstract RealMatrix getSubMatrix(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
    throws MatrixIndexException;
  
  public abstract RealMatrix getRowMatrix(int paramInt)
    throws MatrixIndexException;
  
  public abstract RealMatrix getColumnMatrix(int paramInt)
    throws MatrixIndexException;
  
  public abstract double[] getRow(int paramInt)
    throws MatrixIndexException;
  
  public abstract double[] getColumn(int paramInt)
    throws MatrixIndexException;
  
  public abstract double getEntry(int paramInt1, int paramInt2)
    throws MatrixIndexException;
  
  public abstract RealMatrix transpose();
  
  public abstract RealMatrix inverse()
    throws InvalidMatrixException;
  
  public abstract double getDeterminant();
  
  public abstract boolean isSquare();
  
  public abstract boolean isSingular();
  
  public abstract int getRowDimension();
  
  public abstract int getColumnDimension();
  
  public abstract double getTrace();
  
  public abstract double[] operate(double[] paramArrayOfDouble)
    throws IllegalArgumentException;
  
  public abstract double[] preMultiply(double[] paramArrayOfDouble)
    throws IllegalArgumentException;
  
  public abstract double[] solve(double[] paramArrayOfDouble)
    throws IllegalArgumentException, InvalidMatrixException;
  
  public abstract RealMatrix solve(RealMatrix paramRealMatrix)
    throws IllegalArgumentException, InvalidMatrixException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/linear/RealMatrix.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */