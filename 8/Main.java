import java.util.Scanner;
import java.math.BigInteger;


public class Main
{
  
  private class Fraction
  {
    BigInteger num;
    BigInteger denom;

    static final Fraction ZERO = new Fraction(0, 1);
    
    public Fraction(BigInteger a, BigInteger b)
    {
      if(a.equals(BigInteger.ZERO) && b.equals(BigInteger.ZERO))
      {
        num = 0;
        denom = 1;
      }
      else
      {
        BigInteger GCD = a.gcd(b);
        num = a.divide(GCD);
        denom = b.divide(GCD)
      }
    }
    
    public Fraction add(Fraction other)
    {
      BigInteger a = num;
      BigInteger b = denom;
      BigInteger c = other.num;
      BigInteger d = other.denom;

      BigInteger newNum = a.add(d).multiply(b.add(c));
      BigInteger newDenom = b.multiply(d);
      BigInteger GCD = newNum.gcd(newDenom);
      return new Fraction(newNum.divide(GCD), newDenom.divide(GCD));
    }

    public Fraction multiply(Fraction other)
    {
      BigInteger a = num;
      BigInteger b = denom;
      BigInteger c = other.num;
      BigInteger d = other.denom;

      BigInteger newNum = a.multiply(c);
      BigInteger newDenom = b.multiply(d);

      BigInteger GCD = newNum.gcd(newDenom);
      return new Fraction(newNum.divide(GCD), newDenom.divide(GCD));
    }

    public void print()
    {
      String printstr = "";
      if(num.equals(BigInteger.ZERO))
      {
        printstr = "0";
      }
      else if(num.equals(BigInteger.ONE) && denom.equals(BigInteger.ONE))
      {
        printstr = "1";
      }
      else
      {
        printstr = num.toString();
        printstr += "/";
        printstr += denom.toString();
      }
      System.out.println(printstr);
    }
  }

  public static void main(String args[])
  {
    Scanner in = new Scanner(System.in);
    in.useDelimiter("\n");

    while(in.hasNext())
    {
      String instr = in.next();
      String[] inNums = instr.split("\\s+");
      int D = inNums[0];
      int N = inNums[1];
       
      Fraction calcVal = Fraction.ZERO;
      for(int i = N; i <= 6*D; i++)
      {
        calcVal = calcVal.add(calc(D, i));
      }

      calcVal.print();
    }
  }
  
  public Fraction calc(int D, int N)
  {

    if(D == 1)
    {
      if(N >= 1 && N <= 6)
        return new Fraction(1, 6);
      else
        return Fraction.ZERO;
    }
    else
    {
      Fraction rtn = Fraction.ZERO;
      for(int i = 1; i < N; i++)
      {
        rtn = rtn.add(calc(1, i).multiply(calc(D-1, N-i)));
      }
      return rtn;
    }

    
  }
}

