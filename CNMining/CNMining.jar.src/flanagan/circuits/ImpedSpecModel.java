package flanagan.circuits;

import flanagan.complex.Complex;

public abstract interface ImpedSpecModel
{
  public abstract Complex modelImpedance(double[] paramArrayOfDouble, double paramDouble);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/circuits/ImpedSpecModel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */